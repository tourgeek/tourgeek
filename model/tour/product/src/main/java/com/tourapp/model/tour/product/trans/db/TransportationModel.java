/**
 * @(#)TransportationModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.trans.db;

import com.tourapp.model.tour.product.base.db.*;

public interface TransportationModel extends TransportProductModel
{

    //public static final String ID = ID;
    //public static final String CODE = CODE;
    //public static final String CITY_ID = CITY_ID;
    //public static final String CITY_CODE = CITY_CODE;
    //public static final String TO_CITY_ID = TO_CITY_ID;
    //public static final String TO_CITY_CODE = TO_CITY_CODE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String OPERATORS_CODE = OPERATORS_CODE;
    //public static final String PRODUCT_CHAIN_ID = PRODUCT_CHAIN_ID;
    //public static final String ETD = ETD;
    //public static final String COMMENTS = COMMENTS;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String DESC_SORT = DESC_SORT;
    public static final String TRANS_REVERSE_ID = "TransReverseID";
    public static final String MANUAL_FILE = "ManualFile";
    public static final String FREQUENCY = "Frequency";
    public static final String DISTANCE = "Distance";
    public static final String HOURS = "Hours";
    public static final String BREAKFASTS = "Breakfasts";
    public static final String LUNCHES = "Lunches";
    public static final String DINNERS = "Dinners";
    public static final String DAYS_OF_WEEK = "DaysOfWeek";
    public static final String DISCONTINUED_ON = "DiscontinuedOn";

    public static final String CITY_CODE_KEY = "CityCode";
    public static final String TRANSPORTATION_SCREEN_CLASS = "com.tourapp.tour.product.trans.screen.TransportationScreen";
    public static final String TRANSPORTATION_GRID_SCREEN_CLASS = "com.tourapp.tour.product.trans.screen.TransportationGridScreen";

    public static final String TRANSPORTATION_FILE = "Transportation";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.trans.db.Transportation";
    public static final String THICK_CLASS = "com.tourapp.tour.product.trans.db.Transportation";

}
