/**
 * @(#)PaymentRequestModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface PaymentRequestModel extends Rec
{

    //public static final String ID = ID;
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String VENDOR_ID = "VendorID";
    public static final String AMOUNT = "Amount";
    public static final String TRX_STATUS_ID = "TrxStatusID";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String CHECK_NO = "CheckNo";
    public static final String COMMENTS = "Comments";

    public static final String BANK_ACCT_ID_KEY = "BankAcctID";
    public static final String PAYMENT_REQUEST_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.pymtreq.PaymentRequestScreen";
    public static final String PAYMENT_REQUEST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.pymtreq.PaymentRequestGridScreen";

    public static final String PAYMENT_REQUEST_FILE = "PaymentRequest";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.PaymentRequest";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.PaymentRequest";

}
