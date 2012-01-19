/**
 * @(#)LandPricingGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.land.screen;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.land.db.*;

/**
 *  LandPricingGridScreen - Land pricing.
 */
public class LandPricingGridScreen extends ProductPricingGridScreen
{
    /**
     * Default constructor.
     */
    public LandPricingGridScreen()
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
    public LandPricingGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Land pricing";
    }
    /**
     * Constructor with header passed in.
     */
    public LandPricingGridScreen(Record recProduct, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recProduct, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new LandPricing(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ProductControl(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new Land(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new LandScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().getField(LandPricing.kRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kLandRateID)));
        this.getMainRecord().getField(LandPricing.kClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kLandClassID)));
        this.getMainRecord().getField(LandPricing.kLandVariesID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kVariesOn)));
        this.getMainRecord().getField(LandPricing.kProductTermsID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kProductTermsID)));
        
        this.getMainRecord().addListener(new CompareFileFilter(LandPricing.kClassID, this.getScreenRecord().getField(LandScreenRecord.kLandClassID), "=", null, true));
        this.getScreenRecord().getField(LandScreenRecord.kLandClassID).addListener(new FieldReSelectHandler(this));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        ReferenceField fldLandClass = (ReferenceField)this.getScreenRecord().getField(LandScreenRecord.kLandClassID);
        int iSic = fldLandClass.getIDFromCode(LandClass.SEAT_IN_COACH_CODE);
        if (iSic != 0)
        {
            String strSic = fldLandClass.getReferenceRecord().getField(LandClass.kDescription).toString();
            int iPmc = fldLandClass.getIDFromCode(LandClass.PRIVATE_VEHICLE_CODE);
            if (iPmc != 0)
            {
                String strPmc = fldLandClass.getReferenceRecord().getField(LandClass.kDescription).toString();
                new SRadioButton(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, fldLandClass, ScreenConstants.DISPLAY_FIELD_DESC, Integer.toString(iSic), strSic);
                new SRadioButton(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, fldLandClass, ScreenConstants.DISPLAY_FIELD_DESC, Integer.toString(iPmc), strPmc);
            }
        }
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getHeaderRecord().getField(Product.kVendorID)).getReferenceRecord(this);
        Record recCurrency = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
        this.getRecord(LandPricing.kLandPricingFile).getField(LandPricing.kRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.kLandPricingFile).getField(LandPricing.kClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.kLandPricingFile).getField(LandPricing.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.kLandPricingFile).getField(LandPricing.kEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.kLandPricingFile).getField(LandPricing.kFromPax).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.kLandPricingFile).getField(LandPricing.kToPax).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.kLandPricingFile).getField(LandPricing.kCost).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new LandHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}
