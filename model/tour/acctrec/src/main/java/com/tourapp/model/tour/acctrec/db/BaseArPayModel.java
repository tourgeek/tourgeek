/**
 * @(#)BaseArPayModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.genled.db.*;

public interface BaseArPayModel extends BaseTrxModel
{
    public static final String BATCH = "Batch";
    public static final String ENTERED = "Entered";
    public static final String SUBMITTED = "Submitted";
    public static final String PAID = "Paid";

    public static final String BASE_AR_PAY_FILE = "BaseArPay";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.BaseArPay";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.BaseArPay";

}
