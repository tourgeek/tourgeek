/**
 * @(#)TourHeaderHotel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.tour.detail.db.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

public class TourHeaderHotel extends TourHeaderDetail
    implements TourHeaderHotelModel
{
    private static final long serialVersionUID = 1L;


    public TourHeaderHotel()
    {
        super();
    }
    public TourHeaderHotel(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_HEADER_HOTEL_FILE = "TourHeaderDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourHeaderHotel.TOUR_HEADER_HOTEL_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, TOUR_HEADER_OPTION_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODIFY_CODE, 1, null, null);
        field = new FieldInfo(this, MODIFY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DAY, 2, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ETD, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, RATE_ID, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CLASS_ID, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INFO_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INFO_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COST_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INVENTORY_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACK_DAYS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, COMMENTS, 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, NIGHTS, 2, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_1, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_QTY_1, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_DAYS_1, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_2, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_QTY_2, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_DAYS_2, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_3, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_QTY_3, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_DAYS_3, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_4, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_QTY_4, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_DAYS_4, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, PMC_CUTOFF, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CITY_CODE, 3, null, null);
        field = new FieldInfo(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TO_CITY_CODE, 3, null, null);
        field = new FieldInfo(this, TOUR_HEADER_AIR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GMT_TIME, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, CITY_DESC, 17, null, null);
        field = new FieldInfo(this, TO_CITY_DESC, 17, null, null);
        field = new FieldInfo(this, TICKET_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, XO, 1, null, null);
        field = new FieldInfo(this, CARRIER, 2, null, null);
        field = new FieldInfo(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, FLIGHT_NO, 4, null, null);
        field = new FieldInfo(this, FLIGHT_CLASS, 1, null, null);
        field = new FieldInfo(this, ARRIVE_TIME, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, ADD_DAYS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ARC_STATUS, 2, null, "OK");
        field = new FieldInfo(this, FARE_BASIS, 15, null, null);
        field = new FieldInfo(this, START_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ALLOW, 3, null, null);
        field = new FieldInfo(this, DET_FARE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, CONFIRMED_BY, 16, null, null);
        field = new FieldInfo(this, CONFIRMATION_NO, 20, null, null);
        field = new FieldInfo(this, COUPON, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SEAT, 5, null, null);
        field = new FieldInfo(this, GATE, 5, null, null);
        field = new FieldInfo(this, SEAT_PERF, 1, null, null);
        field = new FieldInfo(this, SMOKING, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, MEALS, 2, null, null);
        field = new FieldInfo(this, DAYS, 8, null, new Float(1));
        field.setDataClass(Float.class);
        field = new FieldInfo(this, PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourHeaderOptionID");
        keyArea.addKeyField("TourHeaderOptionID", Constants.ASCENDING);
        keyArea.addKeyField("Day", Constants.ASCENDING);
        keyArea.addKeyField("Etd", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProductID");
        keyArea.addKeyField("ProductID", Constants.ASCENDING);
        keyArea.addKeyField("Day", Constants.ASCENDING);
        keyArea.addKeyField("Etd", Constants.ASCENDING);
    }

}
