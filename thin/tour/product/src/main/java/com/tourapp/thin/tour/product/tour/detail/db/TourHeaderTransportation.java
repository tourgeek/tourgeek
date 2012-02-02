/**
 * @(#)TourHeaderTransportation.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.tour.detail.db.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

public class TourHeaderTransportation extends TourHeaderTransport
    implements TourHeaderTransportationModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODIFY_CODE = MODIFY_CODE;
    //public static final String MODIFY_ID = MODIFY_ID;
    //public static final String DAY = DAY;
    //public static final String ETD = ETD;
    //public static final String PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    //public static final String PRODUCT_ID = PRODUCT_ID;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String INFO_MESSAGE_TRANSPORT_ID = INFO_MESSAGE_TRANSPORT_ID;
    //public static final String INFO_STATUS_ID = INFO_STATUS_ID;
    //public static final String COST_MESSAGE_TRANSPORT_ID = COST_MESSAGE_TRANSPORT_ID;
    //public static final String COST_STATUS_ID = COST_STATUS_ID;
    //public static final String INVENTORY_MESSAGE_TRANSPORT_ID = INVENTORY_MESSAGE_TRANSPORT_ID;
    //public static final String INVENTORY_STATUS_ID = INVENTORY_STATUS_ID;
    //public static final String PRODUCT_MESSAGE_TRANSPORT_ID = PRODUCT_MESSAGE_TRANSPORT_ID;
    //public static final String PRODUCT_STATUS_ID = PRODUCT_STATUS_ID;
    //public static final String ACK_DAYS = ACK_DAYS;
    //public static final String COMMENTS = COMMENTS;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
    //public static final String NIGHTS = NIGHTS;
    //public static final String MEAL_1 = MEAL_1;
    //public static final String MEAL_QTY_1 = MEAL_QTY_1;
    //public static final String MEAL_DAYS_1 = MEAL_DAYS_1;
    //public static final String MEAL_2 = MEAL_2;
    //public static final String MEAL_QTY_2 = MEAL_QTY_2;
    //public static final String MEAL_DAYS_2 = MEAL_DAYS_2;
    //public static final String MEAL_3 = MEAL_3;
    //public static final String MEAL_QTY_3 = MEAL_QTY_3;
    //public static final String MEAL_DAYS_3 = MEAL_DAYS_3;
    //public static final String MEAL_4 = MEAL_4;
    //public static final String MEAL_QTY_4 = MEAL_QTY_4;
    //public static final String MEAL_DAYS_4 = MEAL_DAYS_4;
    //public static final String PMC_CUTOFF = PMC_CUTOFF;
    //public static final String CITY_ID = CITY_ID;
    //public static final String CITY_CODE = CITY_CODE;
    //public static final String TO_CITY_ID = TO_CITY_ID;
    //public static final String TO_CITY_CODE = TO_CITY_CODE;
    //public static final String TOUR_HEADER_AIR_HEADER_ID = TOUR_HEADER_AIR_HEADER_ID;
    //public static final String GMT_TIME = GMT_TIME;
    //public static final String CITY_DESC = CITY_DESC;
    //public static final String TO_CITY_DESC = TO_CITY_DESC;
    //public static final String TICKET_ID = TICKET_ID;
    //public static final String XO = XO;
    //public static final String CARRIER = CARRIER;
    //public static final String AIRLINE_ID = AIRLINE_ID;
    //public static final String FLIGHT_NO = FLIGHT_NO;
    //public static final String FLIGHT_CLASS = FLIGHT_CLASS;
    //public static final String ARRIVE_TIME = ARRIVE_TIME;
    //public static final String ADD_DAYS = ADD_DAYS;
    //public static final String ARC_STATUS = ARC_STATUS;
    //public static final String FARE_BASIS = FARE_BASIS;
    //public static final String START_DATE = START_DATE;
    //public static final String END_DATE = END_DATE;
    //public static final String ALLOW = ALLOW;
    //public static final String DET_FARE = DET_FARE;
    //public static final String CONFIRMED_BY = CONFIRMED_BY;
    //public static final String CONFIRMATION_NO = CONFIRMATION_NO;
    //public static final String COUPON = COUPON;
    //public static final String SEAT = SEAT;
    //public static final String GATE = GATE;
    //public static final String SEAT_PERF = SEAT_PERF;
    //public static final String SMOKING = SMOKING;
    //public static final String MEALS = MEALS;
    //public static final String DAYS = DAYS;
    //public static final String PRICING_TYPE_ID = PRICING_TYPE_ID;

    public TourHeaderTransportation()
    {
        super();
    }
    public TourHeaderTransportation(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_HEADER_TRANSPORTATION_FILE = "TourHeaderDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourHeaderTransportation.TOUR_HEADER_TRANSPORTATION_FILE : super.getTableNames(bAddQuotes);
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
