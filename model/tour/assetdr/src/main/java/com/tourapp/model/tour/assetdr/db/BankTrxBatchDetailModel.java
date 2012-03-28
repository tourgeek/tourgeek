/**
 * @(#)BankTrxBatchDetailModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import com.tourapp.model.tour.assetdr.db.*;

public interface BankTrxBatchDetailModel extends BankTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    public static final String USER_ID = TRX_USER_ID;
    //public static final String TRX_DATE = TRX_DATE;
    //public static final String AMOUNT_LOCAL = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String BANK_ACCT_ID = BANK_ACCT_ID;
    //public static final String TRX_NUMBER = TRX_NUMBER;
    //public static final String EFT_TRX_NO = EFT_TRX_NO;
    //public static final String PAYEE_TRX_DESC_ID = PAYEE_TRX_DESC_ID;
    //public static final String PAYEE_ID = PAYEE_ID;
    //public static final String PAYEE_NAME = PAYEE_NAME;
    //public static final String AMOUNT = AMOUNT;
    //public static final String EXCHANGE = EXCHANGE;
    //public static final String INV_SIGN = INV_SIGN;
    //public static final String INV_BALANCE = INV_BALANCE;
    //public static final String INV_BALANCE_LOCAL = INV_BALANCE_LOCAL;
    //public static final String COMMENTS = COMMENTS;
    //public static final String DATE_RECONCILED = DATE_RECONCILED;
    //public static final String MANUAL = MANUAL;
    public static final String BANK_TRX_BATCH_ID = "BankTrxBatchID";
    public static final String DISTRIBUTION_DISPLAY = "DistributionDisplay";

    public static final String TRX_STATUS_KEY = "TrxStatus";

    public static final String BANK_TRX_BATCH_ID_KEY = "BankTrxBatchID";
    public static final String BANK_TRX_BATCH_DETAIL_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchDetailScreen";
    public static final String BANK_TRX_BATCH_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchDetailGridScreen";
    public static final String BANK_TRX_BATCH_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.batch.BankTrxBatchDistGridScreen";

    public static final String BANK_TRX_BATCH_DETAIL_FILE = "BankTrxBatchDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.BankTrxBatchDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.BankTrxBatchDetail";

}
