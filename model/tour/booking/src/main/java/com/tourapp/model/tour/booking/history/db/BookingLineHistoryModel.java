/**
 * @(#)BookingLineHistoryModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.history.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingLineHistoryModel extends BookingLineModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String BOOKING_ID = BOOKING_ID;
    //public static final String BOOKING_PAX_ID = BOOKING_PAX_ID;
    //public static final String MODULE_ID = MODULE_ID;
    //public static final String TOUR_HEADER_DETAIL_ID = TOUR_HEADER_DETAIL_ID;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODULE_START_DATE = MODULE_START_DATE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String PRODUCT_TYPE = PRODUCT_TYPE;
    //public static final String REMOTE_REFERENCE_NO = REMOTE_REFERENCE_NO;
    //public static final String SEQUENCE = SEQUENCE;
    //public static final String PRICE = PRICE;
    //public static final String QUANTITY = QUANTITY;
    //public static final String GROSS = GROSS;
    //public static final String COMMISSIONABLE = COMMISSIONABLE;
    //public static final String COMMISSION_RATE = COMMISSION_RATE;
    //public static final String COMMISSION = COMMISSION;
    //public static final String NET = NET;
    //public static final String PRICING_STATUS_ID = PRICING_STATUS_ID;
    //public static final String PAY_AT = PAY_AT;
    //public static final String PAX_GROUPS = PAX_GROUPS;
    //public static final String BOOKING_DETAIL_ID = BOOKING_DETAIL_ID;
    //public static final String PAX_CATEGORY_ID = PAX_CATEGORY_ID;
    public static final String HISTORY_DATE = "HistoryDate";

    public static final String BOOKING_LINE_HISTORY_FILE = "BookingLineHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.history.db.BookingLineHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.history.db.BookingLineHistory";

}
