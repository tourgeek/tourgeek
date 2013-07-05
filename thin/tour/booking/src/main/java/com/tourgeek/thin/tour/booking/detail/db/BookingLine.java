/**
  * @(#)BookingLine.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.booking.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;

public class BookingLine extends BookingSub
    implements BookingLineModel
{
    private static final long serialVersionUID = 1L;


    public BookingLine()
    {
        super();
    }
    public BookingLine(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_LINE_FILE = "BookingLine";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingLine.BOOKING_LINE_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, SEQUENCE, 4, null, new Short((short)9999));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, PRICE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, QUANTITY, 3, null, null);
        field.setDataClass(Short.class);
        //field = new FieldInfo(this, GROSS, 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMISSIONABLE, 1, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COMMISSION_RATE, 5, null, null);
        field.setDataClass(Float.class);
        //field = new FieldInfo(this, COMMISSION, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, NET, 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, PRICING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAY_AT, 1, null, null);
        field = new FieldInfo(this, PAX_GROUPS, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BOOKING_ID_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(SEQUENCE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DETAIL_ACCESS_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_PAX_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_ID, Constants.ASCENDING);
        keyArea.addKeyField(TOUR_HEADER_DETAIL_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_START_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BOOKING_DETAIL_ID_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_PAX_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_DETAIL_ID, Constants.ASCENDING);
        keyArea.addKeyField(PAX_CATEGORY_ID, Constants.ASCENDING);
    }

}
