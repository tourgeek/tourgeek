/**
 * @(#)CountClearedHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report.recon;

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

/**
 *  CountClearedHandler - Count the amount of deposits or credits cleared in the current grid screen..
 */
public class CountClearedHandler extends SubCountHandler
{
    protected boolean m_bCountPositive;
    protected int m_fsReconciled;
    /**
     * Default constructor.
     */
    public CountClearedHandler()
    {
        super();
    }
    /**
     * Count a sub-field.
     */
    public CountClearedHandler(BaseField fieldMain, int ifsToCount, int fsReconciled, boolean bCountPositive)
    {
        this();
        this.init(fieldMain, ifsToCount, fsReconciled, bCountPositive);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fieldMain, int ifsToCount, int fsReconciled, boolean bCountPositive)
    {
        m_bCountPositive = true;
        m_fsReconciled = 0;
        m_fsReconciled = fsReconciled;
        m_bCountPositive = bCountPositive;
        super.init(null, null, -1, fieldMain, ifsToCount, true, false, false);
    }
    /**
     * Get the value to add (Overidden from SubCountHandler).
     * If there was a field specified, return the value, otherwise just return a count of 1.
     * @return The field value.
     */
    public double getFieldValue()
    {
        BaseField m_fldReconciled = this.getOwner().getField(m_fsReconciled);
        if ((m_fldReconciled.isNull()) || (m_fldReconciled.getLength() == 0))
            return 0.00;
        double dFieldValue = super.getFieldValue();
        if ((m_bCountPositive) && (dFieldValue > 0))
            return dFieldValue;
        if ((!m_bCountPositive) && (dFieldValue < 0))
            return dFieldValue;
        return 0.00;
    }

}
