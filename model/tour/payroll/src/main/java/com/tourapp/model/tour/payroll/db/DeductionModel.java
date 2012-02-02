/**
 * @(#)DeductionModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface DeductionModel extends Rec
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = "Description";
    public static final String TYPE = "Type";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String ENTER_HOURS = "EnterHours";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String DEDUCTION_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.misc.DeductionScreen";
    public static final String _CLASS = "com.tourapp.tour.";

    public static final String DEDUCTION_FILE = "Deduction";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.payroll.db.Deduction";
    public static final String THICK_CLASS = "com.tourapp.tour.payroll.db.Deduction";

}
