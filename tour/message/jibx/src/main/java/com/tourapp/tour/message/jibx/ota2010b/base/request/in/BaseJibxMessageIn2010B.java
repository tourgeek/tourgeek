/**
 * @(#)BaseJibxMessageIn2010B.
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
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jibx.schema.org.opentravel._2010B.base.*;
import org.jibx.schema.org.opentravel._2010B.ping.*;
import org.jbundle.thin.base.message.*;

/**
 *  BaseJibxMessageIn2010B - .
 */
public class BaseJibxMessageIn2010B extends JibxConvertToMessage
{
    /**
     * Default constructor.
     */
    public BaseJibxMessageIn2010B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public BaseJibxMessageIn2010B(ExternalTrxMessageIn message)
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
     * Utility to add the standard payload properties to the message
     * @param msg The parser specific message
     * @param message The standardized message.
     */
    public void addPayloadProperties(Object msg, BaseMessage message)
    {
        super.addPayloadProperties(msg, message);
        
        OTAPayloadStdAttributes payloadStdAttributes = (OTAPayloadStdAttributes)msg;
        // Actually, super.addPayloadProperties should have worked correctly
    }

}
