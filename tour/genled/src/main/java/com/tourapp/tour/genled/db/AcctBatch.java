/**
 * @(#)AcctBatch.
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
import com.tourapp.tour.genled.screen.batch.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  AcctBatch - Transaction Entry.
 */
public class AcctBatch extends VirtualRecord
     implements AcctBatchModel
{
    private static final long serialVersionUID = 1L;

    public static final int ACCT_BATCH_DETAIL_SCREEN = ScreenConstants.DETAIL_MODE;
    public static final int ACCT_BATCH_POST = ScreenConstants.POST_MODE;
    /**
     * Default constructor.
     */
    public AcctBatch()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctBatch(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(ACCT_BATCH_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "G/L Transaction Batch Header";
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
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == AcctBatch.ACCT_BATCH_DETAIL_SCREEN)
            screen = new AcctBatchDetailGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == AcctBatch.ACCT_BATCH_POST)
            screen = new AcctBatchPost(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new AcctBatchScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new AcctBatchGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
            field = new CounterField(this, ID, 8, null, null);
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
            field = new AcctBatch_UserID(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new BooleanField(this, RECURRING, 1, null, null);
        if (iFieldSeq == 5)
            field = new AcctBatch_TrxDate(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new StringField(this, COMMENTS, 30, null, null);
        if (iFieldSeq == 7)
            field = new StringField(this, SOURCE, 10, null, null);
        if (iFieldSeq == 8)
            field = new CurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new ShortField(this, NEXT_SEQUENCE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == 10)
        {
            field = new BooleanField(this, AUTO_REVERSAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 11)
        {
            field = new DateField(this, AUTO_REVERSAL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 12)
            field = new BooleanField(this, AUTO_CLOSING, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
        {
            field = new AcctBatch_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "UserID");
            keyArea.addKeyField(USER_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(RECURRING, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.addListener(new SubFileIntegrityHandler(AcctBatchDetail.class.getName()));
    }
    /**
     * Create the auto-closing entries.
     */
    public boolean onAutoClosing()
    {
        Task task = this.getRecordOwner().getTask();
        if ((this.getEditMode() == DBConstants.EDIT_NONE)
            || (this.getEditMode() == DBConstants.EDIT_ADD))
        {
            String strError = "Must auto-reverse a new, empty batch";
            strError = ((BaseApplication)task.getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
            task.setLastError(strError);
            return false;
        }
        if ((this.getField(AcctBatch.NEXT_SEQUENCE).getValue() > 1)
            || (this.getField(AcctBatch.BALANCE).getValue() != 0))
        {
            String strError = "Must auto-reverse a new, empty batch";
            strError = ((BaseApplication)task.getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
            task.setLastError(strError);
            return false;
        }
        
        Period recPeriod = new Period(this.findRecordOwner());
        
        AcctBatchDetail recAcctBatchDetail = new AcctBatchDetail(this.findRecordOwner());
        recAcctBatchDetail.addListener(new SubFileFilter(this));
        
        Account recAccount = new Account(this.findRecordOwner());
        AcctDetail recAccountDetail = new AcctDetail(this.findRecordOwner());
        recAccountDetail.addListener(new SubFileFilter(recAccount));
        DateField fldPeriodStartDate = new DateField(null, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        DateField fldPeriodEndDate = new DateField(null, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        Date dateEnd = recPeriod.getPeriodEndDate(((DateTimeField)this.getField(AcctBatch.TRX_DATE)).getDateTime());
        Date dateStart = recPeriod.getPeriodStartDate(((DateTimeField)this.getField(AcctBatch.TRX_DATE)).getDateTime());
        fldPeriodStartDate.setDate(dateStart, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        fldPeriodEndDate.setDate(dateEnd, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        recAccountDetail.addListener(new ExtractRangeFilter(AcctDetail.TRX_DATE, fldPeriodStartDate, fldPeriodEndDate, ExtractRangeFilter.PAD_DEFAULT));
        
        try {
            double dBalance = 0.00;
            while (recAccount.hasNext())
            {
                recAccount.next();
                if (recAccount.getField(Account.CLOSE_YEAR_END).getState() == false)
                    continue;   // Don't close this one.
                double dAmount = 0.00;
                recAccountDetail.close();
                while (recAccountDetail.hasNext())
                {
                    recAccountDetail.next();
                    dAmount = dAmount + recAccountDetail.getField(AcctDetail.AMOUNT_LOCAL).getValue();
                }
        
                if (dAmount == 0.00)
                    continue;   // No need to close a zero amount
                dBalance = dBalance + dAmount;
                recAcctBatchDetail.addNew();
        
                recAcctBatchDetail.getField(AcctBatchDetail.SEQUENCE).setValue(1);
                recAcctBatchDetail.getField(AcctBatchDetail.ACCOUNT_ID).moveFieldToThis(recAccount.getField(Account.ID));
                recAcctBatchDetail.getField(AcctBatchDetail.AMOUNT).setValue(-dAmount);
        
                recAcctBatchDetail.add();
            }
        
            Object bookmark = this.getHandle(DBConstants.BOOKMARK_HANDLE);
            this.edit();
            this.getField(AcctBatch.AUTO_CLOSING).setState(true);
            this.getField(AcctBatch.TRX_DATE).moveFieldToThis(fldPeriodEndDate);
            this.getField(AcctBatch.BALANCE).setValue(dBalance);
            this.set();
            this.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
        
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally   {
            fldPeriodStartDate.free();
            fldPeriodEndDate.free();
            recPeriod.free();
            recAccount.free();
            recAccountDetail.free();
            recAcctBatchDetail.free();
        }
        return true;
    }

}
