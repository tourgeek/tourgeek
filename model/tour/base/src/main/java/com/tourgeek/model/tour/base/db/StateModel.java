
package com.tourgeek.model.tour.base.db;

import com.tourgeek.model.tour.base.db.*;

public interface StateModel extends LocationModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String NAME = NAME;
    public static final String STATE_POSTAL_CODE = CODE;
    public static final String COUNTRY_ID = "CountryID";
    public static final String DESCRIPTION = "Description";
    public static final String PICTURE = "Picture";
    public static final String POLYGON = "Polygon";
    public static final String GMT_OFFSET = "GMTOffset";

    public static final String STATE_POSTAL_CODE_KEY = "StatePostalCode";

    public static final String COUNTRY_ID_KEY = "CountryID";
    public static final String STATE_SCREEN_CLASS = "com.tourgeek.tour.base.screen.StateScreen";
    public static final String STATE_GRID_SCREEN_CLASS = "com.tourgeek.tour.base.screen.StateGridScreen";

    public static final String STATE_FILE = "State";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.base.db.State";
    public static final String THICK_CLASS = "com.tourgeek.tour.base.db.State";

}
