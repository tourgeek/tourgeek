/**
 * @(#)TaxRateGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.screen.tax;

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
 *  TaxRateGridScreen - Tax Rates.
 */
public class TaxRateGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public TaxRateGridScreen()
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
    public TaxRateGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tax Rates";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TaxRate(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TaxRateScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().setKeyArea(TaxRate.kTaxCodeKey);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(TaxRateScreenRecord.kTaxCode), TaxRate.kTaxCode, this.getScreenRecord().getField(TaxRateScreenRecord.kMaritalStatus), TaxRate.kMaritalStatus, null, -1));
        this.getScreenRecord().getField(TaxRateScreenRecord.kTaxCode).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(TaxRateScreenRecord.kMaritalStatus).addListener(new FieldReSelectHandler(this));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TaxRate.kTaxRateFile).getField(TaxRate.kCutOffAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TaxRate.kTaxRateFile).getField(TaxRate.kTaxRate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new TaxRateHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}
