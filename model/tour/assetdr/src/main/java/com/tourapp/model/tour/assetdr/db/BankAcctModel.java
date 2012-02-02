/**
 * @(#)BankAcctModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface BankAcctModel extends Rec
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = "Description";
    public static final String CURRENCY_ID = "CurrencyID";
    public static final String EFT_ROUTING = "EFTRouting";
    public static final String BANK_ABA = "BankABA";
    public static final String BANK_ACCT_NO = "BankAcctNo";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String NEXT_CHECK = "NextCheck";
    public static final String BALANCE = "Balance";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CURRENCY_ID_KEY = "CurrencyID";
    public static final String BANK_ACCT_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.BankAcctScreen";
    public static final String BANK_ACCT_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.BankAcctGridScreen";

    public static final String BANK_ACCT_FILE = "BankAcct";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.BankAcct";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.BankAcct";

}
