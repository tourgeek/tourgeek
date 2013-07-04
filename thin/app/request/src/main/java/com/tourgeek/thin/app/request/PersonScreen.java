/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.JScreen;

import com.tourgeek.thin.tour.request.db.Request;

/**
 * Main Class for applet OrderEntry
 */
public class PersonScreen extends JScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  Request Constructor.
     */
    public PersonScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public PersonScreen(Object parent, Object obj)
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
        this.addScreenButtons(this);
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return new Request(null); // If overriding class didn't set this
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        FieldInfo fieldInfo = (FieldInfo)super.getFieldForScreen(iIndex);
        if (fieldInfo != null)
            if (fieldInfo != SKIP_THIS_FIELD)
                if ((iIndex == 0)   // Don't display counter, or...
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.PROFILE_ID))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.PROFILE_CODE))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.SEND_VIA_CODE))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.ATTENTION))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.BUNDLE_ID))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.BUNDLE_QTY))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.PRINT_NOW))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.HIST_REPRINT))
                || (fieldInfo.getFieldName().equalsIgnoreCase(Request.BROCHURE_TEXT)))
                    fieldInfo = SKIP_THIS_FIELD;

        return fieldInfo;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent addScreenControl(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        GridBagLayout gridbag = (GridBagLayout)this.getScreenLayout();
        if ((fieldInfo.getFieldName().equals(Request.CITY_OR_TOWN)) || (fieldInfo.getFieldName().equals(Request.POSTAL_CODE)))
            c.gridwidth = 1; // end column
        if ((fieldInfo.getFieldName().equals(Request.STATE_OR_REGION)) || (fieldInfo.getFieldName().equals(Request.COUNTRY)))
        {
            c.gridx = 2;                            // Column 1
            c.gridwidth = 1; // end column
            c.weightx = 0.0;                        // Minimum width to hold labels
            c.anchor = GridBagConstraints.NORTHEAST;    // Labels right justified
            JComponent component = new JLabel(fieldInfo.getFieldDesc());
            gridbag.setConstraints(component, c);
            parent.add(component);
            fieldInfo.addComponent(component);
            c.gridx = 3;                            // Column 1
            c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
            c.gridwidth = 1; // end column
            c.weightx = 1.0;                        // Grow edit and scroll pane but not label
            c.anchor = GridBagConstraints.WEST;     // Edit boxes left justified
        }

        JComponent component = super.addScreenControl(parent, fieldInfo);

        if ((fieldInfo.getFieldName().equals(Request.STATE_OR_REGION)) || (fieldInfo.getFieldName().equals(Request.COUNTRY)))
        {   // Set these back
            c.gridx = 1;                            // Column 1
            c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
            c.gridwidth = 3; // end column
        }
        
        return component;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public void addScreenControls(Container parent)
    {
        super.addScreenControls(parent);
    }
    /**
     * Add the description labels to the first column of the grid.
     */
    public JComponent addScreenLabel(Container parent, Converter fieldInfo)
    {
        if ((fieldInfo.getFieldName().equals(Request.STATE_OR_REGION)) || (fieldInfo.getFieldName().equals(Request.COUNTRY)))
            return null;   // Don't add this label in the first column (will be in 3rd column).

        return super.addScreenLabel(parent, fieldInfo);
    }
}
