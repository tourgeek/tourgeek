/**
 * @(#)BaseProductResponse.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.response;

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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.base.message.core.trx.*;
import java.math.*;
import javax.xml.datatype.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.message.base.response.*;

/**
 *  BaseProductResponse - .
 */
public class BaseProductResponse extends BaseProductMessageDesc
     implements BaseProductResponseModel
{
    public static final String PRODUCT_RESPONSE_MESSAGE = "productResponse";
    /**
     * Default constructor.
     */
    public BaseProductResponse()
    {
        super();
    }
    /**
     * BaseProductResponse Method.
     */
    public BaseProductResponse(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        super.init(messageDataParent, strKey);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        this.addMessageDataDesc(this.createProductResponseMessageData());
        this.addMessageFieldDesc(BaseDataStatus.DATA_STATUS, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BaseDataStatus.DATA_ERROR_MESSAGE, String.class, MessageFieldDesc.OPTIONAL, null);
    }
    /**
     * CreateProductResponseMessageData Method.
     */
    public ProductResponseMessageData createProductResponseMessageData()
    {
        return new ProductResponseMessageData(this, PRODUCT_RESPONSE_MESSAGE);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iErrorCode = Constants.NORMAL_RETURN;
        {
            MessageRecordDesc messageData = (MessageRecordDesc)this.getMessageDataDesc(PRODUCT_RESPONSE_MESSAGE);
            if (messageData != null)
                iErrorCode = messageData.getRawRecordData(record);
            if (iErrorCode != Constants.NORMAL_RETURN)
                return iErrorCode;
        }
        // DO NOT call inherited
        BookingDetail recBookingDetail = (BookingDetail)record;
        int iInfoStatus = BaseDataStatus.VALID;
        if (this.getMessage().get(BaseDataStatus.DATA_STATUS) != null)
            iInfoStatus = this.getMessageDataStatus();
        if (iInfoStatus != BaseDataStatus.VALID)
            recBookingDetail.setErrorMessage(this, this.getMessageDataError());
        else
            recBookingDetail.setErrorMessage(this, null);
        return iErrorCode;
    }
    /**
     * SetMessageDataStatus Method.
     */
    public void setMessageDataStatus(int iMessageStatus)
    {
        this.put(BaseDataStatus.DATA_STATUS, new Integer(iMessageStatus));
    }
    /**
     * SetMessageDataError Method.
     */
    public void setMessageDataError(String strErrorMessage)
    {
        this.put(BaseDataStatus.DATA_ERROR_MESSAGE, strErrorMessage);
    }
    /**
     * GetMessageDataStatus Method.
     */
    public int getMessageDataStatus()
    {
        Integer intMessageStatus = (Integer)this.get(BaseDataStatus.DATA_STATUS);
        if (intMessageStatus == null)
            intMessageStatus = new Integer(BaseDataStatus.NO_STATUS);
        return intMessageStatus.intValue();
    }
    /**
     * GetMessageDataError Method.
     */
    public String getMessageDataError()
    {
        String strErrorMessage = (String)this.get(BaseDataStatus.DATA_ERROR_MESSAGE);
        if (strErrorMessage == null)
            strErrorMessage = DBConstants.BLANK;
        return strErrorMessage;
    }
    /**
     * Get the key prefix for this type of message.
     */
    public String getKeyPrefix()
    {
        return null;
    }
    /**
     * Move the pertinenent information from the request to this reply message.
     */
    public void moveRequestInfoToReply(Message messageRequest)
    {
        super.moveRequestInfoToReply(messageRequest);
        
        BaseProductMessageDesc baseProductMessage = (BaseProductMessageDesc)((BaseMessage)messageRequest).getMessageDataDesc(null); // This is a given
        ProductMessageData productMessageDesc = (ProductMessageData)baseProductMessage.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        int iNodeIndex = 0;
        if (productMessageDesc != null)
            iNodeIndex = productMessageDesc.getNodeIndex();
        ProductResponseMessageData reponseMessageData = (ProductResponseMessageData)this.getMessageDataDesc(PRODUCT_RESPONSE_MESSAGE);
        reponseMessageData.setDataIndex(iNodeIndex, null);    // Match the message node index
        
        this.put(DBConstants.STRING_OBJECT_ID_HANDLE, baseProductMessage.get(DBConstants.STRING_OBJECT_ID_HANDLE));
        this.put(DBParams.RECORD, baseProductMessage.get(DBParams.RECORD));
        if (this.get(DBParams.TIMESTAMP) == null)
            this.put(DBParams.TIMESTAMP, baseProductMessage.get(DBParams.TIMESTAMP));
        if (this.getMessageFieldDesc(MESSAGE_TIMESTAMP) != null)
            if (this.get(MESSAGE_TIMESTAMP) == null)
                this.getMessageFieldDesc(MESSAGE_TIMESTAMP).put(this.getXMLTimeStamp());
    }
    /**
     * GetXMLTimeStamp Method.
     */
    public XMLGregorianCalendar getXMLTimeStamp()
    {
        GregorianCalendar cal = new GregorianCalendar();
        DatatypeFactory dt;
        try {
            dt = DatatypeFactory.newInstance();
            return dt.newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
