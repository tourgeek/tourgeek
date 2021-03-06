/**
  * @(#)AcctBatchValidateBeh.
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
 *  AcctBatchValidateBeh - Make sure the batch entry has an account and amount.
Also check for (and create) Auto-reversal and Auto-distribution entries..
 */
public class AcctBatchValidateBeh extends FileListener
{
    /**
     * Default constructor.
     */
    public AcctBatchValidateBeh()
    {
        super();
    }
    /**
     * AcctBatchValidateBeh Method.
     */
    public AcctBatchValidateBeh(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
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
        if ((iChangeType == DBConstants.ADD_TYPE) || (iChangeType == DBConstants.UPDATE_TYPE))
        {
            Task task = null;
            if (this.getOwner().getRecordOwner() != null)
                task = this.getOwner().getRecordOwner().getTask();
            if (task == null)
                task = BaseApplet.getSharedInstance();
            if (this.getOwner().getField(AcctBatchDetail.ACCOUNT_ID).isNull())
            {
                String strError = "You must enter an Account number";
                strError = ((BaseApplication)task.getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
                return task.setLastError(strError);
            }
            if (this.getOwner().getField(AcctBatchDetail.ACCOUNT_ID).isNull())
            {
                String strError = "You must enter a non-zero amount";
                strError = ((BaseApplication)task.getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
                return task.setLastError(strError);
            }
        }
        if (iChangeType == DBConstants.AFTER_ADD_TYPE)
        {
            Record recAccount = ((ReferenceField)this.getOwner().getField(AcctBatchDetail.ACCOUNT_ID)).getReference();
            if ((recAccount == null) || (recAccount.getEditMode() != Constants.EDIT_CURRENT))
                return iErrorCode;
            if (recAccount.getField(Account.COUNTER_ACCOUNT_ID).getState())
            {
                return ((AcctBatchDetail)this.getOwner()).onCounterBalance(recAccount);
            }
            else if (!recAccount.getField(Account.AUTO_DIST_ID).isNull())
            {
                return ((AcctBatchDetail)this.getOwner()).onAutoDist(recAccount);
            }
        }
        return iErrorCode;
    }

}
