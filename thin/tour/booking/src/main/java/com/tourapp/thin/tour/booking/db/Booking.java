/**
 *  @(#)Booking.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class Booking extends FieldList
{
    public static final String ID = "ID";
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

    public Booking()
    {
        super();
    }
    public Booking(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_FILE = "Booking";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Booking.BOOKING_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA;
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
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "Code");
        keyArea.addKeyField("Code", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProfileID");
        keyArea.addKeyField("ProfileID", Constants.ASCENDING);
        keyArea.addKeyField("BookingDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourID");
        keyArea.addKeyField("TourID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "NextEventDate");
        keyArea.addKeyField("NextEventDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "BookingDate");
        keyArea.addKeyField("BookingDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ModDate");
        keyArea.addKeyField("ModDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "GenericName");
        keyArea.addKeyField("GenericName", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
    }

}
