/**
  * @(#)BaseJibxMessageOut2012A.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.jibx._2012A.base.request.out;

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
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jibx.schema.org.opentravel._2012A.base.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jbundle.thin.base.message.*;
import org.joda.time.*;

/**
 *  BaseJibxMessageOut2012A - .
 */
public class BaseJibxMessageOut2012A extends JibxConvertToNative
{
    /**
     * Default constructor.
     */
    public BaseJibxMessageOut2012A()
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
    public BaseJibxMessageOut2012A(ExternalTrxMessageOut message)
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
     * Move the standard payload properties from the message to the xml.
     * @param message The standarized message
     * @param msg The native parser message.
     */
    public void setPayloadProperties(BaseMessage message, Object msg)
    {
        super.setPayloadProperties(message, msg); // Doesn't hurt to try this
        
        OTAPayloadStdAttributes payloadStdAttributes = (OTAPayloadStdAttributes)msg;
        DateTime timeStamp = new DateTime();
        payloadStdAttributes.setTimeStamp(timeStamp);
        Float version = new Float(1.00);
        payloadStdAttributes.setVersion(version);
        payloadStdAttributes.setTarget(OTAPayloadStdAttributes.Target.PRODUCTION);
    }

}
