/**
 * @(#)AcctDetailScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.detail;

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
import org.jbundle.main.screen.*;
import com.tourapp.tour.genled.db.*;

/**
 *  AcctDetailScreenRecord - Screen Field for Transaction Inquiry.
 */
public class AcctDetailScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kAccountID = kScreenRecordLastField + 1;
    public static final int kCalcStart = kAccountID + 1;
    public static final int kStartDate = kCalcStart + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kStartBalance = kEndDate + 1;
    public static final int kEndBalance = kStartBalance + 1;
    public static final int kChangeBalance = kEndBalance + 1;
    public static final int kAcctDetailScreenRecordLastField = kChangeBalance;
    public static final int kAcctDetailScreenRecordFields = kChangeBalance - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public AcctDetailScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctDetailScreenRecord(RecordOwner screen)
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

    public static final String kAcctDetailScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kAccountID)
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCalcStart)
            field = new BooleanField(this, "CalcStart", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartBalance)
            field = new CurrencyField(this, "StartBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndBalance)
            field = new CurrencyField(this, "EndBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChangeBalance)
            field = new CurrencyField(this, "ChangeBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAcctDetailScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
