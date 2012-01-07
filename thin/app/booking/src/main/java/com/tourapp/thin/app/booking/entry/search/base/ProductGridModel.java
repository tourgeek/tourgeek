/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.base;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jbundle.model.db.Convert;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.cal.grid.CalendarThinTableModel;
import org.jbundle.thin.base.screen.grid.JCellImage;
import org.jbundle.thin.base.screen.grid.JCellTextField;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.tour.product.base.db.Product;

/**
 * Main Class for applet OrderEntry.
 * Note: This extends the CalendarThinTableModel rather then the ThinTableModel, so I have the
 * use of the MySelectionListener.
 */
public class ProductGridModel extends CalendarThinTableModel
{
	private static final long serialVersionUID = 1L;

    public static final int ADD_BUTTON_COLUMN = 0;
	public static final int FORM_BUTTON_COLUMN = ADD_BUTTON_COLUMN + 1;
    public static final int DESC_COLUMN = FORM_BUTTON_COLUMN + 1;
    public static final int PRICE_STATUS = DESC_COLUMN + 1;
    public static final int PRICE = PRICE_STATUS + 1;
    public static final int AVAILABILITY = PRICE + 1;
    public static final int COLUMN_COUNT = AVAILABILITY + 1;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public ProductGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public ProductGridModel(FieldTable table)
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
            case ADD_BUTTON_COLUMN:
                return fieldList.getField(Product.PRODUCT_TYPE).getFieldConverter();
            case DESC_COLUMN:
                return fieldList.getField(Product.DESCRIPTION);
            case PRICE_STATUS:
                Converter fieldInfo = fieldList.getField(Product.DISPLAY_COST_STATUS_ID).getFieldConverter();
                return fieldInfo;
            case PRICE:
                return fieldList.getField(Product.PRODUCT_PRICE_LOCAL);
            case AVAILABILITY:
                Converter fieldInfo2 = fieldList.getField(Product.DISPLAY_INVENTORY_STATUS_ID).getFieldConverter();
                return fieldInfo2;
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
            case ADD_BUTTON_COLUMN:
            case PRICE_STATUS:
            case AVAILABILITY:
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
                return BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + Constants.FORM, Constants.FORM);
            case ADD_BUTTON_COLUMN:
                return BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + ThinMenuConstants.SELECT, ThinMenuConstants.SELECT);
//x             String strType = this.getFieldInfo(iColumnIndex).toString();
//x             return BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + BookingConstants.BUTTON_LOCATION + strType, SearchConstants.ADD);
            case PRICE_STATUS:
                return this.getFieldInfo(iColumnIndex).getData();
            case AVAILABILITY:
                return this.getFieldInfo(iColumnIndex).getData();
            case PRICE:
                Convert fieldInfo =  this.getFieldInfo(iColumnIndex);
//+                String strPrice = fieldInfo.toString();
                if (fieldInfo.getData() == null)
                { // Price was not available... queue a task to look it up.
                }
        }
        return super.getColumnValue(iColumnIndex, iEditMode);
    }
    /**
     *  This default implementation returns false for all cells
     */
    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
    {
        switch (iColumnIndex) // RequestInputID
        {
            case FORM_BUTTON_COLUMN:
            case ADD_BUTTON_COLUMN:
                return true;    // RequestQty
        }
        return false; // Don't allow changes to any line
    }
    /**
     * Don't allow appending.
     */
    public boolean isAppending()
    {
        return false;
    }
    /**
     * Returns the name of the column at columnIndex.
     */
    public String getColumnName(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
            case FORM_BUTTON_COLUMN:
                return ">";
            case ADD_BUTTON_COLUMN:
                return "+";
        }
        return super.getColumnName(iColumnIndex);
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
        case ADD_BUTTON_COLUMN:
        case FORM_BUTTON_COLUMN:
            break;
        case PRICE_STATUS:
        case AVAILABILITY:
            Convert fieldInfo = this.getFieldInfo(iColumnIndex);
            JCellImage component = (JCellImage)fieldInfo.getField().getComponent(0);
            return component;
        default:
            Convert fieldInfo2 = this.getFieldInfo(iColumnIndex);
            JCellTextField component2 = new JCellTextField(fieldInfo2.getMaxLength(), false);
            component2.setOpaque(false);
            if (fieldInfo2.getField() != null)
                if (Number.class.isAssignableFrom(fieldInfo2.getField().getDataClass()))
                    component2.setHorizontalAlignment(JTextField.RIGHT);
            fieldInfo2.getField().addComponent(component2);
            return component2;
        }
        return super.createColumnCellEditor(iColumnIndex);
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
        case ADD_BUTTON_COLUMN:
        case FORM_BUTTON_COLUMN:
            break;
        case PRICE_STATUS:
        case AVAILABILITY:
            Convert fieldInfo = this.getFieldInfo(iColumnIndex);
            JCellImage component = (JCellImage)fieldInfo.getField().getComponent(0);
            return component;
        default:
            Convert fieldInfo2 = this.getFieldInfo(iColumnIndex);
            JCellTextField component2 = new JCellTextField(fieldInfo2.getMaxLength(), false);
            component2.setOpaque(false);
            if (fieldInfo2.getField() != null)
                if (Number.class.isAssignableFrom(fieldInfo2.getField().getDataClass()))
                    component2.setHorizontalAlignment(JTextField.RIGHT);
            fieldInfo2.getField().addComponent(component2);
            return component2;
        }
        return super.createColumnCellRenderer(iColumnIndex);
    }
    /**
     * Get the product type of this record.
     */
    public String getProductType()
    {
        String strProductType = this.getClass().getName().toString();
        strProductType = strProductType.substring(strProductType.lastIndexOf('.') + 1);
        if (strProductType.indexOf("GridModel") != -1)
            strProductType = strProductType.substring(0, strProductType.indexOf("GridModel"));
        if ((strProductType == null) || (strProductType.length() == 0))
            strProductType = "Item";
        return strProductType;
    }
}
