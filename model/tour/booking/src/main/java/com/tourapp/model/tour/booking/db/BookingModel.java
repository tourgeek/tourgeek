/**
 * @(#)BookingModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.db;

import com.tourapp.model.tour.acctrec.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import org.jbundle.model.db.*;
import com.tourapp.model.tour.product.tour.db.*;
import java.util.*;
import com.tourapp.model.tour.booking.db.*;

public interface BookingModel extends CustSaleModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String BOOKING_DATE = CUST_SALE_DATE;
    public static final String EMPLOYEE_ID = CUST_SALE_AGENT_ID;
    public static final String PROFILE_ID = CUST_SALE_CUST_ID;
    public static final String PROFILE_CODE = CUST_SALE_CUST_NO;
    public static final String CODE = "Code";
    public static final String DESCRIPTION = "Description";
    public static final String EMPLOYEE_MOD_ID = "EmployeeModID";
    public static final String MOD_DATE = "ModDate";
    public static final String BOOKING_STATUS_ID = "BookingStatusID";
    public static final String GENERIC_NAME = "GenericName";
    public static final String ADDRESS_LINE_1 = "AddressLine1";
    public static final String ADDRESS_LINE_2 = "AddressLine2";
    public static final String CITY_OR_TOWN = "CityOrTown";
    public static final String STATE_OR_REGION = "StateOrRegion";
    public static final String POSTAL_CODE = "PostalCode";
    public static final String COUNTRY = "Country";
    public static final String TEL = "Tel";
    public static final String FAX = "Fax";
    public static final String EMAIL = "Email";
    public static final String CONTACT = "Contact";
    public static final String LANGUAGE_ID = "LanguageID";
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String EXCHANGE = "Exchange";
    public static final String PAX = "Pax";
    public static final String FOCS = "Focs";
    public static final String SINGLE_PAX = "SinglePax";
    public static final String DOUBLE_PAX = "DoublePax";
    public static final String TRIPLE_PAX = "TriplePax";
    public static final String QUAD_PAX = "QuadPax";
    public static final String CHILDREN = "Children";
    public static final String GATEWAY = "Gateway";
    public static final String TOUR_ID = "TourID";
    public static final String MARKUP = "Markup";
    public static final String STD_COMMISSION = "StdCommission";
    public static final String ACCEPT_DATE = "AcceptDate";
    public static final String DEPOSIT_REC_DATE = "DepositRecDate";
    public static final String FINAL_PAY_REC_DATE = "FinalPayRecDate";
    public static final String BOOKED = "Booked";
    public static final String DEPOSIT_RECEIVED = "DepositReceived";
    public static final String DEPOSIT_DUE = "DepositDue";
    public static final String FINAL_PAYMENT_RECEIVED = "FinalPaymentReceived";
    public static final String FINAL_PAYMENT_DUE = "FinalPaymentDue";
    public static final String CANCELLED = "Cancelled";
    public static final String PROPERTIES = "Properties";
    public static final String DEPOSIT = "Deposit";
    public static final String DEPOSIT_DUE_DATE = "DepositDueDate";
    public static final String FINAL_PAYMENT_DUE_DATE = "FinalPaymentDueDate";
    public static final String NEXT_EVENT_DATE = "NextEventDate";
    public static final String TOUR_EVENT_ID = "TourEventID";
    public static final String GROSS = "Gross";
    public static final String COMMISSION = "Commission";
    public static final String NET = "Net";
    public static final String BALANCE = "Balance";
    public static final String PRICING_STATUS_ID = "PricingStatusID";
    public static final String ASK_FOR_ANSWER = "AskForAnswer";
    public static final String ALWAYS_RESOLVE = "AlwaysResolve";
    public static final String TOUR_PRICING_TYPE_ID = "TourPricingTypeID";
    public static final String NON_TOUR_PRICING_TYPE_ID = "NonTourPricingTypeID";
    public static final String NEXT_LINE_SEQUENCE = "NextLineSequence";

    public static final String CODE_KEY = "Code";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String TOUR_ID_KEY = "TourID";

    public static final String NEXT_EVENT_DATE_KEY = "NextEventDate";

    public static final String BOOKING_DATE_KEY = "BookingDate";

    public static final String MOD_DATE_KEY = "ModDate";

    public static final String GENERIC_NAME_KEY = "GenericName";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String BUTTON_LOCATION = "tour/buttons/";
    public static final String PRODUCT_BOOKING_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.ProductBookingDetailGridScreen";
    public static final String BOOKING_MENU_SCREEN_CLASS = "com.tourapp.tour.booking.entry.base.BookingMenuScreen";
    public static final String BOOKING_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.lookup.BookingGridScreen";

    public static final String BOOKING_FILE = "Booking";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.Booking";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.Booking";
    /**
     * Add the defaults from the control file when you have a new record.
     */
    public Rec addControlDefaults(Rec recBookingControl, Rec recProfileControl);
    /**
     * Add all the tour detail to this booking.
     * @param recTour
     * @param recTourHeader
     * @param recBookingPax
     * @param recBookingAnswer If null, set up the default booking answers.
     * @param dateStart
     * @return An error code or NORMAL_RETURN if okay.
     */
    public int addTourDetail(TourModel recTour, TourHeaderModel recTourHeader, BookingPaxModel recBookingPax, BookingAnswerModel recBookingAnswer, Date dateStart, Field fldAskForAnswer);
    /**
     * ChangeTourDetail Method.
     */
    public int changeTourDetail(TourModel recTour, BookingPaxModel recBookingPax, TourHeaderModel recTourHeader, Date dateOriginal, Date dateStart);
    /**
     * CalcBookingDates Method.
     */
    public int calcBookingDates(Rec recTour, Rec recTourHeader);
    /**
     * Add the ArTrx and BookingLine detail files if they don't already exist.
     * Also add all the listeners for these files.
     * @param bForceRecount If true, make sure the booking totals are correct, especially if this record is in an indeterminate state.
     */
    public ArTrxModel addArDetail(ArTrxModel recArTrx, BookingLineModel recBookingLine, boolean bForceRecount);
    /**
     * Setup the default booking description and code.
     */
    public String setupDefaultDesc(Rec recTourHeader, Field fldDepDate);

}
