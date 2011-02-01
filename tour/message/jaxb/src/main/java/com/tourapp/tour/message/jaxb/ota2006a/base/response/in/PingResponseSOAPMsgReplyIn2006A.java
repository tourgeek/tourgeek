/**
 *  @(#)PingResponseSOAPMsgReplyIn2006A.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2006a.base.response.in;

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
import javax.xml.soap.*;
import com.tourapp.tour.message.base.request.in.*;
import net.sourceforge.ota_tools.jaxb.ota2006a.custom.*;
import org.jbundle.base.message.trx.message.external.*;
import java.io.*;

/**
 *  PingResponseSOAPMsgReplyIn2006A - .
 */
public class PingResponseSOAPMsgReplyIn2006A extends JaxbConvertToMessage
{
    /**
     * Default constructor.
     */
    public PingResponseSOAPMsgReplyIn2006A()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public PingResponseSOAPMsgReplyIn2006A(ExternalTrxMessageIn message)
    {
        this();
        this.init(message);
    }
    /**
     * Initialize class fields.
     */
    public void init(ExternalTrxMessageIn message)
    {
        super.init(message);
    }
    /**
     * Convert the external form to the internal message form.
     * You must override this method.
     * @param root The root object of the marshallable object.
     * @param recordOwner The recordowner
     * @return The error code.
     */
    public int convertMarshallableObjectToInternal(Object root, RecordOwner recordOwner)
    {
        if (root instanceof OTA_PingRS)
        {       // Always
            OTA_PingRS msg = (OTA_PingRS)root;
            java.util.List<?> list = msg.getSuccessAndWarningsAndEchoData();
            String strMessage = null;
            boolean bSuccess = true;    // Who uses this?
            for (Object data : list)
            {
                if (data instanceof String)
                    strMessage = (String)data;
                if (data instanceof SuccessType)
                    bSuccess = true;
            }
            
            BaseMessage message = this.getMessage();
            message.put(PingRequestMessageInProcessor.MESSAGE_PARAM, strMessage);
            
            this.addPayloadProperties(msg, message);
        }
        return DBConstants.NORMAL_RETURN;
    }

}
