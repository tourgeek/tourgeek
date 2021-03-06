/**
  * @(#)CalcBookingDatesHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.base.event;

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
import com.tourgeek.model.tour.booking.db.*;

/**
 *  CalcBookingDatesHandler - Recalc bk dates, status.
 */
public class CalcBookingDatesHandler extends FieldListener
{
    protected Record m_recTour = null;
    protected Record m_recTourHeader = null;
    /**
     * Default constructor.
     */
    public CalcBookingDatesHandler()
    {
        super();
    }
    /**
     * CalcBookingDatesHandler Method.
     */
    public CalcBookingDatesHandler(Record recTour, Record recTourHeader)
    {
        this();
        this.init(recTour, recTourHeader);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recTour, Record recTourHeader)
    {
        m_recTour = null;
        m_recTourHeader = null;
        m_recTour = recTour;
        m_recTourHeader = recTourHeader;
        
        super.init(null);
        
        m_bReadMove = false;
        m_bInitMove = false;
    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (this.getOwner() != null)
            m_recTourHeader.addListener(new FileRemoveBOnCloseHandler(this));
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        BookingModel recBooking = (BookingModel)this.getOwner().getRecord();
        return recBooking.calcBookingDates(m_recTour, m_recTourHeader);
    }

}
