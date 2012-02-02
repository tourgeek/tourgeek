/**
 * @(#)BookingAirHeaderHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.history.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.history.db.*;

public class BookingAirHeaderHistory extends BookingAirHeader
    implements BookingAirHeaderHistoryModel
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
    //public static final String PRODUCT_DETAIL_ID = PRODUCT_DETAIL_ID;
    //public static final String QUES_ANSW_ID = QUES_ANSW_ID;
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

    public BookingAirHeaderHistory()
    {
        super();
    }
    public BookingAirHeaderHistory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_AIR_HEADER_HISTORY_FILE = "BookingAirHeaderHistory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingAirHeaderHistory.BOOKING_AIR_HEADER_HISTORY_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.REMOTE | Constants.USER_DATA | Constants.SERVER_REWRITES;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleStartDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Description", 60, null, null);
        //field = new FieldInfo(this, "ProductType", 15, null, null);
        field = new FieldInfo(this, "RemoteReferenceNo", 60, null, null);
        field = new FieldInfo(this, "ProductDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "QuesAnswID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "TicketTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TicketNumber", 28, null, null);
        field = new FieldInfo(this, "AirlineCode", 2, null, null);
        field = new FieldInfo(this, "AirlineIATA", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AirlineDesc", 16, null, null);
        field = new FieldInfo(this, "Conjunction", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Endorsements", 29, null, null);
        field = new FieldInfo(this, "OriginDest", 13, null, null);
        field = new FieldInfo(this, "BookingReference", 13, null, null);
        field = new FieldInfo(this, "IssueDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "PaxName", 29, null, null);
        field = new FieldInfo(this, "FormOfPayment", 41, null, null);
        field = new FieldInfo(this, "TourCode", 14, null, null);
        field = new FieldInfo(this, "TotalFareBasis", 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Fare", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Equivalent", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        field = new FieldInfo(this, "TaxPercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Tax1", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Tax1Desc", 2, null, null);
        field = new FieldInfo(this, "Tax2", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Tax2Desc", 2, null, null);
        field = new FieldInfo(this, "Total", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Commission", 10, null, "      10   ");
        field = new FieldInfo(this, "Tax", 10, null, "      8   ");
        field = new FieldInfo(this, "CommissionRate", 5, null, "  10 ");
        field = new FieldInfo(this, "Agent", 10, null, " AGENT");
        field = new FieldInfo(this, "International", 3, null, "X/");
        field = new FieldInfo(this, "CommPercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "CommAmount", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TicketBy", 1, null, "U");
        field = new FieldInfo(this, "NetFare", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OverridePercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OverrideAmount", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "NetCost", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Void", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "VoidDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ExchTicket", 20, null, null);
        field = new FieldInfo(this, "DepDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Smoker", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Credit", 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Comment1", 20, null, null);
        field = new FieldInfo(this, "Comment2", 20, null, null);
        field = new FieldInfo(this, "Comment3", 20, null, null);
        field = new FieldInfo(this, "FreqFlier", 20, null, null);
        field = new FieldInfo(this, "Fare1", 60, null, null);
        field = new FieldInfo(this, "Fare2", 60, null, null);
        field = new FieldInfo(this, "Fare3", 60, null, null);
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
