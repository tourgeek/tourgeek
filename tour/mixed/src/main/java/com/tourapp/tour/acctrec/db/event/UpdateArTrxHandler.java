/**
 * @(#)UpdateArTrxHandler.
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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  UpdateArTrxHandler - Update the A/R Trx file when changes are made to the booking..
 */
public class UpdateArTrxHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public UpdateArTrxHandler()
    {
        super();
    }
    /**
     * UpdateArTrxHandler Method.
     */
    public UpdateArTrxHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
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
        if ((iChangeType == DBConstants.AFTER_UPDATE_TYPE)
            || (iChangeType == DBConstants.AFTER_ADD_TYPE))
        {   // Be careful because booking is no longer current
            Booking recBooking = (Booking)this.getOwner();
            boolean bUpdateArTrx = false;
            if (recBooking.getField(Booking.kBookingStatusID).isModified())
                bUpdateArTrx = true;
            if (recBooking.getField(Booking.kNet).isModified())
                bUpdateArTrx = true;
            if (bUpdateArTrx)
            { // Only do if booking is accepted
                BookingStatus recBookingStatus = (BookingStatus)((ReferenceField)recBooking.getField(Booking.kBookingStatusID)).getReference();
                if ((BookingStatus.NO_STATUS_CODE.equalsIgnoreCase(recBookingStatus.getField(BookingStatus.kCode).toString()))
                    || (BookingStatus.PROPOSAL_CODE.equalsIgnoreCase(recBookingStatus.getField(BookingStatus.kCode).toString())))
                        bUpdateArTrx = false;   // Don't update A/R if the booking has not been accepted
                if (recBooking.getField(Booking.kBalance).getValue() != 0)
                    if (recBooking.getField(Booking.kFinalPaymentReceived).getState() == false) // Because if final pymt was received, the balance should be 0
                        bUpdateArTrx = true;    // Special case - A/R Trx's already exist
            }
            if (bUpdateArTrx)
            {
                try   {
                    Object bookmark = null;
                    if (iChangeType == DBConstants.AFTER_UPDATE_TYPE)
                        bookmark = recBooking.getHandle(DBConstants.BOOKMARK_HANDLE);
                    else
                        bookmark = recBooking.getLastModified(DBConstants.BOOKMARK_HANDLE);
                    if (bookmark == null)
                        return this.getOwner().getTask().setLastError("Booking not current on A/R update");    // Should never happen
                    recBooking.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
                    if (recBooking.getEditMode() == DBConstants.EDIT_CURRENT)
                        recBooking.edit();
                    ArTrx recArTrx = recBooking.addArDetail(null, null, true);     // Make sure BookingLine and ArTrx detail (totals) are there
                // First, total the booking balance
                    double dTotal = recBooking.getField(Booking.kNet).getValue();
                // Next, total the current A/R invoice total
                    
                    ArTrxInvoiceSubCountHandler subCountHandler = (ArTrxInvoiceSubCountHandler)recArTrx.getListener(ArTrxInvoiceSubCountHandler.class);
                    double dOldNet = subCountHandler.getTotalToVerify();
                    int iTrxStatus = subCountHandler.getTrxStatus();
                    double dAdjustment = Math.floor((dTotal - dOldNet) * 100.00 + 0.5) / 100.00;
                    if (dAdjustment != 0)
                    { // Add an A/R Adjustment
                        recArTrx.addNew();
                        recArTrx.getField(ArTrx.kTrxStatusID).setValue(iTrxStatus);   // Invoice modification
                        recArTrx.getField(ArTrx.kAmount).setValue(dAdjustment);
                        recArTrx.getField(ArTrx.kComments).moveFieldToThis(((ReferenceField)recArTrx.getField(ArTrx.kTrxStatusID)).getReference().getField(TrxStatus.kStatusDesc));
                        recArTrx.add();
                    }
                    recBooking.set();   // Since it was 'after update' this will leave booking in the same state.
                } catch (DBException ex)    {
                    ex.printStackTrace();
                }
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
