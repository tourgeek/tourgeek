/**
 * @(#)CruiseModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.cruise.db;

import com.tourapp.model.tour.product.base.db.*;

public interface CruiseModel extends TransportProductModel
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
    public static final String CRUISE_REVERSE_ID = "CruiseReverseID";
    public static final String CRUISE_MAN_FILE = "CruiseManFile";
    public static final String FREQUENCY = "Frequency";
    public static final String DISTANCE = "Distance";
    public static final String DAYS = "Days";
    public static final String BREAKFASTS = "Breakfasts";
    public static final String LUNCHES = "Lunches";
    public static final String DINNERS = "Dinners";
    public static final String DISCONTINUED_ON = "DiscontinuedOn";
    public static final String CRUISE_SCREEN_CLASS = "com.tourapp.tour.product.cruise.screen.CruiseScreen";
    public static final String CRUISE_GRID_SCREEN_CLASS = "com.tourapp.tour.product.cruise.screen.CruiseGridScreen";

    public static final String CRUISE_FILE = "Cruise";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.cruise.db.Cruise";
    public static final String THICK_CLASS = "com.tourapp.tour.product.cruise.db.Cruise";

}
