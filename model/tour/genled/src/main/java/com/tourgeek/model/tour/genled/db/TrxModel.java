/**
  * @(#)TrxModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TrxModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String TRX_STATUS_ID = "TrxStatusID";
    public static final String TRX_USER_ID = "TrxUserID";

    public static final String TRX_FILE = "Trx";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.Trx";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.Trx";

}
