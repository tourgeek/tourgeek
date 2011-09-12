/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel.thin;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@tourgeek.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.message.BaseMessage;
import org.jbundle.thin.base.message.BaseMessageFilter;
import org.jbundle.thin.base.message.BaseMessageManager;
import org.jbundle.thin.base.message.BaseMessageReceiver;
import org.jbundle.thin.base.message.JMessageListener;
import org.jbundle.thin.base.message.MessageConstants;
import org.jbundle.thin.base.message.session.ClientSessionMessageFilter;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.remote.RemoteTable;
import org.jbundle.thin.base.remote.RemoteTask;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JScreen;
import org.jbundle.thin.base.screen.JScreenToolbar;
import org.jbundle.thin.base.screen.grid.JCellRemoteComboBox;


/**
 * Main Class for applet OrderEntry
 */
public class TestThinScreen extends JScreen
    implements JMessageListener
{
    private static final long serialVersionUID = 1L;

    public static final String GET_RATE = "getrate";
    
    protected RemoteSession m_remoteSession = null;

    /**
     *  OrderEntry Class Constructor.
     */
    public TestThinScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public TestThinScreen(Object parent, Object obj)
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

        BaseApplet baseApplet = this.getBaseApplet();
        FieldList fieldList = this.getFieldList();
        m_remoteSession = baseApplet.makeRemoteSession(null, "com.tourapp.app.test.hotel.thin.HotelSession");
        baseApplet.linkRemoteSessionTable(m_remoteSession, fieldList, false);
//------------------------------
//        FieldTable table = this.getFieldList().getFieldTable();
 //       RemoteSession remoteSession = ((com.tourapp.thin.base.db.client.RemoteFieldTable)table).getRemoteTableReference();
//------------------------------

        BaseMessageManager messageManager = this.getBaseApplet().getApplication().getMessageManager();
        BaseMessageReceiver handler = (BaseMessageReceiver)messageManager.getMessageQueue(MessageConstants.RECORD_QUEUE_NAME, MessageConstants.INTRANET_QUEUE).getMessageReceiver();
//x     JMessageListener listener = new TestMessageListener(handler);

        JMessageListener listenerForSession = this; // new JScreenMessageHandler(this);
        Properties properties = new Properties();
//        properties.setProperty(MessageConstants.CLASS_NAME, MessageConstants.RECORD_FILTER);
        BaseMessageFilter filterForSession = new ClientSessionMessageFilter(null, m_remoteSession, properties);
//        BaseMessageFilter filterForSession = new BaseMessageFilter(MessageConstants.TRX_RETURN_QUEUE, MessageConstants.REMOTE_QUEUE_TYPE, this, null);
        filterForSession.addMessageListener(listenerForSession);
        handler.addMessageFilter(filterForSession);
    }
    /**
     * Free this screen's objects.
     */
    public void free()
    {
        BaseMessageManager messageManager = this.getBaseApplet().getApplication().getMessageManager();
        messageManager.freeListenersWithSource(this);
        messageManager.freeFiltersWithSource(this);

        super.free();
    }
    /**
     * Add the screen controls to the second column of the grid.
     * @param parent The container to add the control(s) to.
     * @param gridbag The screen layout.
     * @param c The constraint to use.
     */
    public void addScreenControls(Container parent)
    {
        super.addScreenControls(parent);
        
        JButton component = new JButton("Get Rate");
        GridBagConstraints c = this.getGBConstraints();
        GridBagLayout gridbag = (GridBagLayout)this.getScreenLayout();
        gridbag.setConstraints(component, c);
        parent.add(component);
        component.setOpaque(false);
        component.setName(GET_RATE);
        component.addActionListener(this);
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return new HotelScreenTable(null);   // If overriding class didn't set this
    }
    /**
     * Handle the message.
     */
    public int handleMessage(BaseMessage message)
    {
System.out.println("message: " + message);
        Double dblRate = (Double)message.get("hotelCost");
        if (dblRate != null)    // Display the rate
            this.getFieldList().getField("RoomPrice").setValue(dblRate.doubleValue());
        return Constants.NORMAL_RETURN;
    }
    /**
     * Add this message filter to my list.
     */
    public void addListenerMessageFilter(BaseMessageFilter messageFilter)
    {
        // If this wasn't a test, I would do some maintenance here!
    }
    /**
     * Have the message listener remove this filter from its list.
     */
    public void removeListenerMessageFilter(BaseMessageFilter messageFilter)
    {
    }
    /**
     * Have the message listener remove this filter from its list.
     * NOTE: DO NOT call this method, this method is auto-called when you do filter.addMessageListener(listener);
     * @param messageFilter The message filter to remove.
     */
    public BaseMessageFilter getListenerMessageFilter(int iIndex)
    {
        // If this wasn't a test, I would do some maintenance here!
        return null;
    }
    /**
     * Is this listener going to send its messages to a thin client?
     * @return true if yes.
     */
    public boolean isThinListener()
    {
        return false;
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
        if ("StartDate".equalsIgnoreCase(fieldInfo.getFieldName()))
            component = new org.jbundle.thin.base.screen.util.cal.JCalendarDualField(fieldInfo);
        if ("HotelRate".equalsIgnoreCase(fieldInfo.getFieldName()))
            component = this.setupPopup(new com.tourapp.thin.tour.product.hotel.db.HotelRate(null), "Hotel Rate", "Description", "HotelRate", "ID");
        if ("HotelClass".equalsIgnoreCase(fieldInfo.getFieldName()))
            component = this.setupPopup(new com.tourapp.thin.tour.product.hotel.db.HotelClass(null), "Hotel Class", "Description", "HotelClass", "ID");
        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        return component;
    }
    /**
     * Setup this popup field linked to this remote table.
     */
    public JComponent setupPopup(FieldList record, String strDesc, String strFieldName, String strComponentName, String strIndexValue)
    {
        BaseApplet applet = this.getBaseApplet();

        RemoteTable remoteTable = null;
        try   {
            RemoteTask server = (RemoteTask)applet.getRemoteTask();
            synchronized (server)
            {   // In case this is called from another task
                Map<String, Object> dbProperties = applet.getApplication().getProperties();
                remoteTable = server.makeRemoteTable(record.getRemoteClassName(), null, null, dbProperties);
            }
        } catch (RemoteException ex)    {
            ex.printStackTrace();
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        RemoteSession remoteSession = remoteTable;

        boolean bCacheTable = true;
        return new JCellRemoteComboBox(applet, remoteSession, record, strDesc, strFieldName, strComponentName, bCacheTable, strIndexValue, null);
    }
    /**
     * When a control loses focus, move the field to the data area.
     */
    public void focusLost(FocusEvent e)
    {
        super.focusLost(e);
        Component component = (Component)e.getSource();
        if (component instanceof JTextComponent)
        {
            String string = component.getName();
            FieldInfo field = this.getFieldList().getField(string);        // Get the fieldInfo for this component
//            if (field != null)
//                if ("ID".equals(string))
//                    this.readKeyed(field);
        }
    }
    /**
     * Process this action.
     * @param strAction The action to process.
     * By default, this method handles RESET, SUBMIT, and DELETE.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        if (GET_RATE.equalsIgnoreCase(strAction))
        {
            Map properties = new HashMap();
            this.addScreenFieldProperty(properties, "HotelID", "HotelID");
            this.addScreenFieldProperty(properties, "HotelRate", "HotelRate");
            this.addScreenFieldProperty(properties, "HotelClass", "HotelClass");
            this.addScreenFieldProperty(properties, "StartDate", "StartDate");

            try {
//+                synchronized(m_remoteSession)
                {
                m_remoteSession.doRemoteAction(strAction, properties);
                }
            } catch (Exception ex)    {
                ex.printStackTrace();
            }
        }
        return super.doAction(strAction, iOptions);
    }
    /**
     * Utility method to add the data in this screen field to this key in the property object.
     */
    public void addScreenFieldProperty(Map properties, String strFieldName, String strKey)
    {
        properties.put(strKey, this.getFieldList().getField(strFieldName).getData());
    }
    /**
     * Add the toolbars?
     */
    public JComponent createToolbar()
    {
        return new JScreenToolbar(this, null);
    }
 }
