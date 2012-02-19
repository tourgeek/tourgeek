/**
 * @(#)VersionsModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface VersionsModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String VERSIONS_FILE = "Versions";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.Versions";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.Versions";

}
