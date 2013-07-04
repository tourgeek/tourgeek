/**
  * @(#)BookingAnswerModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.booking.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingAnswerModel extends BookingSubModel
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
    public static final String TOUR_OR_OPTION = "TourOrOption";
    public static final String TOUR_OR_OPTION_ID = "TourOrOptionID";
    public static final String SEQUENCE = "Sequence";
    public static final String ASK_FOR_ANSWER = "AskForAnswer";
    public static final String ALWAYS_RESOLVE = "AlwaysResolve";
    public static final String DETAIL_OPTION_EXISTS = "DetailOptionExists";
    public static final String DETAIL_PRICE_EXISTS = "DetailPriceExists";
    public static final String DETAIL_AIR_HEADER_EXISTS = "DetailAirHeaderExists";
    public static final String TOUR_DETAIL_EXISTS = "TourDetailExists";
    public static final String SELECTED = "Selected";
    public static final String DETAIL_ADDED = "DetailAdded";

    public static final String SCAN_ORDER_KEY = "ScanOrder";
    public static final String BOOKING_ANSWER_SCREEN_CLASS = "com.tourapp.tour.booking.entry.tour.BookingAnswerScreen";
    public static final String BOOKING_ANSWER_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.tour.BookingAnswerGridScreen";

    public static final String BOOKING_ANSWER_FILE = "BookingAnswer";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.BookingAnswer";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.BookingAnswer";

}
