/**
 * @(#)AirModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.air.db;

import com.tourapp.model.tour.product.base.db.*;

public interface AirModel extends TransportProductModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String OPERATORS_CODE = OPERATORS_CODE;
    //public static final String PRODUCT_CHAIN_ID = PRODUCT_CHAIN_ID;
    //public static final String CITY_ID = CITY_ID;
    //public static final String ETD = ETD;
    //public static final String ACK_DAYS = ACK_DAYS;
    //public static final String COMMENTS = COMMENTS;
    //public static final String PROPERTIES = PROPERTIES;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
    //public static final String DESC_SORT = DESC_SORT;
    //public static final String PRODUCT_TYPE = PRODUCT_TYPE;
    //public static final String PRODUCT_COST = PRODUCT_COST;
    //public static final String PRODUCT_COST_LOCAL = PRODUCT_COST_LOCAL;
    //public static final String PRODUCT_MESSAGE_TRANSPORT_ID = PRODUCT_MESSAGE_TRANSPORT_ID;
    //public static final String DISPLAY_INVENTORY_STATUS_ID = DISPLAY_INVENTORY_STATUS_ID;
    //public static final String INVENTORY_AVAILABILITY = INVENTORY_AVAILABILITY;
    //public static final String CURRENCY_CODE = CURRENCY_CODE;
    //public static final String CURRENCY_CODE_LOCAL = CURRENCY_CODE_LOCAL;
    //public static final String VENDOR_NAME = VENDOR_NAME;
    //public static final String DISPLAY_COST_STATUS_ID = DISPLAY_COST_STATUS_ID;
    //public static final String PP_COST = PP_COST;
    //public static final String PP_COST_LOCAL = PP_COST_LOCAL;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String PRODUCT_PRICE_LOCAL = PRODUCT_PRICE_LOCAL;
    //public static final String PP_PRICE_LOCAL = PP_PRICE_LOCAL;
    //public static final String CITY_CODE = CITY_CODE;
    //public static final String TO_CITY_ID = TO_CITY_ID;
    //public static final String TO_CITY_CODE = TO_CITY_CODE;
    public static final String AIRLINE_ID = "AirlineID";
    public static final String FLIGHT_NO = "FlightNo";
    public static final String ARRIVE_TIME = "ArriveTime";
    public static final String ADD_DAYS = "AddDays";
    public static final String MEALS = "Meals";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String EQUIPMENT = "Equipment";
    public static final String DAYS = "Days";
    public static final String CLASSES = "Classes";
    public static final String STOPS = "Stops";
    public static final String SEGMENT = "Segment";

    public static final String CITY_CODE_KEY = "CityCode";
    public static final String AIR_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirScreen";
    public static final String AIR_GRID_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirGridScreen";

    public static final String AIR_FILE = "Air";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.air.db.Air";
    public static final String THICK_CLASS = "com.tourapp.tour.product.air.db.Air";

}
