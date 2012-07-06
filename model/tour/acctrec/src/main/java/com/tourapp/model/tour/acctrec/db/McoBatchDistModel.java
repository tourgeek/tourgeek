/**
  * @(#)McoBatchDistModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.acctrec.db.*;

public interface McoBatchDistModel extends CashBatchDistModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String CASH_BATCH_DETAIL_ID = CASH_BATCH_DETAIL_ID;
    //public static final String ACCOUNT_ID = ACCOUNT_ID;
    //public static final String AMOUNT = AMOUNT;
    //public static final String BOOKING_ID = BOOKING_ID;
    public static final String MCO_BATCH_DIST_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoBatchDistScreen";
    public static final String MCO_BATCH_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoBatchDistGridScreen";

    public static final String MCO_BATCH_DIST_FILE = "McoBatchDist";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.McoBatchDist";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.McoBatchDist";

}
