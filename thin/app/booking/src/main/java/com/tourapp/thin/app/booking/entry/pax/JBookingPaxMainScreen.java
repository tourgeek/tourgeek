/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.pax;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.Container;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.client.RemoteFieldTable;
import org.jbundle.thin.base.remote.RemoteException;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.remote.RemoteTable;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.util.JMainScreen;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.tour.booking.db.BookingPax;
import com.tourapp.thin.tour.product.base.db.PaxCategory;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingPaxMainScreen extends JMainScreen
{
	private static final long serialVersionUID = 1L;

	/**
     * The remote session.
     */
    protected RemoteSession m_remoteSession = null;
    /**
     * The passenger room categories (ie., single, double, etc).
     */
    protected PaxCategory m_recPaxCategory = null;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingPaxMainScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingPaxMainScreen(Object parent,Object obj)
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
    public void init(Object parent, Object applet)
    {
        super.init(parent, applet);
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        boolean success = super.addSubPanels(parent);
        if (!success)
        	return success;

        // Next, add the summary screen.
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        FieldList recBooking = tourAppScreen.getFieldList();
        
        JBaseScreen bottomPane = new JBookingPaxSummaryScreen(this, recBooking);
        bottomPane.setOpaque(false);
        bottomPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        parent.add(bottomPane);
        return success;
    }
    /**
     * Create a screen of this type.
     */
    public JBaseScreen createNewScreen(String strType, Object record)
    {
        JBaseScreen screenNew = null;
        if (record == null)
        { // First time only
            TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            RemoteSession parentSessionObject = tourAppScreen.getRemoteSession();
            try {
                m_remoteSession = (RemoteSession)parentSessionObject.makeRemoteSession("com.tourapp.tour.booking.remote.booking.BookingPaxSession");
                record = new BookingPax(this);
                ((BookingPax)record).getField(BookingPax.PAX_CATEGORY_ID).setDefault(new Integer(PaxCategory.DOUBLE_ID));   // Room type defaults to double
                RemoteFieldTable table = (RemoteFieldTable)tourAppScreen.linkRemoteSessionTable(m_remoteSession, (BookingPax)record, true);
                RemoteTable tableRemote = table.getRemoteTableType(null);
                FieldList recBooking = tourAppScreen.getFieldList();
                tableRemote = new PaxCountRemoteTable(tableRemote, recBooking);
                table.setRemoteTable(tableRemote, null);
                if (m_recPaxCategory == null)
                {  // Now set up the PaxCategory (remember TourApp will automatically set this up as a TABLE link).
                    m_recPaxCategory = new PaxCategory(this);
                    boolean bUseCache = true;
                    tourAppScreen.linkRemoteSessionTable(parentSessionObject, m_recPaxCategory, bUseCache);
                }
            } catch (RemoteException ex)    {
                ex.printStackTrace();
            }
        }
        if (Constants.FORM.equalsIgnoreCase(strType))
            screenNew = new JBookingPaxScreen(this, record);
        else if (Constants.GRID.equalsIgnoreCase(strType))
            screenNew = new JBookingPaxGridScreen(this, record);
        else
            screenNew = super.createNewScreen(strType, record);  // Never
        return screenNew;
    }
    /**
     * Get the remote session.
     */
    public RemoteSession getRemoteSession()
    {
        return m_remoteSession;
    }
    /**
     * Get the pax category.
     */
    public PaxCategory getPaxCategory()
    {
        return m_recPaxCategory;
    }
}
