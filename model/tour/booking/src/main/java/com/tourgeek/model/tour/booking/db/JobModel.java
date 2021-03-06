/**
  * @(#)JobModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.booking.db;

import org.jbundle.model.db.*;

public interface JobModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";

    public static final String JOB_FILE = "Job";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.booking.db.Job";
    public static final String THICK_CLASS = "com.tourgeek.tour.booking.db.Job";

}
