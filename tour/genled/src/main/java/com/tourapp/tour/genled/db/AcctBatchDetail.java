/**
 *  @(#)AcctBatchDetail.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.genled.screen.batch.*;

/**
 *  AcctBatchDetail - Journal Entry Batch Detail.
 */
public class AcctBatchDetail extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAcctBatchID = kVirtualRecordLastField + 1;
    public static final int kSequence = kAcctBatchID + 1;
    public static final int kAccountID = kSequence + 1;
    public static final int kAmount = kAccountID + 1;
    public static final int kCounterBalance = kAmount + 1;
    public static final int kAutoDist = kCounterBalance + 1;
    public static final int kAutoAccrual = kAutoDist + 1;
    public static final int kAutoReversal = kAutoAccrual + 1;
    public static final int kAcctBatchDetailLastField = kAutoReversal;
    public static final int kAcctBatchDetailFields = kAutoReversal - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kAcctBatchIDKey = kIDKey + 1;
    public static final int kAcctBatchDetailLastKey = kAcctBatchIDKey;
    public static final int kAcctBatchDetailKeys = kAcctBatchIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String REVERSAL = "Reversal";
    /**
     * Default constructor.
     */
    public AcctBatchDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctBatchDetail(RecordOwner screen)
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

    public static final String kAcctBatchDetailFile = "AcctBatchDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAcctBatchDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Account Transaction";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new AcctBatchDetailScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new AcctBatchDetailGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
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
        if (iFieldSeq == kAcctBatchID)
        {
            field = new ReferenceField(this, "AcctBatchID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kSequence)
            field = new ShortField(this, "Sequence", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAccountID)
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmount)
            field = new DrCrField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCounterBalance)
            field = new BooleanField(this, "CounterBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAutoDist)
            field = new BooleanField(this, "AutoDist", 1, null, null);
        if (iFieldSeq == kAutoAccrual)
            field = new BooleanField(this, "AutoAccrual", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAutoReversal)
            field = new BooleanField(this, "AutoReversal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAcctBatchDetailLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kAcctBatchIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AcctBatchID");
            keyArea.addKeyField(kAcctBatchID, DBConstants.ASCENDING);
            keyArea.addKeyField(kSequence, DBConstants.ASCENDING);
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAcctBatchDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAcctBatchDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Do the auto-distribution.
     */
    public int onAutoDist(Record recAccount)
    {
        if (!recAccount.getField(Account.kAutoDistID).isNull())
        {
            try   {
                Record recAcctBatchDetail = (Record)this.clone(true);
                AutoDist recAutoDist = new AutoDist(Utility.getRecordOwner(this));
                ((ReferenceField)recAccount.getField(Account.kAutoDistID)).setReferenceRecord(recAutoDist);
                Record record = ((ReferenceField)recAccount.getField(Account.kAutoDistID)).getReference();
                if (record == null)
                    return DBConstants.NORMAL_RETURN;
                AutoDistDetail recAutoDistDetail = new AutoDistDetail(this.getRecordOwner());
                recAutoDistDetail.addListener(new SubFileFilter(recAutoDist));
                double dAmount = this.getField(AcctBatchDetail.kAmount).getValue();
                double dBalance = dAmount;
                double dPercentTotal = 0;
                while (recAutoDistDetail.hasNext())
                {
                    recAutoDistDetail.next();
                    recAcctBatchDetail.addNew();
                    for (int i = DBConstants.MAIN_FIELD; i < AcctBatchDetail.kAcctBatchDetailFields; i++)
                    {
                        recAcctBatchDetail.getField(i).moveFieldToThis(this.getField(i));
                    }
                    recAcctBatchDetail.getField(AcctBatchDetail.kAccountID).moveFieldToThis(recAutoDistDetail.getField(AutoDistDetail.kDistAccountID));
                    recAcctBatchDetail.getField(AcctBatchDetail.kAutoDist).setState(true);
                    double dPercent = recAutoDistDetail.getField(AutoDistDetail.kDistPercent).getValue();
                    dPercentTotal += dPercent;
                    double dValue = Math.floor(-dAmount * dPercent * 100 + 0.5) / 100;
                    dBalance = Math.floor((dValue + dBalance) * 100 + 0.5) / 100;
                    if (dPercentTotal > .98) if (dPercentTotal < 1.02) if (dBalance != 0.00) if (!recAutoDistDetail.hasNext())
                    { // If this is the last one, make sure the distribution equals 0. (alows for 33.33% x 3 = 100%)
                        dValue = dValue - dBalance;     // Make sure balance = 0;
                    }
                    recAcctBatchDetail.getField(AcctBatchDetail.kAmount).setValue(dValue);
                    recAcctBatchDetail.add();   // Add the counter-balance entry
                }
                recAcctBatchDetail.free();
                recAcctBatchDetail = null;
                recAutoDist.free();
                recAutoDist = null;
                recAutoDistDetail.free();
                recAutoDistDetail = null;
            } catch (DBException ex)    {
                ex.printStackTrace();
            } catch (CloneNotSupportedException e)  {
                // Never
            }
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Create counter-balance entry.
     */
    public int onCounterBalance(Record recAccount)
    {
        if (recAccount.getField(Account.kCounterAccountID).getState())
        {
            try   {
                Record recAcctBatchDetail = (Record)this.clone(true);
                recAcctBatchDetail.addNew();
                recAcctBatchDetail.moveFields(this, null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE, true, false, false);
                recAcctBatchDetail.getField(AcctBatchDetail.kAccountID).moveFieldToThis(recAccount.getField(Account.kCounterAccountID));
                recAcctBatchDetail.getField(AcctBatchDetail.kAmount).setValue(-this.getField(AcctBatchDetail.kAmount).getValue());
                recAcctBatchDetail.getField(AcctBatchDetail.kCounterBalance).setState(true);
                recAcctBatchDetail.add();   // Add the counter-balance entry
                recAcctBatchDetail.free();
                recAcctBatchDetail = null;
            } catch (DBException ex)    {
                ex.printStackTrace();
            } catch (CloneNotSupportedException e)  {
                // Never
            }
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Create a new transaction set that reverses the transaction sequence
     * that the current record is in.
     */
    public boolean onReversal()
    {
        boolean bSuccess = true;
        RecordOwner screen = this.getRecordOwner();
        if (screen == null)
            return false;
        int iSequence = (int)this.getField(AcctBatchDetail.kSequence).getValue();
        if (this.isModified())
        {
            try {
                if (this.getEditMode() == DBConstants.EDIT_ADD)
                    this.add();
                else if (this.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    this.set();
                else
                    return false;
            } catch (DBException ex)    {
                screen.getTask().setLastError(ex.getMessage());
                return false;
            }
        }
        else if (this.getEditMode() != Constants.EDIT_CURRENT)
            return false;
        Record recAcctBatch = screen.getRecord(AcctBatch.kAcctBatchFile);
        if (recAcctBatch == null)
            return false;
        if (recAcctBatch.getField(AcctBatch.kBalance).getValue() != 0.00)
        {
            String strError = "Batch must be balanced";
            ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
            this.getTask().setLastError(strError);
            return false;
        }
        
        try   {
            BaseField fldTrxSeq = (BaseField)this.getField(AcctBatchDetail.kSequence).clone();
            fldTrxSeq.setValue(iSequence);
            Record recAcctBatchDetail = (Record)this.clone();
            if (recAcctBatchDetail == null)
                return false;
            Record recAcctBatchDetailOut = (Record)this.clone(true);
            recAcctBatchDetail.setKeyArea(AcctBatchDetail.kAcctBatchIDKey);
            while (recAcctBatchDetail.getListener() != null)
            {
                recAcctBatchDetail.removeListener(recAcctBatchDetail.getListener(), true);
            }
            recAcctBatchDetail.addNew();
            FileListener listener = new SubFileFilter(recAcctBatch.getField(AcctBatch.kID), AcctBatchDetail.kAcctBatchID, fldTrxSeq, AcctBatchDetail.kSequence, null, -1);
            recAcctBatchDetail.addListener(listener);
            recAcctBatchDetail.close();
            double dBalance = 0;
            while (recAcctBatchDetail.hasNext())
            {
                recAcctBatchDetail.next();
                dBalance += recAcctBatchDetail.getField(AcctBatchDetail.kAmount).getValue();
            }
            if (dBalance != 0)
            {
                bSuccess = false;
                Task task = this.getRecordOwner().getTask();
                String strError = "Transaction must be balanced";
                strError = ((BaseApplication)task.getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
                task.setLastError(strError);
            }
            else
            {
                recAcctBatchDetail.close();
                while (recAcctBatchDetail.hasNext())
                {
                    recAcctBatchDetail.next();
                    if (recAcctBatchDetail.getField(AcctBatchDetail.kAutoReversal).getState())
                        continue;   // Don't re-add this
                    recAcctBatchDetail.edit();
                    recAcctBatchDetail.getField(AcctBatchDetail.kAutoAccrual).setState(true);
                    recAcctBatchDetailOut.addNew();
                    recAcctBatchDetailOut.moveFields(recAcctBatchDetail, null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE, true, false, false);
                    recAcctBatchDetailOut.getField(AcctBatchDetail.kSequence).moveFieldToThis(recAcctBatch.getField(AcctBatch.kNextSequence));
                    recAcctBatchDetailOut.getField(AcctBatchDetail.kAmount).setValue(-recAcctBatchDetail.getField(AcctBatchDetail.kAmount).getValue());
                    recAcctBatchDetailOut.getField(AcctBatchDetail.kAutoReversal).setState(true);
                    recAcctBatchDetailOut.add();     // Add the counter-balance entry
                    recAcctBatchDetail.set();
                }
            }
            recAcctBatchDetail.free();
            recAcctBatchDetail = null;
            fldTrxSeq.free();
            fldTrxSeq = null;
            recAcctBatchDetailOut.free();
            recAcctBatchDetailOut = null;
        } catch (DBException ex)    {
            ex.printStackTrace();
        } catch (CloneNotSupportedException e)  {
            // Never
        }
        return bSuccess;
    }

}
