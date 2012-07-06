/**
  * @(#)Employee.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.*;
import com.tourapp.model.tour.payroll.db.*;

public class Employee extends Person
    implements EmployeeModel
{
    private static final long serialVersionUID = 1L;


    public Employee()
    {
        super();
    }
    public Employee(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String EMPLOYEE_FILE = "Employee";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Employee.EMPLOYEE_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "payroll";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, CODE, 16, null, null);
        field = new FieldInfo(this, NAME, 30, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_1, 40, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_2, 40, null, null);
        field = new FieldInfo(this, CITY_OR_TOWN, 15, null, null);
        field = new FieldInfo(this, STATE_OR_REGION, 15, null, "CA");
        field = new FieldInfo(this, POSTAL_CODE, 10, null, null);
        field = new FieldInfo(this, COUNTRY, 15, null, null);
        field = new FieldInfo(this, HOME_PHONE, 24, null, null);
        field = new FieldInfo(this, FAX, 24, null, null);
        field = new FieldInfo(this, EMAIL, 40, null, null);
        field = new FieldInfo(this, WEB, 60, null, null);
        field = new FieldInfo(this, HIRE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, DATE_CHANGED, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, CHANGED_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMENTS, 9999, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PASSWORD, 16, null, null);
        field = new FieldInfo(this, NAME_SORT, 6, null, null);
        field = new FieldInfo(this, POSTAL_CODE_SORT, 5, null, null);
        field = new FieldInfo(this, FIRST_NAME, 20, null, null);
        field = new FieldInfo(this, TITLE, 30, null, null);
        field = new FieldInfo(this, BIRTHDATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, EXTENSION, 4, null, null);
        field = new FieldInfo(this, PHOTO, 9999, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, REPORTS_TO, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAY_FREQUENCY, 1, null, null);
        field = new FieldInfo(this, PAY_TYPE, 1, null, "H");
        field = new FieldInfo(this, SALARY, 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, HOURLY_RATE, 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, OVERTIME_RATE, 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SPECIAL_1_RATE, 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SPECIAL_2_RATE, 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TAX_ID_NO, 11, null, null);
        field = new FieldInfo(this, MARITAL_STATUS, 1, null, null);
        field = new FieldInfo(this, FED_ALLOW, 2, null, new Short((short)2));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ADD_DEDUCT, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, FED_EXEMPT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, STATE_TAX_CODE, 2, null, null);
        field = new FieldInfo(this, STATE_ALLOW, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ADD_STATE, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, STATE_EXEMPT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, LOCAL_CODE, 2, null, null);
        field = new FieldInfo(this, LOCAL_ALLOW, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ADD_LOCAL, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, LOCAL_EXEMPT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, FICA_EXEMPT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, FUI_EXEMPT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SUI_EXEMPT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SDI_EXEMPT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DEPARTMENT_ID, 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, HOME_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, IN_PENSION_PLAN, 1, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, EF_TACCOUNT, 30, null, null);
        field = new FieldInfo(this, DIST_PAY, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SICK_DUE, 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SICK_TAKEN, 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, VACATION_DUE, 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, VACATION_TAKEN, 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, YTD_SICK_HOURS, 10, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, YTD_SICK_PAY, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DEDUCTION_ID1, 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, FREQUENCY_1, 1, null, null);
        field = new FieldInfo(this, AMOUNT_1, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DEDUCTION_ID2, 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, FREQUENCY_2, 1, null, null);
        field = new FieldInfo(this, AMOUNT_2, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DEDUCTION_ID3, 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, FREQUENCY_3, 1, null, null);
        field = new FieldInfo(this, AMOUNT_3, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DEDUCTION_ID4, 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, FREQUENCY_4, 1, null, null);
        field = new FieldInfo(this, AMOUNT_4, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, LAST_RAISE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, PREVIOUS_SALARY, 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PREVIOUS_PAY_RATE, 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, REVIEW_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ANNIVERSARY_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, TERMINATION_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, YTD_SUI_PAID, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, YTD_FUI_PAID, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, STATUS, 10, null, null);
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "FirstName");
        keyArea.addKeyField("FirstName", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "PostalCode");
        keyArea.addKeyField("PostalCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "NameSort");
        keyArea.addKeyField("NameSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "DepartmentID");
        keyArea.addKeyField("DepartmentID", Constants.ASCENDING);
        keyArea.addKeyField("NameSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "UserID");
        keyArea.addKeyField("UserID", Constants.ASCENDING);
        keyArea.addKeyField("TerminationDate", Constants.ASCENDING);
    }

}
