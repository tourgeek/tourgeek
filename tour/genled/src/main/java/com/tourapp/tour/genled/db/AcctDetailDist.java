/**
 * @(#)AcctDetailDist.
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
import java.util.*;
import com.tourapp.tour.genled.screen.detail.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  AcctDetailDist - Account Transaction Distribution.
 */
public class AcctDetailDist extends VirtualRecord
     implements AcctDetailDistModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(ACCT_DETAIL_DIST_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.SCREEN_TYPE_MASK) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ACCT_DETAIL_DIST_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.SCREEN_TYPE_MASK) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(ACCT_DETAIL_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.SCREEN_TYPE_MASK) == DIST_GROUP_SCREEN)
            screen = Record.makeNewScreen(ACCT_DETAIL_DIST_GROUP_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        {
            field = new AcctDetailField(this, ACCT_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 4)
            field = new TrxIDField(this, TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new TrxDescField(this, TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new TrxTypeField(this, TRX_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new DateTimeField(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new DrCrField(this, AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new DateTimeField(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new AcctDetailDist_UserID(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new ReferenceField(this, ACCT_DETAIL_DIST_GROUP_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DistDetail");
            keyArea.addKeyField(ACCT_DETAIL_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AcctDetailDistGroupID");
            keyArea.addKeyField(ACCT_DETAIL_DIST_GROUP_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDescID");
            keyArea.addKeyField(TRX_DESC_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_TYPE_ID, DBConstants.ASCENDING);
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
        this.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).addListener(new InitOnceFieldHandler(null));
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
            recPeriod = new Period(this.findRecordOwner());
            bFreePeriod = true;
        }
        boolean bFreeAcctDetail = false;
        if (recAcctDetail == null)
        {
            recAcctDetail = new AcctDetail(this.findRecordOwner());
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
        
            this.getField(AcctDetailDist.ACCT_DETAIL_ID).moveFieldToThis(recAcctDetail.getField(AcctDetail.ID));
            if (fldTrxID != null)
                this.getField(AcctDetailDist.TRX_ID).moveFieldToThis(fldTrxID);
            this.getField(AcctDetailDist.TRX_DESC_ID).moveFieldToThis(recTrxType.getField(TransactionType.SOURCE_TRX_DESC_ID));
            this.getField(AcctDetailDist.TRX_TYPE_ID).moveFieldToThis(recTrxType.getField(TransactionType.ID));
        
            ((DateTimeField)this.getField(AcctDetailDist.TRX_DATE)).setDateTime(dateTrx, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
            this.getField(AcctDetailDist.AMOUNT).setValue(dAmount);
            ((DateTimeField)this.getField(AcctDetailDist.TRX_ENTRY)).setDateTime(dateEntry, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            this.getField(AcctDetailDist.USER_ID).setValue(iUserID);
            if (this.getField(AcctDetailDist.USER_ID).getValue() <= 0)
                this.getField(AcctDetailDist.USER_ID).initField(DBConstants.DISPLAY);
            if (this.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).isNull())
                this.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).setValue(0);    // Can't be null (key)
            this.add();
        
            if (this.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).getValue() == 0)
            {   // First transaction in a group... group = this trx number
                Object objectID = this.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
                this.setHandle(objectID, DBConstants.DATA_SOURCE_HANDLE);
                this.edit();
                this.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).moveFieldToThis(this.getField(AcctDetailDist.ID));
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
        this.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).setValue(0);
        //+ this.startTrx();
    }
    /**
     * EndDistTrx Method.
     */
    public void endDistTrx()
    {
        this.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).setValue(0);
        //+ this.commitTrx();
    }

}
