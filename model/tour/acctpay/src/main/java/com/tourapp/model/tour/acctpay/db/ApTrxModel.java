/**
 * @(#)ApTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import com.tourapp.model.tour.genled.db.*;

public interface ApTrxModel extends TrxModel
{
    public final static String PRODUCT_DETAIL = "Product detail";
    public static final String DEPARTURE_ESTIMATE = "DepEstimate";
    public static final String INVOICE = "Invoice";
    public static final String DEBIT_MEMO = "DebitMemo";
    public static final String CREDIT_MEMO = "CreditMemo";
    public static final String CREDIT_INVOICE_NON_TOUR = "CreditInvNonTour";
    public static final String CREDIT_INVOICE = "CreditInvoice";
    public static final String BROKER_PAYMENT_HEADER = "BrokerPymt";
    public static final String BROKER_PAYMENT = "BrokerPymtPaid";
    public static final String BROKER_PAYMENT_DIST = "BrokerPymtPaidDist";
    public static final String DEPARTURE_EST_MANUAL = "DepEstManual";
    public static final String INVOICE_NON_TOUR = "InvoiceNonTour";
    public static final String INVOICE_PAID = "InvoicePaid";
    public static final String VOUCHER = "Voucher";
    public static final String NO_VOUCHER = "NoVoucher";
    public static final String PAYMENT = "Payment";
    public static final String PREPAYMENT_REQUEST = "Prepayment";
    public static final String PREPAYMENT = "PrepaymentPaid";
    public static final String PREPAYMENT_DIST = "PrepaymentPaidDist";
    public static final String TOUR_ORDER = "TourOrder";
    public static final String XL_TOUR_ORDER = "XLTourOrder";
    public static final String TOUR_ORDER_REQUESTED = "VoucherSend";
    public static final String PAID = "Paid";
    public static final String DIST = "Dist";

    public static final String AP_TRX_FILE = "ApTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.ApTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.ApTrx";

}
