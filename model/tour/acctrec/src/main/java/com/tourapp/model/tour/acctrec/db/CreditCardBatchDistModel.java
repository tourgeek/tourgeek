/**
 * @(#)CreditCardBatchDistModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.acctrec.db.*;

public interface CreditCardBatchDistModel extends CashBatchDistModel
{
    public static final String CREDIT_CARD_BATCH_DIST_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.credit.CreditCardBatchDistScreen";
    public static final String CREDIT_CARD_BATCH_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.credit.CreditCardBatchDistGridScreen";

    public static final String CREDIT_CARD_BATCH_DIST_FILE = "CreditCardBatchDist";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.CreditCardBatchDist";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.CreditCardBatchDist";

}
