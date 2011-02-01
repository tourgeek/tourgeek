package com.tourapp.thin.app.booking.entry.search.tour;

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
import org.jbundle.thin.base.screen.grid.ThinTableModel;

import com.tourapp.thin.app.booking.entry.search.base.JProductGridScreen;
import com.tourapp.thin.tour.product.tour.db.TourHeader;
/**
 * Main Class for applet OrderEntry
 */

public class JTourHeaderGridScreen extends JProductGridScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderGridScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderGridScreen(Object parent, Object model)
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
        return new JTourHeaderScreen(this.getParentObject(), record);
    }
    /**
     * Get the remote table name (For remote table lookup).
     * @return The table name.
     */
    public String getRemoteTableName()
    {
        return TourHeader.TOUR_HEADER_FILE;
    }
    /**
     * Setup the grid model.
     * @param table The table.
     * @return The grid model.
     */
    public ThinTableModel setupGridModel(FieldTable table)
    {
        return new TourHeaderGridModel(table);
    }
}
