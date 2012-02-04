/**
 * @(#)HotelClassModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.hotel.db;

import com.tourapp.model.tour.product.base.db.*;

public interface HotelClassModel extends BaseClassModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    public static final String GENERAL_CODE = "GeneralCode";
    public static final String HOTEL_CLASS_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelClassScreen";
    public static final String HOTEL_CLASS_GRID_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelClassGridScreen";

    public static final String HOTEL_CLASS_FILE = "HotelClass";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.hotel.db.HotelClass";
    public static final String THICK_CLASS = "com.tourapp.tour.product.hotel.db.HotelClass";

}
