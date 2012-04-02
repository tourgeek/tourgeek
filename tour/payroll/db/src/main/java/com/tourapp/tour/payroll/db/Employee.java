/**
 * @(#)Employee.
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
import org.jbundle.main.db.*;
import org.jbundle.main.user.db.*;
import com.tourapp.tour.base.db.event.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.payroll.db.*;

/**
 *  Employee - Employees.
 */
public class Employee extends Person
     implements EmployeeModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Employee()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Employee(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(EMPLOYEE_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Employee";
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
            screen = Record.makeNewScreen(EMPLOYEE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(EMPLOYEE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new CounterField(this, ID, 8, null, null);
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
        //if (iFieldSeq == 3)
        //  field = new StringField(this, CODE, 16, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, NAME, 30, null, null);
        //if (iFieldSeq == 5)
        //  field = new StringField(this, ADDRESS_LINE_1, 40, null, null);
        //if (iFieldSeq == 6)
        //  field = new StringField(this, ADDRESS_LINE_2, 40, null, null);
        //if (iFieldSeq == 7)
        //  field = new StringField(this, CITY_OR_TOWN, 15, null, null);
        if (iFieldSeq == 8)
            field = new StringField(this, STATE_OR_REGION, 15, null, "CA");
        //if (iFieldSeq == 9)
        //  field = new StringField(this, POSTAL_CODE, 10, null, null);
        //if (iFieldSeq == 10)
        //  field = new StringField(this, COUNTRY, 15, null, null);
        if (iFieldSeq == 11)
            field = new PhoneField(this, HOME_PHONE, 24, null, null);
        //if (iFieldSeq == 12)
        //  field = new FaxField(this, FAX, 24, null, null);
        //if (iFieldSeq == 13)
        //  field = new EMailField(this, EMAIL, 40, null, null);
        //if (iFieldSeq == 14)
        //  field = new URLField(this, WEB, 60, null, null);
        if (iFieldSeq == 15)
        {
            field = new Employee_HireDate(this, HIRE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 16)
        //  field = new DateField(this, DATE_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 17)
        //  field = new ReferenceField(this, CHANGED_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new MemoField(this, COMMENTS, 9999, null, null);
        if (iFieldSeq == 19)
            field = new UserField(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 20)
        //  field = new StringField(this, PASSWORD, 16, null, null);
        if (iFieldSeq == 21)
            field = new StringField(this, NAME_SORT, 6, null, null);
        //if (iFieldSeq == 22)
        //  field = new StringField(this, POSTAL_CODE_SORT, 5, null, null);
        if (iFieldSeq == 23)
            field = new StringField(this, FIRST_NAME, 20, null, null);
        if (iFieldSeq == 24)
            field = new StringField(this, TITLE, 30, null, null);
        if (iFieldSeq == 25)
            field = new DateField(this, BIRTHDATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 26)
            field = new StringField(this, EXTENSION, 4, null, null);
        if (iFieldSeq == 27)
            field = new ImageField(this, PHOTO, 9999, null, null);
        if (iFieldSeq == 28)
            field = new EmployeeField(this, REPORTS_TO, 8, null, null);
        if (iFieldSeq == 29)
        {
            field = new PeriodTypeField(this, PAY_FREQUENCY, 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 30)
            field = new PayTypeField(this, PAY_TYPE, 1, null, "H");
        if (iFieldSeq == 31)
            field = new CurrencyField(this, SALARY, 9, null, null);
        if (iFieldSeq == 32)
            field = new CurrencyField(this, HOURLY_RATE, 5, null, null);
        if (iFieldSeq == 33)
            field = new CurrencyField(this, OVERTIME_RATE, 5, null, null);
        if (iFieldSeq == 34)
            field = new CurrencyField(this, SPECIAL_1_RATE, 5, null, null);
        if (iFieldSeq == 35)
            field = new CurrencyField(this, SPECIAL_2_RATE, 5, null, null);
        if (iFieldSeq == 36)
        {
            field = new StringField(this, TAX_ID_NO, 11, null, null);
            field.setMinimumLength(11);
        }
        if (iFieldSeq == 37)
            field = new MaritalStatusField(this, MARITAL_STATUS, 1, null, null);
        if (iFieldSeq == 38)
            field = new ShortField(this, FED_ALLOW, 2, null, new Short((short)2));
        if (iFieldSeq == 39)
            field = new FloatField(this, ADD_DEDUCT, 8, null, null);
        if (iFieldSeq == 40)
            field = new BooleanField(this, FED_EXEMPT, 1, null, null);
        if (iFieldSeq == 41)
            field = new StringField(this, STATE_TAX_CODE, 2, null, null);
        if (iFieldSeq == 42)
            field = new ShortField(this, STATE_ALLOW, 2, null, null);
        if (iFieldSeq == 43)
            field = new FloatField(this, ADD_STATE, 8, null, null);
        if (iFieldSeq == 44)
            field = new BooleanField(this, STATE_EXEMPT, 1, null, null);
        if (iFieldSeq == 45)
            field = new StringField(this, LOCAL_CODE, 2, null, null);
        if (iFieldSeq == 46)
            field = new ShortField(this, LOCAL_ALLOW, 2, null, null);
        if (iFieldSeq == 47)
        {
            field = new FloatField(this, ADD_LOCAL, 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 48)
            field = new BooleanField(this, LOCAL_EXEMPT, 1, null, null);
        if (iFieldSeq == 49)
            field = new BooleanField(this, FICA_EXEMPT, 1, null, null);
        if (iFieldSeq == 50)
            field = new BooleanField(this, FUI_EXEMPT, 1, null, null);
        if (iFieldSeq == 51)
            field = new BooleanField(this, SUI_EXEMPT, 1, null, null);
        if (iFieldSeq == 52)
            field = new BooleanField(this, SDI_EXEMPT, 1, null, null);
        if (iFieldSeq == 53)
            field = new DepartmentField(this, DEPARTMENT_ID, 3, null, null);
        if (iFieldSeq == 54)
            field = new AccountField(this, HOME_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 55)
            field = new BooleanField(this, IN_PENSION_PLAN, 1, null, new Boolean(true));
        if (iFieldSeq == 56)
            field = new StringField(this, EF_TACCOUNT, 30, null, null);
        if (iFieldSeq == 57)
            field = new BooleanField(this, DIST_PAY, 1, null, null);
        if (iFieldSeq == 58)
            field = new FloatField(this, SICK_DUE, 3, null, null);
        if (iFieldSeq == 59)
            field = new FloatField(this, SICK_TAKEN, 3, null, null);
        if (iFieldSeq == 60)
            field = new FloatField(this, VACATION_DUE, 3, null, null);
        if (iFieldSeq == 61)
            field = new FloatField(this, VACATION_TAKEN, 3, null, null);
        if (iFieldSeq == 62)
            field = new FloatField(this, YTD_SICK_HOURS, 10, null, null);
        if (iFieldSeq == 63)
            field = new CurrencyField(this, YTD_SICK_PAY, 10, null, null);
        if (iFieldSeq == 64)
        {
            field = new DeductionField(this, DEDUCTION_ID1, 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 65)
        {
            field = new PeriodTypeField(this, FREQUENCY_1, 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 66)
        {
            field = new CurrencyField(this, AMOUNT_1, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 67)
        {
            field = new DeductionField(this, DEDUCTION_ID2, 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 68)
        {
            field = new PeriodTypeField(this, FREQUENCY_2, 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 69)
        {
            field = new CurrencyField(this, AMOUNT_2, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 70)
        {
            field = new DeductionField(this, DEDUCTION_ID3, 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 71)
        {
            field = new PeriodTypeField(this, FREQUENCY_3, 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 72)
        {
            field = new CurrencyField(this, AMOUNT_3, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 73)
        {
            field = new DeductionField(this, DEDUCTION_ID4, 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 74)
        {
            field = new PeriodTypeField(this, FREQUENCY_4, 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 75)
        {
            field = new CurrencyField(this, AMOUNT_4, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 76)
            field = new DateField(this, LAST_RAISE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 77)
            field = new CurrencyField(this, PREVIOUS_SALARY, 9, null, null);
        if (iFieldSeq == 78)
            field = new CurrencyField(this, PREVIOUS_PAY_RATE, 5, null, null);
        if (iFieldSeq == 79)
            field = new DateField(this, REVIEW_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 80)
            field = new DateField(this, ANNIVERSARY_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 81)
            field = new DateField(this, TERMINATION_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 82)
            field = new CurrencyField(this, YTD_SUI_PAID, 7, null, null);
        if (iFieldSeq == 83)
            field = new CurrencyField(this, YTD_FUI_PAID, 7, null, null);
        if (iFieldSeq == 84)
            field = new BooleanField(this, STATUS, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "FirstName");
            keyArea.addKeyField(FIRST_NAME, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PostalCode");
            keyArea.addKeyField(POSTAL_CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NameSort");
            keyArea.addKeyField(NAME_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DepartmentID");
            keyArea.addKeyField(DEPARTMENT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(NAME_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "UserID");
            keyArea.addKeyField(USER_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TERMINATION_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        CopyLastHandler copyLast = new CopyLastHandler(Employee.NAME_SORT);   // This should be somewhere else!
        this.getField(Employee.NAME).addListener(copyLast);
            
        CopyFieldHandler copyFirst = new CopyFieldHandler(Employee.FIRST_NAME);
        this.getField(Employee.NAME).addListener(copyFirst);
        FieldToUpperHandler upper = new FieldToUpperHandler(null);
        this.getField(Employee.FIRST_NAME).addListener(upper);
        CopyFieldHandler copyState = new CopyFieldHandler(Employee.STATE_TAX_CODE);
        this.getField(Employee.STATE_OR_REGION).addListener(copyState);
        FileListener disableKey = new EnableOnValidHandler(Employee.ID, EnableOnValidHandler.DISABLE_ON_VALID, EnableOnValidHandler.ENABLE_ON_NEW);
        this.addListener(disableKey);
        FieldListener calcOvertime = new CalcOvertimeHandler(this.getField(Employee.OVERTIME_RATE));
        this.getField(Employee.HOURLY_RATE).addListener(calcOvertime);
        FieldListener calcHourly = new CalcHourlyHandler(this.getField(Employee.HOURLY_RATE));
        this.getField(Employee.SALARY).addListener(calcHourly);
    }
    /**
     * GetCurrentUser Method.
     */
    public int getCurrentUser()
    {
        this.setKeyArea(Employee.ID_KEY);
        this.getField(Employee.ID).setString(((MainApplication)this.getRecordOwner().getTask().getApplication()).getUserID());  // Set to current User Name   
        this.getField(Employee.TERMINATION_DATE).setString("");
        int errorCode = DBConstants.ERROR_RETURN;
        try   {
            boolean bSuccess = this.seek(null);
            if (!bSuccess)
                return DBConstants.ERROR_RETURN;
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return errorCode;
    }

}
