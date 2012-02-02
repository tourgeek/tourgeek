/**
 * @(#)AssetDrControlModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface AssetDrControlModel extends Rec
{

    //public static final String ID = ID;
    public static final String CURRENCY_ID = "CurrencyID";
    public static final String LANGUAGE_ID = "LanguageID";
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String TRX_STATUS_ID = "TrxStatusID";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String DATE_RECONCILED = "DateReconciled";
    public static final String ASSET_DR_CONTROL_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.AssetDrControlScreen";
    public static final String ASSET_DR_CONTROL_SCREEN_2_CLASS = "com.tourapp.tour.assetdr.screen.AssetDrControlScreen";

    public static final String ASSET_DR_CONTROL_FILE = "AssetDrControl";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.AssetDrControl";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.AssetDrControl";

}
