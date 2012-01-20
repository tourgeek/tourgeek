/**
 * @(#)Land.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.land.screen.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.land.request.*;
import com.tourapp.tour.booking.inventory.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.land.request.data.*;
import com.tourapp.tour.message.land.response.*;
import com.tourapp.tour.message.land.response.data.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.main.db.base.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.model.tour.product.land.db.*;

/**
 *  Land - Land.
 */
public class Land extends Product
     implements LandModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kDescription = kDescription;
    //public static final int kCode = kCode;
    //public static final int kVendorID = kVendorID;
    //public static final int kOperatorsCode = kOperatorsCode;
    //public static final int kProductChainID = kProductChainID;
    //public static final int kCityID = kCityID;
    //public static final int kEtd = kEtd;
    //public static final int kComments = kComments;
    //public static final int kItineraryDesc = kItineraryDesc;
    //public static final int kDescSort = kDescSort;
    //public static final int kClassID = kClassID;
    public static final int kType = kProductLastField + 1;
    public static final int kManualFile = kType + 1;
    public static final int kHours = kManualFile + 1;
    public static final int kDays = kHours + 1;
    public static final int kNights = kDays + 1;
    public static final int kBreakfasts = kNights + 1;
    public static final int kLunches = kBreakfasts + 1;
    public static final int kDinners = kLunches + 1;
    public static final int kDaysOfWeek = kDinners + 1;
    public static final int kVehicle = kDaysOfWeek + 1;
    public static final int kPMCCost = kVehicle + 1;
    public static final int kPMCCostHome = kPMCCost + 1;
    public static final int kSICCost = kPMCCostHome + 1;
    public static final int kSICCostHome = kSICCost + 1;
    public static final int kPMCPriceHome = kSICCostHome + 1;
    public static final int kSICPriceHome = kPMCPriceHome + 1;
    public static final int kLandLastField = kSICPriceHome;
    public static final int kLandFields = kSICPriceHome - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kDescSortKey = kCodeKey + 1;
    public static final int kVendorIDKey = kDescSortKey + 1;
    public static final int kCityIDKey = kVendorIDKey + 1;
    public static final int kLandLastKey = kCityIDKey;
    public static final int kLandKeys = kCityIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kLandFile = "Land";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kLandFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
            screen = new LandPricingGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_GRID_SCREEN) == Product.INVENTORY_GRID_SCREEN)
            screen = new LandInventoryGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_SCREEN) == Product.INVENTORY_SCREEN)
            screen = new LandInventoryScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.RANGE_ADJUST_SCREEN) == Product.RANGE_ADJUST_SCREEN)
            screen = new LandInventoryRangeAdjust(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new LandScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = new LandGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kDescription)
            field = new ProductDesc(this, "Description", 50, null, null);
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 10, null, null);
        //if (iFieldSeq == kVendorID)
        //{
        //  field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kOperatorsCode)
        //  field = new StringField(this, "OperatorsCode", 20, null, null);
        if (iFieldSeq == kProductChainID)
            field = new LandChainField(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityID)
            field = new CityField(this, "CityID", 3, null, null);
        if (iFieldSeq == kEtd)
        {
            field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kComments)
        //  field = new MemoField(this, "Comments", 32767, null, null);
        //if (iFieldSeq == kItineraryDesc)
        //  field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDescSort)
        //  field = new ProductDescSort(this, "DescSort", 10, null, null);
        if (iFieldSeq == kType)
            field = new LandTypeField(this, "Type", 1, null, "S");
        if (iFieldSeq == kManualFile)
        {
            field = new StringField(this, "ManualFile", 30, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kHours)
            field = new FloatField(this, "Hours", 5, null, null);
        if (iFieldSeq == kDays)
        {
            field = new ShortField(this, "Days", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kNights)
        {
            field = new ShortField(this, "Nights", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBreakfasts)
        {
            field = new ShortField(this, "Breakfasts", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLunches)
        {
            field = new ShortField(this, "Lunches", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDinners)
        {
            field = new ShortField(this, "Dinners", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDaysOfWeek)
        {
            field = new DaysOfWeekField(this, "DaysOfWeek", 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVehicle)
        {
            field = new StringField(this, "Vehicle", 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPMCCost)
        {
            field = new FullCurrencyField(this, "PMCCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPMCCostHome)
        {
            field = new CurrencyField(this, "PMCCostHome", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kSICCost)
        {
            field = new FullCurrencyField(this, "SICCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kSICCostHome)
        {
            field = new CurrencyField(this, "SICCostHome", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kClassID)
        {
            field = new LandClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPMCPriceHome)
        {
            field = new CurrencyField(this, "PMCPriceHome", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kSICPriceHome)
        {
            field = new CurrencyField(this, "SICPriceHome", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLandLastField)
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
            keyArea.addKeyField(kType, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescSort, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kLandLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kLandLastKey)
                keyArea = new EmptyKey(this);
        }
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
        
        ReferenceField fldLandClass = (ReferenceField)this.getProductPricing().getField(LandPricing.kClassID);
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
                    double dCost = ((LandPricing)this.getProductPricing()).getCost(LandPricing.kCost, m_recProductTerms);
                    double dPrice = ((LandPricing)this.getProductPricing()).getField(LandPricing.kPrice).getValue();
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
                this.getField(Land.kPMCCost).setValue(dPMCCostPP);
                this.getField(Land.kPMCPriceHome).setValue(dPMCPricePP);
        
                dCostPP = 0.00;
                dPricePP = 0.00;
                ((LandPricingFilter)listener).setSicPmc(iSIC);
                this.getProductPricing().close();
                while (this.getProductPricing().hasNext())
                {   // Loop until found or not
                    this.getProductPricing().next();
                    int iQuantity = ((LandPricing)this.getProductPricing()).getQuantity(this.getTask(), sTargetPax, productMessageData, mapSurvey);
                    double dCost = ((LandPricing)this.getProductPricing()).getCost(LandPricing.kCost, m_recProductTerms);
                    double dPrice = ((LandPricing)this.getProductPricing()).getField(LandPricing.kPrice).getValue();
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
                this.getField(Land.kSICCost).setValue(dSICCostPP);
                this.getField(Land.kSICPriceHome).setValue(dSICPricePP);
            
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
            
                this.getField(Land.kClassID).setValue(iSicPmc);
            
                this.getField(Land.kPPCost).setValue(dLandCost);
                this.getField(Land.kPPPriceLocal).setValue(dLandPriceLocal);
                this.getField(Product.kProductCost).setValue(dTotalLocalCost);
                this.getField(Product.kProductPriceLocal).setValue(dTotalLocalPrice);
            
                responseMessageData.setPMCCost(this.getField(Land.kPMCCost).getValue());
                responseMessageData.setSICCost(this.getField(Land.kSICCost).getValue());
                responseMessageData.setPPCost(this.getField(Land.kPPCost).getValue());
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
        this.getField(Product.kDisplayCostStatusID).setValue(iCostStatus);
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
    public BookingDetail getBookingDetail(RecordOwner recordOwner)
    {
        return new BookingLand(recordOwner);
    }
    /**
     * Get the est. time of this product.
     * @return The duration of this product (in seconds).
     */
    public long getLengthTime()
    {
        double dDays = this.getField(Land.kDays).getValue();
        if (dDays != 0)
            return (long)(dDays * DBConstants.KMS_IN_A_DAY);
        double dHours = this.getField(kHours).getValue();
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
    public int updateBookingPricing(BookingLine recBookingLine, BookingDetail recBookingDetail, int iChangeType)
    {
        int iErrorCode = DBConstants.ERROR_RETURN;
        
        Date dateTarget = ((DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate)).getDateTime();
        short sTargetPax = recBookingDetail.getNoPax();
        int iPMC = (int)recBookingDetail.getField(BookingDetail.kClassID).getValue();
        
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
                    int iPaxCategory = (int)this.getProductPricing().getField(ProductPricing.kPaxCategoryID).getValue();
                    double dAmount = this.getProductPricing().getField(ProductPricing.kPrice).getValue();
                    boolean bCommissionable = this.getProductPricing().getField(ProductPricing.kCommissionable).getState();
                    double dCommissionRate = this.getProductPricing().getField(ProductPricing.kCommissionRate).getValue();
                    String strPayAt = this.getProductPricing().getField(ProductPricing.kPayAt).toString();
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
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.kPMCPriceHome).getValue() == 0))
                this.getField(Land.kPMCPriceHome).setData(null);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.kSICPriceHome).getValue() == 0))
                this.getField(Land.kSICPriceHome).setData(null);
        }
        else
        {
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.kPMCPriceHome).getValue() == 0))
                this.getField(Land.kPMCPriceHome).setValue(Math.floor(this.getField(kPMCCostHome).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Land.kSICPriceHome).getValue() == 0))
                this.getField(Land.kSICPriceHome).setValue(Math.floor(this.getField(kSICCostHome).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
        }
    }

}
