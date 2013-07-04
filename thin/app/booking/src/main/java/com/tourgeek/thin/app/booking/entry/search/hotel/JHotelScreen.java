/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.hotel;

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
import javax.swing.JPanel;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.JBaseScreen;

import com.tourapp.thin.app.booking.entry.search.base.JProductScreen;
import com.tourapp.thin.tour.product.base.db.Product;
import com.tourapp.thin.tour.product.hotel.db.Hotel;

/**
 * Main Class for applet OrderEntry
 */
public class JHotelScreen extends JProductScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JHotelScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JHotelScreen(Object parent, Object obj)
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
    }
    /**
     * Free this screen.
     */
    public void free()
    {
        super.free();
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return new Hotel(this);
    }
    /**
     * Get the remote table name (For remote table lookup).
     * @return The table name.
     */
    public String getRemoteTableName()
    {
        return Hotel.HOTEL_FILE;
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createGridScreen(FieldList record)
    {
        return new JHotelGridScreen(this.getParentObject(), record);
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent addScreenControl(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(Product.VENDOR_NAME))
        {
            c.gridy = 0;
            c.gridx = 5;                            // Column 4
        }
        if ((fieldInfo.getFieldName().equals(Hotel.CITY_OR_TOWN)) || (fieldInfo.getFieldName().equals(Hotel.POSTAL_CODE)))
            c.gridwidth = 1; // end column
        if ((fieldInfo.getFieldName().equals(Hotel.STATE_OR_REGION)) || (fieldInfo.getFieldName().equals(Hotel.COUNTRY)))
        {
            c.gridx = 2;                            // Column 1
            c.gridwidth = 1; // end column
            c.weightx = 0.0;                        // Minimum width to hold labels
            c.anchor = GridBagConstraints.NORTHEAST;    // Labels right justified
            JComponent component = new JLabel(fieldInfo.getFieldDesc());
            GridBagLayout gridbag = (GridBagLayout)this.getScreenLayout();
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

        if ((fieldInfo.getFieldName().equals(Hotel.STATE_OR_REGION)) || (fieldInfo.getFieldName().equals(Hotel.COUNTRY)))
        {   // Set these back
            c.gridx = 1;                            // Column 1
            c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
            c.gridwidth = 3; // end column
        }
        
        return component;
    }
    /**
     * Add the description labels to the first column of the grid.
     */
    public JComponent addScreenLabel(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(Product.VENDOR_NAME))
        {
            c.gridy = 0;
            c.gridx = 4;                            // Column 3
        }
        if ((fieldInfo.getFieldName().equals(Hotel.STATE_OR_REGION)) || (fieldInfo.getFieldName().equals(Hotel.COUNTRY)))
            return null;   // Don't add this label in the first column (will be in 3rd column).

        return super.addScreenLabel(parent, fieldInfo);
    }
    /**
     * Add the screen controls to the second column of the grid.
     * Create a default component for this fieldInfo.
     * @param fieldInfo the field to create a control for.
     * @return The component.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = null;
        if (Hotel.SINGLE_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
            component = this.getDisplayPanel().addCurrencyAmount(this.getFieldList().getField(Hotel.SINGLE_COST_LOCAL), this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), (JPanel)component);
        }
        if (Hotel.DOUBLE_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
            component = this.getDisplayPanel().addCurrencyAmount(this.getFieldList().getField(Hotel.DOUBLE_COST_LOCAL), this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), (JPanel)component);
        }
        if (Hotel.TRIPLE_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
            component = this.getDisplayPanel().addCurrencyAmount(this.getFieldList().getField(Hotel.TRIPLE_COST_LOCAL), this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), (JPanel)component);
        }
        if (Hotel.QUAD_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
            component = this.getDisplayPanel().addCurrencyAmount(this.getFieldList().getField(Hotel.QUAD_COST_LOCAL), this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), (JPanel)component);
        }
        if (Hotel.MEAL_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
            component = this.getDisplayPanel().addCurrencyAmount(this.getFieldList().getField(Hotel.MEAL_COST_LOCAL), this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), (JPanel)component);
        }
        if (Hotel.ROOM_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
            component = this.getDisplayPanel().addCurrencyAmount(this.getFieldList().getField(Hotel.ROOM_COST_LOCAL), this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), (JPanel)component);
        }
        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        return component;
    }
    /**
     * Get the field name at this location on the screen.
     * @param iScreenSeq The screen sequence to get the field name from.
     * @return The field name at this screen location.
     */
    public String getFieldName(int iScreenSeq)
    {
        if (iScreenSeq >= m_rgFieldNames.length)
            return null;
        return m_rgFieldNames[iScreenSeq];
    }
    /**
     * The fields in screen order.
     */
    public static String[] m_rgFieldNames = {
        Product.DESCRIPTION,
        Hotel.ADDRESS_LINE_1,
        Hotel.ADDRESS_LINE_2,
        Hotel.CITY_OR_TOWN,
        Hotel.STATE_OR_REGION,
        Hotel.POSTAL_CODE,
        Hotel.COUNTRY,

        Hotel.CONTACT,
        Hotel.CONTACT_TITLE,
        Hotel.TEL,
        Hotel.FAX,
        Hotel.EMAIL,
        
        Hotel.GENERAL_MANAGER,
        Hotel.SALES_MANAGER,
        Hotel.LOCAL_CONTACT,
        Hotel.LOCAL_PHONE,
        Hotel.TOLL_FREE_PHONE,
        Hotel.ALT_PHONE,

        Hotel.ROOMS,

        Product.COMMENTS,
        
        Product.VENDOR_NAME,
        Product.OPERATORS_CODE,
        Product.ETD,
        Hotel.CHECK_OUT,
        Hotel.CHILD_AGE,
        Hotel.ONE_FREE,
        Hotel.FREE_TYPE,
        
        Product.DISPLAY_INVENTORY_STATUS_ID,
        Product.INVENTORY_AVAILABILITY,
        
        Product.DISPLAY_COST_STATUS_ID,
        Product.PRODUCT_PRICE_LOCAL,
        
        Hotel.SINGLE_PRICE_LOCAL,
        Hotel.DOUBLE_PRICE_LOCAL,
        Hotel.TRIPLE_PRICE_LOCAL,
        Hotel.QUAD_PRICE_LOCAL,
        Hotel.MEAL_PRICE_LOCAL,
        Hotel.ROOM_PRICE_LOCAL
    };
}
