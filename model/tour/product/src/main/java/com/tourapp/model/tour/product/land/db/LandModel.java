/**
 * @(#)LandModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.land.db;

import com.tourapp.model.tour.product.base.db.*;

public interface LandModel extends ProductModel
{

    //public static final String ID = ID;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String OPERATORS_CODE = OPERATORS_CODE;
    //public static final String PRODUCT_CHAIN_ID = PRODUCT_CHAIN_ID;
    //public static final String CITY_ID = CITY_ID;
    //public static final String ETD = ETD;
    //public static final String COMMENTS = COMMENTS;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
    //public static final String DESC_SORT = DESC_SORT;
    //public static final String CLASS_ID = CLASS_ID;
    public static final String TYPE = "Type";
    public static final String MANUAL_FILE = "ManualFile";
    public static final String HOURS = "Hours";
    public static final String DAYS = "Days";
    public static final String NIGHTS = "Nights";
    public static final String BREAKFASTS = "Breakfasts";
    public static final String LUNCHES = "Lunches";
    public static final String DINNERS = "Dinners";
    public static final String DAYS_OF_WEEK = "DaysOfWeek";
    public static final String VEHICLE = "Vehicle";
    public static final String PMC_COST = "PMCCost";
    public static final String PMC_COST_HOME = "PMCCostHome";
    public static final String SIC_COST = "SICCost";
    public static final String SIC_COST_HOME = "SICCostHome";
    public static final String PMC_PRICE_HOME = "PMCPriceHome";
    public static final String SIC_PRICE_HOME = "SICPriceHome";
    public static final String LAND_COST_PROPERTIES = "landCostProperties.";
    public static final String LAND_SCREEN_CLASS = "com.tourapp.tour.product.land.screen.LandScreen";
    public static final String LAND_GRID_SCREEN_CLASS = "com.tourapp.tour.product.land.screen.LandGridScreen";

    public static final String LAND_FILE = "Land";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.land.db.Land";
    public static final String THICK_CLASS = "com.tourapp.tour.product.land.db.Land";

}
