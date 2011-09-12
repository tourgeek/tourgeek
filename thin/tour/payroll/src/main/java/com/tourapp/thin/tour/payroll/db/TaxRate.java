/**
 * @(#)TaxRate.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class TaxRate extends FieldList
{

    public TaxRate()
    {
        super();
    }
    public TaxRate(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TAX_RATE_FILE = "TaxRate";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TaxRate.TAX_RATE_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "payroll";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL | Constants.USER_DATA;
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
        field = new FieldInfo(this, "TaxCode", 2, null, null);
        field = new FieldInfo(this, "MaritalStatus", 1, null, null);
        field = new FieldInfo(this, "CutOffAmount", 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TaxRate", 5, null, null);
        field.setDataClass(Float.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "TaxCode");
        keyArea.addKeyField("TaxCode", Constants.ASCENDING);
        keyArea.addKeyField("MaritalStatus", Constants.ASCENDING);
        keyArea.addKeyField("CutOffAmount", Constants.ASCENDING);
    }

}
