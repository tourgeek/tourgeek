/**
 * @(#)CashBatchModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.assetdr.db.*;

public interface CashBatchModel extends BankTrxBatchModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String USER_ID = USER_ID;
    //public static final String BANK_ACCT_ID = BANK_ACCT_ID;
    public static final String DETAIL_DATE = "DetailDate";
    public static final String BATCH_CHECKS = "BatchChecks";
    public static final String BATCH_CHECKS_ACTUAL = "BatchChecksActual";
    public static final String BATCH_TOTAL = "BatchTotal";
    public static final String BATCH_TOTAL_ACTUAL = "BatchTotalActual";
    public static final String CASH_BATCH_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchScreen";
    public static final String CASH_BATCH_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchGridScreen";

    public static final String CASH_BATCH_FILE = "CashBatch";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.CashBatch";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.CashBatch";

}
