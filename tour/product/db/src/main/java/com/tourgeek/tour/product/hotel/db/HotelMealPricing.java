
package com.tourgeek.tour.product.hotel.db;

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
import com.tourgeek.tour.product.base.db.*;
import java.util.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.model.tour.product.hotel.db.*;

/**
 *  HotelMealPricing - Hotel meal pricing.
 */
public class HotelMealPricing extends ProductPricing
     implements HotelMealPricingModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(HOTEL_MEAL_PRICING_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(HOTEL_MEAL_PRICING_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(HOTEL_MEAL_PRICING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new ReferenceField(this, HOTEL_ID, 8, null, null);
        //if (iFieldSeq == 4)
        //{
        //  field = new PaxBaseCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.ALL_ID));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 5)
        //{
        //  field = new ReferenceField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 6)
        {
            field = new MealPlanField(this, MEAL_PLAN_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 7)
        //{
        //  field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 8)
        //{
        //  field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 9)
            field = new FullCurrencyField(this, COST, 10, null, null);
        //if (iFieldSeq == 10)
        //{
        //  field = new ProductTermsField(this, PRODUCT_TERMS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, HOTEL_ID_KEY);
            keyArea.addKeyField(HOTEL_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MEAL_PLAN_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(END_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
            if (this.getField(HotelMealPricing.HOTEL_ID).getValue() == iHotelID)
            if (this.getField(HotelMealPricing.MEAL_PLAN_ID).getValue() == iMealPlanID)
            if (this.getField(HotelMealPricing.START_DATE).getValue() <= dateEnd.getTime()) // Start <= thisDate
            if (this.getField(HotelMealPricing.END_DATE).getValue() >= dateStart.getTime())   // End >= thisDate
                return this;        // Valid price
        }
        this.getField(HotelMealPricing.HOTEL_ID).setValue(iHotelID);
        this.getField(HotelMealPricing.MEAL_PLAN_ID).setValue(iMealPlanID);
        ((DateTimeField)this.getField(HotelMealPricing.END_DATE)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        FileListener listener = null;
        try   {
            this.close();
            this.setKeyArea(HotelMealPricing.HOTEL_ID_KEY);
            this.addListener(listener = new SubCurrentFilter(true, false));
            while (this.hasNext())
            {   // Loop until found or not
                this.next();
                if (this.getField(HotelMealPricing.HOTEL_ID).getValue() != iHotelID)
                    break;
                if (this.getField(HotelMealPricing.MEAL_PLAN_ID).getValue() != iMealPlanID)
                    break;
                if (this.getField(HotelMealPricing.START_DATE).getValue() > dateEnd.getTime()) // Start > thisDate
                    break;
                if (this.getField(HotelMealPricing.END_DATE).getValue() >= dateStart.getTime())   // End >= thisDate
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
