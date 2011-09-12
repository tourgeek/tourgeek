/**
 * @(#)ProductSearchType.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
 *  ProductSearchType - .
 */
public class ProductSearchType extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kAir = kDescription + 1;
    public static final int kCar = kAir + 1;
    public static final int kHotel = kCar + 1;
    public static final int kItem = kHotel + 1;
    public static final int kTour = kItem + 1;
    public static final int kTransportation = kTour + 1;
    public static final int kCruise = kTransportation + 1;
    public static final int kLand = kCruise + 1;
    public static final int kProductSearchTypeLastField = kLand;
    public static final int kProductSearchTypeFields = kLand - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kProductSearchTypeLastKey = kDescriptionKey;
    public static final int kProductSearchTypeKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProductSearchType()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductSearchType(RecordOwner screen)
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

    public static final String kProductSearchTypeFile = "ProductSearchType";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductSearchTypeFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.TABLE;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
            screen = new ProductSearchCategoryGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new ProductSearchTypeGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new ProductSearchTypeScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kAir)
            field = new BooleanField(this, "Air", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCar)
            field = new BooleanField(this, "Car", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kHotel)
            field = new BooleanField(this, "Hotel", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItem)
            field = new BooleanField(this, "Item", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTour)
            field = new BooleanField(this, "Tour", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTransportation)
            field = new BooleanField(this, "Transportation", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCruise)
            field = new BooleanField(this, "Cruise", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLand)
            field = new BooleanField(this, "Land", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductSearchTypeLastField)
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
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kProductSearchTypeLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProductSearchTypeLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * SetupProductTypeCheckboxes Method.
     */
    public void setupProductTypeCheckboxes(BaseScreen screen)
    {
        ProductType recProductType = (ProductType)screen.getRecord(ProductType.kProductTypeFile);
        if (recProductType == null)
            recProductType = new ProductType(screen);
        recProductType.setupProductTypeCheckboxes(screen, this, DBConstants.BLANK);
    }

}
