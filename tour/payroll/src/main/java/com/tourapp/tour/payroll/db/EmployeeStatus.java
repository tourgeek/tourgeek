/**
 *  @(#)EmployeeStatus.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.payroll.screen.emp.*;

/**
 *  EmployeeStatus - Sub-Selection of Employee.
 */
public class EmployeeStatus extends Employee
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
        this.getField(Employee.kID).setSelected(true);
        this.getField(Employee.kNameSort).setSelected(true);
        this.getField(Employee.kName).setSelected(true);
        this.getField(Employee.kHireDate).setSelected(true);
        this.getField(Employee.kDateChanged).setSelected(true);
        this.getField(Employee.kPostalCodeSort).setSelected(true);
        this.getField(Employee.kFirstName).setSelected(true);
        this.getField(Employee.kDeductionID1).setSelected(true);
    }

}
