/**
 * @(#)TimeTrxHeader.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.payroll.db.*;

/**
 *  TimeTrxHeader - Header Screen for time trx.
 */
public class TimeTrxHeader extends Screen
{
    /**
     * Default constructor.
     */
    public TimeTrxHeader()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public TimeTrxHeader(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Header Screen for time trx";
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
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TimeTrxScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        Record prTimeTrx = this.getRecord(TimeTrx.TIME_TRX_FILE);
        FieldListener readKeyed = new MainFieldHandler();
        prTimeTrx.getField(TimeTrx.TIME_EMP_NO).addListener(readKeyed);
        Record employee = this.getRecord(Employee.EMPLOYEE_FILE);
        FileListener subFile = new SubFileFilter(this.getScreenRecord().getField(TimeTrxScreenRecord.PAY_ENDING_DATE), TimeTrx.PAY_ENDING, null, null, null, null);
        prTimeTrx.addListener(subFile);
        FieldListener secondary = new ReadSecondaryHandler(employee, Employee.ID);
        //+prTimeTrx.getField(PrTimeTrx.TIME_EMP_NO).addListener(secondary);
        //+FileListener checkField = new ContainsFieldFileBehavior(Employee.PAY_FREQUENCY, this.getScreenRecord().getField(HFPrTimeTrx.EMP_TO_PAY));
        //+employee.addListener(checkField);
        //+FileListener checkTerminated = new SkipBlankBehavior(Employee.TERMINATION_DATE, true); // Skip only if NOT null
        //+employee.addListener(checkTerminated); // Skip terminated empployees
        //+FieldListener fieldBeh = new DisableOnFieldHandler(prTimeTrx.getField(PrTimeTrx.PAY_SALARY), "H");
        //+employee.getField(Employee.PAY_TYPE).addListener(fieldBeh);
        //+FileListener disableKey = new DisableOnValid(PrTimeTrx.TIME_EMP_NO);
        //+prTimeTrx.addListener(disableKey);
        FileListener checkValid = new CheckValidEmployee(this.getScreenRecord().getField(TimeTrxScreenRecord.EMP_TO_PAY));
        employee.addListener(checkValid);
    }
    /**
     * SelectFirstField Method.
     */
    public void selectFirstField()
    {
        /*POSITION pos = m_SFieldList.GetHeadPosition();
        while (pos)
        {   // Sub-Screen's field should get the focus
            ScreenField sField = (ScreenField)m_SFieldList.GetNext(pos);
            if (sField.getMainRecord() != null)
            {
                ((Screen)sField).SelectFirstField();
                return;
            }
        }*/
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
    }

}
