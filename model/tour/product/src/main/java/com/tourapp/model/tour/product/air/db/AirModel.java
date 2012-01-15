/**
 * @(#)AirModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.air.db;

import com.tourapp.model.tour.product.base.db.*;

public interface AirModel extends TransportProductModel
{
    public static final String AIRLINE_ID = "AirlineID";
    public static final String FLIGHT_NO = "FlightNo";
    public static final String ARRIVE_TIME = "ArriveTime";
    public static final String ADD_DAYS = "AddDays";
    public static final String MEALS = "Meals";
    public static final String EQUIPMENT = "Equipment";
    public static final String DAYS = "Days";
    public static final String STOPS = "Stops";
    public static final String AIR_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirScreen";
    public static final String AIR_GRID_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirGridScreen";

    public static final String AIR_FILE = "Air";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.air.db.Air";
    public static final String THICK_CLASS = "com.tourapp.tour.product.air.db.Air";

}
