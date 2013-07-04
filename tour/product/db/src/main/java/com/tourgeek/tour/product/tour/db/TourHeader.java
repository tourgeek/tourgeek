
package com.tourgeek.tour.product.tour.db;

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
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.message.tour.response.*;
import org.jbundle.main.msg.db.*;
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.tour.request.data.*;
import com.tourgeek.tour.message.base.response.data.*;
import com.tourgeek.tour.message.base.request.data.*;
import com.tourgeek.tour.product.base.event.*;
import org.jbundle.main.db.base.*;
import org.jbundle.model.message.*;
import com.tourgeek.tour.product.tour.detail.db.*;
import com.tourgeek.model.tour.booking.inventory.db.*;
import com.tourgeek.model.tour.booking.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.shared.*;
import com.tourgeek.tour.base.field.*;
import com.tourgeek.model.tour.product.tour.db.*;

/**
 *  TourHeader - Tour Header.
 */
public class TourHeader extends Product
     implements TourHeaderModel
{
    private static final long serialVersionUID = 1L;

    protected TourHeaderLine m_recTourHeaderPricing = null;
    public static final int TOUR_DETAIL_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 128;
    /**
     * Default constructor.
     */
    public TourHeader()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeader(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recTourHeaderPricing = null;
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TOUR_HEADER_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour header";
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
            screen = Record.makeNewScreen(TourHeaderOption.TOUR_HEADER_OPTION_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeader.TOUR_DETAIL_SCREEN) == TourHeader.TOUR_DETAIL_SCREEN)
            screen = Record.makeNewScreen(TourHeaderTour.TOUR_HEADER_TOUR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.INVENTORY_GRID_SCREEN) == Product.INVENTORY_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderInventoryModel.TOUR_HEADER_INVENTORY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.INVENTORY_SCREEN) == Product.INVENTORY_SCREEN)
            screen = Record.makeNewScreen(TourHeaderInventoryModel.TOUR_HEADER_INVENTORY_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Product.RANGE_ADJUST_SCREEN) == Product.RANGE_ADJUST_SCREEN)
            screen = Record.makeNewScreen(TourHeaderInventoryModel.TOUR_HEADER_INVENTORY_RANGE_ADJUST_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_PRODUCT_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new ProductDesc(this, DESCRIPTION, 50, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, CODE, 10, null, null);
        if (iFieldSeq == 5)
        {
            field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 6)
        //  field = new StringField(this, OPERATORS_CODE, 20, null, null);
        if (iFieldSeq == 7)
            field = new TourHeaderChainField(this, PRODUCT_CHAIN_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new CityField(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new TimeField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new TourHeader_StartDate(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
            field.setMinimumLength(1);
        }
        if (iFieldSeq == 32)
        {
            field = new TourHeader_EndDate(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
            field.setMinimumLength(1);
        }
        if (iFieldSeq == 33)
        {
            field = new TourTypeField(this, TOUR_TYPE, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 34)
        {
            field = new BooleanField(this, TOUR_SERIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 35)
        {
            field = new ShortField(this, DAYS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 36)
        {
            field = new ShortField(this, NIGHTS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 37)
        {
            field = new BrochureField(this, BROCHURE_ID, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 38)
        {
            field = new AirlineField(this, AIRLINE_ID, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
        {
            field = new ProductCategoryField(this, PRODUCT_CAT_ID, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 40)
        {
            field = new TourClassField(this, TOUR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 41)
            field = new CountryField(this, COUNTRY_ID, 2, null, null);
        if (iFieldSeq == 42)
            field = new RegionField(this, REGION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recTourHeaderPricing != null)
            m_recTourHeaderPricing.free();
        m_recTourHeaderPricing = null;
        super.free();
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        int iDocMode = ScreenConstants.MAINT_MODE;
        if (TourHeader.TOUR_DETAIL.equalsIgnoreCase(strCommand))
            iDocMode = TourHeader.TOUR_DETAIL_SCREEN;
        else
            iDocMode = super.commandToDocType(strCommand);
        return iDocMode;
    }
    /**
     * Read the locally stored product cost (Override).
     */
    public Message processCostRequestInMessage(Message messageIn, Message messageReply)
    {
        ProductRequest productRequest = (ProductRequest)((BaseMessage)messageIn).getMessageDataDesc(null);
        TourMessageData productMessageData = (TourMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData  = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        Date dateTarget = productMessageData.getTargetDate();
        short sTargetPax = passengerMessageData.getTargetPax();
        int iTourRateID = productMessageData.getRateTypeID();
        int iTourClassID = productMessageData.getRateClassID();
        
        if (m_recTourHeaderPricing == null)
        {
            m_recTourHeaderPricing = new TourHeaderLine(this.findRecordOwner());
            if (m_recTourHeaderPricing.getRecordOwner() != null)
                m_recTourHeaderPricing.getRecordOwner().removeRecord(m_recTourHeaderPricing);
        }
        //ReferenceField fldTourClass = (ReferenceField)m_recTourHeaderPricing.getField(TourHeaderPricing.CLASS_ID);
        TourRateResponse responseMessage = null;
        if (messageReply == null)
        {
            messageReply = new TreeMessage(null, null);
            responseMessage =  new TourRateResponse((BaseMessage)messageReply, null);
        }
        else
            responseMessage = (TourRateResponse)((BaseMessage)messageReply).getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        ProductRateResponseMessageData responseProductMessageData = (ProductRateResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
        
        double dTotalLocalCost = this.getTourCost(dateTarget, productMessageData, false);
        double dTotalLocalPrice = this.getTourCost(dateTarget, productMessageData, true);
        
        double dTourCost = 0;
        double dTourPrice = 0;
        
        dTotalLocalCost = Math.floor(dTotalLocalCost * 100.00 + 0.5) / 100.00;
        
        this.getField(TourHeader.CLASS_ID).setValue(iTourClassID);
        
        this.getField(TourHeader.PP_COST).setValue(dTourCost);
        this.getField(TourHeader.PP_PRICE_LOCAL).setValue(dTourPrice);
        this.getField(Product.PRODUCT_COST).setValue(dTotalLocalCost);
        this.getField(Product.PRODUCT_PRICE_LOCAL).setValue(dTotalLocalPrice);
        
        responseProductMessageData.setPPCost(this.getField(TourHeader.PP_COST).getValue());
        if (iTourClassID != productMessageData.getRateClassID())
        {
            responseProductMessageData.setRateClassID(productMessageData.getRateClassID());
            responseProductMessageData.setNewRateClassID(iTourClassID);
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
     * Add the booking detail that goes with the product in this message.
     */
    public int addMessageBookingDetail(BookingDetailModel recBookingDetail, BookingModel recBooking, TourModel recTour, String strMessageTransportID, MessageRecordDesc productRequest) throws DBException
    {
        if (this.getField(TourHeader.TOUR_SERIES).getState() == true)
            productRequest.put(ProductRequest.PRODUCT_MESSAGE, null);   // Don't need to add this detail
        if (productRequest != null)
        {
            TourMessageData tourMessageData = (TourMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
            if (tourMessageData != null)
            {
                Integer objProductID = (Integer)tourMessageData.get("ProductID");
                Date objDetailDate = (Date)tourMessageData.get("DetailDate");
                if (objDetailDate != null)
                {
                    Converter.initGlobals();
                    Calendar calendar = Converter.gCalendar;
                    calendar.setTime(objDetailDate);
                    calendar.set(Calendar.HOUR_OF_DAY, DBConstants.HOUR_DATE_ONLY);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    objDetailDate = calendar.getTime();
                    if (objProductID != null)
                        if (objProductID.equals(recTour.getField(TourModel.TOUR_HEADER_ID).getData()))
                            if (objDetailDate != null)
                                if (objDetailDate.equals(recTour.getField(TourModel.DEPARTURE_DATE).getData()))
                                    productRequest.put(ProductRequest.PRODUCT_MESSAGE, null);   // Don't need to add this detail
                }
            }
        }
        return super.addMessageBookingDetail(recBookingDetail, recBooking, recTour, strMessageTransportID, productRequest);
    }
    /**
     * ChangeMessageBookingDetail Method.
     */
    public int changeMessageBookingDetail(BookingDetailModel recBookingDetail, BookingModel recBooking, TourModel recTour, String strMessageTransportID, MessageRecordDesc productRequest) throws DBException
    {
            if (this.getField(TourHeader.TOUR_SERIES).getState() == true)
            {
                ProductMessageData productMessage = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
                Date date = (Date)productMessage.get(BookingDetailModel.DETAIL_DATE);
                if (date != null)
                {   // Departure date change.
                    TourClass recTourClass = (TourClass)((ReferenceField)this.getField(TourHeader.TOUR_CLASS_ID)).getReferenceRecord(this.findRecordOwner());
                    BaseField fldTourCode = this.getField(TourHeader.CODE);
                    DateField fldDepartureDate = (DateField)recTour.getField(TourModel.DEPARTURE_DATE);
                    BaseField fldTourDesc = this.getField(TourHeader.DESCRIPTION);
                            
                    FieldListener fieldBehavior = null;
                    fieldBehavior = new ChangeTourHeaderHandler(this, recTourClass, (TourModel)recTour, (BookingModel)recBooking, fldTourCode, fldDepartureDate, null);
                    fldDepartureDate.addListener(fieldBehavior);
                    fldDepartureDate.setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        //          +message.addPassengersToBooking(recBooking);
        //          +message.addContactToBooking(recBooking);
                }
                return DBConstants.NORMAL_RETURN;     // For tour headers, the detail is the header, so no detail must be changed
            }
        return super.changeMessageBookingDetail(recBookingDetail, recBooking, recTour, strMessageTransportID, productRequest);
    }
    /**
     * Get the correct remote tour header for this product.
     */
    public TourHeader getBookingTourHeader(BookingControl recBookingControl)
    {
        if (this.getField(TourHeader.TOUR_SERIES).getState() == true)
            return this;   // This is a valid tour header, don't need to set one up
        if (recBookingControl != null)
            if (!recBookingControl.getField(BookingControl.TOUR_HEADER_TOUR_TYPE).isNull())
                if ((this.getEditMode() == DBConstants.EDIT_CURRENT) || (this.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
        {
            TourTypeField fldTourType = (TourTypeField)recBookingControl.getField(BookingControl.TOUR_HEADER_TOUR_TYPE);
            int iTourTypeMask = fldTourType.getBitsToCheck();
            int iTourHeaderTourType = (int)recBookingControl.getField(BookingControl.TOUR_HEADER_TOUR_TYPE).getValue();
            if ((iTourHeaderTourType & (int)this.getField(TourHeader.TOUR_TYPE).getValue() & iTourTypeMask) != 0)
                return this;   // This is a valid tour header, don't need to set one up
        }
        return super.getBookingTourHeader(recBookingControl);
    }
    /**
     * GetProductBookingResponse Method.
     */
    public ProductBookingResponse getProductBookingResponse(String strRequestType, Message message, String strKey)
    {
        if (RequestType.BOOKING_ADD.equalsIgnoreCase(strRequestType))
            return new TourBookingResponse((BaseMessage)message, strKey);
        else if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(strRequestType))
            return new TourBookingChangeResponse((BaseMessage)message, strKey);
        else
            return super.getProductBookingResponse(strRequestType, message, strKey);
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetailModel getBookingDetail(RecordOwner recordOwner)
    {
        return (BookingDetailModel)Record.makeRecordFromClassName(BookingTourModel.THICK_CLASS, recordOwner);
    }
    /**
     * Get the tour cost for the pax category.
     */
    public double getTourCost(Date dateTarget, TourMessageData productMessageData, boolean bGetPrice)
    {
        return this.getTourCost(dateTarget, TourHeaderOption.TOUR, this.getField(TourHeader.ID), productMessageData, bGetPrice);
    }
    /**
     * GetTourCost Method.
     */
    public double getTourCost(Date dateTarget, String strTourOrOption, BaseField fldTourOrOptionID, TourMessageData productMessageData, boolean bGetPrice)
    {
        double dCostTotal = 0;
        Short intPax = (Short)productMessageData.get(Product.PAX_PARAM);
        int iTargetPax = 1;
        if (intPax != null)
            iTargetPax = intPax.intValue();
        
        TourHeaderOption recTourHeaderOption = new TourHeaderOption(this.findRecordOwner());
        PaxCategory recPaxCategory = new PaxCategory(this.findRecordOwner());
        TourHeaderLine recTourHeaderPricing = new TourHeaderLine(this.findRecordOwner());
        try {
            recTourHeaderOption.setKeyArea(TourHeaderOption.TOUR_OR_OPTION_KEY);
            recTourHeaderOption.addListener(new StringSubFileFilter(strTourOrOption, TourHeaderOption.TOUR_OR_OPTION, fldTourOrOptionID.getData().toString(), TourHeaderOption.TOUR_OR_OPTION_ID, null, null));
            recTourHeaderOption.close();
            while (recTourHeaderOption.hasNext())
            {
                recTourHeaderOption.next();
                if (recTourHeaderOption.getField(TourHeaderOption.ASK_FOR_ANSWER).getState() == true)
                    continue;
                if (recTourHeaderOption.getField(TourHeaderOption.ALWAYS_RESOLVE).getState() == false)
                    continue;
                boolean bIsValid = false;
                int iPaxInRooms = 0;
                for (int iRoomType = PaxCategory.SINGLE_ID; iRoomType <= PaxCategory.CHILD_ID; iRoomType++)
                {
                    Short intPaxInRoom = (Short)productMessageData.get(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomType));
                    if (intPaxInRoom != null)
                        iPaxInRooms += intPaxInRoom.shortValue();
                }
                recPaxCategory.close();
                while (recPaxCategory.hasNext())
                {
                    recPaxCategory.next();
                    if (recTourHeaderOption.isValid(null, recPaxCategory.getField(PaxCategory.ID), dateTarget))
                    {   // This option applies to this target departure date
                        bIsValid = true;
                        short shPaxInRoomType = (short)iTargetPax;
                        int iRoomType = (int)recPaxCategory.getField(PaxCategory.ROOM_TYPE).getValue();
                        if (iRoomType != 0)
                        {
                            Short shortPaxInRoomType = (Short)productMessageData.get(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomType));
                            shPaxInRoomType = 0;
                            if (shortPaxInRoomType != null)
                                shPaxInRoomType = shortPaxInRoomType.shortValue();
                            if (iPaxInRooms != iTargetPax)
                            {   // Special case - Didn't specify room configuration, so return the twin share price
                                if ((recPaxCategory.getField(PaxCategory.ID).getValue() == PaxCategory.ALL_ID)
                                    || (recPaxCategory.getField(PaxCategory.ID).getValue() == PaxCategory.DOUBLE_ID))
                                        shPaxInRoomType = (short)iTargetPax;
                                else
                                    shPaxInRoomType = 0;   // Don't process other room types.
                            }
                        }
                        if (shPaxInRoomType == 0)
                            continue;   // No pax in this category
                        double dRoomCost = this.getTourCost(recTourHeaderPricing, recTourHeaderOption, recPaxCategory.getField(PaxCategory.ID), bGetPrice);
                        dCostTotal += dRoomCost * shPaxInRoomType;
                    }
                    if (bIsValid)
                        if (recTourHeaderOption.getField(TourHeaderOption.DETAIL_OPTION_COUNT).getValue() > 0)
                    {
                        dCostTotal += this.getTourCost(dateTarget, TourHeaderOption.OPTION, recTourHeaderOption.getField(TourHeaderOption.ID), productMessageData, bGetPrice);
                    }
                }
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            recTourHeaderOption.free();
            recTourHeaderOption = null;
            recPaxCategory.free();
            recPaxCategory = null;
            recTourHeaderPricing.free();
            recTourHeaderPricing = null;
        }
        
        this.getField(Product.PRODUCT_COST).setValue(dCostTotal);
        int iCostStatus = CostStatus.VALID;
        this.getField(Product.DISPLAY_COST_STATUS_ID).setValue(iCostStatus);
        return dCostTotal;
    }
    /**
     * GetTourCost Method.
     */
    public double getTourCost(TourHeaderLine recTourHeaderLine, TourHeaderOption recTourHeaderOption, BaseField fldPaxCategory, boolean bGetPrice)
    {
        double dTourCost = 0;
        FileListener listener = null;
        recTourHeaderLine.setKeyArea(TourHeaderLine.TOUR_HEADER_OPTION_ID_KEY);
        recTourHeaderLine.addListener(listener = new SubFileFilter(recTourHeaderOption.getField(TourHeaderOption.ID), TourHeaderLine.TOUR_HEADER_OPTION_ID, fldPaxCategory, TourHeaderLine.PAX_CATEGORY_ID, null, null));
        recTourHeaderLine.close();
        try {
            while (recTourHeaderLine.hasNext())
            {
                recTourHeaderLine.next();
                double dCost = 0;
                if (!bGetPrice)
                {
                    dCost = recTourHeaderLine.getField(TourHeaderLine.COST).getValue();
                    ProductTerms recProductTerms = (ProductTerms)((ReferenceField)recTourHeaderLine.getField(TourHeaderLine.PRODUCT_TERMS_ID)).getReference();
                    if (recProductTerms != null)
                        if (recProductTerms.getEditMode() == DBConstants.EDIT_CURRENT)
                            dCost = recProductTerms.calcNetCost(dCost, null);
                }
                else
                    dCost = recTourHeaderLine.getField(TourHeaderLine.PRICE).getValue();
                dTourCost = dTourCost + dCost;
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            recTourHeaderLine.removeListener(listener, true);
        }
        return dTourCost;
    }
    /**
     * Lookup the tour header.
     * You can pass either the description and a target date, or
     * pass the tour code and a target departure date.
     * @param fldTourCode A field containing the tour code.
     * @param fldDepartureDate field Containing the target departure date.
     * @param fldTourDesc A field with the tour desc.
     * @param bDisplayOption The display option.
     * @return true if the lookup was successful (and this TourHeader will be current).
     */
    public int lookupTourHdr(BaseField fldTourCode, BaseField fldDepartureDate, BaseField fldTourDesc, boolean bDisplayOption)
    {
        String strSave = null;
        boolean bHeaderFound = false;
        try   {
            if (fldTourDesc != null)
                if (fldTourDesc.getLength() != 0)
            { // Tour code is in, read using this code and the date
                strSave = fldTourDesc.getString();  // Save this
                this.addNew();
                this.getField(TourHeader.DESCRIPTION).setString(strSave);
                strSave = this.getField(TourHeader.DESC_SORT).toString();
                this.addNew();
                FileListener fileBehavior = new StringSubFileFilter(strSave, TourHeader.DESC_SORT, null, null, null, null);
                this.addListener(fileBehavior);
                FileListener fileBehavior2 = null;
                if (fldDepartureDate.getLength() != 0)
                {
                    fileBehavior2 = new ExtractRangeFilter(TourHeader.START_DATE, fldDepartureDate, TourHeader.END_DATE, fldDepartureDate, ExtractRangeFilter.PAD_END_FIELD);
                    this.addListener(fileBehavior2);
                }
                this.setKeyArea(TourHeader.DESC_SORT_KEY);
                this.close();
                boolean bHasNext = this.hasNext();
                if (bHasNext)
                    this.next();
                this.getField(TourHeader.DESCRIPTION).setEnabled(true);
                this.setKeyArea(TourHeader.ID_KEY);
                this.removeListener(fileBehavior, true);
                if (fileBehavior2 != null)
                    this.removeListener(fileBehavior2, true);
                if (!bHasNext)
                {
                    this.addNew();
                    fldTourDesc.setString(strSave, bDisplayOption, DBConstants.INIT_MOVE);  // Restore this
                }
                else
                    bHeaderFound = true;
            }
            if (bHeaderFound == false)
                if (fldTourCode != null) if (fldTourCode.getLength() != 0)
            { // Tour code is in, read using this code and the date
                strSave = fldTourCode.getString();  // Save this
                this.addNew();
                FileListener fileBehavior = new StringSubFileFilter(strSave, TourHeader.CODE, null, null, null, null);
                this.addListener(fileBehavior);
                FileListener fileBehavior2 = null;
                if (fldDepartureDate.getLength() != 0)
                {
                    fileBehavior2 = new ExtractRangeFilter(TourHeader.START_DATE, fldDepartureDate, TourHeader.END_DATE, fldDepartureDate, ExtractRangeFilter.PAD_END_FIELD);
                    this.addListener(fileBehavior2);
                }
                this.setKeyArea(TourHeader.CODE_KEY);
                this.close();
                boolean bHasNext = this.hasNext();
                if (bHasNext)
                    this.next();
                this.getField(TourHeader.CODE).setEnabled(true);
                this.setKeyArea(TourHeader.ID_KEY);
                this.removeListener(fileBehavior, true);
                if (fileBehavior2 != null)
                    this.removeListener(fileBehavior2, true);
                if (!bHasNext)
                {
                    this.addNew();
                    fldTourCode.setString(strSave, bDisplayOption, DBConstants.INIT_MOVE);  // Restore this
                }
                else
                    bHeaderFound = true;
            }
            if (bHeaderFound == false)
                this.addNew();
        } catch (DBException ex)   {
            ex.printStackTrace();
        }
        if (bHeaderFound)
            return DBConstants.NORMAL_RETURN;
        else
            return DBConstants.KEY_NOT_FOUND;
    }
    /**
     * Create a popup control using this record.
     */
    public ScreenComponent createTourHeaderPopup(ScreenLoc itsLocation, ComponentParent parentScreen, Converter converter, int iDisplayFieldDesc, BaseField fldDepartureDate, BaseField fldStartDate, BaseField
     fldEndDate, BaseField fldTourType)
    {
        Date dateTarget = new Date();
        long ltargetDate = (long)dateTarget.getTime();
        if ((long)fldDepartureDate.getValue() != 0) if ((long)fldDepartureDate.getValue() < ltargetDate)
            ltargetDate = (long)fldDepartureDate.getValue();
        fldStartDate.setValue(ltargetDate);   
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        nextYear.set(Calendar.HOUR_OF_DAY, 0);
        nextYear.set(Calendar.MINUTE, 0);
        nextYear.set(Calendar.SECOND, 0);
        nextYear.set(Calendar.MILLISECOND, 0);
        ltargetDate = nextYear.getTime().getTime();
        fldEndDate.setValue(Math.max(ltargetDate, (long)fldDepartureDate.getValue()));
        
        FileListener fileBehavior = new ExtractRangeFilter(TourHeader.START_DATE, fldStartDate, TourHeader.END_DATE, fldEndDate, ExtractRangeFilter.PAD_DEFAULT);
        this.addListener(fileBehavior);
        this.setKeyArea(TourHeader.DESC_SORT_KEY);
        ScreenComponent screenField = BaseField.createScreenComponent(ScreenModel.POPUP_BOX, itsLocation, parentScreen, converter, iDisplayFieldDesc, null);
        this.removeListener(fileBehavior, true);
        return screenField;
    }
    /**
     * AddSubFileIntegrityHandlers Method.
     */
    public void addSubFileIntegrityHandlers()
    {
        this.addListener(new SubFileIntegrityHandler(TourModel.class.getName(), false));
        this.addListener(new SubFileIntegrityHandler(TourHeaderInventoryModel.class.getName(), false));
        this.addListener(new SubFileIntegrityHandler(TourHeaderOption.class.getName(), false)
        {
            public Record getSubRecord()
            {
                if (m_recDependent == null)
                    m_recDependent = this.createSubRecord();
                if (m_recDependent != null)
                {
                    m_recDependent.setKeyArea(TourHeaderOption.TOUR_OR_OPTION_KEY);
                    StringField fldTourOrOption = new StringField(null, TourHeaderOption.TOUR_OR_OPTION, 1, null, null);
                    m_recDependent.addListener(new FreeOnFreeHandler(fldTourOrOption));
                    fldTourOrOption.setString(TourHeaderOption.TOUR);
                    if (m_recDependent.getListener(SubFileFilter.class.getName()) == null)
                        m_recDependent.addListener(new SubFileFilter(fldTourOrOption, TourHeaderOption.TOUR_OR_OPTION, (BaseField)this.getOwner().getCounterField(), TourHeaderOption.TOUR_OR_OPTION_ID, null, null));
                }
                return m_recDependent;
            }
            public Record createSubRecord()
            {
                TourHeaderOption record = (TourHeaderOption)super.createSubRecord();
                record.addSubFileIntegrityHandlers();
                return record;
            }
        });
    }

}
