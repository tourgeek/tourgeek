/**
 * @(#)TrxStatusModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TrxStatusModel extends Rec
{
    public static final String TRX_STATUS_SCREEN_CLASS = "com.tourapp.tour.genled.screen.trx.TrxStatusScreen";
    public static final String TRX_STATUS_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.trx.TrxStatusGridScreen";

    public static final String TRX_STATUS_FILE = "TrxStatus";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.TrxStatus";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.TrxStatus";

}
