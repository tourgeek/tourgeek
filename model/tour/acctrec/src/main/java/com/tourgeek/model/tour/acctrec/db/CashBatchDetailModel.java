/**
  * @(#)CashBatchDetailModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.acctrec.db;

import org.jbundle.model.db.*;

public interface CashBatchDetailModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String CASH_BATCH_ID = "CashBatchID";
    public static final String BOOKING_ID = "BookingID";
    public static final String CHECK_NO = "CheckNo";
    public static final String CHECK_ABA = "CheckABA";
    public static final String AMOUNT = "Amount";
    public static final String COMMENTS = "Comments";

    public static final String CASH_BATCH_ID_KEY = "CashBatchID";
    public static final String CASH_BATCH_DETAIL_SCREEN_CLASS = "com.tourgeek.tour.acctrec.screen.cash.CashBatchDetailScreen";
    public static final String CASH_BATCH_DETAIL_GRID_SCREEN_CLASS = "com.tourgeek.tour.acctrec.screen.cash.CashBatchDetailGridScreen";

    public static final String CASH_BATCH_DETAIL_FILE = "CashBatchDetail";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.acctrec.db.CashBatchDetail";
    public static final String THICK_CLASS = "com.tourgeek.tour.acctrec.db.CashBatchDetail";

}
