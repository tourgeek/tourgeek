package com.tourapp.thin.app.booking.lookup;

/**
 * JRemoteMenuScreen.java:  Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import org.jbundle.model.DBException;
import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.remote.RemoteTable;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.action.ActionManager;
import org.jbundle.thin.base.screen.menu.JBaseMenuScreen;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.tour.booking.db.Booking;
import com.tourapp.thin.tour.booking.db.Tour;

/**
 * A Basic data entry screen.
 * This screen is made of a panel with a GridBagLayout. Labels in the first column, aligned right.
 * Data fields in the second column aligned left.
 */
public class BookingLookupScreen extends JBaseMenuScreen
{
	private static final long serialVersionUID = 1L;

	/**
     * The remote session.
     */
    protected RemoteSession m_remoteSession = null;
    /**
     * The record.
     */
    protected FieldList m_recBookingMenuLookup = null;  // Remote booking record

    /**
     *  JRemoteMenuScreen Class Constructor.
     */
    public BookingLookupScreen()
    {
        super();
    }
    /**
     *  JRemoteMenuScreen Class Constructor.
     */
    public BookingLookupScreen(Object parent,Object record)
    {
        this();
        this.init(parent, record);
    }
    /**
     * Initialize this class.
     */
    public void init(Object parent, Object record)
    {
        super.init(parent, record);
    }
    /**
     * Process this action.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        BaseApplet applet = this.getBaseApplet();
        if (applet != null)
            if ((Util.isNumeric(strAction)) || (Constants.FORM.equalsIgnoreCase(strAction)))
        {
            applet.setProperty(Params.SCREEN, com.tourapp.thin.app.booking.entry.TourAppScreen.class.getName());
            if ((Util.isNumeric(strAction)) && (!"0".equals(strAction)))
                applet.setProperty(Constants.OBJECT_ID, strAction);
            applet.addSubPanels(null);
        }
        return super.doAction(strAction, iOptions);
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        BaseApplet applet = this.getBaseApplet();
        m_remoteSession = applet.makeRemoteSession(null, "com.tourapp.tour.booking.remote.lookup.BookingMenuSession");

        m_recBookingMenuLookup = new BookingMenuLookup(this); // If overriding class didn't set this
        try   {
            RemoteTable remoteTable = m_remoteSession.getRemoteTable(null);   // Main table (BookingMenuLookup)
            // Do not need to cache this (menus are always read once!)
            /*FieldTable table =*/ new org.jbundle.thin.base.db.client.RemoteFieldTable(m_recBookingMenuLookup, remoteTable, applet.getRemoteTask())
            {
                /**
                 * Get the next record (return a null at EOF).
                 * Note: Remember to set the data source before returning a NORMAL_RETURN.
                 * @param iRelPosition The relative records to move.
                 * @return A record status (NORMAL_RETURN means the move was successful).
                 */
                public int doMove(int iRelPosition) throws DBException
                {
                    if (m_iCurrentRecord == -1)
                    {   // Send fake "New Booking" Icon only on the first access.
                        addNew();
                        FieldList recBooking = getRecord();
                        recBooking.getField(Booking.ID).setValue(0, Constants.DISPLAY, Constants.READ_MOVE);
                        recBooking.getField(Booking.DESCRIPTION).setString("New Booking", Constants.DISPLAY, Constants.READ_MOVE);
                        fieldsToData(recBooking);
                        m_iCurrentRecord++;
                        m_iRecordsAccessed++;
                        return Constants.NORMAL_RETURN;   // This is already the current record
                    }
                    return super.doMove(iRelPosition);
                }
            };
        } catch (RemoteException ex)    {
            ex.printStackTrace();
        } catch (Exception ex)  {
            ex.printStackTrace();
        }

        return m_recBookingMenuLookup;
    }
        /**
     * Get the menu name.
     */
    public String getMenuName(FieldList record)
    {
        return record.getField(Tour.DESCRIPTION).toString();
    }
    /**
     * Get the menu icon.
     */
    public String getMenuIcon(FieldList record)
    {
        if (record.getField(Booking.ID).getValue() != 0)
            return "tour/buttons/Tour";   // For now
        else
            return Constants.FORM;
    }
    /**
     * Get the menu command to send to handle command.
     */
    public String getMenuLink(FieldList record)
    {
        return record.getField(Booking.ID).toString();        // Booking no
    }
    /**
     * Add the toolbars?
     * @return The standard menu toolbar, JMenuToolbar.
     */
    public JComponent createToolbar()
    {
        JBaseToolbar toolbar = (JBaseToolbar)super.createToolbar();
        toolbar.addButton(Constants.FORM, Constants.FORM, Constants.FORM, JBaseToolbar.BEFORE_HELP);
        return toolbar;
    }
    /**
     * Add the JMenubar
     * @return The JMenubar.
     */
    public JMenuBar createMenubar()
    {
        JMenuBar menuBar = ActionManager.getActionManager().setupStandardMenu(this);
        JMenu menu = ActionManager.getActionManager().addMenu(menuBar,ThinMenuConstants.RECORD, JBaseToolbar.BEFORE_HELP);
        ActionManager.getActionManager().addMenuItem(menu, Constants.FORM, this, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        return menuBar;
    }
}
