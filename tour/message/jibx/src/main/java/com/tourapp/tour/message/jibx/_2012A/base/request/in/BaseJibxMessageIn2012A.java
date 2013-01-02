/**
  * @(#)BaseJibxMessageIn2012A.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.jibx._2012A.base.request.in;

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
import org.jibx.schema.org.opentravel._2012A.base.*;

/**
 *  BaseJibxMessageIn2012A - .
 */
public class BaseJibxMessageIn2012A extends JibxConvertToMessage
{
    /**
     * Default constructor.
     */
    public BaseJibxMessageIn2012A()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public BaseJibxMessageIn2012A(ExternalTrxMessageIn message)
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
