/**
 * @(#)BookingAir.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.booking.entry.detail.air.*;
import com.tourapp.tour.message.air.response.in.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingAir - Booking Ticket Segment Detail.
 */
public class BookingAir extends BookingDetail
     implements BookingAirModel
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
    //public static final int kInventoryMessageLogID = kInventoryMessageLogID;
    //public static final int kInfoMessageLogID = kInfoMessageLogID;
    //public static final int kProductMessageLogID = kProductMessageLogID;
    //public static final int kTotalCostUSD = kTotalCostUSD;
    //public static final int kCostMessageLogID = kCostMessageLogID;
    //public static final int kDetailDeleted = kDetailDeleted;
    public static final int kBookingAirLastField = kBookingDetailLastField;
    public static final int kBookingAirFields = kBookingDetailLastField - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDetailAccessKey = kIDKey + 1;
    public static final int kBookingIDKey = kDetailAccessKey + 1;
    public static final int kProductIDKey = kBookingIDKey + 1;
    public static final int kApTrxIDKey = kProductIDKey + 1;
    public static final int kTourIDKey = kApTrxIDKey + 1;
    public static final int kBookingAirHeaderIDKey = kTourIDKey + 1;
    public static final int kCarrierKey = kBookingAirHeaderIDKey + 1;
    public static final int kBookingAirLastKey = kCarrierKey;
    public static final int kBookingAirKeys = kCarrierKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kBookingAirFile = "BookingDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingAirFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(BOOKING_AIR_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(BOOKING_AIR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kDetailDate)
        {
            field = new DateTimeField(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kGMTDate)
        //{
        //  field = new DateTimeField(this, "GMTDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kGMTOffset)
        //  field = new DoubleField(this, "GMTOffset", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductID)
            field = new AirField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new AirRateSelect(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new AirClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new UnusedField(this, "Nights", 2, null, null);
        if (iFieldSeq == kMealPlan1ID)
            field = new UnusedField(this, "MealPlan1ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan1Qty)
            field = new UnusedField(this, "MealPlan1Qty", 2, null, null);
        if (iFieldSeq == kMealPlan1Days)
            field = new UnusedField(this, "MealPlan1Days", 9, null, null);
        if (iFieldSeq == kMealPlan2ID)
            field = new UnusedField(this, "MealPlan2ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan2Qty)
            field = new UnusedField(this, "MealPlan2Qty", 2, null, null);
        if (iFieldSeq == kMealPlan2Days)
            field = new UnusedField(this, "MealPlan2Days", 9, null, null);
        if (iFieldSeq == kMealPlan3ID)
            field = new UnusedField(this, "MealPlan3ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan3Qty)
            field = new UnusedField(this, "MealPlan3Qty", 2, null, null);
        if (iFieldSeq == kMealPlan3Days)
            field = new UnusedField(this, "MealPlan3Days", 9, null, null);
        if (iFieldSeq == kMealPlan4ID)
            field = new UnusedField(this, "MealPlan4ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealPlan4Qty)
            field = new UnusedField(this, "MealPlan4Qty", 2, null, null);
        if (iFieldSeq == kMealPlan4Days)
            field = new UnusedField(this, "MealPlan4Days", 9, null, null);
        if (iFieldSeq == kSinglePax)
        {
            field = new UnusedField(this, "SinglePax", 4, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDoublePax)
        {
            field = new UnusedField(this, "DoublePax", 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kTriplePax)
        {
            field = new UnusedField(this, "TriplePax", 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kQuadPax)
        {
            field = new UnusedField(this, "QuadPax", 2, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kChildren)
            field = new UnusedField(this, "Children", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSingleCost)
            field = new UnusedField(this, "SingleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDoubleCost)
            field = new UnusedField(this, "DoubleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTripleCost)
            field = new UnusedField(this, "TripleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kQuadCost)
            field = new UnusedField(this, "QuadCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChildrenCost)
            field = new UnusedField(this, "ChildrenCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRoomCost)
            field = new UnusedField(this, "RoomCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMealCost)
            field = new UnusedField(this, "MealCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRoomCostLocal)
        {
            field = new UnusedField(this, "RoomCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kMealCostLocal)
        {
            field = new UnusedField(this, "MealCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new BookingAirHeaderField(this, "BookingAirHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGMTTime)
        {
            field = new TimeField(this, "GMTTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEtd)
        {
            field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kXO)
        {
            field = new StringField(this, "XO", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCityCode)
        {
            field = new StringField(this, "CityCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCityDesc)
        {
            field = new StringField(this, "CityDesc", 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCarrier)
        {
            field = new StringField(this, "Carrier", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFlightNo)
        {
            field = new StringField(this, "FlightNo", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFlightClass)
        {
            field = new StringField(this, "FlightClass", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kToCityCode)
        {
            field = new StringField(this, "ToCityCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kToCityDesc)
        {
            field = new StringField(this, "ToCityDesc", 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kArriveTime)
        {
            field = new TimeField(this, "ArriveTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAddDays)
        {
            field = new ShortField(this, "AddDays", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kARCStatus)
            field = new StringField(this, "ARCStatus", 2, null, "OK");
        if (iFieldSeq == kFareBasis)
        {
            field = new StringField(this, "FareBasis", 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStartDate)
        {
            field = new DateField(this, "StartDate", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEndDate)
        {
            field = new DateField(this, "EndDate", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAllow)
        {
            field = new StringField(this, "Allow", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDetFare)
        {
            field = new DoubleField(this, "DetFare", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSegmentConfNo)
            field = new StringField(this, "SegmentConfNo", 128, null, null);
        if (iFieldSeq == kSegmentConfirmedBy)
            field = new StringField(this, "SegmentConfirmedBy", 50, null, null);
        if (iFieldSeq == kCoupon)
        {
            field = new ShortField(this, "Coupon", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSeat)
        {
            field = new StringField(this, "Seat", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kGate)
        {
            field = new StringField(this, "Gate", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSeatPref)
        {
            field = new StringField(this, "SeatPref", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSmoking)
        {
            field = new BooleanField(this, "Smoking", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kMeals)
        {
            field = new StringField(this, "Meals", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
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
        //if (iFieldSeq == kInventoryMessageLogID)
        //  field = new (this, "InventoryMessageLogID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInfoMessageLogID)
        //  field = new (this, "InfoMessageLogID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductMessageLogID)
        //  field = new (this, "ProductMessageLogID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTotalCostUSD)
        //  field = new (this, "TotalCostUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCostMessageLogID)
        //  field = new (this, "CostMessageLogID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDetailDeleted)
        //  field = new (this, "DetailDeleted", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingAirLastField)
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
        if (iKeyArea == kBookingAirHeaderIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingAirHeaderID");
            keyArea.addKeyField(kBookingAirHeaderID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingPaxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kGMTDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCarrierKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Carrier");
            keyArea.addKeyField(kCarrier, DBConstants.ASCENDING);
            keyArea.addKeyField(kFlightNo, DBConstants.ASCENDING);
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingAirLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingAirLastKey)
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
        
        this.addListener(new SharedFileHandler(BookingDetail.kProductTypeID, ProductType.AIR_ID));
    }
    /**
     * SetupEndDate Method.
     */
    public Date setupEndDate()
    {
        Date startDate = this.getStartDate();
        Calendar calendar = DateTimeField.m_calendar;
        Date timeArrive = ((DateTimeField)this.getField(BookingAir.kArriveTime)).getDateTime();
        if (timeArrive == null)
            return startDate;   // Never
        calendar.setTime(timeArrive);
        int iHour = calendar.get(Calendar.HOUR_OF_DAY);
        int iMinute = calendar.get(Calendar.MINUTE);
        int iDays = (int)this.getField(BookingAir.kAddDays).getValue();
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
            Record recAirline = ((ReferenceField)this.getField(BookingDetail.kAirlineID)).getReference();
            if (recAirline != null)
                strProductDesc += recAirline.getField(Airline.kAirlineCode).toString();
            strProductDesc += this.getField(BookingDetail.kFlightNo).toString() + ' ';
            strProductDesc += this.getField(BookingDetail.kCityCode).toString() + '/' + this.getField(BookingDetail.kToCityCode).toString() + ' ';
            strProductDesc += this.getField(BookingDetail.kEtd).toString() + '-' + this.getField(BookingDetail.kArriveTime).toString();
            if (!this.getField(BookingDetail.kAddDays).isNull())
                strProductDesc += " " + this.getField(BookingDetail.kAddDays).toString();
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
        
        strMealDesc = this.getField(BookingAir.kMeals).toString();
        return strMealDesc;
    }
    /**
     * When a new record is set up and you have the booking and tour
     * records, init the detail fields.
     */
    public int initBookingDetailFields(Booking recBooking, Tour recTour, boolean bOnlyIfTargetIsNull)
    {
        int iErrorCode = super.initBookingDetailFields(recBooking, recTour, bOnlyIfTargetIsNull);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingAir.kRateID).isNull()))
            this.getField(BookingAir.kRateID).moveFieldToThis(recTour.getField(Tour.kAirRateID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingAir.kClassID).isNull()))
            this.getField(BookingAir.kClassID).moveFieldToThis(recTour.getField(Tour.kAirClassID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
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
        
        this.moveTargetField(recProduct, BookingDetail.kEtd, Product.kEtd);
        this.moveTargetField(recProduct, BookingDetail.kArriveTime, Air.kArriveTime);
        if (this.moveTargetField(recProduct, BookingDetail.kCityCode, Air.kCityCode))
        {
            //this.moveTargetField(recProduct, BookingDetail.kCityDesc, Air.kCityDesc);
        }
        if (this.moveTargetField(recProduct, BookingDetail.kAirlineID, Air.kAirlineID))
        {
            Record recAirline = ((ReferenceField)this.getField(BookingDetail.kAirlineID)).getReference();
            if (recAirline != null)
                this.getField(BookingDetail.kCarrier).moveFieldToThis(recAirline.getField(Airline.kDescription));
        }
        this.moveTargetField(recProduct, BookingDetail.kFlightNo, Air.kFlightNo);
        this.moveTargetField(recProduct, BookingDetail.kToCityCode, Air.kToCityCode);
        this.moveTargetField(recProduct, BookingDetail.kAddDays, Air.kAddDays);
        this.moveTargetField(recProduct, BookingDetail.kStartDate, Air.kStartDate);
        this.moveTargetField(recProduct, BookingDetail.kEndDate, Air.kEndDate);
        this.moveTargetField(recProduct, BookingDetail.kMeals, Air.kMeals);
        
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
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kEtd, TourHeaderDetail.kEtd);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kArriveTime, TourHeaderDetail.kArriveTime);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kXO, TourHeaderDetail.kXO);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kCityCode, TourHeaderDetail.kCityCode);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kCityDesc, TourHeaderDetail.kCityDesc);
            if (this.moveTargetField(recTourHeaderDetail, BookingDetail.kAirlineID, TourHeaderDetail.kAirlineID))
            {
                Record recAirline = ((ReferenceField)this.getField(BookingDetail.kAirlineID)).getReference();
                if (recAirline != null)
                    this.getField(BookingDetail.kCarrier).moveFieldToThis(recAirline.getField(Airline.kDescription));
            }
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kFlightNo, TourHeaderDetail.kFlightNo);
            //this.moveTargetField(recTourHeaderDetail, BookingDetail.kClass, TourHeaderDetail.kClass);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kToCityCode, TourHeaderDetail.kToCityCode);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kAddDays, TourHeaderDetail.kAddDays);
            //this.moveTargetField(recTourHeaderDetail, BookingDetail.kStatus, TourHeaderDetail.kStatus);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kFareBasis, TourHeaderDetail.kFareBasis);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kStartDate, TourHeaderDetail.kStartDate);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kEndDate, TourHeaderDetail.kEndDate);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kAllow, TourHeaderDetail.kAllow);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kMeals, TourHeaderDetail.kMeals);
        
            if (!recTourHeaderDetail.getField(TourHeaderAir.kTourHeaderAirHeaderID).isNull())
            { // Now move the air header reference (if it exists)
                try {
                    Record recBookingAirHeader = ((ReferenceField)this.getField(BookingAir.kBookingAirHeaderID)).getReferenceRecord();
                    recBookingAirHeader.addNew();
        
                    recBookingAirHeader.setKeyArea(BookingAirHeader.kBookingIDKey);
                    recBookingAirHeader.getField(BookingAirHeader.kBookingID).moveFieldToThis(this.getField(BookingAir.kBookingID));
                    recBookingAirHeader.getField(BookingAirHeader.kBookingPaxID).moveFieldToThis(this.getField(BookingAir.kBookingPaxID));
                    recBookingAirHeader.getField(BookingAirHeader.kModuleID).moveFieldToThis(this.getField(BookingAir.kModuleID));
                    recBookingAirHeader.getField(BookingAirHeader.kModuleStartDate).moveFieldToThis(this.getField(BookingAir.kModuleStartDate));
        
                    recBookingAirHeader.getField(BookingAirHeader.kTourHeaderDetailID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAir.kTourHeaderAirHeaderID));
        
                    if (recBookingAirHeader.seek(null))
                    {
                        this.getField(BookingAir.kBookingAirHeaderID).moveFieldToThis(recBookingAirHeader.getField(BookingAirHeader.kID));
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
    public int checkRequiredParams(int iStatusType)
    {
        if (iStatusType != BookingDetail.kInfoStatusID)
        {
            if (this.getField(BookingDetail.kRateID).isNull())
                return BookingDetail.kRateID;
            if (this.getField(BookingDetail.kClassID).isNull())
                return BookingDetail.kClassID;
        }
        return super.checkRequiredParams(iStatusType);
    }

}
