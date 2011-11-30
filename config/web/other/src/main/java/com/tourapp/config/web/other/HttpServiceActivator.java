/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.config.web.other;

import java.util.Dictionary;

import javax.servlet.Servlet;

import org.jbundle.base.util.DBConstants;
import org.jbundle.base.util.EnvironmentActivator;
import org.jbundle.base.util.Utility;
import org.jbundle.util.osgi.finder.ClassServiceUtility;
import org.jbundle.util.webapp.base.BaseOsgiServlet;
import org.jbundle.util.webapp.base.BaseWebappServlet;
import org.jbundle.util.webapp.base.FileHttpContext;
import org.jbundle.util.webapp.base.HttpServiceTracker;
import org.jbundle.util.webapp.base.MultipleHttpServiceActivator;
import org.jbundle.util.webapp.base.WebappServlet;
import org.jbundle.util.webapp.files.DefaultServlet;
import org.jbundle.util.webapp.redirect.RedirectServlet;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.service.http.HttpContext;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Start up the web service listener.
 * @author don
 */
public class HttpServiceActivator extends MultipleHttpServiceActivator
{
    public static final String JBUNDLE = "jbundle";
    public static final String DOWNLOAD = "download";
    public static final String GIT_WEB = "git-web";
    public static final String AWSTATS = "awstats";

    public static final String JCALENDARBUTTON = JBUNDLE + "/jcalendarbutton";
    public static final String WEBAPP = JBUNDLE + "/webapp";
    public static final String OSGI_WEBSTART = JBUNDLE + "/osgi-webstart";
    public static final String CALENDARPANEL = JBUNDLE + "/calendarpanel";
    public static final String CALENDARPANEL_JNLP = JBUNDLE + "/calendarpanel/jnlp";

    public static final String WEBAPP_FILES = WEBAPP + "/files";
    public static final String WEBAPP_WEBSITE = WEBAPP + "/website";
    public static final String WEBAPP_REDIRECT = WEBAPP + "/redirect";
    public static final String WEBAPP_PROXY = WEBAPP + "/proxy";
    public static final String WEBAPP_UPLOAD = WEBAPP + "/upload";
    public static final String WEBAPP_WEBDAV = WEBAPP + "/webdav";
    public static final String WEBAPP_CGI = WEBAPP + "/cgi";
    public static final String WEBAPP_WEBSTART = WEBAPP + "/webstart";

    public static final String PICTURES = "pictures";

    public static final String UPLOAD = "upload";
    public static final String NOTES = "notes";

    private String[] aliases = {
            JCALENDARBUTTON,
            JBUNDLE,
            CALENDARPANEL_JNLP,
            CALENDARPANEL,
//            AWSTATS,
//x         "demo",
            DOWNLOAD,
//            GIT_WEB,
//            WEBAPP_CGI,
            WEBAPP_FILES,
            WEBAPP_WEBSITE,
            WEBAPP_REDIRECT,
            WEBAPP_PROXY,
            WEBAPP_UPLOAD,
            WEBAPP_WEBDAV,
            WEBAPP_WEBSTART,
            NOTES,
            PICTURES,
            WEBAPP,
            OSGI_WEBSTART,
            UPLOAD,
    };
    
    public final String STATIC_WEB_FILES = "staticWebFiles";
    public final String DEFAULT_STATIC_WEB_FILES = "file:/space/web/";

    /**
     * Called when the http service tracker come up or is shut down.
     * Start or stop the bundle on start/stop.
     * @param event The service event.
     */
    @Override
    public void serviceChanged(ServiceEvent event) {
        if (event.getType() == ServiceEvent.REGISTERED)
        { // Osgi Service is up, Okay to start the server
            ClassServiceUtility.log(context, LogService.LOG_INFO, "Starting Http Service tracker");
            if (httpServiceTracker == null)
            {
                BundleContext context = event.getServiceReference().getBundle().getBundleContext();
                this.checkDependentServicesAndStartup(context, EnvironmentActivator.class.getName(), null);
            }
        }
        if (event.getType() == ServiceEvent.UNREGISTERING)
            super.serviceChanged(event);    // httpService.close();
    }

    /**
     * Get all the web aliases to add http services for.
     *@return A list of the web services.
     */
    public String[] getAliases()
    {
        return aliases;
    }
    
