/**
  * @(#)CountMcoAmountHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.report.pastdue;

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
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import org.bson.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.main.db.*;
import org.jbundle.base.screen.model.util.*;

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
    public CountMcoAmountHandler(BaseField fieldMain, String ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF)
    {
        this();
        this.init(fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fieldMain, String ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF)
    {
        m_iEndMcoClass = 0;
        m_iStartMcoClass = 0;
        m_recTrxStatus = null;
        super.init(null, null, null, fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF, false);
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
                RecordOwner recordOwner = this.getOwner().findRecordOwner();
                m_recTrxStatus = new TrxStatus(recordOwner);
                if (recordOwner != null)
                    recordOwner.removeRecord(m_recTrxStatus);
            }
            m_iStartMcoClass = (int)m_recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.MCO_FILE, Mco.BATCH);
            m_iEndMcoClass = m_iStartMcoClass; //(int)recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, "MCO Payment-Paid amt");
        }
        if ((this.getOwner().getField(ArTrx.TRX_STATUS_ID).getValue() >= m_iStartMcoClass)
            && (this.getOwner().getField(ArTrx.TRX_STATUS_ID).getValue() <= m_iEndMcoClass))
                return super.getFieldValue();
        return 0; // Not an MCO... Don't add
    }

}
