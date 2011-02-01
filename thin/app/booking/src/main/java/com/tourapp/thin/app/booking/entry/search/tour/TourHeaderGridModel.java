package com.tourapp.thin.app.booking.entry.search.tour;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;

import com.tourapp.thin.app.booking.entry.search.base.ProductGridModel;
import com.tourapp.thin.tour.product.tour.db.TourHeader;

/**
 * Main Class for applet OrderEntry
 */
public class TourHeaderGridModel extends ProductGridModel
{
	private static final long serialVersionUID = 1L;

	public static final int TOUR_DAYS_COLUMN = COLUMN_COUNT;

    /**
     *  OrderEntry Class Constructor.
     */
    public TourHeaderGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public TourHeaderGridModel(FieldTable table)
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
        return super.getColumnCount() + 1;
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldInfo(int iIndex)
    {
        FieldList fieldList = m_table.getRecord();
        if (iIndex == TOUR_DAYS_COLUMN)
            return fieldList.getField(TourHeader.DAYS);
        return super.getFieldInfo(iIndex);
    }
    /**
     * Get the column class.
     * Returns String by default, override to supply a different class.
     */
    public Class<?> getColumnClass(int iColumnIndex)
    {
        if (iColumnIndex == TOUR_DAYS_COLUMN)
            return String.class;
        return super.getColumnClass(iColumnIndex);
    }
    /**
     * Get the value of the field at the column.
     * This is NOT a TableModel override, this is my method.
     * @param iColumnIndex The column.
     * @return The string at this location.
     */
    public Object getColumnValue(int iColumnIndex, int iEditMode)
    {
        return super.getColumnValue(iColumnIndex, iEditMode);
    }
}
