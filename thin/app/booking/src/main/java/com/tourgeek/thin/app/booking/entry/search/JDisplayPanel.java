/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.search;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.base.screen.db.converter.ImageConverter;
import org.jbundle.thin.base.screen.db.converter.SecondaryRecordConverter;
import org.jbundle.thin.base.screen.grid.JCellImage;
import org.jbundle.thin.base.screen.landf.ScreenUtil;

import com.tourgeek.thin.app.booking.entry.BookingConstants;
import com.tourgeek.thin.app.booking.entry.search.air.JAirGridScreen;
import com.tourgeek.thin.app.booking.entry.search.base.JProductSearchPane;
import com.tourgeek.thin.app.booking.entry.search.car.JCarGridScreen;
import com.tourgeek.thin.app.booking.entry.search.cruise.JCruiseGridScreen;
import com.tourgeek.thin.app.booking.entry.search.hotel.JHotelGridScreen;
import com.tourgeek.thin.app.booking.entry.search.item.JItemGridScreen;
import com.tourgeek.thin.app.booking.entry.search.land.JLandGridScreen;
import com.tourgeek.thin.app.booking.entry.search.menu.JMenuGridScreen;
import com.tourgeek.thin.app.booking.entry.search.tour.JTourHeaderGridScreen;
import com.tourgeek.thin.app.booking.entry.search.trans.JTransportationGridScreen;
import com.tourgeek.thin.tour.product.base.db.CostStatus;
import com.tourgeek.thin.tour.product.base.db.InventoryStatus;
import com.tourgeek.thin.tour.product.base.db.Product;

/**
 * Parent screen for the tourgeek display area.
 */
public class JDisplayPanel extends JBaseRichScreen
{
	private static final long serialVersionUID = 1L;

    /**
     * The search (grid) table (the right JTable) displaying the product.
     */
    protected JPanel m_displayParent = null;

