package com.tourapp.thin.app.booking.entry.search.air;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;
import com.tourapp.thin.tour.product.air.db.AirClass;

/**
 * Main Class for applet OrderEntry
 */
public class JAirSearchPane extends JProductSearchPane
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JAirSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JAirSearchPane(Object parent, RemoteSession remoteSession)
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

        JComponent location = this.buildLookupEdit(applet, m_remoteSession, null, null, null);
        this.add(location);

        location = this.buildLookupEdit(applet, m_remoteSession, applet.getString(SearchConstants.LOCATION_TO), SearchConstants.LOCATION_TO, null);
        this.add(location);

        FieldList recFlightClass = new AirClass(this);
        this.addFieldList(recFlightClass);
        JComboBox popup = this.buildLinkedPopup(applet, m_remoteSession, recFlightClass, applet.getString(SearchConstants.AIR_CLASS), AirClass.DESCRIPTION, SearchConstants.AIR_CLASS, AirClass.ID, null);
        this.add(popup);

        location = this.buildLookupEdit(applet, m_remoteSession, applet.getString(SearchConstants.AIRLINE), SearchConstants.AIRLINE, "Lookup");
        this.add(location);
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() instanceof JComponent)
        {
            String strButtonName = ((JComponent)evt.getSource()).getName();
            if (SearchConstants.AIR_CLASS.equals(strButtonName))
            {
                this.popupPropChange(evt, SearchConstants.AIR_CLASS);
                return;
            }
            if (SearchConstants.AIRLINE.equals(strButtonName))
            {   // User manually typed the location
                String strString = ((JTextField)evt.getSource()).getText();
                PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(evt.getSource(), SearchConstants.AIRLINE, null, strString);
                this.getMainSearchPane().getTourAppScreen().getParams().propertyChange(propChangeEvent);
            }
            if (strButtonName != null)
                if ((strButtonName.startsWith(SearchConstants.AIRLINE))
                    && (strButtonName.endsWith(SearchConstants.BUTTON)))
            {   // Create and display the Location Window.
                BaseApplet job = new BaseApplet();
                Map<String,Object> properties = null;
                job.initTask(this.getBaseApplet().getApplication(), properties);
                String strLocationParam = strButtonName.substring(0, strButtonName.lastIndexOf(SearchConstants.BUTTON));
                AirlineGridScreen gridScreen = new AirlineGridScreen(job, strLocationParam);
                job.changeSubScreen(null, gridScreen, null);
                TourAppScreen screenMain = this.getMainSearchPane().getTourAppScreen();
                gridScreen.addPropertyChangeListener(screenMain.getParams());
                job.run();
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
        if (SearchConstants.AIRLINE.equalsIgnoreCase(strPropertyName))
        {
            String strLocation = (String)evt.getNewValue();
            Component component = this.getComponentByName(strPropertyName);
            if (component instanceof JTextField)
                ((JTextField)component).setText(strLocation);
        }
        if ((SearchConstants.AIR_CLASS.equalsIgnoreCase(strPropertyName))
            || (SearchConstants.AIRLINE.equalsIgnoreCase(strPropertyName)))
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
        return ProductConstants.AIR;
    }
}
