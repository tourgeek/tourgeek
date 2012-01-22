/**
 * @(#)Product.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import java.util.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.land.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.thin.app.booking.entry.search.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.msg.screen.*;
import com.tourapp.tour.message.base.response.*;
import java.text.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.product.trans.db.*;
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.cruise.db.*;
import com.tourapp.tour.product.item.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.search.screen.*;
import com.tourapp.tour.booking.entry.event.*;
import com.tourapp.tour.product.air.db.*;
import org.jbundle.main.db.base.*;
import com.tourapp.tour.booking.inventory.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  Product - Product.
 */
public class Product extends VirtualRecord
     implements ProductModel, MessageDetailTarget
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kCode = kDescription + 1;
    public static final int kVendorID = kCode + 1;
    public static final int kOperatorsCode = kVendorID + 1;
    public static final int kProductChainID = kOperatorsCode + 1;
    public static final int kCityID = kProductChainID + 1;
    public static final int kEtd = kCityID + 1;
    public static final int kAckDays = kEtd + 1;
    public static final int kComments = kAckDays + 1;
    public static final int kProperties = kComments + 1;
    public static final int kItineraryDesc = kProperties + 1;
    public static final int kDescSort = kItineraryDesc + 1;
    public static final int kProductType = kDescSort + 1;
    public static final int kProductCost = kProductType + 1;
    public static final int kProductCostLocal = kProductCost + 1;
    public static final int kProductMessageTransportID = kProductCostLocal + 1;
    public static final int kDisplayInventoryStatusID = kProductMessageTransportID + 1;
    public static final int kInventoryAvailability = kDisplayInventoryStatusID + 1;
    public static final int kCurrencyCode = kInventoryAvailability + 1;
    public static final int kCurrencyCodeLocal = kCurrencyCode + 1;
    public static final int kVendorName = kCurrencyCodeLocal + 1;
    public static final int kDisplayCostStatusID = kVendorName + 1;
    public static final int kPPCost = kDisplayCostStatusID + 1;
    public static final int kPPCostLocal = kPPCost + 1;
    public static final int kRateID = kPPCostLocal + 1;
    public static final int kClassID = kRateID + 1;
    public static final int kProductPriceLocal = kClassID + 1;
    public static final int kPPPriceLocal = kProductPriceLocal + 1;
    public static final int kProductLastField = kPPPriceLocal;
    public static final int kProductFields = kPPPriceLocal - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kDescSortKey = kCodeKey + 1;
    public static final int kVendorIDKey = kDescSortKey + 1;
    public static final int kCityIDKey = kVendorIDKey + 1;
    public static final int kOperatorsCodeKey = kCityIDKey + 1;
    public static final int kProductChainIDKey = kOperatorsCodeKey + 1;
    public static final int kProductLastKey = kProductChainIDKey;
    public static final int kProductKeys = kProductChainIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String ROOM_TYPE_PARAM = SearchConstants.ROOM_TYPE;
    public static final int PRICING_GRID_SCREEN = ScreenConstants.DETAIL_MODE;
    public static final int INVENTORY_GRID_SCREEN = ScreenConstants.LAST_MODE * 2;
    public static final int INVENTORY_SCREEN = ScreenConstants.LAST_MODE * 4;
    public static final int RANGE_ADJUST_SCREEN = ScreenConstants.LAST_MODE * 16;
    public static final int BOOKING_DETAIL_GRID_SCREEN = ScreenConstants.LAST_MODE * 128;
    public static final int PRODUCT_SEARCH_DETAIL_GRID_SCREEN = ScreenConstants.LAST_MODE * 256;
    public static final int MESSAGE_DETAIL_MODE = ScreenConstants.LAST_MODE * 64;
    protected ProductPricing m_recProductPricing = null;
    protected ProductTerms m_recProductTerms = null;
    protected Inventory m_recInventory = null;
    protected MessageProcessInfo m_recMessageProcessInfo = null;
    /**
     * Default constructor.
     */
    public Product()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Product(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recProductPricing = null;
        m_recProductTerms = null;
        m_recInventory = null;
        m_recMessageProcessInfo = null;
        super.init(screen);
    }

    public static final String kProductFile = "Product";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product";
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
        if ((iDocMode & Product.MESSAGE_DETAIL_MODE) == Product.MESSAGE_DETAIL_MODE)
            screen = new MessageDetailGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.BOOKING_DETAIL_GRID_SCREEN) == Product.BOOKING_DETAIL_GRID_SCREEN)
            screen = new ProductBookingDetailGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Product.PRODUCT_SEARCH_DETAIL_GRID_SCREEN) == Product.PRODUCT_SEARCH_DETAIL_GRID_SCREEN)
            screen = new ProductSearchDetailGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kDescription)
            field = new ProductDesc(this, "Description", 50, null, null);
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 10, null, null);
        if (iFieldSeq == kVendorID)
        {
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOperatorsCode)
            field = new StringField(this, "OperatorsCode", 20, null, null);
        if (iFieldSeq == kProductChainID)
            field = new ProductChainField(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityID)
            field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEtd)
            field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAckDays)
            field = new ShortField(this, "AckDays", 2, null, null);
        if (iFieldSeq == kComments)
            field = new MemoField(this, "Comments", 32767, null, null);
        if (iFieldSeq == kProperties)
            field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItineraryDesc)
            field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDescSort)
            field = new ProductDescSort(this, "DescSort", 10, null, null);
        if (iFieldSeq == kProductType)
        {
            field = new ProductTypeAutoField(this, "ProductType", 15, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kProductCost)
        {
            field = new FullCurrencyField(this, "ProductCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kProductCostLocal)
        {
            field = new CurrencyField(this, "ProductCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kProductMessageTransportID)
            field = new MessageTransportSelect(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDisplayInventoryStatusID)
        {
            field = new InventoryStatusField(this, "DisplayInventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(BaseStatus.NO_STATUS));
            field.setVirtual(true);
        }
        if (iFieldSeq == kInventoryAvailability)
        {
            field = new ShortField(this, "InventoryAvailability", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kCurrencyCode)
        {
            field = new StringField(this, "CurrencyCode", 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kCurrencyCodeLocal)
        {
            field = new StringField(this, "CurrencyCodeLocal", 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kVendorName)
        {
            field = new StringField(this, "VendorName", 30, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kDisplayCostStatusID)
        {
            field = new CostStatusField(this, "DisplayCostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(BaseStatus.NULL_STATUS));
            field.setVirtual(true);
        }
        if (iFieldSeq == kPPCost)
        {
            field = new FullCurrencyField(this, "PPCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPPCostLocal)
        {
            field = new CurrencyField(this, "PPCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kRateID)
        {
            field = new BaseRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kClassID)
        {
            field = new BaseClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kProductPriceLocal)
        {
            field = new CurrencyField(this, "ProductPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPPPriceLocal)
        {
            field = new CurrencyField(this, "PPPriceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductLastField)
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
        if (keyArea == null) if (iKeyArea < kProductLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProductLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recProductPricing != null)
            m_recProductPricing.free();
        m_recProductPricing = null;
        if (m_recProductTerms != null)
            m_recProductTerms.free();
        m_recProductTerms = null;
        if (m_recInventory != null)
            m_recInventory.free();
        m_recInventory = null;
        if (m_recMessageProcessInfo != null)
            m_recMessageProcessInfo.free();
        m_recMessageProcessInfo = null;
        
        super.free();
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        BaseField productDesc = this.getField(Product.kDescription);
        BaseField productSort = this.getField(Product.kDescSort);
        CopyFieldHandler copyField = new CopyFieldHandler(kDescSort);
        productDesc.addListener(copyField);
        FieldToUpperHandler toUpper = new FieldToUpperHandler(null);
        productSort.addListener(toUpper);
        CheckForTheHandler checkThe = new CheckForTheHandler(null);
        productSort.addListener(checkThe);
        this.getField(Product.kProductType).addListener(new GetProductDescHandler(null));
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (Product.MESSAGE_DETAIL_SCREEN.equalsIgnoreCase(strCommand))
            return Product.MESSAGE_DETAIL_MODE;
        if (Product.PRODUCT_SEARCH_DETAIL.equalsIgnoreCase(strCommand))
            return Product.PRODUCT_SEARCH_DETAIL_GRID_SCREEN;
        return super.commandToDocType(strCommand);
    }
    /**
     * Get the physical message to do this task for this product type.
     * Note: Basically this method is here in case I want to add per-product
     * message definitions.
     * @param strMessageProcessorCode The code in the message info file.
     * @return The base physical message.
     */
    public TrxMessageHeader createProcessMessage(String strMessageProcessorCode, String strMessageTransport)
    {
        TrxMessageHeader trxMessageHeader = null;
        MessageProcessInfo recMessageProcessInfo = new MessageProcessInfo(Utility.getRecordOwner(this));
        MessageProcessInfo recMessageProcessInfoCurrent = (MessageProcessInfo)recMessageProcessInfo.getMessageProcessInfo(strMessageProcessorCode);
        if (recMessageProcessInfoCurrent != null)
            trxMessageHeader = recMessageProcessInfoCurrent.createProcessMessageHeader(this, strMessageTransport);
        recMessageProcessInfo.free();
        return trxMessageHeader;
    }
    /**
     * Get the physical message to do this task for this product type.
     * Note: Basically this method is here in case I want to add per-product
     * message definitions.
     * @param strMessageProcessorCode The code in the message info file.
     * @return The base physical message.
     */
    public TrxMessageHeader createProcessMessage(String strMessageInfoType, String strContactType, String strRequestType, String strMessageProcessType, String strProcessType, String strMessageTransport)
    {
        TrxMessageHeader trxMessageHeader = null;
        MessageProcessInfo recMessageProcessInfo = new MessageProcessInfo(Utility.getRecordOwner(this));
        MessageProcessInfo recMessageProcessInfoCurrent = (MessageProcessInfo)recMessageProcessInfo.getMessageProcessInfo(strMessageInfoType, strContactType, strRequestType, strMessageProcessType, strProcessType);
        if (recMessageProcessInfoCurrent != null)
            trxMessageHeader = recMessageProcessInfoCurrent.createProcessMessageHeader(this, strMessageTransport);
        recMessageProcessInfo.free();
        return trxMessageHeader;
    }
    /**
     * GetNextMessageDetailTarget Method.
     */
    public MessageDetailTarget getNextMessageDetailTarget()
    {
        if (this.getField(Hotel.kVendorID).isNull())
            return null;
        return (Vendor)((ReferenceField)this.getField(Hotel.kVendorID)).getReference();
    }
    /**
     * Get the message properties for this product.
     * @param strMessageName The message name.
     * @return A map with the message properties.
     */
    public TrxMessageHeader addMessageProperties(TrxMessageHeader trxMessageHeader)
    {
        return trxMessageHeader;
    }
    /**
     * GetMessageTransport Method.
     */
    public MessageTransport getMessageTransport(TrxMessageHeader trxMessageHeader)
    {
        return (MessageTransport)((ReferenceField)this.getField(Product.kProductMessageTransportID)).getReference();
    }
    /**
     * AddDestInfo Method.
     */
    public TrxMessageHeader addDestInfo(TrxMessageHeader trxMessageHeader)
    {
        return trxMessageHeader;
    }
    /**
     * Process this product information request (override this).
     */
    public BaseMessage processInfoRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        ProductMessageData messageData = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        Date dateTarget = messageData.getTargetDate();
        
        BaseProductResponse responseMessage = null;
        if (messageReply == null)
            messageReply = (BaseMessage)this.getMessageProcessInfo().createReplyMessage((BaseMessage)messageData.getMessage());
        responseMessage = (BaseProductResponse)messageReply.getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        
        int iProductStatus = InventoryStatus.VALID;
        String strErrorMessage = null;
        
        //?this.getField(Product.kDisplayInfoStatusID).setValue(iProductStatus);
        responseMessage.setMessageDataStatus(iProductStatus);
        if (strErrorMessage != null)
            responseMessage.setMessageDataError(strErrorMessage);
        return messageReply;
    }
    /**
     * Read the locally stored product cost (Override).
     */
    public BaseMessage processCostRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        return null;    // Unknown (Override this!)
    }
    /**
     * Get the est. time of this product.
     * @return The duration of this product (in seconds).
     */
    public long getLengthTime()
    {
        return 0; // Override
    }
    /**
     * Check the inventory for this detail.
     * @param message Contains all the update data for this check
     * @param fldTrxID If null, just check the inventory, if not null, update the inventory using this BookingDetail trxID.
     */
    public BaseMessage processAvailabilityRequestInMessage(BaseMessage messageIn, BaseMessage messageReply, BaseField fldTrxID)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        ProductMessageData messageData = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData bookingMessageData = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        Date dateTarget = messageData.getTargetDate();
        int iTargetAmount = bookingMessageData.getTargetPax();
        if (productRequest instanceof CancelRequest)    // CancelRequest (then pax = 0)
            iTargetAmount = 0;
        if (RequestType.BOOKING_CANCEL.equalsIgnoreCase(productRequest.getRequestType()))
            iTargetAmount = 0;
        int iRateID = messageData.getRateTypeID();
        int iClassID = messageData.getRateClassID();
        Object objOtherID = messageData.get(Product.OTHER_ID_PARAM);
        if (objOtherID == null)
            objOtherID = Inventory.NO_OTHER;
        int iOtherID = Integer.parseInt(objOtherID.toString());
        
        BaseProductResponse responseMessage = null;
        if (messageReply == null)
            messageReply = (BaseMessage)this.getMessageProcessInfo().createReplyMessage((BaseMessage)messageData.getMessage());
        responseMessage = (BaseProductResponse)messageReply.getMessageDataDesc(null);
        responseMessage.moveRequestInfoToReply(messageIn);
        
        // First, calculate the room cost
        Inventory recInventory = this.getInventory().getAvailability(this, dateTarget, iRateID, iClassID, iOtherID);
        int iAvailability = Inventory.NO_INVENTORY;
        if (recInventory != null)
            iAvailability = (int)recInventory.getField(Inventory.kAvailable).getValue();
        if (responseMessage instanceof ProductAvailabilityResponse)
        {
            ProductResponseMessageData responseMessageData = (ProductResponseMessageData)responseMessage.getMessageDataDesc(ProductAvailabilityResponse.PRODUCT_RESPONSE_MESSAGE);
            responseMessageData.setAvailability(iAvailability);
        }
        
        int iInventoryStatus = InventoryStatus.VALID;
        String strErrorMessage = null;
        if (iAvailability < iTargetAmount)
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
        else
        { // Okay
            if (fldTrxID != null) // If in update mode, update the availability
            {
                boolean bIsDeleted = false;   // todo (don) Fix this.
                int iErrorCode = recInventory.updateAvailability(iTargetAmount, fldTrxID, 0, bIsDeleted, null);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                {
                    iInventoryStatus = BaseDataStatus.NOT_VALID;
                    if ((this.getRecordOwner() != null)
                        && (this.getRecordOwner().getTask() != null))
                            strErrorMessage = this.getRecordOwner().getTask().getLastError(iErrorCode);
                    else
                        strErrorMessage = "Inventory not available";
                }
            }
                
        }
        this.getField(Product.kDisplayInventoryStatusID).setValue(iInventoryStatus);
        responseMessage.setMessageDataStatus(iInventoryStatus);
        if (strErrorMessage != null)
            responseMessage.setMessageDataError(strErrorMessage);
        
        ProductResponseMessageData responseMessageData = (ProductResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
        responseMessageData.put(BookingDetail.PRODUCT_ID, messageData.get(BookingDetail.PRODUCT_ID));
        responseMessageData.setMessageDataStatus(iInventoryStatus);   // Status for this segment
        if (strErrorMessage != null)
            responseMessageData.setMessageDataError(strErrorMessage);
        
        return messageReply;
    }
    /**
     * This is for products that can be externally booked.
     * @return the booking reply message with the proper params.
     */
    public BaseMessage processBookingRequestInMessage(BaseMessage messageIn, BaseMessage messageReply)
    {
        ProductRequest productRequest = (ProductRequest)messageIn.getMessageDataDesc(null);
        RecordOwner recordOwner = Utility.getRecordOwner(this);
        ProductMessageData messageData = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        ProductBookingResponse responseMessage = null;
        
        if (messageData.getProduct(this))
        {
            String strBookingNo = null;
            int iMessageStatus = BaseDataStatus.OKAY;
            String strErrorMessage = null;
        
            Date dateTarget = messageData.getTargetDate();
            DateField fldDepDate = new DateField(null, "TargetDate", DBConstants.DEFAULT_FIELD_LENGTH, DBConstants.BLANK, null);
            fldDepDate.setDate(dateTarget, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            dateTarget = fldDepDate.getDateTime();  // Make sure module date matches departure date.
        
            if (messageReply == null)
            {
                messageReply = new TreeMessage(null, null);
                responseMessage = this.getProductBookingResponse(productRequest.getRequestType(), messageReply, null);
            }
            else
                responseMessage = (ProductBookingResponse)messageReply.getMessageDataDesc(null);
            responseMessage.moveRequestInfoToReply(messageIn);
        
            String strMessageTransportID = null;
            if (productRequest.getMessage().getMessageHeader() != null)
                strMessageTransportID = (String)productRequest.getMessage().getMessageHeader().get(MessageTransport.TRANSPORT_ID_PARAM);
            BookingDetail recBookingDetail = this.getBookingDetail(Utility.getRecordOwner(this));
            MessageTransport recMessageTransport = new MessageTransport(Utility.getRecordOwner(this));
            boolean bIsDirectTransport = true;
            if (strMessageTransportID != null)
                if (recMessageTransport.getMessageTransport(strMessageTransportID) != null)
                    bIsDirectTransport = recMessageTransport.isDirectTransport();
            recMessageTransport.free();
            if (bIsDirectTransport)
            { // If direct DO NOT create a new booking (this detail is already part of a booking), Just reduce the inventory and return ok
                iMessageStatus = BaseDataStatus.OKAY;
                if (RequestType.BOOKING_CANCEL.equalsIgnoreCase(productRequest.getRequestType()))
                    iMessageStatus = BaseDataStatus.CANCELED;
            }
            else
            { 
                // Now create a new booking from the data passed in
                Booking recBooking = new Booking(recordOwner);
                Tour recTour = new Tour(recordOwner);
                BookingControl recBookingControl = new BookingControl(recordOwner);
                ProfileControl recProfileControl = new ProfileControl(recordOwner);
                recBooking.addControlDefaults(recBookingControl, recProfileControl);
            
                recBookingDetail.addDetailBehaviors(recBooking, recTour);
        
                try {
                    if (RequestType.BOOKING_ADD.equalsIgnoreCase(productRequest.getRequestType()))
                    {
                        recBooking.addNew();
                
                        TourHeader recTourHeader = this.getBookingTourHeader(recBookingControl);
                        recBooking.setupDefaultDesc(recTourHeader, fldDepDate);
                        int iErrorCode = recTour.setupTourFromHeader(recTourHeader, fldDepDate, recBooking.getField(Booking.kCode).toString(), recBooking.getField(Booking.kDescription).toString());
                        if (iErrorCode != DBConstants.NORMAL_RETURN)
                            return null;
                        recBooking.getField(Booking.kTourID).addListener(new CalcBookingDatesHandler(recTour, recTourHeader));
                        recBooking.getField(Booking.kTourID).moveFieldToThis(recTour.getField(Tour.kID));
                
                        recBooking.add();
                        strBookingNo = recBooking.getLastModified(DBConstants.OBJECT_ID_HANDLE).toString();
                        recBooking.setHandle(strBookingNo, DBConstants.OBJECT_ID_HANDLE);
                        recBooking.edit();
                        
                        BookingAnswer recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
                        BookingPax recBookingPax = null;
                        iErrorCode = ((Booking)recBooking).addTourDetail(recTour, recTourHeader, recBookingPax, recBookingAnswer, dateTarget, recBooking.getField(Booking.kAskForAnswer));
                        if (iErrorCode != DBConstants.NORMAL_RETURN)
                            return null;
        
                        this.addMessageBookingDetail(recBookingDetail, recBooking, recTour, strMessageTransportID, productRequest);
        
                        recBooking.getField(Booking.kBookingStatusID).setValue(BookingStatus.OKAY); // Make sure all downline components are ordered
                        recTour.refreshToCurrent(DBConstants.AFTER_UPDATE_TYPE, false);  // Make sure I have the latest copy
                        recTour.getField(Tour.kOrderComponents).setState(true);     // Book = Order Components! (just in case the previous line didn't do this)
                    }
                    else
                    {
                        strBookingNo = (String)messageData.get(BookingDetail.REMOTE_BOOKING_NO);
                        if ((strBookingNo == null) || (strBookingNo.length() == 0))
                        {   
                            iMessageStatus = BaseDataStatus.NOT_VALID;
                            strErrorMessage = "No booking number supplied";
                        }
                        else
                        {
                            recBooking.getField(Booking.kID).setString(strBookingNo);
                            int iOldKeyArea = recBooking.getDefaultOrder();
                            recBooking.setKeyArea(Booking.kIDKey);
                            if (!recBooking.seek(null))
                            {
                                iMessageStatus = BaseDataStatus.NOT_VALID;
                                strErrorMessage = "Invalid booking number supplied";
                            }
                            else
                            {
                                recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
                                if ((recTour == null) ||
                                        ((recTour.getEditMode() != DBConstants.EDIT_CURRENT) && (recTour.getEditMode() != DBConstants.EDIT_IN_PROGRESS)))
                                {   // Never
                                    iMessageStatus = BaseDataStatus.NOT_VALID;
                                    strErrorMessage = "No tour associated with this booking";
                                }
                            }
                            recBooking.setKeyArea(iOldKeyArea);
                            
                            if (iMessageStatus == BaseDataStatus.OKAY)
                            {
                                if (RequestType.BOOKING_CHANGE.equalsIgnoreCase(productRequest.getRequestType()))
                                {
                                    this.changeMessageBookingDetail(recBookingDetail, recBooking, recTour, strMessageTransportID, productRequest);
                                }
                                else // if (RequestType.BOOKING_CANCEL.equalsIgnoreCase(message.getRequestType()))
                                {
                                    int iCancelledStatusID = ((ReferenceField)recBooking.getField(Booking.kBookingStatusID)).getIDFromCode(BookingStatus.CANCELLED_CODE);
                                    recBooking.getField(Booking.kBookingStatusID).setValue(iCancelledStatusID);
                                }
                            }
                        }
                    }
        
                    if (iMessageStatus == BaseDataStatus.OKAY)
                    {
                        // Return the confirmation number
                        ProductResponseMessageData responseMessageData = (ProductResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
                        recBookingDetail.refreshToCurrent(DBConstants.AFTER_UPDATE_TYPE, false);
                        responseMessageData.handlePutRawRecordData(recBookingDetail);
        
                        responseMessageData.setRemoteBookingNo(strBookingNo);
                        iMessageStatus = (int)recTour.getField(Tour.kTourStatusID).getValue();
                        long iTimeoutMS = (long)recBookingControl.getField(BookingControl.kRemoteWaitTime).getValue() * 1000;
                        if (BaseStatus.isWaiting(iMessageStatus))
                        {
                            // Setup tour status listener
                            WaitForFieldChangeHandler listener = new WaitForFieldChangeHandler(iTimeoutMS);
                            recTour.getField(Tour.kTourStatusID).addListener(listener);
                            recTour.refreshToCurrent(DBConstants.AFTER_UPDATE_TYPE, false); // Start with the most recent version
                            iMessageStatus = (int)recTour.getField(Tour.kTourStatusID).getValue();
                        // Wait for the status to change (or timeout)
                            int iErrorCode = DBConstants.NORMAL_RETURN;
                            while (BaseStatus.isWaiting(iMessageStatus))
                            {
                                iErrorCode = listener.waitForChange();
                                if (iErrorCode == WaitForFieldChangeHandler.TIMEOUT_ERROR)
                                    break;
                                iMessageStatus = (int)recTour.getField(Tour.kTourStatusID).getValue();
                            }
                            if (BaseStatus.isWaiting(iMessageStatus))
                            {
                                strErrorMessage = null;
                                if (recBookingDetail != null)
                                    if ((recBookingDetail.getEditMode() == DBConstants.EDIT_CURRENT) || (recBookingDetail.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                                        strErrorMessage = recBookingDetail.getErrorMessage(BookingDetail.kProductStatusID);
                                if ((strErrorMessage == null) || (strErrorMessage.length() == 0))
                                    strErrorMessage = ((ReferenceField)recTour.getField(Tour.kTourStatusID)).getReference().getField(BaseDataStatus.kDescription).toString();
                            }
                        }
                        // Don't need to transfer the cost since you got it on the costing lookup
                        // todo(don) may want to transfer cost and line items for client comparison later
                        //msgBookingRateResponse.setProductCost(recBooking.getField(Booking.kGross).getValue());
                        //msgBookingRateResponse.putRawLineItemData(recBooking);
                
                        if (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                            recTour.set();
                        if (recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                            recBooking.set();
                    }
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    recProfileControl.free();
                    recBookingControl.free();
                    recTour.free();
                    recBooking.free();
                    fldDepDate.free();
                }
            }
            responseMessage.setMessageDataStatus(iMessageStatus);
            if (strErrorMessage != null)
                responseMessage.setMessageDataError(strErrorMessage);
        
            ProductResponseMessageData responseMessageData = (ProductResponseMessageData)responseMessage.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
            responseMessageData.put(BookingDetail.PRODUCT_ID, messageData.get(BookingDetail.PRODUCT_ID));
            responseMessageData.setMessageDataStatus(iMessageStatus);   // Status for this segment
            if (strErrorMessage != null)
                responseMessageData.setMessageDataError(strErrorMessage);
            
            recBookingDetail.free();
        }
        return messageReply;
    }
    /**
     * Add the booking detail that goes with the product in this message.
     */
    public int addMessageBookingDetail(BookingDetail recBookingDetail, Booking recBooking, Tour recTour, String strMessageTransportID, MessageRecordDesc productRequest) throws DBException
    {
        //+ String strDestination = null;   // todo(don) Need to figure out where this message was sent to (what is my xyz [soap?] address?)
        
        // HACK: I only did direct transport while I was testing since this would cause an endless message loop
        //x recBookingDetail.addListener(new SetDirectTransportHandler(null));
        
        return productRequest.handleGetRawRecordData(recBookingDetail);
    }
    /**
     * ChangeMessageBookingDetail Method.
     */
    public int changeMessageBookingDetail(BookingDetail recBookingDetail, Booking recBooking, Tour recTour, String strMessageTransportID, MessageRecordDesc productRequest) throws DBException
    {
        String strDestination = null;   // todo(don) Need to figure out where this message was sent to (what is my xyz [soap?] address?)
        
        // HACK: to make sure the local products were booked directly, otherwise I return a booking in progress to the remote client
        //x recBookingDetail.addListener(new SetDirectTransportHandler(null));
        
        return productRequest.handleGetRawRecordData(recBookingDetail);   // Add the booking detail
    }
    /**
     * Get the correct remote tour header for this product.
     */
    public TourHeader getBookingTourHeader(BookingControl recBookingControl)
    {
        return (TourHeader)((ReferenceField)recBookingControl.getField(BookingControl.kRemoteTourHeaderID)).getReference();
    }
    /**
     * GetProductBookingResponse Method.
     */
    public ProductBookingResponse getProductBookingResponse(String strRequestType, BaseMessage message, String strKey)
    {
        if (RequestType.BOOKING_CANCEL.equalsIgnoreCase(strRequestType))
            return new CancelResponse(message, strKey);
        return null;    // Override this!
    }
    /**
     * Create the booking detail for this product type.
     */
    public BookingDetail getBookingDetail(RecordOwner recordOwner)
    {
        return null;    // Override this!
    }
    /**
     * Given this date, return the best Starting date and time. If none,
     * just return the date that was passed in.
     */
    public Date getStartDate(Date date)
    {
        Date dateEtd = ((TimeField)this.getField(kEtd)).getDateTime();
        if (dateEtd == null)
            return date;
        Calendar calendar = Converter.gCalendar;
        calendar.setTime(dateEtd);
        int iHour = calendar.get(Calendar.HOUR_OF_DAY);
        int iMinute = calendar.get(Calendar.MINUTE);
        int iSecond = calendar.get(Calendar.SECOND);
        int iMillisecond = calendar.get(Calendar.MILLISECOND);
        
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, iHour);
        calendar.set(Calendar.MINUTE, iMinute);
        calendar.set(Calendar.SECOND, iSecond);
        calendar.set(Calendar.MILLISECOND, iMillisecond);
        date = calendar.getTime();
        return date;
    }
    /**
     * Get the inventory record.
     */
    public Inventory getInventory()
    {
        if (m_recInventory == null)
            m_recInventory = new Inventory(Utility.getRecordOwner(this));
        return m_recInventory;
    }
    /**
     * GetProductPricing Method.
     */
    public ProductPricing getProductPricing()
    {
        if (m_recProductPricing == null)
        {
            m_recProductPricing = this.createProductPricing(Utility.getRecordOwner(this));
            if (m_recProductPricing != null)
                if (m_recProductPricing.getRecordOwner() != null)
            {
                m_recProductPricing.getRecordOwner().removeRecord(m_recProductPricing);
                this.addListener(new FreeOnFreeHandler(m_recProductPricing));
            }
        }
        return m_recProductPricing;
    }
    /**
     * GetProductTerms Method.
     */
    public ProductTerms getProductTerms()
    {
        if (m_recProductTerms == null)
        {
            m_recProductTerms = new ProductTerms(Utility.getRecordOwner(this));
            if (m_recProductTerms.getRecordOwner() != null)
                m_recProductTerms.getRecordOwner().removeRecord(m_recProductTerms);
            this.addListener(new FreeOnFreeHandler(m_recProductTerms));
        }
        return m_recProductTerms;
    }
    /**
     * GetMessageProcessInfo Method.
     */
    public MessageProcessInfo getMessageProcessInfo()
    {
        if (m_recMessageProcessInfo == null)
        {
            RecordOwner recordOwner = Utility.getRecordOwner(this);
            m_recMessageProcessInfo = new MessageProcessInfo(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recMessageProcessInfo);
        }
        return m_recMessageProcessInfo;
    }
    /**
     * Get this product's code in the vendor's system and fake it if it doesn't exist.
     */
    public String getOperatorsCode()
    {
        String strProductCode = null;
        if (!this.getField(Product.kOperatorsCode).isNull())
            strProductCode = this.getField(Product.kOperatorsCode).toString();
        else if (!this.getField(Product.kCode).isNull())
            strProductCode = this.getField(Product.kCode).toString();
        else
            strProductCode = this.getField(Product.kDescription).toString();    // Hopefully not.
        return strProductCode;
    }
    /**
     * Get the product vendor's chain code.
     */
    public String getChainCode()
    {
        String strChainCode = null;
        Record recProductChain = ((ReferenceField)this.getField(Product.kProductChainID)).getReference();
        if (recProductChain != null)
            if ((recProductChain.getEditMode() == DBConstants.EDIT_CURRENT) || (recProductChain.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                if (!recProductChain.getField(ProductChain.kCode).isNull())
                    strChainCode = recProductChain.getField(ProductChain.kCode).toString();
        if ((strChainCode == null) || (strChainCode.length() == 0))
            return this.getOperatorsCode();
        return strChainCode;
    }
    /**
     * CreateProductPricing Method.
     */
    public ProductPricing createProductPricing(RecordOwner recordOwner)
    {
        return null;    // Override this!
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
        return DBConstants.ERROR_RETURN;    // No update done.
    }
    /**
     * Create the product record for this product type.
     */
    public static Product getProductRecord(String strProductType, RecordOwner recordOwner)
    {
        Product recProduct = null;
        if (Hotel.kHotelFile.equalsIgnoreCase(strProductType))
            recProduct = new Hotel(recordOwner);
        else if (Land.kLandFile.equalsIgnoreCase(strProductType))
            recProduct = new Land(recordOwner);
        else if (Air.kAirFile.equalsIgnoreCase(strProductType))
            recProduct = new Air(recordOwner);
        else if (Transportation.kTransportationFile.equalsIgnoreCase(strProductType))
            recProduct = new Transportation(recordOwner);
        else if (Car.kCarFile.equalsIgnoreCase(strProductType))
            recProduct = new Car(recordOwner);
        else if (Cruise.kCruiseFile.equalsIgnoreCase(strProductType))
            recProduct = new Cruise(recordOwner);
        else if (Item.kItemFile.equalsIgnoreCase(strProductType))
            recProduct = new Item(recordOwner);
        else if (TourHeader.kTourHeaderFile.equalsIgnoreCase(strProductType))
            recProduct = new TourHeader(recordOwner);
        else if (Tour.kTourFile.equalsIgnoreCase(strProductType))
            recProduct = new TourHeader(recordOwner);  // Yes - Tour header is a tour component
        return recProduct;
    }
    /**
     * SetProperty Method.
     */
    public boolean setProperty(String strKey, String strProperty)
    {
        ((PropertiesField)this.getField(Product.kProperties)).setProperty(strKey, strProperty);
        return true;
    }
    /**
     * Get this property.
     */
    public String getProperty(String strKey)
    {
        return ((PropertiesField)this.getField(Product.kProperties)).getProperty(strKey);
    }
    /**
     * MarkupPriceFromCost Method.
     */
    public void markupPriceFromCost(double dMarkup, boolean bMarkupOnlyIfNoPrice)
    {
        if ((!bMarkupOnlyIfNoPrice) || (this.getField(Product.kProductPriceLocal).getValue() == 0))
        {
            if (dMarkup == 0.00)
                this.getField(Product.kProductPriceLocal).setData(null);
            else
                this.getField(Product.kProductPriceLocal).setValue(Math.floor(this.getField(Product.kProductCostLocal).getValue() * (1 + dMarkup) * 100 + 0.5) / 100);
        }
    }

}
