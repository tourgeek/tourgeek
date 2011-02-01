/**
 *  @(#)ProfitCenterFilter.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;

/**
 *  ProfitCenterFilter - Only allow records that match this profit center.
 */
public class ProfitCenterFilter extends FileFilter
{
    protected BaseField m_fldFilter = null;
    protected int m_fsAccountNoField;
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
    public ProfitCenterFilter(int fsAccountNoField, BaseField fldFilter)
    {
        this();
        this.init(fsAccountNoField, fldFilter);
    }
    /**
     * Initialize class fields.
     */
    public void init(int fsAccountNoField, BaseField fldFilter)
    {
        m_fsAccountNoField = 0;
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
        int iTargetProfitCenter = (int)((ReferenceField)m_fldFilter).getReference().getField(ProfitCenter.kProfitCenterNo).getValue();
        if (iTargetProfitCenter != 0)
            if (iTargetProfitCenter != iProfitCenter)
                return false;
        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList);
    }

}
