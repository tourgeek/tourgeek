/**
 * @(#)BookingTransportation.
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
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.tour.product.trans.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingTransportation - Booking transportation detail.
 */
public class BookingTransportation extends BookingDetail
     implements BookingTransportationModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingTransportation()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingTransportation(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_TRANSPORTATION_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Booking transportation detail";
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
            screen = Record.makeNewScreen(BOOKING_TRANSPORTATION_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_TRANSPORTATION_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == 3)
        {
            field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
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
            field = new TransportationField(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new TransportationRateSelect(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new TransportationClassSelect(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.addListener(new SharedFileHandler(BookingDetail.PRODUCT_TYPE_ID, ProductType.TRANSPORTATION_ID));
    }
    /**
     * SetupEndDate Method.
     */
    public Date setupEndDate()
    {
        Date startDate = this.getStartDate();
        Calendar calendar = DateTimeField.m_calendar;
        ProductModel recTransportation = this.getProduct();
        if (recTransportation != null)
        {
            short hours = (short)recTransportation.getField(Transportation.HOURS).getValue();
            calendar.setTime(startDate);
            calendar.add(Calendar.HOUR_OF_DAY, hours);
            startDate = calendar.getTime();
        }
        return startDate;
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
            Record recTransportationClass = ((ReferenceField)this.getField(BookingTransportation.CLASS_ID)).getReference();
            if (recTransportationClass != null)
                string += " - " + recTransportationClass.getField(TransportationClass.DESCRIPTION).toString();
        }
        return string;
    }
    /**
     * Get the meals on this day. If bDetailedDesc is false, a very short (1-3 char) desc is returned.
     */
    public String getMealDesc(Date dateTarget, boolean bDetailedDesc, Record recMealPlan)
    {
        ProductModel recTransportation = this.getProduct();
        if (recTransportation != null)
            recTransportation.setOpenMode(recTransportation.getOpenMode() | DBConstants.OPEN_CACHE_RECORDS);    // Cache recently used records.
        if (recTransportation != null)
            if ((recTransportation.getEditMode() == DBConstants.EDIT_CURRENT) || (recTransportation.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
        {
            short sBreakfasts = (short)recTransportation.getField(Transportation.BREAKFASTS).getValue();
            short sLunches = (short)recTransportation.getField(Transportation.LUNCHES).getValue();
            short sDinners = (short)recTransportation.getField(Transportation.DINNERS).getValue();
            return this.getMealDescFromCount(dateTarget, bDetailedDesc, recMealPlan, sBreakfasts, sLunches, sDinners);
        }
        return DBConstants.BLANK;
    }
    /**
     * When a new record is set up and you have the booking and tour
     * records, init the detail fields.
     */
    public int initBookingDetailFields(BookingModel recBooking, TourModel recTour, boolean bOnlyIfTargetIsNull)
    {
        int iErrorCode = super.initBookingDetailFields(recBooking, recTour, bOnlyIfTargetIsNull);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingTransportation.RATE_ID).isNull()))
            this.getField(BookingTransportation.RATE_ID).moveFieldToThis((BaseField)recTour.getField(Tour.TRANSPORTATION_RATE_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingTransportation.CLASS_ID).isNull()))
            this.getField(BookingTransportation.CLASS_ID).moveFieldToThis((BaseField)recTour.getField(Tour.TRANSPORTATION_CLASS_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
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
        int iRateTypeID = ((Integer)Utility.getAs(properties, BookingTransportation.RATE_ID, Integer.class, IntegerField.ZERO)).intValue();
        int iRateClassID = ((Integer)Utility.getAs(properties, BookingTransportation.CLASS_ID, Integer.class, IntegerField.ZERO)).intValue();
        Record recBooking = null;
        if (!this.getField(BookingDetail.BOOKING_ID).isNull())
            recBooking = ((ReferenceField)this.getField(BookingDetail.BOOKING_ID)).getReference();
        Record recTour = null;
        if (recBooking != null)
            recTour = ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
        if ((this.getField(BookingTransportation.RATE_ID).getValue() == 0) || (iRateClassID != 0))
        {
            if (iRateTypeID == 0)
                if (recTour != null)
                    iRateTypeID = (int)recTour.getField(Tour.TRANSPORTATION_RATE_ID).getValue();
            if (iRateTypeID != 0)
                this.getField(BookingTransportation.RATE_ID).setValue(iRateTypeID);
        }
        if ((this.getField(BookingTransportation.CLASS_ID).getValue() == 0) || (iRateClassID != 0))
        {
            if (iRateClassID == 0)
                if (recTour != null)
                    iRateClassID = (int)recTour.getField(Tour.TRANSPORTATION_CLASS_ID).getValue();
            if (iRateClassID != 0)
                this.getField(BookingTransportation.CLASS_ID).setValue(iRateClassID);
        }
        return iErrorCode;
    }
    /**
     * Pre-check to see if the minimal required params are set.
     * @return If okay, return 0, otherwise return the field that is required.
     */
    public String checkRequiredParams(String iStatusType)
    {
        return super.checkRequiredParams(iStatusType);
    }

}
