
package com.tourgeek.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface PaymentCycleModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";

    public static final String CODE_KEY = "Code";

    public static final String PAYMENT_CYCLE_FILE = "PaymentCycle";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.acctpay.db.PaymentCycle";
    public static final String THICK_CLASS = "com.tourgeek.tour.acctpay.db.PaymentCycle";

}