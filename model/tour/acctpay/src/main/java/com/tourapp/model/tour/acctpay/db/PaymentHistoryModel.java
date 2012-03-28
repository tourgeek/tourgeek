/**
 * @(#)PaymentHistoryModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import com.tourapp.model.tour.genled.db.*;

public interface PaymentHistoryModel extends LinkTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String TRX_DATE = TRX_DATE;
    //public static final String AMOUNT_LOCAL = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String LINKED_TRX_ID = LINKED_TRX_ID;
    //public static final String LINKED_TRX_DESC_ID = LINKED_TRX_DESC_ID;
    public static final String AP_TRX_ID = "ApTrxID";
    public static final String AMOUNT_APPLIED = "AmountApplied";
    public static final String CURR_LOSS_LOCAL = "CurrLossLocal";

    public static final String AP_TRX_ID_KEY = "ApTrxID";
    public static final String PAYMENT = "Payment";
    public static final String PAYMENT_DISTRIBUTION_ICON = "Transaction";
    public static final String PAYMENT_HISTORY = "Payment History";
    public static final String PREPAYMENT_DIST = "PrepaymentDist";
    public static final String PAYMENT_HISTORY_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.hist.PaymentHistoryScreen";
    public static final String PAYMENT_HISTORY_GRID_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.hist.PaymentHistoryGridScreen";
    public static final String PAYMENT_HISTORY_LINK_TRX_GRID_CLASS = "com.tourapp.tour.acctpay.screen.hist.PaymentHistoryLinkTrxGridScreen";
    public static final String PAYMENT_HISTORY_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.hist.PaymentHistoryDistGridScreen";

    public static final String PAYMENT_HISTORY_FILE = "PaymentHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.PaymentHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.PaymentHistory";

}
