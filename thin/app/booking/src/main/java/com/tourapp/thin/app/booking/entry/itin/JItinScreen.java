/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.itin;

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
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import org.jbundle.model.Remote;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.message.BaseMessage;
import org.jbundle.thin.base.message.BaseMessageFilter;
import org.jbundle.thin.base.message.BaseMessageHeader;
import org.jbundle.thin.base.message.BaseMessageListener;
import org.jbundle.thin.base.message.BaseMessageManager;
import org.jbundle.thin.base.message.BaseMessageReceiver;
import org.jbundle.thin.base.message.MessageConstants;
import org.jbundle.thin.base.message.MessageReceiverFilterList;
import org.jbundle.thin.base.message.event.FieldListMessageHandler;
import org.jbundle.thin.base.message.session.ClientSessionMessageFilter;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.action.ActionManager;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;

/**
 * Main Class for applet OrderEntry
 */
public class JItinScreen extends JBaseScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JItinScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JItinScreen(Object parent,Object obj)
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

        this.addSubPanels(this);
        
        this.createItineraryChangeMessageListener();
    }
    /**
     * 
     */
    public void createItineraryChangeMessageListener()
    {        
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        // Now add listeners to update screen when data changes
        FieldList recTour = tourAppScreen.getTourRecord();
        FieldTable table = recTour.getTable();
        RemoteSession remoteSession = ((org.jbundle.thin.base.db.client.RemoteFieldTable) table).getRemoteTableType(Remote.class);

        BaseMessageManager messageManager = tourAppScreen.getBaseApplet().getApplication().getMessageManager();
        BaseMessageReceiver messageReceiver = (BaseMessageReceiver)messageManager.getMessageQueue(MessageConstants.RECORD_QUEUE_NAME, MessageConstants.INTRANET_QUEUE).getMessageReceiver();
        MessageReceiverFilterList messageFilters = messageReceiver.getMessageFilterList();
        BaseMessageHeader messageHeaderFilter = new BaseMessageHeader(MessageConstants.RECORD_QUEUE_NAME, MessageConstants.INTRANET_QUEUE, null, null);
        
        Iterator<BaseMessageFilter> filterList = messageFilters.getFilterList(messageHeaderFilter);
        while (filterList.hasNext())
        {
            BaseMessageFilter filterForSession = filterList.next();
            if (filterForSession instanceof ClientSessionMessageFilter)
            {   // There should only be this one
                if (((ClientSessionMessageFilter)filterForSession).getRemoteSession() == remoteSession)
                {
                    m_messageListener = new FieldListMessageHandler(recTour, filterForSession)   // Listener automatically added to receiver
                    {
                        /**
                         * Handle this message.
                         * Basically, if I get a message that the current record changed, I re-read the record.
                         * @param The message to handle.
                         * @return An error code.
                         */
                        public int handleMessage(BaseMessage message)
                        {
                                try   {
                                    int iMessageType = Integer.parseInt((String)message.get(MessageConstants.MESSAGE_TYPE_PARAM));
                                    if ((iMessageType == Constants.AFTER_UPDATE_TYPE)
                                            || (iMessageType == Constants.CACHE_UPDATE_TYPE))
                                        { // Record updated - be very careful, you are running in an independent thread.
                                            refreshItinerary();
                                        }
                                } catch (NumberFormatException ex)  {
                                    // Ignore
                                }
                            return super.handleMessage(message);
                        }
                    };
                    break;
                }
            }
        }
    }
    protected BaseMessageListener m_messageListener = null;
    /**
     * Redisplay the screen.
     */
    public void refreshItinerary()
    {
        TourAppScreen screenMain = (TourAppScreen)getTargetScreen(TourAppScreen.class);
        FieldList record = screenMain.getFieldList();
        String strID = record.getField(Params.ID).toString();
        ItinScreen itinScreen = (ItinScreen)JBasePanel.getSubScreen(this, ItinScreen.class);
        itinScreen.displayItinerary(strID);        
    }
    /**
     * Free.
     */
    public void free()
    {
        if (m_messageListener != null)
            m_messageListener.free();
        super.free();
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        JComponent topPane = new ItinScreen(this, null);
        topPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        topPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        parent.add(topPane);
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
        JMenu menu = ActionManager.getActionManager().addMenu(menuBar, ThinMenuConstants.RECORD, JBaseToolbar.BEFORE_HELP);
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
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            return tourAppScreen.doAction(BookingConstants.SEARCH, iOptions);
        }
        return super.doAction(strAction, iOptions);
    }
}
