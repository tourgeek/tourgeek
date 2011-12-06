/**
 * @(#)BookingPaxSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking;

import org.jbundle.base.db.Record;
import org.jbundle.base.remote.BaseSession;
import org.jbundle.base.remote.db.Session;
import org.jbundle.thin.base.remote.RemoteException;

import com.tourapp.tour.booking.db.BookingPax;
import com.tourapp.tour.product.base.db.PaxCategory;

/**
 *  BookingPaxSession - Booking passenger detail session.
 */
public class BookingPaxSession extends Session
{
    /**
     * Default constructor.
     */
    public BookingPaxSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingPaxSession Method.
     */
    public BookingPaxSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
        return new BookingPax(this);
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new PaxCategory(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        BookingPax recBookingPax = (BookingPax)this.getRecord(BookingPax.kBookingPaxFile);
        recBookingPax.addBookingBehaviors(this);
    }

}
