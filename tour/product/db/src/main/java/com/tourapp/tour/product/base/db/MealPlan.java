/**
  * @(#)MealPlan.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  MealPlan - Meal plan.
 */
public class MealPlan extends BaseClass
     implements MealPlanModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(MEAL_PLAN_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(MEAL_PLAN_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(MEAL_PLAN_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == 3)
        //  field = new StringField(this, DESCRIPTION, 20, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, CODE, 2, null, null);
        if (iFieldSeq == 5)
            field = new BooleanField(this, BREAKFAST, 1, null, null);
        if (iFieldSeq == 6)
            field = new BooleanField(this, LUNCH, 1, null, null);
        if (iFieldSeq == 7)
            field = new BooleanField(this, DINNER, 1, null, null);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(DESCRIPTION, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
        this.getField(MealPlan.ID).moveFieldToThis(fldMealPlanID, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        try   {
            boolean bSuccess = this.seek("=");
            if (bSuccess)
            {
                String thisMealString = Constants.BLANK;
                if (bDetailedDesc)
                    strMealDesc = this.getField(MealPlan.DESCRIPTION).getString();
                else
                    strMealDesc = this.getField(MealPlan.CODE).getString();
                if (strMealDesc.length() == 0)
                {
                    if (this.getField(MealPlan.BREAKFAST).getState())
                    {
                        if (bDetailedDesc)
                            strMealDesc += "Breakfast";
                        else
                            strMealDesc += "B";
                    }
                    if (this.getField(MealPlan.LUNCH).getState())
                    {
                        if (bDetailedDesc)
                            strMealDesc += "Lunch";
                        else
                            strMealDesc += "L";
                    }
                    if (this.getField(MealPlan.DINNER).getState())
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
