/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright © 2012 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.BaseApplet;

import com.tourapp.thin.tour.request.db.Request;

/**
 * Main Class for applet OrderEntry
 */
public class AgencyMainScreen extends RequestMainScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  Request Constructor.
     */
    public AgencyMainScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public AgencyMainScreen(Object parent, Object obj)
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
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent, BaseApplet applet)
    {
        parent.setLayout(new BoxLayout(parent, BoxLayout.X_AXIS));

        m_remoteSession = applet.makeRemoteSession(null, "com.tourapp.tour.request.remote.RequestSession");
        FieldList record = new Request(this);
        applet.linkRemoteSessionTable(m_remoteSession, record, false);
        m_screenPerson = new AgencyScreen(parent, record);
        m_recRequest = m_screenPerson.getFieldList();
        applet.linkRemoteSessionTable(m_remoteSession, m_recRequest, false);
        m_screenPerson.setMinimumSize(new Dimension(100, 100));
        m_screenPerson.setAlignmentX(LEFT_ALIGNMENT);
        m_screenPerson.setAlignmentY(TOP_ALIGNMENT);
        parent.add(m_screenPerson);
        
        this.setupItemPane(parent);
        return true;
    }
}
