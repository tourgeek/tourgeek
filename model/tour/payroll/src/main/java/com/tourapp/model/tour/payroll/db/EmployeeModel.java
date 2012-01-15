/**
 * @(#)EmployeeModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.payroll.db;

import org.jbundle.model.main.db.*;

public interface EmployeeModel extends PersonModel
{
    public static final String EMPLOYEE_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.emp.EmployeeScreen";
    public static final String EMPLOYEE_GRID_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.emp.EmployeeGridScreen";

    public static final String EMPLOYEE_FILE = "Employee";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.payroll.db.Employee";
    public static final String THICK_CLASS = "com.tourapp.tour.payroll.db.Employee";

}
