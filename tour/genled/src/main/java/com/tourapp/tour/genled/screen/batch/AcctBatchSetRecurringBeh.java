/**
 * @(#)AcctBatchSetRecurringBeh.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.batch;

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
import com.tourapp.tour.genled.db.*;
import org.jbundle.main.screen.*;
import java.util.*;
import org.jbundle.thin.base.screen.*;

/**
 *  AcctBatchSetRecurringBeh - If there are recurring entries, set the default recurring date.
If not, disable the date field..
 */
public class AcctBatchSetRecurringBeh extends FieldListener
{
    protected Record m_recAcctBatch = null;
    protected Period m_recPeriod = null;
    /**
     * Default constructor.
     */
    public AcctBatchSetRecurringBeh()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctBatchSetRecurringBeh(Record recAcctBatch)
    {
        this();
        this.init(recAcctBatch);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recAcctBatch)
    {
        m_recAcctBatch = null;
        m_recPeriod = null;
        m_recAcctBatch = recAcctBatch;
        super.init(null);
    }
    /**
     * Remember to close the period.
     */
    public void free()
    {
        if (m_recPeriod != null)
            m_recPeriod.close();
        m_recPeriod = null;
        super.free();
    }
    /**
     * If the field changed, check for recurring.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        BaseField fldAccrual = m_recAcctBatch.getField(AcctBatch.kAutoReversal);
        DateField fldAccrualDate = (DateField)m_recAcctBatch.getField(AcctBatch.kAutoReversalDate);
        DateField fldTrxDate = (DateField)m_recAcctBatch.getField(AcctBatch.kTrxDate);
        if (fldAccrual.getState() == false)
        {
            fldAccrualDate.setString(Constants.BLANK);
            fldAccrualDate.setEnabled(false);
        }
        else
        {
            fldAccrualDate.setEnabled(true);
            if (!fldTrxDate.isNull())
            {
                if (m_recPeriod == null)
                {
                    m_recPeriod = new Period(this.getOwner().getRecord().findRecordOwner());
                    if (m_recPeriod.getRecordOwner() != null)
                        m_recPeriod.getRecordOwner().removeRecord(m_recPeriod);
                }
                Date date = fldTrxDate.getDateTime();
                date = m_recPeriod.getPeriodEndDate(date);
                fldAccrualDate.setDate(date, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                Calendar calendar = fldAccrualDate.getCalendar();
                calendar.add(Calendar.DATE, 1);
                fldAccrualDate.setCalendar(calendar, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);   // Start of next period
            }
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
