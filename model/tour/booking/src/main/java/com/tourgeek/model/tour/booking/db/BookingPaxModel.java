
package com.tourgeek.model.tour.booking.db;

import org.jbundle.model.db.*;

public interface BookingPaxModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String BOOKING_ID = "BookingID";
    public static final String SEQUENCE = "Sequence";
    public static final String REMOTE_REFERENCE_NO = "RemoteReferenceNo";
    public static final String PAX_CATEGORY_ID = "PaxCategoryID";
    public static final String PROFILE_ID = "ProfileID";
    public static final String SHARE_BOOKING_PAX_ID = "ShareBookingPaxID";
    public static final String COMMENTS = "Comments";
    public static final String NAME_PREFIX = "NamePrefix";
    public static final String FIRST_NAME = "FirstName";
    public static final String MIDDLE_NAME = "MiddleName";
    public static final String SUR_NAME = "SurName";
    public static final String DATE_OF_BIRTH = "DateOfBirth";
    public static final String GENDER = "Gender";
    public static final String SMOKER = "Smoker";

    public static final String BOOKING_ID_KEY = "BookingID";

    public static final String SUR_NAME_KEY = "SurName";

    public static final String PROFILE_ID_KEY = "ProfileID";
    public static final String SOURCE_REFERENCE_NO = "SourceReferenceNo";
    public static final String BOOKING_PAX_SCREEN_CLASS = "com.tourgeek.tour.booking.entry.pax.BookingPaxScreen";
    public static final String BOOKING_PAX_GRID_SCREEN_CLASS = "com.tourgeek.tour.booking.entry.pax.BookingPaxGridScreen";

    public static final String BOOKING_PAX_FILE = "BookingPax";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.booking.db.BookingPax";
    public static final String THICK_CLASS = "com.tourgeek.tour.booking.db.BookingPax";

}
