/**
 * @(#)ArTrxAgentGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.display;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ArTrxAgentGridScreen - Agent A/R Account inquiry.
 */
public class ArTrxAgentGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public ArTrxAgentGridScreen()
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
    public ArTrxAgentGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
        
        if (this.getRecord(Profile.kProfileFile).getField(Profile.kProfileCode).getComponent(0) != null)
            ((ScreenField)this.getRecord(Profile.kProfileFile).getField(Profile.kProfileCode).getComponent(0)).requestFocus();
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Agent A/R Account inquiry";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Booking(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ArTrx(this);
        new Profile(this);
        new BookingLine(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ArTrxAgentScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileCode).addListener(new MainReadOnlyHandler(Profile.kProfileCodeKey));
        
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(Profile.kProfileFile), true));
        
        this.getHeaderRecord().getField(Profile.kID).addListener(new FieldReSelectHandler(this));
        Record recArTrx = this.getRecord(ArTrx.kArTrxFile);
        Record recBooking = this.getRecord(Booking.kBookingFile);
        Record recBookingLine = this.getRecord(BookingLine.kBookingLineFile);
        recArTrx.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recArTrx));
        recArTrx.addListener(new SubCountHandler(recBooking.getField(Booking.kBalance), ArTrx.kAmount, true, true));
        recBookingLine.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recBookingLine));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.kGross), BookingLine.kGross, true, true));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.kNet), BookingLine.kNet, true, true));
        
        recBooking.addListener(new SubCountHandler(this.getScreenRecord().getField(ArTrxAgentScreenRecord.kBalance), Booking.kBalance, true, true));
        
        this.setEnabled(false);
        this.setAppending(false);
    }
    /**
     * If there is a header record, return it, otherwise, return the main record.
     * The header record is the (optional) main record on gridscreens and is sometimes used
     * to enter data in a sub-record when a header is required.
     * @return The header record.
     */
    public Record getHeaderRecord()
    {
        return this.getRecord(Profile.kProfileFile);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Booking.kBookingFile).getField(Booking.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kBookingDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kBookingStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kTourID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kProfileID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kNet).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kGross).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ArTrxAgentHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
