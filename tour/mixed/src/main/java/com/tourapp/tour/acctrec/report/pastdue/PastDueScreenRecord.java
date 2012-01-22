/**
 * @(#)PastDueScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.pastdue;

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
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;

/**
 *  PastDueScreenRecord - Screen variables.
 */
public class PastDueScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    //public static final int kReportTotal = kReportTotal;
    public static final int kAsOfDate = kReportScreenRecordLastField + 1;
    public static final int kCheckDep = kAsOfDate + 1;
    public static final int kMcoPer = kCheckDep + 1;
    public static final int kCheckFinal = kMcoPer + 1;
    public static final int kPrintRpt = kCheckFinal + 1;
    public static final int kPrtNotice = kPrintRpt + 1;
    public static final int kMcoAmountPaid = kPrtNotice + 1;
    public static final int kDueDate = kMcoAmountPaid + 1;
    public static final int kAmtDue = kDueDate + 1;
    public static final int kDepOrPymt = kAmtDue + 1;
    public static final int ktemplate = kDepOrPymt + 1;
    public static final int kPastDueScreenRecordLastField = ktemplate;
    public static final int kPastDueScreenRecordFields = ktemplate - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public PastDueScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PastDueScreenRecord(RecordOwner screen)
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

    public static final String kPastDueScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new PastDueScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new PastDueScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new PastDueScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == kReportTotal)
        //  field = new CurrencyField(this, "ReportTotal", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == kAsOfDate)
            field = new PastDueScreenRecord_AsOfDate(this, "AsOfDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCheckDep)
            field = new BooleanField(this, "CheckDep", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kMcoPer)
            field = new PercentField(this, "McoPer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCheckFinal)
            field = new BooleanField(this, "CheckFinal", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kPrintRpt)
            field = new BooleanField(this, "PrintRpt", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kPrtNotice)
            field = new BooleanField(this, "PrtNotice", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoAmountPaid)
            field = new CurrencyField(this, "McoAmountPaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDueDate)
            field = new DateField(this, "DueDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmtDue)
            field = new CurrencyField(this, "AmtDue", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepOrPymt)
            field = new StringField(this, "DepOrPymt", 7, null, null);
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 60, null, "report");
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPastDueScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
