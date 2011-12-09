/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.tour;

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

import org.jbundle.model.Remote;
import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.db.converter.SecondaryRecordConverter;
import org.jbundle.thin.base.screen.util.JFSTextField;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.search.base.JProductScreen;
import com.tourapp.thin.tour.product.base.db.Product;
import com.tourapp.thin.tour.product.tour.db.TourHeader;
import com.tourapp.thin.tour.product.tour.db.TourType;

/**
 * Main Class for applet OrderEntry
 */
public class JTourHeaderScreen extends JProductScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JTourHeaderScreen(Object parent, Object obj)
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
        return new TourHeader(this);
    }
    /**
     * Get the remote table name (For remote table lookup).
     * @return The table name.
     */
    public String getRemoteTableName()
    {
        return TourHeader.TOUR_HEADER_FILE;
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
        return super.addScreenControl(parent, fieldInfo);
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
        
        if (TourHeader.TOUR_TYPE.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            FieldList record = new TourType(this);
            this.addFieldList(record);
            RemoteSession remoteSession = ((org.jbundle.thin.base.db.client.RemoteFieldTable)this.getFieldList().getTable()).getRemoteTableType(Remote.class);
            boolean bCacheTable = false;
            TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            screenMain.linkRemoteSessionTable(remoteSession, record, bCacheTable);

            fieldInfo = new SecondaryRecordConverter(fieldInfo, remoteSession, record, TourType.DESCRIPTION, bCacheTable, TourType.ID, null, null);
            component = new JFSTextField(fieldInfo);
            fieldInfo.addComponent(component);
            Util.setEnabled(component, false);
        }

        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        
        return component;
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createGridScreen(FieldList record)
    {
        return new JTourHeaderGridScreen(this.getParentObject(), record);
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

        TourHeader.DAYS,
        TourHeader.NIGHTS,
        TourHeader.TOUR_TYPE,
        TourHeader.START_DATE,
        TourHeader.END_DATE,

        Product.COMMENTS,
        
        Product.VENDOR_NAME,
        Product.OPERATORS_CODE,
        Product.ETD,
        
        Product.DISPLAY_INVENTORY_STATUS_ID,
        Product.INVENTORY_AVAILABILITY,
        
        Product.DISPLAY_COST_STATUS_ID,
        Product.PRODUCT_PRICE_LOCAL
    };
}
