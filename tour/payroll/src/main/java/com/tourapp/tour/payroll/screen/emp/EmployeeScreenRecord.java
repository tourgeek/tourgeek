/**
 *  @(#)EmployeeScreenRecord.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.payroll.db.*;

/**
 *  EmployeeScreenRecord - Screen Fields.
 */
public class EmployeeScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kEmpKeyNo = kScreenRecordLastField + 1;
    public static final int kActiveEmp = kEmpKeyNo + 1;
    public static final int kEmployeeScreenRecordLastField = kActiveEmp;
    public static final int kEmployeeScreenRecordFields = kActiveEmp - DBConstants.MAIN_FIELD + 1;
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

    public static final String kEmployeeScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kEmpKeyNo)
            field = new ShortField(this, "EmpKeyNo", 2, null, null);
        if (iFieldSeq == kActiveEmp)
            field = new BooleanField(this, "ActiveEmp", 1, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kEmployeeScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
