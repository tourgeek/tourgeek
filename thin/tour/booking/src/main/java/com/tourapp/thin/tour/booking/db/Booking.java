/**
  * @(#)Booking.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.booking.db;

import com.tourapp.model.tour.acctrec.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import org.jbundle.model.db.*;
import com.tourapp.model.tour.product.tour.db.*;
import java.util.*;
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
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, CODE_KEY);
        keyArea.addKeyField(CODE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, PROFILE_ID_KEY);
        keyArea.addKeyField(PROFILE_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TOUR_ID_KEY);
        keyArea.addKeyField(TOUR_ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, NEXT_EVENT_DATE_KEY);
        keyArea.addKeyField(NEXT_EVENT_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BOOKING_DATE_KEY);
        keyArea.addKeyField(BOOKING_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, MOD_DATE_KEY);
        keyArea.addKeyField(MOD_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, GENERIC_NAME_KEY);
        keyArea.addKeyField(GENERIC_NAME, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DESCRIPTION_KEY);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
    }
    /**
     * Add the defaults from the control file when you have a new record.
     */
    public Rec addControlDefaults(Rec recBookingControl, Rec recProfileControl)
    {
        return null; // Empty implementation
    }
    /**
     * Add all the tour detail to this booking.
     * @param recTour
     * @param recTourHeader
     * @param recBookingPax
     * @param recBookingAnswer If null, set up the default booking answers.
     * @param dateStart
     * @return An error code or NORMAL_RETURN if okay.
     */
    public int addTourDetail(TourModel recTour, TourHeaderModel recTourHeader, BookingPaxModel recBookingPax, BookingAnswerModel recBookingAnswer, Date dateStart, Field fldAskForAnswer)
    {
        return -1; // Empty implementation
    }
    /**
     * ChangeTourDetail Method.
     */
    public int changeTourDetail(TourModel recTour, BookingPaxModel recBookingPax, TourHeaderModel recTourHeader, Date dateOriginal, Date dateStart)
    {
        return -1; // Empty implementation
    }
    /**
     * CalcBookingDates Method.
     */
    public int calcBookingDates(Rec recTour, Rec recTourHeader)
    {
        return -1; // Empty implementation
    }
    /**
     * Add the ArTrx and BookingLine detail files if they don't already exist.
     * Also add all the listeners for these files.
     * @param bForceRecount If true, make sure the booking totals are correct, especially if this record is in an indeterminate state.
     */
    public ArTrxModel addArDetail(ArTrxModel recArTrx, BookingLineModel recBookingLine, boolean bForceRecount)
    {
        return null; // Empty implementation
    }
    /**
     * Setup the default booking description and code.
     */
    public String setupDefaultDesc(Rec recTourHeader, Field fldDepDate)
    {
        return null; // Empty implementation
    }

}
