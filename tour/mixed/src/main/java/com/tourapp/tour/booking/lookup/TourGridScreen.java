/**
 *  @(#)TourGridScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.inventory.db.*;

/**
 *  TourGridScreen - Booking entry.
 */
public class TourGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public TourGridScreen()
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
    public TourGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return new TourLookupQuery(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        Record record = this.getMainRecord();
        if (record != null)
        {
            Record mainRecord = null;
            if (record instanceof ScreenRecord)
            {
                mainRecord = this.openMainRecord();
                this.setScreenRecord(record);
            }
            if (Tour.kTourFile.equalsIgnoreCase(record.getTableNames(false)))
            {
                record.free();
                mainRecord = this.openMainRecord();
            }
            if (mainRecord == null)
                mainRecord = this.getRecord(TourLookupQuery.kTourLookupQueryFile);
            this.addRecord(mainRecord, true);    // Make sure this is the main record
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
        
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        TourHeader recTourHeader = (TourHeader)this.getRecord(TourHeader.kTourHeaderFile);
        Record screenQuery = this.getScreenRecord();
        
        if (((NumberField)screenQuery.getField(LookupScreenRecord.kQueryKey)).getValue() > 2)
            ((NumberField)screenQuery.getField(LookupScreenRecord.kQueryKey)).setValue(0);
        FieldListener behCheckRange = new CheckRangeHandler(0, 2);
        screenQuery.getField(LookupScreenRecord.kQueryKey).addListener(behCheckRange);
        recTour.addListener(new FileRemoveBOnCloseHandler(behCheckRange));
        
        SortOrderHandler behQueryKeyHandler = new SortOrderHandler(this);
        behQueryKeyHandler.setGridTable(Tour.kDescriptionKey, recTour, -1);
        behQueryKeyHandler.setGridTable(Tour.kDepartureDateKey, recTour, -1);
        screenQuery.getField(LookupScreenRecord.kQueryKey).addListener(behQueryKeyHandler);
        
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kDepartureDate), screenQuery.getField(LookupScreenRecord.kTourHdrStartDate), ">=", null, true));
        screenQuery.getField(LookupScreenRecord.kTourHdrStartDate).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kDepartureDate), screenQuery.getField(LookupScreenRecord.kTourHdrEndDate), "<=", null, true));
        screenQuery.getField(LookupScreenRecord.kTourHdrEndDate).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new BitFileFilter(recTourHeader.getField(TourHeader.kTourType), screenQuery.getField(LookupScreenRecord.kTourHdrTourType)));
        screenQuery.getField(LookupScreenRecord.kTourHdrTourType).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new StartSearchFilter(screenQuery.getField(LookupScreenRecord.kStartTargetField)));
        screenQuery.getField(LookupScreenRecord.kStartTargetField).addListener(new FieldReSelectHandler(this));
        FieldListener behInitOnChange = new InitOnChangeHandler(screenQuery.getField(LookupScreenRecord.kStartTargetField));
        screenQuery.getField(LookupScreenRecord.kQueryKey).addListener(behInitOnChange);
        recTour.addListener(new FileRemoveBOnCloseHandler(behInitOnChange));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kTourHeaderID), screenQuery.getField(LookupScreenRecord.kTourHeaderID), "=", null, true));
        screenQuery.getField(LookupScreenRecord.kTourHeaderID).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kTourStatusID), screenQuery.getField(LookupScreenRecord.kTourStatusID), "=", null, true));
        screenQuery.getField(LookupScreenRecord.kTourStatusID).addListener(new FieldReSelectHandler(this));
        
        this.getScreenRecord().getField(LookupScreenRecord.kBkDisplayType).setValue(DisplayTypeField.TOUR_DISPLAY, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        
        this.setEditing(false);
    }
    /**
     * Read the current file in the header record given the current detail record.
     */
    public void syncHeaderToMain()
    {
        super.syncHeaderToMain();
        
        this.restoreScreenParam(LookupScreenRecord.kTourHdrStartDate);
        this.restoreScreenParam(LookupScreenRecord.kTourHdrEndDate);
        this.restoreScreenParam(LookupScreenRecord.kTourHdrTourType);
        this.restoreScreenParam(LookupScreenRecord.kTourHeaderID);
        this.restoreScreenParam(LookupScreenRecord.kTourStatusID);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ((LookupScreenRecord)this.getScreenRecord()).addStandardToolbar(this);  // I'm running stand-alone
        return ((LookupScreenRecord)this.getScreenRecord()).addTourToolbar(this);
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
        Converter converter = this.getMainRecord().getField(Tour.kTourFile, Tour.kDescription);
        converter = new FieldLengthConverter(converter, 30);
        this.addColumn(converter);
        this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        converter = this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kTourHeaderFile, TourHeader.kDescription);
        converter = new FieldLengthConverter(converter, 30);
        converter = new FieldDescConverter(converter, "Header Tour Desc");
        this.addColumn(converter);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kBlocked).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kAvailable).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTourStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
                String strTourID = this.getMainRecord().getField(Tour.kID).toString();
                strCommand = Utility.addURLParam(strCommand, "TourID", strTourID);
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
