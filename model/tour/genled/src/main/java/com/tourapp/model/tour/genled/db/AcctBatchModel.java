/**
 * @(#)AcctBatchModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AcctBatchModel extends Rec
{
    public static final String ACCT_BATCH_SCREEN_CLASS = "com.tourapp.tour.genled.screen.batch.AcctBatchScreen";
    public static final String ACCT_BATCH_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.batch.AcctBatchGridScreen";
    public static final String AUTO_CLOSING = "Auto-Closing";

    public static final String ACCT_BATCH_FILE = "AcctBatch";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AcctBatch";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AcctBatch";

}
