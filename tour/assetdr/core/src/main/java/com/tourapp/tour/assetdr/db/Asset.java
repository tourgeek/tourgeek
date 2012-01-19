/**
 * @(#)Asset.
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
import com.tourapp.tour.assetdr.screen.asset.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.assetdr.db.*;

/**
 *  Asset - Fixed Assets.
 */
public class Asset extends VirtualRecord
     implements AssetModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kManufacturer = kDescription + 1;
    public static final int kSerialNumber = kManufacturer + 1;
    public static final int kInventoryNo = kSerialNumber + 1;
    public static final int kLocation = kInventoryNo + 1;
    public static final int kPurchaseDate = kLocation + 1;
    public static final int kSaleDate = kPurchaseDate + 1;
    public static final int kFSDeprMethod = kSaleDate + 1;
    public static final int kFSLife = kFSDeprMethod + 1;
    public static final int kFedDeprMethod = kFSLife + 1;
    public static final int kFedLife = kFedDeprMethod + 1;
    public static final int kStateDeprMethod = kFedLife + 1;
    public static final int kStateLife = kStateDeprMethod + 1;
    public static final int kAssetAccountID = kStateLife + 1;
    public static final int kDeprAccountID = kAssetAccountID + 1;
    public static final int kExpenseAccountID = kDeprAccountID + 1;
    public static final int kAssetLastField = kExpenseAccountID;
    public static final int kAssetFields = kExpenseAccountID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kInventoryNoKey = kDescriptionKey + 1;
    public static final int kAssetLastKey = kInventoryNoKey;
    public static final int kAssetKeys = kInventoryNoKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int ASSET_DEPRECIATION_GRID_SCREEN = ScreenConstants.DETAIL_MODE;
    /**
     * Default constructor.
     */
    public Asset()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Asset(RecordOwner screen)
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

    public static final String kAssetFile = "Asset";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAssetFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Asset";
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
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == Asset.ASSET_DEPRECIATION_GRID_SCREEN)
            screen = new AssetDepreciationGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new AssetScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new AssetGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
            field = new CounterField(this, "ID", 10, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 40, null, null);
        if (iFieldSeq == kManufacturer)
        {
            field = new StringField(this, "Manufacturer", 30, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSerialNumber)
            field = new StringField(this, "SerialNumber", 50, null, null);
        if (iFieldSeq == kInventoryNo)
            field = new StringField(this, "InventoryNo", 50, null, null);
        if (iFieldSeq == kLocation)
        {
            field = new StringField(this, "Location", 30, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPurchaseDate)
        {
            field = new Asset_PurchaseDate(this, "PurchaseDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSaleDate)
        {
            field = new DateField(this, "SaleDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFSDeprMethod)
            field = new DeprTypeField(this, "FSDeprMethod", 1, null, null);
        if (iFieldSeq == kFSLife)
            field = new ShortField(this, "FSLife", 2, null, null);
        if (iFieldSeq == kFedDeprMethod)
            field = new DeprTypeField(this, "FedDeprMethod", 1, null, null);
        if (iFieldSeq == kFedLife)
            field = new ShortField(this, "FedLife", 2, null, null);
        if (iFieldSeq == kStateDeprMethod)
            field = new DeprTypeField(this, "StateDeprMethod", 1, null, null);
        if (iFieldSeq == kStateLife)
            field = new ShortField(this, "StateLife", 2, null, null);
        if (iFieldSeq == kAssetAccountID)
            field = new AccountField(this, "AssetAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDeprAccountID)
            field = new AccountField(this, "DeprAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kExpenseAccountID)
            field = new AccountField(this, "ExpenseAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAssetLastField)
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
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (iKeyArea == kInventoryNoKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "InventoryNo");
            keyArea.addKeyField(kInventoryNo, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAssetLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAssetLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
