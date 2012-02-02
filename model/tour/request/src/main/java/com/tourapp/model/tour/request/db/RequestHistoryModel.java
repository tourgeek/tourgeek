/**
 * @(#)RequestHistoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.request.db;

import com.tourapp.model.tour.request.db.*;

public interface RequestHistoryModel extends RequestModel
{

    //public static final String HIST_REPRINT = HIST_REPRINT;
    public static final String HIST_TIME_PRINTED = "HistTimePrinted";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String HIST_REPRINT_KEY = "HistReprint";
    public static final String SCREEN_CLASS = "org.jbundle.base.screen.model.Screen";
    public static final String GRID_SCREEN_CLASS = "org.jbundle.base.screen.model.GridScreen";

    public static final String REQUEST_HISTORY_FILE = "RequestHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.request.db.RequestHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.request.db.RequestHistory";

}
