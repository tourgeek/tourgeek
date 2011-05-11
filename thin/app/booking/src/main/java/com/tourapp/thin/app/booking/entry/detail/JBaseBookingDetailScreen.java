package com.tourapp.thin.app.booking.entry.detail;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbundle.model.DBException;
import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.message.ThinMessageManager;
import org.jbundle.thin.base.screen.util.JMultiFieldPanel;
import org.jbundle.thin.base.screen.util.cal.JCalendarDualField;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.search.JBaseRichScreen;
import com.tourapp.thin.tour.assetdr.db.Currencys;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;

/**
 * Main Class for applet OrderEntry
 */
public class JBaseBookingDetailScreen extends JBaseRichScreen
    implements PropertyChangeListener, FocusListener, ChangeListener
{
	private static final long serialVersionUID = 1L;

	protected RemoteSession m_remoteSession = null;
    protected FieldList m_recProduct = null;

    /**
     *  OrderEntry Class Constructor.
     */
    public JBaseBookingDetailScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBaseBookingDetailScreen(Object parent, Object obj)
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
        m_recProduct = this.getProductRecord();

        m_parent = parent;
        FieldList record = null;
        if (obj instanceof FieldList)
            record = (FieldList)obj;
        else
            record = this.buildFieldList();
        if (record != null)
        {   // This logic is similar to the logic in JBaseScreen, except I create my custom remote session.
            boolean bAddCache = false;
            if (record.getTable() == null)
                this.getBaseApplet().linkRemoteSessionTable(this.getRemoteSession(record), record, bAddCache);
            if (record.getTable() != null)
                if (record.getEditMode() == Constants.EDIT_NONE)
            {
                try   {
                    record.getTable().addNew();
                } catch (DBException ex)    {
                    ex.printStackTrace(); // Never.
                }
            }
        }
        this.addFieldList(new Currencys(this));
        
        BaseApplet applet = this.getBaseApplet();
        if (applet == null)
            applet = BaseApplet.getSharedInstance();
        applet.getApplication().getResources(applet.getApplication().getProperty(Params.RESOURCE), true);   // Make sure I'm using default resources

        super.init(parent, record);

        boolean bSuccess = false;
        FieldList recBookingDetail = this.getFieldList();
        String strID = (String)obj;
        try   {
            if ((strID != null)
                && (strID.length() > 0))
            {
                recBookingDetail.getField(Params.ID).setString(strID);
                bSuccess = recBookingDetail.getTable().seek(null);
                if (bSuccess)
                    this.fieldsToControls();
            }
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        
        recBookingDetail.addPropertyChangeListener(this);

        if (bSuccess)
            if (m_recProduct != null)
                if (this.getFieldList(Currencys.CURRENCYS_FILE) != null)
        {
            try   {
                Map<String,Object> properties = new Hashtable<String,Object>();
                properties.put(Params.FIELD + "1", Currencys.CURRENCYS_FILE + '.' + Currencys.DESCRIPTION);
                properties.put(Params.FIELD + "2", Currencys.CURRENCYS_FILE + '.' + Currencys.CURRENCY_CODE);
                Object objProperties = this.getRemoteSession(null).doRemoteAction(Params.GET_FIELD_DATA, properties);
                if (objProperties instanceof Map)
                {
                    properties = (Map)objProperties;
                    String string = (String)properties.get(Params.FIELD + "1");
                    this.getFieldList(Currencys.CURRENCYS_FILE).getField(Currencys.DESCRIPTION).setString(string);
                    string = (String)properties.get(Params.FIELD + "2");
                    this.getFieldList(Currencys.CURRENCYS_FILE).getField(Currencys.CURRENCY_CODE).setString(string);
                }
                this.fieldsToControls();
            } catch (Exception ex)  {
                ex.printStackTrace();
            }
        }

        ThinMessageManager.createScreenMessageListener(record, this);
        
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        JTabbedPane tabbedPane = (JTabbedPane)JBasePanel.getSubScreen(tourAppScreen, JTabbedPane.class);
        tabbedPane.addChangeListener(this);
    }
    /**
     * Free.
     */
    public void free()
    {
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        JTabbedPane tabbedPane = (JTabbedPane)JBasePanel.getSubScreen(tourAppScreen, JTabbedPane.class);
        tabbedPane.removeChangeListener(this);

        ThinMessageManager.freeScreenMessageListeners(this);

        FieldList recBookingDetail = this.getFieldList();
        recBookingDetail.removePropertyChangeListener(this);
        
        this.writeAndRefresh(recBookingDetail, false);     // Update this if there were any changes (Don't refresh)

        super.free();
    }
    /**
     * Get the product record.
     * Override this to supply the correct product record.
     * @return The product record.
     */
    public FieldList getProductRecord()
    {
        return null;
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
        super.addSubPanels(panelLeft);
        return true;
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return null;  // Overriding class must set this!
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = null;

        String strDefault = fieldInfo.toString();
        if (strDefault == null)
            strDefault = Constants.BLANK;
        
        if (fieldInfo.getFieldName().equals(BookingDetail.DETAIL_DATE))
        {
            component = new JCalendarDualField(fieldInfo);
        }
        else if (fieldInfo.getFieldName().equals(BookingDetail.DETAIL_END_DATE))
        {
            component = new JCalendarDualField(fieldInfo);
        }
        else if (fieldInfo.getFieldName().equals(BookingDetail.DESCRIPTION))
        {
            if (BookingDetail.BOOKING_DETAIL_FILE.equalsIgnoreCase(fieldInfo.getField().getRecord().getTableNames(false)))  // Since Description can be in currency dislay also.
            {
                JComponent component1 = super.createScreenComponent(fieldInfo);
                ((JTextArea)component1).setRows(1);
                Util.setEnabled(component1, false);

    //+            JButton component2 = new JButton(applet.loadImageIcon(Constants.FORM, Constants.FORM));
    //+            component2.setMargin(Constants.NO_INSETS);
    //+            component2.addActionListener(this);   // Requery catalog on change

    //+            JDualFieldPanel panel = new JDualFieldPanel(fieldInfo, component1, component2);

                component = component1;
    //+            component = panel;
            }
            else
            {
                component = super.createScreenComponent(fieldInfo);
                Util.setEnabled(component, false);
            }
        }
        else if ((fieldInfo.getField().getDataClass() == Double.class) && (!fieldInfo.getFieldName().contains("Price")))
        {
            JComponent component2 = super.createScreenComponent(fieldInfo);
            fieldInfo.addComponent(component2);
            Util.setEnabled(component2, false);
            JComponent component1 = new JTextField(Constants.BLANK, 3);
            Util.setEnabled(component1, false);
            if (BookingDetail.TOTAL_COST_LOCAL.equalsIgnoreCase(fieldInfo.getFieldName()))
            {
                TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
                tourAppScreen.getCurrencyRecord().getField(Currencys.CURRENCY_CODE).addComponent(component1);  // Display local currency code
            }
            else
                this.getFieldList(Currencys.CURRENCYS_FILE).getField(Currencys.CURRENCY_CODE).addComponent(component1);

            JMultiFieldPanel panel = new JMultiFieldPanel(fieldInfo);
            panel.addComponent(component1, false);
            panel.addComponent(component2, true);
            component = panel;
        }
        else
            component = super.createScreenComponent(fieldInfo);
        
        if (fieldInfo.getFieldName().contains("Price"))
            if (component != null)
            	Util.setEnabled(component, false);                

        return component;
    }
    /**
     * Move the data record(s) to the screen controls.
     * This is usually not necessary, used only when a screen is first displayed.
     */
    public void fieldsToControls()
    {
        super.fieldsToControls();
        // Also need to display the local currency code.
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        tourAppScreen.getCurrencyRecord().getField(Currencys.CURRENCY_CODE).displayField();  // Display local currency code
    }
    /**
     * Get the remote session.
     */
    public RemoteSession getRemoteSession(FieldList recBookingDetail)
    {
        if (m_remoteSession == null)
            if (recBookingDetail != null)
        {
            String strDetailType = recBookingDetail.getClass().getName();
            strDetailType = strDetailType.substring(strDetailType.lastIndexOf('.') + 1);
            TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            RemoteSession parentSessionObject = tourAppScreen.getRemoteSession();
            try {
                m_remoteSession = (RemoteSession)parentSessionObject.makeRemoteSession("com.tourapp.tour.booking.remote.booking.detail." + strDetailType + "Session");
            } catch (RemoteException ex)    {
                ex.printStackTrace();
            }
        }
        return m_remoteSession;
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
        {
            String strFieldName = this.getFieldName(iIndex);
            if (strFieldName == null)
                return null;
            if (strFieldName.indexOf('.') == -1)
                return this.getFieldList().getField(strFieldName);
            else
                return this.getFieldList(strFieldName.substring(0, strFieldName.indexOf('.'))).getField(strFieldName.substring(strFieldName.indexOf('.') + 1));
        }
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
     * This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source 
     *      and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getSource() instanceof FieldList)
        {   // Always
            FieldList record = (FieldList)evt.getSource();
            String strFieldName = evt.getPropertyName();
            
            if ((BookingDetail.DETAIL_DATE.equalsIgnoreCase(strFieldName))
                || (BookingDetail.DETAIL_END_DATE.equalsIgnoreCase(strFieldName)))
            {
                this.writeAndRefresh(record);
            }
        }
    }
    /**
     * Write and refresh the record.
     * @param record
     */
    public void writeAndRefresh(FieldList record)
    {
        this.writeAndRefresh(record, true);
    }
    /**
     * Write and refresh the record.
     * @param record
     * @param bRefresh Reread the record
     */
    public void writeAndRefresh(FieldList record, boolean bRefresh)
    {
        if ((record == null)
            || (!record.isModified()))
                return;
        FieldTable fieldTable = record.getTable();
        String strID = record.getField(BookingDetail.ID).toString();
        try   {
            if ((record.getEditMode() == Constants.EDIT_IN_PROGRESS) ||
                (record.getEditMode() == Constants.EDIT_CURRENT))
            {
                fieldTable.set(record);
            }
            else
            {
                fieldTable.add(record);
                strID = fieldTable.getLastModified(0).toString();   // Bookmark handle
            }
            if (!bRefresh)
                return;
            fieldTable.addNew();
            this.resetFocus();
            if ((strID != null)
                && (strID.length() > 0))
            {
                record.getField(BookingDetail.ID).setString(strID);
                String strKeyName = record.getKeyName();
                record.setKeyName(Constants.PRIMARY_KEY);
                boolean bSuccess = record.getTable().seek(null);
                if (bSuccess)
                    this.fieldsToControls();
                record.setKeyName(strKeyName);
            }
        } catch (DBException ex)  {
            ex.printStackTrace();
        }
    }
    /**
     * Invoked when the target of the listener has changed its state.
     * Write and refresh the record when user changes tabs.
     * @param e  a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e)
    {
        FieldList recBookingDetail = this.getFieldList();
        this.writeAndRefresh(recBookingDetail);
    }
    /**
     * Process this action.
     * @param strAction The command to process.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            JBookingDetailMainScreen mainScreen = (JBookingDetailMainScreen)this.getTargetScreen(JBookingDetailMainScreen.class);
            if (!(mainScreen.getSource() instanceof JBookingDetailGridScreen))
            {
                JTabbedPane tabbedPane = (JTabbedPane)JBasePanel.getSubScreen(tourAppScreen, JTabbedPane.class);
                tabbedPane.setSelectedIndex(TourAppScreen.CALENDAR_TAB);
                return true;
            }
        }
        return super.doAction(strAction, iOptions);   // This will handle back correctly
    }
}
