/**
 * @(#)EmployeeControl.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.payroll.db.*;

public class EmployeeControl extends FieldList
    implements EmployeeControlModel
{
    private static final long serialVersionUID = 1L;


    public EmployeeControl()
    {
        super();
    }
    public EmployeeControl(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String EMPLOYEE_CONTROL_FILE = "EmployeeControl";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? EmployeeControl.EMPLOYEE_CONTROL_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, ID, 4, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, FEDERAL_ID_NO, 11, null, null);
        field = new FieldInfo(this, FED_EXEMPTION, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, FED_TAX_ACCT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, FED_TAX_DESC, 15, null, null);
        field = new FieldInfo(this, STATE_ID_NO, 11, null, null);
        field = new FieldInfo(this, STATE_EXEMPTION, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, STATE_TAX_ACCT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, STATE_TAX_DESC, 15, null, null);
        field = new FieldInfo(this, LOCAL_ID_NO, 11, null, null);
        field = new FieldInfo(this, LOCAL_EXEMPTION, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, LOCAL_TAX_ACCT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LOCAL_TAX_DESC, 15, null, null);
        field = new FieldInfo(this, FICA_TAX_DESC, 15, null, null);
        field = new FieldInfo(this, FICA_EMPLOYEE, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, FICA_EMPLOYER, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, FICA_TAX_ACCT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MAX_FICA, 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MEDICARE_TAX_DESC, 15, null, null);
        field = new FieldInfo(this, MEDICARE_EMPLOYEE, 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, MAX_MEDICARE, 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MAX_EMPLOYER_FICA, 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MEDICARE_EMPLOYER, 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, MAX_EMPLOYER_MEDICARE, 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, FUI_PER, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, FUI_MAX, 6, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, FUI_TAX_ACCT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CASH_BANK_ACCT_ID, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TAX_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DEFAULT_STATE, 10, null, null);
        field = new FieldInfo(this, SUI_PER, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SUI_MAX, 6, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SUI_TAX_ACCT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SDI_TAX_DESC, 15, null, null);
        field = new FieldInfo(this, SDI_PER, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SDI_MAX, 6, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SDI_TAX_ACCT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LAST_BI_WEEKLY, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DIST_TO_GL, 1, null, null);
        field = new FieldInfo(this, DIST_TO_JOBS, 1, null, null);
        field = new FieldInfo(this, REGULAR_PAY_DESC, 15, null, null);
        field = new FieldInfo(this, OVERTIME_PAY_DESC, 15, null, null);
        field = new FieldInfo(this, OT_TIMES_BASE, 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SP_1_DESC, 15, null, null);
        field = new FieldInfo(this, SP_1_TIMES_BASE, 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SP_2_DESC, 15, null, null);
        field = new FieldInfo(this, SP_2_TIMES_BASE, 4, null, null);
        field.setDataClass(Float.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
