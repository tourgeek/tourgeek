/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.base;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jbundle.model.Remote;
import org.jbundle.model.db.Convert;
import org.jbundle.model.message.MessageManager;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.message.BaseMessageFilter;
import org.jbundle.thin.base.message.BaseMessageManager;
import org.jbundle.thin.base.message.BaseMessageReceiver;
import org.jbundle.thin.base.message.JMessageListener;
import org.jbundle.thin.base.message.MessageConstants;
import org.jbundle.thin.base.message.event.ModelMessageHandler;
import org.jbundle.thin.base.message.session.ClientSessionMessageFilter;
import org.jbundle.thin.base.remote.RemoteException;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.remote.RemoteTable;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.action.ActionManager;
import org.jbundle.thin.base.screen.grid.JCellButton;
import org.jbundle.thin.base.screen.grid.JGridScreen;
import org.jbundle.thin.base.util.ThinMenuConstants;
import org.jbundle.util.calendarpanel.dnd.CalendarDnDMouseListener;
import org.jbundle.util.calendarpanel.dnd.CalendarItemTransferable;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.context.JContextPanel;
import com.tourapp.thin.app.booking.entry.search.JDisplayPanel;
import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.tour.product.base.db.Product;

/**
 * Main Class for applet OrderEntry
 */
