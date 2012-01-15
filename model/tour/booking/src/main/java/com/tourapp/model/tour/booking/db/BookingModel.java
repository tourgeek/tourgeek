/**
 * @(#)BookingModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.db;

import com.tourapp.model.tour.booking.db.*;

public interface BookingModel extends CustSaleModel
{
    public static final String BOOKING_DATE = "BookingDate";
    public static final String EMPLOYEE_ID = "EmployeeID";
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
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String PAX = "Pax";
    public static final String SINGLE_PAX = "SinglePax";
    public static final String DOUBLE_PAX = "DoublePax";
    public static final String TRIPLE_PAX = "TriplePax";
    public static final String QUAD_PAX = "QuadPax";
    public static final String CHILDREN = "Children";
    public static final String TOUR_ID = "TourID";
    public static final String MARKUP = "Markup";
    public static final String STD_COMMISSION = "StdCommission";
    public static final String GROSS = "Gross";
    public static final String NET = "Net";
    public static final String PRICING_STATUS_ID = "PricingStatusID";
    public static final String TOUR_PRICING_TYPE_ID = "TourPricingTypeID";
    public static final String NON_TOUR_PRICING_TYPE_ID = "NonTourPricingTypeID";
    public static final String BUTTON_LOCATION = "tour/buttons/";

    public static final String BOOKING_FILE = "Booking";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.Booking";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.Booking";

}
