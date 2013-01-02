/**
  * @(#)BookingHotel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.detail.db;

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
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.base.db.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingHotel - Booking hotel information.
 */
public class BookingHotel extends BookingDetail
     implements BookingHotelModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingHotel()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingHotel(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_HOTEL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Hotel";
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
            screen = Record.makeNewScreen(BOOKING_HOTEL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_HOTEL_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == 13)
        //{
        //  field = new DateTimeField(this, DETAIL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 14)
        //{
        //  field = new DateTimeField(this, GMT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 15)
        //  field = new DoubleField(this, GMT_OFFSET, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new HotelField(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new HotelRateSelect(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new HotelClassSelect(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new ShortField(this, NIGHTS, 2, null, null);
        if (iFieldSeq == 57)
            field = new MealPlanField(this, MEAL_PLAN_1ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 58)
            field = new ShortField(this, MEAL_PLAN_1_QTY, 2, null, null);
        if (iFieldSeq == 59)
            field = new MealDays(this, MEAL_PLAN_1_DAYS, 9, null, null);
        if (iFieldSeq == 60)
            field = new MealPlanField(this, MEAL_PLAN_2ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 61)
            field = new ShortField(this, MEAL_PLAN_2_QTY, 2, null, null);
        if (iFieldSeq == 62)
            field = new MealDays(this, MEAL_PLAN_2_DAYS, 9, null, null);
        if (iFieldSeq == 63)
            field = new MealPlanField(this, MEAL_PLAN_3ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 64)
            field = new ShortField(this, MEAL_PLAN_3_QTY, 2, null, null);
        if (iFieldSeq == 65)
            field = new MealDays(this, MEAL_PLAN_3_DAYS, 9, null, null);
        if (iFieldSeq == 66)
            field = new MealPlanField(this, MEAL_PLAN_4ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 67)
            field = new ShortField(this, MEAL_PLAN_4_QTY, 2, null, null);
        if (iFieldSeq == 68)
            field = new MealDays(this, MEAL_PLAN_4_DAYS, 9, null, null);
        if (iFieldSeq == 69)
        {
            field = new ShortField(this, SINGLE_PAX, 4, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 70)
        {
            field = new ShortField(this, DOUBLE_PAX, 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 71)
        {
            field = new ShortField(this, TRIPLE_PAX, 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 72)
        {
            field = new ShortField(this, QUAD_PAX, 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 73)
            field = new ShortField(this, CHILDREN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 74)
            field = new FullCurrencyField(this, SINGLE_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 75)
            field = new FullCurrencyField(this, DOUBLE_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 76)
            field = new FullCurrencyField(this, TRIPLE_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 77)
            field = new FullCurrencyField(this, QUAD_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 78)
            field = new FullCurrencyField(this, CHILDREN_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 79)
            field = new FullCurrencyField(this, ROOM_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 80)
            field = new FullCurrencyField(this, MEAL_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 81)
        {
            field = new CurrencyField(this, ROOM_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 82)
        {
            field = new CurrencyField(this, MEAL_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new UnusedField(this, BOOKING_AIR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 90)
            field = new UnusedField(this, GMT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 91)
            field = new UnusedField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 92)
            field = new UnusedField(this, XO, 1, null, null);
        if (iFieldSeq == 93)
            field = new UnusedField(this, CITY_CODE, 3, null, null);
        if (iFieldSeq == 94)
            field = new UnusedField(this, CITY_DESC, 17, null, null);
        if (iFieldSeq == 95)
            field = new UnusedField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 96)
            field = new UnusedField(this, CARRIER, 2, null, null);
        if (iFieldSeq == 97)
            field = new UnusedField(this, FLIGHT_NO, 4, null, null);
        if (iFieldSeq == 98)
            field = new UnusedField(this, FLIGHT_CLASS, 1, null, null);
        if (iFieldSeq == 99)
            field = new UnusedField(this, TO_CITY_CODE, 3, null, null);
        if (iFieldSeq == 100)
            field = new UnusedField(this, TO_CITY_DESC, 17, null, null);
        if (iFieldSeq == 101)
            field = new UnusedField(this, ARRIVE_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 102)
            field = new UnusedField(this, ADD_DAYS, 2, null, null);
        if (iFieldSeq == 103)
            field = new UnusedField(this, ARC_STATUS, 2, null, null);
        if (iFieldSeq == 104)
            field = new UnusedField(this, FARE_BASIS, 15, null, null);
        if (iFieldSeq == 105)
            field = new UnusedField(this, START_DATE, 5, null, null);
        if (iFieldSeq == 106)
            field = new UnusedField(this, END_DATE, 5, null, null);
        if (iFieldSeq == 107)
            field = new UnusedField(this, ALLOW, 3, null, null);
        if (iFieldSeq == 108)
            field = new UnusedField(this, DET_FARE, 10, null, null);
        if (iFieldSeq == 109)
            field = new UnusedField(this, SEGMENT_CONF_NO, 128, null, null);
        if (iFieldSeq == 110)
            field = new UnusedField(this, SEGMENT_CONFIRMED_BY, 50, null, null);
        if (iFieldSeq == 111)
            field = new UnusedField(this, COUPON, 1, null, null);
        if (iFieldSeq == 112)
            field = new UnusedField(this, SEAT, 5, null, null);
        if (iFieldSeq == 113)
            field = new UnusedField(this, GATE, 5, null, null);
        if (iFieldSeq == 114)
            field = new UnusedField(this, SEAT_PREF, 1, null, null);
        if (iFieldSeq == 115)
            field = new UnusedField(this, SMOKING, 1, null, null);
        if (iFieldSeq == 116)
            field = new UnusedField(this, MEALS, 2, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, BOOKING_ID_KEY);
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DETAIL_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, DETAIL_ACCESS_KEY);
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_HEADER_DETAIL_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_START_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, PRODUCT_ID_KEY);
            keyArea.addKeyField(PRODUCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DETAIL_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, AP_TRX_ID_KEY);
            keyArea.addKeyField(AP_TRX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DETAIL_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TOUR_ID_KEY);
            keyArea.addKeyField(TOUR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(PRODUCT_TYPE_ID, DBConstants.ASCENDING);
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
        
        this.addListener(new SharedFileHandler(BookingDetail.PRODUCT_TYPE_ID, ProductType.HOTEL_ID));
    }
    /**
     * Add the listeners to do the price and inventory lookups.
     * Typically these are only done in the concrete classes.
     */
    public void addLookupListeners()
    {
        super.addLookupListeners();
        
        FieldListener dependentStateListener = this.getField(BookingDetail.PRODUCT_ID).getListener(CopyDataHandler.class);
        BaseField fldExchange = this.getField(BookingDetail.EXCHANGE);
        FieldListener fieldListener = null;
        this.getField(BookingHotel.ROOM_COST).addListener(fieldListener = new CalcBalanceHandler(this.getField(BookingHotel.ROOM_COST_LOCAL), this.getField(BookingHotel.ROOM_COST), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        fieldListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        fieldListener.setDependentStateListener(dependentStateListener);
        this.getField(BookingHotel.MEAL_COST).addListener(fieldListener = new CalcBalanceHandler(this.getField(BookingHotel.MEAL_COST_LOCAL), this.getField(BookingHotel.MEAL_COST), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        fieldListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        fieldListener.setDependentStateListener(dependentStateListener);
        
        Boolean boolRequestRequiredFlag = Boolean.TRUE;
        
        String strManualTransportID = Integer.toString(((ReferenceField)this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID)).getIDFromCode(MessageTransport.MANUAL));
        
        Converter converterNotInfoManualTransport = new CheckConverter(this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotInfoManualTransport));
        Converter converterNotCostManualTransport = new CheckConverter(this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotCostManualTransport));
        Converter converterNotInventoryManualTransport = new CheckConverter(this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotInventoryManualTransport));
        Converter converterNotProductManualTransport = new CheckConverter(this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotProductManualTransport));
        
        // If any of these values change, you will have to re-lookup the price.
        this.getField(BookingHotel.NIGHTS).addListener(fieldListener = new CopyDataHandler(this.getField(BookingDetail.INFO_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInfoManualTransport));
        fieldListener.setDependentStateListener(dependentStateListener);
        this.getField(BookingHotel.NIGHTS).addListener(fieldListener = new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, converterNotCostManualTransport));
        fieldListener.setDependentStateListener(dependentStateListener);
        this.getField(BookingHotel.NIGHTS).addListener(fieldListener = new CopyDataHandler(this.getField(BookingDetail.INVENTORY_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        fieldListener.setDependentStateListener(dependentStateListener);
        // If this changes, re-request the booking
        this.getField(BookingHotel.NIGHTS).addListener(fieldListener = new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, converterNotProductManualTransport));
        fieldListener.setDependentStateListener(dependentStateListener);
        
        int mealPlan1 = this.getFieldSeq(BookingHotel.MEAL_PLAN_1ID);
        int mealDays4 = this.getFieldSeq(BookingHotel.MEAL_PLAN_4_DAYS);
        for (int iFieldSeq = mealPlan1; iFieldSeq <= mealDays4; iFieldSeq++)
        {
            // If any of these values change, you will have to re-lookup the price.
            this.getField(iFieldSeq).addListener(fieldListener = new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, converterNotCostManualTransport));
            fieldListener.setDependentStateListener(dependentStateListener);
        // No need to re-request the inventory on meal change
            // If this changes, re-request the booking
            this.getField(iFieldSeq).addListener(fieldListener = new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, converterNotProductManualTransport));
            fieldListener.setDependentStateListener(dependentStateListener);
        }
    }
    /**
     * SetupEndDate Method.
     */
    public Date setupEndDate()
    {
        Date dateStart = this.getStartDate();
        if (dateStart == null)
            return dateStart;
        long lNights = (long)this.getField(BookingHotel.NIGHTS).getValue();
        Date dateEnd = new Date(dateStart.getTime() + (lNights * DBConstants.KMS_IN_A_DAY));
        Calendar calendar = DateTimeField.m_calendar;
        int iHour = 12;   // Default 12:00 pm
        int iMinute = 0;
        if (this.getProduct() != null)
        {
            Hotel recHotel = (Hotel)this.getProduct();
            if (!recHotel.getField(Hotel.CHECK_OUT).isNull())
            {
                calendar.setTime(((TimeField)recHotel.getField(Hotel.CHECK_OUT)).getDateTime());
                iHour = calendar.get(Calendar.HOUR_OF_DAY);
                iMinute = calendar.get(Calendar.MINUTE);
            }
        }
        calendar.setTime(dateEnd);
        calendar.set(Calendar.HOUR_OF_DAY, iHour);
        calendar.set(Calendar.MINUTE, iMinute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateEnd = calendar.getTime();
        return dateEnd;
    }
    /**
     * Set the ending time for this tour product.
     * Then, return the actual ending date that was set.
     */
    public Date setEndDate(Date time)
    {
        // First get the number of nights
        Calendar startDate = ((DateTimeField)this.getField(BookingHotel.DETAIL_DATE)).getCalendar();
        if (startDate != null)
        {
            boolean[] rgbEnabled = this.getField(BookingHotel.DETAIL_END_DATE).setEnableListeners(false);  // No echos
        
            startDate.set(Calendar.HOUR_OF_DAY, 0);
            startDate.set(Calendar.MINUTE, 0);
            startDate.set(Calendar.SECOND, 0);
            startDate.set(Calendar.MILLISECOND, 0);
            long lStartDate = startDate.getTime().getTime();
            startDate.setTime(time);
            startDate.set(Calendar.HOUR_OF_DAY, 0);
            startDate.set(Calendar.MINUTE, 1);  // For rounding
            startDate.set(Calendar.SECOND, 0);
            startDate.set(Calendar.MILLISECOND, 0);
            long lEndDate = startDate.getTime().getTime();
            int iDays = (int)((lEndDate - lStartDate) / DBConstants.KMS_IN_A_DAY);
        
            if (iDays > 0)
            {
                int iOldDays = (int)this.getField(BookingHotel.NIGHTS).getValue();
                int iErrorCode = this.getField(BookingHotel.NIGHTS).setValue(iDays);
                if (iErrorCode == DBConstants.NORMAL_RETURN)
                { // Move this code to a listener!!!
                    int mealQty1 = this.getFieldSeq(BookingHotel.MEAL_PLAN_1_QTY);
                    int mealQty2 = this.getFieldSeq(BookingHotel.MEAL_PLAN_2_QTY);
                    int mealQty4 = this.getFieldSeq(BookingHotel.MEAL_PLAN_4_QTY);
                    for (int iFieldSeq = mealQty1; iFieldSeq <= mealQty4; iFieldSeq += mealQty2 - mealQty1)
                    {
                        if (this.getField(iFieldSeq).getValue() == iOldDays)
                            this.getField(iFieldSeq).setValue(iDays);
                    }
                }
            }
        
            this.getField(BookingHotel.DETAIL_END_DATE).setEnableListeners(rgbEnabled);  // Restore this
        }
        return this.getEndDate();
    }
    /**
     * From the current detail and product info,
     * setup the product description.
     * @return The product description (using the product).
     */
    public String setupProductDesc()
    {
        String string = super.setupProductDesc();
        if (string != null)
            if (string.length() > 0)
        {
            string += " - ";
            String tempString = this.getField(BookingHotel.NIGHTS).toString();
            string += tempString;
            string += " night";
            if (this.getField(BookingHotel.NIGHTS).getValue() > 1)
                string += "s";
        }
        return string;
    }
    /**
     * Get the meals on this day. If bDetailedDesc is false, a very short (1-3 char) desc is returned.
     */
    public String getMealDesc(Date dateTarget, boolean bDetailedDesc, Record recMealPlan)
    {
        if (!this.getField(BookingDetail.MEAL_SUMMARY).isNull())
            if (!bDetailedDesc)
        { // Use the cached meal summary
            Date dateStart = this.getStartDate();
            Calendar calendar = Converter.gCalendar;
            calendar.setTime(dateStart);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dateStart = calendar.getTime();   // Start of day 0
            StringTokenizer st = new StringTokenizer(this.getField(BookingDetail.MEAL_SUMMARY).toString(), Constants.RETURN);
            if (dateTarget.before(dateStart))
                return null;
            for (int iDay = 0; ; iDay++)
            {
                if (!st.hasMoreTokens())
                    return null;
                String strMealToken = st.nextToken();
                dateStart.setTime(dateStart.getTime() + Constants.KMS_IN_A_DAY);    // End of iDay
                if (dateTarget.before(dateStart))
                    return strMealToken;
            }
        }
        MealPlan recMealPlanLocal = null;
        Calendar calendar = DateTimeField.m_calendar;
        if (recMealPlan != null)
            recMealPlanLocal = (MealPlan)recMealPlan;
        else
            recMealPlanLocal = new MealPlan(this.findRecordOwner());
        String strMealDesc = Constants.BLANK;
        Date startDate = this.getStartDate();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        startDate = calendar.getTime();
        
        calendar.setTime(dateTarget);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateTarget = calendar.getTime();
        
        long lTimeToTarget = dateTarget.getTime() - startDate.getTime();
        long iTargetDay = lTimeToTarget / DBConstants.KMS_IN_A_DAY;   // Day 0 = first day
        Hotel recHotel = (Hotel)this.getProduct();
        if (recHotel != null)
            recHotel.setOpenMode(recHotel.getOpenMode() | DBConstants.OPEN_CACHE_RECORDS);    // Cache recently used records.
        int iNights = (int)this.getField(BookingHotel.NIGHTS).getValue();
        if (recHotel != null)
            if ((recHotel.getEditMode() == DBConstants.EDIT_CURRENT) || (recHotel.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                if (iTargetDay < iNights)
        {   // Add meal plan
            int iRateType = (int)this.getField(BookingHotel.RATE_ID).getValue();
            int iRateClass = (int)this.getField(BookingHotel.CLASS_ID).getValue();
            strMealDesc += recHotel.getMealDesc(dateTarget, iRateType, iRateClass, bDetailedDesc, recMealPlanLocal, null);
        }
        int mealID1 = this.getFieldSeq(BookingHotel.MEAL_PLAN_1ID);
        int mealID2 = this.getFieldSeq(BookingHotel.MEAL_PLAN_2ID);
        int mealID4 = this.getFieldSeq(BookingHotel.MEAL_PLAN_1ID);
        for (int fieldSeq = mealID1; fieldSeq <= mealID4; fieldSeq += mealID2 - mealID1)
        {
            if (this.getField(fieldSeq).getLength() != 0)
            {
                short iQty = (short)this.getField(fieldSeq+1).getValue();
                MealDays fldMealDays = (MealDays)this.getField(fieldSeq+2);
                for (short day = 0; day <= iTargetDay; day++)
                {   // Once for each day
                    if (fldMealDays.mealOnThisDay(day))
                    {
                        if (day == iTargetDay) if (iQty > 0)
                        {
                            if (strMealDesc.length() > 0)
                                strMealDesc += " ";
                            strMealDesc += recMealPlanLocal.getMealDesc(this.getField(fieldSeq), bDetailedDesc);
                        }
                        iQty--;   // One less meal
                    }
                }
            }
        }
        if (recMealPlan == null)
            recMealPlanLocal.free();
        return strMealDesc;
    }
    /**
     * When a new record is set up and you have the booking and tour
     * records, init the detail fields.
     */
    public int initBookingDetailFields(BookingModel recBooking, TourModel recTour, boolean bOnlyIfTargetIsNull)
    {
        int iErrorCode = super.initBookingDetailFields(recBooking, recTour, bOnlyIfTargetIsNull);
        int iNights = 1;
        if (this.getRecordOwner() != null)
            if (this.getRecordOwner().getRecord(BookingControl.BOOKING_CONTROL_FILE) != null)
                if (!this.getRecordOwner().getRecord(BookingControl.BOOKING_CONTROL_FILE).getField(BookingControl.NIGHTS).isNull())
                    iNights = (int)this.getRecordOwner().getRecord(BookingControl.BOOKING_CONTROL_FILE).getField(BookingControl.NIGHTS).getValue();
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingHotel.NIGHTS).isNull()))
            this.getField(BookingHotel.NIGHTS).setValue(iNights, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingHotel.RATE_ID).isNull()))
            this.getField(BookingHotel.RATE_ID).moveFieldToThis((BaseField)recTour.getField(Tour.HOTEL_RATE_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingHotel.CLASS_ID).isNull()))
            this.getField(BookingHotel.CLASS_ID).moveFieldToThis((BaseField)recTour.getField(Tour.HOTEL_CLASS_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
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
            this.getField(BookingHotel.NIGHTS).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderHotel.NIGHTS));
            if (recTourHeaderDetail.getField(TourHeaderHotel.NIGHTS).getLength() != 0)
                this.getField(BookingHotel.NIGHTS).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderHotel.NIGHTS));
            int iDetailFieldSeq = recTourHeaderDetail.getFieldSeq(TourHeaderHotel.MEAL_1);
            int mealID1 = this.getFieldSeq(BookingHotel.MEAL_PLAN_1ID);
            int mealDays4 = this.getFieldSeq(BookingHotel.MEAL_PLAN_4_DAYS);
            for (int iFieldSeq = mealID1;  iFieldSeq <= mealDays4; iFieldSeq++, iDetailFieldSeq++)
            {   // Move meal stuff
                this.getField(iFieldSeq).moveFieldToThis(recTourHeaderDetail.getField(iDetailFieldSeq));
            }
        }
        return iErrorCode;
    }
    /**
     * SetDetailProductProperties Method.
     */
    public int setDetailProductProperties(Map<String,Object> properties)
    {
        int iErrorCode = super.setDetailProductProperties(properties);
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        int iNights = ((Integer)Utility.getAs(properties, BookingHotel.NIGHTS, Integer.class, IntegerField.ZERO)).intValue();
        int iRateTypeID = ((Integer)Utility.getAs(properties, BookingHotel.RATE_ID, Integer.class, IntegerField.ZERO)).intValue();
        int iRateClassID = ((Integer)Utility.getAs(properties, BookingHotel.CLASS_ID, Integer.class, IntegerField.ZERO)).intValue();
        if ((this.getField(BookingHotel.NIGHTS).getValue() == 0) || (iNights > 0))
        {
            if (iNights == 0)
                iNights = 1;
            this.getField(BookingHotel.NIGHTS).setValue(iNights);
        }
        Record recBooking = null;
        if (!this.getField(BookingDetail.BOOKING_ID).isNull())
            recBooking = ((ReferenceField)this.getField(BookingDetail.BOOKING_ID)).getReference();
        Record recTour = null;
        if (recBooking != null)
            recTour = ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
        if ((this.getField(BookingHotel.RATE_ID).getValue() == 0) || (iRateTypeID != 0))
        {
            if (iRateTypeID == 0)
                if (recTour != null)
                    iRateTypeID = (int)recTour.getField(Tour.HOTEL_RATE_ID).getValue();
            if (iRateTypeID != 0)
                this.getField(BookingHotel.RATE_ID).setValue(iRateTypeID);
        }
        if ((this.getField(BookingHotel.CLASS_ID).getValue() == 0) || (iRateClassID != 0))
        {
            if (iRateClassID == 0)
                if (recTour != null)
                    iRateClassID = (int)recTour.getField(Tour.HOTEL_CLASS_ID).getValue();
            if (iRateClassID != 0)
                this.getField(BookingHotel.CLASS_ID).setValue(iRateClassID);
        }
        return iErrorCode;
    }
    /**
     * Get the ending icon for this in a calendar.
     */
    public String getEndIcon()
    {
        return this.getStartIcon();
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
            if (this.getField(BookingHotel.NIGHTS).isNull())
                return BookingHotel.NIGHTS;
        }
        return super.checkRequiredParams(iStatusType);
    }
    /**
     * UpdateBookingComponentCostPricing Method.
     */
    public int updateBookingComponentCostPricing(Booking recBooking, int iChangeType)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        double dTotalCost = this.getField(BookingDetail.TOTAL_COST_LOCAL).getValue();
        double dRatioDenominator = 0.00;
        for (int iPaxCategory = PaxCategory.SINGLE_ID; iPaxCategory <= PaxCategory.CHILD_ID; iPaxCategory++)
        {
            int iBookingDetailField = this.getFieldSeq(BookingHotel.SINGLE_COST) + iPaxCategory - PaxCategory.SINGLE_ID;
            int iBookingField = recBooking.getFieldSeq(Booking.SINGLE_PAX) + iPaxCategory - PaxCategory.SINGLE_ID;
            double dPPCost = this.getField(iBookingDetailField).getValue();
            int iPaxInRoom = (int)recBooking.getField(iBookingField).getValue();
            dRatioDenominator = dRatioDenominator + (dPPCost * iPaxInRoom);
        }
        if (dRatioDenominator != 0)
        {
            for (int iPaxCategory = PaxCategory.SINGLE_ID; iPaxCategory <= PaxCategory.CHILD_ID; iPaxCategory++)
            {
                int iBookingDetailField = this.getFieldSeq(BookingHotel.SINGLE_COST) + iPaxCategory - PaxCategory.SINGLE_ID;
                int iBookingField = recBooking.getFieldSeq(Booking.SINGLE_PAX) + iPaxCategory - PaxCategory.SINGLE_ID;
                double dPPCost = this.getField(iBookingDetailField).getValue();
                int iPaxInRoom = (int)recBooking.getField(iBookingField).getValue();
                double dRatioNumerator = dPPCost;
                    dPPCost = dTotalCost * (dRatioNumerator / dRatioDenominator);
                iErrorCode = this.updateBookingLine(this.getBookingLine(), PricingType.COMPONENT_COST_PRICING, iPaxCategory, iPaxInRoom, dPPCost, true, recBooking.getField(Booking.COMMISSION).getValue(), null, PricingStatus.OKAY, iChangeType);
            }
            iErrorCode = DBConstants.NORMAL_RETURN;
        }
        else
        {
            iErrorCode = this.updateBookingLine(this.getBookingLine(), PricingType.COMPONENT_COST_PRICING, 0, recBooking.getCountPax(), dTotalCost / recBooking.getCountPax(), true, recBooking.getField(Booking.COMMISSION).getValue(), null, PricingStatus.OKAY, iChangeType);
            iErrorCode = DBConstants.NORMAL_RETURN; // Error return from updateBookingLine just means there was no update
        }
        
        return iErrorCode;
    }

}
