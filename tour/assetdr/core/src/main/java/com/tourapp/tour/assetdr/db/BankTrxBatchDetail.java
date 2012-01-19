/**
 * @(#)BankTrxBatchDetail.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.db;

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
import com.tourapp.tour.assetdr.screen.batch.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.assetdr.db.*;

/**
 *  BankTrxBatchDetail - .
 */
public class BankTrxBatchDetail extends BankTrx
     implements BankTrxBatchDetailModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxStatusID = kTrxStatusID;
    //public static final int kTrxDate = kTrxDate;
    public static final int kUserID = kTrxUserID;
    public static final int kBankTrxBatchID = kBankTrxLastField + 1;
    public static final int kDistributionDisplay = kBankTrxBatchID + 1;
    public static final int kBankTrxBatchDetailLastField = kDistributionDisplay;
    public static final int kBankTrxBatchDetailFields = kDistributionDisplay - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxDateKey = kIDKey + 1;
    public static final int kTrxStatusKey = kTrxDateKey + 1;
    public static final int kInvBalanceKey = kTrxStatusKey + 1;
    public static final int kBankTrxBatchIDKey = kInvBalanceKey + 1;
    public static final int kBankTrxBatchDetailLastKey = kBankTrxBatchIDKey;
    public static final int kBankTrxBatchDetailKeys = kBankTrxBatchIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int BANK_TRX_BATCH_DIST_GRID_SCREEN = ScreenConstants.DETAIL_MODE;
    public static final int BANK_TRX_BATCH_POST = ScreenConstants.POST_MODE;
    /**
     * Default constructor.
     */
    public BankTrxBatchDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankTrxBatchDetail(RecordOwner screen)
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

    public static final String kBankTrxBatchDetailFile = "BankTrxBatchDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBankTrxBatchDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Bank Transaction";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "assetdr";
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = new BankTrxBatchDistGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.POST_MODE)
            screen = new BankTrxBatchPost(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new BankTrxBatchDetailScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new BankTrxBatchDetailGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kTrxStatusID)
        {
            field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTrxDate)
        {
            field = new BankTrxBatchDetail_TrxDate(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kUserID)
            field = new BankTrxBatchDetail_UserID(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBankTrxBatchID)
        {
            field = new BankTrxBatchField(this, "BankTrxBatchID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kDistributionDisplay)
            field = new AccountField(this, "DistributionDisplay", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBankTrxBatchDetailLastField)
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
        if (iKeyArea == kTrxDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDate");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxStatusKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxStatus");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxStatusID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kInvBalanceKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "InvBalance");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kInvSign, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kInvBalance, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBankTrxBatchIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BankTrxBatchID");
            keyArea.addKeyField(kBankTrxBatchID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBankTrxBatchDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBankTrxBatchDetailLastKey)
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
        ((TrxStatusField)this.getField(BankTrxBatchDetail.kTrxStatusID)).setDesc(BankTrx.kBankTrxFile);
        this.removeListener(this.getListener(VoidOnDeleteHandler.class.getName()), true); // Not used for batches.
        this.addListener(new SubFileIntegrityHandler(BankTrxBatchDist.class.getName(), true));
    }

}
