/**
 * @(#)BookingCancelledEventHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.acctrec.screen.refund.*;

/**
 *  BookingCancelledEventHandler - Do the booking related cancellation stuff,
such as assessing the cancellation charge
and creating the refund pending..
 */
public class BookingCancelledEventHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public BookingCancelledEventHandler()
    {
        super();
    }
    /**
     * BookingCancelledEventHandler Method.
     */
    public BookingCancelledEventHandler(BaseField field)
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
        if (this.getOwner().getState() == true)
        {
            Booking recBooking = (Booking)this.getOwner().getRecord();
            ArTrx recArTrx = (ArTrx)recBooking.addArDetail(null, null, true);
            if (recBooking.getField(Booking.BALANCE).getValue() != recBooking.getField(Booking.NET).getValue())
            {   // Create a cancellation charge
                Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
                if (recTour != null)
                    if ((recTour.getEditMode() == DBConstants.EDIT_CURRENT) || (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                {
                    Record recTourHeader = ((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReference();
                    if (recTourHeader != null)
                        if ((recTourHeader.getEditMode() == DBConstants.EDIT_CURRENT) || (recTourHeader.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                    {
                        Record recTourClass = ((ReferenceField)recTourHeader.getField(TourHeader.TOUR_CLASS_ID)).getReference();
                        if (recTourClass != null)
                            if ((recTourClass.getEditMode() == DBConstants.EDIT_CURRENT) || (recTourClass.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                        {
                            double dCancellationCharge = recTourClass.getField(TourClass.CANCEL_CHARGE).getValue();
                            double dAmountPaid = recBooking.getField(Booking.NET).getValue() - recBooking.getField(Booking.BALANCE).getValue();
                            dCancellationCharge = Math.min(dCancellationCharge, dAmountPaid);
                            try {
                                TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recArTrx.getField(ArTrx.TRX_STATUS_ID)).getReferenceRecord();
                                int iInvoiceModification = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.INVOICE_MODIFICATION);
                                int iRefundPendingTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.REFUND_SUBMITTED);
                                int iCancelTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.CANCELLATION_CHARGE);
                                double dDate = DateTimeField.currentTime();
                                
                                // Cancel all charges
                                recArTrx.addNew();
                                recArTrx.getField(ArTrx.AMOUNT).setValue(-recBooking.getField(Booking.NET).getValue());
                                ((DateTimeField)recArTrx.getField(ArTrx.TRX_DATE)).setValue(dDate);
                                recArTrx.getField(ArTrx.TRX_STATUS_ID).setValue(iInvoiceModification);
                                recArTrx.getField(ArTrx.COMMENTS).moveFieldToThis(((ReferenceField)recArTrx.getField(ArTrx.TRX_STATUS_ID)).getReference().getField(TrxStatus.STATUS_DESC));
                                recArTrx.add();
                                // Add cancellation charge
                                if (dCancellationCharge > 0)
                                {
                                    boolean bOldState = recArTrx.getListener(UpdateArTrxAcctDetailHandler.class, true).setEnabledListener(false);    // Since I will be doing the updating
                                    recArTrx.addListener(new UpdateCancelationAcctDetailHandler(recBooking));
                                    recArTrx.addNew();
                                    recArTrx.getField(ArTrx.AMOUNT).setValue(dCancellationCharge);
                                    dDate = dDate + 1000;   // One second later
                                    ((DateTimeField)recArTrx.getField(ArTrx.TRX_DATE)).setValue(dDate);
                                    recArTrx.getField(ArTrx.TRX_STATUS_ID).setValue(iCancelTrxStatus);
                                    recArTrx.getField(ArTrx.COMMENTS).moveFieldToThis(((ReferenceField)recArTrx.getField(ArTrx.TRX_STATUS_ID)).getReference().getField(TrxStatus.STATUS_DESC));
                                    recArTrx.add();
                                    recArTrx.removeListener(recArTrx.getListener(UpdateCancelationAcctDetailHandler.class), true);
                                    recArTrx.getListener(UpdateArTrxAcctDetailHandler.class, true).setEnabledListener(bOldState);
                                }
                                // Setup refund
                                double dRefund = Math.floor((dAmountPaid - dCancellationCharge) * 100 + 0.5) / 100;
                                if (dRefund > 0)
                                {
                                    boolean bOldState = recArTrx.getListener(UpdateArTrxAcctDetailHandler.class, true).setEnabledListener(false);    // Since I will be doing the updating
                                    recArTrx.addListener(new UpdateRefundAcctDetailHandler(recBooking));
                                    recArTrx.addNew();
                                    recArTrx.getField(ArTrx.AMOUNT).setValue(dRefund);
                                    dDate = dDate + 1000;   // One second later
                                    ((DateTimeField)recArTrx.getField(ArTrx.TRX_DATE)).setValue(dDate);
                                    recArTrx.getField(ArTrx.TRX_STATUS_ID).setValue(iRefundPendingTrxStatus);
                                    recArTrx.getField(ArTrx.COMMENTS).moveFieldToThis(((ReferenceField)recArTrx.getField(ArTrx.TRX_STATUS_ID)).getReference().getField(TrxStatus.STATUS_DESC));
                                    recArTrx.add();
                                    recArTrx.removeListener(recArTrx.getListener(UpdateRefundAcctDetailHandler.class), true);
                                    recArTrx.getListener(UpdateArTrxAcctDetailHandler.class, true).setEnabledListener(bOldState);
                                }
                            } catch (DBException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    
                }
            }
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
