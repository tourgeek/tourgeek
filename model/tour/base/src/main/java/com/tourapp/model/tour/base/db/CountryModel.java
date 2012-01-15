/**
 * @(#)CountryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import com.tourapp.model.tour.base.db.*;

public interface CountryModel extends LocationModel
{
    public static final String COUNTRY_SCREEN_CLASS = "com.tourapp.tour.base.screen.CountryScreen";
    public static final String COUNTRY_GRID_SCREEN_CLASS = "com.tourapp.tour.base.screen.CountryGridScreen";

    public static final String COUNTRY_FILE = "Country";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.Country";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.Country";

}
