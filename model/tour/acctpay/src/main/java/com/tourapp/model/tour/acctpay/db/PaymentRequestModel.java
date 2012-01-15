/**
 * @(#)PaymentRequestModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import org.jbundle.model.db.*;

public interface PaymentRequestModel extends Rec
{

    public static final String PAYMENT_REQUEST_FILE = "PaymentRequest";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.PaymentRequest";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.PaymentRequest";

}
