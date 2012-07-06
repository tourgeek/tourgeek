/**
  * @(#)UpdateAcctDetailHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.user.db.*;
import java.util.*;

/**
 *  UpdateAcctDetailHandler - Add this behavior to the transaction that requires a G/L posting.
When the transaction is made, this behavior creates an entry..
 */
public class UpdateAcctDetailHandler extends FileListener
{
    protected AcctDetail m_recAcctDetail = null;
    protected AcctDetailDist m_recAcctDetailDist = null;
    protected Period m_recPeriod = null;
    protected TransactionType m_recTransactionType = null;
    /**
     * Default constructor.
     */
    public UpdateAcctDetailHandler()
    {
        super();
    }
    /**
     * UpdateAcctDetailHandler Method.
     */
    public UpdateAcctDetailHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_recAcctDetail = null;
        m_recAcctDetailDist = null;
        m_recPeriod = null;
        m_recTransactionType = null;
        super.init(record);
    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (this.getOwner() != null)
            this.getOwner().setOpenMode(this.getOwner().getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        else
        {
            if (m_recAcctDetailDist != null)
                m_recAcctDetailDist.free();
            if (m_recTransactionType != null)
                m_recTransactionType.free();
            if (m_recAcctDetail != null)
                m_recAcctDetail.free();
            if (m_recPeriod != null)
                m_recPeriod.free();
            m_recPeriod = null;
            m_recAcctDetail = null;
            m_recTransactionType = null;
            m_recAcctDetailDist = null;
        }
    }
    /**
     * CheckFiles Method.
     */
    public void checkFiles()
    {
        RecordOwner recordOwner = this.getOwner().findRecordOwner();
        if (m_recAcctDetail == null)
        {
            m_recAcctDetail = new AcctDetail(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recAcctDetail);
        }
        if (m_recAcctDetailDist == null)
        {
            m_recAcctDetailDist = new AcctDetailDist(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recAcctDetailDist);
        }
        if (m_recTransactionType == null)
        {
            m_recTransactionType = new TransactionType(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recTransactionType);
        }
        if (m_recPeriod == null)
        {
            m_recPeriod = new Period(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recPeriod);
        }
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
        if ((iChangeType == DBConstants.AFTER_UPDATE_TYPE)
            || (iChangeType == DBConstants.AFTER_ADD_TYPE))
        {   
            this.checkFiles();
        
            if (iChangeType == DBConstants.AFTER_ADD_TYPE)
                if ((this.getOwner().getEditMode() == DBConstants.EDIT_NONE)
                        || ((this.getOwner().getEditMode() == DBConstants.EDIT_ADD) && (this.getOwner().getCounterField().isNull())))
            {
                try {
                    this.getOwner().setHandle(this.getOwner().getLastModified(DBConstants.DATA_SOURCE_HANDLE), DBConstants.DATA_SOURCE_HANDLE);
                } catch (DBException ex)    {
                    ex.printStackTrace();
                }
            }
        
            int iTrxGroupID = -1;
            if (this.getOwner() instanceof Trx)   // Always
                iTrxGroupID = ((Trx)this.getOwner()).getTrxGroupID();
            m_recTransactionType.setKeyArea(TransactionType.TRX_GROUP_ID_KEY);
            FileListener behSub = new StringSubFileFilter(Integer.toString(iTrxGroupID), TransactionType.TRX_GROUP_ID, null, null, null, null);
            m_recTransactionType.addListener(behSub);
            m_recTransactionType.close();
            try   {
                m_recAcctDetailDist.startDistTrx();
                while (m_recTransactionType.hasNext())
                {
                    m_recTransactionType.next();
                    if (isNewTrx(iChangeType))
                        this.addDetailTrx(m_recTransactionType, m_recAcctDetailDist, m_recAcctDetail, m_recPeriod, 0);
                    else
                        this.modifyDetailTrx(m_recTransactionType, m_recAcctDetailDist, m_recAcctDetail, m_recPeriod);
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
            } finally {
                m_recAcctDetailDist.endDistTrx();
                m_recTransactionType.removeListener(behSub, true);
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }
    /**
     * Is this a new transaction (or a modification of a current transaction).
     * If it is not new, the system will calculate the current posting and do an adjusting entry.
     */
    public boolean isNewTrx(int iChangeType)
    {
        return true;    // Override this!
    }
    /**
     * Override this method and call recAcctDetailDist.addDetailTrx(...) to
     * add the G/L transaction.
     * @param recTransactionType TransactionType record
     * @param recAcctDetailDist AcctDetailDist record
     * @param recAcctDetail AcctDetail record
     * @param recPeriod Period record
     * @param dCurrentBalance The current balance for this type of transaction.
     */
    public void addDetailTrx(TransactionType recTransactionType, AcctDetailDist recAcctDetailDist, AcctDetail recAcctDetail, Period recPeriod, double dCurrentBalance)
    {
        // Override this!
    }
    /**
     * Modify this detail transaction.
     * First calculate the current amount posted for this type of trx, then
     * call addDetailTrx with the current balance.
     */
    public void modifyDetailTrx(TransactionType recTransactionType, AcctDetailDist recAcctDetailDist, AcctDetail recAcctDetail, Period recPeriod)
    {
        recAcctDetailDist.setKeyArea(AcctDetailDist.TRX_DESC_ID_KEY);
        BaseField fldTrxDescID = recTransactionType.getField(TransactionType.TRX_DESC_ID);
        BaseField fldTrxID = this.getOwner().getField(Trx.ID);
        BaseField fldTrxTypeID = recTransactionType.getField(TransactionType.ID);
        FileListener listener = null;
        recAcctDetailDist.addListener(listener = new SubFileFilter(fldTrxDescID, AcctDetailDist.TRX_DESC_ID, fldTrxID, AcctDetailDist.TRX_ID, fldTrxTypeID, AcctDetailDist.TRX_TYPE_ID));
        recAcctDetailDist.close();
        double dCurrentTotal = 0.00;
        try {
            while (recAcctDetailDist.hasNext())
            {
                recAcctDetailDist.next();
                dCurrentTotal = dCurrentTotal + recAcctDetailDist.getField(AcctDetailDist.AMOUNT).getValue();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            recAcctDetailDist.removeListener(listener, true);
        }
        this.addDetailTrx(recTransactionType, recAcctDetailDist, recAcctDetail, recPeriod, dCurrentTotal);
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return null;    // Override this
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        return null;    // Override this
    }
    /**
     * GetTrxStatus Method.
     */
    public TrxStatus getTrxStatus()
    {
        RecordOwner recordOwner = this.getOwner().findRecordOwner();
        TrxStatus recTrxStatus = (TrxStatus)recordOwner.getRecord(TrxStatus.TRX_STATUS_FILE);
        if (recTrxStatus == null)
        {
            recTrxStatus = new TrxStatus(recordOwner);  // Rarely, but if it doesn't exist in the screen, add it!
            this.getOwner().addListener(new FreeOnFreeHandler(recTrxStatus));
        }
        return recTrxStatus;
    }

}
