
package com.tourgeek.tour.booking.entry.base;

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
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.product.base.db.*;

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
        return this.getRecord(Booking.BOOKING_FILE);    // Booking file is the main file
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
        
        Booking recBooking = (Booking)this.getRecord(Booking.BOOKING_FILE);
        Profile recProfile = (Profile)this.getRecord(Profile.PROFILE_FILE);
        recBooking.addSecondProfile(recProfile);    // Agency Secondary logic
        recProfile.setOpenMode(DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        recProfile.addListener(new UpdateOnCloseHandler(null));   // Make sure profile information is updated
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.PROFILE_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        ScreenField sField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Booking.BOOKING_FILE).getField(Booking.PROFILE_CODE), ScreenConstants.DONT_DISPLAY_DESC, null, null, ThinMenuConstants.LOOKUP, ThinMenuConstants.LOOKUP, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(ThinMenuConstants.LOOKUP), this.getRecord(Profile.PROFILE_FILE), null);
        sField.setRequestFocusEnabled(false);
        sField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Booking.BOOKING_FILE).getField(Booking.PROFILE_CODE), ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.FORM, MenuConstants.FORM, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(MenuConstants.FORM), this.getRecord(Profile.PROFILE_FILE), null);
        sField.setRequestFocusEnabled(false);
        sField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, ScreenModel.CLEAR, ScreenModel.CLEAR, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(ScreenModel.CLEAR), null, null);
        sField.setRequestFocusEnabled(false);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.CONTACT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.GENERIC_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.ADDRESS_LINE_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.ADDRESS_LINE_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.CITY_OR_TOWN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.STATE_OR_REGION).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.POSTAL_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.COUNTRY).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.STD_COMMISSION).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.SET_ANCHOR), this, ScreenConstants.DISPLAY_FIELD_DESC);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.TEL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.FAX).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.EMAIL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.AFFILIATION_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.PROFILE_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.PROFILE_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.DATE_ENTERED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.LANGUAGE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.CURRENCYS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolbar = new EmptyToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
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
        Record recProfile = this.getRecord(Profile.PROFILE_FILE);
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
        else if (strCommand.equalsIgnoreCase(ScreenModel.CLEAR))
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
