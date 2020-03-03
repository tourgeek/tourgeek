/**
  * @(#)LandPricingFilter.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.land.db;

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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import com.tourgeek.tour.product.base.db.*;
import java.util.*;
import com.tourgeek.tour.base.db.*;
import java.text.*;
import com.tourgeek.tour.product.land.event.*;
import com.tourgeek.tour.message.land.request.data.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import org.bson.*;

/**
 *  LandPricingFilter - A filter for the valid pricing records for a land.
 */
public class LandPricingFilter extends SubFileFilter
{
    protected Date m_dateTarget = null;
    protected short m_sTargetPax;
    protected int m_iSicPmc = -1;
    /**
     * Default constructor.
     */
    public LandPricingFilter()
    {
        super();
    }
    /**
     * LandPricingFilter Method.
     */
    public LandPricingFilter(Land recLand, Date dateTarget, short sTargetPax, int iSicPmc)
    {
        this();
        this.init(recLand, dateTarget, sTargetPax, iSicPmc);
    }
    /**
     * Initialize class fields.
     */
    public void init(Land recLand, Date dateTarget, short sTargetPax, int iSicPmc)
    {
        m_sTargetPax = 0;
        super.init(null, recLand, null, null, null, null, null, null, null, false, false, false);
        dateTarget = Converter.convertTimeToDate(dateTarget);
        m_dateTarget = dateTarget;
        m_sTargetPax = sTargetPax;
        m_iSicPmc = iSicPmc;
    }
    /**
     * SetSicPmc Method.
     */
    public void setSicPmc(int iSicPmc)
    {
        m_iSicPmc = iSicPmc;
    }
    /**
     * DoEndKey Method.
     */
    public void doEndKey()
    {
        this.getOwner().getField(LandPricing.CLASS_ID).setValue(m_iSicPmc);
        this.getOwner().getField(LandPricing.CLASS_ID).setModified(true);
        super.doEndKey();   // This is the starting key, set the initial position
    }
    /**
     * DoInitialKey Method.
     */
    public void doInitialKey()
    {
        this.getOwner().getField(LandPricing.CLASS_ID).setValue(m_iSicPmc);
        this.getOwner().getField(LandPricing.CLASS_ID).setModified(true);
        ((DateTimeField)this.getOwner().getField(LandPricing.END_DATE)).setDate(m_dateTarget, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getOwner().getField(LandPricing.END_DATE).setModified(true);
        this.getOwner().getField(LandPricing.TO_PAX).setValue(m_sTargetPax);
        this.getOwner().getField(LandPricing.TO_PAX).setModified(true);
        super.doInitialKey();   // This is the starting key, set the initial position
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
        boolean bDontSkip = true;
        if (this.getOwner().getField(LandPricing.START_DATE).getValue() > m_dateTarget.getTime())    // Start > thisDate
            bDontSkip = false;      // Skip this one
        if (this.getOwner().getField(LandPricing.END_DATE).getValue() < m_dateTarget.getTime())   // End >= thisDate
            bDontSkip = false;      // Skip this one
        if (this.getOwner().getField(LandPricing.FROM_PAX).getValue() > m_sTargetPax)    // Start > thisDate
            bDontSkip = false;      // Skip this one
        if (this.getOwner().getField(LandPricing.TO_PAX).getValue() < m_sTargetPax)  // End >= thisDate
            bDontSkip = false;      // Skip this one
        if (bDontSkip)
            bDontSkip = super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);
        return bDontSkip; // Don't skip (no criteria)
    }

}
