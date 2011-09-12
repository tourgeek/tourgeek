/**
 * @(#)CashPlanScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.cashplan;

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
import com.tourapp.tour.base.db.event.*;

/**
 *  CashPlanScreenRecord - Fields for the Cash Receipts Planning Report.
 */
public class CashPlanScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    public static final int kStartDate = kReportScreenRecordLastField + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kPeriodType = kEndDate + 1;
    public static final int kPeriodLength = kPeriodType + 1;
    public static final int kDepositPeriodDate = kPeriodLength + 1;
    public static final int kFinalPeriodDate = kDepositPeriodDate + 1;
    public static final int kDepositDueBalance = kFinalPeriodDate + 1;
    public static final int kFinalDueLessDeposit = kDepositDueBalance + 1;
    public static final int kFinalDueLessDepPymt = kFinalDueLessDeposit + 1;
    public static final int kFromDate = kFinalDueLessDepPymt + 1;
    public static final int kDeposits = kFromDate + 1;
    public static final int kReceipts = kDeposits + 1;
    public static final int kFinalPayments = kReceipts + 1;
    public static final int kFinalReceipts = kFinalPayments + 1;
    public static final int kBalance = kFinalReceipts + 1;
    public static final int kCashPlanScreenRecordLastField = kBalance;
    public static final int kCashPlanScreenRecordFields = kBalance - DBConstants.MAIN_FIELD + 1;
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

    public static final String kCashPlanScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new CashPlanScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new CashPlanScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new CashPlanScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kStartDate)
            field = new CashPlanScreenRecord_StartDate(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPeriodType)
            field = new PeriodTypeField(this, "PeriodType", Constants.DEFAULT_FIELD_LENGTH, null, "PeriodTypeField.MONTHLY");
        if (iFieldSeq == kPeriodLength)
            field = new ShortField(this, "PeriodLength", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kDepositPeriodDate)
            field = new DateField(this, "DepositPeriodDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalPeriodDate)
            field = new DateField(this, "FinalPeriodDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepositDueBalance)
            field = new CurrencyField(this, "DepositDueBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalDueLessDeposit)
            field = new CurrencyField(this, "FinalDueLessDeposit", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalDueLessDepPymt)
            field = new CurrencyField(this, "FinalDueLessDepPymt", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFromDate)
            field = new DateField(this, "FromDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDeposits)
            field = new CurrencyField(this, "Deposits", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReceipts)
            field = new CurrencyField(this, "Receipts", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalPayments)
            field = new CurrencyField(this, "FinalPayments", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalReceipts)
            field = new CurrencyField(this, "FinalReceipts", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalance)
            field = new CurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCashPlanScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
