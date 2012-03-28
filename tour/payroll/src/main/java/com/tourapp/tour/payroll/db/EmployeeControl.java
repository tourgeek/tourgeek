/**
 * @(#)EmployeeControl.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.db;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.payroll.screen.misc.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.model.tour.payroll.db.*;

/**
 *  EmployeeControl - Employee Control File.
 */
public class EmployeeControl extends ControlRecord
     implements EmployeeControlModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public EmployeeControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public EmployeeControl(RecordOwner screen)
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(EMPLOYEE_CONTROL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Control";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "payroll";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(EMPLOYEE_CONTROL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(EMPLOYEE_CONTROL_SCREEN_2_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 4, null, null);
            field.setHidden(true);
        }
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new StringField(this, FEDERAL_ID_NO, 11, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, FED_EXEMPTION, 7, null, null);
        if (iFieldSeq == 5)
        {
            field = new AccountField(this, FED_TAX_ACCT_ID, 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 6)
            field = new StringField(this, FED_TAX_DESC, 15, null, null);
        if (iFieldSeq == 7)
            field = new StringField(this, STATE_ID_NO, 11, null, null);
        if (iFieldSeq == 8)
            field = new CurrencyField(this, STATE_EXEMPTION, 7, null, null);
        if (iFieldSeq == 9)
        {
            field = new AccountField(this, STATE_TAX_ACCT_ID, 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 10)
            field = new StringField(this, STATE_TAX_DESC, 15, null, null);
        if (iFieldSeq == 11)
            field = new StringField(this, LOCAL_ID_NO, 11, null, null);
        if (iFieldSeq == 12)
            field = new CurrencyField(this, LOCAL_EXEMPTION, 7, null, null);
        if (iFieldSeq == 13)
        {
            field = new AccountField(this, LOCAL_TAX_ACCT_ID, 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 14)
            field = new StringField(this, LOCAL_TAX_DESC, 15, null, null);
        if (iFieldSeq == 15)
            field = new StringField(this, FICA_TAX_DESC, 15, null, null);
        if (iFieldSeq == 16)
            field = new PercentField(this, FICA_EMPLOYEE, 5, null, null);
        if (iFieldSeq == 17)
            field = new PercentField(this, FICA_EMPLOYER, 5, null, null);
        if (iFieldSeq == 18)
        {
            field = new AccountField(this, FICA_TAX_ACCT_ID, 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 19)
            field = new CurrencyField(this, MAX_FICA, 7, null, null);
        if (iFieldSeq == 20)
            field = new StringField(this, MEDICARE_TAX_DESC, 15, null, null);
        if (iFieldSeq == 21)
            field = new PercentField(this, MEDICARE_EMPLOYEE, 4, null, null);
        if (iFieldSeq == 22)
            field = new CurrencyField(this, MAX_MEDICARE, 8, null, null);
        if (iFieldSeq == 23)
            field = new CurrencyField(this, MAX_EMPLOYER_FICA, 8, null, null);
        if (iFieldSeq == 24)
            field = new PercentField(this, MEDICARE_EMPLOYER, 4, null, null);
        if (iFieldSeq == 25)
            field = new CurrencyField(this, MAX_EMPLOYER_MEDICARE, 8, null, null);
        if (iFieldSeq == 26)
            field = new PercentField(this, FUI_PER, 5, null, null);
        if (iFieldSeq == 27)
            field = new CurrencyField(this, FUI_MAX, 6, null, null);
        if (iFieldSeq == 28)
        {
            field = new AccountField(this, FUI_TAX_ACCT_ID, 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 29)
            field = new BankAcctField(this, CASH_BANK_ACCT_ID, 2, null, null);
        if (iFieldSeq == 30)
        {
            field = new AccountField(this, TAX_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 31)
            field = new StringField(this, DEFAULT_STATE, 10, null, null);
        if (iFieldSeq == 32)
            field = new PercentField(this, SUI_PER, 5, null, null);
        if (iFieldSeq == 33)
            field = new CurrencyField(this, SUI_MAX, 6, null, null);
        if (iFieldSeq == 34)
        {
            field = new AccountField(this, SUI_TAX_ACCT_ID, 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 35)
            field = new StringField(this, SDI_TAX_DESC, 15, null, null);
        if (iFieldSeq == 36)
            field = new PercentField(this, SDI_PER, 5, null, null);
        if (iFieldSeq == 37)
            field = new CurrencyField(this, SDI_MAX, 6, null, null);
        if (iFieldSeq == 38)
        {
            field = new AccountField(this, SDI_TAX_ACCT_ID, 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == 39)
            field = new BooleanField(this, LAST_BI_WEEKLY, 1, null, null);
        if (iFieldSeq == 40)
            field = new DistField(this, DIST_TO_GL, 1, null, null);
        if (iFieldSeq == 41)
            field = new DistField(this, DIST_TO_JOBS, 1, null, null);
        if (iFieldSeq == 42)
            field = new StringField(this, REGULAR_PAY_DESC, 15, null, null);
        if (iFieldSeq == 43)
            field = new StringField(this, OVERTIME_PAY_DESC, 15, null, null);
        if (iFieldSeq == 44)
            field = new PercentField(this, OT_TIMES_BASE, 4, null, null);
        if (iFieldSeq == 45)
            field = new StringField(this, SP_1_DESC, 15, null, null);
        if (iFieldSeq == 46)
            field = new PercentField(this, SP_1_TIMES_BASE, 4, null, null);
        if (iFieldSeq == 47)
            field = new StringField(this, SP_2_DESC, 15, null, null);
        if (iFieldSeq == 48)
            field = new PercentField(this, SP_2_TIMES_BASE, 4, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }

}
