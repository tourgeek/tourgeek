/**
 *  @(#)Air.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.air.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class Air extends FieldList
{
    public static final String CITY_ID = "CityID";
    public static final String ETD = "Etd";
    public static final String CITY_CODE = "CityCode";
    public static final String TO_CITY_ID = "ToCityID";
    public static final String TO_CITY_CODE = "ToCityCode";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String FLIGHT_NO = "FlightNo";
    public static final String ARRIVE_TIME = "ArriveTime";
    public static final String ADD_DAYS = "AddDays";
    public static final String MEALS = "Meals";
    public static final String EQUIPMENT = "Equipment";
    public static final String DAYS = "Days";
    public static final String STOPS = "Stops";

    public Air()
    {
        super();
    }
    public Air(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String AIR_FILE = "Air";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Air.AIR_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "Description", 50, null, null);
        field = new FieldInfo(this, "Code", 10, null, null);
        field = new FieldInfo(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "OperatorsCode", 20, null, null);
        field = new FieldInfo(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Etd", 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, "AckDays", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Comments", 20, null, null);
        field = new FieldInfo(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "DescSort", 10, null, null);
        //field = new FieldInfo(this, "ProductType", 15, null, null);
        //field = new FieldInfo(this, "ProductCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "ProductCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "DisplayInventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "InventoryAvailability", 5, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        //field = new FieldInfo(this, "CurrencyCodeLocal", 3, null, null);
        //field = new FieldInfo(this, "VendorName", 30, null, null);
        //field = new FieldInfo(this, "DisplayCostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "PPCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "PPCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "ProductPriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "PPPriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, "CityCode", 3, null, null);
        field = new FieldInfo(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ToCityCode", 3, null, null);
        field = new FieldInfo(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "FlightNo", 4, null, null);
        field = new FieldInfo(this, "ArriveTime", 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, "AddDays", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Meals", 3, null, null);
        field = new FieldInfo(this, "StartDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "EndDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Equipment", 3, null, null);
        field = new FieldInfo(this, "Days", 4, null, "");
        field = new FieldInfo(this, "Classes", 6, null, null);
        field = new FieldInfo(this, "Stops", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Segment", 1, null, new Short((short)1));
        field.setDataClass(Short.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "Code");
        keyArea.addKeyField("Code", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CityCode");
        keyArea.addKeyField("CityCode", Constants.ASCENDING);
        keyArea.addKeyField("ToCityCode", Constants.ASCENDING);
        keyArea.addKeyField("AirlineID", Constants.ASCENDING);
        keyArea.addKeyField("FlightNo", Constants.ASCENDING);
        keyArea.addKeyField("EndDate", Constants.ASCENDING);
        keyArea.addKeyField("Days", Constants.ASCENDING);
        keyArea.addKeyField("Segment", Constants.ASCENDING);
    }

}
