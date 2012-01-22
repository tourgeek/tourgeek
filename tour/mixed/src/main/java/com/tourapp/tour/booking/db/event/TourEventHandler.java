/**
 * @(#)TourEventHandler.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  TourEventHandler - A tour or booking event occurred.
Trigger the actions that are scheduled to occur..
 */
public class TourEventHandler extends FieldListener
{
    protected int m_iTourEventID = TourEvent.NO_EVENT;
    /**
     * Default constructor.
     */
    public TourEventHandler()
    {
        super();
    }
    /**
     * TourEventHandler Method.
     */
    public TourEventHandler(int iTourEventID)
    {
        this();
        this.init(iTourEventID);
    }
    /**
     * Initialize class fields.
     */
    public void init(int iTourEventID)
    {
        super.init(null);
        
        m_iTourEventID = iTourEventID;
        
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        if (this.getOwner() instanceof BooleanField)
            if (this.getOwner().getState() == true)
        {
            this.triggerEvent(m_iTourEventID, 0);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }
    /**
     * TriggerEvent Method.
     */
    public void triggerEvent(int iTourEventID, int iBaseStatusID)
    {
        Record recTarget = this.getOwner().getRecord();
        if (recTarget.getEditMode() == DBConstants.EDIT_NONE)
            return;     // Never
        Booking recBooking = null;
        Tour recTour = null;
        if (recTarget instanceof Booking)
        {
            recBooking = (Booking)recTarget;
            recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
        }
        else if (recTarget instanceof Tour)
        {
            recTour = (Tour)recTarget;
        }
        else
        {
            // Never - error
        }
        Record recTourHeader = ((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
        Record recTourClass = ((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReference();
        
        TourEventSchedule recTourEventSchedule = recTour.getTourEventSchedule();
        if (recTourEventSchedule.getListener(SubFileFilter.class) != null)
        {   // Already in use
            recTourEventSchedule = new TourEventSchedule(Utility.getRecordOwner(recTour)); 
        }
        CompareFileFilter listener2 = new CompareFileFilter(TourEventSchedule.kTourEventID, Integer.toString(iTourEventID), DBConstants.EQUALS, null, false);
        recTourEventSchedule.addListener(listener2);
        try {
        
            boolean bFirstTime = true;
            boolean bBaseClass = true;
            while ((recTourClass != null ) && (recTourClass.getEditMode() == DBConstants.EDIT_CURRENT))
            {
                SubFileFilter listener1 = new SubFileFilter(recTourClass);
                recTourEventSchedule.addListener(listener1);
                recTourEventSchedule.close();
                while (recTourEventSchedule.hasNext())
                {
                    recTourEventSchedule.next();
                    if (!bBaseClass)
                    {
                        if (recTourEventSchedule.getField(TourEventSchedule.kTourClassOnly).getState() == true)
                            continue;   // This event runs for the base class only
                    }
                    if (bFirstTime)
                    {
                        recTarget.writeAndRefresh();
                        bFirstTime = false;
                    }
                    if (iTourEventID == TourEvent.BOOKING_STATUS)
                        if (recTourEventSchedule.getField(TourEventSchedule.kBookingStatusID).getValue() != iBaseStatusID)
                            continue;   // Not the right booking status
                    recTourEventSchedule.doAction(recTarget);
                }
                bBaseClass = false;
                recTourEventSchedule.removeListener(listener1, true);
                recTourClass = ((ReferenceField)recTourClass.getField(TourClass.kBasedClassID)).getReference();
            }
            if (!bFirstTime)
                recTarget.writeAndRefresh();
        } catch (DBException ex) {
            ex.printStackTrace();
            return;
        } finally {
            recTourEventSchedule.removeListener(listener2, true);
            recTourEventSchedule.close();
            if (recTourEventSchedule != recTour.getTourEventSchedule())
                recTourEventSchedule.free();    // This was a temp copy.
        }
    }

}
