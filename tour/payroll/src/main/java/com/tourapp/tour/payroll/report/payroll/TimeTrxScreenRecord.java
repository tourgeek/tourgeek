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
    public static final int kPayEndingDate = kScreenRecordLastField + 1;
    public static final String EMP_TO_PAY = "EmpToPay";
    public static final int kEmpToPay = kPayEndingDate + 1;
    public static final int kTimeTrxScreenRecordLastField = kEmpToPay;
    public static final int kTimeTrxScreenRecordFields = kEmpToPay - DBConstants.MAIN_FIELD + 1;
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

    public static final String kTimeTrxScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kPayEndingDate)
            field = new TimeTrxScreenRecord_PayEndingDate(this, "PayEndingDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEmpToPay)
            field = new StringField(this, "EmpToPay", 6, null, "S");
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTimeTrxScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
