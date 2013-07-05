/**
  * @(#)BookingAnswerSelectHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.db.event;

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
import com.tourgeek.tour.booking.db.*;

/**
 *  BookingAnswerSelectHandler - When a new booking answer gets selected, add the tour header detail to the booking..
 */
public class BookingAnswerSelectHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public BookingAnswerSelectHandler()
    {
        super();
    }
    /**
     * BookingAnswerSelectHandler Method.
     */
    public BookingAnswerSelectHandler(BaseField field)
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
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        BookingAnswer recBookingAnswer = (BookingAnswer)this.getOwner().getRecord();
        RecordOwner recordOwner = recBookingAnswer.getRecordOwner();
        Booking recBooking = (Booking)recordOwner.getRecord(Booking.BOOKING_FILE);
        Tour recTour = (Tour)recordOwner.getRecord(Tour.TOUR_FILE);
        BookingPax recBookingPax = (BookingPax)((ReferenceField)recBookingAnswer.getField(BookingAnswer.BOOKING_PAX_ID)).getReference();
        if (recBookingPax.getField(BookingPax.ID).isNull())
            recBookingPax.getField(BookingPax.ID).moveFieldToThis(recBookingAnswer.getField(BookingAnswer.BOOKING_PAX_ID));
        BaseField fldTourModuleID = recBookingAnswer.getField(BookingAnswer.MODULE_ID);
        Date dateStart = ((DateTimeField)recBookingAnswer.getField(BookingAnswer.MODULE_START_DATE)).getDateTime();
        if (this.getOwner().getState() == true)
        {    // This item was selected, add the detail.
            try {
                int iErrorCode = recBookingAnswer.addAnswerDetail(recBooking, recTour, recBookingPax, fldTourModuleID, dateStart);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    return iErrorCode;
            } catch (DBException ex) {
                ex.printStackTrace();
                return ex.getErrorCode();
            }
        }
        else
        {    // Item de-selected, delete and restore previous
            // todo(don) Need to figure this out.
            return this.getOwner().getRecord().getRecordOwner().getTask().setLastError("Can't se-select options");
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
