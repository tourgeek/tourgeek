/**
 * @(#)BookingHotel.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.entry.detail.hotel.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.hotel.response.in.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingHotel - Booking hotel information.
 */
public class BookingHotel extends BookingDetail
     implements BookingHotelModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kBookingID = kBookingID;
    //public static final int kBookingPaxID = kBookingPaxID;
    //public static final int kModuleID = kModuleID;
    //public static final int kTourHeaderDetailID = kTourHeaderDetailID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kDescription = kDescription;
    //public static final int kProductType = kProductType;
    //public static final int kProductTypeID = kProductTypeID;
    //public static final int kDetailDate = kDetailDate;
    //public static final int kGMTDate = kGMTDate;
    //public static final int kGMTOffset = kGMTOffset;
    //public static final int kProductID = kProductID;
    //public static final int kRateID = kRateID;
    //public static final int kClassID = kClassID;
    //public static final int kMarkupFromLast = kMarkupFromLast;
    //public static final int kVendorID = kVendorID;
    //public static final int kTourID = kTourID;
    //public static final int kApTrxID = kApTrxID;
    //public static final int kPricingDetailKey = kPricingDetailKey;
    //public static final int kTotalCost = kTotalCost;
    //public static final int kExchange = kExchange;
    //public static final int kCurrencyCode = kCurrencyCode;
    //public static final int kTotalCostLocal = kTotalCostLocal;
    //public static final int kTotalPriceLocal = kTotalPriceLocal;
    //public static final int kInfoMessageTransportID = kInfoMessageTransportID;
    //public static final int kInfoStatusID = kInfoStatusID;
    //public static final int kInfoRequestKey = kInfoRequestKey;
    //public static final int kInfoStatusRequest = kInfoStatusRequest;
    //public static final int kCostMessageTransportID = kCostMessageTransportID;
    //public static final int kCostStatusID = kCostStatusID;
    //public static final int kCostRequestKey = kCostRequestKey;
    //public static final int kCostStatusRequest = kCostStatusRequest;
    //public static final int kInventoryMessageTransportID = kInventoryMessageTransportID;
    //public static final int kInventoryStatusID = kInventoryStatusID;
    //public static final int kInventoryRequestKey = kInventoryRequestKey;
    //public static final int kInventoryStatusRequest = kInventoryStatusRequest;
    //public static final int kProductMessageTransportID = kProductMessageTransportID;
    //public static final int kProductStatusID = kProductStatusID;
    //public static final int kProductRequestKey = kProductRequestKey;
    //public static final int kProductStatusRequest = kProductStatusRequest;
    //public static final int kRemoteBookingNo = kRemoteBookingNo;
    //public static final int kAckDays = kAckDays;
    //public static final int kLastChanged = kLastChanged;
    //public static final int kDeleted = kDeleted;
    //public static final int kDetailEndDate = kDetailEndDate;
    //public static final int kGMTEndDate = kGMTEndDate;
    //public static final int kMealSummary = kMealSummary;
    //public static final int kStatusSummary = kStatusSummary;
    //public static final int kItineraryDesc = kItineraryDesc;
    //public static final int kProperties = kProperties;
    //public static final int kErrorProperties = kErrorProperties;
    //public static final int kPPCost = kPPCost;
    //public static final int kPPPriceLocal = kPPPriceLocal;
    //public static final int kNights = kNights;
    //public static final int kMealPlan1ID = kMealPlan1ID;
    //public static final int kMealPlan1Qty = kMealPlan1Qty;
    //public static final int kMealPlan1Days = kMealPlan1Days;
    //public static final int kMealPlan2ID = kMealPlan2ID;
    //public static final int kMealPlan2Qty = kMealPlan2Qty;
    //public static final int kMealPlan2Days = kMealPlan2Days;
    //public static final int kMealPlan3ID = kMealPlan3ID;
    //public static final int kMealPlan3Qty = kMealPlan3Qty;
    //public static final int kMealPlan3Days = kMealPlan3Days;
    //public static final int kMealPlan4ID = kMealPlan4ID;
    //public static final int kMealPlan4Qty = kMealPlan4Qty;
    //public static final int kMealPlan4Days = kMealPlan4Days;
    //public static final int kSinglePax = kSinglePax;
    //public static final int kDoublePax = kDoublePax;
    //public static final int kTriplePax = kTriplePax;
    //public static final int kQuadPax = kQuadPax;
    //public static final int kChildren = kChildren;
    //public static final int kSingleCost = kSingleCost;
    //public static final int kDoubleCost = kDoubleCost;
    //public static final int kTripleCost = kTripleCost;
    //public static final int kQuadCost = kQuadCost;
    //public static final int kChildrenCost = kChildrenCost;
    //public static final int kRoomCost = kRoomCost;
    //public static final int kMealCost = kMealCost;
    //public static final int kRoomCostLocal = kRoomCostLocal;
    //public static final int kMealCostLocal = kMealCostLocal;
    //public static final int kVariesCode = kVariesCode;
    //public static final int kVariesQty = kVariesQty;
    //public static final int kVariesCost = kVariesCost;
    //public static final int kPMCCutoff = kPMCCutoff;
    //public static final int kPMCCost = kPMCCost;
    //public static final int kSICCost = kSICCost;
    //public static final int kBookingAirHeaderID = kBookingAirHeaderID;
    //public static final int kGMTTime = kGMTTime;
    //public static final int kEtd = kEtd;
    //public static final int kXO = kXO;
    //public static final int kCityCode = kCityCode;
    //public static final int kCityDesc = kCityDesc;
    //public static final int kAirlineID = kAirlineID;
    //public static final int kCarrier = kCarrier;
    //public static final int kFlightNo = kFlightNo;
    //public static final int kFlightClass = kFlightClass;
    //public static final int kToCityCode = kToCityCode;
    //public static final int kToCityDesc = kToCityDesc;
    //public static final int kArriveTime = kArriveTime;
    //public static final int kAddDays = kAddDays;
    //public static final int kARCStatus = kARCStatus;
    //public static final int kFareBasis = kFareBasis;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kAllow = kAllow;
    //public static final int kDetFare = kDetFare;
    //public static final int kSegmentConfNo = kSegmentConfNo;
    //public static final int kSegmentConfirmedBy = kSegmentConfirmedBy;
    //public static final int kCoupon = kCoupon;
    //public static final int kSeat = kSeat;
    //public static final int kGate = kGate;
    //public static final int kSeatPref = kSeatPref;
    //public static final int kSmoking = kSmoking;
    //public static final int kMeals = kMeals;
    //public static final int kDays = kDays;
    //public static final int kQuantity = kQuantity;
    //public static final int kAskForAnswer = kAskForAnswer;
    //public static final int kAlwaysResolve = kAlwaysResolve;
    //public static final int kPricingTypeID = kPricingTypeID;
    public static final int kBookingHotelLastField = kBookingDetailLastField;
    public static final int kBookingHotelFields = kBookingDetailLastField - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDetailAccessKey = kIDKey + 1;
    public static final int kBookingIDKey = kDetailAccessKey + 1;
    public static final int kProductIDKey = kBookingIDKey + 1;
    public static final int kApTrxIDKey = kProductIDKey + 1;
    public static final int kTourIDKey = kApTrxIDKey + 1;
    public static final int kBookingHotelLastKey = kTourIDKey;
    public static final int kBookingHotelKeys = kTourIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kBookingHotelFile = "BookingDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingHotelFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kBookingID)
        //  field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kBookingPaxID)
        //  field = new BookingPaxField(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == kModuleID)
        //  field = new TourHeaderField(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTourHeaderDetailID)
        //  field = new TourHeaderDetailField(this, "TourHeaderDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTourHeaderOptionID)
        //  field = new TourHeaderOptionField(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDescription)
        //  field = new StringField(this, "Description", 60, null, null);
        if (iFieldSeq == kProductType)
        {
            field = new StringField(this, "ProductType", 15, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kProductTypeID)
        //  field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDetailDate)
        //{
        //  field = new DateTimeField(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kGMTDate)
        //{
        //  field = new DateTimeField(this, "GMTDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kGMTOffset)
        //  field = new DoubleField(this, "GMTOffset", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductID)
            field = new HotelField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new HotelRateSelect(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new HotelClassSelect(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kMarkupFromLast)
        //  field = new PercentField(this, "MarkupFromLast", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kVendorID)
        //  field = new VendorField(this, "VendorID", 8, null, null);
        //if (iFieldSeq == kTourID)
        //  field = new TourField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kApTrxID)
        //  field = new ApTrxField(this, "ApTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPricingDetailKey)
        //  field = new StringField(this, "PricingDetailKey", 128, null, null);
        //if (iFieldSeq == kTotalCost)
        //  field = new FullCurrencyField(this, "TotalCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kExchange)
        //  field = new RealField(this, "Exchange", 10, null, new Double(1.0));
        if (iFieldSeq == kCurrencyCode)
        {
            field = new StringField(this, "CurrencyCode", 3, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kTotalCostLocal)
        //  field = new CurrencyField(this, "TotalCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTotalPriceLocal)
        //  field = new CurrencyField(this, "TotalPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInfoMessageTransportID)
        //  field = new MessageTransportSelect(this, "InfoMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInfoStatusID)
        //  field = new InfoStatusSelect(this, "InfoStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInfoRequestKey)
        //  field = new StringField(this, "InfoRequestKey", 128, null, null);
        if (iFieldSeq == kInfoStatusRequest)
        {
            field = new BooleanField(this, "InfoStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kCostMessageTransportID)
        //  field = new MessageTransportSelect(this, "CostMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCostStatusID)
        //  field = new CostStatusSelect(this, "CostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCostRequestKey)
        //  field = new StringField(this, "CostRequestKey", 128, null, null);
        if (iFieldSeq == kCostStatusRequest)
        {
            field = new BooleanField(this, "CostStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kInventoryMessageTransportID)
        //  field = new MessageTransportSelect(this, "InventoryMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInventoryStatusID)
        //  field = new InventoryStatusSelect(this, "InventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInventoryRequestKey)
        //  field = new StringField(this, "InventoryRequestKey", 128, null, null);
        if (iFieldSeq == kInventoryStatusRequest)
        {
            field = new BooleanField(this, "InventoryStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kProductMessageTransportID)
        //  field = new MessageTransportSelect(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductStatusID)
        //  field = new ProductStatusSelect(this, "ProductStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductRequestKey)
        //  field = new StringField(this, "ProductRequestKey", 128, null, null);
        if (iFieldSeq == kProductStatusRequest)
        {
            field = new BooleanField(this, "ProductStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kRemoteBookingNo)
        //  field = new StringField(this, "RemoteBookingNo", 127, null, null);
        //if (iFieldSeq == kAckDays)
        //  field = new ShortField(this, "AckDays", 4, null, null);
        //if (iFieldSeq == kLastChanged)
        //{
        //  field = new RecordChangedField(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kDeleted)
        //{
        //  field = new BooleanField(this, "Deleted", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kDetailEndDate)
        //  field = new DateTimeField(this, "DetailEndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kGMTEndDate)
        //  field = new DateTimeField(this, "GMTEndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealSummary)
        {
            field = new StringField(this, "MealSummary", 255, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kStatusSummary)
        {
            field = new StatusSummaryField(this, "StatusSummary", 20, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kItineraryDesc)
        //  field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProperties)
        //  field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kErrorProperties)
        //  field = new PropertiesField(this, "ErrorProperties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPPCost)
        //  field = new FullCurrencyField(this, "PPCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPPPriceLocal)
        //  field = new CurrencyField(this, "PPPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNights)
            field = new ShortField(this, "Nights", 2, null, null);
        if (iFieldSeq == kMealPlan1ID)
            field = new MealPlanField(this, "MealPlan1ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan1Qty)
            field = new ShortField(this, "MealPlan1Qty", 2, null, null);
        if (iFieldSeq == kMealPlan1Days)
            field = new MealDays(this, "MealPlan1Days", 9, null, null);
        if (iFieldSeq == kMealPlan2ID)
            field = new MealPlanField(this, "MealPlan2ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan2Qty)
            field = new ShortField(this, "MealPlan2Qty", 2, null, null);
        if (iFieldSeq == kMealPlan2Days)
            field = new MealDays(this, "MealPlan2Days", 9, null, null);
        if (iFieldSeq == kMealPlan3ID)
            field = new MealPlanField(this, "MealPlan3ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan3Qty)
            field = new ShortField(this, "MealPlan3Qty", 2, null, null);
        if (iFieldSeq == kMealPlan3Days)
            field = new MealDays(this, "MealPlan3Days", 9, null, null);
        if (iFieldSeq == kMealPlan4ID)
            field = new MealPlanField(this, "MealPlan4ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan4Qty)
            field = new ShortField(this, "MealPlan4Qty", 2, null, null);
        if (iFieldSeq == kMealPlan4Days)
            field = new MealDays(this, "MealPlan4Days", 9, null, null);
        if (iFieldSeq == kSinglePax)
        {
            field = new ShortField(this, "SinglePax", 4, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDoublePax)
        {
            field = new ShortField(this, "DoublePax", 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kTriplePax)
        {
            field = new ShortField(this, "TriplePax", 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kQuadPax)
        {
            field = new ShortField(this, "QuadPax", 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kChildren)
            field = new ShortField(this, "Children", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSingleCost)
            field = new FullCurrencyField(this, "SingleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDoubleCost)
            field = new FullCurrencyField(this, "DoubleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTripleCost)
            field = new FullCurrencyField(this, "TripleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kQuadCost)
            field = new FullCurrencyField(this, "QuadCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChildrenCost)
            field = new FullCurrencyField(this, "ChildrenCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRoomCost)
            field = new FullCurrencyField(this, "RoomCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealCost)
            field = new FullCurrencyField(this, "MealCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRoomCostLocal)
        {
            field = new CurrencyField(this, "RoomCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kMealCostLocal)
        {
            field = new CurrencyField(this, "MealCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kVariesCode)
            field = new UnusedField(this, "VariesCode", 1, null, null);
        if (iFieldSeq == kVariesQty)
            field = new UnusedField(this, "VariesQty", 2, null, null);
        if (iFieldSeq == kVariesCost)
            field = new UnusedField(this, "VariesCost", 9, null, null);
        if (iFieldSeq == kPMCCutoff)
            field = new UnusedField(this, "PMCCutoff", 3, null, null);
        if (iFieldSeq == kPMCCost)
            field = new UnusedField(this, "PMCCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSICCost)
            field = new UnusedField(this, "SICCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBookingAirHeaderID)
            field = new UnusedField(this, "BookingAirHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGMTTime)
            field = new UnusedField(this, "GMTTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEtd)
            field = new UnusedField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kXO)
            field = new UnusedField(this, "XO", 1, null, null);
        if (iFieldSeq == kCityCode)
            field = new UnusedField(this, "CityCode", 3, null, null);
        if (iFieldSeq == kCityDesc)
            field = new UnusedField(this, "CityDesc", 17, null, null);
        if (iFieldSeq == kAirlineID)
            field = new UnusedField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCarrier)
            field = new UnusedField(this, "Carrier", 2, null, null);
        if (iFieldSeq == kFlightNo)
            field = new UnusedField(this, "FlightNo", 4, null, null);
        if (iFieldSeq == kFlightClass)
            field = new UnusedField(this, "FlightClass", 1, null, null);
        if (iFieldSeq == kToCityCode)
            field = new UnusedField(this, "ToCityCode", 3, null, null);
        if (iFieldSeq == kToCityDesc)
            field = new UnusedField(this, "ToCityDesc", 17, null, null);
        if (iFieldSeq == kArriveTime)
            field = new UnusedField(this, "ArriveTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAddDays)
            field = new UnusedField(this, "AddDays", 2, null, null);
        if (iFieldSeq == kARCStatus)
            field = new UnusedField(this, "ARCStatus", 2, null, null);
        if (iFieldSeq == kFareBasis)
            field = new UnusedField(this, "FareBasis", 15, null, null);
        if (iFieldSeq == kStartDate)
            field = new UnusedField(this, "StartDate", 5, null, null);
        if (iFieldSeq == kEndDate)
            field = new UnusedField(this, "EndDate", 5, null, null);
        if (iFieldSeq == kAllow)
            field = new UnusedField(this, "Allow", 3, null, null);
        if (iFieldSeq == kDetFare)
            field = new UnusedField(this, "DetFare", 10, null, null);
        if (iFieldSeq == kSegmentConfNo)
            field = new UnusedField(this, "SegmentConfNo", 128, null, null);
        if (iFieldSeq == kSegmentConfirmedBy)
            field = new UnusedField(this, "SegmentConfirmedBy", 50, null, null);
        if (iFieldSeq == kCoupon)
            field = new UnusedField(this, "Coupon", 1, null, null);
        if (iFieldSeq == kSeat)
            field = new UnusedField(this, "Seat", 5, null, null);
        if (iFieldSeq == kGate)
            field = new UnusedField(this, "Gate", 5, null, null);
        if (iFieldSeq == kSeatPref)
            field = new UnusedField(this, "SeatPref", 1, null, null);
        if (iFieldSeq == kSmoking)
            field = new UnusedField(this, "Smoking", 1, null, null);
        if (iFieldSeq == kMeals)
            field = new UnusedField(this, "Meals", 2, null, null);
        if (iFieldSeq == kDays)
            field = new UnusedField(this, "Days", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kQuantity)
            field = new UnusedField(this, "Quantity", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAskForAnswer)
            field = new UnusedField(this, "AskForAnswer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAlwaysResolve)
        {
            field = new UnusedField(this, "AlwaysResolve", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPricingTypeID)
            field = new UnusedField(this, "PricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingHotelLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDetailAccessKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DetailAccess");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingPaxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTourHeaderDetailID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleStartDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBookingIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingID");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingPaxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kProductIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductID");
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kApTrxIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ApTrxID");
            keyArea.addKeyField(kApTrxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTourIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourID");
            keyArea.addKeyField(kTourID, DBConstants.ASCENDING);
            keyArea.addKeyField(kVendorID, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductTypeID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingHotelLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingHotelLastKey)
                keyArea = new EmptyKey(this);
        }
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
        int iNights = (int)this.getField(kNights).getValue();
        if (recHotel != null)
            if ((recHotel.getEditMode() == DBConstants.EDIT_CURRENT) || (recHotel.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                if (iTargetDay < iNights)
        {   // Add meal plan
            int iRateType = (int)this.getField(kRateID).getValue();
            int iRateClass = (int)this.getField(kClassID).getValue();
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
    public int initBookingDetailFields(Booking recBooking, Tour recTour, boolean bOnlyIfTargetIsNull)
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
            this.getField(BookingHotel.RATE_ID).moveFieldToThis(recTour.getField(Tour.HOTEL_RATE_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingHotel.CLASS_ID).isNull()))
            this.getField(BookingHotel.CLASS_ID).moveFieldToThis(recTour.getField(Tour.HOTEL_CLASS_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
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
