/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JScreenConstants;
import org.jbundle.thin.base.screen.cal.opt.TaskCalendarStatusHandler;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.base.screen.landf.ScreenUtil;
import org.jbundle.util.calendarpanel.CalendarPanel;
import org.jbundle.util.calendarpanel.model.CalendarModel;
import org.jbundle.util.calendarpanel.util.MouseDateListener;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.search.air.JAirSearchPane;
import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;
import com.tourapp.thin.app.booking.entry.search.car.JCarSearchPane;
import com.tourapp.thin.app.booking.entry.search.cruise.JCruiseSearchPane;
import com.tourapp.thin.app.booking.entry.search.hotel.JHotelSearchPane;
import com.tourapp.thin.app.booking.entry.search.item.JItemSearchPane;
import com.tourapp.thin.app.booking.entry.search.land.JLandSearchPane;
import com.tourapp.thin.app.booking.entry.search.menu.JMenuSearchPane;
import com.tourapp.thin.app.booking.entry.search.tour.JTourHeaderSearchPane;
import com.tourapp.thin.app.booking.entry.search.trans.JTransportationSearchPane;
import com.tourapp.thin.tour.booking.db.Booking;
import com.tourapp.thin.tour.booking.db.Tour;
import com.tourapp.thin.tour.product.base.db.PricingStatus;
import com.tourapp.thin.tour.product.tour.db.TourStatus;

/**
 * Main Class for applet OrderEntry
 */
public class JMainSearchPane extends JBaseRichScreen
{
	private static final long serialVersionUID = 1L;

	/**
     * The current search pane (the left hand pane with parameter input).
     */
    protected JPanel m_searchParent = null;

    /**
     *  Parent search panel.
     */
    public JMainSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JMainSearchPane(Object obj, BaseApplet applet)
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
        m_parent = obj;  // Now I can do the next call.
        FieldList recBooking = this.getTourAppScreen().getFieldList();   // Booking
        this.addFieldList(this.getTourAppScreen().getTourRecord());  // Tour
        super.init(obj, recBooking);

