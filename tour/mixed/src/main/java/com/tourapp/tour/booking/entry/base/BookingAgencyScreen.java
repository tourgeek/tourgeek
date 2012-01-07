/**
 * @(#)BookingAgencyScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.base;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.model.db.*;

/**
 *  BookingAgencyScreen - Booking Profile Information Entry.
 */
public class BookingAgencyScreen extends BookingSubScreen
{
    /**
     * Default constructor.
     */
    public BookingAgencyScreen()
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
    public BookingAgencyScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.AGENCY_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking Profile Information Entry";
    }
    /**
     * Get the main record for this screen.
     * @return The main record.
     */
    public Record getMainRecord()
    {
        return this.getRecord(Booking.kBookingFile);    // Booking file is the main file
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Profile(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Profile recProfile = (Profile)this.getRecord(Profile.kProfileFile);
        recBooking.addSecondProfile(recProfile);    // Agency Secondary logic
        recProfile.setOpenMode(DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        recProfile.addListener(new UpdateOnCloseHandler(null));   // Make sure profile information is updated
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Booking.kBookingFile).getField(Booking.kProfileCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        ScreenField sField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Booking.kBookingFile).getField(Booking.kProfileCode), ScreenConstants.DONT_DISPLAY_DESC, null, null, ThinMenuConstants.LOOKUP, ThinMenuConstants.LOOKUP, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(ThinMenuConstants.LOOKUP), this.getRecord(Profile.kProfileFile), null);
        sField.setRequestFocusEnabled(false);
        sField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Booking.kBookingFile).getField(Booking.kProfileCode), ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.FORM, MenuConstants.FORM, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(MenuConstants.FORM), this.getRecord(Profile.kProfileFile), null);
        sField.setRequestFocusEnabled(false);
        sField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, SCannedBox.CLEAR, SCannedBox.CLEAR, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(SCannedBox.CLEAR), null, null);
        sField.setRequestFocusEnabled(false);
        this.getRecord(Booking.kBookingFile).getField(Booking.kContact).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kGenericName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kAddressLine1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kAddressLine2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kCityOrTown).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kStateOrRegion).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kPostalCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kCountry).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kStdCommission).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.SET_ANCHOR), this, ScreenConstants.DISPLAY_FIELD_DESC);
        this.getRecord(Booking.kBookingFile).getField(Booking.kTel).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kFax).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kEmail).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kAffiliationID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kDateEntered).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kLanguageID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolbar = new EmptyToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        toolbar.setupStartSFields();   // Back button
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.SET_ANCHOR), toolbar, null, ScreenConstants.DEFAULT_DISPLAY, MenuConstants.LOOKUP);
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.SET_ANCHOR), toolbar, null, ScreenConstants.DEFAULT_DISPLAY, MenuConstants.FORM);
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolbar, null, ScreenConstants.DEFAULT_DISPLAY, MenuConstants.RESET);
        toolbar.setupEndSFields();   // Back button
        
        ((BookingHeaderScreen)this.getParentScreen()).addHeaderToolbars(this);
        
        return toolbar;
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
        Record recProfile = this.getRecord(Profile.kProfileFile);
        if (strCommand.equalsIgnoreCase(MenuConstants.LOOKUP))
        {
            BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
            GridScreen screen = (GridScreen)recProfile.makeScreen(null, parentScreen, ScreenConstants.SELECT_MODE, true, true, true, true, null);
            //x if (recProfile.getScreen() == null)
                screen.setSelectQuery(recProfile, false); // Since this record isn't linked to the screen, manually link it.
            return true;
        }
        else if (strCommand.equalsIgnoreCase(MenuConstants.FORM))
        {
            BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
            recProfile.makeScreen(null, parentScreen, ScreenConstants.MAINT_MODE, true, true, true, true, null);
            return true;
        }
        else if (strCommand.equalsIgnoreCase(SCannedBox.CLEAR))
        {
            for (int i = 0; i < this.getSFieldCount(); i++)
            {
                ScreenField sField = this.getSField(i);
                Convert field = sField.getConverter();
                if (field != null)
                    if (field.getField() != null)
                        field.getField().initField(true);
            }
            return true;
        }
        
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
