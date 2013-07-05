/**
  * @(#)CashPlanScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.report.cashplan;

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
import org.jbundle.main.db.*;
import com.tourgeek.tour.base.db.event.*;

/**
 *  CashPlanScreenRecord - Fields for the Cash Receipts Planning Report.
 */
public class CashPlanScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String PERIOD_TYPE = "PeriodType";
    public static final String PERIOD_LENGTH = "PeriodLength";
    public static final String DEPOSIT_PERIOD_DATE = "DepositPeriodDate";
    public static final String FINAL_PERIOD_DATE = "FinalPeriodDate";
    public static final String DEPOSIT_DUE_BALANCE = "DepositDueBalance";
    public static final String FINAL_DUE_LESS_DEPOSIT = "FinalDueLessDeposit";
    public static final String FINAL_DUE_LESS_DEP_PYMT = "FinalDueLessDepPymt";
    public static final String FROM_DATE = "FromDate";
    public static final String DEPOSITS = "Deposits";
    public static final String RECEIPTS = "Receipts";
    public static final String FINAL_PAYMENTS = "FinalPayments";
    public static final String FINAL_RECEIPTS = "FinalReceipts";
    public static final String BALANCE = "Balance";
    /**
     * Default constructor.
     */
    public CashPlanScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CashPlanScreenRecord(RecordOwner screen)
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

    public static final String CASH_PLAN_SCREEN_RECORD_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new CashPlanScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new CashPlanScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new CashPlanScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new CashPlanScreenRecord_StartDate(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new PeriodTypeField(this, PERIOD_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, "PeriodTypeField.MONTHLY");
        if (iFieldSeq == 10)
            field = new ShortField(this, PERIOD_LENGTH, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == 11)
            field = new DateField(this, DEPOSIT_PERIOD_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new DateField(this, FINAL_PERIOD_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new CurrencyField(this, DEPOSIT_DUE_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new CurrencyField(this, FINAL_DUE_LESS_DEPOSIT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new CurrencyField(this, FINAL_DUE_LESS_DEP_PYMT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new DateField(this, FROM_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new CurrencyField(this, DEPOSITS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new CurrencyField(this, RECEIPTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new CurrencyField(this, FINAL_PAYMENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new CurrencyField(this, FINAL_RECEIPTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new CurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
