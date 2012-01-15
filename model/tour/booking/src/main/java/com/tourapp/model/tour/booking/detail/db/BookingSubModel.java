/**
 * @(#)BookingSubModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import org.jbundle.model.db.*;

public interface BookingSubModel extends Rec
{
    public static final String REMOTE_REFERENCE_NO = "RemoteReferenceNo";
    public static final String SOURCE_REFERENCE_NO = "SourceReferenceNo";

    public static final String BOOKING_SUB_FILE = "BookingSub";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingSub";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingSub";

}
