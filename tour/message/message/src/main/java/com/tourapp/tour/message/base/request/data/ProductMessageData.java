/**
  * @(#)ProductMessageData.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.message.base.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.thin.base.message.*;
import java.text.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.db.*;

/**
 *  ProductMessageData - .
 */
public class ProductMessageData extends BaseProductMessageData
{
    public static final String PRODUCT_MESSAGE = "productMessage";
    public static final String OLD_DETAIL_DATE = "oldDetailDate";
    protected String m_iSyncedSourceFieldSeq = null;
    protected String m_iSyncedDestFieldSeq = null;
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
            strKey = ProductMessageData.PRODUCT_MESSAGE;
        super.init(messageDataParent, strKey);
        this.setNodeType(NON_UNIQUE_NODE);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        new MessageFieldDesc(this, BookingDetailModel.PRODUCT_ID, Integer.class, MessageFieldDesc.REQUIRED, null)
        {
            public int getRawFieldData(Converter field)
            {   // Special logic - If there is a product CODE and this field has already been populated, don't move it again.
                if (((BaseMessageRecordDesc)this.getMessageDataParent()).get(ProductModel.CODE) != null)
                    if (!field.getField().isNull())
                        return DBConstants.NORMAL_RETURN;
                return super.getRawFieldData(field);
            }
        };
        this.addMessageFieldDesc(BookingDetailModel.RATE_ID, Integer.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(BookingDetailModel.CLASS_ID, Integer.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(BookingDetailModel.DETAIL_DATE, Date.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(ProductModel.PAX_PARAM, Short.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(ProductModel.ROOM_TYPE_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | PaxCategoryModel.CHILD_ID, null);
        this.addMessageFieldDesc(OLD_DETAIL_DATE, Date.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.NOT_UNIQUE, null);    // Not in key
    }
    /**
     * Get the data description for this param.
     */
    public MessageDataDesc getMessageDataDesc(String strParam)
    {
        String strParamOrig = strParam;
        if (strParam.startsWith(ProductModel.ROOM_TYPE_PARAM))
            strParam = ProductModel.ROOM_TYPE_PARAM;
        MessageDataDesc messageDataDesc = super.getMessageDataDesc(strParam);
        if (messageDataDesc != null)
            if (strParamOrig.startsWith(ProductModel.ROOM_TYPE_PARAM))
                messageDataDesc.setKey(strParamOrig);
        return messageDataDesc;
    }
    /**
     * SetRecordDataStatus Method.
     */
    public int setRecordDataStatus(Record record, String iFieldSeq, int iStatus)
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
                if (iFieldSeq.equals(m_iSyncedSourceFieldSeq))
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
    public boolean updateMessageKeys(Record record, String iStatusType)
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
    public boolean updateMessageKey(Record record, String statusType)
    {
        String strMessageKeyLastTime = record.getField(record.getFieldSeq(statusType) + BookingDetailModel.MESSAGE_KEY_OFFSET).toString();
        String strMessageKey = this.getMessageKey(null).toString();
        if (((strMessageKeyLastTime == null) && (strMessageKeyLastTime == strMessageKey))
            || (strMessageKeyLastTime.equals(strMessageKey)))
        { // No actual changes, return the status to the orig value
            return false; // If the data hasn't changed, don't change the status.
        }
        record.getField(record.getFieldSeq(statusType) + BookingDetailModel.MESSAGE_KEY_OFFSET).setString(strMessageKey);
        return true;    // Key changed
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);    // Note: You MUST call SUPER here to keep from looping endlessly
        
        BookingDetailModel recBookingDetail = (BookingDetailModel)record;
        if (recBookingDetail.getField(BookingDetailModel.MARKUP_FROM_LAST).isNull())
            this.putRawFieldData(recBookingDetail.getField(BookingDetailModel.DETAIL_DATE));
        else
        {
            DateTimeField field = (DateTimeField)recBookingDetail.getField(BookingDetailModel.DETAIL_DATE);
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
        this.putRawFieldData(recBookingDetail.getField(BookingDetailModel.PRODUCT_ID));
        Record recProduct = ((ReferenceField)recBookingDetail.getField(BookingDetailModel.PRODUCT_ID)).getReference();
        if (recProduct != null)
        {
            if (!recProduct.getField(ProductModel.OPERATORS_CODE).isNull())  // Operator's product code
                this.put(ProductModel.CODE, recProduct.getField(ProductModel.OPERATORS_CODE).toString());
            this.put(ProductModel.PRODUCT_NAME_PARAM, recProduct.getField(ProductModel.DESCRIPTION).toString());
        }
        // Get the old date
        FieldDataScratchHandler fieldDataScratchHandler = (FieldDataScratchHandler)((Record)recBookingDetail).getField(BookingDetailModel.DETAIL_DATE).getListener(FieldDataScratchHandler.class);
        if (fieldDataScratchHandler != null)
        {
            Date dateOriginal = (Date)fieldDataScratchHandler.getOriginalData();  // Make sure you know the original date
            if (dateOriginal != null)
                this.put(OLD_DETAIL_DATE, dateOriginal);
        }
        
        this.put(ProductModel.PAX_PARAM, new Short(recBookingDetail.getNoPax()));
        for (int iRoomType = PaxCategoryModel.SINGLE_ID; iRoomType <= PaxCategoryModel.CHILD_ID; iRoomType++)
        {
            short shPaxOfType = (short)recBookingDetail.getPaxInRoom(iRoomType);
            if (shPaxOfType > 0)
            {
                this.put(ProductModel.ROOM_TYPE_PARAM + Integer.toString(iRoomType), new Short(shPaxOfType));
            }
        }
        
        try   {
            if (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
            {       // Then refresh the record so I have and object ID.
                recBookingDetail.getTable().add(recBookingDetail);
                Object bookmark = recBookingDetail.getTable().getLastModified(DBConstants.OBJECT_ID_HANDLE);
                recBookingDetail.getTable().setHandle(bookmark, DBConstants.OBJECT_ID_HANDLE);
                recBookingDetail.getTable().edit();
            }
            if (recBookingDetail.getTable().getHandle(DBConstants.OBJECT_ID_HANDLE) != null)
                this.put(DBConstants.STRING_OBJECT_ID_HANDLE, recBookingDetail.getTable().getHandle(DBConstants.OBJECT_ID_HANDLE));
            this.put(DBParams.RECORD, recBookingDetail.getTableNames(false));
            this.putRawFieldData(recBookingDetail.getField(BookingDetailModel.LAST_CHANGED));
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        this.putRawFieldData(recBookingDetail.getField(BookingDetailModel.RATE_ID));
        Record recRateType = ((ReferenceField)recBookingDetail.getField(BookingDetailModel.RATE_ID)).getReference();
        if (recRateType != null)
            this.put(ProductModel.RATE_TYPE_DESC_PARAM, recRateType.getField(BaseRateModel.DESCRIPTION).getString());
        this.putRawFieldData(recBookingDetail.getField(BookingDetailModel.CLASS_ID));
        Record recRateClass = ((ReferenceField)recBookingDetail.getField(BookingDetailModel.CLASS_ID)).getReference();
        if (recRateClass != null)
            this.put(ProductModel.RATE_CLASS_DESC_PARAM, recRateClass.getField(BaseClassModel.DESCRIPTION).toString());
        
        if (!recBookingDetail.getField(BookingDetailModel.ACK_DAYS).isNull())
        {
            int iAckTime = (int)recBookingDetail.getField(BookingDetailModel.ACK_DAYS).getValue();
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
            Integer intRateType = (Integer)Converter.convertObjectToDatatype(this.get(BookingDetailModel.RATE_ID), Integer.class, null);
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
        return (Date)this.get(BookingDetailModel.DETAIL_DATE);
    }
    /**
     * GetRateClassID Method.
     */
    public int getRateClassID()
    {
        int iRateClass = 0;
        try {
            Integer intRateClass = (Integer)Converter.convertObjectToDatatype(this.get(BookingDetailModel.CLASS_ID), Integer.class, null);
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
        ProductModel recProduct = this.getProductRecord(recordOwner, false);
        try {
            if (recProduct.getTable().setHandle(objProductID, DBConstants.BOOKMARK_HANDLE) != null)
                strProductName = recProduct.getField(ProductModel.DESCRIPTION).toString();
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
        ProductModel recProduct = this.getProductRecord(recordOwner, false);
        try {
            recProduct.setKeyArea(ProductModel.DESC_SORT_KEY);
            recProduct.getField(ProductModel.DESCRIPTION).setString(strProductName);
            strProductName = recProduct.getField(ProductModel.DESC_SORT).toString();
            if (recProduct.getTable().seek(">="))
            {
                if (recProduct.getField(ProductModel.DESCRIPTION).toString().toUpperCase().startsWith(strProductName.toUpperCase()))
                    objProductID = recProduct.getField(ProductModel.ID).getData();
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
        BaseClassModel recProductClass = this.getProductClass(null);
        try {
            if (recProductClass.getTable().setHandle(objProductClassID, DBConstants.BOOKMARK_HANDLE) != null)
                strProductClass = recProductClass.getField(BaseClassModel.DESCRIPTION).toString();
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
        BaseClassModel recProductClass = this.getProductClass(null);
        try {
            recProductClass.setKeyArea(BaseClassModel.CODE_KEY);
            recProductClass.getField(BaseClassModel.CODE).setString(strProductClassCode);
            if (recProductClass.getTable().seek(DBConstants.EQUALS))
            {
                if (recProductClass.getField(BaseClassModel.CODE).toString().toUpperCase().startsWith(strProductClassCode.toUpperCase()))
                    objProductClassID = recProductClass.getField(BaseClassModel.ID).getData();
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
        BaseClassModel recProductClass = this.getProductClass(null);
        try {
            recProductClass.setKeyArea(BaseClassModel.DESCRIPTION_KEY);
            recProductClass.getField(BaseClassModel.DESCRIPTION).setString(strProductClassName);
            if (recProductClass.getTable().seek(">="))
            {
                if (recProductClass.getField(BaseClassModel.DESCRIPTION).toString().toUpperCase().startsWith(strProductClassName.toUpperCase()))
                    objProductClassID = recProductClass.getField(BaseClassModel.ID).getData();
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
    public BaseClassModel getProductClass(RecordOwner recordOwner)
    {
        return null;    // Override this!
    }
    /**
     * ConvertIDToProductRatePlan Method.
     */
    public String convertIDToProductRatePlan(Object objRatePlanID)
    {
        String strProductRate = DBConstants.BLANK;
        BaseRateModel recProductRate = this.getProductRate(null);
        try {
            if (recProductRate.getTable().setHandle(objRatePlanID, DBConstants.BOOKMARK_HANDLE) != null)
                strProductRate = recProductRate.getField(BaseRateModel.DESCRIPTION).toString();
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
        BaseRateModel recProductRate = this.getProductRate(null);
        try {
            recProductRate.setKeyArea(BaseRateModel.DESCRIPTION_KEY);
            recProductRate.getField(BaseRateModel.DESCRIPTION).setString(strRatePlanDesc);
            if (recProductRate.getTable().seek(">="))
            {
                if (recProductRate.getField(BaseRateModel.DESCRIPTION).toString().toUpperCase().startsWith(strRatePlanDesc.toUpperCase()))
                    objProductRateID = recProductRate.getField(BaseRateModel.ID).getData();
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
    public BaseRateModel getProductRate(RecordOwner recordOwner)
    {
        return null;    // Override this
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iErrorCode = Constants.NORMAL_RETURN;
        BookingDetailModel recBookingDetail = (BookingDetailModel)record;
        
        this.getRawFieldData(recBookingDetail.getField(BookingDetailModel.DETAIL_DATE));
        if ((this.get(ProductModel.CODE) != null) && (!DBConstants.BLANK.equals(this.get(ProductModel.CODE))))
        {
            ProductModel recProduct = recBookingDetail.getProduct();
            int iOldOrder = recProduct.getDefaultOrder();
            try {
                recProduct.getField(ProductModel.CODE).setString((String)this.get(ProductModel.CODE));
                recProduct.setKeyArea(ProductModel.CODE_KEY);
                if (recProduct.getTable().seek(null))
                    ((BaseField)recBookingDetail.getField(BookingDetailModel.PRODUCT_ID)).moveFieldToThis((BaseField)recProduct.getField(ProductModel.ID));
            } catch (DBException ex)    {
                ex.printStackTrace();
            } finally {
                ((Record)recProduct).setKeyArea(iOldOrder);
            }
        }
        if (recBookingDetail.getField(BookingDetailModel.PRODUCT_ID).isNull())
            this.getRawFieldData(recBookingDetail.getField(BookingDetailModel.PRODUCT_ID));
        
        this.getRawFieldData(recBookingDetail.getField(BookingDetailModel.RATE_ID));
        this.getRawFieldData(recBookingDetail.getField(BookingDetailModel.CLASS_ID));
        
        iErrorCode = super.getRawRecordData(record);
        
        return iErrorCode;
    }
    /**
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(Rec record)
    {
        String iField = null;
        if (record.getField(BookingDetailModel.INFO_STATUS_ID).getValue() != InfoStatusModel.VALID)
        {
            iField = BookingDetailModel.INFO_STATUS_ID;        // The information must be valid to lookup the price
            if (this.getMessageDataParent() instanceof ProductRequest)
                if (RequestType.INFORMATION.equalsIgnoreCase(((ProductRequest)this.getMessageDataParent()).getRequestType()))
                    iField = null;    // Special case - Info status not required for an info request
        }
        if (record.getField(BookingDetailModel.DETAIL_DATE).isNull())
            if (this.getMessageFieldDesc(BookingDetailModel.DETAIL_DATE) != null)
                if (this.getMessageFieldDesc(BookingDetailModel.DETAIL_DATE).isRequired())
                    iField = BookingDetailModel.DETAIL_DATE;
        if (record.getField(BookingDetailModel.PRODUCT_ID).isNull())
            iField = BookingDetailModel.PRODUCT_ID;
        if (record.getField(BookingDetailModel.RATE_ID).isNull())
            if (this.getMessageFieldDesc(BookingDetailModel.RATE_ID) != null)
                if (this.getMessageFieldDesc(BookingDetailModel.RATE_ID).isRequired())
                    iField = BookingDetailModel.RATE_ID;
        if (record.getField(BookingDetailModel.CLASS_ID).isNull())
            if (this.getMessageFieldDesc(BookingDetailModel.CLASS_ID) != null)
                if (this.getMessageFieldDesc(BookingDetailModel.CLASS_ID).isRequired())
                    iField = BookingDetailModel.CLASS_ID;
        int iStatus = super.checkRequestParams(record);
        if (iField != null)
        {
            iStatus = CostStatusModel.DATA_REQUIRED;        // The information must be valid to lookup the price
            String strError = "Data required in the {0} field";
            if (record.getTask() != null)
                strError = record.getTask().getApplication().getResources(ThinResourceConstants.ERROR_RESOURCE, true).getString(strError);
            strError = MessageFormat.format(strError, record.getField(iField).getFieldDesc());
            ((BookingDetailModel)record).setErrorMessage((ProductRequest)this.getMessageDataParent(), strError);
        }
        return iStatus;
    }
    /**
     * SetSyncFields Method.
     */
    public void setSyncFields(String iSyncedSourceFieldSeq, String iSyncedDestFieldSeq)
    {
        m_iSyncedSourceFieldSeq = iSyncedSourceFieldSeq;
        m_iSyncedDestFieldSeq = iSyncedDestFieldSeq;
    }

}
