package com.tourapp.thin.app.booking.entry.search.menu;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.JBaseScreen;

import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;

/**
 * Main Class for applet OrderEntry
 */
public class JMenuSearchPane extends JProductSearchPane
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JMenuSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JMenuSearchPane(Object parent, RemoteSession remoteSession)
    {
        this();
        this.init(parent, remoteSession);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(Object parent, RemoteSession remoteSession)
    {
        super.init(parent, remoteSession);
        //+ Add other controls here
    }
    /**
     * Make the remote session.
     */
    public RemoteSession createRemoteSession(JBaseScreen parentScreen)
    {
        return null;    // Not necessary for a menu screen
    }
    /**
     * Requery the remote session and reset the model to redisplay it.
     */
    public void requeryRemoteSession()
    {
        // don't
    }
}
