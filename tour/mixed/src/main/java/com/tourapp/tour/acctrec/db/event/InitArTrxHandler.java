/**
 * @(#)InitArTrxHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db.event;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  InitArTrxHandler - .
 */
public class InitArTrxHandler extends FileListener
{
    protected Record m_recBooking = null;
    /**
     * Default constructor.
     */
    public InitArTrxHandler()
    {
        super();
    }
    /**
     * InitArTrxHandler Method.
     */
    public InitArTrxHandler(Record recBooking)
    {
        this();
        this.init(recBooking);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recBooking)
    {
        m_recBooking = null;
        m_recBooking = recBooking;
        super.init(null);
    }
    /**
     * Called when a new blank record is required for the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        super.doNewRecord(bDisplayOption);
        // Booking->Tour->TourHeader->ProductCat P/P  vs  A/R
        Tour recTour = (Tour)((ReferenceField)m_recBooking.getField(Booking.TOUR_ID)).getReference();
        BaseField fldDepartureDate = recTour.getField(Tour.DEPARTURE_DATE);
        this.getOwner().getField(ArTrx.DEPARTURE_DATE).moveFieldToThis(fldDepartureDate, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
    }

}
