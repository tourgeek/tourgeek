/**
 * @(#)TimeTrxScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.report.payroll;

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

/**
 *  TimeTrxScreenRecord - Screen Query.
 */
public class TimeTrxScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PAY_ENDING_DATE = "PayEndingDate";
    public static final String EMP_TO_PAY = "EmpToPay";
    /**
     * Default constructor.
     */
    public TimeTrxScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TimeTrxScreenRecord(RecordOwner screen)
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

    public static final String TIME_TRX_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new TimeTrxScreenRecord_PayEndingDate(this, PAY_ENDING_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new StringField(this, EMP_TO_PAY, 6, null, "S");
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
