/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.model;

import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.remote.RemoteException;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.cal.popup.ProductTypeInfo;
import org.jbundle.util.calendarpanel.model.CalendarConstants;
import org.jbundle.util.calendarpanel.model.CalendarItem;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;
import com.tourapp.thin.tour.product.base.db.ProductType;

public class BookingDetailCalendarItem extends BookingDetail
    implements CalendarItem   //?, com.tourapp.jfo.product.ProductItem
{
	private static final long serialVersionUID = 1L;

	protected static ImageIcon m_iconLookup = null;
    protected static ImageIcon m_iconPrice = null;
    protected static ImageIcon m_iconInventory = null;
    protected static ImageIcon m_iconProduct = null;
    
    protected AbstractThinTableModel m_model = null;

    /**
     * Constructor.
     */
    public BookingDetailCalendarItem()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingDetailCalendarItem(Object object, AbstractThinTableModel model)
    {
        this();
        this.init(object, model);
    }
    /**
     * Constructor.
     */
    public void init(Object object, AbstractThinTableModel model)
    {
        super.init(object);
        m_model = model;
        if (m_iconLookup == null) // First time only
            m_iconLookup = BaseApplet.getSharedInstance().loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INFO, BookingConstants.INFO);
        if (m_iconPrice == null)
            m_iconPrice = BaseApplet.getSharedInstance().loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRICE, BookingConstants.PRICE);
        if (m_iconInventory == null)
            m_iconInventory = BaseApplet.getSharedInstance().loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INVENTORY, BookingConstants.INVENTORY);
        if (m_iconProduct == null)
            m_iconProduct = BaseApplet.getSharedInstance().loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRODUCT, BookingConstants.PRODUCT);
    }
    /**
     * I'm done with this item, free the resources.
     */
    public void free()
    {
        super.free();
    }
    /**
     * Delete this item.
     */
    public boolean remove()
    {
        boolean bSuccess = false;
        try   {
            if ((this.getEditMode() == Constants.EDIT_IN_PROGRESS)
                || (this.getEditMode() == Constants.EDIT_CURRENT))
            {
                this.getTable().remove();
                m_model.makeRowCurrent(-1, true);      // Clear the current record in the model.
            }
        } catch (Exception ex)  {
            ex.printStackTrace();
            bSuccess = false;
        }
        return bSuccess;
    }
    /**
     * Set the model.
     */
    public void setModel(AbstractThinTableModel model)
    {
        m_model = model;
    }
    /**
     * Make sure I link up to the correct remote table.
     */
    public String getRemoteClassName()
    {
        String strClassName = BookingDetail.class.getName().toString();
        int iThinPos = strClassName.indexOf(Constants.THIN_SUBPACKAGE);
        return strClassName.substring(0, iThinPos) + strClassName.substring(iThinPos + 5);
    }
    /**
     * Get the description.
     */
    public java.lang.String getDescription()
    {
        return this.getField(BookingDetail.DESCRIPTION).toString();
    }
    /**
     * Get the start time of this service.
     */
    public Date getStartDate()
    {
        return (Date)this.getField(BookingDetail.DETAIL_DATE).getData();
    }
    /**
     * Get the ending time of this service.
     */
    public Date getEndDate()
    {
        Date date = (Date)this.getField(BookingDetail.DETAIL_END_DATE).getData();
        if (date == null)
            return this.getStartDate();
        return date;
    }
    /**
     * Get the meal description on this date.
     */
    public String getMealDesc(Date date)
    {
        String strMeals = this.getField(BookingDetail.MEAL_SUMMARY).toString();
        Date dateStart = this.getStartDate();
        if (dateStart == null)  // For a new (last+1) record.
            return Constants.BLANK;
        Calendar calendar = Converter.gCalendar;
        calendar.setTime(dateStart);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateStart = calendar.getTime();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        int iDay = (int)((date.getTime() - dateStart.getTime()) / Constants.KMS_IN_A_DAY);
        for (int iStart = 0; iStart < strMeals.length(); iStart++)
        {
            int iEnd = strMeals.indexOf(Constants.RETURN, iStart);
            if (iEnd == -1)
                iEnd = strMeals.length();
            iDay--;
            if (iDay == -1)
                return strMeals.substring(iStart, iEnd);
            iStart = iEnd + Constants.RETURN.length() - 1;  // Gets bumped +1 on next loop
        }
        return Constants.BLANK;
    }
    /**
     * Get the icon (opt).
     */
    public Object getIcon(int iIconType)
    {
        if (iIconType == CalendarConstants.END_ICON)
            return ProductTypeInfo.getProductType(this.getLocalProductType()).getEndIcon();
        else if (iIconType == CalendarConstants.START_ICON)
            return ProductTypeInfo.getProductType(this.getLocalProductType()).getStartIcon();
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.INFO_LOOKUP)
            return m_iconLookup;
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.COST_LOOKUP)
            return m_iconPrice;
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.INVENTORY_LOOKUP)
            return m_iconInventory;
        else if (iIconType == CalendarConstants.START_ICON + BookingConstants.PRODUCT_LOOKUP)
            return m_iconProduct;
        else
            return null;    //??
    }
    /**
     * Highlight color (optional).
     */
    public int getHighlightColor()
    {
        return ProductTypeInfo.getProductType(this.getLocalProductType()).getHighlightColor();
    }
    /**
     * Highlight color (optional).
     */
    public int getSelectColor()
    {
        return ProductTypeInfo.getProductType(this.getLocalProductType()).getSelectColor();
    }
    /**
     * Change the start time of this service.
     */
    public Date setStartDate(Date time)
    {
        return ((CustSaleDetailThinTableModel)m_model).setNewDates(this, time, null);
    }
    /**
     * Change the ending time of this service.
     */
    public Date setEndDate(Date time)
    {
        return ((CustSaleDetailThinTableModel)m_model).setNewDates(this, null, time);
    }
    /**
     * Usually after a set the record is not known... re-read it!
     */
    public void restoreCurrentRecord() throws Exception
    {
        int iIndex = -1;
        if (m_model != null)
            iIndex = m_model.getCurrentRow();
        if (iIndex != -1)
        {
            m_model.updateIfNewRow(-1);     // Force update
            m_model.makeRowCurrent(iIndex, false);
//x         this.getFieldTable().get(iIndex); // Read this record (Not very efficient)!
        }
    }
    /**
     * Get the product type of this record.
     */
    public String getLocalProductType()
    {
        String strProductType = this.getField(BookingDetail.PRODUCT_TYPE).toString();
        if ((strProductType == null) ||
            (strProductType.length() == 0))
                strProductType = ProductType.ITEM;
        return strProductType;
    }
    /**
     * Set the icon (opt).
     */
    public void setIcon(Object icon, int iIconType)
    {
    }
    /**
     * Get the display window for this object.
     */
    public Object getVisualJavaBean(int iPanelType)
    {
        return null;
    }
    /**
     * Get the status of this object.
     */
    public int getStatus()
    {
        Integer intStatus = (Integer)this.getField(BookingDetail.STATUS_SUMMARY).getData();
        if (intStatus == null)
            return 0;
        return intStatus.intValue();
    }
    /**
     * Set the status of this item.
     */
    public int setStatus(int iStatus)
    {
        this.getField(BookingDetail.STATUS_SUMMARY).setData(new Integer(iStatus));
        return this.getStatus();
    }
    public void setAvailability(int p1) throws java.rmi.RemoteException {
    }
    public double getPrice() throws RemoteException {
        return 0;
    }
    public void setPrice(double p1) throws RemoteException {
    }
    public int getAvailability(int p1) throws RemoteException {
        return 0;
    }
    public java.lang.String getProductType() throws RemoteException {
        return ProductType.HOTEL;
    }
//  public void addCategory(final com.tourapp.jfo.product.SearchableCatalog p1) throws RemoteException {
//  }
    public void setDescriptionURL(java.lang.String p1,int p2) throws RemoteException {
    }
//  public com.tourapp.jfo.product.SearchableCatalog getNextCategory() throws java.rmi.RemoteException {
//      return null;
//  }
    public java.lang.String getDescriptionURL(int p1) throws RemoteException {
        return null;
    }
    public void setDescription(java.lang.String p1) throws RemoteException {
    }
    public void setID(final java.lang.Object p1) throws RemoteException {
    }
    public java.lang.Object getID() throws RemoteException {
        return null;
    }
}
