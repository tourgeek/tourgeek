/**
 * @(#)InterestModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.detail;

import org.jbundle.model.db.*;

public interface InterestModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PROFILE_ID = "ProfileID";
    public static final String DESCRIPTION = "Description";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String INTEREST_FILE = "Interest";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.detail.Interest";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.detail.Interest";

}
