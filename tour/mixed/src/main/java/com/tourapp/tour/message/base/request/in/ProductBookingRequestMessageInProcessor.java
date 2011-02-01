/**
 *  @(#)ProductBookingRequestMessageInProcessor.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.request.in;

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
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ProductBookingRequestMessageInProcessor - .
 */
public class ProductBookingRequestMessageInProcessor extends BaseMessageInProcessor
{
    /**
     * Default constructor.
     */
    public ProductBookingRequestMessageInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductBookingRequestMessageInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * GetProductRecord Method.
     */
    public Product getProductRecord()
    {
        return null;    // Override this!
    }
    /**
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        ProductRequest productRequestInMessage = (ProductRequest)internalMessage.getMessageDataDesc(null);
        
        Product recProduct = (Product)this.getMainRecord();
        if (recProduct == null)
            recProduct = this.getProductRecord();
        ProductMessageData productMessageDesc = (ProductMessageData)productRequestInMessage.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        BaseMessage messageReply = null;
        if (productMessageDesc != null)
        {
            for (int index = 1; ;index++)
            {
                productMessageDesc.setNodeIndex(index, null);
                if ((productMessageDesc.get(BookingDetail.PRODUCT_ID) == null) && (productMessageDesc.get(Product.CODE) == null))
                    break;  // End of product descriptions
                if (!(productMessageDesc.getProduct(recProduct)))
                {   // Error, product not found
                    // pend(don)
                }
                messageReply = recProduct.processBookingRequestInMessage(internalMessage, messageReply);
            }
        }
        return messageReply;
    }

}
