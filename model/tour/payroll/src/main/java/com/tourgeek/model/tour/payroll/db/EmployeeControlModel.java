/**
  * @(#)EmployeeControlModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface EmployeeControlModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String FEDERAL_ID_NO = "FederalIdNo";
    public static final String FED_EXEMPTION = "FedExemption";
    public static final String FED_TAX_ACCT_ID = "FedTaxAcctID";
    public static final String FED_TAX_DESC = "FedTaxDesc";
    public static final String STATE_ID_NO = "StateIdNo";
    public static final String STATE_EXEMPTION = "StateExemption";
    public static final String STATE_TAX_ACCT_ID = "StateTaxAcctID";
    public static final String STATE_TAX_DESC = "StateTaxDesc";
    public static final String LOCAL_ID_NO = "LocalIdNo";
    public static final String LOCAL_EXEMPTION = "LocalExemption";
    public static final String LOCAL_TAX_ACCT_ID = "LocalTaxAcctID";
    public static final String LOCAL_TAX_DESC = "LocalTaxDesc";
    public static final String FICA_TAX_DESC = "FicaTaxDesc";
    public static final String FICA_EMPLOYEE = "FICAEmployee";
    public static final String FICA_EMPLOYER = "FICAEmployer";
    public static final String FICA_TAX_ACCT_ID = "FICATaxAcctID";
    public static final String MAX_FICA = "MaxFICA";
    public static final String MEDICARE_TAX_DESC = "MedicareTaxDesc";
    public static final String MEDICARE_EMPLOYEE = "MedicareEmployee";
    public static final String MAX_MEDICARE = "MaxMedicare";
    public static final String MAX_EMPLOYER_FICA = "MaxEmployerFICA";
    public static final String MEDICARE_EMPLOYER = "MedicareEmployer";
    public static final String MAX_EMPLOYER_MEDICARE = "MaxEmployerMedicare";
    public static final String FUI_PER = "FuiPer";
    public static final String FUI_MAX = "FuiMax";
    public static final String FUI_TAX_ACCT_ID = "FUITaxAcctID";
    public static final String CASH_BANK_ACCT_ID = "CashBankAcctID";
    public static final String TAX_ACCT_ID = "TaxAcctID";
    public static final String DEFAULT_STATE = "DefaultState";
    public static final String SUI_PER = "SuiPer";
    public static final String SUI_MAX = "SuiMax";
    public static final String SUI_TAX_ACCT_ID = "SUITaxAcctID";
    public static final String SDI_TAX_DESC = "SdiTaxDesc";
    public static final String SDI_PER = "SdiPer";
    public static final String SDI_MAX = "SdiMax";
    public static final String SDI_TAX_ACCT_ID = "SDITaxAcctID";
    public static final String LAST_BI_WEEKLY = "LastBiWeekly";
    public static final String DIST_TO_GL = "DistToGl";
    public static final String DIST_TO_JOBS = "DistToJobs";
    public static final String REGULAR_PAY_DESC = "RegularPayDesc";
    public static final String OVERTIME_PAY_DESC = "OvertimePayDesc";
    public static final String OT_TIMES_BASE = "OtTimesBase";
    public static final String SP_1_DESC = "Sp1Desc";
    public static final String SP_1_TIMES_BASE = "Sp1TimesBase";
    public static final String SP_2_DESC = "Sp2Desc";
    public static final String SP_2_TIMES_BASE = "Sp2TimesBase";
    public static final String EMPLOYEE_CONTROL_SCREEN_CLASS = "com.tourgeek.tour.payroll.screen.misc.EmployeeControlScreen";
    public static final String EMPLOYEE_CONTROL_SCREEN_2_CLASS = "com.tourgeek.tour.payroll.screen.misc.EmployeeControlScreen";

    public static final String EMPLOYEE_CONTROL_FILE = "EmployeeControl";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.payroll.db.EmployeeControl";
    public static final String THICK_CLASS = "com.tourgeek.tour.payroll.db.EmployeeControl";

}
