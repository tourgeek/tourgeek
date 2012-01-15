/**
 * @(#)AccountModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AccountModel extends Rec
{
    public static final String ACCOUNT_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.AccountScreen";
    public static final String ACCOUNT_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.AccountGridScreen";
    public static final String CREDIT = "C";
    public static final String DEBIT = "D";

    public static final String ACCOUNT_FILE = "Account";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.Account";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.Account";

}
