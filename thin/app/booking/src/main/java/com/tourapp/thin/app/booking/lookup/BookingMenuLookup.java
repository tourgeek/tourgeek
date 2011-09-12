/*
 * BookingMenuLookup.java
 *
 * Created on February 17, 2000, 1:07 AM
 *
 * WARNING WARNING: This is manually created class to mirror tour.booking.remote.BookingMenuLookup
 * ... Remember to change this class if the remote class changes!!!
 
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.lookup;

import java.util.Date;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;

import com.tourapp.thin.tour.booking.db.Booking;
import com.tourapp.thin.tour.booking.db.Tour;

/** 
 *
 * @author  Administrator
 * @version 1.0.0
 */
public class BookingMenuLookup extends FieldList
{
	private static final long serialVersionUID = 1L;

  /** Creates new BookingMenuLookup */
    public BookingMenuLookup()
    {
        super();
    }
    public BookingMenuLookup(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return "BookingMenuLookup";
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
        return Constants.REMOTE;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, Booking.ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, Booking.BOOKING_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, Booking.EMPLOYEE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, Booking.CODE, 20, null, null);
        field = new FieldInfo(this, Booking.DESCRIPTION, 50, null, null);
        field = new FieldInfo(this, Booking.EMPLOYEE_MOD_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, Booking.MOD_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, Booking.BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, Booking.GENERIC_NAME, 30, null, null);
        field = new FieldInfo(this, Tour.DEPARTURE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
    }
}
