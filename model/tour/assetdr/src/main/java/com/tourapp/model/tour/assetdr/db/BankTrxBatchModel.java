/**
 * @(#)BankTrxBatchModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface BankTrxBatchModel extends Rec
{
    public static final String BANK_TRX_BATCH_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchScreen";
    public static final String BANK_TRX_BATCH_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchGridScreen";

    public static final String BANK_TRX_BATCH_FILE = "BankTrxBatch";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.BankTrxBatch";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.BankTrxBatch";

}
