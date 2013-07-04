
package com.tourgeek.model.tour.booking.detail.db;

import com.tourgeek.model.tour.booking.db.*;
import com.tourgeek.model.tour.product.tour.detail.db.*;
import java.util.*;
import org.jbundle.model.db.*;
import org.jbundle.model.db.*;

public interface BookingSubModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String BOOKING_ID = "BookingID";
    public static final String BOOKING_PAX_ID = "BookingPaxID";
    public static final String MODULE_ID = "ModuleID";
    public static final String TOUR_HEADER_DETAIL_ID = "TourHeaderDetailID";
    public static final String TOUR_HEADER_OPTION_ID = "TourHeaderOptionID";
    public static final String MODULE_START_DATE = "ModuleStartDate";
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String REMOTE_REFERENCE_NO = "RemoteReferenceNo";

    public static final String BOOKING_ID_KEY = "BookingID";

    public static final String DETAIL_ACCESS_KEY = "DetailAccess";
    public static final String SOURCE_REFERENCE_NO = "SourceReferenceNo";

    public static final String BOOKING_SUB_FILE = "BookingSub";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.booking.detail.db.BookingSub";
    public static final String THICK_CLASS = "com.tourgeek.tour.booking.detail.db.BookingSub";
    /**
     * AddDetailBehaviors Method.
     */
    public void addDetailBehaviors(BookingModel recBooking, TourModel recTour);
    /**
     * InitBookingDetailFields Method.
     */
    public int initBookingDetailFields(BookingModel recBooking, TourModel recTour, boolean bOnlyIfTargetIsNull);
    /**
     * Set-up the current product info.
     * If properties are supplied, look in the properties for new values.
     * Else, if the target values are not already set, use the default values
     * supplied in the tour and booking records.
     */
    public int setDetailProductInfo(Map<String,Object> properties, TourSubModel recTourHeaderDetail, BookingModel recBooking, TourModel recTour, Field fldPaxID, Field fldQaID, Field fldModID);
    /**
     * Get the main (Booking) record for this detail record.
     * Note: This will only return the main record if it already exists.
     */
    public BookingModel getBooking(boolean bCreateAndReadCurrent);

}
