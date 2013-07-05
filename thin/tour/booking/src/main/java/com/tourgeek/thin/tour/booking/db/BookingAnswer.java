/**
  * @(#)BookingAnswer.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.db.*;

public class BookingAnswer extends BookingSub
    implements BookingAnswerModel
{
    private static final long serialVersionUID = 1L;


    public BookingAnswer()
    {
        super();
    }
    public BookingAnswer(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_ANSWER_FILE = "BookingAnswer";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingAnswer.BOOKING_ANSWER_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        field = new FieldInfo(this, TOUR_OR_OPTION, 1, null, null);
        field = new FieldInfo(this, TOUR_OR_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SEQUENCE, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ASK_FOR_ANSWER, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, ALWAYS_RESOLVE, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DETAIL_OPTION_EXISTS, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DETAIL_PRICE_EXISTS, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DETAIL_AIR_HEADER_EXISTS, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TOUR_DETAIL_EXISTS, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SELECTED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DETAIL_ADDED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, BOOKING_ID_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_PAX_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_START_DATE, Constants.ASCENDING);
        keyArea.addKeyField(TOUR_OR_OPTION, Constants.ASCENDING);
        keyArea.addKeyField(TOUR_OR_OPTION_ID, Constants.ASCENDING);
        keyArea.addKeyField(SEQUENCE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, SCAN_ORDER_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_PAX_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_START_DATE, Constants.ASCENDING);
        keyArea.addKeyField(ID, Constants.ASCENDING);
    }

}
