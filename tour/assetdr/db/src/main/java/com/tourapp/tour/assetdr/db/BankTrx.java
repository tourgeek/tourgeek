/**
 * @(#)BankTrx.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.assetdr.db.*;

/**
 *  BankTrx - Checking account desc..
 */
public class BankTrx extends BaseTrx
     implements BankTrxModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BANK_TRX_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
            screen = Record.makeNewScreen(BANK_TRX_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & BankTrx.BANK_RECON_SCREEN) == BankTrx.BANK_RECON_SCREEN)
            screen = Record.makeNewScreen(BANK_RECON_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & BankTrx.PAYMENT_DISTRIBUTION_SCREEN) == BankTrx.PAYMENT_DISTRIBUTION_SCREEN)
        {
            TrxDesc recTrxDesc = (TrxDesc)((ReferenceField)this.getField(BankTrx.PAYEE_TRX_DESC_ID)).getReference();
            if (properties == null)
                properties = new HashMap<String,Object>();
            properties.put(DBParams.HEADER_RECORD, this.getTableNames(false));
            properties.put(DBParams.HEADER_OBJECT_ID, this.getCounterField().toString());
            if (recTrxDesc != null)
                if ((recTrxDesc.getEditMode() == DBConstants.EDIT_CURRENT) || (recTrxDesc.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                    screen = recTrxDesc.makeLinkTrxScreen(itsLocation, parentScreen, iDocMode, properties);
        }
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BANK_TRX_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(BANK_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //  field = new BankTrx_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new DateTimeField(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new CurrencyField(this, AMOUNT_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new BankTrx_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
        {
            field = new BankAcctField(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 9)
            field = new IntegerField(this, TRX_NUMBER, 8, null, null);
        if (iFieldSeq == 10)
            field = new StringField(this, EFT_TRX_NO, 20, null, null);
        if (iFieldSeq == 11)
            field = new TrxDescField(this, PAYEE_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new ReferenceField(this, PAYEE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new StringField(this, PAYEE_NAME, 30, null, null);
        if (iFieldSeq == 14)
            field = new FullCurrencyField(this, AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new RealField(this, EXCHANGE, 10, null, null);
        if (iFieldSeq == 16)
            field = new PreferredSignField(this, INV_SIGN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new FullCurrencyField(this, INV_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new CurrencyField(this, INV_BALANCE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new StringField(this, COMMENTS, 30, null, null);
        if (iFieldSeq == 20)
            field = new DateTimeField(this, DATE_RECONCILED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new BooleanField(this, MANUAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDate");
            keyArea.addKeyField(BANK_ACCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxClass");
            keyArea.addKeyField(BANK_ACCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_STATUS_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "InvBalance");
            keyArea.addKeyField(BANK_ACCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(INV_SIGN, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(INV_BALANCE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxNumber");
            keyArea.addKeyField(BANK_ACCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_NUMBER, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PayeeID");
            keyArea.addKeyField(PAYEE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(PAYEE_TRX_DESC_ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
