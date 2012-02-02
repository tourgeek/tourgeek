/**
 * @(#)EmployeeStatus.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.db;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.payroll.screen.emp.*;
import com.tourapp.model.tour.payroll.db.*;

/**
 *  EmployeeStatus - Sub-Selection of Employee.
 */
public class EmployeeStatus extends Employee
     implements EmployeeStatusModel
{
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public EmployeeStatus()
    {
        super();
    }
    /**
     * Constructor.
     */
    public EmployeeStatus(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kEmployeeStatusFile = null;  // Screen field
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Employee.ID).setSelected(true);
        this.getField(Employee.NAME_SORT).setSelected(true);
        this.getField(Employee.NAME).setSelected(true);
        this.getField(Employee.HIRE_DATE).setSelected(true);
        this.getField(Employee.DATE_CHANGED).setSelected(true);
        this.getField(Employee.POSTAL_CODE_SORT).setSelected(true);
        this.getField(Employee.FIRST_NAME).setSelected(true);
        this.getField(Employee.DEDUCTION_ID1).setSelected(true);
    }

}
