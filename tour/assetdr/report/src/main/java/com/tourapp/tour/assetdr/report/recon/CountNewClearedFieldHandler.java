/**
  * @(#)CountNewClearedFieldHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.assetdr.report.recon;

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

/**
 *  CountNewClearedFieldHandler - .
 */
public class CountNewClearedFieldHandler extends ReComputeFieldHandler
{
    protected BaseField m_fldCreditsCleared = null;
    protected BaseField m_fldCurrentCleared = null;
    protected BaseField m_fldDepositsCleared = null;
    /**
     * Default constructor.
     */
    public CountNewClearedFieldHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param iTargetFieldSeq The target field sequence to recompute on field change.
     */
    public CountNewClearedFieldHandler(String iTargetFieldSeq)
    {
        this();
        this.init(iTargetFieldSeq);
    }
    /**
     * Initialize class fields.
     */
    public void init(String iTargetFieldSeq)
    {
        m_fldCreditsCleared = null;
        m_fldCurrentCleared = null;
        m_fldDepositsCleared = null;
        super.init(null, iTargetFieldSeq, null);
    }
    /**
     * CheckFields Method.
     */
    public void checkFields()
    {
        if (m_fldCurrentCleared == null)
        {
            Record record = this.getOwner().getRecord();
            m_fldCurrentCleared = record.getField(BankReconScreenRecord.START_CLEARED);
            m_fldDepositsCleared = record.getField(BankReconScreenRecord.DEPOSITS_CLEARED);
            m_fldCreditsCleared = record.getField(BankReconScreenRecord.CHECKS_CLEARED);
        }

    }
    /**
     * Compute the target value.
     * @param srcValue The value of this listener's owner.
     * @return The value to set the target field to.
     */
    public double computeValue(double dSrcValue)
    {
        this.checkFields();
        double dCurrentCleared = m_fldCurrentCleared.getValue();
        double dDepositsCleared = m_fldDepositsCleared.getValue();
        double dCreditsCleared = m_fldCreditsCleared.getValue();
        return dCurrentCleared + dDepositsCleared + dCreditsCleared;
    }

}
