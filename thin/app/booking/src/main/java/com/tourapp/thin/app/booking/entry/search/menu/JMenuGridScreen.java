/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.menu;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.action.ActionManager;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.base.screen.cal.popup.ProductTypeInfo;
import org.jbundle.thin.base.screen.menu.JUnderlinedButton;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.app.booking.entry.search.JDisplayPanel;
import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.app.booking.entry.search.base.JProductGridScreen;

/**
 * Main Class for applet OrderEntry
 */
public class JMenuGridScreen extends JProductGridScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JMenuGridScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JMenuGridScreen(Object parent, Object model)
    {
        this();
        this.init(parent, model);
    }
    /**
     * The init() method is called first.
     */
    public void init(Object parent, Object model)
    {
        super.init(parent, model);
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        parent.setForeground(Color.black);
        
        JPanel panelTop = new JPanel();
        panelTop.setOpaque(false);
        parent.add(panelTop);
        
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        parent = panelTop;
        JComponent comp = null;
        String strDescription = null;
        ImageIcon icon = null;
        
        BaseApplet applet = this.getBaseApplet();
        if (applet == null)
            applet = BaseApplet.getSharedInstance();
        icon = applet.loadImageIcon("banners/tourgeek.gif");
        if (icon != null)
            parent.add(comp = new JLabel(icon));
        if (comp != null)
            comp.setAlignmentX(0.5f);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        parent.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JPanel panelLeft = new JPanel();
        panelLeft.setOpaque(false);
        panel.add(panelLeft);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelLeft.setAlignmentY(0.0f);

        applet.getApplication().getResources(applet.getApplication().getProperty(Params.RESOURCE), true);
        strDescription = applet.getString(SearchConstants.SEARCH + "Text");
        panelLeft.add(new JLabel(strDescription));

        panelLeft.add(this.makeSearchMenuButton(ProductConstants.TOUR));
        panelLeft.add(this.makeSearchMenuButton(ProductConstants.AIR));
        panelLeft.add(this.makeSearchMenuButton(ProductConstants.HOTEL));
        panelLeft.add(this.makeSearchMenuButton(ProductConstants.LAND));
        panelLeft.add(this.makeSearchMenuButton(ProductConstants.CAR));
        panelLeft.add(this.makeSearchMenuButton(ProductConstants.TRANSPORTATION));
        panelLeft.add(this.makeSearchMenuButton(ProductConstants.CRUISE));
        panelLeft.add(this.makeSearchMenuButton(ProductConstants.ITEM));

        Component panelGlue = Box.createHorizontalStrut(20);
        panel.add(panelGlue);

        JPanel panelRight = new JPanel();
        panelRight.setOpaque(false);
        panel.add(panelRight);
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.setAlignmentY(0.0f);

        strDescription = applet.getString("BookingText");
        panelRight.add(new JLabel(strDescription));

        panelRight.add(this.makeBookingMenuButton(SearchConstants.SEARCH));
        panelRight.add(this.makeBookingMenuButton(SearchConstants.CALENDAR));
        panelRight.add(this.makeBookingMenuButton(SearchConstants.PRICE));
        panelRight.add(this.makeBookingMenuButton(SearchConstants.PASSENGER));
        panelRight.add(this.makeBookingMenuButton(SearchConstants.CONTACT));
        panelRight.add(this.makeBookingMenuButton(SearchConstants.ITINERARY));

        strDescription = applet.getString("HelpText");
        parent.add(comp = new JLabel(strDescription));
        comp.setOpaque(false);
        if (comp != null)
            comp.setAlignmentX(0.5f);
        return true;
    }
    /**
     * Create the button/panel for this menu item.
     * @param record The menu record.
     * @return The component to add to this panel.
     */
    public JComponent makeBookingMenuButton(String strName)
    {
        BaseApplet applet = this.getBaseApplet();
        if (applet == null)
            applet = BaseApplet.getSharedInstance();
        ImageIcon icon = applet.loadImageIcon(SearchConstants.BUTTON_LOCATION + strName);
        String strDescription = applet.getString(strName);
        return this.makeMenuButton(strDescription, icon, strName);
    }
    /**
     * Create the button/panel for this menu item.
     * @param record The menu record.
     * @return The component to add to this panel.
     */
    public JComponent makeSearchMenuButton(String strName)
    {
        ProductTypeInfo productType = ProductTypeInfo.getProductType(strName);
        String strDescription = productType.getDescription();
        ImageIcon icon = productType.getStartIcon();
        icon.setDescription(strName);
        return this.makeMenuButton(strDescription, icon, SearchConstants.SEARCH_BUTTON);
    }
    /**
     * Create the button/panel for this menu item.
     * @param record The menu record.
     * @return The component to add to this panel.
     */
    public JComponent makeMenuButton(String strDescription, ImageIcon icon, String strName)
    {
        JUnderlinedButton button = new JUnderlinedButton(strDescription, icon);
        button.setName(strName);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        button.setAlignmentX(0.0f);
        return button;
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        JDisplayPanel panel = (JDisplayPanel)this.getTargetScreen(JDisplayPanel.class);
        if (panel != null)
            panel.actionPerformed(evt);
        else
            super.actionPerformed(evt);
    }
    /**
     * I Don't handle any actions (actually, there will be an error in JGridPanel if I pass this down.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        return false;   // No I didn't handle (Does an actual back, which sends me back to the menu screen)
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        JBaseToolbar toolbar = new JBaseToolbar(this, null);
        toolbar.addButton(Constants.GRID);
        toolbar.addButton(Constants.SUBMIT);
        toolbar.addButton(Constants.DELETE);
        toolbar.addButton(Constants.RESET);
        toolbar.addButton(Constants.HELP);
        return toolbar;
    }
    /**
     * Add the JMenubar
     * @return The JMenubar.
     */
    public JMenuBar createMenubar()
    {
        JMenuBar menuBar = ActionManager.getActionManager().setupStandardMenu(this);
        JMenu menu = ActionManager.getActionManager().addMenu(menuBar,ThinMenuConstants.RECORD, JBaseToolbar.BEFORE_HELP);
        ActionManager.getActionManager().addMenuItem(menu, Constants.GRID, this, KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.SUBMIT, this, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.DELETE, this, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.RESET, this, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, ActionEvent.CTRL_MASK));
        return menuBar;
    }
}
