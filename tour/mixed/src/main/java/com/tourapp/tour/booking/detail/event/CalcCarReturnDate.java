/**
 * @(#)CalcCarReturnDate.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.event;

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
import com.tourapp.tour.booking.detail.db.*;

/**
 *  CalcCarReturnDate - .
 */
public class CalcCarReturnDate extends FieldListener
{
    /**
     * Default constructor.
     */
    public CalcCarReturnDate()
    {
        super();
    }
    /**
     * CalcCarReturnDate Method.
     */
    public CalcCarReturnDate(BaseField field)
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
        BaseField fldDays = this.getOwner();
        BookingCar recBookingCar = (BookingCar)fldDays.getRecord();
        DateTimeField fldStartDate = (DateTimeField)recBookingCar.getField(BookingCar.DETAIL_DATE);
        if ((!fldStartDate.isNull())
            && (fldDays.getValue() > 0))
        {
            Date dateStart = fldStartDate.getDateTime();
            float fDays = (float)fldDays.getValue();
            Date dateEnd = new Date(dateStart.getTime() + (long)(fDays * DBConstants.KMS_IN_A_DAY));
            Calendar calendar = DateTimeField.m_calendar;
        
            calendar.setTime(dateEnd);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dateEnd = calendar.getTime();
            
            DateTimeField fldEndDate = (DateTimeField)recBookingCar.getField(BookingCar.DETAIL_END_DATE);
            return fldEndDate.setDateTime(dateEnd, bDisplayOption, iMoveMode);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
