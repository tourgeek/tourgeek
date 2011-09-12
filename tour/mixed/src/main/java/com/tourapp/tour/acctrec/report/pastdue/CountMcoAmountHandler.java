/**
 * @(#)CountMcoAmountHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.pastdue;

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
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;

/**
 *  CountMcoAmountHandler - Count only the MCO amounts.
 */
public class CountMcoAmountHandler extends SubCountHandler
{
    protected int m_iEndMcoClass;
    protected int m_iStartMcoClass;
    protected TrxStatus m_recTrxStatus = null;
    /**
     * Default constructor.
     */
    public CountMcoAmountHandler()
    {
        super();
    }
    /**
     * Count a sub-field.
     */
    public CountMcoAmountHandler(BaseField fieldMain, int ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF)
    {
        this();
        this.init(fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fieldMain, int ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF)
    {
        m_iEndMcoClass = 0;
        m_iStartMcoClass = 0;
        m_recTrxStatus = null;
        super.init(null, null, -1, fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF, false);
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
     * Get the value to add (Overidden from SubCountHandler).
     * If there was a field specified, return the value, otherwise just return a count of 1.
     * @return The field value.
     */
    public double getFieldValue()
    {
        if (m_iStartMcoClass == 0)
        {
            if (m_recTrxStatus == null)
            {
                RecordOwner recordOwner = Utility.getRecordOwner(this.getOwner());
                m_recTrxStatus = new TrxStatus(recordOwner);
                if (recordOwner != null)
                    recordOwner.removeRecord(m_recTrxStatus);
            }
            m_iStartMcoClass = (int)m_recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.kMcoFile, Mco.BATCH);
            m_iEndMcoClass = m_iStartMcoClass; //(int)recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, "MCO Payment-Paid amt");
        }
        if ((this.getOwner().getField(ArTrx.kTrxStatusID).getValue() >= m_iStartMcoClass)
            && (this.getOwner().getField(ArTrx.kTrxStatusID).getValue() <= m_iEndMcoClass))
                return super.getFieldValue();
        return 0; // Not an MCO... Don't add
    }

}
