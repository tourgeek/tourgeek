/**
  * @(#)CompareRefundHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.refund;

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
import org.bson.*;

/**
 *  CompareRefundHandler - Compare the ArTrx type to make sure this is a refund type.
 */
public class CompareRefundHandler extends FileListener
{
    protected BaseField m_fldTarget = null;
    protected int m_iHeld;
    protected int m_iPay;
    protected int m_iSubmitted;
    /**
     * Default constructor.
     */
    public CompareRefundHandler()
    {
        super();
    }
    /**
     * CompareRefundHandler Method.
     */
    public CompareRefundHandler(BaseField fldTarget, int iSubmitted, int iHeld, int iPay)
    {
        this();
        this.init(fldTarget, iSubmitted, iHeld, iPay);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldTarget, int iSubmitted, int iHeld, int iPay)
    {
        m_fldTarget = null;
        m_iHeld = 0;
        m_iPay = 0;
        m_iSubmitted = 0;
        m_fldTarget = fldTarget;
        m_iSubmitted = iSubmitted;
        m_iHeld = iHeld;
        m_iPay = iPay;
        super.init(null);
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
        boolean bDontSkip = false;
        
        if (m_fldTarget.getValue() == m_iSubmitted)
            bDontSkip = true;
        if (m_fldTarget.getValue() == m_iHeld)
            bDontSkip = true;
        if (m_fldTarget.getValue() == m_iPay)
            bDontSkip = true;
        
        if (bDontSkip)
            return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);    // Dont skip this record
        else
            return false;   // Skip this one
    }

}
