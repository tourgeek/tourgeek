/**
  * @(#)PingRequestJibxMessageOut2014B.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.jibx.x2014B.base.request.out;

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
import org.jibx.schema.org.opentravel.x2014B.ping.*;
import org.jibx.schema.org.opentravel.x2014B.base.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.message.base.request.in.*;

/**
 *  PingRequestJibxMessageOut2014B - .
 */
public class PingRequestJibxMessageOut2014B extends BaseJibxMessageOut2014B
{
    /**
     * Default constructor.
     */
    public PingRequestJibxMessageOut2014B()
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
    public PingRequestJibxMessageOut2014B(ExternalTrxMessageOut message)
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
     * Convert this source message to the ECXML format.
     * Typically you do not override this method, you override the getTransformer method
     * to supply a XSLT document to do the conversion.
     * @param recordOwner TODO
     * @param source The source XML document.
     * @return The XML tree that conforms to the ECXML format.
     */
    public Object convertInternalToMarshallableObject(RecordOwner recordOwner)
    {
        BaseMessage message = this.getMessage();
        String strMessage = message.getString(PingRequestMessageInProcessor.MESSAGE_PARAM);
            
        PingRQ root = new PingRQ();
        root.setEchoData(strMessage);
        
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        this.setPayloadProperties(message, payloadStdAttributes);
        root.setPayloadStdAttributes(payloadStdAttributes);
        
        return root;
    }

}
