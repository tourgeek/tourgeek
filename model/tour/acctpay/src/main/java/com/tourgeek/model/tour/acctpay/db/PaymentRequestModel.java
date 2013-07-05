/**
  * @(#)PaymentRequestModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface PaymentRequestModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String VENDOR_ID = "VendorID";
    public static final String AMOUNT = "Amount";
    public static final String TRX_STATUS_ID = "TrxStatusID";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String CHECK_NO = "CheckNo";
    public static final String COMMENTS = "Comments";

    public static final String BANK_ACCT_ID_KEY = "BankAcctID";
    public static final String PAYMENT_REQUEST_SCREEN_CLASS = "com.tourgeek.tour.acctpay.screen.pymtreq.PaymentRequestScreen";
    public static final String PAYMENT_REQUEST_GRID_SCREEN_CLASS = "com.tourgeek.tour.acctpay.screen.pymtreq.PaymentRequestGridScreen";
    public static final String PRINT_CHECK_JOURNAL_CLASS = "com.tourgeek.tour.acctpay.screen.check.PrintCheckJournal";
    public static final String PRINT_CHECK_POST_CLASS = "com.tourgeek.tour.acctpay.screen.check.PrintCheckPost";

    public static final String PAYMENT_REQUEST_FILE = "PaymentRequest";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.acctpay.db.PaymentRequest";
    public static final String THICK_CLASS = "com.tourgeek.tour.acctpay.db.PaymentRequest";

}
