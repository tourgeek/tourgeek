/**
 *  @(#)ProductResponseMsgReplyInProcessor.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.response.in;

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
import org.jbundle.base.message.trx.processor.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.main.msg.db.base.*;

/**
 *  ProductResponseMsgReplyInProcessor - .
 */
public class ProductResponseMsgReplyInProcessor extends BaseMessageReplyInProcessor
{
    /**
     * Default constructor.
     */
    public ProductResponseMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductResponseMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Add the behaviors.
     */
    public void addListeners()
    {
        super.addListeners();
        Record recBookingDetail = (BookingDetail)this.getMainRecord();
        if (recBookingDetail != null)
            if (this.getStatusFieldSeq() != BookingDetail.kInfoStatusID)    // An info change CAN trigger a pricing change
        {
            Record recBooking = ((ReferenceField)recBookingDetail.getField(BookingDetail.kBookingID)).getReferenceRecord();
            FieldListener listener = recBooking.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class);
            if (listener != null)
                listener.setEnabledListener(false); // A price change from a message can't change the pricing scheme
        }
    }
    /**
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        BaseMessage messageOrig = this.getOriginalMessage(internalMessage);
        BaseProductMessageDesc message = (BaseProductMessageDesc)internalMessage.getMessageDataDesc(null);
        if (messageOrig != null)
        {
            if (messageOrig.getMessageDataDesc(null) != null)
                if (messageOrig.getMessageDataDesc(null).getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE) instanceof ProductMessageData)
            {   // Always
                boolean bSuccess = false;
                for (int index = 1; ; index++)
                {
                    ProductMessageData productRequest = (ProductMessageData)messageOrig.getMessageDataDesc(null).getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
                    productRequest.setDataIndex(index, null);
                    if ((productRequest.get(BookingDetail.PRODUCT_ID) == null) && (productRequest.get(Product.CODE) == null))
                        break;  // End of product descriptions
                    ProductResponseMessageData productResponse = (ProductResponseMessageData)message.getMessageDataDesc(null).getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
                    productResponse.setDataIndex(index, null);  // This is where the response to that message is.
                    
                    String strRecord = (String)productRequest.get(DBParams.RECORD);
                    if ((strRecord == null)
                        || (!strRecord.equals(this.getMainRecord().getTableNames(false))))
                            return null;     // ERROR
                    String strObjectID = (String)productRequest.get(DBConstants.STRING_OBJECT_ID_HANDLE);
                    String strSentMessageKey = productRequest.getMessageKey(null).toString();
                    Record record = this.getRecord(strRecord);
                    try   {
                        if ((record.getOpenMode() & (DBConstants.LOCK_TYPE_MASK | DBConstants.LOCK_STRATEGY_MASK)) == 0)
                            if (this.getTask() != null)
                                record.setOpenMode(record.getOpenMode() | this.getTask().getDefaultLockType(record.getDatabaseType()));  // Never
                        record = record.setHandle(strObjectID, DBConstants.OBJECT_ID_HANDLE);
                        if (record != null)
                        {   // This will also notify anyone listening for this change.
                            String strOrigMessageKey = record.getField(this.getStatusFieldSeq() + BookingDetail.MESSAGE_KEY_OFFSET).toString();
                            int iCurrentStatus = (int)record.getField(this.getStatusFieldSeq()).getValue();
                            if (strSentMessageKey != null)
                                if (strSentMessageKey.equals(strOrigMessageKey))
                                {
                                    if ((iCurrentStatus == BaseStatus.REQUEST_SENT) || (iCurrentStatus == BaseStatus.MANUAL_REQUEST_SENT))
                                    {
                                        record.edit();
                                        int iErrorCode = message.getRawRecordData(record);
                                        if (iErrorCode == DBConstants.NORMAL_RETURN)
                                        {   // Always
                                            int iStatus = productResponse.getMessageDataStatus();
                                            if (iStatus == BaseDataStatus.NO_STATUS)
                                                iStatus = ((BaseProductResponse)message.getMessageDataDesc(null)).getMessageDataStatus();   // Use the message status
                                            int iFieldSeq = this.getStatusFieldSeq();
                                            iErrorCode = this.setRecordDataStatus(record, iFieldSeq, iStatus, productRequest);  // Make sure all the detail has this status
                                            if (record.getEditMode() == DBConstants.EDIT_IN_PROGRESS) // Previous action could have u/d record
                                                record.set();
                                        }
                                        bSuccess = true;
                                    }
                                    else
                                        Utility.getLogger().warning("Message already handled, status = " + iCurrentStatus);
                                }
                                else
                                    Utility.getLogger().warning("Message already handled, strSentMessageKey= "+ strSentMessageKey + " strOrigMessageKey= " + strOrigMessageKey);
                            this.updateLogFiles(internalMessage, bSuccess);  // Need to change log status to SENTOK (also associate return message log trx ID) (todo)
                        }
                    } catch (DBException ex)    {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return null;    // No reply from a reply
    }
    /**
     * SetRecordDataStatus Method.
     */
    public int setRecordDataStatus(Record record, int iFieldSeq, int iStatus, ProductMessageData productRequest)
    {
        return productRequest.setRecordDataStatus(record, iFieldSeq, iStatus);  // Make sure all the detail has this status
    }
    /**
     * GetStatusFieldSeq Method.
     */
    public int getStatusFieldSeq()
    {
        return -1;  // Override this
    }

}
