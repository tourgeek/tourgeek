/**
 * @(#)ChangeTourHeaderHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.tour;

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
import org.jbundle.thin.base.screen.print.thread.*;
import org.jbundle.thin.base.remote.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  ChangeTourHeaderHandler - Lookup tour hdr, get tour.
 */
public class ChangeTourHeaderHandler extends FieldListener
{
    protected BaseField m_fldDepDate = null;
    protected BaseField m_fldTourCode = null;
    protected BaseField m_fldTourDesc = null;
    protected Booking m_recBooking = null;
    protected Tour m_recTour = null;
    protected TourHeader m_recTourHeader = null;
    /**
     * Default constructor.
     */
    public ChangeTourHeaderHandler()
    {
        super();
    }
    /**
     * ChangeTourHeaderHandler Method.
     */
    public ChangeTourHeaderHandler(TourHeader recTourHeader, TourClass recTourClass, Tour recTour, Booking recBooking, BaseField fldTourCode, BaseField fldDepDate, BaseField fldTourDesc)
    {
        this();
        this.init(recTourHeader, recTourClass, recTour, recBooking, fldTourCode, fldDepDate, fldTourDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(TourHeader recTourHeader, TourClass recTourClass, Tour recTour, Booking recBooking, BaseField fldTourCode, BaseField fldDepDate, BaseField fldTourDesc)
    {
        m_fldDepDate = null;
        m_fldTourCode = null;
        m_fldTourDesc = null;
        m_recBooking = null;
        m_recTour = null;
        m_recTourHeader = null;
        m_recTourHeader = recTourHeader;
        m_recTour = recTour;
        m_recBooking = recBooking;
        m_fldTourCode = fldTourCode;
        m_fldDepDate = fldDepDate;
        m_fldTourDesc = fldTourDesc;
        
        super.init(null);
        
        m_bReadMove = false;  // Check on user only!
        m_bInitMove = false;
    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (owner != null)
            if (this.getOwner().getListener() != this)  // Prevents endless loop (listener is already the first in the list)
                if (this.getOwner().getRecord() == m_recTour)
        {   // This is special weird logic - This must be the first in the listener list
            this.getOwner().removeListener(this, false);
            super.setOwner(owner);  // Set it back
            this.setNextListener(this.getOwner().getListener());
            this.getOwner().setListener(this);  // This MUST be the first listener on the list.
        }
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if ((m_recTourHeader.getEditMode() != DBConstants.EDIT_CURRENT)
            && (m_recTourHeader.getEditMode() != DBConstants.EDIT_IN_PROGRESS))
                iErrorCode = ((TourHeader)m_recTourHeader).lookupTourHdr(m_fldTourCode, m_fldDepDate, m_fldTourDesc, bDisplayOption);
        if ((iErrorCode == DBConstants.NORMAL_RETURN) & (m_fldDepDate.getLength() != 0))
        {   // Set up a new tour or lookup the series tour!!!
            BookingAnswer recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
            BookingPax recBookingPax = null;
            Date dateStart = ((DateTimeField)m_recTour.getField(Tour.DEPARTURE_DATE)).getDateTime();  // Use tour departure date.
            
            if ((m_recBooking == null)
                || (m_recBooking.getField(Booking.TOUR_ID).getLength() == 0)
                || (m_recTour.getField(Tour.TOUR_HEADER_ID).compareTo(m_recTourHeader.getField(TourHeader.ID)) != 0))
            {
                String strCode = DBConstants.BLANK;
                String strDesc = DBConstants.BLANK;
                if (m_recBooking != null)
                {
                    boolean[] rgbEnabled = null;
                    if (m_recBooking != null)
                        rgbEnabled = m_recBooking.getField(Booking.TOUR_ID).setEnableListeners(false);  // Since it is possible that Booking will refresh which would clear the tour record
                    m_recBooking.setupDefaultDesc(m_recTourHeader, m_fldDepDate);
                    if (rgbEnabled != null)
                        m_recBooking.getField(Booking.TOUR_ID).setEnableListeners(rgbEnabled);
                    strCode = m_recBooking.getField(Booking.CODE).toString();
                    strDesc = m_recBooking.getField(Booking.DESCRIPTION).toString();
                }
                iErrorCode = m_recTour.setupTourFromHeader(m_recTourHeader, m_fldDepDate, strCode, strDesc);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    return iErrorCode;
                if (m_recBooking == null)
                    return iErrorCode;  // No need to do the booking updates
                m_recBooking.getField(Booking.TOUR_ID).moveFieldToThis(m_recTour.getField(Tour.ID));
        
                if (this.getOwner().getRecord().getTask() instanceof SyncPage)
                {
                    SwingSyncPageWorker worker = new SwingSyncPageWorker(((SyncPage)this.getOwner().getRecord().getTask()), true)
                    {
                        public void done()
                        { // 'Please wait...' is done displaying
                            String strCommand = "addTourDetail";
                            Map<String,Object> properties = new HashMap<String,Object>();
                            properties.put(TourHeader.TOUR_HEADER_FILE, m_recTourHeader.getCounterField().getData());
                            properties.put(m_recTour.getField(Tour.DEPARTURE_DATE).getFieldName(), m_recTour.getField(Tour.DEPARTURE_DATE).getData());
                            Object objReturn;
                            try {
                                objReturn = m_recBooking.handleRemoteCommand(strCommand, properties);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                objReturn = Boolean.FALSE;
                            } catch (DBException e) {
                                e.printStackTrace();
                                objReturn = Boolean.FALSE;
                            }
                            
                            if (Boolean.FALSE.equals(objReturn))
                            {                           
                                BookingAnswer recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
                                BookingPax recBookingPax = null;
                                Date dateStart = ((DateTimeField)m_recTour.getField(Tour.DEPARTURE_DATE)).getDateTime();  // Use tour departure date.
                                ((Booking)m_recBooking).addTourDetail(m_recTour, m_recTourHeader, recBookingPax, recBookingAnswer, dateStart, m_recBooking.getField(Booking.ASK_FOR_ANSWER));
                            }
                        }
                    };
                    worker.start();
                }
                else
                    iErrorCode = ((Booking)m_recBooking).addTourDetail(m_recTour, m_recTourHeader, recBookingPax, recBookingAnswer, dateStart, m_recBooking.getField(Booking.ASK_FOR_ANSWER));
            }
            else
            {
                m_recTour.calcTourDates(m_recTourHeader);
                final Date dateOriginal = (Date)((FieldDataScratchHandler)m_recTour.getField(Tour.DEPARTURE_DATE).getListener(FieldDataScratchHandler.class)).getOriginalData();
                if (this.getOwner().getRecord().getTask() instanceof SyncPage)
                {
                    SwingSyncPageWorker worker = new SwingSyncPageWorker(((SyncPage)this.getOwner().getRecord().getTask()), true)
                    {
                        public void done()
                        {
                            BookingAnswer recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
                            BookingPax recBookingPax = null;
                            Date dateStart = ((DateTimeField)m_recTour.getField(Tour.DEPARTURE_DATE)).getDateTime();  // Use tour departure date.
                            m_recBooking.changeTourDetail(m_recTour, recBookingPax, m_recTourHeader, dateOriginal, dateStart);
                        }
                    };
                    worker.start();
                }
                else
                    iErrorCode = m_recBooking.changeTourDetail(m_recTour, recBookingPax, m_recTourHeader, dateOriginal, dateStart);
            }
            if (iErrorCode == DBConstants.NORMAL_RETURN)
            {
                if (m_recBooking.getField(Booking.DESCRIPTION).isNull())    // Usually Series Tours since they don't set up booking desc.
                    m_recBooking.setupDefaultDesc(m_recTourHeader, m_fldDepDate);
                ((Booking)m_recBooking).calcBookingDates(m_recTour, m_recTourHeader);
            }
        }
        else
        {
            if (m_fldDepDate.getLength() == 0) 
                iErrorCode = DBConstants.NORMAL_RETURN;
            if (m_fldTourCode != null) if (m_fldTourCode.getLength() == 0) if (m_fldTourDesc == null)
                iErrorCode = DBConstants.NORMAL_RETURN;
            if (m_fldTourDesc != null) if (m_fldTourDesc.getLength() == 0) if (m_fldTourCode == null)
                iErrorCode = DBConstants.NORMAL_RETURN;
            if (m_fldTourCode != null) if (m_fldTourCode.getLength() == 0)
                if (m_fldTourDesc != null) if (m_fldTourDesc.getLength() == 0)
                    iErrorCode = DBConstants.NORMAL_RETURN;
            if ((m_recBooking == null)
                || (m_recBooking.getField(Booking.TOUR_ID).getLength() != 0))
            {   // Clear out the current tour
                try   {
                    m_recTour.addNew();
                    m_recBooking.getField(Booking.TOUR_ID).initField(DBConstants.DISPLAY);
                    m_recBooking.getField(Booking.FINAL_PAYMENT_DUE_DATE).initField(DBConstants.DISPLAY);
                    m_recBooking.getField(Booking.DEPOSIT_DUE_DATE).initField(DBConstants.DISPLAY);
                    m_recBooking.getField(Booking.BOOKING_STATUS_ID).initField(DBConstants.DISPLAY);
                } catch (DBException e)   {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return iErrorCode;
    }

}
