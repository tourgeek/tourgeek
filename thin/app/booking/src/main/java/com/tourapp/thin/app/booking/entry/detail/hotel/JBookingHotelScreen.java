/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.detail.hotel;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.util.JBitMaskField;
import org.jbundle.thin.base.screen.util.JRemoteComboBox;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.detail.JBaseBookingDetailScreen;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;
import com.tourapp.thin.tour.booking.detail.db.BookingHotel;
import com.tourapp.thin.tour.product.base.db.MealPlan;
import com.tourapp.thin.tour.product.hotel.db.Hotel;
import com.tourapp.thin.tour.product.hotel.db.HotelClass;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingHotelScreen extends JBaseBookingDetailScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingHotelScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingHotelScreen(Object parent,Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * Constructor.
     */
    public void init(Object parent, Object obj)
    {
        super.init(parent, obj);
    }
    /**
     * Get the product record.
     * Override this to supply the correct product record.
     * @return The product record.
     */
    public FieldList getProductRecord()
    {
        return new Hotel(this);
    }
    /**
     * Free.
     */
    public void free()
    {
        if (this.getFieldList(MealPlan.MEAL_PLAN_FILE) != null)
        {   // Since the meal plan popup record is shared, I need to free it then disconnect it from the controls.
            this.getFieldList(MealPlan.MEAL_PLAN_FILE).free();
            FieldList fieldList = this.getFieldList();
            for (int iFieldSeq = 0; iFieldSeq < fieldList.getFieldCount(); iFieldSeq++)
            {
                FieldInfo field = fieldList.getField(iFieldSeq);
                Component component = null;
                int iIndex = 0;
                while ((component = (Component)field.getComponent(iIndex)) != null)
                {
                    if (component instanceof JRemoteComboBox)
                        if (((JRemoteComboBox)component).getRecord() == this.getFieldList(MealPlan.MEAL_PLAN_FILE))
                            ((JRemoteComboBox)component).setRecord(null);
                    iIndex++; // Bump counter
                }
            }
            this.removeFieldList(this.getFieldList(MealPlan.MEAL_PLAN_FILE));
        }
        super.free();
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return new BookingHotel(this);  // If overriding class didn't set this
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
        BaseApplet applet = this.getBaseApplet();
        
        if (fieldInfo.getFieldName().equals(BookingDetail.CLASS_ID))
        {
            HotelClass recHotelClass = new HotelClass(this);
            component = new JRemoteComboBox(applet, this.getRemoteSession(null), recHotelClass, applet.getString(BookingConstants.HOTEL_CLASS), HotelClass.DESCRIPTION, BookingConstants.HOTEL_CLASS, true, HotelClass.ID, null);
        }
        else if (fieldInfo.getFieldName().startsWith("MealPlan"))
        {
            if (fieldInfo.getFieldName().endsWith(Params.ID))
            {
                if (this.getFieldList(MealPlan.MEAL_PLAN_FILE) == null)
                    this.addFieldList(new MealPlan(this));     // Everyone shares the same copy
                component = new JRemoteComboBox(applet, this.getRemoteSession(null), this.getFieldList(MealPlan.MEAL_PLAN_FILE), applet.getString(BookingConstants.MEAL_PLAN), MealPlan.DESCRIPTION, BookingConstants.MEAL_PLAN, true, MealPlan.ID, null);
            }
            else // if (fieldInfo.getFieldName().endsWith("Days"))
            {
                component = new JBitMaskField(5, false);
            }
        }
        else
            component = super.createScreenComponent(fieldInfo);

        return component;
    }
    /**
     * Add the description labels to the first column of the grid.
     */
    public JComponent addScreenLabel(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(BookingHotel.SINGLE_COST))
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
        if (fieldInfo.getFieldName().equals(BookingHotel.SINGLE_COST))
        {
            c.gridy = 0;
            c.gridx = 5;                            // Column 4
        }
        return super.addScreenControl(parent, fieldInfo);
    }
    /**
     * Get the field name at this location on the screen.
     * @param iScreenSeq The screen sequence to get the field name from.
     * @return The field name at this screen location.
     */
    public String getFieldName(int iScreenSeq)
    {
        if (iScreenSeq >= m_rgFieldNames.length)
            return null;
        return m_rgFieldNames[iScreenSeq];
    }
    /**
     * The fields in screen order.
     */
    public static String[] m_rgFieldNames = {
        BookingDetail.DESCRIPTION,
        BookingDetail.DETAIL_DATE,
        BookingHotel.NIGHTS,
        BookingDetail.CLASS_ID,
        BookingHotel.MEAL_PLAN_1ID,
        BookingHotel.MEAL_PLAN_1_DAYS,
        BookingHotel.MEAL_PLAN_2ID,
        BookingHotel.MEAL_PLAN_2_DAYS,
        BookingHotel.MEAL_PLAN_3ID,
        BookingHotel.MEAL_PLAN_3_DAYS,
        BookingHotel.MEAL_PLAN_4ID,
        BookingHotel.MEAL_PLAN_4_DAYS,
        BookingDetail.TOTAL_PRICE_LOCAL,
//        Currencys.CURRENCYS_FILE + '.' + Currencys.DESCRIPTION,
    };
    /**
     * This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source 
     *      and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        super.propertyChange(evt);

        if (evt.getSource() instanceof FieldList)
        {   // Always
            FieldList record = (FieldList)evt.getSource();
            String strFieldName = evt.getPropertyName();
            
            if (BookingHotel.NIGHTS.equalsIgnoreCase(strFieldName))
            {
                this.writeAndRefresh(record);
            }
        }
    }
}
