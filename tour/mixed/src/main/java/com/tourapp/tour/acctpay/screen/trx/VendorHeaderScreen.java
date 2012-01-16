/**
 * @(#)VendorHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
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
    public VendorHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
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
        this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kVendorID).setEnabled(true);
        
        ((ReferenceField)this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kVendorID)).getReferenceRecord().getField(Vendor.kCode).setEnabled(true);
        this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kDisplayType).setEnabled(true);
        this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kDisplayActive).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kDisplayType).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kDisplayActive).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(VendorScreenRecord.kVendorScreenRecordFile).getField(VendorScreenRecord.kBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
