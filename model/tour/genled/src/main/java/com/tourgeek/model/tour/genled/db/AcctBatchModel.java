/**
  * @(#)AcctBatchModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AcctBatchModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String USER_ID = "UserID";
    public static final String RECURRING = "Recurring";
    public static final String TRX_DATE = "TrxDate";
    public static final String COMMENTS = "Comments";
    public static final String SOURCE = "Source";
    public static final String BALANCE = "Balance";
    public static final String NEXT_SEQUENCE = "NextSequence";
    public static final String AUTO_REVERSAL = "AutoReversal";
    public static final String AUTO_REVERSAL_DATE = "AutoReversalDate";
    public static final String AUTO_CLOSING = "AutoClosing";
    public static final String TRX_ENTRY = "TrxEntry";

    public static final String USER_ID_KEY = "UserID";
    public static final String AUTOCLOSING = "Auto-Closing";
    public static final String ACCT_BATCH_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.batch.AcctBatchScreen";
    public static final String ACCT_BATCH_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.batch.AcctBatchGridScreen";
    public static final String ACCT_BATCH_POST_CLASS = "com.tourgeek.tour.genled.screen.batch.AcctBatchPost";

    public static final String ACCT_BATCH_FILE = "AcctBatch";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.AcctBatch";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.AcctBatch";

}
