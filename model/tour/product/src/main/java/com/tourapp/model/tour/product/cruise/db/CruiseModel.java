/**
 * @(#)CruiseModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.cruise.db;

import com.tourapp.model.tour.product.base.db.*;

public interface CruiseModel extends TransportProductModel
{
    public static final String FREQUENCY = "Frequency";
    public static final String DISTANCE = "Distance";
    public static final String DAYS = "Days";
    public static final String BREAKFASTS = "Breakfasts";
    public static final String LUNCHES = "Lunches";
    public static final String DINNERS = "Dinners";

    public static final String CRUISE_FILE = "Cruise";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.cruise.db.Cruise";
    public static final String THICK_CLASS = "com.tourapp.tour.product.cruise.db.Cruise";

}
