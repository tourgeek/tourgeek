/**
  * @(#)FinStmtReportScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.finstmt;

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
import org.bson.*;
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.genled.db.finstmt.*;
import com.tourgeek.tour.genled.finstmt.screen.*;
import org.jbundle.main.db.*;

/**
 *  FinStmtReportScreenRecord - Financial Statement Params.
 */
public class FinStmtReportScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    public static final String FIN_STMT_HEADER_ID = "FinStmtHeaderID";
    public static final String FIN_STMT_ID = "FinStmtID";
    public static final String BUDGET_COMP = "BudgetComp";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String START_ENTRY = "StartEntry";
    public static final String END_ENTRY = "EndEntry";
    public static final String PROFIT_CENTER_ID = "ProfitCenterID";
    public static final String EXCLUDE_CLOSING = "ExcludeClosing";
    public static final String TEMPLATE = "template";
    public static final String START_BALANCE = "StartBalance";
    public static final String BALANCE_CHANGE = "BalanceChange";
    public static final String END_BALANCE = "EndBalance";
    public static final String TARGET_AMOUNT = "TargetAmount";
    public static final String RATIO_AMOUNT = "RatioAmount";
    public static final String RATIO_PERCENT = "RatioPercent";
    public static final String IS_AMOUNT = "ISAmount";
    public static final String LAST_STATEMENT = "LastStatement";
    public static final String TOTAL_0 = "Total0";
    public static final String TOTAL_1 = "Total1";
    public static final String TOTAL_2 = "Total2";
    public static final String TOTAL_3 = "Total3";
    public static final String TOTAL_4 = "Total4";
    public static final String TOTAL_5 = "Total5";
    public static final String TOTAL_6 = "Total6";
    public static final String TOTAL_7 = "Total7";
    public static final String TOTAL_8 = "Total8";
    public static final String TOTAL_9 = "Total9";
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

    public static final String FIN_STMT_REPORT_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new FinStmtReportScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new FinStmtReportScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new FinStmtReportScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new FinStmtHeaderField(this, FIN_STMT_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new FinStmtField(this, FIN_STMT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new BudgetCompField(this, BUDGET_COMP, 1, null, null);
        if (iFieldSeq == 10)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new DateField(this, START_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new DateField(this, END_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new ProfitCenterField(this, PROFIT_CENTER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new BooleanField(this, EXCLUDE_CLOSING, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 16)
            field = new StringField(this, TEMPLATE, 40, null, "finstmt");
        if (iFieldSeq == 17)
            field = new CurrencyField(this, START_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new CurrencyField(this, BALANCE_CHANGE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new CurrencyField(this, END_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new CurrencyField(this, TARGET_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new CurrencyField(this, RATIO_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
            field = new FloatField(this, RATIO_PERCENT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 23)
            field = new CurrencyField(this, IS_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 24)
            field = new ReferenceField(this, LAST_STATEMENT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 25)
            field = new CurrencyField(this, TOTAL_0, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 26)
            field = new CurrencyField(this, TOTAL_1, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 27)
            field = new CurrencyField(this, TOTAL_2, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 28)
            field = new CurrencyField(this, TOTAL_3, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 29)
            field = new CurrencyField(this, TOTAL_4, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 30)
            field = new CurrencyField(this, TOTAL_5, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 31)
            field = new CurrencyField(this, TOTAL_6, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 32)
            field = new CurrencyField(this, TOTAL_7, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 33)
            field = new CurrencyField(this, TOTAL_8, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 34)
            field = new CurrencyField(this, TOTAL_9, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            m_recPeriod = new Period(this.findRecordOwner());
            if (m_recPeriod.getRecordOwner() != null)
                m_recPeriod.getRecordOwner().removeRecord(m_recPeriod);
        }
        m_recPeriod.setPeriodDefaults(this, FinStmtReportScreenRecord.START_DATE, FinStmtReportScreenRecord.END_DATE, null);
    }

}
