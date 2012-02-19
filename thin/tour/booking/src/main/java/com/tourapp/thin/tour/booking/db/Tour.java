/**
 * @(#)Tour.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.booking.db.*;
import com.tourapp.model.tour.booking.db.*;

public class Tour extends Job
    implements TourModel
{
    private static final long serialVersionUID = 1L;


    public Tour()
    {
        super();
    }
    public Tour(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_FILE = "Tour";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Tour.TOUR_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, DESCRIPTION, 50, null, null);
        field = new FieldInfo(this, CODE, 20, null, null);
        field = new FieldInfo(this, TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DEPARTURE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, TOUR_STATUS_SUMMARY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MANUAL_TOUR_STATUS, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, MIN_TO_OP, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, AIR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AIR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, HOTEL_RATE_ID, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, HOTEL_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LAND_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LAND_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PMC_CUTOFF, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, CAR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CAR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRANSPORTATION_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRANSPORTATION_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CRUISE_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CRUISE_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ITEM_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ITEM_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, FOCS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FILE_NO, 20, null, null);
        field = new FieldInfo(this, FINALIZE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ORDER_COMP_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, CLOSED_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, FINAL_DOC_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, TICKET_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, SP_1_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, SP_2_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, FINALIZED, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, ORDER_COMPONENTS, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, CLOSED, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, FINAL_DOCS, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TICKETS, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SP_1, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SP_2, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SERV_CONF, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DEPARTED, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, CANCELLED, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, NEXT_EVENT_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, TOUR_EVENT_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourHeaderID");
        keyArea.addKeyField("TourHeaderID", Constants.ASCENDING);
        keyArea.addKeyField("DepartureDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "DepartureDate");
        keyArea.addKeyField("DepartureDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "NextEventDate");
        keyArea.addKeyField("NextEventDate", Constants.ASCENDING);
        keyArea.addKeyField("TourEventID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
    }

}
