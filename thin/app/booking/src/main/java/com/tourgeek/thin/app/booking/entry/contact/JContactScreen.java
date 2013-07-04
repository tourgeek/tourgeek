/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.contact;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.JComponent;
import javax.swing.JMenuBar;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.screen.JScreen;

import com.tourgeek.thin.tour.booking.db.Booking;

/**
 * Main Class for applet OrderEntry
 */
public class JContactScreen extends JScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JContactScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JContactScreen(Object parent,Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(Object parent, Object obj)
    {
        super.init(parent, obj);
    }
    /**
     * Free.
     */
    public void free()
    {
        if (this.getFieldList(0) != null)
            this.disconnectControls(this.getFieldList(0));
        this.removeFieldList(null);    // Another screen will free Booking.
        super.free();
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        switch (iIndex)
        {
        case 0:
            return this.getFieldList().getField(Booking.GENERIC_NAME);
        case 1:
            return this.getFieldList().getField(Booking.ADDRESS_LINE_1);
        case 2:
            return this.getFieldList().getField(Booking.ADDRESS_LINE_2);
        case 3:
            return this.getFieldList().getField(Booking.CITY_OR_TOWN);
        case 4:
            return this.getFieldList().getField(Booking.STATE_OR_REGION);
        case 5:
            return this.getFieldList().getField(Booking.POSTAL_CODE);
        case 6:
            return this.getFieldList().getField(Booking.COUNTRY);
        case 7:
            return this.getFieldList().getField(Booking.TEL);
        case 8:
            return this.getFieldList().getField(Booking.FAX);
        case 9:
            return this.getFieldList().getField(Booking.EMAIL);
        default:
        }
        return null;
    }
    /**
     * Add the scrollbars?
     * For maint screens, default to true.
     */
    public boolean isAddScrollbars()
    {
        return true;
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        return null;
    }
    /**
     * Add the menubars?
     * @return The newly created menubar or null.
     */
    public JMenuBar createMenubar()
    {
        return null;
    }
}
