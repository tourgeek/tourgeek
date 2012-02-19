/**
 * @(#)BookingLineModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingLineModel extends BookingSubModel
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
    public static final String SEQUENCE = "Sequence";
    public static final String PRICE = "Price";
    public static final String QUANTITY = "Quantity";
    public static final String GROSS = "Gross";
    public static final String COMMISSIONABLE = "Commissionable";
    public static final String COMMISSION_RATE = "CommissionRate";
    public static final String COMMISSION = "Commission";
    public static final String NET = "Net";
    public static final String PRICING_STATUS_ID = "PricingStatusID";
    public static final String PAY_AT = "PayAt";
    public static final String PAX_GROUPS = "PaxGroups";
    public static final String BOOKING_DETAIL_ID = "BookingDetailID";
    public static final String PAX_CATEGORY_ID = "PaxCategoryID";

    public static final String BOOKING_ID_KEY = "BookingID";

    public static final String BOOKING_DETAIL_ID_KEY = "BookingDetailID";
    public static final String BOOKING_LINE_SCREEN_CLASS = "com.tourapp.tour.booking.entry.acctrec.BookingLineScreen";
    public static final String BOOKING_LINE_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.acctrec.BookingLineGridScreen";

    public static final String BOOKING_LINE_FILE = "BookingLine";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingLine";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingLine";

}
