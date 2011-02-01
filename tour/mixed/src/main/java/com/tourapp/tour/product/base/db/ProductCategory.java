/**
 *  @(#)ProductCategory.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.genled.db.*;

/**
 *  ProductCategory - Tour Product Category.
 */
public class ProductCategory extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kIncomeAccountID = kDescription + 1;
    public static final int kArAccountID = kIncomeAccountID + 1;
    public static final int kPPAccountID = kArAccountID + 1;
    public static final int kXLChgAccountID = kPPAccountID + 1;
    public static final int kLandAccountID = kXLChgAccountID + 1;
    public static final int kUninvAccountID = kLandAccountID + 1;
    public static final int kCostOUAccountID = kUninvAccountID + 1;
    public static final int kApAccountID = kCostOUAccountID + 1;
    public static final int kCurrOUAccountID = kApAccountID + 1;
    public static final int kAirAccountID = kCurrOUAccountID + 1;
    public static final int kPPTicAccountID = kAirAccountID + 1;
    public static final int kProductCategoryLastField = kPPTicAccountID;
    public static final int kProductCategoryFields = kPPTicAccountID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kProductCategoryLastKey = kDescriptionKey;
    public static final int kProductCategoryKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProductCategory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductCategory(RecordOwner screen)
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

    public static final String kProductCategoryFile = "ProductCategory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductCategoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product Category";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kIncomeAccountID)
            field = new AccountField(this, "IncomeAccountID", 7, null, null);
        if (iFieldSeq == kArAccountID)
            field = new AccountField(this, "ArAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPPAccountID)
            field = new AccountField(this, "PPAccountID", 7, null, null);
        if (iFieldSeq == kXLChgAccountID)
            field = new AccountField(this, "XLChgAccountID", 7, null, null);
        if (iFieldSeq == kLandAccountID)
            field = new AccountField(this, "LandAccountID", 7, null, null);
        if (iFieldSeq == kUninvAccountID)
            field = new AccountField(this, "UninvAccountID", 7, null, null);
        if (iFieldSeq == kCostOUAccountID)
            field = new AccountField(this, "CostOUAccountID", 7, null, null);
        if (iFieldSeq == kApAccountID)
            field = new AccountField(this, "ApAccountID", 7, null, null);
        if (iFieldSeq == kCurrOUAccountID)
            field = new AccountField(this, "CurrOUAccountID", 7, null, null);
        if (iFieldSeq == kAirAccountID)
            field = new AccountField(this, "AirAccountID", 7, null, null);
        if (iFieldSeq == kPPTicAccountID)
            field = new AccountField(this, "PPTicAccountID", 7, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductCategoryLastField)
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
        if (keyArea == null) if (iKeyArea < kProductCategoryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProductCategoryLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
