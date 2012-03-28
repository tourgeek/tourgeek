/**
 * @(#)BookingAir.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.db;

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
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.booking.entry.detail.air.*;
import com.tourapp.tour.message.air.response.in.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingAir - Booking Ticket Segment Detail.
 */
public class BookingAir extends BookingDetail
     implements BookingAirModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingAir()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingAir(RecordOwner screen)
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_AIR_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Ticket detail";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_AIR_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_AIR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 3)
        //  field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new BookingPaxField(this, BOOKING_PAX_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new TourHeaderField(this, MODULE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new TourHeaderDetailField(this, TOUR_HEADER_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new TourHeaderOptionField(this, TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new DateTimeField(this, MODULE_START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new StringField(this, DESCRIPTION, 60, null, null);
        if (iFieldSeq == 10)
        {
            field = new StringField(this, PRODUCT_TYPE, 15, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 11)
        //  field = new StringField(this, REMOTE_REFERENCE_NO, 60, null, null);
        //if (iFieldSeq == 12)
        //  field = new ProductTypeField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
        {
            field = new DateTimeField(this, DETAIL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 14)
        //{
        //  field = new DateTimeField(this, GMT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 15)
        //  field = new DoubleField(this, GMT_OFFSET, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new AirField(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new AirRateSelect(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new AirClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 19)
        //  field = new PercentField(this, MARKUP_FROM_LAST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 20)
        //  field = new VendorField(this, VENDOR_ID, 8, null, null);
        //if (iFieldSeq == 21)
        //  field = new TourField(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 22)
        //  field = new ApTrxField(this, AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 23)
        //  field = new StringField(this, PRICING_DETAIL_KEY, 128, null, null);
        //if (iFieldSeq == 24)
        //  field = new FullCurrencyField(this, TOTAL_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 25)
        //  field = new RealField(this, EXCHANGE, 10, null, new Double(1.0));
        if (iFieldSeq == 26)
        {
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 27)
        //  field = new CurrencyField(this, TOTAL_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 28)
        //  field = new CurrencyField(this, TOTAL_PRICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 29)
        //  field = new MessageTransportSelect(this, INFO_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 30)
        //  field = new InfoStatusSelect(this, INFO_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 31)
        //  field = new StringField(this, INFO_REQUEST_KEY, 128, null, null);
        if (iFieldSeq == 32)
        {
            field = new BooleanField(this, INFO_STATUS_REQUEST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 33)
        //  field = new MessageTransportSelect(this, COST_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 34)
        //  field = new CostStatusSelect(this, COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 35)
        //  field = new StringField(this, COST_REQUEST_KEY, 128, null, null);
        if (iFieldSeq == 36)
        {
            field = new BooleanField(this, COST_STATUS_REQUEST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 37)
        //  field = new MessageTransportSelect(this, INVENTORY_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 38)
        //  field = new InventoryStatusSelect(this, INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 39)
        //  field = new StringField(this, INVENTORY_REQUEST_KEY, 128, null, null);
        if (iFieldSeq == 40)
        {
            field = new BooleanField(this, INVENTORY_STATUS_REQUEST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 41)
        //  field = new MessageTransportSelect(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 42)
        //  field = new ProductStatusSelect(this, PRODUCT_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 43)
        //  field = new StringField(this, PRODUCT_REQUEST_KEY, 128, null, null);
        if (iFieldSeq == 44)
        {
            field = new BooleanField(this, PRODUCT_STATUS_REQUEST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 45)
        //  field = new StringField(this, REMOTE_BOOKING_NO, 127, null, null);
        //if (iFieldSeq == 46)
        //  field = new ShortField(this, ACK_DAYS, 4, null, null);
        //if (iFieldSeq == 47)
        //  field = new DateTimeField(this, DETAIL_END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 48)
        //  field = new DateTimeField(this, GMT_END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 49)
        {
            field = new StringField(this, MEAL_SUMMARY, 255, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 50)
        {
            field = new StatusSummaryField(this, STATUS_SUMMARY, 20, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 51)
        //  field = new XmlField(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 52)
        //  field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 53)
        //  field = new PropertiesField(this, ERROR_PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 54)
        //  field = new FullCurrencyField(this, PP_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 55)
        //  field = new CurrencyField(this, PP_PRICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 56)
            field = new UnusedField(this, NIGHTS, 2, null, null);
        if (iFieldSeq == 57)
            field = new UnusedField(this, MEAL_PLAN_1ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 58)
            field = new UnusedField(this, MEAL_PLAN_1_QTY, 2, null, null);
        if (iFieldSeq == 59)
            field = new UnusedField(this, MEAL_PLAN_1_DAYS, 9, null, null);
        if (iFieldSeq == 60)
            field = new UnusedField(this, MEAL_PLAN_2ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 61)
            field = new UnusedField(this, MEAL_PLAN_2_QTY, 2, null, null);
        if (iFieldSeq == 62)
            field = new UnusedField(this, MEAL_PLAN_2_DAYS, 9, null, null);
        if (iFieldSeq == 63)
            field = new UnusedField(this, MEAL_PLAN_3ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 64)
            field = new UnusedField(this, MEAL_PLAN_3_QTY, 2, null, null);
        if (iFieldSeq == 65)
            field = new UnusedField(this, MEAL_PLAN_3_DAYS, 9, null, null);
        if (iFieldSeq == 66)
            field = new UnusedField(this, MEAL_PLAN_4ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 67)
            field = new UnusedField(this, MEAL_PLAN_4_QTY, 2, null, null);
        if (iFieldSeq == 68)
            field = new UnusedField(this, MEAL_PLAN_4_DAYS, 9, null, null);
        if (iFieldSeq == 69)
        {
            field = new UnusedField(this, SINGLE_PAX, 4, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 70)
        {
            field = new UnusedField(this, DOUBLE_PAX, 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 71)
        {
            field = new UnusedField(this, TRIPLE_PAX, 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 72)
        {
            field = new UnusedField(this, QUAD_PAX, 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 73)
            field = new UnusedField(this, CHILDREN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 74)
            field = new UnusedField(this, SINGLE_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 75)
            field = new UnusedField(this, DOUBLE_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 76)
            field = new UnusedField(this, TRIPLE_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 77)
            field = new UnusedField(this, QUAD_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 78)
            field = new UnusedField(this, CHILDREN_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 79)
            field = new UnusedField(this, ROOM_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 80)
            field = new UnusedField(this, MEAL_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 81)
        {
            field = new UnusedField(this, ROOM_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 82)
        {
            field = new UnusedField(this, MEAL_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 83)
            field = new UnusedField(this, VARIES_CODE, 1, null, null);
        if (iFieldSeq == 84)
            field = new UnusedField(this, VARIES_QTY, 2, null, null);
        if (iFieldSeq == 85)
            field = new UnusedField(this, VARIES_COST, 9, null, null);
        if (iFieldSeq == 86)
            field = new UnusedField(this, PMC_CUTOFF, 3, null, null);
        if (iFieldSeq == 87)
            field = new UnusedField(this, PMC_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 88)
            field = new UnusedField(this, SIC_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 89)
            field = new BookingAirHeaderField(this, BOOKING_AIR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 90)
        {
            field = new TimeField(this, GMT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 91)
        {
            field = new TimeField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 92)
        {
            field = new StringField(this, XO, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 93)
        {
            field = new StringField(this, CITY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 94)
        {
            field = new StringField(this, CITY_DESC, 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 95)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 96)
        {
            field = new StringField(this, CARRIER, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 97)
        {
            field = new StringField(this, FLIGHT_NO, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 98)
        {
            field = new StringField(this, FLIGHT_CLASS, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 99)
        {
            field = new StringField(this, TO_CITY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 100)
        {
            field = new StringField(this, TO_CITY_DESC, 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 101)
        {
            field = new TimeField(this, ARRIVE_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 102)
        {
            field = new ShortField(this, ADD_DAYS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 103)
            field = new StringField(this, ARC_STATUS, 2, null, "OK");
        if (iFieldSeq == 104)
        {
            field = new StringField(this, FARE_BASIS, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 105)
        {
            field = new DateField(this, START_DATE, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 106)
        {
            field = new DateField(this, END_DATE, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 107)
        {
            field = new StringField(this, ALLOW, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 108)
        {
            field = new DoubleField(this, DET_FARE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 109)
            field = new StringField(this, SEGMENT_CONF_NO, 128, null, null);
        if (iFieldSeq == 110)
            field = new StringField(this, SEGMENT_CONFIRMED_BY, 50, null, null);
        if (iFieldSeq == 111)
        {
            field = new ShortField(this, COUPON, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 112)
        {
            field = new StringField(this, SEAT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 113)
        {
            field = new StringField(this, GATE, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 114)
        {
            field = new StringField(this, SEAT_PREF, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 115)
        {
            field = new BooleanField(this, SMOKING, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 116)
        {
            field = new StringField(this, MEALS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 117)
            field = new UnusedField(this, DAYS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 118)
            field = new UnusedField(this, QUANTITY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 119)
            field = new UnusedField(this, ASK_FOR_ANSWER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 120)
        {
            field = new UnusedField(this, ALWAYS_RESOLVE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 121)
            field = new UnusedField(this, PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingID");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DETAIL_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DetailAccess");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_HEADER_DETAIL_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_START_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductID");
            keyArea.addKeyField(PRODUCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DETAIL_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ApTrxID");
            keyArea.addKeyField(AP_TRX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DETAIL_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourID");
            keyArea.addKeyField(TOUR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(PRODUCT_TYPE_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 6)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingAirHeaderID");
            keyArea.addKeyField(BOOKING_AIR_HEADER_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(GMT_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 7)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Carrier");
            keyArea.addKeyField(CARRIER, DBConstants.ASCENDING);
            keyArea.addKeyField(FLIGHT_NO, DBConstants.ASCENDING);
            keyArea.addKeyField(DETAIL_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.addListener(new SharedFileHandler(BookingDetail.PRODUCT_TYPE_ID, ProductType.AIR_ID));
    }
    /**
     * SetupEndDate Method.
     */
    public Date setupEndDate()
    {
        Date startDate = this.getStartDate();
        Calendar calendar = DateTimeField.m_calendar;
        Date timeArrive = ((DateTimeField)this.getField(BookingAir.ARRIVE_TIME)).getDateTime();
        if (timeArrive == null)
            return startDate;   // Never
        calendar.setTime(timeArrive);
        int iHour = calendar.get(Calendar.HOUR_OF_DAY);
        int iMinute = calendar.get(Calendar.MINUTE);
        int iDays = (int)this.getField(BookingAir.ADD_DAYS).getValue();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, iDays);
        
        calendar.set(Calendar.HOUR_OF_DAY, iHour);
        calendar.set(Calendar.MINUTE, iMinute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        startDate = calendar.getTime();
        return startDate;
    }
    /**
     * From the current detail and product info,
     * setup the product description.
     * @return The product description (using the product).
     */
    public String setupProductDesc()
    {
        String strProductDesc = super.setupProductDesc();
        if ((strProductDesc == null) || (strProductDesc.length() == 0))
        {
            Record recAirline = ((ReferenceField)this.getField(BookingDetail.AIRLINE_ID)).getReference();
            if (recAirline != null)
                strProductDesc += recAirline.getField(Airline.AIRLINE_CODE).toString();
            strProductDesc += this.getField(BookingDetail.FLIGHT_NO).toString() + ' ';
            strProductDesc += this.getField(BookingDetail.CITY_CODE).toString() + '/' + this.getField(BookingDetail.TO_CITY_CODE).toString() + ' ';
            strProductDesc += this.getField(BookingDetail.ETD).toString() + '-' + this.getField(BookingDetail.ARRIVE_TIME).toString();
            if (!this.getField(BookingDetail.ADD_DAYS).isNull())
                strProductDesc += " " + this.getField(BookingDetail.ADD_DAYS).toString();
        }
        return strProductDesc;
    }
    /**
     * Get the meals on this day. If bDetailedDesc is false, a very short (1-3 char) desc is returned.
     */
    public String getMealDesc(Date dateTarget, boolean bDetailedDesc, Record recMealPlan)
    {
        Calendar calendar = DateTimeField.m_calendar;
        String strMealDesc = Constants.BLANK;
        
        Date dateStart = this.getStartDate();
        calendar.setTime(dateTarget);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateTarget = calendar.getTime();
        if (dateStart.before(dateTarget))
            return strMealDesc;     // No meals before this day
        
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        dateTarget = calendar.getTime();
        if (dateStart.after(dateTarget))
            return strMealDesc;     // No meals after this day
        
        strMealDesc = this.getField(BookingAir.MEALS).toString();
        return strMealDesc;
    }
    /**
     * When a new record is set up and you have the booking and tour
     * records, init the detail fields.
     */
    public int initBookingDetailFields(Booking recBooking, Tour recTour, boolean bOnlyIfTargetIsNull)
    {
        int iErrorCode = super.initBookingDetailFields(recBooking, recTour, bOnlyIfTargetIsNull);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingAir.RATE_ID).isNull()))
            this.getField(BookingAir.RATE_ID).moveFieldToThis(recTour.getField(Tour.AIR_RATE_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingAir.CLASS_ID).isNull()))
            this.getField(BookingAir.CLASS_ID).moveFieldToThis(recTour.getField(Tour.AIR_CLASS_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return iErrorCode;
    }
    /**
     * Move data from the product record to this record.
     * @param recProduct the souce product record.
     * @return An error code.
     */
    public int moveProductFields(Product recProduct)
    {
        int iErrorCode = super.moveProductFields(recProduct);
        
        this.moveTargetField(recProduct, BookingDetail.ETD, Product.ETD);
        this.moveTargetField(recProduct, BookingDetail.ARRIVE_TIME, Air.ARRIVE_TIME);
        if (this.moveTargetField(recProduct, BookingDetail.CITY_CODE, Air.CITY_CODE))
        {
            //this.moveTargetField(recProduct, BookingDetail.CITY_DESC, Air.CITY_DESC);
        }
        if (this.moveTargetField(recProduct, BookingDetail.AIRLINE_ID, Air.AIRLINE_ID))
        {
            Record recAirline = ((ReferenceField)this.getField(BookingDetail.AIRLINE_ID)).getReference();
            if (recAirline != null)
                this.getField(BookingDetail.CARRIER).moveFieldToThis(recAirline.getField(Airline.DESCRIPTION));
        }
        this.moveTargetField(recProduct, BookingDetail.FLIGHT_NO, Air.FLIGHT_NO);
        this.moveTargetField(recProduct, BookingDetail.TO_CITY_CODE, Air.TO_CITY_CODE);
        this.moveTargetField(recProduct, BookingDetail.ADD_DAYS, Air.ADD_DAYS);
        this.moveTargetField(recProduct, BookingDetail.START_DATE, Air.START_DATE);
        this.moveTargetField(recProduct, BookingDetail.END_DATE, Air.END_DATE);
        this.moveTargetField(recProduct, BookingDetail.MEALS, Air.MEALS);
        
        return iErrorCode;
    }
    /**
     * Move from the tour header record to this record.
     * NOTE: Be careful as the TourHeaderDetail record can be null (so fields that depend on Tour can be set).
     * @param recTourHeaderDetail The tour header record.
     * @return An error code.
     */
    public int moveTourHeaderFields(TourSub recTourHeaderDetail, Tour recTour)
    {
        int iErrorCode = super.moveTourHeaderFields(recTourHeaderDetail, recTour);
        if (recTourHeaderDetail != null)
        {
            this.moveTargetField(recTourHeaderDetail, BookingDetail.ETD, TourHeaderDetail.ETD);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.ARRIVE_TIME, TourHeaderDetail.ARRIVE_TIME);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.XO, TourHeaderDetail.XO);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.CITY_CODE, TourHeaderDetail.CITY_CODE);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.CITY_DESC, TourHeaderDetail.CITY_DESC);
            if (this.moveTargetField(recTourHeaderDetail, BookingDetail.AIRLINE_ID, TourHeaderDetail.AIRLINE_ID))
            {
                Record recAirline = ((ReferenceField)this.getField(BookingDetail.AIRLINE_ID)).getReference();
                if (recAirline != null)
                    this.getField(BookingDetail.CARRIER).moveFieldToThis(recAirline.getField(Airline.DESCRIPTION));
            }
            this.moveTargetField(recTourHeaderDetail, BookingDetail.FLIGHT_NO, TourHeaderDetail.FLIGHT_NO);
            //this.moveTargetField(recTourHeaderDetail, BookingDetail.CLASS, TourHeaderDetail.CLASS);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.TO_CITY_CODE, TourHeaderDetail.TO_CITY_CODE);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.ADD_DAYS, TourHeaderDetail.ADD_DAYS);
            //this.moveTargetField(recTourHeaderDetail, BookingDetail.STATUS, TourHeaderDetail.STATUS);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.FARE_BASIS, TourHeaderDetail.FARE_BASIS);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.START_DATE, TourHeaderDetail.START_DATE);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.END_DATE, TourHeaderDetail.END_DATE);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.ALLOW, TourHeaderDetail.ALLOW);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.MEALS, TourHeaderDetail.MEALS);
        
            if (!recTourHeaderDetail.getField(TourHeaderAir.TOUR_HEADER_AIR_HEADER_ID).isNull())
            { // Now move the air header reference (if it exists)
                try {
                    Record recBookingAirHeader = ((ReferenceField)this.getField(BookingAir.BOOKING_AIR_HEADER_ID)).getReferenceRecord();
                    recBookingAirHeader.addNew();
        
                    recBookingAirHeader.setKeyArea(BookingAirHeader.BOOKING_ID_KEY);
                    recBookingAirHeader.getField(BookingAirHeader.BOOKING_ID).moveFieldToThis(this.getField(BookingAir.BOOKING_ID));
                    recBookingAirHeader.getField(BookingAirHeader.BOOKING_PAX_ID).moveFieldToThis(this.getField(BookingAir.BOOKING_PAX_ID));
                    recBookingAirHeader.getField(BookingAirHeader.MODULE_ID).moveFieldToThis(this.getField(BookingAir.MODULE_ID));
                    recBookingAirHeader.getField(BookingAirHeader.MODULE_START_DATE).moveFieldToThis(this.getField(BookingAir.MODULE_START_DATE));
        
                    recBookingAirHeader.getField(BookingAirHeader.TOUR_HEADER_DETAIL_ID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAir.TOUR_HEADER_AIR_HEADER_ID));
        
                    if (recBookingAirHeader.seek(null))
                    {
                        this.getField(BookingAir.BOOKING_AIR_HEADER_ID).moveFieldToThis(recBookingAirHeader.getField(BookingAirHeader.ID));
                    }
        
        
                } catch (DBException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return iErrorCode;
    }
    /**
     * SetDetailProductProperties Method.
     */
    public int setDetailProductProperties(Map<String,Object> properties)
    {
        return super.setDetailProductProperties(properties);
    }
    /**
     * Pre-check to see if the minimal required params are set.
     * @return If okay, return 0, otherwise return the field that is required.
     */
    public String checkRequiredParams(String iStatusType)
    {
        if (iStatusType != BookingDetail.INFO_STATUS_ID)
        {
            if (this.getField(BookingDetail.RATE_ID).isNull())
                return BookingDetail.RATE_ID;
            if (this.getField(BookingDetail.CLASS_ID).isNull())
                return BookingDetail.CLASS_ID;
        }
        return super.checkRequiredParams(iStatusType);
    }

}
