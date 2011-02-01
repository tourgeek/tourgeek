/**
 *  @(#)ProductType.
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

/**
 *  ProductType - Product Type.
 */
public class ProductType extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kCode = kDescription + 1;
    public static final int kIcon = kCode + 1;
    public static final int kProductTypeLastField = kIcon;
    public static final int kProductTypeFields = kIcon - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kDescriptionKey = kCodeKey + 1;
    public static final int kProductTypeLastKey = kDescriptionKey;
    public static final int kProductTypeKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String AIR = "Air";
    public static final int AIR_ID = 1;
    public static final String CAR = "Car";
    public static final int CAR_ID = 2;
    public static final String CRUISE = "Cruise";
    public static final int CRUISE_ID = 8;
    public static final String HOTEL = "Hotel";
    public static final int HOTEL_ID = 3;
    public static final String ITEM = "Item";
    public static final int ITEM_ID = 4;
    public static final String LAND = "Land";
    public static final int LAND_ID = 9;
    public static final String TOUR = "Tour";
    public static final int TOUR_ID = 6;
    public static final String TRANSPORTATION = "Transportation";
    public static final int TRANSPORTATION_ID = 7;
    public static final String UNKNOWN = "Unknown";
    public static final int UNKNOWN_ID = 0;
    public static final String TOUR_CODE = "T";
    public static final String AIR_CODE = "A";
    public static final String HOTEL_CODE = "H";
    public static final String LAND_CODE = "L";
    public static final String TRANSPORTATION_CODE = "R";
    public static final String CAR_CODE = "V";
    public static final String CRUISE_CODE = "U";
    public static final String ITEM_CODE = "I";
    public static final String UNKNOWN_CODE = "U";
    /**
     * Default constructor.
     */
    public ProductType()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductType(RecordOwner screen)
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

    public static final String kProductTypeFile = "ProductType";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductTypeFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product Type";
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
        return DBConstants.TABLE | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
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
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 1, null, null);
        if (iFieldSeq == kIcon)
            field = new ImageField(this, "Icon", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductTypeLastField)
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
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kProductTypeLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProductTypeLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Get the product type of this product class.
     */
    public int getProductTypeID(Product product)
    {
        this.close();
        String string = product.getTableNames(false);
        if (string.equals("TourHeader"))
            string = "Tour";
        return this.getProductTypeIDFromName(string);
    }
    /**
     * GetProductTypeIDFromName Method.
     */
    public int getProductTypeIDFromName(String string)
    {
        boolean bSuccess = false;
        if (this.getEditMode() == DBConstants.EDIT_CURRENT)
            if (string.equals(this.getField(ProductType.kDescription).toString()))
                bSuccess = true;    // Already current
        this.getField(ProductType.kDescription).setString(string);
        this.setKeyArea(ProductType.kDescriptionKey);
        try   {
            if (bSuccess == false)
                bSuccess = this.seek("=");
        } catch (DBException ex)    {
            bSuccess = false;
        }
        if (bSuccess)
            return (int)this.getField(ProductType.kID).getValue();
        else
            return -1;  // Not found???
    }
    /**
     * SetupProductTypeCheckboxes Method.
     */
    public void setupProductTypeCheckboxes(BaseScreen screen, Record record, String strFieldSuffix)
    {
        try {
            this.close();
            while (this.hasNext())
            {
                this.next();
                BaseField field = record.getField(this.getField(ProductType.kDescription).toString() + strFieldSuffix);
                if (field != null)
                    new SCheckBox(screen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), screen, field, ScreenConstants.DEFAULT_DISPLAY);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
