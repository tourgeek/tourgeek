/**
  * @(#)BaseRateModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface BaseRateModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CODE_KEY = "Code";

    public static final String BASE_RATE_FILE = "BaseRate";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.db.BaseRate";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.db.BaseRate";

}
