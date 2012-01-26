/**
 * @(#)UpdateTrxSystemHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.trx;

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

/**
 *  UpdateTrxSystemHandler - Update the trx system cache fields when the system file changes.
 */
public class UpdateTrxSystemHandler extends FileListener
{
    protected TrxDesc m_recTrxDesc = null;
    /**
     * Default constructor.
     */
    public UpdateTrxSystemHandler()
    {
        super();
    }
    /**
     * UpdateTrxSystemHandler Method.
     */
    public UpdateTrxSystemHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_recTrxDesc = null;
        super.init(record);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recTrxDesc != null)
            m_recTrxDesc.free();
        m_recTrxDesc = null;
        super.free();
    }
    /**
     * Clone Method.
     */
    public Object clone()
    {
        return new UpdateTrxSystemHandler(null);
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
            try {
                Record recTrxSystem = this.getOwner();
                RecordOwner recordOwner = recTrxSystem.findRecordOwner();
                if (m_recTrxDesc == null)
                {
                    m_recTrxDesc = new TrxDesc(recordOwner);
                    m_recTrxDesc.addListener(new SubFileFilter(recTrxSystem));
                    m_recTrxDesc.addListener(new UpdateTrxDescHandler(null));
                    if (recordOwner != null)
                        recordOwner.removeRecord(m_recTrxDesc);
                }
                m_recTrxDesc.close();
                while (m_recTrxDesc.hasNext())
                {
                    m_recTrxDesc.next();
                    m_recTrxDesc.edit();
                    m_recTrxDesc.getField(TrxDesc.kDescCode).setModified(true); // Have to make sure the UpdateTrxDescHandler is called.
                    if (iChangeType == DBConstants.UPDATE_TYPE)
                        m_recTrxDesc.set();
                    if (iChangeType == DBConstants.DELETE_TYPE)
                        m_recTrxDesc.remove();
                }
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
