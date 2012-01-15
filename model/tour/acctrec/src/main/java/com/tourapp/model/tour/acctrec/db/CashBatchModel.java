/**
 * @(#)CashBatchModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.assetdr.db.*;

public interface CashBatchModel extends BankTrxBatchModel
{
    public static final String CASH_BATCH_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchScreen";
    public static final String CASH_BATCH_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchGridScreen";

    public static final String CASH_BATCH_FILE = "CashBatch";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.CashBatch";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.CashBatch";

}
