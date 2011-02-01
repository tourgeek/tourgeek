/**
 *  @(#)Hotel.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.db;

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
import com.tourapp.tour.product.hotel.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.base.db.*;
import java.util.*;
import com.tourapp.tour.acctpay.db.*;
import java.text.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.booking.inventory.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.hotel.request.data.*;
import com.tourapp.tour.message.hotel.response.data.*;
import com.tourapp.tour.message.hotel.response.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.data.*;

/**
 *  Hotel - Hotel.
 */
public class Hotel extends Product
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
    public static final int kCheckOut = kProductLastField + 1;
    public static final int kSameAsVendor = kCheckOut + 1;
    public static final int kContact = kSameAsVendor + 1;
    public static final int kContactTitle = kContact + 1;
    public static final int kAddressLine1 = kContactTitle + 1;
    public static final int kAddressLine2 = kAddressLine1 + 1;
    public static final int kCityOrTown = kAddressLine2 + 1;
    public static final int kStateOrRegion = kCityOrTown + 1;
    public static final int kPostalCode = kStateOrRegion + 1;
    public static final int kCountry = kPostalCode + 1;
    public static final int kTel = kCountry + 1;
    public static final int kFax = kTel + 1;
    public static final int kEmail = kFax + 1;
    public static final int kRooms = kEmail + 1;
    public static final int kGeneralManager = kRooms + 1;
    public static final int kSalesManager = kGeneralManager + 1;
    public static final int kLocalContact = kSalesManager + 1;
    public static final int kLocalPhone = kLocalContact + 1;
    public static final int kTollFreePhone = kLocalPhone + 1;
    public static final int kAltPhone = kTollFreePhone + 1;
    public static final int kOneFree = kAltPhone + 1;
    public static final int kFreeType = kOneFree + 1;
    public static final int kChildAge = kFreeType + 1;
    public static final int kSingleCost = kChildAge + 1;
    public static final int kDoubleCost = kSingleCost + 1;
    public static final int kTripleCost = kDoubleCost + 1;
    public static final int kQuadCost = kTripleCost + 1;
    public static final int kRoomCost = kQuadCost + 1;
    public static final int kMealCost = kRoomCost + 1;
    public static final int kSingleCostLocal = kMealCost + 1;
    public static final int kDoubleCostLocal = kSingleCostLocal + 1;
    public static final int kTripleCostLocal = kDoubleCostLocal + 1;
    public static final int kQuadCostLocal = kTripleCostLocal + 1;
    public static final int kRoomCostLocal = kQuadCostLocal + 1;
    public static final int kMealCostLocal = kRoomCostLocal + 1;
    public static final int kSinglePriceLocal = kMealCostLocal + 1;
    public static final int kDoublePriceLocal = kSinglePriceLocal + 1;
    public static final int kTriplePriceLocal = kDoublePriceLocal + 1;
    public static final int kQuadPriceLocal = kTriplePriceLocal + 1;
    public static final int kRoomPriceLocal = kQuadPriceLocal + 1;
    public static final int kMealPriceLocal = kRoomPriceLocal + 1;
    public static final int kHotelLastField = kMealPriceLocal;
    public static final int kHotelFields = kMealPriceLocal - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kDescSortKey = kCodeKey + 1;
    public static final int kVendorIDKey = kDescSortKey + 1;
    public static final int kCityIDKey = kVendorIDKey + 1;
    public static final int kOperatorsCodeKey = kCityIDKey + 1;
    public static final int kProductChainIDKey = kOperatorsCodeKey + 1;
    public static final int kHotelLastKey = kProductChainIDKey;
    public static final int kHotelKeys = kProductChainIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String MEAL_DETAIL = "Meal";
    public static final String MEAL_PLAN_DAYS_PARAM = "mealDays";
    public static final String MEAL_PLAN_ID_PARAM = SearchConstants.MEAL_PLAN;
    public static final String MEAL_PLAN_QTY_PARAM = SearchConstants.MEAL_PLAN_QTY;
    protected HotelMealPricing m_recHotelMealPricing = null;
    public static final int MEAL_PRICING_GRID_SCREEN = ScreenConstants.LAST_MODE * 32;
    /**
     * Default constructor.
     */
    public Hotel()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Hotel(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recHotelMealPricing = null;
        super.init(screen);
    }

    public static final String kHotelFile = "Hotel";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kHotelFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if ((iDocMode & Hotel.PRICING_GRID_SCREEN) == Hotel.PRICING_GRID_SCREEN)
            screen = new HotelPricingGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Hotel.INVENTORY_GRID_SCREEN) == Hotel.INVENTORY_GRID_SCREEN)
            screen = new HotelInventoryGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Hotel.INVENTORY_SCREEN) == Hotel.INVENTORY_SCREEN)
            screen = new HotelInventoryScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Hotel.MEAL_PRICING_GRID_SCREEN) == Hotel.MEAL_PRICING_GRID_SCREEN)
            screen = new HotelMealPricingGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Hotel.RANGE_ADJUST_SCREEN) == Hotel.RANGE_ADJUST_SCREEN)
            screen = new HotelInventoryRangeAdjust(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new HotelScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = new HotelGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kVendorID)
        {
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kOperatorsCode)
        //  field = new StringField(this, "OperatorsCode", 20, null, null);
        if (iFieldSeq == kProductChainID)
            field = new HotelChainField(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityID)
        {
            field = new CityField(this, "CityID", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kEtd)
        //  field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCheckOut)
            field = new TimeField(this, "CheckOut", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kComments)
        //  field = new MemoField(this, "Comments", 32767, null, null);
        //if (iFieldSeq == kItineraryDesc)
        //  field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDescSort)
        //  field = new ProductDescSort(this, "DescSort", 10, null, null);
        if (iFieldSeq == kSameAsVendor)
        {
            field = new BooleanField(this, "SameAsVendor", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kContact)
            field = new StringField(this, "Contact", 30, null, null);
        if (iFieldSeq == kContactTitle)
            field = new StringField(this, "ContactTitle", 30, null, null);
        if (iFieldSeq == kAddressLine1)
            field = new StringField(this, "AddressLine1", 40, null, null);
        if (iFieldSeq == kAddressLine2)
            field = new StringField(this, "AddressLine2", 40, null, null);
        if (iFieldSeq == kCityOrTown)
            field = new StringField(this, "CityOrTown", 15, null, null);
        if (iFieldSeq == kStateOrRegion)
            field = new StringField(this, "StateOrRegion", 15, null, null);
        if (iFieldSeq == kPostalCode)
            field = new StringField(this, "PostalCode", 10, null, null);
        if (iFieldSeq == kCountry)
            field = new StringField(this, "Country", 15, null, null);
        if (iFieldSeq == kTel)
            field = new PhoneField(this, "Tel", 20, null, null);
        if (iFieldSeq == kFax)
            field = new FaxField(this, "Fax", 20, null, null);
        if (iFieldSeq == kEmail)
            field = new EMailField(this, "Email", 40, null, null);
        if (iFieldSeq == kRooms)
            field = new ShortField(this, "Rooms", 4, null, null);
        if (iFieldSeq == kGeneralManager)
            field = new StringField(this, "GeneralManager", 20, null, null);
        if (iFieldSeq == kSalesManager)
            field = new StringField(this, "SalesManager", 20, null, null);
        if (iFieldSeq == kLocalContact)
            field = new StringField(this, "LocalContact", 20, null, null);
        if (iFieldSeq == kLocalPhone)
            field = new PhoneField(this, "LocalPhone", 20, null, null);
        if (iFieldSeq == kTollFreePhone)
            field = new PhoneField(this, "TollFreePhone", 20, null, null);
        if (iFieldSeq == kAltPhone)
            field = new PhoneField(this, "AltPhone", 20, null, null);
        if (iFieldSeq == kOneFree)
            field = new ShortField(this, "OneFree", 2, null, new Short((short)15));
        if (iFieldSeq == kFreeType)
            field = new HotelFreeField(this, "FreeType", 1, null, "S");
        if (iFieldSeq == kChildAge)
        {
            field = new ShortField(this, "ChildAge", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSingleCost)
        {
            field = new FullCurrencyField(this, "SingleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDoubleCost)
        {
            field = new FullCurrencyField(this, "DoubleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kTripleCost)
        {
            field = new FullCurrencyField(this, "TripleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kQuadCost)
        {
            field = new FullCurrencyField(this, "QuadCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kRoomCost)
        {
            field = new FullCurrencyField(this, "RoomCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kMealCost)
        {
            field = new FullCurrencyField(this, "MealCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kSingleCostLocal)
        {
            field = new CurrencyField(this, "SingleCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDoubleCostLocal)
        {
            field = new CurrencyField(this, "DoubleCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kTripleCostLocal)
        {
            field = new CurrencyField(this, "TripleCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kQuadCostLocal)
        {
            field = new CurrencyField(this, "QuadCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
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
        if (iFieldSeq == kSinglePriceLocal)
        {
            field = new CurrencyField(this, "SinglePriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDoublePriceLocal)
        {
            field = new CurrencyField(this, "DoublePriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kTriplePriceLocal)
        {
            field = new CurrencyField(this, "TriplePriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kQuadPriceLocal)
        {
            field = new CurrencyField(this, "QuadPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kRoomPriceLocal)
        {
            field = new CurrencyField(this, "RoomPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kMealPriceLocal)
        {
            field = new CurrencyField(this, "MealPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kHotelLastField)
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
        if (iKeyArea == kOperatorsCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "OperatorsCode");
            keyArea.addKeyField(kOperatorsCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kProductChainIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductChainID");
            keyArea.addKeyField(kProductChainID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescSort, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kHotelLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kHotelLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recHotelMealPricing != null)
        {
            m_recHotelMealPricing.free();
            m_recHotelMealPricing = null;
        }
        super.free();
    }
    /**
     * Read the locally stored product cost (Override).
     */
    public BaseMessage processCostRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        String NO_ROOM_RATE = "No room rate";
        BaseApplication application = null;
        if (this.getRecordOwner() != null)
            if (this.getRecordOwner().getTask() != null)
                application = (BaseApplication)this.getRecordOwner().getTask().getApplication();
        if (application == null)
            application = (BaseApplication)BaseApplet.getSharedInstance().getApplication();
        int iCostStatus = BaseStatus.VALID;
        String strErrorMessage = DBConstants.BLANK;
        HotelMessageData productMessageData = (HotelMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData  = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        Date dateTarget = productMessageData.getTargetDate();
        int iRateType = productMessageData.getRateTypeID();
        int iRateClass = productMessageData.getRateClassID();
        short sNights = productMessageData.getNights();
        
        HotelRateResponse responseMessage = null;
        if (messageReply == null)
        {
            messageReply = new TreeMessage(null, null);
            responseMessage =  new HotelRateResponse(messageReply, null);
        }
        else
            responseMessage = (HotelRateResponse)messageReply.getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        HotelRateResponseMessageData responseMessageData = (HotelRateResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
        
        // First, calculate the room cost
        double dTotalRoomCost = 0;
        double dTotalLocalRoomPrice = 0;
        for (int iRoomCategory = PaxCategory.SINGLE_ID, iFieldSeq = Hotel.kSingleCost, iPriceFieldSeq = Hotel.kSinglePriceLocal; iRoomCategory <= PaxCategory.CHILD_ID; iRoomCategory++, iFieldSeq++, iPriceFieldSeq++)
        {
            double dRoomCost = this.getHotelCost(dateTarget, iRateType, iRateClass, (short)1, iRoomCategory, false);
            responseMessageData.setRoomCost(iRoomCategory, dRoomCost);
            dRoomCost = this.getHotelCost(dateTarget, iRateType, iRateClass, sNights, iRoomCategory, false);
            double dRoomPriceLocal = this.getHotelCost(dateTarget, iRateType, iRateClass, sNights, iRoomCategory, true);
            if (iFieldSeq <= Hotel.kQuadCost)
            {
                this.getField(iFieldSeq).setValue(dRoomCost);
                this.getField(iPriceFieldSeq).setValue(dRoomPriceLocal);
            }
            int iPaxInRoom = passengerMessageData.getPaxInRoom(iRoomCategory);
            int iRoomCapacity = iRoomCategory;
            if (iRoomCategory == PaxCategory.CHILD_ID)
                iRoomCapacity = 1;
            if ((iPaxInRoom > 0) && (dRoomCost == 0))
            {
                iCostStatus = BaseStatus.NOT_VALID;
                strErrorMessage = NO_ROOM_RATE;
                if (application != null)
                    strErrorMessage = application.getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(NO_ROOM_RATE);
            }
            dTotalRoomCost += dRoomCost * iPaxInRoom / iRoomCapacity;
            dTotalLocalRoomPrice += dRoomPriceLocal * iPaxInRoom / iRoomCapacity;
        }
        responseMessageData.setTotalRoomCost(dTotalRoomCost);
        
        short iTotalPax = passengerMessageData.getTargetPax();
        // Now, calculate the meal costs
        double dTotalMealCost = 0;
        double dTotalMealPriceLocal = 0;
        for (int iFieldSeq = 1; iFieldSeq <= 4; iFieldSeq++)
        {
            int iMealPlanID = productMessageData.getMealPlanID(iFieldSeq);
            if (iMealPlanID > 0)
            {
                int iMeals = productMessageData.getMealQuantity(iFieldSeq);
                int iMealDays = productMessageData.getMealDays(iFieldSeq);
                Date dateMeal = new Date(dateTarget.getTime());
                for (int iDay = 0; ; iDay++)
                {
                    dateMeal.setTime(dateMeal.getTime() + (iDay * DBConstants.KMS_IN_A_DAY));    // Next day
                    if (iMeals == 0)
                        break;  // All done
                    if (iMealDays == 0)
                        iMeals--;
                    else
                    {
                        if (((1 << iDay) & iMealDays) == 0)
                            iMeals--;
                        else
                            continue;
                        iMealDays = (~(1 << iDay)) & iMealDays;
                    }
                    double dMealCost = this.getMealCost(dateMeal, iMealPlanID, false);
                    double dMealPriceLocal = this.getMealCost(dateMeal, iMealPlanID, true);
                    if (dMealCost == 0)
                    {
                        iCostStatus = BaseStatus.NOT_VALID;
                        String NO_MEAL_RATE = "No meal";
                        strErrorMessage = NO_MEAL_RATE;
                        if (application != null)
                            strErrorMessage = MessageFormat.format(application.getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(NO_MEAL_RATE), iFieldSeq);
                    }
                    dTotalMealCost += dMealCost * iTotalPax;
                    dTotalMealPriceLocal += dMealPriceLocal * iTotalPax;
                }
            }
        }
        responseMessageData.setTotalMealCost(dTotalMealCost);
        
        double dTotalCost = dTotalRoomCost + dTotalMealCost;
        double dTotalPriceLocal = dTotalLocalRoomPrice + dTotalMealPriceLocal;
        dTotalCost = Math.floor(dTotalCost * 100.00 + 0.5) / 100.00;
        
        this.getField(Product.kProductCost).setValue(dTotalCost);
        this.getField(Product.kProductPriceLocal).setValue(dTotalPriceLocal);
        responseMessageData.setProductCost(dTotalCost);
        
        if (dTotalCost == 0)
        {
            iCostStatus = BaseStatus.NOT_VALID;
            strErrorMessage = NO_ROOM_RATE;
            if (application != null)
                strErrorMessage = application.getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(NO_ROOM_RATE);
        }
        this.getField(Product.kDisplayCostStatusID).setValue(iCostStatus);
        responseMessage.setMessageDataStatus(iCostStatus);
        if (iCostStatus != BaseStatus.VALID)
            responseMessage.setMessageDataError(strErrorMessage);
        return messageReply;
    }
    /**
     * Check the inventory for this detail.
     * @param message Contains all the update data for this check
     * @param fldTrxID If null, just check the inventory, if not null, update the inventory using this BookingDetail trxID.
     */
    public BaseMessage processAvailabilityRequestInMessage(BaseMessage messageIn, BaseMessage messageReply, BaseField fldTrxID)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        ProductMessageData productMessageData = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        Date dateTarget = productMessageData.getTargetDate();
        int iRateID = productMessageData.getRateTypeID();
        int iClassID = productMessageData.getRateClassID();
        Object objOtherID = productMessageData.get(Product.OTHER_ID_PARAM);
        if (objOtherID == null)
            objOtherID = Inventory.NO_OTHER;
        int iOtherID = Integer.parseInt(objOtherID.toString());
        String strErrorMessage = null;
        int iInventoryStatus = InventoryStatus.VALID;
        
        BaseProductResponse responseMessage = null;
        if (messageReply == null)
            messageReply = this.getMessageProcessInfo().createReplyMessage((BaseMessage)productRequest.getMessage());
        responseMessage = (BaseProductResponse)messageReply.getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        
        //       First, calculate the room cost
        int iAvailability = 0;
        int iRooms = 0;
        int iNights = 0;
        if (productMessageData instanceof HotelMessageData)    // Could be CancelRequest (then nights = 0)
            iNights = ((HotelMessageData)productMessageData).getNights();
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iRoomCategory = PaxCategory.SINGLE_ID;
        Set<Integer> setSurvey = this.getInventory().surveyInventory(fldTrxID); 
        for (int iFieldSeq = Hotel.kSingleCost; iRoomCategory <= PaxCategory.CHILD_ID; iRoomCategory++, iFieldSeq++)
        {
            dateTarget = productMessageData.getTargetDate();
            int iPaxInRoom = passengerMessageData.getPaxInRoom(iRoomCategory);
            if (productRequest instanceof CancelRequest)    // CancelRequest (then pax = 0)
                iPaxInRoom = 0;
            int iRoomCapacity = iRoomCategory;
            if (iRoomCategory == PaxCategory.CHILD_ID)
                iRoomCapacity = 1;
            iRooms = iPaxInRoom / iRoomCapacity;
            if (iRooms > 0)
            {
                for (int day = 0; day < iNights; day++)
                {
                    Inventory recInventory = this.getInventory().getAvailability(this, dateTarget, iRateID, iClassID, iOtherID);
                    iAvailability = Inventory.NO_INVENTORY;
                    if (recInventory != null)
                        iAvailability = (int)recInventory.getField(Inventory.kAvailable).getValue();
                    if ((recInventory != null) && (fldTrxID != null)) // If in update mode, update the availability
                    { // No need to check avail, updateAvailability checks the correct availability (taking into account previous the previous usage) 
                        boolean bIsDeleted = false;   // todo (don) Fix this.
                        iErrorCode = recInventory.updateAvailability(iRooms, fldTrxID, iRoomCategory, bIsDeleted, setSurvey);
                        if (iErrorCode != DBConstants.NORMAL_RETURN)
                            break;
                    }
                    else if (iAvailability < iRooms)
                        break;
                    else if (iAvailability == Inventory.NO_INVENTORY)
                        break;
                    DateConverter.initGlobals();
                    DateConverter.gCalendar.setTime(dateTarget);
                    DateConverter.gCalendar.add(Calendar.DATE, +1);
                    dateTarget = DateConverter.gCalendar.getTime();
                }
            }
            if ((iAvailability < iRooms) || (iAvailability == Inventory.NO_INVENTORY)
                    || (iErrorCode != DBConstants.NORMAL_RETURN))
                break;
        }
        if (fldTrxID != null) // If in update mode, fix the availability
        {
            if ((iAvailability < iRooms) || (iAvailability == Inventory.NO_INVENTORY)
                || (iErrorCode != DBConstants.NORMAL_RETURN))
            { // Have to back-out the changes and return.
                int iErrorCode2 = this.getInventory().removeInventory(fldTrxID);
                if (iErrorCode2 != DBConstants.NORMAL_RETURN)
                    iErrorCode = iErrorCode2;
                iInventoryStatus = BaseDataStatus.NOT_VALID;
                if (strErrorMessage == null)
                {
                    if ((this.getRecordOwner() != null)
                        && (this.getRecordOwner().getTask() != null))
                            strErrorMessage = this.getRecordOwner().getTask().getLastError(iErrorCode);
                    else
                        strErrorMessage = "Inventory not available";
                }
            }
            else
            {
                this.getInventory().removeTrxs(fldTrxID, setSurvey);  // Remove any transactions that are no longer used
            }
        }
        if (responseMessage instanceof ProductAvailabilityResponse)
        {
            ProductResponseMessageData responseMessageData = (ProductResponseMessageData)responseMessage.getMessageDataDesc(HotelRateResponse.PRODUCT_RESPONSE_MESSAGE);
            responseMessageData.setAvailability(iAvailability);
        }
        
        if (iAvailability < iRooms)
        {
            strErrorMessage = InventoryStatus.NO_INVENTORY_ERROR_MESSAGE;
            if (this.getRecordOwner() != null)
                if (this.getRecordOwner().getTask() != null)
            {
                strErrorMessage = this.getRecordOwner().getTask().getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(strErrorMessage);
                strErrorMessage = MessageFormat.format(strErrorMessage, iAvailability, dateTarget);
            }
            iInventoryStatus = InventoryStatus.NOT_VALID;
        }
        else if (iAvailability == Inventory.NO_INVENTORY)
        {
            strErrorMessage = InventoryStatus.LOOKUP_ERROR_MESSAGE;
            if (this.getRecordOwner() != null)
                if (this.getRecordOwner().getTask() != null)
                    strErrorMessage = this.getRecordOwner().getTask().getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(strErrorMessage);
            iInventoryStatus = InventoryStatus.ERROR;
        }
        this.getField(Product.kDisplayInventoryStatusID).setValue(iInventoryStatus);
        responseMessage.setMessageDataStatus(iInventoryStatus);
        if (strErrorMessage != null)
            responseMessage.setMessageDataError(strErrorMessage);
        return messageReply;
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
            return new HotelBookingResponse(message, strKey);
        else if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(strRequestType))
            return new HotelBookingChangeResponse(message, strKey);
        else
            return super.getProductBookingResponse(strRequestType, message, strKey);
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetail getBookingDetail(RecordOwner recordOwner)
    {
        return new BookingHotel(recordOwner);
    }
    /**
     * Calc the hotel cost given parameters.
     */
    public double getHotelCost(Date dateTarget, int iRateType, int iRateClass, short sNights, int iRoomType, boolean bGetPrice)
    {
        double dCost = 0;
        if (dateTarget == null)
            return 0;
        int iHotelID = (int)this.getField(Hotel.kID).getValue();
        while (sNights > 0)
        {
            HotelPricing recProductCostLookup = ((HotelPricing)this.getProductPricing()).getHotelCost(iHotelID, dateTarget, iRateType, iRateClass, iRoomType);
            if (recProductCostLookup != null)
            {
                if (!bGetPrice)
                    dCost += recProductCostLookup.getCost(HotelPricing.kRoomCost, this.getProductTerms());
                else
                    dCost += recProductCostLookup.getField(HotelPricing.kRoomPrice).getValue();
            }
            else
                return 0;   // No cost for this day = error
            dateTarget = new Date(dateTarget.getTime() + DBConstants.KMS_IN_A_DAY);
            sNights--;
        }
        return dCost;
    }
    /**
     * Get the cost for meals.
     */
    public double getMealCost(Date dateTarget, int iMealPlanID, boolean bGetPrice)
    {
        double dCost = 0;
        if (m_recHotelMealPricing == null)
        {
            m_recHotelMealPricing = new HotelMealPricing(Utility.getRecordOwner(this));
            if (m_recHotelMealPricing.getRecordOwner() != null)
                m_recHotelMealPricing.getRecordOwner().removeRecord(m_recHotelMealPricing);
        }
        int iHotelID = (int)this.getField(Hotel.kID).getValue();
        HotelMealPricing recProductCostLookup = ((HotelMealPricing)m_recHotelMealPricing).getMealCost(iHotelID, dateTarget, iMealPlanID);
        if (recProductCostLookup != null)
        {
            if (!bGetPrice)
                dCost += recProductCostLookup.getCost(HotelMealPricing.kCost, this.getProductTerms());
            else
                dCost += recProductCostLookup.getField(HotelMealPricing.kPrice).getValue();
        }
        return dCost;
    }
    /**
     * GetMealDesc Method.
     */
    public String getMealDesc(Date dateTarget, int iRateType, int iRateClass, boolean bDetailedDesc, MealPlan recMealPlan, ProductPricing recProductCost)
    {
        String strMealDesc = DBConstants.BLANK;
        int iHotelID = (int)this.getField(Hotel.kID).getValue();
        if (recProductCost == null)
            recProductCost = this.getProductPricing();
        recProductCost = ((HotelPricing)recProductCost).getHotelCost(iHotelID, dateTarget, iRateType, iRateClass, PaxCategory.DOUBLE_ID);
        if (recProductCost != null)
        {
            MealPlan recMealPlanNew = null;
            if (recMealPlan == null)
                recMealPlan = recMealPlanNew = new MealPlan(Utility.getRecordOwner(this));
            strMealDesc += recMealPlan.getMealDesc(recProductCost.getField(HotelPricing.kMealPlanID), bDetailedDesc);
            if (recMealPlanNew != null)
                recMealPlanNew.free();
        }
        return strMealDesc;
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
        int iErrorCode = DBConstants.ERROR_RETURN;  // Error just means that there were no line items added
        Booking recBooking = recBookingDetail.getBooking(true);
        
        int iHotelID = (int)this.getField(Product.kID).getValue();
        Date dateTarget = ((DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate)).getDateTime();
        int iRateID = (int)recBookingDetail.getField(BookingDetail.kRateID).getValue();
        int iClassID = (int)recBookingDetail.getField(BookingDetail.kClassID).getValue();
        
        for (int iPaxCategory = PaxCategory.SINGLE_ID; iPaxCategory <= PaxCategory.CHILD_ID; iPaxCategory++)
        {
            short sTargetPax = (short)recBooking.getField(Booking.kSinglePax + iPaxCategory - PaxCategory.SINGLE_ID).getValue();
            if (sTargetPax == 0)
                continue;
            HotelPricing recProductPricing = null;
            double dAmount = 0;
            short sNights = (short)recBookingDetail.getField(BookingHotel.kNights).getValue();
            while (sNights > 0)
            {
                recProductPricing = ((HotelPricing)this.getProductPricing()).getHotelCost(iHotelID, dateTarget, iRateID, iClassID, iPaxCategory);
                if (recProductPricing != null)
                    dAmount = dAmount + recProductPricing.getField(ProductPricing.kPrice).getValue();
                dateTarget = new Date(dateTarget.getTime() + DBConstants.KMS_IN_A_DAY);
                sNights--;
            }
            if (recProductPricing != null)
                if (dAmount != 0)
            {
                int iPricingType = PricingType.COMPONENT_PRICING;
                int iQuantity = sTargetPax;
                boolean bCommissionable = recProductPricing.getField(ProductPricing.kCommissionable).getState();
                double dCommissionRate = recProductPricing.getField(ProductPricing.kCommissionRate).getValue();
                String strPayAt = recProductPricing.getField(ProductPricing.kPayAt).toString();
                int iErrorCode2 = recBookingDetail.updateBookingLine(recBookingLine, iPricingType, iPaxCategory, iQuantity, dAmount, bCommissionable, dCommissionRate, strPayAt, PricingStatus.OKAY, iChangeType);
                if (iErrorCode2 == DBConstants.NORMAL_RETURN)
                    iErrorCode = DBConstants.NORMAL_RETURN;   // Some Valid pricing was added
            }
        }
        return iErrorCode;   // For now
    }
    /**
     * CreateProductPricing Method.
     */
    public ProductPricing createProductPricing(RecordOwner recordOwner)
    {
        return new HotelPricing(recordOwner);
    }
    /**
     * MarkupPriceFromCost Method.
     */
    public void markupPriceFromCost(double dMarkup, boolean bMarkupOnlyIfNoPrice)
    {
        super.markupPriceFromCost(dMarkup, bMarkupOnlyIfNoPrice);
        if (dMarkup == 0.00)
        {
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kSinglePriceLocal).getValue() == 0))
                this.getField(Hotel.kSinglePriceLocal).setData(null);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kDoublePriceLocal).getValue() == 0))
                this.getField(Hotel.kDoublePriceLocal).setData(null);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kTriplePriceLocal).getValue() == 0))
                this.getField(Hotel.kTriplePriceLocal).setData(null);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kQuadPriceLocal).getValue() == 0))
                this.getField(Hotel.kQuadPriceLocal).setData(null);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kRoomPriceLocal).getValue() == 0))
                this.getField(Hotel.kRoomPriceLocal).setData(null);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kMealPriceLocal).getValue() == 0))
                this.getField(Hotel.kMealPriceLocal).setData(null);
        }
        else
        {
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kSinglePriceLocal).getValue() == 0))
                this.getField(Hotel.kSinglePriceLocal).setValue(Math.floor(this.getField(Hotel.kSingleCostLocal).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kDoublePriceLocal).getValue() == 0))
                this.getField(Hotel.kDoublePriceLocal).setValue(Math.floor(this.getField(Hotel.kDoubleCostLocal).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kTriplePriceLocal).getValue() == 0))
                this.getField(Hotel.kTriplePriceLocal).setValue(Math.floor(this.getField(Hotel.kTripleCostLocal).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kQuadPriceLocal).getValue() == 0))
                this.getField(Hotel.kQuadPriceLocal).setValue(Math.floor(this.getField(Hotel.kQuadCostLocal).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kRoomPriceLocal).getValue() == 0))
                this.getField(Hotel.kRoomPriceLocal).setValue(Math.floor(this.getField(Hotel.kRoomCostLocal).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
            if ((!bMarkupOnlyIfNoPrice) || (this.getField(Hotel.kMealPriceLocal).getValue() == 0))
                this.getField(Hotel.kMealPriceLocal).setValue(Math.floor(this.getField(Hotel.kMealCostLocal).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
        }
    }

}
