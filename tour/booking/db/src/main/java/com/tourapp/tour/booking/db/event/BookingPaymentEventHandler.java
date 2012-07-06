/**
  * @(#)BookingPaymentEventHandler.
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

/**
 *  BookingPaymentEventHandler - Trigger the deposit received and final payments received events.
 */
public class BookingPaymentEventHandler extends FieldDataScratchHandler
{
    /**
     * Default constructor.
     */
    public BookingPaymentEventHandler()
    {
        super();
    }
    /**
     * BookingPaymentEventHandler Method.
     */
    public BookingPaymentEventHandler(BaseField field)
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
        this.setRespondsToMode(DBConstants.SCREEN_MOVE, true);
    }
    /**
     * DoSetData Method.
     */
    public int doSetData(Object objData, boolean bDisplayOption, int iMoveMode)
    {
        this.setOriginalData(this.getOwner().getData());
        return super.doSetData(objData, bDisplayOption, iMoveMode);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Double doubleOriginalData = (Double)this.getOriginalData();
        if (doubleOriginalData == null)
            doubleOriginalData = DoubleField.ZERO;
        Record recBooking = this.getOwner().getRecord();
        double dNet = recBooking.getField(Booking.NET).getValue();
        if (doubleOriginalData.doubleValue() != 0)  // If this was just 0 zero before this change, there is no was this will trigger anything
            if (dNet > 0)
        {
            double dBalance = Math.floor(recBooking.getField(Booking.BALANCE).getValue() * 100 + 0.5) / 100;
            double dPaid = Math.floor((dNet - dBalance) * 100 + 0.5) / 100;
            if (dPaid >= recBooking.getField(Booking.DEPOSIT).getValue())
                recBooking.getField(Booking.DEPOSIT_RECEIVED).setState(true);
            if (dBalance <= 0)
                recBooking.getField(Booking.FINAL_PAYMENT_RECEIVED).setState(true);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
