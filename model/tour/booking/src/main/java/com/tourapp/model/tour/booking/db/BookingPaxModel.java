/**
 * @(#)BookingPaxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.db;

import org.jbundle.model.db.*;

public interface BookingPaxModel extends Rec
{
    public static final String REMOTE_REFERENCE_NO = "RemoteReferenceNo";
    public static final String PAX_CATEGORY_ID = "PaxCategoryID";
    public static final String COMMENTS = "Comments";
    public static final String FIRST_NAME = "FirstName";
    public static final String MIDDLE_NAME = "MiddleName";
    public static final String SUR_NAME = "SurName";
    public static final String SMOKER = "Smoker";
    public static final String SOURCE_REFERENCE_NO = "SourceReferenceNo";

    public static final String BOOKING_PAX_FILE = "BookingPax";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.BookingPax";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.BookingPax";

}
