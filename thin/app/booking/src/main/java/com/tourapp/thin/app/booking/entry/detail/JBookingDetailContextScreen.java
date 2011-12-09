/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.detail;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jbundle.model.DBException;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JScreenConstants;
import org.jbundle.thin.base.screen.cal.grid.CalendarThinTableModel;
import org.jbundle.thin.base.screen.util.JMultiFieldPanel;
import org.jbundle.thin.base.screen.util.cal.JCalendarDualField;
import org.jbundle.thin.base.util.ThinUtil;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.model.BookingDetailCalendarItem;
import com.tourapp.thin.app.booking.entry.search.JBaseRichScreen;
import com.tourapp.thin.tour.assetdr.db.Currencys;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingDetailContextScreen extends JBaseRichScreen
    implements PropertyChangeListener
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingDetailContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingDetailContextScreen(Object parent, Object obj)
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
        
        this.getFieldList().addPropertyChangeListener(this);    // Only respond to propery changes when this record is connected to a grid
    }
    /*
     * Free the resources held by this object.
     * Besides freeing all the sub-screens, this method disconnects all of my
     * fields from their controls.
     */
    public void free()
    {
        if (m_vFieldListList != null)
        {
            for (int i = m_vFieldListList.size() - 1; i >= 0; i--)
            {   // Step 1 - Disconnect the controls from the fields
                FieldList fieldList = this.getFieldList(i);
                if (fieldList != null)
                {
                    this.disconnectControls(fieldList);
                    if (fieldList.getOwner() == this)
                        fieldList.free();
                }
            }
            if (m_vFieldListList != null)
                m_vFieldListList.clear();   // Note JBaseField.free() frees all the field lists
            m_vFieldListList = null;
        }
        // Remember to disconnect these.
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        this.disconnectControls(tourAppScreen.getCurrencyRecord());
        super.free();
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return new BookingDetail(null);
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        FieldList fieldList = this.getFieldList();
        switch (iIndex)
        {
            case 0:
                return fieldList.getField(BookingDetail.DESCRIPTION);
            case 1:
                FieldInfo converter = fieldList.getField(BookingDetail.STATUS_SUMMARY);
                if (converter == null)
                    converter = SKIP_THIS_FIELD;
                return converter;
            case 2:
                return fieldList.getField(BookingDetail.TOTAL_PRICE_LOCAL);
            case 3:
                return fieldList.getField(BookingDetail.DETAIL_DATE);
            case 4:
                return fieldList.getField(BookingDetail.DETAIL_END_DATE);
            case 5:
//                return fieldList.getFieldInfo(BookingDetail.TOTAL_COST);
        }
        return null;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        BaseApplet applet = this.getBaseApplet();
        if (fieldInfo.getFieldName().equals(BookingDetail.STATUS_SUMMARY))
        {
            return this.addStatusComponent(fieldInfo, applet, this.getProductType());
        }
        else if (fieldInfo.getFieldName().equals(BookingDetail.DESCRIPTION))
        {
            JComponent component1 = super.createScreenComponent(fieldInfo);
            ((JTextArea)component1).setRows(1);
            ThinUtil.setEnabled(component1, false);

            JButton component2 = new JButton(applet.loadImageIcon(Constants.FORM, Constants.FORM));
            component2.setMargin(JScreenConstants.NO_INSETS);
            component2.addActionListener(this);   // Requery catalog on change

            JMultiFieldPanel panel = new JMultiFieldPanel(fieldInfo, component1, component2);
            
            return panel;
        }
        else if (fieldInfo.getFieldName().equals(BookingDetail.DETAIL_DATE))
        {
            return new JCalendarDualField(fieldInfo, true, true);
        }
        else if (fieldInfo.getFieldName().equals(BookingDetail.DETAIL_END_DATE))
        {
            return new JCalendarDualField(fieldInfo, true, true);
        }
        else if ((fieldInfo.getField().getDataClass() == Double.class) && (!fieldInfo.getFieldName().contains("rice")))
        {
            JComponent component2 = super.createScreenComponent(fieldInfo);
            fieldInfo.addComponent(component2);
            ThinUtil.setEnabled(component2, false);
            JComponent component1 = new JTextField(Constants.BLANK, 3);
            ThinUtil.setEnabled(component1, false);
            if (BookingDetail.TOTAL_COST_LOCAL.equalsIgnoreCase(fieldInfo.getFieldName()))
            {
                TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
                tourAppScreen.getCurrencyRecord().getField(Currencys.CURRENCY_CODE).addComponent(component1);  // Display local currency code
            }
            else
                this.getFieldList().getField(BookingDetail.CURRENCY_CODE).addComponent(component1);

            JMultiFieldPanel panel = new JMultiFieldPanel(fieldInfo);
            panel.addComponent(component1, false);
            panel.addComponent(component2, true);
            return panel;
        }
        return super.createScreenComponent(fieldInfo);
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
     * Add the description labels to the first column of the grid.
     */
    public JComponent addScreenLabel(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(BookingDetail.DETAIL_DATE))
        {
            c.gridy = 0;
            c.gridx = 4;                            // Column 3
        }
        return super.addScreenLabel(parent, fieldInfo);
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent addScreenControl(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(BookingDetail.DETAIL_DATE))
        {
            c.gridy = 0;
            c.gridx = 5;                            // Column 4
        }
        return super.addScreenControl(parent, fieldInfo);
    }
    /**
     * A field changed on this screen.
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        String strFieldName = evt.getPropertyName();
        FieldList fieldList = this.getFieldList();
        FieldInfo field = fieldList.getField(strFieldName);
        if (field != null)
        {
            if ((BookingDetail.DETAIL_DATE.equals(field.getFieldName()))
                || (BookingDetail.DETAIL_END_DATE.equals(field.getFieldName())))
            {
                TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
                
                CalendarThinTableModel calendarModel = (CalendarThinTableModel)tourAppScreen.getCalendarModel();
                int iRowIndex = calendarModel.getSelectedRow();
                BookingDetailCalendarItem item = (BookingDetailCalendarItem)calendarModel.makeRowCurrent(iRowIndex, false);
                if (fieldList.getField(BookingDetail.ID).getData().equals(item.getField(BookingDetail.ID).getData()))
                {
                    item = (BookingDetailCalendarItem)calendarModel.makeRowCurrent(iRowIndex, true);
                    FieldInfo fieldCalendar = item.getField(field.getFieldName());
                    fieldCalendar.setData(field.getData());
                    try {
                        calendarModel.cacheCurrentLockedData(iRowIndex, fieldList);
                        calendarModel.updateIfNewRow(-1);
                    } catch (DBException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
