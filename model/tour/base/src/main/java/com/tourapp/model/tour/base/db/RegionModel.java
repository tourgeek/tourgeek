/**
 * @(#)RegionModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import com.tourapp.model.tour.base.db.*;

public interface RegionModel extends LocationModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = NAME;
    //public static final String CODE = CODE;
    public static final String CONTINENT_ID = "ContinentID";
    public static final String MEMO = "Memo";
    public static final String PICTURE = "Picture";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CONTINENT_ID_KEY = "ContinentID";
    public static final String REGION_SCREEN_CLASS = "com.tourapp.tour.base.screen.RegionScreen";
    public static final String REGION_GRID_SCREEN_CLASS = "com.tourapp.tour.base.screen.RegionGridScreen";

    public static final String REGION_FILE = "Region";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.Region";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.Region";

}
