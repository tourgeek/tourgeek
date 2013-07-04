/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.contact;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.action.ActionManager;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourgeek.thin.app.booking.entry.BookingConstants;
import com.tourgeek.thin.app.booking.entry.TourGeekScreen;

/**
 * Main Class for applet OrderEntry
 */
public class JMainContactScreen extends JBaseScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JMainContactScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JMainContactScreen(Object parent, Object obj)
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
                
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.addSubPanels(this, (FieldList)obj);
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent, FieldList record)
    {
        JComponent topPane = new JContactScreen(this, record);
        topPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        topPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        parent.add(topPane);
        return true;
    }
    /**
     * Free.
     */
    public void free()
    {
        super.free();
    }
    /**
     * Add the scrollbars?
     * For maint screens, default to true.
     */
    public boolean isAddScrollbars()
    {
        return true;
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        JBaseToolbar toolbar = new JBaseToolbar(this, null);
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
        ActionManager.getActionManager().addMenuItem(menu, Constants.SUBMIT, this, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.DELETE, this, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.RESET, this, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, ActionEvent.CTRL_MASK));
        return menuBar;
    }
    /**
     * Process this action.
     * @param strAction The command to process.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        TourGeekScreen TourGeekScreen = (TourGeekScreen)this.getTargetScreen(TourGeekScreen.class);
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            return TourGeekScreen.doAction(BookingConstants.SEARCH, iOptions);
        }
        return super.doAction(strAction, iOptions);
    }
}
