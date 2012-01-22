/**
 * @(#)CheckScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.db;

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

/**
 *  CheckScreenRecord - Special fields required for check printing.
 */
public class CheckScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kCheckDate = kReportScreenRecordLastField + 1;
    public static final int kCheckNo = kCheckDate + 1;
    public static final int kPayee = kCheckNo + 1;
    public static final int kCheckAmount = kPayee + 1;
    public static final int kCheckAmountText = kCheckAmount + 1;
    public static final int kCheckScreenRecordLastField = kCheckAmountText;
    public static final int kCheckScreenRecordFields = kCheckAmountText - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CheckScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CheckScreenRecord(RecordOwner screen)
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

    public static final String kCheckScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kCheckDate)
            field = new CheckScreenRecord_CheckDate(this, "CheckDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCheckNo)
            field = new IntegerField(this, "CheckNo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPayee)
            field = new StringField(this, "Payee", 60, null, null);
        if (iFieldSeq == kCheckAmount)
            field = new CurrencyField(this, "CheckAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCheckAmountText)
            field = new AmountTextField(this, "CheckAmountText", 128, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCheckScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        this.getField(CheckScreenRecord.kCheckAmount).addListener(new CopyFieldHandler(CheckScreenRecord.kCheckAmountText));
        super.addListeners();
    }

}
