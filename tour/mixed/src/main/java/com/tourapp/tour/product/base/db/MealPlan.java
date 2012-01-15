/**
 * @(#)MealPlan.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  MealPlan - Meal plan.
 */
public class MealPlan extends BaseClass
     implements MealPlanModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kDescription = kDescription;
    //public static final int kCode = kCode;
    public static final int kBreakfast = kBaseClassLastField + 1;
    public static final int kLunch = kBreakfast + 1;
    public static final int kDinner = kLunch + 1;
    public static final int kMealPlanLastField = kDinner;
    public static final int kMealPlanFields = kDinner - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kCodeKey = kDescriptionKey + 1;
    public static final int kMealPlanLastKey = kCodeKey;
    public static final int kMealPlanKeys = kCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public MealPlan()
    {
        super();
    }
    /**
     * Constructor.
     */
    public MealPlan(RecordOwner screen)
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

    public static final String kMealPlanFile = "MealPlan";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kMealPlanFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Meal Plan";
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
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new MealPlanScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new MealPlanGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kDescription)
        //  field = new StringField(this, "Description", 20, null, null);
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 2, null, null);
        if (iFieldSeq == kBreakfast)
            field = new BooleanField(this, "Breakfast", 1, null, null);
        if (iFieldSeq == kLunch)
            field = new BooleanField(this, "Lunch", 1, null, null);
        if (iFieldSeq == kDinner)
            field = new BooleanField(this, "Dinner", 1, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kMealPlanLastField)
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
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kMealPlanLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kMealPlanLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * GetMealDesc Method.
     */
    public String getMealDesc(BaseField fldMealPlanID, boolean bDetailedDesc)
    {
        String strMealDesc = Constants.BLANK;
        if ((fldMealPlanID == null) || (fldMealPlanID.getLength() == 0))
            return strMealDesc;
        this.getField(MealPlan.kID).moveFieldToThis(fldMealPlanID, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        try   {
            boolean bSuccess = this.seek("=");
            if (bSuccess)
            {
                String thisMealString = Constants.BLANK;
                if (bDetailedDesc)
                    strMealDesc = this.getField(MealPlan.kDescription).getString();
                else
                    strMealDesc = this.getField(MealPlan.kCode).getString();
                if (strMealDesc.length() == 0)
                {
                    if (this.getField(kBreakfast).getState())
                    {
                        if (bDetailedDesc)
                            strMealDesc += "Breakfast";
                        else
                            strMealDesc += "B";
                    }
                    if (this.getField(kLunch).getState())
                    {
                        if (bDetailedDesc)
                            strMealDesc += "Lunch";
                        else
                            strMealDesc += "L";
                    }
                    if (this.getField(kDinner).getState())
                    {
                        if (bDetailedDesc)
                            strMealDesc += "Dinner";
                        else
                            strMealDesc += "D";
                    }
                }
            }
        } catch (DBException e)   {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return strMealDesc;
    }

}
