/**
 * @(#)QEmployee.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.screen.emp;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.payroll.db.*;

/**
 *  QEmployee - Employee Maintenance.
 */
public class QEmployee extends BaseScreen
{
    /**
     * Default constructor.
     */
    public QEmployee()
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
    public QEmployee(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Employee Maintenance";
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record query = this.getScreenRecord();
        Employee mainFile = (Employee)this.getRecord(Employee.kEmployeeFile);
        //?ShortField empKey = (ShortField)query.getField(Employee.kEmpKeyNo);
        //?Converter converter;
        //?converter = new KeyRadioConverter(empKey, mainFile.getTable(), Employee.kLastNameKey);
        //?new SRadioButton(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, converter);
        //?converter = new KeyRadioConverter(empKey, mainFile.getTable(), Employee.kFirstNameKey);
        //?new SRadioButton(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, converter);
        //?converter = new KeyRadioConverter(empKey, mainFile.getTable(), Employee.kIDKey);
        //?new SRadioButton(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, converter);
        
        BaseField activeFlag = query.getField(EmployeeScreenRecord.kActiveEmp);
        activeFlag.setState(true, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE); // Initial state
        ScreenLocation next = this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT);
        //?activeFlag.setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this);
        //?GridScreen gridScreen = new CDEmployee(null);
        //?gridScreen.Create(Point(0, next.y), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        //?FieldListener fieldBehavior = new FieldReSelectHandler(gridScreen);
        //?activeFlag.addListener(fieldBehavior);
        //?FileListener skipBehavior = new SkipBlankBehavior(Employee.kTerminationDate, true, activeFlag);
        //?mainFile.addListener(skipBehavior);  // Skip terminated employees when set
        
        //?FieldListener keyBehavior = new QueryKeyHandler(gridScreen);
        //?empKey.addListener(keyBehavior);
        //?empKey.setValue(Employee.kLastNameKey);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new EmployeeScreenRecord(this);
    }

}
