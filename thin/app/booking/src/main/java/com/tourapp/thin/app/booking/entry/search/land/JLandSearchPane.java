/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.land;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;

import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;

import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;

/**
 * Main Class for applet OrderEntry
 */
public class JLandSearchPane extends JProductSearchPane
{
	private static final long serialVersionUID = 1L;

    /**
     *  OrderEntry Class Constructor.
     */
    public JLandSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JLandSearchPane(Object parent, RemoteSession remoteSession)
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
        BaseApplet applet = this.getBaseApplet();

        JComponent text = this.buildTextEdit(applet, m_remoteSession, applet.getString(SearchConstants.SEARCH_TEXT), SearchConstants.SEARCH_TEXT);
        this.add(text);

        JComponent location = this.buildLookupEdit(applet, m_remoteSession, null, null, null);
        this.add(location);

        JComponent pax = this.buildTextEdit(applet, m_remoteSession, applet.getString(SearchConstants.PAX), SearchConstants.PAX);
        this.add(pax);
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() instanceof JComponent)
        {
            String strButtonName = ((JComponent)evt.getSource()).getName();
            if (SearchConstants.PAX.equals(strButtonName))
            {   // User manually typed the location
                String strString = ((JTextField)evt.getSource()).getText();
                PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(evt.getSource(), SearchConstants.PAX, null, strString);
                this.getMainSearchPane().getTourAppScreen().getParams().propertyChange(propChangeEvent);
            }
        }
        super.actionPerformed(evt);
    }
    /**
     * Property change (listen for date change from JLocationScreen).
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        String strPropertyName = evt.getPropertyName();
        if (SearchConstants.PAX.equalsIgnoreCase(strPropertyName))
        {
            if (evt.getSource() != this)
                this.requeryRemoteSession();
        }
        else
            super.propertyChange(evt);
    }
    /**
     * Get the remote table name.
     */
    public String getRemoteTableName()
    {
        return ProductConstants.LAND;
    }
}
