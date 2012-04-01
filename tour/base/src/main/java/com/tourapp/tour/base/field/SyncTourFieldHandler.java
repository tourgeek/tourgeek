/**
 * @(#)SyncTourFieldHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.field;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.product.tour.db.*;

/**
 *  SyncTourFieldHandler - .
 */
public class SyncTourFieldHandler extends FieldListener
{
    protected Record m_recBooking = null;
    protected String m_iBookingFieldSeq = null;
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
    public SyncTourFieldHandler(Record recBooking, String iBookingFieldSeq)
    {
        this();
        this.init(recBooking, iBookingFieldSeq);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recBooking, String iBookingFieldSeq)
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
        Record recTour = this.getOwner().getRecord();
        if (m_recBooking != null)
            if (m_recBooking.getField(BookingModel.TOUR_ID).equals(recTour.getCounterField()))
                if ((m_recBooking.getEditMode() == DBConstants.EDIT_CURRENT) || (m_recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                Record recTourHeader = ((ReferenceField)recTour.getField(TourModel.TOUR_HEADER_ID)).getReference();
                if (recTourHeader != null)
                    if (recTourHeader.getField(TourHeaderModel.TOUR_SERIES).getState() == false)
                    {
                        boolean[] rgbEnabled = m_recBooking.getField(m_iBookingFieldSeq).setEnableListeners(false);
                        m_recBooking.getField(m_iBookingFieldSeq).moveFieldToThis(this.getOwner());
                        m_recBooking.getField(m_iBookingFieldSeq).setEnableListeners(rgbEnabled);
                    }
            }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
