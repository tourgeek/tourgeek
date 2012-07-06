/**
  * @(#)BookingScreenHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.profile.db.*;
import org.jbundle.base.screen.model.util.*;
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
 *  BookingScreenHandler - Behavior to switch booking scr.
 */
public class BookingScreenHandler extends SwitchSubScreenHandler
{
    public static final String SUB_SCREEN_PARAM = "subScreen";
    public static final int MENU_SCREEN = 1;
    public final static int SUMMARY_SCREEN = 2;
    public final static int AGENCY_SCREEN = 3;
    public static final int PAX_SCREEN = 4;
    public final static int TOUR_SCREEN = 5;
    public final static int OPTIONS_SCREEN = 14;
    public final static int LINE_SCREEN = 6;
    public final static int AR_TRX_SCREEN = 7;
    public final static int FIT_SCREEN = 16;
    public final static int HOTEL_SCREEN = 8;
    public final static int LAND_SCREEN = 9;
    public final static int AIR_SCREEN = 10;
    public static final int AIR_HEADER_SCREEN = 21;
    public final static int CAR_SCREEN = 12;
    public final static int CRUISE_SCREEN = 13;
    public final static int TRANSPORTATION_SCREEN = 11;
    public static final int TOUR_DETAIL_SCREEN = 19;
    public static final int ITEM_SCREEN = 20;
    public final static int VOUCHER_SCREEN = 17;
    public final static int CALENDAR_SCREEN = 15;
    public final static int ITIN_SCREEN = 18;
    public static final String OPTIONS = "Options";
    public static final String INVENTORY = "Inventory";
    public static final String TOUR_SERIES = "(Series) Tours";
    public static final String MENU = "Menu";
    public static final String SUMMARY = "Summary";
    public static final String AGENCY = "Agency";
    public static final String PASSENGER = "Passenger";
    public static final String TOUR = "Tour";
    public static final String PRICE = "Price";
    public static final String LINE = "Line";
    public static final String CALENDAR = "Calendar";
    public static final String DETAIL = "Detail";
    public static final String ITINERARY = "Itinerary";
    public static final String STATUS = "Status";
    public static final String BOOKING_LOOKUP = "bookingLookup";
    /**
     * Default constructor.
     */
    public BookingScreenHandler()
    {
        super();
    }
    /**
     * BookingScreenHandler Method.
     */
    public BookingScreenHandler(BaseField field, BasePanel screenParent, BasePanel subScreen)
    {
        this();
        this.init(field, screenParent, subScreen);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * GetSubScreen Method.
     */
    public BasePanel getSubScreen(BasePanel parentScreen, ScreenLocation screenLocation, Map<String,Object> properties, int screenNo)
    {
        super.getSubScreen(parentScreen, screenLocation, properties, screenNo);
        int count = 0;
        while (parentScreen.popHistory(1, false) != null)
        {
            count++;
        }
        count--;    // Want to move back to the first one
        if (count > 0)
            parentScreen.popHistory(count, true);   // Dump all browser history
        BaseScreen screen = null;
        BaseField useAgency = null;
        switch(screenNo)
        {
        case MENU_SCREEN: // Menu
            screen = new BookingMenuScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case SUMMARY_SCREEN:    // Summary screen
            screen = new BookingSummaryScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case AGENCY_SCREEN:   // Agency Maint
            screen = new BookingAgencyScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case PAX_SCREEN:    // Pax display
            if ( parentScreen.getRecord(Booking.BOOKING_FILE).getField(Booking.PAX).getValue() == 0)
                screen = new BookingPaxScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);
            else
                screen = new BookingPaxGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);
            break;
        case TOUR_SCREEN: // Tour maint
            screen = new BookingTourScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case LINE_SCREEN: // Line maint
            screen = new BookingLineGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case AR_TRX_SCREEN:   // A/R display
            screen = new BookingArTrxGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case AIR_HEADER_SCREEN:   // Air maint
            screen = new BookingAirHeaderGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case AIR_SCREEN:    // Air maint
            screen = new BookingAirGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case HOTEL_SCREEN:  // Hotel maint
            screen = new BookingHotelGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case LAND_SCREEN: // Land maint
            screen = new BookingLandGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case TRANSPORTATION_SCREEN: // Transportation maint
            screen = new BookingTransportationGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case CAR_SCREEN:    // Car maint
            screen = new BookingCarGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case CRUISE_SCREEN:   // Cruise maint
            screen = new BookingCruiseGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case ITEM_SCREEN: // Item maint
            screen = new BookingItemGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case TOUR_DETAIL_SCREEN:    // Tour detail
            screen = new BookingTourDetailGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case OPTIONS_SCREEN: // Tour options selection
            screen = new BookingAnswerGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case CALENDAR_SCREEN: // Calendar maint
            screen = new BookingCalendar(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case FIT_SCREEN:    // FIT maint
            screen = new BookingDetailGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case VOUCHER_SCREEN: // Voucher maint
            screen = new TourApTrxGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        case ITIN_SCREEN: // Itinerary maint
            screen = new BookingItinerary(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | ScreenConstants.DONT_PUSH_TO_BROSWER, properties);break;
        default:
            screen = null;
        }
        //?if (screen != null)
        //?   screen.create(Point(0, 0), m_pParentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }

}
