/**
 * @(#)EmployeeScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.screen.emp;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.*;
import com.tourapp.tour.payroll.db.*;

/**
 *  EmployeeScreenRecord - Screen Fields.
 */
public class EmployeeScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String EMP_KEY_NO = "EmpKeyNo";
    public static final String ACTIVE_EMP = "ActiveEmp";
    /**
     * Default constructor.
     */
    public EmployeeScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public EmployeeScreenRecord(RecordOwner screen)
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

    public static final String EMPLOYEE_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new ShortField(this, EMP_KEY_NO, 2, null, null);
        if (iFieldSeq == 1)
            field = new BooleanField(this, ACTIVE_EMP, 1, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
