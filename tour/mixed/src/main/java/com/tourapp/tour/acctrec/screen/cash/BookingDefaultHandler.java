/**
 * @(#)BookingDefaultHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.cash;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  BookingDefaultHandler - This listener sets the default amount and description for a booking
cash receipt..
 */
public class BookingDefaultHandler extends CopyStringHandler
{
    protected Record m_recArTrx = null;
    /**
     * Default constructor.
     */
    public BookingDefaultHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param (Don't pass the field param).
     */
    public BookingDefaultHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        m_recArTrx = null;
        super.init(field, null, null, null);
    }
    /**
     * Free the resources.
     */
    public void free()
    {
        if (m_recArTrx != null)
            m_recArTrx.free();
        m_recArTrx = null;
        super.free();
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        if (!this.getOwner().isNull())
        {
            Record recCashBatchDetail = this.getOwner().getRecord();
            if (recCashBatchDetail.getField(CashBatchDetail.kAmount).isNull())
                if (recCashBatchDetail.getField(CashBatchDetail.kComments).isNull())
            {
                Record recBooking = ((ReferenceField)recCashBatchDetail.getField(CashBatchDetail.kBookingID)).getReference();
                if (recBooking != null)
                { // Got a valid booking, see if this payment is a probably a deposit or final payment
                    boolean bFinalPayment = false;
                    if (recBooking.getField(Booking.kDepositReceived).getState() == true)
                        bFinalPayment = true;
                    else
                    {
                        Calendar calDate = ((DateTimeField)recBooking.getField(Booking.kFinalPaymentDueDate)).getCalendar();
                        if (calDate != null)
                        {
                            calDate.add(Calendar.DATE, -20);
                            Calendar calNow = Calendar.getInstance();
                            if (calDate.before(calNow))
                                bFinalPayment = true;   // Probably a final payment
                        }
                    }
                    String strComment = this.getComment(bFinalPayment);
                    double dAmount = 0;
                    if (bFinalPayment)
                    {   // Final payment
                        // First, total up the balance.
                        if (m_recArTrx == null)
                        {
                            RecordOwner recordOwner = Utility.getRecordOwner(this.getOwner().getRecord());
                            m_recArTrx = new ArTrx(recordOwner);
                            if (recordOwner != null)
                                recordOwner.removeRecord(m_recArTrx);
                            m_recArTrx.addListener(new SubFileFilter(recBooking));
                            m_recArTrx.addListener(new SubCountHandler(recBooking.getField(Booking.kBalance), ArTrx.kAmount, false, true));
                        }
                        try {
                            m_recArTrx.close();
                            boolean bNoEntries = true;
                            while (m_recArTrx.hasNext())
                            {
                                bNoEntries = false;
                                m_recArTrx.next();
                            }
                            if (bNoEntries)
                                dAmount = recBooking.getField(Booking.kNet).getValue();
                            else
                                dAmount = recBooking.getField(Booking.kBalance).getValue();
                        } catch (DBException ex)    {
                            ex.printStackTrace();
                        }
                    }
                    else
                    {   // Deposit
                        dAmount = recBooking.getField(Booking.kDeposit).getValue();
                    }
                    if (dAmount != 0)
                    {
                        recCashBatchDetail.getField(CashBatchDetail.kAmount).setValue(dAmount);
                        recCashBatchDetail.getField(CashBatchDetail.kComments).setString(strComment);
                    }
                }
            }
        }
        return DBConstants.NORMAL_RETURN;   // Don't call inherited
    }
    /**
     * Get the default comment for this payment.
     */
    public String getComment(boolean bFinalPayment)
    {
        String strComment = "Deposit";
        if (bFinalPayment)
            strComment = "Final payment";
        if (this.getOwner().getRecord().getRecordOwner() != null)
            if (this.getOwner().getRecord().getRecordOwner().getTask() != null)
                strComment = ((BaseApplication)this.getOwner().getRecord().getRecordOwner().getTask().getApplication()).getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(strComment);
        return strComment;
    }

}
