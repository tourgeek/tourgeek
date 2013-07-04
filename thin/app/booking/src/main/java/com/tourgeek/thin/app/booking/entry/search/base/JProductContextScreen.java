/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.search.base;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JScreenConstants;
import org.jbundle.thin.base.screen.landf.ScreenUtil;

import com.tourgeek.thin.app.booking.entry.BookingConstants;
import com.tourgeek.thin.app.booking.entry.TourGeekScreen;
import com.tourgeek.thin.app.booking.entry.search.JBaseRichScreen;
import com.tourgeek.thin.app.booking.entry.search.SearchConstants;
import com.tourgeek.thin.tour.product.base.db.Product;

/**
 * Main Class for Product Context screen.
 */
public class JProductContextScreen extends JBaseRichScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JProductContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JProductContextScreen(Object parent, Object obj)
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
        
        TourGeekScreen screenMain = this.getTourGeekScreen();
        screenMain.getParams().addPropertyChangeListener(this);
        Date date = (Date)this.getTourGeekScreen().getParams().get(SearchConstants.DATE);
        PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(this, SearchConstants.DATE, null, date);
        this.propertyChange(propChangeEvent);   // Let me set my date to match the system
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return null;    // DO NOT SET THIS (Always passed in) new Product(null); // Overriding class must set this
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        FieldList fieldList = this.getFieldList();
        switch (iIndex)
        {
            case 0:
                return fieldList.getField(Product.DESCRIPTION);
            case 1:
                return fieldList.getField(Product.PRODUCT_COST);
            case 2:
                return fieldList.getField(Product.VENDOR_NAME);
            case 3:
                return fieldList.getField(Product.PRODUCT_TYPE);
            case 4:
                return fieldList.getField(Product.PRODUCT_COST_LOCAL);
            case 5:
                return fieldList.getField(Product.DISPLAY_INVENTORY_STATUS_ID);
        }
        return null;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = null;
        BaseApplet applet = this.getBaseApplet();
        if (Product.DESCRIPTION.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            
            component = super.createScreenComponent(fieldInfo);
            if (component instanceof JTextArea)
                ((JTextArea)component).setRows(1);
            ScreenUtil.setEnabled(component, false);
            fieldInfo.addComponent(component);
            panel.add(component);

            ImageIcon icon = this.getBaseApplet().loadImageIcon(Constants.FORM);
            JButton button = new JButton(icon);
            button.setOpaque(false);
            button.setMargin(JScreenConstants.NO_INSETS);
            button.setName(Constants.FORM);
            panel.add(button);
            button.addActionListener(this);

            component = panel;
        }
        if (Product.PRODUCT_COST_LOCAL.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getTourGeekScreen().getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), null);

            fieldInfo = this.getFieldList().getField(Product.DISPLAY_COST_STATUS_ID);
            this.getTourGeekScreen().getDisplayPanel().addStatusComponents(fieldInfo, (JPanel)component, this);
        }
        if (Product.PRODUCT_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getTourGeekScreen().getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
        }
        if (Product.DISPLAY_INVENTORY_STATUS_ID.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            if (fieldInfo instanceof FieldInfo)
            {   // That way this only runs the first time.
                JPanel panel = new JPanel();
                panel.setOpaque(false);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                this.getTourGeekScreen().getDisplayPanel().addStatusComponents(fieldInfo, panel, this);
                component = panel;
            }
        }
        if (Product.PRODUCT_TYPE.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            
            JComponent panelDate = this.buildDateEdit(applet);
            panelDate.setAlignmentX(LEFT_ALIGNMENT);
            panelDate.setAlignmentY(TOP_ALIGNMENT);
            panel.add(panelDate);

            String strProductType = this.getFieldList().getField(Product.PRODUCT_TYPE).toString();
            if ((strProductType == null) || (strProductType.length() == 0))
                    strProductType = SearchConstants.DEFAULT_PRODUCT_TYPE;
            String strDesc = applet.getString(SearchConstants.ADD) + ' ' + applet.getString(strProductType);
            Icon icon = applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + strProductType);
            JButton button = new JButton(strDesc, icon);
            button.setOpaque(false);
            button.setMargin(JScreenConstants.NO_INSETS);
            button.setAlignmentX(LEFT_ALIGNMENT);
            button.setAlignmentY(TOP_ALIGNMENT);
            panel.add(Box.createHorizontalStrut(4));
            panel.add(button);
            button.setName(SearchConstants.ADD);
            button.addActionListener(this);
            component = panel;
            panel.add(Box.createHorizontalStrut(4));
        }
        
        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        
        if (Product.VENDOR_NAME.equalsIgnoreCase(fieldInfo.getFieldName()))
            ScreenUtil.setEnabled(component, false);
        
        return component;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent addScreenControl(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(Product.PRODUCT_TYPE))
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
        if (fieldInfo.getFieldName().equals(Product.PRODUCT_TYPE))
        {
            BaseApplet applet = this.getBaseApplet();
            c.gridy = 0;
            c.gridx = 4;                            // Column 3
            ((FieldInfo)fieldInfo).setFieldDesc(applet.getString(SearchConstants.DATE));    // Don't display the desc.
            JComponent component = super.addScreenLabel(parent, fieldInfo);
            component.setAlignmentX(LEFT_ALIGNMENT);
            component.setAlignmentY(TOP_ALIGNMENT);
            return component;
        }
        return super.addScreenLabel(parent, fieldInfo);
    }
    /**
     * Get the the base applet that is the parent of this screen.
     */
    public TourGeekScreen getTourGeekScreen()
    {
        return (TourGeekScreen)this.getTargetScreen(TourGeekScreen.class);
    }
    /**
     * A button was pressed in the table.
     * @param strAction The action to handle.
     * @return true if the action was handled here.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        if (SearchConstants.ADD.equalsIgnoreCase(strAction))
        {
            FieldList recProduct = this.getFieldList();
            if (recProduct != null)
//x                if ((recProduct.getEditMode() == Constants.EDIT_CURRENT) || (recProduct.getEditMode() == Constants.EDIT_IN_PROGRESS))
            {
                FieldInfo fieldInfo = recProduct.getField(0);
                if (fieldInfo != null)
                {
                    String strID = fieldInfo.toString();
                    String strProductType = null;
                    if (recProduct.getField(Product.PRODUCT_TYPE) != null)
                        strProductType = recProduct.getField(Product.PRODUCT_TYPE).toString();
                    if ((strID != null) && (strID.length() > 0))
                    {
                        TourGeekScreen TourGeekScreen = this.getTourGeekScreen();
                        TourGeekScreen.addProductToSession(strProductType, strID, null);
                    }
                }
            }
            return true;    // Handled
        }
        return super.doAction(strAction, iOptions);
    }
}
