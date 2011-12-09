/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.pax;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.ImageIcon;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.grid.ThinTableModel;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.grid.JCellButton;
import org.jbundle.thin.base.screen.grid.JCellThreeStateCheckBox;

import com.tourapp.thin.tour.booking.db.BookingPax;

/**
 * Main Class for applet OrderEntry.
 * Note: This extends the CalendarThinTableModel rather then the ThinTableModel, so I have the
 * use of the MySelectionListener.
 */
public class BookingPaxGridModel extends ThinTableModel
{
	private static final long serialVersionUID = 1L;

	public static final String PAX_CATEGORY = "PaxCategory";

    protected ImageIcon m_imageForm = null;
    protected ImageIcon m_imageDelete = null;

    public static final int FORM_BUTTON_COLUMN = 0;
    public static final int DELETE_BUTTON_COLUMN =  FORM_BUTTON_COLUMN + 1;
    public static final int ROOM_COLUMN = DELETE_BUTTON_COLUMN + 1;
    public static final int FIRST_NAME_COLUMN = ROOM_COLUMN + 1;
    public static final int LAST_NAME_COLUMN = FIRST_NAME_COLUMN + 1;
    public static final int SMOKER_COLUMN = LAST_NAME_COLUMN + 1;
    public static final int COLUMN_COUNT = SMOKER_COLUMN + 1;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public BookingPaxGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public BookingPaxGridModel(FieldTable table)
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
    public Converter getFieldInfo(int iIndex)
    {
        FieldList fieldList = m_table.getRecord();
        switch (iIndex)
        {
            case FORM_BUTTON_COLUMN:
            case DELETE_BUTTON_COLUMN:
                return null;
            case ROOM_COLUMN:
                return fieldList.getField(BookingPax.PAX_CATEGORY_ID);
            case FIRST_NAME_COLUMN:
                return fieldList.getField(BookingPax.FIRST_NAME);
            case LAST_NAME_COLUMN:
                return fieldList.getField(BookingPax.SUR_NAME);
            case SMOKER_COLUMN:
                return fieldList.getField(BookingPax.SMOKER);
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
            case FORM_BUTTON_COLUMN:
            case DELETE_BUTTON_COLUMN:
                return ImageIcon.class;
        }
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
        switch (iColumnIndex) // RequestInputID
        {
            case FORM_BUTTON_COLUMN:
                if (iEditMode == Constants.EDIT_NONE)
                    return null;
                if (m_imageForm == null)
                    m_imageForm = BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + Constants.FORM);
                return m_imageForm;
            case DELETE_BUTTON_COLUMN:
                if (iEditMode == Constants.EDIT_NONE)
                    return null;
                if (m_imageDelete == null)
                    m_imageDelete = BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + Constants.DELETE);
                return m_imageDelete;
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
        case FORM_BUTTON_COLUMN:
        case DELETE_BUTTON_COLUMN:
        case SMOKER_COLUMN:
            return (TableCellRenderer)this.createColumnCellEditor(iColumnIndex);
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
        case FORM_BUTTON_COLUMN:
            ImageIcon icon = (ImageIcon)this.getValueAt(0, iColumnIndex);
            JCellButton button = new JCellButton(icon);
            button.setOpaque(false);
            button.setName(Constants.FORM);
            return button;
        case DELETE_BUTTON_COLUMN:
            ImageIcon icon2 = (ImageIcon)this.getValueAt(0, iColumnIndex);
            JCellButton button3 = new JCellButton(icon2);
            button3.setOpaque(false);
            button3.setName(Constants.DELETE);
            return button3;
        case SMOKER_COLUMN:
            JCellThreeStateCheckBox checkbox = new JCellThreeStateCheckBox(null);
            return checkbox;
        }
        return super.createColumnCellEditor(iColumnIndex);
    }
    /**
     * Returns the name of the column at columnIndex.
     */
    public String getColumnName(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
            case FORM_BUTTON_COLUMN:
                return "+";
            case DELETE_BUTTON_COLUMN:
                return "x";
        }
        return super.getColumnName(iColumnIndex);
    }
}
