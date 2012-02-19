/**
 * @(#)VendorHeaderScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.trx;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  VendorHeaderScreen - Header screen for Vendor A/P Detail display.
 */
public class VendorHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public VendorHeaderScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public VendorHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Header screen for Vendor A/P Detail display";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(VendorScreenRecord.VENDOR_ID).setEnabled(true);
        
        ((ReferenceField)this.getScreenRecord().getField(VendorScreenRecord.VENDOR_ID)).getReferenceRecord().getField(Vendor.CODE).setEnabled(true);
        this.getScreenRecord().getField(VendorScreenRecord.DISPLAY_TYPE).setEnabled(true);
        this.getScreenRecord().getField(VendorScreenRecord.DISPLAY_ACTIVE).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(VendorScreenRecord.VENDOR_SCREEN_RECORD_FILE).getField(VendorScreenRecord.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(VendorScreenRecord.VENDOR_SCREEN_RECORD_FILE).getField(VendorScreenRecord.DISPLAY_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(VendorScreenRecord.VENDOR_SCREEN_RECORD_FILE).getField(VendorScreenRecord.DISPLAY_ACTIVE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CURRENCYS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(VendorScreenRecord.VENDOR_SCREEN_RECORD_FILE).getField(VendorScreenRecord.BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
