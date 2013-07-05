/**
  * @(#)AutoDistModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AutoDistModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String AUTO_DIST_DESC = "AutoDistDesc";

    public static final String AUTO_DIST_DESC_KEY = "AutoDistDesc";
    public static final String AUTO_DIST_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.autodist.AutoDistScreen";
    public static final String AUTO_DIST_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.autodist.AutoDistGridScreen";

    public static final String AUTO_DIST_FILE = "AutoDist";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.AutoDist";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.AutoDist";

}
