/**
 * @(#)BankTrxBatchDist.
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
import com.tourapp.tour.assetdr.screen.batch.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.assetdr.db.*;

/**
 *  BankTrxBatchDist - Temporary Distribution file for Bank Transactions..
 */
public class BankTrxBatchDist extends VirtualRecord
     implements BankTrxBatchDistModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kBankTrxBatchDetailID = kVirtualRecordLastField + 1;
    public static final int kAccountID = kBankTrxBatchDetailID + 1;
    public static final int kAmount = kAccountID + 1;
    public static final int kBankTrxBatchDistLastField = kAmount;
    public static final int kBankTrxBatchDistFields = kAmount - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBankTrxBatchDetailIDKey = kIDKey + 1;
    public static final int kBankTrxBatchDistLastKey = kBankTrxBatchDetailIDKey;
    public static final int kBankTrxBatchDistKeys = kBankTrxBatchDetailIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public BankTrxBatchDist()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankTrxBatchDist(RecordOwner screen)
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

    public static final String kBankTrxBatchDistFile = "BankTrxBatchDist";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBankTrxBatchDistFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Bank Transaction Distribution";
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
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new BankTrxBatchDistScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new BankTrxBatchDistGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kBankTrxBatchDetailID)
        {
            field = new ReferenceField(this, "BankTrxBatchDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kAccountID)
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmount)
            field = new CurrencyField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBankTrxBatchDistLastField)
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
        if (iKeyArea == kBankTrxBatchDetailIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BankTrxBatchDetailID");
            keyArea.addKeyField(kBankTrxBatchDetailID, DBConstants.ASCENDING);
            keyArea.addKeyField(kAccountID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBankTrxBatchDistLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBankTrxBatchDistLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
