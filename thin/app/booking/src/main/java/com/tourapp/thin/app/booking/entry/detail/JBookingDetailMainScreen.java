/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.detail;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;

import javax.swing.JTable;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.base.screen.grid.ThinTableModel;
import org.jbundle.thin.base.screen.util.JMainScreen;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.detail.line.JBookingLineGridScreen;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingDetailMainScreen extends JMainScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JBookingDetailMainScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingDetailMainScreen(Object parent, Object obj)
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
        return super.addSubPanels(parent);
    }
    /**
     * Create a screen of this type.
     */
    public JBaseScreen createNewScreen(String strType, Object record)
    {
        if (record == null)
        { // First time only
//          TourCalendarScreen tourCalendarScreen = (TourCalendarScreen)this.getTargetScreen(TourCalendarScreen.class);
//          RemoteSession parentSessionObject = tourCalendarScreen.getRemoteSession();
//          m_remoteSession = this.getBaseApplet().makeRemoteSession(parentSessionObject, Constants.ROOT_PACKAGE + "tour.booking.remote.booking.BookingPaxSession");
//          record = new BookingPax(this);
//          this.getBaseApplet().linkRemoteSessionTable(m_remoteSession, (BookingPax)record, true);
        }
        if (Constants.GRID.equalsIgnoreCase(strType))
        {
            TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            if (tourAppScreen.isUsingPricingScreens())
                return new JBookingLineGridScreen(this, null);
            else
                return new JBookingDetailGridScreen(this, record);
        }
        else if (Constants.FORM.equalsIgnoreCase(strType))
        {
            String strProductType = ((FieldList)record).getField(BookingDetail.PRODUCT_TYPE).toString();
            String strID = ((FieldList)record).getField(Params.ID).toString();
            if ((ProductConstants.TOUR.equalsIgnoreCase(strProductType)) /*++|| (ProductConstants.TOUR_HEADER.equalsIgnoreCase(strProductType))*/)
                return new com.tourapp.thin.app.booking.entry.detail.tour.JBookingTourScreen(this, strID);
            else if (ProductConstants.HOTEL.equalsIgnoreCase(strProductType))
                return new com.tourapp.thin.app.booking.entry.detail.hotel.JBookingHotelScreen(this, strID);
            else if (ProductConstants.LAND.equalsIgnoreCase(strProductType))
                return new com.tourapp.thin.app.booking.entry.detail.land.JBookingLandScreen(this, strID);
            else if (ProductConstants.TRANSPORTATION.equalsIgnoreCase(strProductType))
                return new com.tourapp.thin.app.booking.entry.detail.trans.JBookingTransportationScreen(this, strID);
            else if (ProductConstants.CRUISE.equalsIgnoreCase(strProductType))
                return new com.tourapp.thin.app.booking.entry.detail.cruise.JBookingCruiseScreen(this, strID);
            else if (ProductConstants.CAR.equalsIgnoreCase(strProductType))
                return new com.tourapp.thin.app.booking.entry.detail.car.JBookingCarScreen(this, strID);
            else if (ProductConstants.AIR.equalsIgnoreCase(strProductType))
                return new com.tourapp.thin.app.booking.entry.detail.air.JBookingAirScreen(this, strID);
            else if (ProductConstants.ITEM.equalsIgnoreCase(strProductType))
                return new com.tourapp.thin.app.booking.entry.detail.item.JBookingItemScreen(this, strID);
        }
        return null;    // Never!
    }
    /**
     * Get the model in this screen's sub-screen.
     * (Overidden to supply the correct model)
     * @param table
     * @return
     */
    public ThinTableModel getModel(JTable table)
    {
        ThinTableModel model = (ThinTableModel)((TourAppScreen)this.getTargetScreen(TourAppScreen.class)).getCalendarModel();
        return model;
    }
    /**
     * Process this action.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        return super.doAction(strAction, iOptions);
    }
    
    protected Object m_source = null;
    /**
     * Set source of the calling screen.
     * This is used by the back button to return to the correct screen.
     * @param source
     */
    public void setSource(Object source)
    {
        m_source = source;
    }
    /**
     * Set source of the calling screen.
     * This is used by the back button to return to the correct screen.
     * @return
     */
    public Object getSource()
    {
        return m_source;
    }
}
