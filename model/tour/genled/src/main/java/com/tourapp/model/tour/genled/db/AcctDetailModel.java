/**
 * @(#)AcctDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import com.tourapp.model.tour.genled.db.*;

public interface AcctDetailModel extends BaseTrxModel
{
    public static final String MANUALENTRY = "ManualEntry";
    public static final String RECURRINGTRX = "RecurringTrx";
    public static final String ACCRUAL = "Accrual";
    public static final String ACCRUALREVERSAL = "AccrualReversal";
    public static final String CLOSINGENTRY = "ClosingEntry";
    public static final String STARTBALANCE = "StartBalance";
    public static final String SUMMARYTRX = "SummaryTrx";

    public static final String ACCT_DETAIL_FILE = "AcctDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AcctDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AcctDetail";

}
