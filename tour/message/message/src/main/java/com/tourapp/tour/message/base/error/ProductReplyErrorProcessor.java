/**
  * @(#)ProductReplyErrorProcessor.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.base.error;

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
import org.jbundle.base.message.core.trx.*;
import com.tourapp.tour.message.base.response.*;

/**
 *  ProductReplyErrorProcessor - Processor for replies.
 */
public class ProductReplyErrorProcessor extends BaseProductMessageErrorProcessor
{
    /**
     * Default constructor.
     */
    public ProductReplyErrorProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductReplyErrorProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        if (internalMessage == null)
            return null;
        String strErrorMessage = (String)internalMessage.getMessageHeader().get(TrxMessageHeader.MESSAGE_ERROR);
        
        BaseMessage errorMessage = internalMessage;
        if (errorMessage.getMessageDataDesc(null) != null)
        {   // Always
            BaseProductResponse productResponse = (BaseProductResponse)errorMessage.getMessageDataDesc(null);
            productResponse.setMessageDataStatus(MessageDataDesc.ERROR);
            productResponse.setMessageDataError(strErrorMessage);
        }
        return errorMessage;
    }

}
