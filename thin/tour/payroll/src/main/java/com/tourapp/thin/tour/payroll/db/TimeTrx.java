/**
  * @(#)TimeTrx.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.payroll.db.*;

public class TimeTrx extends FieldList
    implements TimeTrxModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, PAY_ENDING, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, TIME_EMP_NO, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAY_SEQ, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, PAY_SALARY, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, REGULAR_HRS, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OVERTIME_HRS, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SP_1_HOURS, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SP_2_HOURS, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_DE_1, 3, null, null);
        field = new FieldInfo(this, TIME_HRS_1, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_AMT_1, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_DE_2, 3, null, null);
        field = new FieldInfo(this, TIME_HRS_2, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_AMT_2, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_DE_3, 3, null, null);
        field = new FieldInfo(this, TIME_HRS_3, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_AMT_3, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_DE_4, 3, null, null);
        field = new FieldInfo(this, TIME_HRS_4, 6, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TIME_AMT_4, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, REGULAR_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OVERTIME_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SPECIAL_1_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SPECIAL_2_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, GROSS_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, STATE_GROSS_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, NON_TAX_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, FED_TAXES, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, STATE_TAXES, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, LOCAL_TAXES, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, FICA_TAXES, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SDI_TAXES, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OTHER_DED, 7, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, NET_PAY, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, PR_CHECK_NUM, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, WEEKS_WORKED, 6, null, null);
        field.setDataClass(Float.class);
        //field = new FieldInfo(this, DED_EARN_HOURS, 6, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, DED_EARN_DESC, 16, null, null);
        //field = new FieldInfo(this, DED_EARN_AMT, 8, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, DED_EARN_YTD, 10, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, PAY_GROSS, 8, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, PAY_TAXES, 8, null, null);
        //field.setDataClass(Float.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PayEnding");
        keyArea.addKeyField("PayEnding", Constants.ASCENDING);
        keyArea.addKeyField("TimeEmpNo", Constants.ASCENDING);
        keyArea.addKeyField("PaySeq", Constants.ASCENDING);
    }

}
