/**
  * @(#)ApControlModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface ApControlModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String AUTO_AP_CODE = "AutoApCode";
    public static final String AP_BANK_ACCT_ID = "ApBankAcctID";
    public static final String AP_ACCOUNT_ID = "ApAccountID";
    public static final String BROKER_VENDOR_ID = "BrokerVendorID";
    public static final String COUNTRY_ID = "CountryID";
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String MESSAGE_TRANSPORT_ID = "MessageTransportID";
    public static final String VENDOR_STATUS_ID = "VendorStatusID";
    public static final String PAYMENT_CYCLE_ID = "PaymentCycleID";
    public static final String PAYMENT_CODE_ID = "PaymentCodeID";
    public static final String PREPAY_TYPE_ID = "PrepayTypeID";
    public static final String NON_TOUR_AP_ACCOUNT_ID = "NonTourApAccountID";
    public static final String COST_ACCOUNT_ID = "CostAccountID";
    public static final String CURR_OU_ACCOUNT_ID = "CurrOUAccountID";
    public static final String PREPAY_ACCOUNT_ID = "PrepayAccountID";
    public static final String NON_TOUR_PREPAY_ACCOUNT_ID = "NonTourPrepayAccountID";
    public static final String AIR_ACCOUNT_ID = "AirAccountID";
    public static final String PP_TIC_ACCOUNT_ID = "PPTicAccountID";
    public static final String ARC_SUMMARY_ACCOUNT_ID = "ArcSummaryAccountID";
    public static final String OVERRIDE_REC_ACCOUNT_ID = "OverrideRecAccountID";
    public static final String OVERRIDE_SUMM_ACCOUNT_ID = "OverrideSummAccountID";
    public static final String OVERRIDE_GAIN_LOSS_ACCOUNT_ID = "OverrideGainLossAccountID";
    public static final String TEN_99_TEMPLATE = "Ten99Template";

    public static final String AP_CONTROL_FILE = "ApControl";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.acctpay.db.ApControl";
    public static final String THICK_CLASS = "com.tourgeek.tour.acctpay.db.ApControl";

}
