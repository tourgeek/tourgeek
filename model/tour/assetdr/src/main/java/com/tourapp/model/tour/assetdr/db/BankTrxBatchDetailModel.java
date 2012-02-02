/**
 * @(#)BankTrxBatchDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import com.tourapp.model.tour.assetdr.db.*;

public interface BankTrxBatchDetailModel extends BankTrxModel
{

    //public static final String ID = ID;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_DATE = TRX_DATE;
    public static final String USER_ID = TRX_USER_ID;
    public static final String BANK_TRX_BATCH_ID = "BankTrxBatchID";
    public static final String DISTRIBUTION_DISPLAY = "DistributionDisplay";

    public static final String TRX_STATUS_KEY = "TrxStatus";

    public static final String BANK_TRX_BATCH_ID_KEY = "BankTrxBatchID";
    public static final String BANK_TRX_BATCH_DETAIL_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchDetailScreen";
    public static final String BANK_TRX_BATCH_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchDetailGridScreen";

    public static final String BANK_TRX_BATCH_DETAIL_FILE = "BankTrxBatchDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.BankTrxBatchDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.BankTrxBatchDetail";

}
