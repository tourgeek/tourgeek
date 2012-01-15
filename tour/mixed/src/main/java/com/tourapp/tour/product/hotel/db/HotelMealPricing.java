/**
 * @(#)HotelMealPricing.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.screen.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.hotel.db.*;

/**
 *  HotelMealPricing - Hotel meal pricing.
 */
public class HotelMealPricing extends ProductPricing
     implements HotelMealPricingModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kHotelID = kProductID;
    public static final int kMealPlanID = kClassID;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kProductTermsID = kProductTermsID;
    //public static final int kCost = kCost;
    public static final int kHotelMealPricingLastField = kProductPricingLastField;
    public static final int kHotelMealPricingFields = kProductPricingLastField - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kHotelIDKey = kIDKey + 1;
    public static final int kHotelMealPricingLastKey = kHotelIDKey;
    public static final int kHotelMealPricingKeys = kHotelIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public HotelMealPricing()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelMealPricing(RecordOwner screen)
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

    public static final String kHotelMealPricingFile = "HotelMealPricing";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kHotelMealPricingFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Meal Price";
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
            screen = new HotelMealPricingScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new HotelMealPricingGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kHotelID)
            field = new ReferenceField(this, "HotelID", 8, null, null);
        if (iFieldSeq == kMealPlanID)
        {
            field = new MealPlanField(this, "MealPlanID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
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
        //if (iFieldSeq == kProductTermsID)
        //{
        //  field = new ProductTermsField(this, "ProductTermsID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == kCost)
            field = new FullCurrencyField(this, "Cost", 10, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kHotelMealPricingLastField)
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
        if (iKeyArea == kHotelIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "HotelID");
            keyArea.addKeyField(kHotelID, DBConstants.ASCENDING);
            keyArea.addKeyField(kMealPlanID, DBConstants.ASCENDING);
            keyArea.addKeyField(kEndDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kHotelMealPricingLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kHotelMealPricingLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Lookup the meal cost.
     */
    public HotelMealPricing getMealCost(int iHotelID, Date targetDate, int iMealPlanID)
    {
        if (targetDate == null)
            return null;
        Converter.initGlobals();
        Calendar calendar = Converter.gCalendar;
        calendar.setTime(targetDate);
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
            if (this.getField(kHotelID).getValue() == iHotelID)
            if (this.getField(kMealPlanID).getValue() == iMealPlanID)
            if (this.getField(kStartDate).getValue() <= dateEnd.getTime()) // Start <= thisDate
            if (this.getField(kEndDate).getValue() >= dateStart.getTime())   // End >= thisDate
                return this;        // Valid price
        }
        this.getField(kHotelID).setValue(iHotelID);
        this.getField(kMealPlanID).setValue(iMealPlanID);
        ((DateTimeField)this.getField(kEndDate)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        FileListener listener = null;
        try   {
            this.close();
            this.setKeyArea(HotelMealPricing.kHotelIDKey);
            this.addListener(listener = new SubCurrentFilter(true, false));
            while (this.hasNext())
            {   // Loop until found or not
                this.next();
                if (this.getField(kHotelID).getValue() != iHotelID)
                    break;
                if (this.getField(kMealPlanID).getValue() != iMealPlanID)
                    break;
                if (this.getField(kStartDate).getValue() > dateEnd.getTime()) // Start > thisDate
                    break;
                if (this.getField(kEndDate).getValue() >= dateStart.getTime())   // End >= thisDate
                    return this;        // Valid price
            }
        } catch (DBException e)   {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (listener != null)
                this.removeListener(listener, true);
        }
        return null;    // Rate not found if loop finished
    }

}
