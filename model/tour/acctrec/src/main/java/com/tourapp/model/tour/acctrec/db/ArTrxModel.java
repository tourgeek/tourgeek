/**
 * @(#)ArTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.genled.db.*;

public interface ArTrxModel extends LinkTrxModel
{
    public static final String CR_DR_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.misc.CrDrScreen";
    public static final String CR_DR_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.misc.CrDrGridScreen";
    public static final String PAYMENT = "Payment";
    public static final String CANCELLATION_CHARGE = "CancellationCharge";
    public static final String CREDIT_CARD = "CreditCard";
    public static final String CREDIT_MEMO = "CreditMemo";
    public static final String DEBIT_MEMO = "DebitMemo";
    public static final String ELECTRONIC_PAYMENT = "ElectronicPayment";
    public static final String INVOICE = "Invoice";
    public static final String INVOICE_MODIFICATION = "InvoiceModification";
    public static final String MCO = "Mco";
    public static final String REFUND_SUBMITTED = "RefundSubmitted";
    public static final String REFUND_HELD = "RefundHeld";
    public static final String REFUND_PAY = "RefundPay";
    public static final String REFUND_PAID = "RefundPaid";
    public static final String REFUND_PAID_MANUAL = "RefundPaidManual";
    public static final String REFUND_PENDING = "RefundPending";

    public static final String AR_TRX_FILE = "ArTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.ArTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.ArTrx";

}
