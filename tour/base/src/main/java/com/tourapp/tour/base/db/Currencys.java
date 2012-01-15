/**
 * @(#)Currencys.
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
import org.jbundle.main.user.db.*;
import com.tourapp.tour.base.screen.*;
import com.tourapp.model.tour.base.db.*;

/**
 *  Currencys - Currencies.
 */
public class Currencys extends VirtualRecord
     implements CurrencysModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kCurrencyCode = kDescription + 1;
    public static final int kLastRate = kCurrencyCode + 1;
    public static final int kRateChangedDate = kLastRate + 1;
    public static final int kRateChangedBy = kRateChangedDate + 1;
    public static final int kCostingRate = kRateChangedBy + 1;
    public static final int kCostingChangedDate = kCostingRate + 1;
    public static final int kCostingChangedBy = kCostingChangedDate + 1;
    public static final int kRoundAt = kCostingChangedBy + 1;
    public static final int kIntegerDesc = kRoundAt + 1;
    public static final int kFractionDesc = kIntegerDesc + 1;
    public static final int kFractionAmount = kFractionDesc + 1;
    public static final int kSign = kFractionAmount + 1;
    public static final int kLanguageID = kSign + 1;
    public static final int kNaturalInteger = kLanguageID + 1;
    public static final int kNaturalFraction = kNaturalInteger + 1;
    public static final int kCurrencysLastField = kNaturalFraction;
    public static final int kCurrencysFields = kNaturalFraction - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCurrencyCodeKey = kIDKey + 1;
    public static final int kDescriptionKey = kCurrencyCodeKey + 1;
    public static final int kCurrencysLastKey = kDescriptionKey;
    public static final int kCurrencysKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Currencys()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Currencys(RecordOwner screen)
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

    public static final String kCurrencysFile = "Currencys";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCurrencysFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Currency";
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
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(CURRENCYS_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(CURRENCYS_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new StringField(this, "Description", 25, null, null);
        if (iFieldSeq == kCurrencyCode)
            field = new StringField(this, "CurrencyCode", 3, null, null);
        if (iFieldSeq == kLastRate)
            field = new RealField(this, "LastRate", 10, null, null);
        if (iFieldSeq == kRateChangedDate)
            field = new DateField(this, "RateChangedDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateChangedBy)
            field = new UserField(this, "RateChangedBy", 16, null, null);
        if (iFieldSeq == kCostingRate)
            field = new RealField(this, "CostingRate", 10, null, null);
        if (iFieldSeq == kCostingChangedDate)
            field = new DateField(this, "CostingChangedDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCostingChangedBy)
            field = new UserField(this, "CostingChangedBy", 16, null, null);
        if (iFieldSeq == kRoundAt)
        {
            field = new ShortField(this, "RoundAt", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kIntegerDesc)
            field = new StringField(this, "IntegerDesc", 20, null, "Dollar");
        if (iFieldSeq == kFractionDesc)
            field = new StringField(this, "FractionDesc", 20, null, null);
        if (iFieldSeq == kFractionAmount)
            field = new IntegerField(this, "FractionAmount", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(100));
        if (iFieldSeq == kSign)
            field = new StringField(this, "Sign", 3, null, "$");
        if (iFieldSeq == kLanguageID)
            field = new LanguageField(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNaturalInteger)
            field = new StringField(this, "NaturalInteger", 20, null, null);
        if (iFieldSeq == kNaturalFraction)
            field = new StringField(this, "NaturalFraction", 20, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCurrencysLastField)
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
        if (iKeyArea == kCurrencyCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "CurrencyCode");
            keyArea.addKeyField(kCurrencyCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCurrencysLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCurrencysLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        DateUpdatedHandler update1 = new DateUpdatedHandler(kRateChangedDate);
        this.getField(kLastRate).addListener(update1);
        ChangedByHandler name1 = new ChangedByHandler(kRateChangedBy);
        this.getField(kLastRate).addListener(name1);
        DateUpdatedHandler update2 = new DateUpdatedHandler(kCostingChangedDate);
        this.getField(kCostingRate).addListener(update2);
        ChangedByHandler name2 = new ChangedByHandler(kCostingChangedBy);
        this.getField(kCostingRate).addListener(name2);
    }
    /**
     * Convert to USD.
     */
    public double convertCostToUSD(double dCost, boolean bCostingExchange)
    {
        double dExchange;
        if ((bCostingExchange) && (!this.getField(kCostingRate).isNull()))
            dExchange = this.getField(kCostingRate).getValue();
        else
            dExchange = this.getField(kLastRate).getValue();
        dCost = Math.floor(dCost * dExchange * 100.00 + 0.5) / 100.00;
        return dCost;
    }

}
