/**
 * @(#)BookingCruiseModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingCruiseModel extends BookingDetailModel
{
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String BOOKING_CRUISE_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.cruise.BookingCruiseScreen";
    public static final String BOOKING_CRUISE_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.cruise.BookingCruiseGridScreen";

    public static final String BOOKING_CRUISE_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingCruise";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingCruise";

}
