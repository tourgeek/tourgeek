/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.hotel;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;

import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;
import com.tourapp.thin.tour.product.hotel.db.HotelClass;

/**
 * Main Class for applet OrderEntry
 */
public class JHotelSearchPane extends JProductSearchPane
{
	private static final long serialVersionUID = 1L;

    /**
     *  OrderEntry Class Constructor.
     */
    public JHotelSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JHotelSearchPane(Object parent, RemoteSession remoteSession)
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

        FieldList recHotelClass = new HotelClass(this);
        this.addFieldList(recHotelClass);
        JComboBox popup = this.buildLinkedPopup(applet, m_remoteSession, recHotelClass, applet.getString(SearchConstants.HOTEL_CLASS), HotelClass.DESCRIPTION, SearchConstants.HOTEL_CLASS, HotelClass.ID, null);
        this.add(popup);
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() instanceof JComponent)
        {
            String strButtonName = ((JComponent)evt.getSource()).getName();
            if (SearchConstants.HOTEL_CLASS.equals(strButtonName))
            {
                this.popupPropChange(evt, SearchConstants.HOTEL_CLASS);
                return;
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
        if ((SearchConstants.HOTEL_CLASS.equalsIgnoreCase(strPropertyName))
            || (SearchConstants.NIGHTS.equalsIgnoreCase(strPropertyName)))
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
        return ProductConstants.HOTEL;
    }
}
