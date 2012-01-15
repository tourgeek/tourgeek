/**
 * @(#)TourHeader.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.db;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.product.tour.screen.*;
import com.tourapp.tour.product.tour.other.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.tour.response.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.tour.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.entry.tour.*;
import com.tourapp.tour.booking.inventory.db.*;
import org.jbundle.main.msg.db.base.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.model.tour.product.tour.db.*;

/**
 *  TourHeader - Tour Header.
 */
public class TourHeader extends Product
     implements TourHeaderModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kCode = kCode;
    //public static final int kDescription = kDescription;
    //public static final int kVendorID = kVendorID;
    //public static final int kOperatorsCode = kOperatorsCode;
    //public static final int kProductChainID = kProductChainID;
    //public static final int kCityID = kCityID;
    //public static final int kEtd = kEtd;
    //public static final int kComments = kComments;
    //public static final int kItineraryDesc = kItineraryDesc;
    //public static final int kDescSort = kDescSort;
    public static final int kStartDate = kProductLastField + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kTourType = kEndDate + 1;
    public static final int kTourSeries = kTourType + 1;
    public static final int kDays = kTourSeries + 1;
    public static final int kNights = kDays + 1;
    public static final int kBrochureID = kNights + 1;
    public static final int kAirlineID = kBrochureID + 1;
    public static final int kProductCatID = kAirlineID + 1;
    public static final int kTourClassID = kProductCatID + 1;
    public static final int kCountryID = kTourClassID + 1;
    public static final int kRegionID = kCountryID + 1;
    public static final int kTourHeaderLastField = kRegionID;
    public static final int kTourHeaderFields = kRegionID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kDescSortKey = kCodeKey + 1;
    public static final int kVendorIDKey = kDescSortKey + 1;
    public static final int kCityIDKey = kVendorIDKey + 1;
    public static final int kTourHeaderLastKey = kCityIDKey;
    public static final int kTourHeaderKeys = kCityIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    protected TourHeaderLine m_recTourHeaderPricing = null;
    public static final int TOUR_DETAIL_SCREEN = ScreenConstants.LAST_MODE * 128;
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

    public static final String kTourHeaderFile = "TourHeader";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourHeaderFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & Product.PRICING_GRID_SCREEN) == Product.PRICING_GRID_SCREEN)
            screen = new TourHeaderOptionGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeader.TOUR_DETAIL_SCREEN) == TourHeader.TOUR_DETAIL_SCREEN)
            screen = new TourHeaderTourGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_GRID_SCREEN) == Product.INVENTORY_GRID_SCREEN)
            screen = new TourHeaderInventoryGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.INVENTORY_SCREEN) == Product.INVENTORY_SCREEN)
            screen = new TourHeaderInventoryScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.RANGE_ADJUST_SCREEN) == Product.RANGE_ADJUST_SCREEN)
            screen = new TourHeaderInventoryRangeAdjust(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new TourHeaderScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = new TourProductGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 10, null, null);
        if (iFieldSeq == kDescription)
            field = new ProductDesc(this, "Description", 50, null, null);
        if (iFieldSeq == kVendorID)
        {
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kOperatorsCode)
        //  field = new StringField(this, "OperatorsCode", 20, null, null);
        if (iFieldSeq == kProductChainID)
            field = new TourHeaderChainField(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCityID)
        //  field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kEtd)
        //  field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kComments)
        //  field = new MemoField(this, "Comments", 32767, null, null);
        //if (iFieldSeq == kItineraryDesc)
        //  field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDescSort)
        //  field = new ProductDescSort(this, "DescSort", 10, null, null);
        if (iFieldSeq == kStartDate)
        {
            field = new TourHeader_StartDate(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
            field.setMinimumLength(1);
        }
        if (iFieldSeq == kEndDate)
        {
            field = new TourHeader_EndDate(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
            field.setMinimumLength(1);
        }
        if (iFieldSeq == kTourType)
        {
            field = new TourTypeField(this, "TourType", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTourSeries)
        {
            field = new BooleanField(this, "TourSeries", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
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
        if (iFieldSeq == kBrochureID)
        {
            field = new BrochureField(this, "BrochureID", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirlineID)
        {
            field = new AirlineField(this, "AirlineID", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kProductCatID)
        {
            field = new ProductCategoryField(this, "ProductCatID", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTourClassID)
        {
            field = new TourClassField(this, "TourClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", 2, null, null);
        if (iFieldSeq == kRegionID)
            field = new RegionField(this, "RegionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourHeaderLastField)
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
        if (keyArea == null) if (iKeyArea < kTourHeaderLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourHeaderLastKey)
                keyArea = new EmptyKey(this);
        }
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
    public BaseMessage processCostRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        TourMessageData productMessageData = (TourMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData  = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        Date dateTarget = productMessageData.getTargetDate();
        short sTargetPax = passengerMessageData.getTargetPax();
        int iTourRateID = productMessageData.getRateTypeID();
        int iTourClassID = productMessageData.getRateClassID();
        
        if (m_recTourHeaderPricing == null)
        {
            m_recTourHeaderPricing = new TourHeaderLine(Utility.getRecordOwner(this));
            if (m_recTourHeaderPricing.getRecordOwner() != null)
                m_recTourHeaderPricing.getRecordOwner().removeRecord(m_recTourHeaderPricing);
        }
        //ReferenceField fldTourClass = (ReferenceField)m_recTourHeaderPricing.getField(TourHeaderPricing.kClassID);
        TourRateResponse responseMessage = null;
        if (messageReply == null)
        {
            messageReply = new TreeMessage(null, null);
            responseMessage =  new TourRateResponse(messageReply, null);
        }
        else
            responseMessage = (TourRateResponse)messageReply.getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        ProductRateResponseMessageData responseProductMessageData = (ProductRateResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
        
        double dTotalLocalCost = this.getTourCost(dateTarget, productMessageData, false);
        double dTotalLocalPrice = this.getTourCost(dateTarget, productMessageData, true);
        
        double dTourCost = 0;
        double dTourPrice = 0;
        
        dTotalLocalCost = Math.floor(dTotalLocalCost * 100.00 + 0.5) / 100.00;
        
        this.getField(TourHeader.kClassID).setValue(iTourClassID);
        
        this.getField(TourHeader.kPPCost).setValue(dTourCost);
        this.getField(TourHeader.kPPPriceLocal).setValue(dTourPrice);
        this.getField(Product.kProductCost).setValue(dTotalLocalCost);
        this.getField(Product.kProductPriceLocal).setValue(dTotalLocalPrice);
        
        responseProductMessageData.setPPCost(this.getField(TourHeader.kPPCost).getValue());
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
     * Add the booking detail that goes with the product in this message.
     */
    public int addMessageBookingDetail(BookingDetail recBookingDetail, Booking recBooking, Tour recTour, String strMessageTransportID, MessageRecordDesc productRequest) throws DBException
    {
        if (this.getField(TourHeader.kTourSeries).getState() == true)
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
                        if (objProductID.equals(recTour.getField(Tour.kTourHeaderID).getData()))
                            if (objDetailDate != null)
                                if (objDetailDate.equals(recTour.getField(Tour.kDepartureDate).getData()))
                                    productRequest.put(ProductRequest.PRODUCT_MESSAGE, null);   // Don't need to add this detail
                }
            }
        }
        return super.addMessageBookingDetail(recBookingDetail, recBooking, recTour, strMessageTransportID, productRequest);
    }
    /**
     * ChangeMessageBookingDetail Method.
     */
    public int changeMessageBookingDetail(BookingDetail recBookingDetail, Booking recBooking, Tour recTour, String strMessageTransportID, MessageRecordDesc productRequest) throws DBException
    {
            if (this.getField(TourHeader.kTourSeries).getState() == true)
            {
                ProductMessageData productMessage = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
                Date date = (Date)productMessage.get(BookingDetail.DETAIL_DATE);
                if (date != null)
                {   // Departure date change.
                    TourClass recTourClass = (TourClass)((ReferenceField)this.getField(TourHeader.kTourClassID)).getReferenceRecord(Utility.getRecordOwner(this));
                    BaseField fldTourCode = this.getField(TourHeader.kCode);
                    DateField fldDepartureDate = (DateField)recTour.getField(Tour.kDepartureDate);
                    BaseField fldTourDesc = this.getField(TourHeader.kDescription);
                            
                    FieldListener fieldBehavior = null;
                    fieldBehavior = new ChangeTourHeaderHandler(this, recTourClass, recTour, recBooking, fldTourCode, fldDepartureDate, null);
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
        if (this.getField(TourHeader.kTourSeries).getState() == true)
            return this;   // This is a valid tour header, don't need to set one up
        if (recBookingControl != null)
            if (!recBookingControl.getField(BookingControl.kTourHeaderTourType).isNull())
                if ((this.getEditMode() == DBConstants.EDIT_CURRENT) || (this.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
        {
            TourTypeField fldTourType = (TourTypeField)recBookingControl.getField(BookingControl.kTourHeaderTourType);
            int iTourTypeMask = fldTourType.getBitsToCheck();
            int iTourHeaderTourType = (int)recBookingControl.getField(BookingControl.kTourHeaderTourType).getValue();
            if ((iTourHeaderTourType & (int)this.getField(TourHeader.kTourType).getValue() & iTourTypeMask) != 0)
                return this;   // This is a valid tour header, don't need to set one up
        }
        return super.getBookingTourHeader(recBookingControl);
    }
    /**
     * GetProductBookingResponse Method.
     */
    public ProductBookingResponse getProductBookingResponse(String strRequestType, BaseMessage message, String strKey)
    {
        if (RequestType.BOOKING_ADD.equalsIgnoreCase(strRequestType))
            return new TourBookingResponse(message, strKey);
        else if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(strRequestType))
            return new TourBookingChangeResponse(message, strKey);
        else
            return super.getProductBookingResponse(strRequestType, message, strKey);
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetail getBookingDetail(RecordOwner recordOwner)
    {
        return new BookingTour(recordOwner);
    }
    /**
     * Get the tour cost for the pax category.
     */
    public double getTourCost(Date dateTarget, TourMessageData productMessageData, boolean bGetPrice)
    {
        return this.getTourCost(dateTarget, TourHeaderOption.TOUR, this.getField(TourHeader.kID), productMessageData, bGetPrice);
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
        
        TourHeaderOption recTourHeaderOption = new TourHeaderOption(Utility.getRecordOwner(this));
        PaxCategory recPaxCategory = new PaxCategory(Utility.getRecordOwner(this));
        TourHeaderLine recTourHeaderPricing = new TourHeaderLine(Utility.getRecordOwner(this));
        try {
            recTourHeaderOption.setKeyArea(TourHeaderOption.kTourOrOptionKey);
            recTourHeaderOption.addListener(new StringSubFileFilter(strTourOrOption, TourHeaderOption.kTourOrOption, fldTourOrOptionID.getData().toString(), TourHeaderOption.kTourOrOptionID, null, -1));
            recTourHeaderOption.close();
            while (recTourHeaderOption.hasNext())
            {
                recTourHeaderOption.next();
                if (recTourHeaderOption.getField(TourHeaderOption.kAskForAnswer).getState() == true)
                    continue;
                if (recTourHeaderOption.getField(TourHeaderOption.kAlwaysResolve).getState() == false)
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
                    if (recTourHeaderOption.isValid(null, recPaxCategory.getField(PaxCategory.kID), dateTarget))
                    {   // This option applies to this target departure date
                        bIsValid = true;
                        short shPaxInRoomType = (short)iTargetPax;
                        int iRoomType = (int)recPaxCategory.getField(PaxCategory.kRoomType).getValue();
                        if (iRoomType != 0)
                        {
                            Short shortPaxInRoomType = (Short)productMessageData.get(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomType));
                            shPaxInRoomType = 0;
                            if (shortPaxInRoomType != null)
                                shPaxInRoomType = shortPaxInRoomType.shortValue();
                            if (iPaxInRooms != iTargetPax)
                            {   // Special case - Didn't specify room configuration, so return the twin share price
                                if ((recPaxCategory.getField(PaxCategory.kID).getValue() == PaxCategory.ALL_ID)
                                    || (recPaxCategory.getField(PaxCategory.kID).getValue() == PaxCategory.DOUBLE_ID))
                                        shPaxInRoomType = (short)iTargetPax;
                                else
                                    shPaxInRoomType = 0;   // Don't process other room types.
                            }
                        }
                        if (shPaxInRoomType == 0)
                            continue;   // No pax in this category
                        double dRoomCost = this.getTourCost(recTourHeaderPricing, recTourHeaderOption, recPaxCategory.getField(PaxCategory.kID), bGetPrice);
                        dCostTotal += dRoomCost * shPaxInRoomType;
                    }
                    if (bIsValid)
                        if (recTourHeaderOption.getField(TourHeaderOption.kDetailOptionCount).getValue() > 0)
                    {
                        dCostTotal += this.getTourCost(dateTarget, TourHeaderOption.OPTION, recTourHeaderOption.getField(TourHeaderOption.kID), productMessageData, bGetPrice);
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
        
        this.getField(Product.kProductCost).setValue(dCostTotal);
        int iCostStatus = CostStatus.VALID;
        this.getField(Product.kDisplayCostStatusID).setValue(iCostStatus);
        return dCostTotal;
    }
    /**
     * GetTourCost Method.
     */
    public double getTourCost(TourHeaderLine recTourHeaderLine, TourHeaderOption recTourHeaderOption, BaseField fldPaxCategory, boolean bGetPrice)
    {
        double dTourCost = 0;
        FileListener listener = null;
        recTourHeaderLine.setKeyArea(TourHeaderLine.kTourHeaderOptionIDKey);
        recTourHeaderLine.addListener(listener = new SubFileFilter(recTourHeaderOption.getField(TourHeaderOption.kID), TourHeaderLine.kTourHeaderOptionID, fldPaxCategory, TourHeaderLine.kPaxCategoryID, null, -1));
        recTourHeaderLine.close();
        try {
            while (recTourHeaderLine.hasNext())
            {
                recTourHeaderLine.next();
                double dCost = 0;
                if (!bGetPrice)
                {
                    dCost = recTourHeaderLine.getField(TourHeaderLine.kCost).getValue();
                    ProductTerms recProductTerms = (ProductTerms)((ReferenceField)recTourHeaderLine.getField(TourHeaderLine.kProductTermsID)).getReference();
                    if (recProductTerms != null)
                        if (recProductTerms.getEditMode() == DBConstants.EDIT_CURRENT)
                            dCost = recProductTerms.calcNetCost(dCost, null);
                }
                else
                    dCost = recTourHeaderLine.getField(TourHeaderLine.kPrice).getValue();
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
                this.getField(TourHeader.kDescription).setString(strSave);
                strSave = this.getField(TourHeader.kDescSort).toString();
                this.addNew();
                FileListener fileBehavior = new StringSubFileFilter(strSave, TourHeader.kDescSort, null, -1, null, -1);
                this.addListener(fileBehavior);
                FileListener fileBehavior2 = null;
                if (fldDepartureDate.getLength() != 0)
                {
                    fileBehavior2 = new ExtractRangeFilter(TourHeader.kStartDate, fldDepartureDate, TourHeader.kEndDate, fldDepartureDate, ExtractRangeFilter.PAD_END_FIELD);
                    this.addListener(fileBehavior2);
                }
                this.setKeyArea(TourHeader.kDescSortKey);
                this.close();
                boolean bHasNext = this.hasNext();
                if (bHasNext)
                    this.next();
                this.getField(TourHeader.kDescription).setEnabled(true);
                this.setKeyArea(TourHeader.kIDKey);
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
                FileListener fileBehavior = new StringSubFileFilter(strSave, TourHeader.kCode, null, -1, null, -1);
                this.addListener(fileBehavior);
                FileListener fileBehavior2 = null;
                if (fldDepartureDate.getLength() != 0)
                {
                    fileBehavior2 = new ExtractRangeFilter(TourHeader.kStartDate, fldDepartureDate, TourHeader.kEndDate, fldDepartureDate, ExtractRangeFilter.PAD_END_FIELD);
                    this.addListener(fileBehavior2);
                }
                this.setKeyArea(TourHeader.kCodeKey);
                this.close();
                boolean bHasNext = this.hasNext();
                if (bHasNext)
                    this.next();
                this.getField(TourHeader.kCode).setEnabled(true);
                this.setKeyArea(TourHeader.kIDKey);
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
    public SPopupBox createTourHeaderPopup(ScreenLocation itsLocation, BasePanel parentScreen, Converter converter, int iDisplayFieldDesc, BaseField fldDepartureDate, BaseField fldStartDate, BaseField
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
        
        FileListener fileBehavior = new ExtractRangeFilter(TourHeader.kStartDate, fldStartDate, TourHeader.kEndDate, fldEndDate, ExtractRangeFilter.PAD_DEFAULT);
        this.addListener(fileBehavior);
        this.setKeyArea(TourHeader.kDescSortKey);
        SPopupBox screenField = new SPopupBox(itsLocation, parentScreen, converter, iDisplayFieldDesc);
        this.removeListener(fileBehavior, true);
        return screenField;
    }
    /**
     * AddSubFileIntegrityHandlers Method.
     */
    public void addSubFileIntegrityHandlers()
    {
        this.addListener(new SubFileIntegrityHandler(Tour.class.getName(), false));
        this.addListener(new SubFileIntegrityHandler(TourHeaderInventory.class.getName(), false));
        this.addListener(new SubFileIntegrityHandler(TourHeaderOption.class.getName(), false)
        {
            public Record getSubRecord()
            {
                if (m_recDependent == null)
                    m_recDependent = this.createSubRecord();
                if (m_recDependent != null)
                {
                    m_recDependent.setKeyArea(TourHeaderOption.kTourOrOptionKey);
                    StringField fldTourOrOption = new StringField(null, TourHeaderOptionScreen.TOUR_OR_OPTION, 1, null, null);
                    m_recDependent.addListener(new FreeOnFreeHandler(fldTourOrOption));
                    fldTourOrOption.setString(TourHeaderOption.TOUR);
                    if (m_recDependent.getListener(SubFileFilter.class.getName()) == null)
                        m_recDependent.addListener(new SubFileFilter(fldTourOrOption, TourHeaderOption.kTourOrOption, (BaseField)this.getOwner().getCounterField(), TourHeaderOption.kTourOrOptionID, null, -1));
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
