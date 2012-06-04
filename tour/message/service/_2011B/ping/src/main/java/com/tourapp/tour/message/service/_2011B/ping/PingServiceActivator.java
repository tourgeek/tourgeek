/*
 * RmiSessionServer.java
 *
 * Created on January 10, 2000, 4:47 PM
 
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.tour.message.service._2011B.ping;

import java.util.Map;

import org.jbundle.base.model.MessageApp;
import org.jbundle.base.model.Utility;
import org.jbundle.base.util.BaseThickActivator;
import org.osgi.framework.BundleContext;

public class PingServiceActivator extends BaseThickActivator
{
	
    /**
     * Make sure the dependent services are up, then call startupService.
     * @param versionRange Bundle version
     * @param baseBundleServiceClassName
     * @return false if I'm waiting for the service to startup.
     */
    public boolean checkDependentServices(BundleContext bundleContext)
    {
//?    	boolean success = this.addDependentServiceListener(bundleContext, MessageServerActivator.class.getName(), MessageApp.class.getName(), MessageInfoApplication.class.getName(), null);
    	boolean success = this.addDependentService(bundleContext, MessageApp.class.getName(), "org.jbundle.main.msg.app.MessageInfoApplication", null, null);
    	success = success & super.checkDependentServices(bundleContext);
    	return success;
    }
    /**
     * Start this service.
     * Override this to do all the startup.
     * @return true if successful.
     */
    public Object startupService(BundleContext bundleContext)
    {
        Map<String,Object> props = this.getServiceProperties();
        MessageApp app = (MessageApp)this.getService(MessageApp.class.getName());
        try {
            service = new PingService2011BImpl(app, null, props);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    	return service;
    }
}
