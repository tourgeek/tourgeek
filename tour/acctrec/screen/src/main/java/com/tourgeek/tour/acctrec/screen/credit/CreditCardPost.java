/**
  * @(#)CreditCardPost.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.credit;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourgeek.tour.acctrec.screen.mco.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.acctrec.screen.cash.*;
import com.tourgeek.tour.booking.db.*;

/**
 *  CreditCardPost - Post the new credit card batch.
 */
public class CreditCardPost extends McoPost
{
    /**
     * Default constructor.
     */
    public CreditCardPost()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public CreditCardPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Post the new credit card batch";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new CreditCard(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        
        new CreditCardBatchDist(this);
        
        new ArTrx(this);
        new ArControl(this);
        
        new TrxStatus(this);
        new TransactionType(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        // Don't call super
        Record recTrxStatusRef = ((ReferenceField)this.getMainRecord().getField(Trx.TRX_STATUS_ID)).getReferenceRecord(); // Make sure this TrxStatus is different from the one I use for a key.
        this.removeRecord(recTrxStatusRef);
        
        m_iTrxStatusEntered = ((TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE)).getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.ENTERED);
        ((TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE)).getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.BATCH);
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(TrxStatus.TRX_STATUS_FILE)));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.COUNT), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.TOTAL), CreditCard.AMT_APPLY, false, true));
        
        CreditCardBatchDist recCreditCardBatchDist = (CreditCardBatchDist)this.getRecord(CreditCardBatchDist.CREDIT_CARD_BATCH_DIST_FILE);
        recCreditCardBatchDist.addListener(new SubFileFilter(this.getRecord(CreditCard.CREDIT_CARD_FILE)));
        recCreditCardBatchDist.addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE), CreditCardBatchDist.AMOUNT, false, true));
                
        CreditCard recCreditCard = (CreditCard)this.getRecord(CreditCard.CREDIT_CARD_FILE);
        
        Booking recBooking = (Booking)((ReferenceField)recCreditCard.getField(CreditCard.BOOKING_ID)).getReferenceRecord(this);
        recCreditCard.getField(CreditCard.BOOKING_ID).addListener(new ReadSecondaryHandler(recBooking, null, true, true, true));     // Update record
        ArTrx recArTrx = (ArTrx)this.getRecord(ArTrx.AR_TRX_FILE);
        recBooking.addArDetail(recArTrx, null, false);
        
        recCreditCard.close();
        try   {   // Recount totals
            while (recCreditCard.hasNext())
            {
                recCreditCard.next();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(CreditCard.CREDIT_CARD_FILE);
    }
    /**
     * Return the distribution detail record.
     * @return The dist record.
     */
    public Record getDistRecord()
    {
        return this.getRecord(CreditCardBatchDist.CREDIT_CARD_BATCH_DIST_FILE);
    }
    /**
     * (Optionally) update this detail transaction.
     * @return true if success.
     */
    public boolean updateDetailTrx()
    {
        Record recDetail = this.getDetailRecord();
        try {
            recDetail.edit();
            recDetail.getField(Mco.TRX_STATUS_ID).setValue(m_iTrxStatusEntered);
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        return super.updateDetailTrx();
    }
    /**
     * Setup and post this base transaction.
     * @param recBaseTrx The base transaction to post.
     * @param recTransactionType The transaction type for the TRX posting.
     * @return true If successful.
     */
    public boolean postBaseTrx(BaseTrx recBaseTrx, TransactionType recTransactionType)
    {
        // Step 2b - Post the transaction side of the distribution.
        BaseField fldDrAccountID = this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.CREDIT_CARD_REC_ACCOUNT_ID);
        Record recDetail = this.getDetailRecord();
        try {
            recDetail.writeAndRefresh();
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        double dAmount = recDetail.getField(Mco.AMT_APPLY).getValue();
        boolean bSuccess = recBaseTrx.onPostTrxDist(fldDrAccountID, dAmount, PostingType.TRX_POST);
        return bSuccess;
    }
    /**
     * Post the distribution detail.
     * @param recBaseTrx The base transaction to post.
     * @param recTransactionType The transaction type for the DIST posting.
     * @return true If successful.
     */
    public boolean postDistTrx(BaseTrx recBaseTrx, TransactionType recTransactionType)
    {
        BaseField fldDefAccountID = this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.NON_TOUR_ACCOUNT_ID);
        
        Record recBatchDetail = this.getDetailRecord();
        double dLocalTotal = recBatchDetail.getField(Mco.AMT_APPLY).getValue();
        String strComment = recBatchDetail.getField(Mco.COMMENTS).toString();
        
        return this.postDistTrx(recBaseTrx, dLocalTotal, strComment, fldDefAccountID);
    }

}
