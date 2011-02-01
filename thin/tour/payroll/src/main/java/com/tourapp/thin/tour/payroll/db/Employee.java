/**
 *  @(#)Employee.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class Employee extends FieldList
{

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
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Code", 16, null, null);
        field = new FieldInfo(this, "Name", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, "CA");
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "HomePhone", 24, null, null);
        field = new FieldInfo(this, "Fax", 24, null, null);
        field = new FieldInfo(this, "Email", 40, null, null);
        field = new FieldInfo(this, "Web", 60, null, null);
        field = new FieldInfo(this, "HireDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "DateChanged", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ChangedID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Comments", 9999, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Password", 16, null, null);
        field = new FieldInfo(this, "NameSort", 6, null, null);
        field = new FieldInfo(this, "PostalCodeSort", 5, null, null);
        field = new FieldInfo(this, "FirstName", 20, null, null);
        field = new FieldInfo(this, "Title", 30, null, null);
        field = new FieldInfo(this, "Birthdate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Extension", 4, null, null);
        field = new FieldInfo(this, "Photo", 9999, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ReportsTo", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PayFrequency", 1, null, null);
        field = new FieldInfo(this, "PayType", 1, null, "H");
        field = new FieldInfo(this, "Salary", 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "HourlyRate", 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "OvertimeRate", 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Special1Rate", 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Special2Rate", 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TaxIdNo", 11, null, null);
        field = new FieldInfo(this, "MaritalStatus", 1, null, null);
        field = new FieldInfo(this, "FedAllow", 2, null, new Short((short)2));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AddDeduct", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "FedExempt", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "StateTaxCode", 2, null, null);
        field = new FieldInfo(this, "StateAllow", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AddState", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "StateExempt", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "LocalCode", 2, null, null);
        field = new FieldInfo(this, "LocalAllow", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AddLocal", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "LocalExempt", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "FicaExempt", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "FuiExempt", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "SuiExempt", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "SdiExempt", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DepartmentID", 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "HomeAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InPensionPlan", 1, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "EFTaccount", 30, null, null);
        field = new FieldInfo(this, "DistPay", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "SickDue", 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "SickTaken", 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "VacationDue", 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "VacationTaken", 3, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "YTDSickHours", 10, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "YTDSickPay", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DeductionID1", 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Frequency1", 1, null, null);
        field = new FieldInfo(this, "Amount1", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DeductionID2", 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Frequency2", 1, null, null);
        field = new FieldInfo(this, "Amount2", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DeductionID3", 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Frequency3", 1, null, null);
        field = new FieldInfo(this, "Amount3", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DeductionID4", 3, null, "");
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Frequency4", 1, null, null);
        field = new FieldInfo(this, "Amount4", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "LastRaiseDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "PreviousSalary", 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "PreviousPayRate", 5, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ReviewDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "AnniversaryDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "TerminationDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "YtdSuiPaid", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "YtdFuiPaid", 7, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Status", 10, null, null);
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
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
