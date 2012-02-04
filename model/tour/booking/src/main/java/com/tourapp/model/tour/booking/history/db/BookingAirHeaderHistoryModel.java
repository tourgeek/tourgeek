/**
 * @(#)BookingAirHeaderHistoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.history.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingAirHeaderHistoryModel extends BookingAirHeaderModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String BOOKING_ID = BOOKING_ID;
    //public static final String BOOKING_PAX_ID = BOOKING_PAX_ID;
    //public static final String MODULE_ID = MODULE_ID;
    //public static final String TOUR_HEADER_DETAIL_ID = TOUR_HEADER_DETAIL_ID;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODULE_START_DATE = MODULE_START_DATE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String PRODUCT_TYPE = PRODUCT_TYPE;
    //public static final String REMOTE_REFERENCE_NO = REMOTE_REFERENCE_NO;
    //public static final String TICKET_TRX_ID = TICKET_TRX_ID;
    //public static final String TICKET_NUMBER = TICKET_NUMBER;
    //public static final String AIRLINE_CODE = AIRLINE_CODE;
    //public static final String AIRLINE_IATA = AIRLINE_IATA;
    //public static final String AIRLINE_DESC = AIRLINE_DESC;
    //public static final String CONJUNCTION = CONJUNCTION;
    //public static final String ENDORSEMENTS = ENDORSEMENTS;
    //public static final String ORIGIN_DEST = ORIGIN_DEST;
    //public static final String BOOKING_REFERENCE = BOOKING_REFERENCE;
    //public static final String ISSUE_DATE = ISSUE_DATE;
    //public static final String PAX_NAME = PAX_NAME;
    //public static final String FORM_OF_PAYMENT = FORM_OF_PAYMENT;
    //public static final String TOUR_CODE = TOUR_CODE;
    //public static final String TOTAL_FARE_BASIS = TOTAL_FARE_BASIS;
    //public static final String FARE = FARE;
    //public static final String EQUIVALENT = EQUIVALENT;
    //public static final String CURRENCY_CODE = CURRENCY_CODE;
    //public static final String TAX_PERCENT = TAX_PERCENT;
    //public static final String TAX_1 = TAX_1;
    //public static final String TAX_1_DESC = TAX_1_DESC;
    //public static final String TAX_2 = TAX_2;
    //public static final String TAX_2_DESC = TAX_2_DESC;
    //public static final String TOTAL = TOTAL;
    //public static final String COMMISSION = COMMISSION;
    //public static final String TAX = TAX;
    //public static final String COMMISSION_RATE = COMMISSION_RATE;
    //public static final String AGENT = AGENT;
    //public static final String INTERNATIONAL = INTERNATIONAL;
    //public static final String COMM_PERCENT = COMM_PERCENT;
    //public static final String COMM_AMOUNT = COMM_AMOUNT;
    //public static final String TICKET_BY = TICKET_BY;
    //public static final String NET_FARE = NET_FARE;
    //public static final String OVERRIDE_PERCENT = OVERRIDE_PERCENT;
    //public static final String OVERRIDE_AMOUNT = OVERRIDE_AMOUNT;
    //public static final String NET_COST = NET_COST;
    //public static final String VOID = VOID;
    //public static final String VOID_DATE = VOID_DATE;
    //public static final String EXCH_TICKET = EXCH_TICKET;
    //public static final String DEP_DATE = DEP_DATE;
    //public static final String SMOKER = SMOKER;
    //public static final String CREDIT = CREDIT;
    //public static final String COMMENT_1 = COMMENT_1;
    //public static final String COMMENT_2 = COMMENT_2;
    //public static final String COMMENT_3 = COMMENT_3;
    //public static final String FREQ_FLIER = FREQ_FLIER;
    //public static final String FARE_1 = FARE_1;
    //public static final String FARE_2 = FARE_2;
    //public static final String FARE_3 = FARE_3;
    public static final String HISTORY_DATE = "HistoryDate";

    public static final String BOOKING_AIR_HEADER_HISTORY_FILE = "BookingAirHeaderHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.history.db.BookingAirHeaderHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.history.db.BookingAirHeaderHistory";

}
