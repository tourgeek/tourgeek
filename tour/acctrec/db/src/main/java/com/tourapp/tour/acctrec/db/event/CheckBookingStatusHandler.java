/**
 * @(#)CheckBookingStatusHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  CheckBookingStatusHandler - Make sure the booking status is not 'no-status' if I am adding a new
ArTrx. If I am adding a new trx, then the booking must have been
'accepted'..
 */
public class CheckBookingStatusHandler extends FileListener
{
    protected BookingModel m_recBooking = null;
    /**
     * Default constructor.
     */
    public CheckBookingStatusHandler()
    {
        super();
    }
    /**
     * CheckBookingStatusHandler Method.
     */
    public CheckBookingStatusHandler(BookingModel recBooking)
    {
        this();
        this.init(recBooking);
    }
    /**
     * Initialize class fields.
     */
    public void init(BookingModel recBooking)
    {
        m_recBooking = null;
        m_recBooking = recBooking;
        super.init(null);
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
        if ((iChangeType == DBConstants.AFTER_ADD_TYPE) || (iChangeType == DBConstants.AFTER_UPDATE_TYPE))
        { // Has to be 'after' since I use the same ArTrx file.
            if ((m_recBooking.getEditMode() == DBConstants.EDIT_CURRENT) || (m_recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                Record recBookingStatus = ((ReferenceField)m_recBooking.getField(BookingModel.BOOKING_STATUS_ID)).getReference();
                if ((BookingStatusModel.NO_STATUS_CODE.equalsIgnoreCase(recBookingStatus.getField(BookingStatusModel.CODE).toString()))
                        || (BookingStatusModel.PROPOSAL_CODE.equalsIgnoreCase(recBookingStatus.getField(BookingStatusModel.CODE).toString())))
                {
                    if (!this.getOwner().getField(ArTrx.TRX_STATUS_ID).isNull())
                        if (!ArTrx.INVOICE.equalsIgnoreCase(((TrxStatusField)this.getOwner().getField(ArTrx.TRX_STATUS_ID)).getReference().getField(TrxStatus.DESC_CODE).toString()))
                            if (!ArTrx.INVOICE_MODIFICATION.equalsIgnoreCase(((TrxStatusField)this.getOwner().getField(ArTrx.TRX_STATUS_ID)).getReference().getField(TrxStatus.DESC_CODE).toString()))
                                if (this.getOwner().getField(ArTrx.AMOUNT).getValue() < 0)
                                {
                                    if (m_recBooking.getEditMode() == DBConstants.EDIT_CURRENT)
                                    {
                                        if ((m_recBooking.getOpenMode() & DBConstants.OPEN_READ_ONLY) != 0)
                                            m_recBooking.setOpenMode(m_recBooking.getOpenMode() & ~DBConstants.OPEN_READ_ONLY);
                                        try {
                                            m_recBooking.getTable().edit();
                                        } catch (DBException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    int iAcceptedID = ((ReferenceField)m_recBooking.getField(BookingModel.BOOKING_STATUS_ID)).getIDFromCode(BookingStatusModel.ACCEPTED_CODE);
                                    if (iAcceptedID > 0)
                                        m_recBooking.getField(BookingModel.BOOKING_STATUS_ID).setValue(iAcceptedID);
                                }
                }
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
