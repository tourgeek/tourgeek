/**
 *  @(#)CalcBookingActionDateHandler.
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
        
        BaseField actionType = recBooking.getField(Booking.kTourEventID);
        actionType.setValue(TourEvent.NO_EVENT);
        recBooking.getField(Booking.kNextEventDate).setToLimit(DBConstants.END_SELECT_KEY);
        BaseField actionDate = recBooking.getField(Booking.kNextEventDate);
        
        if (recBooking.getField(Booking.kBooked).getState() == false)
            if (recBooking.getField(Booking.kBookingDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recBooking.getField(Booking.kBookingDate));
            actionType.setValue(TourEvent.BOOKING);
        }
        
        if (recBooking.getField(Booking.kDepositDue).getState() == false)
            if (recBooking.getField(Booking.kDepositDueDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recBooking.getField(Booking.kDepositDueDate));
            actionType.setValue(TourEvent.DEPOSIT_DUE);
        }
        
        if (recBooking.getField(Booking.kFinalPaymentDue).getState() == false)
            if (recBooking.getField(Booking.kFinalPaymentDueDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recBooking.getField(Booking.kFinalPaymentDueDate));
            actionType.setValue(TourEvent.FINAL_PAY_DUE);
        }
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
