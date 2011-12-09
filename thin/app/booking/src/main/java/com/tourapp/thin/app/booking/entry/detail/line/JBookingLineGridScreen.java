/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.detail.line;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;

import javax.swing.JMenuBar;

import org.jbundle.model.DBException;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.remote.RemoteException;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.grid.JGridScreen;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.detail.JBookingDetailGridScreen;
import com.tourapp.thin.app.booking.entry.detail.JBookingDetailMainScreen;
import com.tourapp.thin.tour.booking.detail.db.BookingLine;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingLineGridScreen extends JGridScreen
{
	private static final long serialVersionUID = 1L;

    /**
     * The remote session.
     */
    protected RemoteSession m_remoteSession = null;

    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingLineGridScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingLineGridScreen(Object parent,Object obj)
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
        this.getGridModel().setAppending(false);
    }
    /**
     * Free.
     */
    public void free()
    {
        super.free();
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public FieldList buildFieldList()
    {
        BookingLine record =  new BookingLine(this);    // If overriding class didn't set this
        
        FieldInfo field = new FieldInfo(record, BookingLine.GROSS, 18, null, null)
        {
            private static final long serialVersionUID = 1L;

            public boolean isVirtual()
            {
                return true;
            }
            public Object doGetData() 
            { // Move raw data from this field
                double dPrice = this.getRecord().getField(BookingLine.PRICE).getValue();
                double dQuantity = this.getRecord().getField(BookingLine.QUANTITY).getValue();
                double dGross = Math.floor(dPrice * dQuantity * 100 + 0.5) / 100;
                return new Double(dGross);
            }
        };
        field.setDataClass(Double.class);

        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        RemoteSession parentSessionObject = tourAppScreen.getRemoteSession();
        try {
            m_remoteSession = (RemoteSession)parentSessionObject.makeRemoteSession("com.tourapp.tour.booking.remote.booking.BookingLineSession");
            
            tourAppScreen.linkRemoteSessionTable(m_remoteSession, (BookingLine)record, true);
        } catch (RemoteException ex)    {
            ex.printStackTrace();
        }
        return record;
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public AbstractThinTableModel createGridModel(FieldList record)
    {
        return new BookingLineGridModel(record.getTable());
    }
    /**
     * Process this action.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        boolean bAction = false;
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            return tourAppScreen.doAction(BookingConstants.SEARCH, iOptions);
        }
        else if (Constants.RESET.equalsIgnoreCase(strAction))
        {
            AbstractThinTableModel model = (AbstractThinTableModel)this.getGridModel();
            FieldTable table = model.getFieldTable();
            table.close();
            model.resetTheModel();
            return true;
        }
        else if (Constants.FORM.equalsIgnoreCase(strAction))
        {
            FieldList recBookingLine = this.makeSelectedRowCurrent();
            if (recBookingLine != null)
                if ((recBookingLine.getEditMode() == Constants.EDIT_CURRENT) || (recBookingLine.getEditMode() == Constants.EDIT_IN_PROGRESS))
            {
                JBaseScreen screen = null;
                Integer intDetailID = (Integer)recBookingLine.getField(BookingLine.BOOKING_DETAIL_ID).getData();
                FieldTable tlbBookingDetail = ((AbstractThinTableModel)tourAppScreen.getCalendarModel()).getFieldTable();
                if ((intDetailID != null) && (intDetailID.intValue() != 0))
                {
                    try {
                        tlbBookingDetail.addNew();
                        tlbBookingDetail.getRecord().getField(Params.ID).setData(intDetailID);
                        if (tlbBookingDetail.seek(null))
                        {
                            JBookingDetailMainScreen mainScreen = (JBookingDetailMainScreen)this.getTargetScreen(JBookingDetailMainScreen.class);
                            screen = mainScreen.createNewScreen(Constants.FORM, tlbBookingDetail.getRecord());
                        }
                    } catch (DBException e) {
                        e.printStackTrace();
                    }
                }
                if (screen == null)
                    screen = new JBookingDetailGridScreen(this, tlbBookingDetail.getRecord());
                if (screen != null)
                {
                    Container parent = null;
                    if (screen.getParentObject() instanceof Container)
                        parent = (Container)screen.getParentObject();
                    this.getBaseApplet().changeSubScreen(parent, screen, null);
                }
            }
            return true;
        }
        bAction = super.doAction(strAction, iOptions);
        
        return bAction;
    }
    /**
     * Add the menubars?
     * @return The newly created menubar or null.
     */
    public JMenuBar createMenubar()
    {
        return null;    // No menu bar on this sub-screen.
    }
}
