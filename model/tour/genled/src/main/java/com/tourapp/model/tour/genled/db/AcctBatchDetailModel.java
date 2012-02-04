/**
 * @(#)AcctBatchDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AcctBatchDetailModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String ACCT_BATCH_ID = "AcctBatchID";
    public static final String SEQUENCE = "Sequence";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String AMOUNT = "Amount";
    public static final String COUNTER_BALANCE = "CounterBalance";
    public static final String AUTO_DIST = "AutoDist";
    public static final String AUTO_ACCRUAL = "AutoAccrual";
    public static final String AUTO_REVERSAL = "AutoReversal";

    public static final String ACCT_BATCH_ID_KEY = "AcctBatchID";
    public static final String ACCT_BATCH_DETAIL_SCREEN_CLASS = "com.tourapp.tour.genled.screen.batch.AcctBatchDetailScreen";
    public static final String ACCT_BATCH_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.batch.AcctBatchDetailGridScreen";
    public static final String REVERSAL = "Reversal";

    public static final String ACCT_BATCH_DETAIL_FILE = "AcctBatchDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AcctBatchDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AcctBatchDetail";

}
