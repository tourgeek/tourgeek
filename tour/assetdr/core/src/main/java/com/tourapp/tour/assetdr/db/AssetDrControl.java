/**
 * @(#)AssetDrControl.
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
import com.tourapp.tour.assetdr.screen.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.assetdr.db.*;

/**
 *  AssetDrControl - Control File.
 */
public class AssetDrControl extends ControlRecord
     implements AssetDrControlModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kCurrencyID = kControlRecordLastField + 1;
    public static final int kLanguageID = kCurrencyID + 1;
    public static final int kBankAcctID = kLanguageID + 1;
    public static final int kTrxStatusID = kBankAcctID + 1;
    public static final int kAccountID = kTrxStatusID + 1;
    public static final int kDateReconciled = kAccountID + 1;
    public static final int kAssetDrControlLastField = kDateReconciled;
    public static final int kAssetDrControlFields = kDateReconciled - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kAssetDrControlLastKey = kIDKey;
    public static final int kAssetDrControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public AssetDrControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AssetDrControl(RecordOwner screen)
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

    public static final String kAssetDrControlFile = "AssetDrControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAssetDrControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Asset-Debt Control";
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
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ASSET_DR_CONTROL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(ASSET_DR_CONTROL_SCREEN_2_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kCurrencyID)
            field = new CurrencysField(this, "CurrencyID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLanguageID)
            field = new LanguageField(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxStatusID)
            field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAccountID)
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDateReconciled)
            field = new DateTimeField(this, "DateReconciled", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAssetDrControlLastField)
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
        if (keyArea == null) if (iKeyArea < kAssetDrControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAssetDrControlLastKey)
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
        ((TrxStatusField)this.getField(AssetDrControl.kTrxStatusID)).setDesc(BankTrx.kBankTrxFile);
    }

}
