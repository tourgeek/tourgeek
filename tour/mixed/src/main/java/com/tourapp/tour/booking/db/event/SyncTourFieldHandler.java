/**
 *  @(#)SyncTourFieldHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db.event;

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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  SyncTourFieldHandler - .
 */
public class SyncTourFieldHandler extends FieldListener
{
    protected Booking m_recBooking = null;
    protected int m_iBookingFieldSeq = -1;
    /**
     * Default constructor.
     */
    public SyncTourFieldHandler()
    {
        super();
    }
    /**
     * SyncTourFieldHandler Method.
     */
    public SyncTourFieldHandler(Booking recBooking, int iBookingFieldSeq)
    {
        this();
        this.init(recBooking, iBookingFieldSeq);
    }
    /**
     * Initialize class fields.
     */
    public void init(Booking recBooking, int iBookingFieldSeq)
    {
        m_recBooking = null;
        super.init(null);
        m_iBookingFieldSeq = iBookingFieldSeq;
        m_recBooking = recBooking;
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Tour recTour = (Tour)this.getOwner().getRecord();
        if (m_recBooking != null)
            if (m_recBooking.getField(Booking.kTourID).equals(recTour.getCounterField()))
                if ((m_recBooking.getEditMode() == DBConstants.EDIT_CURRENT) || (m_recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                Record recTourHeader = ((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
                if (recTourHeader != null)
                    if (recTourHeader.getField(TourHeader.kTourSeries).getState() == false)
                    {
                        boolean[] rgbEnabled = m_recBooking.getField(m_iBookingFieldSeq).setEnableListeners(false);
                        m_recBooking.getField(m_iBookingFieldSeq).moveFieldToThis(this.getOwner());
                        m_recBooking.getField(m_iBookingFieldSeq).setEnableListeners(rgbEnabled);
                    }
            }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
