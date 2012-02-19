/**
 * @(#)BookingCruiseSession.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking.detail;

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
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.base.remote.*;

/**
 *  BookingCruiseSession - .
 */
public class BookingCruiseSession extends BookingDetailBaseSession
{
    /**
     * Default constructor.
     */
    public BookingCruiseSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingCruiseSession Method.
     */
    public BookingCruiseSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
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
        return new BookingCruise(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
    }

}
