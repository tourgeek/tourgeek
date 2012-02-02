/**
 * @(#)CalcPlanTotals.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.cashplan;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.event.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  CalcPlanTotals - Calculate the total for the cash plan report.
 */
public class CalcPlanTotals extends FileListener
{
    protected ScreenRecord m_recCashPlan = null;
    /**
     * Default constructor.
     */
    public CalcPlanTotals()
    {
        super();
    }
    /**
     * CalcPlanTotals Method.
     */
    public CalcPlanTotals(ScreenRecord recCashPlan)
    {
        this();
        this.init(recCashPlan);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenRecord recCashPlan)
    {
        m_recCashPlan = null;
        m_recCashPlan = recCashPlan;
        super.init(null);
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
        double mainValue, currentValue;
        int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);   // Initialize the record
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        if (iChangeType == DBConstants.MOVE_NEXT_TYPE)
        {
            Record recBooking = this.getOwner();
        
            double dDeposit = recBooking.getField(Booking.DEPOSIT).getValue();
            double dGross = recBooking.getField(Booking.GROSS).getValue();
            double dNet = recBooking.getField(Booking.NET).getValue();
            double dBalance = Math.max(recBooking.getField(Booking.BALANCE).getValue(), 0);
        
            double dAmountPaid = dNet - dBalance;
        
            double dDepositDue = Math.min(dDeposit, Math.max(0, dDeposit - dAmountPaid));
        
            double dFinalBalanceDue = Math.max(dNet - dDeposit, 0);
            double dCurrentBalanceDue = Math.min(dFinalBalanceDue, dBalance);
        
            m_recCashPlan.getField(CashPlanScreenRecord.DEPOSIT_DUE_BALANCE).setValue(dDepositDue);
            m_recCashPlan.getField(CashPlanScreenRecord.FINAL_DUE_LESS_DEPOSIT).setValue(dFinalBalanceDue);
            m_recCashPlan.getField(CashPlanScreenRecord.FINAL_DUE_LESS_DEP_PYMT).setValue(dCurrentBalanceDue);
        }
        return iErrorCode;
    }

}
