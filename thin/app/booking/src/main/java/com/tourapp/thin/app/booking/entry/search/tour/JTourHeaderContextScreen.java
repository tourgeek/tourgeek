/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.tour;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.JComponent;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.screen.landf.ScreenUtil;

import com.tourapp.thin.app.booking.entry.search.base.JProductContextScreen;
import com.tourapp.thin.tour.product.tour.db.TourHeader;

/**
 * Main Class for Product Context screen.
 */
public class JTourHeaderContextScreen extends JProductContextScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderContextScreen(Object parent, Object obj)
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
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        if (iIndex == 2)
            return this.getFieldList().getField(TourHeader.DAYS);
        if (iIndex > 2)
            iIndex--;
        return super.getFieldForScreen(iIndex);
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = super.createScreenComponent(fieldInfo);
        if (TourHeader.DAYS.equals(fieldInfo.getFieldName()))
            ScreenUtil.setEnabled(component, false);
        return component;
    }
}
