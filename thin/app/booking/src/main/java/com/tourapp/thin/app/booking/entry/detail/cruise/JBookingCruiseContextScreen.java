/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.detail.cruise;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.JComponent;

import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Converter;

import com.tourapp.thin.app.booking.entry.detail.JBookingDetailContextScreen;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingCruiseContextScreen extends JBookingDetailContextScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingCruiseContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingCruiseContextScreen(Object parent, Object obj)
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
