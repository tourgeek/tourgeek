/**
 * @(#)AcctBatchDetail.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.model.tour.genled.db.*;

/**
 *  AcctBatchDetail - Journal Entry Batch Detail.
 */
public class AcctBatchDetail extends VirtualRecord
     implements AcctBatchDetailModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(ACCT_BATCH_DETAIL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ACCT_BATCH_DETAIL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(ACCT_BATCH_DETAIL_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setHidden(true);
        }
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
        {
            field = new ReferenceField(this, ACCT_BATCH_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 4)
            field = new ShortField(this, SEQUENCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new AccountField(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new DrCrField(this, AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new BooleanField(this, COUNTER_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new BooleanField(this, AUTO_DIST, 1, null, null);
        if (iFieldSeq == 9)
            field = new BooleanField(this, AUTO_ACCRUAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new BooleanField(this, AUTO_REVERSAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AcctBatchID");
            keyArea.addKeyField(ACCT_BATCH_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(SEQUENCE, DBConstants.ASCENDING);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Do the auto-distribution.
     */
    public int onAutoDist(Record recAccount)
    {
        if (!recAccount.getField(Account.AUTO_DIST_ID).isNull())
        {
            try   {
                Record recAcctBatchDetail = (Record)this.clone(true);
                AutoDist recAutoDist = new AutoDist(this.findRecordOwner());
                ((ReferenceField)recAccount.getField(Account.AUTO_DIST_ID)).setReferenceRecord(recAutoDist);
                Record record = ((ReferenceField)recAccount.getField(Account.AUTO_DIST_ID)).getReference();
                if (record == null)
                    return DBConstants.NORMAL_RETURN;
                AutoDistDetail recAutoDistDetail = new AutoDistDetail(this.getRecordOwner());
                recAutoDistDetail.addListener(new SubFileFilter(recAutoDist));
                double dAmount = this.getField(AcctBatchDetail.AMOUNT).getValue();
                double dBalance = dAmount;
                double dPercentTotal = 0;
                while (recAutoDistDetail.hasNext())
                {
                    recAutoDistDetail.next();
                    recAcctBatchDetail.addNew();
                    for (int i = DBConstants.MAIN_FIELD; i < recAcctBatchDetail.getFieldCount(); i++)
                    {
                        recAcctBatchDetail.getField(i).moveFieldToThis(this.getField(i));
                    }
                    recAcctBatchDetail.getField(AcctBatchDetail.ACCOUNT_ID).moveFieldToThis(recAutoDistDetail.getField(AutoDistDetail.DIST_ACCOUNT_ID));
                    recAcctBatchDetail.getField(AcctBatchDetail.AUTO_DIST).setState(true);
                    double dPercent = recAutoDistDetail.getField(AutoDistDetail.DIST_PERCENT).getValue();
                    dPercentTotal += dPercent;
                    double dValue = Math.floor(-dAmount * dPercent * 100 + 0.5) / 100;
                    dBalance = Math.floor((dValue + dBalance) * 100 + 0.5) / 100;
                    if (dPercentTotal > .98) if (dPercentTotal < 1.02) if (dBalance != 0.00) if (!recAutoDistDetail.hasNext())
                    { // If this is the last one, make sure the distribution equals 0. (alows for 33.33% x 3 = 100%)
                        dValue = dValue - dBalance;     // Make sure balance = 0;
                    }
                    recAcctBatchDetail.getField(AcctBatchDetail.AMOUNT).setValue(dValue);
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
        if (recAccount.getField(Account.COUNTER_ACCOUNT_ID).getState())
        {
            try   {
                Record recAcctBatchDetail = (Record)this.clone(true);
                recAcctBatchDetail.addNew();
                recAcctBatchDetail.moveFields(this, null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE, true, false, false);
                recAcctBatchDetail.getField(AcctBatchDetail.ACCOUNT_ID).moveFieldToThis(recAccount.getField(Account.COUNTER_ACCOUNT_ID));
                recAcctBatchDetail.getField(AcctBatchDetail.AMOUNT).setValue(-this.getField(AcctBatchDetail.AMOUNT).getValue());
                recAcctBatchDetail.getField(AcctBatchDetail.COUNTER_BALANCE).setState(true);
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
        int iSequence = (int)this.getField(AcctBatchDetail.SEQUENCE).getValue();
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
        Record recAcctBatch = (Record)screen.getRecord(AcctBatch.ACCT_BATCH_FILE);
        if (recAcctBatch == null)
            return false;
        if (recAcctBatch.getField(AcctBatch.BALANCE).getValue() != 0.00)
        {
            String strError = "Batch must be balanced";
            ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
            this.getTask().setLastError(strError);
            return false;
        }
        
        try   {
            BaseField fldTrxSeq = (BaseField)this.getField(AcctBatchDetail.SEQUENCE).clone();
            fldTrxSeq.setValue(iSequence);
            Record recAcctBatchDetail = (Record)this.clone();
            if (recAcctBatchDetail == null)
                return false;
            Record recAcctBatchDetailOut = (Record)this.clone(true);
            recAcctBatchDetail.setKeyArea(AcctBatchDetail.ACCT_BATCH_ID_KEY);
            while (recAcctBatchDetail.getListener() != null)
            {
                recAcctBatchDetail.removeListener(recAcctBatchDetail.getListener(), true);
            }
            recAcctBatchDetail.addNew();
            FileListener listener = new SubFileFilter(recAcctBatch.getField(AcctBatch.ID), AcctBatchDetail.ACCT_BATCH_ID, fldTrxSeq, AcctBatchDetail.SEQUENCE, null, null);
            recAcctBatchDetail.addListener(listener);
            recAcctBatchDetail.close();
            double dBalance = 0;
            while (recAcctBatchDetail.hasNext())
            {
                recAcctBatchDetail.next();
                dBalance += recAcctBatchDetail.getField(AcctBatchDetail.AMOUNT).getValue();
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
                    if (recAcctBatchDetail.getField(AcctBatchDetail.AUTO_REVERSAL).getState())
                        continue;   // Don't re-add this
                    recAcctBatchDetail.edit();
                    recAcctBatchDetail.getField(AcctBatchDetail.AUTO_ACCRUAL).setState(true);
                    recAcctBatchDetailOut.addNew();
                    recAcctBatchDetailOut.moveFields(recAcctBatchDetail, null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE, true, false, false);
                    recAcctBatchDetailOut.getField(AcctBatchDetail.SEQUENCE).moveFieldToThis(recAcctBatch.getField(AcctBatch.NEXT_SEQUENCE));
                    recAcctBatchDetailOut.getField(AcctBatchDetail.AMOUNT).setValue(-recAcctBatchDetail.getField(AcctBatchDetail.AMOUNT).getValue());
                    recAcctBatchDetailOut.getField(AcctBatchDetail.AUTO_REVERSAL).setState(true);
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
