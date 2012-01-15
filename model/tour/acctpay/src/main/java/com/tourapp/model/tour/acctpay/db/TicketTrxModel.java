/**
 * @(#)TicketTrxModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import com.tourapp.model.tour.acctpay.db.*;

public interface TicketTrxModel extends ApTrxModel
{
    public static final String ARC_SUBMITTED = "ArcSubmitted";
    public static final String CANCEL_TICKET = "CancelTicket";
    public static final String NO_TICKET = "NoTicket";
    public static final String OVERRIDE_PAID = "OverridePaid";
    public static final String REQUEST_AUTO_TICKET = "RequestAutoTicket";
    public static final String REQUEST_TICKET = "RequestTicket";
    public static final String TICKET_CANCELLED = "TicketCancelled";
    public static final String TICKETED = "Ticketed";
    public static final String WAITING_FOR_TICKET = "WaitingForTicket";

    public static final String TICKET_TRX_FILE = "ApTrx";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.TicketTrx";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.TicketTrx";

}
