/**
 * @(#)BookingItemSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking.detail;

import org.jbundle.base.db.Record;
import org.jbundle.base.remote.BaseSession;
import org.jbundle.thin.base.remote.RemoteException;

import com.tourapp.tour.booking.detail.db.BookingItem;

/**
 *  BookingItemSession - .
 */
public class BookingItemSession extends BookingDetailBaseSession
{
    /**
     * Default constructor.
     */
    public BookingItemSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingItemSession Method.
     */
    public BookingItemSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
        return new BookingItem(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
    }

}
