/**
 *  @(#)PastDueHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.pastdue;

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
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;

/**
 *  PastDueHandler - Check to see if this booking is past-due.
 */
public class PastDueHandler extends FileListener
{
    protected Record m_recPastDue = null;
    /**
     * Default constructor.
     */
    public PastDueHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PastDueHandler(Record recPastDue)
    {
        this();
        this.init(recPastDue);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recPastDue)
    {
        m_recPastDue = null;
        m_recPastDue = recPastDue;
        super.init(null);
    }
    /**
     * Set up/do the local criteria.
     * @param strbFilter The SQL query string to add to.
     * @param bIncludeFileName Include the file name with this query?
     * @param vParamList The param list to add the raw data to (for prepared statements).
     * @return True if you should not skip this record (does a check on the local data).
     */
    public boolean doLocalCriteria(StringBuffer strbFilter, boolean bIncludeFileName, Vector vParamList)
    {
        if (m_recPastDue.getField(PastDueScreenRecord.kCheckDep).getState() == true)
        { // Check for deposit past due
            if (this.getOwner().getField(Booking.kDepositDueDate).compareTo(m_recPastDue.getField(PastDueScreenRecord.kAsOfDate)) < 0)
            { // Deposit date in range
                // count it!
                double dNet = this.getOwner().getField(Booking.kNet).getValue();
                double dBalance = this.getOwner().getField(Booking.kBalance).getValue();
                double dAmountPaid = dNet - dBalance;
        
                double dDepositAmt = this.getOwner().getField(Booking.kDeposit).getValue();
                if (m_recPastDue.getField(PastDueScreenRecord.kMcoPer).getValue() > 0)
                {
                    if (dAmountPaid < dDepositAmt)
                        if (m_recPastDue.getField(PastDueScreenRecord.kMcoAmountPaid).getValue() < (dDepositAmt * (1.0 - m_recPastDue.getField(PastDueScreenRecord.kMcoPer).getValue())))
                            return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList);     // Deposit not paid, print record
                }
                else
                    if (dAmountPaid < dDepositAmt)
                        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList);     // Deposit not paid, print record
            }
        }
        if (m_recPastDue.getField(PastDueScreenRecord.kCheckFinal).getState() == true)
        { // Check for final pay past due
            if (this.getOwner().getField(Booking.kFinalPaymentDueDate).compareTo(m_recPastDue.getField(PastDueScreenRecord.kAsOfDate)) < 0)
            { // Deposit date in range
                double dBalance = this.getOwner().getField(Booking.kBalance).getValue();
                if (dBalance > 0)
                    return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList);   // Not fully paid, print it
            }
        }
        return false;   // Skip this record
    }

}
