/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.config.web.other;

import java.util.Dictionary;

import javax.servlet.Servlet;

import org.jbundle.base.util.DBConstants;
import org.jbundle.base.util.Utility;
import org.jbundle.util.osgi.finder.ClassServiceUtility;
import org.jbundle.util.webapp.files.FilesDefaultServlet;
import org.jbundle.util.webapp.osgi.FileHttpContext;
import org.jbundle.util.webapp.osgi.HttpServiceTracker;
import org.jbundle.util.webapp.osgi.OSGiFileServlet;
import org.jbundle.util.webapp.redirect.RedirectServlet;
import org.osgi.service.http.HttpContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Start up the web service listener.
 * @author don
 */
public class HttpServiceActivator extends org.jbundle.config.web.httpservice.MultipleHttpServiceActivator
{
    public static final String JBUNDLE = "jbundle";
    public static final String DOWNLOAD = "download";
    public static final String GIT_WEB = "git-web";
    public static final String AWSTATS = "awstats";

    public static final String JCALENDARBUTTON = JBUNDLE + "/jcalendarbutton";
    public static final String SIMPLESERVLETS = JBUNDLE + "/simpleservlets";
    public static final String OSGI_WEBSTART = JBUNDLE + "/osgi-webstart";
    public static final String CALENDARPANEL = JBUNDLE + "/calendarpanel";
    public static final String CALENDARPANEL_JNLP = JBUNDLE + "/calendarpanel/jnlp";

    public static final String WEBAPP_FILES = SIMPLESERVLETS + "/files";
    public static final String WEBAPP_WEBSITE = SIMPLESERVLETS + "/website";
    public static final String WEBAPP_REDIRECT = SIMPLESERVLETS + "/redirect";
    public static final String WEBAPP_PROXY = SIMPLESERVLETS + "/proxy";
    public static final String WEBAPP_UPLOAD = SIMPLESERVLETS + "/upload";
    public static final String WEBAPP_WEBDAV = SIMPLESERVLETS + "/webdav";
    public static final String WEBAPP_CGI = SIMPLESERVLETS + "/cgi";
    public static final String WEBAPP_WEBSTART = SIMPLESERVLETS + "/webstart";

    public static final String PICTURES = "pictures";

    public static final String UPLOAD = "upload";
    public static final String NOTES = "notes";

    private String[] aliases = {
            JCALENDARBUTTON,
            JBUNDLE,
            CALENDARPANEL_JNLP,
            CALENDARPANEL,
            AWSTATS,
//x         "demo",
            DOWNLOAD,
            GIT_WEB,
            WEBAPP_CGI,
            WEBAPP_FILES,
            WEBAPP_WEBSITE,
            WEBAPP_REDIRECT,
            WEBAPP_PROXY,
            WEBAPP_UPLOAD,
            WEBAPP_WEBDAV,
            WEBAPP_WEBSTART,
            NOTES,
            PICTURES,
            SIMPLESERVLETS,
            OSGI_WEBSTART,
            UPLOAD,
    };
    
    public final String STATIC_WEB_FILES = "staticWebFiles";
    public final String DEFAULT_STATIC_WEB_FILES = "file:/space/web/";

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
            if (WEBAPP_WEBSITE.equalsIgnoreCase(alias))
            {
                //x servlet = new org.jbundle.util.webapp.osgi.OSGiFileServlet();   // Need to redirect from root url
                servlet = new org.jbundle.util.webapp.redirect.RegexRedirectServlet();
                this.addRedirectProperties(alias, properties);
            }
            else if (WEBAPP_FILES.equalsIgnoreCase(alias))
            {
                servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(FilesDefaultServlet.class.getName());
                if (servlet == null)    // Fallback to OSGiServlet
                    servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(RedirectServlet.class.getName());
                httpContext = new FileHttpContext(context.getBundle());
                this.addRedirectProperties(alias, properties);
                properties.put(FilesDefaultServlet.BASE_PATH, "/space/web/");
            }
            else if (WEBAPP_REDIRECT.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.redirect.RegexRedirectServlet();
                this.addRedirectProperties(alias, properties);
            }
            else if (WEBAPP_PROXY.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.proxy.ProxyServlet();
            }
            else if (WEBAPP_UPLOAD.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.upload.UploadServlet();
            }
            else if (WEBAPP_CGI.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.CGIServlet();
            }
            else if (WEBAPP_WEBDAV.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.WebdavServlet();
            }
            else if (WEBAPP_WEBSTART.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.jnlpservlet.JnlpServlet();
            }
            else if (AWSTATS.equalsIgnoreCase(alias))
            {
                servlet = new org.apache.catalina.servlets.CGIServlet();
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
            else if (CALENDARPANEL_JNLP.equalsIgnoreCase(alias))
            {
                servlet = new org.jbundle.util.webapp.jnlpservlet.JnlpServlet();
            }
            else if ((JBUNDLE.equalsIgnoreCase(alias))
                    || (JCALENDARBUTTON.equalsIgnoreCase(alias))
                    || (CALENDARPANEL.equalsIgnoreCase(alias))
                    || (SIMPLESERVLETS.equalsIgnoreCase(alias))
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
        properties.put(RedirectServlet.MATCH_PARAM, DBConstants.BLANK);
        String lastPath = alias;
        if (lastPath.lastIndexOf("/") != -1)
            lastPath = lastPath.substring(lastPath.lastIndexOf("/") + 1);
        properties.put(RedirectServlet.TARGET, Utility.addURLPath(lastPath, "index.html"));
        String urlCodeBase = context.getProperty(STATIC_WEB_FILES);
        if (urlCodeBase == null)
            urlCodeBase = DEFAULT_STATIC_WEB_FILES;
        if (!"/".equals(alias))
            urlCodeBase = Utility.addURLPath(urlCodeBase, alias) + "/"; // Should have trailing '/'
        properties.put(OSGiFileServlet.BASE_PATH, urlCodeBase);        
    }
}
