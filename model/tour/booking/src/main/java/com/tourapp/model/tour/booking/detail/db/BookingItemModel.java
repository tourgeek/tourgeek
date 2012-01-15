/**
 * @(#)BookingItemModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingItemModel extends BookingDetailModel
{
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String BOOKING_ITEM_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.item.BookingItemScreen";
    public static final String BOOKING_ITEM_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.item.BookingItemGridScreen";

    public static final String BOOKING_ITEM_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingItem";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingItem";

}
