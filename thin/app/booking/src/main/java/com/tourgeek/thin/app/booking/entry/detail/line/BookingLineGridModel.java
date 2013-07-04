/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.detail.line;

/**
 * OrderEntry.java:   Applet
 *  Copyright © 2012 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.ImageIcon;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jbundle.model.db.Convert;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.db.converter.ImageConverter;
import org.jbundle.thin.base.screen.db.converter.SecondaryRecordConverter;
import org.jbundle.thin.base.screen.grid.JCellButton;
import org.jbundle.thin.base.screen.grid.JCellImage;
import org.jbundle.thin.base.screen.grid.ThinTableModel;

import com.tourgeek.thin.tour.booking.detail.db.BookingLine;
import com.tourgeek.thin.tour.product.base.db.PricingStatus;

/**
 * Main Class for applet OrderEntry.
 * Note: This extends the CalendarThinTableModel rather then the ThinTableModel, so I have the
 * use of the MySelectionListener.
 */
public class BookingLineGridModel extends ThinTableModel
{
	private static final long serialVersionUID = 1L;

	public static final String PAX_CATEGORY = "PaxCategory";

    protected ImageIcon m_imageForm = null;
    protected ImageIcon m_imageDelete = null;

    public static final int FORM_BUTTON_COLUMN = 0;
    public static final int DESCRIPTION_COLUMN = FORM_BUTTON_COLUMN + 1;
    public static final int PRICE_COLUMN = DESCRIPTION_COLUMN + 1;
    public static final int QUANTITY_COLUMN = PRICE_COLUMN + 1;
    public static final int EXTENSION_COLUMN = QUANTITY_COLUMN + 1;
    public static final int STATUS_COLUMN = EXTENSION_COLUMN + 1;
    public static final int COLUMN_COUNT = STATUS_COLUMN + 1;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public BookingLineGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public BookingLineGridModel(FieldTable table)
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
            case FORM_BUTTON_COLUMN:
                return null;
            case DESCRIPTION_COLUMN:
                return fieldList.getField(BookingLine.DESCRIPTION);
            case PRICE_COLUMN:
                return fieldList.getField(BookingLine.PRICE);
            case QUANTITY_COLUMN:
                return fieldList.getField(BookingLine.QUANTITY);
            case EXTENSION_COLUMN:
                return fieldList.getField(BookingLine.GROSS);
            case STATUS_COLUMN:
                Converter fieldInfo = fieldList.getField(BookingLine.PRICING_STATUS_ID).getFieldConverter();
                if (fieldInfo instanceof FieldInfo)
                {
                    FieldList record = new PricingStatus(this);
                    fieldInfo = new SecondaryRecordConverter(fieldInfo, null, record, PricingStatus.ICON, false, PricingStatus.ID, null, null);
                    fieldInfo = new ImageConverter(fieldInfo);  // Add this the first time.
                    Object component = new JCellImage(fieldInfo);
                    fieldInfo.addComponent(component);
                }
                return fieldInfo;
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
            case STATUS_COLUMN:
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
                if (m_imageForm == null)
                    m_imageForm = BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + Constants.FORM);
                return m_imageForm;
            case STATUS_COLUMN:
                return this.getFieldInfo(iColumnIndex).getData();
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
        case STATUS_COLUMN:
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
            ImageIcon icon = (ImageIcon)this.getValueAt(-1, iColumnIndex);
            JCellButton button = new JCellButton(icon);
            button.setOpaque(false);
            button.setName(Constants.FORM);
            return button;
        case STATUS_COLUMN:
            Convert fieldInfo = this.getFieldInfo(iColumnIndex);
            JCellImage component = (JCellImage)fieldInfo.getField().getComponent(0);
            return component;
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
        }
        return super.getColumnName(iColumnIndex);
    }
    /**
     * Is this cell editable.
     * @return true unless this is a deleted record.
     */
    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
    {
        if (iColumnIndex == FORM_BUTTON_COLUMN)
            return true;
        return false;       // For now - Can't change pricing.
    }
}
