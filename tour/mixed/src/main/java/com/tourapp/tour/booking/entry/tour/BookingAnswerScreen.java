/**
 * @(#)BookingAnswerScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.tour;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.event.*;

/**
 *  BookingAnswerScreen - Booking Answer File.
 */
public class BookingAnswerScreen extends BookingSubScreen
{
    /**
     * Default constructor.
     */
    public BookingAnswerScreen()
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
    public BookingAnswerScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.OPTIONS_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);

    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking Answer File";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingAnswer(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.setAppending(false);
        
        this.getMainRecord().getField(BookingAnswer.SELECTED).addListener(new BookingAnswerSelectHandler(null));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
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
        if (strCommand.equalsIgnoreCase(MenuConstants.FORMDETAIL))
        {
            BasePanel parentScreen = this.getParentScreen();
            ScreenLocation itsLocation = null;
            if ((iCommandOptions & ScreenConstants.USE_NEW_WINDOW) == ScreenConstants.USE_SAME_WINDOW)  // Use same window
                itsLocation = this.getScreenLocation();
            else
                parentScreen = Screen.makeWindow(this.getTask().getApplication());
        
            Record record = this.getMainRecord();
            if ((record.getEditMode() != Constants.EDIT_CURRENT) && (record.getEditMode() != Constants.EDIT_IN_PROGRESS))
                return false;
        
            BookingPax recBookingPax = (BookingPax)((ReferenceField)this.getMainRecord().getField(BookingAnswer.BOOKING_PAX_ID)).getReference();
            if (recBookingPax != null)
            {
                try {
                    BookingPax recBookingPax2 = (BookingPax)recBookingPax.clone();
                    recBookingPax2.readSameRecord(recBookingPax, false, true);
                    recBookingPax = recBookingPax2;
                } catch (CloneNotSupportedException ex) {
                    // Never
                }
            }
            String strModuleID = record.getField(BookingAnswer.MODULE_ID).toString();
            String strDateModuleStart = record.getField(BookingAnswer.MODULE_START_DATE).toString();
            String strTourOrOption = TourHeaderOption.OPTION;
            String strTourOrOptionID = record.getField(BookingAnswer.TOUR_HEADER_OPTION_ID).toString();
            Converter fieldConverter = null;
            int iDisplayFieldDesc = ScreenConstants.DISPLAY_MODE | ScreenConstants.DONT_DISPLAY_FIELD_DESC;
            if ((iCommandOptions & ScreenConstants.USE_NEW_WINDOW) == ScreenConstants.USE_SAME_WINDOW)  // Use same window
                this.free();
            new BookingAnswerGridScreen(recBookingPax, strModuleID, strTourOrOption, strTourOrOptionID, strDateModuleStart, null, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, null);
            return true;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BookingAnswer.kBookingAnswerFile).getField(BookingAnswer.kSelected).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingAnswer.kBookingAnswerFile).getField(BookingAnswer.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
