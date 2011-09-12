/**
 * @(#)TourHeaderGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.lookup;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.*;

/**
 *  TourHeaderGridScreen - Booking entry.
 */
public class TourHeaderGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public TourHeaderGridScreen()
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
    public TourHeaderGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Booking entry";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TourHeader(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        Record record = this.getMainRecord();
        if (record instanceof ScreenRecord)
        {
            this.removeRecord(record);
            this.openMainRecord();
            this.addRecord(record, false);
            this.setScreenRecord(record);
        }
        super.openOtherRecords();
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        if (!(this.getScreenRecord() instanceof LookupScreenRecord))
            return new LookupScreenRecord(this);
        else
            return this.getScreenRecord();
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.setEditing(false);
        TourHeader recTourHeader = (TourHeader)this.getRecord(TourHeader.kTourHeaderFile);
        Record screenRecord = this.getScreenRecord();
        
        int iCommandID = (int)screenRecord.getField(LookupScreenRecord.kBookingListFormat).getValue();
        if (((NumberField)screenRecord.getField(BookingScreenRecord.kBkSubScreen)).getValue() > 3)
            ((NumberField)screenRecord.getField(BookingScreenRecord.kBkSubScreen)).setValue(0);
        FieldListener behCheckRange = new CheckRangeHandler(0, 3);
        screenRecord.getField(BookingScreenRecord.kBkSubScreen).addListener(behCheckRange);
        recTourHeader.addListener(new FileRemoveBOnCloseHandler(behCheckRange));
        
        KeyArea tempKeyStart = recTourHeader.makeIndex(DBConstants.NOT_UNIQUE, null);  // Add temp key
        tempKeyStart.addKeyField(TourHeader.kStartDate, DBConstants.ASCENDING);
        KeyArea tempKeyEnd = recTourHeader.makeIndex(DBConstants.NOT_UNIQUE, null);
        tempKeyEnd.addKeyField(TourHeader.kEndDate, DBConstants.ASCENDING);
        SortOrderHandler behQueryKeyHandler = new SortOrderHandler(this);
        behQueryKeyHandler.setGridTable(TourHeader.kDescription, recTourHeader, 0);
        behQueryKeyHandler.setGridTable(tempKeyStart.getKeySeq(), recTourHeader, 1);
        behQueryKeyHandler.setGridTable(tempKeyEnd.getKeySeq(), recTourHeader, 2);
        behQueryKeyHandler.setGridTable(TourHeader.kID, recTourHeader, 3);
        screenRecord.getField(BookingScreenRecord.kBkSubScreen).addListener(behQueryKeyHandler);
        
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kBrochureID, screenRecord.getField(LookupScreenRecord.kTourHdrBrochureID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourHdrBrochureID).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new BitFileFilter(TourHeader.kTourType, screenRecord.getField(LookupScreenRecord.kTourHdrTourType)));
        screenRecord.getField(LookupScreenRecord.kTourHdrTourType).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kAirlineID, screenRecord.getField(LookupScreenRecord.kTourHdrAirlineCode), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourHdrAirlineCode).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kEndDate, screenRecord.getField(LookupScreenRecord.kTourHdrStartDate), ">=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourHdrStartDate).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kStartDate, screenRecord.getField(LookupScreenRecord.kTourHdrEndDate), "<=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourHdrEndDate).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new StartSearchFilter(screenRecord.getField(LookupScreenRecord.kStartTargetField)));
        screenRecord.getField(LookupScreenRecord.kStartTargetField).addListener(new FieldReSelectHandler(this));
        FieldListener behInitOnChange = new InitOnChangeHandler(screenRecord.getField(LookupScreenRecord.kStartTargetField));
        screenRecord.getField(LookupScreenRecord.kQueryKey).addListener(behInitOnChange);
        recTourHeader.addListener(new FileRemoveBOnCloseHandler(behInitOnChange));
        
        this.getScreenRecord().getField(LookupScreenRecord.kBkDisplayType).setValue(DisplayTypeField.TOUR_HEADER_DISPLAY, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ((LookupScreenRecord)this.getScreenRecord()).addStandardToolbar(this);  // I'm running stand-alone
        return ((LookupScreenRecord)this.getScreenRecord()).addTourHdrToolbar(this);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        //x new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + "Booking", "Booking entry", null);
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record query = this.getMainRecord();
        Converter converter = query.getField(TourHeader.kTourHeaderFile, TourHeader.kDescription);
        converter = new FieldLengthConverter(converter, 30);
        this.addColumn(converter);
        this.addColumn(query.getField(TourHeader.kTourHeaderFile, TourHeader.kStartDate));
        //? this.addColumn(query.getField(TourHeader.kTourHeaderFile, TourHeader.kID));
        this.addColumn(query.getField(TourHeader.kTourHeaderFile, TourHeader.kEndDate));
        this.addColumn(query.getField(TourHeader.kTourHeaderFile, TourHeader.kDays));
        this.addColumn(query.getField(TourHeader.kTourHeaderFile, TourHeader.kNights));
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
        if (strCommand != null)
            if (strCommand.equalsIgnoreCase("Booking Entry"))
        {
            iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER;     // Must open a new window
            strCommand = Utility.addURLParam(null, DBParams.APPLET, DBParams.BASE_APPLET);
            strCommand = Utility.addURLParam(strCommand, DBParams.SCREEN, com.tourapp.thin.app.booking.entry.TourAppScreen.class.getName());
            if ((this.getMainRecord().getEditMode() == DBConstants.EDIT_IN_PROGRESS) ||
                (this.getMainRecord().getEditMode() == DBConstants.EDIT_CURRENT))
            {   // Add booking ID
                String strTourHeaderID = this.getMainRecord().getField(TourHeader.kID).toString();
                strCommand = Utility.addURLParam(strCommand, "TourHeaderID", strTourHeaderID);
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
