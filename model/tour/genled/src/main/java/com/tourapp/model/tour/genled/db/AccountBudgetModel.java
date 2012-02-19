/**
 * @(#)AccountBudgetModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AccountBudgetModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String ACCOUNT_ID = "AccountID";
    public static final String BUD_COM_CODE = "BudComCode";
    public static final String DETAIL_DATE = "DetailDate";
    public static final String AMOUNT = "Amount";

    public static final String BUD_COM_CODE_KEY = "BudComCode";
    public static final String ACCOUNT_BUDGET_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.AccountBudgetScreen";
    public static final String ACCOUNT_BUDGET_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.AccountBudgetGridScreen";

    public static final String ACCOUNT_BUDGET_FILE = "AccountBudget";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AccountBudget";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AccountBudget";

}
