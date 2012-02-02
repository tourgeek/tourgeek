/**
 * @(#)BookingDetailHistoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.history.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingDetailHistoryModel extends BookingDetailModel
{

    //public static final String ID = ID;
    public static final String HISTORY_DATE = "HistoryDate";

    public static final String BOOKING_DETAIL_HISTORY_FILE = "BookingDetailHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.history.db.BookingDetailHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.history.db.BookingDetailHistory";

}
