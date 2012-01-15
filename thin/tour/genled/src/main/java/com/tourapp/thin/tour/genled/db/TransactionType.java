/**
 * @(#)TransactionType.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.genled.db.*;

public class TransactionType extends FieldList
    implements TransactionTypeModel
{

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
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "TypeCode", 20, null, null);
        field = new FieldInfo(this, "TypeDesc", 30, null, null);
        field = new FieldInfo(this, "TrxGroupID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "GroupCode", 20, null, null);
        field = new FieldInfo(this, "GroupDesc", 30, null, null);
        field = new FieldInfo(this, "TrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DescCode", 20, null, null);
        field = new FieldInfo(this, "Description", 30, null, null);
        field = new FieldInfo(this, "TrxSystemID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "SystemCode", 20, null, null);
        field = new FieldInfo(this, "SystemDesc", 30, null, null);
        field = new FieldInfo(this, "TypicalBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "PostingType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "SourceFile", 50, null, null);
        field = new FieldInfo(this, "SourceTrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "SourceTrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "SourcePreferredSign", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "AmountField", 50, null, null);
        field = new FieldInfo(this, "TrxDateField", 50, null, null);
        field = new FieldInfo(this, "EntryDateField", 50, null, null);
        field = new FieldInfo(this, "UserIDField", 50, null, null);
        field = new FieldInfo(this, "TrxIDField", 50, null, null);
        field = new FieldInfo(this, "AccountIDFile", 50, null, null);
        field = new FieldInfo(this, "AccountIDField", 50, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxGroupID");
        keyArea.addKeyField("TrxGroupID", Constants.ASCENDING);
        keyArea.addKeyField("TypeDesc", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxTypeCode");
        keyArea.addKeyField("SystemCode", Constants.ASCENDING);
        keyArea.addKeyField("DescCode", Constants.ASCENDING);
        keyArea.addKeyField("GroupCode", Constants.ASCENDING);
        keyArea.addKeyField("TypeCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "SourceTrxStatusID");
        keyArea.addKeyField("SourceTrxStatusID", Constants.ASCENDING);
        keyArea.addKeyField("TrxDescID", Constants.ASCENDING);
    }

}
