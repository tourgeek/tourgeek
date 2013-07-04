/**
  * @(#)SCFModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface SCFModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String SCF_FROM = "ScfFrom";
    public static final String SCF_TO = "ScfTo";
    public static final String DESCRIPTION = "Description";
    public static final String SALESPERSON_ID = "SalespersonID";
    public static final String SALES_REGION_ID = "SalesRegionID";
    public static final String UPS_ZONE = "UpsZone";
    public static final String ZIP_ZONE = "ZipZone";

    public static final String SCF_TO_KEY = "ScfTo";

    public static final String SCF_FILE = "SCF";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.SCF";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.SCF";

}
