/**
 * @(#)BookingLineHistoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.history.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingLineHistoryModel extends BookingLineModel
{

    //public static final String ID = ID;
    public static final String HISTORY_DATE = "HistoryDate";

    public static final String BOOKING_LINE_HISTORY_FILE = "BookingLineHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.history.db.BookingLineHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.history.db.BookingLineHistory";

}
