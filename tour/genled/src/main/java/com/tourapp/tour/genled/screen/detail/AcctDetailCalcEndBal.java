/**
 * @(#)AcctDetailCalcEndBal.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.detail;

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

/**
 *  AcctDetailCalcEndBal - Recompute the end balance if start or change changes.
 */
public class AcctDetailCalcEndBal extends ReComputeFieldHandler
{
    protected Record m_recAcctDetailScreenRecord = null;
    /**
     * Default constructor.
     */
    public AcctDetailCalcEndBal()
    {
        super();
    }
    /**
     * Constructor.
     * @param iTargetFieldSeq The target field sequence to recompute on field change.
     */
    public AcctDetailCalcEndBal(Record recAcctDetailScreenRecord)
    {
        this();
        this.init(recAcctDetailScreenRecord);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recAcctDetailScreenRecord)
    {
        m_recAcctDetailScreenRecord = null;
        m_recAcctDetailScreenRecord = recAcctDetailScreenRecord;
        super.init(null, -1, AcctDetailScreenRecord.END_BALANCE, null);
    }
    /**
     * Compute the target value.
     * @param srcValue The value of this listener's owner.
     * @return The value to set the target field to.
     */
    public double computeValue(double dSrcValue)
    {
        double dEndValue = m_recAcctDetailScreenRecord.getField(AcctDetailScreenRecord.START_BALANCE).getValue();
        dEndValue += m_recAcctDetailScreenRecord.getField(AcctDetailScreenRecord.CHANGE_BALANCE).getValue();
        return dEndValue;
    }

}
