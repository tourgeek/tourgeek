/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.land;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.JComponent;
import javax.swing.JTextField;

import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.JBaseScreen;

import com.tourapp.thin.app.booking.entry.search.base.JProductScreen;
import com.tourapp.thin.tour.product.base.db.Product;
import com.tourapp.thin.tour.product.land.db.Land;

/**
 * Main Class for applet OrderEntry
 */
public class JLandScreen extends JProductScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JLandScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JLandScreen(Object parent, Object obj)
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
        return new Land(this);
    }
    /**
     * Get the remote table name (For remote table lookup).
     * @return The table name.
     */
    public String getRemoteTableName()
    {
        return Land.LAND_FILE;
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createGridScreen(FieldList record)
    {
        return new JLandGridScreen(this.getParentObject(), record);
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

        JComponent component = super.addScreenControl(parent, fieldInfo);

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
        
        if (Land.TYPE.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = new JTextField(Constants.BLANK, 20)
            {
            	private static final long serialVersionUID = 1L;

            	public static final String ENTERTAINMENT = "E";
                public final static String ITINERARY = "I";
                public static final String SIGHTSEEING = "S";
                public static final String TRANSFER = "T";
                public static final String SIGHTSEEING_TEXT = "Sightseeing";
                public static final String TRANSFER_TEXT = "Transfer";
                public static final String ENTERTAINMENT_TEXT = "Entertainment";
                public static final String ITINERARY_TEXT = "Itinerary only";
                public void setText(String text)
                {
                    if (ENTERTAINMENT.equalsIgnoreCase(text))
                        text = getBaseApplet().getString(ENTERTAINMENT_TEXT);
                    else if (TRANSFER.equalsIgnoreCase(text))
                        text = getBaseApplet().getString(TRANSFER_TEXT);
                    else if (ITINERARY.equalsIgnoreCase(text))
                        text = getBaseApplet().getString(ITINERARY_TEXT);
                    else if (SIGHTSEEING.equalsIgnoreCase(text))
                        text = getBaseApplet().getString(SIGHTSEEING_TEXT);
                    super.setText(text);
                }
            };
            fieldInfo.addComponent(component);
            Util.setEnabled(component, false);
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

        Land.CITY_ID,
        Land.TYPE,
        Land.HOURS,
        Land.DAYS,
        Land.NIGHTS,
        Land.BREAKFASTS,
        Land.LUNCHES,
        Land.DINNERS,
        Land.DAYS_OF_WEEK,
        Land.VEHICLE,

        Product.COMMENTS,
        
        Product.VENDOR_NAME,
        Product.OPERATORS_CODE,
        Product.ETD,
        
        Product.DISPLAY_INVENTORY_STATUS_ID,
        Product.INVENTORY_AVAILABILITY,
        
        Product.DISPLAY_COST_STATUS_ID,
        Product.PRODUCT_PRICE_LOCAL,
        
        Land.SIC_PRICE_HOME,
        Land.PMC_PRICE_HOME
    };
}
