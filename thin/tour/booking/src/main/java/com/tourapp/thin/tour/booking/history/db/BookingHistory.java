/**
 * @(#)BookingHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.history.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.booking.db.*;
import com.tourapp.model.tour.booking.history.db.*;

public class BookingHistory extends Booking
    implements BookingHistoryModel
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

    public BookingHistory()
    {
        super();
    }
    public BookingHistory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_HISTORY_FILE = "BookingHistory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingHistory.BOOKING_HISTORY_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "history";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA | Constants.SERVER_REWRITES | Constants.DONT_LOG_TRX;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 6, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BookingDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "EmployeeID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileCode", 16, null, null);
        field = new FieldInfo(this, "Code", 20, null, null);
        field = new FieldInfo(this, "Description", 50, null, null);
        field = new FieldInfo(this, "EmployeeModID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "BookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "GenericName", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Tel", 24, null, null);
        field = new FieldInfo(this, "Fax", 24, null, null);
        field = new FieldInfo(this, "Email", 40, null, null);
        field = new FieldInfo(this, "Contact", 25, null, null);
        field = new FieldInfo(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        field = new FieldInfo(this, "Exchange", 15, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, "Pax", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Focs", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "SinglePax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "DoublePax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "TriplePax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "QuadPax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Children", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Gateway", 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Markup", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "StdCommission", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "AcceptDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "DepositRecDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "FinalPayRecDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Booked", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DepositReceived", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DepositDue", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "FinalPaymentReceived", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "FinalPaymentDue", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Cancelled", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Deposit", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DepositDueDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "FinalPaymentDueDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "NextEventDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "TourEventID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Gross", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Commission", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Net", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Balance", 18, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, "PricingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PricingStatus.OKAY));
        //field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AskForAnswer", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, "AlwaysResolve", 10, null, new Boolean(false));
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "TourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "NonTourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "NextLineSequence", 5, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "CustSaleDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "CustSaleAgent", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CustSaleCustID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CustSaleCustNo", 16, null, null);
        field = new FieldInfo(this, "HistoryDate", 25, null, null);
        field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea.addKeyField("HistoryDate", Constants.ASCENDING);
    }
    /**
    * This is not an auto-counter record.
    */
    public boolean isAutoSequence()
    {
        return false;
    }

}
