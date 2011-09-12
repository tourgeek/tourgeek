/**
 * @(#)Request.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.request.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class Request extends FieldList
{
    public static final String PROFILE_ID = "ProfileID";
    public static final String PROFILE_CODE = "ProfileCode";
    public static final String GENERIC_NAME = "GenericName";
    public static final String CITY_OR_TOWN = "CityOrTown";
    public static final String STATE_OR_REGION = "StateOrRegion";
    public static final String POSTAL_CODE = "PostalCode";
    public static final String COUNTRY = "Country";
    public static final String ATTENTION = "Attention";
    public static final String SEND_VIA_CODE = "SendViaCode";
    public static final String BUNDLE_ID = "BundleID";
    public static final String BUNDLE_QTY = "BundleQty";
    public static final String BROCHURE_TEXT = "BrochureText";
    public static final String PRINT_NOW = "PrintNow";
    public static final String HIST_REPRINT = "HistReprint";

    public Request()
    {
        super();
    }
    public Request(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String REQUEST_FILE = "Request";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Request.REQUEST_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "request";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA;
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
        field = new FieldInfo(this, "ProfileID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileCode", 16, null, null);
        field = new FieldInfo(this, "GenericName", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Attention", 24, null, null);
        field = new FieldInfo(this, "Email", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "SendViaCode", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BundleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BundleQty", 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "BrochureText", 255, null, null);
        field = new FieldInfo(this, "PrintNow", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "HistReprint", 10, null, new Boolean(false));
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "PostalCode");
        keyArea.addKeyField("PostalCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProfileCode");
        keyArea.addKeyField("ProfileCode", Constants.ASCENDING);
    }

}
