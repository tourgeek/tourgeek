/**
 * @(#)BookingHistoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.history.db;

import com.tourapp.model.tour.booking.db.*;

public interface BookingHistoryModel extends BookingModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String BOOKING_DATE = BOOKING_DATE;
    //public static final String EMPLOYEE_ID = EMPLOYEE_ID;
    //public static final String PROFILE_ID = PROFILE_ID;
    //public static final String PROFILE_CODE = PROFILE_CODE;
    //public static final String CODE = CODE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String EMPLOYEE_MOD_ID = EMPLOYEE_MOD_ID;
    //public static final String MOD_DATE = MOD_DATE;
    //public static final String BOOKING_STATUS_ID = BOOKING_STATUS_ID;
    //public static final String GENERIC_NAME = GENERIC_NAME;
    //public static final String ADDRESS_LINE_1 = ADDRESS_LINE_1;
    //public static final String ADDRESS_LINE_2 = ADDRESS_LINE_2;
    //public static final String CITY_OR_TOWN = CITY_OR_TOWN;
    //public static final String STATE_OR_REGION = STATE_OR_REGION;
    //public static final String POSTAL_CODE = POSTAL_CODE;
    //public static final String COUNTRY = COUNTRY;
    //public static final String TEL = TEL;
    //public static final String FAX = FAX;
    //public static final String EMAIL = EMAIL;
    //public static final String CONTACT = CONTACT;
    //public static final String LANGUAGE_ID = LANGUAGE_ID;
    //public static final String CURRENCYS_ID = CURRENCYS_ID;
    //public static final String CURRENCY_CODE = CURRENCY_CODE;
    //public static final String EXCHANGE = EXCHANGE;
    //public static final String PAX = PAX;
    //public static final String FOCS = FOCS;
    //public static final String SINGLE_PAX = SINGLE_PAX;
    //public static final String DOUBLE_PAX = DOUBLE_PAX;
    //public static final String TRIPLE_PAX = TRIPLE_PAX;
    //public static final String QUAD_PAX = QUAD_PAX;
    //public static final String CHILDREN = CHILDREN;
    //public static final String GATEWAY = GATEWAY;
    //public static final String TOUR_ID = TOUR_ID;
    //public static final String MARKUP = MARKUP;
    //public static final String STD_COMMISSION = STD_COMMISSION;
    //public static final String ACCEPT_DATE = ACCEPT_DATE;
    //public static final String DEPOSIT_REC_DATE = DEPOSIT_REC_DATE;
    //public static final String FINAL_PAY_REC_DATE = FINAL_PAY_REC_DATE;
    //public static final String BOOKED = BOOKED;
    //public static final String DEPOSIT_RECEIVED = DEPOSIT_RECEIVED;
    //public static final String DEPOSIT_DUE = DEPOSIT_DUE;
    //public static final String FINAL_PAYMENT_RECEIVED = FINAL_PAYMENT_RECEIVED;
    //public static final String FINAL_PAYMENT_DUE = FINAL_PAYMENT_DUE;
    //public static final String CANCELLED = CANCELLED;
    //public static final String PROPERTIES = PROPERTIES;
    //public static final String DEPOSIT = DEPOSIT;
    //public static final String DEPOSIT_DUE_DATE = DEPOSIT_DUE_DATE;
    //public static final String FINAL_PAYMENT_DUE_DATE = FINAL_PAYMENT_DUE_DATE;
    //public static final String NEXT_EVENT_DATE = NEXT_EVENT_DATE;
    //public static final String TOUR_EVENT_ID = TOUR_EVENT_ID;
    //public static final String GROSS = GROSS;
    //public static final String COMMISSION = COMMISSION;
    //public static final String NET = NET;
    //public static final String BALANCE = BALANCE;
    //public static final String PRICING_STATUS_ID = PRICING_STATUS_ID;
    //public static final String ASK_FOR_ANSWER = ASK_FOR_ANSWER;
    //public static final String ALWAYS_RESOLVE = ALWAYS_RESOLVE;
    //public static final String TOUR_PRICING_TYPE_ID = TOUR_PRICING_TYPE_ID;
    //public static final String NON_TOUR_PRICING_TYPE_ID = NON_TOUR_PRICING_TYPE_ID;
    //public static final String NEXT_LINE_SEQUENCE = NEXT_LINE_SEQUENCE;
    //public static final String CUST_SALE_DATE = CUST_SALE_DATE;
    //public static final String CUST_SALE_AGENT = CUST_SALE_AGENT;
    //public static final String CUST_SALE_CUST_ID = CUST_SALE_CUST_ID;
    //public static final String CUST_SALE_CUST_NO = CUST_SALE_CUST_NO;
    public static final String HISTORY_DATE = "HistoryDate";

    public static final String BOOKING_HISTORY_FILE = "BookingHistory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.history.db.BookingHistory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.history.db.BookingHistory";

}
