/**
 * @(#)TrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TrxModel extends Rec
{

    //public static final String ID = ID;
    public static final String TRX_STATUS_ID = "TrxStatusID";

    public static final String TRX_FILE = "Trx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.Trx";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.Trx";

}
