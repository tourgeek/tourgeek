/**
 * @(#)AirModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.air.db;

import com.tourapp.model.tour.product.base.db.*;

public interface AirModel extends TransportProductModel
{

    //public static final String ID = ID;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    //public static final String CITY_ID = CITY_ID;
    //public static final String CITY_CODE = CITY_CODE;
    //public static final String TO_CITY_ID = TO_CITY_ID;
    //public static final String TO_CITY_CODE = TO_CITY_CODE;
    //public static final String PRODUCT_CHAIN_ID = PRODUCT_CHAIN_ID;
    //public static final String ETD = ETD;
    //public static final String COMMENTS = COMMENTS;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
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
