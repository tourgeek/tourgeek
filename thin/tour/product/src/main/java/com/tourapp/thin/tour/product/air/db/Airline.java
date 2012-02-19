/**
 * @(#)Airline.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.air.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.air.db.*;

public class Airline extends FieldList
    implements AirlineModel
{
    private static final long serialVersionUID = 1L;


    public Airline()
    {
        super();
    }
    public Airline(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String AIRLINE_FILE = "Airline";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Airline.AIRLINE_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
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
        field = new FieldInfo(this, DESCRIPTION, 40, null, null);
        field = new FieldInfo(this, SHORT_DESC, 16, null, null);
        field = new FieldInfo(this, AIRLINE_CODE, 2, null, null);
        field = new FieldInfo(this, ICAO_CODE, 4, null, null);
        field = new FieldInfo(this, COUNTRY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AIRLINE_NO, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CONTACT, 30, null, null);
        field = new FieldInfo(this, CONTACT_TITLE, 30, null, null);
        field = new FieldInfo(this, ACCEPT_MC_OS, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, MCO_SVC_CHG, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, MCO_REC_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MCO_VAR_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, OVERRIDE_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, OR_VAR_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LOGO, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "AirlineCode");
        keyArea.addKeyField("AirlineCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "AirlineNo");
        keyArea.addKeyField("AirlineNo", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "VendorID");
        keyArea.addKeyField("VendorID", Constants.ASCENDING);
    }

}
