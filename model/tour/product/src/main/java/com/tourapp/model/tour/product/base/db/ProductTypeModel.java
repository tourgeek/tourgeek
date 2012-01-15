/**
 * @(#)ProductTypeModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface ProductTypeModel extends Rec
{
    public static final String AIR = "Air";
    public static final int AIR_ID = 1;
    public static final String CAR = "Car";
    public static final int CAR_ID = 2;
    public static final String CRUISE = "Cruise";
    public static final int CRUISE_ID = 8;
    public static final String HOTEL = "Hotel";
    public static final int HOTEL_ID = 3;
    public static final String ITEM = "Item";
    public static final int ITEM_ID = 4;
    public static final String LAND = "Land";
    public static final int LAND_ID = 9;
    public static final String TOUR = "Tour";
    public static final int TOUR_ID = 6;
    public static final String TRANSPORTATION = "Transportation";
    public static final int TRANSPORTATION_ID = 7;
    public static final String UNKNOWN = "Unknown";
    public static final int UNKNOWN_ID = 0;
    public static final String TOUR_CODE = "T";
    public static final String AIR_CODE = "A";
    public static final String HOTEL_CODE = "H";
    public static final String LAND_CODE = "L";
    public static final String TRANSPORTATION_CODE = "R";
    public static final String CAR_CODE = "V";
    public static final String CRUISE_CODE = "U";
    public static final String ITEM_CODE = "I";
    public static final String UNKNOWN_CODE = "U";

    public static final String PRODUCT_TYPE_FILE = "ProductType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.ProductType";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.ProductType";

}
