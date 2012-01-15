/**
 * @(#)BankTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import com.tourapp.model.tour.genled.db.*;

public interface BankTrxModel extends BaseTrxModel
{
    public static final String PAYMENT_DISTRIBUTION = "Payment Distribution";
    public static final String BANK_TRX_BATCH_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchScreen";
    public static final String BANK_TRX_BATCH_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchGridScreen";

    public static final String BANK_TRX_FILE = "BankTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.BankTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.BankTrx";

}
