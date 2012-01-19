/**
 * @(#)FinStmtReportScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.finstmt;

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
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.genled.finstmt.screen.*;
import org.jbundle.main.db.*;

/**
 *  FinStmtReportScreenRecord - Financial Statement Params.
 */
public class FinStmtReportScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kFinStmtHeaderID = kReportScreenRecordLastField + 1;
    public static final int kFinStmtID = kFinStmtHeaderID + 1;
    public static final int kBudgetComp = kFinStmtID + 1;
    public static final int kStartDate = kBudgetComp + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kStartEntry = kEndDate + 1;
    public static final int kEndEntry = kStartEntry + 1;
    public static final int kProfitCenterID = kEndEntry + 1;
    public static final int kExcludeClosing = kProfitCenterID + 1;
    public static final int ktemplate = kExcludeClosing + 1;
    public static final int kStartBalance = ktemplate + 1;
    public static final int kBalanceChange = kStartBalance + 1;
    public static final int kEndBalance = kBalanceChange + 1;
    public static final int kTargetAmount = kEndBalance + 1;
    public static final int kRatioAmount = kTargetAmount + 1;
    public static final int kRatioPercent = kRatioAmount + 1;
    public static final int kISAmount = kRatioPercent + 1;
    public static final int kLastStatement = kISAmount + 1;
    public static final int kTotal0 = kLastStatement + 1;
    public static final int kTotal1 = kTotal0 + 1;
    public static final int kTotal2 = kTotal1 + 1;
    public static final int kTotal3 = kTotal2 + 1;
    public static final int kTotal4 = kTotal3 + 1;
    public static final int kTotal5 = kTotal4 + 1;
    public static final int kTotal6 = kTotal5 + 1;
    public static final int kTotal7 = kTotal6 + 1;
    public static final int kTotal8 = kTotal7 + 1;
    public static final int kTotal9 = kTotal8 + 1;
    public static final int kFinStmtReportScreenRecordLastField = kTotal9;
    public static final int kFinStmtReportScreenRecordFields = kTotal9 - DBConstants.MAIN_FIELD + 1;
    protected Period m_recPeriod = null;
    /**
     * Default constructor.
     */
    public FinStmtReportScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public FinStmtReportScreenRecord(RecordOwner screen)
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

    public static final String kFinStmtReportScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kFinStmtHeaderID)
            field = new FinStmtHeaderField(this, "FinStmtHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinStmtID)
            field = new FinStmtField(this, "FinStmtID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBudgetComp)
            field = new BudgetCompField(this, "BudgetComp", 1, null, null);
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartEntry)
            field = new DateField(this, "StartEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndEntry)
            field = new DateField(this, "EndEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProfitCenterID)
            field = new ProfitCenterField(this, "ProfitCenterID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kExcludeClosing)
            field = new BooleanField(this, "ExcludeClosing", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 40, null, "finstmt");
        if (iFieldSeq == kStartBalance)
            field = new CurrencyField(this, "StartBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalanceChange)
            field = new CurrencyField(this, "BalanceChange", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndBalance)
            field = new CurrencyField(this, "EndBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTargetAmount)
            field = new CurrencyField(this, "TargetAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRatioAmount)
            field = new CurrencyField(this, "RatioAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRatioPercent)
            field = new FloatField(this, "RatioPercent", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kISAmount)
            field = new CurrencyField(this, "ISAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLastStatement)
            field = new ReferenceField(this, "LastStatement", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal0)
            field = new CurrencyField(this, "Total0", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal1)
            field = new CurrencyField(this, "Total1", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal2)
            field = new CurrencyField(this, "Total2", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal3)
            field = new CurrencyField(this, "Total3", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal4)
            field = new CurrencyField(this, "Total4", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal5)
            field = new CurrencyField(this, "Total5", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal6)
            field = new CurrencyField(this, "Total6", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal7)
            field = new CurrencyField(this, "Total7", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal8)
            field = new CurrencyField(this, "Total8", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal9)
            field = new CurrencyField(this, "Total9", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kFinStmtReportScreenRecordLastField)
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
        m_recPeriod = null;
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
        m_recPeriod.setPeriodDefaults(this, FinStmtReportScreenRecord.kStartDate, FinStmtReportScreenRecord.kEndDate, null);
    }

}
