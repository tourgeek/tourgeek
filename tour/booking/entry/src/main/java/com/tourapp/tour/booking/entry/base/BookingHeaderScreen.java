/**
  * @(#)BookingHeaderScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.entry.base;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.booking.entry.pax.*;
import com.tourapp.tour.booking.entry.tour.*;
import com.tourapp.tour.booking.entry.detail.hotel.*;
import com.tourapp.tour.booking.entry.detail.land.*;
import com.tourapp.tour.booking.entry.acctpay.*;
import com.tourapp.tour.booking.entry.calendar.*;
import com.tourapp.tour.booking.entry.detail.detail.*;
import com.tourapp.tour.booking.entry.itin.*;
import com.tourapp.tour.booking.entry.detail.car.*;
import com.tourapp.tour.booking.entry.detail.trans.*;
import com.tourapp.tour.booking.entry.detail.cruise.*;
import com.tourapp.tour.booking.entry.detail.air.*;
import com.tourapp.tour.booking.entry.detail.item.*;
import com.tourapp.tour.booking.entry.detail.tour.*;

/**
 *  BookingHeaderScreen - Booking entry.
 */
public class BookingHeaderScreen extends Screen
{
    /**
     * Default constructor.
     */
    public BookingHeaderScreen()
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
    public BookingHeaderScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Does the current user have permission to access this screen.
     * @return NORMAL_RETURN if access is allowed, ACCESS_DENIED or LOGIN_REQUIRED otherwise.
     */
    public int checkSecurity()
    {
        if (this.getProperty(DBParams.HELP) != null)
            return DBConstants.NORMAL_RETURN; // Make sure I don't get a login required if this is a help (menu) screen.
        else
            return super.checkSecurity();
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
        new BookingControl(this);
        new ProfileControl(this);
        
        Record recBooking = this.getMainRecord();
        Record recTour = ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReferenceRecord(this);
        recTour.setOpenMode((recTour.getOpenMode() & ~DBConstants.OPEN_READ_ONLY) | DBConstants.OPEN_LOCK_ON_CHANGE_STRATEGY);
        Record recTourHeader = ((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReferenceRecord(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BookingScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.addMainKeyBehavior();
        Booking recBooking = (Booking)this.getMainRecord();
        Tour recTour = (Tour)this.getRecord(Tour.TOUR_FILE);
        if (!ScreenConstants.HTML_SCREEN_TYPE.equalsIgnoreCase(this.getViewFactory().getViewSubpackage()))
            recTour.setupRecordListener(this, false, false);   // I need to listen for record changes
        TourHeader recTourHdr = (TourHeader)this.getRecord(TourHeader.TOUR_HEADER_FILE);
        ReferenceField fldTourID = (ReferenceField)recBooking.getField(Booking.TOUR_ID);
        BookingControl recBookingControl = (BookingControl)this.getRecord(BookingControl.BOOKING_CONTROL_FILE);
        ProfileControl recProfileControl = (ProfileControl)this.getRecord(ProfileControl.PROFILE_CONTROL_FILE);
        
        recBooking.setOpenMode(recBooking.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        fldTourID.addListener(new ReadSecondaryHandler(recTour, Tour.ID_KEY, DBConstants.DONT_CLOSE_ON_FREE, true, true));  // Update record
        MoveOnValidHandler tourChangeHandler = (MoveOnValidHandler)recTour.getListener(MoveOnValidHandler.class);
        if (tourChangeHandler != null)
        {   // Always
            recTour.removeListener(tourChangeHandler, true);
            recTour.addListener(new TourChangeHandler(recBooking));
        }
        recTour.addListener(new DisplayReadHandler(Tour.TOUR_HEADER_ID, recTourHdr, TourHeader.ID));        
        recTour.getField(Tour.TOUR_HEADER_ID).addListener(new MainReadOnlyHandler(null));
        recBooking.addControlDefaults(recBookingControl, recProfileControl);
        
        this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).setData(null);  // Initial value
        FieldListener listener = new BookingScreenHandler(null, null, null);
        this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).addListener(listener);
        
        int iCurrentScreen = BookingScreenHandler.MENU_SCREEN;
        if (this.getProperty(BookingScreenHandler.SUB_SCREEN_PARAM) != null)
            iCurrentScreen = Integer.parseInt(this.getProperty(BookingScreenHandler.SUB_SCREEN_PARAM));  // Initial value
        listener.setEnabledListener(false); // Don't switch screens now
        this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).setValue(iCurrentScreen);  // Initial value
        listener.setEnabledListener(true);
        
        recTour.getField(Tour.DEPARTURE_DATE).addListener(listener = new MoveOnChangeHandler(this.getScreenRecord().getField(BookingScreenRecord.LAST_DATE), null, false, true));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
    }
    /**
     * Set this property.
     * @param strProperty The property key.
     * @param strValue The property value.
     */
    public void setProperty(String strProperty, String strValue)
    {
        super.setProperty(strProperty, strValue);
        if (BookingScreenHandler.SUB_SCREEN_PARAM.equalsIgnoreCase(strProperty))
        { // Special logic to synchronize the screen number with the actual screen number
        try {
                int value = Integer.parseInt(strValue);
                SwitchSubScreenHandler listener = (SwitchSubScreenHandler)this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).getListener(BookingScreenHandler.class);
                if (listener != null)
                    listener.setCurrentScreenNo(value);
                boolean[] rgbEnabled = this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).setEnableListeners(false);
                this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).setValue(value);
                this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).setEnableListeners(rgbEnabled);
            } catch (Exception ex) {
                // Ignore
            }
        }
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return null;    // No toolbar (not even the default toolbars
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        // Don't add any screen fields (not even the defaults)
    }
    /**
     * Add the header toolbars to the sub-screen.
     * @param screen The sub-screen to add the toolbars to.
     * @return The created toolbar.
     */
    public ToolScreen addHeaderToolbars(BasePanel screen)
    {
        ToolScreen toolbar = new EmptyToolbar(null, screen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        
        ResourceBundle resources = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.BOOKING_RESOURCE, true);
        
        BaseField field = this.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN);
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, resources.getString(BookingScreenHandler.MENU), Booking.BUTTON_LOCATION + BookingScreenHandler.MENU, Integer.toString(BookingScreenHandler.MENU_SCREEN), null);
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.SUMMARY, Integer.toString(BookingScreenHandler.SUMMARY_SCREEN), resources.getString(BookingScreenHandler.SUMMARY));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.AGENCY, Integer.toString(BookingScreenHandler.AGENCY_SCREEN), resources.getString(BookingScreenHandler.AGENCY));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.PASSENGER, Integer.toString(BookingScreenHandler.PAX_SCREEN), resources.getString(BookingScreenHandler.PASSENGER));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.TOUR, Integer.toString(BookingScreenHandler.TOUR_SCREEN), resources.getString(BookingScreenHandler.TOUR));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.PRICE, Integer.toString(BookingScreenHandler.LINE_SCREEN), resources.getString(BookingScreenHandler.PRICE));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.LINE, Integer.toString(BookingScreenHandler.AR_TRX_SCREEN), resources.getString(BookingScreenHandler.LINE));
        
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.OPTIONS, Integer.toString(BookingScreenHandler.OPTIONS_SCREEN), resources.getString(BookingScreenHandler.OPTIONS));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.CALENDAR, Integer.toString(BookingScreenHandler.CALENDAR_SCREEN), resources.getString(BookingScreenHandler.CALENDAR));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.DETAIL, Integer.toString(BookingScreenHandler.FIT_SCREEN), resources.getString(BookingScreenHandler.DETAIL));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.ITINERARY, Integer.toString(BookingScreenHandler.ITIN_SCREEN), resources.getString(BookingScreenHandler.ITINERARY));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + BookingScreenHandler.STATUS, Integer.toString(BookingScreenHandler.VOUCHER_SCREEN), resources.getString(BookingScreenHandler.STATUS));
        
        
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.TOUR, Integer.toString(BookingScreenHandler.TOUR_DETAIL_SCREEN), resources.getString(ProductType.TOUR));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.AIR, Integer.toString(BookingScreenHandler.AIR_SCREEN), resources.getString(ProductType.AIR));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.HOTEL, Integer.toString(BookingScreenHandler.HOTEL_SCREEN), resources.getString(ProductType.HOTEL));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.LAND, Integer.toString(BookingScreenHandler.LAND_SCREEN), resources.getString(ProductType.LAND));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.CAR, Integer.toString(BookingScreenHandler.CAR_SCREEN), resources.getString(ProductType.CAR));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.TRANSPORTATION, Integer.toString(BookingScreenHandler.TRANSPORTATION_SCREEN), resources.getString(ProductType.TRANSPORTATION));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.CRUISE, Integer.toString(BookingScreenHandler.CRUISE_SCREEN), resources.getString(ProductType.CRUISE));
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, field, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.ITEM, Integer.toString(BookingScreenHandler.ITEM_SCREEN), resources.getString(ProductType.ITEM));
        
        Converter converter = new AltFieldConverter(this.getRecord(Booking.BOOKING_FILE).getField(Booking.DESCRIPTION), this.getRecord(Tour.TOUR_FILE).getField(Tour.DESCRIPTION));
        converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_STATUS_ID).setupDefaultView(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolbar, ScreenConstants.DONT_DISPLAY_DESC);
        if (((TourStatusField)this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_STATUS_ID)).getIconField(null) != null)
            if (((TourStatusField)this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_STATUS_ID)).getIconField(null).getListener(TourStatusUpdateHandler.class) == null)
                ((TourStatusField)this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_STATUS_ID)).getIconField(null).addListener(new TourStatusUpdateHandler(null));   // Hack
        new SCannedBox(toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.DONT_SET_ANCHOR), toolbar, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.LOOKUP, BookingScreenHandler.BOOKING_LOOKUP, resources.getString(BookingScreenHandler.BOOKING_LOOKUP));
        
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
        if ((MenuConstants.RESET.equalsIgnoreCase(strCommand))
            || (MenuConstants.DELETE.equalsIgnoreCase(strCommand))
            || (MenuConstants.FIRST.equalsIgnoreCase(strCommand))
            || (MenuConstants.PREVIOUS.equalsIgnoreCase(strCommand))
            || (MenuConstants.NEXT.equalsIgnoreCase(strCommand))
            || (MenuConstants.LAST.equalsIgnoreCase(strCommand))
            || (MenuConstants.REQUERY.equalsIgnoreCase(strCommand))
            || (MenuConstants.SUBMIT).equalsIgnoreCase(strCommand))
                return false;   // I NEVER handle file a command (send it to the sub screen!)
        if (BookingScreenHandler.BOOKING_LOOKUP.equalsIgnoreCase(strCommand))
        {
            BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
            Record record = this.getMainRecord();
            Map<String,Object> properties = null;
            record.makeScreen(null, parentScreen, ScreenConstants.SELECT_MODE, true, true, true, true, properties);
            return true;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
