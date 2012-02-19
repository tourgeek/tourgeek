/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking;

/**
 * OrderEntry.java:   Applet
 *  Copyright © 2012 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@tourgeek.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseFrame;
import org.jbundle.thin.base.screen.JScreen;


/**
 * Main Class for applet OrderEntry
 */
public class BookingApplet extends BaseApplet
{
	private static final long serialVersionUID = 1L;

    /**
     *  OrderEntry Class Constructor.
     */
    public BookingApplet()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public BookingApplet(String[] args)
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
        return "Name: Booking Applet\r\n" +
               "Author: Don Corley\r\n" +
               "E-Mail: don@tourgeek.com";
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        parent.setLayout(new BoxLayout(parent, BoxLayout.X_AXIS));
//      JScreen thinscreen = new TestThinScreen(null);
//      FieldList record = thinscreen.getFieldList();
//      this.linkNewRemoteTable(null, record);

        FieldList record = null;
//      FieldList record = new com.tourapp.thin.main.Menus(this);  // If overriding class didn't set this
//      this.linkNewRemoteTable(null, record);
        if (this.getProperty(Params.MENU) == null)
            this.setProperty(Params.MENU, "1205");
        JScreen thinscreen = new org.jbundle.thin.base.screen.menu.JRemoteMenuScreen(this, record);    // test
//      JScreen thinscreen = new com.tourapp.thin.app.booking.lookup.BookingLookupScreen(this, record);  // test
 
        thinscreen.setMinimumSize(new Dimension(100, 100));
        thinscreen.setAlignmentX(LEFT_ALIGNMENT);
        thinscreen.setAlignmentY(TOP_ALIGNMENT);
        parent.add(thinscreen);
        return true;
    }
    /**
     *  The main() method acts as the applet's entry point when it is run
     *  as a standalone application. It is ignored if the applet is run from
     *  within an HTML page.
     */
    public static void main(String args[])
    {
        BaseApplet.main(args);
        BookingApplet applet = (BookingApplet)BookingApplet.getSharedInstance();
        if (applet == null)
            applet = new BookingApplet(args);
        new JBaseFrame("Booking entry", applet);
    }
    /**
     * Update the order object with the current screen information.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        boolean bHandled = super.doAction(strAction, iOptions);
        if (strAction == Constants.SUBMIT)
            return true;
        return bHandled;
    }
}
