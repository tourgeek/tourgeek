/**
 *  @(#)EmployeeControl.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.payroll.screen.misc.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  EmployeeControl - Employee Control File.
 */
public class EmployeeControl extends ControlRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kFederalIdNo = kControlRecordLastField + 1;
    public static final int kFedExemption = kFederalIdNo + 1;
    public static final int kFedTaxAcctID = kFedExemption + 1;
    public static final int kFedTaxDesc = kFedTaxAcctID + 1;
    public static final int kStateIdNo = kFedTaxDesc + 1;
    public static final int kStateExemption = kStateIdNo + 1;
    public static final int kStateTaxAcctID = kStateExemption + 1;
    public static final int kStateTaxDesc = kStateTaxAcctID + 1;
    public static final int kLocalIdNo = kStateTaxDesc + 1;
    public static final int kLocalExemption = kLocalIdNo + 1;
    public static final int kLocalTaxAcctID = kLocalExemption + 1;
    public static final int kLocalTaxDesc = kLocalTaxAcctID + 1;
    public static final int kFicaTaxDesc = kLocalTaxDesc + 1;
    public static final int kFICAEmployee = kFicaTaxDesc + 1;
    public static final int kFICAEmployer = kFICAEmployee + 1;
    public static final int kFICATaxAcctID = kFICAEmployer + 1;
    public static final int kMaxFICA = kFICATaxAcctID + 1;
    public static final int kMedicareTaxDesc = kMaxFICA + 1;
    public static final int kMedicareEmployee = kMedicareTaxDesc + 1;
    public static final int kMaxMedicare = kMedicareEmployee + 1;
    public static final int kMaxEmployerFICA = kMaxMedicare + 1;
    public static final int kMedicareEmployer = kMaxEmployerFICA + 1;
    public static final int kMaxEmployerMedicare = kMedicareEmployer + 1;
    public static final int kFuiPer = kMaxEmployerMedicare + 1;
    public static final int kFuiMax = kFuiPer + 1;
    public static final int kFUITaxAcctID = kFuiMax + 1;
    public static final int kCashBankAcctID = kFUITaxAcctID + 1;
    public static final int kTaxAcctID = kCashBankAcctID + 1;
    public static final int kDefaultState = kTaxAcctID + 1;
    public static final int kSuiPer = kDefaultState + 1;
    public static final int kSuiMax = kSuiPer + 1;
    public static final int kSUITaxAcctID = kSuiMax + 1;
    public static final int kSdiTaxDesc = kSUITaxAcctID + 1;
    public static final int kSdiPer = kSdiTaxDesc + 1;
    public static final int kSdiMax = kSdiPer + 1;
    public static final int kSDITaxAcctID = kSdiMax + 1;
    public static final int kLastBiWeekly = kSDITaxAcctID + 1;
    public static final int kDistToGl = kLastBiWeekly + 1;
    public static final int kDistToJobs = kDistToGl + 1;
    public static final int kRegularPayDesc = kDistToJobs + 1;
    public static final int kOvertimePayDesc = kRegularPayDesc + 1;
    public static final int kOtTimesBase = kOvertimePayDesc + 1;
    public static final int kSp1Desc = kOtTimesBase + 1;
    public static final int kSp1TimesBase = kSp1Desc + 1;
    public static final int kSp2Desc = kSp1TimesBase + 1;
    public static final int kSp2TimesBase = kSp2Desc + 1;
    public static final int kEmployeeControlLastField = kSp2TimesBase;
    public static final int kEmployeeControlFields = kSp2TimesBase - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kEmployeeControlLastKey = kIDKey;
    public static final int kEmployeeControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kEmployeeControlFile = "EmployeeControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kEmployeeControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new EmployeeControlScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new EmployeeControlScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
            field = new CounterField(this, "ID", 4, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kFederalIdNo)
            field = new StringField(this, "FederalIdNo", 11, null, null);
        if (iFieldSeq == kFedExemption)
            field = new CurrencyField(this, "FedExemption", 7, null, null);
        if (iFieldSeq == kFedTaxAcctID)
        {
            field = new AccountField(this, "FedTaxAcctID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kFedTaxDesc)
            field = new StringField(this, "FedTaxDesc", 15, null, null);
        if (iFieldSeq == kStateIdNo)
            field = new StringField(this, "StateIdNo", 11, null, null);
        if (iFieldSeq == kStateExemption)
            field = new CurrencyField(this, "StateExemption", 7, null, null);
        if (iFieldSeq == kStateTaxAcctID)
        {
            field = new AccountField(this, "StateTaxAcctID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kStateTaxDesc)
            field = new StringField(this, "StateTaxDesc", 15, null, null);
        if (iFieldSeq == kLocalIdNo)
            field = new StringField(this, "LocalIdNo", 11, null, null);
        if (iFieldSeq == kLocalExemption)
            field = new CurrencyField(this, "LocalExemption", 7, null, null);
        if (iFieldSeq == kLocalTaxAcctID)
        {
            field = new AccountField(this, "LocalTaxAcctID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kLocalTaxDesc)
            field = new StringField(this, "LocalTaxDesc", 15, null, null);
        if (iFieldSeq == kFicaTaxDesc)
            field = new StringField(this, "FicaTaxDesc", 15, null, null);
        if (iFieldSeq == kFICAEmployee)
            field = new PercentField(this, "FICAEmployee", 5, null, null);
        if (iFieldSeq == kFICAEmployer)
            field = new PercentField(this, "FICAEmployer", 5, null, null);
        if (iFieldSeq == kFICATaxAcctID)
        {
            field = new AccountField(this, "FICATaxAcctID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kMaxFICA)
            field = new CurrencyField(this, "MaxFICA", 7, null, null);
        if (iFieldSeq == kMedicareTaxDesc)
            field = new StringField(this, "MedicareTaxDesc", 15, null, null);
        if (iFieldSeq == kMedicareEmployee)
            field = new PercentField(this, "MedicareEmployee", 4, null, null);
        if (iFieldSeq == kMaxMedicare)
            field = new CurrencyField(this, "MaxMedicare", 8, null, null);
        if (iFieldSeq == kMaxEmployerFICA)
            field = new CurrencyField(this, "MaxEmployerFICA", 8, null, null);
        if (iFieldSeq == kMedicareEmployer)
            field = new PercentField(this, "MedicareEmployer", 4, null, null);
        if (iFieldSeq == kMaxEmployerMedicare)
            field = new CurrencyField(this, "MaxEmployerMedicare", 8, null, null);
        if (iFieldSeq == kFuiPer)
            field = new PercentField(this, "FuiPer", 5, null, null);
        if (iFieldSeq == kFuiMax)
            field = new CurrencyField(this, "FuiMax", 6, null, null);
        if (iFieldSeq == kFUITaxAcctID)
        {
            field = new AccountField(this, "FUITaxAcctID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kCashBankAcctID)
            field = new BankAcctField(this, "CashBankAcctID", 2, null, null);
        if (iFieldSeq == kTaxAcctID)
        {
            field = new AccountField(this, "TaxAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kDefaultState)
            field = new StringField(this, "DefaultState", 10, null, null);
        if (iFieldSeq == kSuiPer)
            field = new PercentField(this, "SuiPer", 5, null, null);
        if (iFieldSeq == kSuiMax)
            field = new CurrencyField(this, "SuiMax", 6, null, null);
        if (iFieldSeq == kSUITaxAcctID)
        {
            field = new AccountField(this, "SUITaxAcctID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kSdiTaxDesc)
            field = new StringField(this, "SdiTaxDesc", 15, null, null);
        if (iFieldSeq == kSdiPer)
            field = new PercentField(this, "SdiPer", 5, null, null);
        if (iFieldSeq == kSdiMax)
            field = new CurrencyField(this, "SdiMax", 6, null, null);
        if (iFieldSeq == kSDITaxAcctID)
        {
            field = new AccountField(this, "SDITaxAcctID", 7, null, null);
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kLastBiWeekly)
            field = new BooleanField(this, "LastBiWeekly", 1, null, null);
        if (iFieldSeq == kDistToGl)
            field = new DistField(this, "DistToGl", 1, null, null);
        if (iFieldSeq == kDistToJobs)
            field = new DistField(this, "DistToJobs", 1, null, null);
        if (iFieldSeq == kRegularPayDesc)
            field = new StringField(this, "RegularPayDesc", 15, null, null);
        if (iFieldSeq == kOvertimePayDesc)
            field = new StringField(this, "OvertimePayDesc", 15, null, null);
        if (iFieldSeq == kOtTimesBase)
            field = new PercentField(this, "OtTimesBase", 4, null, null);
        if (iFieldSeq == kSp1Desc)
            field = new StringField(this, "Sp1Desc", 15, null, null);
        if (iFieldSeq == kSp1TimesBase)
            field = new PercentField(this, "Sp1TimesBase", 4, null, null);
        if (iFieldSeq == kSp2Desc)
            field = new StringField(this, "Sp2Desc", 15, null, null);
        if (iFieldSeq == kSp2TimesBase)
            field = new PercentField(this, "Sp2TimesBase", 4, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kEmployeeControlLastField)
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
        if (keyArea == null) if (iKeyArea < kEmployeeControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kEmployeeControlLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
