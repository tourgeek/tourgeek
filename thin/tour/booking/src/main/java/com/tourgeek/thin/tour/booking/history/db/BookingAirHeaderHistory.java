/**
  * @(#)BookingAirHeaderHistory.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.booking.history.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.history.db.*;

public class BookingAirHeaderHistory extends BookingAirHeader
    implements BookingAirHeaderHistoryModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_PAX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODULE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODULE_START_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, DESCRIPTION, 60, null, null);
        //field = new FieldInfo(this, PRODUCT_TYPE, 15, null, null);
        field = new FieldInfo(this, REMOTE_REFERENCE_NO, 60, null, null);
        field = new FieldInfo(this, TICKET_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TICKET_NUMBER, 28, null, null);
        field = new FieldInfo(this, AIRLINE_CODE, 2, null, null);
        field = new FieldInfo(this, AIRLINE_IATA, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, AIRLINE_DESC, 16, null, null);
        field = new FieldInfo(this, CONJUNCTION, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ENDORSEMENTS, 29, null, null);
        field = new FieldInfo(this, ORIGIN_DEST, 13, null, null);
        field = new FieldInfo(this, BOOKING_REFERENCE, 13, null, null);
        field = new FieldInfo(this, ISSUE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, PAX_NAME, 29, null, null);
        field = new FieldInfo(this, FORM_OF_PAYMENT, 41, null, null);
        field = new FieldInfo(this, TOUR_CODE, 14, null, null);
        field = new FieldInfo(this, TOTAL_FARE_BASIS, 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, FARE, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, EQUIVALENT, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        field = new FieldInfo(this, TAX_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TAX_1, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TAX_1_DESC, 2, null, null);
        field = new FieldInfo(this, TAX_2, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TAX_2_DESC, 2, null, null);
        field = new FieldInfo(this, TOTAL, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, COMMISSION, 10, null, "      10   ");
        field = new FieldInfo(this, TAX, 10, null, "      8   ");
        field = new FieldInfo(this, COMMISSION_RATE, 5, null, "  10 ");
        field = new FieldInfo(this, AGENT, 10, null, " AGENT");
        field = new FieldInfo(this, INTERNATIONAL, 3, null, "X/");
        field = new FieldInfo(this, COMM_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, COMM_AMOUNT, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TICKET_BY, 1, null, "U");
        field = new FieldInfo(this, NET_FARE, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OVERRIDE_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OVERRIDE_AMOUNT, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, NET_COST, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, VOID, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, VOID_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, EXCH_TICKET, 20, null, null);
        field = new FieldInfo(this, DEP_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, SMOKER, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, CREDIT, 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COMMENT_1, 20, null, null);
        field = new FieldInfo(this, COMMENT_2, 20, null, null);
        field = new FieldInfo(this, COMMENT_3, 20, null, null);
        field = new FieldInfo(this, FREQ_FLIER, 20, null, null);
        field = new FieldInfo(this, FARE_1, 60, null, null);
        field = new FieldInfo(this, FARE_2, 60, null, null);
        field = new FieldInfo(this, FARE_3, 60, null, null);
        field = new FieldInfo(this, HISTORY_DATE, 25, null, null);
        field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea.addKeyField(HISTORY_DATE, Constants.ASCENDING);
    }
    /**
    * This is not an auto-counter record.
    */
    public boolean isAutoSequence()
    {
        return false;
    }

}
