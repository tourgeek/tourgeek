/**
 *  @(#)TourHeaderCruise.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class TourHeaderCruise extends com.tourapp.thin.tour.product.tour.detail.db.TourHeaderTransport
{

    public TourHeaderCruise()
    {
        super();
    }
    public TourHeaderCruise(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_HEADER_CRUISE_FILE = "TourHeaderDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourHeaderCruise.TOUR_HEADER_CRUISE_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.REMOTE | Constants.SHARED_TABLE | Constants.USER_DATA;
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
        field = new FieldInfo(this, "TourHeaderOptionID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModifyCode", 1, null, null);
        field = new FieldInfo(this, "ModifyID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Day", 2, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Etd", 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProductID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InfoMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InfoStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CostMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InventoryMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProductStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AckDays", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Comments", 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Nights", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Meal1", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealQty1", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealDays1", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Meal2", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealQty2", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealDays2", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Meal3", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealQty3", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealDays3", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Meal4", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealQty4", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealDays4", 2, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "PMCCutoff", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CityCode", 3, null, null);
        field = new FieldInfo(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ToCityCode", 3, null, null);
        field = new FieldInfo(this, "TourHeaderAirHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "GMTTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "CityDesc", 17, null, null);
        field = new FieldInfo(this, "ToCityDesc", 17, null, null);
        field = new FieldInfo(this, "TicketID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "XO", 1, null, null);
        field = new FieldInfo(this, "Carrier", 2, null, null);
        field = new FieldInfo(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "FlightNo", 4, null, null);
        field = new FieldInfo(this, "FlightClass", 1, null, null);
        field = new FieldInfo(this, "ArriveTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "AddDays", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "ARCStatus", 2, null, null);
        field = new FieldInfo(this, "FareBasis", 15, null, null);
        field = new FieldInfo(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Allow", 3, null, null);
        field = new FieldInfo(this, "DetFare", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ConfirmedBy", 16, null, null);
        field = new FieldInfo(this, "ConfirmationNo", 20, null, null);
        field = new FieldInfo(this, "Coupon", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Seat", 5, null, null);
        field = new FieldInfo(this, "Gate", 5, null, null);
        field = new FieldInfo(this, "SeatPerf", 1, null, null);
        field = new FieldInfo(this, "Smoking", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Meals", 2, null, null);
        field = new FieldInfo(this, "Days", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "PricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        super.setupKeys();
    }

}
