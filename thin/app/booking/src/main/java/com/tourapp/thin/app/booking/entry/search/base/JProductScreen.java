package com.tourapp.thin.app.booking.entry.search.base;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.db.converter.SecondaryRecordConverter;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.remote.RemoteTable;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.JScreen;
import org.jbundle.thin.base.screen.message.ThinMessageManager;
import org.jbundle.thin.base.screen.util.JFSTextField;
import org.jbundle.thin.base.screen.util.JFSTextScroller;
import org.jbundle.thin.base.screen.util.LinkedConverter;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.context.JContextPanel;
import com.tourapp.thin.app.booking.entry.search.JDisplayPanel;
import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.tour.product.air.db.Air;
import com.tourapp.thin.tour.product.air.db.City;
import com.tourapp.thin.tour.product.base.db.Product;

/**
 * Main Class for applet OrderEntry
 */
public class JProductScreen extends JScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JProductScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JProductScreen(Object parent, Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * Initialize the screen.
     */
    public void init(Object parent, Object obj)
    {
        FieldList record = null;
        RemoteSession remoteSession = null;
        RemoteTable remoteTable = null;
        FieldTable table = null;
        Object objKey = null;
        if (obj instanceof RemoteSession)
            remoteSession = (RemoteSession)obj;
        else if (obj instanceof FieldList)
        {       // Switch to a full record, but use the same remote session.
            FieldList recordIn = (FieldList)obj;
            objKey = recordIn.getField(0).getData();
            table = recordIn.getTable();
            recordIn.setTable(null);
            // No need to free the recordIn as it is freed from it's screen (just make sure the remote table/session is not freed)
            
            remoteSession = ((org.jbundle.thin.base.db.client.RemoteFieldTable)table).getRemoteTableType(java.rmi.server.RemoteStub.class);
        }
        if (remoteSession != null)
        {   // Always
            try {
                remoteSession.doRemoteAction(SearchConstants.SELECT_MAINT, null);
                
                remoteTable = (RemoteTable)remoteSession;
                record = remoteTable.makeFieldList(null);
                record.setOwner(this);
                
                if (table == null)
                {
                    remoteTable = new org.jbundle.thin.base.db.client.CachedRemoteTable(remoteTable);
                    BaseApplet applet = (BaseApplet)this.getTargetScreen((Container)parent, BaseApplet.class);
                    table = new org.jbundle.thin.base.db.client.RemoteFieldTable(record, remoteTable, applet);
                }

                record.setTable(table);
                table.setRecord(record);
                if (objKey != null)
                {
                    record.getField(0).setData(objKey);
                    if (record.getTable().seek(null))
                    {
//                        record.getFieldTable().edit();
                    }
                }
            } catch (RemoteException ex)    {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        super.init(parent, record);

        BaseApplet applet = this.getBaseApplet();
        if (applet == null)
            applet = BaseApplet.getSharedInstance();
        applet.getApplication().getResources(applet.getApplication().getProperty(Params.RESOURCE), true);   // Make sure I'm using default resources

        TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        JContextPanel contextPanel = screenMain.getContextPanel();
        contextPanel.selectPanel(SearchConstants.PRODUCT, this.getRemoteTableName(), record, true);        // Display this context panel and link to this record.
        
        ThinMessageManager.createScreenMessageListener(record, this);
    }
    /**
     * 
     */
    public void free()
    {
        ThinMessageManager.freeScreenMessageListeners(this);

        TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        JContextPanel contextPanel = screenMain.getContextPanel();
        if (contextPanel != null)
            contextPanel.selectPanel(SearchConstants.PRODUCT, this.getRemoteTableName(), null, true);   // Clear this context screen (if it is still being displayed).
        // Don't free the main record since the session is owned by the search pane.
        FieldList recProduct = this.getFieldList();
        if (recProduct != null)
            this.disconnectControls(recProduct);
        this.removeFieldList(null);
        super.free();
    }
    /**
     * Add any screen sub-panel(s) now.
     * You might want to override this to create an alternate parent screen.
     * @param parent The parent to add the new screen to.
     */
    public boolean addSubPanels(Container parent)
    {
        // There may be a better way of doing this, but this makes the screen top justified.
        parent.setLayout(new BoxLayout(parent, BoxLayout.X_AXIS));

        JPanel panelLeftTop = new JPanel();
        panelLeftTop.setOpaque(false);
        panelLeftTop.setAlignmentY(JLabel.TOP_ALIGNMENT);
        parent.add(panelLeftTop);

        JPanel panelLeft = new JPanel();
        panelLeft.setOpaque(false);
        panelLeftTop.add(panelLeft);        
        return super.addSubPanels(panelLeft);
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
        if (Product.COMMENTS.equalsIgnoreCase(fieldInfo.getFieldName()))
            component = new JFSTextScroller(null);
        if ((Product.DISPLAY_COST_STATUS_ID.equals(fieldInfo.getFieldName()))
            || (Product.DISPLAY_INVENTORY_STATUS_ID.equals(fieldInfo.getFieldName())))
        {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            this.getDisplayPanel().addStatusComponents(fieldInfo, panel, this);
            component = panel;
        }
        if ((Product.CITY_ID.equalsIgnoreCase(fieldInfo.getFieldName()))
            || (Air.CITY_CODE.equalsIgnoreCase(fieldInfo.getFieldName()))
            || (Air.TO_CITY_CODE.equalsIgnoreCase(fieldInfo.getFieldName())))
        {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            FieldList record = new City(this);
            this.addFieldList(record);
            RemoteSession remoteSession = ((org.jbundle.thin.base.db.client.RemoteFieldTable)this.getFieldList().getTable()).getRemoteTableType(java.rmi.server.RemoteStub.class);
            boolean bCacheTable = true;
            TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            screenMain.linkRemoteSessionTable(remoteSession, record, bCacheTable);

            if (!Product.CITY_ID.equalsIgnoreCase(fieldInfo.getFieldName()))
            {
                component = super.createScreenComponent(fieldInfo);
                Util.setEnabled(component, false);
                fieldInfo.addComponent(component);
                panel.add(component);
            }
            else
            {
                fieldInfo = record.getField(City.CITY_CODE);
                component = new JFSTextField(fieldInfo);
                ((JFSTextField)component).setColumns(3);
                Util.setEnabled(component, false);
                fieldInfo.addComponent(component);
                panel.add(component);                
            }
            panel.add(Box.createHorizontalStrut(2));
            
            String strCityID = Product.CITY_ID;
            if (Air.TO_CITY_CODE.equalsIgnoreCase(fieldInfo.getFieldName()))
                strCityID = Air.TO_CITY_ID;
            fieldInfo = this.getFieldList().getField(strCityID);
            fieldInfo = new SecondaryRecordConverter(fieldInfo, remoteSession, record, City.DESCRIPTION, bCacheTable, City.ID, null, null);
            component = new JFSTextField(fieldInfo);
            ((JFSTextField)component).setColumns(25);
            Util.setEnabled(component, false);
            fieldInfo.addComponent(component);
            panel.add(component);

            component = panel;
        }
        if (Product.PRODUCT_COST.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            component = this.getDisplayPanel().addCurrencyAmount(fieldInfo, this.getFieldList().getField(Product.CURRENCY_CODE), null);
            component = this.getDisplayPanel().addCurrencyAmount(this.getFieldList().getField(Product.PRODUCT_COST_LOCAL), this.getFieldList().getField(Product.CURRENCY_CODE_LOCAL), (JPanel)component);
        }
        if (com.tourapp.thin.tour.product.land.db.Land.DAYS_OF_WEEK.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            fieldInfo = new LinkedConverter(fieldInfo)
            {
                public Object getData()
                {
                    Object data = super.getData();
                    
                    String string = Constants.BLANK;
                    if (data instanceof Short)
                    {
                        Converter.initGlobals();
                        Calendar calendar = Converter.gCalendar;
                        Date dateTarget = new Date();   // Pick a date, any date
                        int iFirstDayOfWeek = calendar.getFirstDayOfWeek();
                        calendar.setTime(dateTarget);
                        int iTargetDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        int iOffset = -Math.abs(iTargetDayOfWeek - iFirstDayOfWeek);
                        calendar.add(Calendar.DATE, iOffset);

                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);

                        for (short sBitPosition = 1; sBitPosition <= 7; sBitPosition++)    // Calendar.SUNDAY -> Calendar.SATURDAY
                        {
                            if ((((Short)data).shortValue() & (1 << sBitPosition)) != 0)
                            {
                                String strWeek = this.getDateString(calendar.getTime(), DateFormat.DAY_OF_WEEK_FIELD);
                                string = string + strWeek.substring(0, 2);
                            }
                            calendar.add(Calendar.DATE, 1);
                        }
                    }
                    
                    return string;
                }
                protected DateFormat m_df = DateFormat.getDateInstance(DateFormat.FULL);
                protected StringBuffer m_sb = new StringBuffer();
                /**
                 * Convert this data to a string (using the supplied format).
                 * @param dateTarget The date to convert to a string.
                 * @param iDateFormat The format for the date.
                 * @return The date as a string.
                 */
                public String getDateString(Date dateTarget, int iDateFormat)
                {
                    m_sb.setLength(0);
                    FieldPosition fieldPosition = new FieldPosition(iDateFormat);
                    String string = null;
                    string = m_df.format(dateTarget, m_sb, fieldPosition).toString();
                    int iBegin = fieldPosition.getBeginIndex();
                    int iEnd = fieldPosition.getEndIndex();
                    string = string.substring(iBegin, iEnd);
                    return string;
                }
            };
                component = new JFSTextField(fieldInfo);
                ((JFSTextField)component).setColumns(14);
                Util.setEnabled(component, false);
                fieldInfo.addComponent(component);
                this.add(component);                
        }

        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        
        if (component instanceof JTextField)
            if (((JTextField)component).getColumns() > 35)
                ((JTextField)component).setColumns(35);
        if (Product.DESCRIPTION.equalsIgnoreCase(fieldInfo.getFieldName()))
            if (component instanceof JTextArea)
                ((JTextArea)component).setRows(1);
        if (component != null)
        	Util.setEnabled(component, false);
        return component;
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        return new JBaseToolbar(this, null)
        {
        	private static final long serialVersionUID = 1L;
            /**
             * Add the buttons to this window.
             */
            public void addButtons()
            {
                this.addButton(Constants.BACK);
                this.addButton(Constants.GRID);
                this.addButton(Constants.HELP);
            }
        };
    }
    /**
     * Process this action.
     * @param strAction The action to process.
     * By default, this method handles RESET, SUBMIT, and DELETE.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        if (Constants.BACK.equalsIgnoreCase(strAction))
            strAction = Constants.GRID;
        return super.doAction(strAction, iOptions);
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
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     * @param The index of this field in the record.
     * @return The fieldinfo object.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        if (this.getFieldList() != null)
            return this.getFieldList().getField(this.getFieldName(iIndex));
        return null;    // Never.
    }
    /**
     * Get the field name at this location on the screen.
     * @param iScreenSeq The screen sequence to get the field name from.
     * @return The field name at this screen location.
     */
    public String getFieldName(int iScreenSeq)
    {
        // Override this to do something else!
        Converter field = super.getFieldForScreen(iScreenSeq);
        if (field == null)
            return null;
        if (field == SKIP_THIS_FIELD)
            return Constants.BLANK;
        return field.getFieldName();
    }
    /**
     * Add the menubars?
     * @return The newly created menubar or null.
     */
    public JMenuBar createMenubar()
    {
        return null;    // No menu bar on this sub-screen.
    }
    /**
     * Add the scrollbars?
     * For maint screens, default to true.
     */
    public boolean isAddScrollbars()
    {
        return false;   // My parent already has scrollers
    }
}
