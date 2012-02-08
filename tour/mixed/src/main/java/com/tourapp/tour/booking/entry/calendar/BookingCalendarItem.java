/**
 * @(#)BookingCalendarItem.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.calendar;

import java.util.Date;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import org.jbundle.base.model.MenuConstants;
import org.jbundle.base.screen.model.BaseScreen;
import org.jbundle.base.screen.model.calendar.CalendarRecordItem;
import org.jbundle.model.util.Colors;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.util.calendarpanel.model.CalendarConstants;
import org.jbundle.util.calendarpanel.model.CalendarItem;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.tour.booking.detail.db.BookingDetail;
import com.tourapp.tour.product.base.db.ProductType;

/**
 *  BookingCalendarItem - .
 */
public class BookingCalendarItem extends CalendarRecordItem
     implements CalendarItem
{
    public static Hashtable<String,Integer> m_htcolorHighlight = new Hashtable<String,Integer>();
    static  {
        m_htcolorHighlight.put(ProductType.AIR, 0x00ffc0c0);
        m_htcolorHighlight.put(ProductType.HOTEL, 0x00c0c0ff);
        m_htcolorHighlight.put(ProductType.LAND, 0x00c0ffff);
        m_htcolorHighlight.put(ProductType.CAR, 0x00ffc0ff);
        m_htcolorHighlight.put(ProductType.CRUISE, 0x00c0ffc0);
        m_htcolorHighlight.put(ProductType.TRANSPORTATION, 0x00c0c0c0);
        m_htcolorHighlight.put(ProductType.TOUR, 0x00ffffff);
        m_htcolorHighlight.put(ProductType.ITEM, 0x00e0e0e0);
    };
    protected static ImageIcon m_iconLookup = null;
    protected static ImageIcon m_iconPrice = null;
    protected static ImageIcon m_iconInventory = null;
    protected static ImageIcon m_iconProduct = null;
    /**
     * Default constructor.
     */
    public BookingCalendarItem()
    {
        super();
    }
    /**
     * A class to return a CalendarItem from a record.
     */
    public BookingCalendarItem(BaseScreen gridScreen, int iIconField, int iStartDateTimeField, int iEndDateTimeField, int iDescriptionField, int iStatusField)
    {
        this();
        this.init(gridScreen, iIconField, iStartDateTimeField, iEndDateTimeField, iDescriptionField, iStatusField);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseScreen gridScreen, int iIconField, int iStartDateTimeField, int iEndDateTimeField, int iDescriptionField, int iStatusField)
    {
        super.init(gridScreen, iIconField, iStartDateTimeField, iEndDateTimeField, iDescriptionField, iStatusField);
        if (m_iconLookup == null) // First time only
            m_iconLookup = this.getTask().loadImageIcon(BookingConstants.BUTTON_LOCATION + MenuConstants.LOOKUP, MenuConstants.LOOKUP);
        if (m_iconPrice == null)
            m_iconPrice = this.getTask().loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.COST, BookingConstants.COST);
        if (m_iconInventory == null)
            m_iconInventory = this.getTask().loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INVENTORY, BookingConstants.INVENTORY);
        if (m_iconProduct == null)
            m_iconProduct = this.getTask().loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRODUCT, BookingConstants.PRODUCT);
    }
    /**
     * Get the description.
     * @return The description.
     */
    public String getDescription()
    {
        return this.getBookingDetail().getProductDesc();
    }
    /**
     * Get the start time of this service.
     * @return The start date.
     */
    public Date getStartDate()
    {
        return this.getBookingDetail().getStartDate();
    }
    /**
     * Get the ending time of this service.
     * @return The end date.
     */
    public Date getEndDate()
    {
        return this.getBookingDetail().getEndDate();
    }
    /**
     * Get the icon (opt).
     * @return The icon.
     */
    public ImageIcon getIcon(int iIconType)
    {
        String strIcon = null;
        if (iIconType == CalendarConstants.START_ICON)
            strIcon = this.getBookingDetail().getStartIcon();
        else if (iIconType == CalendarConstants.END_ICON)
            strIcon = this.getBookingDetail().getEndIcon();
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.INFO_LOOKUP)
            return m_iconLookup;
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.COST_LOOKUP)
            return m_iconPrice;
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.INVENTORY_LOOKUP)
            return m_iconInventory;
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.PRODUCT_LOOKUP)
            return m_iconProduct;
        else
            return null;    //??        if (strIcon == null)
        if (strIcon == null)
            return null;
        return this.getTask().loadImageIcon(strIcon, null);
    }
    /**
     * Get the status of this object.
     * @return The status.
     */
    public int getStatus()
    {
        return this.getBookingDetail().getStatus();
    }
    /**
     * Get the meal description on this date.
     * @return The meal description.
     */
    public String getMealDesc(Date date)
    {
        return this.getBookingDetail().getMealDesc(date, false, null);
    }
    /**
     * Highlight color (optional).
     * @return The highlight color (green).
     */
    public int getHighlightColor()
    {
        String strProductType = this.getBookingDetail().getField(BookingDetail.PRODUCT_TYPE).toString();
        int color = Colors.NULL;
        if (strProductType != null)
            color = m_htcolorHighlight.get(strProductType);
        if (color == Colors.NULL)
            color = Colors.GREEN;
        return color;
    }
    /**
     * Get the selection colot.
     */
    public int getSelectColor()
    {
        return Colors.NULL;    // Have the calendar screen figure out a color
    }
    /**
     * Get the Visual Javabean.
     */
    public Object getVisualJavaBean(int iPanelType)
    {
        return null;
    }
    /**
     * Change the start time of this service.
     * @param time The new start date.
     */
    public Date setStartDate(Date date)
    {
        this.editTargetRecord();
        date = this.getBookingDetail().setStartDate(date);
        this.updateTargetRecord();
        return date;
    }
    /**
     * Change the ending time of this service.
     * @param date The new end date.
     */
    public Date setEndDate(Date date)
    {
        this.editTargetRecord();
        date = this.getBookingDetail().setEndDate(date);
        this.updateTargetRecord();
        return date;
    }
    /**
     * Get the detail record.
     */
    public BookingDetail getBookingDetail()
    {
        return (BookingDetail)this.getMainRecord().getTable().getCurrentTable().getRecord();
    }
    /**
     * Utility method to get the (applet) task so I can load a graphic.
     */
    public BaseApplet getTask()
    {
        return (BaseApplet)this.getBookingDetail().getRecordOwner().getTask();
    }

}
