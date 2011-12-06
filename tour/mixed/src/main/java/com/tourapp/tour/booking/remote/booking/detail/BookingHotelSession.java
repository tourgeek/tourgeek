/**
 * @(#)BookingHotelSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking.detail;

import org.jbundle.base.db.Record;
import org.jbundle.base.remote.BaseSession;
import org.jbundle.thin.base.remote.RemoteException;

import com.tourapp.tour.booking.detail.db.BookingHotel;
import com.tourapp.tour.product.base.db.MealPlan;
import com.tourapp.tour.product.hotel.db.HotelClass;

/**
 *  BookingHotelSession - .
 */
public class BookingHotelSession extends BookingDetailBaseSession
{
    /**
     * Default constructor.
     */
    public BookingHotelSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingHotelSession Method.
     */
    public BookingHotelSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Object objectID)
    {
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Override this to open the main file for this session.
     */
    public Record openMainRecord()
    {
        return new BookingHotel(this);
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new HotelClass(this);
        new MealPlan(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
    }

}
