/**
  * @(#)BaseTrxModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.genled.db;

import com.tourapp.model.tour.genled.db.*;

public interface BaseTrxModel extends TrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    public static final String TRX_DATE = "TrxDate";
    public static final String AMOUNT_LOCAL = "AmountLocal";
    public static final String TRX_ENTRY = "TrxEntry";
    public static final String TRX_TYPE = "Trx";
    public static final String DIST_TYPE = "Dist";

    public static final String BASE_TRX_FILE = "BaseTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.BaseTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.BaseTrx";

}
