/**
 * @(#)AcctDetailDistModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AcctDetailDistModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String ACCT_DETAIL_ID = "AcctDetailID";
    public static final String TRX_ID = "TrxID";
    public static final String TRX_DESC_ID = "TrxDescID";
    public static final String TRX_TYPE_ID = "TrxTypeID";
    public static final String TRX_DATE = "TrxDate";
    public static final String AMOUNT = "Amount";
    public static final String TRX_ENTRY = "TrxEntry";
    public static final String USER_ID = "UserID";
    public static final String ACCT_DETAIL_DIST_GROUP_ID = "AcctDetailDistGroupID";

    public static final String DIST_DETAIL_KEY = "DistDetail";

    public static final String ACCT_DETAIL_DIST_GROUP_ID_KEY = "AcctDetailDistGroupID";

    public static final String TRX_DESC_ID_KEY = "TrxDescID";
    public static final String DIST_DISTRIBUTION = "Distribution";
    public static final String DIST_GROUP = "Group";
    public static final String DIST_SOURCE = "Source";
    public static final String DIST_TRANSACTION = "Transaction";
    public static final String ACCT_DETAIL_DIST_SCREEN_CLASS = "com.tourapp.tour.genled.screen.detail.AcctDetailDistScreen";
    public static final String ACCT_DETAIL_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.detail.AcctDetailDistGridScreen";

    public static final String ACCT_DETAIL_DIST_FILE = "AcctDetailDist";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AcctDetailDist";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AcctDetailDist";

}
