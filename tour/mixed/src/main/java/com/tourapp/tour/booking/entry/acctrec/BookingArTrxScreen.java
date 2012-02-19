/**
 * @(#)BookingArTrxScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctrec.db.*;

/**
 *  BookingArTrxScreen - A/R Open File.
 */
public class BookingArTrxScreen extends BookingSubScreen
{
    /**
     * Default constructor.
     */
    public BookingArTrxScreen()
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
    public BookingArTrxScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Open the files and setup the screen.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Additional properties to pass to the screen.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.AR_TRX_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "A/R Open File";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        if (this.getRecord(ArTrx.AR_TRX_FILE) != null)
            return this.getRecord(ArTrx.AR_TRX_FILE);
        return new ArTrx(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();        
        
        this.setAppending(false);
        this.setEditing(false);
        
        ArTrx recArTrx = (ArTrx)this.getRecord(ArTrx.AR_TRX_FILE);
        Booking recBooking = (Booking)this.getRecord(Booking.BOOKING_FILE);
        recBooking.addArDetail(recArTrx, null, false);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(LinkTrx.SOURCE), LinkTrx.SOURCE, LinkTrx.SOURCE, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION), AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, null);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolbar = super.addToolbars();
        
        ToolScreen toolbar2 = new EmptyToolbar(this.getNextLocation(ScreenConstants.LAST_LOCATION, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        BaseField converter = null;
        converter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.GROSS);
        ScreenComponent sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.STD_COMMISSION);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.NET);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        
        converter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.DEPOSIT);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.DEPOSIT_DUE_DATE);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        
        converter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.FINAL_PAYMENT_DUE_DATE);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.BALANCE);
        sField = converter.setupDefaultView(toolbar2.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar2, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        
        return toolbar;
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(ArTrx.AR_TRX_FILE).getField(ArTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.AR_TRX_FILE).getField(ArTrx.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.AR_TRX_FILE).getField(ArTrx.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.AR_TRX_FILE).getField(ArTrx.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (LinkTrx.SOURCE.equalsIgnoreCase(strCommand))
        {
            LinkTrx recLinkTrx = (LinkTrx)this.getMainRecord();
            strCommand = recLinkTrx.getSourceCommand();
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
