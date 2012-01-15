/**
 * @(#)CashBatchDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import org.jbundle.model.db.*;

public interface CashBatchDetailModel extends Rec
{
    public static final String CASH_BATCH_DETAIL_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchDetailScreen";
    public static final String CASH_BATCH_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.cash.CashBatchDetailGridScreen";

    public static final String CASH_BATCH_DETAIL_FILE = "CashBatchDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.CashBatchDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.CashBatchDetail";

}
