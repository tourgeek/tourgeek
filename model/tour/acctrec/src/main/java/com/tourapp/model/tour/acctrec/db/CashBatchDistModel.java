/**
  * @(#)CashBatchDistModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.assetdr.db.*;

public interface CashBatchDistModel extends BankTrxBatchDistModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String CASH_BATCH_DETAIL_ID = BANK_TRX_BATCH_DETAIL_ID;
    //public static final String ACCOUNT_ID = ACCOUNT_ID;
    //public static final String AMOUNT = AMOUNT;
    public static final String BOOKING_ID = "BookingID";

    public static final String CASH_BATCH_DETAIL_ID_KEY = "CashBatchDetailID";
    public static final String CASH_BATCH_DIST_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchDistScreen";
    public static final String CASH_BATCH_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchDistGridScreen";

    public static final String CASH_BATCH_DIST_FILE = "CashBatchDist";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.CashBatchDist";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.CashBatchDist";

}
