
package com.tourgeek.tour.genled.screen.trx;

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
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.main.screen.*;
import com.tourgeek.tour.genled.db.*;

/**
 *  UpdateTrxStatusHandler - .
 */
public class UpdateTrxStatusHandler extends FileListener
{
    protected TrxDesc m_recTrxDesc = null;
    protected TransactionType m_recTrxType = null;
    /**
     * Default constructor.
     */
    public UpdateTrxStatusHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param If you know the trxDesc that is changing, supply it here.
     */
    public UpdateTrxStatusHandler(Record record, TrxDesc recTrxDesc)
    {
        this();
        this.init(record, recTrxDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, TrxDesc recTrxDesc)
    {
        m_recTrxDesc = null;
        m_recTrxType = null;
        m_recTrxDesc = recTrxDesc;
        super.init(record);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recTrxType != null)
            m_recTrxType.free();
        m_recTrxType = null;
        super.free();
    }
    /**
     * Clone Method.
     */
    public Object clone()
    {
        return new UpdateTrxStatusHandler(null, m_recTrxDesc);
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
        if ((iChangeType == DBConstants.UPDATE_TYPE)
            || (iChangeType == DBConstants.DELETE_TYPE))
        {
            Record recTrxStatus = this.getOwner();
            RecordOwner recordOwner = recTrxStatus.findRecordOwner();
            if (m_recTrxType == null)
            {
                m_recTrxType = new TransactionType(recordOwner);
                m_recTrxType.setKeyArea(TransactionType.SOURCE_TRX_STATUS_ID_KEY);
                m_recTrxType.addListener(new SubFileFilter(recTrxStatus.getField(TrxStatus.ID), TransactionType.SOURCE_TRX_STATUS_ID, null, null, null, null));
                if (recordOwner != null)
                    recordOwner.removeRecord(m_recTrxType);
            }
            Record recTrxDesc = m_recTrxDesc;
            if (recTrxDesc == null)
                recTrxDesc = ((ReferenceField)recTrxStatus.getField(TrxStatus.TRX_DESC_ID)).getReference();
            try {
                m_recTrxType.close();
                while (m_recTrxType.hasNext())
                {
                    m_recTrxType.next();
                    m_recTrxType.edit();
                    if (iChangeType == DBConstants.UPDATE_TYPE)
                    {
                        if (recTrxDesc != null)
                            if (recTrxDesc.getField(TrxDesc.ID).equals(recTrxStatus.getField(TrxStatus.TRX_DESC_ID)))
                                m_recTrxType.getField(TransactionType.SOURCE_FILE).moveFieldToThis(recTrxDesc.getField(TrxDesc.SOURCE_FILE));
                        m_recTrxType.getField(TransactionType.SOURCE_PREFERRED_SIGN).moveFieldToThis(recTrxStatus.getField(TrxStatus.PREFERRED_SIGN));
                        m_recTrxType.getField(TransactionType.SOURCE_TRX_DESC_ID).moveFieldToThis(recTrxStatus.getField(TrxStatus.TRX_DESC_ID));
                    }
                    else if (iChangeType == DBConstants.DELETE_TYPE)
                    {
                        m_recTrxType.getField(TransactionType.SOURCE_TRX_STATUS_ID).setData(null);
                        m_recTrxType.getField(TransactionType.SOURCE_PREFERRED_SIGN).setData(null);
                        m_recTrxType.getField(TransactionType.SOURCE_FILE).setData(null);
                        m_recTrxType.getField(TransactionType.SOURCE_TRX_DESC_ID).setData(null);
                    }
                    m_recTrxType.set();
                }
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        if ((iChangeType == DBConstants.UPDATE_TYPE)
            || (iChangeType == DBConstants.ADD_TYPE))
        {
            Record recTrxStatus = this.getOwner();
            if (m_recTrxDesc == null)
            {       // Only update the TrxDesc if you are updating the status record.
                Record recTrxDesc = ((ReferenceField)recTrxStatus.getField(TrxStatus.TRX_DESC_ID)).getReference();
                recTrxStatus.getField(TrxStatus.DESC_CODE).moveFieldToThis(recTrxDesc.getField(TrxDesc.DESC_CODE));
        
                Record recTrxSystem = ((ReferenceField)recTrxDesc.getField(TrxDesc.TRX_SYSTEM_ID)).getReference();
                recTrxStatus.getField(TrxStatus.TRX_SYSTEM_ID).moveFieldToThis(recTrxSystem.getField(TrxSystem.ID));
                recTrxStatus.getField(TrxStatus.SYSTEM_CODE).moveFieldToThis(recTrxSystem.getField(TrxSystem.SYSTEM_CODE));
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
