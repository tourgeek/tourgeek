/**
 * @(#)OperationsBookingDetailGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.operations;

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
        
            String strContactID = this.getScreenRecord().getField(LookupScreenRecord.kVendorID).toString();
            if (Vendor.kVendorFile.equalsIgnoreCase(strUserContactType))
            {
                if ((strContactID == null) || (strContactID.length() == 0))
                    if ((strUserContactID != null) && (strUserContactID.length() > 0))
                        this.getScreenRecord().getField(LookupScreenRecord.kVendorID).setString(strContactID = strUserContactID);
                iErrorCode = this.checkContactSecurity(Vendor.kVendorFile, strContactID);
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
        
        String strContactID = this.getScreenRecord().getField(LookupScreenRecord.kVendorID).toString();
        
        if ((strUserContactID != null) && (strUserContactID.equals(strContactID)))
            if (Vendor.kVendorFile.equalsIgnoreCase(strUserContactType))
                return true;
        return false;
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        BookingDetail recBookingDetail = (BookingDetail)this.getRecord(BookingDetail.kBookingDetailFile);
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        Record screenRecord = this.getScreenRecord();
        
        SortOrderHandler behQueryKeyHandler = new SortOrderHandler(this)
        {
            public int setupGridOrder()
            {
                int iErrorCode = super.setupGridOrder();
                
                if (m_recGrid != null)
                {
                    if ((m_recGrid.getKeyArea().getKeyName().equals(m_recGrid.getField(BookingDetail.kBookingDetailFile, BookingDetail.kDetailDate).getFieldName()))
                        && (!getScreenRecord().getField(LookupScreenRecord.kVendorID).isNull()))
                            m_recGrid.setKeyArea(m_iKeyAreaArray.length - 1);   // Vendor key
                    else if ((m_recGrid.getKeyArea().getKeyName().equals(m_recGrid.getField(BookingDetail.kBookingDetailFile, BookingDetail.kVendorID).getFieldName()))
                        && (getScreenRecord().getField(LookupScreenRecord.kVendorID).isNull()))
                            m_recGrid.setKeyArea(2);   // DetailDate key
                }
        
                return iErrorCode;
            }
        };
        behQueryKeyHandler.setGridTable(BookingDetail.kIDKey, recBookingDetail, -1);
        behQueryKeyHandler.setGridTable(BookingDetail.kDetailDateKey, recBookingDetail, -1);
        behQueryKeyHandler.setGridTable(Tour.kDescriptionKey, recTour, -1);
        behQueryKeyHandler.setGridTable(Tour.kDepartureDateKey, recTour, -1);
        behQueryKeyHandler.setGridTable(Booking.kCodeKey, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.kCodeKey, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.kDescriptionKey, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.kBookingDateKey, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.kBookingDateKey, recBooking, -1);
        behQueryKeyHandler.setGridTable(Booking.kBookingDateKey, recBooking, -1);
        behQueryKeyHandler.setGridTable(BookingDetail.kVendorIDKey, recBookingDetail, -1);
        
        if (this.isContactDisplay())
            screenRecord.getField(LookupScreenRecord.kQueryKey).setValue(11);   // Vendor key
        
        screenRecord.getField(LookupScreenRecord.kQueryKey).addListener(behQueryKeyHandler);
        
        this.getMainRecord().addListener(new CompareFileFilter(recBooking.getField(Booking.kEmployeeID), screenRecord.getField(LookupScreenRecord.kCurrentAgent), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kCurrentAgent).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recBooking.getField(Booking.kBookingDate), screenRecord.getField(LookupScreenRecord.kStartBkDate), ">=", null, true));
        screenRecord.getField(LookupScreenRecord.kStartBkDate).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kDepartureDate), screenRecord.getField(LookupScreenRecord.kTourHdrStartDate), ">=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourHdrStartDate).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kDepartureDate), screenRecord.getField(LookupScreenRecord.kTourHdrEndDate), "<=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourHdrEndDate).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new StartSearchFilter(screenRecord.getField(LookupScreenRecord.kStartTargetField)));
        
        this.getMainRecord().addListener(new CompareFileFilter(recBooking.getField(Booking.kBookingStatusID), screenRecord.getField(LookupScreenRecord.kBookingStatusID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kBookingStatusID).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kTourStatusID), screenRecord.getField(LookupScreenRecord.kTourStatusID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourStatusID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(BookingDetail.kProductTypeID), screenRecord.getField(LookupScreenRecord.kProductTypeID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kProductTypeID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.kTourHeaderID), screenRecord.getField(LookupScreenRecord.kTourHeaderID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kTourHeaderID).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(BookingDetail.kVendorID), screenRecord.getField(LookupScreenRecord.kVendorID), "=", null, true));
        screenRecord.getField(LookupScreenRecord.kVendorID).addListener(new FieldReSelectHandler(this));
        screenRecord.getField(LookupScreenRecord.kVendorID).addListener(new ChangeOnChangeHandler(this.getScreenRecord().getField(LookupScreenRecord.kQueryKey)));  // Check key
        
        this.setAppending(false);
        this.setEditing(false);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolScreen = new EmptyToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        toolScreen.setupStartSFields();     // Back button
        toolScreen.setupDisplaySFields();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Booking entry", Booking.BUTTON_LOCATION + "Booking", "Booking entry", null);
        toolScreen.setupEndSFields();
        if (!this.isContactDisplay())
        {
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kStartBkDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kBookingStatusID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kCurrentAgent).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kTourHdrStartDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kTourStatusID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kTourHeaderID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kTourHdrEndDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kProductTypeID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(LookupScreenRecord.kLookupScreenRecordFile).getField(LookupScreenRecord.kVendorID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        }
        return toolScreen;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kStatusSummary).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kDetailDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kBookingDetailFile, BookingDetail.kDescription);
        converter = new FieldLengthConverter(converter, 30);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTourStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kBookingID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kBookingStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kBookingDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
