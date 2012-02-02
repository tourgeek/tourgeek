/**
 * @(#)BookingDetail.
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
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingDetail - Customer Sale Detail.
 */
public class BookingDetail extends BookingSub
     implements BookingDetailModel
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
        {
            Object objObjectID = null;
            try {
                objObjectID = this.getHandle(DBConstants.OBJECT_ID_HANDLE);
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
            if ((this.getEditMode() == DBConstants.EDIT_ADD) || (this.getEditMode() == DBConstants.EDIT_NONE))
                if (this.getField(BookingDetail.PRODUCT_TYPE_ID).isNull())
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
                    return record.makeScreen((ScreenLocation)itsLocation, (BasePanel)parentScreen, iDocMode, properties);
            if (objObjectID != null)
                parentScreen.setProperty(DBConstants.STRING_OBJECT_ID_HANDLE, objObjectID.toString());
            if (this.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.HOTEL_ID)
                screen = new BookingHotelScreen(null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
            else //if (this.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.LAND_ID)
                screen = new BookingLandScreen(null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        }
        else //if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new BookingDetailGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        
        BookingDetailSoftDeleteHandler listener = new BookingDetailSoftDeleteHandler(this.getField(BookingDetail.DELETED));
        this.addListener(listener);
        listener.filterThisRecord(false);   // Display deleted record (usually switchable in screens)
        
        this.getField(BookingDetail.PRODUCT_TYPE).addListener(new ProductTypeHandler(null));
        // Since Meals handler is such a resource hog, you should only add it manually todo(don)
        this.getField(BookingDetail.MEAL_SUMMARY).addListener(new MealsHandler(null));
        this.getField(BookingDetail.STATUS_SUMMARY).addListener(new StatusHandler(null));
        
        this.getField(BookingDetail.AP_TRX_ID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.PRODUCT_ID), null, false));
        
        this.addLookupListeners();
    }
    /**
     * Add the listeners to do the price and inventory lookups.
     * Typically these are only done in the concrete classes.
     */
    public void addLookupListeners()
    {
        BaseField fldExchange = this.getField(BookingDetail.EXCHANGE);
        this.getField(BookingDetail.TOTAL_COST).addListener(new CalcBalanceHandler(this.getField(BookingDetail.TOTAL_COST_LOCAL), this.getField(BookingDetail.TOTAL_COST), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        
        String strManualTransportID = Integer.toString(((ReferenceField)this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID)).getIDFromCode(MessageTransport.MANUAL));
        
        Converter converterNotInfoManualTransport = new CheckConverter(this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotInfoManualTransport));
        Converter converterNotCostManualTransport = new CheckConverter(this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotCostManualTransport));
        Converter converterNotInventoryManualTransport = new CheckConverter(this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotInventoryManualTransport));
        Converter converterNotProductManualTransport = new CheckConverter(this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID), strManualTransportID, null, false);
        this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(converterNotProductManualTransport));
        
        this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.INFO_STATUS_ID), strManualTransportID, false));
        this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.INVENTORY_STATUS_ID), strManualTransportID, false));
        this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.COST_STATUS_ID), strManualTransportID, false));
        this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.TOTAL_COST), strManualTransportID, false));
        this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).addListener(new DisableOnFieldHandler(this.getField(BookingDetail.PRODUCT_STATUS_ID), strManualTransportID, false));
        
        Boolean boolRequestRequiredFlag = Boolean.TRUE;
        InitOnChangeHandler listener = null;
        this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.INFO_REQUEST_KEY)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.INFO_STATUS_REQUEST), boolRequestRequiredFlag, null));
        this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.INVENTORY_REQUEST_KEY)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.INVENTORY_STATUS_REQUEST), boolRequestRequiredFlag, null));
        this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.COST_REQUEST_KEY)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, null));
        this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).addListener(listener = new InitOnChangeHandler(this.getField(BookingDetail.PRODUCT_REQUEST_KEY)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, null));
        
        // First, handle new information lookups
        this.getField(BookingDetail.PRODUCT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.INFO_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInfoManualTransport));
        this.getField(BookingDetail.DETAIL_DATE).addListener(new FieldDataScratchHandler(null, false));   // Don't change on refresh
        this.getField(BookingDetail.DETAIL_DATE).addListener(new CopyDataHandler(this.getField(BookingDetail.INFO_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInfoManualTransport));
        this.getField(BookingDetail.INFO_STATUS_REQUEST).addListener(new CheckRequestRequiredHandler(BookingDetail.INFO_STATUS_ID));
        
        // If any of these values change, you will have to re-lookup the price.
        this.getField(BookingDetail.PRODUCT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, converterNotCostManualTransport));
        this.getField(BookingDetail.DETAIL_DATE).addListener(new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, converterNotCostManualTransport));
        this.getField(BookingDetail.DETAIL_END_DATE).addListener(new FieldListener(null)
        { // If the end data changes call this setEndDate method
            public int fieldChanged(boolean bDisplayOption, int iMoveMode)
            { // Override to do something!
                int iReturnCode = super.fieldChanged(bDisplayOption, iMoveMode);
                
                if (iMoveMode == DBConstants.SCREEN_MOVE)
                setEndDate(((DateTimeField)getOwner()).getDateTime());
                
                return iReturnCode;
            }
        });
        this.getField(BookingDetail.RATE_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, converterNotCostManualTransport));
        this.getField(BookingDetail.CLASS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, converterNotCostManualTransport));
        // If the info changes from valid AND the transport is not manual, change the info status to request required.
        FieldConverter convIfCostStatusTrueAndNotManual = new AltFieldConverter(new RadioConverter(this.getField(BookingDetail.INFO_STATUS_ID), new Integer(CostStatus.VALID), true), converterNotCostManualTransport, Integer.toString(CostStatus.VALID));
        this.getField(BookingDetail.INFO_STATUS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, convIfCostStatusTrueAndNotManual));
        // If when you update this record a Lookup is requested, set to schedule lookup and schedule the lookup.
        this.getField(BookingDetail.COST_STATUS_REQUEST).addListener(new CheckRequestRequiredHandler(BookingDetail.COST_STATUS_ID));
        
        // If any of these values change, you will have to re-lookup the inventory.
        this.getField(BookingDetail.PRODUCT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.INVENTORY_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        this.getField(BookingDetail.DETAIL_DATE).addListener(new CopyDataHandler(this.getField(BookingDetail.INVENTORY_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        this.getField(BookingDetail.RATE_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.INVENTORY_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        this.getField(BookingDetail.CLASS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.INVENTORY_STATUS_REQUEST), boolRequestRequiredFlag, converterNotInventoryManualTransport));
        // If the info changes from valid AND the transport is not manual, change the info status to request required.
        FieldConverter convIfInventoryStatusTrueAndNotManual = new AltFieldConverter(new RadioConverter(this.getField(BookingDetail.INFO_STATUS_ID), new Integer(CostStatus.VALID), true), converterNotInventoryManualTransport, Integer.toString(CostStatus.VALID));
        this.getField(BookingDetail.INFO_STATUS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.INVENTORY_STATUS_REQUEST), boolRequestRequiredFlag, convIfInventoryStatusTrueAndNotManual));
        // If when you update this record a Lookup is requested, set to schedule lookup and schedule the lookup.
        this.getField(BookingDetail.INVENTORY_STATUS_REQUEST).addListener(new CheckRequestRequiredHandler(BookingDetail.INVENTORY_STATUS_ID));
        // If these change, re-request the booking
        this.getField(BookingDetail.PRODUCT_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.DETAIL_DATE).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.RATE_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.CLASS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, converterNotProductManualTransport));
        this.getField(BookingDetail.DELETED).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, converterNotProductManualTransport));
        // If the info changes from valid AND the transport is not manual, change the info status to request required.
        FieldConverter convIfProductStatusTrueAndNotManual = new AltFieldConverter(new RadioConverter(this.getField(BookingDetail.INFO_STATUS_ID), new Integer(CostStatus.VALID), true), converterNotProductManualTransport, Integer.toString(CostStatus.VALID));
        this.getField(BookingDetail.INFO_STATUS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, convIfProductStatusTrueAndNotManual));
        this.getField(BookingDetail.INVENTORY_STATUS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, convIfProductStatusTrueAndNotManual));
        this.getField(BookingDetail.COST_STATUS_ID).addListener(new CopyDataHandler(this.getField(BookingDetail.PRODUCT_STATUS_REQUEST), boolRequestRequiredFlag, convIfProductStatusTrueAndNotManual));
        // When a product booking is requested, this listener will set up the message
        this.getField(BookingDetail.PRODUCT_STATUS_REQUEST).addListener(new CheckRequestRequiredHandler(BookingDetail.PRODUCT_STATUS_ID));
        
        this.getField(BookingDetail.MARKUP_FROM_LAST).addListener(new CopyDataHandler(this.getField(BookingDetail.COST_STATUS_REQUEST), boolRequestRequiredFlag, converterNotCostManualTransport));
        FieldDataScratchHandler fieldListener = null;
        this.getField(BookingDetail.MARKUP_FROM_LAST).addListener(fieldListener = new FieldDataScratchHandler(null)
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
                        double dCost = this.getOwner().getRecord().getField(BookingDetail.TOTAL_COST).getValue();
                        dCost = Math.floor((dCost / (1 + fOrig)) * (1 + fCurrent) * 100 + 0.5) / 100;
                        this.getOwner().getRecord().getField(BookingDetail.TOTAL_COST).setValue(dCost);
                    }
                    this.setOriginalData(this.getOwner().getData());
                }
                return super.fieldChanged(bDisplayOption, iMoveMode);
            }
        });
        fieldListener.setRespondsToMode(DBConstants.SCREEN_MOVE, true);
        
        this.addListener(new UpdateTourStatusHandler(BookingDetail.INFO_STATUS_ID));
        this.addListener(new UpdateTourStatusHandler(BookingDetail.INVENTORY_STATUS_ID));
        this.addListener(new UpdateTourStatusHandler(BookingDetail.COST_STATUS_ID));
        this.addListener(new UpdateTourStatusHandler(BookingDetail.PRODUCT_STATUS_ID));
        
        this.addListener(new BookingDetailPriceChangeHandler(null));
    }
    /**
     * Override this to add record listeners and filters to every screen where this is the main record.
     * @param screen The screen these listeners will be in.
     */
    public void addScreenListeners(RecordOwner screen)
    {
        super.addScreenListeners(screen);
        String strManualTransportID = Integer.toString(((ReferenceField)this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID)).getIDFromCode(MessageTransport.MANUAL));
        if (((ReferenceField)this.getField(BookingDetail.PRODUCT_ID)).getReferenceRecord() != null)
            this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new ManualProductInfoHandler(((ReferenceField)this.getField(BookingDetail.PRODUCT_ID)).getReferenceRecord().getField(Product.DESCRIPTION), strManualTransportID, false));
    }
    /**
     * AddSlaveListeners Method.
     */
    public void addSlaveListeners()
    {
        super.addSlaveListeners();
        
        this.addListener(new HistoryHandler(BookingDetailHistory.class.getName(), BookingDetailHistory.HISTORY_DATE, null));
    }
    /**
     * Get the record type from the field that specifies the record type.
     * (Override this).
     * @return The record type (as an object).
     */
    public BaseField getSharedRecordTypeKey()
    {
        return this.getField(BookingDetail.PRODUCT_TYPE_ID);
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
            screenRecord = (Record)recBooking.getRecordOwner().getScreenRecord();
            Record recProduct = ((ReferenceField)this.getField(BookingDetail.PRODUCT_ID)).getReferenceRecord(this.getRecordOwner());
            // Note that I add these listeners in reverse since they always do the other listeners before they do themselves.
            if (recProduct != null)
            {
                if (recProduct instanceof TransportProduct)
                    this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.LAST_CITY_ID), ((TransportProduct)recProduct).getField(TransportProduct.TO_CITY_ID), null, false, false, false, true, true, null, true));
                this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.LAST_CITY_ID), recProduct.getField(Product.CITY_ID), null, false, false, false, true, true, null, false));
            }
            // If the end date is non-null, use it!
            this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.LAST_DATE), this.getField(BookingDetail.DETAIL_DATE), null, false, false, false, true, true, null, false));
            this.addListener(new MoveOnEventHandler(screenRecord.getField(BookingScreenRecord.LAST_DATE), this.getField(BookingDetail.DETAIL_END_DATE), null, false, false, false, true, true, null, true));
            
            FieldListener listener = this.getField(BookingDetail.DETAIL_DATE).getListener(InitOnceFieldHandler.class.getName());
            if (listener != null)
                this.getField(BookingDetail.DETAIL_DATE).removeListener(listener, true);
            this.getField(BookingDetail.DETAIL_DATE).addListener(new InitFieldHandler(screenRecord.getField(BookingScreenRecord.LAST_DATE), false));
        }
        // Note these next listener are just a backup if the screenRecord date is null or not available. (First departure, then today)
        this.getField(BookingDetail.DETAIL_DATE).addListener(new InitFieldHandler(new Date()));
        this.getField(BookingDetail.DETAIL_DATE).addListener(new InitFieldHandler(recTour.getField(Tour.DEPARTURE_DATE), false));
        
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
        ((ReferenceField)this.getField(BookingDetail.PRODUCT_ID)).getReferenceRecord(this.getRecordOwner());    // Reference same recordowner
        return (Product)((ReferenceField)this.getField(BookingDetail.PRODUCT_ID)).getReference();
    }
    /**
     * Set the start date for this item.
     * This is here so you can do an action when the user changes the start date.
     * The StartDateHandler is added automatically to this class.
     */
    public Date setStartDate(Date time)
    {
        ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).setDateTime(time, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        return ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();
    }
    /**
     * Get the start date and time for this product.
     * Return null if there is no date and time.
     */
    public Date getStartDate()
    {
        return ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();
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
                dateStart = ((DateTimeField)recTour.getField(Tour.DEPARTURE_DATE)).getDateTime();
            calendar.setTime(dateStart);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            int iDaysOffset = (int)recTourHeaderDetail.getField(TourHeaderDetail.DAY).getValue();
            iDaysOffset -= 1; // Day 1 = offset +0
            calendar.add(Calendar.DATE, iDaysOffset);
            dateStart = calendar.getTime();     // Start date
            if (!recTourHeaderDetail.getField(TourHeaderDetail.ETD).isNull())
            {
                timeDetail = ((DateTimeField)recTourHeaderDetail.getField(TourHeaderDetail.ETD)).getDateTime();    // Start time
                bSetTime = true;    // Use this time!
            }
        }
        
        Product recProduct = this.getProduct();
        if (!bSetTime)
            if ((recProduct != null) && (!recProduct.getField(Product.ETD).isNull()))
        {
            timeDetail = ((TimeField)recProduct.getField(Product.ETD)).getDateTime();
            bSetTime = true;    // Did supply a time
        }
        
        if ((dateStart == null) || (dateStart.getTime() == 0)
            || ((recTourHeaderDetail == null) && (!this.getField(BookingDetail.DETAIL_DATE).isNull())))
                dateStart = ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();    // Don't change date (change time only if 0 or 12)
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
        
        boolean[] rgbEnabled = this.getField(BookingDetail.DETAIL_DATE).setEnableListeners(false);  // Don't call again
        ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).setDate(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        if (timeDetail != null)
            ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).setTime(timeDetail, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingDetail.DETAIL_DATE).setEnableListeners(rgbEnabled);
        
        return ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();
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
        return ((DateTimeField)this.getField(BookingDetail.DETAIL_END_DATE)).getDateTime();
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
            Record recCity = ((ReferenceField)recProduct.getField(Product.CITY_ID)).getReference();
            if (recCity != null)
            {
                this.getField(BookingDetail.GMT_OFFSET).moveFieldToThis(recCity.getField(City.GMT_OFFSET));
                if (recCity.getField(City.GMT_OFFSET).isNull())
                {
                    Record recCountry = ((ReferenceField)recCity.getField(City.COUNTRY_ID)).getReference();
                    if (recCountry != null)
                    {
                        this.getField(BookingDetail.GMT_OFFSET).moveFieldToThis(recCountry.getField(Country.GMT_OFFSET));
                        if (this.getField(BookingDetail.GMT_OFFSET).isNull())
                        {
                            Record recState = ((ReferenceField)recCity.getField(City.STATE_ID)).getReference();
                            if (recState != null)
                                this.getField(BookingDetail.GMT_OFFSET).moveFieldToThis(recState.getField(State.GMT_OFFSET));
                        }
                    }
                }
            }
        }
        Calendar calendar = DateTimeField.m_calendar;
        
        Date date = this.getStartDate();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, (int)this.getField(BookingDetail.GMT_OFFSET).getValue());
        date = calendar.getTime();
        ((DateTimeField)this.getField(BookingDetail.GMT_DATE)).setDateTime(date,  DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        date = this.getEndDate();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, (int)this.getField(BookingDetail.GMT_OFFSET).getValue());
        date = calendar.getTime();
        ((DateTimeField)this.getField(BookingDetail.GMT_END_DATE)).setDateTime(date,  DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * Get the description of the product for this line item.
     * Usually, you just get the description of the current product.
     * For manual lines, the manual description is returned.
     */
    public String getProductDesc()
    {
        return this.getField(BookingDetail.DESCRIPTION).toString();

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
            return this.getField(BookingDetail.DESCRIPTION).toString();
        return recProduct.getField(Product.DESCRIPTION).toString();
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
        this.getField(BookingDetail.TOUR_ID).moveFieldToThis(recTour.getField(Tour.ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
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
            && (this.getField(BookingDetail.PRODUCT_ID).getLength() == 0))
            || ((recTourHeaderDetail == null)
            && (this.getField(BookingDetail.DETAIL_DATE).getLength() == 0)))
        {
            this.getField(BookingDetail.INFO_STATUS_ID).setValue(CostStatus.DATA_REQUIRED);
            String strError = "Data required in the {0} field";
            strError = this.getTask().getApplication().getResources(ThinResourceConstants.ERROR_RESOURCE, true).getString(strError);
            strError = MessageFormat.format(strError, this.getField(BookingDetail.INFO_STATUS_ID).getFieldDesc());
            this.setErrorMessage(BookingDetail.INFO_STATUS_ID, strError);
            // Even though there is an error, continue setting the other properties (hey behaviors are disabled)
        }
        if (recBooking == null)
            recBooking = this.getBooking(true);
        if (recTour == null)
        {
            if (this.getField(BookingDetail.TOUR_ID).isNull())
                recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
            else
                recTour = (Tour)((ReferenceField)this.getField(BookingDetail.TOUR_ID)).getReference();
        }
        
        boolean[] rgbEnabled = this.setEnableListeners(false);
        Object[] rgobjEnabled = this.setEnableFieldListeners(false);
        
        // Save the current state so I know what was changed afterwards
        int iFieldsTypes = BaseBuffer.ALL_FIELDS;
        BaseBuffer buffer = new VectorBuffer(null, iFieldsTypes);
        buffer.fieldsToBuffer(this, iFieldsTypes);
        boolean[] rgbModified = this.getModified();
        
        this.initBookingDetailFields(recBooking, recTour, true);    // This will init any fields that aren't already set up.
        if (this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).isNull())
        {
            int iDefaultInfoTransport = ((ReferenceField)this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID)).getIDFromCode(MessageTransport.DEFAULT);
            this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).setValue(iDefaultInfoTransport, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
        
        if (this.getField(BookingDetail.PRODUCT_ID).isJustModified())
        {   // Note: No need to enable behaviors, since if info status changes, the other status' behaviors will be called also. 
            this.getField(BookingDetail.INFO_STATUS_ID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.INFO_REQUEST_KEY).initField(DBConstants.DISPLAY);    // Zero this out
            this.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).initField(DBConstants.DISPLAY);
            this.getField(BookingDetail.COST_STATUS_ID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.COST_REQUEST_KEY).initField(DBConstants.DISPLAY);    // Zero this out
            this.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).initField(DBConstants.DISPLAY);
            this.getField(BookingDetail.INVENTORY_STATUS_ID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.INVENTORY_REQUEST_KEY).initField(DBConstants.DISPLAY);    // Zero this out
            this.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).initField(DBConstants.DISPLAY);
            this.getField(BookingDetail.PRODUCT_STATUS_ID).setValue(InfoStatus.NOT_VALID);
            this.getField(BookingDetail.PRODUCT_REQUEST_KEY).initField(DBConstants.DISPLAY);    // Zero this out
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
            this.getField(BookingDetail.PRODUCT_ID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.PRODUCT_ID));
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
        
        Date dateStart = ((DateTimeField)this.getField(BookingDetail.MODULE_START_DATE)).getDateTime();
        Date date = this.setupStartDate(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
        ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        date = this.setupEndDate();
        ((DateTimeField)this.getField(BookingDetail.DETAIL_END_DATE)).setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        String strDesc = this.setupProductDesc();
        this.getField(BookingDetail.DESCRIPTION).setString(strDesc);
        this.setupGMTDates();
        
        this.getField(BookingDetail.TOUR_ID).moveFieldToThis(recTour.getField(Tour.ID));   // Could be fake tour or dual series
        
        return iErrorCode;
    }
    /**
     * Move data from the product record to this record.
     * @param recProduct the souce product record.
     * @return An error code.
     */
    public int moveProductFields(Product recProduct)
    {
        if (this.getField(BookingDetail.ACK_DAYS).isNull())
            this.moveTargetField(recProduct, BookingDetail.ACK_DAYS, Product.ACK_DAYS);
        
        this.getField(BookingDetail.VENDOR_ID).setModified(false);  // This will be auto-restored in calling (setDetailProductFields) method.
        this.getField(BookingDetail.VENDOR_ID).moveFieldToThis(recProduct.getField(Product.VENDOR_ID));
        if (this.getField(BookingDetail.VENDOR_ID).isModified())
        {
            Record recVendor = ((ReferenceField)recProduct.getField(Product.VENDOR_ID)).getReference();
            if (recVendor != null)
            {
                Record recCurrency = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReference();
                if (recCurrency != null)
                {
                    if (!recCurrency.getField(Currencys.COSTING_RATE).isNull())
                        this.getField(BookingDetail.EXCHANGE).moveFieldToThis(recCurrency.getField(Currencys.COSTING_RATE));
                    else
                        this.getField(BookingDetail.EXCHANGE).moveFieldToThis(recCurrency.getField(Currencys.LAST_RATE));
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
            this.moveTargetField(recTourHeaderDetail, BookingDetail.INFO_MESSAGE_TRANSPORT_ID, TourHeaderDetail.INFO_MESSAGE_TRANSPORT_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.INFO_STATUS_ID, TourHeaderDetail.INFO_STATUS_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID, TourHeaderDetail.INVENTORY_MESSAGE_TRANSPORT_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.INVENTORY_STATUS_ID, TourHeaderDetail.INVENTORY_STATUS_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.COST_MESSAGE_TRANSPORT_ID, TourHeaderDetail.COST_MESSAGE_TRANSPORT_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.COST_STATUS_ID, TourHeaderDetail.COST_STATUS_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID, TourHeaderDetail.PRODUCT_MESSAGE_TRANSPORT_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.PRODUCT_STATUS_ID, TourHeaderDetail.PRODUCT_STATUS_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.ACK_DAYS, TourHeaderDetail.ACK_DAYS);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.RATE_ID, TourHeaderDetail.RATE_ID);
            this.moveTargetField(recTourHeaderDetail, BookingDetail.CLASS_ID, TourHeaderDetail.CLASS_ID);
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
    public boolean moveTargetField(Record recTarget, String iDetailField, String iTargetField)
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
        ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingDetail.PRODUCT_TYPE_ID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.PRODUCT_TYPE_ID));
        int iErrorCode = super.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
        return iErrorCode;
    }
    /**
     * Get Pax using this service.
     */
    public short getNoPax()
    {
        if (this.getField(BookingDetail.BOOKING_PAX_ID).getValue() != 0)
            return 1; // Pax mod
        else
        {
            Record recBooking = this.getBooking(true);
            if (recBooking == null)
                return 1;
            return (short)recBooking.getField(Booking.PAX).getValue();
        }
    }
    /**
     * How many of this type of passenger (ie., single, double, etc.) are in this type of room?.
     */
    public int getPaxInRoom(int iRoomType)
    {
        if (this.getField(BookingDetail.BOOKING_PAX_ID).getValue() != 0)
            return 1; // Pax mod **FIX THIS**
        else
        {
            Booking booking = this.getBooking(true);
            if ((iRoomType >= PaxCategory.SINGLE_ID)
                && (iRoomType <= PaxCategory.CHILD_ID))
            {
                int iFieldSeq = booking.getFieldSeq(Booking.SINGLE_PAX) + iRoomType - PaxCategory.SINGLE_ID;
                return (int)booking.getField(iFieldSeq).getValue();
            }
            else
                return booking.getCountPax();
        }
    }
    /**
     * Get the overall status of this line item.
     * This includes status of the pricing, inventory, date, etc.
     */
    public int getStatus()
    {
        int iStatus = (1 << 0);   // Normal status
        if ((this.getField(BookingDetail.INFO_STATUS_ID).getValue() != InfoStatus.VALID) && (this.getField(BookingDetail.INFO_STATUS_ID).getValue() != InfoStatus.NO_STATUS) && (this.getField(BookingDetail.INFO_STATUS_ID).getValue() != InfoStatus.NULL_STATUS))
            iStatus = iStatus | (1 << BookingConstants.INFO_LOOKUP);
        if ((this.getField(BookingDetail.COST_STATUS_ID).getValue() != CostStatus.VALID) && (this.getField(BookingDetail.COST_STATUS_ID).getValue() != CostStatus.NO_STATUS) && (this.getField(BookingDetail.COST_STATUS_ID).getValue() != CostStatus.NULL_STATUS))
            iStatus = iStatus | (1 << BookingConstants.COST_LOOKUP);
        if ((this.getField(BookingDetail.INVENTORY_STATUS_ID).getValue() != InventoryStatus.VALID) && (this.getField(BookingDetail.INVENTORY_STATUS_ID).getValue() != InventoryStatus.NO_STATUS) && (this.getField(BookingDetail.INVENTORY_STATUS_ID).getValue() != InventoryStatus.NULL_STATUS))
            iStatus = iStatus | (1 << BookingConstants.INVENTORY_LOOKUP);
        if ((this.getField(BookingDetail.PRODUCT_STATUS_ID).getValue() != ProductStatus.VALID) && (this.getField(BookingDetail.PRODUCT_STATUS_ID).getValue() != ProductStatus.NO_STATUS) && (this.getField(BookingDetail.PRODUCT_STATUS_ID).getValue() != ProductStatus.NULL_STATUS) && (this.getField(BookingDetail.PRODUCT_STATUS_ID).getValue() != ProductStatus.PROPOSAL) && (this.getField(BookingDetail.PRODUCT_STATUS_ID).getValue() != ProductStatus.CANCELED))
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
        return BookingConstants.BUTTON_LOCATION + this.getField(BookingDetail.PRODUCT_TYPE).toString();
    }
    /**
     * Check if this request is required and process the message.
     * Then update the status to reflect the new status of the update.
     * Note: Usually you don't call this method directly, the best way to check
     * for a request is to set the corresponding flag (ie., InfoStatusRequest) to true.
     * @param iStatusType The status field to update.
     */
    public void checkRequestRequired(String iStatusType)
    {
        String iFieldSeq = this.checkRequiredParams(iStatusType);  // Quickly check the fields to keep from building a message every time
        if (iFieldSeq != null)
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
        if (iStatusType == BookingDetail.INFO_STATUS_ID)
            if (this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).isNull())
        {
            int iDefaultInfoTransport = ((ReferenceField)this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID)).getIDFromCode(MessageTransport.DEFAULT);
            this.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).setValue(iDefaultInfoTransport);
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
                    Record recTour = ((ReferenceField)this.getBooking(true).getField(Booking.TOUR_ID)).getReference();
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
    public String checkRequiredParams(String iStatusType)
    {
        if (this.getField(BookingDetail.PRODUCT_ID).isNull())
            return BookingDetail.PRODUCT_ID;    // Product must be non-null
        if (this.getField(BookingDetail.DETAIL_DATE).isNull())
            return BookingDetail.DETAIL_DATE; // Date must be non-null
        return null;    // Looks good so far.
    }
    /**
     * Lookup the message for this status update.
     * If no update is required, return a null message.
     * @return The message to process (or null if no message).
     */
    public BaseProductMessageDesc checkMessageRequired(String iStatusType)
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
            strContactType = Product.PRODUCT_FILE;      // Cancellation message is generic
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
    public String getErrorMessage(String iStatusType)
    {
        return ((PropertiesField)this.getField(BookingDetail.ERROR_PROPERTIES)).getProperty(this.getFieldParam(this.getField(iStatusType)) + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM);
    }
    /**
     * Set the error message in this record for this message type.
     */
    public void setErrorMessage(BaseProductMessageDesc messageData, String strError)
    {
        ((PropertiesField)this.getField(BookingDetail.ERROR_PROPERTIES)).setProperty(messageData.getMessageTypeParam() + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM, strError, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * Set the error message in this record for this status type.
     */
    public void setErrorMessage(String iStatusType, String strError)
    {
        ((PropertiesField)this.getField(BookingDetail.ERROR_PROPERTIES)).setProperty(this.getFieldParam(this.getField(iStatusType)) + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM, strError, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * For this kind of message, get the process type.
     * For now, all are UPDATE, which means update the information in the booking
     * when the response comes back (vs. just return the information to the caller).
     */
    public String getProcessType(String iStatusType)
    {
        return ProcessType.UPDATE;
    }
    /**
     * Add any message properties that are set in this record.
     */
    public void addMessageProperties(String strPrefix, boolean bDeleteProperties, TrxMessageHeader messageHeader, BaseMessage message, String strNewPrefix)
    {
        Map<String,Object> properties = ((PropertiesField)this.getField(BookingDetail.PROPERTIES)).getProperties();
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
                        ((PropertiesField)this.getField(BookingDetail.PROPERTIES)).setProperty(strKey, null);  // Remove from record
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
        if (fldStatusID == this.getField(BookingDetail.INFO_STATUS_ID))
            strParam = BookingDetail.INFO_PARAM;
        else if (fldStatusID == this.getField(BookingDetail.COST_STATUS_ID))
            strParam = BookingDetail.COST_PARAM;
        else if (fldStatusID == this.getField(BookingDetail.INVENTORY_STATUS_ID))
            strParam = BookingDetail.INVENTORY_PARAM;
        else if (fldStatusID == this.getField(BookingDetail.PRODUCT_STATUS_ID))
            strParam = BookingDetail.PRODUCT_PARAM;
        return strParam;
    }
    /**
     * Get the request type code for this status field sequence.
     */
    public String getRequestType(String iStatusType)
    {
        String strRequestType = this.getField(iStatusType).getFieldName();
        if (RequestType.BOOKING.equalsIgnoreCase(strRequestType))
        {
            if (!this.productOrdered())
                strRequestType = RequestType.BOOKING_ADD;
            else
            {
                if (this.getField(BookingDetail.DELETED).getState() == true)
                    strRequestType = RequestType.BOOKING_CANCEL;
                else
                {
                    Record recTour = ((ReferenceField)this.getField(BookingDetail.TOUR_ID)).getReference();
                    if ((recTour != null) &&
                        ((recTour.getEditMode() == DBConstants.EDIT_CURRENT) || (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                            && (recTour.getField(Tour.CANCELLED).getState() == true))
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
        if (this.getField(BookingDetail.PRODUCT_REQUEST_KEY).isNull())
            return false;
        else
            return true;
    }
    /**
     * Get the message transport code for this message type.
     */
    public String getMessageTransport(String iStatusType)
    {
        Record recMessageTransport = ((ReferenceField)this.getField(this.getFieldSeq(iStatusType) + MESSAGE_TRANSPORT_OFFSET)).getReference();
        if ((recMessageTransport != null) &&
            ((recMessageTransport.getEditMode() == DBConstants.EDIT_CURRENT) || (recMessageTransport.getEditMode() == DBConstants.EDIT_IN_PROGRESS)))
                return recMessageTransport.getField(MessageTransport.CODE).toString();
        return null;
    }
    /**
     * HandlePriceChange Method.
     */
    public int handlePriceChange(int iChangeType)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        Booking recBooking = this.getBooking(true);
        
        int iNonTourPricingType = (int)((ReferenceField)recBooking.getField(Booking.NON_TOUR_PRICING_TYPE_ID)).getReference().getField(PricingType.PRICING_CODES).getValue();
        if (iNonTourPricingType == PricingType.OPTION_PRICING)  // Can't have option pricing on non-tours!
            iNonTourPricingType = PricingType.COMPONENT_PRICING | PricingType.COMPONENT_COST_PRICING;
        
        if ((!this.getField(BookingDetail.TOUR_HEADER_OPTION_ID).isNull()) || (this.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.TOUR_ID))
        {   // Entered using a module or is a module
            BaseField fldModuleID = this.getField(BookingDetail.MODULE_ID);
            Date dateStart = ((DateTimeField)this.getField(BookingDetail.MODULE_START_DATE)).getDateTime();
            if (this.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.TOUR_ID)
            {
                fldModuleID = this.getField(BookingDetail.PRODUCT_ID);
                dateStart = ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();
            }
            int iTourPricingType = recBooking.getTourPricingType(null, fldModuleID, dateStart);
            if ((iTourPricingType & PricingType.OPTION_PRICING) != 0)
            {
                if ((this.getField(BookingDetail.TOTAL_COST_LOCAL).isModified())
                        || (iChangeType == DBConstants.DELETE_TYPE))   // This means the record has been deleted
                {
                        if ((recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) == null)
                            || (recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).isEnabledListener() == true))  // If this is DISABLED, don't change the status (only on initial setup)
                    {
                        PricingType recPricingType2 = new PricingType(this.findRecordOwner());
                        PricingType recPricingType3 = recPricingType2.getPricingType(PricingType.COMPONENT_COST_PRICING);
                        if (recPricingType3 != null)
                            recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).moveFieldToThis(recPricingType3.getField(PricingType.ID));
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
            if (!strProductKey.equals(this.getField(BookingDetail.PRICING_DETAIL_KEY).toString()))
            {
                Product recProduct = this.getProduct();
        
                iErrorCode = this.updateBookingComponentPricing(recProduct, iChangeType);
        
                if (iErrorCode == DBConstants.NORMAL_RETURN)
                    return iErrorCode;  // Otherwise, continue and price using the cost.
                iErrorCode = DBConstants.NORMAL_RETURN;
                strProductKey = this.getField(BookingDetail.PRICING_DETAIL_KEY).toString();
            }
        }
        if ((iNonTourPricingType & PricingType.COMPONENT_COST_PRICING) != 0)
        {
            if ((this.getField(BookingDetail.TOTAL_COST_LOCAL).isModified())
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
            double dCommissionRate = this.getBooking(true).getField(Booking.STD_COMMISSION).getValue();
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
        
        double dPPCost = this.getField(BookingDetail.TOTAL_COST_LOCAL).getValue();
        int iQuantity = this.getPaxInRoom(PaxCategory.ALL_ID);
        if (iQuantity != 0)
            dPPCost = dPPCost / iQuantity;
        iErrorCode = this.updateBookingLine(this.getBookingLine(), PricingType.COMPONENT_COST_PRICING, PaxCategory.ALL_ID, iQuantity, dPPCost, true, recBooking.getField(Booking.COMMISSION).getValue(), null, PricingStatus.OKAY, iChangeType);
        
        return iErrorCode;
    }
    /**
     * GetPricingDetailKey Method.
     */
    public String getPricingDetailKey()
    {
        String strReturn = DBConstants.BLANK;
        
        strReturn = this.addKeyField(strReturn, BookingDetail.PRODUCT_ID);
        strReturn = this.addKeyField(strReturn, BookingDetail.DETAIL_DATE);
        strReturn = this.addKeyField(strReturn, BookingDetail.CLASS_ID);
        strReturn = this.addKeyField(strReturn, BookingDetail.RATE_ID);
        
        return strReturn;
    }
    /**
     * AddKeyField Method.
     */
    public String addKeyField(String strReturn, String iFieldSeq)
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
            Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
            m_recBookingLine = new BookingLine(this.findRecordOwner());
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
            this.getField(BookingDetail.TOTAL_PRICE_LOCAL).setValue(0.00);
            this.getField(BookingDetail.PP_PRICE_LOCAL).setValue(0.00);
        }
        
        Booking recBooking = this.getBooking(true);        
        try {
            recBookingLine.addNew();
            if (iChangeType != DBConstants.ADD_TYPE)
            {
                recBookingLine.getField(BookingLine.BOOKING_ID).moveFieldToThis(recBooking.getField(Booking.ID));
                recBookingLine.getField(BookingLine.BOOKING_PAX_ID).moveFieldToThis(this.getField(BookingDetail.BOOKING_PAX_ID));   // For now
                recBookingLine.getField(BookingLine.BOOKING_DETAIL_ID).moveFieldToThis(this.getField(BookingDetail.ID));
                recBookingLine.getField(BookingLine.PAX_CATEGORY_ID).setValue(iPaxCategory);
                recBookingLine.setKeyArea(BookingLine.BOOKING_DETAIL_ID_KEY);
                if ((recBookingLine.seek(">="))
                    && (recBookingLine.getField(BookingLine.BOOKING_ID).equals(recBooking.getField(Booking.ID)))
                        && (recBookingLine.getField(BookingLine.BOOKING_PAX_ID).equals(this.getField(BookingDetail.BOOKING_PAX_ID)))
                        && (recBookingLine.getField(BookingLine.BOOKING_DETAIL_ID).equals(this.getField(BookingDetail.ID)))
                        && (recBookingLine.getField(BookingLine.PAX_CATEGORY_ID).getValue() == iPaxCategory))
                    recBookingLine.edit();
                else
                    recBookingLine.addNew();
            }
        
            double dPPCost = 0;
            double dPPPrice = 0;
            double dMarkup = recBooking.getField(Booking.MARKUP).getValue();
            if (bCommissionable)
                dCommissionRate = recBooking.getField(Booking.STD_COMMISSION).getValue();
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
        
            recBookingLine.getField(BookingLine.BOOKING_ID).moveFieldToThis(recBooking.getField(Booking.ID));
        
            recBookingLine.getField(BookingLine.MODULE_ID).moveFieldToThis(this.getField(BookingDetail.MODULE_ID));
            recBookingLine.getField(BookingLine.MODULE_START_DATE).moveFieldToThis(this.getField(BookingDetail.MODULE_START_DATE));
            if (this.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.TOUR_ID)
            {
                recBookingLine.getField(BookingLine.MODULE_ID).moveFieldToThis(this.getField(BookingDetail.PRODUCT_ID));
                recBookingLine.getField(BookingLine.MODULE_START_DATE).moveFieldToThis(this.getField(BookingDetail.DETAIL_DATE));
            }
        
            // Note: BookingLine.SEQUENCE is assigned automatically.
            String strDescription = this.getField(BookingDetail.DESCRIPTION).toString();
            if (iPaxCategory != PaxCategory.ALL_ID)
            {
                RoomTypeField fldTemp = new RoomTypeField(null, DBConstants.BLANK, DBConstants.DEFAULT_FIELD_LENGTH, null, null);
                String strRoomType = fldTemp.convertIndexToString(iPaxCategory);
                fldTemp.free();
                strDescription = strDescription + " (" + strRoomType + ')';
            }
            recBookingLine.getField(BookingLine.DESCRIPTION).setString(strDescription);
            recBookingLine.getField(BookingLine.PRICE).setValue(dPPPrice);
        
            recBookingLine.getField(BookingLine.QUANTITY).setValue(iQuantity);
        
            recBookingLine.getField(BookingLine.COMMISSIONABLE).setState(bCommissionable);
            recBookingLine.getField(BookingLine.COMMISSION_RATE).setValue(dCommissionRate);
            recBookingLine.getField(BookingLine.PRICING_STATUS_ID).setValue(iPricingStatusID);
            if ((strPayAt == null) || (strPayAt.length() == 0))
                strPayAt = PayAtField.FINAL_PAY_DATE;
            recBookingLine.getField(BookingLine.PAY_AT).setString(strPayAt);
            recBookingLine.getField(BookingLine.BOOKING_PAX_ID).moveFieldToThis(this.getField(BookingDetail.BOOKING_PAX_ID));   // For now
            
            Object bookingDetailID = this.getField(BookingDetail.ID).getData();
            if (iChangeType == DBConstants.ADD_TYPE)
                if (bookingDetailID == null)    // Always for add
                {
                    if (this.getEditMode() == DBConstants.EDIT_ADD)
                        this.writeAndRefresh();     // Need record ID
                    bookingDetailID = this.getField(BookingDetail.ID).getData();
                }
            recBookingLine.getField(BookingLine.BOOKING_DETAIL_ID).setData(bookingDetailID);
            recBookingLine.getField(BookingLine.PAX_CATEGORY_ID).setValue(iPaxCategory);
            if ((iChangeType == DBConstants.DELETE_TYPE)
                || ((iPricingStatusID != PricingStatus.NOT_VALID) && ((recBookingLine.getField(BookingLine.PRICE).getValue() == 0)
                        || (recBookingLine.getField(BookingLine.QUANTITY).getValue() == 0))))
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
                this.getField(BookingDetail.TOTAL_PRICE_LOCAL).setValue(this.getField(BookingDetail.TOTAL_PRICE_LOCAL).getValue() + recBookingLine.getField(BookingLine.GROSS).getValue());
                this.getField(BookingDetail.PP_PRICE_LOCAL).setValue(this.getField(BookingDetail.PP_PRICE_LOCAL).getValue() + recBookingLine.getField(BookingLine.PRICE).getValue());
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
                recBookingDetail.checkRequestRequired(BookingDetail.INFO_STATUS_ID);
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
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
        if ((recTour == null)
                || (recTour.getEditMode() == DBConstants.EDIT_NONE))
            return DBConstants.ERROR_RETURN;
        TourHeader recTourHeader = (TourHeader)this.getProduct();
        if ((recTourHeader == null)
                || (recTourHeader.getEditMode() == DBConstants.EDIT_NONE))
            return DBConstants.ERROR_RETURN;
        Date dateStart = ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();
        if (dateStart == null)
            return DBConstants.ERROR_RETURN;
        try {
            recTour.writeAndRefresh();
            recBooking.writeAndRefresh();
        } catch (DBException ex) {
            ex.printStackTrace();
        }
        
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        FieldDataScratchHandler fieldDataScratchHandler = (FieldDataScratchHandler)this.getField(BookingDetail.DETAIL_DATE).getListener(FieldDataScratchHandler.class);
        Date dateOriginal = (Date)fieldDataScratchHandler.getOriginalData();
        
        if (iChangeType == DBConstants.AFTER_DELETE_TYPE)
        {   // Deleted/canceled
            iErrorCode = recBooking.deleteTourDetail(recTour, recBookingPax, this.getField(BookingTour.PRODUCT_ID), dateStart);
        }
        else if ((dateOriginal == null) || (dateOriginal.equals(dateStart)))
        {   // New
            iErrorCode = recBooking.addTourDetail(recTour, recTourHeader, recBookingPax, recBookingAnswer, dateStart, this.getField(BookingTour.ASK_FOR_ANSWER));
        }
        else
        {   // Changed
            iErrorCode = recBooking.changeTourDetail(recTour, recBookingPax, recTourHeader, dateOriginal, dateStart);
        }
        return iErrorCode;
    }

}
