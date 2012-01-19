/**
 * @(#)FMEmployee.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.payroll.db.*;

/**
 *  FMEmployee - Screen Fields.
 */
public class FMEmployee extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kEmpScreenNo = kScreenRecordLastField + 1;
    public static final int kFMEmployeeLastField = kEmpScreenNo;
    public static final int kFMEmployeeFields = kEmpScreenNo - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public FMEmployee()
    {
        super();
    }
    /**
     * Constructor.
     */
    public FMEmployee(RecordOwner screen)
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

    public static final String kFMEmployeeFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kEmpScreenNo)
            field = new ShortField(this, "EmpScreenNo", 2, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kFMEmployeeLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
