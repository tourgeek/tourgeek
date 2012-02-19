/**
 * @(#)TrxGroup.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.genled.db.*;

public class TrxGroup extends FieldList
    implements TrxGroupModel
{
    private static final long serialVersionUID = 1L;


    public TrxGroup()
    {
        super();
    }
    public TrxGroup(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TRX_GROUP_FILE = "TrxGroup";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TrxGroup.TRX_GROUP_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, GROUP_CODE, 20, null, null);
        field = new FieldInfo(this, GROUP_DESC, 30, null, null);
        field = new FieldInfo(this, TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DESC_CODE, 20, null, null);
        field = new FieldInfo(this, TRX_SYSTEM_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SYSTEM_CODE, 20, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxDescID");
        keyArea.addKeyField("TrxDescID", Constants.ASCENDING);
        keyArea.addKeyField("GroupDesc", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "SystemCode");
        keyArea.addKeyField("SystemCode", Constants.ASCENDING);
        keyArea.addKeyField("DescCode", Constants.ASCENDING);
        keyArea.addKeyField("GroupCode", Constants.ASCENDING);
    }

}
