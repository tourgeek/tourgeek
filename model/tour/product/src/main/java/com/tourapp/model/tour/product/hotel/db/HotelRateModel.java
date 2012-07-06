/**
  * @(#)HotelRateModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.product.hotel.db;

import com.tourapp.model.tour.product.base.db.*;

public interface HotelRateModel extends BaseRateModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    public static final String GENERAL_RATE = "GeneralRate";
    public static final String HOTEL_RATE_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelRateScreen";
    public static final String HOTEL_RATE_GRID_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelRateGridScreen";

    public static final String HOTEL_RATE_FILE = "HotelRate";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.hotel.db.HotelRate";
    public static final String THICK_CLASS = "com.tourapp.tour.product.hotel.db.HotelRate";

}
