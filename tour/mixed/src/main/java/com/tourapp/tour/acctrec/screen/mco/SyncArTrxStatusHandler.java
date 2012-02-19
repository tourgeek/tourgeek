/**
 * @(#)SyncArTrxStatusHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.mco;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.db.*;

/**
 *  SyncArTrxStatusHandler - Synchronize the A/R Trx file with this BaseArPay record..
 */
public class SyncArTrxStatusHandler extends FileListener
{
    protected BaseField m_fldTrxStatusID = null;
    protected ArTrx m_recArTrx = null;
    /**
     * Default constructor.
     */
    public SyncArTrxStatusHandler()
    {
        super();
    }
    /**
     * SyncArTrxStatusHandler Method.
     */
    public SyncArTrxStatusHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_fldTrxStatusID = null;
        m_recArTrx = null;
        super.init(record);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recArTrx != null)
            m_recArTrx.free();
        m_recArTrx = null;
        if (m_fldTrxStatusID != null)
            m_fldTrxStatusID.free();
        m_fldTrxStatusID = null;
        super.free();
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        Record recBaseArTrx = this.getOwner();
        if (m_fldTrxStatusID == null)
        {
            try {
                m_fldTrxStatusID = (BaseField)recBaseArTrx.getField(BaseTrx.TRX_STATUS_ID).clone();
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
        // Save the (previous) status, so you can change the status if updated.
        m_fldTrxStatusID.moveFieldToThis(recBaseArTrx.getField(BaseTrx.TRX_STATUS_ID));
        super.doValidRecord(bDisplayOption);
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
        if (iChangeType == DBConstants.UPDATE_TYPE)
        {
             if (m_recArTrx == null)
            {
                RecordOwner recordOwner = this.getOwner().findRecordOwner();
                m_recArTrx = new ArTrx(recordOwner);
                if (recordOwner != null)
                    recordOwner.removeRecord(m_recArTrx);
            }
            Record recBaseArTrx = this.getOwner();
            if (m_recArTrx.getListener(SubFileFilter.class.getName()) == null)
            {
                m_recArTrx.setKeyArea(ArTrx.LINKED_TRX_ID_KEY);
                m_recArTrx.addListener(new SubFileFilter(m_fldTrxStatusID, ArTrx.TRX_STATUS_ID, recBaseArTrx.getField(BaseArPay.ID), ArTrx.LINKED_TRX_ID, null, null));
            }
            try {
                m_recArTrx.close();
                while (m_recArTrx.hasNext())
                {
                    m_recArTrx.next();
                    m_recArTrx.edit();
                    m_recArTrx.getField(ArTrx.TRX_STATUS_ID).moveFieldToThis(recBaseArTrx.getField(BaseArPay.TRX_STATUS_ID));
                    m_recArTrx.set();
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
