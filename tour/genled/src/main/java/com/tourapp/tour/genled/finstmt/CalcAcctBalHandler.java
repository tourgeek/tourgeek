/**
 * @(#)CalcAcctBalHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.finstmt;

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
 *  CalcAcctBalHandler - .
 */
public class CalcAcctBalHandler extends FileListener
{
    protected boolean m_bInclusive;
    protected BaseField m_fldEndDate = null;
    protected BaseField m_fldGrandTotal = null;
    protected BaseField m_fldStartDate = null;
    protected BaseField m_fldTarget = null;
    protected AcctDetail m_recAcctDetail = null;
    /**
     * Default constructor.
     */
    public CalcAcctBalHandler()
    {
        super();
    }
    /**
     * CalcAcctBalHandler Method.
     */
    public CalcAcctBalHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_bInclusive = true;
        m_fldEndDate = null;
        m_fldGrandTotal = null;
        m_fldStartDate = null;
        m_fldTarget = null;
        m_recAcctDetail = null;
        super.init(record);
    }
    /**
     * Constructor.
     */
    public CalcAcctBalHandler(BaseField fldTarget, BaseField fldStartDate, BaseField fldEndDate, boolean bInclusive)
    {
        this();
        this.init(fldTarget, fldStartDate, fldEndDate, bInclusive);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldTarget, BaseField fldStartDate, BaseField fldEndDate, boolean bInclusive)
    {
        m_bInclusive = true;
        m_fldEndDate = null;
        m_fldGrandTotal = null;
        m_fldStartDate = null;
        m_fldTarget = null;
        m_recAcctDetail = null;
        m_fldTarget = fldTarget;
        m_fldStartDate = fldStartDate;
        m_fldEndDate = fldEndDate;
        m_bInclusive = bInclusive;
        m_fldGrandTotal = null;
        m_recAcctDetail = null;
        super.init(null);
    }
    /**
     * CalcAcctBalHandler Method.
     */
    public CalcAcctBalHandler(BaseField fldTarget, BaseField fldStartDate, BaseField fldEndDate, boolean bInclusive, BaseField fldGrandTotal)
    {
        this();
        this.init(fldTarget, fldStartDate, fldEndDate, bInclusive, fldGrandTotal);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldTarget, BaseField fldStartDate, BaseField fldEndDate, boolean bInclusive, BaseField fldGrandTotal)
    {
        m_bInclusive = true;
        m_fldEndDate = null;
        m_fldGrandTotal = null;
        m_fldStartDate = null;
        m_fldTarget = null;
        m_recAcctDetail = null;
        m_fldTarget = fldTarget;
        m_fldStartDate = fldStartDate;
        m_fldEndDate = fldEndDate;
        m_bInclusive = bInclusive;
        m_fldGrandTotal = fldGrandTotal;
        m_recAcctDetail = null;
        super.init(null);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recAcctDetail != null)
            m_recAcctDetail.free();
        m_recAcctDetail = null;
        super.free();
    }
    /**
     * Called when a new blank record is required for the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        m_fldTarget.setValue(0);
        super.doNewRecord(bDisplayOption);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        m_recAcctDetail = this.getAcctDetail();
        
        m_fldTarget.initField(DBConstants.DISPLAY);
        m_fldTarget.setValue(0);
        m_recAcctDetail.close();
        try   {
            while (m_recAcctDetail.hasNext())
            {   // Go through and count
                m_recAcctDetail.next();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        if (m_fldGrandTotal != null)
            m_fldGrandTotal.setValue(m_fldGrandTotal.getValue() + m_fldTarget.getValue());
        super.doValidRecord(bDisplayOption);
    }
    /**
     * GetAcctDetail Method.
     */
    public AcctDetail getAcctDetail()
    {
        if (m_recAcctDetail == null)
        {
            m_recAcctDetail = new AcctDetail(this.getOwner().findRecordOwner());
            if (m_recAcctDetail.getRecordOwner() != null)
                m_recAcctDetail.getRecordOwner().removeRecord(m_recAcctDetail);
            m_recAcctDetail.addListener(new SubFileFilter(this.getOwner()));
        
            if (m_fldStartDate != null)
            {
                if (m_bInclusive)
                {
                    SubFileFilter listener = new SubFileFilter(m_fldStartDate, AcctDetail.TRX_DATE, null, null, null, null);
                    listener.setEndKey(false);
                    m_recAcctDetail.addListener(listener);
                }
                else
                {
                    if (m_fldStartDate != null)
                        m_recAcctDetail.addListener(new CompareFileFilter(AcctDetail.TRX_DATE, m_fldStartDate, ">", null, false));
                }
            }
            if (m_fldEndDate != null)
            {
                if (m_bInclusive)
                {
                    SubFileFilter listener = new SubFileFilter(m_fldEndDate, AcctDetail.TRX_DATE, null, null, null, null);
                    listener.setInitialKey(false);
                    m_recAcctDetail.addListener(listener);
                }
                else
                {
                    if (m_fldEndDate != null)
                        m_recAcctDetail.addListener(new CompareFileFilter(AcctDetail.TRX_DATE, m_fldEndDate, "<", null, false));
                }
            }
            m_recAcctDetail.addListener(new SubCountHandler(m_fldTarget, AcctDetail.AMOUNT_LOCAL, false, true));
        }
        return m_recAcctDetail;
    }

}
