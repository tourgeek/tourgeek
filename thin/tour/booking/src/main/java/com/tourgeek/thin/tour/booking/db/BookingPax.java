/**
  * @(#)BookingPax.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.booking.db.*;

public class BookingPax extends FieldList
    implements BookingPaxModel
{
    private static final long serialVersionUID = 1L;


    public BookingPax()
    {
        super();
    }
    public BookingPax(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_PAX_FILE = "BookingPax";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingPax.BOOKING_PAX_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, ID, 5, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, BOOKING_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SEQUENCE, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, REMOTE_REFERENCE_NO, 60, null, null);
        field = new FieldInfo(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SHARE_BOOKING_PAX_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMENTS, 9999, null, null);
        field = new FieldInfo(this, NAME_PREFIX, 4, null, null);
        field = new FieldInfo(this, FIRST_NAME, 20, null, null);
        field = new FieldInfo(this, MIDDLE_NAME, 20, null, null);
        field = new FieldInfo(this, SUR_NAME, 20, null, null);
        field = new FieldInfo(this, DATE_OF_BIRTH, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, GENDER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, SMOKER, 1, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BOOKING_ID_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(SEQUENCE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, SUR_NAME_KEY);
        keyArea.addKeyField(SUR_NAME, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, PROFILE_ID_KEY);
        keyArea.addKeyField(PROFILE_ID, Constants.ASCENDING);
    }

}
