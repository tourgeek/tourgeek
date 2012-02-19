/**
 * @(#)ApTrxModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import com.tourapp.model.tour.genled.db.*;

public interface ApTrxModel extends TrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    public static final String CODE = "Code";
    public static final String DESCRIPTION = "Description";
    public static final String AP_TRX_TYPE_ID = "ApTrxTypeID";
    public static final String ACTIVE_TRX = "ActiveTrx";
    public static final String VENDOR_ID = "VendorID";
    public static final String TOUR_ID = "TourID";
    public static final String PRODUCT_TYPE_ID = "ProductTypeID";
    public static final String START_SERVICE_DATE = "StartServiceDate";
    public static final String END_SERVICE_DATE = "EndServiceDate";
    public static final String FINALIZATION_DATE = "FinalizationDate";
    public static final String ORDER_DATE = "OrderDate";
    public static final String ACKNOWLEDGE_DATE = "AcknowledgeDate";
    public static final String ACKNOWLEDGED_ON = "AcknowledgedOn";
    public static final String ACKNOWLEDGED_BY = "AcknowledgedBy";
    public static final String VENDOR_CONFIRMATION_NO = "VendorConfirmationNo";
    public static final String VENDOR_CONF_STATUS = "VendorConfStatus";
    public static final String DEPARTURE_ESTIMATE = "DepartureEstimate";
    public static final String DEPARTURE_EXCHANGE = "DepartureExchange";
    public static final String DEPARTURE_ESTIMATE_LOCAL = "DepartureEstimateLocal";
    public static final String DEPARTURE_DATE = "DepartureDate";
    public static final String INVOICE_NO = "InvoiceNo";
    public static final String INVOICE_DATE = "InvoiceDate";
    public static final String INVOICE_AMOUNT = "InvoiceAmount";
    public static final String INVOICE_LOCAL = "InvoiceLocal";
    public static final String INVOICE_BALANCE = "InvoiceBalance";
    public static final String INVOICE_BALANCE_LOCAL = "InvoiceBalanceLocal";
    public static final String AMOUNT_SELECTED = "AmountSelected";
    public static final String DRAFT_VENDOR_ID = "DraftVendorID";
    public static final String PREPAYMENT_AP_TRX_ID = "PrepaymentApTrxID";
    public static final String VOUCHER_BASED_DATE = "VoucherBasedDate";
    public static final String TRX_ENTRY = "TrxEntry";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String TICKET_NUMBER = "TicketNumber";
    public static final String ISSUE_DATE = "IssueDate";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String ARC_DATE = "ArcDate";
    public static final String ARC_PAY = "ArcPay";
    public static final String INTL = "Intl";
    public static final String CREDIT_CARD = "CreditCard";
    public static final String TOTAL = "Total";
    public static final String FARE = "Fare";
    public static final String COMM_AMOUNT = "CommAmount";
    public static final String COMM_PERCENT = "CommPercent";
    public static final String TAX_AMOUNT = "TaxAmount";
    public static final String TAX_PERCENT = "TaxPercent";
    public static final String NET_FARE = "NetFare";
    public static final String COST_AMOUNT = "CostAmount";
    public static final String OVERRIDE_PERCENT = "OverridePercent";
    public static final String OVERRIDE_AMOUNT = "OverrideAmount";
    public static final String OVERRIDE_PAID_DATE = "OverridePaidDate";
    public static final String OVERRIDE_PAID = "OverridePaid";
    public static final String VOID_DATE = "VoidDate";

    public static final String CODE_KEY = "Code";

    public static final String VENDOR_ID_KEY = "VendorID";

    public static final String TOUR_ID_KEY = "TourID";
    public final static String PRODUCT_DETAIL = "Product detail";
    public static final String AP_TRX_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.trx.ApTrxScreen";
    public static final String _CLASS = "com.tourapp.tour.";
    public static final String DEP_ESTIMATE = "DepEstimate";
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
