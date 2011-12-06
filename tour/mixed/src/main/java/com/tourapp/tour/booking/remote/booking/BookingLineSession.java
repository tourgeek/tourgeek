/**
 * @(#)BookingLineSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking;

import org.jbundle.base.db.Record;
import org.jbundle.base.db.filter.SubFileFilter;
import org.jbundle.base.remote.BaseSession;
import org.jbundle.base.remote.db.Session;
import org.jbundle.thin.base.remote.RemoteException;

import com.tourapp.tour.booking.db.Booking;
import com.tourapp.tour.booking.detail.db.BookingLine;

/**
 *  BookingLineSession - Booking line item detail session.
 */
public class BookingLineSession extends Session
{
    /**
     * Default constructor.
     */
    public BookingLineSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingLineSession Method.
     */
    public BookingLineSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
        return new BookingLine(this);
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        
        this.getRecord(BookingLine.kBookingLineFile).addListener(new SubFileFilter(recBooking, true));
    }

}
