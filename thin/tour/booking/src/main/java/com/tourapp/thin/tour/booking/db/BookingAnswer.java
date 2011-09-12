/**
 * @(#)BookingAnswer.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class BookingAnswer extends FieldList
{

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
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleStartDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Description", 60, null, null);
        //field = new FieldInfo(this, "ProductType", 15, null, null);
        field = new FieldInfo(this, "RemoteReferenceNo", 60, null, null);
        field = new FieldInfo(this, "TourOrOption", 1, null, null);
        field = new FieldInfo(this, "TourOrOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Sequence", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AskForAnswer", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "AlwaysResolve", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DetailOptionExists", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DetailPriceExists", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DetailAirHeaderExists", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "TourDetailExists", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Selected", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DetailAdded", 10, null, new Boolean(false));
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
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "BookingID");
        keyArea.addKeyField("BookingID", Constants.ASCENDING);
        keyArea.addKeyField("BookingPaxID", Constants.ASCENDING);
        keyArea.addKeyField("ModuleID", Constants.ASCENDING);
        keyArea.addKeyField("ModuleStartDate", Constants.ASCENDING);
        keyArea.addKeyField("TourOrOption", Constants.ASCENDING);
        keyArea.addKeyField("TourOrOptionID", Constants.ASCENDING);
        keyArea.addKeyField("Sequence", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ScanOrder");
        keyArea.addKeyField("BookingID", Constants.ASCENDING);
        keyArea.addKeyField("BookingPaxID", Constants.ASCENDING);
        keyArea.addKeyField("ModuleID", Constants.ASCENDING);
        keyArea.addKeyField("ModuleStartDate", Constants.ASCENDING);
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
