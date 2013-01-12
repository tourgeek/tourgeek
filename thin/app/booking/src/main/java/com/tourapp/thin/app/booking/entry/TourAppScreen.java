/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbundle.model.DBException;
import org.jbundle.model.message.MessageManager;
import org.jbundle.model.message.MessageReceiver;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.KeyAreaInfo;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.message.BaseMessageFilter;
import org.jbundle.thin.base.message.BaseMessageManager;
import org.jbundle.thin.base.message.JMessageListener;
import org.jbundle.thin.base.message.MessageConstants;
import org.jbundle.thin.base.message.event.ModelMessageHandler;
import org.jbundle.thin.base.message.session.ClientSessionMessageFilter;
import org.jbundle.thin.base.remote.RemoteException;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.remote.RemoteTable;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.base.screen.landf.ScreenUtil;
import org.jbundle.thin.base.screen.util.ParamDispatcher;
import org.jbundle.thin.base.thread.PrivateTaskScheduler;
import org.jbundle.thin.base.thread.TaskScheduler;
import org.jbundle.thin.base.util.message.ThinMessageManager;
import org.jbundle.thin.opt.location.JTreePanel;
import org.jbundle.util.calendarpanel.event.MyListSelectionEvent;
import org.jbundle.util.calendarpanel.event.MyListSelectionListener;
import org.jbundle.util.calendarpanel.model.CalendarModel;

import com.tourapp.thin.app.booking.entry.contact.JMainContactScreen;
import com.tourapp.thin.app.booking.entry.context.JContextPanel;
import com.tourapp.thin.app.booking.entry.detail.JBookingDetailMainScreen;
import com.tourapp.thin.app.booking.entry.itin.ItinScreen;
import com.tourapp.thin.app.booking.entry.itin.JItinScreen;
import com.tourapp.thin.app.booking.entry.model.BookingDetailCalendarItem;
import com.tourapp.thin.app.booking.entry.model.BookingDetailCalendarScreen;
import com.tourapp.thin.app.booking.entry.model.CustSaleDetailThinTableModel;
import com.tourapp.thin.app.booking.entry.pax.JBookingPaxMainScreen;
import com.tourapp.thin.app.booking.entry.search.JBaseRichScreen;
import com.tourapp.thin.app.booking.entry.search.JDisplayPanel;
import com.tourapp.thin.app.booking.entry.search.JMainSearchPane;
import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.app.booking.lookup.BookingLookupScreen;
import com.tourapp.thin.tour.assetdr.db.Currencys;
import com.tourapp.thin.tour.booking.db.Booking;
import com.tourapp.thin.tour.booking.db.Tour;
import com.tourapp.thin.tour.product.tour.db.PricingType;


