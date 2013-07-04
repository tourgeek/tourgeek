/**
  * @(#)SalespersonModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface SalespersonModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String SALESPERSON_NAME = "SalespersonName";
    public static final String VENDOR_ID = "VendorID";

    public static final String SALESPERSON_NAME_KEY = "SalespersonName";

    public static final String SALESPERSON_FILE = "Salesperson";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.Salesperson";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.Salesperson";

}
