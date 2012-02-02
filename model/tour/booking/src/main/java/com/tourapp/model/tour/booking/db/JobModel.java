/**
 * @(#)JobModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.db;

import org.jbundle.model.db.*;

public interface JobModel extends Rec
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = "Description";

    public static final String JOB_FILE = "Job";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.Job";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.Job";

}
