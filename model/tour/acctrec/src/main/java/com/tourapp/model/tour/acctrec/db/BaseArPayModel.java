/**
 * @(#)BaseArPayModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.genled.db.*;

public interface BaseArPayModel extends BaseTrxModel
{

    //public static final String ID = ID;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_DATE = TRX_DATE;
    public static final String AMT_APPLY = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    public static final String BOOKING_ID = "BookingID";
    public static final String GROSS = "Gross";
    public static final String SVC_PER = "SvcPer";
    public static final String SVC_AMT = "SvcAmt";
    public static final String NET = "Net";
    public static final String COMMENTS = "Comments";
    public static final String DATE_SUBMITTED = "DateSubmitted";
    public static final String DATE_PAID = "DatePaid";
    public static final String AMOUNT_PAID = "AmountPaid";
    public static final String PAID = "Paid";
    public static final String PAYMENT_ENTERED = "PaymentEntered";
    public static final String BATCH = "Batch";
    public static final String ENTERED = "Entered";
    public static final String SUBMITTED = "Submitted";
    public static final String ITEM_PAID = "Paid";

    public static final String BASE_AR_PAY_FILE = "BaseArPay";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.BaseArPay";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.BaseArPay";

}
