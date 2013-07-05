/**
  * @(#)EmployeeLookupModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface EmployeeLookupModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;

    public static final String EMPLOYEE_LOOKUP_FILE = "EmployeeLookup";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.payroll.db.EmployeeLookup";
    public static final String THICK_CLASS = "com.tourgeek.tour.payroll.db.EmployeeLookup";

}
