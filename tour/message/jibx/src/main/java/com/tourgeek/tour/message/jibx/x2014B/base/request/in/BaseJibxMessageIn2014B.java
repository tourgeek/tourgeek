/**
  * @(#)BaseJibxMessageIn2014B.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.jibx.x2014B.base.request.in;

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
import org.jbundle.thin.base.message.*;
import org.jibx.schema.org.opentravel.x2014B.base.*;

/**
 *  BaseJibxMessageIn2014B - .
 */
public class BaseJibxMessageIn2014B extends JibxConvertToMessage
{
    /**
     * Default constructor.
     */
    public BaseJibxMessageIn2014B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public BaseJibxMessageIn2014B(ExternalTrxMessageIn message)
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
