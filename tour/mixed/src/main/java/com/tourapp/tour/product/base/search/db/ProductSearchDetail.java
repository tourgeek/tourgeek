/**
 *  @(#)ProductSearchDetail.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.search.db;

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
import com.tourapp.tour.product.base.search.screen.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  ProductSearchDetail - .
 */
public class ProductSearchDetail extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kProductID = kVirtualRecordLastField + 1;
    public static final int kProductTypeID = kProductID + 1;
    public static final int kProductSearchTypeID = kProductTypeID + 1;
    public static final int kProductSearchCategoryID = kProductSearchTypeID + 1;
    public static final int kSearchData = kProductSearchCategoryID + 1;
    public static final int kProductSearchDetailLastField = kSearchData;
    public static final int kProductSearchDetailFields = kSearchData - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProductIDKey = kIDKey + 1;
    public static final int kProductSearchCategoryIDKey = kProductIDKey + 1;
    public static final int kProductSearchDetailLastKey = kProductSearchCategoryIDKey;
    public static final int kProductSearchDetailKeys = kProductSearchCategoryIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProductSearchDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductSearchDetail(RecordOwner screen)
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

    public static final String kProductSearchDetailFile = "ProductSearchDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductSearchDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new ProductSearchDetailScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new ProductSearchDetailGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kProductID)
        {
            field = new ProductField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kProductTypeID)
            field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductSearchTypeID)
        {
            field = new ProductSearchTypeField(this, "ProductSearchTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kProductSearchCategoryID)
            field = new ProductSearchCategoryField(this, "ProductSearchCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSearchData)
            field = new StringField(this, "SearchData", 60, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductSearchDetailLastField)
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
        if (iKeyArea == kProductIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductID");
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductTypeID, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductSearchTypeID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kProductSearchCategoryIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductSearchCategoryID");
            keyArea.addKeyField(kProductSearchCategoryID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kProductSearchDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProductSearchDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getField(ProductSearchDetail.kProductID).addListener(new SetProductTypeHandler(this.getField(ProductSearchDetail.kProductTypeID)));
    }

}
