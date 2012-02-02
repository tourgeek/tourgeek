/**
 * @(#)ApTrxBaseFilter.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  ApTrxBaseFilter - Base filter for A/P Transactions.
 */
public class ApTrxBaseFilter extends ListFileFilter
{
    protected boolean m_bFirstTime = true;
    protected TrxDesc m_recTrxDesc = null;
    protected TrxStatus m_recTrxStatus = null;
    /**
     * Default constructor.
     */
    public ApTrxBaseFilter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ApTrxBaseFilter(String fsTarget)
    {
        this();
        this.init(fsTarget);
    }
    /**
     * Initialize class fields.
     */
    public void init(String fsTarget)
    {
        m_recTrxDesc = null;
        m_recTrxStatus = null;
        super.init(null, fsTarget);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recTrxStatus != null)
            m_recTrxStatus.free();
        m_recTrxStatus = null;
        if (m_recTrxDesc != null)
            m_recTrxDesc.free();
        m_recTrxDesc = null;
        super.free();
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
        if (this.isFilterChange())
        {
            this.clearFilter();
        
            this.scanNewFilter();
        }
        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList);
    }
    /**
     * Has the filter changed from the last time this was used?
     * If yes, return true and change your compare values for next time.
     * Usually Override this (default logic returns true on first time).
     * @return True if filter has changed.
     */
    public boolean isFilterChange()
    {
        if (m_bFirstTime)
        {
            m_bFirstTime = false;
            return true;
        }
        return false;
    }
    /**
     * Scan the Status file and add any status that go with this filter.
     */
    public void scanNewFilter()
    {
        TrxStatus recTrxStatus = this.getTrxStatus();
        try {
            recTrxStatus.close();
            while (recTrxStatus.hasNext())
            {
                recTrxStatus.next();
                if (this.checkTrxStatus(recTrxStatus))
                    this.addTrxStatusID(recTrxStatus);
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * Should I add this Status to the filter?
     * Override this!
     * @return true if status is valid.
     */
    public boolean checkTrxStatus(TrxStatus recTrxStatus)
    {
        return true;
    }
    /**
     * Add this TrxStatus to the filter.
     */
    public void addTrxStatusID(TrxStatus recTrxStatus)
    {
        this.addFilter(new Integer((int)recTrxStatus.getField(TrxStatus.ID).getValue()));
    }
    /**
     * Return the TrxStatus file.
     * @return The TrxStatus file.
     */
    public TrxStatus getTrxStatus()
    {
        if (m_recTrxStatus == null)
        {
            RecordOwner recordOwner = this.getOwner().findRecordOwner();
            m_recTrxStatus = new TrxStatus(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recTrxStatus);
            m_recTrxDesc = new TrxDesc(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recTrxDesc);
            m_recTrxDesc.getKeyArea(TrxDesc.DESC_CODE_KEY);
            m_recTrxDesc.getField(TrxDesc.DESC_CODE).setString(ApTrx.AP_TRX_FILE);
            try {
                if (m_recTrxDesc.seek("="))
                {
                    m_recTrxStatus.addListener(new SubFileFilter(m_recTrxDesc));
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
        }
        return m_recTrxStatus;
    }

}
