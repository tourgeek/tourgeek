/**
 * @(#)ArControlModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import org.jbundle.model.db.*;

public interface ArControlModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String AR_BANK_ACCT_ID = "ArBankAcctID";
    public static final String AR_ACCOUNT_ID = "ArAccountID";
    public static final String MCO_REC_ACCOUNT_ID = "McoRecAccountID";
    public static final String MCO_VAR_ACCOUNT_ID = "McoVarAccountID";
    public static final String MCO_SUSPENSE_ACCOUNT_ID = "McoSuspenseAccountID";
    public static final String MCO_COMM_PER = "McoCommPer";
    public static final String MCO_SVC_PER = "McoSvcPer";
    public static final String MCO_TAX_PER = "McoTaxPer";
    public static final String NON_TOUR_ACCOUNT_ID = "NonTourAccountID";
    public static final String REFUND_BANK_ACCT_ID = "RefundBankAcctID";
    public static final String REFUND_SUSPENSE_ACCOUNT_ID = "RefundSuspenseAccountID";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String CARD_ID = "CardID";
    public static final String CREDIT_CARD_REC_ACCOUNT_ID = "CreditCardRecAccountID";
    public static final String CREDIT_CARD_SUSPENSE_ACCOUNT_ID = "CreditCardSuspenseAccountID";
    public static final String CREDIT_CARD_VAR_ACCOUNT_ID = "CreditCardVarAccountID";
    public static final String CREDIT_CARD_SVC_PER = "CreditCardSvcPer";
    public static final String CREDIT_DEBIT_ACCOUNT_ID = "CreditDebitAccountID";
    public static final String AR_CONTROL_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.misc.ArControlScreen";
    public static final String AR_CONTROL_SCREEN_2_CLASS = "com.tourapp.tour.acctrec.screen.misc.ArControlScreen";

    public static final String AR_CONTROL_FILE = "ArControl";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.ArControl";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.ArControl";

}
