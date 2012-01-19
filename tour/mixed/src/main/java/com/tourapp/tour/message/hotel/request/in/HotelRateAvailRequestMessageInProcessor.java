/**
 * @(#)HotelRateAvailRequestMessageInProcessor.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.request.in;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.hotel.response.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.*;

/**
 *  HotelRateAvailRequestMessageInProcessor - Process a combined rate and availability message.
 */
public class HotelRateAvailRequestMessageInProcessor extends HotelRateRequestMessageInProcessor
{
    /**
     * Default constructor.
     */
    public HotelRateAvailRequestMessageInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelRateAvailRequestMessageInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        ProductRequest msgProductAvailRequestIn = (ProductRequest)internalMessage.getMessageDataDesc(null);
        
        Product recProduct = (Product)this.getMainRecord();
        if (recProduct == null)
            recProduct = this.getProductRecord();
        if (!((ProductMessageData)msgProductAvailRequestIn.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).getProduct(recProduct))
        {   // Error, hotel not found
            // pend(don)
        }
        BaseProductResponse productAvailResponseOut = (BaseProductResponse)recProduct.processAvailabilityRequestInMessage((BaseMessage)msgProductAvailRequestIn.getMessage(), null, null).getMessageDataDesc(null);
        //        ProductAvailabilityResponse
        BaseProductResponse productRateResponseOut = (BaseProductResponse)recProduct.processCostRequestInMessage((BaseMessage)msgProductAvailRequestIn.getMessage(), null).getMessageDataDesc(null);
        
        TrxMessageHeader messageHeader = (TrxMessageHeader)productAvailResponseOut.getMessage().getMessageHeader();
        //?Map<String,Object> mapMessage = productAvailResponseOut.getMap();
        //?Map<String,Object> mapMessage2 = productRateResponseOut.getMap();
        //?mapMessage.putAll(mapMessage2);
        BaseMessage response = null;//?new BaseMessage(messageHeader, new HotelRateAvailResponse(null, mapMessage));
        
        return response;
    }

}
