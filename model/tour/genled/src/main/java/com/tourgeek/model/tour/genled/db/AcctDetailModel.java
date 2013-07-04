
package com.tourgeek.model.tour.genled.db;

import com.tourgeek.model.tour.genled.db.*;

public interface AcctDetailModel extends BaseTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String TRX_TYPE_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String TRX_DATE = TRX_DATE;
    //public static final String AMOUNT_LOCAL = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    public static final String ACCOUNT_ID = "AccountID";
    public static final String SOURCE = "Source";
    public static final String COMMENTS = "Comments";
    public static final String VERSION_ID = "VersionID";

    public static final String ACCOUNT_ID_KEY = "AccountID";

    public static final String TRX_DATE_KEY = "TrxDate";

    public static final String SOURCE_KEY = "Source";
    public static final String ACCT_DETAIL_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.detail.AcctDetailGridScreen";
    public static final String ACCT_DETAIL_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.detail.AcctDetailScreen";
    public static final String ACCT_DETAIL_DIST_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.detail.AcctDetailDistGridScreen";
    public static final String MANUALENTRY = "ManualEntry";
    public static final String RECURRINGTRX = "RecurringTrx";
    public static final String ACCRUAL = "Accrual";
    public static final String ACCRUALREVERSAL = "AccrualReversal";
    public static final String CLOSINGENTRY = "ClosingEntry";
    public static final String STARTBALANCE = "StartBalance";
    public static final String SUMMARYTRX = "SummaryTrx";

    public static final String ACCT_DETAIL_FILE = "AcctDetail";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.AcctDetail";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.AcctDetail";

}
