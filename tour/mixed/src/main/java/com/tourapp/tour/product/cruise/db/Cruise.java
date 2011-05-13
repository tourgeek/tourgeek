/**
 *  @(#)Cruise.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.cruise.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.cruise.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.cruise.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.message.cruise.response.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.main.msg.db.base.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  Cruise - Cruise.
 */
public class Cruise extends TransportProduct
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kCode = kCode;
    //public static final int kCityID = kCityID;
    //public static final int kCityCode = kCityCode;
    //public static final int kToCityID = kToCityID;
    //public static final int kToCityCode = kToCityCode;
    //public static final int kDescription = kDescription;
    //public static final int kVendorID = kVendorID;
    //public static final int kOperatorsCode = kOperatorsCode;
    //public static final int kProductChainID = kProductChainID;
    //public static final int kEtd = kEtd;
    //public static final int kComments = kComments;
    //public static final int kItineraryDesc = kItineraryDesc;
    //public static final int kDescSort = kDescSort;
    public static final int kCruiseReverseID = kTransportProductLastField + 1;
    public static final int kCruiseManFile = kCruiseReverseID + 1;
    public static final int kFrequency = kCruiseManFile + 1;
    public static final int kDistance = kFrequency + 1;
    public static final int kDays = kDistance + 1;
    public static final int kBreakfasts = kDays + 1;
    public static final int kLunches = kBreakfasts + 1;
    public static final int kDinners = kLunches + 1;
    public static final int kDiscontinuedOn = kDinners + 1;
    public static final int kCruiseLastField = kDiscontinuedOn;
    public static final int kCruiseFields = kDiscontinuedOn - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kDescSortKey = kCodeKey + 1;
    public static final int kVendorIDKey = kDescSortKey + 1;
    public static final int kCityIDKey = kVendorIDKey + 1;
    public static final int kCruiseLastKey = kCityIDKey;
    public static final int kCruiseKeys = kCityIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Cruise()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Cruise(RecordOwner screen)
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

    public static final String kCruiseFile = "Cruise";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCruiseFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Cruise";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & Product.PRICING_GRID_SCREEN) == Product.PRICING_GRID_SCREEN)
            screen = new CruisePricingGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_GRID_SCREEN) == Product.INVENTORY_GRID_SCREEN)
            screen = new CruiseInventoryGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_SCREEN) == Product.INVENTORY_SCREEN)
            screen = new CruiseInventoryScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.RANGE_ADJUST_SCREEN) == Product.RANGE_ADJUST_SCREEN)
            screen = new CruiseInventoryRangeAdjust(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new CruiseScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = new CruiseGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //  field = new CounterField(this, "ID", 8, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kCode)
        //  field = new StringField(this, "Code", 10, null, null);
        //if (iFieldSeq == kCityID)
        //  field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCityCode)
        //{
        //  field = new StringField(this, "CityCode", 3, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kToCityID)
        //  field = new CityField(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kToCityCode)
        //{
        //  field = new StringField(this, "ToCityCode", 3, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kDescription)
        //  field = new ProductDesc(this, "Description", 50, null, null);
        if (iFieldSeq == kCruiseReverseID)
            field = new CruiseField(this, "CruiseReverseID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kVendorID)
        //{
        //  field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kOperatorsCode)
        //  field = new StringField(this, "OperatorsCode", 20, null, null);
        if (iFieldSeq == kProductChainID)
            field = new CruiseChainField(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCruiseManFile)
            field = new StringField(this, "CruiseManFile", 30, null, null);
        //if (iFieldSeq == kEtd)
        //  field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFrequency)
            field = new StringField(this, "Frequency", 15, null, null);
        if (iFieldSeq == kDistance)
            field = new StringField(this, "Distance", 15, null, null);
        if (iFieldSeq == kDays)
            field = new ShortField(this, "Days", 3, null, null);
        if (iFieldSeq == kBreakfasts)
            field = new ShortField(this, "Breakfasts", 2, null, null);
        if (iFieldSeq == kLunches)
            field = new ShortField(this, "Lunches", 2, null, null);
        if (iFieldSeq == kDinners)
            field = new ShortField(this, "Dinners", 2, null, null);
        //if (iFieldSeq == kComments)
        //  field = new MemoField(this, "Comments", 32767, null, null);
        if (iFieldSeq == kDiscontinuedOn)
            field = new DateField(this, "DiscontinuedOn", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kItineraryDesc)
        //  field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDescSort)
        //  field = new ProductDescSort(this, "DescSort", 10, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCruiseLastField)
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
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescSortKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DescSort");
            keyArea.addKeyField(kDescSort, DBConstants.ASCENDING);
        }
        if (iKeyArea == kVendorIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "VendorID");
            keyArea.addKeyField(kVendorID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescSort, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCityIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CityID");
            keyArea.addKeyField(kCityID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescSort, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCruiseLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCruiseLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Read the locally stored product cost (Override).
     */
    public BaseMessage processCostRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        CruiseMessageData productMessageData = (CruiseMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData  = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        String NO_CRUISE_RATE = "No cruise rate";
        BaseApplication application = null;
        if (this.getRecordOwner() != null)
            if (this.getRecordOwner().getTask() != null)
                application = (BaseApplication)this.getRecordOwner().getTask().getApplication();
        if (application == null)
            application = (BaseApplication)BaseApplet.getSharedInstance().getApplication();
        int iCostStatus = BaseStatus.VALID;
        String strErrorMessage = DBConstants.BLANK;
        
        Date dateTarget = productMessageData.getTargetDate();
        short sTargetPax = passengerMessageData.getTargetPax();
        int iCruiseRateID = productMessageData.getRateTypeID();
        int iCruiseClassID = productMessageData.getRateClassID();
        
        CruiseRateResponse responseMessage = null;
        if (messageReply == null)
        {
            messageReply = new TreeMessage(null, null);
            responseMessage =  new CruiseRateResponse(messageReply, null);
        }
        else
            responseMessage = (CruiseRateResponse)messageReply.getMessageDataDesc(null);
        
        responseMessage.moveRequestInfoToReply(messageIn);
        ProductRateResponseMessageData responseProductMessageData = (ProductRateResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
        
        double dPPCost = this.getCruiseCost(dateTarget, iCruiseRateID, iCruiseClassID, false);
        double dPPPriceLocal = this.getCruiseCost(dateTarget, iCruiseRateID, iCruiseClassID, true);
        
        this.getField(Cruise.kPPCost).setValue(dPPCost);
        this.getField(Cruise.kPPPriceLocal).setValue(dPPPriceLocal);
        
        double dCruiseCost = dPPCost;
        
        double dTotalLocalCost = Math.floor(dCruiseCost * sTargetPax * 100.00 + 0.5) / 100.00;
        double dTotalLocalPrice = Math.floor(dPPPriceLocal * sTargetPax * 100.00 + 0.5) / 100.00;
        
        this.getField(Cruise.kClassID).setValue(iCruiseClassID);
        this.getField(Cruise.kRateID).setValue(iCruiseRateID);
        
        this.getField(Cruise.kPPCost).setValue(dCruiseCost);
        this.getField(Product.kProductCost).setValue(dTotalLocalCost);
        this.getField(Product.kProductPriceLocal).setValue(dTotalLocalPrice);
        
        responseProductMessageData.setPPCost(this.getField(Cruise.kPPCost).getValue());
        if (iCruiseClassID != productMessageData.getRateClassID())
        {
            responseProductMessageData.setRateClassID(productMessageData.getRateClassID());
            responseProductMessageData.setNewRateClassID(iCruiseClassID);
        }
        responseProductMessageData.setProductCost(dTotalLocalCost);
        
        int iStatus = BaseStatus.VALID;
        if (dTotalLocalCost == 0)
            iStatus = BaseStatus.NOT_VALID;
        responseMessage.setMessageDataStatus(iStatus);
        this.getField(Product.kDisplayCostStatusID).setValue(iStatus);
        return messageReply;
    }
    /**
     * Check the inventory for this detail.
     * @param message Contains all the update data for this check
     * @param fldTrxID If null, just check the inventory, if not null, update the inventory using this BookingDetail trxID.
     */
    public BaseMessage processAvailabilityRequestInMessage(BaseMessage messageIn, BaseMessage messageReply, BaseField fldTrxID)
    {
        return super.processAvailabilityRequestInMessage(messageIn, messageReply, fldTrxID);
    }
    /**
     * This is for products that can be externally booked.
     * @return the booking reply message with the proper params.
     */
    public BaseMessage processBookingRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        return super.processBookingRequestInMessage(messageIn, messageReply);
    }
    /**
     * GetProductBookingResponse Method.
     */
    public ProductBookingResponse getProductBookingResponse(String strRequestType, BaseMessage message, String strKey)
    {
        if (RequestType.BOOKING_ADD.equalsIgnoreCase(strRequestType))
            return new CruiseBookingResponse(message, strKey);
        else if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(strRequestType))
            return new CruiseBookingChangeResponse(message, strKey);
        else
            return super.getProductBookingResponse(strRequestType, message, strKey);
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetail getBookingDetail(RecordOwner recordOwner)
    {
        return new BookingCruise(recordOwner);
    }
    /**
     * GetCruiseCost Method.
     */
    public double getCruiseCost(Date dateTarget, int iRateID, int iClassID, boolean bGetPrice)
    {
        double dCost = 0;
        CruisePricing recProductCostLookup = ((CruisePricing)this.getProductPricing()).getCruiseCost(this, dateTarget, iRateID, iClassID);
        if (recProductCostLookup != null)
        {
            if (!bGetPrice)
                dCost += recProductCostLookup.getCost(CruisePricing.kCost, this.getProductTerms());
            else
                dCost += recProductCostLookup.getField(CruisePricing.kPrice).getValue();
        }
        return dCost;
    }
    /**
     * Using this booking detail, create or change the pricing
     * using the price amount in the ProductPricing record.
     * @param recBookingLine The line file
     * @param recBookingDetail The detail for this type of product
     * @return NORMAL_RETURN if price exists and was added
     * @return ERROR_RETURN if no pricing (or a zero price) was added.
     */
    public int updateBookingPricing(BookingLine recBookingLine, BookingDetail recBookingDetail, int iChangeType)
    {
        Date dateTarget = ((DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate)).getDateTime();
        short sTargetPax = recBookingDetail.getNoPax();
        int iClassID = (int)recBookingDetail.getField(BookingDetail.kClassID).getValue();
        int iRateID = (int)recBookingDetail.getField(BookingDetail.kRateID).getValue();
        CruisePricing recProductPricing = ((CruisePricing)this.getProductPricing()).getCruiseCost(this, dateTarget, iRateID, iClassID);
        if (recProductPricing != null)
        {
            int iPricingType = PricingType.COMPONENT_PRICING;
            int iPaxCategory = PaxCategory.ALL_ID;
            int iQuantity = sTargetPax;
            double dAmount = recProductPricing.getField(ProductPricing.kPrice).getValue();
            boolean bCommissionable = recProductPricing.getField(ProductPricing.kCommissionable).getState();
            double dCommissionRate = recProductPricing.getField(ProductPricing.kCommissionRate).getValue();
            String strPayAt = recProductPricing.getField(ProductPricing.kPayAt).toString();
            return recBookingDetail.updateBookingLine(recBookingLine, iPricingType, iPaxCategory, iQuantity, dAmount, bCommissionable, dCommissionRate, strPayAt, PricingStatus.OKAY, iChangeType);
        }
        return DBConstants.ERROR_RETURN;
    }
    /**
     * CreateProductPricing Method.
     */
    public ProductPricing createProductPricing(RecordOwner recordOwner)
    {
        return new CruisePricing(recordOwner);
    }

}
