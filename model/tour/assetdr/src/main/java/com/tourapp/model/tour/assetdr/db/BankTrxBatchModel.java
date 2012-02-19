/**
 * @(#)BankTrxBatchModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface BankTrxBatchModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String USER_ID = "UserID";
    public static final String BANK_ACCT_ID = "BankAcctID";

    public static final String USER_ID_KEY = "UserID";
    public static final String BANK_TRX_BATCH_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchScreen";
    public static final String BANK_TRX_BATCH_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchGridScreen";

    public static final String BANK_TRX_BATCH_FILE = "BankTrxBatch";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.BankTrxBatch";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.BankTrxBatch";

}