public class JProductGridScreen extends JGridScreen
    implements ActionListener
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JProductGridScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JProductGridScreen(Object parent, Object model)
    {
        this();
        this.init(parent, model);
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
        FieldList record = null;
        RemoteSession remoteSession = null;
        RemoteTable remoteTable = null;
        FieldTable table = null;
        BaseApplet applet = (BaseApplet)this.getTargetScreen((Container)parent, BaseApplet.class);
        if (obj instanceof RemoteSession)
            remoteSession = (RemoteSession)obj;
        else if (obj instanceof FieldList)
        {       // Switch to a full record, but use the same remote session.
            FieldList recordIn = (FieldList)obj;
            table = recordIn.getTable();
            recordIn.setTable(null);
            // No need to free the recordIn as it is freed from its screen (just make sure the remote table/session is not freed)
            
            remoteSession = ((org.jbundle.thin.base.db.client.RemoteFieldTable)table).getRemoteTableType(null);
            try {
                remoteSession.doRemoteAction(SearchConstants.SELECT_GRID, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (remoteSession != null)
        {   // Always
            try {
                String strTableName = this.getRemoteTableName();
                remoteTable = remoteSession.getRemoteTable(strTableName);
                record = remoteTable.makeFieldList(null);
                record.setOwner(this);
                
                if (table == null)
                {
                    remoteTable = new org.jbundle.thin.base.db.client.CachedRemoteTable(remoteTable);
                    table = new org.jbundle.thin.base.db.client.RemoteFieldTable(record, remoteTable, applet);
                }

                record.setTable(table);
                table.setRecord(record);
            } catch (RemoteException ex)    {
                ex.printStackTrace();
            }
            obj = this.setupGridModel(record.getTable());
        }

        if (obj instanceof AbstractThinTableModel)
        {   // Always
            m_thinTableModel = (AbstractThinTableModel)obj;
            record = m_thinTableModel.getFieldTable().getRecord();
        }

        super.init(parent, record);
        
        if (m_thinTableModel != null)
        {
            TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen((Container)parent, TourAppScreen.class);
            JContextPanel contextPanel = screenMain.getContextPanel();
            ((ProductGridModel)m_thinTableModel).addMySelectionListener(contextPanel);        // Listen for (selection) changes.

            // Listen for remote (returned rate) messages.
            MessageManager messageManager = applet.getApplication().getMessageManager();
            BaseMessageReceiver handler = (BaseMessageReceiver)messageManager.getMessageQueue(MessageConstants.SESSION_QUEUE_NAME, MessageConstants.INTRANET_QUEUE).getMessageReceiver();

            FieldTable tableMain = this.getFieldList().getTable();
            org.jbundle.thin.base.remote.RemoteTable remoteSessionMain = ((org.jbundle.thin.base.db.client.RemoteFieldTable)tableMain).getRemoteTableType(Remote.class);
            JMessageListener listenerForSession = new ModelMessageHandler(null, this.getGridModel())
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
                    {
                        int iCurrentSelection = m_jTableScreen.getSelectedRow();
                        if (iStartIndex == iCurrentSelection)
                        {   // If the current selection has changed, have the context panel change too.
                            TourAppScreen screenMain = (TourAppScreen)getTargetScreen(TourAppScreen.class);
                            JContextPanel contextPanel = screenMain.getContextPanel();
                            if (BookingConstants.PRODUCT.equals(contextPanel.getScreenType())
                                && (((ProductGridModel)m_thinTableModel).getProductType().equals(contextPanel.getProductType())))
                                    contextPanel.selectPanel(m_thinTableModel, iCurrentSelection);     // Redisplay this info if it is in the context panel.
                        }
                    }
                }
            };
            Properties properties = new Properties();
            properties.setProperty(MessageConstants.CLASS_NAME, MessageConstants.GRID_FILTER);
            BaseMessageFilter filterForSession = new ClientSessionMessageFilter(MessageConstants.RECORD_QUEUE_NAME, MessageConstants.INTRANET_QUEUE, this, remoteSessionMain, properties);
            filterForSession.addMessageListener(listenerForSession);
            synchronized (this.getBaseApplet().getRemoteTask())
            {   // Wait for remote filter to set up before I start accessing the data
                handler.addMessageFilter(filterForSession);
            }
        }
    }
    /**
     * Free this screen's objects.
     */
    public void free()
    {
        BaseMessageManager messageManager = (BaseMessageManager)this.getBaseApplet().getApplication().getMessageManager();
        messageManager.freeListenersWithSource(this);
        messageManager.freeFiltersWithSource(this);

        if (m_thinTableModel != null)
        {
            TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            JContextPanel contextPanel = screenMain.getContextPanel();
            ((ProductGridModel)m_thinTableModel).removeMySelectionListener(contextPanel);        // Don't listen for (selection) changes.
            if (contextPanel != null)
                contextPanel.selectPanel(m_thinTableModel, JContextPanel.DISCONNECT);     // Don't access me anymore.
        }

        if (this.getJTable() != null)
        {
            AbstractThinTableModel model = this.getGridModel();
            if (model != null)
            {
                FieldList recProduct = model.getFieldTable().getRecord();
                if (recProduct != null)
                    this.disconnectControls(recProduct);
//x                recProduct.free();   // It is not necessary to free the field list since SearchPane frees it.
                model.free();
            }
        }
        this.removeFieldList(null);    // Just being paranoid
        super.free();
    }
    /**
     * Get the screen description to display in the status bar.
     */
    public String getScreenDesc()
    {
        String strCode = this.getRemoteTableName();
        String strScreenDesc = strCode;
        if (strCode != null)
            if (this.getBaseApplet() != null)
        {
            strScreenDesc = this.getBaseApplet().getString(strCode + "Tip");
            if (strScreenDesc.endsWith("Tip"))
                strScreenDesc = this.getBaseApplet().getString(strCode);
        }
        return strScreenDesc;
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent)
    {
        parent.setLayout(new BorderLayout());
        FieldList record = this.getFieldList();
        if (m_thinTableModel == null)
            m_thinTableModel = this.createGridModel(record);
        m_jTableScreen = new JTable();
        m_jTableScreen.setOpaque(false);
//        m_jTableScreen.setSurrendersFocusOnKeystroke(true);
        
        m_jTableScreen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        m_jTableScreen.setModel(m_thinTableModel);
        if (m_thinTableModel instanceof AbstractThinTableModel)    // Always
            ((AbstractThinTableModel)m_thinTableModel).setGridScreen(m_jTableScreen, false);   // Notify model of selection changes, etc.
        m_jTableScreen.setColumnSelectionAllowed(false);  // By default, don't allow column selections
        for (int iIndex = 0; iIndex < m_jTableScreen.getColumnModel().getColumnCount(); iIndex++)
        {
            TableColumn newColumn = m_jTableScreen.getColumnModel().getColumn(iIndex);
            if (m_thinTableModel.getColumnClass(iIndex) == ImageIcon.class)
            {
                if ((m_thinTableModel.getFieldInfo(iIndex) != null)
                    && ((Product.DISPLAY_COST_STATUS_ID.equals(m_thinTableModel.getFieldInfo(iIndex).getFieldName()))
                    || (Product.DISPLAY_INVENTORY_STATUS_ID.equals(m_thinTableModel.getFieldInfo(iIndex).getFieldName()))))
                {
                    Convert fieldInfo = m_thinTableModel.getFieldInfo(iIndex);
                    if (fieldInfo instanceof FieldInfo)
                    {   // That way this only runs the first time.
                        this.getDisplayPanel().addStatusComponents((Converter)fieldInfo, null, this);
                    }
                    newColumn.setPreferredWidth(20);    // Yeah I know, but I know the width and I don't want to have to wait to load the icon.
                    TableCellEditor button = m_thinTableModel.createColumnCellEditor(iIndex);
                    newColumn.setCellEditor(button);
                    TableCellRenderer button2 = m_thinTableModel.createColumnCellRenderer(iIndex);
                    newColumn.setCellRenderer(button2);
                }
                else
                {
                    Class<?> classColumn = m_thinTableModel.getColumnClass(iIndex);
//x                    Object objIcon = m_thinTableModel.getValueAt(0, iIndex);
                    if (classColumn != null)
                        if (ImageIcon.class.isAssignableFrom(classColumn))
//x                    if (objIcon instanceof ImageIcon)
                    {
                        Object objIcon = m_thinTableModel.getColumnValue(iIndex, Constants.EDIT_NONE);
                        ImageIcon icon = (ImageIcon)objIcon;
                        newColumn.setPreferredWidth(20);    // Yeah I know, but I know the width and I don't want to have to wait to load the icon.
                        JCellButton button = new JCellButton(icon);
                        if ((m_thinTableModel.getFieldInfo(iIndex) != null)
                            && (Product.PRODUCT_TYPE.equals(m_thinTableModel.getFieldInfo(iIndex).getFieldName())))
                        {
                            button.setTransferHandler(new ProductTransferHandler(CalendarItemTransferable.CALENDAR_ITEM_TRANSFER_TYPE));
                            CalendarDnDMouseListener listener = new CalendarDnDMouseListener(button);
                            button.addMouseListener(listener);
                        }
                        button.setName(icon.getDescription());
                        newColumn.setCellEditor(button);
                        button.addActionListener(this);
                        button = new JCellButton(icon);
                        newColumn.setCellRenderer(button);
                    }
                }
            }
            else if (m_thinTableModel.getFieldInfo(iIndex) != null)
            {
                TableCellEditor button = m_thinTableModel.createColumnCellEditor(iIndex);
                newColumn.setCellEditor(button);
                TableCellRenderer button2 = m_thinTableModel.createColumnCellRenderer(iIndex);
                newColumn.setCellRenderer(button2);
            }
            if (iIndex == ProductGridModel.DESC_COLUMN)
                newColumn.setPreferredWidth(240); // Description column.
//+         if (iIndex == 0)
//+             ((DefaultTableCellRenderer)newColumn.getHeaderRenderer()).setToolTipText("Add to itinerary");
        }
        JScrollPane scrollpane = new JScrollPane(m_jTableScreen);
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setOpaque(false);
        scrollpane.setPreferredSize(new Dimension(10,10));
        scrollpane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollpane.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(scrollpane);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//x        m_jTableScreen.getColumnModel().addColumnModelListener(this);
        return true;
    }
    /**
     * A button was pressed in the table.
     * @param strAction The action to handle.
     * @return true if the action was handled here.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        if (ThinMenuConstants.SELECT.equalsIgnoreCase(strAction))
        {
            if (m_jTableScreen == null)
                return false;
            int iRow = m_jTableScreen.getSelectedRow();
            AbstractThinTableModel model = (AbstractThinTableModel)m_jTableScreen.getModel();
            FieldList recProduct = model.makeRowCurrent(iRow, false);
            if (recProduct != null)
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
                        this.addProductToSession(strProductType, strID, null);
                        this.getDisplayPanel().getTourAppScreen().handleAction(BookingConstants.CALENDAR, null, iOptions);  // Switch to the calendar tab
                    }
                }
            }
            return true;    // Handled
        }
        else if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            JDisplayPanel searchPanel = this.getDisplayPanel();
            searchPanel.switchScreens(this.getBaseApplet(), null, iOptions);  // Switch to the menu screen
            return true;    // Done!
        }
        else
            return super.doAction(strAction, iOptions);
    }
    /**
     * Requery the remote session and reset the model to redisplay it.
     */
    public void addProductToSession(String strProductType, String strID, Date dateTarget)
    {
        Map<String,Object> properties = this.getDisplayPanel().getTourAppScreen().getParams().getProperties();
        
        BaseApplet baseApplet = this.getBaseApplet();
        String strDate = (String)properties.get(SearchConstants.DATE);
        if ((strDate == null) && (dateTarget == null))
        {
            JDisplayPanel searchPanel = this.getDisplayPanel();
            
            if (searchPanel.enterDateDialog(baseApplet, this.getDisplayPanel().getTourAppScreen().getParams()) == null)
                return;
            properties = this.getDisplayPanel().getTourAppScreen().getParams().getProperties();
            strDate = (String)properties.get(SearchConstants.DATE);
            if (strDate == null)
                return;
        }
        
        TourAppScreen tourAppScreen = this.getDisplayPanel().getTourAppScreen();
        tourAppScreen.addProductToSession(strProductType, strID, dateTarget);
    }
    /**
     * Get the the base applet that is the parent of this screen.
     */
    public JDisplayPanel getDisplayPanel()
    {
        return (JDisplayPanel)this.getTargetScreen(JDisplayPanel.class);
    }
    /**
     * Get the remote table name.
     */
    public String getRemoteTableName()
    {
        return null;
    }
    /**
     * Setup the grid model.
     * @param table The table.
     * @return The grid model.
     */
    public AbstractThinTableModel setupGridModel(FieldTable table)
    {
        return null;
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        JBaseToolbar toolbar = new JBaseToolbar(this, null);
        toolbar.addButton(Constants.FORM);
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
        return menuBar;
    }
}
