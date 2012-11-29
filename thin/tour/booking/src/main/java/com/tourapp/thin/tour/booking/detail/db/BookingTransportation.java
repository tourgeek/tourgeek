/**
  * @(#)BookingTransportation.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.booking.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

public class BookingTransportation extends BookingDetail
    implements BookingTransportationModel
{
    private static final long serialVersionUID = 1L;


    public BookingTransportation()
    {
        super();
    }
    public BookingTransportation(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_TRANSPORTATION_FILE = "BookingDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingTransportation.BOOKING_TRANSPORTATION_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_PAX_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODULE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODULE_START_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, DESCRIPTION, 60, null, null);
        //field = new FieldInfo(this, PRODUCT_TYPE, 15, null, null);
        field = new FieldInfo(this, REMOTE_REFERENCE_NO, 60, null, null);
        field = new FieldInfo(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DETAIL_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, GMT_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, GMT_OFFSET, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MARKUP_FROM_LAST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, VENDOR_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRICING_DETAIL_KEY, 128, null, null);
        field = new FieldInfo(this, TOTAL_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, EXCHANGE, 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        //field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        field = new FieldInfo(this, TOTAL_COST_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TOTAL_PRICE_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, INFO_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INFO_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INFO_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, INFO_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COST_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COST_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, COST_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, INVENTORY_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INVENTORY_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, INVENTORY_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, PRODUCT_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, REMOTE_BOOKING_NO, 127, null, null);
        field = new FieldInfo(this, ACK_DAYS, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DETAIL_END_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, GMT_END_DATE, 25, null, null);
        field.setDataClass(Date.class);
        //field = new FieldInfo(this, MEAL_SUMMARY, 255, null, null);
        //field = new FieldInfo(this, STATUS_SUMMARY, 20, null, null);
        //field.setDataClass(Integer.class);
        field = new FieldInfo(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, ERROR_PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, PP_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PP_PRICE_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, NIGHTS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_1ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_1_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_1_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_2ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_2_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_2_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_3ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_3_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_3_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_4ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_4_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_4_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        //field = new FieldInfo(this, SINGLE_PAX, 4, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, DOUBLE_PAX, 2, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, TRIPLE_PAX, 2, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, QUAD_PAX, 2, null, null);
        //field.setDataClass(Short.class);
        field = new FieldInfo(this, CHILDREN, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SINGLE_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DOUBLE_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TRIPLE_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, QUAD_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, CHILDREN_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, ROOM_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MEAL_COST, 18, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, ROOM_COST_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, MEAL_COST_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, VARIES_CODE, 1, null, "");
        field = new FieldInfo(this, VARIES_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, VARIES_COST, 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PMC_CUTOFF, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, PMC_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SIC_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, BOOKING_AIR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GMT_TIME, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, ETD, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, XO, 1, null, null);
        field = new FieldInfo(this, CITY_CODE, 3, null, null);
        field = new FieldInfo(this, CITY_DESC, 17, null, null);
        field = new FieldInfo(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CARRIER, 2, null, null);
        field = new FieldInfo(this, FLIGHT_NO, 4, null, null);
        field = new FieldInfo(this, FLIGHT_CLASS, 1, null, null);
        field = new FieldInfo(this, TO_CITY_CODE, 3, null, null);
        field = new FieldInfo(this, TO_CITY_DESC, 17, null, null);
        field = new FieldInfo(this, ARRIVE_TIME, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, ADD_DAYS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ARC_STATUS, 2, null, "OK");
        field = new FieldInfo(this, FARE_BASIS, 15, null, null);
        field = new FieldInfo(this, START_DATE, 5, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_DATE, 5, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ALLOW, 3, null, null);
        field = new FieldInfo(this, DET_FARE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SEGMENT_CONF_NO, 128, null, null);
        field = new FieldInfo(this, SEGMENT_CONFIRMED_BY, 50, null, null);
        field = new FieldInfo(this, COUPON, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SEAT, 5, null, null);
        field = new FieldInfo(this, GATE, 5, null, null);
        field = new FieldInfo(this, SEAT_PREF, 1, null, null);
        field = new FieldInfo(this, SMOKING, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, MEALS, 2, null, null);
        field = new FieldInfo(this, DAYS, 8, null, new Float(1));
        field.setDataClass(Float.class);
        field = new FieldInfo(this, QUANTITY, 5, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ASK_FOR_ANSWER, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, ALWAYS_RESOLVE, 10, null, new Boolean(false));
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
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
