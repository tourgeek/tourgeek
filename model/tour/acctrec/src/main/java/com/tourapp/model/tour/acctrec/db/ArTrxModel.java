/**
 * @(#)ArTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.genled.db.*;

public interface ArTrxModel extends LinkTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String TRX_DATE = TRX_DATE;
    public static final String AMOUNT = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String LINKED_TRX_ID = LINKED_TRX_ID;
    //public static final String LINKED_TRX_DESC_ID = LINKED_TRX_DESC_ID;
    public static final String BOOKING_ID = "BookingID";
    public static final String COMMENTS = "Comments";
    public static final String DEPARTURE_DATE = "DepartureDate";

    public static final String BOOKING_ID_KEY = "BookingID";

    public static final String TRX_STATUS_ID_KEY = "TrxStatusID";
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
