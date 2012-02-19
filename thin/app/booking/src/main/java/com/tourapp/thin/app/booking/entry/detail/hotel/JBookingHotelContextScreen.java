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
import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import java.util.Date;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;

import com.tourapp.thin.app.booking.entry.detail.JBookingDetailContextScreen;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;
import com.tourapp.thin.tour.booking.detail.db.BookingHotel;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingHotelContextScreen extends JBookingDetailContextScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingHotelContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingHotelContextScreen(Object parent,Object obj)
    {
        this();
        this.init(parent, obj);
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
            case 5:
                return fieldList.getField(BookingHotel.NIGHTS);
        }
        return super.getFieldForScreen(iIndex);
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
            if (BookingHotel.NIGHTS.equals(field.getFieldName()))
            {
                Short shNights = (Short)field.getData();
                Date date = (Date)fieldList.getField(BookingDetail.DETAIL_DATE).getData();
                if (date != null)
                    if (shNights != null)
                {
                    Converter.initGlobals();
                    Calendar calendar = Converter.gCalendar;
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, shNights.shortValue());
                    fieldList.getField(BookingDetail.DETAIL_END_DATE).setData(calendar.getTime());
                }
            }
        }
        super.propertyChange(evt);
    }
}
