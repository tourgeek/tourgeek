
package com.tourgeek.model.tour.base.db;

import com.tourgeek.model.tour.base.db.*;

public interface ContinentModel extends LocationModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = NAME;
    //public static final String CODE = CODE;

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CONTINENT_FILE = "Continent";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.base.db.Continent";
    public static final String THICK_CLASS = "com.tourgeek.tour.base.db.Continent";

}
