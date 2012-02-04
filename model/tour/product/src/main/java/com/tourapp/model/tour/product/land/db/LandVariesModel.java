/**
 * @(#)LandVariesModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.land.db;

import org.jbundle.model.db.*;

public interface LandVariesModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String CODE = "Code";
    public static final String DESCRIPTION = "Description";
    public static final String VARIES_BY = "VariesBy";

    public static final String VARIES_BY_KEY = "VariesBy";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String VARIES_PARAM = "varies";
    public static final String LAND_VARIES_SCREEN_CLASS = "com.tourapp.tour.product.land.screen.LandVariesScreen";
    public static final String LAND_VARIES_GRID_SCREEN_CLASS = "com.tourapp.tour.product.land.screen.LandVariesGridScreen";

    public static final String LAND_VARIES_FILE = "LandVaries";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.land.db.LandVaries";
    public static final String THICK_CLASS = "com.tourapp.tour.product.land.db.LandVaries";

}
