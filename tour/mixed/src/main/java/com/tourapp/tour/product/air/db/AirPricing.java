/**
 * @(#)AirPricing.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.air.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.air.screen.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.air.db.*;

/**
 *  AirPricing - .
 */
public class AirPricing extends ProductPricing
     implements AirPricingModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kProductID = kProductID;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kRateID = kRateID;
    //public static final int kClassID = kClassID;
    //public static final int kCost = kCost;
    //public static final int kProductTermsID = kProductTermsID;
    public static final int kAirPricingLastField = kProductPricingLastField;
    public static final int kAirPricingFields = kProductPricingLastField - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProductIDKey = kIDKey + 1;
    public static final int kAirPricingLastKey = kProductIDKey;
    public static final int kAirPricingKeys = kProductIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public AirPricing()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AirPricing(RecordOwner screen)
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

    public static final String kAirPricingFile = "AirPricing";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAirPricingFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Flight pricing";
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(AIR_PRICING_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(AIR_PRICING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kProductID)
        {
            field = new AirField(this, "ProductID", 8, null, null);
            field.setNullable(false);
        }
        //if (iFieldSeq == kStartDate)
        //{
        //  field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kEndDate)
        //{
        //  field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == kRateID)
        {
            field = new AirRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClassID)
        {
            field = new AirClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kCost)
        //  field = new FullCurrencyField(this, "Cost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductTermsID)
        //{
        //  field = new ProductTermsField(this, "ProductTermsID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAirPricingLastField)
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
            keyArea.addKeyField(kClassID, DBConstants.ASCENDING);
            keyArea.addKeyField(kEndDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAirPricingLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAirPricingLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * GetAirCost Method.
     */
    public AirPricing getAirCost(Air recAir, Date dateTarget, int iRateID, int iClassID)
    {
        return (AirPricing)this.getProductCost(recAir, dateTarget, iRateID, iClassID);
    }

}
