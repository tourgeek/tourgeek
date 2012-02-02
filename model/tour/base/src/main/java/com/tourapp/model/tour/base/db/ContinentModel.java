/**
 * @(#)ContinentModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import com.tourapp.model.tour.base.db.*;

public interface ContinentModel extends LocationModel
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = NAME;

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CONTINENT_FILE = "Continent";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.Continent";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.Continent";

}
