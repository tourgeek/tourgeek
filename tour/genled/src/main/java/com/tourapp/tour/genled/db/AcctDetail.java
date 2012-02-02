/**
 * @(#)AcctDetail.
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
import com.tourapp.tour.genled.screen.detail.*;
import java.util.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  AcctDetail - Account Transaction Detail.
 */
public class AcctDetail extends BaseTrx
     implements AcctDetailModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxDate = kTrxDate;
    public static final int kTrxTypeID = kTrxStatusID;
    //public static final int kTrxEntry = kTrxEntry;
    //public static final int kAmountLocal = kAmountLocal;
    //public static final int kTrxUserID = kTrxUserID;
    public static final int kAccountID = kBaseTrxLastField + 1;
    public static final int kSource = kAccountID + 1;
    public static final int kComments = kSource + 1;
    public static final int kVersionID = kComments + 1;
    public static final int kAcctDetailLastField = kVersionID;
    public static final int kAcctDetailFields = kVersionID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kAccountIDKey = kIDKey + 1;
    public static final int kTrxDateKey = kAccountIDKey + 1;
    public static final int kSourceKey = kTrxDateKey + 1;
    public static final int kAcctDetailLastKey = kSourceKey;
    public static final int kAcctDetailKeys = kSourceKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int ACCT_DIST_GRID_SCREEN = ScreenConstants.DETAIL_MODE;
    /**
     * Default constructor.
     */
    public AcctDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctDetail(RecordOwner screen)
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

    public static final String kAcctDetailFile = "AcctDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAcctDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "G/L transactions";
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
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == AcctDetail.ACCT_DIST_GRID_SCREEN)
            screen = new AcctDetailDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new AcctDetailScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new AcctDetailGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
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
        if (iFieldSeq == kAccountID)
        {
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kTrxDate)
            field = new AcctDetail_TrxDate(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxTypeID)
            field = new TrxTypeField(this, "TrxTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxEntry)
            field = new AcctDetail_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountLocal)
            field = new DrCrField(this, "AmountLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxUserID)
        //  field = new AcctDetail_TrxUserID(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSource)
        {
            field = new StringField(this, "Source", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kComments)
        {
            field = new StringField(this, "Comments", 30, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVersionID)
            field = new VersionField(this, "VersionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAcctDetailLastField)
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
        if (iKeyArea == kAccountIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AccountID");
            keyArea.addKeyField(kAccountID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxTypeID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxEntry, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDate");
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxEntry, DBConstants.ASCENDING);
        }
        if (iKeyArea == kSourceKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Source");
            keyArea.addKeyField(kSource, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxEntry, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAcctDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAcctDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        // Make sure amount is added on merge
        this.getField(AcctDetail.AMOUNT_LOCAL).addListener(new MergeDataAddHandler(null));
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.addListener(new NoDeleteModifyHandler(null));
    }
    /**
     * This is a special form of BaseTrx posting... used for manual G/L posting and for
     * Distribution where a history file is not referenced
     * DO NOT call inherited!.
     */
    public boolean onPostManualDist(AcctDetailDist recAcctDetailDist)
    {
        // This is a special form of BaseTrx posting... used for manual G/L posting and for
        // Distribution where a history file is not referenced
        // DO NOT call inherited!
        try   {
            recAcctDetailDist.addNew();
            recAcctDetailDist.getField(AcctDetailDist.ACCT_DETAIL_ID).moveFieldToThis(this.getField(AcctDetail.ID));
            recAcctDetailDist.getField(AcctDetailDist.TRX_ID).moveFieldToThis(this.getField(AcctDetail.ID));  // No audit trail needed
            recAcctDetailDist.getField(AcctDetailDist.TRX_DESC_ID).moveFieldToThis(((ReferenceField)this.getField(AcctDetail.TRX_TYPE_ID)).getReference().getField(TransactionType.SOURCE_TRX_DESC_ID));
            recAcctDetailDist.getField(AcctDetailDist.TRX_DATE).moveFieldToThis(this.getField(AcctDetail.TRX_DATE));
            recAcctDetailDist.getField(AcctDetailDist.AMOUNT).moveFieldToThis(this.getField(AcctDetail.AMOUNT_LOCAL));
            recAcctDetailDist.getField(AcctDetailDist.TRX_ENTRY).moveFieldToThis(this.getField(AcctDetail.TRX_ENTRY));
            recAcctDetailDist.getField(AcctDetailDist.USER_ID).setValue(((UserField)recAcctDetailDist.getField(AcctDetailDist.USER_ID)).getUserID());
            recAcctDetailDist.add();
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Update or Write the account detail for this trx.
     */
    public void updateAcctDetail(BaseField accountID, Date dateTrx, TransactionType recTrxType, Date dateTrxEntry, double amount)
    {
        int iOpenMode = this.getOpenMode();
        try   {
            this.setKeyArea(AcctDetail.ACCOUNT_ID_KEY);
            this.getField(AcctDetail.ACCOUNT_ID).moveFieldToThis(accountID);
            ((DateTimeField)this.getField(AcctDetail.TRX_DATE)).setDateTime(dateTrx, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            this.getField(AcctDetail.TRX_TYPE_ID).moveFieldToThis(recTrxType.getField(TransactionType.ID));
            ((DateTimeField)this.getField(AcctDetail.TRX_ENTRY)).setDateTime(dateTrxEntry, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
            boolean bSuccess = this.seek("=");
            this.setOpenMode(iOpenMode & ~DBConstants.OPEN_READ_ONLY);  //Often this record comes from a display which is read only
            if (!bSuccess)
            {
                this.addNew();
                this.getField(AcctDetail.ACCOUNT_ID).moveFieldToThis(accountID);
                ((DateField)this.getField(AcctDetail.TRX_DATE)).setDateTime(dateTrx, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                ((DateField)this.getField(AcctDetail.TRX_ENTRY)).setDateTime(dateTrxEntry, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                this.getField(AcctDetail.TRX_TYPE_ID).moveFieldToThis(recTrxType.getField(TransactionType.ID));
                this.getField(AcctDetail.AMOUNT_LOCAL).setValue(amount);
                this.getField(AcctDetail.SOURCE).moveFieldToThis(recTrxType.getField(TransactionType.SYSTEM_CODE));
                if (this.getField(AcctDetail.SOURCE).isNull())
                    this.getField(AcctDetail.SOURCE).moveFieldToThis(recTrxType.getField(TransactionType.SYSTEM_DESC));
                this.getField(AcctDetail.COMMENTS).moveFieldToThis(recTrxType.getField(TransactionType.GROUP_DESC));
                this.add();
                Object bookmark = this.getLastModified(DBConstants.BOOKMARK_HANDLE);
                this.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
            }
            else
            {
                this.edit();
            // Add code to re-try if locked+++
                double dNewAmount = this.getField(AcctDetail.AMOUNT_LOCAL).getValue();
                dNewAmount = dNewAmount + amount;
                this.getField(AcctDetail.AMOUNT_LOCAL).setValue(dNewAmount);
                this.set();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            this.setOpenMode(iOpenMode);
        }
    }

}
