/**
 * @(#)PingRequestJibxMessageIn2010B.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jibx.ota2010b.base.request.in;

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
import org.jbundle.base.message.trx.message.external.*;
import org.jibx.schema.org.opentravel._2010B.ping.*;
import org.jibx.schema.org.opentravel._2010B.base.*;
import com.tourapp.tour.message.base.request.in.*;
import org.jbundle.thin.base.message.*;

/**
 *  PingRequestJibxMessageIn2010B - .
 */
public class PingRequestJibxMessageIn2010B extends BaseJibxMessageIn2010B
{
    /**
     * Default constructor.
     */
    public PingRequestJibxMessageIn2010B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public PingRequestJibxMessageIn2010B(ExternalTrxMessageIn message)
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
        if (root instanceof PingRQ)
        {       // Always
            PingRQ msg = (PingRQ)root;
            String strMessage = msg.getEchoData();
            
            BaseMessage message = this.getMessage();
            message.put(PingRequestMessageInProcessor.MESSAGE_PARAM, strMessage);
        
            OTAPayloadStdAttributes payloadStdAttributes = msg.getPayloadStdAttributes();
            this.addPayloadProperties(payloadStdAttributes, message);
            return DBConstants.NORMAL_RETURN;
        }
        else
            return DBConstants.ERROR_RETURN;
    }

}
