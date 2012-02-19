/**
 * @(#)Booking.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.booking.db.*;
import com.tourapp.model.tour.booking.db.*;

public class Booking extends CustSale
    implements BookingModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, 6, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, BOOKING_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, EMPLOYEE_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PROFILE_CODE, 16, null, null);
        field = new FieldInfo(this, CODE, 20, null, null);
        field = new FieldInfo(this, DESCRIPTION, 50, null, null);
        field = new FieldInfo(this, EMPLOYEE_MOD_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MOD_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GENERIC_NAME, 30, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_1, 40, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_2, 40, null, null);
        field = new FieldInfo(this, CITY_OR_TOWN, 15, null, null);
        field = new FieldInfo(this, STATE_OR_REGION, 15, null, null);
        field = new FieldInfo(this, POSTAL_CODE, 10, null, null);
        field = new FieldInfo(this, COUNTRY, 15, null, null);
        field = new FieldInfo(this, TEL, 24, null, null);
        field = new FieldInfo(this, FAX, 24, null, null);
        field = new FieldInfo(this, EMAIL, 40, null, null);
        field = new FieldInfo(this, CONTACT, 25, null, null);
        field = new FieldInfo(this, LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        field = new FieldInfo(this, EXCHANGE, 15, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, PAX, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FOCS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SINGLE_PAX, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DOUBLE_PAX, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, TRIPLE_PAX, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, QUAD_PAX, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, CHILDREN, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, GATEWAY, 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MARKUP, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, STD_COMMISSION, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, ACCEPT_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, DEPOSIT_REC_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, FINAL_PAY_REC_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, BOOKED, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DEPOSIT_RECEIVED, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DEPOSIT_DUE, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, FINAL_PAYMENT_RECEIVED, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, FINAL_PAYMENT_DUE, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, CANCELLED, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, DEPOSIT, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DEPOSIT_DUE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, FINAL_PAYMENT_DUE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, NEXT_EVENT_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, TOUR_EVENT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GROSS, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMISSION, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, NET, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, BALANCE, 18, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, PRICING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PricingStatus.OKAY));
        //field.setDataClass(Integer.class);
        field = new FieldInfo(this, ASK_FOR_ANSWER, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, ALWAYS_RESOLVE, 10, null, new Boolean(false));
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, NON_TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, NEXT_LINE_SEQUENCE, 5, null, new Short((short)1));
        field.setDataClass(Short.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
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
