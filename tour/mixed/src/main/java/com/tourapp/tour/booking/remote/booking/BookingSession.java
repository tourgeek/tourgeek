/**
 * @(#)BookingSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking;

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
import org.jbundle.base.remote.db.*;
import java.rmi.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.remote.*;
import com.tourapp.thin.app.booking.entry.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.booking.entry.tour.*;
import com.tourapp.tour.booking.db.event.*;

/**
 *  BookingSession - .
 */
public class BookingSession extends TableSession
{
    /**
     * Default constructor.
     */
    public BookingSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingSession Method.
     */
    public BookingSession(TaskSession parentSessionObject) throws RemoteException
    {
        this();
        this.init(parentSessionObject);
    }
    /**
     * Initialize class fields.
     */
    public void init(TaskSession parentSessionObject)
    {
        super.init(parentSessionObject, null, null);
    }
    /**
     * Override this to open the main file for this session.
     */
    public Record openMainRecord()
    {
        return new Booking(this);
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        Tour recTour = (Tour)((ReferenceField)this.getMainRecord().getField(Booking.kTourID)).getReferenceRecord(this);//new Tour(this);
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReferenceRecord(this);
        
        new BookingControl(this);
        new ProfileControl(this);
        
        BookingDetail recCustSaleDetail = new BookingDetail(this);
        recCustSaleDetail.setKeyArea(BookingDetail.kBookingIDKey);
        ProductSession productSession = null;   // pend(don) How do I do this?
        try   {
            new BookingDetailSession(this, recCustSaleDetail, null, productSession);
        } catch (RemoteException ex)    {
            ex.printStackTrace();
        }
        
        new BookingLine(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        Booking recBooking = (Booking)this.getMainRecord();
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        TourHeader recTourHdr = (TourHeader)this.getRecord(TourHeader.kTourHeaderFile);
        ReferenceField fldTourID = (ReferenceField)recBooking.getField(Booking.kTourID);
        BookingControl recBookingControl = (BookingControl)this.getRecord(BookingControl.kBookingControlFile);
        ProfileControl recProfileControl = (ProfileControl)this.getRecord(ProfileControl.kProfileControlFile);
        
        recBooking.setOpenMode(recBooking.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        fldTourID.addListener(new ReadSecondaryHandler(recTour, Tour.kIDKey, DBConstants.DONT_CLOSE_ON_FREE, true, true));  // Update record
        recTour.addListener(new DisplayReadHandler(Tour.kTourHeaderID, recTourHdr, TourHeader.kID));        
        recTour.getField(Tour.kTourHeaderID).addListener(new MainReadOnlyHandler(DBConstants.MAIN_KEY_AREA));
        recBooking.addControlDefaults(recBookingControl, recProfileControl);
        
        // This code read the currency CODE into a virtual field for use in displays 
        Record recCurrencys = ((ReferenceField)recBooking.getField(Booking.kCurrencysID)).getReferenceRecord(this);
        recBooking.getField(Booking.kCurrencysID).addListener(new ReadSecondaryHandler(recCurrencys));
        MoveOnChangeHandler moveListener = new MoveOnChangeHandler(recBooking.getField(Booking.kCurrencyCode), recCurrencys.getField(Currencys.kCurrencyCode));
        moveListener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        moveListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recBooking.getField(Booking.kCurrencysID).addListener(moveListener);
        
        this.getRecord(BookingDetail.kBookingDetailFile).setKeyArea(BookingDetail.kBookingIDKey);
        ((BookingSub)this.getRecord(BookingDetail.kBookingDetailFile)).addDetailBehaviors(recBooking, recTour);
        recBooking.addListener(new RequeryOnUpdateHandler(this.getRecord(BookingDetail.kBookingDetailFile)));
        
        this.getRecord(BookingLine.kBookingLineFile).setKeyArea(BookingLine.kBookingKey);
        ((BookingLine)this.getRecord(BookingLine.kBookingLineFile)).addDetailBehaviors(recBooking, recTour);
        recBooking.addListener(new RecountOnValidHandler(this.getRecord(BookingLine.kBookingLineFile)));
                
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReferenceRecord(this);
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReferenceRecord(this);
        
        BaseField fldTourCode = this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kCode);
        BaseField fldDepartureDate = this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate);
        BaseField fldTourDesc = this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kDescription);
        
        FieldListener fieldBehavior = null;
        fieldBehavior = new ChangeTourHeaderHandler(recTourHeader, recTourClass, recTour, recBooking, fldTourCode, fldDepartureDate, fldTourDesc);
        fldDepartureDate.addListener(fieldBehavior);
        
        NewBookingHandler newBookingHandler = (NewBookingHandler)recBooking.getListener(NewBookingHandler.class);
        if (newBookingHandler != null)
            newBookingHandler.useThinTourHeader(true);
    }
    /**
     * Override this to do an action sent from the client.
     * @param strCommand The command to execute
     * @param properties The properties for the command
     * @returns Object Return a Boolean.TRUE for success, Boolean.FALSE for failure.
     */
    public Object doRemoteCommand(String strCommand, Map<String,Object> properties) throws RemoteException, DBException
    {
        if (BookingConstants.GET_DETAIL_COMMAND.equalsIgnoreCase(strCommand))
        {
                return this.getRemoteTable(BookingDetail.kBookingDetailFile);
        }
        else if (DBConstants.RESET.equalsIgnoreCase(strCommand))
        {
            Booking recBooking = (Booking)this.getMainRecord();
            try {
                recBooking.addNew();
            } catch (DBException ex)   {
                ex.printStackTrace();
            }
        }
        else if (DBConstants.DELETE.equalsIgnoreCase(strCommand))
        {
            Booking recBooking = (Booking)this.getMainRecord();
            try {
                if ((recBooking.getEditMode() == Constants.EDIT_CURRENT)
                    || (recBooking.getEditMode() == Constants.EDIT_IN_PROGRESS))
                {
                    recBooking.remove();    // This will delete the detail also
                    recBooking.addNew();
                    return Boolean.TRUE;    // Success
                }
            } catch (DBException ex)   {
                ex.printStackTrace();
                return Boolean.FALSE;   // Error
            }
            return Boolean.FALSE;   // Error
        }
        return super.doRemoteCommand(strCommand, properties);
    }

}
