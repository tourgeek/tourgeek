/**
 * @(#)TransactionTypeModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TransactionTypeModel extends Rec
{
    public static final String GENLED = "genled";
    public static final String ASSETDR = "assetdr";
    public static final String ACCTPAY = "acctpay";
    public static final String ACCTREC = "acctrec";
    public static final String PAYROLL = "payroll";
    public static final String AIR = "air";

    public static final String TRANSACTION_TYPE_FILE = "TransactionType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.TransactionType";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.TransactionType";

}
