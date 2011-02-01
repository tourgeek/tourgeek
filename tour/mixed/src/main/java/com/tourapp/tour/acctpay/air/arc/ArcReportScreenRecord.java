/**
 *  @(#)ArcReportScreenRecord.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.air.arc;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import org.jbundle.main.db.*;

/**
 *  ArcReportScreenRecord - .
 */
public class ArcReportScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    //public static final int kReportTotal = kReportTotal;
    public static final int kLastArcDate = kReportScreenRecordLastField + 1;
    public static final int kSummaryAccountID = kLastArcDate + 1;
    public static final int kArcReportScreenRecordLastField = kSummaryAccountID;
    public static final int kArcReportScreenRecordFields = kSummaryAccountID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public ArcReportScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ArcReportScreenRecord(RecordOwner screen)
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

    public static final String kArcReportScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new ArcReportScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new ArcReportScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new ArcReportScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == kReportTotal)
        //  field = new CurrencyField(this, "ReportTotal", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == kLastArcDate)
            field = new DateField(this, "LastArcDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSummaryAccountID)
            field = new AccountField(this, "SummaryAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kArcReportScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
