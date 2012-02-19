/**
 * @(#)TrxGroupModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TrxGroupModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String GROUP_CODE = "GroupCode";
    public static final String GROUP_DESC = "GroupDesc";
    public static final String TRX_DESC_ID = "TrxDescID";
    public static final String DESC_CODE = "DescCode";
    public static final String TRX_SYSTEM_ID = "TrxSystemID";
    public static final String SYSTEM_CODE = "SystemCode";

    public static final String TRX_DESC_ID_KEY = "TrxDescID";

    public static final String SYSTEM_CODE_KEY = "SystemCode";
    public static final String TRX_GROUP_SCREEN_CLASS = "com.tourapp.tour.genled.screen.trx.TrxGroupScreen";
    public static final String TRX_GROUP_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.trx.TrxGroupGridScreen";

    public static final String TRX_GROUP_FILE = "TrxGroup";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.TrxGroup";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.TrxGroup";

}
