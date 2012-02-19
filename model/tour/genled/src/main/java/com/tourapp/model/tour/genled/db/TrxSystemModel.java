/**
 * @(#)TrxSystemModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TrxSystemModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String SYSTEM_CODE = "SystemCode";
    public static final String SYSTEM_DESC = "SystemDesc";

    public static final String SYSTEM_CODE_KEY = "SystemCode";

    public static final String SYSTEM_DESC_KEY = "SystemDesc";

    public static final String TRX_SYSTEM_FILE = "TrxSystem";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.TrxSystem";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.TrxSystem";

}
