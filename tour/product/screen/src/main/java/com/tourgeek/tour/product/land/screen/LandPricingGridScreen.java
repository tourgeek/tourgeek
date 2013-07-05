/**
  * @(#)LandPricingGridScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.land.screen;

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
import com.tourgeek.tour.product.base.screen.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.product.land.db.*;

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
        
        this.getMainRecord().getField(LandPricing.RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.LAND_RATE_ID)));
        this.getMainRecord().getField(LandPricing.CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.LAND_CLASS_ID)));
        this.getMainRecord().getField(LandPricing.LAND_VARIES_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.VARIES_ON)));
        this.getMainRecord().getField(LandPricing.PRODUCT_TERMS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.PRODUCT_TERMS_ID)));
        
        this.getMainRecord().addListener(new CompareFileFilter(LandPricing.CLASS_ID, this.getScreenRecord().getField(LandScreenRecord.LAND_CLASS_ID), "=", null, true));
        this.getScreenRecord().getField(LandScreenRecord.LAND_CLASS_ID).addListener(new FieldReSelectHandler(this));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        ReferenceField fldLandClass = (ReferenceField)this.getScreenRecord().getField(LandScreenRecord.LAND_CLASS_ID);
        int iSic = fldLandClass.getIDFromCode(LandClass.SEAT_IN_COACH_CODE);
        if (iSic != 0)
        {
            String strSic = fldLandClass.getReferenceRecord().getField(LandClass.DESCRIPTION).toString();
            int iPmc = fldLandClass.getIDFromCode(LandClass.PRIVATE_VEHICLE_CODE);
            if (iPmc != 0)
            {
                String strPmc = fldLandClass.getReferenceRecord().getField(LandClass.DESCRIPTION).toString();
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
        Record recVendor = ((ReferenceField)this.getHeaderRecord().getField(Product.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrency = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getRecord(LandPricing.LAND_PRICING_FILE).getField(LandPricing.RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.LAND_PRICING_FILE).getField(LandPricing.CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.LAND_PRICING_FILE).getField(LandPricing.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.LAND_PRICING_FILE).getField(LandPricing.END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.LAND_PRICING_FILE).getField(LandPricing.FROM_PAX).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.LAND_PRICING_FILE).getField(LandPricing.TO_PAX).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(LandPricing.LAND_PRICING_FILE).getField(LandPricing.COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new LandHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
