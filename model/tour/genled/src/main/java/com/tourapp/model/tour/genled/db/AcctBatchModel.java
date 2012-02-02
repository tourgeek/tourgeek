/**
 * @(#)AcctBatchModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AcctBatchModel extends Rec
{

    //public static final String ID = ID;
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
    public static final String ACCT_BATCH_SCREEN_CLASS = "com.tourapp.tour.genled.screen.batch.AcctBatchScreen";
    public static final String ACCT_BATCH_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.batch.AcctBatchGridScreen";
    public static final String AUTOCLOSING = "Auto-Closing";

    public static final String ACCT_BATCH_FILE = "AcctBatch";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AcctBatch";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AcctBatch";

}
