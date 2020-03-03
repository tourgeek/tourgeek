/**
  * @(#)PastDueHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.report.pastdue;

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
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import org.bson.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.main.db.*;
import org.jbundle.base.screen.model.util.*;

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
    public boolean doLocalCriteria(StringBuffer strbFilter, boolean bIncludeFileName, Vector vParamList, Document doc)
    {
        if (m_recPastDue.getField(PastDueScreenRecord.CHECK_DEP).getState() == true)
        { // Check for deposit past due
            if (this.getOwner().getField(Booking.DEPOSIT_DUE_DATE).compareTo(m_recPastDue.getField(PastDueScreenRecord.AS_OF_DATE)) < 0)
            { // Deposit date in range
                // count it!
                double dNet = this.getOwner().getField(Booking.NET).getValue();
                double dBalance = this.getOwner().getField(Booking.BALANCE).getValue();
                double dAmountPaid = dNet - dBalance;
        
                double dDepositAmt = this.getOwner().getField(Booking.DEPOSIT).getValue();
                if (m_recPastDue.getField(PastDueScreenRecord.MCO_PER).getValue() > 0)
                {
                    if (dAmountPaid < dDepositAmt)
                        if (m_recPastDue.getField(PastDueScreenRecord.MCO_AMOUNT_PAID).getValue() < (dDepositAmt * (1.0 - m_recPastDue.getField(PastDueScreenRecord.MCO_PER).getValue())))
                            return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);        // Deposit not paid, print record
                }
                else
                    if (dAmountPaid < dDepositAmt)
                        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);        // Deposit not paid, print record
            }
        }
        if (m_recPastDue.getField(PastDueScreenRecord.CHECK_FINAL).getState() == true)
        { // Check for final pay past due
            if (this.getOwner().getField(Booking.FINAL_PAYMENT_DUE_DATE).compareTo(m_recPastDue.getField(PastDueScreenRecord.AS_OF_DATE)) < 0)
            { // Deposit date in range
                double dBalance = this.getOwner().getField(Booking.BALANCE).getValue();
                if (dBalance > 0)
                    return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc); // Not fully paid, print it
            }
        }
        return false;   // Skip this record
    }

}
