/**
  * @(#)BookingLineStatusHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.detail.event;

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
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.booking.db.*;

/**
 *  BookingLineStatusHandler - .
 */
public class BookingLineStatusHandler extends FileListener
{
    protected int m_iOldPricingStatusID;
    /**
     * Default constructor.
     */
    public BookingLineStatusHandler()
    {
        super();
    }
    /**
     * BookingLineStatusHandler Method.
     */
    public BookingLineStatusHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_iOldPricingStatusID = PricingStatus.NULL_STATUS;
        super.init(record);
    }
    /**
     * Called when a new blank record is required for the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        m_iOldPricingStatusID = PricingStatus.NULL_STATUS;
        super.doNewRecord(bDisplayOption);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        m_iOldPricingStatusID = (int)this.getOwner().getField(BookingLine.PRICING_STATUS_ID).getValue();
        
        super.doValidRecord(bDisplayOption);
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
        int iNewPricingStatusID = (int)this.getOwner().getField(BookingLine.PRICING_STATUS_ID).getValue();
        switch (iChangeType)
        {
            case DBConstants.AFTER_REQUERY_TYPE:
                this.setMainStatus(PricingStatus.VALID);
                break;
            case DBConstants.SELECT_EOF_TYPE:
                break;
            case DBConstants.MOVE_NEXT_TYPE:
                if (iNewPricingStatusID == PricingStatus.NOT_VALID)
                    this.setMainStatus(PricingStatus.NOT_VALID);
                break;
            case DBConstants.AFTER_ADD_TYPE:
            case DBConstants.AFTER_UPDATE_TYPE:
                if (iNewPricingStatusID != m_iOldPricingStatusID)
                {
                    if (m_iOldPricingStatusID == PricingStatus.NOT_VALID)
                        if ((iNewPricingStatusID == PricingStatus.VALID) || (iNewPricingStatusID == PricingStatus.MANUAL))
                            this.rescanStatus();
                    if (iNewPricingStatusID == PricingStatus.NOT_VALID)
                        this.setMainStatus(PricingStatus.NOT_VALID);
                }
                break;
            case DBConstants.AFTER_DELETE_TYPE:
                if (m_iOldPricingStatusID == PricingStatus.NOT_VALID)
                    this.rescanStatus();
                break;
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }
    /**
     * RescanStatus Method.
     */
    public void rescanStatus()
    {
        Record recBookingLine = this.getOwner();
        RecordOwner screen = this.getOwner().getRecordOwner();
        if (screen instanceof GridScreenParent)
        {
            ((GridScreenParent)screen).reSelectRecords();
        }
        else if (screen != null)
        {
            Record recBooking = (Record)screen.getRecord(Booking.BOOKING_FILE);
            if (recBooking != null)
            {   // Always
                this.setMainStatus(PricingStatus.VALID);
                FileListener listener = new SubFileFilter(recBooking);
                recBookingLine.addListener(listener);
                int iOldKeyArea = recBookingLine.getDefaultOrder();
                recBookingLine.setKeyArea(BookingLine.BOOKING_ID_KEY);
                recBookingLine.close();
                try {
                    while (recBookingLine.hasNext())
                    {
                        recBookingLine.next();
                    }
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    recBookingLine.removeListener(listener, true);
                    recBookingLine.setKeyArea(iOldKeyArea);
                }
            }
        }
    }
    /**
     * SetMainStatus Method.
     */
    public void setMainStatus(int iMainStatus)
    {
        RecordOwner screen = this.getOwner().getRecordOwner();
        if (screen != null)
        {
            Record recBooking = (Record)screen.getRecord(Booking.BOOKING_FILE);
            if (recBooking != null)
                recBooking.getField(Booking.PRICING_STATUS_ID).setValue(iMainStatus);
        }
    }

}
