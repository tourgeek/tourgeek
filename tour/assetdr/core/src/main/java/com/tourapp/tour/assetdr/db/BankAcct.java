/**
 * @(#)BankAcct.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.assetdr.screen.*;
import com.tourapp.tour.assetdr.screen.trx.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.assetdr.db.*;

/**
 *  BankAcct - Bank Accounts.
 */
public class BankAcct extends VirtualRecord
     implements BankAcctModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kCurrencyID = kDescription + 1;
    public static final int kEFTRouting = kCurrencyID + 1;
    public static final int kBankABA = kEFTRouting + 1;
    public static final int kBankAcctNo = kBankABA + 1;
    public static final int kAccountID = kBankAcctNo + 1;
    public static final int kNextCheck = kAccountID + 1;
    public static final int kBalance = kNextCheck + 1;
    public static final int kBankAcctLastField = kBalance;
    public static final int kBankAcctFields = kBalance - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kCurrencyIDKey = kDescriptionKey + 1;
    public static final int kBankAcctLastKey = kCurrencyIDKey;
    public static final int kBankAcctKeys = kCurrencyIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int BANK_TRX_GRID_SCREEN = ScreenConstants.DETAIL_MODE;
    /**
     * Default constructor.
     */
    public BankAcct()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankAcct(RecordOwner screen)
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

    public static final String kBankAcctFile = "BankAcct";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBankAcctFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Account";
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == BankAcct.BANK_TRX_GRID_SCREEN)
            screen = new BankTrxGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new BankAcctScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new BankAcctGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 2, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kCurrencyID)
            field = new CurrencysField(this, "CurrencyID", 3, null, null);
        if (iFieldSeq == kEFTRouting)
        {
            field = new StringField(this, "EFTRouting", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBankABA)
        {
            field = new StringField(this, "BankABA", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBankAcctNo)
        {
            field = new StringField(this, "BankAcctNo", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAccountID)
        {
            field = new AccountField(this, "AccountID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kNextCheck)
            field = new IntegerField(this, "NextCheck", 8, null, new Integer(0));
        if (iFieldSeq == kBalance)
        {
            field = new CurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBankAcctLastField)
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
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCurrencyIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CurrencyID");
            keyArea.addKeyField(kCurrencyID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBankAcctLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBankAcctLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
