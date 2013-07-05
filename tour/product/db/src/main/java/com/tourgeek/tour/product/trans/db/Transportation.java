/**
  * @(#)Transportation.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.trans.db;

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
import com.tourgeek.tour.product.base.db.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.trans.request.data.*;
import com.tourgeek.tour.message.base.response.data.*;
import com.tourgeek.tour.message.trans.response.*;
import com.tourgeek.tour.message.base.request.data.*;
import org.jbundle.main.db.base.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.booking.inventory.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.model.tour.product.trans.db.*;

/**
 *  Transportation - Transportation.
 */
public class Transportation extends TransportProduct
     implements TransportationModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Transportation()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Transportation(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(TRANSPORTATION_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transportation";
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
            screen = Record.makeNewScreen(TransportationPricing.TRANSPORTATION_PRICING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.INVENTORY_GRID_SCREEN) == Product.INVENTORY_GRID_SCREEN)
            screen = Record.makeNewScreen(TransportationInventoryModel.TRANSPORTATION_INVENTORY_GRID_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.INVENTORY_SCREEN) == Product.INVENTORY_SCREEN)
            screen = Record.makeNewScreen(TransportationInventoryModel.TRANSPORTATION_INVENTORY_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.RANGE_ADJUST_SCREEN) == Product.RANGE_ADJUST_SCREEN)
            screen = Record.makeNewScreen(TRANSPORTATION_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TRANSPORTATION_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TRANSPORTATION_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 8, null, null);
            field.setHidden(true);
        }
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
            field = new ProductDesc(this, DESCRIPTION, 30, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, CODE, 10, null, null);
        //if (iFieldSeq == 5)
        //{
        //  field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 6)
        {
            field = new StringField(this, OPERATORS_CODE, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
            field = new TransportationChainField(this, PRODUCT_CHAIN_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new CityField(this, CITY_ID, 3, null, null);
        if (iFieldSeq == 9)
        {
            field = new TimeField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 10)
        //  field = new ShortField(this, ACK_DAYS, 2, null, null);
        //if (iFieldSeq == 11)
        //  field = new MemoField(this, COMMENTS, 32767, null, null);
        //if (iFieldSeq == 12)
        //  field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 13)
        //  field = new XmlField(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new ProductDescSort(this, DESC_SORT, 10, null, null);
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
            field = new TransportationClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        //if (iFieldSeq == 31)
        //{
        //  field = new StringField(this, CITY_CODE, 3, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 32)
        //  field = new CityField(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 33)
        //{
        //  field = new StringField(this, TO_CITY_CODE, 3, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 34)
            field = new TransportationField(this, TRANS_REVERSE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 35)
        {
            field = new StringField(this, MANUAL_FILE, 30, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 36)
        {
            field = new StringField(this, FREQUENCY, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 37)
        {
            field = new StringField(this, DISTANCE, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 38)
        {
            field = new ShortField(this, HOURS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
        {
            field = new ShortField(this, BREAKFASTS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 40)
        {
            field = new ShortField(this, LUNCHES, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 41)
        {
            field = new ShortField(this, DINNERS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 42)
        {
            field = new DaysOfWeekField(this, DAYS_OF_WEEK, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 43)
            field = new DateField(this, DISCONTINUED_ON, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, CODE_KEY);
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, DESC_SORT_KEY);
            keyArea.addKeyField(DESC_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, VENDOR_ID_KEY);
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DESC_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, CITY_ID_KEY);
            keyArea.addKeyField(CITY_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DESC_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, CITY_CODE_KEY);
            keyArea.addKeyField(CITY_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(TO_CITY_CODE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Read the locally stored product cost (Override).
     */
    public Message processCostRequestInMessage(Message messageIn, Message messageReply)
    {
        ProductRequest productRequest = (ProductRequest)((BaseMessage)messageIn).getMessageDataDesc(null);
        TransportationMessageData productMessageData = (TransportationMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData  = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        Date dateTarget = productMessageData.getTargetDate();
        short sTargetPax = passengerMessageData.getTargetPax();
        int iTransportationClassID = productMessageData.getRateClassID();
        
        TransportationRateResponse responseMessage = null;
        if (messageReply == null)
        {
            messageReply = new TreeMessage(null, null);
            responseMessage =  new TransportationRateResponse((BaseMessage)messageReply, null);
        }
        else
            responseMessage = (TransportationRateResponse)((BaseMessage)messageReply).getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        ProductRateResponseMessageData responseProductMessageData = (ProductRateResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
        
        double dPPCost = this.getTransportationCost(dateTarget, sTargetPax, iTransportationClassID, false);
        double dPPPriceLocal = this.getTransportationCost(dateTarget, sTargetPax, iTransportationClassID, true);
        
        this.getField(Transportation.PP_COST).setValue(dPPCost);
        this.getField(Transportation.PP_PRICE_LOCAL).setValue(dPPPriceLocal);
        
        double dTransportationCost = dPPCost;
        
        double dTotalLocalCost = Math.floor(dTransportationCost * sTargetPax * 100.00 + 0.5) / 100.00;
        double dTotalLocalPrice = Math.floor(dPPPriceLocal * sTargetPax * 100.00 + 0.5) / 100.00;
        
        this.getField(Transportation.CLASS_ID).setValue(iTransportationClassID);
        
        this.getField(Transportation.PP_COST).setValue(dTransportationCost);
        this.getField(Product.PRODUCT_COST).setValue(dTotalLocalCost);
        this.getField(Product.PRODUCT_PRICE_LOCAL).setValue(dTotalLocalPrice);
        
        responseProductMessageData.setPPCost(this.getField(Transportation.PP_COST).getValue());
        if (iTransportationClassID != productMessageData.getRateClassID())
        {
            responseProductMessageData.setRateClassID(productMessageData.getRateClassID());
            responseProductMessageData.setNewRateClassID(iTransportationClassID);
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
    public Message processAvailabilityRequestInMessage(Message messageIn, Message messageReply, Field fldTrxID)
    {
        return super.processAvailabilityRequestInMessage(messageIn, messageReply, fldTrxID);
    }
    /**
     * This is for products that can be externally booked.
     * @return the booking reply message with the proper params.
     */
    public Message processBookingRequestInMessage(Message messageIn, Message messageReply)
    {
        return super.processBookingRequestInMessage(messageIn, messageReply);
    }
    /**
     * GetProductBookingResponse Method.
     */
    public ProductBookingResponse getProductBookingResponse(String strRequestType, Message message, String strKey)
    {
        if (RequestType.BOOKING_ADD.equalsIgnoreCase(strRequestType))
            return new TransportationBookingResponse((BaseMessage)message, strKey);
        else if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(strRequestType))
            return new TransportationBookingChangeResponse((BaseMessage)message, strKey);
        else
            return super.getProductBookingResponse(strRequestType, message, strKey);
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetailModel getBookingDetail(RecordOwner recordOwner)
    {
        return (BookingDetailModel)Record.makeRecordFromClassName(BookingTransportationModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetTransportationCost Method.
     */
    public double getTransportationCost(Date dateTarget, short sTargetPax, int iTransportationClassID, boolean bGetPrice)
    {
        double dCost = 0;
        
        TransportationPricing recProductCostLookup = ((TransportationPricing)this.getProductPricing()).getTransportationCost(this, dateTarget, sTargetPax, iTransportationClassID);
        if (recProductCostLookup != null)
        {
            if (!bGetPrice)
                dCost += recProductCostLookup.getCost(TransportationPricing.COST, this.getProductTerms());
            else
                dCost += recProductCostLookup.getField(TransportationPricing.PRICE).getValue();
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
    public int updateBookingPricing(BookingLineModel recBookingLine, BookingDetailModel recBookingDetail, int iChangeType)
    {
        Date dateTarget = ((DateTimeField)recBookingDetail.getField(BookingDetailModel.DETAIL_DATE)).getDateTime();
        short sTargetPax = recBookingDetail.getNoPax();
        int iClassID = (int)recBookingDetail.getField(BookingDetailModel.CLASS_ID).getValue();
        int iRateID = (int)recBookingDetail.getField(BookingDetailModel.RATE_ID).getValue();
        TransportationPricing recProductPricing = ((TransportationPricing)this.getProductPricing()).getTransportationCost(this, dateTarget, sTargetPax, iClassID);
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
        return new TransportationPricing(recordOwner);
    }

}
