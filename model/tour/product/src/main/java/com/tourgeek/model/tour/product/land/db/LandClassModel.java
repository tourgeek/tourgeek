
package com.tourgeek.model.tour.product.land.db;

import com.tourgeek.model.tour.product.base.db.*;

public interface LandClassModel extends BaseClassModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    public static final String PRIVATE_VEHICLE_CODE = "PMC";
    public static final String SEAT_IN_COACH_CODE = "SIC";

    public static final String LAND_CLASS_FILE = "LandClass";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.land.db.LandClass";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.land.db.LandClass";

}
