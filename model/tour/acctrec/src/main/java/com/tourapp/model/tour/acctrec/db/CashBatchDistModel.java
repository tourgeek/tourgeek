/**
 * @(#)CashBatchDistModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.assetdr.db.*;

public interface CashBatchDistModel extends BankTrxBatchDistModel
{

    public static final String CASH_BATCH_DIST_FILE = "CashBatchDist";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.CashBatchDist";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.CashBatchDist";

}
