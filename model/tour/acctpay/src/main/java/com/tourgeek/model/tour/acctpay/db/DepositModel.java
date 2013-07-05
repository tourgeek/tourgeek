/**
  * @(#)DepositModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface DepositModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String DEPOSIT_TYPE = "DepositType";
    public static final String QUANTITY = "Quantity";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String DEPOSIT_FILE = "Deposit";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.acctpay.db.Deposit";
    public static final String THICK_CLASS = "com.tourgeek.tour.acctpay.db.Deposit";

}
