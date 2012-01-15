/**
 * @(#)MealPlanModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import com.tourapp.model.tour.product.base.db.*;

public interface MealPlanModel extends BaseClassModel
{
    public static final String DESCRIPTION = "Description";
    public static final String MEAL_PLAN_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.MealPlanScreen";
    public static final String MEAL_PLAN_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.MealPlanGridScreen";

    public static final String MEAL_PLAN_FILE = "MealPlan";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.MealPlan";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.MealPlan";

}
