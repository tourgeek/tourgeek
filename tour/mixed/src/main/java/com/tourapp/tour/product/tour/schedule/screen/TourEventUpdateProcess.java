/**
 *  @(#)TourEventUpdateProcess.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.schedule.screen;

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
import org.jbundle.base.thread.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.schedule.db.*;

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
            String strFieldName = field.getFieldName(false, true);
            if (this.getProperty(strFieldName) != null)
                this.getScreenRecord().getField(iIndex).setString(this.getProperty(strFieldName));
        }
        if (this.getScreenRecord().getField(TourEventScreenRecord.kTourUpdate).getState())
            this.updateTourEvents();
        if (this.getScreenRecord().getField(TourEventScreenRecord.kBookingUpdate).getState())
            this.updateBookingEvents();
    }
    /**
     * UpdateTourEvents Method.
     */
    public void updateTourEvents()
    {
        try {
            Record recTour = this.getRecord(Tour.kTourFile);
            recTour.setKeyArea(Tour.kNextEventDateKey);
            while (recTour.hasNext())
            {
                recTour.next();
                if (recTour.getField(Tour.kNextEventDate).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.kActionCutoffDate)) > 0)
                    break;  // All done
                int iTourEvent = -1;
                while (recTour.getField(Tour.kNextEventDate).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.kActionCutoffDate)) <= 0)
                {
                    if (iTourEvent == recTour.getField(Tour.kTourEventID).getValue())
                    {
                        // todo(don) Never! TourEventID didn't update!
                        break;
                    }
                    iTourEvent = (int)recTour.getField(Tour.kTourEventID).getValue();
                    if (iTourEvent == TourEvent.FINALIZATION)
                        recTour.getField(Tour.kFinalized).setState(true);
                    if (iTourEvent == TourEvent.FINAL_DOCS)
                        recTour.getField(Tour.kFinalDocs).setState(true);
                    if (iTourEvent == TourEvent.TICKETING)
                        recTour.getField(Tour.kTickets).setState(true);
                    if (iTourEvent == TourEvent.SPECIAL_1)
                        recTour.getField(Tour.kSp1).setState(true);
                    if (iTourEvent == TourEvent.SPECIAL_2)
                        recTour.getField(Tour.kSp2).setState(true);
                    if (iTourEvent == TourEvent.DEPARTURE)
                        recTour.getField(Tour.kDeparted).setState(true);
                    if (iTourEvent == TourEvent.ORDER_COMPONENTS)
                        recTour.getField(Tour.kOrderComponents).setState(true);
                    if (iTourEvent == TourEvent.SERVICES_CONFIRMED)
                        recTour.getField(Tour.kServConf).setState(true);
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
            Record recBooking = this.getRecord(Booking.kBookingFile);
            recBooking.setKeyArea(Booking.kNextEventDateKey);
            while (recBooking.hasNext())
            {
                recBooking.next();
                if (recBooking.getField(Booking.kNextEventDate).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.kActionCutoffDate)) > 0)
                    break;  // All done
                int iTourEvent = -1;
                while (recBooking.getField(Booking.kNextEventDate).compareTo(this.getScreenRecord().getField(TourEventScreenRecord.kActionCutoffDate)) <= 0)
                {
                    if (iTourEvent == recBooking.getField(Booking.kTourEventID).getValue())
                    {
                        // todo(don) Never! TourEventID didn't update!
                        break;
                    }
                    iTourEvent = (int)recBooking.getField(Booking.kTourEventID).getValue();
                    if (iTourEvent == TourEvent.BOOKING)
                        recBooking.getField(Booking.kBooked).setState(true);
                    if (iTourEvent == TourEvent.DEPOSIT_DUE)
                        recBooking.getField(Booking.kDepositDue).setState(true);
                    if (iTourEvent == TourEvent.FINAL_PAY_DUE)
                        recBooking.getField(Booking.kFinalPaymentDue).setState(true);
                }
                if (recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBooking.set();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        }
    }

}
