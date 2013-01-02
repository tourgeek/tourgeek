/**
  * @(#)TransactionType.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.db;

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
 *  TransactionType - Transaction type.
 */
public class TransactionType extends VirtualRecord
     implements TransactionTypeModel
{
    private static final long serialVersionUID = 1L;

    protected Map<String,Object> m_htTrxStatus;
    protected int m_iCurrentTrxStatus = -1;
    /**
     * Default constructor.
     */
    public TransactionType()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TransactionType(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_htTrxStatus = null;
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TRANSACTION_TYPE_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transaction desc";
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
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TRANSACTION_TYPE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(TRANSACTION_TYPE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new StringField(this, TYPE_CODE, 20, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, TYPE_DESC, 30, null, null);
        if (iFieldSeq == 5)
        {
            field = new TrxGroupField(this, TRX_GROUP_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 6)
            field = new StringField(this, GROUP_CODE, 20, null, null);
        if (iFieldSeq == 7)
            field = new StringField(this, GROUP_DESC, 30, null, null);
        if (iFieldSeq == 8)
            field = new TrxDescField(this, TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new StringField(this, DESC_CODE, 20, null, null);
        if (iFieldSeq == 10)
            field = new StringField(this, DESCRIPTION, 30, null, null);
        if (iFieldSeq == 11)
            field = new TrxSystemField(this, TRX_SYSTEM_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new StringField(this, SYSTEM_CODE, 20, null, null);
        if (iFieldSeq == 13)
            field = new StringField(this, SYSTEM_DESC, 30, null, null);
        if (iFieldSeq == 14)
            field = new PreferredBalanceField(this, TYPICAL_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new PostingType(this, POSTING_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new StringField(this, SOURCE_FILE, 50, null, null);
        if (iFieldSeq == 17)
            field = new TrxDescField(this, SOURCE_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new TrxStatusField(this, SOURCE_TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new PreferredSignField(this, SOURCE_PREFERRED_SIGN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new StringField(this, AMOUNT_FIELD, 50, null, null);
        if (iFieldSeq == 21)
            field = new StringField(this, TRX_DATE_FIELD, 50, null, null);
        if (iFieldSeq == 22)
            field = new StringField(this, ENTRY_DATE_FIELD, 50, null, null);
        if (iFieldSeq == 23)
            field = new StringField(this, USER_ID_FIELD, 50, null, null);
        if (iFieldSeq == 24)
            field = new StringField(this, TRX_ID_FIELD, 50, null, null);
        if (iFieldSeq == 25)
            field = new StringField(this, ACCOUNT_ID_FILE, 50, null, null);
        if (iFieldSeq == 26)
            field = new StringField(this, ACCOUNT_ID_FIELD, 50, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TRX_GROUP_ID_KEY);
            keyArea.addKeyField(TRX_GROUP_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TYPE_DESC, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TRX_TYPE_CODE_KEY);
            keyArea.addKeyField(SYSTEM_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(DESC_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(GROUP_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(TYPE_CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, SOURCE_TRX_STATUS_ID_KEY);
            keyArea.addKeyField(SOURCE_TRX_STATUS_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DESC_ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Get the transaction type.
     * @param iTrxGroupID The transaction group.
     * @param strPostingType The posting type.
     * @return The transaction type record.
     */
    public TransactionType getTrxType(int iTrxGroupID, String strPostingType)
    {
        if (iTrxGroupID == -1)
        {
            try {
                this.addNew();
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
            return this;    // None
        }
        if (m_iCurrentTrxStatus != iTrxGroupID)
        {   // Not cached yet
            this.setKeyArea(TransactionType.TRX_GROUP_ID_KEY);
            StringSubFileFilter listener = (StringSubFileFilter)this.getListener(StringSubFileFilter.class.getName());
            if (listener == null)
                this.addListener(listener = new StringSubFileFilter(Integer.toString(iTrxGroupID), TransactionType.TRX_GROUP_ID, null, null, null, null));
            listener.setFirst(Integer.toString(iTrxGroupID));
            m_htTrxStatus = new HashMap<String,Object>();
            try {
                this.close();
                while (this.hasNext())
                {
                    this.next();
                    this.cacheTransactionType(DBConstants.BLANK, this); // This will set the default to the first one
                    this.cacheTransactionType(this.getField(TransactionType.TYPE_CODE).toString(), this);
                    this.cacheTransactionType(this.getField(TransactionType.POSTING_TYPE).toString(), this);
                    this.cacheTransactionType(this.getField(TransactionType.TYPICAL_BALANCE).toString(), this);
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
            m_iCurrentTrxStatus = iTrxGroupID;
        }
        Object objBookmark = m_htTrxStatus.get(strPostingType);
        if (objBookmark == null)
            objBookmark = m_htTrxStatus.get(DBConstants.BLANK); // Default
        try {
            this.setHandle(objBookmark, DBConstants.BOOKMARK_HANDLE);
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return this;
    }
    /**
     * CacheTransactionType Method.
     */
    public void cacheTransactionType(String strKey, TransactionType recTransactionType)
    {
        try {
            if (m_htTrxStatus.get(strKey) == null)
                m_htTrxStatus.put(strKey, recTransactionType.getHandle(DBConstants.BOOKMARK_HANDLE));
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * Lookup this transaction type given the System, Class, and Desc.
     */
    public int getTrxTypeID(String strSystemCode, String strDescCode, String strGroupCode, String strTypeCode)
    {
        int iTrxTypeID = 0;
        this.setKeyArea(TransactionType.TRX_TYPE_CODE_KEY);
        this.getField(TransactionType.SYSTEM_CODE).setString(strSystemCode);
        this.getField(TransactionType.DESC_CODE).setString(strDescCode);
        this.getField(TransactionType.GROUP_CODE).setString(strGroupCode);
        this.getField(TransactionType.TYPE_CODE).setString(strTypeCode);
        try   {
            boolean bSuccess = this.seek("=");
            if (bSuccess)
                iTrxTypeID = (int)this.getField(TransactionType.ID).getValue();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return iTrxTypeID;
    }

}
