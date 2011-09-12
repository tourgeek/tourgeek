/**
 * @(#)Country.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.db;

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
import com.tourapp.tour.base.screen.*;

/**
 *  Country - Country.
 */
public class Country extends Location
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kName = kName;
    //public static final int kCode = kCode;
    public static final int kCurrencysID = kLocationLastField + 1;
    public static final int kLanguageID = kCurrencysID + 1;
    public static final int kICAOCountryCode = kLanguageID + 1;
    public static final int kFaxPrefix = kICAOCountryCode + 1;
    public static final int kInternationalTax = kFaxPrefix + 1;
    public static final int kDomesticTax = kInternationalTax + 1;
    public static final int kArrivalTax = kDomesticTax + 1;
    public static final int kGMTOffset = kArrivalTax + 1;
    public static final int kRegionID = kGMTOffset + 1;
    public static final int kDescription = kRegionID + 1;
    public static final int kPicture = kDescription + 1;
    public static final int kCountryLastField = kPicture;
    public static final int kCountryFields = kPicture - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kNameKey = kIDKey + 1;
    public static final int kCodeKey = kNameKey + 1;
    public static final int kRegionIDKey = kCodeKey + 1;
    public static final int kCountryLastKey = kRegionIDKey;
    public static final int kCountryKeys = kRegionIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Country()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Country(RecordOwner screen)
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

    public static final String kCountryFile = "Country";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCountryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Country";
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
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new CountryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new CountryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kName)
            field = new StringField(this, "Name", 40, null, null);
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 2, null, null);
        if (iFieldSeq == kCurrencysID)
            field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLanguageID)
            field = new LanguageField(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kICAOCountryCode)
            field = new StringField(this, "ICAOCountryCode", 3, null, null);
        if (iFieldSeq == kFaxPrefix)
        {
            field = new StringField(this, "FaxPrefix", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kInternationalTax)
        {
            field = new DoubleField(this, "InternationalTax", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDomesticTax)
        {
            field = new DoubleField(this, "DomesticTax", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kArrivalTax)
            field = new DoubleField(this, "ArrivalTax", 10, null, null);
        if (iFieldSeq == kGMTOffset)
        {
            field = new FloatField(this, "GMTOffset", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kRegionID)
            field = new RegionField(this, "RegionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDescription)
            field = new MemoField(this, "Description", 9999, null, null);
        if (iFieldSeq == kPicture)
            field = new ImageField(this, "Picture", 9999, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCountryLastField)
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
        if (iKeyArea == kNameKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Name");
            keyArea.addKeyField(kName, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kRegionIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "RegionID");
            keyArea.addKeyField(kRegionID, DBConstants.ASCENDING);
            keyArea.addKeyField(kName, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCountryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCountryLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
