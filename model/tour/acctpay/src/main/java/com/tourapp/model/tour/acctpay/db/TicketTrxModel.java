/**
  * @(#)TicketTrxModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.acctpay.db;

import com.tourapp.model.tour.acctpay.db.*;

public interface TicketTrxModel extends ApTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String CODE = CODE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String AP_TRX_TYPE_ID = AP_TRX_TYPE_ID;
    //public static final String ACTIVE_TRX = ACTIVE_TRX;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String TOUR_ID = TOUR_ID;
    //public static final String PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    //public static final String START_SERVICE_DATE = START_SERVICE_DATE;
    //public static final String END_SERVICE_DATE = END_SERVICE_DATE;
    //public static final String FINALIZATION_DATE = FINALIZATION_DATE;
    //public static final String ORDER_DATE = ORDER_DATE;
    //public static final String ACKNOWLEDGE_DATE = ACKNOWLEDGE_DATE;
    //public static final String ACKNOWLEDGED_ON = ACKNOWLEDGED_ON;
    //public static final String ACKNOWLEDGED_BY = ACKNOWLEDGED_BY;
    //public static final String VENDOR_CONFIRMATION_NO = VENDOR_CONFIRMATION_NO;
    //public static final String VENDOR_CONF_STATUS = VENDOR_CONF_STATUS;
    //public static final String DEPARTURE_ESTIMATE = DEPARTURE_ESTIMATE;
    //public static final String DEPARTURE_EXCHANGE = DEPARTURE_EXCHANGE;
    //public static final String DEPARTURE_ESTIMATE_LOCAL = DEPARTURE_ESTIMATE_LOCAL;
    //public static final String DEPARTURE_DATE = DEPARTURE_DATE;
    //public static final String INVOICE_NO = INVOICE_NO;
    //public static final String INVOICE_DATE = INVOICE_DATE;
    //public static final String INVOICE_AMOUNT = INVOICE_AMOUNT;
    //public static final String INVOICE_LOCAL = INVOICE_LOCAL;
    //public static final String INVOICE_BALANCE = INVOICE_BALANCE;
    //public static final String INVOICE_BALANCE_LOCAL = INVOICE_BALANCE_LOCAL;
    //public static final String AMOUNT_SELECTED = AMOUNT_SELECTED;
    //public static final String DRAFT_VENDOR_ID = DRAFT_VENDOR_ID;
    //public static final String PREPAYMENT_AP_TRX_ID = PREPAYMENT_AP_TRX_ID;
    //public static final String VOUCHER_BASED_DATE = VOUCHER_BASED_DATE;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String ACCOUNT_ID = ACCOUNT_ID;
    //public static final String TICKET_NUMBER = TICKET_NUMBER;
    //public static final String ISSUE_DATE = ISSUE_DATE;
    //public static final String AIRLINE_ID = AIRLINE_ID;
    //public static final String ARC_DATE = ARC_DATE;
    //public static final String ARC_PAY = ARC_PAY;
    //public static final String INTL = INTL;
    //public static final String CREDIT_CARD = CREDIT_CARD;
    //public static final String TOTAL = TOTAL;
    //public static final String FARE = FARE;
    //public static final String COMM_AMOUNT = COMM_AMOUNT;
    //public static final String COMM_PERCENT = COMM_PERCENT;
    //public static final String TAX_AMOUNT = TAX_AMOUNT;
    //public static final String TAX_PERCENT = TAX_PERCENT;
    //public static final String NET_FARE = NET_FARE;
    //public static final String COST_AMOUNT = COST_AMOUNT;
    //public static final String OVERRIDE_PERCENT = OVERRIDE_PERCENT;
    //public static final String OVERRIDE_AMOUNT = OVERRIDE_AMOUNT;
    //public static final String OVERRIDE_PAID_DATE = OVERRIDE_PAID_DATE;
    //public static final String OVERRIDE_PAID = OVERRIDE_PAID;
    //public static final String VOID_DATE = VOID_DATE;
    public static final String ARC_REPORT_POST_CLASS = "com.tourapp.tour.acctpay.air.arc.ArcReportPost";
    public static final String TICKET_TRX_GRID_SCREEN_CLASS = "com.tourapp.tour.acctpay.air.ticket.TicketTrxGridScreen";
    public static final String OVERRIDE_SCREEN_CLASS = "com.tourapp.tour.acctpay.air.oride.OverrideScreen";
    public static final String OVERRIDE_GRID_SCREEN_CLASS = "com.tourapp.tour.acctpay.air.oride.OverrideGridScreen";
    public static final String TICKET_TRX_SCREEN_CLASS = "com.tourapp.tour.acctpay.air.ticket.TicketTrxScreen";
    public static final String ARC_SUBMITTED = "ArcSubmitted";
    public static final String CANCEL_TICKET = "CancelTicket";
    public static final String NO_TICKET = "NoTicket";
    public static final String OVER_RIDE_PAID = "OverridePaid";
    public static final String REQUEST_AUTO_TICKET = "RequestAutoTicket";
    public static final String REQUEST_TICKET = "RequestTicket";
    public static final String TICKET_CANCELLED = "TicketCancelled";
    public static final String TICKETED = "Ticketed";
    public static final String WAITING_FOR_TICKET = "WaitingForTicket";

    public static final String TICKET_TRX_FILE = "ApTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.TicketTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.TicketTrx";

}
