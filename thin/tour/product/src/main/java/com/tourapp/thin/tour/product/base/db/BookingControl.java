/**
 * @(#)BookingControl.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.base.db.*;

public class BookingControl extends FieldList
    implements BookingControlModel
{
    private static final long serialVersionUID = 1L;


    public BookingControl()
    {
        super();
    }
    public BookingControl(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_CONTROL_FILE = "BookingControl";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingControl.BOOKING_CONTROL_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
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
        field = new FieldInfo(this, ID, 4, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, AUTO_BOOKING_CODE, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, AGENCY_COMM, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, DEPOSIT_DAYS, 3, null, new Short((short)10));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ACCEPT_DAYS, 5, null, new Short((short)10));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FINAL_DAYS, 3, null, new Short((short)30));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FINALIZATION_DAYS, 3, null, new Short((short)30));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FINAL_DOC_DAYS, 3, null, new Short((short)15));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, TICKETING_DAYS, 3, null, new Short((short)5));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, PROFILE_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, XLD_BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, XLD_TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, THIN_TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, REMOTE_TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, REMOTE_WAIT_TIME, 10, null, new Integer(10));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAX, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SINGLE_PAX, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DOUBLE_PAX, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, NIGHTS, 2, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MARKUP, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, NON_TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SERIES_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODULE_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, THIN_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
