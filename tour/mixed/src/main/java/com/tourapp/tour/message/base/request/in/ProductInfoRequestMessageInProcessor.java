/**
 * @(#)ProductInfoRequestMessageInProcessor.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.message.trx.processor.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ProductInfoRequestMessageInProcessor - .
 */
public class ProductInfoRequestMessageInProcessor extends BaseMessageInProcessor
{
    /**
     * Default constructor.
     */
    public ProductInfoRequestMessageInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductInfoRequestMessageInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Create the product record.
     * Override in the concrete classes.
     */
    public Product getProductRecord()
    {
        return null;
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
                messageReply = recProduct.processInfoRequestInMessage(internalMessage, messageReply);
            }
        }
        return messageReply;
    }

}
