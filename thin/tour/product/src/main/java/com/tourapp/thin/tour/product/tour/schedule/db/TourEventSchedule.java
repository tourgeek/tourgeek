/**
 * @(#)TourEventSchedule.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.schedule.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class TourEventSchedule extends FieldList
{

    public TourEventSchedule()
    {
        super();
    }
    public TourEventSchedule(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_EVENT_SCHEDULE_FILE = "TourEventSchedule";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourEventSchedule.TOUR_EVENT_SCHEDULE_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "ActionProperties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "TourClassID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "TourEventID", 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourClassOnly", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "TourActionType", 1, null, null);
        field = new FieldInfo(this, "ActionTourEventID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ActionBookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ActionMessageProcessInfoID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ActionMessageTransportID", 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "RunProcessIn", Constants.DEFAULT_FIELD_LENGTH, null, "L");
        //field = new FieldInfo(this, "ActionDocumentName", 30, null, null);
        field = new FieldInfo(this, "ActionDocumentText", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourClassID");
        keyArea.addKeyField("TourClassID", Constants.ASCENDING);
        keyArea.addKeyField("TourEventID", Constants.ASCENDING);
    }

}
