/**
  * @(#)CheckReconDateHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.assetdr.report.recon;

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
import com.tourgeek.tour.assetdr.db.*;
import org.jbundle.base.screen.model.util.*;
import org.bson.*;

/**
 *  CheckReconDateHandler - Only returns checks with this reconciliation date, or a blank recon date.
 */
public class CheckReconDateHandler extends CompareFileFilter
{
    /**
     * Default constructor.
     */
    public CheckReconDateHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CheckReconDateHandler(BaseField fldToCheck, BaseField fldToCompare)
    {
        this();
        this.init(fldToCheck, fldToCompare);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldToCheck, BaseField fldToCompare)
    {
        BaseField falseField = new BooleanField(null, "Name", 1, "Name", new Boolean(false));
        super.init(null, null, null, ">", falseField, true, fldToCheck, fldToCompare);
    }
    /**
     * Clone Method.
     */
    public Object clone()
    {
        return new CheckReconDateHandler(m_fldToCheck, m_fldToCompare);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_fldToCheck instanceof BooleanField)
        {
            m_fldToCheck.free();
            m_fldToCheck = null;
        }
        super.free();
    }
    /**
     * Set up/do the remote criteria.
     * @param strbFilter The SQL query string to add to.
     * @param bIncludeFileName Include the file name with this query?
     * @param vParamList The param list to add the raw data to (for prepared statements).
     * @return True if you should not skip this record (does a check on the local data).
     */
    public boolean doRemoteCriteria(StringBuffer strbFilter, boolean bIncludeFileName, Vector vParamList, Document doc)
    {
        boolean bDontSkip;
        if (strbFilter != null)   if (strbFilter.length() != 0)
            strbFilter.append(" AND ");
        if (strbFilter != null)
            strbFilter.append("(");
        boolean bDontSkip2 = this.fieldCompare(m_fldToCheck, m_fldToCompare, m_strSeekSign, strbFilter, bIncludeFileName, vParamList, doc);
        if (strbFilter != null)
            strbFilter.append(" OR ");
        boolean bDontSkip3 = this.fieldCompare(m_fldToCheck, (String)null, "=", strbFilter, bIncludeFileName, vParamList, doc);  // Is null
        if (strbFilter != null)
            strbFilter.append(") ");
        if (strbFilter != null)
            bDontSkip = true; // Don't need to compare, if I'm creating a filter to pass to SQL 
        else
            bDontSkip = (bDontSkip2 | bDontSkip3);
        if (bDontSkip)
            return super.doRemoteCriteria(strbFilter, bIncludeFileName, vParamList, doc);    // Dont skip this record
        else
            return false;   // Skip this one
    }

}
