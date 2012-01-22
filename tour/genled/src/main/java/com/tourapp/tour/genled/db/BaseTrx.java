/**
 * @(#)BaseTrx.
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
import org.jbundle.main.user.db.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  BaseTrx - Base system transaction.
 */
public class BaseTrx extends Trx
     implements BaseTrxModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxStatusID = kTrxStatusID;
    public static final int kTrxDate = kTrxLastField + 1;
    public static final int kAmountLocal = kTrxDate + 1;
    public static final int kTrxEntry = kAmountLocal + 1;
    public static final int kTrxUserID = kTrxEntry + 1;
    public static final int kBaseTrxLastField = kTrxUserID;
    public static final int kBaseTrxFields = kTrxUserID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBaseTrxLastKey = kIDKey;
    public static final int kBaseTrxKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public BaseTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BaseTrx(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kBaseTrxFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setHidden(true);
        }
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxDate)
            field = new DateTimeField(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountLocal)
            field = new CurrencyField(this, "AmountLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxEntry)
            field = new BaseTrx_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxUserID)
            field = new BaseTrx_TrxUserID(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBaseTrxLastField)
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
     * Get this cached record.
     */
    public Record getCachedRecord(String strRecord)
    {
        if (AcctDetail.kAcctDetailFile.equalsIgnoreCase(strRecord))
            return m_recAcctDetail;
        if (AcctDetailDist.kAcctDetailDistFile.equalsIgnoreCase(strRecord))
            return m_recAcctDetailDist;
        if (TransactionType.kTransactionTypeFile.equalsIgnoreCase(strRecord))
            return m_recTransactionType;
        if (Period.kPeriodFile.equalsIgnoreCase(strRecord))
            return m_recPeriod;
        return null;
    }
    /**
     * StartDistTrx Method.
     */
    public void startDistTrx()
    {
        this.checkFiles();
        m_recAcctDetailDist.startDistTrx();
    }
    /**
     * EndDistTrx Method.
     */
    public void endDistTrx()
    {
        if (m_recAcctDetailDist != null)
            m_recAcctDetailDist.endDistTrx();
    }
    /**
     * Cancel the transaction in progress.
     */
    public boolean cancelDistTrx()
    {
        return false;    // Error - Not successful
    }
    /**
     * GetTrxType Method.
     */
    public TransactionType getTrxType(String strPostingType)
    {
        int iTrxGroupID = this.getTrxGroupID();
        if (m_recTransactionType == null)
        {
            m_recTransactionType = new TransactionType(this.getRecordOwner());
            if (this.getRecordOwner() != null)
                this.getRecordOwner().removeRecord(m_recTransactionType);
        }
        return m_recTransactionType.getTrxType(iTrxGroupID, strPostingType);
    }
    /**
     * Post this transaction to G/L.
     */
    public boolean onPost(BaseField fldTrxAccountID, BaseField fldDistAccountID)
    {
        this.checkFiles();
        if ((fldTrxAccountID != null) && (fldDistAccountID != null))
            this.startDistTrx();
        // Step 2a - Create and write the transaction (in BaseTrx).
        boolean bSuccess = this.onPostTrx();
        if (!bSuccess)
        {       // Back out and void - bad trx.
            this.onVoidTrx();
            return false;
        }
        // Step 2b - Post the transaction side of the distribution.
        if (fldTrxAccountID != null)
            bSuccess = this.onPostTrxDist(fldTrxAccountID, PostingType.TRX_POST);
        if (!bSuccess)
        {       // Back out and void - bad trx.
            this.onVoidTrx();
            return false;
        }
        // Step 2c - Post the distribution side of the transaction.
        if (fldDistAccountID != null)
            bSuccess = this.onPostTrxDist(fldDistAccountID, PostingType.DIST_POST);
        if (!bSuccess)
        {       // Back out and void - bad trx.
            this.onVoidTrx();
            return false;
        }
        if ((fldTrxAccountID != null) && (fldDistAccountID != null))
            this.endDistTrx();
        return true;    // Success
    }
    /**
     * OnPostTrx Method.
     */
    public boolean onPostTrx()
    {
        try   {
        // Step 2a - Write the transaction.
            this.writeAndRefresh();
        } catch (DBException ex)    {
            ex.printStackTrace();
            //+recAcctDetail.getDatabase().rollback();
            return false;
        }
        return true;    // Success
    }
    /**
     * OnPostTrxDist Method.
     */
    public boolean onPostTrxDist(BaseField fldAccountID, double dTrxAmount, TransactionType recTrxType, AcctDetail recAcctDetail, AcctDetailDist recAcctDetailDist, Period recPeriod)
    {
        // Step 2b - Post the transaction side of the distribution.
        if (recTrxType == null)
            return this.setupErrorMessage(null, "Transaction type is not valid");
        
        DateTimeField fldTrxDate = null;
        if (!recTrxType.getField(TransactionType.kTrxDateField).isNull())
            fldTrxDate = (DateTimeField)this.getField(recTrxType.getField(TransactionType.kTrxDateField).toString());
        if (fldTrxDate == null)
            fldTrxDate = (DateTimeField)this.getField(BaseTrx.kTrxDate);
        
        DateTimeField fldTrxEntryDate = null;
        if (!recTrxType.getField(TransactionType.kEntryDateField).isNull())
            fldTrxEntryDate = (DateTimeField)this.getField(recTrxType.getField(TransactionType.kEntryDateField).toString());
        else if (fldTrxEntryDate == null)
            fldTrxEntryDate = (DateTimeField)this.getField(BaseTrx.kTrxEntry);
        
        int iUserID = -1;
        if (!recTrxType.getField(TransactionType.kUserIDField).isNull())
        {
            BaseField fldUserID = this.getField(recTrxType.getField(TransactionType.kUserIDField).toString());
            if (fldUserID != null)
                iUserID = (int)fldUserID.getValue();
        }
        else if (iUserID == -1)
            iUserID = (int)this.getField(BaseTrx.kTrxUserID).getValue();
        
        BaseField fldTrxID = null;
        if (!recTrxType.getField(TransactionType.kTrxIDField).isNull())
            fldTrxID = this.getField(recTrxType.getField(TransactionType.kTrxIDField).toString());
        if (fldTrxID == null)
            fldTrxID = this.getField(BaseTrx.kID);
        
        return recAcctDetailDist.addDetailTrx(fldAccountID, fldTrxDate, fldTrxID, recTrxType, fldTrxEntryDate, dTrxAmount, iUserID, recAcctDetail, recPeriod);
    }
    /**
     * OnPostTrxDist Method.
     */
    public boolean onPostTrxDist(BaseField fldAccountID, double dTrxAmount, int iTrxGroupID, String strPostingType, AcctDetail recAcctDetail, AcctDetailDist recAcctDetailDist, Period recPeriod)
    {
        TransactionType recTrxType = m_recTransactionType.getTrxType(iTrxGroupID, strPostingType);
        return this.onPostTrxDist(fldAccountID, dTrxAmount, recTrxType, recAcctDetail, recAcctDetailDist, recPeriod);
    }
    /**
     * OnPostTrxDist Method.
     */
    public boolean onPostTrxDist(BaseField fldAccountID, double dTrxAmount, String strPostingType, AcctDetail recAcctDetail, AcctDetailDist recAcctDetailDist, Period recPeriod)
    {
        int iTrxGroupID = this.getTrxGroupID();
        return this.onPostTrxDist(fldAccountID, dTrxAmount, iTrxGroupID, strPostingType, recAcctDetail, recAcctDetailDist, recPeriod);
    }
    /**
     * OnPostTrxDist Method.
     */
    public boolean onPostTrxDist(BaseField fldAccountID, String strPostingType)
    {
        double dTrxAmount = 0;
        TransactionType recTrxType = this.getTrxType(strPostingType);
        if ((recTrxType != null) && (!recTrxType.getField(TransactionType.kAmountField).isNull()))
            dTrxAmount = this.getField(recTrxType.getField(TransactionType.kAmountField).toString()).getValue();
        else
            dTrxAmount = this.getField(BaseTrx.kAmountLocal).getValue();
        return this.onPostTrxDist(fldAccountID, dTrxAmount, strPostingType);
    }
    /**
     * OnPostTrxDist Method.
     */
    public boolean onPostTrxDist(BaseField fldAccountID, double dTrxAmount, String strPostingType)
    {
        this.checkFiles();
        return this.onPostTrxDist(fldAccountID, dTrxAmount, strPostingType, m_recAcctDetail, m_recAcctDetailDist, m_recPeriod);
    }
    /**
     * Void this transaction and reverse the G/L postings.
     */
    public boolean onVoid()
    {
        this.checkFiles();
        this.startDistTrx();
        // Step 2a - Create and write the bank transaction (in BankTrx).
        if (!this.onVoidTrx())
            return this.cancelDistTrx();
        // Step 2b - Go through all the transaction distribution and reverse them.
        if (!this.onVoidReverse())
            return this.cancelDistTrx();
        this.endDistTrx();
        return true;    // Success
    }
    /**
     * Zero this part of the transaction.
     */
    public boolean onVoidTrx()
    {
        // Step 2a - Write the transaction.
        if (this.getField(BaseTrx.kAmountLocal).getValue() == 0)
            return this.setupErrorMessage(null, "Transaction already cleared");
        try   {
            if (this.getEditMode() == Constants.EDIT_CURRENT)
            {
                if (this.edit() != DBConstants.NORMAL_RETURN)
                    return this.setupErrorMessage(null, "Transaction in use, can't post");
            }
            if (this.getEditMode() != Constants.EDIT_IN_PROGRESS)
                    return this.setupErrorMessage(null, "Not a valid transaction, can't post");
        
            this.getField(BaseTrx.kAmountLocal).setValue(0);      // Void this trx
            Object objectID = this.getHandle(DBConstants.DATA_SOURCE_HANDLE);
            this.set();
            this.setHandle(objectID, DBConstants.DATA_SOURCE_HANDLE);   // Restore
        
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        return true;    // Success
    }
    /**
     * Reverse all the G/L Transactions.
     */
    public boolean onVoidReverse()
    {
        try   {
            AcctDetailDist recAcctDetailDist = this.getDistSearch();
        
            this.getField(BaseTrx.kTrxEntry).initField(DBConstants.DONT_DISPLAY); // New Entry date = now
        
            recAcctDetailDist.close();
            while (recAcctDetailDist.hasNext())
            {
                recAcctDetailDist.next();
                Record recAcctDetail = ((ReferenceField)recAcctDetailDist.getField(AcctDetailDist.kAcctDetailID)).getReference();
                if (recAcctDetail != null)
                    if (!recAcctDetail.isNull())
                {
                    Record recTransactionType = ((ReferenceField)recAcctDetail.getField(AcctDetail.kTrxTypeID)).getReference();
                    if (recTransactionType != null)
                        if (!recTransactionType.isNull())
                    {
                        if (recTransactionType.getField(TransactionType.kSourceTrxStatusID).getValue() == this.getField(BaseTrx.kTrxStatusID).getValue())
                        {
                            if (this.getField(BaseTrx.kTrxEntry).equals(recAcctDetailDist.getField(AcctDetailDist.kTrxEntry)))
                                continue;   // This is one of my new transactions (Don't reverse)
                            boolean bSuccess = this.onVoidDist(recAcctDetailDist);
                            if (!bSuccess)
                                return bSuccess;
                        }
                    }
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        return true;    // Success
    }
    /**
     * Reverse this transaction distribution.
     */
    public boolean onVoidDist(AcctDetailDist recAcctDetailDist)
    {
        ReferenceField fldAcctDetailID = (ReferenceField)recAcctDetailDist.getField(AcctDetailDist.kAcctDetailID);
        AcctDetail recAcctDetail = (AcctDetail)fldAcctDetailID.getReference();
        if (recAcctDetail == null)
            return this.setupErrorMessage(null, "Distribution does not have a valid transaction");
        
        BaseField fldAccountID = recAcctDetail.getField(AcctDetail.kAccountID);
        ReferenceField fldTrxTypeID = (ReferenceField)recAcctDetail.getField(AcctDetail.kTrxTypeID);
        TransactionType recTransactionType = (TransactionType)fldTrxTypeID.getReference();      // Ouch.. this should NOT be necessary
        if (recTransactionType == null)
            return this.setupErrorMessage(null, "Transaction type is not valid");
        
        DateTimeField trxDate = (DateTimeField)recAcctDetailDist.getField(AcctDetailDist.kTrxDate);
        BaseField fldTrxID = recAcctDetailDist.getField(AcctDetailDist.kTrxID);
        DateTimeField trxEntryDate = (DateTimeField)this.getField(BaseTrx.kTrxEntry); // New Entry date!
        int iUserID = (int)((UserField)this.getField(BaseTrx.kTrxUserID)).getUserID();   // New User ID
        double dAmount = -recAcctDetailDist.getField(AcctDetailDist.kAmount).getValue();
        
        m_recAcctDetailDist.addDetailTrx(fldAccountID, trxDate, fldTrxID, recTransactionType, trxEntryDate, dAmount, iUserID, recAcctDetail, m_recPeriod);
        return true;    // Success
    }
    /**
     * Set the error text and return a unsucessful flag.
     */
    public boolean setupErrorMessage(String strKey, String strDefaultMessage)
    {
        Task task = null;
        if (this.getRecordOwner() != null)
            task = this.getRecordOwner().getTask();
        if (task != null)
        {
            String strMessage = null;
            if (strKey != null)
                strMessage = task.getString(strKey);
            if ((strMessage == null) || (strMessage == strKey))
                if (strDefaultMessage != null)
                    strMessage = strDefaultMessage;
            task.setLastError(strMessage);
        }
        return false; // Error - not successful
    }

}
