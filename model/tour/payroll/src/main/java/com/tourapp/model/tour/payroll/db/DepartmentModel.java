/**
 * @(#)DepartmentModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface DepartmentModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String ACCOUNT_ID = "AccountID";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String DEPARTMENT_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.misc.DepartmentScreen";
    public static final String _CLASS = "com.tourapp.tour.";

    public static final String DEPARTMENT_FILE = "Department";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.payroll.db.Department";
    public static final String THICK_CLASS = "com.tourapp.tour.payroll.db.Department";

}
