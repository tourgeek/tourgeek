/**
 * @(#)CancelRequest.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.request;

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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.data.*;

/**
 *  CancelRequest - Cancellation request.
 */
public class CancelRequest extends ProductRequest
{
    /**
     * Default constructor.
     */
    public CancelRequest()
    {
        super();
    }
    /**
     * CancelRequest Method.
     */
    public CancelRequest(MessageDataParent messageDataParent, String strKey)
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
        ProductMessageData productMessage = (ProductMessageData)this.getMessageDataDesc(PRODUCT_MESSAGE);
        productMessage.addMessageFieldDesc(BookingDetail.REMOTE_BOOKING_NO, String.class, MessageFieldDesc.REQUIRED, MessageFieldDesc.NOT_UNIQUE | MessageFieldDesc.DONT_INIT, null);
        // UNIQUE adds this to the key making it different from product order
        productMessage.addMessageFieldDesc(BookingDetail.PRODUCT_TYPE, String.class, MessageFieldDesc.REQUIRED, MessageFieldDesc.UNIQUE | MessageFieldDesc.DONT_INIT, null);
        
        ((MessageFieldDesc)productMessage.getMessageDataDesc(BookingDetail.RATE_ID)).setRequired(MessageFieldDesc.OPTIONAL);
        ((MessageFieldDesc)productMessage.getMessageDataDesc(BookingDetail.CLASS_ID)).setRequired(MessageFieldDesc.OPTIONAL);
        ((MessageFieldDesc)productMessage.getMessageDataDesc(Product.PAX_PARAM)).setRequired(MessageFieldDesc.OPTIONAL);
        ((MessageFieldDesc)productMessage.getMessageDataDesc(Product.ROOM_TYPE_PARAM)).setRequired(MessageFieldDesc.OPTIONAL);
    }
    /**
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(Record record)
    {
        int iErrorCode = super.initForMessage(record);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            iErrorCode = this.initBookingApTrx(record);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            iErrorCode = this.initBookingInventory(record);
        return iErrorCode;
    }
    /**
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return RequestType.BOOKING_CANCEL;
    }

}
