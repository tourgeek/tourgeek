package com.tourapp.thin.app.booking.entry.search.hotel;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.base.screen.grid.ThinTableModel;

import com.tourapp.thin.app.booking.entry.search.base.JProductGridScreen;

/**
 * Main Class for applet OrderEntry
 */
public class JHotelGridScreen extends JProductGridScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JHotelGridScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JHotelGridScreen(Object parent, Object model)
    {
        this();
        this.init(parent, model);
    }
    /**
     * The init() method is called first.
     */
    public void init(Object parent, Object model)
    {
        super.init(parent, model);
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createMaintScreen(FieldList record)
    {
        return new JHotelScreen(this.getParentObject(), record);
    }
    /**
     * Get the remote table name (For remote table lookup).
     * @return The table name.
     */
    public String getRemoteTableName()
    {
        return ProductConstants.HOTEL;
    }
    /**
     * Setup the grid model.
     * @param table The table.
     * @return The grid model.
     */
    public ThinTableModel setupGridModel(FieldTable table)
    {
        return new HotelGridModel(table);
    }
}
