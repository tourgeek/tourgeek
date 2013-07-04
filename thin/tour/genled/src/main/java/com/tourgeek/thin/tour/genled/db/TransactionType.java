
package com.tourgeek.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.genled.db.*;

public class TransactionType extends FieldList
    implements TransactionTypeModel
{
    private static final long serialVersionUID = 1L;


    public TransactionType()
    {
        super();
    }
    public TransactionType(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TRANSACTION_TYPE_FILE = "TransactionType";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TransactionType.TRANSACTION_TYPE_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL | Constants.SHARED_DATA | Constants.LOCALIZABLE;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, TYPE_CODE, 20, null, null);
        field = new FieldInfo(this, TYPE_DESC, 30, null, null);
        field = new FieldInfo(this, TRX_GROUP_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GROUP_CODE, 20, null, null);
        field = new FieldInfo(this, GROUP_DESC, 30, null, null);
        field = new FieldInfo(this, TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DESC_CODE, 20, null, null);
        field = new FieldInfo(this, DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, TRX_SYSTEM_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SYSTEM_CODE, 20, null, null);
        field = new FieldInfo(this, SYSTEM_DESC, 30, null, null);
        field = new FieldInfo(this, TYPICAL_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, POSTING_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, SOURCE_FILE, 50, null, null);
        field = new FieldInfo(this, SOURCE_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SOURCE_TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SOURCE_PREFERRED_SIGN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, AMOUNT_FIELD, 50, null, null);
        field = new FieldInfo(this, TRX_DATE_FIELD, 50, null, null);
        field = new FieldInfo(this, ENTRY_DATE_FIELD, 50, null, null);
        field = new FieldInfo(this, USER_ID_FIELD, 50, null, null);
        field = new FieldInfo(this, TRX_ID_FIELD, 50, null, null);
        field = new FieldInfo(this, ACCOUNT_ID_FILE, 50, null, null);
        field = new FieldInfo(this, ACCOUNT_ID_FIELD, 50, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TRX_GROUP_ID_KEY);
        keyArea.addKeyField(TRX_GROUP_ID, Constants.ASCENDING);
        keyArea.addKeyField(TYPE_DESC, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TRX_TYPE_CODE_KEY);
        keyArea.addKeyField(SYSTEM_CODE, Constants.ASCENDING);
        keyArea.addKeyField(DESC_CODE, Constants.ASCENDING);
        keyArea.addKeyField(GROUP_CODE, Constants.ASCENDING);
        keyArea.addKeyField(TYPE_CODE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, SOURCE_TRX_STATUS_ID_KEY);
        keyArea.addKeyField(SOURCE_TRX_STATUS_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DESC_ID, Constants.ASCENDING);
    }

}
