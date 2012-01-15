/**
 * @(#)BookingPax.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.db.*;

public class BookingPax extends FieldList
    implements BookingPaxModel
{

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
        field = new FieldInfo(this, "ID", 5, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BookingID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Sequence", 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "RemoteReferenceNo", 60, null, null);
        field = new FieldInfo(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ShareBookingPaxID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Comments", 9999, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "NamePrefix", 4, null, null);
        field = new FieldInfo(this, "FirstName", 20, null, null);
        field = new FieldInfo(this, "MiddleName", 20, null, null);
        field = new FieldInfo(this, "SurName", 20, null, null);
        field = new FieldInfo(this, "DateOfBirth", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Gender", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "Smoker", 1, null, null);
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "BookingID");
        keyArea.addKeyField("BookingID", Constants.ASCENDING);
        keyArea.addKeyField("Sequence", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "SurName");
        keyArea.addKeyField("SurName", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProfileID");
        keyArea.addKeyField("ProfileID", Constants.ASCENDING);
    }

}
