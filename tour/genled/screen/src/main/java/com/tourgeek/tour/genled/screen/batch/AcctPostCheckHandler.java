/**
  * @(#)AcctPostCheckHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.screen.batch;

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
import com.tourgeek.tour.genled.db.*;
import org.jbundle.main.screen.*;
import org.jbundle.base.screen.model.util.*;
import java.util.*;
import org.jbundle.thin.base.screen.*;

/**
 *  AcctPostCheckHandler - If any transaction are auto-accrual reversal, set the flag in the AcctBatch header file..
 */
public class AcctPostCheckHandler extends FileListener
{
    protected Record m_recAcctBatch = null;
    /**
     * Default constructor.
     */
    public AcctPostCheckHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctPostCheckHandler(Record recAcctBatch)
    {
        this();
        this.init(recAcctBatch);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recAcctBatch)
    {
        m_recAcctBatch = null;
        m_recAcctBatch = recAcctBatch;
        super.init(null);
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
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        if ((iChangeType == DBConstants.MOVE_NEXT_TYPE) || (iChangeType == DBConstants.ADD_TYPE) || (iChangeType == DBConstants.UPDATE_TYPE))
        {
            if (this.getOwner().getField(AcctBatchDetail.AUTO_REVERSAL).getState() == true)
                m_recAcctBatch.getField(AcctBatch.AUTO_REVERSAL).setState(true);
        }
        return iErrorCode;

    }

}
