/**
 * @(#)BookingLineModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingLineModel extends BookingSubModel
{
    public static final String DESCRIPTION = "Description";
    public static final String PRICE = "Price";
    public static final String QUANTITY = "Quantity";
    public static final String GROSS = "Gross";
    public static final String PRICING_STATUS_ID = "PricingStatusID";
    public static final String BOOKING_DETAIL_ID = "BookingDetailID";

    public static final String BOOKING_LINE_FILE = "BookingLine";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingLine";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingLine";

}
