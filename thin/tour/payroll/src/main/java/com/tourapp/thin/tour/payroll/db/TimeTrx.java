/**
 *  @(#)TimeTrx.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class TimeTrx extends FieldList
{

    public TimeTrx()
    {
        super();
    }
    public TimeTrx(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TIME_TRX_FILE = "TimeTrx";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TimeTrx.TIME_TRX_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "PayEnding", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "TimeEmpNo", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PaySeq", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "PaySalary", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "RegularHrs", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OvertimeHrs", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Sp1Hours", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Sp2Hours", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeDe1", 3, null, null);
        field = new FieldInfo(this, "TimeHrs1", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeAmt1", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeDe2", 3, null, null);
        field = new FieldInfo(this, "TimeHrs2", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeAmt2", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeDe3", 3, null, null);
        field = new FieldInfo(this, "TimeHrs3", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeAmt3", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeDe4", 3, null, null);
        field = new FieldInfo(this, "TimeHrs4", 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TimeAmt4", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "RegularPay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OvertimePay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Special1Pay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Special2Pay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "GrossPay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "StateGrossPay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "NonTaxPay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "FedTaxes", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "StateTaxes", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "LocalTaxes", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "FicaTaxes", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "SdiTaxes", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OtherDed", 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "NetPay", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "PrCheckNum", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "WeeksWorked", 6, null, null);
        field.setDataClass(Float.class);
        //field = new FieldInfo(this, "DedEarnHours", 6, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, "DedEarnDesc", 16, null, null);
        //field = new FieldInfo(this, "DedEarnAmt", 8, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, "DedEarnYtd", 10, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "PayGross", 8, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, "PayTaxes", 8, null, null);
        //field.setDataClass(Float.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PayEnding");
        keyArea.addKeyField("PayEnding", Constants.ASCENDING);
        keyArea.addKeyField("TimeEmpNo", Constants.ASCENDING);
        keyArea.addKeyField("PaySeq", Constants.ASCENDING);
    }

}
