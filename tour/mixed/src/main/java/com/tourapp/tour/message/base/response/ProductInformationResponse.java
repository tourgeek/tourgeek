/**
 *  @(#)ProductInformationResponse.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.model.db.*;

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
        messageData.addMessageFieldDesc(Product.PRODUCT_NAME_PARAM, String.class, MessageFieldDesc.REQUIRED, null);
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
            BookingDetail recBookingDetail = (BookingDetail)record;
            boolean[] rgbListeners = recBookingDetail.getField(BookingDetail.kInfoStatusRequest).setEnableListeners(false); // No Echo
            iErrorCode = recBookingDetail.setDetailProductInfo(null, null, null, null, null, null, null);
            recBookingDetail.getField(BookingDetail.kInfoStatusRequest).setEnableListeners(rgbListeners);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
            {
                int iMessageStatus = BaseDataStatus.NOT_VALID;
                this.setMessageDataStatus(iMessageStatus);
                String setError = "Error";
                if (recBookingDetail.getRecordOwner() != null)
                    if (recBookingDetail.getRecordOwner().getTask() != null)
                        recBookingDetail.getRecordOwner().getTask().getLastError(iErrorCode);
                recBookingDetail.setErrorMessage(this, setError);
            }                
        }
        return iErrorCode;
    }

}
