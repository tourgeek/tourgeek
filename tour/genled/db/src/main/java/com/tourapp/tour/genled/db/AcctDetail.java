/**
  * @(#)AcctDetail.
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
import com.tourapp.model.tour.genled.db.*;

/**
 *  AcctDetail - Account Transaction Detail.
 */
public class AcctDetail extends BaseTrx
     implements AcctDetailModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(ACCT_DETAIL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
            screen = Record.makeNewScreen(ACCT_DETAIL_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ACCT_DETAIL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(ACCT_DETAIL_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
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
            field = new TrxTypeField(this, TRX_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new AcctDetail_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new AcctDetail_TrxDate(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new DrCrField(this, AMOUNT_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new AcctDetail_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
        {
            field = new AccountField(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 9)
        {
            field = new StringField(this, SOURCE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 10)
        {
            field = new StringField(this, COMMENTS, 30, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 11)
            field = new VersionField(this, VERSION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AccountID");
            keyArea.addKeyField(ACCOUNT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_TYPE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_ENTRY, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDate");
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_ENTRY, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Source");
            keyArea.addKeyField(SOURCE, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_ENTRY, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
