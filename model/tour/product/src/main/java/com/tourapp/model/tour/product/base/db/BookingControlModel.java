/**
 * @(#)BookingControlModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface BookingControlModel extends Rec
{

    //public static final String ID = ID;
    public static final String AUTO_BOOKING_CODE = "AutoBookingCode";
    public static final String AGENCY_COMM = "AgencyComm";
    public static final String DEPOSIT_DAYS = "DepositDays";
    public static final String ACCEPT_DAYS = "AcceptDays";
    public static final String FINAL_DAYS = "FinalDays";
    public static final String FINALIZATION_DAYS = "FinalizationDays";
    public static final String FINAL_DOC_DAYS = "FinalDocDays";
    public static final String TICKETING_DAYS = "TicketingDays";
    public static final String PROFILE_TYPE_ID = "ProfileTypeID";
    public static final String BOOKING_STATUS_ID = "BookingStatusID";
    public static final String XLD_BOOKING_STATUS_ID = "XldBookingStatusID";
    public static final String PAX_CATEGORY_ID = "PaxCategoryID";
    public static final String TOUR_STATUS_ID = "TourStatusID";
    public static final String XLD_TOUR_STATUS_ID = "XldTourStatusID";
    public static final String TOUR_CLASS_ID = "TourClassID";
    public static final String PRODUCT_CATEGORY_ID = "ProductCategoryID";
    public static final String TOUR_HEADER_ID = "TourHeaderID";
    public static final String THIN_TOUR_HEADER_ID = "ThinTourHeaderID";
    public static final String REMOTE_TOUR_HEADER_ID = "RemoteTourHeaderID";
    public static final String REMOTE_WAIT_TIME = "RemoteWaitTime";
    public static final String PAX = "Pax";
    public static final String SINGLE_PAX = "SinglePax";
    public static final String DOUBLE_PAX = "DoublePax";
    public static final String NIGHTS = "Nights";
    public static final String MARKUP = "Markup";
    public static final String TOUR_PRICING_TYPE_ID = "TourPricingTypeID";
    public static final String NON_TOUR_PRICING_TYPE_ID = "NonTourPricingTypeID";
    public static final String SERIES_TOUR_TYPE = "SeriesTourType";
    public static final String TOUR_HEADER_TOUR_TYPE = "TourHeaderTourType";
    public static final String MODULE_TOUR_TYPE = "ModuleTourType";
    public static final String THIN_TOUR_TYPE = "ThinTourType";

    public static final String BOOKING_CONTROL_FILE = "BookingControl";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.BookingControl";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.BookingControl";

}
