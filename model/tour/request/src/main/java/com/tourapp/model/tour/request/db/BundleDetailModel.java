/**
 * @(#)BundleDetailModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.request.db;

import org.jbundle.model.db.*;

public interface BundleDetailModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String BUNDLE_ID = "BundleID";
    public static final String BROCHURE_ID = "BrochureID";

    public static final String BUNDLE_ID_KEY = "BundleID";
    public static final String BUNDLE_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.request.screen.bundle.BundleDetailGridScreen";

    public static final String BUNDLE_DETAIL_FILE = "BundleDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.request.db.BundleDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.request.db.BundleDetail";

}
