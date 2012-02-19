/**
 * @(#)SCF.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.profile.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.profile.db.*;

public class SCF extends FieldList
    implements SCFModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, SCF_FROM, 3, null, null);
        field = new FieldInfo(this, SCF_TO, 3, null, null);
        field = new FieldInfo(this, DESCRIPTION, 25, null, null);
        field = new FieldInfo(this, SALESPERSON_ID, 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SALES_REGION_ID, 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, UPS_ZONE, 1, null, null);
        field = new FieldInfo(this, ZIP_ZONE, 1, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ScfTo");
        keyArea.addKeyField("ScfTo", Constants.ASCENDING);
        keyArea.addKeyField("ScfFrom", Constants.ASCENDING);
    }

}
