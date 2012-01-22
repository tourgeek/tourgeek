/**
 * @(#)Trx.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  Trx - .
 */
public class Trx extends VirtualRecord
     implements TrxModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kTrxStatusID = kVirtualRecordLastField + 1;
    public static final int kTrxLastField = kTrxStatusID;
    public static final int kTrxFields = kTrxStatusID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxLastKey = kIDKey;
    public static final int kTrxKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    protected AcctDetail m_recAcctDetail = null;
    protected AcctDetailDist m_recAcctDetailDist = null;
    protected AcctDetailDist m_recAcctDetailDistSearch = null;
    protected Period m_recPeriod = null;
    protected TransactionType m_recTransactionType = null;
    /**
     * Default constructor.
     */
    public Trx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Trx(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recAcctDetail = null;
        m_recAcctDetailDist = null;
        m_recAcctDetailDistSearch = null;
        m_recPeriod = null;
        m_recTransactionType = null;
        super.init(screen);
    }

    public static final String kTrxFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kTrxStatusID)
            field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTrxLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recAcctDetail != null)
            m_recAcctDetail.free();
        m_recAcctDetail = null;
        if (m_recAcctDetailDist != null)
            m_recAcctDetailDist.free();
        m_recAcctDetailDist = null;
        if (m_recTransactionType != null)
            m_recTransactionType.free();
        m_recTransactionType = null;
        if (m_recPeriod != null)
            m_recPeriod.free();
        m_recPeriod = null;
        if (m_recAcctDetailDistSearch != null)
            m_recAcctDetailDistSearch.free();
        m_recAcctDetailDistSearch = null;
        super.free();
    }
    /**
     * CheckFiles Method.
     */
    public void checkFiles()
    {
        RecordOwner recordOwner = Utility.getRecordOwner(this);
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
     * Get the record with this file name.
     * This is more usefull in the queryrecord.
     * @return This if match.
     */
    public Record getRecord(String strFileName)
    {
        if (TransactionType.kTransactionTypeFile.equalsIgnoreCase(strFileName))
            if (m_recTransactionType != null)
                return m_recTransactionType;
        if (AcctDetail.kAcctDetailFile.equalsIgnoreCase(strFileName))
            if (m_recAcctDetail != null)
                return m_recAcctDetail;
        if (AcctDetailDist.kAcctDetailDistFile.equalsIgnoreCase(strFileName))
            if (m_recAcctDetailDist != null)
                return m_recAcctDetailDist;
        if (Period.kPeriodFile.equalsIgnoreCase(strFileName))
            if (m_recPeriod != null)
                return m_recPeriod;
        return super.getRecord(strFileName);
    }
    /**
     * Get the AcctDetailDist file and set it for for a sequential search on
     * this Trx.
     * @param fldTrxDescID The TrxDescField for this Trx.
     * @return The AcctDetailDist file.
     */
    public AcctDetailDist getDistSearch(BaseField fldTrxDescID)
    {
        if (m_recAcctDetailDistSearch == null)
        {
            m_recAcctDetailDistSearch = new AcctDetailDist(Utility.getRecordOwner(this));
            if (m_recAcctDetailDistSearch.getRecordOwner() != null)
                m_recAcctDetailDistSearch.getRecordOwner().removeRecord(m_recAcctDetailDistSearch);
        }
        else
            m_recAcctDetailDistSearch.removeListener(m_recAcctDetailDistSearch.getListener(SubFileFilter.class.getName()), true);
        m_recAcctDetailDistSearch.setKeyArea(AcctDetailDist.kTrxDescIDKey);
        m_recAcctDetailDistSearch.addListener(new SubFileFilter(fldTrxDescID, AcctDetailDist.kTrxDescID, this.getField(BaseTrx.kID), AcctDetailDist.kTrxID, null, -1));
        return m_recAcctDetailDistSearch;
    }
    /**
     * Get the AcctDetailDist file and set it for for a sequential search on
     * this Trx.
     * @return The AcctDetailDist file.
     */
    public AcctDetailDist getDistSearch()
    {
        Record recTrxStatus = ((ReferenceField)this.getField(Trx.kTrxStatusID)).getReference();
        if (recTrxStatus == null)
            return null;
        return this.getDistSearch(recTrxStatus.getField(TrxStatus.kTrxDescID));
    }
    /**
     * Get the cached transaction type record.
     * @return The transaction type record.
     */
    public TransactionType getTransactionType()
    {
        if (m_recTransactionType == null)
        {
            m_recTransactionType = new TransactionType(Utility.getRecordOwner(this));
            if (m_recTransactionType.getRecordOwner() != null)
                m_recTransactionType.getRecordOwner().removeRecord(m_recTransactionType);
        }
        return m_recTransactionType;
    }
    /**
     * Look at the posting distribution for this transaction and get the
     * account for this transaction type.
     * @param fldTrxDescID The Trx desc ID.
     * @param iTrxStatusID The Trx status ID of the transaction you are looking for.
     * @param strPostingType The type of posting that you are looking for.
     * @return The field with the account ID of this transaction type (null = none).
     */
    public BaseField getTrxAccountID(BaseField fldTrxDescID, int iTrxStatusID, String strPostingType)
    {
        BaseField fldAccountID = null;
        
        AcctDetailDist recAcctDetailDistSearch = this.getDistSearch(fldTrxDescID);
        
        try {
            m_recAcctDetailDistSearch.close();
            while (m_recAcctDetailDistSearch.hasNext())
            {
                m_recAcctDetailDistSearch.next();
                Record recAcctDetail = ((ReferenceField)m_recAcctDetailDistSearch.getField(AcctDetailDist.kAcctDetailID)).getReference();
                if (recAcctDetail != null)
                    if (!recAcctDetail.isNull())
                {
                    Record recTransactionType = ((ReferenceField)recAcctDetail.getField(AcctDetail.kTrxTypeID)).getReference();
                    if (recTransactionType != null)
                        if (!recTransactionType.isNull())
                    {
                        if (recTransactionType.getField(TransactionType.kSourceTrxStatusID).getValue() == iTrxStatusID)
                            if ((strPostingType.equalsIgnoreCase(recTransactionType.getField(TransactionType.kPostingType).toString()))
                                || (strPostingType.equalsIgnoreCase(recTransactionType.getField(TransactionType.kTypeCode).toString())))
                        {
                            fldAccountID = recAcctDetail.getField(AcctDetail.kAccountID);
                            break;
                        }
                    }
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return fldAccountID;
    }
    /**
     * Look at the posting distribution for this transaction and get the
     * account for this transaction type.
     * @param iTrxStatusID The Trx status ID of the transaction you are looking for.
     * @param strPostingType The type of posting that you are looking for.
     * @return The field with the account ID of this transaction type (null = none).
    .
     */
    public BaseField getTrxAccountID(int iTrxStatusID, String strPostingType)
    {
        Record recTrxStatus = ((ReferenceField)this.getField(Trx.kTrxStatusID)).getReference();
        if (recTrxStatus == null)
            return null;
        return this.getTrxAccountID(recTrxStatus.getField(TrxStatus.kTrxDescID), iTrxStatusID, strPostingType);
    }
    /**
     * Look at the posting distribution for this transaction and get the
     * account for this transaction type.
     * @param strPostingType The type of posting that you are looking for.
     * @return The field with the account ID of this transaction type (null = none).
     */
    public BaseField getTrxAccountID(String strPostingType)
    {
        int iTrxStatusID = (int)this.getField(Trx.kTrxStatusID).getValue();
        return this.getTrxAccountID(iTrxStatusID, strPostingType);
    }
    /**
     * Get the transaction group ID for this Trx.
     * @return The group trx ID.
     */
    public int getTrxGroupID()
    {
        Record recTrxStatus = ((ReferenceField)this.getField(Trx.kTrxStatusID)).getReference();
        if (recTrxStatus == null)
            return -1;  // Never
        if (m_recTransactionType == null)
        {
            m_recTransactionType = new TransactionType(Utility.getRecordOwner(this));
            if (m_recTransactionType.getRecordOwner() != null)
                m_recTransactionType.getRecordOwner().removeRecord(m_recTransactionType);
        }
        m_recTransactionType.getField(TransactionType.kSourceTrxStatusID).moveFieldToThis(recTrxStatus.getField(TrxStatus.kID));
        m_recTransactionType.getField(TransactionType.kTrxDescID).moveFieldToThis(recTrxStatus.getField(TrxStatus.kTrxDescID));
        try {
            m_recTransactionType.setKeyArea(TransactionType.kSourceTrxStatusIDKey);
            if (m_recTransactionType.seek("="))
            {
                return (int)m_recTransactionType.getField(TransactionType.kTrxGroupID).getValue();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return -1;
    }
    /**
     * Get the TrxDesc record for this type of record.
     * @param recTrxDesc An empty TrxDesc record to use.
     * @return A TrxDesc record with this trx's desc in it.
     */
    public TrxDesc getTrxDesc(TrxDesc recTrxDesc)
    {
        if (recTrxDesc == null)
        {
            // If there is a valid status, get the Desc from the status
            Record recTrxStatus = ((ReferenceField)this.getField(Trx.kTrxStatusID)).getReference();
            if (recTrxStatus != null)
                return (TrxDesc)((ReferenceField)recTrxStatus.getField(TrxStatus.kTrxDescID)).getReference();
            // Okay, no status. Get the desc from the linked status so TrxDesc will be freed properly
            recTrxStatus =  ((ReferenceField)this.getField(Trx.kTrxStatusID)).getReferenceRecord();
            recTrxDesc = (TrxDesc)((ReferenceField)recTrxStatus.getField(TrxStatus.kTrxDescID)).getReferenceRecord();
        }
        // Need to read the desc the hard way. Read the code which is the file name.
        recTrxDesc.setKeyArea(TrxDesc.kDescCodeKey);
        recTrxDesc.getField(TrxDesc.kDescCode).setString(this.getTableNames(false));
        try {
            if (recTrxDesc.seek("="))
                return recTrxDesc;
        } catch (DBException ex) {
            ex.printStackTrace();
        }
        return null;    // Never;
    }

}
