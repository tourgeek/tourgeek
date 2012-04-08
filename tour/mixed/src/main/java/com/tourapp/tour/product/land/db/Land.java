/**
 * @(#)Land.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.land.db;

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
import com.tourapp.tour.product.base.db.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourapp.tour.message.land.request.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.land.request.data.*;
import com.tourapp.tour.message.land.response.*;
import com.tourapp.tour.message.land.response.data.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.main.db.base.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.inventory.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.model.tour.product.land.db.*;

/**
 *  Land - Land.
 */
public class Land extends Product
     implements LandModel
{
    private static final long serialVersionUID = 1L;

    public static final String SIC_PMC_PARAM = SearchConstants.SIC_PMC;
    /**
     * Default constructor.
     */
    public Land()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Land(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(LAND_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Land";
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
            screen = Record.makeNewScreen(LandPricing.LAND_PRICING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.INVENTORY_GRID_SCREEN) == Product.INVENTORY_GRID_SCREEN)
            screen = Record.makeNewScreen(LandInventoryModel.LAND_INVENTORY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.INVENTORY_SCREEN) == Product.INVENTORY_SCREEN)
            screen = Record.makeNewScreen(LandInventoryModel.LAND_INVENTORY_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.RANGE_ADJUST_SCREEN) == Product.RANGE_ADJUST_SCREEN)
            screen = Record.makeNewScreen(LandInventoryModel.LAND_INVENTORY_RANGE_ADJUST_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(LAND_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(LAND_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == 3)
            field = new ProductDesc(this, DESCRIPTION, 50, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, CODE, 10, null, null);
        //if (iFieldSeq == 5)
        //{
        //  field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 6)
        //  field = new StringField(this, OPERATORS_CODE, 20, null, null);
        if (iFieldSeq == 7)
            field = new LandChainField(this, PRODUCT_CHAIN_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new LandClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new LandTypeField(this, TYPE, 1, null, "S");
        if (iFieldSeq == 32)
        {
            field = new StringField(this, MANUAL_FILE, 30, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 33)
            field = new FloatField(this, HOURS, 5, null, null);
        if (iFieldSeq == 34)
        {
            field = new ShortField(this, DAYS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 35)
        {
            field = new ShortField(this, NIGHTS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 36)
        {
            field = new ShortField(this, BREAKFASTS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 37)
        {
            field = new ShortField(this, LUNCHES, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 38)
        {
            field = new ShortField(this, DINNERS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
        {
            field = new DaysOfWeekField(this, DAYS_OF_WEEK, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 40)
        {
            field = new StringField(this, VEHICLE, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 41)
        {
            field = new FullCurrencyField(this, PMC_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 42)
        {
            field = new CurrencyField(this, PMC_COST_HOME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 43)
        {
            field = new FullCurrencyField(this, SIC_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 44)
        {
            field = new CurrencyField(this, SIC_COST_HOME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 45)
        {
            field = new CurrencyField(this, PMC_PRICE_HOME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 46)
        {
            field = new CurrencyField(this, SIC_PRICE_HOME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DescSort");
            keyArea.addKeyField(DESC_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "VendorID");
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DESC_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CityID");
            keyArea.addKeyField(CITY_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TYPE, DBConstants.ASCENDING);
            keyArea.addKeyField(DESC_SORT, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Read the locally stored product cost (Override).
     */
    public BaseMessage processCostRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        String DATE_REQUIRED = "Date required";
        String PAX_REQUIRED = "Passengers required";
        int iCostStatus = BaseStatus.VALID;
        double dTotalLocalCost = 0.00;
        String strErrorMessage = DBConstants.BLANK;
        String strPMCErrorMessage = DBConstants.BLANK;
        String strSICErrorMessage = DBConstants.BLANK;
        
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        LandMessageData productMessageData = (LandMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        Date dateTarget = productMessageData.getTargetDate();
        if (dateTarget == null)
            strErrorMessage = this.getTask().getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(DATE_REQUIRED);
        short sTargetPax = passengerMessageData.getTargetPax();
        if (sTargetPax <= 0)
            strErrorMessage = this.getTask().getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(PAX_REQUIRED);
        int iSicPmc = productMessageData.getRateClassID();
        
        ReferenceField fldLandClass = (ReferenceField)this.getProductPricing().getField(LandPricing.CLASS_ID);
        int iPMC = fldLandClass.getIDFromCode(LandClass.PRIVATE_VEHICLE_CODE);
        int iSIC = fldLandClass.getIDFromCode(LandClass.SEAT_IN_COACH_CODE);
        LandRateResponse responseMessage = null;
        if (messageReply == null)
        {
            messageReply = new TreeMessage(null, null);
            responseMessage =  new LandRateResponse(messageReply, null);
        }
        else
            responseMessage = (LandRateResponse)messageReply.getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        LandRateResponseMessageData responseMessageData = (LandRateResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
        
        Map<String,Object> mapSurvey = new HashMap<String, Object>();
        
        if ((strErrorMessage == null) || (strErrorMessage.length() == 0))
        {
            FileListener listener = ((LandPricing)this.getProductPricing()).setupLandFilter(this, dateTarget, sTargetPax, iPMC);
            this.getProductPricing().addListener(listener);
            double dCostPP = 0.00;
            double dPricePP = 0.00;
            
            try   {
                this.getProductPricing().close();
                while (this.getProductPricing().hasNext())
                {   // Loop until found or not
                    this.getProductPricing().next();
                    int iQuantity = ((LandPricing)this.getProductPricing()).getQuantity(this.getTask(), sTargetPax, productMessageData, mapSurvey);
                    double dCost = ((LandPricing)this.getProductPricing()).getCost(LandPricing.COST, m_recProductTerms);
                    double dPrice = ((LandPricing)this.getProductPricing()).getField(LandPricing.PRICE).getValue();
                    if (strPMCErrorMessage.length() == 0)
                    {
                        dCostPP += dCost * iQuantity / sTargetPax;
                        dPricePP += dPrice * iQuantity / sTargetPax;
                    }
                    if (iQuantity < 0)
                    { // Error
                        strPMCErrorMessage = this.getTask().getLastError(iQuantity);
                        dCostPP = 0;
                        dPricePP = 0;
                    }
                }
                double dPMCCostPP = dCostPP;
                double dPMCPricePP = dPricePP;
                this.getField(Land.PMC_COST).setValue(dPMCCostPP);
                this.getField(Land.PMC_PRICE_HOME).setValue(dPMCPricePP);
        
                dCostPP = 0.00;
                dPricePP = 0.00;
                ((LandPricingFilter)listener).setSicPmc(iSIC);
                this.getProductPricing().close();
                while (this.getProductPricing().hasNext())
                {   // Loop until found or not
                    this.getProductPricing().next();
                    int iQuantity = ((LandPricing)this.getProductPricing()).getQuantity(this.getTask(), sTargetPax, productMessageData, mapSurvey);
                    double dCost = ((LandPricing)this.getProductPricing()).getCost(LandPricing.COST, m_recProductTerms);
                    double dPrice = ((LandPricing)this.getProductPricing()).getField(LandPricing.PRICE).getValue();
                    if (strSICErrorMessage.length() == 0)
                    {
                        dCostPP += dCost * iQuantity / sTargetPax;
                        dPricePP += dPrice * iQuantity / sTargetPax;
                    }
                    if (iQuantity < 0)
                    { // Error
                        strSICErrorMessage = this.getTask().getLastError(iQuantity);
                        dCostPP = 0;
                        dPricePP = 0;
                    }
                }
                double dSICCostPP = dCostPP;
                double dSICPricePP = dPricePP;
                this.getField(Land.SIC_COST).setValue(dSICCostPP);
                this.getField(Land.SIC_PRICE_HOME).setValue(dSICPricePP);
            
                if (dSICCostPP == 0)
                    if (dPMCCostPP > 0)
                        iSicPmc = iPMC;
                if (dPMCCostPP == 0)
                    if (dSICCostPP > 0)
                        iSicPmc = iSIC;
                if (iSicPmc <= 0)
                {   // Pick the cheapest
                    if (dSICCostPP > dPMCCostPP)
                        iSicPmc = iPMC;
                    else
                        iSicPmc = iSIC;
                }
                if ((dSICCostPP == 0) && (dPMCCostPP == 0))
                    iSicPmc = 0;
            
                double dLandCost = 0;
                double dLandPriceLocal = 0;
                if (iSicPmc == iPMC)
                {
                    dLandCost = dPMCCostPP;
                    dLandPriceLocal = dPMCPricePP;
                }
                else if (iSicPmc == iSIC)
                {
                    dLandCost = dSICCostPP;
                    dLandPriceLocal = dSICPricePP;
                }
            
                dTotalLocalCost = Math.floor(dLandCost * sTargetPax * 100.00 + 0.5) / 100.00;
                double dTotalLocalPrice = Math.floor(dLandPriceLocal * sTargetPax * 100.00 + 0.5) / 100.00;
            
                this.getField(Land.CLASS_ID).setValue(iSicPmc);
            
                this.getField(Land.PP_COST).setValue(dLandCost);
                this.getField(Land.PP_PRICE_LOCAL).setValue(dLandPriceLocal);
                this.getField(Product.PRODUCT_COST).setValue(dTotalLocalCost);
                this.getField(Product.PRODUCT_PRICE_LOCAL).setValue(dTotalLocalPrice);
            
                responseMessageData.setPMCCost(this.getField(Land.PMC_COST).getValue());
                responseMessageData.setSICCost(this.getField(Land.SIC_COST).getValue());
                responseMessageData.setPPCost(this.getField(Land.PP_COST).getValue());
                if (iSicPmc != productMessageData.getRateClassID())
                {
                    responseMessageData.setRateClassID(productMessageData.getRateClassID());
                    responseMessageData.setNewRateClassID(iSicPmc);
                }
                responseMessageData.setProductCost(dTotalLocalCost);
            } catch (DBException e)   {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                this.getProductPricing().removeListener(listener, true);
            }
        }
        if (dTotalLocalCost == 0)
            iCostStatus = BaseStatus.NOT_VALID;
        responseMessage.setMessageDataStatus(iCostStatus);
        this.getField(Product.DISPLAY_COST_STATUS_ID).setValue(iCostStatus);
        if (iCostStatus != BaseStatus.VALID)
        {
            responseMessage.setMessageDataError(strErrorMessage);
            if (strPMCErrorMessage.length() > 0)
                responseMessage.setMessageDataError(strPMCErrorMessage);
            if (strSICErrorMessage.length() > 0)
                responseMessage.setMessageDataError(strSICErrorMessage);
            if ((responseMessage.getMessageDataError() == null)
                || (responseMessage.getMessageDataError().length() == 0))
                responseMessage.setMessageDataError(this.getTask().getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(COST_NOT_FOUND_MSG));
            for (String key : mapSurvey.keySet())
            {
                responseMessage.put(key, mapSurvey.get(key).toString());
            }
        }
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
            return new LandBookingResponse(message, strKey);
        else if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(strRequestType))
            return new LandBookingChangeResponse(message, strKey);
        else
            return super.getProductBookingResponse(strRequestType, message, strKey);
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetailModel getBookingDetail(RecordOwner recordOwner)
    {
        return (BookingDetailModel)Record.makeRecordFromClassName(BookingLandModel.THICK_CLASS, recordOwner);
    }
    /**
     * Get the est. time of this product.
     * @return The duration of this product (in seconds).
     */
    public long getLengthTime()
    {
        double dDays = this.getField(Land.DAYS).getValue();
        if (dDays != 0)
            return (long)(dDays * DBConstants.KMS_IN_A_DAY);
        double dHours = this.getField(Land.HOURS).getValue();
        return (long)(dHours * 60 * 60 * 1000); // Milliseconds
    }
    /**
     * CreateProductPricing Method.
     */
    public ProductPricing createProductPricing(RecordOwner recordOwner)
    {
        return new LandPricing(recordOwner);
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
        int iErrorCode = DBConstants.ERROR_RETURN;
        
        Date dateTarget = ((DateTimeField)recBookingDetail.getField(BookingDetailModel.DETAIL_DATE)).getDateTime();
        short sTargetPax = recBookingDetail.getNoPax();
        int iPMC = (int)recBookingDetail.getField(BookingDetailModel.CLASS_ID).getValue();
        
        String strPrefix = LAND_COST_PROPERTIES;
        TrxMessageHeader messageHeader = new TrxMessageHeader(null, null);
        BaseMessage message = new TreeMessage(messageHeader, null);
        LandRateRequest landRateRequest = new LandRateRequest(message, null);
        landRateRequest.handleGetRawRecordData(recBookingDetail);
        recBookingDetail.addMessageProperties(strPrefix, false, messageHeader, message, null);
        
        FileListener listener = ((LandPricing)this.getProductPricing()).setupLandFilter(this, dateTarget, sTargetPax, iPMC);
        this.getProductPricing().addListener(listener);
        
        Map<String,Object> mapSurvey = messageHeader.getProperties();
        LandMessageData productMessageData = (LandMessageData)landRateRequest.getMessageDataDesc(LandRateRequest.PRODUCT_MESSAGE);
        
        try   {
            this.getProductPricing().close();
            while (this.getProductPricing().hasNext())
            {   // Loop until found or not
                this.getProductPricing().next();
                int iQuantity = ((LandPricing)this.getProductPricing()).getQuantity(this.getTask(), sTargetPax, productMessageData, mapSurvey);
                if (iQuantity != 0)
                {
                    int iPricingType = PricingType.COMPONENT_PRICING;
                    int iPaxCategory = (int)this.getProductPricing().getField(ProductPricing.PAX_CATEGORY_ID).getValue();
                    double dAmount = this.getProductPricing().getField(ProductPricing.PRICE).getValue();
                    boolean bCommissionable = this.getProductPricing().getField(ProductPricing.COMMISSIONABLE).getState();
                    double dCommissionRate = this.getProductPricing().getField(ProductPricing.COMMISSION_RATE).getValue();
                    String strPayAt = this.getProductPricing().getField(ProductPricing.PAY_AT).toString();
                    iErrorCode = recBookingDetail.updateBookingLine(recBookingLine, iPricingType, iPaxCategory, iQuantity, dAmount, bCommissionable, dCommissionRate, strPayAt, PricingStatus.OKAY, iChangeType);
                }
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        }
        
        return iErrorCode;
    }
    /**
     * MarkupPriceFromCost Method.
     */
    public void markupPriceFromCost(double dMarkup, boolean bMarkupOnlyIfNoPrice)
    {
        super.markupPriceFromCost(dMarkup, bMarkupOnlyIfNoPrice);
        if (dMarkup == 0.00)
        {
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.PMC_PRICE_HOME).getValue() == 0))
                this.getField(Land.PMC_PRICE_HOME).setData(null);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.SIC_PRICE_HOME).getValue() == 0))
                this.getField(Land.SIC_PRICE_HOME).setData(null);
        }
        else
        {
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.PMC_PRICE_HOME).getValue() == 0))
                this.getField(Land.PMC_PRICE_HOME).setValue(Math.floor(this.getField(Land.PMC_COST_HOME).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.SIC_PRICE_HOME).getValue() == 0))
                this.getField(Land.SIC_PRICE_HOME).setValue(Math.floor(this.getField(Land.SIC_COST_HOME).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
        }
    }

}
