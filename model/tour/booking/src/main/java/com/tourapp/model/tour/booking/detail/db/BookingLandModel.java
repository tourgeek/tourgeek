/**
 * @(#)BookingLandModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingLandModel extends BookingDetailModel
{
    public static final String PMC_COST = "PMCCost";
    public static final String SIC_COST = "SICCost";
    public static final String BOOKING_LAND_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.land.BookingLandScreen";
    public static final String BOOKING_LAND_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.land.BookingLandGridScreen";

    public static final String BOOKING_LAND_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingLand";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingLand";

}
