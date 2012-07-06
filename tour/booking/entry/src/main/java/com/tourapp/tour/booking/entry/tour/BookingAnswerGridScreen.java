/**
  * @(#)BookingAnswerGridScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
 *  BookingAnswerGridScreen - Booking Answer File.
 */
public class BookingAnswerGridScreen extends BookingSubGridScreen
{
    protected String m_strModuleID = null;
    protected String m_strModuleStartDate = null;
    protected DateTimeField m_fldModuleStart = null;
    protected BookingPax m_recBookingPax = null;
    protected String m_strTourOrOptionID = null;
    protected String m_strTourOrOption = TourHeaderOption.TOUR;
    public static final String MODULE_ID = "ModuleID";
    public static final String MODULE_START_DATE = "ModuleStartDate";
    public static final String TOUR_OR_OPTION_ID = "TourOrOptionID";
    public static final String TOUR_OR_OPTION = "TourOrOption";
    public static final String BOOKING_PAX_ID = "BookingPaxID";
    /**
     * Default constructor.
     */
    public BookingAnswerGridScreen()
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
    public BookingAnswerGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * BookingAnswerGridScreen Method.
     */
    public BookingAnswerGridScreen(BookingPax recBookingPax, String strModID, String strTourOrOpt, String strTourOrOptID, String strModStartDate, Record record, ScreenLocation itsLocation,
     BasePanel parentScreen, Converter fieldConv, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(recBookingPax, strModID, strTourOrOpt, strTourOrOptID, strModStartDate, record, itsLocation, parentScreen, fieldConv, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(BookingPax recBookingPax, String strModID, String strTourOrOpt, String strTourOrOptID, String strModStartDate, Record record, ScreenLocation itsLocation,
     BasePanel parentScreen, Converter fieldConv, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        m_strModuleID = null;
        m_strModuleStartDate = null;
        m_fldModuleStart = null;
        m_recBookingPax = null;
        m_strTourOrOptionID = null;
        m_recBookingPax = recBookingPax;
        m_strModuleID = strModID;
        m_strModuleStartDate = strModStartDate;
        m_strTourOrOption = strTourOrOpt;
        m_strTourOrOptionID = strTourOrOptID;
        super.init(record, itsLocation, parentScreen, fieldConv, iDisplayFieldDesc, properties);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_fldModuleStart != null)
            m_fldModuleStart.free();
        m_fldModuleStart = null;
        if (m_recBookingPax != null)
            if (m_recBookingPax.getRecordOwner() != this)
        {
            m_recBookingPax.free();
            m_recBookingPax = null;
        }
        super.free();
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
        this.getMainRecord().getField(BookingAnswer.DESCRIPTION).setEnabled(false);
        
        Record recTourHeader = this.getRecord(TourHeader.TOUR_HEADER_FILE);
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        m_fldModuleStart.setString(m_strModuleStartDate);
        
        this.getMainRecord().setKeyArea(BookingAnswer.BOOKING_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(recBooking.getField(Booking.ID), BookingAnswer.BOOKING_ID, m_recBookingPax.getField(BookingPax.ID), BookingAnswer.BOOKING_PAX_ID, recTourHeader.getField(TourHeader.ID), BookingAnswer.MODULE_ID));
        this.getMainRecord().addListener(new StringSubFileFilter(m_strTourOrOption, BookingAnswer.TOUR_OR_OPTION, m_strTourOrOptionID, BookingAnswer.TOUR_OR_OPTION_ID, null, null));
        this.getMainRecord().addListener(new SubFileFilter(m_fldModuleStart, BookingAnswer.MODULE_START_DATE, null, null, null, null));
        BooleanField fldTrue = new BooleanField(null, DBConstants.BLANK, DBConstants.DEFAULT_FIELD_LENGTH, DBConstants.BLANK, null);
        fldTrue.setState(true);
        this.getMainRecord().addListener(new FreeOnFreeHandler(fldTrue));
        this.getMainRecord().addListener(new CompareFileFilter(BookingAnswer.ASK_FOR_ANSWER, fldTrue, DBConstants.EQUALS, null, false));
        
        this.setAppending(false);
        
        this.getMainRecord().getField(BookingAnswer.SELECTED).addListener(new BookingAnswerSelectHandler(null));
    }
    /**
     * RestoreTheProperties Method.
     */
    public TourHeader restoreTheProperties()
    {
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        
        if (m_recBookingPax == null)
            m_recBookingPax = new BookingPax(this);
        if (m_recBookingPax.getField(BookingPax.ID).isNull())
            m_recBookingPax.getField(BookingPax.ID).setValue(0);
        if (this.getProperty(BookingAnswerGridScreen.TOUR_OR_OPTION) != null)
            m_strTourOrOption = this.getProperty(BookingAnswerGridScreen.TOUR_OR_OPTION);
        if (m_strTourOrOption == null)  // Never
            m_strTourOrOption = TourHeaderOption.TOUR;
        
        Record recTour = ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReference();
        
        if (this.getProperty(BookingAnswerGridScreen.MODULE_ID) != null)
            m_strModuleID = this.getProperty(BookingAnswerGridScreen.MODULE_ID);
        if (this.getProperty(BookingAnswerGridScreen.MODULE_START_DATE) != null)
            m_strModuleStartDate = this.getProperty(BookingAnswerGridScreen.MODULE_START_DATE);
        if (m_strModuleStartDate != null)
            m_fldModuleStart.setString(m_strModuleStartDate);
        if ((m_strModuleID != null)
            && (m_strModuleStartDate != null))
        {
            if ((!m_fldModuleStart.equals(recTour.getField(Tour.DEPARTURE_DATE)))
                || (!m_strModuleID.equals(recTourHeader.getField(TourHeader.ID).toString())))
            {
                recTourHeader = new TourHeader(this);
                recTourHeader.getField(TourHeader.ID).setString(m_strModuleID);
                try {
                    if (recTourHeader.seek(null))
                    {   // Always
        
                    }
                } catch (DBException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else
        {
            m_strModuleID = recTourHeader.getField(TourHeader.ID).toString();
            m_strModuleStartDate = recTour.getField(Tour.DEPARTURE_DATE).toString();
        }
        
        if (this.getProperty(BookingAnswerGridScreen.TOUR_OR_OPTION_ID) != null)
            m_strTourOrOptionID = this.getProperty(BookingAnswerGridScreen.TOUR_OR_OPTION_ID);
        if (m_strTourOrOptionID == null)
            m_strTourOrOptionID = m_strModuleID;
        
        return recTourHeader;
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        m_fldModuleStart = new DateTimeField(null, BookingAnswerGridScreen.MODULE_START_DATE, Constants.DEFAULT_FIELD_LENGTH, this.getMainRecord().getString(BookingAnswerGridScreen.MODULE_START_DATE), null);
        
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        TourHeader recTourHeader = this.restoreTheProperties();
        if (recTourHeader.getRecordOwner() == this)
        {
            recTourHeader.getField(TourHeader.DESCRIPTION).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            recTourHeader.getField(TourHeader.DESCRIPTION).setEnabled(false);
            m_fldModuleStart.setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            m_fldModuleStart.setEnabled(false);
        }
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BookingAnswer.BOOKING_ANSWER_FILE).getField(BookingAnswer.SELECTED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingAnswer.BOOKING_ANSWER_FILE).getField(BookingAnswer.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the command string to restore screen.
     */
    public String getScreenURL()
    {
        String strURL = super.getScreenURL();
        
        strURL = Utility.addURLParam(strURL, BookingAnswerGridScreen.MODULE_ID, m_strModuleID, false);
        strURL = Utility.addURLParam(strURL, BookingAnswerGridScreen.MODULE_START_DATE, m_strModuleStartDate, false);
        strURL = Utility.addURLParam(strURL, BookingAnswerGridScreen.TOUR_OR_OPTION, m_strTourOrOption, false);
        strURL = Utility.addURLParam(strURL, BookingAnswerGridScreen.TOUR_OR_OPTION_ID, m_strTourOrOptionID, false);
        strURL = Utility.addURLParam(strURL, BookingAnswerGridScreen.BOOKING_PAX_ID, m_recBookingPax.getField(BookingPax.ID).toString(), false);
        return strURL;
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
        
            BookingPax recBookingPax = m_recBookingPax;
            if (m_recBookingPax.getRecordOwner() == this)
                this.removeRecord(m_recBookingPax);
            if ((iCommandOptions & ScreenConstants.USE_NEW_WINDOW) == ScreenConstants.USE_SAME_WINDOW)  // Use same window
                m_recBookingPax = null;
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

}
