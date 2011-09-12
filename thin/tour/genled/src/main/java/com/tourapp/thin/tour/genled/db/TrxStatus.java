/**
 * @(#)TrxStatus.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class TrxStatus extends FieldList
{

    public TrxStatus()
    {
        super();
    }
    public TrxStatus(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TRX_STATUS_FILE = "TrxStatus";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TrxStatus.TRX_STATUS_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "StatusCode", 20, null, null);
        field = new FieldInfo(this, "StatusDesc", 30, null, null);
        field = new FieldInfo(this, "PreferredSign", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "TrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DescCode", 20, null, null);
        field = new FieldInfo(this, "TrxSystemID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "SystemCode", 20, null, null);
        field = new FieldInfo(this, "ActiveTrx", 10, null, new Boolean(true));
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxDescID");
        keyArea.addKeyField("TrxDescID", Constants.ASCENDING);
        keyArea.addKeyField("StatusDesc", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "SystemCode");
        keyArea.addKeyField("SystemCode", Constants.ASCENDING);
        keyArea.addKeyField("DescCode", Constants.ASCENDING);
        keyArea.addKeyField("StatusCode", Constants.ASCENDING);
    }

}
