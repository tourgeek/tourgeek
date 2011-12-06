/**
 * @(#)BookingCarSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking.detail;

import org.jbundle.base.db.Record;
import org.jbundle.base.remote.BaseSession;
import org.jbundle.thin.base.remote.RemoteException;

import com.tourapp.tour.booking.detail.db.BookingCar;

/**
 *  BookingCarSession - .
 */
public class BookingCarSession extends BookingDetailBaseSession
{
    /**
     * Default constructor.
     */
    public BookingCarSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingCarSession Method.
     */
    public BookingCarSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
        return new BookingCar(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
    }

}
