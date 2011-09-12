/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.cruise;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import com.tourapp.thin.app.booking.entry.search.base.JProductContextScreen;

/**
 * Main Class for Product Context screen.
 */
public class JCruiseContextScreen extends JProductContextScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JCruiseContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JCruiseContextScreen(Object parent, Object obj)
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
}
