package com.tourapp.thin.app.booking.entry.detail.item;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import org.jbundle.thin.base.db.Converter;

import com.tourapp.thin.app.booking.entry.detail.JBookingDetailContextScreen;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingItemContextScreen extends JBookingDetailContextScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingItemContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingItemContextScreen(Object parent, Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
//        FieldList fieldList = this.getFieldList();
        switch (iIndex)
        {
            case 3:
            case 4:
            case 5:
                return null;
        }
        return super.getFieldForScreen(iIndex);
    }
}
