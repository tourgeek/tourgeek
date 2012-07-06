/**
  * @(#)ProductInformationResponse.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  ProductInformationResponse - .
 */
public class ProductInformationResponse extends BaseProductResponse
{
    /**
     * Default constructor.
     */
    public ProductInformationResponse()
    {
        super();
    }
    /**
     * ProductInformationResponse Method.
     */
    public ProductInformationResponse(MessageDataParent messageDataParent, String strKey)
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
     * Get the key prefix for this type of message.
     */
    public String getKeyPrefix()
    {
        return "message.information.";
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        ProductResponseMessageData messageData = (ProductResponseMessageData)this.getMessageDataDesc(PRODUCT_RESPONSE_MESSAGE);
        messageData.addMessageFieldDesc(ProductModel.PRODUCT_NAME_PARAM, String.class, MessageFieldDesc.REQUIRED, null);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iErrorCode = super.getRawRecordData(record);
        
        if (iErrorCode == DBConstants.NORMAL_RETURN)
        {
            BookingDetailModel recBookingDetail = (BookingDetailModel)record;
            boolean[] rgbListeners = ((BaseField)recBookingDetail.getField(BookingDetailModel.INFO_STATUS_REQUEST)).setEnableListeners(false); // No Echo
            iErrorCode = recBookingDetail.setDetailProductInfo(null, null, null, null, null, null, null);
            ((Record)recBookingDetail).getField(BookingDetailModel.INFO_STATUS_REQUEST).setEnableListeners(rgbListeners);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
            {
                int iMessageStatus = BaseDataStatusModel.NOT_VALID;
                this.setMessageDataStatus(iMessageStatus);
                String setError = "Error";
                if (((Record)recBookingDetail).getRecordOwner() != null)
                    if (((Record)recBookingDetail).getRecordOwner().getTask() != null)
                        ((Record)recBookingDetail).getRecordOwner().getTask().getLastError(iErrorCode);
                recBookingDetail.setErrorMessage(this, setError);
            }                
        }
        return iErrorCode;
    }

}