    /**
     * Make a servlet tracker for the servlet at this alias.
     */
    public ServiceTracker makeServletTracker(String alias, Dictionary<String, String> properties)
    {
//?        dictionary.put(HttpServiceTracker.SERVICE_PID, getServicePid(context));
//?        dictionary.put(HttpServiceTracker.SERVLET_CLASS, getServletClass(context));
        properties.put(BaseWebappServlet.ALIAS, alias); 
        
        Servlet servlet = null;
        HttpContext httpContext = null;
        HttpServiceTracker serviceTracker = null; 
        try {
            if (WEBAPP_WEBSITE.equalsIgnoreCase(alias))
            {
                //x servlet = new org.jbundle.util.webapp.osgi.OSGiFileServlet();   // Need to redirect from root url
                servlet = new org.jbundle.util.webapp.redirect.RegexRedirectServlet();
                this.addRedirectProperties(alias, properties);
            }
            else if (WEBAPP_FILES.equalsIgnoreCase(alias))
            {
                servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(DefaultServlet.class.getName());
                if (servlet == null)    // Fallback to OSGiServlet
                    servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(RedirectServlet.class.getName());
                httpContext = new FileHttpContext(context.getBundle());
                this.addRedirectProperties(alias, properties);
                properties.put(DefaultServlet.BASE_PATH, "/space/web/");
            }
            else if (WEBAPP_REDIRECT.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.redirect.RegexRedirectServlet();
                this.addRedirectProperties(alias, properties);
                properties.put(RedirectServlet.TARGET, "http://www.jbundle.org");
            }
            else if (WEBAPP_PROXY.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.proxy.ProxyServlet();
                properties.put(org.jbundle.util.webapp.proxy.ProxyServlet.PROXY, "http://www.jbundle.org:8181/jbundle/webapp/");
            }
            else if (WEBAPP_UPLOAD.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.upload.UploadServlet();
            }
            else if (WEBAPP_CGI.equalsIgnoreCase(alias))
            {
//                servlet = new org.apache.catalina.servlets.CGIServlet();
            }
            else if (WEBAPP_WEBDAV.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.webdav.WebdavServlet();
                properties.put(WebappServlet.BASE_PATH, "/space/web/jbundle/webapp/webdav");
                properties.put("readonly", "true"); // false
                properties.put("listings", "true");
            }
            else if (WEBAPP_WEBSTART.equalsIgnoreCase(alias))
            {
//                servlet = new jnlp.sample.servlet.JnlpDownloadServlet();
            }
            else if (AWSTATS.equalsIgnoreCase(alias))
            {
//                servlet = new org.apache.catalina.servlets.CGIServlet();
            }
            else if (DOWNLOAD.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.base.BaseOsgiServlet();
            }
            else if (GIT_WEB.equalsIgnoreCase(alias))
            {
//                servlet = new org.apache.catalina.servlets.CGIServlet();
            }
            else if (NOTES.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.WebdavServlet();
            }
            else if (UPLOAD.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.upload.UploadServlet();
            }
            else if (CALENDARPANEL_JNLP.equalsIgnoreCase(alias))
            {
                servlet = new jnlp.sample.servlet.JnlpDownloadServlet();
            }
            else if ((JBUNDLE.equalsIgnoreCase(alias))
                    || (JCALENDARBUTTON.equalsIgnoreCase(alias))
                    || (CALENDARPANEL.equalsIgnoreCase(alias))
                    || (WEBAPP.equalsIgnoreCase(alias))
                    || (OSGI_WEBSTART.equalsIgnoreCase(alias))
                    || (PICTURES.equalsIgnoreCase(alias))
                        )
            {   // Everything else is a pointer to a static resource
                servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(RedirectServlet.class.getName());
                this.addRedirectProperties(alias, properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (httpContext == null)   
            httpContext = this.getHttpContext();
        if (serviceTracker == null)
            serviceTracker = this.createServiceTracker(context, httpContext, properties);
        serviceTracker.setServlet(servlet);
        return serviceTracker;
    }
    public void addRedirectProperties(String alias, Dictionary<String, String> properties)
    {
        // This redirects root url to index.html
        properties.put(RedirectServlet.MATCH, DBConstants.BLANK);
        String lastPath = alias;
        if (lastPath.lastIndexOf("/") != -1)
            lastPath = lastPath.substring(lastPath.lastIndexOf("/") + 1);
        properties.put(RedirectServlet.TARGET, Utility.addURLPath(lastPath, "index.html"));
        String urlCodeBase = context.getProperty(STATIC_WEB_FILES);
        if (urlCodeBase == null)
            urlCodeBase = DEFAULT_STATIC_WEB_FILES;
        if (!"/".equals(alias))
            urlCodeBase = Utility.addURLPath(urlCodeBase, alias) + "/"; // Should have trailing '/'
        properties.put(BaseOsgiServlet.BASE_PATH, urlCodeBase);        
    }
}
