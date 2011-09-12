/**
 * @(#)CheckReconDateHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report.recon;

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
import com.tourapp.tour.assetdr.db.*;

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
        super.init(null, -1, null, ">", falseField, true, fldToCheck, fldToCompare);
    }
    /**
     * Clone Method.
     */
    public Object clone()
    {
        return new CheckReconDateHandler(m_fldToCheck, m_fldToCompare);
    }
    /**
     * Set up/do the remote criteria.
     * @param strbFilter The SQL query string to add to.
     * @param bIncludeFileName Include the file name with this query?
     * @param vParamList The param list to add the raw data to (for prepared statements).
     * @return True if you should not skip this record (does a check on the local data).
     */
    public boolean doRemoteCriteria(StringBuffer strbFilter, boolean bIncludeFileName, Vector vParamList)
    {
        boolean bDontSkip;
        if (strbFilter != null)   if (strbFilter.length() != 0)
            strbFilter.append(" AND ");
        if (strbFilter != null)
            strbFilter.append("(");
        boolean bDontSkip2 = this.fieldCompare(m_fldToCheck, m_fldToCompare, m_strSeekSign, strbFilter, bIncludeFileName, vParamList);
        if (strbFilter != null)
            strbFilter.append(" OR ");
        boolean bDontSkip3 = this.fieldCompare(m_fldToCheck, (String)null, "=", strbFilter, bIncludeFileName, vParamList);  // Is null
        if (strbFilter != null)
            strbFilter.append(") ");
        if (strbFilter != null)
            bDontSkip = true; // Don't need to compare, if I'm creating a filter to pass to SQL 
        else
            bDontSkip = (bDontSkip2 | bDontSkip3);
        if (bDontSkip)
            return super.doRemoteCriteria(strbFilter, bIncludeFileName, vParamList);    // Dont skip this record
        else
            return false;   // Skip this one
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

}
