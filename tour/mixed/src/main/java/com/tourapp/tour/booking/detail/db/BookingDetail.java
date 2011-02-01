/**
 *  @(#)BookingDetail.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.thin.app.booking.entry.*;
import com.tourapp.tour.booking.detail.event.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.booking.entry.detail.land.*;
import com.tourapp.tour.booking.entry.detail.hotel.*;
import com.tourapp.tour.booking.entry.detail.detail.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.*;
import org.jbundle.base.message.trx.transport.local.*;
import org.jbundle.base.message.trx.processor.*;
import org.jbundle.base.message.trx.transport.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.history.db.*;
import java.text.*;
import org.jbundle.thin.base.db.buff.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  BookingDetail - Customer Sale Detail.
 */
public class BookingDetail extends BookingSub
{
    private static final long serialVersionUID = 1L;
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String DETAIL_DATE = "DetailDate";
    public static final String PRODUCT_ID = "ProductID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String TOTAL_COST = "TotalCost";
    public static final String REMOTE_BOOKING_NO = "RemoteBookingNo";
    public static final String PP_COST = "PPCost";
    public static final String NIGHTS = "Nights";
    public static final String ROOM_COST = "RoomCost";
    public static final String MEAL_COST = "MealCost";
    public static final String PMC_COST = "PMCCost";
    public static final String SIC_COST = "SICCost";
    public static final String DAYS = "Days";
    public static final String QUANTITY = "Quantity";

    //public static final int kID = kID;
    //public static final int kBookingID = kBookingID;
    //public static final int kBookingPaxID = kBookingPaxID;
    //public static final int kModuleID = kModuleID;
    //public static final int kTourHeaderDetailID = kTourHeaderDetailID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kDescription = kDescription;
    //public static final int kProductType = kProductType;
    //public static final int kLastChanged = kLastChanged;
    //public static final int kDeleted = kDeleted;
    public static final int kProductTypeID = kBookingSubLastField + 1;
    public static final int kDetailDate = kProductTypeID + 1;
    public static final int kGMTDate = kDetailDate + 1;
    public static final int kGMTOffset = kGMTDate + 1;
    public static final int kProductID = kGMTOffset + 1;
    public static final int kRateID = kProductID + 1;
    public static final int kClassID = kRateID + 1;
    public static final int kMarkupFromLast = kClassID + 1;
    public static final int kVendorID = kMarkupFromLast + 1;
    public static final int kTourID = kVendorID + 1;
    public static final int kApTrxID = kTourID + 1;
    public static final int kPricingDetailKey = kApTrxID + 1;
    public static final int kTotalCost = kPricingDetailKey + 1;
    public static final int kExchange = kTotalCost + 1;
    public static final int kCurrencyCode = kExchange + 1;
    public static final int kTotalCostLocal = kCurrencyCode + 1;
    public static final int kTotalPriceLocal = kTotalCostLocal + 1;
    public static final int kInfoMessageTransportID = kTotalPriceLocal + 1;
    public static final int kInfoStatusID = kInfoMessageTransportID + 1;
    public static final int kInfoRequestKey = kInfoStatusID + 1;
    public static final int kInfoStatusRequest = kInfoRequestKey + 1;
    public static final int kCostMessageTransportID = kInfoStatusRequest + 1;
    public static final int kCostStatusID = kCostMessageTransportID + 1;
    public static final int kCostRequestKey = kCostStatusID + 1;
    public static final int kCostStatusRequest = kCostRequestKey + 1;
    public static final int kInventoryMessageTransportID = kCostStatusRequest + 1;
    public static final int kInventoryStatusID = kInventoryMessageTransportID + 1;
    public static final int kInventoryRequestKey = kInventoryStatusID + 1;
    public static final int kInventoryStatusRequest = kInventoryRequestKey + 1;
    public static final int kProductMessageTransportID = kInventoryStatusRequest + 1;
    public static final int kProductStatusID = kProductMessageTransportID + 1;
    public static final int kProductRequestKey = kProductStatusID + 1;
    public static final int kProductStatusRequest = kProductRequestKey + 1;
    public static final int kRemoteBookingNo = kProductStatusRequest + 1;
    public static final int kAckDays = kRemoteBookingNo + 1;
    public static final int kDetailEndDate = kAckDays + 1;
    public static final int kGMTEndDate = kDetailEndDate + 1;
    public static final int kMealSummary = kGMTEndDate + 1;
    public static final int kStatusSummary = kMealSummary + 1;
    public static final int kItineraryDesc = kStatusSummary + 1;
    public static final int kProperties = kItineraryDesc + 1;
    public static final int kErrorProperties = kProperties + 1;
    public static final int kPPCost = kErrorProperties + 1;
    public static final int kPPPriceLocal = kPPCost + 1;
    public static final int kNights = kPPPriceLocal + 1;
    public static final int kMealPlan1ID = kNights + 1;
    public static final int kMealPlan1Qty = kMealPlan1ID + 1;
    public static final int kMealPlan1Days = kMealPlan1Qty + 1;
    public static final int kMealPlan2ID = kMealPlan1Days + 1;
    public static final int kMealPlan2Qty = kMealPlan2ID + 1;
    public static final int kMealPlan2Days = kMealPlan2Qty + 1;
    public static final int kMealPlan3ID = kMealPlan2Days + 1;
    public static final int kMealPlan3Qty = kMealPlan3ID + 1;
    public static final int kMealPlan3Days = kMealPlan3Qty + 1;
    public static final int kMealPlan4ID = kMealPlan3Days + 1;
    public static final int kMealPlan4Qty = kMealPlan4ID + 1;
    public static final int kMealPlan4Days = kMealPlan4Qty + 1;
    public static final int kSinglePax = kMealPlan4Days + 1;
    public static final int kDoublePax = kSinglePax + 1;
    public static final int kTriplePax = kDoublePax + 1;
    public static final int kQuadPax = kTriplePax + 1;
    public static final int kChildren = kQuadPax + 1;
    public static final int kSingleCost = kChildren + 1;
    public static final int kDoubleCost = kSingleCost + 1;
    public static final int kTripleCost = kDoubleCost + 1;
    public static final int kQuadCost = kTripleCost + 1;
    public static final int kChildrenCost = kQuadCost + 1;
    public static final int kRoomCost = kChildrenCost + 1;
    public static final int kMealCost = kRoomCost + 1;
    public static final int kRoomCostLocal = kMealCost + 1;
    public static final int kMealCostLocal = kRoomCostLocal + 1;
    public static final int kVariesCode = kMealCostLocal + 1;
    public static final int kVariesQty = kVariesCode + 1;
    public static final int kVariesCost = kVariesQty + 1;
    public static final int kPMCCutoff = kVariesCost + 1;
    public static final int kPMCCost = kPMCCutoff + 1;
    public static final int kSICCost = kPMCCost + 1;
    public static final int kBookingAirHeaderID = kSICCost + 1;
    public static final int kGMTTime = kBookingAirHeaderID + 1;
    public static final int kEtd = kGMTTime + 1;
    public static final int kXO = kEtd + 1;
    public static final int kCityCode = kXO + 1;
    public static final int kCityDesc = kCityCode + 1;
    public static final int kAirlineID = kCityDesc + 1;
    public static final int kCarrier = kAirlineID + 1;
    public static final int kFlightNo = kCarrier + 1;
    public static final int kFlightClass = kFlightNo + 1;
    public static final int kToCityCode = kFlightClass + 1;
    public static final int kToCityDesc = kToCityCode + 1;
    public static final int kArriveTime = kToCityDesc + 1;
    public static final int kAddDays = kArriveTime + 1;
    public static final int kARCStatus = kAddDays + 1;
    public static final int kFareBasis = kARCStatus + 1;
    public static final int kStartDate = kFareBasis + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kAllow = kEndDate + 1;
    public static final int kDetFare = kAllow + 1;
    public static final int kSegmentConfNo = kDetFare + 1;
    public static final int kSegmentConfirmedBy = kSegmentConfNo + 1;
    public static final int kCoupon = kSegmentConfirmedBy + 1;
    public static final int kSeat = kCoupon + 1;
    public static final int kGate = kSeat + 1;
    public static final int kSeatPref = kGate + 1;
    public static final int kSmoking = kSeatPref + 1;
    public static final int kMeals = kSmoking + 1;
    public static final int kDays = kMeals + 1;
    public static final int kQuantity = kDays + 1;
    public static final int kAskForAnswer = kQuantity + 1;
    public static final int kAlwaysResolve = kAskForAnswer + 1;
    public static final int kPricingTypeID = kAlwaysResolve + 1;
    public static final int kBookingDetailLastField = kPricingTypeID;
    public static final int kBookingDetailFields = kPricingTypeID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDetailAccessKey = kIDKey + 1;
    public static final int kBookingIDKey = kDetailAccessKey + 1;
    public static final int kProductIDKey = kBookingIDKey + 1;
    public static final int kApTrxIDKey = kProductIDKey + 1;
    public static final int kTourIDKey = kApTrxIDKey + 1;
    public static final int kDetailDateKey = kTourIDKey + 1;
    public static final int kVendorIDKey = kDetailDateKey + 1;
    public static final int kBookingDetailLastKey = kVendorIDKey;
    public static final int kBookingDetailKeys = kVendorIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int MESSAGE_TRANSPORT_OFFSET = BookingDetail.kInfoMessageTransportID - BookingDetail.kInfoStatusID;
    public static final int MESSAGE_KEY_OFFSET = BookingDetail.kInfoRequestKey - BookingDetail.kInfoStatusID;
    public static final int MESSAGE_REQUEST_OFFSET = BookingDetail.kInfoStatusRequest - BookingDetail.kInfoStatusID;
    public static final String MESSAGE_PARAM = "message";
    public static final String ERROR_PARAM = "error";
    public static final String INFO_PARAM = "info";
    public static final String COST_PARAM = "cost";
    public static final String INVENTORY_PARAM = "inventory";
    public static final String PRODUCT_PARAM = "product";
    protected BookingLine m_recBookingLine = null;
    /**
     * Default constructor.
     */
    public BookingDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingDetail(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recBookingLine = null;
        super.init(screen);
    }

