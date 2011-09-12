/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.client.RemoteFieldTable;
import org.jbundle.thin.base.db.client.memory.MemoryRemoteTable;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.JScreen;
import org.jbundle.thin.base.screen.JScreenToolbar;
import org.jbundle.thin.base.screen.grid.ThinTableModel;
import org.jbundle.thin.base.screen.grid.opt.JAltGridScreen;
import org.jbundle.thin.base.util.Application;

import com.tourapp.thin.tour.request.db.Request;
import com.tourapp.thin.tour.request.db.RequestDetail;
import com.tourapp.thin.tour.request.db.RequestInput;

/**
 * Main Class for applet OrderEntry
 */
public class RequestMainScreen extends JBaseScreen
{
    private static final long serialVersionUID = 1L;

    protected JScreen m_screenPerson = null;
    protected ThinTableModel m_modelBrochures = null;
    protected JAltGridScreen m_gridscreen = null;
    protected FieldList m_recRequestInput = null;
    protected FieldList m_recItem = null;
    protected FieldList m_recRequest = null;
    protected FieldList m_recRequestDetail = null;
    protected FieldList m_recRequestInputRemote = null;
    protected FieldList m_recBundle = null;
    protected RemoteSession m_remoteSession = null;
    
    /**
     *  Request Constructor.
     */
    public RequestMainScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public RequestMainScreen(Object parent, Object obj)
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
        
        this.addSubPanels(this, this.getBaseApplet());
    }
    /**
     * Check the access rights for this screen.
     * (Override this to allow or deny fine-grained access)
     */
    public int checkSecurity(Application application)
    {
        return Constants.NORMAL_RETURN;
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
        m_screenPerson = new CompanyScreen(parent, record);
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
     * Update the order object with the current screen information.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        if (super.doAction(strAction, iOptions))
            return true;
        if (strAction == Constants.SUBMIT)
        {
            m_gridscreen.controlsToFields();    // Move the current quantities to the fields.
            FieldTable table = m_recRequestInput.getTable();
            try   {
                table.close();
                while (table.hasNext())
                {
                    table.next();
                    int iQuantity = (int)m_recRequestInput.getField(RequestInput.BROCHURE_QTY).getValue();
                    if (iQuantity > 0)
                    {
                        m_recRequestDetail.getTable().addNew();
                        m_recRequestDetail.getField(RequestDetail.BROCHURE_ID).setData(m_recRequestInput.getField(RequestInput.BROCHURE_ID).getData());
                        m_recRequestDetail.getField(RequestDetail.BROCHURE_QTY).setData(m_recRequestInput.getField(RequestInput.BROCHURE_QTY).getData());
                        m_recRequestDetail.getTable().add(m_recRequestDetail);
                    }
                }
            } catch (Exception ex)  {
                ex.printStackTrace();
            }
            return this.handleAction(Constants.RESET, this, iOptions);  // Now, reset the submitted fields
        }
        if (strAction == Constants.RESET)
        {
            FieldTable table = m_recRequestInput.getTable();
            try   {
                table.close();
                while (table.hasNext())
                {
                    table.next();
                    table.edit(); // This is a memory based table, so this will be fast.
                    m_recRequestInput.getField(RequestInput.BROCHURE_QTY).initField(true);
                    table.set(m_recRequestInput);
                }
            } catch (Exception ex)  {
                ex.printStackTrace();
            }
            m_gridscreen.fieldsToControls();    // Move the new (blank) values to the screen.
            return true;
        }
        return false;
    }
    /**
     * Popup item selected.
     */
    public void actionPerformed(ActionEvent event)
    {
        if (m_modelBrochures == null)
            return;
        if (!(event.getSource() instanceof JComboBox))
            return;
        JComboBox m_popupBox = (JComboBox)event.getSource();
        int iIndex = m_popupBox.getSelectedIndex();
        try   {
            if (m_recBundle.getTable().get(iIndex) != null)
            {
                getItemList();      // Re-query the new item list
            }
            m_modelBrochures.resetTheModel(); // Invalidate and requery the new model
        } catch (Exception ex)  {
            // Ignore, catalog unchanged
            ex.printStackTrace();
        }
    }
    /**
     * This method sets up the brochure list for display.
     * NOTE: This MUST be moved down to the server.
     */
    public FieldList getItemList()
    {
        if (m_recRequestDetail == null)
        {
            m_recRequestDetail = new RequestDetail(this);
            this.getBaseApplet().linkRemoteSessionTable(m_remoteSession, m_recRequestDetail, false);
            m_recRequestInputRemote = new RequestInput(this); // Remote version
            this.getBaseApplet().linkRemoteSessionTable(m_remoteSession, m_recRequestInputRemote, false);
            m_recRequestInput = new RequestInput(this);   // Local (memory-based) version
            MemoryRemoteTable table = new MemoryRemoteTable(m_recRequestInput);
            new RemoteFieldTable(m_recRequestInput, table, null);
        }
        else
        {
            try   {
                FieldTable tableInput = m_recRequestInput.getTable();
                tableInput.close();
                while (tableInput.hasNext())
                {   // Clear the memory based table
                    tableInput.next();
                    tableInput.remove();
                }
            } catch (Exception ex)  {
                ex.printStackTrace();
            }
        }
        FieldTable tableInput = m_recRequestInput.getTable();
        try   {
            FieldTable table = m_recRequestInputRemote.getTable();
            m_remoteSession.doRemoteAction("RebuildRequestInput", null);    // Rebuild the list
            table.close();
            while (table.hasNext())
            {
                table.next();
                tableInput.addNew();
                m_recRequestInput.getField(RequestInput.BROCHURE_ID).setData(m_recRequestInputRemote.getField(RequestInput.BROCHURE_ID).getData());
                m_recRequestInput.getField(RequestInput.BROCHURE_DESC).setData(m_recRequestInputRemote.getField(RequestInput.BROCHURE_DESC).getData());
                tableInput.add(m_recRequestInput);
            }
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        return m_recRequestInput;
    }
    /**
     * Build the right pane (that displays the items for selection)
     */
    public void setupItemPane(Container parent)
    {
        FieldList record = this.getItemList();
        m_modelBrochures = new RequestGridModel(record.getTable());
        m_gridscreen = new JAltGridScreen(null, m_modelBrochures);
        m_gridscreen.setAlignmentX(RIGHT_ALIGNMENT);
        m_gridscreen.setAlignmentY(TOP_ALIGNMENT);
        parent.add(m_gridscreen);
        
        m_modelBrochures.setGridScreen(m_gridscreen, true);
    }
    /**
     * Show or hide the "Company" field depending on the checkbox, then redisplay the catalog
     * and focus on the first field.
     */
    public void showCompany(boolean bShow)
    {
        m_modelBrochures.resetTheModel(); // Invalidate and requery the new model
        this.getBaseApplet().invalidate();
        this.getBaseApplet().validate();
        this.getBaseApplet().repaint();
    }
    /**
     * Is the company checkbox clicked?
     */
    public boolean isCompanySelected()
    {
        if (m_screenPerson instanceof CompanyScreen)
            return ((CompanyScreen)m_screenPerson).isCompanySelected();
        return false; // Default
    }
    /**
     * Add the toolbars?
     */
    public JComponent createToolbar()
    {
        return new JScreenToolbar(this, null);
    }
    /**
     * Add the scrollbars?
     */
    public boolean isAddScrollbars()
    {
        return true;
    }
}
