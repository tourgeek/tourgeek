/**
 *  @(#)BookingLineGridScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.acctrec;

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
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctrec.db.*;

/**
 *  BookingLineGridScreen - Booking Line Items.
 */
public class BookingLineGridScreen extends BookingSubGridScreen
{
    /**
     * Default constructor.
     */
    public BookingLineGridScreen()
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
    public BookingLineGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.LINE_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking Line Items";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        if (this.getRecord(BookingLine.kBookingLineFile) != null)
            return this.getRecord(BookingLine.kBookingLineFile);
        return new BookingLine(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReferenceRecord(this);
        super.openOtherRecords();
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        BookingLine recBookingLine = (BookingLine)this.getRecord(BookingLine.kBookingLineFile);
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        recBooking.addArDetail(null, recBookingLine, false);
        
        recBookingLine.getField(BookingLine.kPrice).addListener(new CopyDataHandler(recBookingLine.getField(BookingLine.kPricingStatusID), new Integer(PricingStatus.MANUAL), null));
        recBookingLine.addListener(new BookingLineStatusHandler(null));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolbar = super.addToolbars();
        
        ToolScreen toolbar2 = new EmptyToolbar(this.getNextLocation(ScreenConstants.LAST_LOCATION, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        BaseField converter = null;
        ScreenField sField = null;
        
        converter = this.getRecord(Booking.kBookingFile).getField(Booking.kGross);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = this.getRecord(Booking.kBookingFile).getField(Booking.kNet);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = this.getRecord(Booking.kBookingFile).getField(Booking.kPricingStatusID);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        
        converter = this.getRecord(Booking.kBookingFile).getField(Booking.kTourPricingTypeID);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        converter = this.getRecord(Booking.kBookingFile).getField(Booking.kNonTourPricingTypeID);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        
        return toolbar;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BookingLine.kBookingLineFile).getField(BookingLine.kPricingStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingLine.kBookingLineFile).getField(BookingLine.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingLine.kBookingLineFile).getField(BookingLine.kPrice).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingLine.kBookingLineFile).getField(BookingLine.kQuantity).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingLine.kBookingLineFile).getField(BookingLine.kGross).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingLine.kBookingLineFile).getField(BookingLine.kCommission).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingLine.kBookingLineFile).getField(BookingLine.kNet).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
