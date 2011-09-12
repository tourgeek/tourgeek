/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.BorderLayout;
import java.awt.Container;

import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseFrame;


/**
 * Main Class for applet OrderEntry
 */
public class PopupAgencyApplet extends AgencyApplet
{
	private static final long serialVersionUID = 1L;

    /**
     *  OrderEntry Class Constructor.
     */
    public PopupAgencyApplet()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public PopupAgencyApplet(String[] args)
    {
        this();
        this.init(args);
    }
    /**
     *  The main() method acts as the applet's entry point when it is run
     *  as a standalone application. It is ignored if the applet is run from
     *  within an HTML page.
     */
    public static void main(String args[])
    {
        BaseApplet.main(args);
        PopupAgencyApplet applet = (PopupAgencyApplet)PopupAgencyApplet.getSharedInstance();
        if (applet == null)
            applet = new PopupAgencyApplet(args);
        new JBaseFrame("Brochure Requests", applet);
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        parent.setLayout(new BorderLayout());
        AgencyMainScreen baseScreen = new PopupMainScreen(parent, this);
        return this.changeSubScreen(parent, baseScreen, null);
    }
}
