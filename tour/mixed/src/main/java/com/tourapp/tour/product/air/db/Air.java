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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.air.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(AIR_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, 8, null, null);
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
        //  field = new ProductDesc(this, DESCRIPTION, 50, null, null);
        //if (iFieldSeq == 4)
        //  field = new StringField(this, CODE, 10, null, null);
        //if (iFieldSeq == 5)
        //{
        //  field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 6)
        //  field = new StringField(this, OPERATORS_CODE, 20, null, null);
        if (iFieldSeq == 7)
            field = new AirChainField(this, PRODUCT_CHAIN_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new CityField(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new TimeField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 10)
        //  field = new ShortField(this, ACK_DAYS, 2, null, null);
        if (iFieldSeq == 11)
        {
            field = new StringField(this, COMMENTS, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 12)
        //  field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 13)
        //  field = new XmlField(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 14)
        //  field = new ProductDescSort(this, DESC_SORT, 10, null, null);
        if (iFieldSeq == 15)
        {
            field = new ProductTypeAutoField(this, PRODUCT_TYPE, 15, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 16)
        {
            field = new FullCurrencyField(this, PRODUCT_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 17)
        {
            field = new CurrencyField(this, PRODUCT_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 18)
        //  field = new MessageTransportSelect(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
        {
            field = new InventoryStatusField(this, DISPLAY_INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(BaseStatus.NO_STATUS));
            field.setVirtual(true);
        }
        if (iFieldSeq == 20)
        {
            field = new ShortField(this, INVENTORY_AVAILABILITY, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 21)
        {
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 22)
        {
            field = new StringField(this, CURRENCY_CODE_LOCAL, 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 23)
        {
            field = new StringField(this, VENDOR_NAME, 30, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 24)
        {
            field = new CostStatusField(this, DISPLAY_COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(BaseStatus.NULL_STATUS));
            field.setVirtual(true);
        }
        if (iFieldSeq == 25)
        {
            field = new FullCurrencyField(this, PP_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 26)
        {
            field = new CurrencyField(this, PP_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 27)
        {
            field = new BaseRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 28)
        {
            field = new BaseClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 29)
        {
            field = new CurrencyField(this, PRODUCT_PRICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 30)
        {
            field = new CurrencyField(this, PP_PRICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 31)
        {
            field = new StringField(this, CITY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 32)
        //  field = new CityField(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 33)
        {
            field = new StringField(this, TO_CITY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 34)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 35)
            field = new StringField(this, FLIGHT_NO, 4, null, null);
        if (iFieldSeq == 36)
        {
            field = new TimeField(this, ARRIVE_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 37)
        {
            field = new ShortField(this, ADD_DAYS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 38)
        {
            field = new StringField(this, MEALS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
        {
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 40)
        {
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 41)
        {
            field = new StringField(this, EQUIPMENT, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 42)
        {
            field = new StringField(this, DAYS, 4, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 43)
        {
            field = new StringField(this, CLASSES, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 44)
            field = new ShortField(this, STOPS, 1, null, null);
        if (iFieldSeq == 45)
        {
            field = new ShortField(this, SEGMENT, 1, null, new Short((short)1));
            field.addListener(new InitOnceFieldHandler(null));
        }
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
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CityCode");
            keyArea.addKeyField(CITY_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(TO_CITY_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(AIRLINE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(FLIGHT_NO, DBConstants.ASCENDING);
            keyArea.addKeyField(END_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(DAYS, DBConstants.ASCENDING);
            keyArea.addKeyField(SEGMENT, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.getField(Air.AIRLINE_ID).addListener(new SetFlightDescHandler(null));
        this.getField(Air.FLIGHT_NO).addListener(new SetFlightDescHandler(null));
        this.getField(Air.CITY_CODE).addListener(new SetFlightDescHandler(null));
        this.getField(Air.TO_CITY_CODE).addListener(new SetFlightDescHandler(null));
        this.getField(Air.ETD).addListener(new SetFlightDescHandler(null));
        this.getField(Air.ARRIVE_TIME).addListener(new SetFlightDescHandler(null));
        this.getField(Air.ADD_DAYS).addListener(new SetFlightDescHandler(null));
        
        this.getField(Air.AIRLINE_ID).addListener(new SetFlightCodeHandler(null));
        this.getField(Air.FLIGHT_NO).addListener(new SetFlightCodeHandler(null));
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
        
        this.getField(Air.PP_COST).setValue(dPPCost);
        this.getField(Air.PP_PRICE_LOCAL).setValue(dPPPriceLocal);
        
        double dAirCost = dPPCost;
        
        double dTotalLocalCost = Math.floor(dAirCost * sTargetPax * 100.00 + 0.5) / 100.00;
        double dTotalLocalPrice = Math.floor(dPPPriceLocal * sTargetPax * 100.00 + 0.5) / 100.00;
        
        this.getField(Air.CLASS_ID).setValue(iAirClassID);
        
        this.getField(Air.PP_COST).setValue(dAirCost);
        this.getField(Product.PRODUCT_COST).setValue(dTotalLocalCost);
        this.getField(Product.PRODUCT_PRICE_LOCAL).setValue(dTotalLocalPrice);
        
        responseProductMessageData.setPPCost(this.getField(Air.PP_COST).getValue());
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
        this.getField(Product.DISPLAY_COST_STATUS_ID).setValue(iStatus);
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
                dCost += recProductCostLookup.getCost(AirPricing.COST, this.getProductTerms());
            else
                dCost += recProductCostLookup.getField(AirPricing.PRICE).getValue();
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
        Date dateTarget = ((DateTimeField)recBookingDetail.getField(BookingDetail.DETAIL_DATE)).getDateTime();
        short sTargetPax = recBookingDetail.getNoPax();
        int iRateID = (int)recBookingDetail.getField(BookingDetail.RATE_ID).getValue();
        int iClassID = (int)recBookingDetail.getField(BookingDetail.CLASS_ID).getValue();
        AirPricing recProductPricing = ((AirPricing)this.getProductPricing()).getAirCost(this, dateTarget, iRateID, iClassID);
        if (recProductPricing != null)
        {
            int iPricingType = PricingType.COMPONENT_PRICING;
            int iPaxCategory = PaxCategory.ALL_ID;
            int iQuantity = sTargetPax;
            double dAmount = recProductPricing.getField(ProductPricing.PRICE).getValue();
            boolean bCommissionable = recProductPricing.getField(ProductPricing.COMMISSIONABLE).getState();
            double dCommissionRate = recProductPricing.getField(ProductPricing.COMMISSION_RATE).getValue();
            String strPayAt = recProductPricing.getField(ProductPricing.PAY_AT).toString();
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
