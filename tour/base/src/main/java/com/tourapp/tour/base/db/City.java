/**
 * @(#)City.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.base.screen.*;
import com.tourapp.model.tour.base.db.*;

/**
 *  City - Airports.
 */
public class City extends Location
     implements CityModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kName;
    public static final int kCityCode = kCode;
    public static final int kTicketCityDesc = kLocationLastField + 1;
    public static final int kICAOCode = kTicketCityDesc + 1;
    public static final int kMainCityID = kICAOCode + 1;
    public static final int kStateID = kMainCityID + 1;
    public static final int kCountryID = kStateID + 1;
    public static final int kDomesticTax = kCountryID + 1;
    public static final int kInternationalTax = kDomesticTax + 1;
    public static final int kArrivalTax = kInternationalTax + 1;
    public static final int kFacilitiesTax = kArrivalTax + 1;
    public static final int kGMTOffset = kFacilitiesTax + 1;
    public static final int kLatitude = kGMTOffset + 1;
    public static final int kLongitude = kLatitude + 1;
    public static final int kAltitude = kLongitude + 1;
    public static final int kCityType = kAltitude + 1;
    public static final int kMap = kCityType + 1;
    public static final int kCityLastField = kMap;
    public static final int kCityFields = kMap - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kCityCodeKey = kDescriptionKey + 1;
    public static final int kCountryIDKey = kCityCodeKey + 1;
    public static final int kTicketCityDescKey = kCountryIDKey + 1;
    public static final int kCityLastKey = kTicketCityDescKey;
    public static final int kCityKeys = kTicketCityDescKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public City()
    {
        super();
    }
    /**
     * Constructor.
     */
    public City(RecordOwner screen)
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

    public static final String kCityFile = "City";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCityFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "City";
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
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(CITY_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(CITY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new StringField(this, "Description", 40, null, null);
        if (iFieldSeq == kTicketCityDesc)
            field = new StringField(this, "TicketCityDesc", 16, null, null);
        if (iFieldSeq == kCityCode)
            field = new StringField(this, "CityCode", 3, null, null);
        if (iFieldSeq == kICAOCode)
            field = new StringField(this, "ICAOCode", 4, null, null);
        if (iFieldSeq == kMainCityID)
            field = new CityField(this, "MainCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStateID)
            field = new StateField(this, "StateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCountryID)
        {
            field = new CountryField(this, "CountryID", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDomesticTax)
        {
            field = new DoubleField(this, "DomesticTax", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kInternationalTax)
        {
            field = new DoubleField(this, "InternationalTax", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kArrivalTax)
            field = new DoubleField(this, "ArrivalTax", 10, null, null);
        if (iFieldSeq == kFacilitiesTax)
            field = new FloatField(this, "FacilitiesTax", 8, null, null);
        if (iFieldSeq == kGMTOffset)
        {
            field = new FloatField(this, "GMTOffset", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLatitude)
            field = new DoubleField(this, "Latitude", 8, null, null);
        if (iFieldSeq == kLongitude)
            field = new DoubleField(this, "Longitude", 8, null, null);
        if (iFieldSeq == kAltitude)
            field = new DoubleField(this, "Altitude", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityType)
            field = new CityTypeField(this, "CityType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMap)
            field = new ImageField(this, "Map", 9999, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCityLastField)
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
        if (iKeyArea == kCityCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "CityCode");
            keyArea.addKeyField(kCityCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCountryIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CountryID");
            keyArea.addKeyField(kCountryID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTicketCityDescKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TicketCityDesc");
            keyArea.addKeyField(kTicketCityDesc, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCityLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCityLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
