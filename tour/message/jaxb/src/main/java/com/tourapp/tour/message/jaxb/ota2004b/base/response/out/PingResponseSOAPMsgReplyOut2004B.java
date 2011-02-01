/**
 *  @(#)PingResponseSOAPMsgReplyOut2004B.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2004b.base.response.out;

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
import org.jbundle.base.message.trx.message.external.convert.jaxb.*;
import org.jbundle.thin.base.message.*;
import net.sourceforge.ota_tools.jaxb.ota2004b.custom.*;
import com.tourapp.tour.message.base.request.in.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.base.message.trx.message.external.*;

/**
 *  PingResponseSOAPMsgReplyOut2004B - .
 */
public class PingResponseSOAPMsgReplyOut2004B extends JaxbConvertToNative
{
    /**
     * Default constructor.
     */
    public PingResponseSOAPMsgReplyOut2004B()
    {
        super();
    }
    /**
     * This is the base class for a transaction which is sent externally.
     * The two main sub-classes of this class are InternalTrxMessage and ExternalTrxMessage.
     * An InternalTrxMessage is the data I create internally to send to the destination. It
     * usually contains all the relative information needed to send to the destination.
     * An ExternalTrxMessage is the message converted to a format that the receiver can
     * understand (such as ebXML).
     */
    public PingResponseSOAPMsgReplyOut2004B(ExternalTrxMessageOut message)
    {
        this();
        this.init(message);
    }
    /**
     * Initialize class fields.
     */
    public void init(ExternalTrxMessageOut message)
    {
        super.init(message);
    }
    /**
     * Note: This code is not the same as 2008A.
     */
    public Object convertInternalToMarshallableObject(RecordOwner recordOwner)
    {
        BaseMessage message = this.getMessage();
        String strMessage = message.getString(PingRequestMessageInProcessor.MESSAGE_PARAM);
        
        ObjectFactory factory = new ObjectFactory();
        OTAPingRS root = factory.createOTAPingRS();
        
        if (strMessage != null)
        {
            SuccessType successType = factory.createSuccessType();
            root.setSuccess(successType);
            root.setEchoData(strMessage);
        }
        else
        {
            ErrorsType errorsType = factory.createErrorsType();
            root.setErrors(errorsType);
            ErrorType errorType = factory.createErrorType();
            errorsType.getError().add(errorType);
            String strErrorMessage = null;
            if (message.getMessageDataDesc(null) instanceof BaseProductResponse)
            {   // Always
                BaseProductResponse productResponse = (BaseProductResponse)message.getMessageDataDesc(null);
                if (productResponse.getMessageDataStatus() == MessageDataDesc.ERROR)
                {   // Always
                    strErrorMessage = productResponse.getMessageDataError();
                }
            }
            if (strErrorMessage == null)
                strErrorMessage = "Error - No data to PING";
            errorType.setValue(strErrorMessage);
            errorType.setType("1");
        }
        
        this.setPayloadProperties(message, root);
        
        return root;
    }

}
