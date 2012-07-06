/**
  * @(#)CalcActionDateHandler.
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
        
        BaseField actionType = recTour.getField(Tour.TOUR_EVENT_ID);
        actionType.setValue(TourEvent.NO_EVENT);
        recTour.getField(Tour.NEXT_EVENT_DATE).setToLimit(DBConstants.END_SELECT_KEY);
        BaseField actionDate = recTour.getField(Tour.NEXT_EVENT_DATE);
        
        if (recTour.getField(Tour.DEPARTED).getState() == false)
            if (recTour.getField(Tour.DEPARTURE_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.DEPARTURE_DATE));
            actionType.setValue(TourEvent.DEPARTURE);
        }
        
        if (recTour.getField(Tour.SP_1).getState() == false)
            if (recTour.getField(Tour.SP_1_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.SP_1_DATE));
            actionType.setValue(TourEvent.SPECIAL_1);
        }
        
        if (recTour.getField(Tour.SP_2).getState() == false)
            if (recTour.getField(Tour.SP_2_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.SP_2_DATE));
            actionType.setValue(TourEvent.SPECIAL_2);
        }
        
        if (recTour.getField(Tour.TICKETS).getState() == false)
            if (recTour.getField(Tour.TICKET_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.TICKET_DATE));
            actionType.setValue(TourEvent.TICKETING);
        }
        
        if (recTour.getField(Tour.FINAL_DOCS).getState() == false)
            if (recTour.getField(Tour.FINAL_DOC_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.FINAL_DOC_DATE));
            actionType.setValue(TourEvent.FINAL_DOCS);
        }
        
        if (recTour.getField(Tour.FINALIZED).getState() == false)
            if (recTour.getField(Tour.FINALIZE_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.FINALIZE_DATE));
            actionType.setValue(TourEvent.FINALIZATION);
        }
        
        if (recTour.getField(Tour.CLOSED).getState() == false)
            if (recTour.getField(Tour.CLOSED_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.CLOSED_DATE));
            actionType.setValue(TourEvent.TOUR_CLOSED);
        }
        
        if (recTour.getField(Tour.ORDER_COMPONENTS).getState() == false)
            if (recTour.getField(Tour.ORDER_COMP_DATE).compareTo(actionDate) <= 0)
        {
            actionDate.moveFieldToThis(recTour.getField(Tour.ORDER_COMP_DATE));
            actionType.setValue(TourEvent.ORDER_COMPONENTS);
        }
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
