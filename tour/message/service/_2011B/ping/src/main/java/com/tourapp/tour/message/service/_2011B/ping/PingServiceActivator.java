/**
 * @(#)PingServiceActivator.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.service._2011B.ping;

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
import org.jbundle.base.message.service.*;

/**
 *  PingServiceActivator - .
 */
public class PingServiceActivator extends BaseMessageServiceActivator
{
    /**
     * Default constructor.
     */
    public PingServiceActivator()
    {
        super();
    }
    /**
     * Create the service implementation.
     */
    public Object createMessageService(RecordOwnerParent task, Record recordMain, Map<String, Object> properties)
    {
        return new PingService2011BImpl(task, null, properties);
    }
    /**
     * Get the interface/service class name.
     * @return The interface class.
     */
    public Class<?> getInterfaceClass()
    {
        return org.jibx.schema.org.opentravel._2011B.ping.ws.PingService.class;
    }

}
