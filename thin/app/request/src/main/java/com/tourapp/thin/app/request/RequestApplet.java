/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@tourgeek.com
 *  @version 1.0.0.
 */
import java.awt.BorderLayout;
import java.awt.Container;

import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseFrame;


/**
 * Main Class for applet OrderEntry
 */
public class RequestApplet extends BaseApplet
{
	private static final long serialVersionUID = 1L;

    /**
     *  OrderEntry Class Constructor.
     */
    public RequestApplet()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public RequestApplet(String args[])
    {
        this();
        this.init(args);
    }
    /**
     * The getAppletInfo() method returns a string describing the applet's
     * author, copyright date, or miscellaneous information.
     */
    public String getAppletInfo()
    {
        return "Name: Thin Test\r\n" +
               "Author: Don Corley\r\n" +
               "E-Mail: don@tourgeek.com";
    }
    /**
     *  The main() method acts as the applet's entry point when it is run
     *  as a standalone application. It is ignored if the applet is run from
     *  within an HTML page.
     */
    public static void main(String args[])
    {
        BaseApplet.main(args);
        RequestApplet applet = (RequestApplet)RequestApplet.getSharedInstance();
        if (applet == null)
            applet = new RequestApplet(args);
        new JBaseFrame("Brochure requests", applet);
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        parent.setLayout(new BorderLayout());
        RequestMainScreen baseScreen = new RequestMainScreen(parent, this);
        return this.changeSubScreen(parent, baseScreen, null);
    }
}
