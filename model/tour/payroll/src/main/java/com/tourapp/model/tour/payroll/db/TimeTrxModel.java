/**
 * @(#)TimeTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface TimeTrxModel extends Rec
{

    //public static final String ID = ID;
    public static final String PAY_ENDING = "PayEnding";
    public static final String TIME_EMP_NO = "TimeEmpNo";
    public static final String PAY_SEQ = "PaySeq";
    public static final String PAY_SALARY = "PaySalary";
    public static final String REGULAR_HRS = "RegularHrs";
    public static final String OVERTIME_HRS = "OvertimeHrs";
    public static final String SP_1_HOURS = "Sp1Hours";
    public static final String SP_2_HOURS = "Sp2Hours";
    public static final String TIME_DE_1 = "TimeDe1";
    public static final String TIME_HRS_1 = "TimeHrs1";
    public static final String TIME_AMT_1 = "TimeAmt1";
    public static final String TIME_DE_2 = "TimeDe2";
    public static final String TIME_HRS_2 = "TimeHrs2";
    public static final String TIME_AMT_2 = "TimeAmt2";
    public static final String TIME_DE_3 = "TimeDe3";
    public static final String TIME_HRS_3 = "TimeHrs3";
    public static final String TIME_AMT_3 = "TimeAmt3";
    public static final String TIME_DE_4 = "TimeDe4";
    public static final String TIME_HRS_4 = "TimeHrs4";
    public static final String TIME_AMT_4 = "TimeAmt4";
    public static final String REGULAR_PAY = "RegularPay";
    public static final String OVERTIME_PAY = "OvertimePay";
    public static final String SPECIAL_1_PAY = "Special1Pay";
    public static final String SPECIAL_2_PAY = "Special2Pay";
    public static final String GROSS_PAY = "GrossPay";
    public static final String STATE_GROSS_PAY = "StateGrossPay";
    public static final String NON_TAX_PAY = "NonTaxPay";
    public static final String FED_TAXES = "FedTaxes";
    public static final String STATE_TAXES = "StateTaxes";
    public static final String LOCAL_TAXES = "LocalTaxes";
    public static final String FICA_TAXES = "FicaTaxes";
    public static final String SDI_TAXES = "SdiTaxes";
    public static final String OTHER_DED = "OtherDed";
    public static final String NET_PAY = "NetPay";
    public static final String PR_CHECK_NUM = "PrCheckNum";
    public static final String WEEKS_WORKED = "WeeksWorked";
    public static final String DED_EARN_HOURS = "DedEarnHours";
    public static final String DED_EARN_DESC = "DedEarnDesc";
    public static final String DED_EARN_AMT = "DedEarnAmt";
    public static final String DED_EARN_YTD = "DedEarnYtd";
    public static final String PAY_GROSS = "PayGross";
    public static final String PAY_TAXES = "PayTaxes";

    public static final String PAY_ENDING_KEY = "PayEnding";
    public static final String TIME_TRX_SCREEN_CLASS = "com.tourapp.tour.payroll.report.payroll.TimeTrxScreen";
    public static final String TIME_TRX_GRID_SCREEN_CLASS = "com.tourapp.tour.payroll.report.payroll.TimeTrxGridScreen";

    public static final String TIME_TRX_FILE = "TimeTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.payroll.db.TimeTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.payroll.db.TimeTrx";

}
