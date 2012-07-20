/**
  * @(#)CashBatchPost.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.cash;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.model.tour.booking.db.*;

/**
 *  CashBatchPost - Post the Cash Receipts.
 */
public class CashBatchPost extends BaseArTrxPostScreen
{
    /**
     * Default constructor.
     */
    public CashBatchPost()
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
    public CashBatchPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Post the Cash Receipts";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new CashBatch(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords(); 
        if (this.getMainRecord().getTableNames(false).equalsIgnoreCase(CashBatchDetail.CASH_BATCH_DETAIL_FILE))
            this.addRecord(((ReferenceField)this.getMainRecord().getField(CashBatchDetail.CASH_BATCH_ID)).getReference(), true);
        else
            new CashBatchDetail(this);
        new CashBatchDist(this);
        
        new ArControl(this);
        
        new ArTrx(this);
        
        new BankTrx(this);
        
        new TransactionType(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().getField(CashBatch.BANK_ACCT_ID).addListener(new InitFieldHandler(this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.AR_BANK_ACCT_ID)));
        
        this.getScreenRecord().getField(CashBatchScreenRecord.USER_ID).moveFieldToThis(this.getMainRecord().getField(CashBatch.USER_ID));
        this.getMainRecord().setKeyArea(CashBatch.USER_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(CashBatchScreenRecord.USER_ID), CashBatch.USER_ID, null, null, null, null));
        
        CashBatchDetail recCashBatchDetail = (CashBatchDetail)this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE);
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(CashBatch.CASH_BATCH_FILE)));
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).addListener(new SubCountHandler(this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_CHECKS_ACTUAL), false, true));
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).addListener(new SubCountHandler(this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_TOTAL_ACTUAL), CashBatchDetail.AMOUNT, false, true));
        
        CashBatchDist recCashBatchDist = (CashBatchDist)this.getRecord(CashBatchDist.CASH_BATCH_DIST_FILE);
        recCashBatchDist.addListener(new SubFileFilter(this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE)));
        recCashBatchDist.addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE), CashBatchDist.AMOUNT, false, true));
        
        BookingModel recBooking = (BookingModel)((ReferenceField)recCashBatchDist.getField(CashBatchDist.BOOKING_ID)).getReferenceRecord(this);
        recCashBatchDist.getField(CashBatchDist.BOOKING_ID).addListener(new ReadSecondaryHandler((Record)recBooking, null, true, true, true));     // Update record
        ArTrx recArTrx = (ArTrx)this.getRecord(ArTrx.AR_TRX_FILE);
        recBooking.addArDetail(recArTrx, null, false);
        
        recCashBatchDetail.close();
        try   {   // Recount totals
            while (recCashBatchDetail.hasNext())
            {
                recCashBatchDetail.next();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
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
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BANK_ACCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.DETAIL_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_CHECKS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_CHECKS_ACTUAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_TOTAL_ACTUAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.setEnabled(false);
        SCannedBox box = new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        box.setEnabled(true);
    }
    /**
     * Get the batch detail record.
     */
    public Record getDetailRecord()
    {
        return this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE);
    }
    /**
     * Return the distribution detail record.
     * @return The dist record.
     */
    public Record getDistRecord()
    {
        return this.getRecord(CashBatchDist.CASH_BATCH_DIST_FILE);
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(BankTrx.BANK_TRX_FILE);
    }
    /**
     * Is the batch header record valid?
     * @return true if valid (if false, set the last error).
     */
    public boolean checkValidHeader()
    {
        boolean bSuccess = super.checkValidHeader();
        if (!bSuccess)
            return bSuccess;
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getMainRecord().getField(CashBatch.BANK_ACCT_ID)).getReference();
        if (recBankAcct == null)
        {
            this.displayError(application.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString("Invalid Bank account, can't post"));
            return false;
        }
        BaseField fldDefAccountID = this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.AR_ACCOUNT_ID);
        if (fldDefAccountID.isNull())
        {
            this.displayError(application.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString("No default account set in control file"));
            return false;
        }
        if (this.getMainRecord().getField(CashBatch.DETAIL_DATE).isNull())
        {
            this.displayError(application.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString("You must have a transaction date, can't post"));
            return false;
        }
        if (((!this.getMainRecord().getField(CashBatch.BATCH_TOTAL).isNull()) && (Math.abs(this.getMainRecord().getField(CashBatch.BATCH_TOTAL).getValue()) != Math.abs(this.getMainRecord().getField(CashBatch.BATCH_TOTAL_ACTUAL).getValue()))) ||
            ((!this.getMainRecord().getField(CashBatch.BATCH_CHECKS).isNull()) && (this.getMainRecord().getField(CashBatch.BATCH_CHECKS).getValue() != this.getMainRecord().getField(CashBatch.BATCH_CHECKS_ACTUAL).getValue())))
        {
            this.displayError(application.getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString("The batch doesn't balance, can't post"));
            return false;
        }
        return true;
    }
    /**
     * Update the base trx to the new status.
     * @return true if successful.
     */
    public boolean updateBaseTrx()
    {
        boolean bSuccess = false;
        // Now post the total deposit amount
        BaseTrx recBaseTrx = this.getBaseTrx();
        Record recCashBatch = this.getMainRecord();
        TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recBaseTrx.getField(BankTrx.TRX_STATUS_ID)).getReferenceRecord();
        try   {
        // Step 2a - Create and write the bank transaction (in BankTrx).
            recBaseTrx.addNew();
            int iTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.PAYMENT);
            recBaseTrx.getField(BankTrx.TRX_STATUS_ID).setValue(iTrxStatusID);
            recBaseTrx.getField(BankTrx.PAYEE_NAME).moveFieldToThis(recTrxStatus.getField(TrxStatus.STATUS_DESC));
            recBaseTrx.getField(BankTrx.TRX_DATE).moveFieldToThis(recCashBatch.getField(CashBatch.DETAIL_DATE));
            recBaseTrx.getField(BankTrx.AMOUNT_LOCAL).setValue(recCashBatch.getField(CashBatch.BATCH_TOTAL_ACTUAL).getValue());
            recBaseTrx.getField(BankTrx.TRX_ENTRY).initField(DBConstants.DONT_DISPLAY);
            recBaseTrx.getField(BankTrx.BANK_ACCT_ID).moveFieldToThis(recCashBatch.getField(CashBatch.BANK_ACCT_ID));
            recBaseTrx.getField(BankTrx.AMOUNT).setValue(recCashBatch.getField(CashBatch.BATCH_TOTAL_ACTUAL).getValue());
            recBaseTrx.getField(BankTrx.EXCHANGE).initField(DBConstants.DONT_DISPLAY);
            recBaseTrx.getField(BankTrx.COMMENTS).moveFieldToThis(recTrxStatus.getField(TrxStatus.STATUS_DESC));
            // Step 2 - Post it to the G/L
            //+recAcctDetail.getDatabase().startTrx();
            // Step 2a - Create and write the bank transaction (in BankTrx).
            bSuccess = recBaseTrx.onPostTrx();
            if (!bSuccess)
                return bSuccess;
        } catch (DBException ex) {
            ex.printStackTrace();
            return false;
        }
        return bSuccess;    // Success
    }
    /**
     * Make sure this batch detail trx is valid.
     * @return True if batch is okay.
     */
    public boolean checkValidDetail()
    {
        boolean bSuccess = super.checkValidDetail();
        return bSuccess;
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
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getMainRecord().getField(CashBatch.BANK_ACCT_ID)).getReference();
        BaseField fldDrAccountID = recBankAcct.getField(BankAcct.ACCOUNT_ID);
        Record recDetail = this.getDetailRecord();
        double dAmount = recDetail.getField(CashBatchDetail.AMOUNT).getValue();
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
        double dLocalTotal = recBatchDetail.getField(CashBatchDetail.AMOUNT).getValue();
        String strComment = recBatchDetail.getField(CashBatchDetail.COMMENTS).toString();
        
        return this.postDistTrx(recBaseTrx, dLocalTotal, strComment, fldDefAccountID);
    }

}
