/**
 * @(#)PeriodModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface PeriodModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String END_PERIOD = "EndPeriod";
    public static final String PERIOD_CLOSED = "PeriodClosed";

    public static final String END_PERIOD_KEY = "EndPeriod";

    public static final String PERIOD_FILE = "Period";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.Period";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.Period";

}
