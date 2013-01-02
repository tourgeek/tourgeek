/**
  * @(#)VoidOnDeleteHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.assetdr.db;

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
 *  VoidOnDeleteHandler - When the user deletes a bank trx, this class voids it instead..
 */
public class VoidOnDeleteHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public VoidOnDeleteHandler()
    {
        super();
    }
    /**
     * VoidOnDeleteHandler Method.
     */
    public VoidOnDeleteHandler(Record record)
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
        if (iChangeType == DBConstants.DELETE_TYPE)
        {
            Task task = null;
            if (this.getOwner().getRecordOwner() != null)
                task = this.getOwner().getRecordOwner().getTask();
            boolean bSuccess = ((BankTrx)this.getOwner()).onVoid();
            if (bSuccess)
                if (task != null)
            {
                String strError = "Transaction voided successfully";
                strError = ((BaseApplication)task.getApplication()).getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString(strError);
                return task.setLastError(strError);
            }
            return DBConstants.ERROR_RETURN;  // Don't delete the physical record
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
