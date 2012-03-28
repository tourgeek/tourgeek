/**
 * @(#)ProfitCenterFilter.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  ProfitCenterFilter - Only allow records that match this profit center.
 */
public class ProfitCenterFilter extends FileFilter
{
    protected BaseField m_fldFilter = null;
    protected String m_fsAccountNoField;
    /**
     * Default constructor.
     */
    public ProfitCenterFilter()
    {
        super();
    }
    /**
     * ProfitCenterFilter Method.
     */
    public ProfitCenterFilter(String fsAccountNoField, BaseField fldFilter)
    {
        this();
        this.init(fsAccountNoField, fldFilter);
    }
    /**
     * Initialize class fields.
     */
    public void init(String fsAccountNoField, BaseField fldFilter)
    {
        m_fsAccountNoField = "";
        super.init(null);
        m_fsAccountNoField = fsAccountNoField;
        m_fldFilter = fldFilter;
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
        int iAccountNo = (int)this.getOwner().getField(m_fsAccountNoField).getValue();
        int iProfitCenter = iAccountNo - ((int)(iAccountNo / 1000)) * 1000;
        int iTargetProfitCenter = (int)((ReferenceField)m_fldFilter).getReference().getField(ProfitCenter.PROFIT_CENTER_NO).getValue();
        if (iTargetProfitCenter != 0)
            if (iTargetProfitCenter != iProfitCenter)
                return false;
        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList);
    }

}
