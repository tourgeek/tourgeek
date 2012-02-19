/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.model;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.action.ActionManager;
import org.jbundle.thin.base.screen.cal.opt.TaskCalendarStatusHandler;
import org.jbundle.thin.base.screen.cal.popup.JPopupPanel;
import org.jbundle.thin.base.screen.cal.popup.ProductTypeInfo;
import org.jbundle.util.calendarpanel.CalendarPanel;
import org.jbundle.util.calendarpanel.event.MyListSelectionEvent;
import org.jbundle.util.calendarpanel.model.CalendarConstants;
import org.jbundle.util.calendarpanel.model.CalendarModel;
import org.jbundle.util.calendarpanel.util.CalendarCache;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.search.JDisplayPanel;
import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;

/**
 * A JMainScreen is a holder screen for a Grid/Form screen.
 * This screen is useful where a sub-screen changes between a grid and maint view.
 * This screen has it's own history stack and logic to switch sub-screens.
 * Just Override the createNewScreen method to supply the correct grid or form screen.
 * Optionally, you can override and add a sub screen with summary data in the init method.
 * (but remember to call super!).
 * This class can also be used for any sub-screen that requires a history (such as a menu
 * in a sub-screen).
 */
public class BookingDetailCalendarScreen extends JBaseScreen
{
	private static final long serialVersionUID = 1L;

    /**
     * JMainScreen Class Constructor.
     */
    public BookingDetailCalendarScreen()
    {
        super();
    }
    /**
     * JMainScreen Class Constructor.
     * @param parent Typically, you pass the BaseApplet as the parent.
     * @param record and the record or GridTableModel as the parent.
     */
    public BookingDetailCalendarScreen(Object parent, Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * JMainScreen Class Constructor.
     * @param parent Typically, you pass the BaseApplet as the parent.
     * @param record and the record or GridTableModel as the parent.
     */
    public void init(Object parent, Object applet)
    {
        super.init(parent, applet);

        this.addSubPanels(this);
    }
    /**
     * Add any screen sub-panel(s) now.
     * @param parent The parent screen to add sub-screens to.
     */
    public boolean addSubPanels(Container parent)
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Calendar panel
        TourAppScreen screen = (TourAppScreen)this.getParentObject();
        ImageIcon backgroundImage = null;
        if (screen.getBaseApplet() != null)
        	backgroundImage = screen.getBaseApplet().getBackgroundImage();	// Calendar panel is transparent, but this helps with rendering see-thru components 
        CalendarPanel calpanel = new CalendarPanel(screen.getCalendarModel(), true, backgroundImage);
        calpanel.addPropertyChangeListener(screen.getParams());
        screen.getParams().addPropertyChangeListener(calpanel);
        calpanel.setStatusListener(new TaskCalendarStatusHandler(screen.getBaseApplet()));
        
        calpanel.setPopupComponent(new JPopupPanel(this.getBaseApplet(), this));
        
        parent.add(calpanel);
        return true;
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
     * Process this action.
     * @param strAction The command to process.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        TourAppScreen tourAppScreen = (TourAppScreen)this.getParentObject();
        
        CalendarModel model = tourAppScreen.getCalendarModel();
        CalendarPanel calpanel = (CalendarPanel)JBasePanel.getSubScreen(this, CalendarPanel.class);
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            return tourAppScreen.doAction(BookingConstants.SEARCH, iOptions);
        }
        else if (Constants.FORM.equalsIgnoreCase(strAction))
        {
            CalendarCache calCache = calpanel.getSelectedItem();
            int iIndex = calpanel.getItemIndex(calCache);
            model.fireTableRowSelected(this, iIndex, MyListSelectionEvent.CONTENT_SELECT | MyListSelectionEvent.CONTENT_CLICK);
            return true;    // Handled
        }
        else if (Constants.DELETE.equalsIgnoreCase(strAction))
        {
            calpanel.actionPerformed(new ActionEvent(this, 0, CalendarConstants.DELETE));
            return true;    // Always Handled
        }
        else if (ProductTypeInfo.getProductType(strAction) != null)
        {
            JDisplayPanel displayPanel = tourAppScreen.getDisplayPanel();
            JProductSearchPane pane = displayPanel.switchScreens(this.getBaseApplet(), strAction);
            tourAppScreen.handleAction(BookingConstants.SEARCH, null, iOptions);  // Make sure we are on the search tab
            pane.requestFocus();
            return true;
        }
        return super.doAction(strAction, iOptions);
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        JBaseToolbar toolbar = new JBaseToolbar(this, null);
        toolbar.addButton(Constants.FORM);
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
        ActionManager.getActionManager().addMenuItem(menu, Constants.FORM, this, KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.SUBMIT, this, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.DELETE, this, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.RESET, this, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, ActionEvent.CTRL_MASK));
        return menuBar;
    }
}
