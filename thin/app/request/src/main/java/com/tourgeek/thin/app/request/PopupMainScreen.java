/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.request;

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
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.BaseApplet;

import com.tourgeek.thin.tour.request.db.Bundle;
import com.tourgeek.thin.tour.request.db.Request;
import com.tourgeek.thin.tour.request.db.RequestControl;

/**
 * Main Class for applet OrderEntry
 */
public class PopupMainScreen extends AgencyMainScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  Request Constructor.
     */
    public PopupMainScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public PopupMainScreen(Object parent, Object obj)
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
        m_remoteSession = applet.makeRemoteSession(null, "com.tourgeek.tour.request.remote.RequestSession");
        FieldList record = new Request(this);
        applet.linkRemoteSessionTable(m_remoteSession, record, false);
        m_screenPerson = new PopupAgencyScreen(parent, record);
        m_recRequest = m_screenPerson.getFieldList();
        applet.linkRemoteSessionTable(m_remoteSession, m_recRequest, false);
        m_screenPerson.setMinimumSize(new Dimension(100, 100));
        m_screenPerson.setAlignmentX(LEFT_ALIGNMENT);
        m_screenPerson.setAlignmentY(TOP_ALIGNMENT);
        parent.add(m_screenPerson);
        
        this.setupItemPane(parent);
        return true;
    }
    /**
     * Build the right pane (that displays the items for selection)
     */
    public void setupItemPane(Container parent)
    {
        FieldList record = this.getItemList();
        m_modelBrochures = new RequestGridModel(record.getTable());
        m_gridscreen = new CheckboxGridScreen(this, m_modelBrochures);

        JComboBox m_popup = this.buildBundlePopup();
        JPanel panelRight = new JPanel();
        panelRight.setOpaque(false);
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.add(m_popup);
        panelRight.add(m_gridscreen);
        panelRight.setPreferredSize(new Dimension(250, 200));   // Max width
        panelRight.setAlignmentX(RIGHT_ALIGNMENT);
        panelRight.setAlignmentY(TOP_ALIGNMENT);
        parent.add(panelRight);
        m_modelBrochures.setGridScreen(m_gridscreen, true);
    }
    /**
     * Fill the bundle popup with the bundle items.
     */
    public JComboBox buildBundlePopup()
    {
        JComboBox m_popup = null;

        m_popup = new JComboBox();
        m_popup.setOpaque(false);
        m_popup.setAlignmentX(LEFT_ALIGNMENT);
        m_popup.setAlignmentY(TOP_ALIGNMENT);
        try   {
            m_recBundle = new Bundle(this);
            FieldTable table = this.getBaseApplet().linkRemoteSessionTable(m_remoteSession, m_recBundle, false);
            RequestControl recRequestControl = new RequestControl(null);
            FieldTable tableRequestControl = this.getBaseApplet().linkRemoteSessionTable(m_remoteSession, recRequestControl, false);
            tableRequestControl.next();     // This will read the control record
            int iDefaultBundleID = (int)recRequestControl.getField(RequestControl.THIN_BUNDLE_ID).getValue();
            int iDefaultIndex = 0;
            for (int iIndex = 0; ; iIndex++)
            {
                if (table.get(iIndex) == null)  // I use get, so the index matches the index of JComboBox
                    break;
                String strCriteria = m_recBundle.getField(Bundle.DESCRIPTION).getString();
                if ((strCriteria != null) && (strCriteria.length() > 0))
                    m_popup.addItem(strCriteria);
                if (iDefaultBundleID == (int)m_recBundle.getField(Bundle.ID).getValue())
                    iDefaultIndex = iIndex;
            }
            m_popup.setSelectedIndex(iDefaultIndex);
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        m_popup.addActionListener(this);    // Requery catalog on change
        return m_popup;
    }
}
