/**
  * @(#)OperationsBookingDetailGridScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.operations;

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
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  OperationsBookingDetailGridScreen - Review booking operation status.
 */
public class OperationsBookingDetailGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public OperationsBookingDetailGridScreen()
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
    public OperationsBookingDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Review booking operation status";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingDetailQuery(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new LookupScreenRecord(this);
    }
    /**
     * Does the current user have permission to access this screen.
     * @return NORMAL_RETURN if access is allowed, ACCESS_DENIED or LOGIN_REQUIRED otherwise.
     */
    public int checkSecurity()
    {
        int iErrorCode = super.checkSecurity();
        if (iErrorCode == DBConstants.NORMAL_RETURN)
        {   // Okay, their group can access this screen, but can this user access this data?
            String strUserContactType = this.getProperty(DBParams.CONTACT_TYPE);
            String strUserContactID = this.getProperty(DBParams.CONTACT_ID);
        
            String strContactID = this.getScreenRecord().getField(LookupScreenRecord.VENDOR_ID).toString();
            if (Vendor.VENDOR_FILE.equalsIgnoreCase(strUserContactType))
            {
                if ((strContactID == null) || (strContactID.length() == 0))
                    if ((strUserContactID != null) && (strUserContactID.length() > 0))
                        this.getScreenRecord().getField(LookupScreenRecord.VENDOR_ID).setString(strContactID = strUserContactID);
                iErrorCode = this.checkContactSecurity(Vendor.VENDOR_FILE, strContactID);
            }
        }
        return iErrorCode;
    }
    /**
     * IsContactDisplay Method.
     */
    public boolean isContactDisplay()
    {
        String strUserContactType = this.getProperty(DBParams.CONTACT_TYPE);
        String strUserContactID = this.getProperty(DBParams.CONTACT_ID);
        
        String strContactID = this.getScreenRecord().getField(LookupScreenRecord.VENDOR_ID).toString();
        
        if ((strUserContactID != null) && (strUserContactID.equals(strContactID)))
            if (Vendor.VENDOR_FILE.equalsIgnoreCase(strUserContactType))
                return true;
        return false;
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        BookingDetail recBookingDetail = (BookingDetail)this.getRecord(BookingDetail.BOOKING_DETAIL_FILE);
        Booking recBooking = (Booking)this.getRecord(Booking.BOOKING_FILE);
        Tour recTour = (Tour)this.getRecord(Tour.TOUR_FILE);
        Record screenRecord = this.getScreenRecord();
        
        SortOrderHandler behQueryKeyHandler = new SortOrderHandler(this)
        {
            public int setupGridOrder()
            {
                int iErrorCode = super.setupGridOrder();
                
                if (m_recGrid != null)
                {
                    if ((m_recGrid.getKeyArea().getKeyName().equals(m_recGrid.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.DETAIL_DATE).getFieldName()))
                        && (!getScreenRecord().getField(LookupScreenRecord.VENDOR_ID).isNull()))
                            m_recGrid.setKeyArea(m_iKeyAreaArray.length - 1);   // Vendor key
                    else if ((m_recGrid.getKeyArea().getKeyName().equals(m_recGrid.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.VENDOR_ID).getFieldName()))
                        && (getScreenRecord().getField(LookupScreenRecord.VENDOR_ID).isNull()))
                            m_recGrid.setKeyArea(2);   // DetailDate key
                }
        
                return iErrorCode;
            }
        };
        behQueryKeyHandler.setGridTable(BookingDetail.ID_KEY, recBookingDetail, -1);
        behQueryKeyHandler.setGridTable(BookingDetail.DETAIL_DATE_KEY, recBookingDetail, -1);
        behQueryKeyHandler.setGridTable(Tour.DESCRIPTION_KEY, recTour, -1);
        behQueryKeyHandler.setGridTable(Tour.DEPARTURE_DATE_KEY, recTour, -1);
        behQueryKeyHandler.setGridTable(Booking.CODE_KEY, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.CODE_KEY, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.DESCRIPTION_KEY, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.BOOKING_DATE_KEY, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.BOOKING_DATE_KEY, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.BOOKING_DATE_KEY, recBooking, -1);
        behQueryKeyHandler.setGridTable(BookingDetail.VENDOR_ID_KEY, recBookingDetail, -1);
        
        if (this.isContactDisplay())
            screenRecord.getField(LookupScreenRecord.QUERY_KEY).setValue(11);   // Vendor key
        
        screenRecord.getField(LookupScreenRecord.QUERY_KEY).addListener(behQueryKeyHandler);
        
        this.getMainRecord().addListener(new CompareFileFilter(recBooking.getField(Booking.EMPLOYEE_ID), screenRecord.getField(LookupScreenRecord.CURRENT_AGENT), "=", null, true));
        screenRecord.getField(LookupScreenRecord.CURRENT_AGENT).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recBooking.getField(Booking.BOOKING_DATE), screenRecord.getField(LookupScreenRecord.START_BK_DATE), ">=", null, true));
        screenRecord.getField(LookupScreenRecord.START_BK_DATE).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.DEPARTURE_DATE), screenRecord.getField(LookupScreenRecord.TOUR_HDR_START_DATE), ">=", null, true));
        screenRecord.getField(LookupScreenRecord.TOUR_HDR_START_DATE).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.DEPARTURE_DATE), screenRecord.getField(LookupScreenRecord.TOUR_HDR_END_DATE), "<=", null, true));
        screenRecord.getField(LookupScreenRecord.TOUR_HDR_END_DATE).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new StartSearchFilter(screenRecord.getField(LookupScreenRecord.START_TARGET_FIELD)));
        
        this.getMainRecord().addListener(new CompareFileFilter(recBooking.getField(Booking.BOOKING_STATUS_ID), screenRecord.getField(LookupScreenRecord.BOOKING_STATUS_ID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.BOOKING_STATUS_ID).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.TOUR_STATUS_ID), screenRecord.getField(LookupScreenRecord.TOUR_STATUS_ID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.TOUR_STATUS_ID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(BookingDetail.PRODUCT_TYPE_ID), screenRecord.getField(LookupScreenRecord.PRODUCT_TYPE_ID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.PRODUCT_TYPE_ID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.TOUR_HEADER_ID), screenRecord.getField(LookupScreenRecord.TOUR_HEADER_ID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.TOUR_HEADER_ID).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(BookingDetail.VENDOR_ID), screenRecord.getField(LookupScreenRecord.VENDOR_ID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.VENDOR_ID).addListener(new FieldReSelectHandler(this));
        screenRecord.getField(LookupScreenRecord.VENDOR_ID).addListener(new ChangeOnChangeHandler(this.getScreenRecord().getField(LookupScreenRecord.QUERY_KEY)));  // Check key
        
        this.setAppending(false);
        this.setEditing(false);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolScreen = new EmptyToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        toolScreen.setupStartSFields();     // Back button
        toolScreen.setupDisplaySFields();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Booking entry", Booking.BUTTON_LOCATION + "Booking", "Booking entry", null);
        toolScreen.setupEndSFields();
        if (!this.isContactDisplay())
        {
            this.getScreenRecord().getField(LookupScreenRecord.START_BK_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.BOOKING_STATUS_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.CURRENT_AGENT).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.TOUR_HDR_START_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.TOUR_STATUS_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.TOUR_HEADER_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.TOUR_HDR_END_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.PRODUCT_TYPE_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getScreenRecord().getField(LookupScreenRecord.VENDOR_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        }
        return toolScreen;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.STATUS_SUMMARY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.DETAIL_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.DESCRIPTION);
        converter = new FieldLengthConverter(converter, 30);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.BOOKING_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.BOOKING_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.BOOKING_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
