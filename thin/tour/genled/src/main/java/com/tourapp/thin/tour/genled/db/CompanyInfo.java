/**
 * @(#)CompanyInfo.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.*;
import com.tourapp.model.tour.genled.db.*;

public class CompanyInfo extends Company
    implements CompanyInfoModel
{

    public CompanyInfo()
    {
        super();
    }
    public CompanyInfo(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String COMPANY_INFO_FILE = "CompanyInfo";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? CompanyInfo.COMPANY_INFO_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Code", 16, null, null);
        field = new FieldInfo(this, "CompanyName", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Tel", 24, null, null);
        field = new FieldInfo(this, "Fax", 24, null, null);
        field = new FieldInfo(this, "Email", 40, null, null);
        field = new FieldInfo(this, "Web", 60, null, null);
        field = new FieldInfo(this, "DateEntered", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "DateChanged", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ChangedID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Comments", 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Password", 16, null, null);
        field = new FieldInfo(this, "NameSort", 6, null, null);
        field = new FieldInfo(this, "PostalCodeSort", 5, null, null);
        field = new FieldInfo(this, "Contact", 30, null, null);
        field = new FieldInfo(this, "Logo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
