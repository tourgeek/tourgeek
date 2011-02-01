/**
 *  @(#)AcctDetailDist.
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
import java.util.*;
import com.tourapp.tour.genled.screen.detail.*;

/**
 *  AcctDetailDist - Account Transaction Distribution.
 */
public class AcctDetailDist extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAcctDetailID = kVirtualRecordLastField + 1;
    public static final int kTrxID = kAcctDetailID + 1;
    public static final int kTrxDescID = kTrxID + 1;
    public static final int kTrxTypeID = kTrxDescID + 1;
    public static final int kTrxDate = kTrxTypeID + 1;
    public static final int kAmount = kTrxDate + 1;
    public static final int kTrxEntry = kAmount + 1;
    public static final int kUserID = kTrxEntry + 1;
    public static final int kAcctDetailDistGroupID = kUserID + 1;
    public static final int kAcctDetailDistLastField = kAcctDetailDistGroupID;
    public static final int kAcctDetailDistFields = kAcctDetailDistGroupID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDistDetailKey = kIDKey + 1;
    public static final int kAcctDetailDistGroupIDKey = kDistDetailKey + 1;
    public static final int kTrxDescIDKey = kAcctDetailDistGroupIDKey + 1;
    public static final int kAcctDetailDistLastKey = kTrxDescIDKey;
    public static final int kAcctDetailDistKeys = kTrxDescIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String DIST_DISTRIBUTION = "Distribution";
    public static final String DIST_GROUP = "Group";
    public static final String DIST_SOURCE = "Source";
    public static final String DIST_TRANSACTION = "Transaction";
    public static final int DIST_GROUP_SCREEN = ScreenConstants.DISPLAY_MODE | 4096;
    /**
     * Default constructor.
     */
    public AcctDetailDist()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctDetailDist(RecordOwner screen)
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

    public static final String kAcctDetailDistFile = "AcctDetailDist";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAcctDetailDistFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Account Detail Distribution";
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.SCREEN_TYPE_MASK) == ScreenConstants.MAINT_MODE)
            screen = new AcctDetailDistScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.SCREEN_TYPE_MASK) == ScreenConstants.DISPLAY_MODE)
            screen = new AcctDetailDistGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.SCREEN_TYPE_MASK) == DIST_GROUP_SCREEN)
            screen = new AcctDetailDistGroupGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kAcctDetailID)
        {
            field = new AcctDetailField(this, "AcctDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kTrxID)
            field = new TrxIDField(this, "TrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxDescID)
            field = new TrxDescField(this, "TrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxTypeID)
            field = new TrxTypeField(this, "TrxTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxDate)
            field = new DateTimeField(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmount)
            field = new DrCrField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxEntry)
            field = new DateTimeField(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kUserID)
            field = new AcctDetailDist_UserID(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAcctDetailDistGroupID)
            field = new ReferenceField(this, "AcctDetailDistGroupID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAcctDetailDistLastField)
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
        if (iKeyArea == kDistDetailKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DistDetail");
            keyArea.addKeyField(kAcctDetailID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kAcctDetailDistGroupIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AcctDetailDistGroupID");
            keyArea.addKeyField(kAcctDetailDistGroupID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxDescIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDescID");
            keyArea.addKeyField(kTrxDescID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxTypeID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAcctDetailDistLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAcctDetailDistLastKey)
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
        this.getField(AcctDetailDist.kAcctDetailDistGroupID).addListener(new InitOnceFieldHandler(null));
        this.addListener(new NoDeleteModifyHandler(null));
    }
    /**
     * Add the detail transaction (and post to AcctDetail).
     */
    public boolean addDetailTrx(BaseField accountID, DateTimeField trxDate, BaseField fldTrxID, TransactionType recTrxType, DateTimeField trxEntryDate, double dAmount, int iUserID, AcctDetail
     recAcctDetail, Period recPeriod)
    {
        // First, get the correct period for this posting
        if (dAmount == 0)
            return true;
        boolean bFreePeriod = false;
        if (recPeriod == null)
        {
            recPeriod = new Period(Utility.getRecordOwner(this));
            bFreePeriod = true;
        }
        boolean bFreeAcctDetail = false;
        if (recAcctDetail == null)
        {
            recAcctDetail = new AcctDetail(Utility.getRecordOwner(this));
            bFreeAcctDetail = true;
        }
        Date dateTrx = null;
        if ((trxDate != null) && (trxDate.getValue() != 0))
            dateTrx = trxDate.getDateTime();
        else
            dateTrx = new Date(); // Today (default)
        Date dateTrxPost = recPeriod.getPeriodEndDate(dateTrx);
        
        Date dateEntry = null;
        if ((trxEntryDate != null) && (trxEntryDate.getValue() != 0))
            dateEntry = trxEntryDate.getDateTime();
        else
            dateEntry = new Date();
        Date dateTrxEntry = recPeriod.getPeriodEndDate(dateEntry);
        
        // Okay, first post the transaction to the AcctDetail
        recAcctDetail.updateAcctDetail(accountID, dateTrxPost, recTrxType, dateTrxEntry, dAmount);
        
        // Now, add the transaction detail (the audit-trail)
        try   {
            this.addNew();
        
            this.getField(AcctDetailDist.kAcctDetailID).moveFieldToThis(recAcctDetail.getField(AcctDetail.kID));
            if (fldTrxID != null)
                this.getField(AcctDetailDist.kTrxID).moveFieldToThis(fldTrxID);
            this.getField(AcctDetailDist.kTrxDescID).moveFieldToThis(recTrxType.getField(TransactionType.kSourceTrxDescID));
            this.getField(AcctDetailDist.kTrxTypeID).moveFieldToThis(recTrxType.getField(TransactionType.kID));
        
            ((DateTimeField)this.getField(AcctDetailDist.kTrxDate)).setDateTime(dateTrx, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
            this.getField(AcctDetailDist.kAmount).setValue(dAmount);
            ((DateTimeField)this.getField(AcctDetailDist.kTrxEntry)).setDateTime(dateEntry, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            this.getField(AcctDetailDist.kUserID).setValue(iUserID);
            if (this.getField(AcctDetailDist.kUserID).getValue() <= 0)
                this.getField(AcctDetailDist.kUserID).initField(DBConstants.DISPLAY);
            if (this.getField(AcctDetailDist.kAcctDetailDistGroupID).isNull())
                this.getField(AcctDetailDist.kAcctDetailDistGroupID).setValue(0);    // Can't be null (key)
            this.add();
        
            if (this.getField(AcctDetailDist.kAcctDetailDistGroupID).getValue() == 0)
            {   // First transaction in a group... group = this trx number
                Object objectID = this.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
                this.setHandle(objectID, DBConstants.DATA_SOURCE_HANDLE);
                this.edit();
                this.getField(AcctDetailDist.kAcctDetailDistGroupID).moveFieldToThis(this.getField(AcctDetailDist.kID));
                this.set();
            }
        
        } catch (DBException ex)    {
            ex.printStackTrace();
            //+ rollback
        }
        if (bFreePeriod)
            recPeriod.free();
        if (bFreeAcctDetail)
            recAcctDetail.free();
        return true;
    }
    /**
     * StartDistTrx Method.
     */
    public void startDistTrx()
    {
        this.getField(AcctDetailDist.kAcctDetailDistGroupID).setValue(0);
        //+ this.startTrx();
    }
    /**
     * EndDistTrx Method.
     */
    public void endDistTrx()
    {
        this.getField(AcctDetailDist.kAcctDetailDistGroupID).setValue(0);
        //+ this.commitTrx();
    }

}
