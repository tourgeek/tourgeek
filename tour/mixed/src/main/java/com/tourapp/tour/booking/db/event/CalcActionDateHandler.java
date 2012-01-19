/**
 * @(#)CalcActionDateHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.schedule.db.*;

/**
 *  CalcActionDateHandler - Recalc the action type/date.
 */
public class CalcActionDateHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public CalcActionDateHandler()
    {
        super();
    }
    /**
     * CalcActionDateHandler Method.
     */
    public CalcActionDateHandler(BaseField field)
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
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Tour recTour = (Tour)this.getOwner().getRecord();
        if (recTour.getEditMode() == DBConstants.EDIT_CURRENT)
        {
            if ((recTour.getOpenMode() | DBConstants.LOCK_STRATEGY_MASK) == 0)
                recTour.setOpenMode(recTour.getOpenMode() | DBConstants.OPEN_LOCK_ON_EDIT_STRATEGY);   // Error if already locked
            try {
                if (recTour.edit() != DBConstants.NORMAL_RETURN)
                    return DBConstants.NORMAL_RETURN;   // No problem - already locked
            } catch (DBException ex) {
                ex.printStackTrace();
                return DBConstants.NORMAL_RETURN;
            }
        }
        if (recTour.getListener(UpdateOnCloseHandler.class) == null)
            recTour.addListener(new UpdateOnCloseHandler(null)); // Make sure this is updated
        
        BaseField actionType = recTour.getField(Tour.kTourEventID);
        actionType.setValue(TourEvent.NO_EVENT);
        recTour.getField(Tour.kNextEventDate).setToLimit(DBConstants.END_SELECT_KEY);
        BaseField actionDate = recTour.getField(Tour.kNextEventDate);
        
        if (recTour.getField(Tour.kDeparted).getState() == false)
            if (recTour.getField(Tour.kDepartureDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kDepartureDate));
            actionType.setValue(TourEvent.DEPARTURE);
        }
        
        if (recTour.getField(Tour.kSp1).getState() == false)
            if (recTour.getField(Tour.kSp1Date).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kSp1Date));
            actionType.setValue(TourEvent.SPECIAL_1);
        }
        
        if (recTour.getField(Tour.kSp2).getState() == false)
            if (recTour.getField(Tour.kSp2Date).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kSp2Date));
            actionType.setValue(TourEvent.SPECIAL_2);
        }
        
        if (recTour.getField(Tour.kTickets).getState() == false)
            if (recTour.getField(Tour.kTicketDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kTicketDate));
            actionType.setValue(TourEvent.TICKETING);
        }
        
        if (recTour.getField(Tour.kFinalDocs).getState() == false)
            if (recTour.getField(Tour.kFinalDocDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kFinalDocDate));
            actionType.setValue(TourEvent.FINAL_DOCS);
        }
        
        if (recTour.getField(Tour.kFinalized).getState() == false)
            if (recTour.getField(Tour.kFinalizeDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kFinalizeDate));
            actionType.setValue(TourEvent.FINALIZATION);
        }
        
        if (recTour.getField(Tour.kClosed).getState() == false)
            if (recTour.getField(Tour.kClosedDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kClosedDate));
            actionType.setValue(TourEvent.TOUR_CLOSED);
        }
        
        if (recTour.getField(Tour.kOrderComponents).getState() == false)
            if (recTour.getField(Tour.kOrderCompDate).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.kOrderCompDate));
            actionType.setValue(TourEvent.ORDER_COMPONENTS);
        }
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
