
package com.tourgeek.model.tour.acctrec.db;

import com.tourgeek.model.tour.acctrec.db.*;

public interface CreditCardBatchDistModel extends CashBatchDistModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String CASH_BATCH_DETAIL_ID = CASH_BATCH_DETAIL_ID;
    //public static final String ACCOUNT_ID = ACCOUNT_ID;
    //public static final String AMOUNT = AMOUNT;
    //public static final String BOOKING_ID = BOOKING_ID;
    public static final String CREDIT_CARD_BATCH_DIST_SCREEN_CLASS = "com.tourgeek.tour.acctrec.screen.credit.CreditCardBatchDistScreen";
    public static final String CREDIT_CARD_BATCH_DIST_GRID_SCREEN_CLASS = "com.tourgeek.tour.acctrec.screen.credit.CreditCardBatchDistGridScreen";

    public static final String CREDIT_CARD_BATCH_DIST_FILE = "CreditCardBatchDist";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.acctrec.db.CreditCardBatchDist";
    public static final String THICK_CLASS = "com.tourgeek.tour.acctrec.db.CreditCardBatchDist";

}
