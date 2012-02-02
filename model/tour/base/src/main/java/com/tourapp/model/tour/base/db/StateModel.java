/**
 * @(#)StateModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import com.tourapp.model.tour.base.db.*;

public interface StateModel extends LocationModel
{

    //public static final String ID = ID;
    //public static final String NAME = NAME;
    public static final String STATE_POSTAL_CODE = CODE;
    public static final String COUNTRY_ID = "CountryID";
    public static final String DESCRIPTION = "Description";
    public static final String PICTURE = "Picture";
    public static final String POLYGON = "Polygon";
    public static final String GMT_OFFSET = "GMTOffset";

    public static final String STATE_POSTAL_CODE_KEY = "StatePostalCode";

    public static final String COUNTRY_ID_KEY = "CountryID";
    public static final String STATE_SCREEN_CLASS = "com.tourapp.tour.base.screen.StateScreen";
    public static final String STATE_GRID_SCREEN_CLASS = "com.tourapp.tour.base.screen.StateGridScreen";

    public static final String STATE_FILE = "State";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.State";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.State";

}
