/**
 * @(#)BookingAirHeaderModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingAirHeaderModel extends BookingSubModel
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
    public static final String TICKET_TRX_ID = "TicketTrxID";
    public static final String TICKET_NUMBER = "TicketNumber";
    public static final String AIRLINE_CODE = "AirlineCode";
    public static final String AIRLINE_IATA = "AirlineIATA";
    public static final String AIRLINE_DESC = "AirlineDesc";
    public static final String CONJUNCTION = "Conjunction";
    public static final String ENDORSEMENTS = "Endorsements";
    public static final String ORIGIN_DEST = "OriginDest";
    public static final String BOOKING_REFERENCE = "BookingReference";
    public static final String ISSUE_DATE = "IssueDate";
    public static final String PAX_NAME = "PaxName";
    public static final String FORM_OF_PAYMENT = "FormOfPayment";
    public static final String TOUR_CODE = "TourCode";
    public static final String TOTAL_FARE_BASIS = "TotalFareBasis";
    public static final String FARE = "Fare";
    public static final String EQUIVALENT = "Equivalent";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String TAX_PERCENT = "TaxPercent";
    public static final String TAX_1 = "Tax1";
    public static final String TAX_1_DESC = "Tax1Desc";
    public static final String TAX_2 = "Tax2";
    public static final String TAX_2_DESC = "Tax2Desc";
    public static final String TOTAL = "Total";
    public static final String COMMISSION = "Commission";
    public static final String TAX = "Tax";
    public static final String COMMISSION_RATE = "CommissionRate";
    public static final String AGENT = "Agent";
    public static final String INTERNATIONAL = "International";
    public static final String COMM_PERCENT = "CommPercent";
    public static final String COMM_AMOUNT = "CommAmount";
    public static final String TICKET_BY = "TicketBy";
    public static final String NET_FARE = "NetFare";
    public static final String OVERRIDE_PERCENT = "OverridePercent";
    public static final String OVERRIDE_AMOUNT = "OverrideAmount";
    public static final String NET_COST = "NetCost";
    public static final String VOID = "Void";
    public static final String VOID_DATE = "VoidDate";
    public static final String EXCH_TICKET = "ExchTicket";
    public static final String DEP_DATE = "DepDate";
    public static final String SMOKER = "Smoker";
    public static final String CREDIT = "Credit";
    public static final String COMMENT_1 = "Comment1";
    public static final String COMMENT_2 = "Comment2";
    public static final String COMMENT_3 = "Comment3";
    public static final String FREQ_FLIER = "FreqFlier";
    public static final String FARE_1 = "Fare1";
    public static final String FARE_2 = "Fare2";
    public static final String FARE_3 = "Fare3";

    public static final String BOOKING_ID_KEY = "BookingID";

    public static final String TICKET_NUMBER_KEY = "TicketNumber";

    public static final String DEP_DATE_KEY = "DepDate";

    public static final String ISSUE_DATE_KEY = "IssueDate";
    public static final String BOOKING_AIR_HEADER_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.air.BookingAirHeaderScreen";
    public static final String BOOKING_AIR_HEADER_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.air.BookingAirHeaderGridScreen";

    public static final String BOOKING_AIR_HEADER_FILE = "BookingAirHeader";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingAirHeader";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingAirHeader";

}
