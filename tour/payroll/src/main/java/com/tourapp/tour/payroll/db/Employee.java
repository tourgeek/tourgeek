/**
 * @(#)Employee.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.payroll.screen.emp.*;
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

    //public static final int kID = kID;
    //public static final int kUserID = kUserID;
    //public static final int kNameSort = kNameSort;
    //public static final int kAddressLine2 = kAddressLine2;
    //public static final int kName = kName;
    public static final int kHireDate = kDateEntered;
    //public static final int kDateChanged = kDateChanged;
    //public static final int kChangedID = kChangedID;
    //public static final int kAddressLine1 = kAddressLine1;
    //public static final int kCityOrTown = kCityOrTown;
    //public static final int kStateOrRegion = kStateOrRegion;
    //public static final int kPostalCode = kPostalCode;
    //public static final int kCountry = kCountry;
    public static final int kHomePhone = kTel;
    //public static final int kEmail = kEmail;
    //public static final int kWeb = kWeb;
    //public static final int kComments = kComments;
    //public static final int kPassword = kPassword;
    //public static final int kPostalCodeSort = kPostalCodeSort;
    public static final int kFirstName = kPersonLastField + 1;
    public static final int kTitle = kFirstName + 1;
    public static final int kBirthdate = kTitle + 1;
    public static final int kExtension = kBirthdate + 1;
    public static final int kPhoto = kExtension + 1;
    public static final int kReportsTo = kPhoto + 1;
    public static final int kPayFrequency = kReportsTo + 1;
    public static final int kPayType = kPayFrequency + 1;
    public static final int kSalary = kPayType + 1;
    public static final int kHourlyRate = kSalary + 1;
    public static final int kOvertimeRate = kHourlyRate + 1;
    public static final int kSpecial1Rate = kOvertimeRate + 1;
    public static final int kSpecial2Rate = kSpecial1Rate + 1;
    public static final int kTaxIdNo = kSpecial2Rate + 1;
    public static final int kMaritalStatus = kTaxIdNo + 1;
    public static final int kFedAllow = kMaritalStatus + 1;
    public static final int kAddDeduct = kFedAllow + 1;
    public static final int kFedExempt = kAddDeduct + 1;
    public static final int kStateTaxCode = kFedExempt + 1;
    public static final int kStateAllow = kStateTaxCode + 1;
    public static final int kAddState = kStateAllow + 1;
    public static final int kStateExempt = kAddState + 1;
    public static final int kLocalCode = kStateExempt + 1;
    public static final int kLocalAllow = kLocalCode + 1;
    public static final int kAddLocal = kLocalAllow + 1;
    public static final int kLocalExempt = kAddLocal + 1;
    public static final int kFicaExempt = kLocalExempt + 1;
    public static final int kFuiExempt = kFicaExempt + 1;
    public static final int kSuiExempt = kFuiExempt + 1;
    public static final int kSdiExempt = kSuiExempt + 1;
    public static final int kDepartmentID = kSdiExempt + 1;
    public static final int kHomeAccountID = kDepartmentID + 1;
    public static final int kInPensionPlan = kHomeAccountID + 1;
    public static final int kEFTaccount = kInPensionPlan + 1;
    public static final int kDistPay = kEFTaccount + 1;
    public static final int kSickDue = kDistPay + 1;
    public static final int kSickTaken = kSickDue + 1;
    public static final int kVacationDue = kSickTaken + 1;
    public static final int kVacationTaken = kVacationDue + 1;
    public static final int kYTDSickHours = kVacationTaken + 1;
    public static final int kYTDSickPay = kYTDSickHours + 1;
    public static final int kDeductionID1 = kYTDSickPay + 1;
    public static final int kFrequency1 = kDeductionID1 + 1;
    public static final int kAmount1 = kFrequency1 + 1;
    public static final int kDeductionID2 = kAmount1 + 1;
    public static final int kFrequency2 = kDeductionID2 + 1;
    public static final int kAmount2 = kFrequency2 + 1;
    public static final int kDeductionID3 = kAmount2 + 1;
    public static final int kFrequency3 = kDeductionID3 + 1;
    public static final int kAmount3 = kFrequency3 + 1;
    public static final int kDeductionID4 = kAmount3 + 1;
    public static final int kFrequency4 = kDeductionID4 + 1;
    public static final int kAmount4 = kFrequency4 + 1;
    public static final int kLastRaiseDate = kAmount4 + 1;
    public static final int kPreviousSalary = kLastRaiseDate + 1;
    public static final int kPreviousPayRate = kPreviousSalary + 1;
    public static final int kReviewDate = kPreviousPayRate + 1;
    public static final int kAnniversaryDate = kReviewDate + 1;
    public static final int kTerminationDate = kAnniversaryDate + 1;
    public static final int kYtdSuiPaid = kTerminationDate + 1;
    public static final int kYtdFuiPaid = kYtdSuiPaid + 1;
    public static final int kStatus = kYtdFuiPaid + 1;
    public static final int kEmployeeLastField = kStatus;
    public static final int kEmployeeFields = kStatus - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kFirstNameKey = kIDKey + 1;
    public static final int kPostalCodeKey = kFirstNameKey + 1;
    public static final int kNameSortKey = kPostalCodeKey + 1;
    public static final int kDepartmentIDKey = kNameSortKey + 1;
    public static final int kUserIDKey = kDepartmentIDKey + 1;
    public static final int kEmployeeLastKey = kUserIDKey;
    public static final int kEmployeeKeys = kUserIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kEmployeeFile = "Employee";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kEmployeeFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kUserID)
            field = new UserField(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFirstName)
            field = new StringField(this, "FirstName", 20, null, null);
        if (iFieldSeq == kNameSort)
            field = new StringField(this, "NameSort", 6, null, null);
        //if (iFieldSeq == kAddressLine2)
        //  field = new StringField(this, "AddressLine2", 40, null, null);
        if (iFieldSeq == kName)
            field = new StringField(this, "Name", 30, null, null);
        if (iFieldSeq == kTitle)
            field = new StringField(this, "Title", 30, null, null);
        if (iFieldSeq == kBirthdate)
            field = new DateField(this, "Birthdate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kHireDate)
        {
            field = new Employee_HireDate(this, "HireDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kDateChanged)
        //  field = new DateField(this, "DateChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kChangedID)
        //  field = new ReferenceField(this, "ChangedID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAddressLine1)
        //  field = new StringField(this, "AddressLine1", 40, null, null);
        //if (iFieldSeq == kCityOrTown)
        //  field = new StringField(this, "CityOrTown", 15, null, null);
        if (iFieldSeq == kStateOrRegion)
            field = new StringField(this, "StateOrRegion", 15, null, "CA");
        //if (iFieldSeq == kPostalCode)
        //  field = new StringField(this, "PostalCode", 10, null, null);
        //if (iFieldSeq == kCountry)
        //  field = new StringField(this, "Country", 15, null, null);
        if (iFieldSeq == kHomePhone)
            field = new PhoneField(this, "HomePhone", 24, null, null);
        if (iFieldSeq == kExtension)
            field = new StringField(this, "Extension", 4, null, null);
        //if (iFieldSeq == kEmail)
        //  field = new EMailField(this, "Email", 40, null, null);
        //if (iFieldSeq == kWeb)
        //  field = new URLField(this, "Web", 60, null, null);
        if (iFieldSeq == kPhoto)
            field = new ImageField(this, "Photo", 9999, null, null);
        if (iFieldSeq == kComments)
            field = new MemoField(this, "Comments", 9999, null, null);
        if (iFieldSeq == kReportsTo)
            field = new EmployeeField(this, "ReportsTo", 8, null, null);
        if (iFieldSeq == kPayFrequency)
        {
            field = new PeriodTypeField(this, "PayFrequency", 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPayType)
            field = new PayTypeField(this, "PayType", 1, null, "H");
        if (iFieldSeq == kSalary)
            field = new CurrencyField(this, "Salary", 9, null, null);
        if (iFieldSeq == kHourlyRate)
            field = new CurrencyField(this, "HourlyRate", 5, null, null);
        if (iFieldSeq == kOvertimeRate)
            field = new CurrencyField(this, "OvertimeRate", 5, null, null);
        if (iFieldSeq == kSpecial1Rate)
            field = new CurrencyField(this, "Special1Rate", 5, null, null);
        if (iFieldSeq == kSpecial2Rate)
            field = new CurrencyField(this, "Special2Rate", 5, null, null);
        if (iFieldSeq == kTaxIdNo)
        {
            field = new StringField(this, "TaxIdNo", 11, null, null);
            field.setMinimumLength(11);
        }
        if (iFieldSeq == kMaritalStatus)
            field = new MaritalStatusField(this, "MaritalStatus", 1, null, null);
        if (iFieldSeq == kFedAllow)
            field = new ShortField(this, "FedAllow", 2, null, new Short((short)2));
        if (iFieldSeq == kAddDeduct)
            field = new FloatField(this, "AddDeduct", 8, null, null);
        if (iFieldSeq == kFedExempt)
            field = new BooleanField(this, "FedExempt", 1, null, null);
        if (iFieldSeq == kStateTaxCode)
            field = new StringField(this, "StateTaxCode", 2, null, null);
        if (iFieldSeq == kStateAllow)
            field = new ShortField(this, "StateAllow", 2, null, null);
        if (iFieldSeq == kAddState)
            field = new FloatField(this, "AddState", 8, null, null);
        if (iFieldSeq == kStateExempt)
            field = new BooleanField(this, "StateExempt", 1, null, null);
        if (iFieldSeq == kLocalCode)
            field = new StringField(this, "LocalCode", 2, null, null);
        if (iFieldSeq == kLocalAllow)
            field = new ShortField(this, "LocalAllow", 2, null, null);
        if (iFieldSeq == kAddLocal)
        {
            field = new FloatField(this, "AddLocal", 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLocalExempt)
            field = new BooleanField(this, "LocalExempt", 1, null, null);
        if (iFieldSeq == kFicaExempt)
            field = new BooleanField(this, "FicaExempt", 1, null, null);
        if (iFieldSeq == kFuiExempt)
            field = new BooleanField(this, "FuiExempt", 1, null, null);
        if (iFieldSeq == kSuiExempt)
            field = new BooleanField(this, "SuiExempt", 1, null, null);
        if (iFieldSeq == kSdiExempt)
            field = new BooleanField(this, "SdiExempt", 1, null, null);
        if (iFieldSeq == kDepartmentID)
            field = new DepartmentField(this, "DepartmentID", 3, null, null);
        if (iFieldSeq == kHomeAccountID)
            field = new AccountField(this, "HomeAccountID", 7, null, null);
        if (iFieldSeq == kInPensionPlan)
            field = new BooleanField(this, "InPensionPlan", 1, null, new Boolean(true));
        if (iFieldSeq == kEFTaccount)
            field = new StringField(this, "EFTaccount", 30, null, null);
        if (iFieldSeq == kDistPay)
            field = new BooleanField(this, "DistPay", 1, null, null);
        if (iFieldSeq == kSickDue)
            field = new FloatField(this, "SickDue", 3, null, null);
        if (iFieldSeq == kSickTaken)
            field = new FloatField(this, "SickTaken", 3, null, null);
        if (iFieldSeq == kVacationDue)
            field = new FloatField(this, "VacationDue", 3, null, null);
        if (iFieldSeq == kVacationTaken)
            field = new FloatField(this, "VacationTaken", 3, null, null);
        if (iFieldSeq == kYTDSickHours)
            field = new FloatField(this, "YTDSickHours", 10, null, null);
        if (iFieldSeq == kYTDSickPay)
            field = new CurrencyField(this, "YTDSickPay", 10, null, null);
        if (iFieldSeq == kDeductionID1)
        {
            field = new DeductionField(this, "DeductionID1", 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFrequency1)
        {
            field = new PeriodTypeField(this, "Frequency1", 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAmount1)
        {
            field = new CurrencyField(this, "Amount1", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDeductionID2)
        {
            field = new DeductionField(this, "DeductionID2", 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFrequency2)
        {
            field = new PeriodTypeField(this, "Frequency2", 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAmount2)
        {
            field = new CurrencyField(this, "Amount2", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDeductionID3)
        {
            field = new DeductionField(this, "DeductionID3", 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFrequency3)
        {
            field = new PeriodTypeField(this, "Frequency3", 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAmount3)
        {
            field = new CurrencyField(this, "Amount3", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDeductionID4)
        {
            field = new DeductionField(this, "DeductionID4", 3, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFrequency4)
        {
            field = new PeriodTypeField(this, "Frequency4", 1, null, "PeriodTypeField.WEEKLY");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAmount4)
        {
            field = new CurrencyField(this, "Amount4", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLastRaiseDate)
            field = new DateField(this, "LastRaiseDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPreviousSalary)
            field = new CurrencyField(this, "PreviousSalary", 9, null, null);
        if (iFieldSeq == kPreviousPayRate)
            field = new CurrencyField(this, "PreviousPayRate", 5, null, null);
        if (iFieldSeq == kReviewDate)
            field = new DateField(this, "ReviewDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAnniversaryDate)
            field = new DateField(this, "AnniversaryDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTerminationDate)
            field = new DateField(this, "TerminationDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kYtdSuiPaid)
            field = new CurrencyField(this, "YtdSuiPaid", 7, null, null);
        if (iFieldSeq == kYtdFuiPaid)
            field = new CurrencyField(this, "YtdFuiPaid", 7, null, null);
        //if (iFieldSeq == kPassword)
        //  field = new StringField(this, "Password", 16, null, null);
        if (iFieldSeq == kStatus)
            field = new BooleanField(this, "Status", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPostalCodeSort)
        //  field = new StringField(this, "PostalCodeSort", 5, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kEmployeeLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kFirstNameKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "FirstName");
            keyArea.addKeyField(kFirstName, DBConstants.ASCENDING);
        }
        if (iKeyArea == kPostalCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PostalCode");
            keyArea.addKeyField(kPostalCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kNameSortKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NameSort");
            keyArea.addKeyField(kNameSort, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDepartmentIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DepartmentID");
            keyArea.addKeyField(kDepartmentID, DBConstants.ASCENDING);
            keyArea.addKeyField(kNameSort, DBConstants.ASCENDING);
        }
        if (iKeyArea == kUserIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "UserID");
            keyArea.addKeyField(kUserID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTerminationDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kEmployeeLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kEmployeeLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        CopyLastHandler copyLast = new CopyLastHandler(kNameSort);  // This should be somewhere else!
        this.getField(kName).addListener(copyLast);
            
        CopyFieldHandler copyFirst = new CopyFieldHandler(kFirstName);
        this.getField(kName).addListener(copyFirst);
        FieldToUpperHandler upper = new FieldToUpperHandler(null);
        this.getField(kFirstName).addListener(upper);
        CopyFieldHandler copyState = new CopyFieldHandler(kStateTaxCode);
        this.getField(kStateOrRegion).addListener(copyState);
        FileListener disableKey = new EnableOnValidHandler(Employee.kID, EnableOnValidHandler.DISABLE_ON_VALID, EnableOnValidHandler.ENABLE_ON_NEW);
        this.addListener(disableKey);
        FieldListener calcOvertime = new CalcOvertimeHandler(this.getField(Employee.kOvertimeRate));
        this.getField(kHourlyRate).addListener(calcOvertime);
        FieldListener calcHourly = new CalcHourlyHandler(this.getField(Employee.kHourlyRate));
        this.getField(kSalary).addListener(calcHourly);
    }
    /**
     * GetCurrentUser Method.
     */
    public int getCurrentUser()
    {
        this.setKeyArea(Employee.kIDKey);
        this.getField(Employee.kID).setString(((MainApplication)this.getRecordOwner().getTask().getApplication()).getUserID());   // Set to current User Name 
        this.getField(kTerminationDate).setString("");
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
