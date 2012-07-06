/**
  * @(#)BumpCheckNoHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.refund;

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
 *  BumpCheckNoHandler - .
 */
public class BumpCheckNoHandler extends FileListener
{
    protected BaseField m_fldCount = null;
    protected BaseField m_fldStart = null;
    protected int m_iLastCheckNo;
    /**
     * Default constructor.
     */
    public BumpCheckNoHandler()
    {
        super();
    }
    /**
     * Start the target field and bump it with each read.
     */
    public BumpCheckNoHandler(BaseField fldCount, BaseField fldStart)
    {
        this();
        this.init(fldCount, fldStart);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldCount, BaseField fldStart)
    {
        m_fldCount = null;
        m_fldStart = null;
        m_iLastCheckNo = 0;
        m_fldCount = fldCount;
        m_fldStart = fldStart;
        if (m_fldStart != null)
            m_iLastCheckNo = (int)m_fldStart.getValue() - 1;
        super.init(null);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        m_iLastCheckNo++;
        m_fldCount.setValue(m_iLastCheckNo);
    }
    /**
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            if (iChangeType == DBConstants.AFTER_REQUERY_TYPE)
        {    // Init count
            m_iLastCheckNo = (int)m_fldStart.getValue() - 1;
            m_fldCount.setValue(m_iLastCheckNo);
        }
        return iErrorCode;
    }
    /**
     * Get the next check no.
     * @return The next check no.
     */
    public int getNextCheckNo()
    {
        return m_iLastCheckNo + 1;
    }

}
