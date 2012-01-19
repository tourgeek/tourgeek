/**
 * @(#)CreditCardPost.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.credit;

import java.awt.*;
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctrec.screen.mco.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.booking.db.*;

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
        Record recTrxStatusRef = ((ReferenceField)this.getMainRecord().getField(Trx.kTrxStatusID)).getReferenceRecord(); // Make sure this TrxStatus is different from the one I use for a key.
        this.removeRecord(recTrxStatusRef);
        
        m_iTrxStatusEntered = ((TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile)).getTrxStatusID(TransactionType.ACCTREC, CreditCard.kCreditCardFile, CreditCard.ENTERED);
        ((TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile)).getTrxStatusID(TransactionType.ACCTREC, CreditCard.kCreditCardFile, CreditCard.BATCH);
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(TrxStatus.kTrxStatusFile)));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.kCount), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.kTotal), CreditCard.kAmtApply, false, true));
        
        CreditCardBatchDist recCreditCardBatchDist = (CreditCardBatchDist)this.getRecord(CreditCardBatchDist.kCreditCardBatchDistFile);
        recCreditCardBatchDist.addListener(new SubFileFilter(this.getRecord(CreditCard.kCreditCardFile)));
        recCreditCardBatchDist.addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.kChangeBalance), CreditCardBatchDist.kAmount, false, true));
                
        CreditCard recCreditCard = (CreditCard)this.getRecord(CreditCard.kCreditCardFile);
        
        Booking recBooking = (Booking)((ReferenceField)recCreditCard.getField(CreditCard.kBookingID)).getReferenceRecord(this);
        recCreditCard.getField(CreditCard.kBookingID).addListener(new ReadSecondaryHandler(recBooking, DBConstants.MAIN_KEY_AREA, true, true, true));     // Update record
        ArTrx recArTrx = (ArTrx)this.getRecord(ArTrx.kArTrxFile);
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
        return (BaseTrx)this.getRecord(CreditCard.kCreditCardFile);
    }
    /**
     * Return the distribution detail record.
     * @return The dist record.
     */
    public Record getDistRecord()
    {
        return this.getRecord(CreditCardBatchDist.kCreditCardBatchDistFile);
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
            recDetail.getField(Mco.kTrxStatusID).setValue(m_iTrxStatusEntered);
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
        BaseField fldDrAccountID = this.getRecord(ArControl.kArControlFile).getField(ArControl.kCreditCardRecAccountID);
        Record recDetail = this.getDetailRecord();
        try {
            recDetail.writeAndRefresh();
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        double dAmount = recDetail.getField(Mco.kAmtApply).getValue();
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
        BaseField fldDefAccountID = this.getRecord(ArControl.kArControlFile).getField(ArControl.kNonTourAccountID);
        
        Record recBatchDetail = this.getDetailRecord();
        double dLocalTotal = recBatchDetail.getField(Mco.kAmtApply).getValue();
        String strComment = recBatchDetail.getField(Mco.kComments).toString();
        
        return this.postDistTrx(recBaseTrx, dLocalTotal, strComment, fldDefAccountID);
    }

}
