/**
 * @(#)GenledScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.report;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.genled.finstmt.*;

/**
 *  GenledScreenRecord - Params for General Ledger Print-out.
 */
public class GenledScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    //public static final int kReportTotal = kReportTotal;
    //public static final int kReportCount = kReportCount;
    public static final int kStartAccountID = kReportScreenRecordLastField + 1;
    public static final int kEndAccountID = kStartAccountID + 1;
    public static final int kStartDate = kEndAccountID + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kCutoffDate = kEndDate + 1;
    public static final int kPrintDetail = kCutoffDate + 1;
    public static final int kSubTotals = kPrintDetail + 1;
    public static final int kPageBreaks = kSubTotals + 1;
    public static final int kStartEntry = kPageBreaks + 1;
    public static final int kEndEntry = kStartEntry + 1;
    public static final int kProfitCenterID = kEndEntry + 1;
    public static final int kStartSource = kProfitCenterID + 1;
    public static final int kEndSource = kStartSource + 1;
    public static final int kStartBalance = kEndSource + 1;
    public static final int kChangeBalance = kStartBalance + 1;
    public static final int kEndBalance = kChangeBalance + 1;
    public static final int kSubTotal = kEndBalance + 1;
    public static final int kDebit = kSubTotal + 1;
    public static final int kCredit = kDebit + 1;
    public static final int ktemplate = kCredit + 1;
    public static final int kGenledScreenRecordLastField = ktemplate;
    public static final int kGenledScreenRecordFields = ktemplate - DBConstants.MAIN_FIELD + 1;
    protected Period m_recPeriod = null;
    /**
     * Default constructor.
     */
    public GenledScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public GenledScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recPeriod = null;
        super.init(screen);
    }

    public static final String kGenledScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new GenledScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new GenledScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new GenledScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kReportTotal)
            field = new DrCrField(this, "ReportTotal", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == kReportCount)
        //  field = new IntegerField(this, "ReportCount", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        if (iFieldSeq == kStartAccountID)
            field = new AccountField(this, "StartAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndAccountID)
            field = new AccountField(this, "EndAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCutoffDate)
            field = new DateField(this, "CutoffDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPrintDetail)
            field = new BooleanField(this, "PrintDetail", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kSubTotals)
            field = new BooleanField(this, "SubTotals", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPageBreaks)
            field = new BooleanField(this, "PageBreaks", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartEntry)
            field = new DateField(this, "StartEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndEntry)
            field = new DateField(this, "EndEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProfitCenterID)
            field = new ProfitCenterField(this, "ProfitCenterID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartSource)
            field = new StringField(this, "StartSource", 10, null, null);
        if (iFieldSeq == kEndSource)
            field = new StringField(this, "EndSource", 10, null, null);
        if (iFieldSeq == kStartBalance)
            field = new DrCrField(this, "StartBalance", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == kChangeBalance)
            field = new DrCrField(this, "ChangeBalance", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == kEndBalance)
            field = new DrCrField(this, "EndBalance", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == kSubTotal)
            field = new DrCrField(this, "SubTotal", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == kDebit)
            field = new StringField(this, "Debit", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCredit)
            field = new StringField(this, "Credit", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 40, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kGenledScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recPeriod != null)
            m_recPeriod.free();
        m_recPeriod= null;
        super.free();
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        if (m_recPeriod == null)
        {
            m_recPeriod = new Period(Utility.getRecordOwner(this));
            if (m_recPeriod.getRecordOwner() != null)
                m_recPeriod.getRecordOwner().removeRecord(m_recPeriod);
        }
        m_recPeriod.setPeriodDefaults(this, GenledScreenRecord.kStartDate, GenledScreenRecord.kEndDate, null);
        this.getField(GenledScreenRecord.kCutoffDate).moveFieldToThis(this.getField(GenledScreenRecord.kEndDate));
    }

}
