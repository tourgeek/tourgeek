/**
 *  @(#)PingResponseSOAPMsgReplyIn2004B.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2004b.base.response.in;

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
import net.sourceforge.ota_tools.jaxb.ota2004b.custom.*;
import com.tourapp.tour.message.base.request.in.*;
import org.jbundle.base.message.trx.message.external.*;

/**
 *  PingResponseSOAPMsgReplyIn2004B - .
 */
public class PingResponseSOAPMsgReplyIn2004B extends JaxbConvertToMessage
{
    /**
     * Default constructor.
     */
    public PingResponseSOAPMsgReplyIn2004B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public PingResponseSOAPMsgReplyIn2004B(ExternalTrxMessageIn message)
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
     * Note: This code is not the same as PingResponseSOAPMsgReplyIn2008A.
     */
    public int convertMarshallableObjectToInternal(Object root, RecordOwner recordOwner)
    {
        if (root instanceof OTAPingRS)
        {       // Always
            OTAPingRS msg = (OTAPingRS)root;
            String strMessage = msg.getEchoData();
            SuccessType successType = msg.getSuccess();
            boolean bSuccess = true;    // Who uses this?
            
            BaseMessage message = this.getMessage();
            message.put(PingRequestMessageInProcessor.MESSAGE_PARAM, strMessage);
            
            this.addPayloadProperties(msg, message);
        }
        return DBConstants.NORMAL_RETURN;
    }

}
