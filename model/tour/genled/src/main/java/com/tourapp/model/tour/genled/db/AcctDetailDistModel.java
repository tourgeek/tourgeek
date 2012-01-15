/**
 * @(#)AcctDetailDistModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AcctDetailDistModel extends Rec
{
    public static final String DIST_DISTRIBUTION = "Distribution";
    public static final String DIST_GROUP = "Group";
    public static final String DIST_SOURCE = "Source";
    public static final String DIST_TRANSACTION = "Transaction";

    public static final String ACCT_DETAIL_DIST_FILE = "AcctDetailDist";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AcctDetailDist";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AcctDetailDist";

}
