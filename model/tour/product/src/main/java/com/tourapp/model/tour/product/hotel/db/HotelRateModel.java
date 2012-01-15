/**
 * @(#)HotelRateModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.hotel.db;

import com.tourapp.model.tour.product.base.db.*;

public interface HotelRateModel extends BaseRateModel
{
    public static final String HOTEL_RATE_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelRateScreen";
    public static final String HOTEL_RATE_GRID_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelRateGridScreen";

    public static final String HOTEL_RATE_FILE = "HotelRate";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.hotel.db.HotelRate";
    public static final String THICK_CLASS = "com.tourapp.tour.product.hotel.db.HotelRate";

}
