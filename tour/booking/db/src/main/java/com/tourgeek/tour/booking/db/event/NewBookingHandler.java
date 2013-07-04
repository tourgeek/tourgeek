
package com.tourgeek.tour.booking.db.event;

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
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.booking.detail.db.*;

/**
 *  NewBookingHandler - If a new booking is created because a new booking detail was added,
I need to initialize the new booking record..
 */
public class NewBookingHandler extends FileListener
{
    protected boolean m_bUseThinTourHeader = false;
    /**
     * Default constructor.
     */
    public NewBookingHandler()
    {
        super();
    }
    /**
     * NewBookingHandler Method.
     */
    public NewBookingHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        if ((iChangeType == DBConstants.ADD_TYPE) || (iChangeType == DBConstants.UPDATE_TYPE) || (iChangeType == DBConstants.AFTER_REFRESH_TYPE))
        {
            Booking recBooking = (Booking)this.getOwner();
            // This code is only run when this record is added because of a BookingDetail add
            Date date = null;   // Departure date
            // First look through the dependent listeners for a subFileFilter
            BaseListener nextListener = recBooking.getListener(FileRemoveBOnCloseHandler.class.getName());
            while (nextListener != null)
            {
                if (((FileRemoveBOnCloseHandler)nextListener).getDependentListener() instanceof SubFileFilter)
                {
                    SubFileFilter subFileFilter = (SubFileFilter)((FileRemoveBOnCloseHandler)nextListener).getDependentListener();
                    if (subFileFilter.getOwner() instanceof BookingDetail)
                    {
        //x              if ((subFileFilter.getOwner().getEditMode() != DBConstants.EDIT_NONE) && (subFileFilter.getOwner().getEditMode() != DBConstants.EDIT_ADD))
                        {
                            date = ((DateTimeField)subFileFilter.getOwner().getField(BookingDetail.DETAIL_DATE)).getDateTime();
                            if (date == null)
                                date = ((DateTimeField)subFileFilter.getOwner().getTable().getCurrentTable().getRecord().getField(BookingDetail.DETAIL_DATE)).getDateTime();
                            if (date != null)
                                break;  // Found, done
                        }
                    }
                }
                nextListener = nextListener.getListener(FileRemoveBOnCloseHandler.class.getName());
            }
            if (date != null)
            {
                RecordOwner recordOwner = recBooking.getRecordOwner();
                Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
                TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReference();
                
                int iErrorCode = DBConstants.NORMAL_RETURN;
                if ((recTourHeader == null)
                    || ((recTourHeader.getEditMode() == DBConstants.EDIT_ADD) || (recTourHeader.getEditMode() == DBConstants.EDIT_NONE)))
                {   // No tour header, use the default tour header
                    if (recBooking.getField(Booking.TOUR_ID).isNull())
                    {
                        Record recBookingControl = (Record)recordOwner.getRecord(BookingControl.BOOKING_CONTROL_FILE);
                        if (m_bUseThinTourHeader)
                            recTourHeader = (TourHeader)((ReferenceField)recBookingControl.getField(BookingControl.THIN_TOUR_HEADER_ID)).getReference();
                       else
                            recTourHeader = (TourHeader)((ReferenceField)recBookingControl.getField(BookingControl.TOUR_HEADER_ID)).getReference();
                        if ((recTourHeader == null)
                            || ((recTourHeader.getEditMode() != DBConstants.EDIT_CURRENT) && (recTourHeader.getEditMode() != DBConstants.EDIT_IN_PROGRESS)))
                                return recordOwner.getTask().setLastError("Must have a default tour header selected in the Booking Control file");
                    }
                }
                else
                {
                    if (!recBooking.getField(Booking.TOUR_ID).isNull())
                        return DBConstants.NORMAL_RETURN;      // A tour is already set up for this booking
                }
                    // Setup this tour from a top-level tour header
                DateField fldDepDate = new DateField(null, null, DBConstants.DEFAULT_FIELD_LENGTH, null, null);
                fldDepDate.setDateTime(date, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                if (recBooking.getField(Booking.TOUR_ID).isNull())
                    iErrorCode = recTour.setupTourFromHeader(recTourHeader, fldDepDate, DBConstants.BLANK, DBConstants.BLANK);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                {
                    fldDepDate.free();
                    return iErrorCode;
                }
                try {
                    if ((recBooking.getEditMode() == DBConstants.EDIT_ADD) || (recBooking.getEditMode() == DBConstants.EDIT_NONE))
                    {   // Never (since updating the tour refreshes and locks a new record)
                        boolean[] rgbEnabled = recBooking.setEnableListeners(false);
                        Object[] rgobjEnabled = recBooking.setEnableFieldListeners(false);
                        recBooking.addNew();
                        recBooking.setEnableListeners(rgbEnabled);
                        recBooking.setEnableFieldListeners(rgobjEnabled);
                    }
                    else
                        recBooking.edit();
                recBooking.calcBookingDates(recTour, recTourHeader);
                recBooking.getField(Booking.TOUR_ID).moveFieldToThis(recTour.getField(Tour.ID));
                recBooking.setupDefaultDesc(recTourHeader, fldDepDate);
                recBooking.getField(Booking.CODE).handleFieldChanged(DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                iErrorCode = recBooking.addTourDetail(recTour, recTourHeader, null, null, fldDepDate.getDateTime(), recBooking.getField(Booking.ASK_FOR_ANSWER));
                fldDepDate.free();
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    return iErrorCode;
                if ((recBooking.getEditMode() == DBConstants.EDIT_ADD) || (recBooking.getEditMode() == DBConstants.EDIT_NONE))
                    recBooking.add();
                else
                    recBooking.set();
                Object bookmark = recBooking.getLastModified(DBConstants.OBJECT_ID_HANDLE);    // Success
                recBooking.setHandle(bookmark, DBConstants.OBJECT_ID_HANDLE);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }
    /**
     * UseThinTourHeader Method.
     */
    public void useThinTourHeader(boolean bUseThinTourHeader)
    {
        m_bUseThinTourHeader = bUseThinTourHeader;
    }

}
