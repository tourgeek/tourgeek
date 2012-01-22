/**
 * @(#)BookingMenuSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.lookup;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.remote.db.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.main.db.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.base.remote.*;

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
    public BookingMenuSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Map<String, Object> objectID)
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
