/**
 * @(#)HotelPricing.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.db;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.screen.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.hotel.db.*;

/**
 *  HotelPricing - Hotel pricing.
 */
public class HotelPricing extends ProductPricing
     implements HotelPricingModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kProductID = kProductID;
    //public static final int kPaxCategoryID = kPaxCategoryID;
    //public static final int kRateID = kRateID;
    //public static final int kClassID = kClassID;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kProductTermsID = kProductTermsID;
    //public static final int kCost = kCost;
    //public static final int kPrice = kPrice;
    public static final int kRoomCost = kProductPricingLastField + 1;
    public static final int kRoomPrice = kRoomCost + 1;
    public static final int kDaysOfWeek = kRoomPrice + 1;
    public static final int kMealPlanID = kDaysOfWeek + 1;
    public static final int kUseRateID = kMealPlanID + 1;
    public static final int kUseClassID = kUseRateID + 1;
    public static final int kHotelPricingLastField = kUseClassID;
    public static final int kHotelPricingFields = kUseClassID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProductIDKey = kIDKey + 1;
    public static final int kHotelPricingLastKey = kProductIDKey;
    public static final int kHotelPricingKeys = kProductIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public HotelPricing()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelPricing(RecordOwner screen)
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

    public static final String kHotelPricingFile = "HotelPricing";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kHotelPricingFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Room Rate";
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
            screen = Record.makeNewScreen(HOTEL_PRICING_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(HOTEL_PRICING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new ReferenceField(this, "ProductID", 8, null, null);
        if (iFieldSeq == kPaxCategoryID)
        {
            field = new PaxBaseCategoryField(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.DOUBLE_ID));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kRateID)
        {
            field = new HotelRateField(this, "RateID", 4, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClassID)
        {
            field = new HotelClassField(this, "ClassID", 4, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStartDate)
        {
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEndDate)
        {
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kProductTermsID)
        {
            field = new ProductTermsField(this, "ProductTermsID", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kCost)
        //  field = new FullCurrencyField(this, "Cost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRoomCost)
            field = new FullCurrencyField(this, "RoomCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPrice)
        //  field = new CurrencyField(this, "Price", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRoomPrice)
            field = new CurrencyField(this, "RoomPrice", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDaysOfWeek)
            field = new DaysOfWeekField(this, "DaysOfWeek", 6, null, null);
        if (iFieldSeq == kMealPlanID)
        {
            field = new MealPlanField(this, "MealPlanID", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kUseRateID)
        {
            field = new HotelRateField(this, "UseRateID", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kUseClassID)
        {
            field = new HotelClassField(this, "UseClassID", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kHotelPricingLastField)
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ProductID");
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
            keyArea.addKeyField(kPaxCategoryID, DBConstants.ASCENDING);
            keyArea.addKeyField(kRateID, DBConstants.ASCENDING);
            keyArea.addKeyField(kClassID, DBConstants.ASCENDING);
            keyArea.addKeyField(kEndDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kHotelPricingLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kHotelPricingLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.getField(HotelPricing.COST).addListener(new PaxToRoomHandler(HotelPricing.ROOM_COST));
        this.getField(HotelPricing.ROOM_COST).addListener(new RoomToPaxHandler(HotelPricing.COST));
        this.getField(HotelPricing.PRICE).addListener(new PaxToRoomHandler(HotelPricing.ROOM_PRICE));
        this.getField(HotelPricing.ROOM_PRICE).addListener(new RoomToPaxHandler(HotelPricing.PRICE));
    }
    /**
     * GetHotelCost Method.
     */
    public HotelPricing getHotelCost(int iHotelID, Date dateTarget, int iRateTypeID, int iRateClassID, int iPaxCategoryID)
    {
        if (dateTarget == null)
            return null;
        Converter.initGlobals();
        Calendar calendar = Converter.gCalendar;
        calendar.setTime(dateTarget);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dateStart = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date dateEnd = calendar.getTime();
        if ((this.getEditMode() == Constants.EDIT_IN_PROGRESS) || (this.getEditMode() == Constants.EDIT_CURRENT))
        {
            if (this.getField(ProductPricing.PRODUCT_ID).getValue() == iHotelID)
            if (this.getField(ProductPricing.PAX_CATEGORY_ID).getValue() == iPaxCategoryID)
            if (this.getField(ProductPricing.RATE_ID).getValue() == iRateTypeID)
            if (this.getField(ProductPricing.CLASS_ID).getValue() == iRateClassID)
            if (this.getField(ProductPricing.START_DATE).getValue() <= dateEnd.getTime()) // Start <= thisDate
            if (this.getField(ProductPricing.END_DATE).getValue() >= dateStart.getTime())   // End >= thisDate
                return this;        // Valid price
        }
        this.getField(ProductPricing.PRODUCT_ID).setValue(iHotelID);
        this.getField(ProductPricing.PAX_CATEGORY_ID).setValue(iPaxCategoryID);
        this.getField(ProductPricing.RATE_ID).setValue(iRateTypeID);
        this.getField(ProductPricing.CLASS_ID).setValue(iRateClassID);
        ((DateTimeField)this.getField(ProductPricing.END_DATE)).setDate(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        FileListener listener = null;
        try   {
            this.close();
            this.setKeyArea(HotelPricing.PRODUCT_ID_KEY);
            this.addListener(listener = new SubCurrentFilter(true, false));
            while (this.hasNext())
            {   // Loop until found or not
                this.next();
                if (this.getField(ProductPricing.PRODUCT_ID).getValue() != iHotelID)
                    break;
                if (this.getField(ProductPricing.PAX_CATEGORY_ID).getValue() != iPaxCategoryID)
                    break;
                if (this.getField(ProductPricing.RATE_ID).getValue() != iRateTypeID)
                    break;
                if (this.getField(ProductPricing.CLASS_ID).getValue() != iRateClassID)
                    break;
                if (this.getField(ProductPricing.START_DATE).getValue() > dateEnd.getTime())    // Start > thisDate
                    break;
                if (this.getField(ProductPricing.END_DATE).getValue() >= dateStart.getTime())   // End >= thisDate
                {
                    if ((this.getField(HotelPricing.USE_RATE_ID).getLength() == 0) &&
                        (this.getField(HotelPricing.USE_CLASS_ID).getLength() == 0))
                            return this;        // Valid price
                // Read the "Use rate" rate
                    iRateTypeID = (int)this.getField(HotelPricing.USE_RATE_ID).getValue();
                    iRateClassID = (int)this.getField(HotelPricing.USE_CLASS_ID).getValue();
                    double dMarkupLine = this.getField(HotelPricing.PRODUCT_TERMS_ID).getValue();
                    double dHotelCost = this.getField(HotelPricing.ROOM_COST).getValue();
                    String strMeals = this.getField(HotelPricing.MEAL_PLAN_ID).getString();
        
                    if (listener != null)
                        this.removeListener(listener, true);
                    if (this.getHotelCost(iHotelID, dateTarget, iRateTypeID, iRateClassID, iPaxCategoryID) == null)
                        return null;    // Not found
        
                    if (this.getField(HotelPricing.PRODUCT_TERMS_ID).isNull())
                        this.getField(HotelPricing.PRODUCT_TERMS_ID).setValue(dMarkupLine);
                    if (this.getField(HotelPricing.ROOM_COST).isNull())
                        this.getField(HotelPricing.ROOM_COST).setValue(dHotelCost);
                    if (this.getField(HotelPricing.MEAL_PLAN_ID).isNull())
                        this.getField(HotelPricing.MEAL_PLAN_ID).setString(strMeals);
                    return this;        // Valid price
                }
            }
        } catch (DBException ex)   {
            ex.printStackTrace();
        } finally {
            if (listener != null)
                this.removeListener(listener, true);
        }
        return null;    // Rate not found if loop finished
    }

}