    /**
     *  Parent search panel.
     */
    public JDisplayPanel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JDisplayPanel(Object obj, BaseApplet applet)
    {
        this();
        this.init(obj, applet);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(Object obj, BaseApplet applet)
    {
        super.init(null, null);

        // Next, set up the right "content" panel
        m_displayParent = new JPanel();
        m_displayParent.setOpaque(false);
        m_displayParent.setLayout(new BorderLayout());
        this.add(m_displayParent);
        
        m_parent = obj;

        this.switchScreens(applet, null, Constants.DONT_PUSH_TO_BROWSER);   // Default screen = menu screen
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        BaseApplet applet = this.getBaseApplet();
        JComponent button = (JComponent)evt.getSource();
        String strButtonName = button.getName();
        if (SearchConstants.SEARCH_BUTTON.equals(strButtonName))
        {
            ImageIcon icon = null;
            if (button instanceof JToggleButton)
                icon = (ImageIcon)((JToggleButton)button).getIcon();
            else if (button instanceof JButton)
                icon = (ImageIcon)((JButton)button).getIcon();
            else if (button instanceof JLabel)
                icon = (ImageIcon)((JLabel)button).getIcon();
            String strDesc = icon.getDescription();
            BaseApplet.handleAction(BookingConstants.SEARCH, this.getTourGeekScreen(), null, 0);  // Make sure we are on the search tab
            JDisplayPanel displayPanel = this.getTourGeekScreen().getDisplayPanel();
            displayPanel.switchScreens(applet, strDesc, Constants.DONT_PUSH_TO_BROWSER);
        }
        else
            super.actionPerformed(evt);
    }
    /**
     * Switch the search pane and the display screen to this (product type) display.
     * @param strDesc The product type (such as Hotel, Land, etc).
     * @param iOptions options
     */
    public JProductSearchPane switchScreens(BaseApplet applet, String strDesc, int iOptions)
    {
        JProductSearchPane searchPane = this.getTourGeekScreen().getMainSearchPane().switchScreens(applet, strDesc, iOptions);
        
        JBaseScreen displayScreen = this.getDisplayScreen();
        if (displayScreen != null)
        {
            displayScreen.free();
            displayScreen = null;
        }
        // Now display the proper search pane and table pane.
        if (ProductConstants.AIR.equalsIgnoreCase(strDesc))
            displayScreen = new JAirGridScreen(m_displayParent, searchPane.getRemoteSession());
        else if (ProductConstants.HOTEL.equalsIgnoreCase(strDesc))
            displayScreen = new JHotelGridScreen(m_displayParent, searchPane.getRemoteSession());
        else if (ProductConstants.LAND.equalsIgnoreCase(strDesc))
            displayScreen = new JLandGridScreen(m_displayParent, searchPane.getRemoteSession());
        else if (ProductConstants.CAR.equalsIgnoreCase(strDesc))
            displayScreen = new JCarGridScreen(m_displayParent, searchPane.getRemoteSession());
        else if (ProductConstants.TRANSPORTATION.equalsIgnoreCase(strDesc))
            displayScreen = new JTransportationGridScreen(m_displayParent, searchPane.getRemoteSession());
        else if (ProductConstants.CRUISE.equalsIgnoreCase(strDesc))
            displayScreen = new JCruiseGridScreen(m_displayParent, searchPane.getRemoteSession());
        else if (ProductConstants.ITEM.equalsIgnoreCase(strDesc))
            displayScreen = new JItemGridScreen(m_displayParent, searchPane.getRemoteSession());
        else if (ProductConstants.TOUR.equalsIgnoreCase(strDesc))
            displayScreen = new JTourHeaderGridScreen(m_displayParent, searchPane.getRemoteSession());
        else
            displayScreen = new JMenuGridScreen(m_displayParent, null);

        applet.changeSubScreen(m_displayParent, displayScreen, null, iOptions);

        if (searchPane != null)
            searchPane.requeryRemoteSession();
        
        return searchPane;
    }
    /**
     * Get the display (right-hand) screen.
     */
    public JBaseScreen getDisplayScreen()
    {
        return (JBaseScreen)JBasePanel.getSubScreen(m_displayParent, JBaseScreen.class);
    }
    /**
     * Process this action.
     * @param strAction The action to process.
     * By default, this method handles RESET, SUBMIT, and DELETE.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        if (Constants.RESET.equalsIgnoreCase(strAction))
            return false;   // I DO NOT handle this (JScreen would have)
        return super.doAction(strAction, iOptions);
    }
    /**
     * Add a cost or inventory status display consisting of the icon and the description.
     */
    public JComponent addStatusComponents(Converter fieldInfo, JPanel panel, Object recordOwner)
    {
        FieldList recCostStatus = null;
        if (Product.DISPLAY_COST_STATUS_ID.equals(fieldInfo.getFieldName()))
            recCostStatus = new CostStatus(recordOwner);
        else
            recCostStatus = new InventoryStatus(recordOwner);
        RemoteSession remoteSession = this.getTourGeekScreen().getMainSearchPane().getProductSearchPane().getRemoteSession();
        boolean bCacheTable = false;

        fieldInfo = new SecondaryRecordConverter(fieldInfo, remoteSession, recCostStatus, CostStatus.ICON, bCacheTable, CostStatus.ID, null, null);
        fieldInfo = new ImageConverter(fieldInfo);  // Add this the first time.
        JComponent component = new JCellImage(fieldInfo);
        ScreenUtil.setEnabled(component, false);
        fieldInfo.addComponent(component);
        if (panel != null)
        {
            panel.add(component);

            panel.add(Box.createHorizontalStrut(4));

            fieldInfo = recCostStatus.getField(CostStatus.DESCRIPTION);
            JComponent textField = super.createScreenComponent(fieldInfo);
            fieldInfo.addComponent(textField);
            ScreenUtil.setEnabled(textField, false);
            panel.add(textField);
        }
        return component;
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        return this.getDisplayScreen().createToolbar();
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JMenuBar createMenubar()
    {
        return this.getDisplayScreen().createMenubar();
    }
    /**
     * Add the optional scrollers and toolbars to this screen.
     * @param baseScreen The new screen (which has information on scrollers, toolbars, etc).
     */
    public JComponent setupMenuAndToolbar(JBasePanel baseScreen, JComponent screen)
    {
        JComponent component = super.setupMenuAndToolbar(baseScreen, screen);
        if (this.getTourGeekScreen().getMainSearchPane() != null)
        	if (this.getTourGeekScreen().getMainSearchPane().getProductSearchPane() != null)
  		{
	        JProductSearchPane searchPane = this.getTourGeekScreen().getMainSearchPane().getProductSearchPane();
    	    searchPane.requeryRemoteIfStale();
        }
        return component;
    }
}
