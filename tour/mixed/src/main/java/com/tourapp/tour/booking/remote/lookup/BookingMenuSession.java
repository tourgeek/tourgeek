/**
 * @(#)BookingMenuSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.lookup;

import java.util.Map;

import org.jbundle.base.db.Record;
import org.jbundle.base.db.filter.CompareFileFilter;
import org.jbundle.base.remote.BaseSession;
import org.jbundle.base.remote.db.Session;
import org.jbundle.base.util.DBConstants;
import org.jbundle.main.db.ReportScreenRecord;
import org.jbundle.model.DBException;
import org.jbundle.thin.base.remote.RemoteException;

import com.tourapp.tour.booking.db.Booking;

/**
 *  BookingMenuSession - Remote side of the booking menu.
 */
public class BookingMenuSession extends Session
{
    /**
     * Default constructor.
     */
    public BookingMenuSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingMenuSession Method.
     */
    public BookingMenuSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
        return new BookingMenuLookup(this);
    }
    /**
     * Add the screen record to this session.
     */
    public Record addScreenRecord()
    {
        return new ReportScreenRecord(this);    // For current user name
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().setKeyArea(DBConstants.MAIN_KEY_AREA + 1);
        this.getMainRecord().addListener(new CompareFileFilter(this.getRecord(Booking.kBookingFile).getField(Booking.kEmployeeID), this.getScreenRecord().getField(ReportScreenRecord.kReportUserID), "="));
    }
    /**
     * Override this to do an action sent from the client.
     * @param strCommand The command to execute
     * @param properties The properties for the command
     * @returns Object Return a Boolean.TRUE for success, Boolean.FALSE for failure.
     */
    public Object doRemoteCommand(String strCommand, Map<String,Object> properties) throws RemoteException, DBException
    {
        // Add code here later when you have folder handling
        return super.doRemoteCommand(strCommand, properties);
    }

}
