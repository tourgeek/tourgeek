/**
 * @(#)CityModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import com.tourapp.model.tour.base.db.*;

public interface CityModel extends LocationModel
{
    public static final String DESCRIPTION = "Description";
    public static final String CITY_CODE = "CityCode";

    public static final String CITY_FILE = "City";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.City";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.City";

}
