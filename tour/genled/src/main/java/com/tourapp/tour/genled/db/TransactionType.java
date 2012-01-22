/**
 * @(#)TransactionType.
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
import com.tourapp.tour.genled.screen.trx.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  TransactionType - Transaction type.
 */
public class TransactionType extends VirtualRecord
     implements TransactionTypeModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kTypeCode = kVirtualRecordLastField + 1;
    public static final int kTypeDesc = kTypeCode + 1;
    public static final int kTrxGroupID = kTypeDesc + 1;
    public static final int kGroupCode = kTrxGroupID + 1;
    public static final int kGroupDesc = kGroupCode + 1;
    public static final int kTrxDescID = kGroupDesc + 1;
    public static final int kDescCode = kTrxDescID + 1;
    public static final int kDescription = kDescCode + 1;
    public static final int kTrxSystemID = kDescription + 1;
    public static final int kSystemCode = kTrxSystemID + 1;
    public static final int kSystemDesc = kSystemCode + 1;
    public static final int kTypicalBalance = kSystemDesc + 1;
    public static final int kPostingType = kTypicalBalance + 1;
    public static final int kSourceFile = kPostingType + 1;
    public static final int kSourceTrxDescID = kSourceFile + 1;
    public static final int kSourceTrxStatusID = kSourceTrxDescID + 1;
    public static final int kSourcePreferredSign = kSourceTrxStatusID + 1;
    public static final int kAmountField = kSourcePreferredSign + 1;
    public static final int kTrxDateField = kAmountField + 1;
    public static final int kEntryDateField = kTrxDateField + 1;
    public static final int kUserIDField = kEntryDateField + 1;
    public static final int kTrxIDField = kUserIDField + 1;
    public static final int kAccountIDFile = kTrxIDField + 1;
    public static final int kAccountIDField = kAccountIDFile + 1;
    public static final int kTransactionTypeLastField = kAccountIDField;
    public static final int kTransactionTypeFields = kAccountIDField - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxGroupIDKey = kIDKey + 1;
    public static final int kTrxTypeCodeKey = kTrxGroupIDKey + 1;
    public static final int kSourceTrxStatusIDKey = kTrxTypeCodeKey + 1;
    public static final int kTransactionTypeLastKey = kSourceTrxStatusIDKey;
    public static final int kTransactionTypeKeys = kSourceTrxStatusIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kTransactionTypeFile = "TransactionType";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTransactionTypeFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
            screen = new TrxTypeScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new TrxTypeGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kTypeCode)
            field = new StringField(this, "TypeCode", 20, null, null);
        if (iFieldSeq == kTypeDesc)
            field = new StringField(this, "TypeDesc", 30, null, null);
        if (iFieldSeq == kTrxGroupID)
        {
            field = new TrxGroupField(this, "TrxGroupID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kGroupCode)
            field = new StringField(this, "GroupCode", 20, null, null);
        if (iFieldSeq == kGroupDesc)
            field = new StringField(this, "GroupDesc", 30, null, null);
        if (iFieldSeq == kTrxDescID)
            field = new TrxDescField(this, "TrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDescCode)
            field = new StringField(this, "DescCode", 20, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kTrxSystemID)
            field = new TrxSystemField(this, "TrxSystemID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSystemCode)
            field = new StringField(this, "SystemCode", 20, null, null);
        if (iFieldSeq == kSystemDesc)
            field = new StringField(this, "SystemDesc", 30, null, null);
        if (iFieldSeq == kTypicalBalance)
            field = new PreferredBalanceField(this, "TypicalBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPostingType)
            field = new PostingType(this, "PostingType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSourceFile)
            field = new StringField(this, "SourceFile", 50, null, null);
        if (iFieldSeq == kSourceTrxDescID)
            field = new TrxDescField(this, "SourceTrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSourceTrxStatusID)
            field = new TrxStatusField(this, "SourceTrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSourcePreferredSign)
            field = new PreferredSignField(this, "SourcePreferredSign", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountField)
            field = new StringField(this, "AmountField", 50, null, null);
        if (iFieldSeq == kTrxDateField)
            field = new StringField(this, "TrxDateField", 50, null, null);
        if (iFieldSeq == kEntryDateField)
            field = new StringField(this, "EntryDateField", 50, null, null);
        if (iFieldSeq == kUserIDField)
            field = new StringField(this, "UserIDField", 50, null, null);
        if (iFieldSeq == kTrxIDField)
            field = new StringField(this, "TrxIDField", 50, null, null);
        if (iFieldSeq == kAccountIDFile)
            field = new StringField(this, "AccountIDFile", 50, null, null);
        if (iFieldSeq == kAccountIDField)
            field = new StringField(this, "AccountIDField", 50, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTransactionTypeLastField)
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
        if (iKeyArea == kTrxGroupIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxGroupID");
            keyArea.addKeyField(kTrxGroupID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTypeDesc, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxTypeCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxTypeCode");
            keyArea.addKeyField(kSystemCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kGroupCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kTypeCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kSourceTrxStatusIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "SourceTrxStatusID");
            keyArea.addKeyField(kSourceTrxStatusID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDescID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTransactionTypeLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTransactionTypeLastKey)
                keyArea = new EmptyKey(this);
        }
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
            this.setKeyArea(TransactionType.kTrxGroupIDKey);
            StringSubFileFilter listener = (StringSubFileFilter)this.getListener(StringSubFileFilter.class.getName());
            if (listener == null)
                this.addListener(listener = new StringSubFileFilter(Integer.toString(iTrxGroupID), TransactionType.kTrxGroupID, null, -1, null, -1));
            listener.setFirst(Integer.toString(iTrxGroupID));
            m_htTrxStatus = new HashMap<String,Object>();
            try {
                this.close();
                while (this.hasNext())
                {
                    this.next();
                    this.cacheTransactionType(DBConstants.BLANK, this); // This will set the default to the first one
                    this.cacheTransactionType(this.getField(TransactionType.kTypeCode).toString(), this);
                    this.cacheTransactionType(this.getField(TransactionType.kPostingType).toString(), this);
                    this.cacheTransactionType(this.getField(TransactionType.kTypicalBalance).toString(), this);
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
        this.setKeyArea(TransactionType.kTrxTypeCodeKey);
        this.getField(TransactionType.kSystemCode).setString(strSystemCode);
        this.getField(TransactionType.kDescCode).setString(strDescCode);
        this.getField(TransactionType.kGroupCode).setString(strGroupCode);
        this.getField(TransactionType.kTypeCode).setString(strTypeCode);
        try   {
            boolean bSuccess = this.seek("=");
            if (bSuccess)
                iTrxTypeID = (int)this.getField(TransactionType.kID).getValue();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return iTrxTypeID;
    }

}
