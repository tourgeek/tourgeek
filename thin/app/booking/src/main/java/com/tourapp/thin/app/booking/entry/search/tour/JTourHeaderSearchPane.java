package com.tourapp.thin.app.booking.entry.search.tour;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 */
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;

import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;
import com.tourapp.thin.tour.product.tour.db.TourType;

/**
 * Main Class for applet OrderEntry
 */
public class JTourHeaderSearchPane extends JProductSearchPane
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderSearchPane(Object parent, RemoteSession remoteSession)
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

        FieldList recTourType = new TourType(this);
        this.addFieldList(recTourType);
        JComboBox popup = this.buildLinkedPopup(applet, m_remoteSession, recTourType, applet.getString(SearchConstants.TOUR_TYPE), TourType.DESCRIPTION, SearchConstants.TOUR_TYPE, TourType.ID, null);
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
            if (SearchConstants.TOUR_TYPE.equals(strButtonName))
            {
                this.popupPropChange(evt, SearchConstants.TOUR_TYPE);
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
        if (SearchConstants.TOUR_TYPE.equalsIgnoreCase(strPropertyName))
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
        return "TourHeader";
    }
}
