/**
 *  @(#)UpdateTourStatusHandler.
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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  UpdateTourStatusHandler - Update the tour status when this BookingDetail record changes.
 */
public class UpdateTourStatusHandler extends FileListener
{
    protected int m_iFieldSeq = -1;
    /**
     * Default constructor.
     */
    public UpdateTourStatusHandler()
    {
        super();
    }
    /**
     * UpdateTourStatusHandler Method.
     */
    public UpdateTourStatusHandler(int iFieldSeq)
    {
        this();
        this.init(iFieldSeq);
    }
    /**
     * Initialize class fields.
     */
    public void init(int iFieldSeq)
    {
        super.init(null);
        m_iFieldSeq = iFieldSeq;
    }
    /**
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        if ((iChangeType == DBConstants.AFTER_ADD_TYPE) || (iChangeType == DBConstants.AFTER_UPDATE_TYPE) || (iChangeType == DBConstants.AFTER_DELETE_TYPE))
        {
            BookingDetail recBookingDetail = (BookingDetail)this.getOwner();
            BaseField fieldTarget = recBookingDetail.getField(m_iFieldSeq);
            if ((iChangeType == DBConstants.AFTER_DELETE_TYPE) || (fieldTarget.isModified()))
            {                
                Record recTour = null;
                Booking recBooking = recBookingDetail.getBooking(false);
                // Do a special check - if the Booking's tour IS this detail's tour and it is current and locked, use that one.
                if (recBooking != null)
                    if ((recBooking.getEditMode() == DBConstants.EDIT_CURRENT) || (recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                        if (recBooking.getField(Booking.kTourID).equals(recBookingDetail.getField(BookingDetail.kTourID)))
                            recTour = ((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
                
                boolean bUpdateTourNow = true;
                if ((recTour == null) || ((recTour.getEditMode() != DBConstants.EDIT_IN_PROGRESS) && (recTour.getEditMode() != DBConstants.EDIT_CURRENT)))
                    recTour = ((ReferenceField)recBookingDetail.getField(BookingDetail.kTourID)).getReference();
                else
                    bUpdateTourNow = false;
                if ((recTour != null)
                    && ((recTour.getEditMode() == DBConstants.EDIT_CURRENT) || (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS)))
                {   // Always
                    try {
                        if ((recTour.getOpenMode() & DBConstants.OPEN_READ_ONLY) == DBConstants.OPEN_READ_ONLY)
                            recTour.setOpenMode(recTour.getOpenMode() & ~DBConstants.OPEN_READ_ONLY);
                            if ((recTour.getOpenMode() & DBConstants.LOCK_STRATEGY_MASK) == DBConstants.OPEN_NO_LOCK_STRATEGY)
                                recTour.setOpenMode(recTour.getOpenMode() | DBConstants.OPEN_LOCK_ON_CHANGE_STRATEGY);
                        if (!bUpdateTourNow)
                            UpdateOnCloseHandler.addUpdateOnCloseHandler(recBooking, recTour, false, true, true);   // Make sure recTour is updated whenever booking is
                        recTour.edit();
                        ((TourStatusSummaryField)recTour.getField(Tour.kTourStatusSummary)).setDetailProperty(recBookingDetail, m_iFieldSeq, iChangeType);
                        if (bUpdateTourNow)
                            recTour.set();
                    } catch (DBException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
