/**
  * @(#)BookingLineSession.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.remote.booking;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.remote.db.*;
import org.jbundle.base.remote.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.thin.base.remote.*;
import com.tourgeek.tour.booking.db.*;

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
    public BookingLineSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
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
        Booking recBooking = (Booking)this.getRecord(Booking.BOOKING_FILE);
        
        this.getRecord(BookingLine.BOOKING_LINE_FILE).addListener(new SubFileFilter(recBooking, true));
    }

}
