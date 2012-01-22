/**
 * @(#)BookingCalendar.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.calendar;

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
import org.jbundle.util.calendarpanel.model.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.entry.detail.base.*;
import com.tourapp.tour.booking.entry.base.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.record.*;
import org.jbundle.thin.base.screen.cal.popup.*;
import org.jbundle.base.screen.view.swing.*;
import org.jbundle.util.calendarpanel.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.trans.db.*;
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.cruise.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.product.item.db.*;
import org.jbundle.base.screen.model.calendar.*;
import javax.swing.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.thin.app.booking.entry.*;

/**
 *  BookingCalendar - Booking entry sub-screen to manipulate itinerary using a calendar screen.
 */
public class BookingCalendar extends CalendarScreen
{
    /**
     * Default constructor.
     */
    public BookingCalendar()
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
    public BookingCalendar(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.CALENDAR_SCREEN));
        if (!(parentScreen instanceof BookingHeaderScreen))
        {
            Record recMain = null;
            if (record instanceof Booking)
            {
                recMain = record;
                record = null;
            }
            parentScreen = new BookingHeaderScreen(recMain, itsLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            itsLocation = parentScreen.getNextLocation(ScreenConstants.FLUSH_LEFT, ScreenConstants.FILL_REMAINDER);
        }
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking entry sub-screen to manipulate itinerary using a calendar screen";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingDetail(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BookingDetailScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        
        BookingDetail record = (BookingDetail)this.getMainRecord();
        record.setKeyArea(BookingDetail.kBookingIDKey);
        record.addDetailBehaviors(recBooking, recTour);
        record.addListener(new CompareFileFilter(BookingDetail.kProductStatusID, Integer.toString(ProductStatus.CANCELED), FileListener.NOT_EQUAL, null, true));
        record.addListener(new CompareFileFilter(BookingDetail.kProductTypeID, Integer.toString(ProductType.ITEM_ID), FileListener.NOT_EQUAL, null, true));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolbar = super.addToolbars();
        ((BookingHeaderScreen)this.getParentScreen()).addHeaderToolbars(this);
        return toolbar;    // No toolbar (not even the default toolbars
    }
    /**
     * GetStartDate Method.
     */
    public Date getStartDate()
    {
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        if (recTour != null)
            return ((DateTimeField)recTour.getField(Tour.kDepartureDate)).getDateTime();
        return super.getStartDate();
    }
    /**
     * GetSelectDate Method.
     */
    public Date getSelectDate()
    {
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        if (recTour != null)
            return ((DateTimeField)recTour.getField(Tour.kDepartureDate)).getDateTime();
        return super.getSelectDate();
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        ResourceBundle resources = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.BOOKING_RESOURCE, true);
        
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.ANCHOR_DEFAULT), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.TOUR, ProductType.TOUR, resources.getString(ProductType.TOUR));
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.AIR, ProductType.AIR, resources.getString(ProductType.AIR));
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.HOTEL, ProductType.HOTEL, resources.getString(ProductType.HOTEL));
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.LAND, ProductType.LAND, resources.getString(ProductType.LAND));
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.CAR, ProductType.CAR, resources.getString(ProductType.CAR));
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.TRANSPORTATION, ProductType.TRANSPORTATION, resources.getString(ProductType.TRANSPORTATION));
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.CRUISE, ProductType.CRUISE, resources.getString(ProductType.CRUISE));
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, DBConstants.BLANK, Booking.BUTTON_LOCATION + ProductType.ITEM, ProductType.ITEM, resources.getString(ProductType.ITEM));
    }
    /**
     * Get the CalendarItem for this record.
     */
    public CalendarItem getCalendarItem(Rec fieldList)
    {
        return new BookingCalendarItem(this, -1, 0, 1, 2, -1);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        // This is not used as get item is implemented, but this is needed for propert toolbar alignment
        this.getMainRecord().getField(BookingDetail.kDetailDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kDetailEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kProductID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kProductStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        
        if (this.getScreenFieldView().getControl() instanceof CalendarPanel)
        {   // Always
            CalendarPanel calpanel = (CalendarPanel)this.getScreenFieldView().getControl();
            BaseApplet applet = (BaseApplet)this.getAppletScreen().getScreenFieldView().getControl();
            calpanel.setPopupComponent(new JPopupPanel(applet, (VScreenField)this.getScreenFieldView()));
        }
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
        boolean bSuccess = super.doCommand(strCommand, sourceSField, iCommandOptions);
        iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER;
        Product record = null;
        Map<String,Object> properties = new Hashtable<String,Object>();
        
        if (ProductType.TOUR.equalsIgnoreCase(strCommand))
        {
            record = new TourHeader(this);
        }
        else if (ProductType.AIR.equalsIgnoreCase(strCommand))
        {
            record = new Air(this);
            this.setThisProperty(properties, Tour.kAirRateID, BookingDetail.kRateID);
            this.setThisProperty(properties, Tour.kAirClassID, BookingDetail.kClassID);
        }
        else if (ProductType.HOTEL.equalsIgnoreCase(strCommand))
        {
            record = new Hotel(this);
            this.setThisProperty(properties, Tour.kHotelClassID, BookingDetail.kClassID);
            this.setThisProperty(properties, Tour.kHotelRateID, BookingDetail.kRateID);
        }
        else if (ProductType.LAND.equals(strCommand))
        {
            record = new Land(this);
            if (this.getRecord(Booking.kBookingFile).getField(Booking.kPax).getValue() > 0)
                properties.put(this.getRecord(Booking.kBookingFile).getField(Booking.kPax).getFieldName(), this.getRecord(Booking.kBookingFile).getField(Booking.kPax).toString());
            this.setThisProperty(properties, Tour.kLandRateID, BookingDetail.kRateID);
            this.setThisProperty(properties, Tour.kLandClassID, BookingDetail.kClassID);
        }
        else if (ProductType.TRANSPORTATION.equalsIgnoreCase(strCommand))
        {
            record = new Transportation(this);
            if (this.getRecord(Booking.kBookingFile).getField(Booking.kPax).getValue() > 0)
                properties.put(this.getRecord(Booking.kBookingFile).getField(Booking.kPax).getFieldName(), this.getRecord(Booking.kBookingFile).getField(Booking.kPax).toString());
            this.setThisProperty(properties, Tour.kTransportationRateID, BookingDetail.kRateID);
            this.setThisProperty(properties, Tour.kTransportationClassID, BookingDetail.kClassID);
        }
        else if (ProductType.CAR.equalsIgnoreCase(strCommand))
        {
            record = new Car(this);
            this.setThisProperty(properties, Tour.kCarRateID, BookingDetail.kRateID);
            this.setThisProperty(properties, Tour.kCarClassID, BookingDetail.kClassID);
        }
        else if (ProductType.CRUISE.equalsIgnoreCase(strCommand))
        {
            record = new Cruise(this);
            this.setThisProperty(properties, Tour.kCruiseRateID, BookingDetail.kRateID);
            this.setThisProperty(properties, Tour.kCruiseClassID, BookingDetail.kClassID);
        }
        else if (ProductType.ITEM.equalsIgnoreCase(strCommand))
        {
            record = new Item(this);
            this.setThisProperty(properties, Tour.kItemRateID, BookingDetail.kRateID);
            this.setThisProperty(properties, Tour.kItemClassID, BookingDetail.kClassID);
        }
        
        Record screenRecord = null;
        Record recBooking = this.getRecord(Booking.kBookingFile);
        if ((recBooking != null)
            && (recBooking.getRecordOwner() != null)
                && (recBooking.getRecordOwner().getScreenRecord() instanceof BookingScreenRecord))
                    screenRecord = recBooking.getRecordOwner().getScreenRecord();
        
        if (record != null)
        {       // Set the default target date.
            if (this.getScreenFieldView().getControl() instanceof CalendarPanel)
            {   // Always
                CalendarPanel panel = (CalendarPanel)this.getScreenFieldView().getControl();
                Date date = null;
                String strCityID = null;
                if (panel.getSelectedPane() != null)
                {
                    date = panel.getSelectedPane().getThisDate();
                }
                else if (panel.getSelectedItem() != null)
                {
                    date = panel.getSelectedItem().getItem().getEndDate();
                    if (date == null)
                        date = panel.getSelectedItem().getItem().getStartDate();
                }
                if (date == null)
                    if (screenRecord != null)
                    date = ((DateTimeField)screenRecord.getField(BookingScreenRecord.kLastDate)).getDateTime();
                if (date == null)
                    date = ((DateTimeField)this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate)).getDateTime();
                Converter.initGlobals();
                if (date != null)
                {
                    Calendar calendar = Converter.gCalendar;
                    calendar.setTime(date);
                    if (calendar.get(Calendar.HOUR_OF_DAY) == 0)
                        if (calendar.get(Calendar.MINUTE) == 0)
                        if (calendar.get(Calendar.SECOND) == 0)
                        if (calendar.get(Calendar.MILLISECOND) == 0)
                    {       // Default time should be noon
                        calendar.set(Calendar.HOUR_OF_DAY, 12);
                        date = calendar.getTime();
                    }
                    properties.put(this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kDetailDate).getFieldName(), date.toString());
                }
            }
            
            if (screenRecord != null)
                if (!screenRecord.getField(BookingScreenRecord.kLastCityID).isNull())
                    properties.put(record.getField(Product.kCityID).getFieldName(), screenRecord.getField(BookingScreenRecord.kLastCityID).toString());
        
            this.removeRecord(record);    // Remove it from the recordowner's list    record.setRecordOwner(null);
            record.addListener(new OnSelectHandler(this, DBConstants.USER_DEFINED_TYPE)
            {
                public RecordMessage createMessage(Object bookmark)
                {
                    RecordMessage message = super.createMessage(bookmark);
                    if (message != null)
                    {
                        Record recProduct = getOwner();
                        RecordOwner gridScreen = recProduct.getRecordOwner();
                        Record screenRecord = gridScreen.getScreenRecord();
                        this.addMessageProperty(message, screenRecord, ProductScreenRecord.kDetailDate);
                        this.addMessageProperty(message, screenRecord, ProductScreenRecord.kClassID);
                        this.addMessageProperty(message, screenRecord, ProductScreenRecord.kRateID);
                    }
                    return message;
                }
                public void addMessageProperty(BaseMessage message, Record screenRecord, int iFieldSeq)
                {
                    if (!screenRecord.getField(iFieldSeq).isNull())
                        message.put(screenRecord.getField(iFieldSeq).getFieldName(), screenRecord.getField(iFieldSeq).toString());
                }
            });
        
            ScreenLocation itsLocation = null;
            BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
            boolean bReadCurrentRecord = false;
            boolean bUseBaseTable = false;
            boolean bLinkGridToQuery = false;
            boolean bCloneThisQuery = false;
            BasePanel screen = (BasePanel)record.makeScreen(itsLocation, parentScreen, ScreenConstants.SELECT_MODE, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, properties);
            return true;    // Success
        }
        return bSuccess;
    }
    /**
     * This is property in this property object.
     */
    public void setThisProperty(Map<String,Object> properties, int iTourFieldSeq, int iParamFieldSeq)
    {
        if (!this.getRecord(Tour.kTourFile).getField(iTourFieldSeq).isNull())
            properties.put(this.getRecord(BookingDetail.kBookingDetailFile).getField(iParamFieldSeq).getFieldName(), this.getRecord(Tour.kTourFile).getField(iTourFieldSeq).toString());
    }
    /**
     * A record with this datasource handle changed, notify any behaviors that are checking.
     * NOTE: Be very careful as this code is running in an independent thread
     * (synchronize to the task before calling record calls).
     * NOTE: For now, you are only notified of the main record changes.
     * @param message The message to handle.
     * @return The error code.
     */
    public int handleMessage(BaseMessage message)
    {
        int iErrorCode = super.handleMessage(message);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            if (message instanceof RecordMessage)
        {
            String strRecordName = (String)message.getMessageHeader().get(RecordMessageHeader.TABLE_NAME);
            Object bookmark = ((RecordMessageHeader)message.getMessageHeader()).getBookmark(DBConstants.BOOKMARK_HANDLE);
            BookingDetail recBookingDetail = (BookingDetail)this.getMainRecord();
        
            ProductType recProductType = (ProductType)this.getRecord(ProductType.kProductTypeFile);
            if (recProductType == null)
                recProductType = new ProductType(this);
            recBookingDetail = (BookingDetail)recBookingDetail.createSharedRecord(new Integer(recProductType.getProductTypeIDFromName(strRecordName)), this);
            if (recBookingDetail != null)
            {   // Now I have the specific detail record to add
                try {
                    Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
                    Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
                    recBookingDetail.addDetailBehaviors(recBooking, recTour);
                    recBookingDetail.setOpenMode(recBookingDetail.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
                    recBookingDetail.addNew();
                    this.setDetailProperty(message, recBookingDetail, BookingDetail.kDetailDate);
                    Calendar calendar = ((DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate)).getCalendar();
                    if (calendar != null)
                        if (calendar.get(Calendar.HOUR_OF_DAY) == 0)
                            if (calendar.get(Calendar.MINUTE) == 0)
                            if (calendar.get(Calendar.SECOND) == 0)
                            if (calendar.get(Calendar.MILLISECOND) == 0)
                        {       // Default time should be noon
                            calendar.set(Calendar.HOUR_OF_DAY, 12);
                            ((DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate)).setCalendar(calendar, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                        }
                    this.setDetailProperty(message, recBookingDetail, BookingDetail.kRateID);
                    this.setDetailProperty(message, recBookingDetail, BookingDetail.kClassID);
                    recBookingDetail.getField(BookingDetail.kProductID).setString(bookmark.toString());
                    recBookingDetail.add();
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    recBookingDetail.free();
                }
            }
        }
        return iErrorCode;
    }
    /**
     * SetDetailProperty Method.
     */
    public void setDetailProperty(BaseMessage message, Record record, int iFieldSeq)
    {
        String strParamName = record.getField(iFieldSeq).getFieldName();
        if (message.get(strParamName) != null)
        {
            if (message.get(strParamName) instanceof String)
                record.getField(iFieldSeq).setString((String)message.get(strParamName));
            else
                record.getField(iFieldSeq).setData(message.get(strParamName));
        }
    }

}
