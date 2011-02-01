package com.tourapp.thin.app.booking.entry.detail.land;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.JComponent;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.util.Util;

import com.tourapp.thin.app.booking.entry.detail.JBookingDetailContextScreen;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingLandContextScreen extends JBookingDetailContextScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingLandContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingLandContextScreen(Object parent,Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = super.createScreenComponent(fieldInfo);
        
        if (fieldInfo.getFieldName().equals(BookingDetail.DETAIL_END_DATE))
            Util.setEnabled(component, false);

        return component;
    }
}
