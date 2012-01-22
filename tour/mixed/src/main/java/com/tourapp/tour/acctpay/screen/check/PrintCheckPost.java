/**
 * @(#)PrintCheckPost.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.check;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctrec.screen.refund.*;

/**
 *  PrintCheckPost - Post the A/P Checks.
 */
public class PrintCheckPost extends BaseApTrxPostScreen
{
    protected int m_iPaymentTrxStatusID;
    protected int m_iPrepaymentTrxStatusID;
    protected int m_iTrxGroupID = -1;
    /**
     * Default constructor.
     */
    public PrintCheckPost()
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
    public PrintCheckPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        m_iPaymentTrxStatusID = 0;
        m_iPrepaymentTrxStatusID = 0;
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Post the A/P Checks";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new PaymentRequest(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApTrx(this);
        new PaymentHistory(this);
        new BankTrx(this);
        
        new TrxStatus(this);
        new TrxGroup(this);
        
        new ApControl(this);
        
        new AcctDetail(this);
        new AcctDetailDist(this);
        new Period(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new PrintCheckScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        if (this.getProperty(this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).getFieldName()) != null)
            this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).addListener(new InitFieldHandler(this.getProperty(this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).getFieldName())));
        else
            this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kApBankAcctID)));
        Record recBankAcct = ((ReferenceField)this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID)).getReference();  // Make sure this record is referenced
        
        FieldListener listener = new ReadSecondaryHandler(recBankAcct);
        this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).addListener(listener);
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        
        this.getMainRecord().addListener(new SubFileFilter(recBankAcct));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kReportCount), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kReportTotal), PaymentRequest.kAmount, false, true));
        
        Record recApTrx = this.getRecord(ApTrx.kApTrxFile);
        recApTrx.setKeyArea(ApTrx.kVendorIDKey);
        recApTrx.addListener(new SubFileFilter(this.getMainRecord().getField(PaymentRequest.kVendorID), ApTrx.kVendorID, null, -1, null, -1));
        
        recBankAcct.addListener(new MoveOnValidHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kNextCheckNo), recBankAcct.getField(BankAcct.kNextCheck)));
        this.getScreenRecord().getField(PrintCheckScreenRecord.kNextCheckNo).setSFieldToProperty();
        this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckDate).setSFieldToProperty();
        this.getScreenRecord().getField(PrintCheckScreenRecord.kChecksToPrint).setSFieldToProperty();
        
        this.getMainRecord().addListener(new BumpCheckNoHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckNo), this.getScreenRecord().getField(PrintCheckScreenRecord.kNextCheckNo)));
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(PaymentRequest.kVendorID)).getReferenceRecord(this);
        this.getMainRecord().getField(PaymentRequest.kVendorID).addListener(new ReadSecondaryHandler(recVendor));
        recVendor.addListener(new MoveOnValidHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kPayee), recVendor.getField(Vendor.kVendorName)));
        this.getMainRecord().addListener(new MoveOnValidHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckAmount), this.getMainRecord().getField(PaymentRequest.kAmount)));
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        m_iPrepaymentTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.PREPAYMENT);   // Prepayment
        m_iPaymentTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, PaymentHistory.kPaymentHistoryFile, PaymentHistory.PAYMENT);
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.kPaymentHistoryFile);
        recPaymentHistory.addListener(new SubFileFilter(recApTrx));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        super.addToolbarButtons(toolScreen);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kChecksToPrint).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kCheckDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kNextCheckNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * Get the group ID for this detail transaction.
     * Typically you must override this method to supply the correct group ID.
     * @param recDetailTrx The current batch record.
     * @return The group trx id.
     */
    public int getTrxGroupID(Record recDetailTrx)
    {
        if (m_iTrxGroupID <= 0)
        {
            TrxGroup recTrxGroup = (TrxGroup)this.getRecord(TrxGroup.kTrxGroupFile);
            m_iTrxGroupID = recTrxGroup.getTrxGroupID(TransactionType.ACCTPAY, PaymentHistory.kPaymentHistoryFile, PaymentHistory.PAYMENT);
        }
        return m_iTrxGroupID;
    }
    /**
     * Post this detail transaction to the BaseTrx and the G/L.
     * @return True if successful.
     */
    public boolean postDetailTrx()
    {
        AcctDetailDist recAcctDetailDist = (AcctDetailDist)this.getRecord(AcctDetailDist.kAcctDetailDistFile);
        recAcctDetailDist.startDistTrx();
        boolean bSuccess = super.postDetailTrx();
        recAcctDetailDist.endDistTrx();
        return bSuccess;
    }
    /**
     * Get the batch detail record.
     */
    public Record getDetailRecord()
    {
        return this.getRecord(PaymentRequest.kPaymentRequestFile);
    }
    /**
     * Return the distribution detail record.
     * @return The dist record.
     */
    public Record getDistRecord()
    {
        return this.getRecord(ApTrx.kApTrxFile);
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(PaymentHistory.kPaymentHistoryFile);
    }
    /**
     * Is the batch header record valid?
     * @return true if valid (if false, set the last error).
     */
    public boolean checkValidHeader()
    {
        return true;
    }
    /**
     * Delete the batch header.
     */
    public boolean removeTrxHeader()
    {
        BumpCheckNoHandler listener = (BumpCheckNoHandler)this.getMainRecord().getListener(BumpCheckNoHandler.class.getName());
        if (listener != null)
        {
            int iNextCheckNo = listener.getNextCheckNo();
            try {
                BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getRecord(PaymentRequest.kPaymentRequestFile).getField(PaymentRequest.kBankAcctID)).getReferenceRecord();
                if (recBankAcct != null)
                {
                    BankAcct recBankAcct2 = null;
                    try {
                        recBankAcct2 = (BankAcct)recBankAcct.clone();   // I Do this since recBankAcct is linked to a popup which makes it grid and readonly.
                        recBankAcct2.addNew();
                        recBankAcct2.getField(BankAcct.kID).moveFieldToThis(this.getRecord(PaymentRequest.kPaymentRequestFile).getField(PaymentRequest.kBankAcctID));
                        if (recBankAcct2.seek(null))
                        {   // Always
                            recBankAcct2.edit();
                            recBankAcct2.getField(BankAcct.kNextCheck).setValue(iNextCheckNo);
                            recBankAcct2.set();
                        }
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    } finally {
                        recBankAcct2.free();
                    }
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
                return false;
            }
        }
        return true;    // Don't need to call super since there is no batch header
    }
    /**
     * Remove this batch detail transaction and the distribution.
     * @return true if successful.
     */
    public boolean removeDetailTrx()
    {
        // Step 3 - Delete the batch (if not recurring)
        Record recBankTrxBatchDetail = this.getDetailRecord();
        try   {
            recBankTrxBatchDetail.remove();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return true;
    }
    /**
     * Setup and post this base transaction.
     * @param recBaseTrx The base transaction to post.
     * @param recTransactionType The transaction type for the TRX posting.
     * @return true If successful.
     */
    public boolean postBaseTrx(BaseTrx recBaseTrx, TransactionType recTransactionType)
    {
        boolean bSuccess = true;
        // Step 2b - Post the transaction side of the distribution.
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getRecord(PaymentRequest.kPaymentRequestFile).getField(PaymentRequest.kBankAcctID)).getReference();
        BaseField fldCrAccountID = recBankAcct.getField(BankAcct.kAccountID);
        
        Record recPaymentRequest = this.getDetailRecord();
        double dAmount = -recPaymentRequest.getField(PaymentRequest.kAmount).getValue();
        
        // Now post the total deposit amount
        BaseTrx recBankTrx = (BankTrx)this.getRecord(BankTrx.kBankTrxFile);
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        try   {
        // Step 2a - Create and write the bank transaction (in BankTrx).
            recBankTrx.addNew();
            recBankTrx.getField(BankTrx.kTrxStatusID).moveFieldToThis(recTransactionType.getField(TransactionType.kSourceTrxStatusID));
            recBankTrx.getField(BankTrx.kPayeeTrxDescID).moveFieldToThis(recTransactionType.getField(TransactionType.kTrxDescID));
            recBankTrx.getField(BankTrx.kPayeeID).moveFieldToThis(recPaymentRequest.getField(PaymentRequest.kVendorID));
            recBankTrx.getField(BankTrx.kPayeeName).moveFieldToThis(((ReferenceField)recPaymentRequest.getField(PaymentRequest.kVendorID)).getReference().getField(Vendor.kVendorName));
            recBankTrx.getField(BankTrx.kTrxDate).moveFieldToThis(this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckDate));
            recBankTrx.getField(BankTrx.kTrxEntry).initField(DBConstants.DONT_DISPLAY);
            if (recPaymentRequest.getField(PaymentRequest.kCheckNo).isNull())
            {   // Automatic check
                recBankTrx.getField(BankTrx.kTrxNumber).moveFieldToThis(this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckNo));
                recBankTrx.getField(BankTrx.kManual).setState(false);
            }
            else
            {   // Manual check
                recBankTrx.getField(BankTrx.kTrxNumber).moveFieldToThis(recPaymentRequest.getField(PaymentRequest.kCheckNo));
                recBankTrx.getField(BankTrx.kManual).setState(true);
            }
            recBankTrx.getField(BankTrx.kBankAcctID).moveFieldToThis(recBankAcct.getField(BankAcct.kID));
            recBankTrx.getField(BankTrx.kAmount).setValue(dAmount);
            ((BankTrx)recBankTrx).calcUSDAmounts(true);
            double dAmountUSD = recBankTrx.getField(BankTrx.kAmountLocal).getValue();
            recBankTrx.getField(BankTrx.kComments).moveFieldToThis(recPaymentRequest.getField(PaymentRequest.kComments));
            if (recBankTrx.getField(BankTrx.kComments).isNull())
                recBankTrx.getField(BankTrx.kComments).moveFieldToThis(recTrxStatus.getField(TrxStatus.kStatusDesc));
            // Step 2 - Post it to the G/L
            // Step 2a - Create and write the bank transaction (in BankTrx).
            bSuccess = recBankTrx.onPostTrx();
            if (!bSuccess)
                return bSuccess;
            // Step 2b - Post the transaction side of the distribution.
        //+ if (fldCrAccountID == null)
            AcctDetail recAcctDetail = (AcctDetail)this.getRecord(AcctDetail.kAcctDetailFile);
            AcctDetailDist recAcctDetailDist = (AcctDetailDist)this.getRecord(AcctDetailDist.kAcctDetailDistFile);
            Period recPeriod = (Period)this.getRecord(Period.kPeriodFile);
            bSuccess = recBankTrx.onPostTrxDist(fldCrAccountID, dAmountUSD, recTransactionType, recAcctDetail, recAcctDetailDist, recPeriod);
            if (!bSuccess)
            {       // Back out and void - bad trx.
                recBankTrx.onVoidTrx();
                return false;
            }
        } catch (DBException ex) {
            ex.printStackTrace();
            return false;
        }
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
        PaymentHistory recPaymentHistory = (PaymentHistory)this.getRecord(PaymentHistory.kPaymentHistoryFile);
        ApTrx recApTrx = (ApTrx)this.getDistRecord();
        Record recPaymentRequest = this.getDetailRecord();
        BaseField fldVendorID = recPaymentRequest.getField(PaymentRequest.kVendorID);
        BankTrx recBankTrx = (BankTrx)this.getRecord(BankTrx.kBankTrxFile);
        double dAmountUSD = -recBankTrx.getField(BankTrx.kAmountLocal).getValue();  // Must be positive.
        BaseField fldTrxID = recBankTrx.getField(BankTrx.kID);
        BaseField fldTrxDescID = ((TrxStatusField)recBankTrx.getField(BankTrx.kTrxStatusID)).getReference().getField(TrxStatus.kTrxDescID);
        double dAmount = recPaymentRequest.getField(PaymentRequest.kAmount).getValue();
        return recPaymentHistory.postDistTrx(this, recTransactionType, recApTrx, fldVendorID, fldTrxDescID, fldTrxID, dAmount, dAmountUSD);
    }

}
