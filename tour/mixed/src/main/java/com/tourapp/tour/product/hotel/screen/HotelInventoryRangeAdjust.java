/**
 * @(#)HotelInventoryRangeAdjust.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.screen;

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
import com.tourapp.tour.booking.inventory.db.*;
import com.tourapp.tour.product.hotel.db.*;

/**
 *  HotelInventoryRangeAdjust - .
 */
public class HotelInventoryRangeAdjust extends ProductInventoryRangeAdjust
{
    /**
     * Default constructor.
     */
    public HotelInventoryRangeAdjust()
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
    public HotelInventoryRangeAdjust(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * HotelInventoryRangeAdjust Method.
     */
    public HotelInventoryRangeAdjust(Record recProduct, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
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
        return new HotelInventory(this);
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new Hotel(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new HotelScreenRecord(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kPaxCategoryID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kBlocked).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kOversell).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kClosed).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelScreenRecord.kHotelScreenRecordFile).getField(HotelScreenRecord.kDelete).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication app = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, app.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString(ADJUST_RANGE), MenuConstants.POST, ADJUST_RANGE, null);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new HotelHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * Setup the key for the inventory record.
     * Note: The product ID and type should already be set by SubFilter.
     * @param recInventory The inv record.
     * @param fldTargetDate The date to set.
     */
    public void setInvKey(Record recInventory, BaseField fldTargetDate)
    {
        recInventory.getField(Inventory.kInvDate).moveFieldToThis(fldTargetDate);
        recInventory.getField(Inventory.kRateID).moveFieldToThis(this.getScreenRecord().getField(HotelScreenRecord.kRateID));
        recInventory.getField(Inventory.kClassID).moveFieldToThis(this.getScreenRecord().getField(HotelScreenRecord.kClassID));
        recInventory.getField(Inventory.kOtherID).moveFieldToThis(this.getScreenRecord().getField(HotelScreenRecord.kPaxCategoryID));
    }

}
