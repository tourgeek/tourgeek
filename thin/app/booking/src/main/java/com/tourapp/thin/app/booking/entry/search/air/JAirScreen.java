/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.air;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jbundle.model.Remote;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.db.converter.SecondaryRecordConverter;
import org.jbundle.thin.base.screen.landf.ScreenUtil;
import org.jbundle.thin.base.screen.util.JFSImage;
import org.jbundle.thin.base.screen.util.JFSTextField;

import com.tourapp.thin.app.booking.entry.search.base.JProductScreen;
import com.tourapp.thin.tour.product.air.db.Air;
import com.tourapp.thin.tour.product.air.db.Airline;
import com.tourapp.thin.tour.product.base.db.Product;

/**
 * Main Class for applet OrderEntry
 */
public class JAirScreen extends JProductScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JAirScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JAirScreen(Object parent, Object obj)
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
        return new Air(this);
    }
    /**
     * Get the remote table name (For remote table lookup).
     * @return The table name.
     */
    public String getRemoteTableName()
    {
        return Air.AIR_FILE;
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createGridScreen(FieldList record)
    {
        return new JAirGridScreen(this.getParentObject(), record);
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

        if (Air.AIRLINE_ID.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            FieldList record = new Airline(this);
            this.addFieldList(record);
            RemoteSession remoteSession = ((org.jbundle.thin.base.db.client.RemoteFieldTable)this.getFieldList().getTable()).getRemoteTableType(Remote.class);
            boolean bCacheTable = true;

            fieldInfo = new SecondaryRecordConverter(fieldInfo, remoteSession, record, Airline.DESCRIPTION, bCacheTable, Airline.ID, null, null);
            component = new JFSTextField(fieldInfo);
            ((JFSTextField)component).setColumns(25);
            ScreenUtil.setEnabled(component, false);
            fieldInfo.addComponent(component);
            panel.add(component);

            fieldInfo = record.getField(Airline.LOGO);
            component = new JFSImage(null);
            ((JFSImage)component).setBorder(null);
            fieldInfo.addComponent(component);
            panel.add(component);
            
            component = panel;
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

        Air.AIRLINE_ID,
        Air.FLIGHT_NO,
        Air.CITY_CODE,
        Air.TO_CITY_CODE,
        Product.ETD,
        Air.ARRIVE_TIME,
        Air.ADD_DAYS,
        Air.EQUIPMENT,
        Air.MEALS,
        Air.DAYS,
        Air.STOPS,
        
        Product.COMMENTS,
        
        Product.VENDOR_NAME,
        Product.OPERATORS_CODE,
        
        Product.DISPLAY_INVENTORY_STATUS_ID,
        Product.INVENTORY_AVAILABILITY,
        
        Product.DISPLAY_COST_STATUS_ID,
        Product.PRODUCT_PRICE_LOCAL,
    };
}
