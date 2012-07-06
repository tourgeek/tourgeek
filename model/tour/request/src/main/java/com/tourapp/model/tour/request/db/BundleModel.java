/**
  * @(#)BundleModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.request.db;

import org.jbundle.model.db.*;

public interface BundleModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String BUNDLE_SCREEN_CLASS = "com.tourapp.tour.request.screen.bundle.BundleScreen";
    public static final String BUNDLE_GRID_SCREEN_CLASS = "com.tourapp.tour.request.screen.bundle.BundleGridScreen";

    public static final String BUNDLE_FILE = "Bundle";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.request.db.Bundle";
    public static final String THICK_CLASS = "com.tourapp.tour.request.db.Bundle";

}
