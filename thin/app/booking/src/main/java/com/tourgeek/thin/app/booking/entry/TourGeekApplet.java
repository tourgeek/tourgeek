/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry;

import java.awt.BorderLayout;
import java.awt.Container;

import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseFrame;


public class TourGeekApplet extends BaseApplet
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public TourGeekApplet()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public TourGeekApplet(String[] args)
    {
        this();
        this.init(args);
    }
    /**
     * Initializes the applet.  You never need to call this directly; it is
     * called automatically by the system once the applet is created.
     */
    public void init()
    {
        super.init();
    }
    /**
     * Get the class name or property name of the resource file.
     */
    public void init(String[] args)
    {
        super.init(args);
    }
    /**
     * Called to start the applet.  You never need to call this directly; it
     * is called when the applet's document is visited.
     */
    public void start()
    {
        super.start();
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent, int options)
    {
        parent.setLayout(new BorderLayout());
        
        TourGeekScreen panel = new TourGeekScreen();
        if (!this.checkSecurity(panel))
            return false;
        panel.init(this, null);
        
        parent.add(panel);
        return true;
    }
    /**
     * Called to stop the applet.  This is called when the applet's document is
     * no longer on the screen.  It is guaranteed to be called before destroy()
     * is called.  You never need to call this method directly
     */
    public void stopTask()
    {
        super.stopTask();
    }
    /**
     * Cleans up whatever resources are being held.  If the applet is active
     * it is stopped.
     */
    public void destroy()
    {
        super.destroy();
    }
    /**
     * For Stand-alone.
     */
    public static void main(String[] args)
    {
        BaseApplet.main(args);
        TourGeekApplet applet = (TourGeekApplet)TourGeekApplet.getSharedInstance();
        if (applet == null)
            applet = new TourGeekApplet(args);
        new JBaseFrame("tourgeek", applet);
    }
}
