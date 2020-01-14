/**
  * @(#)PingRequestJibxMessageIn2012B.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.jibx._2014B.base.request.in;

import org.jbundle.base.model.*;
import org.jibx.schema.org.opentravel._2014B.ping.*;
import org.jibx.schema.org.opentravel._2014B.base.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.message.base.request.in.*;

/**
 *  PingRequestJibxMessageIn2012B - .
 */
public class PingRequestJibxMessageIn2014B extends BaseJibxMessageIn2014B
{
    /**
     * Default constructor.
     */
    public PingRequestJibxMessageIn2014B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public PingRequestJibxMessageIn2014B(ExternalTrxMessageIn message)
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
