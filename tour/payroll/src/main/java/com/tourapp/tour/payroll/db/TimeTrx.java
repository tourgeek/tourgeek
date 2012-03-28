/**
 * @(#)TimeTrx.
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
import com.tourapp.tour.payroll.report.payroll.*;
import com.tourapp.model.tour.payroll.db.*;

/**
 *  TimeTrx - Timecard entry.
 */
public class TimeTrx extends VirtualRecord
     implements TimeTrxModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TIME_TRX_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TIME_TRX_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TIME_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        if (iFieldSeq == 3)
        {
            field = new TimeTrx_PayEnding(this, PAY_ENDING, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 4)
        {
            field = new IntegerField(this, TIME_EMP_NO, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 5)
            field = new ShortField(this, PAY_SEQ, 1, null, null);
        if (iFieldSeq == 6)
            field = new BooleanField(this, PAY_SALARY, 1, null, null);
        if (iFieldSeq == 7)
            field = new FloatField(this, REGULAR_HRS, 6, null, null);
        if (iFieldSeq == 8)
        {
            field = new FloatField(this, OVERTIME_HRS, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 9)
        {
            field = new FloatField(this, SP_1_HOURS, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 10)
        {
            field = new FloatField(this, SP_2_HOURS, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 11)
            field = new StringField(this, TIME_DE_1, 3, null, null);
        if (iFieldSeq == 12)
        {
            field = new FloatField(this, TIME_HRS_1, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 13)
        {
            field = new FloatField(this, TIME_AMT_1, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
            field = new StringField(this, TIME_DE_2, 3, null, null);
        if (iFieldSeq == 15)
        {
            field = new FloatField(this, TIME_HRS_2, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 16)
        {
            field = new FloatField(this, TIME_AMT_2, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 17)
            field = new StringField(this, TIME_DE_3, 3, null, null);
        if (iFieldSeq == 18)
        {
            field = new FloatField(this, TIME_HRS_3, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 19)
        {
            field = new FloatField(this, TIME_AMT_3, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 20)
            field = new StringField(this, TIME_DE_4, 3, null, null);
        if (iFieldSeq == 21)
        {
            field = new FloatField(this, TIME_HRS_4, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 22)
        {
            field = new FloatField(this, TIME_AMT_4, 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 23)
            field = new FloatField(this, REGULAR_PAY, 8, null, null);
        if (iFieldSeq == 24)
            field = new FloatField(this, OVERTIME_PAY, 8, null, null);
        if (iFieldSeq == 25)
            field = new FloatField(this, SPECIAL_1_PAY, 8, null, null);
        if (iFieldSeq == 26)
            field = new FloatField(this, SPECIAL_2_PAY, 8, null, null);
        if (iFieldSeq == 27)
            field = new FloatField(this, GROSS_PAY, 8, null, null);
        if (iFieldSeq == 28)
            field = new FloatField(this, STATE_GROSS_PAY, 8, null, null);
        if (iFieldSeq == 29)
            field = new FloatField(this, NON_TAX_PAY, 8, null, null);
        if (iFieldSeq == 30)
            field = new FloatField(this, FED_TAXES, 7, null, null);
        if (iFieldSeq == 31)
            field = new FloatField(this, STATE_TAXES, 7, null, null);
        if (iFieldSeq == 32)
            field = new FloatField(this, LOCAL_TAXES, 7, null, null);
        if (iFieldSeq == 33)
            field = new FloatField(this, FICA_TAXES, 7, null, null);
        if (iFieldSeq == 34)
            field = new FloatField(this, SDI_TAXES, 7, null, null);
        if (iFieldSeq == 35)
            field = new FloatField(this, OTHER_DED, 7, null, null);
        if (iFieldSeq == 36)
            field = new FloatField(this, NET_PAY, 8, null, null);
        if (iFieldSeq == 37)
            field = new IntegerField(this, PR_CHECK_NUM, 6, null, null);
        if (iFieldSeq == 38)
            field = new FloatField(this, WEEKS_WORKED, 6, null, null);
        if (iFieldSeq == 39)
        {
            field = new FloatField(this, DED_EARN_HOURS, 6, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 40)
        {
            field = new StringField(this, DED_EARN_DESC, 16, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 41)
        {
            field = new FloatField(this, DED_EARN_AMT, 8, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 42)
        {
            field = new DoubleField(this, DED_EARN_YTD, 10, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 43)
        {
            field = new FloatField(this, PAY_GROSS, 8, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 44)
        {
            field = new FloatField(this, PAY_TAXES, 8, null, null);
            field.setVirtual(true);
        }
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PayEnding");
            keyArea.addKeyField(PAY_ENDING, DBConstants.ASCENDING);
            keyArea.addKeyField(TIME_EMP_NO, DBConstants.ASCENDING);
            keyArea.addKeyField(PAY_SEQ, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
            StringSubFileFilter temp = new StringSubFileFilter(taxCode, maritalCode, TAX_CODE, MARITAL_STATUS);
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
