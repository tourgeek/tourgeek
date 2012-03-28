/**
 * @(#)ProductInformationRequest.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.main.msg.db.*;

/**
 *  ProductInformationRequest - .
 */
public class ProductInformationRequest extends ProductRequest
{
    /**
     * Default constructor.
     */
    public ProductInformationRequest()
    {
        super();
    }
    /**
     * ProductInformationRequest Method.
     */
    public ProductInformationRequest(MessageDataParent messageDataParent, String strKey)
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
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return RequestType.INFORMATION;
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        ProductMessageData productMessageData = null;
        this.addMessageDataDesc(productMessageData = this.createProductMessageData());
        productMessageData.removeMessageDataDesc(BookingDetail.RATE_ID);    // No required for information
        productMessageData.removeMessageDataDesc(BookingDetail.CLASS_ID);
        // Don't need the booking and pax info.
    }
    /**
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new ProductMessageData(this, PRODUCT_MESSAGE)
        {
            public void setupMessageDataDesc()
            {
                // For now, just use these fields in the message
                this.addMessageFieldDesc(BookingDetail.PRODUCT_ID, Integer.class, MessageFieldDesc.REQUIRED, null);
                this.addMessageFieldDesc(BookingDetail.DETAIL_DATE, Date.class, MessageFieldDesc.REQUIRED, null);
                this.addMessageFieldDesc(Product.OPERATORS_CODE, String.class, MessageFieldDesc.OPTIONAL, null);
                this.addMessageFieldDesc(Product.PRODUCT_NAME_PARAM, String.class, MessageFieldDesc.OPTIONAL, null);
            }
        };
    }

}
