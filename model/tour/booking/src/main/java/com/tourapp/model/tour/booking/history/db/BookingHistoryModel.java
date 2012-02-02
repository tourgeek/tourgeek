/**
 * @(#)BookingHistoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.history.db;

import com.tourapp.model.tour.booking.db.*;

public interface BookingHistoryModel extends BookingModel
{

    //public static final String ID = ID;
    public static final String HISTORY_DATE = "HistoryDate";

    public static final String BOOKING_HISTORY_FILE = "BookingHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.history.db.BookingHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.history.db.BookingHistory";

}
