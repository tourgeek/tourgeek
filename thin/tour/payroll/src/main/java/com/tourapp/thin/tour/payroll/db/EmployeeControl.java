/**
 *  @(#)EmployeeControl.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class EmployeeControl extends FieldList
{

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
        field = new FieldInfo(this, "ID", 4, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "FederalIdNo", 11, null, null);
        field = new FieldInfo(this, "FedExemption", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "FedTaxAcctID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "FedTaxDesc", 15, null, null);
        field = new FieldInfo(this, "StateIdNo", 11, null, null);
        field = new FieldInfo(this, "StateExemption", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "StateTaxAcctID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "StateTaxDesc", 15, null, null);
        field = new FieldInfo(this, "LocalIdNo", 11, null, null);
        field = new FieldInfo(this, "LocalExemption", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "LocalTaxAcctID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "LocalTaxDesc", 15, null, null);
        field = new FieldInfo(this, "FicaTaxDesc", 15, null, null);
        field = new FieldInfo(this, "FICAEmployee", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "FICAEmployer", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "FICATaxAcctID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "MaxFICA", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "MedicareTaxDesc", 15, null, null);
        field = new FieldInfo(this, "MedicareEmployee", 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "MaxMedicare", 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "MaxEmployerFICA", 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "MedicareEmployer", 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "MaxEmployerMedicare", 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "FuiPer", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "FuiMax", 6, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "FUITaxAcctID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CashBankAcctID", 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TaxAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DefaultState", 10, null, null);
        field = new FieldInfo(this, "SuiPer", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "SuiMax", 6, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "SUITaxAcctID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "SdiTaxDesc", 15, null, null);
        field = new FieldInfo(this, "SdiPer", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "SdiMax", 6, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "SDITaxAcctID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "LastBiWeekly", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DistToGl", 1, null, null);
        field = new FieldInfo(this, "DistToJobs", 1, null, null);
        field = new FieldInfo(this, "RegularPayDesc", 15, null, null);
        field = new FieldInfo(this, "OvertimePayDesc", 15, null, null);
        field = new FieldInfo(this, "OtTimesBase", 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Sp1Desc", 15, null, null);
        field = new FieldInfo(this, "Sp1TimesBase", 4, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Sp2Desc", 15, null, null);
        field = new FieldInfo(this, "Sp2TimesBase", 4, null, null);
        field.setDataClass(Float.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
