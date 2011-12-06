/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.base;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.remote.RemoteException;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.JScreen;
import org.jbundle.thin.base.screen.JScreenConstants;
import org.jbundle.thin.base.screen.grid.JGridScreen;
import org.jbundle.thin.base.screen.grid.ThinTableModel;
import org.jbundle.thin.base.screen.util.JDescTextField;
import org.jbundle.thin.base.screen.util.JRemoteComboBox;
import org.jbundle.thin.opt.location.JLocationScreen;
import org.jbundle.thin.opt.location.JTreePanel;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.context.JContextPanel;
import com.tourapp.thin.app.booking.entry.search.JDisplayPanel;
import com.tourapp.thin.app.booking.entry.search.JMainSearchPane;
import com.tourapp.thin.app.booking.entry.search.SearchConstants;

/**
 * Product search parameter input screen.
 */
public class JProductSearchPane extends JBaseScreen
    implements PropertyChangeListener
{
	private static final long serialVersionUID = 1L;

	/**
     * My remote session.
     */
    protected RemoteSession m_remoteSession = null;

    /**
     *  OrderEntry Class Constructor.
     */
    public JProductSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JProductSearchPane(Object parent, RemoteSession remoteSession)
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
        super.init(parent, null);

        m_remoteSession = remoteSession;
        if (m_remoteSession == null)
            m_remoteSession = this.createRemoteSession((JBaseScreen)this.getTargetScreen(JBaseScreen.class));
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        if (screenMain != null)
        {
            this.addPropertyChangeListener(screenMain.getParams());
            screenMain.getParams().addPropertyChangeListener(this);
        }

        BaseApplet applet = this.getBaseApplet();
        if (applet == null)
            applet = BaseApplet.getSharedInstance();
        applet.getApplication().getResources(applet.getApplication().getProperty(Params.RESOURCE), true);   // Make sure I'm using default resources
    }
    /**
     * Free the resources held by this object.
     * NOTE NOTE NOTE: This method does not remove this panel from the parent, or free() sub-panels; you have to do that.
     */
    public void free()
    {
        TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        if (screenMain != null)
        {
            this.removePropertyChangeListener(screenMain.getParams());
            screenMain.getParams().removePropertyChangeListener(this);
        }
        super.free();   // This will free the main record which will free the remote session.
        m_remoteSession = null;
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() instanceof JComponent)
        {
            String strButtonName = ((JComponent)evt.getSource()).getName();
            if ((strButtonName != null)
                && ((strButtonName.startsWith(SearchConstants.LOCATION))
                    && (strButtonName.endsWith(SearchConstants.BUTTON))))
            {   // Create and display the Location Window.
                BaseApplet job = new BaseApplet();
                Map<String,Object> properties = null;
                job.initTask(this.getBaseApplet().getApplication(), properties);
                String strLocationParam = strButtonName.substring(0, strButtonName.lastIndexOf(SearchConstants.BUTTON));
                JLocationScreen locationScreen = new JLocationScreen(job, strLocationParam);
                job.changeSubScreen(null, locationScreen, null);
                TourAppScreen screenMain = this.getMainSearchPane().getTourAppScreen();
                locationScreen.addPropertyChangeListener(screenMain.getParams());
                job.run();
            }
            else if ((strButtonName != null)
                && ((strButtonName.equals(SearchConstants.LOCATION))
                    || (strButtonName.equals(SearchConstants.LOCATION_TO))))
            {   // User manually typed the location
                String strString = ((JTextField)evt.getSource()).getText();
                // First, clear the current location ID (without echoing it to the remote session).
                PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(this, strButtonName + "ID", null, null);
                this.getMainSearchPane().getTourAppScreen().getParams().propertyChange(propChangeEvent);
                // Then, set the new location (for the remote session to process).
                propChangeEvent = new PropertyChangeEvent(evt.getSource(), strButtonName, null, strString);
                this.getMainSearchPane().getTourAppScreen().getParams().propertyChange(propChangeEvent);
            }
            else if (SearchConstants.SEARCH_TEXT.equals(strButtonName))
            {   // User manually typed the location
                String strString = ((JTextField)evt.getSource()).getText();
                PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(evt.getSource(), SearchConstants.SEARCH_TEXT, null, strString);
                this.getMainSearchPane().getTourAppScreen().getParams().propertyChange(propChangeEvent);
            }
            else
                super.actionPerformed(evt);
        }
    }
    /**
     * Property change (listen for date change from JLocationScreen).
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        String strPropertyName = evt.getPropertyName();
        if ((strPropertyName.equals(SearchConstants.LOCATION))
            || (strPropertyName.equals(SearchConstants.LOCATION_TO)))
        {
            String strLocation = (String)evt.getNewValue();
            Component component = this.getComponentByName(strPropertyName);
            if (component instanceof JTextField)
                ((JTextField)component).setText(strLocation);
        }
        
        if ((strPropertyName.equalsIgnoreCase(JTreePanel.LOCATION))
            || (strPropertyName.equalsIgnoreCase(SearchConstants.LOCATION_TO))
            || (strPropertyName.equalsIgnoreCase(SearchConstants.DATE))
            || (strPropertyName.equalsIgnoreCase(SearchConstants.SEARCH_TEXT)))
        {
            if (evt.getSource() != this)
                this.requeryRemoteSession();
        }
//        else
//            super.propertyChange(evt);
    }
    /**
     * 
     * @return True if th(is) search pane is currently displayed.
     */
    public boolean isDisplayed()
    {
       return (this.getMainSearchPane().getTourAppScreen().getSelectedIndex() == TourAppScreen.SEARCH_TAB);
    }
    protected boolean m_bRequeryOnDisplay = false;
    /**
     * Requery the base table if this information is out of date.
     */
    public void requeryRemoteIfStale()
    {
        if (m_bRequeryOnDisplay)
        {
            m_bRequeryOnDisplay = false;
            this.requeryRemoteSession();
        }
    }
    /**
     * Requery the remote session and reset the model to redisplay it.
     */
    public void requeryRemoteSession()
    {
        if (!this.isDisplayed())
        {   // Wait for this screen to be redisplayed for refresh
            m_bRequeryOnDisplay = true;
            return;
        }
        Map<String,Object> properties = this.getMainSearchPane().getTourAppScreen().getParams().getProperties();
        TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        Cursor cursor = null;

        synchronized (this.getBaseApplet().getRemoteTask())
        {
            cursor = this.getBaseApplet().setStatus(Cursor.WAIT_CURSOR, screenMain, null);
            try   {
                Object objReturn = this.getRemoteSession().doRemoteAction(SearchConstants.REQUERY_COMMAND, properties);
                Boolean boolSuccess = null;
                if (objReturn instanceof Boolean)
                    boolSuccess = (Boolean)objReturn; // Success
                if (objReturn instanceof Map)
                {
                    boolSuccess = (Boolean)((Map)objReturn).get(SearchConstants.SUCCESS); // Success
                    if (boolSuccess != null)
                        if (boolSuccess.booleanValue())
                    {   // Something changed... go through the properties and find the change(s)
                        Iterator<String> keys = ((Map)objReturn).keySet().iterator();
                        while (keys.hasNext())
                        {
                            String strKey = keys.next();
                            if (!((Map)objReturn).get(strKey).equals(properties.get(strKey)))
                            {   // This key changed, notify everyone else (saying I am the source keeps this from echoing to me).
                                PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(this, strKey, properties.get(strKey), ((Map)objReturn).get(strKey));
                                this.getMainSearchPane().getTourAppScreen().getParams().propertyChange(propChangeEvent);
                            }
                        }
                    }
                }
                if (boolSuccess != null)
                    if (boolSuccess.booleanValue())
                {
                    JBasePanel displayPanel = this.getMainSearchPane().getTourAppScreen().getCurrentDisplayPanel();
                    if (!(displayPanel instanceof JDisplayPanel))
                        return;
                    JBaseScreen screenDisplay = ((JDisplayPanel)displayPanel).getDisplayScreen();
                    ThinTableModel searchTable = null;//this.getSearchPanel().getTableModel();
                    if (screenDisplay instanceof JGridScreen)
                    {
                        searchTable = ((JGridScreen)screenDisplay).getGridModel();
                        FieldTable table = searchTable.getFieldTable();
                        try   {
                            table.close();
                            table.open();
                        } catch (Exception ex)  {
                            ex.printStackTrace();
                        }
                        int iCurrentRow = searchTable.getSelectedRow();
                        boolean bContextPanelCurrent = false;
                        JContextPanel contextPanel = this.getMainSearchPane().getTourAppScreen().getContextPanel();
                        if (BookingConstants.PRODUCT.equals(contextPanel.getScreenType())
                            && (searchTable instanceof ProductGridModel)
                            && (((ProductGridModel)searchTable).getProductType().equals(contextPanel.getProductType())))
                                bContextPanelCurrent = true;
                        if (iCurrentRow != -1)
                        {
                            ((JGridScreen)screenDisplay).getJTable().clearSelection();
                            if (bContextPanelCurrent)
                                searchTable.selectionChanged(this, -1, -1, org.jbundle.util.calendarpanel.event.MyListSelectionEvent.SELECT);
                        }
                        searchTable.resetTheModel();
                        if (iCurrentRow != -1)
                        {
                            if (bContextPanelCurrent)
                            {
                                ((JGridScreen)screenDisplay).getJTable().setRowSelectionInterval(iCurrentRow, iCurrentRow);
                                searchTable.selectionChanged(this, iCurrentRow, iCurrentRow, org.jbundle.util.calendarpanel.event.MyListSelectionEvent.SELECT);
                            }
                        }
                    }
                    else if (screenDisplay instanceof JScreen)
                    {
                        FieldList record = ((JScreen)screenDisplay).getFieldList();
                        FieldTable table = record.getTable();
                        Object bookmark = record.getField(0).getData();
                        if (bookmark != null)
                        {
                            if (table instanceof org.jbundle.thin.base.db.client.RemoteFieldTable)  // Always
                            {
                                org.jbundle.thin.base.db.client.CachedRemoteTable remoteTable = (org.jbundle.thin.base.db.client.CachedRemoteTable)((org.jbundle.thin.base.db.client.RemoteFieldTable)table).getRemoteTableType(org.jbundle.thin.base.db.client.CachedRemoteTable.class);
                                if (remoteTable != null)
                                    remoteTable.setCache(bookmark, null);   // Clear this cache entry
                            }
                            table.addNew();    // Clear current bookmark
                            record.getField(0).setData(bookmark);
                            if (table.seek(null))
                            {
        //                        record.getFieldTable().edit();
                            }
                        }
                    }
                }
            } catch (Exception ex)  {
                ex.printStackTrace();
            } finally {
                this.getBaseApplet().setStatus(0, screenMain, cursor);
            }
        }
    }
    /**
     *  For the action listener (menu commands).
     */
    public JComponent buildLookupEdit(BaseApplet applet, RemoteSession remoteSession, String strDesc, String strComponentName, String strLookupIcon)
    {
        if (strComponentName == null)
            strComponentName = JTreePanel.LOCATION;
        if (strDesc == null)
            strDesc = applet.getString(strComponentName);
        JPanel panelLocation = new JPanel();
        panelLocation.setOpaque(false);
        panelLocation.setAlignmentX(LEFT_ALIGNMENT);
        panelLocation.setAlignmentY(TOP_ALIGNMENT);
        panelLocation.setLayout(new BoxLayout(panelLocation, BoxLayout.X_AXIS));
        
        JDescTextField text = new JDescTextField(10, strDesc, this);        // Share this with the one in the other folder
        String strValue = (String)this.getMainProperty(strComponentName);
        if (strValue != null)
            text.setText(strValue);
        text.setName(strComponentName);
        panelLocation.add(text);
        text.setMaximumSize(new Dimension(200, 20));    // HACK

        if (strLookupIcon == null)
            strLookupIcon = "Location";
        JButton buttonLocation = new JButton(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + strLookupIcon, strLookupIcon));
        buttonLocation.setPreferredSize(new Dimension(20, 22));
        buttonLocation.setMargin(JScreenConstants.NO_INSETS);
        buttonLocation.setName(strComponentName + SearchConstants.BUTTON);
        buttonLocation.setToolTipText(applet.getString(strLookupIcon + Constants.TIP));
        panelLocation.add(buttonLocation);
        buttonLocation.setAlignmentX(LEFT_ALIGNMENT);
        buttonLocation.setAlignmentY(TOP_ALIGNMENT);
        buttonLocation.addActionListener(this);   // Requery catalog on change

        panelLocation.setAlignmentX(LEFT_ALIGNMENT);
        return panelLocation;
    }
    /**
     * Build a text edit field.
     */
    public JComponent buildTextEdit(BaseApplet applet, RemoteSession remoteSession, String strDesc, String strName)
    {
        JTextField text = new JDescTextField(10, strDesc, this);
        text.setMaximumSize(new Dimension(200, 20));    // HACK
        String strValue = (String)this.getMainProperty(strName);
        if (strValue != null)
            text.setText(strValue);
        text.setName(strName);
        text.setAlignmentX(LEFT_ALIGNMENT);
        return text;
    }
    /**
     * Build a popup box using a remote fieldlist.
     */
    public JComboBox buildLinkedPopup(BaseApplet applet, RemoteSession remoteSession, FieldList record, String strDesc, String strFieldName, String strComponentName, String strIndexField, String strKeyIndex)
    {
        JRemoteComboBox box = new JRemoteComboBox(applet, remoteSession, record, strDesc, strFieldName, strComponentName, true, strIndexField, strKeyIndex);
        box.setMaximumSize(new Dimension(200, 20));    // HACK
        String strValue = (String)this.getMainProperty(strComponentName);
        if (strValue != null)
            box.setControlValue(strValue);
        box.addActionListener(this);    // Requery catalog on change
        box.setAlignmentX(LEFT_ALIGNMENT);
        return box;
    }
    /**
     * Get this property from the main screen.
     */
    public Object getMainProperty(String strKey)
    {
        return this.getMainSearchPane().getTourAppScreen().getParams().get(strKey);
    }
    /**
     * Make the remote session.
     */
    public RemoteSession createRemoteSession(JBaseScreen parentScreen)
    {
        RemoteSession remoteSession = null;
        if (parentScreen == null)
            return null;
        RemoteSession parentSessionObject = ((JMainSearchPane)parentScreen).getTourAppScreen().getRemoteSession();
        String strTableName = this.getRemoteTableName();
        if (strTableName != null)
        {
            String strRemoteSession = "com.tourapp.tour.product.remote.search." + strTableName + "SearchSession";
            try   {
                remoteSession = (RemoteSession)parentSessionObject.makeRemoteSession(strRemoteSession);
//                RemoteTable remoteTable = remoteSession.getRemoteTable(strTableName);
//                this.setFieldList(remoteTable.makeFieldList(null));
//                remoteTable = new com.tourapp.thin.base.db.client.CachedRemoteTable(remoteTable);
//                new com.tourapp.thin.base.db.client.RemoteFieldTable(this.getFieldList(), remoteTable, applet);
            } catch (RemoteException ex)    {
                ex.printStackTrace();
            }
        }
        return remoteSession;
    }
    /**
     * A popup was changed, send the appropriate property change event.
     */
    public void popupPropChange(ActionEvent evt, String strProperty)
    {
        String strID = null;
        if (evt.getSource() instanceof JRemoteComboBox)
        {
            Object objID = ((JRemoteComboBox)evt.getSource()).getControlValue();
            if (objID != null)
                strID = objID.toString();
        }
        else
        {
            int iID = ((JComboBox)evt.getSource()).getSelectedIndex();
            if (iID > 0)
                strID = Integer.toString(iID - 1);
        }
        PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(evt.getSource(), strProperty, null, strID);
        this.getMainSearchPane().getTourAppScreen().getParams().propertyChange(propChangeEvent);
        return;
    }
    /**
     * Get the remote session.
     */
    public RemoteSession getRemoteSession()
    {
        return m_remoteSession;
    }
    /**
     * Get the remote table name.
     */
    public String getRemoteTableName()
    {
        return null;
    }
    /**
     * Get the parent of this screen.
     */
    public JMainSearchPane getMainSearchPane()
    {
        return (JMainSearchPane)this.getTargetScreen(JMainSearchPane.class);
    }
}
