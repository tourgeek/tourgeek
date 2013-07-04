/**
  * @(#)CalcDepositHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.db.event;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  CalcDepositHandler - .
 */
public class CalcDepositHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public CalcDepositHandler()
    {
        super();
    }
    /**
     * CalcDepositHandler Method.
     */
    public CalcDepositHandler(BaseField field)
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
        this.setRespondsToMode(DBConstants.READ_MOVE, false);   // Usually, you only want to recompute on screen change
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);   // Usually, you only want to recompute on screen change
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Record recBooking = this.getOwner().getRecord();
        int iPax = (int)recBooking.getField(Booking.PAX).getValue();
        double dDepositAmount = 0;
        if (!recBooking.getField(Booking.TOUR_ID).isNull())
        {
            Record recTour = ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
            if (recTour != null)
            {
                Record recTourHeader = ((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReference();
                if (recTourHeader != null)
                {
                    TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.TOUR_CLASS_ID)).getReference();
                    if (recTourClass != null)
                    {
                        recTourClass.fixBasedFields();
                        dDepositAmount = recTourClass.getField(TourClass.DEPOSIT_AMOUNT).getValue();
                    }
                }
            }
        }
        
        double dDeposit = dDepositAmount * iPax;
        recBooking.getField(Booking.DEPOSIT).setValue(dDeposit);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
