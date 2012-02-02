/**
 * @(#)CarModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.car.db;

import com.tourapp.model.tour.product.base.db.*;

public interface CarModel extends ProductModel
{

    //public static final String ID = ID;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    //public static final String CITY_ID = CITY_ID;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String OPERATORS_CODE = OPERATORS_CODE;
    //public static final String PRODUCT_CHAIN_ID = PRODUCT_CHAIN_ID;
    //public static final String COMMENTS = COMMENTS;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
    //public static final String DESC_SORT = DESC_SORT;
    //public static final String CLASS_ID = CLASS_ID;
    public static final String MANUAL_FILE = "ManualFile";
    public static final String VEHICLE = "Vehicle";
    public static final String PER_VEHICLE_COST = "PerVehicleCost";
    public static final String PER_VEHICLE_PRICE_LOCAL = "PerVehiclePriceLocal";
    public static final String CAR_SCREEN_CLASS = "com.tourapp.tour.product.car.screen.CarScreen";
    public static final String CAR_GRID_SCREEN_CLASS = "com.tourapp.tour.product.car.screen.CarGridScreen";

    public static final String CAR_FILE = "Car";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.car.db.Car";
    public static final String THICK_CLASS = "com.tourapp.tour.product.car.db.Car";

}
