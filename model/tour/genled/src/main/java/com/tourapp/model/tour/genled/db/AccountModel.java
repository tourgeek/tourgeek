/**
 * @(#)AccountModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AccountModel extends Rec
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = "Description";
    public static final String ACCOUNT_NO = "AccountNo";
    public static final String TYPICAL_BALANCE = "TypicalBalance";
    public static final String SECTION_SUB_TOTAL = "SectionSubTotal";
    public static final String COUNTER_ACCOUNT_ID = "CounterAccountID";
    public static final String AUTO_DIST_ID = "AutoDistID";
    public static final String CLOSE_YEAR_END = "CloseYearEnd";
    public static final String DISCONTINUED = "Discontinued";

    public static final String ACCOUNT_NO_KEY = "AccountNo";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String ACCOUNT_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.AccountScreen";
    public static final String ACCOUNT_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.AccountGridScreen";
    public static final String CREDIT = "C";
    public static final String DEBIT = "D";

    public static final String ACCOUNT_FILE = "Account";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.Account";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.Account";

}
