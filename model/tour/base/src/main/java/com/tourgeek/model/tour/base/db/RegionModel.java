/**
  * @(#)RegionModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.base.db;

import com.tourgeek.model.tour.base.db.*;

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
    public static final String REGION_SCREEN_CLASS = "com.tourgeek.tour.base.screen.RegionScreen";
    public static final String REGION_GRID_SCREEN_CLASS = "com.tourgeek.tour.base.screen.RegionGridScreen";

    public static final String REGION_FILE = "Region";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.base.db.Region";
    public static final String THICK_CLASS = "com.tourgeek.tour.base.db.Region";

}
