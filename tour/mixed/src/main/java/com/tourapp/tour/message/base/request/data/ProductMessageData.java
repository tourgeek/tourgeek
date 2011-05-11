/**
 *  @(#)ProductMessageData.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.request.data;

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
import com.tourapp.tour.message.base.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.thin.base.message.*;
import java.text.*;
import org.jbundle.model.db.*;

/**
 *  ProductMessageData - .
 */
public class ProductMessageData extends BaseProductMessageData
{
    public static final String OLD_DETAIL_DATE = "oldDetailDate";
    protected int m_iSyncedSourceFieldSeq = -1;
    protected int m_iSyncedDestFieldSeq = -1;
    /**
     * Default constructor.
     */
    public ProductMessageData()
    {
        super();
    }
    /**
     * ProductMessageData Method.
     */
    public ProductMessageData(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        if (strKey == null)
            strKey = ProductRequest.PRODUCT_MESSAGE;
        super.init(messageDataParent, strKey);
        this.setNodeType(NON_UNIQUE_NODE);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        new MessageFieldDesc(this, BookingDetail.PRODUCT_ID, Integer.class, MessageFieldDesc.REQUIRED, null)
        {
            public int getRawFieldData(Converter field)
            {   // Special logic - If there is a product CODE and this field has already been populated, don't move it again.
                if (((BaseMessageRecordDesc)this.getMessageDataParent()).get(Product.CODE) != null)
                    if (!field.getField().isNull())
                        return DBConstants.NORMAL_RETURN;
                return super.getRawFieldData(field);
            }
        };
        this.addMessageFieldDesc(BookingDetail.RATE_ID, Integer.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(BookingDetail.CLASS_ID, Integer.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(BookingDetail.DETAIL_DATE, Date.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(Product.PAX_PARAM, Short.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(Product.ROOM_TYPE_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | PaxCategory.CHILD_ID, null);
        this.addMessageFieldDesc(OLD_DETAIL_DATE, Date.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.NOT_UNIQUE, null);    // Not in key
    }
    /**
     * Get the data description for this param.
     */
    public MessageDataDesc getMessageDataDesc(String strParam)
    {
        String strParamOrig = strParam;
        if (strParam.startsWith(Product.ROOM_TYPE_PARAM))
            strParam = Product.ROOM_TYPE_PARAM;
        MessageDataDesc messageDataDesc = super.getMessageDataDesc(strParam);
        if (messageDataDesc != null)
            if (strParamOrig.startsWith(Product.ROOM_TYPE_PARAM))
                messageDataDesc.setKey(strParamOrig);
        return messageDataDesc;
    }
    /**
     * SetRecordDataStatus Method.
     */
    public int setRecordDataStatus(Record record, int iFieldSeq, int iStatus)
    {
        int iErrorCode = Constants.NORMAL_RETURN;
        int iRecordCount = this.getRecordCount(record);
        
        Rec recTargetRecord = this.setDataIndex(RESET_INDEX, record);   // Reset index if multiple
        
        for (int index = 1; index <= iRecordCount; index++)
        {
            Record recNext = (Record)this.setDataIndex(index, recTargetRecord);
            if (recNext == null)
                break;
            
            try {
                recNext.edit();
                recNext.getField(iFieldSeq).setValue(iStatus);
                if (iFieldSeq == m_iSyncedSourceFieldSeq)
                    recNext.getField(m_iSyncedDestFieldSeq).setValue(iStatus);  // Special synced status fields
                // No need to update recNext as setDataIndex does.
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
        this.setDataIndex(END_OF_NODES, recTargetRecord);
        return iErrorCode;
    }
    /**
     * Update the message key (in the booking detail) to match the message detail in this message.
     */
    public boolean updateMessageKeys(Record record, int iStatusType)
    {
        boolean bChanged = false;
        int iRecordCount = this.getRecordCount(record);
        
        Rec recTargetRecord = this.setDataIndex(RESET_INDEX, record);   // Reset index if multiple
        
        for (int index = 1; index <= iRecordCount; index++)
        {
            Record recNext = (Record)this.setDataIndex(index, recTargetRecord);
            if (recNext == null)
                break;
            
            try {
                recNext.edit();
                if (recNext.getRecordName().equals(record.getRecordName()))
                    if (recNext.getCounterField().equals(record.getCounterField()))
                        if (this.updateMessageKey(recNext, iStatusType))   // Make sure the current record has all the updates.
                            bChanged = true;
                if (this.updateMessageKey(recNext, iStatusType))   // Usually VALID
                    bChanged = true;
                if (!this.isCurrentDataRecord(recNext))
                    recNext.set();
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
        this.setDataIndex(END_OF_NODES, recTargetRecord);
        return bChanged;
    }
    /**
     * Update this message key to match the message key in this message.
     */
    public boolean updateMessageKey(Record record, int iStatusType)
    {
        String strMessageKeyLastTime = record.getField(iStatusType + BookingDetail.MESSAGE_KEY_OFFSET).toString();
        String strMessageKey = this.getMessageKey(null).toString();
        if (((strMessageKeyLastTime == null) && (strMessageKeyLastTime == strMessageKey))
            || (strMessageKeyLastTime.equals(strMessageKey)))
        { // No actual changes, return the status to the orig value
            return false; // If the data hasn't changed, don't change the status.
        }
        record.getField(iStatusType + BookingDetail.MESSAGE_KEY_OFFSET).setString(strMessageKey);
        return true;    // Key changed
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(FieldList record)
    {
        int iErrorCode = super.putRawRecordData(record);    // Note: You MUST call SUPER here to keep from looping endlessly
        
        BookingDetail recBookingDetail = (BookingDetail)record;
        if (recBookingDetail.getField(BookingDetail.kMarkupFromLast).isNull())
            this.putRawFieldData(recBookingDetail.getField(BookingDetail.kDetailDate));
        else
        {
            DateTimeField field = (DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate);
            String strKey = this.getFullKey(field.getFieldName());
            Calendar calendar = field.getCalendar();
            calendar.add(Calendar.YEAR, -1);
            Object objValue = calendar.getTime();
            Class<?> classData = this.getMessage().getNativeClassType(field.getField().getDataClass());
            try {
                objValue = Converter.convertObjectToDatatype(objValue, classData, null);  // I do this just to be careful.
            } catch (Exception ex) {
                objValue = null;
            }
            this.getMessage().putNative(strKey, objValue);
        }
        this.putRawFieldData(recBookingDetail.getField(BookingDetail.kProductID));
        Record recProduct = ((ReferenceField)recBookingDetail.getField(BookingDetail.kProductID)).getReference();
        if (recProduct != null)
        {
            if (!recProduct.getField(Product.kOperatorsCode).isNull())  // Operator's product code
                this.put(Product.CODE, recProduct.getField(Product.kOperatorsCode).toString());
            this.put(Product.PRODUCT_NAME_PARAM, recProduct.getField(Product.kDescription).toString());
        }
        // Get the old date
        FieldDataScratchHandler fieldDataScratchHandler = (FieldDataScratchHandler)recBookingDetail.getField(BookingDetail.kDetailDate).getListener(FieldDataScratchHandler.class);
        if (fieldDataScratchHandler != null)
        {
            Date dateOriginal = (Date)fieldDataScratchHandler.getOriginalData();  // Make sure you know the original date
            if (dateOriginal != null)
                this.put(OLD_DETAIL_DATE, dateOriginal);
        }
        
        this.put(Product.PAX_PARAM, new Short(recBookingDetail.getNoPax()));
        for (int iRoomType = PaxCategory.SINGLE_ID; iRoomType <= PaxCategory.CHILD_ID; iRoomType++)
        {
            short shPaxOfType = (short)recBookingDetail.getPaxInRoom(iRoomType);
            if (shPaxOfType > 0)
            {
                this.put(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomType), new Short(shPaxOfType));
            }
        }
        
        try   {
            if (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
            {       // Then refresh the record so I have and object ID.
                recBookingDetail.add();
                Object bookmark = recBookingDetail.getLastModified(DBConstants.OBJECT_ID_HANDLE);
                recBookingDetail.setHandle(bookmark, DBConstants.OBJECT_ID_HANDLE);
                recBookingDetail.edit();
            }
            if (recBookingDetail.getHandle(DBConstants.OBJECT_ID_HANDLE) != null)
                this.put(DBConstants.STRING_OBJECT_ID_HANDLE, recBookingDetail.getHandle(DBConstants.OBJECT_ID_HANDLE));
            this.put(DBParams.RECORD, recBookingDetail.getTableNames(false));
            this.putRawFieldData(recBookingDetail.getField(BookingDetail.kLastChanged));
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        this.putRawFieldData(recBookingDetail.getField(BookingDetail.kRateID));
        Record recRateType = ((ReferenceField)recBookingDetail.getField(BookingDetail.kRateID)).getReference();
        if (recRateType != null)
            this.put(Product.RATE_TYPE_DESC_PARAM, recRateType.getField(BaseRate.kDescription).getString());
        this.putRawFieldData(recBookingDetail.getField(BookingDetail.kClassID));
        Record recRateClass = ((ReferenceField)recBookingDetail.getField(BookingDetail.kClassID)).getReference();
        if (recRateClass != null)
            this.put(Product.RATE_CLASS_DESC_PARAM, recRateClass.getField(BaseClass.kDescription).toString());
        
        if (!recBookingDetail.getField(BookingDetail.kAckDays).isNull())
        {
            int iAckTime = (int)recBookingDetail.getField(BookingDetail.kAckDays).getValue();
            if (this.getMessage() != null)
                if (this.getMessage().getMessageHeader() instanceof TrxMessageHeader)
                {   // Always
                    int iMultiplier = 1;
                    try {
                        iMultiplier = ((Integer)Converter.convertObjectToDatatype(this.getMessage().getMessageHeader().get("timeoutMultiplier"), Integer.class, new Integer(1))).intValue();
                    } catch (Exception e) {
                        iMultiplier = 1;
                    }
                    iAckTime = iAckTime * iMultiplier;  // In seconds
                    ((TrxMessageHeader)this.getMessage().getMessageHeader()).put(TrxMessageHeader.MESSAGE_TIMEOUT, Integer.toString(iAckTime));
                }
        }
        return iErrorCode;
    }
    /**
     * GetRateTypeID Method.
     */
    public int getRateTypeID()
    {
        int iRateType = 0;
        try {
            Integer intRateType = (Integer)Converter.convertObjectToDatatype(this.get(BookingDetail.RATE_ID), Integer.class, null);
            if (intRateType != null)
                iRateType = intRateType.intValue();
        } catch (Exception e) {
        }
        return iRateType;
    }
    /**
     * GetTargetDate Method.
     */
    public Date getTargetDate()
    {
        return (Date)this.get(BookingDetail.DETAIL_DATE);
    }
    /**
     * GetRateClassID Method.
     */
    public int getRateClassID()
    {
        int iRateClass = 0;
        try {
            Integer intRateClass = (Integer)Converter.convertObjectToDatatype(this.get(BookingDetail.CLASS_ID), Integer.class, null);
            if (intRateClass != null)
                iRateClass = intRateClass.intValue();
        } catch (Exception e) {
        }
        return iRateClass;
    }
    /**
     * ConvertIDToProductName Method.
     */
    public String convertIDToProductName(RecordOwner recordOwner, Object objProductID)
    {
        String strProductName = DBConstants.BLANK;
        Product recProduct = this.getProductRecord(recordOwner, false);
        try {
            if (recProduct.setHandle(objProductID, DBConstants.BOOKMARK_HANDLE) != null)
                strProductName = recProduct.getField(Product.kDescription).toString();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recProduct.free();
        return strProductName;
    }
    /**
     * ConvertProductNameToID Method.
     */
    public Integer convertProductNameToID(RecordOwner recordOwner, String strProductName)
    {
        Object objProductID = null;
        Product recProduct = this.getProductRecord(recordOwner, false);
        try {
            recProduct.setKeyArea(Product.kDescSortKey);
            recProduct.getField(Product.kDescription).setString(strProductName);
            strProductName = recProduct.getField(Product.kDescSort).toString();
            if (recProduct.seek(">="))
            {
                if (recProduct.getField(Product.kDescription).toString().toUpperCase().startsWith(strProductName.toUpperCase()))
                    objProductID = recProduct.getField(Product.kID).getData();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recProduct.free();
        return (Integer)objProductID;
    }
    /**
     * ConvertIDToProductClass Method.
     */
    public String convertIDToProductClass(Object objProductClassID)
    {
        String strProductClass = DBConstants.BLANK;
        BaseClass recProductClass = this.getProductClass(null);
        try {
            if (recProductClass.setHandle(objProductClassID, DBConstants.BOOKMARK_HANDLE) != null)
                strProductClass = recProductClass.getField(BaseClass.kDescription).toString();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recProductClass.free();
        return strProductClass;
    }
    /**
     * ConvertProductClassCodeToID Method.
     */
    public Integer convertProductClassCodeToID(String strProductClassCode)
    {
        Object objProductClassID = null;
        BaseClass recProductClass = this.getProductClass(null);
        try {
            recProductClass.setKeyArea(BaseClass.kCodeKey);
            recProductClass.getField(BaseClass.kCode).setString(strProductClassCode);
            if (recProductClass.seek(DBConstants.EQUALS))
            {
                if (recProductClass.getField(BaseClass.kCode).toString().toUpperCase().startsWith(strProductClassCode.toUpperCase()))
                    objProductClassID = recProductClass.getField(BaseClass.kID).getData();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recProductClass.free();
        return (Integer)objProductClassID;
    }
    /**
     * ConvertProductClassNameToID Method.
     */
    public Integer convertProductClassNameToID(String strProductClassName)
    {
        Object objProductClassID = null;
        BaseClass recProductClass = this.getProductClass(null);
        try {
            recProductClass.setKeyArea(BaseClass.kDescriptionKey);
            recProductClass.getField(BaseClass.kDescription).setString(strProductClassName);
            if (recProductClass.seek(">="))
            {
                if (recProductClass.getField(BaseClass.kDescription).toString().toUpperCase().startsWith(strProductClassName.toUpperCase()))
                    objProductClassID = recProductClass.getField(BaseClass.kID).getData();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recProductClass.free();
        return (Integer)objProductClassID;
    }
    /**
     * GetProductClass Method.
     */
    public BaseClass getProductClass(RecordOwner recordOwner)
    {
        return null;    // Override this!
    }
    /**
     * ConvertIDToProductRatePlan Method.
     */
    public String convertIDToProductRatePlan(Object objRatePlanID)
    {
        String strProductRate = DBConstants.BLANK;
        BaseRate recProductRate = this.getProductRate(null);
        try {
            if (recProductRate.setHandle(objRatePlanID, DBConstants.BOOKMARK_HANDLE) != null)
                strProductRate = recProductRate.getField(BaseRate.kDescription).toString();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recProductRate.free();
        return strProductRate;
    }
    /**
     * ConvertProductRatePlanToID Method.
     */
    public Integer convertProductRatePlanToID(String strRatePlanDesc)
    {
        Object objProductRateID = null;
        BaseRate recProductRate = this.getProductRate(null);
        try {
            recProductRate.setKeyArea(BaseRate.kDescriptionKey);
            recProductRate.getField(BaseRate.kDescription).setString(strRatePlanDesc);
            if (recProductRate.seek(">="))
            {
                if (recProductRate.getField(BaseRate.kDescription).toString().toUpperCase().startsWith(strRatePlanDesc.toUpperCase()))
                    objProductRateID = recProductRate.getField(BaseRate.kID).getData();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recProductRate.free();
        return (Integer)objProductRateID;
    }
    /**
     * GetProductRate Method.
     */
    public BaseRate getProductRate(RecordOwner recordOwner)
    {
        return null;    // Override this
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(FieldList record)
    {
        int iErrorCode = Constants.NORMAL_RETURN;
        BookingDetail recBookingDetail = (BookingDetail)record;
        
        this.getRawFieldData(recBookingDetail.getField(BookingDetail.kDetailDate));
        if ((this.get(Product.CODE) != null) && (!DBConstants.BLANK.equals(this.get(Product.CODE))))
        {
            Product recProduct = recBookingDetail.getProduct();
            int iOldOrder = recProduct.getDefaultOrder();
            try {
                recProduct.getField(Product.kCode).setString((String)this.get(Product.CODE));
                recProduct.setKeyArea(Product.kCodeKey);
                if (recProduct.seek(null))
                    recBookingDetail.getField(BookingDetail.kProductID).moveFieldToThis(recProduct.getField(Product.kID));
            } catch (DBException ex)    {
                ex.printStackTrace();
            } finally {
                recProduct.setKeyArea(iOldOrder);
            }
        }
        if (recBookingDetail.getField(BookingDetail.kProductID).isNull())
            this.getRawFieldData(recBookingDetail.getField(BookingDetail.kProductID));
        
        this.getRawFieldData(recBookingDetail.getField(BookingDetail.kRateID));
        this.getRawFieldData(recBookingDetail.getField(BookingDetail.kClassID));
        
        iErrorCode = super.getRawRecordData(record);
        
        return iErrorCode;
    }
    /**
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(FieldList record)
    {
        int iField = -1;
        if (record.getField(BookingDetail.kInfoStatusID).getValue() != InfoStatus.VALID)
        {
            iField = BookingDetail.kInfoStatusID;        // The information must be valid to lookup the price
            if (this.getMessageDataParent() instanceof ProductRequest)
                if (RequestType.INFORMATION.equalsIgnoreCase(((ProductRequest)this.getMessageDataParent()).getRequestType()))
                    iField = -1;    // Special case - Info status not required for an info request
        }
        if (record.getField(BookingDetail.kDetailDate).isNull())
            if (this.getMessageFieldDesc(BookingDetail.DETAIL_DATE) != null)
                if (this.getMessageFieldDesc(BookingDetail.DETAIL_DATE).isRequired())
                    iField = BookingDetail.kDetailDate;
        if (record.getField(BookingDetail.kProductID).isNull())
            iField = BookingDetail.kProductID;
        if (record.getField(BookingDetail.kRateID).isNull())
            if (this.getMessageFieldDesc(BookingDetail.RATE_ID) != null)
                if (this.getMessageFieldDesc(BookingDetail.RATE_ID).isRequired())
                    iField = BookingDetail.kRateID;
        if (record.getField(BookingDetail.kClassID).isNull())
            if (this.getMessageFieldDesc(BookingDetail.CLASS_ID) != null)
                if (this.getMessageFieldDesc(BookingDetail.CLASS_ID).isRequired())
                    iField = BookingDetail.kClassID;
        int iStatus = super.checkRequestParams(record);
        if (iField != -1)
        {
            iStatus = CostStatus.DATA_REQUIRED;        // The information must be valid to lookup the price
            String strError = "Data required in the {0} field";
            if (record.getTask() != null)
                strError = record.getTask().getApplication().getResources(ThinResourceConstants.ERROR_RESOURCE, true).getString(strError);
            strError = MessageFormat.format(strError, record.getField(iField).getFieldDesc());
            ((BookingDetail)record).setErrorMessage((ProductRequest)this.getMessageDataParent(), strError);
        }
        return iStatus;
    }
    /**
     * SetSyncFields Method.
     */
    public void setSyncFields(int iSyncedSourceFieldSeq, int iSyncedDestFieldSeq)
    {
        m_iSyncedSourceFieldSeq = iSyncedSourceFieldSeq;
        m_iSyncedDestFieldSeq = iSyncedDestFieldSeq;
    }

}
