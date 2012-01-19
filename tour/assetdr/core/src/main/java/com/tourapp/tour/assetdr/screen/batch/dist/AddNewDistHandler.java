/**
 * @(#)AddNewDistHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.batch.dist;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  AddNewDistHandler - This special class tests for the case when the user set a single distribution
account, but the header record was new so I couldn't write the detail
records yet..
 */
public class AddNewDistHandler extends FileListener
{
    protected BankTrxBatchDist m_recBankTrxBatchDist = null;
    /**
     * Default constructor.
     */
    public AddNewDistHandler()
    {
        super();
    }
    /**
     * AddNewDistHandler Method.
     */
    public AddNewDistHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_recBankTrxBatchDist = null;
        super.init(record);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recBankTrxBatchDist != null)
            m_recBankTrxBatchDist.free();
        m_recBankTrxBatchDist = null;
        super.free();
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
        if (!this.getOwner().getTableNames(false).equalsIgnoreCase(BankTrxBatchDetail.kBankTrxBatchDetailFile))
            return DBConstants.NORMAL_RETURN;   // If this is being overidden, don't do rest of code.
        switch (iChangeType)
        {
            case DBConstants.AFTER_ADD_TYPE:
                BaseField fieldAcct = this.getOwner().getField(BankTrxBatchDetail.kDistributionDisplay);
                if (fieldAcct != null)
                    if (!fieldAcct.isNull())
                {
                    Object bookmark = this.getOwner().getLastModified(DBConstants.BOOKMARK_HANDLE);
                    if (bookmark != null)
                    {
                        try {
                            if (m_recBankTrxBatchDist == null)
                            {
                               RecordOwner recordOwner = Utility.getRecordOwner(this.getOwner());
                               m_recBankTrxBatchDist = new BankTrxBatchDist(recordOwner);
                                if (recordOwner != null)
                                    recordOwner.removeRecord(m_recBankTrxBatchDist);
                            }
                            m_recBankTrxBatchDist.addNew();
                            m_recBankTrxBatchDist.getField(BankTrxBatchDist.kBankTrxBatchDetailID).setData(bookmark);
                            m_recBankTrxBatchDist.getField(BankTrxBatchDist.kAccountID).moveFieldToThis(fieldAcct);
                            m_recBankTrxBatchDist.getField(BankTrxBatchDist.kAmount).moveFieldToThis(this.getOwner().getField(BankTrxBatchDetail.kAmount));
                            m_recBankTrxBatchDist.add();
                        } catch (DBException ex)    {
                            ex.printStackTrace();
                        }
                    }
                }
            break;
        }
        return iErrorCode;
    }

}
