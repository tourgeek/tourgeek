
package com.tourgeek.tour.booking.remote.booking;

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
import org.jbundle.base.remote.db.*;
import org.jbundle.thin.base.remote.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.product.remote.*;
import com.tourgeek.thin.app.booking.entry.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.product.base.event.*;
import com.tourgeek.tour.booking.db.event.*;

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
        Tour recTour = (Tour)((ReferenceField)this.getMainRecord().getField(Booking.TOUR_ID)).getReferenceRecord(this);//new Tour(this);
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReferenceRecord(this);
        
        new BookingControl(this);
        new ProfileControl(this);
        
        BookingDetail recCustSaleDetail = new BookingDetail(this);
        recCustSaleDetail.setKeyArea(BookingDetail.BOOKING_ID_KEY);
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
        Tour recTour = (Tour)this.getRecord(Tour.TOUR_FILE);
        TourHeader recTourHdr = (TourHeader)this.getRecord(TourHeader.TOUR_HEADER_FILE);
        ReferenceField fldTourID = (ReferenceField)recBooking.getField(Booking.TOUR_ID);
        BookingControl recBookingControl = (BookingControl)this.getRecord(BookingControl.BOOKING_CONTROL_FILE);
        ProfileControl recProfileControl = (ProfileControl)this.getRecord(ProfileControl.PROFILE_CONTROL_FILE);
        
        recBooking.setOpenMode(recBooking.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        fldTourID.addListener(new ReadSecondaryHandler(recTour, Tour.ID_KEY, DBConstants.DONT_CLOSE_ON_FREE, true, true));  // Update record
        recTour.addListener(new DisplayReadHandler(Tour.TOUR_HEADER_ID, recTourHdr, TourHeader.ID));        
        recTour.getField(Tour.TOUR_HEADER_ID).addListener(new MainReadOnlyHandler(null));
        recBooking.addControlDefaults(recBookingControl, recProfileControl);
        
        // This code read the currency CODE into a virtual field for use in displays 
        Record recCurrencys = ((ReferenceField)recBooking.getField(Booking.CURRENCYS_ID)).getReferenceRecord(this);
        recBooking.getField(Booking.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        MoveOnChangeHandler moveListener = new MoveOnChangeHandler(recBooking.getField(Booking.CURRENCY_CODE), recCurrencys.getField(Currencys.CURRENCY_CODE));
        moveListener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        moveListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recBooking.getField(Booking.CURRENCYS_ID).addListener(moveListener);
        
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).setKeyArea(BookingDetail.BOOKING_ID_KEY);
        ((BookingSub)this.getRecord(BookingDetail.BOOKING_DETAIL_FILE)).addDetailBehaviors(recBooking, recTour);
        recBooking.addListener(new RequeryOnUpdateHandler(this.getRecord(BookingDetail.BOOKING_DETAIL_FILE)));
        
        this.getRecord(BookingLine.BOOKING_LINE_FILE).setKeyArea(BookingLine.BOOKING_ID_KEY);
        ((BookingLine)this.getRecord(BookingLine.BOOKING_LINE_FILE)).addDetailBehaviors(recBooking, recTour);
        recBooking.addListener(new RecountOnValidHandler(this.getRecord(BookingLine.BOOKING_LINE_FILE)));
                
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReferenceRecord(this);
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.TOUR_CLASS_ID)).getReferenceRecord(this);
        
        BaseField fldTourCode = this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.CODE);
        BaseField fldDepartureDate = this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE);
        BaseField fldTourDesc = this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.DESCRIPTION);
        
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
                return this.getRemoteTable(BookingDetail.BOOKING_DETAIL_FILE);
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
