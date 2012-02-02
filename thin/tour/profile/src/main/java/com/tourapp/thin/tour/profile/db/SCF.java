/**
 * @(#)SCF.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.profile.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.profile.db.*;

public class SCF extends FieldList
    implements SCFModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;

    public SCF()
    {
        super();
    }
    public SCF(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String SCF_FILE = "SCF";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? SCF.SCF_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
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
        field = new FieldInfo(this, "ScfFrom", 3, null, null);
        field = new FieldInfo(this, "ScfTo", 3, null, null);
        field = new FieldInfo(this, "Description", 25, null, null);
        field = new FieldInfo(this, "SalespersonID", 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "SalesRegionID", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "UpsZone", 1, null, null);
        field = new FieldInfo(this, "ZipZone", 1, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ScfTo");
        keyArea.addKeyField("ScfTo", Constants.ASCENDING);
        keyArea.addKeyField("ScfFrom", Constants.ASCENDING);
    }

}
