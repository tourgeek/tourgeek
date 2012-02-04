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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.genled.finstmt.*;

/**
 *  GenledScreenRecord - Params for General Ledger Print-out.
 */
public class GenledScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    public static final String START_ACCOUNT_ID = "StartAccountID";
    public static final String END_ACCOUNT_ID = "EndAccountID";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String CUTOFF_DATE = "CutoffDate";
    public static final String PRINT_DETAIL = "PrintDetail";
    public static final String SUB_TOTALS = "SubTotals";
    public static final String PAGE_BREAKS = "PageBreaks";
    public static final String START_ENTRY = "StartEntry";
    public static final String END_ENTRY = "EndEntry";
    public static final String PROFIT_CENTER_ID = "ProfitCenterID";
    public static final String START_SOURCE = "StartSource";
    public static final String END_SOURCE = "EndSource";
    public static final String START_BALANCE = "StartBalance";
    public static final String CHANGE_BALANCE = "ChangeBalance";
    public static final String END_BALANCE = "EndBalance";
    public static final String SUB_TOTAL = "SubTotal";
    public static final String DEBIT = "Debit";
    public static final String CREDIT = "Credit";
    public static final String TEMPLATE = "template";
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

    public static final String GENLED_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new GenledScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new GenledScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new GenledScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        if (iFieldSeq == 5)
            field = new DrCrField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new AccountField(this, START_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new AccountField(this, END_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new DateField(this, CUTOFF_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new BooleanField(this, PRINT_DETAIL, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 13)
            field = new BooleanField(this, SUB_TOTALS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new BooleanField(this, PAGE_BREAKS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new DateField(this, START_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new DateField(this, END_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new ProfitCenterField(this, PROFIT_CENTER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new StringField(this, START_SOURCE, 10, null, null);
        if (iFieldSeq == 19)
            field = new StringField(this, END_SOURCE, 10, null, null);
        if (iFieldSeq == 20)
            field = new DrCrField(this, START_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == 21)
            field = new DrCrField(this, CHANGE_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == 22)
            field = new DrCrField(this, END_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == 23)
            field = new DrCrField(this, SUB_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        if (iFieldSeq == 24)
            field = new StringField(this, DEBIT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 25)
            field = new StringField(this, CREDIT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 26)
            field = new StringField(this, TEMPLATE, 40, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
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
            m_recPeriod = new Period(this.findRecordOwner());
            if (m_recPeriod.getRecordOwner() != null)
                m_recPeriod.getRecordOwner().removeRecord(m_recPeriod);
        }
        m_recPeriod.setPeriodDefaults(this, GenledScreenRecord.START_DATE, GenledScreenRecord.END_DATE, null);
        this.getField(GenledScreenRecord.CUTOFF_DATE).moveFieldToThis(this.getField(GenledScreenRecord.END_DATE));
    }

}
