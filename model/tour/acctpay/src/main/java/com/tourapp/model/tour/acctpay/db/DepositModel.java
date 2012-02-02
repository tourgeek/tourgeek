/**
 * @(#)DepositModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface DepositModel extends Rec
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = "Description";
    public static final String DEPOSIT_TYPE = "DepositType";
    public static final String QUANTITY = "Quantity";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String DEPOSIT_FILE = "Deposit";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.Deposit";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.Deposit";

}
