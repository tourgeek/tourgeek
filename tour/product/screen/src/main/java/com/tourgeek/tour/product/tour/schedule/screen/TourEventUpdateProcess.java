/**
  * @(#)TourEventUpdateProcess.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.tour.schedule.screen;

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
import org.jbundle.base.thread.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.schedule.db.*;

/**
 *  TourEventUpdateProcess - .
 */
public class TourEventUpdateProcess extends BaseProcess
{
    /**
     * Default constructor.
     */
    public TourEventUpdateProcess()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourEventUpdateProcess(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new Tour(this);
    }
    /**
     * Open the other files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Booking(this);
    }
    /**
     * Add the behaviors.
     */
    public void addListeners()
    {
        super.addListeners();
    }
    /**
     * Add the screen record.
     */
    public Record addScreenRecord()
    {
        return new TourEventScreenRecord(this);
    }
    /**
     * Run Method.
     */
    public void run()
    {
        Record screenRecord = this.getScreenRecord();
        for (int iIndex = 0; iIndex < screenRecord.getFieldCount(); iIndex++)
        {
            BaseField field = screenRecord.getField(iIndex);
            String strFieldName = field.getFieldName(false, true, false);
            if (this.getProperty(strFieldName) != null)
                this.getScreenRecord().getField(iIndex).setString(this.getProperty(strFieldName));
        }
        if (this.getScreenRecord().getField(TourEventScreenRecord.TOUR_UPDATE).getState())
            this.updateTourEvents();
        if (this.getScreenRecord().getField(TourEventScreenRecord.BOOKING_UPDATE).getState())
            this.updateBookingEvents();
    }
    /**
     * UpdateTourEvents Method.
     */
    public void updateTourEvents()
    {
        try {
            Record recTour = this.getRecord(Tour.TOUR_FILE);
            recTour.setKeyArea(Tour.NEXT_EVENT_DATE_KEY);
            while (recTour.hasNext())
            {
                recTour.next();
                if (recTour.getField(Tour.NEXT_EVENT_DATE).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.ACTION_CUTOFF_DATE)) > 0)
                    break;  // All done
                int iTourEvent = -1;
                while (recTour.getField(Tour.NEXT_EVENT_DATE).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.ACTION_CUTOFF_DATE)) <= 0)
                {
                    if (iTourEvent == recTour.getField(Tour.TOUR_EVENT_ID).getValue())
                    {
                        // todo(don) Never! TourEventID didn't update!
                        break;
                    }
                    iTourEvent = (int)recTour.getField(Tour.TOUR_EVENT_ID).getValue();
                    if (iTourEvent == TourEvent.FINALIZATION)
                        recTour.getField(Tour.FINALIZED).setState(true);
                    if (iTourEvent == TourEvent.FINAL_DOCS)
                        recTour.getField(Tour.FINAL_DOCS).setState(true);
                    if (iTourEvent == TourEvent.TICKETING)
                        recTour.getField(Tour.TICKETS).setState(true);
                    if (iTourEvent == TourEvent.SPECIAL_1)
                        recTour.getField(Tour.SP_1).setState(true);
                    if (iTourEvent == TourEvent.SPECIAL_2)
                        recTour.getField(Tour.SP_2).setState(true);
                    if (iTourEvent == TourEvent.DEPARTURE)
                        recTour.getField(Tour.DEPARTED).setState(true);
                    if (iTourEvent == TourEvent.ORDER_COMPONENTS)
                        recTour.getField(Tour.ORDER_COMPONENTS).setState(true);
                    if (iTourEvent == TourEvent.SERVICES_CONFIRMED)
                        recTour.getField(Tour.SERV_CONF).setState(true);
                }
                if (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recTour.set();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * UpdateBookingEvents Method.
     */
    public void updateBookingEvents()
    {
        try {
            Record recBooking = this.getRecord(Booking.BOOKING_FILE);
            recBooking.setKeyArea(Booking.NEXT_EVENT_DATE_KEY);
            while (recBooking.hasNext())
            {
                recBooking.next();
                if (recBooking.getField(Booking.NEXT_EVENT_DATE).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.ACTION_CUTOFF_DATE)) > 0)
                    break;  // All done
                int iTourEvent = -1;
                while (recBooking.getField(Booking.NEXT_EVENT_DATE).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.ACTION_CUTOFF_DATE)) <= 0)
                {
                    if (iTourEvent == recBooking.getField(Booking.TOUR_EVENT_ID).getValue())
                    {
                        // todo(don) Never! TourEventID didn't update!
                        break;
                    }
                    iTourEvent = (int)recBooking.getField(Booking.TOUR_EVENT_ID).getValue();
                    if (iTourEvent == TourEvent.BOOKING)
                        recBooking.getField(Booking.BOOKED).setState(true);
                    if (iTourEvent == TourEvent.DEPOSIT_DUE)
                        recBooking.getField(Booking.DEPOSIT_DUE).setState(true);
                    if (iTourEvent == TourEvent.FINAL_PAY_DUE)
                        recBooking.getField(Booking.FINAL_PAYMENT_DUE).setState(true);
                }
                if (recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBooking.set();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        }
    }

}
