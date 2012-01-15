/**
 * @(#)BookingCarModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingCarModel extends BookingDetailModel
{
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String DAYS = "Days";
    public static final String QUANTITY = "Quantity";

    public static final String BOOKING_CAR_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingCar";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingCar";

}
