/**
  * @(#)McoPost.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.mco;

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
import com.tourgeek.tour.acctrec.screen.cash.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.product.air.db.*;
import com.tourgeek.tour.booking.db.*;

/**
 *  McoPost - Post the MCOs.
 */
public class McoPost extends BaseArTrxPostScreen
{
    protected int m_iTrxStatusEntered = 0;
    /**
     * Default constructor.
     */
    public McoPost()
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
    public McoPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Post the MCOs";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Mco(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        
        new McoBatchDist(this);
        
        new ArTrx(this);
        new ArControl(this);
        
        new TrxStatus(this);
        new TransactionType(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CashBatchScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        m_iTrxStatusEntered = ((TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE)).getTrxStatusID(TransactionType.ACCTREC, Mco.MCO_FILE, Mco.ENTERED);
        ((TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE)).getTrxStatusID(TransactionType.ACCTREC, Mco.MCO_FILE, Mco.BATCH);
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(TrxStatus.TRX_STATUS_FILE)));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.COUNT), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.TOTAL), Mco.AMT_APPLY, false, true));
        
        McoBatchDist recMcoBatchDist = (McoBatchDist)this.getRecord(McoBatchDist.MCO_BATCH_DIST_FILE);
        recMcoBatchDist.addListener(new SubFileFilter(this.getRecord(Mco.MCO_FILE)));
        recMcoBatchDist.addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE), McoBatchDist.AMOUNT, false, true));
        
        Mco recMco = (Mco)this.getRecord(Mco.MCO_FILE);
        
        Booking recBooking = (Booking)((ReferenceField)recMco.getField(Mco.BOOKING_ID)).getReferenceRecord(this);
        recMco.getField(Mco.BOOKING_ID).addListener(new ReadSecondaryHandler(recBooking, null, true, true, true));     // Update record
        ArTrx recArTrx = (ArTrx)this.getRecord(ArTrx.AR_TRX_FILE);
        recBooking.addArDetail(recArTrx, null, false);
        
        recMco.close();
        try   {   // Recount totals
            while (recMco.hasNext())
            {
                recMco.next();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(CashBatchScreenRecord.CASH_BATCH_SCREEN_RECORD_FILE).getField(CashBatchScreenRecord.COUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatchScreenRecord.CASH_BATCH_SCREEN_RECORD_FILE).getField(CashBatchScreenRecord.TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, "Post", "Post", "Post", null);
    }
    /**
     * Get the batch detail record.
     */
    public Record getDetailRecord()
    {
        return this.getMainRecord();
    }
    /**
     * Return the distribution detail record.
     * @return The dist record.
     */
    public Record getDistRecord()
    {
        return this.getRecord(McoBatchDist.MCO_BATCH_DIST_FILE);
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(Mco.MCO_FILE);
    }
    /**
     * Is the batch header record valid?
     * @return true if valid (if false, set the last error).
     */
    public boolean checkValidHeader()
    {
        // Don't call inherited as there is no batch header on MCOs
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        BaseField fldDefAccountID = this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.AR_ACCOUNT_ID);
        if (fldDefAccountID.isNull())
        {
            this.displayError(application.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString("No default account set in control file"));
            return false;
        }
        return true;    // Success
    }
    /**
     * Make sure this batch detail trx is valid.
     * @return True if batch is okay.
     */
    public boolean checkValidDetail()
    {
        return super.checkValidDetail();
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
        BaseField fldDrAccountID = this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.MCO_REC_ACCOUNT_ID);
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
    /**
     * Remove this batch detail transaction and the distribution.
     * @return true if successful.
     */
    public boolean removeDetailTrx()
    {
        // Step 3 - Delete the batch (if not recurring)
        Record recBankTrxBatchDetail = this.getDetailRecord();
        Record recBankTrxBatchDist = this.getDistRecord();
        recBankTrxBatchDist.close();
        try   {
            while (recBankTrxBatchDist.hasNext())
            {
                recBankTrxBatchDist.next();
                recBankTrxBatchDist.edit();
                recBankTrxBatchDist.remove();
            }
            // Don't remove the detail trx as it is not in a batch.
        } catch (DBException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Delete the batch header.
     */
    public boolean removeTrxHeader()
    {
        this.getScreenRecord().getField(CashBatchScreenRecord.COUNT).setData(null);
        this.getScreenRecord().getField(CashBatchScreenRecord.TOTAL).setData(null);
        String strSuccess = "Posted successfully";
        strSuccess = this.getTask().getApplication().getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(strSuccess);
        this.getTask().setStatusText(this.getTask().getString(strSuccess));
        return true;    // Not required for Mcos
    }

}
