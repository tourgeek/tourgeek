/**
  * @(#)FMEmployee.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.payroll.screen.emp;

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
import com.tourgeek.tour.payroll.db.*;

/**
 *  FMEmployee - Screen Fields.
 */
public class FMEmployee extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String EMP_SCREEN_NO = "EmpScreenNo";
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

    public static final String FM_EMPLOYEE_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new ShortField(this, EMP_SCREEN_NO, 2, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
