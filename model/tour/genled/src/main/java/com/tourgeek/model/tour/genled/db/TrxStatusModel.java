/**
  * @(#)TrxStatusModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TrxStatusModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String STATUS_CODE = "StatusCode";
    public static final String STATUS_DESC = "StatusDesc";
    public static final String PREFERRED_SIGN = "PreferredSign";
    public static final String TRX_DESC_ID = "TrxDescID";
    public static final String DESC_CODE = "DescCode";
    public static final String TRX_SYSTEM_ID = "TrxSystemID";
    public static final String SYSTEM_CODE = "SystemCode";
    public static final String ACTIVE_TRX = "ActiveTrx";

    public static final String TRX_DESC_ID_KEY = "TrxDescID";

    public static final String SYSTEM_CODE_KEY = "SystemCode";
    public static final String TRX_STATUS_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.trx.TrxStatusScreen";
    public static final String TRX_STATUS_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.trx.TrxStatusGridScreen";

    public static final String TRX_STATUS_FILE = "TrxStatus";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.TrxStatus";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.TrxStatus";

}
