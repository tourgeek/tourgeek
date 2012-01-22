/**
 * @(#)SyncBookingFieldHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  SyncBookingFieldHandler - Sync this booking field with the associated tour field.
 */
public class SyncBookingFieldHandler extends FieldListener
{
    protected int m_iTourFieldSeq = -1;
    /**
     * Default constructor.
     */
    public SyncBookingFieldHandler()
    {
        super();
    }
    /**
     * SyncBookingFieldHandler Method.
     */
    public SyncBookingFieldHandler(int iTourFieldSeq)
    {
        this();
        this.init(iTourFieldSeq);
    }
    /**
     * Initialize class fields.
     */
    public void init(int iTourFieldSeq)
    {
        super.init(null);
        m_iTourFieldSeq = iTourFieldSeq;
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Booking recBooking = (Booking)this.getOwner().getRecord();
        Tour recTour = null;
        if (!recBooking.getField(Booking.kTourID).isNull())
            recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
        if (recTour != null)
            if ((recTour.getEditMode() == DBConstants.EDIT_CURRENT) || (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                Record recTourHeader = ((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
                if (recTourHeader != null)
                    if (recTourHeader.getField(TourHeader.kTourSeries).getState() == false)
                    {
                        if (recBooking.getListener(WriteOnUpdateHandler.class) == null)
                            recBooking.addListener(new WriteOnUpdateHandler(recTour, false));
                        boolean[] rgbEnabled = recTour.getField(m_iTourFieldSeq).setEnableListeners(false);
                        recTour.getField(m_iTourFieldSeq).moveFieldToThis(this.getOwner());
                        recTour.getField(m_iTourFieldSeq).setEnableListeners(rgbEnabled);
                    }
            }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
