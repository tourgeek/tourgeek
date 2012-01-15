/**
 * @(#)PaymentHistoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import com.tourapp.model.tour.genled.db.*;

public interface PaymentHistoryModel extends LinkTrxModel
{
    public static final String PAYMENT = "Payment";
    public static final String PAYMENT_DISTRIBUTION_ICON = "Transaction";
    public static final String PAYMENT_HISTORY = "Payment History";
    public static final String PREPAYMENT_DIST = "PrepaymentDist";

    public static final String PAYMENT_HISTORY_FILE = "PaymentHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.PaymentHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.PaymentHistory";

}
