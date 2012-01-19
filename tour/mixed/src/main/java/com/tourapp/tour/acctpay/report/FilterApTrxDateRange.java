/**
 * @(#)FilterApTrxDateRange.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  FilterApTrxDateRange - Filter based on the departure date range.
Note: Can't use ExtractRange singe ApTrx.kDepartureDate is virtual..
 */
public class FilterApTrxDateRange extends FileFilter
{
    protected BaseField m_fldEnd = null;
    protected BaseField m_fldStart = null;
    protected int m_iFieldSeq = -1;
    /**
     * Default constructor.
     */
    public FilterApTrxDateRange()
    {
        super();
    }
    /**
     * FilterApTrxDateRange Method.
     */
    public FilterApTrxDateRange(int iFieldSeq, BaseField fldStart, BaseField fldEnd)
    {
        this();
        this.init(iFieldSeq, fldStart, fldEnd);
    }
    /**
     * Initialize class fields.
     */
    public void init(int iFieldSeq, BaseField fldStart, BaseField fldEnd)
    {
        super.init(null);
        m_iFieldSeq = iFieldSeq;
        m_fldStart = fldStart;
        m_fldEnd = fldEnd;
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
        boolean bFlag = super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList);
        if (bFlag == true)
        {
            BaseField fldDepartureDate = this.getOwner().getField(m_iFieldSeq);
            boolean[] rgbEnabled = fldDepartureDate.setEnableListeners(true);
            if (!m_fldStart.isNull())
            {
                if ((fldDepartureDate.getData() != null) && (fldDepartureDate.compareTo(m_fldStart) >= 0))
                    bFlag = true;
                else
                    bFlag = false;
            }
            if (bFlag == true)
                if (!m_fldEnd.isNull())
            {
                if ((fldDepartureDate.getData() == null) || (fldDepartureDate.compareTo(m_fldEnd) <= 0))
                    bFlag = true;
                else
                    bFlag = false;
            }
            fldDepartureDate.setEnableListeners(rgbEnabled);
        }
        return bFlag;
    }

}
