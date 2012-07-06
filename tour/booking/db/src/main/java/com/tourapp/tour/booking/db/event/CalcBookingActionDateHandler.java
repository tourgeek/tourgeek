/**
  * @(#)CalcBookingActionDateHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.schedule.db.*;

/**
 *  CalcBookingActionDateHandler - .
 */
public class CalcBookingActionDateHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public CalcBookingActionDateHandler()
    {
        super();
    }
    /**
     * CalcBookingActionDateHandler Method.
     */
    public CalcBookingActionDateHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Init Method.
     */
    public void init(BaseField field)
    {
        super.init(field);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Booking recBooking = (Booking)this.getOwner().getRecord();
        if (recBooking.getEditMode() == DBConstants.EDIT_CURRENT)
        {
            if ((recBooking.getOpenMode() | DBConstants.LOCK_STRATEGY_MASK) == 0)
                recBooking.setOpenMode(recBooking.getOpenMode() | DBConstants.OPEN_LOCK_ON_EDIT_STRATEGY);   // Error if already locked
            try {
                if (recBooking.edit() != DBConstants.NORMAL_RETURN)
                    return DBConstants.NORMAL_RETURN;   // Nor problem - already locked
            } catch (DBException ex) {
                ex.printStackTrace();
                return DBConstants.NORMAL_RETURN;
            }
        }
        if (recBooking.getListener(UpdateOnCloseHandler.class) == null)
            recBooking.addListener(new UpdateOnCloseHandler(null)); // Make sure this is updated
        
        BaseField actionType = recBooking.getField(Booking.TOUR_EVENT_ID);
        actionType.setValue(TourEvent.NO_EVENT);
        recBooking.getField(Booking.NEXT_EVENT_DATE).setToLimit(DBConstants.END_SELECT_KEY);
        BaseField actionDate = recBooking.getField(Booking.NEXT_EVENT_DATE);
        
        if (recBooking.getField(Booking.BOOKED).getState() == false)
            if (recBooking.getField(Booking.BOOKING_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recBooking.getField(Booking.BOOKING_DATE));
            actionType.setValue(TourEvent.BOOKING);
        }
        
        if (recBooking.getField(Booking.DEPOSIT_DUE).getState() == false)
            if (recBooking.getField(Booking.DEPOSIT_DUE_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recBooking.getField(Booking.DEPOSIT_DUE_DATE));
            actionType.setValue(TourEvent.DEPOSIT_DUE);
        }
        
        if (recBooking.getField(Booking.FINAL_PAYMENT_DUE).getState() == false)
            if (recBooking.getField(Booking.FINAL_PAYMENT_DUE_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recBooking.getField(Booking.FINAL_PAYMENT_DUE_DATE));
            actionType.setValue(TourEvent.FINAL_PAY_DUE);
        }
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
