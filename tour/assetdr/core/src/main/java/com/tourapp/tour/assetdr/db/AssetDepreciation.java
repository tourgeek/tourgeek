/**
 * @(#)AssetDepreciation.
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
 *  AssetDepreciation - Asset Depreciation Detail.
 */
public class AssetDepreciation extends VirtualRecord
     implements AssetDepreciationModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAssetID = kVirtualRecordLastField + 1;
    public static final int kDeprDate = kAssetID + 1;
    public static final int kDeprPost = kDeprDate + 1;
    public static final int kVersionID = kDeprPost + 1;
    public static final int kDeprAmount = kVersionID + 1;
    public static final int kDeprDrID = kDeprAmount + 1;
    public static final int kDeprCrID = kDeprDrID + 1;
    public static final int kAssetDepreciationLastField = kDeprCrID;
    public static final int kAssetDepreciationFields = kDeprCrID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kAssetIDKey = kIDKey + 1;
    public static final int kAssetDepreciationLastKey = kAssetIDKey;
    public static final int kAssetDepreciationKeys = kAssetIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public AssetDepreciation()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AssetDepreciation(RecordOwner screen)
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

    public static final String kAssetDepreciationFile = "AssetDepreciation";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAssetDepreciationFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Depreciation history";
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(ASSET_DEPRECIATION_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(ASSET_DEPRECIATION_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kAssetID)
        {
            field = new AssetField(this, "AssetID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kDeprDate)
        {
            field = new DateField(this, "DeprDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDeprPost)
        {
            field = new DateField(this, "DeprPost", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVersionID)
        {
            field = new VersionField(this, "VersionID", 1, null, "X");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDeprAmount)
            field = new CurrencyField(this, "DeprAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDeprDrID)
            field = new AccountField(this, "DeprDrID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDeprCrID)
            field = new AccountField(this, "DeprCrID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAssetDepreciationLastField)
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kAssetIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AssetID");
            keyArea.addKeyField(kAssetID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDeprDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kVersionID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAssetDepreciationLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAssetDepreciationLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
