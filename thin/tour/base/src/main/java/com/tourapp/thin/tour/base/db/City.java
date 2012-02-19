/**
 * @(#)City.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.base.db.*;
import com.tourapp.model.tour.base.db.*;

public class City extends Location
    implements CityModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, CITY_CODE, 3, null, null);
        field = new FieldInfo(this, TICKET_CITY_DESC, 16, null, null);
        field = new FieldInfo(this, ICAO_CODE, 4, null, null);
        field = new FieldInfo(this, MAIN_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, STATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COUNTRY_ID, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DOMESTIC_TAX, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, INTERNATIONAL_TAX, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, ARRIVAL_TAX, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, FACILITIES_TAX, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, GMT_OFFSET, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, LATITUDE, 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, LONGITUDE, 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, ALTITUDE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, CITY_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, MAP, 9999, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "CityCode");
        keyArea.addKeyField("CityCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CountryID");
        keyArea.addKeyField("CountryID", Constants.ASCENDING);
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TicketCityDesc");
        keyArea.addKeyField("TicketCityDesc", Constants.ASCENDING);
    }

}
