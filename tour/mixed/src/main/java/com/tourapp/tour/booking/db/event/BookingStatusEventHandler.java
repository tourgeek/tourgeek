/**
 * @(#)BookingStatusEventHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  BookingStatusEventHandler - Monitor the booking status for changes to trigger events:
- Accepted -> Accepted event
- Cancelled -> Cancelled event.
 */
public class BookingStatusEventHandler extends TourEventHandler
{
    /**
     * Default constructor.
     */
    public BookingStatusEventHandler()
    {
        super();
    }
    /**
     * BookingStatusEventHandler Method.
     */
    public BookingStatusEventHandler(int iTourEventID)
    {
        this();
        this.init(iTourEventID);
    }
    /**
     * Initialize class fields.
     */
    public void init(int iTourEventID)
    {
        super.init(iTourEventID);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        boolean bBookingModified = false;
        String strKey = null;
        Record recBooking = this.getOwner().getRecord();
        int iOldOpenMode = recBooking.setOpenMode(recBooking.getOpenMode() & ~DBConstants.OPEN_READ_ONLY);
        Record recBookingStatus = ((BookingStatusField)this.getOwner()).getReference();
        Stack<String> stack = new Stack<String>();
        while ((recBookingStatus != null) && (recBookingStatus.getEditMode() == DBConstants.EDIT_CURRENT))
        {
            strKey = "eventStatus." + recBookingStatus.getField(BookingStatus.DESCRIPTION).toString() + "." + recBookingStatus.getField(BookingStatus.ID).toString();
            if (BooleanField.YES.equals(((PropertiesField)recBooking.getField(Booking.PROPERTIES)).getProperty(strKey)))
                break;  // Done
            stack.push(strKey);
            if (BookingStatus.CANCELLED_CODE.equals(recBookingStatus.getField(BookingStatus.CODE).toString()))
                this.getOwner().getRecord().getField(Booking.CANCELLED).setState(true);
            recBookingStatus = ((BookingStatusField)recBookingStatus.getField(BookingStatus.FOLLOWS_BOOKING_STATUS_ID)).getReference();
        }
        while (!stack.empty())
        {
            strKey = stack.pop();
            int iBookingStatusID = Integer.parseInt(strKey.substring(strKey.lastIndexOf('.') + 1));
            
            this.triggerEvent(m_iTourEventID, iBookingStatusID);
        
            ((PropertiesField)recBooking.getField(Booking.PROPERTIES)).setProperty(strKey, BooleanField.YES);
            bBookingModified = true;
        }
        if (bBookingModified)
        {
            try {
                recBooking.writeAndRefresh();
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
        recBooking.setOpenMode(iOldOpenMode);
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
