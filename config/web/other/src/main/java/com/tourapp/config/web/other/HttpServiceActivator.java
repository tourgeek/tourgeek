/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.config.web.other;

import java.util.Dictionary;

import javax.servlet.Servlet;

import org.jbundle.base.util.DBConstants;
import org.jbundle.base.util.Utility;
import org.jbundle.util.osgi.finder.ClassServiceUtility;
import org.jbundle.util.webapp.osgi.HttpServiceTracker;
import org.jbundle.util.webapp.osgi.OSGiFileServlet;
import org.jbundle.util.webapp.redirect.RedirectServlet;
import org.osgi.service.http.HttpContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Start up the web service listener.
 * @author don
 */
public class HttpServiceActivator extends org.jbundle.web.httpservice.MultipleHttpServiceActivator
{
    public static final String JBUNDLE = "jbundle";
    public static final String AWSTATS = "awstats";
    public static final String DOWNLOAD = "download";
    public static final String GIT_WEB = "git-web";

    public static final String CALENDARPANEL = "calendarpanel";

    public static final String WEBAPP_CGI = "jbundle-util-webapp-cgi";
    public static final String WEBAPP_FILES = "jbundle-util-webapp-files";
    public static final String WEBAPP_PROXY = "jbundle-util-webapp-proxy";
    public static final String WEBAPP_REDIRECT = "jbundle-util-webapp-redirect";
    public static final String WEBAPP_UPLOAD = "jbundle-util-webapp-upload";
    public static final String WEBAPP_WEBDAV = "jbundle-util-webapp-webdav";
    public static final String WEBAPP_WEBSITE = "jbundle-util-webapp-website";
    public static final String WEBAPP_WEBSTART = "jbundle-util-webapp-webstart-jnlp";

    public static final String JCALENDARBUTTON = "jcalendarbutton";
    public static final String NOTES = "notes";
    public static final String PICTURES = "pictures";
    public static final String SIMPLESERVLETS = "simpleservlets";
    public static final String UPLOAD = "upload";

    private String[] aliases = {
            JBUNDLE,
            AWSTATS,
            CALENDARPANEL,
//x         CALENDARPANELJNLP,
//x         "demo",
            DOWNLOAD,
            GIT_WEB,
            WEBAPP_CGI,
            WEBAPP_FILES,
            WEBAPP_PROXY,
            WEBAPP_REDIRECT,
            WEBAPP_UPLOAD,
            WEBAPP_WEBDAV,
            WEBAPP_WEBSITE,
            WEBAPP_WEBSTART,
            JCALENDARBUTTON,
            NOTES,
            PICTURES,
            SIMPLESERVLETS,
            UPLOAD,
    };
    
    public String STATIC_WEB_FILES = "staticWebFiles";
    public String DEFAULT_STATIC_WEB_FILES = "file:/space/web/";

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
        properties.put(HttpServiceTracker.WEB_ALIAS, alias); 
        
        Servlet servlet = null;
        HttpContext httpContext = null;
        HttpServiceTracker serviceTracker = null; 
        try {
            if (AWSTATS.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.CGIServlet();
            }
            else if (WEBAPP_PROXY.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.proxy.ProxyServlet();
            }
            else if (WEBAPP_CGI.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.CGIServlet();
            }
            else if (WEBAPP_FILES.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.osgi.OSGiFileServlet();
            }
            else if (WEBAPP_REDIRECT.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.redirect.RegexRedirectServlet();
            }
            else if (WEBAPP_UPLOAD.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.upload.UploadServlet();
            }
            else if (WEBAPP_WEBDAV.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.WebdavServlet();
            }
            else if (WEBAPP_WEBSITE.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.osgi.OSGiFileServlet();
            }
            else if (WEBAPP_WEBSTART.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.jnlpservlet.JnlpServlet();
            }
            else if (DOWNLOAD.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.osgi.OSGiFileServlet();
            }
            else if (GIT_WEB.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.CGIServlet();
            }
            else if (NOTES.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.WebdavServlet();
            }
            else if (UPLOAD.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.upload.UploadServlet();
            }
            else if ((JBUNDLE.equalsIgnoreCase(alias))
                    || (JCALENDARBUTTON.equalsIgnoreCase(alias))
                    || (CALENDARPANEL.equalsIgnoreCase(alias))
                    || (SIMPLESERVLETS.equalsIgnoreCase(alias))
                    || (PICTURES.equalsIgnoreCase(alias))
                        )
            {   // Everything else is a pointer to a static resource
                servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(RedirectServlet.class.getName());
                properties.put(RedirectServlet.MATCH_PARAM, DBConstants.BLANK);
                properties.put(RedirectServlet.TARGET, Utility.addURLPath(alias, "index.html"));
                String urlCodeBase = context.getProperty(STATIC_WEB_FILES);
                if (urlCodeBase == null)
                    urlCodeBase = DEFAULT_STATIC_WEB_FILES;
                if (!"/".equals(alias))
                    urlCodeBase = Utility.addURLPath(urlCodeBase, alias) + "/"; // Should have trailing '/'
                properties.put(OSGiFileServlet.BASE_PATH, urlCodeBase);
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
}
