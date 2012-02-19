/**
 * @(#)PaymentCodeModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface PaymentCodeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";

    public static final String CODE_KEY = "Code";

    public static final String PAYMENT_CODE_FILE = "PaymentCode";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.PaymentCode";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.PaymentCode";

}
