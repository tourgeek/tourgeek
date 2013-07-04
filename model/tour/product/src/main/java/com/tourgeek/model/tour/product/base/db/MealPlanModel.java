
package com.tourgeek.model.tour.product.base.db;

import com.tourgeek.model.tour.product.base.db.*;

public interface MealPlanModel extends BaseClassModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    public static final String BREAKFAST = "Breakfast";
    public static final String LUNCH = "Lunch";
    public static final String DINNER = "Dinner";
    public static final String MEAL_PLAN_SCREEN_CLASS = "com.tourgeek.tour.product.base.screen.MealPlanScreen";
    public static final String MEAL_PLAN_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.base.screen.MealPlanGridScreen";

    public static final String MEAL_PLAN_FILE = "MealPlan";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.db.MealPlan";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.db.MealPlan";

}
