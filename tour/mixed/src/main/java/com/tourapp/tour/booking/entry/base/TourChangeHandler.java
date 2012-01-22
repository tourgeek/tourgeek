/**
 * @(#)TourChangeHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.base;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  TourChangeHandler - If tour header is changed in booking entry, adjust the current booking.
 */
public class TourChangeHandler extends FileListener
{
    protected Booking m_recBooking = null;
    /**
     * Default constructor.
     */
    public TourChangeHandler()
    {
        super();
    }
    /**
     * TourChangeHandler Method.
     */
    public TourChangeHandler(Booking recBooking)
    {
        this();
        this.init(recBooking);
    }
    /**
     * Initialize class fields.
     */
    public void init(Booking recBooking)
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
        //+    m_recBooking.addNew();
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        boolean bBookingOkay = false;
        if ((m_recBooking.getEditMode() == DBConstants.EDIT_CURRENT) ||(m_recBooking.getEditMode() == DBConstants.EDIT_CURRENT))
        {
            if (m_recBooking.getField(Booking.kTourID).equals(this.getOwner().getField(Tour.kID)))
                bBookingOkay = true;
        }
        if (!bBookingOkay)
        {   // Sync booking with tour
            Record recTourHeader = ((ReferenceField)this.getOwner().getField(Tour.kTourHeaderID)).getReference();
            if (recTourHeader != null)
            {   // Always
                if (recTourHeader.getField(TourHeader.kTourSeries).getState() == true)
                {   // Not series, sync the booking with the tour
                    //+ Read the booking for this tour
                }
                else
                {   // Is series... leave booking alone?
                    
                }
            }
        }
        else
        {
        //+    m_recBooking.addNew();
        }
    }

}