public class TourAppScreen extends JBaseScreen
    implements MyListSelectionListener
{
	private static final long serialVersionUID = 1L;

	public static final String TABBED_PANE = "tabbedPane";
    public static final int CALENDAR_TAB = 0;
    public static final int CONTACT_TAB = 1;
    public static final int PAX_TAB = 2;
    public static final int PRICE_TAB = 3;
    public static final int ITIN_TAB = 4;
    public static final int SEARCH_TAB = 5;
    
    public static final String BOOKING_SESSION_CLASS = "com.tourapp.tour.booking.remote.booking.BookingSession";
    
    /**
     * The model for the tour detail.
     */
    protected CalendarModel m_model = null;
    /**
     * The main (BookingSession) session for this program.
     */
    protected RemoteSession m_remoteSession = null;
    /**
     * The current context panel (the small panel on the bottom of the screen).
     * This panel changes to a small summary screen when the user does a mouse over.
     */
    protected JContextPanel m_contextPanel = null;
    /**
     * Search panel. The small panel to the left that displays search input fields (and the small calendar).
     */
    protected JMainSearchPane m_searchPane = null;
    /**
     * Current display panel. The large display to the right that displays the calendar or search grid.
     */
    protected JBasePanel m_currentDisplayPanel = null;

    /**
     * TourAppScreen Class Constructor.
     */
    public TourAppScreen()
    {
        super();
    }
    /**
     * TourAppScreen Class Constructor.
     */
    public TourAppScreen(Object parent, Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * Initialize this class.
     */
    public void init(Object parent, Object obj)
    {
        if (parent instanceof BaseApplet)
        { // Always
            String strThisPackage = this.getClass().getName();
            strThisPackage = strThisPackage.substring(0, strThisPackage.lastIndexOf('.') + 1);
            ((BaseApplet)parent).getApplication().setProperty(Params.RESOURCE, strThisPackage + "Tour");
            ((BaseApplet)parent).getApplication().setLanguage(null);        // Set the default language

        }
        super.init(parent, obj);
        this.addSubPanels(this);

        this.getCalendarModel().addMySelectionListener(this);
    }
    /**
     * Free the resources held by this object.
     * NOTE NOTE NOTE: This method does not remove this panel from the parent, or free() sub-panels; you have to do that.
     */
    public void free()
    {
        BaseMessageManager messageManager = (BaseMessageManager)this.getBaseApplet().getApplication().getMessageManager();
        messageManager.freeListenersWithSource(this);
        messageManager.freeFiltersWithSource(this);

        this.saveBooking(false);    // Make sure booking changes are saved
        this.getContextPanel().free();  // This must be freed first, so the context isn't changed again
        m_contextPanel = null;
        super.free();
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        // Set up the calendar
        m_model = this.setupCalendarModel();
        
        ThinMessageManager.createScreenMessageListener(this.getFieldList(), this);    // Listen for changes
        ThinMessageManager.createScreenMessageListener(this.getTourRecord(), this);

            // 1st level - a panel to hold everything
        parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
        
        JPanel topPane = new JPanel();
        topPane.setOpaque(false);
        parent.add(topPane);
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
    // Create the search pane
        BaseApplet applet = this.getBaseApplet();
        
        JPanel leftPanel = new JPanel()
        {   // Left pane needs to be small.
            private static final long serialVersionUID = 1L;
            public Dimension getPreferredSize()
            {
                return super.getMinimumSize();
            }
        };
        leftPanel.setOpaque(false);
        leftPanel.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.setAlignmentY(TOP_ALIGNMENT);
        topPane.add(leftPanel);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        m_searchPane = new JMainSearchPane(this, applet);
        m_searchPane.setAlignmentX(LEFT_ALIGNMENT);
        m_searchPane.setAlignmentY(TOP_ALIGNMENT);
        leftPanel.add(m_searchPane);
    // Create a tab pane
            // 4th level - 2nd Panel - Display panels = Tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.setName(TABBED_PANE);
        tabbedPane.setAlignmentX(RIGHT_ALIGNMENT);
        tabbedPane.setAlignmentY(TOP_ALIGNMENT);
        // Add the tab to the center
        topPane.add(tabbedPane);
    
            // 4th level - 3rd Panel - Display panels = ContextPanel
        m_contextPanel = new JContextPanel(this); // Display legend
        m_model.addMySelectionListener(m_contextPanel);     // Listen for (selection) changes.
        parent.add(m_contextPanel);

        tabbedPane.addChangeListener(new PanelChangeListener());

        Container component = null;
        for (int i = 0; ; i++)
        {
            component = this.addTabbedPane(tabbedPane, i);
            if (component == null)
                break;
        }
        
        if (applet.getProperty(Constants.OBJECT_ID) == null)
            tabbedPane.setSelectedIndex(SEARCH_TAB);
        return true;
    }
    /**
     * Get currently selected tab.
     * @return
     */
    public int getSelectedIndex()
    {
        JTabbedPane tabbedPane = (JTabbedPane)this.getComponentByName(TABBED_PANE);
        return tabbedPane.getSelectedIndex();
    }
    /**
     * Change the tabbed panel.
     */
    class PanelChangeListener extends Object
        implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            JTabbedPane tabbedPane = (JTabbedPane)e.getSource();
            int i = tabbedPane.getSelectedIndex();
            JPanel calendarParent = (JPanel)tabbedPane.getSelectedComponent();
            if (m_currentDisplayPanel != null)
                if (!cacheThisScreen(i))
            {   // Free the old screen
                Container target = m_currentDisplayPanel;
                while (target != null)
                {
                    if (target.getParent() != null)
                        if (target.getParent().getParent() == tabbedPane)
                        {
                            target.getParent().remove(target);
                            break;
                        }
                    target = target.getParent();
                }
                m_currentDisplayPanel.free();
                m_currentDisplayPanel = null;
            }
            JBaseScreen baseScreen = (JBaseScreen)JBasePanel.getSubScreen(calendarParent, JBaseScreen.class);
            if (baseScreen != null)
            {
                baseScreen.setupMenuAndToolbar(baseScreen, null);
                baseScreen.requestFocus();
                return;     // Already set up.
            }
            m_currentDisplayPanel = createTabbedPanel(i);
            
            JComponent component = m_currentDisplayPanel.setupNewScreen(m_currentDisplayPanel);
            BaseApplet applet = getBaseApplet();
            JPanel image = new org.jbundle.thin.base.screen.comp.JTiledImage(applet.getBackgroundImage(), applet.getBackgroundColor(), applet);
            image.setLayout(new BorderLayout());
            image.add(component);
            component = image;

            calendarParent.add(component);
            component.requestFocus();
        }
    };
    /**
     * Should I cache this screen or free it?
     * @param iIndex
     * @return
     */
    public boolean cacheThisScreen(int iIndex)
    {
        switch (iIndex)
        {
        case SEARCH_TAB:
        case CALENDAR_TAB:
        case PRICE_TAB:
        case PAX_TAB:
            return true;
        case CONTACT_TAB:
        case ITIN_TAB:
        default:
            return false;
        }
    }
    /**
     * Add an empty pane number iIndex.
     * This will be used later to only load a panel after the tab has been clicked the first time.
     */
    public JComponent addTabbedPane(JTabbedPane tabbedPane, int iIndex)
    {
        JComponent buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        switch (iIndex)
        {
        case SEARCH_TAB:
                // Search screen
            return this.addThisTabbedPane(tabbedPane, BookingConstants.SEARCH, buttonPanel);
        case CALENDAR_TAB:
                // Calendar panel
            return this.addThisTabbedPane(tabbedPane, BookingConstants.CALENDAR, buttonPanel);
        case PRICE_TAB:
                // Price or Costing panel
            return this.addThisTabbedPane(tabbedPane, BookingConstants.PRICE, buttonPanel);
        case PAX_TAB:
                // Passenger screen
            return this.addThisTabbedPane(tabbedPane, BookingConstants.PASSENGER, buttonPanel);
        case CONTACT_TAB:
                // Contact screen
            return this.addThisTabbedPane(tabbedPane, BookingConstants.CONTACT, buttonPanel);
        case ITIN_TAB:
                // Itinerary screen
            return this.addThisTabbedPane(tabbedPane, BookingConstants.ITINERARY, buttonPanel);
        default:
        };
        return null;
    }
    /**
     * Add pane number iIndex.
     * This will be used later to only load a panel after the tab has been clicked the first time.
     */
    public JComponent addThisTabbedPane(JTabbedPane tabbedPane, String strDesc, JComponent searchPanel)
    {
        BaseApplet applet = this.getBaseApplet();
            // 5th level - 1st panel = Search screen
        ImageIcon searchIcon = applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + strDesc + ".gif", strDesc);
        tabbedPane.addTab(applet.getString(strDesc), searchIcon, searchPanel);
        return searchPanel;
    }
    /**
     * Create pane number iIndex.
     * This will be used later to only load a panel after the tab has been clicked the first time.
     */
    public JBasePanel createTabbedPanel(int iIndex)
    {
        BaseApplet applet = this.getBaseApplet();
        switch (iIndex)
        {
        case SEARCH_TAB:
                // Search screen
            return new JDisplayPanel(this, applet);
        case CALENDAR_TAB:
                // Calendar screen
            return new BookingDetailCalendarScreen(this, applet);
        case PRICE_TAB:
                // Pricing screen
            return new JBookingDetailMainScreen(this, null);
        case PAX_TAB:
                // Passengers screen
            return new JBookingPaxMainScreen(this, applet);
        case CONTACT_TAB:
                // Contact screen
            return new JMainContactScreen(this, this.getFieldList());
        case ITIN_TAB:
                // Itinerary screen
            return new JItinScreen(this, null);
        default:
        };
        return null;
    }
    public static TaskScheduler taskScheduler = null;   // HACK
    /**
     * Temp?
     */
    public CalendarModel setupCalendarModel()
    {
        BaseApplet baseApplet = this.getBaseApplet();

        // Be VERY careful here. Since booking is subject to major delays, it has it's own session.
        // DO NOT do any direct remote calls... always run them in a task on this private job scheduler
        taskScheduler = new PrivateTaskScheduler(baseApplet.getApplication(), 0, true);

        m_remoteSession = baseApplet.makeRemoteSession(null, BOOKING_SESSION_CLASS);
        
        try   {
            FieldList recBooking = ((RemoteTable)m_remoteSession).makeFieldList(BookingConstants.SELECTED);
            // Need to add key area manually
            KeyAreaInfo keyArea = new KeyAreaInfo(recBooking, Constants.SECONDARY_KEY, Booking.CODE);
            keyArea.addKeyField(Booking.CODE, Constants.ASCENDING);
            recBooking.addKeyArea(keyArea);
            this.addFieldList(recBooking);  // This gives me the virtual fields also.
        } catch (Exception ex)    {
            ex.printStackTrace();
        }

        this.linkRemoteSessionTable(null, this.getFieldList(), false);
        FieldList recTour = new Tour(this);
        this.addFieldList(recTour);
        this.linkRemoteSessionTable(null, recTour, false);

        String objectID = baseApplet.getProperty(Constants.OBJECT_ID);
        String productType = baseApplet.getProperty(BookingConstants.PRODUCT_TYPE);

        if (productType == null)
        	this.readThisBooking(objectID, Booking.ID, false);
        else
        {
        	Date date = null;
        	String startDate = baseApplet.getProperty(BookingConstants.DATE);
        	if (startDate != null)
        	{
        		try {
					date = FieldInfo.stringToDate(startDate, Constants.DATE_ONLY);
				} catch (Exception e) {
					// Ignore (date = null)
				}
        	}
        	if (date == null)
        	{
	        	JBaseRichScreen tempScreen = new JBaseRichScreen(this, baseApplet);
	        	this.add(tempScreen);	// Hack - Need a rich screen to handle the date stuff
	            date = tempScreen.enterDateDialog(baseApplet, this.getParams());
	            this.remove(tempScreen);
	            tempScreen.free();
        	}
            if (date != null)
            {
            	boolean success = this.addProductToSession(productType, objectID, date);
            	if (!success)
            		;	// For now, ignore failure and just display the blank screen.
            }
        }

        RemoteTable bookingDetailSession = null;
        FieldList recBookingDetail = null;
        try   {
            bookingDetailSession = (RemoteTable)m_remoteSession.doRemoteAction(BookingConstants.GET_DETAIL_COMMAND, null);
            recBookingDetail = bookingDetailSession.makeFieldList(BookingConstants.SELECTED);
        } catch (Exception ex)    {
            ex.printStackTrace();
        }
        BookingDetailCalendarItem recBookingDetailCalendarItem = new BookingDetailCalendarItem(null, null);
        while (recBookingDetailCalendarItem.getFieldCount() > 0)
        {
            FieldInfo field = recBookingDetailCalendarItem.getField(0);
            field.free();
        }
        for (int iFieldSeq = 0; iFieldSeq < recBookingDetail.getFieldCount(); iFieldSeq++)
        {
            FieldInfo fieldActual = recBookingDetail.getField(iFieldSeq);
            FieldInfo field = new FieldInfo(recBookingDetailCalendarItem, fieldActual.getFieldName(), fieldActual.getMaxLength(), fieldActual.getFieldDesc(), fieldActual.getDefault());
            field.setDataClass(fieldActual.getDataClass());
        }
        this.linkRemoteSessionTable(bookingDetailSession, recBookingDetailCalendarItem, true);

        CustSaleDetailThinTableModel model = new CustSaleDetailThinTableModel(recBookingDetailCalendarItem.getTable());
        recBookingDetailCalendarItem.setModel(model);

        MessageManager messageManager = baseApplet.getApplication().getMessageManager();
        JMessageListener listenerForSession = new ModelMessageHandler(null, model)
        {
            /**
             * A message has been received to update the model at this row.
             * NOTE: DO NOT call this method directly; it is guaranteed to be in the awt thread and IS NOT general thread safe.
             * @param iMessageType
             * @param iStartIndex
             * @param iEndIndex
             */
            public void updateModel(int iMessageType, int iStartIndex, int iEndIndex)
            {
                super.updateModel(iMessageType, iStartIndex, iEndIndex);
                int iCurrentSelection = ((CustSaleDetailThinTableModel)m_model).getSelectedRow();
                if (iStartIndex == iCurrentSelection)
                {   // If the current selection has changed, have the context panel change too.
                    JContextPanel contextPanel = getContextPanel();
                    if (BookingConstants.BOOKING.equals(contextPanel.getScreenType()))
                    {
                        BookingDetailCalendarItem fieldList = (BookingDetailCalendarItem)((CustSaleDetailThinTableModel)m_model).makeRowCurrent(iCurrentSelection, false);
                        if (fieldList.getLocalProductType().equals(contextPanel.getProductType()))
                            contextPanel.selectPanel((CustSaleDetailThinTableModel)m_model, iCurrentSelection);     // Redisplay this info if it is in the context panel.
                    }
                }
            }
        };
        MessageReceiver handler = messageManager.getMessageQueue(MessageConstants.RECORD_QUEUE_NAME, MessageConstants.INTRANET_QUEUE).getMessageReceiver();

        Properties properties = new Properties();
        properties.setProperty(MessageConstants.CLASS_NAME, MessageConstants.GRID_FILTER);
        BaseMessageFilter filterForSession = new ClientSessionMessageFilter(MessageConstants.RECORD_QUEUE_NAME, MessageConstants.INTRANET_QUEUE, this, bookingDetailSession, properties);
        filterForSession.addMessageListener(listenerForSession);
        handler.addMessageFilter(filterForSession);

        return model;
    }
    /**
     * Return the global calendar model.
     */
    public CalendarModel getCalendarModel()
    {
        return m_model;
    }
    /**
     * Get the remote (Booking) session.
     */
    public RemoteSession getRemoteSession()
    {
        return m_remoteSession;
    }
    
    protected ParamDispatcher m_params = null;
    
    public static final String[] m_rgstrValidProperties =
    {
        SearchConstants.DATE,
        SearchConstants.TOUR_TYPE,
        SearchConstants.SEARCH_TEXT,
        SearchConstants.HOTEL_CLASS,
        SearchConstants.NIGHTS,
        SearchConstants.PAX,
        SearchConstants.AIR_CLASS,
        SearchConstants.AIRLINE,
        SearchConstants.CAR_CLASS,
        SearchConstants.TRANSPORTATION_CLASS,
        SearchConstants.CRUISE_CLASS,
        SearchConstants.LOCATION_TO,

        JTreePanel.LOCATION_RECORD,
        JTreePanel.LOCATION_ID,
        JTreePanel.LOCATION,
        SearchConstants.DATE_DISPLAY,
    };
    /**
     * Get the params that go with this screen.
     */
    public ParamDispatcher getParams()
    {
        if (m_params == null)
            m_params = new ParamDispatcher(m_rgstrValidProperties);
        return m_params;
    }
    /**
     * Process this action.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        JBaseScreen screen = null;

        boolean bHandled = false;

        if (Constants.RESET.equalsIgnoreCase(strAction))
        {
            bHandled = this.readThisBooking(null, null,  true);
        }
        
        if (!bHandled)
            bHandled = super.doAction(strAction, iOptions);
    
        if (bHandled == false)
            if (Constants.GRID.equalsIgnoreCase(strAction))
        {
            try   {
                boolean bSuccess = this.saveBooking(false);
                if (bSuccess)
                    screen = this.createGridScreen(null);   // Do not pass this record.
                if (screen != null)
                {
                    Container parent = null;
                    if (screen.getParentObject() instanceof Container)
                        parent = (Container)screen.getParentObject();
                    this.getBaseApplet().changeSubScreen(parent, screen, null, iOptions);
                    bHandled = true;
                }
            } catch (Exception ex)  {
                ex.printStackTrace();
            }
        }
        if (Constants.SUBMIT.equalsIgnoreCase(strAction))
        {
            bHandled = this.saveBooking(false);
            if (bHandled)
                this.readThisBooking(null, null, true);
            this.resetFocus();
        }
        else if (Constants.DELETE.equalsIgnoreCase(strAction))
        {       // Delete this booking
            boolean bSuccess = this.deleteBooking();
            if (bSuccess)
            {   // Clear the detail.
                this.refreshDetailRecords();
            }
            this.resetFocus();
            bHandled = true;   // Handled by this screen
        }
        else if (Constants.RESET.equalsIgnoreCase(strAction))
        {    // Ignore the current booking changes and clear the screen.
            this.resetFocus();
            bHandled = true;   // Handled by this screen
        }

        if (bHandled == false)
        {
            if (ProductConstants.TOUR.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.tour.JBookingTourScreen();   // Note: I did not initialize the class by passing the params
            else if (ProductConstants.AIR.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.air.JBookingAirScreen();   // Note: I did not initialize the class by passing the params
            else if (ProductConstants.HOTEL.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.hotel.JBookingHotelScreen();   // Note: I did not initialize the class by passing the params
            else if (ProductConstants.LAND.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.land.JBookingLandScreen();  // Note: I did not initialize the class by passing the params
            else if (ProductConstants.CAR.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.car.JBookingCarScreen();  // Note: I did not initialize the class by passing the params
            else if (ProductConstants.TRANSPORTATION.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.trans.JBookingTransportationScreen();  // Note: I did not initialize the class by passing the params
            else if (ProductConstants.CRUISE.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.cruise.JBookingCruiseScreen();  // Note: I did not initialize the class by passing the params
            else if (ProductConstants.ITEM.equalsIgnoreCase(strAction))
                screen = new com.tourapp.thin.app.booking.entry.detail.item.JBookingItemScreen();  // Note: I did not initialize the class by passing the params
            else
            {
                int iTab = -1;
                if (BookingConstants.SEARCH.equalsIgnoreCase(strAction))
                    iTab = SEARCH_TAB;
                else if (BookingConstants.CALENDAR.equalsIgnoreCase(strAction))
                    iTab = CALENDAR_TAB;
                else if (BookingConstants.PRICE.equalsIgnoreCase(strAction))
                    iTab = PRICE_TAB;
                else if (BookingConstants.PASSENGER.equalsIgnoreCase(strAction))
                    iTab = PAX_TAB;
                else if (BookingConstants.CONTACT.equalsIgnoreCase(strAction))
                    iTab = CONTACT_TAB;
                else if (BookingConstants.ITINERARY.equalsIgnoreCase(strAction))
                    iTab = ITIN_TAB;
                if (iTab != -1)
                {
                    JTabbedPane tabbedPane = (JTabbedPane)this.getComponentByName(TABBED_PANE);
                    tabbedPane.setSelectedIndex(iTab);
                    bHandled = true;
                }
            }
            if (screen != null)
            {
                this.initAndDisplayScreen(screen, null);
            }
        }
        return bHandled;
    }
    /**
     * Read this booking number.
     * @param strKeyValue The booking number to read (or null to clear the booking no).
     */
    public boolean readThisBooking(String strKeyValue, String strKeyField, boolean bRefreshDetailRecords)
    {
        boolean bSuccess = false;
        try   {
            FieldList recBooking = this.getFieldList();
            if ((strKeyValue != null) && (strKeyValue.length() > 0))
            {
                if ((recBooking.getEditMode() == Constants.EDIT_CURRENT) || (recBooking.getEditMode() == Constants.EDIT_IN_PROGRESS))
                {
                    try {
                        if (Booking.ID.equalsIgnoreCase(strKeyField))
                        {
                            int iBookingNo = Integer.parseInt(strKeyValue);
                            if (iBookingNo == recBooking.getField(Booking.ID).getValue())
                                return true;    // Already read
                        }
                        else if (Booking.CODE.equalsIgnoreCase(strKeyField))
                        {
                            if (strKeyValue.equalsIgnoreCase(recBooking.getField(Booking.CODE).toString()))
                                return true;    // Already read
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
                recBooking.getTable().addNew();
                recBooking.getField(strKeyField).setString(strKeyValue);
                String strOldKeyArea = recBooking.getKeyName();
                recBooking.setKeyArea(strKeyField);
                bSuccess = recBooking.getTable().seek(Constants.EQUALS);
                recBooking.setKeyArea(strOldKeyArea);
                if (bSuccess)
                {
                    bSuccess = this.getTourRecord().getTable().seek(Constants.SEEK_CURRENT_RECORD);
                    if (bSuccess)
                        bSuccess = this.getCurrencyRecord().getTable().seek(Constants.SEEK_CURRENT_RECORD);
                }
            }
            else
            {
                recBooking.getTable().addNew();    // Clean the booking record
                this.getRemoteSession().doRemoteAction(Constants.RESET, null);    // Tell the remote session that I'm done with this booking
                this.getTourRecord().getTable().addNew();  // Clear tour record
                bSuccess = true;
            }
        } catch (DBException ex)  {
            this.getBaseApplet().setStatusText(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            bSuccess = false;
        } catch (RemoteException ex)  {
            this.getBaseApplet().setStatusText(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            bSuccess = false;
        }

        if (bSuccess == true)
            if (bRefreshDetailRecords)
        {   // Clear the detail records.
            this.refreshDetailRecords();
            this.resetFocus();
        }
    
        return bSuccess;
    }
    /**
     * The booking has changed, update the detail records to match.
     */
    public void refreshDetailRecords()
    {
        JContextPanel contextPanel = this.getContextPanel();
        contextPanel.selectPanel(null, null, null, true);        // Display the main context panel.

        JBasePanel paxScreen = (JBasePanel)JBasePanel.getSubScreen(this, JBookingPaxMainScreen.class);
        if (paxScreen != null)
            BaseApplet.handleAction(Constants.RESET, paxScreen, paxScreen, 0);  // Send to paxScreen and children.
        // Clear the itin screen
        ItinScreen itinScreen = (ItinScreen)JBasePanel.getSubScreen(this, ItinScreen.class);
        if (itinScreen != null)
            BaseApplet.handleAction(Constants.RESET, itinScreen, itinScreen, 0);  // Send to itin screen and children.
        // Now, clear the product detail.
        AbstractThinTableModel model = (AbstractThinTableModel)this.getCalendarModel();
        FieldTable table = model.getFieldTable();
        table.close();
        model.resetTheModel();
    }
    /**
     * Save the current booking.
     * @param bRefreshRecord Re-read the record after add or set.
     * @return true If booking was successfully saved.
     */
    public boolean saveBooking(boolean bRefreshRecord)
    {
        FieldList recBooking = null;
        FieldTable tblBooking = null;
        try {
            Object bookmark = null;
            recBooking = this.getFieldList();
            tblBooking = recBooking.getTable();
            if (recBooking.isModified())
            {
                if ((recBooking.getEditMode() == Constants.EDIT_IN_PROGRESS) ||
                    (recBooking.getEditMode() == Constants.EDIT_CURRENT))
                {
                    bookmark = tblBooking.getHandle(0);
                    tblBooking.set(recBooking);
                    if (bRefreshRecord)
                        tblBooking.setHandle(bookmark, 0);
                }
                else
                {
                    tblBooking.add(recBooking);
                    if (bRefreshRecord)
                    {
                        bookmark = tblBooking.getLastModified(0);
                        tblBooking.setHandle(bookmark, 0);
                    }
                }
            }
            FieldList recTour = this.getTourRecord();
            FieldTable tblTour = recTour.getTable();
            if (recTour.isModified())
            {
                if ((recTour.getEditMode() == Constants.EDIT_IN_PROGRESS) ||
                    (recTour.getEditMode() == Constants.EDIT_CURRENT))
                {
                    bookmark = tblTour.getHandle(0);
                    tblTour.set(recTour);
                    if (bRefreshRecord)
                        tblTour.setHandle(bookmark, 0);
                }
                else
                {
                    tblTour.add(recTour);
                    if (bRefreshRecord)
                    {
                        bookmark = tblTour.getLastModified(0);
                        tblTour.setHandle(bookmark, 0);
                    }
                }
            }
        } catch (DBException ex)    {
            this.getBaseApplet().setStatusText(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    /**
     * Delete the current booking.
     * @return true If booking was successfully saved.
     */
    public boolean deleteBooking()
    {
        boolean bSuccess = false;
        try   {
            FieldList recBooking = this.getFieldList();
            if ((recBooking.getEditMode() == Constants.EDIT_NONE) || (recBooking.getEditMode() == Constants.EDIT_ADD))
            {
                recBooking.getTable().addNew();
                return true;
            }
            String strTitle = "Delete";
            String strMessage = "Are you sure you want to delete this booking?";
            if (JOptionPane.showConfirmDialog(ScreenUtil.getFrame(this), strMessage, strTitle, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
                return false;
            recBooking.getTable().addNew();
            Object objSuccess = this.getRemoteSession().doRemoteAction(Constants.DELETE, null);    // Tell the remote session that I'm done with this booking
            if (objSuccess instanceof Boolean)
                bSuccess = ((Boolean)objSuccess).booleanValue();
            recBooking.getTable().addNew();    // Clear the booking record
            this.getTourRecord().getTable().addNew();  // Clear tour record
            bSuccess = true;
        } catch (RemoteException ex)  {
            this.getBaseApplet().setStatusText(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            bSuccess = false;
        } catch (DBException ex)  {
            this.getBaseApplet().setStatusText(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            bSuccess = false;
        }
        return bSuccess;
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createGridScreen(FieldList record)
    {
        return new BookingLookupScreen(this.getParentObject(), record);
    }
    /**
     * These items have changed, update them on the screen.
     */
    public void selectionChanged(MyListSelectionEvent event)
    {
        int iType = event.getType();
        if (iType == MyListSelectionEvent.CONTENT_CLICK)    // Select + click
        {
            JTabbedPane tabbedPane = (JTabbedPane)JBasePanel.getSubScreen(this, JTabbedPane.class);
            tabbedPane.setSelectedIndex(PRICE_TAB);
            JBookingDetailMainScreen mainScreen = (JBookingDetailMainScreen)JBasePanel.getSubScreen(this, JBookingDetailMainScreen.class);
            mainScreen.setSource(event.getSource());
            BaseApplet.handleAction(Constants.FORM, mainScreen, null, 0);
        }
    }
    /**
     * Process this action.
     */
    public void initAndDisplayScreen(JBaseScreen screen, FieldList record)
    {
        BaseApplet applet = this.getBaseApplet();
        if ((record != null) || (screen != null))
        {
            Container parent = ((JDisplayPanel)JBasePanel.getSubScreen(this, JDisplayPanel.class)).getDisplayScreen();
            screen.init(parent, record);
            applet.changeSubScreen(parent, screen, null, Constants.DONT_PUSH_TO_BROWSER);
        }
    }
    /**
     * Get the context panel.
     */
    public JContextPanel getContextPanel()
    {
        return m_contextPanel;
    }
    /**
     * Get the search pane.
     */
    public JMainSearchPane getMainSearchPane()
    {
        return m_searchPane;
    }
    /**
     * Get the search pane.
     */
    public JBasePanel getCurrentDisplayPanel()
    {
        return m_currentDisplayPanel;
    }
    /**
     * Get the the base applet that is the parent of this screen.
     */
    public JDisplayPanel getDisplayPanel()
    {
        return (JDisplayPanel)JBasePanel.getSubScreen(this, JDisplayPanel.class);
    }
    /**
     * Get the tour record.
     */
    public FieldList getTourRecord()
    {
        return this.getFieldList(Tour.TOUR_FILE);
    }
    /**
     * Get the tour record.
     */
    public FieldList getCurrencyRecord()
    {
        if (this.getFieldList(Currencys.CURRENCYS_FILE) == null)
        {
            Currencys recCurrency = new Currencys(this);
            this.addFieldList(recCurrency);
            this.linkRemoteSessionTable(null, recCurrency, false);
        }
        return this.getFieldList(Currencys.CURRENCYS_FILE);
    }
    /**
     * Link this record to the remote Ejb Table in this remote session.
     * Note: If you don't pass a record, this method will create one for you of the main
     * record of this session.
     * @param session Optional parent or database session.
     * @param strTableSessionClass Optional class for the table session. Null defaults to TableSessionObject.
     * @param record Thin record or null if you want the main record is this session.
     * @param bUseCache Use cache table
     * @return The new fieldtable for this fieldlist.
     */
    public FieldTable linkRemoteSessionTable(RemoteSession session, FieldList record, boolean bUseCache)
    {
        if (session == null)
            session = m_remoteSession;
        BaseApplet applet = this.getBaseApplet();
        return applet.linkRemoteSessionTable(session, record, bUseCache);
    }
    /**
     * Requery the remote session and reset the model to redisplay it.
     */
    public boolean addProductToSession(String strProductType, String strID, Date dateTarget)
    {
        boolean bSuccess = false;
        Map<String,Object> properties = this.getParams().getProperties();
        
        BaseApplet baseApplet = this.getBaseApplet();
        String strDate = (String)properties.get(SearchConstants.DATE);
        
        properties.put(Constants.OBJECT_ID, strID);
        properties.put(SearchConstants.PRODUCT_TYPE, strProductType);
        synchronized (baseApplet.getRemoteTask())
        {
            try   {
                RemoteSession remoteSession = this.getRemoteSession();

                if (dateTarget != null)
                    properties.put(SearchConstants.DATE, dateTarget.toString());
                RemoteTable bookingDetailSession = (RemoteTable)remoteSession.doRemoteAction(BookingConstants.GET_DETAIL_COMMAND, properties);
                Object objReturn = bookingDetailSession.doRemoteAction(SearchConstants.ADD_COMMAND, properties);
                if (strDate != null)
                    properties.put(SearchConstants.DATE, strDate);  // Set it back.
                if (objReturn instanceof Boolean)
                { // Success/Failure
                    return ((Boolean)objReturn).booleanValue();
                }
                else if (objReturn instanceof Integer)
                { // Integer is the booking number
                    return this.readThisBooking(objReturn.toString(), Booking.ID, true);
                }
            } catch (RemoteException ex)    {
                ex.printStackTrace();
            } catch (Exception ex)  {
                System.out.println(ex.getMessage());
            }
        }
        return bSuccess;
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        return this.getCurrentDisplayPanel().createToolbar();
    }
    /**
     * Add the menubars?
     * @return The newly created menubar or null.
     */
    public JMenuBar createMenubar()
    {
        return this.getCurrentDisplayPanel().createMenubar();
    }
    /**
     * Does this booking use pricing screens (or should I use costing screens)
     * @return True if I should use the pricing screens.
     */
    public boolean isUsingPricingScreens()
    {
        FieldList recBooking = this.getFieldList();
        if ((recBooking.getEditMode() == Constants.EDIT_NONE) || (recBooking.getEditMode() == Constants.EDIT_ADD))
            return true;
        if ((this.isOptionPricing(Booking.TOUR_PRICING_TYPE_ID)) || (this.isOptionPricing(Booking.NON_TOUR_PRICING_TYPE_ID)))
            return true;
        return false;   // Costing type
    }
    /**
     * Is this an option pricing type?
     * @param strBookingField
     * @return
     */
    public boolean isOptionPricing(String strFieldName)
    {
        FieldList recPricingType = this.getFieldList(PricingType.PRICING_TYPE_FILE);
        if (recPricingType == null)
        {
            recPricingType = new PricingType(this);
            this.getBaseApplet().linkNewRemoteTable(recPricingType, false);
        }
        FieldList recBooking = this.getFieldList();
        Integer intID = (Integer)recBooking.getField(strFieldName).getData();
        recPricingType.getField("ID").setData(intID);
        try {
            if (recPricingType.getTable().seek(null))
            {
                int intPricingCodes = ((Integer)recPricingType.getField(PricingType.PRICING_CODES).getData()).intValue();
                if ((intPricingCodes & PricingType.OPTION_PRICING) != 0)
                    return true;
                else
                    return false;
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * Markup this cost using the current markup percentages.
     * @param dCost
     * @return
     */
    public double markupThisCost(double dCost)
    {
        if (this.isUsingPricingScreens())
            return 0.00;    // Can't get price from cost
        FieldList recBooking = this.getFieldList();
        double dMarkup = ((Double)recBooking.getField(Booking.MARKUP).getData()).doubleValue(); 
        double dCommission = 0; //((Double)recBooking.getFieldInfo(Booking.STD_COMMISSION).getData()).doubleValue();
        return Math.floor(((dCost * (1 + dMarkup)) / (1 - dCommission)) * 100 + 0.5) / 100;
    }
}
