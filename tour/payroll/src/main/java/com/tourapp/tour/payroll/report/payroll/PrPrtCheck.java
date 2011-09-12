/**
 * @(#)PrPrtCheck.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.report.payroll;

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
import com.tourapp.tour.payroll.db.*;

/**
 *  PrPrtCheck - Print the Checks.
 */
public class PrPrtCheck extends GridScreen
{
    /**
     * Default constructor.
     */
    public PrPrtCheck()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public PrPrtCheck(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Print the Checks";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TimeTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Employee(this);
        new EmployeeControl(this);
        new Deduction(this);
        new TaxRate(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        FileListener readSecond = new DisplayReadHandler(TimeTrx.kTimeEmpNo, this.getRecord(Employee.kEmployeeFile), Employee.kID);
        this.getRecord(TimeTrx.kTimeTrxFile).addListener(readSecond);
        QueryRecord queryInfo = null;
        CalcEmpTaxesHandler calcTaxes = new CalcEmpTaxesHandler(queryInfo, this.getRecord(Employee.kEmployeeFile), this.getRecord(Deduction.kDeductionFile), this.getRecord(TaxRate.kTaxRateFile), this.getRecord(EmployeeControl.kEmployeeControlFile));
        this.getRecord(TimeTrx.kTimeTrxFile).addListener(calcTaxes);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
    }

}
