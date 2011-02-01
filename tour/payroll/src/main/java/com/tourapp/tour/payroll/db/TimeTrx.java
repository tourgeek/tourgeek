/**
 *  @(#)TimeTrx.
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
import com.tourapp.tour.payroll.report.payroll.*;

/**
 *  TimeTrx - Timecard entry.
 */
public class TimeTrx extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kPayEnding = kVirtualRecordLastField + 1;
    public static final int kTimeEmpNo = kPayEnding + 1;
    public static final int kPaySeq = kTimeEmpNo + 1;
    public static final int kPaySalary = kPaySeq + 1;
    public static final int kRegularHrs = kPaySalary + 1;
    public static final int kOvertimeHrs = kRegularHrs + 1;
    public static final int kSp1Hours = kOvertimeHrs + 1;
    public static final int kSp2Hours = kSp1Hours + 1;
    public static final int kTimeDe1 = kSp2Hours + 1;
    public static final int kTimeHrs1 = kTimeDe1 + 1;
    public static final int kTimeAmt1 = kTimeHrs1 + 1;
    public static final int kTimeDe2 = kTimeAmt1 + 1;
    public static final int kTimeHrs2 = kTimeDe2 + 1;
    public static final int kTimeAmt2 = kTimeHrs2 + 1;
    public static final int kTimeDe3 = kTimeAmt2 + 1;
    public static final int kTimeHrs3 = kTimeDe3 + 1;
    public static final int kTimeAmt3 = kTimeHrs3 + 1;
    public static final int kTimeDe4 = kTimeAmt3 + 1;
    public static final int kTimeHrs4 = kTimeDe4 + 1;
    public static final int kTimeAmt4 = kTimeHrs4 + 1;
    public static final int kRegularPay = kTimeAmt4 + 1;
    public static final int kOvertimePay = kRegularPay + 1;
    public static final int kSpecial1Pay = kOvertimePay + 1;
    public static final int kSpecial2Pay = kSpecial1Pay + 1;
    public static final int kGrossPay = kSpecial2Pay + 1;
    public static final int kStateGrossPay = kGrossPay + 1;
    public static final int kNonTaxPay = kStateGrossPay + 1;
    public static final int kFedTaxes = kNonTaxPay + 1;
    public static final int kStateTaxes = kFedTaxes + 1;
    public static final int kLocalTaxes = kStateTaxes + 1;
    public static final int kFicaTaxes = kLocalTaxes + 1;
    public static final int kSdiTaxes = kFicaTaxes + 1;
    public static final int kOtherDed = kSdiTaxes + 1;
    public static final int kNetPay = kOtherDed + 1;
    public static final int kPrCheckNum = kNetPay + 1;
    public static final int kWeeksWorked = kPrCheckNum + 1;
    public static final int kDedEarnHours = kWeeksWorked + 1;
    public static final int kDedEarnDesc = kDedEarnHours + 1;
    public static final int kDedEarnAmt = kDedEarnDesc + 1;
    public static final int kDedEarnYtd = kDedEarnAmt + 1;
    public static final int kPayGross = kDedEarnYtd + 1;
    public static final int kPayTaxes = kPayGross + 1;
    public static final int kTimeTrxLastField = kPayTaxes;
    public static final int kTimeTrxFields = kPayTaxes - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kPayEndingKey = kIDKey + 1;
    public static final int kTimeTrxLastKey = kPayEndingKey;
    public static final int kTimeTrxKeys = kPayEndingKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TimeTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TimeTrx(RecordOwner screen)
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

    public static final String kTimeTrxFile = "TimeTrx";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTimeTrxFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transaction";
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
            screen = new TimeTrxScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new TimeTrxGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kPayEnding)
        {
            field = new TimeTrx_PayEnding(this, "PayEnding", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeEmpNo)
        {
            field = new IntegerField(this, "TimeEmpNo", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPaySeq)
            field = new ShortField(this, "PaySeq", 1, null, null);
        if (iFieldSeq == kPaySalary)
            field = new BooleanField(this, "PaySalary", 1, null, null);
        if (iFieldSeq == kRegularHrs)
            field = new FloatField(this, "RegularHrs", 6, null, null);
        if (iFieldSeq == kOvertimeHrs)
        {
            field = new FloatField(this, "OvertimeHrs", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSp1Hours)
        {
            field = new FloatField(this, "Sp1Hours", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSp2Hours)
        {
            field = new FloatField(this, "Sp2Hours", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeDe1)
            field = new StringField(this, "TimeDe1", 3, null, null);
        if (iFieldSeq == kTimeHrs1)
        {
            field = new FloatField(this, "TimeHrs1", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeAmt1)
        {
            field = new FloatField(this, "TimeAmt1", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeDe2)
            field = new StringField(this, "TimeDe2", 3, null, null);
        if (iFieldSeq == kTimeHrs2)
        {
            field = new FloatField(this, "TimeHrs2", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeAmt2)
        {
            field = new FloatField(this, "TimeAmt2", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeDe3)
            field = new StringField(this, "TimeDe3", 3, null, null);
        if (iFieldSeq == kTimeHrs3)
        {
            field = new FloatField(this, "TimeHrs3", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeAmt3)
        {
            field = new FloatField(this, "TimeAmt3", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeDe4)
            field = new StringField(this, "TimeDe4", 3, null, null);
        if (iFieldSeq == kTimeHrs4)
        {
            field = new FloatField(this, "TimeHrs4", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTimeAmt4)
        {
            field = new FloatField(this, "TimeAmt4", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kRegularPay)
            field = new FloatField(this, "RegularPay", 8, null, null);
        if (iFieldSeq == kOvertimePay)
            field = new FloatField(this, "OvertimePay", 8, null, null);
        if (iFieldSeq == kSpecial1Pay)
            field = new FloatField(this, "Special1Pay", 8, null, null);
        if (iFieldSeq == kSpecial2Pay)
            field = new FloatField(this, "Special2Pay", 8, null, null);
        if (iFieldSeq == kGrossPay)
            field = new FloatField(this, "GrossPay", 8, null, null);
        if (iFieldSeq == kStateGrossPay)
            field = new FloatField(this, "StateGrossPay", 8, null, null);
        if (iFieldSeq == kNonTaxPay)
            field = new FloatField(this, "NonTaxPay", 8, null, null);
        if (iFieldSeq == kFedTaxes)
            field = new FloatField(this, "FedTaxes", 7, null, null);
        if (iFieldSeq == kStateTaxes)
            field = new FloatField(this, "StateTaxes", 7, null, null);
        if (iFieldSeq == kLocalTaxes)
            field = new FloatField(this, "LocalTaxes", 7, null, null);
        if (iFieldSeq == kFicaTaxes)
            field = new FloatField(this, "FicaTaxes", 7, null, null);
        if (iFieldSeq == kSdiTaxes)
            field = new FloatField(this, "SdiTaxes", 7, null, null);
        if (iFieldSeq == kOtherDed)
            field = new FloatField(this, "OtherDed", 7, null, null);
        if (iFieldSeq == kNetPay)
            field = new FloatField(this, "NetPay", 8, null, null);
        if (iFieldSeq == kPrCheckNum)
            field = new IntegerField(this, "PrCheckNum", 6, null, null);
        if (iFieldSeq == kWeeksWorked)
            field = new FloatField(this, "WeeksWorked", 6, null, null);
        if (iFieldSeq == kDedEarnHours)
        {
            field = new FloatField(this, "DedEarnHours", 6, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDedEarnDesc)
        {
            field = new StringField(this, "DedEarnDesc", 16, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDedEarnAmt)
        {
            field = new FloatField(this, "DedEarnAmt", 8, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDedEarnYtd)
        {
            field = new DoubleField(this, "DedEarnYtd", 10, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPayGross)
        {
            field = new FloatField(this, "PayGross", 8, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPayTaxes)
        {
            field = new FloatField(this, "PayTaxes", 8, null, null);
            field.setVirtual(true);
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTimeTrxLastField)
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
        if (iKeyArea == kPayEndingKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PayEnding");
            keyArea.addKeyField(kPayEnding, DBConstants.ASCENDING);
            keyArea.addKeyField(kTimeEmpNo, DBConstants.ASCENDING);
            keyArea.addKeyField(kPaySeq, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTimeTrxLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTimeTrxLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * CalcPay Method.
     */
    public void calcPay(Record queryInfo, Record employee, Record prDedEarn, Record prTaxRates, Record empControl)
    {
            double regularPay = 0;
        /* HACK
            if ((this.getField(kPaySalary).getState())  (employee.getField(kPayType) == "S"))
               regularPay = employee.getField(kSalary).getValue();
            else
                regularPay = employee.getField(kHourlyRate).getValue()  this.getField(kRegularHrs).getValue();
            this.getField(kRegularPay).setValue(regularPay);
            double overtimePay = employee.getField(kOvertimeRate).getValue()  this.getField(kOvertimeHrs).getValue();
            this.getField(kOvertimePay).setValue(overtimePay);
            double special1Pay = employee.getField(kSpecial1Rate).getValue()  this.getField(kSp1Hours).getValue();
            this.getField(kSpecial1Pay).setValue(special1Pay);
            double special2Pay = employee.getField(kSpecial2Rate).getValue()  this.getField(kSp2Hours).getValue();
            this.getField(kSpecial2Pay).setValue(special2Pay);
            this.getField(kGrossPay).setValue(regularPay + overtimePay + special1Pay + special2Pay);
        
        // Calculate earnings
        //?   String freqCodes = queryInfo.getField(kFreqCodes).getString();
            String freqCodes = "S";
            this.getField(kNonTaxPay).setValue(0);
            String deCodes = "ENISV";
            for (int index = 0; ; index++)
            {
                this.SetupDE(index, deCodes, freqCodes, employee, prDedEarn);
                if (this.getField(kDedEarnAmt) == "")
                    break;
                if ((prDedEarn.getField(kType) == "N") || (prDedEarn.getField(kType) == "I") || (prDedEarn.getField(kType) == "T"))
                    this.getField(kNonTaxPay).setValue(this.getField(kNonTaxPay).getValue() + this.getField(kDedEarnAmt).getValue());
                else
                    this.getField(kGrossPay).setValue(this.getField(kGrossPay).getValue() + this.getField(kDedEarnAmt).getValue());
            }
            this.getField(kSsGrossPay) = this.getField(kGrossPay);
            this.getField(kStateGrossPay) = this.getField(kGrossPay);
        // Calculate deductions
            this.getField(kOtherDed).setValue(0);
            deCodes = "DTR";
            for (index = 0; ; index++)
            {
                this.SetupDE(index, deCodes, freqCodes, employee, prDedEarn);
                if (this.getField(kDedEarnAmt) == "")
                    break;
                if (prDedEarn.getField(kType) == "D")
                    this.getField(kOtherDed).setValue(this.getField(kOtherDed).getValue() + this.getField(kDedEarnAmt).getValue());
                if (prDedEarn.getField(kType) == "T")
                {
                    this.getField(kGrossPay).setValue(this.getField(kGrossPay).getValue() - this.getField(kDedEarnAmt).getValue());
                    this.getField(kSsGrossPay).setValue(this.getField(kSsGrossPay).getValue() - this.getField(kDedEarnAmt).getValue());
                    this.getField(kStateGrossPay).setValue(this.getField(kStateGrossPay).getValue() - this.getField(kDedEarnAmt).getValue());
                }
                if (prDedEarn.getField(kType) == "R")
                {
                    this.getField(kGrossPay).setValue(this.getField(kGrossPay).getValue() - this.getField(kDedEarnAmt).getValue());
                    this.getField(kStateGrossPay).setValue(this.getField(kStateGrossPay).getValue() - this.getField(kDedEarnAmt).getValue());
                }
            }
        // Calculate taxes
            float multiplier = 52.14;
            if (employee.getField(kPayFrequency).getLength())
            switch (employee.getField(kPayFrequency).getString().GetAt(0))
            {
            case 'B':
                multiplier = 26.07;break;
            case 'S':
                multiplier = 24;break;
            case 'M':
                multiplier = 12;break;
            case 'Q':
                multiplier = 4;break;
            case 'A':
                multiplier = 1;break;
            case 'W':
            default:
                multiplier = 52.14;break;
            }
        
            double tax = 0;
            String taxCode = "FE";    // Federal Taxes
            double fedPay = this.getField(kGrossPay).getValue();
            fedPay = fedPay  multiplier - employee.getField(kFedExemption).getValue()  empControl.getField(kFedAllow).getValue();
            if (!employee.getField(kFedExempt).getState())
                tax = this.GetTax(taxCode, employee.getField(kMaritalStatus).getString(), fedPay, prTaxRates);
            tax = tax / multiplier;
            double addDeduct = employee.getField(kAddDeduct).getValue();
            if (addDeduct)
            {
                if (addDeduct < 1.00)
                    tax = tax + fedPay  addDeduct;
                else
                    tax = tax + addDeduct;
            }
            this.getField(kFedTaxes).setValue(tax);
        
            tax = 0;
            taxCode = employee.getField(kState).getString();
            double statePay = this.getField(kStateGrossPay).getValue();
            statePay = statePay    multiplier - employee.getField(kStateExemption).getValue()  empControl.getField(kStateAllow).getValue();
            if (!employee.getField(kStateExempt).getState())
                tax = this.GetTax(taxCode, employee.getField(kMaritalStatus).getString(), statePay, prTaxRates);
            tax = tax / multiplier;
            addDeduct = employee.getField(kAddState).getValue();
            if (addDeduct)
            {
                if (addDeduct < 1.00)
                    tax = tax + statePay  addDeduct;
                else
                    tax = tax + addDeduct;
            }
            this.getField(kStateTaxes).setValue(tax);
        
            tax = 0;
            taxCode = employee.getField(kLocalCode).getString();
            double localPay = this.getField(kGrossPay).getValue();
            localPay = localPay    multiplier - employee.getField(kLocalExemption).getValue()  empControl.getField(kLocalAllow).getValue();
            if (!employee.getField(kLocalExempt).getState())
                tax = this.GetTax(taxCode, employee.getField(kMaritalStatus).getString(), localPay, prTaxRates);
            tax = tax / multiplier;
            addDeduct = employee.getField(kAddLocal).getValue();
            if (addDeduct)
            {
                if (addDeduct < 1.00)
                    tax = tax + localPay  addDeduct;
                else
                    tax = tax + addDeduct;
            }
            this.getField(kLocalTaxes).setValue(tax);
        
            double ficaTaxes = this.getField(kSsGrossPay).getValue()  empControl.getField(kFICAEmployee).getValue() / 100;
            if (employee.getField(kFicaExempt).getState())
                ficaTaxes = 0;
            if (ficaTaxes + employee.getField(kYtdEmpFica).getValue() > empControl.getField(kMaxFICA).getValue())
                ficaTaxes = max(0, empControl.getField(kMaxFICA).getValue()   - employee.getField(kYtdEmpFica).getValue());
            this.getField(kFicaTaxes).setValue(ficaTaxes);
        
            double sdiTaxes = this.getField(kGrossPay).getValue()  empControl.getField(kSdiPer).getValue() / 100;
            if (employee.getField(kSdiExempt).getState())
                sdiTaxes = 0;
            if (sdiTaxes + employee.getField(kYtdSdiWh).getValue() > empControl.getField(kSdiMax).getValue())
                sdiTaxes = max(0, empControl.getField(kSdiMax).getValue() - employee.getField(kYtdSdiWh).getValue());
            this.getField(kSdiTaxes).setValue(sdiTaxes);
        
            this.getField(kNetPay).setValue(this.getField(kGrossPay).getValue()
                     + this.getField(kNonTaxPay).getValue()
                     - this.getField(kFedTaxes).getValue()
                     - this.getField(kStateTaxes).getValue()
                     - this.getField(kLocalTaxes).getValue()
                     - this.getField(kFicaTaxes).getValue()
                     - this.getField(kSdiTaxes).getValue()
                     - this.getField(kOtherDed).getValue());
        this.getField(kPayGross).setValue(this.getField(kGrossPay).getValue() + this.getField(kNonTaxPay).getValue());
        this.getField(kPayTaxes).setValue(this.getField(kFedTaxes).getValue()
            + this.getField(kStateTaxes).getValue()
            + this.getField(kLocalTaxes).getValue()
            + this.getField(kFicaTaxes).getValue()
            + this.getField(kSdiTaxes).getValue());
        */
    }
    /**
     * GetDeCode Method.
     */
    public void getDeCode(String deCode, Record prDedEarn)
    {
        /* HACK
            if (deCode.IsEmpty())
            {
                this.getField(kDedEarnDesc) = "";
                return;
            }
            prDedEarn.CancelRecord();
            prDedEarn.getField(kDeductionID) = deCode;
            int errorCode = prDedEarn.ReadRecord(kReadOnlyMode);
            if (errorCode == DBConstants.NORMAL_RETURN)
            {
                this.getField(kDedEarnDesc) = prDedEarn.getField(kDeShort);
                if (this.getField(kDedEarnDesc) == "")
                    this.getField(kDedEarnDesc) = prDedEarn.getField(kDeductionID);
            }
        */
    }
    /**
     * GetTax Method.
     */
    public double getTax(String taxCode, String maritalCode, double salaryAmount, Record prTaxRates)
    {
            double annualTax = 0;
        /* HACK
            if (taxCode.IsEmpty())
                return 0;
            StringSubFileFilter temp = new StringSubFileFilter(taxCode, maritalCode, kTaxCode, kMaritalStatus);
            prTaxRates.addListener(temp);
            prTaxRates.SelectRecords(kReadOnlyMode);
            double lastCutOff = 0, lastTaxRate = 0, cutOffAmount = 0, taxRate = 0;
            while (salaryAmount > cutOffAmount)
            {
                lastCutOff = cutOffAmount;
                lastTaxRate = taxRate;
                int errorCode = prTaxRates.NextRecord();
                cutOffAmount = prTaxRates.getField(kCutOffAmount).getValue();
                if (errorCode)
                    cutOffAmount = salaryAmount;
                taxRate = prTaxRates.getField(kTaxRate).getValue();
                annualTax += (min(salaryAmount, cutOffAmount) - lastCutOff)  lastTaxRate / 100;
            }
            prTaxRates.removeListener(temp);
        */
            return annualTax;
    }
    /**
     * SetupDE Method.
     */
    public void setupDE(int index, String deTypes, String freqCodes, Record employee, Record prDedEarn)
    {
        /* HACK
            String deCode = "";
            String freqCode = "";
            String deType = "";
            this.getField(kDedEarnAmt) = "";
            this.getField(kDedEarnHours) = "";
            this.getField(kDedEarnDesc) = "";
            this.getField(kDedEarnYtd) = "";
            int dedNo = 1, indexTarget = 0;
            while ((indexTarget <= index)  (dedNo <= 8))
            {
                freqCode = freqCodes; // Starts out true
                switch(dedNo)
                {
                case 1:
                    deCode = this.getField(kTimeDe1).getString();
                    this.getField(kDedEarnHours) =  this.getField(kTimeHrs1);
                    this.getField(kDedEarnAmt) = this.getField(kTimeAmt1);
                    break;
                case 2:
                    deCode = this.getField(kTimeDe2).getString();
                    this.getField(kDedEarnHours) =  this.getField(kTimeHrs2);
                    this.getField(kDedEarnAmt) = this.getField(kTimeAmt2);
                    break;
                case 3:
                    deCode = this.getField(kTimeDe3).getString();
                    this.getField(kDedEarnHours) =  this.getField(kTimeHrs3);
                    this.getField(kDedEarnAmt) = this.getField(kTimeAmt3);
                    break;
                case 4:
                    deCode = this.getField(kTimeDe4).getString();
                    this.getField(kDedEarnHours) =  this.getField(kTimeHrs4);
                    this.getField(kDedEarnAmt) = this.getField(kTimeAmt4);
                    break;
                case 5:
                    deCode = employee.getField(kDeductionID1).getString();
                    freqCode =  employee.getField(kFrequency1).getString();
                    this.getField(kDedEarnAmt) = employee.getField(kAmount1);
                    break;
                case 6:
                    deCode = employee.getField(kDeductionID2).getString();
                    freqCode =  employee.getField(kFrequency2).getString();
                    this.getField(kDedEarnAmt) = employee.getField(kAmount2);
                    break;
                case 7:
                    deCode = employee.getField(kDeductionID3).getString();
                    freqCode =  employee.getField(kFrequency3).getString();
                    this.getField(kDedEarnAmt) = employee.getField(kAmount3);
                    break;
                case 8:
                    deCode = employee.getField(kDeductionID4).getString();
                    freqCode =  employee.getField(kFrequency4).getString();
                    this.getField(kDedEarnAmt) = employee.getField(kAmount4);
                    break;
                default:
                    deCode = "";
                }
                dedNo++;
                if (deCode.IsEmpty())
                    continue;
                if (!freqCode.IsEmpty()) if (freqCodes.Find(freqCode.GetAt(0)) == -1)
                    continue;
                this.GetDeCode(deCode, prDedEarn);
                deType = prDedEarn.getField(kType).getString();
                if (!deType.IsEmpty()) if (deTypes.Find(deType.GetAt(0)) == -1)
                    continue;
                indexTarget++;
                if (this.getField(kDedEarnDesc) == "")
                    this.getField(kDedEarnAmt) = "";
                else
                {
                    if (this.getField(kDedEarnAmt).getValue() < 1.00)
                        this.getField(kDedEarnAmt).setValue(this.getField(kDedEarnAmt).getValue()  this.getField(kGrossPay).getValue());
                }
            }
        */
    }

}
