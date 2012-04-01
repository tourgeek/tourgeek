/**
 * @(#)PaymentHistory.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.db;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.hist.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  PaymentHistory - .
 */
public class PaymentHistory extends LinkTrx
     implements PaymentHistoryModel
{
    private static final long serialVersionUID = 1L;

    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE | 4096;
    /**
     * Default constructor.
     */
    public PaymentHistory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PaymentHistory(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(PAYMENT_HISTORY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Payment History";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctpay";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & PaymentHistory.LINK_DISTRIBUTION_SCREEN) == PaymentHistory.LINK_DISTRIBUTION_SCREEN)
            screen = Record.makeNewScreen(PAYMENT_HISTORY_LINK_TRX_GRID_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
            || ((iDocMode & PaymentHistory.DISTRIBUTION_SCREEN) == PaymentHistory.DISTRIBUTION_SCREEN))
                screen = Record.makeNewScreen(PAYMENT_HISTORY_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(PAYMENT_HISTORY_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = Record.makeNewScreen(PAYMENT_HISTORY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 3)
        //  field = new TrxStatusField(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new PaymentHistory_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new DateTimeField(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new CurrencyField(this, AMOUNT_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new PaymentHistory_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new TrxField(this, LINKED_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new TrxDescField(this, LINKED_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
        {
            field = new ApTrxField(this, AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 11)
            field = new FullCurrencyField(this, AMOUNT_APPLIED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new CurrencyField(this, CURR_LOSS_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "LinkedTrxID");
            keyArea.addKeyField(LINKED_TRX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(LINKED_TRX_DESC_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ApTrxID");
            keyArea.addKeyField(AP_TRX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return PaymentHistory.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }
    /**
     * Post the distribution detail.
     * Note: This method doesn't really operate directly on the PaymentHistory,
     * it is here because this is a convient place to share this Payment distribution code.
     * @param recordOwner
     * @param recTransactionType
     * @param recApTrx
     * @param fldVendorID
     * @param fldTrxID
     * @param dCheckBalance Amount of this check.
     * @param dCheckBalanceUSD Amount of this check in USD
     * @return true If successful.
     */
    public boolean postDistTrx(RecordOwner recordOwner, TransactionType recTransactionType, ApTrx recApTrx, BaseField fldVendorID, BaseField fldTrxDescID, BaseField fldTrxID, double
     dCheckBalance, double dCheckBalanceUSD)
    {
        boolean bSuccess = true;
        double dExchange = 1.0;
        if (dCheckBalance != 0)
            dExchange = dCheckBalanceUSD / dCheckBalance;
        try   {
            recApTrx.close();
            while (recApTrx.hasNext())
            {
                recApTrx.next();
                if (recApTrx.getField(ApTrx.AMOUNT_SELECTED).getValue() == 0)
                    continue; // Not selected
                if (dCheckBalance <= 0)
                    break;
                recApTrx.edit();
        
                double dAmountSelected = recApTrx.getField(ApTrx.AMOUNT_SELECTED).getValue();
                double dInvoiceAmount = recApTrx.getField(ApTrx.INVOICE_AMOUNT).getValue();
                double dInvoiceAmountUSD = recApTrx.getField(ApTrx.INVOICE_LOCAL).getValue();
                double dInvoiceBalance = recApTrx.getField(ApTrx.INVOICE_BALANCE).getValue();
                double dInvoiceBalanceUSD = recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).getValue();
        
                if (dAmountSelected > dCheckBalance)
                    dAmountSelected = dCheckBalance;
                dCheckBalance = dCheckBalance - dAmountSelected;
                double dNewBalance = dInvoiceBalance - dAmountSelected;
                double dExchangeRate = 1;
                if ((dInvoiceAmountUSD == 0) || (dInvoiceAmount == 0))
                {
                    dExchangeRate = dExchange;
                    recApTrx.getField(ApTrx.INVOICE_LOCAL).setValue((Math.floor(dInvoiceAmount * dExchangeRate * 100 + 0.5)) / 100);
                }
                else
                    dExchangeRate = dInvoiceAmountUSD / dInvoiceAmount;
                double dNewBalanceUSD = (Math.floor(dNewBalance * dExchangeRate * 100 + 0.5)) / 100;
                double dAmountSelectedUSD = dInvoiceBalanceUSD - dNewBalanceUSD;
        
                recApTrx.getField(ApTrx.AMOUNT_SELECTED).setValue(Math.max(0, dNewBalance));    // Don't select P/P
                recApTrx.getField(ApTrx.INVOICE_BALANCE).setValue(dNewBalance);
                recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).setValue(dNewBalanceUSD);
                int iOrigApStatus = (int)recApTrx.getField(ApTrx.TRX_STATUS_ID).getValue();
                if (dNewBalance <= 0)
                {   // Change status to paid
                    TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.TRX_STATUS_ID)).getReference();
                    if (recTrxStatus != null)
                    {
                        String strPaidStatus = recTrxStatus.getField(TrxStatus.STATUS_CODE).toString() + ApTrx.PAID;
                        int iNewTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, strPaidStatus);
                        if (iNewTrxStatus > 0)
                            recApTrx.getField(ApTrx.TRX_STATUS_ID).setValue(iNewTrxStatus);
                    }
                }
        
                if (!recApTrx.getField(ApTrx.DRAFT_VENDOR_ID).isNull())
                {   // Broker payment draft to distribute.
                    int iVendorID = (int)recApTrx.getField(ApTrx.DRAFT_VENDOR_ID).getValue();
                    recApTrx.getField(ApTrx.DRAFT_VENDOR_ID).moveFieldToThis(recApTrx.getField(ApTrx.VENDOR_ID));
                    recApTrx.getField(ApTrx.VENDOR_ID).setValue(iVendorID);
        
                    dAmountSelected = recApTrx.getField(ApTrx.INVOICE_AMOUNT).getValue();
                    dAmountSelectedUSD = recApTrx.getField(ApTrx.INVOICE_LOCAL).getValue();
                    recApTrx.getField(ApTrx.AMOUNT_SELECTED).setValue(0);
                    recApTrx.getField(ApTrx.INVOICE_BALANCE).setValue(-dAmountSelected);
                    recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).setValue(-dAmountSelectedUSD);
                }
        
                BaseField fldAccountID = recApTrx.getField(ApTrx.ACCOUNT_ID);     // Explicit distribution
                if (fldAccountID.isNull())
                    fldAccountID = recApTrx.getTrxAccountID(iOrigApStatus, PostingType.TRX_POST);    //recApTrx.getField(ApTrx.ACCOUNT_ID); // The account that was credited on creation
                if (fldAccountID.isNull())
                {   // Should add some code here?
                }
        
                double dNewCheckBalanceUSD = (Math.floor(dCheckBalance * dExchange * 100 + 0.5)) / 100;
                double dCheckAmountAppliedUSD = dCheckBalanceUSD - dNewCheckBalanceUSD;
                dCheckBalanceUSD = dCheckBalanceUSD - dCheckAmountAppliedUSD;
                double dCurrencyLoss = dCheckAmountAppliedUSD - dAmountSelectedUSD;
                int iSign = (dCurrencyLoss > 0) ? +1 : -1;
                dCurrencyLoss = (Math.floor(Math.abs(dCurrencyLoss) * 100 + 0.5)) / 100 * iSign;
                BaseField fldCurrAccountID = null;
                if (dCurrencyLoss != 0)
                {
                    Record recProductCategory = (Record)recApTrx.getProductCategory();
                    if (recProductCategory != null)
                        fldCurrAccountID = recProductCategory.getField(ProductCategory.CURR_OU_ACCOUNT_ID);
                    if ((fldCurrAccountID == null) || (fldCurrAccountID.isNull()))
                    {
                        Record recApControl = (Record)recordOwner.getRecord(ApControl.AP_CONTROL_FILE);
                        if (recApControl == null)
                            recApControl = new ApControl(recordOwner);
                        fldCurrAccountID = recApControl.getField(ApControl.CURR_OU_ACCOUNT_ID);
                    }
                }
                this.addPaymentHistory(recordOwner, recApTrx, fldAccountID, PostingType.DIST_POST, (int)recTransactionType.getField(TransactionType.SOURCE_TRX_STATUS_ID).getValue(), fldTrxDescID, fldTrxID, dAmountSelected, dAmountSelectedUSD, fldCurrAccountID, PostingType.DIFFERENCE_POST, dCurrencyLoss);
        
                FileListener listener = recApTrx.getListener(SubFileFilter.class);
                if (listener != null)
                    listener.setEnabledListener(false); // Allow vendorID change
                recApTrx.set();
                if (listener != null)
                    listener.setEnabledListener(true);
            }
        
            if (dCheckBalance != 0)
            { // Amount distributed not equal to selected amount, distribute the balance to a prepayment
                recApTrx.addNew();
                TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.TRX_STATUS_ID)).getReferenceRecord();
                int iPrepaymentTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.PREPAYMENT);   // Prepayment
                recApTrx.getField(ApTrx.TRX_STATUS_ID).setValue(iPrepaymentTrxStatusID);
                recApTrx.getField(ApTrx.VENDOR_ID).moveFieldToThis(fldVendorID);
                recApTrx.getField(ApTrx.INVOICE_AMOUNT).setValue(-dCheckBalance);
                recApTrx.getField(ApTrx.INVOICE_LOCAL).setValue(-dCheckBalanceUSD);
                recApTrx.getField(ApTrx.INVOICE_BALANCE).setValue(-dCheckBalance);
                recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).setValue(-dCheckBalanceUSD);
                recApTrx.getField(ApTrx.AMOUNT_SELECTED).setValue(0);
                recApTrx.getField(ApTrx.ACCOUNT_ID).moveFieldToThis(((Record)recordOwner.getRecord(ApControl.AP_CONTROL_FILE)).getField(ApControl.NON_TOUR_PREPAY_ACCOUNT_ID)); // Non-tour P/P
                recApTrx.add();
                Object varBookmark = recApTrx.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
                recApTrx.setHandle(varBookmark, DBConstants.DATA_SOURCE_HANDLE);
        
                BaseField fldAccountID = recApTrx.getField(ApTrx.ACCOUNT_ID);     // Explicit distribution
                if (fldAccountID.isNull())
                    fldAccountID = recApTrx.getTrxAccountID(-1, PostingType.TRX_POST);    //recApTrx.getField(ApTrx.ACCOUNT_ID); // The account that was credited on creation
                this.addPaymentHistory(recordOwner, recApTrx, fldAccountID, PostingType.DIST_POST, (int)recTransactionType.getField(TransactionType.SOURCE_TRX_STATUS_ID).getValue(), fldTrxDescID, fldTrxID, dCheckBalance, dCheckBalanceUSD, null, null, 0);
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return bSuccess;
    }
    /**
     * Add the payment history for this A/P transaction.
     * @param recPaymentHistory
     * @param recApTrx
     * @param iOldTrxStatusID
     * @param iTrxStatusID
     * @param recBankTrx
     * @param iTrxDescID The trx description ID
     * @param dAmount
     * @param dAmountUSD
     * @param fldCurrAccountID
     * @param strDiffPostingType
     * @param dCurrencyLoss
     * @return True if successful.
     */
    public boolean addPaymentHistory(RecordOwner recordOwner, ApTrx recApTrx, BaseField fldAccountID, String strPostType, int iTrxStatusID, BaseField fldTrxDescID, BaseField fldTrxID, double
     dAmount,double dAmountUSD, BaseField fldCurrAccountID, String strDiffPostingType, double dCurrLoss)
    {
        boolean bSuccess = false;
        try {
            this.addNew();
            this.getField(PaymentHistory.AP_TRX_ID).moveFieldToThis(recApTrx.getField(ApTrx.ID));
            this.getField(PaymentHistory.TRX_STATUS_ID).setValue(iTrxStatusID);
            this.getField(PaymentHistory.TRX_DATE).setValue(DateTimeField.todaysDate());
            this.getField(PaymentHistory.AMOUNT_APPLIED).setValue(dAmount);
            this.getField(PaymentHistory.AMOUNT_LOCAL).setValue(dAmountUSD);
            this.getField(PaymentHistory.CURR_LOSS_LOCAL).setValue(dCurrLoss);
            this.getField(PaymentHistory.LINKED_TRX_ID).moveFieldToThis(fldTrxID);
            if (fldTrxDescID != null)
                this.getField(PaymentHistory.LINKED_TRX_DESC_ID).moveFieldToThis(fldTrxDescID);
            this.getField(PaymentHistory.TRX_ENTRY).setValue(DateTimeField.currentTime());
            this.add();
            Object bookmark = this.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
            this.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
            // Step 2b - Post the transaction side of the distribution.
            AcctDetail recAcctDetail = (AcctDetail)recordOwner.getRecord(AcctDetail.ACCT_DETAIL_FILE);
            AcctDetailDist recAcctDetailDist = (AcctDetailDist)recordOwner.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE);
            Period recPeriod = (Period)recordOwner.getRecord(Period.PERIOD_FILE);
            bSuccess = this.onPostTrxDist(fldAccountID, dAmountUSD, strPostType, recAcctDetail, recAcctDetailDist, recPeriod);
            if (!bSuccess)
            {       // Back out and void - bad trx.
        //?        this.onVoidTrx();
        //?        return false;
            }
            if (dCurrLoss != 0.00)
            {
                bSuccess = this.onPostTrxDist(fldCurrAccountID, dCurrLoss, strDiffPostingType, recAcctDetail, recAcctDetailDist, recPeriod);
                if (!bSuccess)
                {       // Back out and void - bad trx.
            //?        this.onVoidTrx();
            //?        return false;
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return bSuccess;
    }
    /**
     * Distribute this prepayment to the selected amounts for this vendor.
     * Note: This is only here in PaymentHistory for access to this shared code.
     * @param recordOwner
     * @param recApTrx
     * @param recSelectApTrx.
     */
    public boolean distribute(RecordOwner recordOwner, ApTrx recApTrx, ApTrx recSelectApTrx)
    {
        boolean bSuccess = true;
        try {
            recApTrx.edit();
            TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.TRX_STATUS_ID)).getReference();
            String strPaidStatus = PaymentHistory.PREPAYMENT_DIST;
            if (recTrxStatus != null)
                strPaidStatus = recTrxStatus.getField(TrxStatus.STATUS_CODE).toString() + ApTrx.DIST;
            TransactionType recTransactionType = (TransactionType)recordOwner.getRecord(TransactionType.TRANSACTION_TYPE_FILE);
            recTransactionType.getTrxTypeID(TransactionType.ACCTPAY, PaymentHistory.PAYMENT_HISTORY_FILE, strPaidStatus, PaymentHistory.DIST_TYPE);
            BaseField fldVendorID = recApTrx.getField(ApTrx.VENDOR_ID);
            double dExchange = 1;
            if ((recApTrx.getField(ApTrx.INVOICE_LOCAL).getValue() != 0) && (recApTrx.getField(ApTrx.INVOICE_AMOUNT).getValue() != 0))
                dExchange = recApTrx.getField(ApTrx.INVOICE_LOCAL).getValue() / recApTrx.getField(ApTrx.INVOICE_AMOUNT).getValue();
            BaseField fldTrxID = recApTrx.getField(ApTrx.ID);
            double dDistributedAmount = -recordOwner.getRecord(Vendor.VENDOR_FILE).getField(Vendor.AMOUNT_SELECTED).getValue();
            if (-recApTrx.getField(ApTrx.INVOICE_BALANCE).getValue() < -dDistributedAmount)
                dDistributedAmount = recApTrx.getField(ApTrx.INVOICE_BALANCE).getValue();  // Negative
        
            double dBalance = recApTrx.getField(ApTrx.INVOICE_BALANCE).getValue();  // Negative
            double dStartBalanceUSD = recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).getValue();   // Negative
        
            dBalance = dBalance - dDistributedAmount;   // Negative balance
            int iSign = (dBalance < 0) ? -1 : +1;
            double dBalanceUSD = Math.floor(Math.abs(dBalance * dExchange * 100 + 0.5)) / 100 * iSign;
            double dDistributedAmountUSD = dStartBalanceUSD - dBalanceUSD;  // Negative amount distributed
        
            ((AcctDetailDist)recordOwner.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE)).startDistTrx();
            bSuccess = this.postDistTrx(recordOwner, recTransactionType, recSelectApTrx, fldVendorID, recTrxStatus.getField(TrxStatus.TRX_DESC_ID), fldTrxID, -dDistributedAmount, -dDistributedAmountUSD);
            int iDistGroupID = (int)recordOwner.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE).getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).getValue();
        
            if (bSuccess)
            {
                recApTrx.getField(ApTrx.INVOICE_BALANCE).setValue(dBalance);
                recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).setValue(dBalanceUSD);
        
                int iOrigApStatus = (int)recApTrx.getField(ApTrx.TRX_STATUS_ID).getValue();
                int iNewTrxStatus = -1;
                if (dBalance == 0)
                {   // Change status to paid
                    iNewTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, strPaidStatus);
                    if (iNewTrxStatus > 0)
                        recApTrx.getField(ApTrx.TRX_STATUS_ID).setValue(iNewTrxStatus);
                }
        
                Object bookmark = recApTrx.getHandle(DBConstants.DATA_SOURCE_HANDLE);
                recApTrx.set();
                recApTrx.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
        
                // Now I need to find the G/L prepayment account that the pp was debited to originally
                BaseField fldAccountID = null;
                this.addNew();
                int iOldKeyArea = this.getDefaultOrder();
                this.setKeyArea(PaymentHistory.AP_TRX_ID_KEY);
                this.getField(PaymentHistory.AP_TRX_ID).moveFieldToThis(recApTrx.getField(ApTrx.ID));
                this.getField(PaymentHistory.TRX_DATE).setToLimit(DBConstants.START_SELECT_KEY);
                this.getField(PaymentHistory.ID).setData(null);
                if (this.seek(">="))
                    if (this.getField(PaymentHistory.AP_TRX_ID).equals(recApTrx.getField(ApTrx.ID)))
                        fldAccountID = this.getTrxAccountID(PostingType.DIST_POST);
                this.setKeyArea(iOldKeyArea);
                if ((fldAccountID == null) || (fldAccountID.isNull()))
                {       // If not found, just use a default (never?)
                    if (((recApTrx.getEditMode() == DBConstants.EDIT_IN_PROGRESS) || (recApTrx.getEditMode() == DBConstants.EDIT_CURRENT)) && (!recApTrx.getField(ApTrx.ACCOUNT_ID).isNull()))
                        fldAccountID = recApTrx.getField(ApTrx.ACCOUNT_ID); // Dist account for prepayments and broker payments
                    else if (recApTrx.getField(ApTrx.TOUR_ID).isNull())
                        fldAccountID = ((Record)recordOwner.getRecord(ApControl.AP_CONTROL_FILE)).getField(ApControl.NON_TOUR_PREPAY_ACCOUNT_ID);
                    else
                        fldAccountID = ((Record)recordOwner.getRecord(ApControl.AP_CONTROL_FILE)).getField(ApControl.PREPAY_ACCOUNT_ID);
                }
                DateTimeField fldTrxDate = null;
                TransactionType recTrxType = (TransactionType)recordOwner.getRecord(TransactionType.TRANSACTION_TYPE_FILE);
                recTrxType.getTrxTypeID(TransactionType.ACCTPAY, PaymentHistory.PAYMENT_HISTORY_FILE, strPaidStatus, PaymentHistory.TRX_TYPE);
                DateTimeField fldTrxEntryDate = null;
                int iUserID = -1;
                AcctDetailDist recAcctDetailDist = (AcctDetailDist)recordOwner.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE);
                AcctDetail recAcctDetail = (AcctDetail)recordOwner.getRecord(AcctDetail.ACCT_DETAIL_FILE);
                Period recPeriod = (Period)recordOwner.getRecord(Period.PERIOD_FILE);
        
                recAcctDetailDist.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).setValue(iDistGroupID);
                bSuccess = recAcctDetailDist.addDetailTrx(fldAccountID, fldTrxDate, fldTrxID, recTrxType, fldTrxEntryDate, dDistributedAmountUSD, iUserID, recAcctDetail, recPeriod);
                ((AcctDetailDist)recordOwner.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE)).endDistTrx();
            }
            else
            {   // Back out the transactions here!
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            bSuccess = false;
        }
        return bSuccess;
    }

}
