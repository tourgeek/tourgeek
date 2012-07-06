/**
  * @(#)PrepayTypeModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface PrepayTypeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";

    public static final String CODE_KEY = "Code";

    public static final String PREPAY_TYPE_FILE = "PrepayType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.PrepayType";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.PrepayType";

}
