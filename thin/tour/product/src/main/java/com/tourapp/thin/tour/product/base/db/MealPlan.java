/**
 * @(#)MealPlan.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.base.db.*;
import com.tourapp.model.tour.product.base.db.*;

public class MealPlan extends BaseClass
    implements MealPlanModel
{
    private static final long serialVersionUID = 1L;


    public MealPlan()
    {
        super();
    }
    public MealPlan(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String MEAL_PLAN_FILE = "MealPlan";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? MealPlan.MEAL_PLAN_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.TABLE | Constants.SHARED_DATA | Constants.LOCALIZABLE;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, DESCRIPTION, 20, null, null);
        field = new FieldInfo(this, CODE, 2, null, null);
        field = new FieldInfo(this, BREAKFAST, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, LUNCH, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DINNER, 1, null, null);
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "Code");
        keyArea.addKeyField("Code", Constants.ASCENDING);
    }

}
