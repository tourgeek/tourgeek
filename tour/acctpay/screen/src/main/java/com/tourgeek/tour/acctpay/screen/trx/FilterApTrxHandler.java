/**
  * @(#)FilterApTrxHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.screen.trx;

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
import com.tourgeek.tour.acctpay.db.*;
import org.bson.*;
import com.tourgeek.tour.genled.db.*;

/**
 *  FilterApTrxHandler - Filter the A/P Detail depending on the ApTrxClassField.
 */
public class FilterApTrxHandler extends ListFileFilter
{
    protected BaseField m_fldApTrxClass = null;
    protected int m_iLastValue = ApTrxClassField.ALL;
    protected TrxStatus m_recTrxStatus = null;
    /**
     * Default constructor.
     */
    public FilterApTrxHandler()
    {
        super();
    }
    /**
     * FilterApTrxHandler Method.
     */
    public FilterApTrxHandler(BaseField fldApTrxClass)
    {
        this();
        this.init(fldApTrxClass);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldApTrxClass)
    {
        m_fldApTrxClass = null;
        m_recTrxStatus = null;
        m_fldApTrxClass = fldApTrxClass;
        super.init(null, ApTrx.TRX_STATUS_ID);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recTrxStatus != null)
            m_recTrxStatus.free();
        m_recTrxStatus = null;
        super.free();
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
        int iValue = ApTrxClassField.ALL;
        if (m_fldApTrxClass != null)
            iValue = (int)m_fldApTrxClass.getValue();
        if (m_iLastValue != iValue)
        {
            this.clearFilter();
            if (iValue == ApTrxClassField.INVOICES)
            {
                this.addTrxStatusID(ApTrx.INVOICE);
                this.addTrxStatusID(ApTrx.INVOICE_NON_TOUR);
            }
            else if (iValue == ApTrxClassField.DEPARTURE_ESTIMATES)
            {
                this.addTrxStatusID(ApTrx.DEPARTURE_EST_MANUAL);
                this.addTrxStatusID(ApTrx.DEP_ESTIMATE);
            }
            else if (iValue == ApTrxClassField.PREPAYMENTS)
            {
                this.addTrxStatusID(ApTrx.PREPAYMENT_REQUEST);
                this.addTrxStatusID(ApTrx.PREPAYMENT);
                this.addTrxStatusID(ApTrx.DEBIT_MEMO);
                this.addTrxStatusID(ApTrx.CREDIT_INVOICE);
                this.addTrxStatusID(ApTrx.CREDIT_INVOICE_NON_TOUR);
            }
            m_iLastValue = iValue;
        }
        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);
    }
    /**
     * AddTrxStatusID Method.
     */
    public void addTrxStatusID(String strApTrxDesc)
    {
        if (m_recTrxStatus == null)
        {
            RecordOwner recordOwner = this.getOwner().findRecordOwner();
            m_recTrxStatus = new TrxStatus(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recTrxStatus);
        }
        this.addFilter(new Integer(m_recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, strApTrxDesc)));
    }

}
