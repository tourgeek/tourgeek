/**
 * @(#)HotelPricing.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import com.tourapp.tour.product.base.db.*;
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(HOTEL_PRICING_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
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
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new ReferenceField(this, PRODUCT_ID, 8, null, null);
        if (iFieldSeq == 4)
        {
            field = new PaxBaseCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.DOUBLE_ID));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 5)
        {
            field = new HotelRateField(this, RATE_ID, 4, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 6)
        {
            field = new HotelClassField(this, CLASS_ID, 4, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
        {
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 8)
        {
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 9)
        //  field = new FullCurrencyField(this, COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
        {
            field = new ProductTermsField(this, PRODUCT_TERMS_ID, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 11)
        //  field = new CurrencyField(this, PRICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 12)
        //{
        //  field = new BooleanField(this, COMMISSIONABLE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 13)
        //{
        //  field = new PercentField(this, COMMISSION_RATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 14)
        //  field = new PayAtField(this, PAY_AT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new FullCurrencyField(this, ROOM_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new CurrencyField(this, ROOM_PRICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new DaysOfWeekField(this, DAYS_OF_WEEK, 6, null, null);
        if (iFieldSeq == 18)
        {
            field = new MealPlanField(this, MEAL_PLAN_ID, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 19)
        {
            field = new HotelRateField(this, USE_RATE_ID, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 20)
        {
            field = new HotelClassField(this, USE_CLASS_ID, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ProductID");
            keyArea.addKeyField(PRODUCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(PAX_CATEGORY_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(RATE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(CLASS_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(END_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
