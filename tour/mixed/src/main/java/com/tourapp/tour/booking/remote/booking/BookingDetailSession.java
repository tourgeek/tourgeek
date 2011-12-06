/**
 * @(#)BookingDetailSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.base.remote.opt.*;
import org.jbundle.thin.base.remote.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import java.text.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.base.remote.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.remote.*;

/**
 *  BookingDetailSession - .
 */
public class BookingDetailSession extends TableModelSession
{
    protected ProductSession m_productSession = null;
    /**
     * Default constructor.
     */
    public BookingDetailSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingDetailSession Method.
     */
    public BookingDetailSession(BaseSession parentSessionObject, Record record, Object objectID, ProductSession productSession) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID, productSession);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Object objectID, ProductSession productSession)
    {
        m_productSession = null;
        m_productSession = productSession;
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
        recTour.setOpenMode(recTour.getOpenMode() & ~DBConstants.OPEN_READ_ONLY);    // Need to be able to change this!
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getRecord(BookingDetail.kBookingDetailFile).setupRecordListener(this, true, true);
    }
    /**
     * Override this to do an action sent from the client.
     * @param strCommand The command to execute
     * @param properties The properties for the command
     * @returns Object Return a Boolean.TRUE for success, Boolean.FALSE for failure.
     */
    public Object doRemoteCommand(String strCommand, Map<String,Object> properties) throws RemoteException, DBException
    {
        if (SearchConstants.ADD_COMMAND.equalsIgnoreCase(strCommand))
        {
            return this.doAddAction(properties);
        }
        return super.doRemoteCommand(strCommand, properties);
    }
    /**
     * Do the add product action.
     */
    public Object doAddAction(Map<String,Object> properties) throws RemoteException, DBException
    {
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
        
        String strProductType = (String)properties.get(SearchConstants.PRODUCT_TYPE);
        Object objProductID = properties.get(Constants.OBJECT_ID);
        Date date = (Date)Utility.getAs(properties, SearchConstants.DATE, Date.class);
        Object objectID = null;
        
        boolean bAddDetail = true;
        if (ProductType.TOUR.equals(strProductType))
        {   // Tour Header
            bAddDetail = false; // This is (probably) the tour header
            if ((recTourHeader.getEditMode() == DBConstants.EDIT_ADD) || (recTourHeader.getEditMode() == DBConstants.EDIT_NONE))
            {       // Should not be set yet
                try {
                    // Note there is a special case... If the detail session just wants me to create a blank booking, it passes a blank tourID.
                    if ((!(objProductID instanceof String)) || (!Constants.BLANK.equalsIgnoreCase((String)objProductID)))
                        if (recTourHeader.setHandle(objProductID, DBConstants.OBJECT_ID_HANDLE) != null)
                    {
                        int iHeaderTourType = (int)this.getRecord(BookingControl.kBookingControlFile).getField(BookingControl.kTourHeaderTourType).getValue();
                        TourTypeField fldTourType = (TourTypeField)(RecordReferenceField)recTourHeader.getField(TourHeader.kTourType);
                        int iTourTypeMask = fldTourType.getBitsToCheck();
                        if ((iHeaderTourType & (int)recTourHeader.getField(TourHeader.kTourType).getValue() & iTourTypeMask) == 0)
                            bAddDetail = true;  // This is a module
                    }
                    if (bAddDetail == false)
                    {   // Set up the tour header (and the booking)
                        if (date != null)
                        {
                            ((DateField)recTour.getField(Tour.kDepartureDate)).setDate(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                            if (recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                                recBooking.writeAndRefresh();
                        }
                    }
                } catch (DBException ex)    {
                    ex.printStackTrace();
                    throw new RemoteException(ex.getMessage());
                }
            }
            else
            {
                throw new RemoteException("Can't select a tour header, when you already have one selected");
            }
        }
        objectID = recBooking.getCounterField().getData();
        
        if (bAddDetail)
        {   // Product detail
            int errorCode = this.addDetailProduct(properties);
            if (errorCode != DBConstants.NORMAL_RETURN)
                throw new RemoteException(this.getTask().getLastError(errorCode));
            if (objectID == null) // New booking
                objectID = recBooking.getCounterField().getData();
            else
                objectID = Boolean.TRUE;    // Success
        }
        return objectID;
    }
    /**
     * AddDetailProduct Method.
     */
    public int addDetailProduct(Map<String,Object> properties)
    {
        String strProductType = (String)properties.get(SearchConstants.PRODUCT_TYPE);
        Object objProductID = properties.get(Constants.OBJECT_ID);
        Date date = (Date)Utility.getAs(properties, SearchConstants.DATE, Date.class);
        
        BookingDetail recBookingDetail = (BookingDetail)this.getRecord(BookingDetail.kBookingDetailFile);
        try {
            ProductType recProductType = (ProductType)((ReferenceField)recBookingDetail.getField(BookingDetail.kProductTypeID)).getReferenceRecord();
            int iProductType = recProductType.getProductTypeIDFromName(strProductType);
            if (iProductType == -1)
                return this.getTask().setLastError("Unknown product type"); //Never
            int iOldOpenMode = recBookingDetail.getOpenMode();
            recBookingDetail.setOpenMode(recBookingDetail.getOpenMode() & ~DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);  // Turn this off for a sec.
            recBookingDetail.getField(BookingDetail.kProductTypeID).setValue(iProductType);
            recBookingDetail.addNew();
            recBookingDetail.setOpenMode(iOldOpenMode);
            recBookingDetail = (BookingDetail)recBookingDetail.getTable().getCurrentTable().getRecord();
        
            recBookingDetail.setDetailProductInfo(properties, null, null, null, null, null, null);
        
            ((DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate)).setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            recBookingDetail.getField(BookingDetail.kProductID).setString(objProductID.toString());
        
            if (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
                recBookingDetail.add(); // Never
            else
                recBookingDetail.set(); // Maybe
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Get the ID of this object. For example, if object was an employee you might return 'EmployeeID=43332'.
     * This is a server defined string, so supply whatever it takes for you to find this object.
     * <P><B>NOTE: Make sure you return an object that implements Serializable (like String).</B></P>.
     */
    public Object getID() throws RemoteException
    {
        return null;    // Add code.
    }
    /**
     * GetQuantity Method.
     */
    public int getQuantity() throws RemoteException
    {
        return 0; // Add code
    }
    /**
     * Get the inventory.
     */
    public int getAvailability(int iQuantiry) throws RemoteException
    {
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        boolean bInventory = recCustSaleDetail.getField(BookingDetail.kInventoryStatusID).getState();
        return -1;
    }
    /**
     * Get the product end date.
     */
    public Date getEndDate() throws RemoteException
    {
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        return recCustSaleDetail.getEndDate();
    }
    /**
     * Get the item start date.
     */
    public Date getStartDate() throws RemoteException
    {
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        Date date = recCustSaleDetail.getStartDate();
        return date;
    }
    /**
     * Get the meal description for this date.
     */
    public String getMealDesc(Date date) throws RemoteException
    {
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        Record recMealPlan = recCustSaleDetail.getRecordOwner().getRecord(MealPlan.kMealPlanFile);
        return recCustSaleDetail.getMealDesc(date, false, recMealPlan);
    }
    /**
     * Get the status of this item.
     */
    public int getStatus() throws RemoteException
    {
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        return recCustSaleDetail.getStatus();
    }
    /**
     * Get the price.
     */
    public double getPrice() throws RemoteException
    {
        double dPrice = 0;
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        
        Properties properties = new Properties();
        int iMode = 0;
        
        String strMessageCode = null;//???recCustSaleDetail.getMessageRequestCode(BookingDetail.kCostStatusID);
        properties.setProperty(TrxMessageHeader.MESSAGE_RESPONSE_CLASS, strMessageCode);
        properties.setProperty(DBParams.RECORD, recCustSaleDetail.getTableNames(false));
        try   {
            properties.setProperty(DBConstants.OBJECT_ID, recCustSaleDetail.getHandle(DBConstants.OBJECT_ID_HANDLE).toString());
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        try   {
            // Don't need to lock, setRecordCurrent did it already!
            int iStatus = (int)recCustSaleDetail.getField(BookingDetail.kCostStatusID).getValue();;
            dPrice = recCustSaleDetail.getField(BookingDetail.kTotalCostLocal).getValue();
            if (recCustSaleDetail.isModified())
                recCustSaleDetail.set();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        
        return dPrice;
    }
    /**
     * The the object ID. This method is usually only supported locally.
     */
    public void setID(Object objID) throws RemoteException
    {
        // Add code.
    }
    /**
     * SetQuantity Method.
     */
    public void setQuantity(int iQuantity) throws RemoteException
    {
        // add code.
    }
    /**
     * SetAvailability Method.
     */
    public void setAvailability(int iQuantity) throws RemoteException
    {
        // Add code
    }
    /**
     * Set the end date.
     */
    public Date setEndDate(Date time) throws RemoteException
    {
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        Date newTime = time;
        try   {
            recCustSaleDetail.edit();
            newTime = recCustSaleDetail.setEndDate(time);
            if (!time.equals(newTime))
                recCustSaleDetail.set();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return newTime;
    }
    /**
     * Set the new start time for this service.
     */
    public Date setStartDate(Date dateStart) throws RemoteException
    {
        Date newTime = dateStart;
        BookingDetail recCustSaleDetail = (BookingDetail)this.setRecordCurrent();
        try   {
            recCustSaleDetail.edit();
            newTime = recCustSaleDetail.setStartDate(dateStart);
            recCustSaleDetail.set();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return newTime;
    }
    /**
     * Set the price.
     */
    public void setPrice(double dPrice) throws RemoteException
    {
        // Add code
    }

}
