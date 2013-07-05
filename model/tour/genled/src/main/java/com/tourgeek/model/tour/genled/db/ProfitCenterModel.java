/**
  * @(#)ProfitCenterModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface ProfitCenterModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PROFIT_CENTER_NO = "ProfitCenterNo";
    public static final String DESCRIPTION = "Description";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String PROFIT_CENTER_NO_KEY = "ProfitCenterNo";

    public static final String PROFIT_CENTER_FILE = "ProfitCenter";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.ProfitCenter";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.ProfitCenter";

}
