/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.config.web.other;

import java.util.Dictionary;

import javax.servlet.Servlet;

import org.jbundle.base.model.DBConstants;
import org.jbundle.base.model.Utility;
import org.jbundle.base.util.Environment;
import org.jbundle.model.Env;
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
import org.osgi.service.http.HttpContext;
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
    public static final String REPOSITORY = "repository";

    public static final String JCALENDARBUTTON = JBUNDLE + "/jcalendarbutton";
    public static final String WEBAPP = JBUNDLE + "/webapp";
    public static final String OSGI_WEBSTART = JBUNDLE + "/osgi-webstart";
    public static final String AUTO_WEBSTART = JBUNDLE + "/auto-webstart";
    public static final String OSGI = JBUNDLE + "/osgi";
    public static final String JBUNDLE_SITE = JBUNDLE + "/jbundle";
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
    public static final String WEBAPP_WEBSTART_APPLET = WEBAPP + "/webstart.html";

    public static final String PICTURES = "pictures";

    public static final String UPLOAD = "upload";
    public static final String NOTES = "notes";
    public static final String DEMO = "demo";

    private String[] aliases = {
            JCALENDARBUTTON,
            JBUNDLE,
            CALENDARPANEL_JNLP,
            CALENDARPANEL,
//            AWSTATS,
            REPOSITORY,
            DEMO,
            DOWNLOAD,
//            GIT_WEB,
//            WEBAPP_CGI,
            WEBAPP_FILES,
            WEBAPP_WEBSITE,
            WEBAPP_REDIRECT,
            WEBAPP_PROXY,
            WEBAPP_UPLOAD,
            WEBAPP_WEBDAV,
//            WEBAPP_WEBSTART,          // Already enabled by osgi-webstart
//            WEBAPP_WEBSTART_APPLET,   // Already enabled by osgi-webstart
            NOTES,
            PICTURES,
            WEBAPP,
            AUTO_WEBSTART,
            OSGI_WEBSTART,
            OSGI,
            JBUNDLE_SITE,
            UPLOAD,
    };
    
    public final String STATIC_WEB_FILES = "staticWebFiles";
    public final String DEFAULT_STATIC_WEB_PATH = "/web/";
    public final String DEFAULT_STATIC_WEB_FILES = "file:" + DEFAULT_STATIC_WEB_PATH;

    /**
     * Make sure the dependent services are up, then call startupService.
     * @param versionRange Bundle version
     * @param baseBundleServiceClassName
     * @return false if I'm waiting for the service to startup.
     */
    public boolean checkDependentServices(BundleContext bundleContext)
    {
    	boolean success = this.addDependentService(context, Env.class.getName(), Environment.class.getName(), null, null);
    	success = success & super.checkDependentServices(bundleContext);
    	return success;
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
                httpContext = new FileHttpContext(servlet, context.getBundle());
                String path = DEFAULT_STATIC_WEB_PATH;
                properties.put(DefaultServlet.BASE_PATH, path);
            }
            else if (DEMO.equalsIgnoreCase(alias))
            {
                servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(RedirectServlet.class.getName());
                httpContext = new FileHttpContext(servlet, context.getBundle());
                String path = this.getProperty("wwwDemoPath");    // Basepath (usually pointing to a directory) is supplied in menu properties
                if (path == null)
                    path = "com/tourapp/res/docs/com/tourgeek/www/";
                properties.put(DefaultServlet.BASE_PATH, path);
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
                properties.put(WebappServlet.BASE_PATH, DEFAULT_STATIC_WEB_PATH + "jbundle/webapp/webdav");
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
                    || (AUTO_WEBSTART.equalsIgnoreCase(alias))
                    || (OSGI_WEBSTART.equalsIgnoreCase(alias))
                    || (OSGI.equalsIgnoreCase(alias))
                    || (JBUNDLE_SITE.equalsIgnoreCase(alias))
                    || (PICTURES.equalsIgnoreCase(alias))
                    || (REPOSITORY.equalsIgnoreCase(alias))
                    || (WEBAPP_WEBSTART.equalsIgnoreCase(alias))
                    || (WEBAPP_WEBSTART_APPLET.equalsIgnoreCase(alias))
                        )
            {   // Everything else is a pointer to a static resource - Note: These resources are in the '/web' directory
                servlet = (Servlet)ClassServiceUtility.getClassService().makeObjectFromClassName(RedirectServlet.class.getName());
                this.addRedirectProperties(alias, properties);
                if (OSGI_WEBSTART.equalsIgnoreCase(alias))
                {
                    properties.put(RedirectServlet.MATCH, ".*");
                    properties.remove(BaseOsgiServlet.BASE_PATH);        
                    properties.put(RedirectServlet.TARGET, "/" + AUTO_WEBSTART);
                }
                if (REPOSITORY.equalsIgnoreCase(alias))
                {   // Lives at jbundle/repository, served from /repository
                    this.addRedirectProperties(JBUNDLE + "/" + alias, properties);
                    properties.remove(RedirectServlet.MATCH);
                    properties.remove(RedirectServlet.TARGET);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (servlet != null)
        {
            if (httpContext == null)   
                httpContext = this.getHttpContext();
            if (serviceTracker == null)
                serviceTracker = this.createServiceTracker(context, httpContext, properties);
            serviceTracker.setServlet(servlet);
        }
        return serviceTracker;
    }
    /**
     * Add properties to redirect root to index.html
     * @param alias
     * @param properties
     */
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
