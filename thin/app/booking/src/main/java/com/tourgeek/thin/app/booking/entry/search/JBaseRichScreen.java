/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.search;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.LinkedConverter;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JScreen;
import org.jbundle.thin.base.screen.JScreenConstants;
import org.jbundle.thin.base.screen.db.converter.ImageConverter;
import org.jbundle.thin.base.screen.db.converter.SecondaryRecordConverter;
import org.jbundle.thin.base.screen.grid.JCellImage;
import org.jbundle.thin.base.screen.landf.ScreenUtil;
import org.jbundle.thin.base.screen.util.JBlinkLabel;
import org.jbundle.thin.base.screen.util.JDescTextField;
import org.jbundle.thin.base.screen.util.JFSTextField;
import org.jbundle.thin.base.screen.util.JMultiFieldPanel;
import org.jbundle.thin.base.screen.util.JRemoteComboBox;
import org.jbundle.thin.base.util.ThinUtil;

import com.tourgeek.thin.app.booking.entry.BookingConstants;
import com.tourgeek.thin.app.booking.entry.TourGeekScreen;
import com.tourgeek.thin.tour.booking.detail.db.BookingDetail;

/**
* Base JScreen with support for a bunch of cool controls.
 */
public class JBaseRichScreen extends JScreen
    implements PropertyChangeListener
{
	private static final long serialVersionUID = 1L;
	public static final String DATE_BUTTON = "DateButton";
    public static final String DATE_FIELD = "DateField";
    public static final String CALENDAR = "Calendar";

    /**
     *  Parent search panel.
     */
    public JBaseRichScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBaseRichScreen(Object obj, BaseApplet applet)
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
        super.init(null, null);

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
    /**
     * Display the date entry dialog and notify the action listeners of the change (and value).
     * @param applet The applet.
     * @param action The action to add the date change listener to.
     * @return The date or null if cancel was pressed (JOptionPane.CANCEL_OPTION)
     */
    public Date enterDateDialog(BaseApplet applet, Action action)
    {
    	JComponent panel = this.buildDateEdit(applet);
        JTextField tfDate = (JTextField)this.getComponentByName(JDisplayPanel.DATE_FIELD, panel);
        DateChangeListener dateListener = new DateChangeListener(tfDate);
        action.addPropertyChangeListener(dateListener);
        String strTitle = applet.getString("dateWarning");
        int iOption = JOptionPane.showConfirmDialog(this, panel, strTitle, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        action.removePropertyChangeListener(dateListener);
        if (iOption != JOptionPane.CANCEL_OPTION)
        	return (Date)action.getValue(SearchConstants.DATE);
        else
        	return null;	// Cancel
    }
    /**
    * Change the property on date change
    */
   class DateChangeListener extends Object
       implements PropertyChangeListener
   {
       protected JTextField m_tfDate = null;

       public DateChangeListener(JTextField tfDate)
       {
           super();
           m_tfDate = tfDate;
       }
       /** This method gets called when a bound property is changed.
        * @param evt A PropertyChangeEvent object describing the event source
        *    and the property that has changed.
        */
       public void propertyChange(PropertyChangeEvent evt)
       {
           if (SearchConstants.DATE.equalsIgnoreCase(evt.getPropertyName()))
           {   // Somewhere, the user entered/clicked a date
               String strDate = Constants.BLANK;
               Date dateTarget = (Date)evt.getNewValue();
               Converter.initGlobals();
               if (dateTarget != null)
               {
                   synchronized (Converter.gCalendar)
                   {
                       strDate = Converter.gDateTimeFormat.format(dateTarget);
                   }
               }
               m_tfDate.setText(strDate);
               Container parent = m_tfDate.getParent();
               while (parent != null)
               {
                   if (parent instanceof JOptionPane)
                   {
                       ((JOptionPane)parent).setValue(JOptionPane.OK_OPTION);
                       parent.setVisible(false);
                       break;
                   }
                   parent = parent.getParent();
               }
           }
       }
   }
    /**
     *  Set up the date entry field (and button for popup).
     */
    public JComponent buildDateEdit(BaseApplet applet)
    {
        JPanel panelDate = new JPanel();
        panelDate.setOpaque(false);
        panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.X_AXIS));
        
        JTextField tfDate = new JDescTextField(15, applet.getString(SearchConstants.DATE), this);      // Share this with the one in the other folder
        tfDate.setMaximumSize(new Dimension(200, 20));  // HACK
        tfDate.setName(DATE_FIELD);
        panelDate.add(tfDate);

        JButton buttonDate = new JButton(applet.loadImageIcon(CALENDAR, CALENDAR));
        buttonDate.setToolTipText(applet.getString("CalendarTip"));
        buttonDate.setMargin(JScreenConstants.NO_INSETS);
        buttonDate.setPreferredSize(SearchConstants.DEFAULT_BUTTON_SIZE);
        buttonDate.setName(DATE_BUTTON);
        panelDate.add(buttonDate);
        buttonDate.setAlignmentX(LEFT_ALIGNMENT);
        buttonDate.setAlignmentY(TOP_ALIGNMENT);
        buttonDate.addActionListener(this);     // To display popup
        
        tfDate.addFocusListener(new FocusAdapter()
        { // Make sure a tab with a changed field triggers date field validation.
            String m_strOldValue;
            public void focusGained(FocusEvent evt)
            {
                m_strOldValue = ((JTextField)evt.getSource()).getText();
                super.focusGained(evt);
            }
            public void focusLost(FocusEvent evt)
            {
                super.focusLost(evt);
                if (!m_strOldValue.equalsIgnoreCase(((JTextField)evt.getSource()).getText()))
                    actionPerformed(new ActionEvent(evt.getSource(), evt.getID(), null));
            }
        });
        return panelDate;
    }
    /**
     * Build a text edit field.
     */
    public JComponent buildTextEdit(BaseApplet applet, RemoteSession remoteSession, String strDesc, String strName)
    {
        JTextField text = new JDescTextField(10, strDesc, this);
        text.setMaximumSize(new Dimension(200, 20));    // HACK
        Object objValue = this.getMainProperty(strName);
        if (objValue != null)
            text.setText(objValue.toString());
        text.setName(strName);
        return text;
    }
    /**
     * Build a popup box using a remote fieldlist.
     */
    public JComboBox buildLinkedPopup(BaseApplet applet, RemoteSession remoteSession, FieldList record, String strDesc, String strFieldName, String strComponentName, String strIndexField, String strKeyIndex)
    {
        JRemoteComboBox box = new JRemoteComboBox(applet, remoteSession, record, strDesc, strFieldName, strComponentName, true, strIndexField, strKeyIndex);
        box.setMaximumSize(new Dimension(200, 20));    // HACK
        String strValue = (String)this.getMainProperty(strComponentName);
        if (strValue != null)
            box.setControlValue(strValue);
        box.addActionListener(this);    // Requery catalog on change
        return box;
    }
    /**
     * This is a utility method to set up a icon/description table display.
     * @param record
     * @param fieldInfo
     * @param strIndexName
     * @param strIconFieldName
     * @param strDescFieldName
     * @return
     */
    public JComponent addSecondaryIconComponent(FieldList record, Converter fieldInfo, String strIndexName, String strIconFieldName, String strDescFieldName)
    {
        this.getTourGeekScreen().linkRemoteSessionTable(null, record, false);
        boolean bCacheTable = false;

        Converter fieldInfo2 = new SecondaryRecordConverter(fieldInfo, null, record, strIconFieldName, bCacheTable, strIndexName, null, "1");
        fieldInfo2 = new ImageConverter(fieldInfo2);  // Add this the first time.
        JComponent component1 = new JCellImage(fieldInfo2);
        component1.setBorder(null);
        if (strDescFieldName == null)
            return component1;

//      Typically, you don't want another 2ndary, but since this is a memory table who cares.
        fieldInfo2 = new SecondaryRecordConverter(fieldInfo, null, record, strDescFieldName, bCacheTable, strIndexName, null, "1");
        JComponent component2 = new JFSTextField(fieldInfo2);
        ScreenUtil.setEnabled(component2, false);

        JComponent component = new JMultiFieldPanel(fieldInfo, component1, component2);
        return component;
    }
    /**
     * Add the booking detail item status component.
     * @return The component
     */
    public JComponent addStatusComponent(Converter fieldInfo, BaseApplet applet, String strProductType)
    {
        JBlinkLabel component1 = new JBlinkLabel(null);
        component1.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + strProductType), 0);
        component1.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INFO), BookingConstants.INFO_LOOKUP);
        component1.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRICE), BookingConstants.COST_LOOKUP);
        component1.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INVENTORY), BookingConstants.INVENTORY_LOOKUP);
        component1.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRODUCT), BookingConstants.PRODUCT_LOOKUP);
        
        this.stringSetup(); // Make sure local strings are set.
        StatusConverter converter = new StatusConverter(fieldInfo);
        JFSTextField component2 = new JFSTextField(converter);
        ScreenUtil.setEnabled(component2, false);
        
        JMultiFieldPanel panel = new JMultiFieldPanel(fieldInfo, component1, component2);
        
        return panel;        
    }
    /**
     * Add the currency and amount to the JPanel.
     */
    public JPanel addCurrencyAmount(Converter fieldInfo, Converter fldCurrency, JPanel panel)
    {
        if (panel == null)
        {
            panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        }

        JComponent textField = new JTextField(Constants.BLANK, 3);
        ScreenUtil.setEnabled(textField, false);
        fldCurrency.addComponent(textField);
        panel.add(textField);

        JComponent labelDesc = super.createScreenComponent(fieldInfo);
        ScreenUtil.setEnabled(labelDesc, false);
        fieldInfo.addComponent(labelDesc);
        panel.add(labelDesc);

        return panel;
    }
    /**
     * Get this property from the main screen.
     */
    public Object getMainProperty(String strKey)
    {
        return this.getTourGeekScreen().getParams().get(strKey);
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        JComponent button = (JComponent)evt.getSource();
        String strButtonName = button.getName();
        if (DATE_BUTTON.equals(strButtonName))
        {   // Create and display the Location Window.
            Date date = (Date)this.getTourGeekScreen().getParams().get(SearchConstants.DATE);
            JComponent cal = org.jbundle.util.jcalendarbutton.JCalendarPopup.createCalendarPopup(date, button);
            cal.addPropertyChangeListener(this.getTourGeekScreen().getParams());
        }
        else if (DATE_FIELD.equals(strButtonName))
        {
            String strString = ((JTextField)button).getText();
            Date date = null;
            Converter.initGlobals();
            synchronized (Converter.gCalendar)
            {
                try   {
                    date = Converter.gDateTimeFormat.parse(strString);
                } catch (ParseException ex)   {
                    try   {
                        date = Converter.gDateFormat.parse(strString);
                    } catch (ParseException ex2)    {
                        date = null;
                        // continue with the next parse
                    }
                }
            }
            PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(button, SearchConstants.DATE, null, date);
            this.getTourGeekScreen().getParams().propertyChange(propChangeEvent);
        }
        else
            super.actionPerformed(evt);
    }
    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *    and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (SearchConstants.DATE_DISPLAY.equalsIgnoreCase(evt.getPropertyName()))
        {   // Display this target date (on a mouse flyover or exit)
            String strDate = Constants.BLANK;
            Date dateTarget = (Date)evt.getNewValue();
            if (dateTarget == null)     // mouseexit - Set to current value
                dateTarget = (Date)this.getTourGeekScreen().getParams().get(SearchConstants.DATE);
            Converter.initGlobals();
            if (dateTarget != null)
            {
                synchronized (Converter.gCalendar)
                {
                    strDate = Converter.gDateTimeFormat.format(dateTarget);
                }
            }
            JTextField tfDate = (JTextField)this.getComponentByName(DATE_FIELD);
            if (tfDate != null)
                tfDate.setText(strDate);
        }
        else if (SearchConstants.DATE.equalsIgnoreCase(evt.getPropertyName()))
        {   // Somewhere, the user entered/clicked a date
            String strDate = Constants.BLANK;
            Date dateTarget = (Date)evt.getNewValue();
            Converter.initGlobals();
            if (dateTarget != null)
                strDate = Converter.gDateTimeFormat.format(dateTarget);
            JTextField tfDate = (JTextField)this.getComponentByName(DATE_FIELD);
            if (tfDate != null)
                tfDate.setText(strDate);
        }
    }
    /**
     * Get the the base applet that is the parent of this screen.
     */
    public TourGeekScreen getTourGeekScreen()
    {
        return (TourGeekScreen)this.getTargetScreen(TourGeekScreen.class);
    }
    /**
    *
    */
   public void stringSetup()
   {
       if (PRICE != null)
           return;
       BaseApplet applet = this.getBaseApplet();
       PRICE = applet.getString(BookingConstants.PRICE);
       INVENTORY = applet.getString(BookingConstants.INVENTORY);
       INFO = applet.getString(BookingConstants.INFO);
       PRODUCT = applet.getString(BookingConstants.INFO);
       VALID = applet.getString("Information valid");
       PENDING = applet.getString("pending");
   }
   public String PRICE = null;
   public String INVENTORY = null;
   public String INFO = null;
   public String PRODUCT = null;
   public String VALID = null;
   public String PENDING = null;
   /**
    * Convert the status to status text.
    */
   class StatusConverter extends LinkedConverter
   {
       /**
        * Constructor.
        */
       public StatusConverter()
       {
           super();
       }
       /**
        * Constructor.
        */
       public StatusConverter(Converter converter)
       {
           this();
           this.init(converter);
       }
       /**
        * Constructor.
        */
       public void init(Converter converter)
       {
           super.init(converter);
       }
       /**
        * Get the binary image of this field's data.
        * @return The raw data.
        */
       public Object getData() 
       { // Move raw data from this field
           Object data = super.getData();
           if (data instanceof Integer)
           {   // Always
               int iStatusType = ((Integer)data).intValue();
               String strData = Constants.BLANK;
//               int x = (iStatusType & 1 << BookingConstants.INFO_LOOKUP);
               if ((iStatusType & 1 << BookingConstants.INFO_LOOKUP) != 0)
                   strData += INFO + ' ';
               if ((iStatusType & 1 << BookingConstants.COST_LOOKUP) != 0)
                   strData += PRICE + ' ';
               if ((iStatusType & 1 << BookingConstants.INVENTORY_LOOKUP) != 0)
                   strData += INVENTORY + ' ';
               if ((iStatusType & 1 << BookingConstants.PRODUCT_LOOKUP) != 0)
                   strData += PRODUCT + ' ';
               if (!Constants.BLANK.equals(strData))
                   strData += PENDING;
               else
                   strData = VALID;
               data = strData;
           }
           return data;
       }
   }
   /**
    * Get the product type for an item displayed on this screen.
    * @return The product type.
    */
   public String getProductType()
   {
       String strProductType = this.getFieldList().getField(BookingDetail.PRODUCT_TYPE).toString();
       if ((strProductType == null) ||
           (strProductType.length() == 0))
       {
           String strClass = this.getClass().getName();
           int iIndex = strClass.lastIndexOf("ContextScreen");
           if (iIndex == -1)
               iIndex = strClass.lastIndexOf("Screen");
           if (iIndex != -1)
           {
               int iFirstIndex = strClass.lastIndexOf("JBooking", iIndex);
               if (iFirstIndex != -1)
                   strProductType = strClass.substring(iFirstIndex + "JBooking".length(), iIndex);
           }
       }
       if ((strProductType == null) ||
           (strProductType.length() == 0))
               strProductType = "Item";
       return strProductType;
   }
}
