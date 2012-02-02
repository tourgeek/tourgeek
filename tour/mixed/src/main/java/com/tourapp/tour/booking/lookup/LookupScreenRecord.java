/**
 * @(#)LookupScreenRecord.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.user.db.*;
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  LookupScreenRecord - Screen Fields.
 */
public class LookupScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String QUERY_KEY = "QueryKey";
    public static final int kQueryKey = kScreenRecordLastField + 1;
    public static final String START_TARGET_FIELD = "StartTargetField";
    public static final int kStartTargetField = kQueryKey + 1;
    public static final String START_BK_DAYS = "StartBkDays";
    public static final int kStartBkDays = kStartTargetField + 1;
    public static final String CURRENT_AGENT = "CurrentAgent";
    public static final int kCurrentAgent = kStartBkDays + 1;
    public static final String START_BK_DATE = "StartBkDate";
    public static final int kStartBkDate = kCurrentAgent + 1;
    public static final String BK_DISPLAY_TYPE = "BkDisplayType";
    public static final int kBkDisplayType = kStartBkDate + 1;
    public static final String TOUR_HDR_BROCHURE_ID = "TourHdrBrochureID";
    public static final int kTourHdrBrochureID = kBkDisplayType + 1;
    public static final String TOUR_HDR_AIRLINE_CODE = "TourHdrAirlineCode";
    public static final int kTourHdrAirlineCode = kTourHdrBrochureID + 1;
    public static final String TOUR_HDR_TOUR_TYPE = "TourHdrTourType";
    public static final int kTourHdrTourType = kTourHdrAirlineCode + 1;
    public static final String TOUR_HDR_START_DATE = "TourHdrStartDate";
    public static final int kTourHdrStartDate = kTourHdrTourType + 1;
    public static final String TOUR_HDR_END_DATE = "TourHdrEndDate";
    public static final int kTourHdrEndDate = kTourHdrStartDate + 1;
    public static final String START_HDR_DAYS = "StartHdrDays";
    public static final int kStartHdrDays = kTourHdrEndDate + 1;
    public static final String END_HDR_DAYS = "EndHdrDays";
    public static final int kEndHdrDays = kStartHdrDays + 1;
    public static final String BOOKING_LIST_FORMAT = "BookingListFormat";
    public static final int kBookingListFormat = kEndHdrDays + 1;
    public static final String BOOKING_STATUS_ID = "BookingStatusID";
    public static final int kBookingStatusID = kBookingListFormat + 1;
    public static final String TOUR_HEADER_ID = "TourHeaderID";
    public static final int kTourHeaderID = kBookingStatusID + 1;
    public static final String TOUR_STATUS_ID = "TourStatusID";
    public static final int kTourStatusID = kTourHeaderID + 1;
    public static final String DEPARTURE_DATE = "DepartureDate";
    public static final int kDepartureDate = kTourStatusID + 1;
    public static final String START_TARGET_DATE = "StartTargetDate";
    public static final int kStartTargetDate = kDepartureDate + 1;
    public static final String END_TARGET_DATE = "EndTargetDate";
    public static final int kEndTargetDate = kStartTargetDate + 1;
    public static final String PRODUCT_TYPE_ID = "ProductTypeID";
    public static final int kProductTypeID = kEndTargetDate + 1;
    public static final String VENDOR_ID = "VendorID";
    public static final int kVendorID = kProductTypeID + 1;
    public static final int kLookupScreenRecordLastField = kVendorID;
    public static final int kLookupScreenRecordFields = kVendorID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public LookupScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LookupScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kLookupScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kQueryKey)
            field = new ShortField(this, "QueryKey", 4, null, null);
        if (iFieldSeq == kStartTargetField)
            field = new StringField(this, "StartTargetField", 10, null, null);
        if (iFieldSeq == kStartBkDays)
            field = new DoubleField(this, "StartBkDays", 15, null, new Double(-30));
        if (iFieldSeq == kCurrentAgent)
            field = new UserFilter(this, "CurrentAgent", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartBkDate)
            field = new DateField(this, "StartBkDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBkDisplayType)
            field = new DisplayTypeField(this, "BkDisplayType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHdrBrochureID)
            field = new BrochureField(this, "TourHdrBrochureID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHdrAirlineCode)
            field = new AirlineField(this, "TourHdrAirlineCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHdrTourType)
            field = new TourTypeSelect(this, "TourHdrTourType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHdrStartDate)
            field = new DateField(this, "TourHdrStartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHdrEndDate)
            field = new DateField(this, "TourHdrEndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartHdrDays)
            field = new DoubleField(this, "StartHdrDays", 15, null, null);
        if (iFieldSeq == kEndHdrDays)
            field = new DoubleField(this, "EndHdrDays", 15, null, null);
        if (iFieldSeq == kBookingListFormat)
            field = new LookupScreenRecord_BookingListFormat(this, "BookingListFormat", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBookingStatusID)
            field = new BookingStatusSelect(this, "BookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHeaderID)
            field = new TourHeaderSelect(this, "TourHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourStatusID)
            field = new TourStatusSelect(this, "TourStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepartureDate)
            field = new DateField(this, "DepartureDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartTargetDate)
            field = new DateField(this, "StartTargetDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndTargetDate)
            field = new DateField(this, "EndTargetDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductTypeID)
            field = new ProductTypeSelect(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorID)
            field = new VendorSelect(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLookupScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getField(LookupScreenRecord.BK_DISPLAY_TYPE).addListener(new SwitchBookingScreenHandler(null));
        
        this.getField(LookupScreenRecord.BOOKING_LIST_FORMAT).addListener(new RegisterValueHandler(null));
        int nCommandID = (int)this.getField(LookupScreenRecord.BOOKING_LIST_FORMAT).getValue();
        
        this.getField(LookupScreenRecord.QUERY_KEY).addListener(new RegisterValueHandler(null));
        this.getField(LookupScreenRecord.CURRENT_AGENT).addListener(new RegisterValueHandler(null));
        
        this.getField(LookupScreenRecord.START_BK_DAYS).addListener(new RegisterValueHandler(null));
        this.getField(LookupScreenRecord.START_BK_DATE).addListener(new InitDateOffsetHandler(this.getField(LookupScreenRecord.START_BK_DAYS), 0, 0, 0, false));
        this.getField(LookupScreenRecord.START_BK_DATE).initField(DBConstants.DISPLAY);  // Recompute new initial value
        this.getField(LookupScreenRecord.START_BK_DATE).addListener(new ReComputeTimeOffsetHandler(LookupScreenRecord.START_BK_DAYS));
        
        this.getField(LookupScreenRecord.START_HDR_DAYS).addListener(new RegisterValueHandler(null));    
        this.getField(LookupScreenRecord.TOUR_HDR_START_DATE).addListener(new InitDateOffsetHandler(this.getField(LookupScreenRecord.START_HDR_DAYS), 0, 0, 0));
        this.getField(LookupScreenRecord.TOUR_HDR_START_DATE).initField(DBConstants.DISPLAY);   // Recompute new initial value
        this.getField(LookupScreenRecord.TOUR_HDR_START_DATE).addListener(new ReComputeTimeOffsetHandler(LookupScreenRecord.START_HDR_DAYS));
        
        this.getField(LookupScreenRecord.END_HDR_DAYS).addListener(new RegisterValueHandler(null));  
        this.getField(LookupScreenRecord.TOUR_HDR_END_DATE).addListener(new ReComputeTimeOffsetHandler(LookupScreenRecord.END_HDR_DAYS));
    }
    /**
     * AddStandardToolbar Method.
     */
    public ToolScreen addStandardToolbar(BasePanel parentScreen)
    {
        ToolScreen toolScreen = new EmptyToolbar(null, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        toolScreen.setupStartSFields();     // Back button
        toolScreen.setupDisplaySFields();
        //x new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Booking entry", Booking.BUTTON_LOCATION + "Booking", "Booking entry", null);
        toolScreen.setupEndSFields();
        this.getField(LookupScreenRecord.BK_DISPLAY_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.START_TARGET_FIELD).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.TOUR_HDR_START_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        return toolScreen;
    }
    /**
     * Add the standard toolbar for booking display.
     */
    public ToolScreen addBookingToolbar(BasePanel parentScreen)
    {
        ToolScreen toolScreen = new EmptyToolbar(null, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        this.getField(LookupScreenRecord.START_BK_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.CURRENT_AGENT).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.TOUR_HDR_END_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.TOUR_STATUS_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.BOOKING_STATUS_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        return toolScreen;
    }
    /**
     * Add the tour departure toolbar.
     */
    public ToolScreen addTourToolbar(BasePanel parentScreen)
    {
        ToolScreen toolScreen = new EmptyToolbar(null, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        this.getField(LookupScreenRecord.TOUR_HDR_END_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.TOUR_HDR_TOUR_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        TourHeader recTourHeader = (TourHeader)((ReferenceField)this.getField(LookupScreenRecord.TOUR_HEADER_ID)).getReferenceRecord();
        QueryConverter tourHdrConv = new QueryConverter((ReferenceField)this.getField(LookupScreenRecord.TOUR_HEADER_ID), recTourHeader, TourHeader.DESCRIPTION, true);
        BaseField fldDepartureDate = this.getField(LookupScreenRecord.DEPARTURE_DATE);
        BaseField fldStartDate = this.getField(LookupScreenRecord.START_TARGET_DATE);
        BaseField fldEndDate = this.getField(LookupScreenRecord.END_TARGET_DATE);
        recTourHeader.createTourHeaderPopup(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, tourHdrConv, ScreenConstants.DEFAULT_DISPLAY, fldDepartureDate, fldStartDate, fldEndDate, null);
        
        this.getField(LookupScreenRecord.TOUR_STATUS_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        return toolScreen;
    }
    /**
     * AddTourHdrToolbar Method.
     */
    public ToolScreen addTourHdrToolbar(BasePanel parentScreen)
    {
        ToolScreen toolScreen = new EmptyToolbar(null, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        this.getField(LookupScreenRecord.TOUR_HDR_BROCHURE_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.TOUR_HDR_AIRLINE_CODE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.TOUR_HDR_TOUR_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getField(LookupScreenRecord.TOUR_HDR_END_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.AT_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        return toolScreen;
    }

}
