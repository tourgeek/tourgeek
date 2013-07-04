/**
  * @(#)BookingMenuLookup.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.remote.lookup;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.model.tour.booking.remote.lookup.*;

/**
 *  BookingMenuLookup - Special query for remote section of the thin booking menu.
 */
public class BookingMenuLookup extends QueryRecord
     implements BookingMenuLookupModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingMenuLookup()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingMenuLookup(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_MENU_LOOKUP_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Override this to Setup all the records for this query.
     * Only used for querys and abstract-record queries.
     * Actually adds records not tables, but the records aren't physically
     * added here, the record's tables are added to my table.
     * @param The recordOwner to pass to the records that are added.
     * @see addTable.
     */
    public void addTables(RecordOwner recordOwner)
    {
        this.addTable(new Booking(recordOwner));
        this.addTable(new Tour(recordOwner));
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Booking.BOOKING_FILE, Booking.ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.BOOKING_DATE).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.EMPLOYEE_ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.CODE).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.DESCRIPTION).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.EMPLOYEE_MOD_ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.MOD_DATE).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.BOOKING_STATUS_ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.GENERIC_NAME).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.DEPARTURE_DATE).setSelected(true);
    }
    /**
     * Set all the fields to selected/not selected.
     */
    public void setSelected(boolean bSelect)
    {
        if (bSelect == false)   // Don't allow TableSessionObject to select all the fields
            super.setSelected(bSelect);
    }
    /**
     * Setup this key area.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == DBConstants.MAIN_KEY_AREA)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(this.getField(Booking.BOOKING_FILE, Booking.ID), DBConstants.ASCENDING);
        }
        if (iKeyArea == DBConstants.MAIN_KEY_AREA + 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DescriptionKey");
            keyArea.addKeyField(this.getField(Booking.BOOKING_FILE, Booking.DESCRIPTION), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Booking.BOOKING_FILE), this.getRecord(Tour.TOUR_FILE), Booking.TOUR_ID, Tour.ID);
    }

}
