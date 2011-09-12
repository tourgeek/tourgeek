/**
 * @(#)CashBatch.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db;

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
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import org.jbundle.main.user.db.*;

/**
 *  CashBatch - Cash Receipts.
 */
public class CashBatch extends BankTrxBatch
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kUserID = kUserID;
    //public static final int kBankAcctID = kBankAcctID;
    public static final int kDetailDate = kBankTrxBatchLastField + 1;
    public static final int kBatchChecks = kDetailDate + 1;
    public static final int kBatchChecksActual = kBatchChecks + 1;
    public static final int kBatchTotal = kBatchChecksActual + 1;
    public static final int kBatchTotalActual = kBatchTotal + 1;
    public static final int kCashBatchLastField = kBatchTotalActual;
    public static final int kCashBatchFields = kBatchTotalActual - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kUserIDKey = kIDKey + 1;
    public static final int kCashBatchLastKey = kUserIDKey;
    public static final int kCashBatchKeys = kUserIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public CashBatch()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CashBatch(RecordOwner screen)
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

    public static final String kCashBatchFile = "CashBatch";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCashBatchFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Cash Receipt";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctrec";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = new CashBatchDetailGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.POST_MODE)
            screen = new CashBatchPost(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new CashBatchScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new CashBatchGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kUserID)
        //  field = new UserField(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kBankAcctID)
        //  field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailDate)
        {
            field = new CashBatch_DetailDate(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBatchChecks)
            field = new ShortField(this, "BatchChecks", 4, null, null);
        if (iFieldSeq == kBatchChecksActual)
            field = new ShortField(this, "BatchChecksActual", 4, null, null);
        if (iFieldSeq == kBatchTotal)
            field = new CurrencyField(this, "BatchTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBatchTotalActual)
            field = new CurrencyField(this, "BatchTotalActual", 10, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCashBatchLastField)
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
        if (iKeyArea == kUserIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "UserID");
            keyArea.addKeyField(kUserID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCashBatchLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCashBatchLastKey)
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
        this.addListener(new EnableOnValidHandler(CashBatch.kBatchChecksActual, EnableOnValidHandler.DISABLE_ON_VALID, EnableOnValidHandler.DISABLE_ON_NEW));
        this.addListener(new EnableOnValidHandler(CashBatch.kBatchTotalActual, EnableOnValidHandler.DISABLE_ON_VALID, EnableOnValidHandler.DISABLE_ON_NEW));
        
        this.removeListener(this.getListener(SubFileIntegrityHandler.class.getName()), true);
        this.addListener(new SubFileIntegrityHandler(CashBatchDetail.class.getName(), true));
    }

}
