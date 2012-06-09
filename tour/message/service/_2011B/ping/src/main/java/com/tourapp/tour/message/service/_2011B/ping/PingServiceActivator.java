/*
 * RmiSessionServer.java
 *
 * Created on January 10, 2000, 4:47 PM
 
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.tour.message.service._2011B.ping;

import java.util.Map;

import org.jbundle.base.db.Record;
import org.jbundle.base.message.service.BaseMessageServiceActivator;
import org.jbundle.model.RecordOwnerParent;

public class PingServiceActivator extends BaseMessageServiceActivator
{
	
    public Object createMessageService(RecordOwnerParent task, Record recordMain, Map<String, Object> properties)
    {
        return new PingService2011BImpl(task, null, properties);
    }
    /**
     * Get the interface/service class name.
     * @return
     */
    public Class<?> getInterfaceClass()
    {
        return org.jibx.schema.org.opentravel._2011B.ping.ws.PingService.class;
    }
}
