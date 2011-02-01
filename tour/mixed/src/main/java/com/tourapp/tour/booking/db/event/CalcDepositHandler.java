/**
 *  @(#)CalcDepositHandler.
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
        int iPax = (int)recBooking.getField(Booking.kPax).getValue();
        double dDepositAmount = 0;
        if (!recBooking.getField(Booking.kTourID).isNull())
        {
            Record recTour = ((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
            if (recTour != null)
            {
                Record recTourHeader = ((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
                if (recTourHeader != null)
                {
                    TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReference();
                    if (recTourClass != null)
                    {
                        recTourClass.fixBasedFields();
                        dDepositAmount = recTourClass.getField(TourClass.kDepositAmount).getValue();
                    }
                }
            }
        }
        
        double dDeposit = dDepositAmount * iPax;
        recBooking.getField(Booking.kDeposit).setValue(dDeposit);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
