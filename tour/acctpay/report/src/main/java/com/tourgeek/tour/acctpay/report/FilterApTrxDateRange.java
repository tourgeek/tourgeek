/**
  * @(#)FilterApTrxDateRange.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.report;

import java.util.*;

import org.bson.Document;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;

/**
 *  FilterApTrxDateRange - Filter based on the departure date range.
Note: Can't use ExtractRange singe ApTrx.kDepartureDate is virtual..
 */
public class FilterApTrxDateRange extends FileFilter
{
    protected BaseField m_fldEnd = null;
    protected BaseField m_fldStart = null;
    protected String m_iFieldSeq = null;
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
    public FilterApTrxDateRange(String iFieldSeq, BaseField fldStart, BaseField fldEnd)
    {
        this();
        this.init(iFieldSeq, fldStart, fldEnd);
    }
    /**
     * Initialize class fields.
     */
    public void init(String iFieldSeq, BaseField fldStart, BaseField fldEnd)
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
     * @param doc
     * @return True if you should not skip this record (does a check on the local data).
     */
    public boolean doLocalCriteria(StringBuffer strbFilter, boolean bIncludeFileName, Vector vParamList, Document doc)
    {
        boolean bFlag = super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);
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
