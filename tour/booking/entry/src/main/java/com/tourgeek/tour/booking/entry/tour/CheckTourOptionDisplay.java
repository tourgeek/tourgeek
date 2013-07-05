/**
  * @(#)CheckTourOptionDisplay.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.entry.tour;

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
import com.tourgeek.tour.booking.db.event.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.booking.entry.base.*;

/**
 *  CheckTourOptionDisplay - If the "Always Answer" flag is set by adding a tour
with an answer that is required, switch to the option screen..
 */
public class CheckTourOptionDisplay extends FieldListener
{
    public boolean m_bOldAlwaysResolve = false;
    /**
     * Default constructor.
     */
    public CheckTourOptionDisplay()
    {
        super();
    }
    /**
     * CheckTourOptionDisplay Method.
     */
    public CheckTourOptionDisplay(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = super.fieldChanged(bDisplayOption, iMoveMode);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
        {
            RecordOwner recordOwner = this.getOwner().getRecord().getRecordOwner();
            Record recBooking = (Record)recordOwner.getRecord(Booking.BOOKING_FILE);
            if (m_bOldAlwaysResolve == false)
                if (recBooking.getField(Booking.ALWAYS_RESOLVE).getState() == true)
            {
                recordOwner.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).setValue(BookingScreenHandler.OPTIONS_SCREEN); // Switch to the options screen
            }
        
            m_bOldAlwaysResolve = recBooking.getField(Booking.ALWAYS_RESOLVE).getState();
        }
        return iErrorCode;
    }

}
