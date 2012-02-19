/*
 *  @(#)City.
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.tour.product.air.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class City extends FieldList
{
    public static final String ID = "ID";
    public static final String DESCRIPTION = "Description";
    public static final String CITY_CODE = "CityCode";

    public City()
    {
        super();
    }
    public City(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String CITY_FILE = "City";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? City.CITY_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Description", 40, null, null);
        field = new FieldInfo(this, "CityCode", 3, null, null);
        field = new FieldInfo(this, "TicketCityDesc", 16, null, null);
        field = new FieldInfo(this, "ICAOCode", 4, null, null);
        field = new FieldInfo(this, "MainCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "StateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CountryID", 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DomesticTax", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "InternationalTax", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ArrivalTax", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "FacilitiesTax", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "GMTOffset", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Latitude", 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Longitude", 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Altitude", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "CityType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "Map", 9999, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "CityCode");
        keyArea.addKeyField("CityCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CountryID");
        keyArea.addKeyField("CountryID", Constants.ASCENDING);
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TicketCityDesc");
        keyArea.addKeyField("TicketCityDesc", Constants.ASCENDING);
    }

}
