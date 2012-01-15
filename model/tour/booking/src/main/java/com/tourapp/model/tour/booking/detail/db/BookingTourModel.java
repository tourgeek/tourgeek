/**
 * @(#)BookingTourModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingTourModel extends BookingDetailModel
{
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String BOOKING_TOUR_DETAIL_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.tour.BookingTourDetailScreen";
    public static final String BOOKING_TOUR_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.tour.BookingTourDetailGridScreen";

    public static final String BOOKING_TOUR_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingTour";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingTour";

}
