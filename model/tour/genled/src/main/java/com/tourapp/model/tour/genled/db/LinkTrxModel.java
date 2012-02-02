/**
 * @(#)LinkTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import com.tourapp.model.tour.genled.db.*;

public interface LinkTrxModel extends BaseTrxModel
{

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
