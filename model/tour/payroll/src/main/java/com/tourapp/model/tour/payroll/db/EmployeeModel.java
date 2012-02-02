/**
 * @(#)EmployeeModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.payroll.db;

import org.jbundle.model.main.db.*;

public interface EmployeeModel extends PersonModel
{

    //public static final String ID = ID;
    //public static final String USER_ID = USER_ID;
    //public static final String NAME_SORT = NAME_SORT;
    //public static final String ADDRESS_LINE_2 = ADDRESS_LINE_2;
    //public static final String NAME = NAME;
    public static final String HIRE_DATE = DATE_ENTERED;
    //public static final String DATE_CHANGED = DATE_CHANGED;
    //public static final String CHANGED_ID = CHANGED_ID;
    //public static final String ADDRESS_LINE_1 = ADDRESS_LINE_1;
    //public static final String CITY_OR_TOWN = CITY_OR_TOWN;
    //public static final String STATE_OR_REGION = STATE_OR_REGION;
    //public static final String POSTAL_CODE = POSTAL_CODE;
    //public static final String COUNTRY = COUNTRY;
    public static final String HOME_PHONE = TEL;
    //public static final String EMAIL = EMAIL;
    //public static final String WEB = WEB;
    //public static final String COMMENTS = COMMENTS;
    //public static final String PASSWORD = PASSWORD;
    //public static final String POSTAL_CODE_SORT = POSTAL_CODE_SORT;
    public static final String FIRST_NAME = "FirstName";
    public static final String TITLE = "Title";
    public static final String BIRTHDATE = "Birthdate";
    public static final String EXTENSION = "Extension";
    public static final String PHOTO = "Photo";
    public static final String REPORTS_TO = "ReportsTo";
    public static final String PAY_FREQUENCY = "PayFrequency";
    public static final String PAY_TYPE = "PayType";
    public static final String SALARY = "Salary";
    public static final String HOURLY_RATE = "HourlyRate";
    public static final String OVERTIME_RATE = "OvertimeRate";
    public static final String SPECIAL_1_RATE = "Special1Rate";
    public static final String SPECIAL_2_RATE = "Special2Rate";
    public static final String TAX_ID_NO = "TaxIdNo";
    public static final String MARITAL_STATUS = "MaritalStatus";
    public static final String FED_ALLOW = "FedAllow";
    public static final String ADD_DEDUCT = "AddDeduct";
    public static final String FED_EXEMPT = "FedExempt";
    public static final String STATE_TAX_CODE = "StateTaxCode";
    public static final String STATE_ALLOW = "StateAllow";
    public static final String ADD_STATE = "AddState";
    public static final String STATE_EXEMPT = "StateExempt";
    public static final String LOCAL_CODE = "LocalCode";
    public static final String LOCAL_ALLOW = "LocalAllow";
    public static final String ADD_LOCAL = "AddLocal";
    public static final String LOCAL_EXEMPT = "LocalExempt";
    public static final String FICA_EXEMPT = "FicaExempt";
    public static final String FUI_EXEMPT = "FuiExempt";
    public static final String SUI_EXEMPT = "SuiExempt";
    public static final String SDI_EXEMPT = "SdiExempt";
    public static final String DEPARTMENT_ID = "DepartmentID";
    public static final String HOME_ACCOUNT_ID = "HomeAccountID";
    public static final String IN_PENSION_PLAN = "InPensionPlan";
    public static final String EF_TACCOUNT = "EFTaccount";
    public static final String DIST_PAY = "DistPay";
    public static final String SICK_DUE = "SickDue";
    public static final String SICK_TAKEN = "SickTaken";
    public static final String VACATION_DUE = "VacationDue";
    public static final String VACATION_TAKEN = "VacationTaken";
    public static final String YTD_SICK_HOURS = "YTDSickHours";
    public static final String YTD_SICK_PAY = "YTDSickPay";
    public static final String DEDUCTION_ID1 = "DeductionID1";
    public static final String FREQUENCY_1 = "Frequency1";
    public static final String AMOUNT_1 = "Amount1";
    public static final String DEDUCTION_ID2 = "DeductionID2";
    public static final String FREQUENCY_2 = "Frequency2";
    public static final String AMOUNT_2 = "Amount2";
    public static final String DEDUCTION_ID3 = "DeductionID3";
    public static final String FREQUENCY_3 = "Frequency3";
    public static final String AMOUNT_3 = "Amount3";
    public static final String DEDUCTION_ID4 = "DeductionID4";
    public static final String FREQUENCY_4 = "Frequency4";
    public static final String AMOUNT_4 = "Amount4";
    public static final String LAST_RAISE_DATE = "LastRaiseDate";
    public static final String PREVIOUS_SALARY = "PreviousSalary";
    public static final String PREVIOUS_PAY_RATE = "PreviousPayRate";
    public static final String REVIEW_DATE = "ReviewDate";
    public static final String ANNIVERSARY_DATE = "AnniversaryDate";
    public static final String TERMINATION_DATE = "TerminationDate";
    public static final String YTD_SUI_PAID = "YtdSuiPaid";
    public static final String YTD_FUI_PAID = "YtdFuiPaid";
    public static final String STATUS = "Status";

    public static final String FIRST_NAME_KEY = "FirstName";

    public static final String POSTAL_CODE_KEY = "PostalCode";

    public static final String NAME_SORT_KEY = "NameSort";

    public static final String DEPARTMENT_ID_KEY = "DepartmentID";

    public static final String USER_ID_KEY = "UserID";
    public static final String EMPLOYEE_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.emp.EmployeeScreen";
    public static final String EMPLOYEE_GRID_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.emp.EmployeeGridScreen";

    public static final String EMPLOYEE_FILE = "Employee";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.payroll.db.Employee";
    public static final String THICK_CLASS = "com.tourapp.tour.payroll.db.Employee";

}
