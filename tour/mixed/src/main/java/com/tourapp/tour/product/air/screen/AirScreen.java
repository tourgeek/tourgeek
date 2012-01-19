/**
 * @(#)AirScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.air.screen;

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
import com.tourapp.tour.product.air.event.*;
import com.tourapp.tour.product.base.event.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.base.db.*;

/**
 *  AirScreen - .
 */
public class AirScreen extends ProductScreen
{
    /**
     * Default constructor.
     */
    public AirScreen()
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
    public AirScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Air(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new AirScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.addMainKeyBehavior();
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(Product.kVendorID)).getReferenceRecord(this);
        recVendor.getField(Vendor.kCurrencysID).setEnabled(false);
        
        Record recAir = this.getMainRecord();
        Record recAirline = ((ReferenceField)recAir.getField(Air.kAirlineID)).getReferenceRecord();
        recAir.getField(Air.kAirlineID).addListener(new MoveOnChangeHandler(recAir.getField(Air.kVendorID), recAirline.getField(Airline.kVendorID)));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(Product.kVendorID)).getReferenceRecord(this);
        Record recCurrency = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
        this.getRecord(Air.kAirFile).getField(Air.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kAirlineID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kFlightNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kCityCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        ReferenceField field = (ReferenceField)this.getRecord(Air.kAirFile).getField(Air.kCityID);
        field.setupTableLookup(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC, field.makeReferenceRecord(), -1, City.kDescription, false);
        this.getRecord(Air.kAirFile).getField(Air.kToCityCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        field = (ReferenceField)this.getRecord(Air.kAirFile).getField(Air.kToCityID);
        field.setupTableLookup(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC, field.makeReferenceRecord(), -1, City.kDescription, false);
        this.getRecord(Air.kAirFile).getField(Air.kEtd).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kArriveTime).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kAddDays).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kEquipment).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kMeals).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kDays).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kClasses).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kStops).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Air.kAirFile).getField(Air.kItineraryDesc).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.FILL_REMAINDER), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
