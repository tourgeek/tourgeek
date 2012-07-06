/**
  * @(#)TourEventSchedule.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.product.tour.schedule.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.*;
import com.tourapp.model.tour.product.tour.schedule.db.*;

public class TourEventSchedule extends PropertiesRecord
    implements TourEventScheduleModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, ACTION_PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, TOUR_CLASS_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, TOUR_EVENT_ID, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_CLASS_ONLY, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TOUR_ACTION_TYPE, 1, null, null);
        field = new FieldInfo(this, ACTION_TOUR_EVENT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACTION_BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACTION_MESSAGE_PROCESS_INFO_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACTION_MESSAGE_TRANSPORT_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, RUN_PROCESS_IN, Constants.DEFAULT_FIELD_LENGTH, null, "L");
        //field = new FieldInfo(this, ACTION_DOCUMENT_NAME, 30, null, null);
        field = new FieldInfo(this, ACTION_DOCUMENT_TEXT, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourClassID");
        keyArea.addKeyField("TourClassID", Constants.ASCENDING);
        keyArea.addKeyField("TourEventID", Constants.ASCENDING);
    }

}
