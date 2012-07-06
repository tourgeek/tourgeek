/**
  * @(#)AffiliationModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface AffiliationModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String CODE = "Code";
    public static final String DESCRIPTION = "Description";
    public static final String AGENT_COMM = "AgentComm";
    public static final String AFFILIATION_COMM = "AffiliationComm";
    public static final String VENDOR_ID = "VendorID";

    public static final String CODE_KEY = "Code";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String AFFILIATION_SCREEN_CLASS = "com.tourapp.tour.profile.screen.AffiliationScreen";
    public static final String AFFILIATION_GRID_SCREEN_CLASS = "com.tourapp.tour.profile.screen.AffiliationGridScreen";

    public static final String AFFILIATION_FILE = "Affiliation";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.Affiliation";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.Affiliation";

}
