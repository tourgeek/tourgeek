/**
 * @(#)Air.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.air.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.air.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.air.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.message.air.response.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.main.db.base.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.air.db.*;

/**
 *  Air - Flights.
 */
public class Air extends TransportProduct
     implements AirModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kDescription = kDescription;
    //public static final int kCode = kCode;
    //public static final int kCityID = kCityID;
    //public static final int kCityCode = kCityCode;
    //public static final int kToCityID = kToCityID;
    //public static final int kToCityCode = kToCityCode;
    //public static final int kProductChainID = kProductChainID;
    //public static final int kEtd = kEtd;
    //public static final int kComments = kComments;
    //public static final int kItineraryDesc = kItineraryDesc;
    public static final int kAirlineID = kTransportProductLastField + 1;
    public static final int kFlightNo = kAirlineID + 1;
    public static final int kArriveTime = kFlightNo + 1;
    public static final int kAddDays = kArriveTime + 1;
    public static final int kMeals = kAddDays + 1;
    public static final int kStartDate = kMeals + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kEquipment = kEndDate + 1;
    public static final int kDays = kEquipment + 1;
    public static final int kClasses = kDays + 1;
    public static final int kStops = kClasses + 1;
    public static final int kSegment = kStops + 1;
    public static final int kAirLastField = kSegment;
    public static final int kAirFields = kSegment - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kCityCodeKey = kCodeKey + 1;
    public static final int kAirLastKey = kCityCodeKey;
    public static final int kAirKeys = kCityCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Air()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Air(RecordOwner screen)
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

    public static final String kAirFile = "Air";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAirFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & Product.PRICING_GRID_SCREEN) == Product.PRICING_GRID_SCREEN)
            screen = new AirPricingGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_GRID_SCREEN) == Product.INVENTORY_GRID_SCREEN)
            screen = new AirInventoryGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_SCREEN) == Product.INVENTORY_SCREEN)
            screen = new AirInventoryScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.RANGE_ADJUST_SCREEN) == Product.RANGE_ADJUST_SCREEN)
            screen = new AirInventoryRangeAdjust(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new AirScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = new AirGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kDescription)
        //  field = new ProductDesc(this, "Description", 50, null, null);
        //if (iFieldSeq == kCode)
        //  field = new StringField(this, "Code", 10, null, null);
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFlightNo)
            field = new StringField(this, "FlightNo", 4, null, null);
        //if (iFieldSeq == kCityID)
        //  field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityCode)
        {
            field = new StringField(this, "CityCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kToCityID)
        //  field = new CityField(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kToCityCode)
        {
            field = new StringField(this, "ToCityCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kProductChainID)
            field = new AirChainField(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEtd)
            field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        if (iFieldSeq == kMeals)
        {
            field = new StringField(this, "Meals", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStartDate)
        {
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEndDate)
        {
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEquipment)
        {
            field = new StringField(this, "Equipment", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDays)
        {
            field = new StringField(this, "Days", 4, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClasses)
        {
            field = new StringField(this, "Classes", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStops)
            field = new ShortField(this, "Stops", 1, null, null);
        if (iFieldSeq == kComments)
        {
            field = new StringField(this, "Comments", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kItineraryDesc)
        //  field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSegment)
        {
            field = new ShortField(this, "Segment", 1, null, new Short((short)1));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAirLastField)
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
        if (iKeyArea == kCityCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CityCode");
            keyArea.addKeyField(kCityCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kToCityCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kAirlineID, DBConstants.ASCENDING);
            keyArea.addKeyField(kFlightNo, DBConstants.ASCENDING);
            keyArea.addKeyField(kEndDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kDays, DBConstants.ASCENDING);
            keyArea.addKeyField(kSegment, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAirLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAirLastKey)
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
        
        this.getField(Air.kAirlineID).addListener(new SetFlightDescHandler(null));
        this.getField(Air.kFlightNo).addListener(new SetFlightDescHandler(null));
        this.getField(Air.kCityCode).addListener(new SetFlightDescHandler(null));
        this.getField(Air.kToCityCode).addListener(new SetFlightDescHandler(null));
        this.getField(Air.kEtd).addListener(new SetFlightDescHandler(null));
        this.getField(Air.kArriveTime).addListener(new SetFlightDescHandler(null));
        this.getField(Air.kAddDays).addListener(new SetFlightDescHandler(null));
        
        this.getField(Air.kAirlineID).addListener(new SetFlightCodeHandler(null));
        this.getField(Air.kFlightNo).addListener(new SetFlightCodeHandler(null));
    }
    /**
     * Read the locally stored product cost (Override).
     */
    public BaseMessage processCostRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        AirMessageData productMessageData = (AirMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData  = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        Date dateTarget = productMessageData.getTargetDate();
        short sTargetPax = passengerMessageData.getTargetPax();
        int iAirRateID = productMessageData.getRateTypeID();
        int iAirClassID = productMessageData.getRateClassID();
        
        AirRateResponse responseMessage = null;
        if (messageReply == null)
        {
            messageReply = new TreeMessage(null, null);
            responseMessage =  new AirRateResponse(messageReply, null);
        }
        else
            responseMessage = (AirRateResponse)messageReply.getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        ProductRateResponseMessageData responseProductMessageData = (ProductRateResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
                
        double dPPCost = this.getAirCost(dateTarget, iAirRateID, iAirClassID, false);
        double dPPPriceLocal = this.getAirCost(dateTarget, iAirRateID, iAirClassID, true);
        
        this.getField(Air.kPPCost).setValue(dPPCost);
        this.getField(Air.kPPPriceLocal).setValue(dPPPriceLocal);
        
        double dAirCost = dPPCost;
        
        double dTotalLocalCost = Math.floor(dAirCost * sTargetPax * 100.00 + 0.5) / 100.00;
        double dTotalLocalPrice = Math.floor(dPPPriceLocal * sTargetPax * 100.00 + 0.5) / 100.00;
        
        this.getField(Air.kClassID).setValue(iAirClassID);
        
        this.getField(Air.kPPCost).setValue(dAirCost);
        this.getField(Product.kProductCost).setValue(dTotalLocalCost);
        this.getField(Product.kProductPriceLocal).setValue(dTotalLocalPrice);
        
        responseProductMessageData.setPPCost(this.getField(Air.kPPCost).getValue());
        if (iAirClassID != productMessageData.getRateClassID())
        {
            responseProductMessageData.setRateClassID(productMessageData.getRateClassID());
            responseProductMessageData.setNewRateClassID(iAirClassID);
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
            return new AirBookingResponse(message, strKey);
        else if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(strRequestType))
            return new AirBookingChangeResponse(message, strKey);
        else
            return super.getProductBookingResponse(strRequestType, message, strKey);
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetail getBookingDetail(RecordOwner recordOwner)
    {
        return new BookingAir(recordOwner);
    }
    /**
     * GetAirCost Method.
     */
    public double getAirCost(Date dateTarget, int iAirRateID, int iAirClassID, boolean bGetPrice)
    {
        double dCost = 0;
        AirPricing recProductCostLookup = ((AirPricing)this.getProductPricing()).getAirCost(this, dateTarget, iAirRateID, iAirClassID);
        if (recProductCostLookup != null)
        {
            if (!bGetPrice)
                dCost += recProductCostLookup.getCost(AirPricing.kCost, this.getProductTerms());
            else
                dCost += recProductCostLookup.getField(AirPricing.kPrice).getValue();
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
        int iRateID = (int)recBookingDetail.getField(BookingDetail.kRateID).getValue();
        int iClassID = (int)recBookingDetail.getField(BookingDetail.kClassID).getValue();
        AirPricing recProductPricing = ((AirPricing)this.getProductPricing()).getAirCost(this, dateTarget, iRateID, iClassID);
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
        return new AirPricing(recordOwner);
    }

}
