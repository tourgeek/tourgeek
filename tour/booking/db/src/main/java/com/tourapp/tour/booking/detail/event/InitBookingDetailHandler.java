/**
  * @(#)InitBookingDetailHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.detail.event;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  InitBookingDetailHandler - Init fields in cust detail.
 */
public class InitBookingDetailHandler extends FileListener
{
    protected Booking m_recBooking = null;
    protected Tour m_recTour = null;
    /**
     * Default constructor.
     */
    public InitBookingDetailHandler()
    {
        super();
    }
    /**
     * InitBookingDetailHandler Method.
     */
    public InitBookingDetailHandler(Booking recBooking, Tour recTour)
    {
        this();
        this.init(recBooking, recTour);
    }
    /**
     * Initialize class fields.
     */
    public void init(Booking recBooking, Tour recTour)
    {
        m_recBooking = null;
        m_recTour = null;
        m_recBooking = recBooking;
        m_recTour = recTour;
        super.init(null);
    }
    /**
     * Clone Method.
     */
    public Object clone()
    {
        return new InitBookingDetailHandler(m_recBooking, m_recTour);
    }
    /**
     * Called when a new blank record is required for the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        ((BookingSub)this.getOwner()).initBookingDetailFields(m_recBooking, m_recTour, false);
        super.doNewRecord(bDisplayOption);
    }

}
