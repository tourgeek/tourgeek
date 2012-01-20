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

    //public static final int kID = kID;
    public static final int kUserID = kVirtualRecordLastField + 1;
    public static final int kRecurring = kUserID + 1;
    public static final int kTrxDate = kRecurring + 1;
    public static final int kComments = kTrxDate + 1;
    public static final int kSource = kComments + 1;
    public static final int kBalance = kSource + 1;
    public static final int kNextSequence = kBalance + 1;
    public static final int kAutoReversal = kNextSequence + 1;
    public static final int kAutoReversalDate = kAutoReversal + 1;
    public static final int kAutoClosing = kAutoReversalDate + 1;
    public static final int kTrxEntry = kAutoClosing + 1;
    public static final int kAcctBatchLastField = kTrxEntry;
    public static final int kAcctBatchFields = kTrxEntry - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kUserIDKey = kIDKey + 1;
    public static final int kAcctBatchLastKey = kUserIDKey;
    public static final int kAcctBatchKeys = kUserIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kAcctBatchFile = "AcctBatch";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAcctBatchFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kUserID)
            field = new AcctBatch_UserID(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRecurring)
            field = new BooleanField(this, "Recurring", 1, null, null);
        if (iFieldSeq == kTrxDate)
            field = new AcctBatch_TrxDate(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kComments)
            field = new StringField(this, "Comments", 30, null, null);
        if (iFieldSeq == kSource)
            field = new StringField(this, "Source", 10, null, null);
        if (iFieldSeq == kBalance)
            field = new CurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNextSequence)
            field = new ShortField(this, "NextSequence", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kAutoReversal)
        {
            field = new BooleanField(this, "AutoReversal", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kAutoReversalDate)
        {
            field = new DateField(this, "AutoReversalDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kAutoClosing)
            field = new BooleanField(this, "AutoClosing", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxEntry)
        {
            field = new AcctBatch_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAcctBatchLastField)
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
        if (iKeyArea == kUserIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "UserID");
            keyArea.addKeyField(kUserID, DBConstants.ASCENDING);
            keyArea.addKeyField(kRecurring, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAcctBatchLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAcctBatchLastKey)
                keyArea = new EmptyKey(this);
        }
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
        if ((this.getField(AcctBatch.kNextSequence).getValue() > 1)
            || (this.getField(AcctBatch.kBalance).getValue() != 0))
        {
            String strError = "Must auto-reverse a new, empty batch";
            strError = ((BaseApplication)task.getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strError);
            task.setLastError(strError);
            return false;
        }
        
        Period recPeriod = new Period(Utility.getRecordOwner(this));
        
        AcctBatchDetail recAcctBatchDetail = new AcctBatchDetail(Utility.getRecordOwner(this));
        recAcctBatchDetail.addListener(new SubFileFilter(this));
        
        Account recAccount = new Account(Utility.getRecordOwner(this));
        AcctDetail recAccountDetail = new AcctDetail(Utility.getRecordOwner(this));
        recAccountDetail.addListener(new SubFileFilter(recAccount));
        DateField fldPeriodStartDate = new DateField(null, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        DateField fldPeriodEndDate = new DateField(null, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        Date dateEnd = recPeriod.getPeriodEndDate(((DateTimeField)this.getField(AcctBatch.kTrxDate)).getDateTime());
        Date dateStart = recPeriod.getPeriodStartDate(((DateTimeField)this.getField(AcctBatch.kTrxDate)).getDateTime());
        fldPeriodStartDate.setDate(dateStart, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        fldPeriodEndDate.setDate(dateEnd, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        recAccountDetail.addListener(new ExtractRangeFilter(AcctDetail.kTrxDate, fldPeriodStartDate, fldPeriodEndDate, ExtractRangeFilter.PAD_DEFAULT));
        
        try {
            double dBalance = 0.00;
            while (recAccount.hasNext())
            {
                recAccount.next();
                if (recAccount.getField(Account.kCloseYearEnd).getState() == false)
                    continue;   // Don't close this one.
                double dAmount = 0.00;
                recAccountDetail.close();
                while (recAccountDetail.hasNext())
                {
                    recAccountDetail.next();
                    dAmount = dAmount + recAccountDetail.getField(AcctDetail.kAmountLocal).getValue();
                }
        
                if (dAmount == 0.00)
                    continue;   // No need to close a zero amount
                dBalance = dBalance + dAmount;
                recAcctBatchDetail.addNew();
        
                recAcctBatchDetail.getField(AcctBatchDetail.kSequence).setValue(1);
                recAcctBatchDetail.getField(AcctBatchDetail.kAccountID).moveFieldToThis(recAccount.getField(Account.kID));
                recAcctBatchDetail.getField(AcctBatchDetail.kAmount).setValue(-dAmount);
        
                recAcctBatchDetail.add();
            }
        
            Object bookmark = this.getHandle(DBConstants.BOOKMARK_HANDLE);
            this.edit();
            this.getField(AcctBatch.kAutoClosing).setState(true);
            this.getField(AcctBatch.kTrxDate).moveFieldToThis(fldPeriodEndDate);
            this.getField(AcctBatch.kBalance).setValue(dBalance);
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
