/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.context;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.base.screen.cal.popup.ProductTypeInfo;
import org.jbundle.util.calendarpanel.util.JUnderlinedLabel;

import com.tourgeek.thin.app.booking.entry.BookingConstants;

/**
 * This panel displays the Legend (instructions) for the Calendar screen.
 * It is usually displayed when there is nothing selected.
 */
public class JLegendScreen extends JBasePanel
{
	private static final long serialVersionUID = 1L;

	/**
     * Constructor.
     */
    public JLegendScreen()
    {
        super();
    }
    /**
     * Constructor.
     */
    public JLegendScreen(Object obj)
    {
        this();
        this.init(obj);
    }
    /**
     * Constructor.
     */
    public void init(Object obj)
    {
        this.setForeground(Color.black);
        this.setOpaque(false);
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addSubScreen(obj);
    }
    /**
     * addSubScreen.
     */
    public void addSubScreen(Object obj)
    {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        this.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        this.addLegendItem(panel, ProductConstants.TOUR);
        this.addLegendItem(panel, ProductConstants.AIR);
        this.addLegendItem(panel, ProductConstants.HOTEL);
        this.addLegendItem(panel, ProductConstants.LAND);
        this.addLegendItem(panel, ProductConstants.CAR);
        this.addLegendItem(panel, ProductConstants.TRANSPORTATION);
        this.addLegendItem(panel, ProductConstants.CRUISE);
        this.addLegendItem(panel, ProductConstants.ITEM);

        this.addLegendItem(panel, BookingConstants.MEAL);
        this.addLegendItem(panel, BookingConstants.PRICE);
        this.addLegendItem(panel, BookingConstants.INVENTORY);
        this.addLegendItem(panel, BookingConstants.NO_INVENTORY);
        this.addLegendItem(panel, BookingConstants.BOOKING);
    }
    /**
     * Add a legend item.
     */
    public void addLegendItem(JPanel panel, String strItemDesc)
    {
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        ProductTypeInfo productType = ProductTypeInfo.getProductType(strItemDesc);
        ImageIcon image = null;
        if (this.getBaseApplet() != null)
        	image = this.getBaseApplet().getBackgroundImage();
        JUnderlinedLabel label;
        panel.add(label = new JUnderlinedLabel(null, productType.getStartIcon(), false, new Color(productType.getHighlightColor()), new Color(productType.getSelectColor()), 0, null, false));
        label.setBackgroundImage(image);
        panel.add(label = new JUnderlinedLabel(null, productType.getDescription(), true, new Color(productType.getHighlightColor()), new Color(productType.getSelectColor()), 0, null, false));
        label.setBackgroundImage(image);
    }
}
