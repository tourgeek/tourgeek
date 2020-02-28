/**
  * @(#)ExcludeClosingFilter.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.finstmt;

import java.util.*;

import org.bson.Document;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import com.tourgeek.tour.genled.db.*;

/**
 *  ExcludeClosingFilter - Exclude closing entries? filter.
 */
public class ExcludeClosingFilter extends FileFilter
{
    protected TransactionType m_recTransactionType = null;
    protected int m_iTypeClosing;
    protected BaseField m_fldExcludeClosing = null;
    /**
     * Default constructor.
     */
    public ExcludeClosingFilter()
    {
        super();
    }
    /**
     * ExcludeClosingFilter Method.
     */
    public ExcludeClosingFilter(BaseField fldExcludeClosing)
    {
        this();
        this.init(fldExcludeClosing);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldExcludeClosing)
    {
        m_recTransactionType = null;
        m_iTypeClosing = 0;
        m_fldExcludeClosing = null;
        m_fldExcludeClosing = fldExcludeClosing;
        super.init(null);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recTransactionType != null)
            m_recTransactionType.free();
        m_recTransactionType = null;
        super.free();
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
        if (m_fldExcludeClosing != null)
            if (m_fldExcludeClosing.getState() == true)
                if (this.getOwner().getField(AcctDetail.TRX_TYPE_ID).getValue() == this.getClosingTrxType())
            return false; // Skip this record.
        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);
    }
    /**
     * GetClosingTrxType Method.
     */
    public int getClosingTrxType()
    {
        if (m_recTransactionType == null)
        {
            m_recTransactionType = new TransactionType(this.getOwner().findRecordOwner());
            if (m_recTransactionType.getRecordOwner() != null)
                m_recTransactionType.getRecordOwner().removeRecord(m_recTransactionType);
            m_iTypeClosing = m_recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.ACCT_DETAIL_FILE, AcctDetail.CLOSINGENTRY, AcctDetail.CLOSINGENTRY);
        }
        return m_iTypeClosing;
    }

}
