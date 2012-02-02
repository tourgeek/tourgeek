/**
 * @(#)BookingSubModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import org.jbundle.model.db.*;

public interface BookingSubModel extends Rec
{

    //public static final String ID = ID;
    public static final String BOOKING_ID = "BookingID";
    public static final String BOOKING_PAX_ID = "BookingPaxID";
    public static final String MODULE_ID = "ModuleID";
    public static final String TOUR_HEADER_DETAIL_ID = "TourHeaderDetailID";
    public static final String TOUR_HEADER_OPTION_ID = "TourHeaderOptionID";
    public static final String MODULE_START_DATE = "ModuleStartDate";
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String REMOTE_REFERENCE_NO = "RemoteReferenceNo";

    public static final String DETAIL_ACCESS_KEY = "DetailAccess";

    public static final String BOOKING_KEY = "Booking";
    public static final String SOURCE_REFERENCE_NO = "SourceReferenceNo";

    public static final String BOOKING_SUB_FILE = "BookingSub";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingSub";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingSub";

}
