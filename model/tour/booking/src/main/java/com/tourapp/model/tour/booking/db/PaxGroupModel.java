/**
  * @(#)PaxGroupModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.booking.db;

import org.jbundle.model.db.*;

public interface PaxGroupModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String BOOKING_ID = "BookingID";
    public static final String GROUP_NO = "GroupNo";
    public static final String GROUP_DESCRIPTION = "GroupDescription";
    public static final String PAX_ID = "PaxID";

    public static final String BOOKING_ID_KEY = "BookingID";

    public static final String PAX_GROUP_FILE = "PaxGroup";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.PaxGroup";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.PaxGroup";

}