    public static final String kBookingDetailFile = "BookingDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product Detail";
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
        return DBConstants.REMOTE | DBConstants.BASE_TABLE_CLASS | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
        {
            Object objObjectID = null;
            try {
                objObjectID = this.getHandle(DBConstants.OBJECT_ID_HANDLE);
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
            if ((this.getEditMode() == DBConstants.EDIT_ADD) || (this.getEditMode() == DBConstants.EDIT_NONE))
                if (this.getField(BookingDetail.kProductTypeID).isNull())
                    if (properties != null)
                        if (properties.get(DBConstants.OBJECT_ID) != null)
            {   // The only way to figure out the product type is to read the record
                String strObjectID = properties.get(DBConstants.OBJECT_ID).toString();
                try {
                    this.setHandle(strObjectID, DBConstants.OBJECT_ID_HANDLE);
                } catch (DBException ex) {
                    ex.printStackTrace();
                }
            }
            Record record = this.getTable().getCurrentTable().getRecord();
            if (record != null)
                if (record != this) // Record should be the concrete class
                    return record.makeScreen(itsLocation, parentScreen, iDocMode, properties);
            if (objObjectID != null)
                parentScreen.setProperty(DBConstants.STRING_OBJECT_ID_HANDLE, objObjectID.toString());
            if (this.getField(BookingDetail.kProductTypeID).getValue() == ProductType.HOTEL_ID)
                screen = new BookingHotelScreen(null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
            else //if (this.getField(BookingDetail.kProductTypeID).getValue() == ProductType.LAND_ID)
                screen = new BookingLandScreen(null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        }
        else //if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new BookingDetailGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kBookingID)
        {
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kBookingPaxID)
            field = new BookingPaxField(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
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
        if (iFieldSeq == kProductTypeID)
            field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailDate)
        {
            field = new DateTimeField(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kGMTDate)
        {
            field = new DateTimeField(this, "GMTDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kGMTOffset)
            field = new DoubleField(this, "GMTOffset", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductID)
            field = new ProductField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new BaseRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new BaseClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMarkupFromLast)
            field = new PercentField(this, "MarkupFromLast", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", 8, null, null);
        if (iFieldSeq == kTourID)
            field = new TourField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kApTrxID)
            field = new ApTrxField(this, "ApTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPricingDetailKey)
            field = new StringField(this, "PricingDetailKey", 128, null, null);
        if (iFieldSeq == kTotalCost)
            field = new FullCurrencyField(this, "TotalCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kExchange)
            field = new RealField(this, "Exchange", 10, null, new Double(1.0));
        if (iFieldSeq == kCurrencyCode)
        {
            field = new StringField(this, "CurrencyCode", 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kTotalCostLocal)
            field = new CurrencyField(this, "TotalCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalPriceLocal)
            field = new CurrencyField(this, "TotalPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInfoMessageTransportID)
            field = new MessageTransportSelect(this, "InfoMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInfoStatusID)
            field = new InfoStatusSelect(this, "InfoStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInfoRequestKey)
            field = new StringField(this, "InfoRequestKey", 128, null, null);
        if (iFieldSeq == kInfoStatusRequest)
        {
            field = new BooleanField(this, "InfoStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kCostMessageTransportID)
            field = new MessageTransportSelect(this, "CostMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCostStatusID)
            field = new CostStatusSelect(this, "CostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCostRequestKey)
            field = new StringField(this, "CostRequestKey", 128, null, null);
        if (iFieldSeq == kCostStatusRequest)
        {
            field = new BooleanField(this, "CostStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kInventoryMessageTransportID)
            field = new MessageTransportSelect(this, "InventoryMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInventoryStatusID)
            field = new InventoryStatusSelect(this, "InventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInventoryRequestKey)
            field = new StringField(this, "InventoryRequestKey", 128, null, null);
        if (iFieldSeq == kInventoryStatusRequest)
        {
            field = new BooleanField(this, "InventoryStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kProductMessageTransportID)
            field = new MessageTransportSelect(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductStatusID)
            field = new ProductStatusSelect(this, "ProductStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductRequestKey)
            field = new StringField(this, "ProductRequestKey", 128, null, null);
        if (iFieldSeq == kProductStatusRequest)
        {
            field = new BooleanField(this, "ProductStatusRequest", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kRemoteBookingNo)
            field = new StringField(this, "RemoteBookingNo", 127, null, null);
        if (iFieldSeq == kAckDays)
            field = new ShortField(this, "AckDays", 4, null, null);
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
        if (iFieldSeq == kDetailEndDate)
            field = new DateTimeField(this, "DetailEndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGMTEndDate)
            field = new DateTimeField(this, "GMTEndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        if (iFieldSeq == kItineraryDesc)
            field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProperties)
            field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kErrorProperties)
            field = new PropertiesField(this, "ErrorProperties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPPCost)
            field = new FullCurrencyField(this, "PPCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPPPriceLocal)
            field = new CurrencyField(this, "PPPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new StringField(this, "VariesCode", 1, null, "");
        if (iFieldSeq == kVariesQty)
            field = new ShortField(this, "VariesQty", 2, null, null);
        if (iFieldSeq == kVariesCost)
            field = new FullCurrencyField(this, "VariesCost", 9, null, null);
        if (iFieldSeq == kPMCCutoff)
            field = new ShortField(this, "PMCCutoff", 3, null, null);
        if (iFieldSeq == kPMCCost)
            field = new FullCurrencyField(this, "PMCCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSICCost)
            field = new FullCurrencyField(this, "SICCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        {
            field = new FloatField(this, "Days", Constants.DEFAULT_FIELD_LENGTH, null, new Float(1));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kQuantity)
        {
            field = new ShortField(this, "Quantity", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAskForAnswer)
            field = new BooleanField(this, "AskForAnswer", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kAlwaysResolve)
        {
            field = new BooleanField(this, "AlwaysResolve", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
            field.setVirtual(true);
        }
        if (iFieldSeq == kPricingTypeID)
            field = new PricingTypeSelect(this, "PricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingDetailLastField)
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
            keyArea.addKeyField(kProductTypeID, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
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
        if (iKeyArea == kDetailDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DetailDate");
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductTypeID, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kVendorIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "VendorID");
            keyArea.addKeyField(kVendorID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        BookingDetailSoftDeleteHandler listener = new BookingDetailSoftDeleteHandler(this.getField(BookingDetail.kDeleted));
        this.addListener(listener);
        listener.filterThisRecord(false);   // Display deleted record (usually switchable in screens)
        
        this.getField(BookingDetail.kProductType).addListener(new ProductTypeHandler(null));
        // Since Meals handler is such a resource hog, you should only add it manually todo(don)
        this.getField(BookingDetail.kMealSummary).addListener(new MealsHandler(null));
        this.getField(BookingDetail.kStatusSummary).addListener(new StatusHandler(null));
        
        this.getField(BookingDetail.kApTrxID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.kProductID), null, false));
        
        this.addLookupListeners();
    }
    /**
     * Add the listeners to do the price and inventory lookups.
     * Typically these are only done in the concrete classes.
     */
    public void addLookupListeners()
    {
        BaseField fldExchange = this.getField(BookingDetail.kExchange);
        this.getField(BookingDetail.kTotalCost).addListener(new CalcBalanceHandler(this.getField(BookingDetail.kTotalCostLocal), this.getField(BookingDetail.kTotalCost), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        
        String strManualTransportID = Integer.toString(((ReferenceField)this.getField(BookingDetail.kCostMessageTransportID)).getIDFromCode(MessageTransport.MANUAL));
        
        Converter converterNotInfoManualTransport = new CheckConverter(this.getField(BookingDetail.kInfoMessageTransportID), strManualTransportID, null, false);
        this.getField(BookingDetail.kInfoMessageTransportID).addListener(new RemoveConverterOnFreeHandler(converterNotInfoManualTransport));
        Converter converterNotCostManualTransport = new CheckConverter(this.getField(BookingDetail.kCostMessageTransportID), strManualTransportID, null, false);
        this.getField(BookingDetail.kCostMessageTransportID).addListener(new RemoveConverterOnFreeHandler(converterNotCostManualTransport));
        Converter converterNotInventoryManualTransport = new CheckConverter(this.getField(BookingDetail.kInventoryMessageTransportID), strManualTransportID, null, false);
        this.getField(BookingDetail.kInventoryMessageTransportID).addListener(new RemoveConverterOnFreeHandler(converterNotInventoryManualTransport));
        Converter converterNotProductManualTransport = new CheckConverter(this.getField(BookingDetail.kProductMessageTransportID), strManualTransportID, null, false);
        this.getField(BookingDetail.kProductMessageTransportID).addListener(new RemoveConverterOnFreeHandler(converterNotProductManualTransport));
        
        this.getField(BookingDetail.kInfoMessageTransportID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.kInfoStatusID), strManualTransportID, false));
        this.getField(BookingDetail.kInventoryMessageTransportID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.kInventoryStatusID), strManualTransportID, false));
        this.getField(BookingDetail.kCostMessageTransportID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.kCostStatusID), strManualTransportID, false));
        this.getField(BookingDetail.kCostMessageTransportID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.kTotalCost), strManualTransportID, false));
        this.getField(BookingDetail.kProductMessageTransportID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.kProductStatusID), strManualTransportID, false));
        
        Boolean boolRequestRequiredFlag = Boolean.TRUE;
        InitOnChangeHandler listener = null;
        this.getField(BookingDetail.kInfoMessageTransportID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.kInfoRequestKey)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.kInfoMessageTransportID).addListener(new CopyDataHandler(this.getField(BookingDetail.kInfoStatusRequest), boolRequestRequiredFlag, null));
        this.getField(BookingDetail.kInventoryMessageTransportID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.kInventoryRequestKey)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.kInventoryMessageTransportID).addListener(new CopyDataHandler(this.getField(BookingDetail.kInventoryStatusRequest), boolRequestRequiredFlag, null));
        this.getField(BookingDetail.kCostMessageTransportID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.kCostRequestKey)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.kCostMessageTransportID).addListener(new CopyDataHandler(this.getField(BookingDetail.kCostStatusRequest), boolRequestRequiredFlag, null));
        this.getField(BookingDetail.kProductMessageTransportID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.kProductRequestKey)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.kProductMessageTransportID).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, null));
        
        // First, handle new information lookups
        this.getField(BookingDetail.kProductID).addListener(new CopyDataHandler(this.getField(BookingDetail.kInfoStatusRequest), boolRequestRequiredFlag, converterNotInfoManualTransport));
        this.getField(BookingDetail.kDetailDate).addListener(new FieldDataScratchHandler(null, false));   // Don't change on refresh
        this.getField(BookingDetail.kDetailDate).addListener(new CopyDataHandler(this.getField(BookingDetail.kInfoStatusRequest), boolRequestRequiredFlag, converterNotInfoManualTransport));
        this.getField(BookingDetail.kInfoStatusRequest).addListener(new CheckRequestRequiredHandler(BookingDetail.kInfoStatusID));
        
        // If any of these values change, you will have to re-lookup the price.
        this.getField(BookingDetail.kProductID).addListener(new CopyDataHandler(this.getField(BookingDetail.kCostStatusRequest), boolRequestRequiredFlag, converterNotCostManualTransport));
        this.getField(BookingDetail.kDetailDate).addListener(new CopyDataHandler(this.getField(BookingDetail.kCostStatusRequest), boolRequestRequiredFlag, converterNotCostManualTransport));
        this.getField(BookingDetail.kDetailEndDate).addListener(new FieldListener(null)
        { // If the end data changes call this setEndDate method
            public int fieldChanged(boolean bDisplayOption, int iMoveMode)
            { // Override to do something!
                int iReturnCode = super.fieldChanged(bDisplayOption, iMoveMode);
                
                if (iMoveMode == DBConstants.SCREEN_MOVE)
                setEndDate(((DateTimeField)getOwner()).getDateTime());
                
                return iReturnCode;
            }
        });
        this.getField(BookingDetail.kRateID).addListener(new CopyDataHandler(this.getField(BookingDetail.kCostStatusRequest), boolRequestRequiredFlag, converterNotCostManualTransport));
        this.getField(BookingDetail.kClassID).addListener(new CopyDataHandler(this.getField(BookingDetail.kCostStatusRequest), boolRequestRequiredFlag, converterNotCostManualTransport));
        // If the info changes from valid AND the transport is not manual, change the info status to request required.
        FieldConverter convIfCostStatusTrueAndNotManual = new AltFieldConverter(new RadioConverter(this.getField(BookingDetail.kInfoStatusID), new Integer(CostStatus.VALID), true), converterNotCostManualTransport, Integer.toString(CostStatus.VALID));
        this.getField(BookingDetail.kInfoStatusID).addListener(new CopyDataHandler(this.getField(BookingDetail.kCostStatusRequest), boolRequestRequiredFlag, convIfCostStatusTrueAndNotManual));
        // If when you update this record a Lookup is requested, set to schedule lookup and schedule the lookup.
        this.getField(BookingDetail.kCostStatusRequest).addListener(new CheckRequestRequiredHandler(BookingDetail.kCostStatusID));
        
        // If any of these values change, you will have to re-lookup the inventory.
        this.getField(BookingDetail.kProductID).addListener(new CopyDataHandler(this.getField(BookingDetail.kInventoryStatusRequest), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        this.getField(BookingDetail.kDetailDate).addListener(new CopyDataHandler(this.getField(BookingDetail.kInventoryStatusRequest), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        this.getField(BookingDetail.kRateID).addListener(new CopyDataHandler(this.getField(BookingDetail.kInventoryStatusRequest), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        this.getField(BookingDetail.kClassID).addListener(new CopyDataHandler(this.getField(BookingDetail.kInventoryStatusRequest), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        // If the info changes from valid AND the transport is not manual, change the info status to request required.
        FieldConverter convIfInventoryStatusTrueAndNotManual = new AltFieldConverter(new RadioConverter(this.getField(BookingDetail.kInfoStatusID), new Integer(CostStatus.VALID), true), converterNotInventoryManualTransport, Integer.toString(CostStatus.VALID));
        this.getField(BookingDetail.kInfoStatusID).addListener(new CopyDataHandler(this.getField(BookingDetail.kInventoryStatusRequest), boolRequestRequiredFlag, convIfInventoryStatusTrueAndNotManual));
        // If when you update this record a Lookup is requested, set to schedule lookup and schedule the lookup.
        this.getField(BookingDetail.kInventoryStatusRequest).addListener(new CheckRequestRequiredHandler(BookingDetail.kInventoryStatusID));
        // If these change, re-request the booking
        this.getField(BookingDetail.kProductID).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.kDetailDate).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.kRateID).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.kClassID).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.kDeleted).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, converterNotProductManualTransport));
        // If the info changes from valid AND the transport is not manual, change the info status to request required.
        FieldConverter convIfProductStatusTrueAndNotManual = new AltFieldConverter(new RadioConverter(this.getField(BookingDetail.kInfoStatusID), new Integer(CostStatus.VALID), true), converterNotProductManualTransport, Integer.toString(CostStatus.VALID));
        this.getField(BookingDetail.kInfoStatusID).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, convIfProductStatusTrueAndNotManual));
        this.getField(BookingDetail.kInventoryStatusID).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, convIfProductStatusTrueAndNotManual));
        this.getField(BookingDetail.kCostStatusID).addListener(new CopyDataHandler(this.getField(BookingDetail.kProductStatusRequest), boolRequestRequiredFlag, convIfProductStatusTrueAndNotManual));
        // When a product booking is requested, this listener will set up the message
        this.getField(BookingDetail.kProductStatusRequest).addListener(new CheckRequestRequiredHandler(BookingDetail.kProductStatusID));
        
        this.getField(BookingDetail.kMarkupFromLast).addListener(new CopyDataHandler(this.getField(BookingDetail.kCostStatusRequest), boolRequestRequiredFlag, converterNotCostManualTransport));
        FieldDataScratchHandler fieldListener = null;
        this.getField(BookingDetail.kMarkupFromLast).addListener(fieldListener = new FieldDataScratchHandler(null)
        {
            public int fieldChanged(boolean bDisplayOption, int iMoveMode)
            {
                if (iMoveMode == DBConstants.SCREEN_MOVE)   // This is necessary if an override changes the repondsTo.
                {
                    Object data = this.getOriginalData();
                    float fOrig = 0;
                    if (data instanceof Float)
                        fOrig = ((Float)data).floatValue();
                    float fCurrent = (float)this.getOwner().getValue();
                    if (fCurrent != 0)
                        if (fCurrent != fOrig)
                    {
                        double dCost = this.getOwner().getRecord().getField(BookingDetail.kTotalCost).getValue();
                        dCost = Math.floor((dCost / (1 + fOrig)) * (1 + fCurrent) * 100 + 0.5) / 100;
                        this.getOwner().getRecord().getField(BookingDetail.kTotalCost).setValue(dCost);
                    }
                    this.setOriginalData(this.getOwner().getData());
                }
                return super.fieldChanged(bDisplayOption, iMoveMode);
            }
        });
        fieldListener.setRespondsToMode(DBConstants.SCREEN_MOVE, true);
        
        this.addListener(new UpdateTourStatusHandler(BookingDetail.kInfoStatusID));
        this.addListener(new UpdateTourStatusHandler(BookingDetail.kInventoryStatusID));
        this.addListener(new UpdateTourStatusHandler(BookingDetail.kCostStatusID));
        this.addListener(new UpdateTourStatusHandler(BookingDetail.kProductStatusID));
        
        this.addListener(new BookingDetailPriceChangeHandler(null));
    }
    /**
     * Override this to add record listeners and filters to every screen where this is the main record.
     * @param screen The screen these listeners will be in.
     */
    public void addScreenListeners(RecordOwner screen)
    {
        super.addScreenListeners(screen);
        String strManualTransportID = Integer.toString(((ReferenceField)this.getField(BookingDetail.kCostMessageTransportID)).getIDFromCode(MessageTransport.MANUAL));
        if (((ReferenceField)this.getField(BookingDetail.kProductID)).getReferenceRecord() != null)
            this.getField(BookingDetail.kInfoMessageTransportID).addListener(new ManualProductInfoHandler(((ReferenceField)this.getField(BookingDetail.kProductID)).getReferenceRecord().getField(Product.kDescription), strManualTransportID, false));
    }
    /**
     * AddSlaveListeners Method.
     */
    public void addSlaveListeners()
    {
        super.addSlaveListeners();
        
        this.addListener(new HistoryHandler(BookingDetailHistory.class.getName(), BookingDetailHistory.kHistoryDate, -1));
    }
    /**
     * Get the record type from the field that specifies the record type.
     * (Override this).
     * @return The record type (as an object).
     */
    public BaseField getSharedRecordTypeKey()
    {
        return this.getField(BookingDetail.kProductTypeID);
    }
    /**
     * Get the shared record that goes with this key.
     * (Always override this).
     * @param objKey The value that specifies the record type.
     * @return The correct (new) record for this type (or null if none).
     */
    public Record createSharedRecord(Object objKey, RecordOwner recordOwner)
    {
        if (objKey instanceof Integer)
        {
            int iProductType = ((Integer)objKey).intValue();
            if (iProductType == ProductType.HOTEL_ID)
                return new BookingHotel(recordOwner);
            if (iProductType == ProductType.LAND_ID)
                return new BookingLand(recordOwner);
            if (iProductType == ProductType.AIR_ID)
                return new BookingAir(recordOwner);
            if (iProductType == ProductType.CAR_ID)
                return new BookingCar(recordOwner);
            if (iProductType == ProductType.CRUISE_ID)
                return new BookingCruise(recordOwner);
            if (iProductType == ProductType.ITEM_ID)
                return new BookingItem(recordOwner);
            if (iProductType == ProductType.TOUR_ID)
                return new BookingTour(recordOwner);
            if (iProductType == ProductType.TRANSPORTATION_ID)
                return new BookingTransportation(recordOwner);
        }
        return null;
    }
    /**
     * CreateSharedDetailRecord Method.
     */
    public static Record createSharedDetailRecord(String strProductType, RecordOwner recordOwner)
    {
        if (ProductType.HOTEL.equalsIgnoreCase(strProductType))
            return new BookingHotel(recordOwner);
        if (ProductType.LAND.equalsIgnoreCase(strProductType))
            return new BookingLand(recordOwner);
        if (ProductType.AIR.equalsIgnoreCase(strProductType))
            return new BookingAir(recordOwner);
        if (ProductType.CAR.equalsIgnoreCase(strProductType))
            return new BookingCar(recordOwner);
        if (ProductType.CRUISE.equalsIgnoreCase(strProductType))
            return new BookingCruise(recordOwner);
        if (ProductType.ITEM.equalsIgnoreCase(strProductType))
            return new BookingItem(recordOwner);
        if (ProductType.TOUR.equalsIgnoreCase(strProductType))
            return new BookingTour(recordOwner);
        if (ProductType.TRANSPORTATION.equalsIgnoreCase(strProductType))
            return new BookingTransportation(recordOwner);
        return null;

    }
    /**
     * AddDetailBehaviors Method.
     */
    public void addDetailBehaviors(Booking recBooking, Tour recTour)
    {
        // NOTE: This next set of logic should really go somewhere else, but this date/city default logic is easy here.
        Record screenRecord = null;
        if ((recBooking.getRecordOwner() != null)
            && (recBooking.getRecordOwner().getScreenRecord() instanceof BookingScreenRecord))
        {
            screenRecord = recBooking.getRecordOwner().getScreenRecord();
            Record recProduct = ((ReferenceField)this.getField(BookingDetail.kProductID)).getReferenceRecord(this.getRecordOwner());
            // Note that I add these listeners in reverse since they always do the other listeners before they do themselves.
            if (recProduct != null)
            {
                if (recProduct instanceof TransportProduct)
                    this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.kLastCityID), ((TransportProduct)recProduct).getField(TransportProduct.kToCityID), null, false, false, false, true, true, null, true));
                this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.kLastCityID), recProduct.getField(Product.kCityID), null, false, false, false, true, true, null, false));
            }
            // If the end date is non-null, use it!
            this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.kLastDate), this.getField(BookingDetail.kDetailDate), null, false, false, false, true, true, null, false));
            this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.kLastDate), this.getField(BookingDetail.kDetailEndDate), null, false, false, false, true, true, null, true));
            
            FieldListener listener = this.getField(BookingDetail.kDetailDate).getListener(InitOnceFieldHandler.class.getName());
            if (listener != null)
                this.getField(BookingDetail.kDetailDate).removeListener(listener, true);
            this.getField(BookingDetail.kDetailDate).addListener(new InitFieldHandler(screenRecord.getField(BookingScreenRecord.kLastDate), false));
        }
        // Note these next listener are just a backup if the screenRecord date is null or not available. (First departure, then today)
        this.getField(BookingDetail.kDetailDate).addListener(new InitFieldHandler(new Date()));
        this.getField(BookingDetail.kDetailDate).addListener(new InitFieldHandler(recTour.getField(Tour.kDepartureDate), false));
        
        // Make sure header is up-to-date for possible server record changes.
        this.addListener(new WriteOnUpdateHandler(recBooking, true));
        super.addDetailBehaviors(recBooking, recTour); 
        
        this.setOpenMode(this.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
    }
    /**
     * Get the product for this detail.
     */
    public Product getProduct()
    {
        ((ReferenceField)this.getField(BookingDetail.kProductID)).getReferenceRecord(this.getRecordOwner());    // Reference same recordowner
        return (Product)((ReferenceField)this.getField(BookingDetail.kProductID)).getReference();
    }
    /**
     * Set the start date for this item.
     * This is here so you can do an action when the user changes the start date.
     * The StartDateHandler is added automatically to this class.
     */
    public Date setStartDate(Date time)
    {
        ((DateTimeField)this.getField(BookingDetail.kDetailDate)).setDateTime(time, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        return ((DateTimeField)this.getField(BookingDetail.kDetailDate)).getDateTime();
    }
    /**
     * Get the start date and time for this product.
     * Return null if there is no date and time.
     */
    public Date getStartDate()
    {
        return ((DateTimeField)this.getField(BookingDetail.kDetailDate)).getDateTime();
    }
    /**
     * Set up the date and time for this detail item 
     * @return The start date.
     */
    public Date setupStartDate(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        Calendar calendar = DateTimeField.m_calendar;
        Date timeDetail = null;  // Time portion
        boolean bSetTime = false;
        
        if (recTourHeaderDetail != null)
        {
            // If you passed tour detail, use this tour detail to create the date/time for this booking detail
            // Date comes from the # days offset for this detail
            // Time comes from the detail time field (if null, use the product's etd time)
            if ((dateStart == null) || (dateStart.getTime() == 0))
                dateStart = ((DateTimeField)recTour.getField(Tour.kDepartureDate)).getDateTime();
            calendar.setTime(dateStart);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            int iDaysOffset = (int)recTourHeaderDetail.getField(TourHeaderDetail.kDay).getValue();
            iDaysOffset -= 1; // Day 1 = offset +0
            calendar.add(Calendar.DATE, iDaysOffset);
            dateStart = calendar.getTime();     // Start date
            if (!recTourHeaderDetail.getField(TourHeaderDetail.kEtd).isNull())
            {
                timeDetail = ((DateTimeField)recTourHeaderDetail.getField(TourHeaderDetail.kEtd)).getDateTime();    // Start time
                bSetTime = true;    // Use this time!
            }
        }
        
        Product recProduct = this.getProduct();
        if (!bSetTime)
            if ((recProduct != null) && (!recProduct.getField(Product.kEtd).isNull()))
        {
            timeDetail = ((TimeField)recProduct.getField(Product.kEtd)).getDateTime();
            bSetTime = true;    // Did supply a time
        }
        
        if ((dateStart == null) || (dateStart.getTime() == 0)
            || ((recTourHeaderDetail == null) && (!this.getField(BookingDetail.kDetailDate).isNull())))
                dateStart = ((DateTimeField)this.getField(BookingDetail.kDetailDate)).getDateTime();    // Don't change date (change time only if 0 or 12)
        if (dateStart != null)
            calendar.setTime(dateStart); // Time portion
        if (!bSetTime)
            if (((calendar.get(Calendar.HOUR_OF_DAY) != 0) && (calendar.get(Calendar.HOUR_OF_DAY) != 12)) || (calendar.get(Calendar.MINUTE) != 0) || (calendar.get(Calendar.SECOND) != 0) || (calendar.get(Calendar.MILLISECOND) != 0))
        {
            timeDetail = dateStart;
            bSetTime = true;    // Did supply a time
        }
        
        if (timeDetail == null)
            bSetTime = false;   // Never (being careful)
        
        if (!bSetTime)
        {   // If 
            calendar.setTime(new Date());
            // Default time is noon, not midnight.
            calendar.set(Calendar.HOUR_OF_DAY, 12);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            timeDetail = calendar.getTime();
        }
        else
        {
            calendar.setTime(timeDetail);
            if ((calendar.get(Calendar.HOUR_OF_DAY) == 12) || (calendar.get(Calendar.MINUTE) == 0) || (calendar.get(Calendar.SECOND) == 0) || (calendar.get(Calendar.MILLISECOND) == 0))
            {       // Special case - user specified 12:00PM exactly, by adding this, it won't auto-change to another value.
                calendar.set(Calendar.MILLISECOND, 1);
                timeDetail = calendar.getTime();
            }
        }
        
        boolean[] rgbEnabled = this.getField(BookingDetail.kDetailDate).setEnableListeners(false);  // Don't call again
        ((DateTimeField)this.getField(BookingDetail.kDetailDate)).setDate(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        if (timeDetail != null)
            ((DateTimeField)this.getField(BookingDetail.kDetailDate)).setTime(timeDetail, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingDetail.kDetailDate).setEnableListeners(rgbEnabled);
        
        return ((DateTimeField)this.getField(BookingDetail.kDetailDate)).getDateTime();
    }
    /**
     * Set the ending time for this tour product.
     * Then, return the actual ending date that was set.
     */
    public Date setEndDate(Date time)
    {
        return this.getEndDate();   // By default, don't allow changes
    }
    /**
     * Get the end product date and time.
     * @return The date.
     */
    public Date getEndDate()
    {
        return ((DateTimeField)this.getField(BookingDetail.kDetailEndDate)).getDateTime();
    }
    /**
     * SetupEndDate Method.
     */
    public Date setupEndDate()
    {
        long lLengthTime = 0;
        Product recProduct = this.getProduct();
        if (recProduct != null)
            lLengthTime = recProduct.getLengthTime();
        Date dateStart = this.getStartDate();
        Date dateEnd = null;
        if (dateStart != null)
            dateEnd = new Date(dateStart.getTime() + lLengthTime);
        return dateEnd;
    }
    /**
     * Calc the GMT start and end dates.
     */
    public void setupGMTDates()
    {
        Product recProduct = this.getProduct();
        if ((recProduct != null) &&
            ((recProduct.getEditMode() == DBConstants.EDIT_IN_PROGRESS) || (recProduct.getEditMode() == DBConstants.EDIT_CURRENT)))
        {
            Record recCity = ((ReferenceField)recProduct.getField(Product.kCityID)).getReference();
            if (recCity != null)
            {
                this.getField(BookingDetail.kGMTOffset).moveFieldToThis(recCity.getField(City.kGMTOffset));
                if (recCity.getField(City.kGMTOffset).isNull())
                {
                    Record recCountry = ((ReferenceField)recCity.getField(City.kCountryID)).getReference();
                    if (recCountry != null)
                    {
                        this.getField(BookingDetail.kGMTOffset).moveFieldToThis(recCountry.getField(Country.kGMTOffset));
                        if (this.getField(BookingDetail.kGMTOffset).isNull())
                        {
                            Record recState = ((ReferenceField)recCity.getField(City.kStateID)).getReference();
                            if (recState != null)
                                this.getField(BookingDetail.kGMTOffset).moveFieldToThis(recState.getField(State.kGMTOffset));
                        }
                    }
                }
            }
        }
        Calendar calendar = DateTimeField.m_calendar;
        
        Date date = this.getStartDate();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, (int)this.getField(BookingDetail.kGMTOffset).getValue());
        date = calendar.getTime();
        ((DateTimeField)this.getField(BookingDetail.kGMTDate)).setDateTime(date,  DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        date = this.getEndDate();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, (int)this.getField(BookingDetail.kGMTOffset).getValue());
        date = calendar.getTime();
        ((DateTimeField)this.getField(BookingDetail.kGMTEndDate)).setDateTime(date,  DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * Get the description of the product for this line item.
     * Usually, you just get the description of the current product.
     * For manual lines, the manual description is returned.
     */
    public String getProductDesc()
    {
        return this.getField(BookingDetail.kDescription).toString();

    }
    /**
     * From the current detail and product info,
     * setup the product description.
     * @return The product description (using the product).
     */
    public String setupProductDesc()
    {
        Product recProduct = this.getProduct();
        if (recProduct == null)
            return this.getField(BookingDetail.kDescription).toString();
        return recProduct.getField(Product.kDescription).toString();
    }
    /**
     * Get the meals on this day. If bDetailedDesc is false, a very short (1-3 char) desc is returned.
     */
    public String getMealDesc(Date dateTarget, boolean bDetailedDesc, Record recMealPlan)
    {
        return Constants.BLANK;
    }
    /**
     * Get the meal description from these meal counts.
     */
    public String getMealDescFromCount(Date dateTarget, boolean bDetailedDesc, Record recMealPlan, short sBreakfasts, short sLunches, short sDinners)
    {
        Calendar calendar = DateTimeField.m_calendar;
        String strMealDesc = Constants.BLANK;
        Date dateStart = this.getStartDate();
        calendar.setTime(dateStart);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateStart = calendar.getTime();
        
        calendar.setTime(dateTarget);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateTarget = calendar.getTime();
        int day = (int)((dateTarget.getTime() - dateStart.getTime()) / DBConstants.KMS_IN_A_DAY);
        if (day < 0)
            return strMealDesc; // Return blank... can't have a meal before day 0
        
        if (sBreakfasts >= day + 1)
        {
            if (bDetailedDesc)
                strMealDesc += "Breakfast";
            else
                strMealDesc += "B";
        }
        if (sLunches >= day + 1)
        {
            if (strMealDesc.length() > 0)
                strMealDesc += " ";
            if (bDetailedDesc)
                strMealDesc += "Lunch";
            else
                strMealDesc += "L";
        }
        if (sDinners >= day + 1)
        {
            if (strMealDesc.length() > 0)
                strMealDesc += " ";
            if (bDetailedDesc)
                strMealDesc += "Dinner";
            else
                strMealDesc += "D";
        }
        return strMealDesc;
    }
    /**
     * When a new record is set up and you have the booking and tour
     * records, init the detail fields.
     */
    public int initBookingDetailFields(Booking recBooking, Tour recTour, boolean bOnlyIfTargetIsNull)
    {
        int iErrorCode = super.initBookingDetailFields(recBooking, recTour, bOnlyIfTargetIsNull);
        this.getField(BookingDetail.kTourID).moveFieldToThis(recTour.getField(Tour.kID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return iErrorCode;
    }
    /**
     * Set-up the current product info.
     * If properties are supplied, look in the properties for new values.
     * Else, if the target values are not already set, use the default values
     * supplied in the tour and booking records.
     */
    public int setDetailProductInfo(Map<String,Object> properties, TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        if (((recTourHeaderDetail == null)
            && (this.getField(BookingDetail.kProductID).getLength() == 0))
            || ((recTourHeaderDetail == null)
            && (this.getField(BookingDetail.kDetailDate).getLength() == 0)))
        {
            this.getField(BookingDetail.kInfoStatusID).setValue(CostStatus.DATA_REQUIRED);
            String strError = "Data required in the {0} field";
            strError = this.getTask().getApplication().getResources(ThinResourceConstants.ERROR_RESOURCE, true).getString(strError);
            strError = MessageFormat.format(strError, this.getField(BookingDetail.kInfoStatusID).getFieldDesc());
            this.setErrorMessage(BookingDetail.kInfoStatusID, strError);
            // Even though there is an error, continue setting the other properties (hey behaviors are disabled)
        }
        if (recBooking == null)
            recBooking = this.getBooking(true);
        if (recTour == null)
        {
            if (this.getField(BookingDetail.kTourID).isNull())
                recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
            else
                recTour = (Tour)((ReferenceField)this.getField(BookingDetail.kTourID)).getReference();
        }
        
        boolean[] rgbEnabled = this.setEnableListeners(false);
        Object[] rgobjEnabled = this.setEnableFieldListeners(false);
        
        // Save the current state so I know what was changed afterwards
        int iFieldsTypes = BaseBuffer.ALL_FIELDS;
        BaseBuffer buffer = new VectorBuffer(null, iFieldsTypes);
        buffer.fieldsToBuffer(this, iFieldsTypes);
        boolean[] rgbModified = this.getModified();
        
        this.initBookingDetailFields(recBooking, recTour, true);    // This will init any fields that aren't already set up.
        if (this.getField(BookingDetail.kInfoMessageTransportID).isNull())
        {
            int iDefaultInfoTransport = ((ReferenceField)this.getField(BookingDetail.kInfoMessageTransportID)).getIDFromCode(MessageTransport.DEFAULT);
            this.getField(BookingDetail.kInfoMessageTransportID).setValue(iDefaultInfoTransport, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
        
        if (this.getField(BookingDetail.kProductID).isJustModified())
        {   // Note: No need to enable behaviors, since if info status changes, the other status' behaviors will be called also. 
            this.getField(BookingDetail.kInfoStatusID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.kInfoRequestKey).initField(DBConstants.DISPLAY);    // Zero this out
            this.getField(BookingDetail.kCostMessageTransportID).initField(DBConstants.DISPLAY);
            this.getField(BookingDetail.kCostStatusID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.kCostRequestKey).initField(DBConstants.DISPLAY);    // Zero this out
            this.getField(BookingDetail.kInventoryMessageTransportID).initField(DBConstants.DISPLAY);
            this.getField(BookingDetail.kInventoryStatusID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.kInventoryRequestKey).initField(DBConstants.DISPLAY);    // Zero this out
            this.getField(BookingDetail.kProductMessageTransportID).initField(DBConstants.DISPLAY);
            this.getField(BookingDetail.kProductStatusID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.kProductRequestKey).initField(DBConstants.DISPLAY);    // Zero this out
        }
        
        int iErrorCode = super.setDetailProductInfo(properties, recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
        
        this.setEnableListeners(rgbEnabled);
        this.setEnableFieldListeners(rgobjEnabled);
        
        this.checkAndHandleFieldChanges(buffer, rgbModified, false);   // Call listeners of any changed fields
        
        return iErrorCode;
    }
    /**
     * For this tour detail, move all the detail field to the current booking line item detail.
     */
    public int setDetailProductFields(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        int iErrorCode = super.setDetailProductFields(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
        if (recTourHeaderDetail != null)
            this.getField(BookingDetail.kProductID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.kProductID));
        Product recProduct = this.getProduct();
        if (recProduct != null)
            if ((recProduct.getEditMode() == DBConstants.EDIT_NONE) || (recProduct.getEditMode() == DBConstants.EDIT_ADD))
                recProduct = null;
        
        boolean[] rgbModifiedBefore = this.getModified();
        if (recProduct != null)
            this.moveProductFields(recProduct);
        boolean[] rgbModifiedAfterProduct = this.getModified();
        
        this.setModified(rgbModifiedBefore);
        this.moveTourHeaderFields(recTourHeaderDetail, recTour);
        
        boolean[] rgbModifiedAfterTourHeader = this.getModified();
        for (int i = 0; i < rgbModifiedAfterTourHeader.length; i++)
        {   // If it was modified anywhere, set modified flag.
            rgbModifiedAfterTourHeader[i] = rgbModifiedBefore[i] | rgbModifiedAfterProduct[i] | rgbModifiedAfterTourHeader[i];
        }
        this.setModified(rgbModifiedAfterTourHeader);
        
        Date dateStart = ((DateTimeField)this.getField(BookingDetail.kModuleStartDate)).getDateTime();
        Date date = this.setupStartDate(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
        ((DateTimeField)this.getField(BookingDetail.kDetailDate)).setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        date = this.setupEndDate();
        ((DateTimeField)this.getField(BookingDetail.kDetailEndDate)).setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        String strDesc = this.setupProductDesc();
        this.getField(BookingDetail.kDescription).setString(strDesc);
        this.setupGMTDates();
        
        this.getField(BookingDetail.kTourID).moveFieldToThis(recTour.getField(Tour.kID));   // Could be fake tour or dual series
        
        return iErrorCode;
    }
    /**
     * Move data from the product record to this record.
     * @param recProduct the souce product record.
     * @return An error code.
     */
    public int moveProductFields(Product recProduct)
    {
        if (this.getField(BookingDetail.kAckDays).isNull())
            this.moveTargetField(recProduct, BookingDetail.kAckDays, Product.kAckDays);
        
        this.getField(BookingDetail.kVendorID).setModified(false);  // This will be auto-restored in calling (setDetailProductFields) method.
        this.getField(BookingDetail.kVendorID).moveFieldToThis(recProduct.getField(Product.kVendorID));
        if (this.getField(BookingDetail.kVendorID).isModified())
        {
            Record recVendor = ((ReferenceField)recProduct.getField(Product.kVendorID)).getReference();
            if (recVendor != null)
            {
                Record recCurrency = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReference();
                if (recCurrency != null)
                {
                    if (!recCurrency.getField(Currencys.kCostingRate).isNull())
                        this.getField(BookingDetail.kExchange).moveFieldToThis(recCurrency.getField(Currencys.kCostingRate));
                    else
                        this.getField(BookingDetail.kExchange).moveFieldToThis(recCurrency.getField(Currencys.kLastRate));
                }
            }
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Move from the tour header record to this record.
     * NOTE: Be careful as the TourHeaderDetail record can be null (so fields that depend on Tour can be set).
     * @param recTourHeaderDetail The tour header record.
     * @return An error code.
     */
    public int moveTourHeaderFields(TourSub recTourHeaderDetail, Tour recTour)
    {
        if (recTourHeaderDetail != null)
        {
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kInfoMessageTransportID, TourHeaderDetail.kInfoMessageTransportID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kInfoStatusID, TourHeaderDetail.kInfoStatusID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kInventoryMessageTransportID, TourHeaderDetail.kInventoryMessageTransportID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kInventoryStatusID, TourHeaderDetail.kInventoryStatusID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kCostMessageTransportID, TourHeaderDetail.kCostMessageTransportID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kCostStatusID, TourHeaderDetail.kCostStatusID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kProductMessageTransportID, TourHeaderDetail.kProductMessageTransportID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kProductStatusID, TourHeaderDetail.kProductStatusID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kAckDays, TourHeaderDetail.kAckDays);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kRateID, TourHeaderDetail.kRateID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.kClassID, TourHeaderDetail.kClassID);
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Move from the target field to this field
     * If this field is unmodified and the target is not null.
     * @param recTarget The source record
     * @param iDetailField The destinatation field in BookingDetail
     * @param iTargetField The source field.
     * @return true If the field was changed.
     */
    public boolean moveTargetField(Record recTarget, int iDetailField, int iTargetField)
    {
        if (!this.getField(iDetailField).isModified()) if (!recTarget.getField(iTargetField).isNull())
        {
            FieldListener listener = this.getField(iDetailField).getListener(InitOnceFieldHandler.class);
            boolean bOldState = false;
            if (listener != null)
                bOldState = listener.setEnabledListener(false);
            this.getField(iDetailField).moveFieldToThis(recTarget.getField(iTargetField), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            if (listener != null)
                listener.setEnabledListener(bOldState);
            return true;
        }
        return false;
    }
    /**
     * Setup the detail key, given this tour detail record.
     */
    public int setupDetailKey(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        Date date = this.setupStartDate(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
        ((DateTimeField)this.getField(BookingDetail.kDetailDate)).setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingDetail.kProductTypeID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.kProductTypeID));
        int iErrorCode = super.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
        return iErrorCode;
    }
    /**
     * Get Pax using this service.
     */
    public short getNoPax()
    {
        if (this.getField(BookingDetail.kBookingPaxID).getValue() != 0)
            return 1; // Pax mod
        else
        {
            Record recBooking = this.getBooking(true);
            if (recBooking == null)
                return 1;
            return (short)recBooking.getField(Booking.kPax).getValue();
        }
    }
    /**
     * How many of this type of passenger (ie., single, double, etc.) are in this type of room?.
     */
    public int getPaxInRoom(int iRoomType)
    {
        if (this.getField(BookingDetail.kBookingPaxID).getValue() != 0)
            return 1; // Pax mod **FIX THIS**
        else
        {
            if ((iRoomType >= PaxCategory.SINGLE_ID)
                && (iRoomType <= PaxCategory.CHILD_ID))
            {
                int iFieldSeq = Booking.kSinglePax + iRoomType - PaxCategory.SINGLE_ID;
                return (int)this.getBooking(true).getField(iFieldSeq).getValue();
            }
            else
                return this.getBooking(true).getCountPax();
        }
    }
    /**
     * Get the overall status of this line item.
     * This includes status of the pricing, inventory, date, etc.
     */
    public int getStatus()
    {
        int iStatus = (1 << 0);   // Normal status
        if ((this.getField(BookingDetail.kInfoStatusID).getValue() != InfoStatus.VALID) && (this.getField(BookingDetail.kInfoStatusID).getValue() != InfoStatus.NO_STATUS) && (this.getField(BookingDetail.kInfoStatusID).getValue() != InfoStatus.NULL_STATUS))
            iStatus = iStatus | (1 << BookingConstants.INFO_LOOKUP);
        if ((this.getField(BookingDetail.kCostStatusID).getValue() != CostStatus.VALID) && (this.getField(BookingDetail.kCostStatusID).getValue() != CostStatus.NO_STATUS) && (this.getField(BookingDetail.kCostStatusID).getValue() != CostStatus.NULL_STATUS))
            iStatus = iStatus | (1 << BookingConstants.COST_LOOKUP);
        if ((this.getField(BookingDetail.kInventoryStatusID).getValue() != InventoryStatus.VALID) && (this.getField(BookingDetail.kInventoryStatusID).getValue() != InventoryStatus.NO_STATUS) && (this.getField(BookingDetail.kInventoryStatusID).getValue() != InventoryStatus.NULL_STATUS))
            iStatus = iStatus | (1 << BookingConstants.INVENTORY_LOOKUP);
        if ((this.getField(BookingDetail.kProductStatusID).getValue() != ProductStatus.VALID) && (this.getField(BookingDetail.kProductStatusID).getValue() != ProductStatus.NO_STATUS) && (this.getField(BookingDetail.kProductStatusID).getValue() != ProductStatus.NULL_STATUS) && (this.getField(BookingDetail.kProductStatusID).getValue() != ProductStatus.PROPOSAL) && (this.getField(BookingDetail.kProductStatusID).getValue() != ProductStatus.CANCELED))
            iStatus = iStatus | (1 << BookingConstants.PRODUCT_LOOKUP);
        return iStatus;
    }
    /**
     * Get the starting icon for this product.
     * (Default to the standard icon).
     */
    public String getStartIcon()
    {
        return this.getBitmap();
    }
    /**
     * Get the ending icon for this in a calendar.
     */
    public String getEndIcon()
    {
        return null;
    }
    /**
     * Get the icon that goes with this file.
     */
    public String getBitmap()
    {
        return BookingConstants.BUTTON_LOCATION + this.getField(BookingDetail.kProductType).toString();
    }
    /**
     * Check if this request is required and process the message.
     * Then update the status to reflect the new status of the update.
     * Note: Usually you don't call this method directly, the best way to check
     * for a request is to set the corresponding flag (ie., InfoStatusRequest) to true.
     * @param iStatusType The status field to update.
     */
    public void checkRequestRequired(int iStatusType)
    {
        int iFieldSeq = this.checkRequiredParams(iStatusType);  // Quickly check the fields to keep from building a message every time
        if (iFieldSeq > 0)
        {
            this.getField(iStatusType).setValue(BaseDataStatus.DATA_REQUIRED, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            String strError = "Data required in the {0} field";
            if (this.getTask() != null)
                if (this.getTask().getApplication() != null)
                    strError = this.getTask().getApplication().getResources(ThinResourceConstants.ERROR_RESOURCE, true).getString(strError);
            strError = MessageFormat.format(strError, this.getField(iFieldSeq).getFieldDesc());
            this.setErrorMessage(iStatusType, strError);
            return;   // Don't even start, we still need some basic information.
        }
        if (iStatusType == BookingDetail.kInfoStatusID)
            if (this.getField(BookingDetail.kInfoMessageTransportID).isNull())
        {
            int iDefaultInfoTransport = ((ReferenceField)this.getField(BookingDetail.kInfoMessageTransportID)).getIDFromCode(MessageTransport.DEFAULT);
            this.getField(BookingDetail.kInfoMessageTransportID).setValue(iDefaultInfoTransport);
        }
        BaseProductMessageDesc message = this.checkMessageRequired(iStatusType);
        if (message == null)
            return;
        int iStatus = (int)this.getField(iStatusType).getValue();
        
        int iErrorCode = message.initForMessage(this);
        if (iErrorCode != DBConstants.NORMAL_RETURN)  // Clear the product cost info
        { // Handle error in initialization.
            String strErrorMessage = "Error on message initialization";
            if (this.getRecordOwner() != null)
                if (this.getRecordOwner().getTask() != null)
                    strErrorMessage = this.getRecordOwner().getTask().getLastError(iErrorCode);
            ((ProductMessageData)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).setRecordDataStatus(this, iStatusType, BaseDataStatus.NOT_VALID);
            this.setErrorMessage(iStatusType, strErrorMessage);
            return;   // Don't even start, error initializing.
        }
        TrxMessageHeader trxMessageHeader = (TrxMessageHeader)message.getMessage().getMessageHeader();
        if (MessageTransport.MANUAL.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.SEND_MESSAGE_BY_PARAM)))
        {
            // Manually sent messages are not changed. The user sets the message status manually.
        }
        else
        {
            if (MessageTransportTypeField.MANUAL_RESPONSE.equals(trxMessageHeader.get(MessageTransport.TRANSPORT_TYPE_PARAM)))
            {   // If you are looking for a manual lookup, make sure I specifically say it is okay.
                iStatus = BaseDataStatus.MANUAL_REQUEST_REQUIRED; // Can't do a manual lookup by default (takes too long).
                if (DBConstants.TRUE.equals(trxMessageHeader.get(MessageTransport.MANUAL_RESPONSE_PARAM)))
                    iStatus = BaseDataStatus.MANUAL_REQUEST_SENT; // Unless you specifically ask for it.
            }
            else
                iStatus = BaseDataStatus.REQUEST_SENT;
            ((ProductMessageData)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).setRecordDataStatus(this, iStatusType, iStatus);
            if ((iStatus == BaseDataStatus.MANUAL_REQUEST_SENT) || (iStatus == BaseDataStatus.REQUEST_SENT))
            {   // Only lookup if automatic
                try {
                    this.addListener(new SendMessageAfterUpdateHandler(message.getMessage()));
                    Record recTour = ((ReferenceField)this.getBooking(true).getField(Booking.kTourID)).getReference();
                    recTour.writeAndRefresh();
                    this.getBooking(true).writeAndRefresh();
                    this.writeAndRefresh();
                    if (this.getListener(SendMessageAfterUpdateHandler.class) != null) // Just in case write and refresh didn't need to write this (also frees listener)
                        this.getListener(SendMessageAfterUpdateHandler.class).doRecordChange(null, DBConstants.AFTER_UPDATE_TYPE, DBConstants.DISPLAY);
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    if (this.getListener(SendMessageAfterUpdateHandler.class) != null) // Just in case
                        this.removeListener(this.getListener(SendMessageAfterUpdateHandler.class), true);
                }
            }
        }
    }
    /**
     * Pre-check to see if the minimal required params are set.
     * @return If okay, return 0, otherwise return the field that is required.
     */
    public int checkRequiredParams(int iStatusType)
    {
        if (this.getField(BookingDetail.kProductID).isNull())
            return BookingDetail.kProductID;    // Product must be non-null
        if (this.getField(BookingDetail.kDetailDate).isNull())
            return BookingDetail.kDetailDate; // Date must be non-null
        return 0; // Looks good so far.
    }
    /**
     * Lookup the message for this status update.
     * If no update is required, return a null message.
     * @return The message to process (or null if no message).
     */
    public BaseProductMessageDesc checkMessageRequired(int iStatusType)
    {
        if (this.getEditMode() == DBConstants.EDIT_NONE)
            return null;
        Product recProduct = this.getProduct();
        if (recProduct == null)
            return null;   // Never
        String strMessageInfoType = MessageInfoType.REQUEST;
        String strRequestType = this.getRequestType(iStatusType);
        String strMessageProcessType = MessageType.MESSAGE_OUT;
        String strProcessType = this.getProcessType(iStatusType);
        String strContactType = recProduct.getTableNames(false);
        if (RequestType.BOOKING_CANCEL.equalsIgnoreCase(strRequestType))
            strContactType = Product.kProductFile;      // Cancellation message is generic
        String strMessageTransport = this.getMessageTransport(iStatusType);
        if (MessageTransport.MANUAL.equals(strMessageTransport))
            return null;    // No need to send a message if manual
        TrxMessageHeader trxMessageHeader = recProduct.createProcessMessage(strMessageInfoType, strContactType, strRequestType, strMessageProcessType, strProcessType, strMessageTransport);
        if (trxMessageHeader == null)
            return null;
        String strPrefix = this.getFieldParam(this.getField(iStatusType)) + '.' + BookingDetail.MESSAGE_PARAM + '.' + BaseMessage.HEADER_TAG + '.';
        this.addMessageProperties(strPrefix, true, trxMessageHeader, null, null);
        BaseMessage message = BaseMessage.createMessage(trxMessageHeader);
        BaseProductMessageDesc messageData = (BaseProductMessageDesc)message.getMessageDataDesc(null);
        if ((message == null) || (messageData == null))
            return null;
        trxMessageHeader.addTaskProperties(this.getTask());
        if (this.getEditMode() == DBConstants.EDIT_ADD)
        {
            try {
                this.writeAndRefresh();
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        trxMessageHeader.put(TrxMessageHeader.REFERENCE_ID, this.getCounterField().toString());
        trxMessageHeader.put(TrxMessageHeader.REFERENCE_TYPE, this.getTableNames(false));
        trxMessageHeader.put(TrxMessageHeader.REFERENCE_CLASS, this.getClass().getName());
        strPrefix = this.getFieldParam(this.getField(iStatusType)) + '.' + BookingDetail.MESSAGE_PARAM + '.';
        this.addMessageProperties(strPrefix, false, null, message, strPrefix);        // Add any land varies params
        String strMessageTransportID = (String)message.getMessageHeader().get(MessageTransport.TRANSPORT_ID_PARAM);
        if ((strMessageTransportID != null) && (strMessageTransportID.length() > 0))
        {
            boolean[] brgEnabled = this.getField(iStatusType + MESSAGE_TRANSPORT_OFFSET).setEnableListeners(false);    // No echo
            this.getField(iStatusType + MESSAGE_TRANSPORT_OFFSET).setString(strMessageTransportID);   // Set the message transport
            this.getField(iStatusType + MESSAGE_TRANSPORT_OFFSET).setEnableListeners(brgEnabled);
            if (!strMessageTransportID.equals(this.getField(iStatusType + MESSAGE_TRANSPORT_OFFSET).toString()))
            { // Transport was changed, I must get the NEW message (for the new transport)!
                return this.checkMessageRequired(iStatusType);
            }
        }
        this.setErrorMessage(messageData, null);
        int iStatus = messageData.checkRequestParams(this);
        if (iStatus == BaseDataStatus.DATA_VALID)
        {
            messageData.handlePutRawRecordData(this);    // Set the properties for a price lookup
            if (!((ProductMessageData)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).updateMessageKeys(this, iStatusType))
                return null; // If the data hasn't changed, don't change the status.
            return messageData;
        }
        ((ProductMessageData)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).setRecordDataStatus(this, iStatusType, iStatus);
        return null;    // Status was returned and set, don't process this message.
    }
    /**
     * GetErrorMessage Method.
     */
    public String getErrorMessage(int iStatusType)
    {
        return ((PropertiesField)this.getField(BookingDetail.kErrorProperties)).getProperty(this.getFieldParam(this.getField(iStatusType)) + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM);
    }
    /**
     * Set the error message in this record for this message type.
     */
    public void setErrorMessage(BaseProductMessageDesc messageData, String strError)
    {
        ((PropertiesField)this.getField(BookingDetail.kErrorProperties)).setProperty(messageData.getMessageTypeParam() + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM, strError, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * Set the error message in this record for this status type.
     */
    public void setErrorMessage(int iStatusType, String strError)
    {
        ((PropertiesField)this.getField(BookingDetail.kErrorProperties)).setProperty(this.getFieldParam(this.getField(iStatusType)) + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM, strError, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * For this kind of message, get the process type.
     * For now, all are UPDATE, which means update the information in the booking
     * when the response comes back (vs. just return the information to the caller).
     */
    public String getProcessType(int iStatusType)
    {
        return ProcessType.UPDATE;
    }
    /**
     * Add any message properties that are set in this record.
     */
    public void addMessageProperties(String strPrefix, boolean bDeleteProperties, TrxMessageHeader messageHeader, BaseMessage message, String strNewPrefix)
    {
        Map<String,Object> properties = ((PropertiesField)this.getField(BookingDetail.kProperties)).getProperties();
        if (properties != null)
        {
            Iterator<String> iterator = properties.keySet().iterator();
            while (iterator.hasNext())
            {
                String strKey = iterator.next();
                if (strKey.startsWith(strPrefix))
                {
                    String value = (String)properties.get(strKey);
                    if (bDeleteProperties)
                        ((PropertiesField)this.getField(BookingDetail.kProperties)).setProperty(strKey, null);  // Remove from record
                    strKey = strKey.substring(strPrefix.length());
                    if (strNewPrefix != null)
                        strKey = strNewPrefix + strKey;
                    if (messageHeader != null)
                        messageHeader.put(strKey, value);
                    if (message != null)
                        if (message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE) != null)
                            ((MessageRecordDesc)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).put(strKey, value);
                }
            }
        }
    }
    /**
     * For this messagestatusID field, return the message type param
     * @param fldStatusID The status id field
     * @return The param for this status type.
     */
    public String getFieldParam(BaseField fldStatusID)
    {
        String strParam = DBConstants.BLANK;
        if (fldStatusID == this.getField(BookingDetail.kInfoStatusID))
            strParam = BookingDetail.INFO_PARAM;
        else if (fldStatusID == this.getField(BookingDetail.kCostStatusID))
            strParam = BookingDetail.COST_PARAM;
        else if (fldStatusID == this.getField(BookingDetail.kInventoryStatusID))
            strParam = BookingDetail.INVENTORY_PARAM;
        else if (fldStatusID == this.getField(BookingDetail.kProductStatusID))
            strParam = BookingDetail.PRODUCT_PARAM;
        return strParam;
    }
    /**
     * Get the request type code for this status field sequence.
     */
    public String getRequestType(int iStatusType)
    {
        String strRequestType = this.getField(iStatusType).getFieldName();
        if (RequestType.BOOKING.equalsIgnoreCase(strRequestType))
        {
            if (!this.productOrdered())
                strRequestType = RequestType.BOOKING_ADD;
            else
            {
                if (this.getField(BookingDetail.kDeleted).getState() == true)
                    strRequestType = RequestType.BOOKING_CANCEL;
                else
                {
                    Record recTour = ((ReferenceField)this.getField(BookingDetail.kTourID)).getReference();
                    if ((recTour != null) &&
                        ((recTour.getEditMode() == DBConstants.EDIT_CURRENT) || (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                            && (recTour.getField(Tour.kCancelled).getState() == true))
                        strRequestType = RequestType.BOOKING_CANCEL;
                    else
                        strRequestType = RequestType.BOOKING_CHANGE;
                }
            }
        }
        return strRequestType;
    }
    /**
     * ProductOrdered Method.
     */
    public boolean productOrdered()
    {
        if (this.getField(BookingDetail.kProductRequestKey).isNull())
            return false;
        else
            return true;
    }
    /**
     * Get the message transport code for this message type.
     */
    public String getMessageTransport(int iStatusType)
    {
        Record recMessageTransport = ((ReferenceField)this.getField(iStatusType + MESSAGE_TRANSPORT_OFFSET)).getReference();
        if ((recMessageTransport != null) &&
            ((recMessageTransport.getEditMode() == DBConstants.EDIT_CURRENT) || (recMessageTransport.getEditMode() == DBConstants.EDIT_IN_PROGRESS)))
                return recMessageTransport.getField(MessageTransport.kCode).toString();
        return null;
    }
    /**
     * HandlePriceChange Method.
     */
    public int handlePriceChange(int iChangeType)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        Booking recBooking = this.getBooking(true);
        
        int iNonTourPricingType = (int)((ReferenceField)recBooking.getField(Booking.kNonTourPricingTypeID)).getReference().getField(PricingType.kPricingCodes).getValue();
        if (iNonTourPricingType == PricingType.OPTION_PRICING)  // Can't have option pricing on non-tours!
            iNonTourPricingType = PricingType.COMPONENT_PRICING | PricingType.COMPONENT_COST_PRICING;
        
        if ((!this.getField(BookingDetail.kTourHeaderOptionID).isNull()) || (this.getField(BookingDetail.kProductTypeID).getValue() == ProductType.TOUR_ID))
        {   // Entered using a module or is a module
            BaseField fldModuleID = this.getField(BookingDetail.kModuleID);
            Date dateStart = ((DateTimeField)this.getField(BookingDetail.kModuleStartDate)).getDateTime();
            if (this.getField(BookingDetail.kProductTypeID).getValue() == ProductType.TOUR_ID)
            {
                fldModuleID = this.getField(BookingDetail.kProductID);
                dateStart = ((DateTimeField)this.getField(BookingDetail.kDetailDate)).getDateTime();
            }
            int iTourPricingType = recBooking.getTourPricingType(null, fldModuleID, dateStart);
            if ((iTourPricingType & PricingType.OPTION_PRICING) != 0)
            {
                if ((this.getField(BookingDetail.kTotalCostLocal).isModified())
                        || (iChangeType == DBConstants.DELETE_TYPE))   // This means the record has been deleted
                {
                        if ((recBooking.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class) == null)
                            || (recBooking.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class).isEnabledListener() == true))  // If this is DISABLED, don't change the status (only on initial setup)
                    {
                        PricingType recPricingType2 = new PricingType(Utility.getRecordOwner(this));
                        PricingType recPricingType3 = recPricingType2.getPricingType(PricingType.COMPONENT_COST_PRICING);
                        if (recPricingType3 != null)
                            recBooking.getField(Booking.kTourPricingTypeID).moveFieldToThis(recPricingType3.getField(PricingType.kID));
                        recPricingType2.free();
                    }
                }
                return iErrorCode;  // Pricing already entered from the tour header option
            }
            else
                iNonTourPricingType = iTourPricingType;
        }
        if ((iNonTourPricingType & PricingType.COMPONENT_PRICING) != 0)
        {
            String strProductKey = this.getPricingDetailKey();
            if (!strProductKey.equals(this.getField(BookingDetail.kPricingDetailKey).toString()))
            {
                Product recProduct = this.getProduct();
        
                iErrorCode = this.updateBookingComponentPricing(recProduct, iChangeType);
        
                if (iErrorCode == DBConstants.NORMAL_RETURN)
                    return iErrorCode;  // Otherwise, continue and price using the cost.
                iErrorCode = DBConstants.NORMAL_RETURN;
                strProductKey = this.getField(BookingDetail.kPricingDetailKey).toString();
            }
        }
        if ((iNonTourPricingType & PricingType.COMPONENT_COST_PRICING) != 0)
        {
            if ((this.getField(BookingDetail.kTotalCostLocal).isModified())
                || (iChangeType == DBConstants.DELETE_TYPE))   // This means the record has been deleted
            {
                return this.updateBookingComponentCostPricing(recBooking, iChangeType);
            }
        }
        if (iErrorCode != DBConstants.NORMAL_RETURN)
        {
            BookingLine recBookingLine = this.getBookingLine();
            int iPricingType = PricingType.COMPONENT_PRICING;
            int iPaxCategory = PaxCategory.ALL_ID;
            int iQuantity = this.getNoPax();
            double dAmount = 0;
            double dCommissionRate = this.getBooking(true).getField(Booking.kStdCommission).getValue();
            boolean bCommissionable = dCommissionRate != 0;
            String strPayAt = PayAtField.FINAL_PAY_DATE;
            int iPricingStatus = PricingStatus.NOT_VALID;
            iErrorCode = this.updateBookingLine(recBookingLine, iPricingType, iPaxCategory, iQuantity, dAmount, bCommissionable, dCommissionRate, strPayAt, iPricingStatus, iChangeType);
        }
        return iErrorCode;
    }
    /**
     * Update the booking pricing (given this detail change)
     * @param recProduct The product
     * @param iChangeType The change type.
     */
    public int updateBookingComponentPricing(Product recProduct, int iChangeType)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
            
        if (iChangeType == DBConstants.ADD_TYPE)
            if (this.getEditMode() == DBConstants.EDIT_ADD)    // Always
        {
            try {
                this.writeAndRefresh();
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        
        iErrorCode = recProduct.updateBookingPricing(this.getBookingLine(), this, iChangeType);
        
        return iErrorCode;
    }
    /**
     * UpdateBookingComponentCostPricing Method.
     */
    public int updateBookingComponentCostPricing(Booking recBooking, int iChangeType)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        double dPPCost = this.getField(BookingDetail.kTotalCostLocal).getValue();
        int iQuantity = this.getPaxInRoom(PaxCategory.ALL_ID);
        if (iQuantity != 0)
            dPPCost = dPPCost / iQuantity;
        iErrorCode = this.updateBookingLine(this.getBookingLine(), PricingType.COMPONENT_COST_PRICING, PaxCategory.ALL_ID, iQuantity, dPPCost, true, recBooking.getField(Booking.kCommission).getValue(), null, PricingStatus.OKAY, iChangeType);
        
        return iErrorCode;
    }
    /**
     * GetPricingDetailKey Method.
     */
    public String getPricingDetailKey()
    {
        String strReturn = DBConstants.BLANK;
        
        strReturn = this.addKeyField(strReturn, BookingDetail.kProductID);
        strReturn = this.addKeyField(strReturn, BookingDetail.kDetailDate);
        strReturn = this.addKeyField(strReturn, BookingDetail.kClassID);
        strReturn = this.addKeyField(strReturn, BookingDetail.kRateID);
        
        return strReturn;
    }
    /**
     * AddKeyField Method.
     */
    public String addKeyField(String strReturn, int iFieldSeq)
    {
        char chSeparator = '/'; // Character.forDigit(iCount, Character.MAX_RADIX);
        
        if (!this.getField(iFieldSeq).isNull())
        {
            if (strReturn.length() > 0)
                strReturn += chSeparator;
            strReturn += this.getField(iFieldSeq).toString();
        }
        return strReturn;
    }
    /**
     * Get/create and setup the BookingLine record.
     */
    public BookingLine getBookingLine()
    {
        if (m_recBookingLine == null)
        {
            Booking recBooking = this.getBooking(true);
            Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
            m_recBookingLine = new BookingLine(Utility.getRecordOwner(this));
            m_recBookingLine.addDetailBehaviors(recBooking, recTour);
            if (m_recBookingLine.getRecordOwner() != null)
                m_recBookingLine.getRecordOwner().removeRecord(m_recBookingLine);    // Set it is not on the recordowner's list
            this.addListener(new FreeOnFreeHandler(m_recBookingLine));
        }
        return m_recBookingLine;
    }
    /**
     * Add the booking line item for this booking detail.
     * @param recBookingLine The line file
     * @param iPricingType Cost or Pricing.
     * @param iPaxCategory The passenger room type (category)
     * @param iQuantity The number to add
     * @param dAmount The unit amount
     * @param bCommissionable Is this line item fully commissionable?
     * @param dCommission The commission rate if not fully commissionable.
     * @param iChangeType The detail change type
     * @return NORMAL_RETURN if a pricing item was added
     * @return ERROR_RETURN If no pricing was added, or a item was deleted making this line item total zero.
     */
    public int updateBookingLine(BookingLine recBookingLine, int iPricingType, int iPaxCategory,  int iQuantity, double dAmount, boolean bCommissionable, double dCommissionRate, String
     strPayAt, int iPricingStatusID, int iChangeType)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if ((iPaxCategory == PaxCategory.SINGLE_ID) || (iPaxCategory == 0))
        {   // First time through
            this.getField(BookingDetail.kTotalPriceLocal).setValue(0.00);
            this.getField(BookingDetail.kPPPriceLocal).setValue(0.00);
        }
        
        Booking recBooking = this.getBooking(true);        
        try {
            recBookingLine.addNew();
            if (iChangeType != DBConstants.ADD_TYPE)
            {
                recBookingLine.getField(BookingLine.kBookingID).moveFieldToThis(recBooking.getField(Booking.kID));
                recBookingLine.getField(BookingLine.kBookingPaxID).moveFieldToThis(this.getField(BookingDetail.kBookingPaxID));   // For now
                recBookingLine.getField(BookingLine.kBookingDetailID).moveFieldToThis(this.getField(BookingDetail.kID));
                recBookingLine.getField(BookingLine.kPaxCategoryID).setValue(iPaxCategory);
                recBookingLine.setKeyArea(BookingLine.kBookingDetailIDKey);
                if ((recBookingLine.seek(">="))
                    && (recBookingLine.getField(BookingLine.kBookingID).equals(recBooking.getField(Booking.kID)))
                        && (recBookingLine.getField(BookingLine.kBookingPaxID).equals(this.getField(BookingDetail.kBookingPaxID)))
                        && (recBookingLine.getField(BookingLine.kBookingDetailID).equals(this.getField(BookingDetail.kID)))
                        && (recBookingLine.getField(BookingLine.kPaxCategoryID).getValue() == iPaxCategory))
                    recBookingLine.edit();
                else
                    recBookingLine.addNew();
            }
        
            double dPPCost = 0;
            double dPPPrice = 0;
            double dMarkup = recBooking.getField(Booking.kMarkup).getValue();
            if (bCommissionable)
                dCommissionRate = recBooking.getField(Booking.kStdCommission).getValue();
            if ((iPricingType & PricingType.COMPONENT_PRICING) != 0)
            {
                dPPPrice = dAmount; // Price is passed in
            }
            else if ((iPricingType & PricingType.COMPONENT_COST_PRICING) != 0)
            {
                dPPCost = dAmount;
                dPPPrice = (Math.floor(dPPCost * (1.0 + dMarkup) * 100 + 0.5)) / 100;     // Net to the agent.
                dPPPrice = (Math.floor(dPPPrice / (1 - dCommissionRate) * 100)) / 100;    // This amount minus the commission will give the price.
            }
        
            recBookingLine.getField(BookingLine.kBookingID).moveFieldToThis(recBooking.getField(Booking.kID));
        
            recBookingLine.getField(BookingLine.kModuleID).moveFieldToThis(this.getField(BookingDetail.kModuleID));
            recBookingLine.getField(BookingLine.kModuleStartDate).moveFieldToThis(this.getField(BookingDetail.kModuleStartDate));
            if (this.getField(BookingDetail.kProductTypeID).getValue() == ProductType.TOUR_ID)
            {
                recBookingLine.getField(BookingLine.kModuleID).moveFieldToThis(this.getField(BookingDetail.kProductID));
                recBookingLine.getField(BookingLine.kModuleStartDate).moveFieldToThis(this.getField(BookingDetail.kDetailDate));
            }
        
            // Note: BookingLine.kSequence is assigned automatically.
            String strDescription = this.getField(BookingDetail.kDescription).toString();
            if (iPaxCategory != PaxCategory.ALL_ID)
            {
                RoomTypeField fldTemp = new RoomTypeField(null, DBConstants.BLANK, DBConstants.DEFAULT_FIELD_LENGTH, null, null);
                String strRoomType = fldTemp.convertIndexToString(iPaxCategory);
                fldTemp.free();
                strDescription = strDescription + " (" + strRoomType + ')';
            }
            recBookingLine.getField(BookingLine.kDescription).setString(strDescription);
            recBookingLine.getField(BookingLine.kPrice).setValue(dPPPrice);
        
            recBookingLine.getField(BookingLine.kQuantity).setValue(iQuantity);
        
            recBookingLine.getField(BookingLine.kCommissionable).setState(bCommissionable);
            recBookingLine.getField(BookingLine.kCommissionRate).setValue(dCommissionRate);
            recBookingLine.getField(BookingLine.kPricingStatusID).setValue(iPricingStatusID);
            if ((strPayAt == null) || (strPayAt.length() == 0))
                strPayAt = PayAtField.FINAL_PAY_DATE;
            recBookingLine.getField(BookingLine.kPayAt).setString(strPayAt);
            recBookingLine.getField(BookingLine.kBookingPaxID).moveFieldToThis(this.getField(BookingDetail.kBookingPaxID));   // For now
            
            Object bookingDetailID = this.getField(BookingDetail.kID).getData();
            if (iChangeType == DBConstants.ADD_TYPE)
                if (bookingDetailID == null)    // Always for add
                {
                    if (this.getEditMode() == DBConstants.EDIT_ADD)
                        this.writeAndRefresh();     // Need record ID
                    bookingDetailID = this.getField(BookingDetail.kID).getData();
                }
            recBookingLine.getField(BookingLine.kBookingDetailID).setData(bookingDetailID);
            recBookingLine.getField(BookingLine.kPaxCategoryID).setValue(iPaxCategory);
            if ((iChangeType == DBConstants.DELETE_TYPE)
                || ((iPricingStatusID != PricingStatus.NOT_VALID) && ((recBookingLine.getField(BookingLine.kPrice).getValue() == 0)
                        || (recBookingLine.getField(BookingLine.kQuantity).getValue() == 0))))
            {
                if (recBookingLine.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBookingLine.remove();
                iErrorCode = DBConstants.ERROR_RETURN;  // This just means that there are no line items.
            }
            else
            {
                if (recBookingLine.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBookingLine.set();
                else
                    recBookingLine.add();
            }
            if (iErrorCode == DBConstants.NORMAL_RETURN)
            {
                this.getField(BookingDetail.kTotalPriceLocal).setValue(this.getField(BookingDetail.kTotalPriceLocal).getValue() + recBookingLine.getField(BookingLine.kGross).getValue());
                this.getField(BookingDetail.kPPPriceLocal).setValue(this.getField(BookingDetail.kPPPriceLocal).getValue() + recBookingLine.getField(BookingLine.kPrice).getValue());
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        }
        return iErrorCode;
    }
    /**
     * Here is a single tour detail item.
     * Add it to the current booking line item detail.
     */
    public int setupDetail(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        int iErrorCode = super.setupDetail(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
        {
            BookingDetail recBookingDetail = (BookingDetail)this.getTable().getCurrentTable().getRecord();
            if (recBookingDetail.getEditMode() != DBConstants.EDIT_NONE)    // May have been deleted
                recBookingDetail.checkRequestRequired(BookingDetail.kInfoStatusID);
        }
        return iErrorCode;
    }
    /**
     * If there are any detail items to add/change/delete when this detail is added, do it.
     * Override this to set up any detail info for this detail (ie., a direct TourHeader).
     */
    public int setupDirectDetail(int iChangeType)
    {
        BookingAnswer recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
        BookingPax recBookingPax = null;
        Booking recBooking = this.getBooking(true);
        if ((recBooking == null)
                || (recBooking.getEditMode() == DBConstants.EDIT_NONE))
            return DBConstants.ERROR_RETURN;
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
        if ((recTour == null)
                || (recTour.getEditMode() == DBConstants.EDIT_NONE))
            return DBConstants.ERROR_RETURN;
        TourHeader recTourHeader = (TourHeader)this.getProduct();
        if ((recTourHeader == null)
                || (recTourHeader.getEditMode() == DBConstants.EDIT_NONE))
            return DBConstants.ERROR_RETURN;
        Date dateStart = ((DateTimeField)this.getField(BookingDetail.kDetailDate)).getDateTime();
        if (dateStart == null)
            return DBConstants.ERROR_RETURN;
        try {
            recTour.writeAndRefresh();
            recBooking.writeAndRefresh();
        } catch (DBException ex) {
            ex.printStackTrace();
        }
        
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        FieldDataScratchHandler fieldDataScratchHandler = (FieldDataScratchHandler)this.getField(BookingDetail.kDetailDate).getListener(FieldDataScratchHandler.class);
        Date dateOriginal = (Date)fieldDataScratchHandler.getOriginalData();
        
        if (iChangeType == DBConstants.AFTER_DELETE_TYPE)
        {   // Deleted/canceled
            iErrorCode = recBooking.deleteTourDetail(recTour, recBookingPax, this.getField(BookingTour.kProductID), dateStart);
        }
        else if ((dateOriginal == null) || (dateOriginal.equals(dateStart)))
        {   // New
            iErrorCode = recBooking.addTourDetail(recTour, recTourHeader, recBookingPax, recBookingAnswer, dateStart, this.getField(BookingTour.kAskForAnswer));
        }
        else
        {   // Changed
            iErrorCode = recBooking.changeTourDetail(recTour, recBookingPax, recTourHeader, dateOriginal, dateStart);
        }
        return iErrorCode;
    }

}
