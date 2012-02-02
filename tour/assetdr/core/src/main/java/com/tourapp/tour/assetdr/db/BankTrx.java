/**
 * @(#)BankTrx.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.db;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.screen.trx.*;
import com.tourapp.tour.assetdr.report.recon.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.assetdr.db.*;

/**
 *  BankTrx - Checking account desc..
 */
public class BankTrx extends BaseTrx
     implements BankTrxModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxStatusID = kTrxStatusID;
    //public static final int kTrxDate = kTrxDate;
    //public static final int kAmountLocal = kAmountLocal;
    //public static final int kTrxUserID = kTrxUserID;
    //public static final int kTrxEntry = kTrxEntry;
    public static final int kBankAcctID = kBaseTrxLastField + 1;
    public static final int kTrxNumber = kBankAcctID + 1;
    public static final int kEFTTrxNo = kTrxNumber + 1;
    public static final int kPayeeTrxDescID = kEFTTrxNo + 1;
    public static final int kPayeeID = kPayeeTrxDescID + 1;
    public static final int kPayeeName = kPayeeID + 1;
    public static final int kAmount = kPayeeName + 1;
    public static final int kExchange = kAmount + 1;
    public static final int kInvSign = kExchange + 1;
    public static final int kInvBalance = kInvSign + 1;
    public static final int kInvBalanceLocal = kInvBalance + 1;
    public static final int kComments = kInvBalanceLocal + 1;
    public static final int kDateReconciled = kComments + 1;
    public static final int kManual = kDateReconciled + 1;
    public static final int kBankTrxLastField = kManual;
    public static final int kBankTrxFields = kManual - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxDateKey = kIDKey + 1;
    public static final int kTrxClassKey = kTrxDateKey + 1;
    public static final int kInvBalanceKey = kTrxClassKey + 1;
    public static final int kTrxNumberKey = kInvBalanceKey + 1;
    public static final int kPayeeIDKey = kTrxNumberKey + 1;
    public static final int kBankTrxLastKey = kPayeeIDKey;
    public static final int kBankTrxKeys = kPayeeIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    protected Record m_recBankTrx = null;
    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE;
    public static final int BANK_RECON_SCREEN = ScreenConstants.DISPLAY_MODE | 256;
    public static final int PAYMENT_DISTRIBUTION_SCREEN = LinkTrx.LINK_DISTRIBUTION_SCREEN;
    /**
     * Default constructor.
     */
    public BankTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankTrx(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recBankTrx = null;
        super.init(screen);
    }

    public static final String kBankTrxFile = "BankTrx";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBankTrxFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transaction";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "assetdr";
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
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == BankTrx.DISTRIBUTION_SCREEN)
            screen = new BankTrxDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & BankTrx.BANK_RECON_SCREEN) == BankTrx.BANK_RECON_SCREEN)
            screen = new BankReconScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & BankTrx.PAYMENT_DISTRIBUTION_SCREEN) == BankTrx.PAYMENT_DISTRIBUTION_SCREEN)
        {
            TrxDesc recTrxDesc = (TrxDesc)((ReferenceField)this.getField(BankTrx.PAYEE_TRX_DESC_ID)).getReference();
            if (properties == null)
                properties = new HashMap<String,Object>();
            properties.put(DBParams.HEADER_RECORD, this.getTableNames(false));
            properties.put(DBParams.HEADER_OBJECT_ID, this.getCounterField().toString());
            if (recTrxDesc != null)
                if ((recTrxDesc.getEditMode() == DBConstants.EDIT_CURRENT) || (recTrxDesc.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                    screen = recTrxDesc.makeLinkTrxScreen((ScreenLocation)itsLocation, (BasePanel)parentScreen, iDocMode, properties);
        }
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new BankTrxScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new BankTrxGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kBankAcctID)
        {
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxNumber)
            field = new IntegerField(this, "TrxNumber", 8, null, null);
        if (iFieldSeq == kEFTTrxNo)
            field = new StringField(this, "EFTTrxNo", 20, null, null);
        if (iFieldSeq == kTrxDate)
            field = new DateTimeField(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPayeeTrxDescID)
            field = new TrxDescField(this, "PayeeTrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPayeeID)
            field = new ReferenceField(this, "PayeeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPayeeName)
            field = new StringField(this, "PayeeName", 30, null, null);
        if (iFieldSeq == kAmount)
            field = new FullCurrencyField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kExchange)
            field = new RealField(this, "Exchange", 10, null, null);
        //if (iFieldSeq == kAmountLocal)
        //  field = new CurrencyField(this, "AmountLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxUserID)
        //  field = new BankTrx_TrxUserID(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvSign)
            field = new PreferredSignField(this, "InvSign", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvBalance)
            field = new FullCurrencyField(this, "InvBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvBalanceLocal)
            field = new CurrencyField(this, "InvBalanceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxEntry)
        //  field = new BankTrx_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kComments)
            field = new StringField(this, "Comments", 30, null, null);
        if (iFieldSeq == kDateReconciled)
            field = new DateTimeField(this, "DateReconciled", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kManual)
            field = new BooleanField(this, "Manual", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBankTrxLastField)
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
        if (iKeyArea == kTrxDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDate");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxClassKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxClass");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxStatusID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kInvBalanceKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "InvBalance");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kInvSign, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kInvBalance, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxNumberKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxNumber");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxNumber, DBConstants.ASCENDING);
        }
        if (iKeyArea == kPayeeIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PayeeID");
            keyArea.addKeyField(kPayeeID, DBConstants.ASCENDING);
            keyArea.addKeyField(kPayeeTrxDescID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBankTrxLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBankTrxLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.addListener(new VoidOnDeleteHandler(null));
        this.getField(BankTrx.INV_BALANCE).addListener(new UpdatePreferredSign(null, this.getField(BankTrx.INV_SIGN)));
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (BankTrx.PAYMENT_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return BankTrx.PAYMENT_DISTRIBUTION_SCREEN;
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return BankTrx.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }
    /**
     * Zero this part of the transaction.
     */
    public boolean onVoidTrx()
    {
        this.getField(BankTrx.AMOUNT).setValue(0);
        return super.onVoidTrx();
    }
    /**
     * Give this amount and trx type, calculate the exchange rate and the
     * USD amounts, so the G/L postings will be balanced.
     */
    public void calcUSDAmounts(boolean bUpdate)
    {
        Record recBankAcct = ((ReferenceField)this.getField(BankTrx.BANK_ACCT_ID)).getReference();
        if (recBankAcct == null)
            return; // Error - never.
        RecordOwner recordOwner = this.findRecordOwner();
        Record recAssetDrControl = null;
        if (recordOwner != null)
            recAssetDrControl = (Record)recordOwner.getRecord(AssetDrControl.ASSET_DR_CONTROL_FILE);
        if (recAssetDrControl == null)
            recAssetDrControl = new AssetDrControl(recordOwner);
        
        double dAmount = this.getField(BankTrx.AMOUNT).getValue();
        double dExchange = this.getField(BankTrx.EXCHANGE).getValue();
        double dAmountLocal = this.getField(BankTrx.AMOUNT_LOCAL).getValue();
        double dInventoryBalance = dAmount;
        double dInventoryBalanceLocal = dAmountLocal;
        double dTotalAppliedLocal = 0;
        
        if (recBankAcct.getField(BankAcct.CURRENCY_ID).equals(recAssetDrControl.getField(AssetDrControl.CURRENCY_ID)))
        {
            dExchange = 1.0;
            dAmountLocal = dAmount;
            dInventoryBalance = 0;
            dInventoryBalanceLocal = 0;
        }
        else
        {
            if (dInventoryBalance < 0)
                dExchange = 0.0;
            else
            {   // Deposits are the only transactions that can specify exchange rates
        //      TrxStatus recTrxStatus = ((ReferenceField)this.getField(BankTrx.TRX_STATUS_ID)).getReference();
            }
            if (dExchange == 0)
            {
                dAmountLocal = 0;
                // First check the inventory for balances that must be applied.
                if (m_recBankTrx == null)
                {
                    m_recBankTrx = new BankTrx(recordOwner);
                    if (recordOwner != null)
                        recordOwner.removeRecord(m_recBankTrx);
                    this.addListener(new FreeOnFreeHandler(m_recBankTrx));
                }
        
                BaseField fldBankAcct = this.getField(BankTrx.BANK_ACCT_ID);
                String strSign = PreferredSignField.POSITIVE;
                if (dInventoryBalance > 0)
                    strSign = PreferredSignField.NEGATIVE;
                FileListener filter = new StringSubFileFilter(fldBankAcct.toString(), BankTrx.BANK_ACCT_ID, strSign, BankTrx.INV_SIGN, null, null);
                m_recBankTrx.addListener(filter);
                m_recBankTrx.setKeyArea(BankTrx.INV_BALANCE_KEY);
                try {
                    m_recBankTrx.close();
                    while (m_recBankTrx.hasNext())
                    {
                        m_recBankTrx.next();
                        if (bUpdate)
                            m_recBankTrx.edit();
                        double dInvCurrent = m_recBankTrx.getField(BankTrx.INV_BALANCE).getValue();
                        double dInvCurrentLocal = m_recBankTrx.getField(BankTrx.INV_BALANCE_LOCAL).getValue();
                        if (dInvCurrent != 0)
                            dExchange = dInvCurrentLocal / dInvCurrent;
                        else
                            continue;   // Never
        
                        double dApplyAmount = 0;
                        if (dInventoryBalance > 0)
                            dApplyAmount = Math.min(-dInvCurrent, dInventoryBalance);
                        else
                            dApplyAmount = Math.max(-dInvCurrent, dInventoryBalance);
        
                        dInvCurrent = dInvCurrent + dApplyAmount;    // Opposite signs = (-)
                        double dNewInvCurrentLocal = Math.floor(dInvCurrent * dExchange * 100 + 0.5) / 100;
        
                        dInventoryBalance = dInventoryBalance - dApplyAmount;
                        double dApplyAmountLocal = dNewInvCurrentLocal - dInvCurrentLocal;
                        dTotalAppliedLocal = dTotalAppliedLocal + dApplyAmountLocal;
                        dAmountLocal = dAmountLocal + dApplyAmountLocal;
        
                        if (bUpdate)
                        {
                            m_recBankTrx.getField(BankTrx.INV_BALANCE).setValue(dInvCurrent);
                            m_recBankTrx.getField(BankTrx.INV_BALANCE_LOCAL).setValue(dNewInvCurrentLocal);
                            if (dNewInvCurrentLocal == 0)
                                m_recBankTrx.getField(BankTrx.INV_SIGN).setData(0);   // Inventory used up.
                            m_recBankTrx.set();
                        }
                        if (dInventoryBalance == 0)
                            break;
                    }
                } catch (DBException ex)    {
                    ex.printStackTrace();
                } finally {
                    m_recBankTrx.removeListener(filter, true);
                }
        
                if (dInventoryBalance != 0)
                {
                    Record recCurrencys = ((ReferenceField)recBankAcct.getField(BankAcct.CURRENCY_ID)).getReference();
                    if (recCurrencys == null)
                        dExchange = 1;  // Error
                    else
                        dExchange = recCurrencys.getField(Currencys.LAST_RATE).getValue();
                }
                dInventoryBalanceLocal = Math.floor(dInventoryBalance * dExchange * 100 + 0.5) / 100;
                dAmountLocal = dAmountLocal + dInventoryBalanceLocal;
            }
        }
        this.getField(BankTrx.AMOUNT_LOCAL).setValue(dAmountLocal);
        this.getField(BankTrx.INV_BALANCE).setValue(dInventoryBalance);
        this.getField(BankTrx.INV_BALANCE_LOCAL).setValue(dInventoryBalanceLocal);
    }

}
