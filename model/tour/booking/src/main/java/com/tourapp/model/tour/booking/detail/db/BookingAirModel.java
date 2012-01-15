/**
 * @(#)BookingAirModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingAirModel extends BookingDetailModel
{
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String BOOKING_AIR_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.air.BookingAirScreen";
    public static final String BOOKING_AIR_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.air.BookingAirGridScreen";

    public static final String BOOKING_AIR_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingAir";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingAir";

}
