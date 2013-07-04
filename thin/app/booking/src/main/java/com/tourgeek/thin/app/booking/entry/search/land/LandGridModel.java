/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.search.land;

/**
 * OrderEntry.java:   Applet
 *  Copyright © 2012 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import org.jbundle.model.db.Convert;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;

import com.tourgeek.thin.app.booking.entry.search.base.ProductGridModel;
import com.tourgeek.thin.tour.product.land.db.Land;

/**
 * Main Class for applet OrderEntry
 */
public class LandGridModel extends ProductGridModel
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public LandGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public LandGridModel(FieldTable table)
    {
        this();
        this.init(table);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(FieldTable table)
    {
        super.init(table);
    }
    /**
     * Get the row count.
     */
    public int getColumnCount()
    {
        return super.getColumnCount() + 2;
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Convert getFieldInfo(int iIndex)
    {
        FieldList fieldList = m_table.getRecord();
        switch (iIndex)
        {
            case COLUMN_COUNT:
                return fieldList.getField(Land.PMC_PRICE_HOME);
            case COLUMN_COUNT + 1:
                return fieldList.getField(Land.SIC_PRICE_HOME);
        }
        return super.getFieldInfo(iIndex);
    }
}
