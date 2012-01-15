/**
 * @(#)ProductMessageErrorProcessor.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.error;

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
import com.tourapp.tour.message.base.request.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.response.*;

/**
 *  ProductMessageErrorProcessor - Error processor for messages going out.
 */
public class ProductMessageErrorProcessor extends BaseProductMessageErrorProcessor
{
    /**
     * Default constructor.
     */
    public ProductMessageErrorProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductMessageErrorProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        ProductRequest productRequest = (ProductRequest)internalMessage.getMessageDataDesc(null);
        
        String strErrorMessage = (String)internalMessage.getMessageHeader().get(TrxMessageHeader.MESSAGE_ERROR);
        String strReturnQueueName = null;
        if (internalMessage.getMessageHeader() instanceof TrxMessageHeader)
            strReturnQueueName = (String)((TrxMessageHeader)internalMessage.getMessageHeader()).getMessageHeaderMap().get(MessageConstants.RETURN_QUEUE_NAME);
        if (strReturnQueueName == null)
            strReturnQueueName = MessageConstants.TRX_RETURN_QUEUE;
        TrxMessageHeader messageReplyHeader = null;
        
        if (internalMessage.getMessageHeader() instanceof TrxMessageHeader)
            messageReplyHeader = ((TrxMessageHeader)internalMessage.getMessageHeader()).createReplyHeader();
        else
            messageReplyHeader = new TrxMessageHeader(strReturnQueueName, MessageConstants.INTERNET_QUEUE, null);
        messageReplyHeader.setQueueName(strReturnQueueName);
        if (internalMessage.getMessageHeader().getRegistryIDMatch() != null)
            messageReplyHeader.setRegistryIDMatch(internalMessage.getMessageHeader().getRegistryIDMatch());
         
        MessageProcessInfo recMessageProcessInfo = (MessageProcessInfo)this.getRecord(MessageProcessInfo.kMessageProcessInfoFile);
        if (recMessageProcessInfo == null)
            recMessageProcessInfo = new MessageProcessInfo(this);
        BaseMessage errorMessage = (BaseMessage)recMessageProcessInfo.createReplyMessage(internalMessage);
        errorMessage.setMessageHeader(messageReplyHeader);
        if (errorMessage.getMessageDataDesc(null) != null)
        {   // Always
            BaseProductResponse productResponse = (BaseProductResponse)errorMessage.getMessageDataDesc(null);
            productResponse.moveRequestInfoToReply(errorMessage);
            productResponse.setMessageDataStatus(MessageDataDesc.ERROR);
            productResponse.setMessageDataError(strErrorMessage);
        }
        return errorMessage;
    }

}
