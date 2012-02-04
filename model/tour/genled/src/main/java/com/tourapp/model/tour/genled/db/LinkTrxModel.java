/**
 * @(#)LinkTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import com.tourapp.model.tour.genled.db.*;

public interface LinkTrxModel extends BaseTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String TRX_DATE = TRX_DATE;
    //public static final String AMOUNT_LOCAL = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    public static final String LINKED_TRX_ID = "LinkedTrxID";
    public static final String LINKED_TRX_DESC_ID = "LinkedTrxDescID";

    public static final String LINKED_TRX_ID_KEY = "LinkedTrxID";
    public static final String PAYMENT_HISTORY_ICON = "Price";
    public static final String SOURCE = "Source";
    public static final String PAYMENT_DISTRIBUTION = "Payment Distribution";

    public static final String LINK_TRX_FILE = "LinkTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.LinkTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.LinkTrx";

}
