/**
 * @(#)ProfileTypeModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface ProfileTypeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String PROFILE_TYPE_FILE = "ProfileType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.ProfileType";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.ProfileType";

}