        this.switchScreens(applet, null, Constants.DONT_PUSH_TO_BROWSER);
    }
    /**
     * Add any screen sub-panel(s) now.
     * You might want to override this to create an alternate parent screen.
     * @param parent The parent to add the new screen to.
     */
    public boolean addSubPanels(Container parent)
    {
        if (parent == null)
            parent = this;
        TourAppScreen screenMain = (TourAppScreen)m_parent;
        BaseApplet applet = this.getBaseApplet();

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        CalendarModel model = screenMain.getCalendarModel();

        // Add the buttons (may want to change this to a card tab)
        panel.add(this.createButtonPanel(applet));
        
        JComponent panelDate = this.buildDateEdit(applet);
        panelDate.setAlignmentX(LEFT_ALIGNMENT);
        panelDate.setAlignmentY(TOP_ALIGNMENT);
        panel.add(panelDate);

        m_searchParent = new JPanel();
        m_searchParent.setOpaque(false);
        m_searchParent.setAlignmentX(LEFT_ALIGNMENT);
        m_searchParent.setLayout(new BorderLayout());
        panel.add(m_searchParent);
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel calendar = this.addSmallCalendar(applet, panel, model, screenMain);
        calendar.setPreferredSize(new Dimension(10, 400));
        
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setAlignmentX(LEFT_ALIGNMENT);
        infoPanel.setAlignmentY(TOP_ALIGNMENT);
        panel.add(infoPanel);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        FieldList recBooking = screenMain.getFieldList();

        JPanel panelTotal = new JPanel();
        panelTotal.setOpaque(false);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.X_AXIS));

        FieldList recPricingStatus = new PricingStatus(this);
        Converter fldPricingStatus = recBooking.getField(Booking.PRICING_STATUS_ID);
        JComponent compPricingStatus = this.addSecondaryIconComponent(recPricingStatus, fldPricingStatus, PricingStatus.ID, PricingStatus.ICON, null);        
        this.addScreenComponent(panelTotal, fldPricingStatus, compPricingStatus);
        panelTotal.add(Box.createHorizontalStrut(3));

        Converter fldPrice = recBooking.getField(Booking.GROSS);
        this.addScreenLabel(infoPanel, fldPrice);
        JComponent component = this.addCurrencyAmount(fldPrice, this.getFieldList().getField(Booking.CURRENCY_CODE), panelTotal);

        this.addScreenComponent(infoPanel, fldPrice, component);
        ScreenUtil.setEnabled(component, false);

        FieldList recTour = screenMain.getTourRecord();
        Converter fldTourStatus = recTour.getField(Tour.TOUR_STATUS_ID);
        this.addScreenLabel(infoPanel, fldTourStatus);

        FieldList record = new TourStatus(this);
        component = this.addSecondaryIconComponent(record, fldTourStatus, TourStatus.ID, TourStatus.ICON, TourStatus.DESCRIPTION);
        this.addScreenComponent(infoPanel, fldTourStatus, component);
        return true;
    }
    /**
     * Free the resources held by this object.
     * NOTE NOTE NOTE: This method does not remove this panel from the parent, or free() sub-panels; you have to do that.
     */
    public void free()
    {
        FieldList recBooking = this.getTourAppScreen().getFieldList();   // Booking
        FieldList recTour = this.getTourAppScreen().getTourRecord();  // Tour
        if (recTour != null)
            this.disconnectControls(recTour);
        if (recBooking != null)
            this.disconnectControls(recBooking);
        this.removeFieldList(null);    // Another screen will free Booking.
        super.free();
    }
    /**
     * Create the button panel.
     */
    public JPanel createButtonPanel(BaseApplet applet)
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.setAlignmentY(TOP_ALIGNMENT);

        ButtonGroup buttonGroup = new ButtonGroup();    //?
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.TOUR, false);
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.AIR, false);
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.HOTEL, false);
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.LAND, false);
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.CAR, false);
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.TRANSPORTATION, false);
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.CRUISE, false);
        this.addSearchButton(applet, buttonGroup, buttonPanel, ProductConstants.ITEM, false);
        return buttonPanel;
    }
    /**
     * Add a search button to this panel and button group (only one can be selected at a time).
     */
    public void addSearchButton(BaseApplet applet, ButtonGroup buttonGroup, JPanel buttonPanel, String strType, boolean bSelected)
    {
        JToggleButton button = null;
        buttonPanel.add(button = new JToggleButton(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + strType, strType), bSelected));
        button.setToolTipText(applet.getString(strType + SearchConstants.TIP));
        buttonGroup.add(button);
        button.setMargin(JScreenConstants.NO_INSETS);
        button.addActionListener(this);
        button.setName(SearchConstants.SEARCH_BUTTON);
    }
    /**
     * Add the small calendar to this panel.
     */
    public JPanel addSmallCalendar(BaseApplet applet, JPanel panelParent, CalendarModel model, TourAppScreen screenMain)
    {
        JScrollPane scrollpane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setOpaque(false);
//        scrollpane.setPreferredSize(new Dimension(300,300));
            // 6th level - calendar panel
        ImageIcon backgroundImage = null;
        if (this.getBaseApplet() != null)
        	backgroundImage = this.getBaseApplet().getBackgroundImage();	// Calendar panel is transparent, but this helps with rendering see-thru components 
        CalendarPanel calendarPanel = new CalendarPanel(model, false, backgroundImage);
        MouseDateListener mouseListener1 = new MouseDateListener(calendarPanel);
        MouseDateListener mouseListener2 = new MouseDateListener(calendarPanel);
        calendarPanel.addMouseMotionListener(mouseListener1);
        calendarPanel.addMouseListener(mouseListener2);
        calendarPanel.setStatusListener(new TaskCalendarStatusHandler(this.getBaseApplet()));

        mouseListener1.addPropertyChangeListener(screenMain.getParams());
        mouseListener2.addPropertyChangeListener(screenMain.getParams());

        calendarPanel.addPropertyChangeListener(screenMain.getParams());
        screenMain.getParams().addPropertyChangeListener(calendarPanel);
        screenMain.getParams().addPropertyChangeListener(this);

        scrollpane.setViewportView(calendarPanel);
        JPanel panelOne = new JPanel();
        panelOne.add(scrollpane);
        panelOne.setOpaque(false);
        panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS));
        panelOne.setAlignmentX(LEFT_ALIGNMENT);
        panelOne.setAlignmentY(BOTTOM_ALIGNMENT);

        panelParent.add(panelOne);
        scrollpane.setAlignmentX(LEFT_ALIGNMENT);
        scrollpane.setAlignmentY(BOTTOM_ALIGNMENT);
        return panelOne;
    }
    /**
     * Get the display (right-hand) screen.
     */
    public JProductSearchPane getProductSearchPane()
    {
        return (JProductSearchPane)JBasePanel.getSubScreen(m_searchParent, JProductSearchPane.class);
    }
    /**
     * Switch the search pane and the display screen to this (product type) display.
     * @param strDesc The product type (such as Hotel, Land, etc).
     * @param iOptions Options
     */
    public JProductSearchPane switchScreens(BaseApplet applet, String strDesc, int iOptions)
    {
        JProductSearchPane searchPane = null;
        // Now display the proper search pane and table pane.
//        JProductSearchPane m_searchParent = this.getProductSearchPane();
        if (ProductConstants.AIR.equalsIgnoreCase(strDesc))
            searchPane = new JAirSearchPane(m_searchParent, null);
        else if (ProductConstants.HOTEL.equalsIgnoreCase(strDesc))
            searchPane = new JHotelSearchPane(m_searchParent, null);
        else if (ProductConstants.LAND.equalsIgnoreCase(strDesc))
            searchPane = new JLandSearchPane(m_searchParent, null);
        else if (ProductConstants.CAR.equalsIgnoreCase(strDesc))
            searchPane = new JCarSearchPane(m_searchParent, null);
        else if (ProductConstants.TRANSPORTATION.equalsIgnoreCase(strDesc))
            searchPane = new JTransportationSearchPane(m_searchParent, null);
        else if (ProductConstants.CRUISE.equalsIgnoreCase(strDesc))
            searchPane = new JCruiseSearchPane(m_searchParent, null);
        else if (ProductConstants.ITEM.equalsIgnoreCase(strDesc))
            searchPane = new JItemSearchPane(m_searchParent, null);
        else if (ProductConstants.TOUR.equalsIgnoreCase(strDesc))
            searchPane = new JTourHeaderSearchPane(m_searchParent, null);
        else
            searchPane = new JMenuSearchPane(m_searchParent, null);
        searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.Y_AXIS));

        if (!applet.changeSubScreen(m_searchParent, searchPane, null, iOptions))
        	return null;	// Error/user not permitted
        
        return searchPane;
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        JComponent button = (JComponent)evt.getSource();
        String strButtonName = button.getName();
        if (SearchConstants.SEARCH_BUTTON.equals(strButtonName))
        {
            BaseApplet.handleAction(BookingConstants.SEARCH, this.getTourAppScreen(), null, 0);  // Make sure we are on the search tab
            JDisplayPanel displayPanel = (JDisplayPanel)this.getTourAppScreen().getCurrentDisplayPanel();
            displayPanel.actionPerformed(evt);
        }
        else
            super.actionPerformed(evt);
    }
}
