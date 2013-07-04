
package com.tourgeek.tour.message.base.request.in;

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
import org.jbundle.base.message.trx.processor.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.message.base.*;

/**
 *  PingRequestMessageInProcessor - .
 */
public class PingRequestMessageInProcessor extends BaseMessageInProcessor
{
    public static final String MESSAGE_PARAM = "message";
    /**
     * Default constructor.
     */
    public PingRequestMessageInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PingRequestMessageInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        if (internalMessage.getMessageDataDesc(null) == null)
            internalMessage.addMessageDataDesc(new BaseProductMessageDesc(null, null));
        BaseMessageHeader inMessageHeader = internalMessage.getMessageHeader();
        String strMessage = (String)internalMessage.get(MESSAGE_PARAM);
        BaseMessageHeader messageReplyHeader = null;
        messageReplyHeader = new TrxMessageHeader(inMessageHeader.getQueueName(), inMessageHeader.getQueueType(), null);
        
        BaseMessage messageReply = new TreeMessage(messageReplyHeader, null);
        BaseProductResponse productResponse = new BaseProductResponse(null, null);
        messageReply.addMessageDataDesc(productResponse);
        if (strMessage != null)
            messageReply.put(MESSAGE_PARAM, strMessage);
        else
        {   // Error - Empty message
            String strErrorMessage = null;
            if (internalMessage.getMessageHeader() instanceof TrxMessageHeader) // Always
                if (((TrxMessageHeader)internalMessage.getMessageHeader()).get(TrxMessageHeader.MESSAGE_ERROR) != null)
                    strErrorMessage = ((TrxMessageHeader)internalMessage.getMessageHeader()).get(TrxMessageHeader.MESSAGE_ERROR).toString();    // Rare
            if (strErrorMessage == null)
                strErrorMessage = "Error - No data to PING";
            productResponse.setMessageDataStatus(MessageDataDesc.ERROR);
            productResponse.setMessageDataError(strErrorMessage);
        }
        productResponse.moveRequestInfoToReply(internalMessage);
        
        return messageReply;
    }

}
