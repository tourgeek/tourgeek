/**
 * @(#)PaymentHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.hist.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.base.db.*;

/**
 *  PaymentHistory - .
 */
public class PaymentHistory extends LinkTrx
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxStatusID = kTrxStatusID;
    //public static final int kTrxDate = kTrxDate;
    //public static final int kAmountLocal = kAmountLocal;
    //public static final int kTrxUserID = kTrxUserID;
    //public static final int kLinkedTrxID = kLinkedTrxID;
    //public static final int kLinkedTrxDescID = kLinkedTrxDescID;
    //public static final int kTrxEntry = kTrxEntry;
    public static final int kApTrxID = kLinkTrxLastField + 1;
    public static final int kAmountApplied = kApTrxID + 1;
    public static final int kCurrLossLocal = kAmountApplied + 1;
    public static final int kPaymentHistoryLastField = kCurrLossLocal;
    public static final int kPaymentHistoryFields = kCurrLossLocal - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kLinkedTrxIDKey = kIDKey + 1;
    public static final int kApTrxIDKey = kLinkedTrxIDKey + 1;
    public static final int kPaymentHistoryLastKey = kApTrxIDKey;
    public static final int kPaymentHistoryKeys = kApTrxIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String PAYMENT = "Payment";
    public static final String PAYMENT_DISTRIBUTION_ICON = "Transaction";
    public static final String PAYMENT_HISTORY = "Payment History";
    public static final String PREPAYMENT_DIST = "PrepaymentDist";
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

    public static final String kPaymentHistoryFile = "PaymentHistory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kPaymentHistoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & PaymentHistory.LINK_DISTRIBUTION_SCREEN) == PaymentHistory.LINK_DISTRIBUTION_SCREEN)
            screen = new PaymentHistoryLinkTrxGridScreen(null, this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
            || ((iDocMode & PaymentHistory.DISTRIBUTION_SCREEN) == PaymentHistory.DISTRIBUTION_SCREEN))
                screen = new PaymentHistoryDistGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new PaymentHistoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new PaymentHistoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kApTrxID)
        {
            field = new ApTrxField(this, "ApTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxDate)
        //  field = new DateTimeField(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountApplied)
            field = new FullCurrencyField(this, "AmountApplied", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountLocal)
            field = new CurrencyField(this, "AmountLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrLossLocal)
            field = new CurrencyField(this, "CurrLossLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxUserID)
        //  field = new PaymentHistory_TrxUserID(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kLinkedTrxID)
        //  field = new TrxField(this, "LinkedTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kLinkedTrxDescID)
        //  field = new TrxDescField(this, "LinkedTrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxEntry)
        //  field = new PaymentHistory_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPaymentHistoryLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kLinkedTrxIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "LinkedTrxID");
            keyArea.addKeyField(kLinkedTrxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kLinkedTrxDescID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kApTrxIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ApTrxID");
            keyArea.addKeyField(kApTrxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kPaymentHistoryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kPaymentHistoryLastKey)
                keyArea = new EmptyKey(this);
        }
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
                if (recApTrx.getField(ApTrx.kAmountSelected).getValue() == 0)
                    continue; // Not selected
                if (dCheckBalance <= 0)
                    break;
                recApTrx.edit();
        
                double dAmountSelected = recApTrx.getField(ApTrx.kAmountSelected).getValue();
                double dInvoiceAmount = recApTrx.getField(ApTrx.kInvoiceAmount).getValue();
                double dInvoiceAmountUSD = recApTrx.getField(ApTrx.kInvoiceLocal).getValue();
                double dInvoiceBalance = recApTrx.getField(ApTrx.kInvoiceBalance).getValue();
                double dInvoiceBalanceUSD = recApTrx.getField(ApTrx.kInvoiceBalanceLocal).getValue();
        
                if (dAmountSelected > dCheckBalance)
                    dAmountSelected = dCheckBalance;
                dCheckBalance = dCheckBalance - dAmountSelected;
                double dNewBalance = dInvoiceBalance - dAmountSelected;
                double dExchangeRate = 1;
                if ((dInvoiceAmountUSD == 0) || (dInvoiceAmount == 0))
                {
                    dExchangeRate = dExchange;
                    recApTrx.getField(ApTrx.kInvoiceLocal).setValue((Math.floor(dInvoiceAmount * dExchangeRate * 100 + 0.5)) / 100);
                }
                else
                    dExchangeRate = dInvoiceAmountUSD / dInvoiceAmount;
                double dNewBalanceUSD = (Math.floor(dNewBalance * dExchangeRate * 100 + 0.5)) / 100;
                double dAmountSelectedUSD = dInvoiceBalanceUSD - dNewBalanceUSD;
        
                recApTrx.getField(ApTrx.kAmountSelected).setValue(Math.max(0, dNewBalance));    // Don't select P/P
                recApTrx.getField(ApTrx.kInvoiceBalance).setValue(dNewBalance);
                recApTrx.getField(ApTrx.kInvoiceBalanceLocal).setValue(dNewBalanceUSD);
                int iOrigApStatus = (int)recApTrx.getField(ApTrx.kTrxStatusID).getValue();
                if (dNewBalance <= 0)
                {   // Change status to paid
                    TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.kTrxStatusID)).getReference();
                    if (recTrxStatus != null)
                    {
                        String strPaidStatus = recTrxStatus.getField(TrxStatus.kStatusCode).toString() + ApTrx.PAID;
                        int iNewTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, strPaidStatus);
                        if (iNewTrxStatus > 0)
                            recApTrx.getField(ApTrx.kTrxStatusID).setValue(iNewTrxStatus);
                    }
                }
        
                if (!recApTrx.getField(ApTrx.kDraftVendorID).isNull())
                {   // Broker payment draft to distribute.
                    int iVendorID = (int)recApTrx.getField(ApTrx.kDraftVendorID).getValue();
                    recApTrx.getField(ApTrx.kDraftVendorID).moveFieldToThis(recApTrx.getField(ApTrx.kVendorID));
                    recApTrx.getField(ApTrx.kVendorID).setValue(iVendorID);
        
                    dAmountSelected = recApTrx.getField(ApTrx.kInvoiceAmount).getValue();
                    dAmountSelectedUSD = recApTrx.getField(ApTrx.kInvoiceLocal).getValue();
                    recApTrx.getField(ApTrx.kAmountSelected).setValue(0);
                    recApTrx.getField(ApTrx.kInvoiceBalance).setValue(-dAmountSelected);
                    recApTrx.getField(ApTrx.kInvoiceBalanceLocal).setValue(-dAmountSelectedUSD);
                }
        
                BaseField fldAccountID = recApTrx.getField(ApTrx.kAccountID);     // Explicit distribution
                if (fldAccountID.isNull())
                    fldAccountID = recApTrx.getTrxAccountID(iOrigApStatus, PostingType.TRX_POST);    //recApTrx.getField(ApTrx.kAccountID); // The account that was credited on creation
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
                    Record recProductCategory = recApTrx.getProductCategory();
                    if (recProductCategory != null)
                        fldCurrAccountID = recProductCategory.getField(ProductCategory.kCurrOUAccountID);
                    if ((fldCurrAccountID == null) || (fldCurrAccountID.isNull()))
                    {
                        Record recApControl = recordOwner.getRecord(ApControl.kApControlFile);
                        if (recApControl == null)
                            recApControl = new ApControl(recordOwner);
                        fldCurrAccountID = recApControl.getField(ApControl.kCurrOUAccountID);
                    }
                }
                this.addPaymentHistory(recordOwner, recApTrx, fldAccountID, PostingType.DIST_POST, (int)recTransactionType.getField(TransactionType.kSourceTrxStatusID).getValue(), fldTrxDescID, fldTrxID, dAmountSelected, dAmountSelectedUSD, fldCurrAccountID, PostingType.DIFFERENCE_POST, dCurrencyLoss);
        
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
                TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.kTrxStatusID)).getReferenceRecord();
                int iPrepaymentTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.PREPAYMENT);   // Prepayment
                recApTrx.getField(ApTrx.kTrxStatusID).setValue(iPrepaymentTrxStatusID);
                recApTrx.getField(ApTrx.kVendorID).moveFieldToThis(fldVendorID);
                recApTrx.getField(ApTrx.kInvoiceAmount).setValue(-dCheckBalance);
                recApTrx.getField(ApTrx.kInvoiceLocal).setValue(-dCheckBalanceUSD);
                recApTrx.getField(ApTrx.kInvoiceBalance).setValue(-dCheckBalance);
                recApTrx.getField(ApTrx.kInvoiceBalanceLocal).setValue(-dCheckBalanceUSD);
                recApTrx.getField(ApTrx.kAmountSelected).setValue(0);
                recApTrx.getField(ApTrx.kAccountID).moveFieldToThis(recordOwner.getRecord(ApControl.kApControlFile).getField(ApControl.kNonTourPrepayAccountID)); // Non-tour P/P
                recApTrx.add();
                Object varBookmark = recApTrx.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
                recApTrx.setHandle(varBookmark, DBConstants.DATA_SOURCE_HANDLE);
        
                BaseField fldAccountID = recApTrx.getField(ApTrx.kAccountID);     // Explicit distribution
                if (fldAccountID.isNull())
                    fldAccountID = recApTrx.getTrxAccountID(-1, PostingType.TRX_POST);    //recApTrx.getField(ApTrx.kAccountID); // The account that was credited on creation
                this.addPaymentHistory(recordOwner, recApTrx, fldAccountID, PostingType.DIST_POST, (int)recTransactionType.getField(TransactionType.kSourceTrxStatusID).getValue(), fldTrxDescID, fldTrxID, dCheckBalance, dCheckBalanceUSD, null, null, 0);
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
            this.getField(PaymentHistory.kApTrxID).moveFieldToThis(recApTrx.getField(ApTrx.kID));
            this.getField(PaymentHistory.kTrxStatusID).setValue(iTrxStatusID);
            this.getField(PaymentHistory.kTrxDate).setValue(DateTimeField.todaysDate());
            this.getField(PaymentHistory.kAmountApplied).setValue(dAmount);
            this.getField(PaymentHistory.kAmountLocal).setValue(dAmountUSD);
            this.getField(PaymentHistory.kCurrLossLocal).setValue(dCurrLoss);
            this.getField(PaymentHistory.kLinkedTrxID).moveFieldToThis(fldTrxID);
            if (fldTrxDescID != null)
                this.getField(PaymentHistory.kLinkedTrxDescID).moveFieldToThis(fldTrxDescID);
            this.getField(PaymentHistory.kTrxEntry).setValue(DateTimeField.currentTime());
            this.add();
            Object bookmark = this.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
            this.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
            // Step 2b - Post the transaction side of the distribution.
            AcctDetail recAcctDetail = (AcctDetail)recordOwner.getRecord(AcctDetail.kAcctDetailFile);
            AcctDetailDist recAcctDetailDist = (AcctDetailDist)recordOwner.getRecord(AcctDetailDist.kAcctDetailDistFile);
            Period recPeriod = (Period)recordOwner.getRecord(Period.kPeriodFile);
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
            TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.kTrxStatusID)).getReference();
            String strPaidStatus = PaymentHistory.PREPAYMENT_DIST;
            if (recTrxStatus != null)
                strPaidStatus = recTrxStatus.getField(TrxStatus.kStatusCode).toString() + ApTrx.DIST;
            TransactionType recTransactionType = (TransactionType)recordOwner.getRecord(TransactionType.kTransactionTypeFile);
            recTransactionType.getTrxTypeID(TransactionType.ACCTPAY, PaymentHistory.kPaymentHistoryFile, strPaidStatus, PaymentHistory.DIST_TYPE);
            BaseField fldVendorID = recApTrx.getField(ApTrx.kVendorID);
            double dExchange = 1;
            if ((recApTrx.getField(ApTrx.kInvoiceLocal).getValue() != 0) && (recApTrx.getField(ApTrx.kInvoiceAmount).getValue() != 0))
                dExchange = recApTrx.getField(ApTrx.kInvoiceLocal).getValue() / recApTrx.getField(ApTrx.kInvoiceAmount).getValue();
            BaseField fldTrxID = recApTrx.getField(ApTrx.kID);
            double dDistributedAmount = -recordOwner.getRecord(Vendor.kVendorFile).getField(Vendor.kAmountSelected).getValue();
            if (-recApTrx.getField(ApTrx.kInvoiceBalance).getValue() < -dDistributedAmount)
                dDistributedAmount = recApTrx.getField(ApTrx.kInvoiceBalance).getValue();  // Negative
        
            double dBalance = recApTrx.getField(ApTrx.kInvoiceBalance).getValue();  // Negative
            double dStartBalanceUSD = recApTrx.getField(ApTrx.kInvoiceBalanceLocal).getValue();   // Negative
        
            dBalance = dBalance - dDistributedAmount;   // Negative balance
            int iSign = (dBalance < 0) ? -1 : +1;
            double dBalanceUSD = Math.floor(Math.abs(dBalance * dExchange * 100 + 0.5)) / 100 * iSign;
            double dDistributedAmountUSD = dStartBalanceUSD - dBalanceUSD;  // Negative amount distributed
        
            ((AcctDetailDist)recordOwner.getRecord(AcctDetailDist.kAcctDetailDistFile)).startDistTrx();
            bSuccess = this.postDistTrx(recordOwner, recTransactionType, recSelectApTrx, fldVendorID, recTrxStatus.getField(TrxStatus.kTrxDescID), fldTrxID, -dDistributedAmount, -dDistributedAmountUSD);
            int iDistGroupID = (int)recordOwner.getRecord(AcctDetailDist.kAcctDetailDistFile).getField(AcctDetailDist.kAcctDetailDistGroupID).getValue();
        
            if (bSuccess)
            {
                recApTrx.getField(ApTrx.kInvoiceBalance).setValue(dBalance);
                recApTrx.getField(ApTrx.kInvoiceBalanceLocal).setValue(dBalanceUSD);
        
                int iOrigApStatus = (int)recApTrx.getField(ApTrx.kTrxStatusID).getValue();
                int iNewTrxStatus = -1;
                if (dBalance == 0)
                {   // Change status to paid
                    iNewTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, strPaidStatus);
                    if (iNewTrxStatus > 0)
                        recApTrx.getField(ApTrx.kTrxStatusID).setValue(iNewTrxStatus);
                }
        
                Object bookmark = recApTrx.getHandle(DBConstants.DATA_SOURCE_HANDLE);
                recApTrx.set();
                recApTrx.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
        
                // Now I need to find the G/L prepayment account that the pp was debited to originally
                BaseField fldAccountID = null;
                this.addNew();
                int iOldKeyArea = this.getDefaultOrder();
                this.setKeyArea(PaymentHistory.kApTrxIDKey);
                this.getField(PaymentHistory.kApTrxID).moveFieldToThis(recApTrx.getField(ApTrx.kID));
                this.getField(PaymentHistory.kTrxDate).setToLimit(DBConstants.START_SELECT_KEY);
                this.getField(PaymentHistory.kID).setData(null);
                if (this.seek(">="))
                    if (this.getField(PaymentHistory.kApTrxID).equals(recApTrx.getField(ApTrx.kID)))
                        fldAccountID = this.getTrxAccountID(PostingType.DIST_POST);
                this.setKeyArea(iOldKeyArea);
                if ((fldAccountID == null) || (fldAccountID.isNull()))
                {       // If not found, just use a default (never?)
                    if (((recApTrx.getEditMode() == DBConstants.EDIT_IN_PROGRESS) || (recApTrx.getEditMode() == DBConstants.EDIT_CURRENT)) && (!recApTrx.getField(ApTrx.kAccountID).isNull()))
                        fldAccountID = recApTrx.getField(ApTrx.kAccountID); // Dist account for prepayments and broker payments
                    else if (recApTrx.getField(ApTrx.kTourID).isNull())
                        fldAccountID = recordOwner.getRecord(ApControl.kApControlFile).getField(ApControl.kNonTourPrepayAccountID);
                    else
                        fldAccountID = recordOwner.getRecord(ApControl.kApControlFile).getField(ApControl.kPrepayAccountID);
                }
                DateTimeField fldTrxDate = null;
                TransactionType recTrxType = (TransactionType)recordOwner.getRecord(TransactionType.kTransactionTypeFile);
                recTrxType.getTrxTypeID(TransactionType.ACCTPAY, PaymentHistory.kPaymentHistoryFile, strPaidStatus, PaymentHistory.TRX_TYPE);
                DateTimeField fldTrxEntryDate = null;
                int iUserID = -1;
                AcctDetailDist recAcctDetailDist = (AcctDetailDist)recordOwner.getRecord(AcctDetailDist.kAcctDetailDistFile);
                AcctDetail recAcctDetail = (AcctDetail)recordOwner.getRecord(AcctDetail.kAcctDetailFile);
                Period recPeriod = (Period)recordOwner.getRecord(Period.kPeriodFile);
        
                recAcctDetailDist.getField(AcctDetailDist.kAcctDetailDistGroupID).setValue(iDistGroupID);
                bSuccess = recAcctDetailDist.addDetailTrx(fldAccountID, fldTrxDate, fldTrxID, recTrxType, fldTrxEntryDate, dDistributedAmountUSD, iUserID, recAcctDetail, recPeriod);
                ((AcctDetailDist)recordOwner.getRecord(AcctDetailDist.kAcctDetailDistFile)).endDistTrx();
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
