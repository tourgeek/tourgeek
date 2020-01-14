/**
  * @(#)PingService2012BImpl.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.service._2014B.ping;

import java.util.*;

import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.base.message.service.*;
import org.jibx.schema.org.opentravel._2014B.ping.*;
import org.jibx.schema.org.opentravel._2014B.ping.ws.*;

/**
 *  PingService2012BImpl - .
 */
public class PingService2014BImpl extends BaseServiceMessageTransport
     implements PingService
{
    /**
     * Default constructor.
     */
    public PingService2014BImpl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PingService2014BImpl(RecordOwnerParent parent, Rec recordMain, Map<String, Object> properties)
    {
        this();
        this.init(parent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent parent, Rec recordMain, Map<String, Object> properties)
    {
        super.init(parent, recordMain, properties);
    }
    /**
     * Ping Method.
     */
    public PingRS ping(PingRQ request)
    {
        return (PingRS)this.processMessage(request);
    }

}
