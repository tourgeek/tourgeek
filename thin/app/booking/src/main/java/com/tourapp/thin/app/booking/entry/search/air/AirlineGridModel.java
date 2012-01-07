/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.air;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.ImageIcon;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jbundle.model.db.Convert;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.grid.JCellButton;
import org.jbundle.thin.base.screen.grid.ThinTableModel;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.tour.product.air.db.Airline;

/**
 * Main Class for applet OrderEntry.
 * Note: This extends the CalendarThinTableModel rather
 * than the ThinTableModel, so I have the
 * use of the MySelectionListener.
 */
public class AirlineGridModel extends ThinTableModel
{
	private static final long serialVersionUID = 1L;

	public static final int SELECT_BUTTON_COLUMN = 0;
    public static final int CODE_COLUMN = SELECT_BUTTON_COLUMN + 1;
    public static final int DESC_COLUMN = CODE_COLUMN + 1;
    public static final int COLUMN_COUNT = DESC_COLUMN + 1;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public AirlineGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public AirlineGridModel(FieldTable table)
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
        return COLUMN_COUNT;
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
            case SELECT_BUTTON_COLUMN:
                return null;
            case CODE_COLUMN:
                return fieldList.getField(Airline.AIRLINE_CODE);
            case DESC_COLUMN:
                return fieldList.getField(Airline.DESCRIPTION);
        }
        return super.getFieldInfo(iIndex);
    }
    /**
     * Get the column class.
     * Returns String by default, override to supply a different class.
     */
    public Class<?> getColumnClass(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
            case SELECT_BUTTON_COLUMN:
                return ImageIcon.class;
        }
        return super.getColumnClass(iColumnIndex);
    }
    /**
     * Get the value (this method returns the RAW data rather than Thin's String.
     */
    public Object getColumnValue(int iColumnIndex, int iEditMode)
    {
        switch (iColumnIndex) // RequestInputID
        {
            case SELECT_BUTTON_COLUMN:
                return BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + ThinMenuConstants.SELECT, ThinMenuConstants.SELECT);
        }
        return super.getColumnValue(iColumnIndex, iEditMode);
    }
    /**
     * Get the cell renderer for this column.
     * @param The column to get the cell renderer for.
     * @return The cell renderer or null to use the default.
     */
    public TableCellRenderer createColumnCellRenderer(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
        case SELECT_BUTTON_COLUMN:
            ImageIcon icon = (ImageIcon)this.getValueAt(0, iColumnIndex);
            JCellButton button = new JCellButton(icon);
            button.setOpaque(false);
            button.setName(ThinMenuConstants.SELECT);
            return button;
        }
        return super.createColumnCellRenderer(iColumnIndex);
    }
    /**
     * Get the cell editor for this column.
     * @param The column to get the cell editor for.
     * @return The cell editor or null to use the default.
     */
    public TableCellEditor createColumnCellEditor(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
        case SELECT_BUTTON_COLUMN:
            ImageIcon icon = (ImageIcon)this.getValueAt(0, iColumnIndex);
            JCellButton button = new JCellButton(icon);
            button.setOpaque(false);
            button.setName(ThinMenuConstants.SELECT);
            return button;
        }
        return super.createColumnCellEditor(iColumnIndex);
    }
    /**
     * Don't allow appending.
     */
    public boolean isAppending()
    {
        return false;
    }
    /**
     * Is this cell editable.
     * @return true unless this is a deleted record.
     */
    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
    {
        switch (iColumnIndex)
        {
        case SELECT_BUTTON_COLUMN:
            return true;
        }
        return super.isCellEditable(iRowIndex, iColumnIndex);
    }
    /**
     * Returns the name of the column at columnIndex.
     */
    public String getColumnName(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
            case SELECT_BUTTON_COLUMN:
                return ">";
        }
        return super.getColumnName(iColumnIndex);
    }
}
