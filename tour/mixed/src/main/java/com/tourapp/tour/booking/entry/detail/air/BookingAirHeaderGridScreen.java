/**
 * @(#)BookingAirHeaderGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.air;

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
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  BookingAirHeaderGridScreen - Booking Ticket Header Detail.
 */
public class BookingAirHeaderGridScreen extends BookingSubGridScreen
{
    /**
     * Default constructor.
     */
    public BookingAirHeaderGridScreen()
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
    public BookingAirHeaderGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.AIR_HEADER_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking Ticket Header Detail";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingAirHeader(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        BookingAirHeader recBookingAirHeader = (BookingAirHeader)this.getMainRecord();
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        recBookingAirHeader.addDetailBehaviors(recBooking, recTour);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        super.addToolbarButtons(toolScreen);
        ResourceBundle resources = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.BOOKING_RESOURCE, true);
        BaseField field = this.getRecord(Booking.kBookingFile).getRecordOwner().getScreenRecord().getField(BookingScreenRecord.kBkSubScreen);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, field, ScreenConstants.DEFAULT_DISPLAY, null, resources.getString(ProductType.AIR), Booking.BUTTON_LOCATION + ProductType.AIR, Integer.toString(BookingScreenHandler.AIR_SCREEN), resources.getString(ProductType.AIR + "Tip"));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BookingAirHeader.kBookingAirHeaderFile).getField(BookingAirHeader.kAirlineDesc).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
