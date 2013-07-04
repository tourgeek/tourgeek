/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright © 2012 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.grid.ThinTableModel;

import com.tourapp.thin.tour.request.db.Brochure;
import com.tourapp.thin.tour.request.db.RequestInput;

/**
 * Main Class for applet OrderEntry
 */
public class RequestGridModel extends ThinTableModel
{
    private static final long serialVersionUID = 1L;

    public static final int QTY_COLUMN = 0;
    public static final int DESC_COLUMN = 1;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public RequestGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public RequestGridModel(FieldTable table)
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
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return new Brochure(null);  // If overriding class didn't set this
    }
    /**
     * Get the row count.
     */
    public int getColumnCount()
    {
        return 2; //m_table.getFieldList().size();
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldInfo(int iIndex)
    {
    	String fieldName = null;
        if (iIndex == QTY_COLUMN) // RequestInputID
            fieldName = RequestInput.BROCHURE_QTY;   // RequestQty
        else if (iIndex == DESC_COLUMN)
            fieldName = RequestInput.BROCHURE_DESC;   // RequestDesc
        else
            return null;
        Converter converter = m_table.getRecord().getField(fieldName);
        if (converter != null)
            converter = converter.getFieldConverter();  // Make sure you have the front converter.
        return converter;
    }
    /**
     *  This default implementation returns false for all cells
     */
    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
    {
        if (iColumnIndex == DESC_COLUMN)
            return false; // Don't allow changes to description line
        return super.isCellEditable(iRowIndex, iColumnIndex);   // All my fields are editable
    }
    /**
     * Don't allow appending.
     */
    public boolean isAppending()
    {
        return false;
    }
}
