/**
  * @(#)UpdateTrxTypeHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.screen.trx;

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
import com.tourapp.tour.genled.db.*;

/**
 *  UpdateTrxTypeHandler - This listener update the cache fields in the TrxType record..
 */
public class UpdateTrxTypeHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public UpdateTrxTypeHandler()
    {
        super();
    }
    /**
     * UpdateTrxTypeHandler Method.
     */
    public UpdateTrxTypeHandler(Record record)
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
     * Clone Method.
     */
    public Object clone()
    {
        return new UpdateTrxTypeHandler(null);
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
        switch (iChangeType)
        {
            case DBConstants.ADD_TYPE:
            case DBConstants.UPDATE_TYPE:
                Record record = this.getOwner();
                Record recTrxGroup = ((ReferenceField)record.getField(TransactionType.TRX_GROUP_ID)).getReference();
                record.getField(TransactionType.GROUP_CODE).moveFieldToThis(recTrxGroup.getField(TrxGroup.GROUP_CODE));
                record.getField(TransactionType.GROUP_DESC).moveFieldToThis(recTrxGroup.getField(TrxGroup.GROUP_DESC));
        
                Record recTrxDesc = ((ReferenceField)recTrxGroup.getField(TrxGroup.TRX_DESC_ID)).getReference();
                record.getField(TransactionType.TRX_DESC_ID).moveFieldToThis(recTrxDesc.getField(TrxDesc.ID));
                record.getField(TransactionType.DESC_CODE).moveFieldToThis(recTrxDesc.getField(TrxDesc.DESC_CODE));
                record.getField(TransactionType.DESCRIPTION).moveFieldToThis(recTrxDesc.getField(TrxDesc.DESCRIPTION));
        
                Record recTrxSystem = ((ReferenceField)recTrxDesc.getField(TrxDesc.TRX_SYSTEM_ID)).getReference();
                record.getField(TransactionType.TRX_SYSTEM_ID).moveFieldToThis(recTrxSystem.getField(TrxSystem.ID));
                record.getField(TransactionType.SYSTEM_CODE).moveFieldToThis(recTrxSystem.getField(TrxSystem.SYSTEM_CODE));
                record.getField(TransactionType.SYSTEM_DESC).moveFieldToThis(recTrxSystem.getField(TrxSystem.SYSTEM_DESC));
        
                break;
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
