
package com.tourgeek.tour.payroll.db;

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
import com.tourgeek.model.tour.payroll.db.*;

/**
 *  EmployeeStatus - Sub-Selection of Employee.
 */
public class EmployeeStatus extends Employee
     implements EmployeeStatusModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public EmployeeStatus()
    {
        super();
    }
    /**
     * Constructor.
     */
    public EmployeeStatus(RecordOwner screen)
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

    public static final String EMPLOYEE_STATUS_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, 8, null, null);
        //  field.setHidden(true);
        //}
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
        //if (iFieldSeq == 4)
        //  field = new StringField(this, NAME, 30, null, null);
        //if (iFieldSeq == 5)
        //  field = new StringField(this, ADDRESS_LINE_1, 40, null, null);
        //if (iFieldSeq == 6)
        //  field = new StringField(this, ADDRESS_LINE_2, 40, null, null);
        //if (iFieldSeq == 7)
        //  field = new StringField(this, CITY_OR_TOWN, 15, null, null);
        //if (iFieldSeq == 8)
        //  field = new StringField(this, STATE_OR_REGION, 15, null, "CA");
        //if (iFieldSeq == 9)
        //  field = new StringField(this, POSTAL_CODE, 10, null, null);
        //if (iFieldSeq == 10)
        //  field = new StringField(this, COUNTRY, 15, null, null);
        //if (iFieldSeq == 11)
        //  field = new PhoneField(this, HOME_PHONE, 24, null, null);
        //if (iFieldSeq == 12)
        //  field = new FaxField(this, FAX, 24, null, null);
        //if (iFieldSeq == 13)
        //  field = new EMailField(this, EMAIL, 40, null, null);
        //if (iFieldSeq == 14)
        //  field = new URLField(this, WEB, 60, null, null);
        //if (iFieldSeq == 15)
        //{
        //  field = new EmployeeStatus_HireDate(this, HIRE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 16)
        //  field = new DateField(this, DATE_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 17)
        //  field = new ReferenceField(this, CHANGED_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 18)
        //  field = new MemoField(this, COMMENTS, 9999, null, null);
        //if (iFieldSeq == 19)
        //  field = new UserField(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 20)
        //  field = new StringField(this, PASSWORD, 16, null, null);
        //if (iFieldSeq == 21)
        //  field = new StringField(this, NAME_SORT, 6, null, null);
        //if (iFieldSeq == 22)
        //  field = new StringField(this, POSTAL_CODE_SORT, 5, null, null);
        //if (iFieldSeq == 23)
        //  field = new StringField(this, FIRST_NAME, 20, null, null);
        //if (iFieldSeq == 24)
        //  field = new StringField(this, TITLE, 30, null, null);
        //if (iFieldSeq == 25)
        //  field = new DateField(this, BIRTHDATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 26)
        //  field = new StringField(this, EXTENSION, 4, null, null);
        //if (iFieldSeq == 27)
        //  field = new ImageField(this, PHOTO, 9999, null, null);
        //if (iFieldSeq == 28)
        //  field = new EmployeeField(this, REPORTS_TO, 8, null, null);
        //if (iFieldSeq == 29)
        //{
        //  field = new PeriodTypeField(this, PAY_FREQUENCY, 1, null, "PeriodTypeField.WEEKLY");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 30)
        //  field = new PayTypeField(this, PAY_TYPE, 1, null, "H");
        //if (iFieldSeq == 31)
        //  field = new CurrencyField(this, SALARY, 9, null, null);
        //if (iFieldSeq == 32)
        //  field = new CurrencyField(this, HOURLY_RATE, 5, null, null);
        //if (iFieldSeq == 33)
        //  field = new CurrencyField(this, OVERTIME_RATE, 5, null, null);
        //if (iFieldSeq == 34)
        //  field = new CurrencyField(this, SPECIAL_1_RATE, 5, null, null);
        //if (iFieldSeq == 35)
        //  field = new CurrencyField(this, SPECIAL_2_RATE, 5, null, null);
        //if (iFieldSeq == 36)
        //{
        //  field = new StringField(this, TAX_ID_NO, 11, null, null);
        //  field.setMinimumLength(11);
        //}
        //if (iFieldSeq == 37)
        //  field = new MaritalStatusField(this, MARITAL_STATUS, 1, null, null);
        //if (iFieldSeq == 38)
        //  field = new ShortField(this, FED_ALLOW, 2, null, new Short((short)2));
        //if (iFieldSeq == 39)
        //  field = new FloatField(this, ADD_DEDUCT, 8, null, null);
        //if (iFieldSeq == 40)
        //  field = new BooleanField(this, FED_EXEMPT, 1, null, null);
        //if (iFieldSeq == 41)
        //  field = new StringField(this, STATE_TAX_CODE, 2, null, null);
        //if (iFieldSeq == 42)
        //  field = new ShortField(this, STATE_ALLOW, 2, null, null);
        //if (iFieldSeq == 43)
        //  field = new FloatField(this, ADD_STATE, 8, null, null);
        //if (iFieldSeq == 44)
        //  field = new BooleanField(this, STATE_EXEMPT, 1, null, null);
        //if (iFieldSeq == 45)
        //  field = new StringField(this, LOCAL_CODE, 2, null, null);
        //if (iFieldSeq == 46)
        //  field = new ShortField(this, LOCAL_ALLOW, 2, null, null);
        //if (iFieldSeq == 47)
        //{
        //  field = new FloatField(this, ADD_LOCAL, 8, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 48)
        //  field = new BooleanField(this, LOCAL_EXEMPT, 1, null, null);
        //if (iFieldSeq == 49)
        //  field = new BooleanField(this, FICA_EXEMPT, 1, null, null);
        //if (iFieldSeq == 50)
        //  field = new BooleanField(this, FUI_EXEMPT, 1, null, null);
        //if (iFieldSeq == 51)
        //  field = new BooleanField(this, SUI_EXEMPT, 1, null, null);
        //if (iFieldSeq == 52)
        //  field = new BooleanField(this, SDI_EXEMPT, 1, null, null);
        //if (iFieldSeq == 53)
        //  field = new DepartmentField(this, DEPARTMENT_ID, 3, null, null);
        //if (iFieldSeq == 54)
        //  field = new AccountField(this, HOME_ACCOUNT_ID, 7, null, null);
        //if (iFieldSeq == 55)
        //  field = new BooleanField(this, IN_PENSION_PLAN, 1, null, new Boolean(true));
        //if (iFieldSeq == 56)
        //  field = new StringField(this, EF_TACCOUNT, 30, null, null);
        //if (iFieldSeq == 57)
        //  field = new BooleanField(this, DIST_PAY, 1, null, null);
        //if (iFieldSeq == 58)
        //  field = new FloatField(this, SICK_DUE, 3, null, null);
        //if (iFieldSeq == 59)
        //  field = new FloatField(this, SICK_TAKEN, 3, null, null);
        //if (iFieldSeq == 60)
        //  field = new FloatField(this, VACATION_DUE, 3, null, null);
        //if (iFieldSeq == 61)
        //  field = new FloatField(this, VACATION_TAKEN, 3, null, null);
        //if (iFieldSeq == 62)
        //  field = new FloatField(this, YTD_SICK_HOURS, 10, null, null);
        //if (iFieldSeq == 63)
        //  field = new CurrencyField(this, YTD_SICK_PAY, 10, null, null);
        //if (iFieldSeq == 64)
        //{
        //  field = new DeductionField(this, DEDUCTION_ID1, 3, null, "");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 65)
        //{
        //  field = new PeriodTypeField(this, FREQUENCY_1, 1, null, "PeriodTypeField.WEEKLY");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 66)
        //{
        //  field = new CurrencyField(this, AMOUNT_1, 7, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 67)
        //{
        //  field = new DeductionField(this, DEDUCTION_ID2, 3, null, "");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 68)
        //{
        //  field = new PeriodTypeField(this, FREQUENCY_2, 1, null, "PeriodTypeField.WEEKLY");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 69)
        //{
        //  field = new CurrencyField(this, AMOUNT_2, 7, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 70)
        //{
        //  field = new DeductionField(this, DEDUCTION_ID3, 3, null, "");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 71)
        //{
        //  field = new PeriodTypeField(this, FREQUENCY_3, 1, null, "PeriodTypeField.WEEKLY");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 72)
        //{
        //  field = new CurrencyField(this, AMOUNT_3, 7, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 73)
        //{
        //  field = new DeductionField(this, DEDUCTION_ID4, 3, null, "");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 74)
        //{
        //  field = new PeriodTypeField(this, FREQUENCY_4, 1, null, "PeriodTypeField.WEEKLY");
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 75)
        //{
        //  field = new CurrencyField(this, AMOUNT_4, 7, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 76)
        //  field = new DateField(this, LAST_RAISE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 77)
        //  field = new CurrencyField(this, PREVIOUS_SALARY, 9, null, null);
        //if (iFieldSeq == 78)
        //  field = new CurrencyField(this, PREVIOUS_PAY_RATE, 5, null, null);
        //if (iFieldSeq == 79)
        //  field = new DateField(this, REVIEW_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 80)
        //  field = new DateField(this, ANNIVERSARY_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 81)
        //  field = new DateField(this, TERMINATION_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 82)
        //  field = new CurrencyField(this, YTD_SUI_PAID, 7, null, null);
        //if (iFieldSeq == 83)
        //  field = new CurrencyField(this, YTD_FUI_PAID, 7, null, null);
        //if (iFieldSeq == 84)
        //  field = new BooleanField(this, STATUS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Employee.ID).setSelected(true);
        this.getField(Employee.NAME_SORT).setSelected(true);
        this.getField(Employee.NAME).setSelected(true);
        this.getField(Employee.HIRE_DATE).setSelected(true);
        this.getField(Employee.DATE_CHANGED).setSelected(true);
        this.getField(Employee.POSTAL_CODE_SORT).setSelected(true);
        this.getField(Employee.FIRST_NAME).setSelected(true);
        this.getField(Employee.DEDUCTION_ID1).setSelected(true);
    }

}
