/**
  * @(#)ChangeTourHeaderHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.base.event;

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
import org.jbundle.thin.base.screen.print.thread.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.thin.base.thread.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.model.tour.booking.db.*;

/**
 *  ChangeTourHeaderHandler - Lookup tour hdr, get tour.
 */
public class ChangeTourHeaderHandler extends FieldListener
{
    protected BaseField m_fldDepDate = null;
    protected BaseField m_fldTourCode = null;
    protected BaseField m_fldTourDesc = null;
    protected BookingModel m_recBooking = null;
    protected TourModel m_recTour = null;
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
    public ChangeTourHeaderHandler(TourHeader recTourHeader, TourClass recTourClass, TourModel recTour, BookingModel recBooking, BaseField fldTourCode, BaseField fldDepDate, BaseField fldTourDesc)
    {
        this();
        this.init(recTourHeader, recTourClass, recTour, recBooking, fldTourCode, fldDepDate, fldTourDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(TourHeader recTourHeader, TourClass recTourClass, TourModel recTour, BookingModel recBooking, BaseField fldTourCode, BaseField fldDepDate, BaseField fldTourDesc)
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
            BookingAnswerModel recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
            BookingPaxModel recBookingPax = null;
            Date dateStart = ((DateTimeField)m_recTour.getField(TourModel.DEPARTURE_DATE)).getDateTime();  // Use tour departure date.
            
            if ((m_recBooking == null)
                || (m_recBooking.getField(BookingModel.TOUR_ID).getLength() == 0)
                || (m_recTour.getField(TourModel.TOUR_HEADER_ID).compareTo(m_recTourHeader.getField(TourHeader.ID)) != 0))
            {
                String strCode = DBConstants.BLANK;
                String strDesc = DBConstants.BLANK;
                if (m_recBooking != null)
                {
                    boolean[] rgbEnabled = null;
                    if (m_recBooking != null)
                        rgbEnabled = ((Record)m_recBooking).getField(BookingModel.TOUR_ID).setEnableListeners(false);  // Since it is possible that Booking will refresh which would clear the tour record
                    m_recBooking.setupDefaultDesc(m_recTourHeader, m_fldDepDate);
                    if (rgbEnabled != null)
                        ((Record)m_recBooking).getField(BookingModel.TOUR_ID).setEnableListeners(rgbEnabled);
                    strCode = m_recBooking.getField(BookingModel.CODE).toString();
                    strDesc = m_recBooking.getField(BookingModel.DESCRIPTION).toString();
                }
                iErrorCode = m_recTour.setupTourFromHeader(m_recTourHeader, m_fldDepDate, strCode, strDesc);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    return iErrorCode;
                if (m_recBooking == null)
                    return iErrorCode;  // No need to do the booking updates
                ((Record)m_recBooking).getField(BookingModel.TOUR_ID).moveFieldToThis(((Record)m_recTour).getField(TourModel.ID));
        
                if (this.getOwner().getRecord().getTask() instanceof SyncPage)
                {
                    SwingSyncPageWorker worker = new SwingSyncPageWorker(((SyncPage)this.getOwner().getRecord().getTask()), true)
                    {
                        public void done()
                        { // 'Please wait...' is done displaying
                            String strCommand = "addTourDetail";
                            Map<String,Object> properties = new HashMap<String,Object>();
                            properties.put(TourHeader.TOUR_HEADER_FILE, m_recTourHeader.getCounterField().getData());
                            properties.put(m_recTour.getField(TourModel.DEPARTURE_DATE).getFieldName(), m_recTour.getField(TourModel.DEPARTURE_DATE).getData());
                            Object objReturn;
                            try {
                                objReturn = ((Record)m_recBooking).handleRemoteCommand(strCommand, properties);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                objReturn = Boolean.FALSE;
                            } catch (DBException e) {
                                e.printStackTrace();
                                objReturn = Boolean.FALSE;
                            }
                            
                            if (Boolean.FALSE.equals(objReturn))
                            {                           
                                BookingAnswerModel recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
                                BookingPaxModel recBookingPax = null;
                                Date dateStart = ((DateTimeField)m_recTour.getField(TourModel.DEPARTURE_DATE)).getDateTime();  // Use tour departure date.
                                ((BookingModel)m_recBooking).addTourDetail(m_recTour, m_recTourHeader, recBookingPax, recBookingAnswer, dateStart, m_recBooking.getField(BookingModel.ASK_FOR_ANSWER));
                            }
                        }
                    };
                    worker.start();
                }
                else
                    iErrorCode = ((BookingModel)m_recBooking).addTourDetail(m_recTour, m_recTourHeader, recBookingPax, recBookingAnswer, dateStart, m_recBooking.getField(BookingModel.ASK_FOR_ANSWER));
            }
            else
            {
                m_recTour.calcTourDates(m_recTourHeader);
                final Date dateOriginal = (Date)((FieldDataScratchHandler)((Record)m_recTour).getField(TourModel.DEPARTURE_DATE).getListener(FieldDataScratchHandler.class)).getOriginalData();
                if (this.getOwner().getRecord().getTask() instanceof SyncPage)
                {
                    SwingSyncPageWorker worker = new SwingSyncPageWorker(((SyncPage)this.getOwner().getRecord().getTask()), true)
                    {
                        public void done()
                        {
                            BookingAnswerModel recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
                            BookingPaxModel recBookingPax = null;
                            Date dateStart = ((DateTimeField)m_recTour.getField(TourModel.DEPARTURE_DATE)).getDateTime();  // Use tour departure date.
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
                if (m_recBooking.getField(BookingModel.DESCRIPTION).isNull())    // Usually Series Tours since they don't set up booking desc.
                    m_recBooking.setupDefaultDesc(m_recTourHeader, m_fldDepDate);
                ((BookingModel)m_recBooking).calcBookingDates(m_recTour, m_recTourHeader);
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
                || (m_recBooking.getField(BookingModel.TOUR_ID).getLength() != 0))
            {   // Clear out the current tour
                try   {
                    m_recTour.getTable().addNew();
                    m_recBooking.getField(BookingModel.TOUR_ID).initField(DBConstants.DISPLAY);
                    m_recBooking.getField(BookingModel.FINAL_PAYMENT_DUE_DATE).initField(DBConstants.DISPLAY);
                    m_recBooking.getField(BookingModel.DEPOSIT_DUE_DATE).initField(DBConstants.DISPLAY);
                    m_recBooking.getField(BookingModel.BOOKING_STATUS_ID).initField(DBConstants.DISPLAY);
                } catch (DBException e)   {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return iErrorCode;
    }

}
