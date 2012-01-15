/**
 * @(#)TransportationModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.trans.db;

import com.tourapp.model.tour.product.base.db.*;

public interface TransportationModel extends TransportProductModel
{
    public static final String FREQUENCY = "Frequency";
    public static final String DISTANCE = "Distance";
    public static final String HOURS = "Hours";
    public static final String BREAKFASTS = "Breakfasts";
    public static final String LUNCHES = "Lunches";
    public static final String DINNERS = "Dinners";
    public static final String DAYS_OF_WEEK = "DaysOfWeek";
    public static final String TRANSPORTATION_SCREEN_CLASS = "com.tourapp.tour.product.trans.screen.TransportationScreen";
    public static final String TRANSPORTATION_GRID_SCREEN_CLASS = "com.tourapp.tour.product.trans.screen.TransportationGridScreen";

    public static final String TRANSPORTATION_FILE = "Transportation";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.trans.db.Transportation";
    public static final String THICK_CLASS = "com.tourapp.tour.product.trans.db.Transportation";

}
