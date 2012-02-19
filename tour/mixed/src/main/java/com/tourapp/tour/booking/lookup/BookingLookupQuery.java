/**
 * @(#)BookingLookupQuery.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.lookup;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.model.tour.booking.lookup.*;

/**
 *  BookingLookupQuery - Booking and Tour Query.
 */
public class BookingLookupQuery extends QueryRecord
     implements BookingLookupQueryModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingLookupQuery()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingLookupQuery(RecordOwner screen)
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

    public static final String BOOKING_LOOKUP_QUERY_FILE = null;    // Screen field
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
        this.addTable(new BookingPax(recordOwner));
        //this.setGridFile(bkBooking, Booking.BOOKING_DATE_KEY);
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Booking.BOOKING_FILE, Booking.ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.CODE).setSelected(true);
        this.getField(BookingPax.BOOKING_PAX_FILE, BookingPax.ID).setSelected(true);
        this.getField(BookingPax.BOOKING_PAX_FILE, BookingPax.NAME_PREFIX).setSelected(true);
        this.getField(BookingPax.BOOKING_PAX_FILE, BookingPax.FIRST_NAME).setSelected(true);
        this.getField(BookingPax.BOOKING_PAX_FILE, BookingPax.MIDDLE_NAME).setSelected(true);
        this.getField(BookingPax.BOOKING_PAX_FILE, BookingPax.SUR_NAME).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.DEPARTURE_DATE).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.DESCRIPTION).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.TOUR_STATUS_ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.GENERIC_NAME).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.BOOKING_DATE).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.MOD_DATE).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.EMPLOYEE_ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.EMPLOYEE_MOD_ID).setSelected(true);
        this.getField(Booking.BOOKING_FILE, Booking.BOOKING_STATUS_ID).setSelected(true);
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
        //x   keyArea.addKeyField(this.getField(Booking.BOOKING_FILE, Booking.ID), DBConstants.ASCENDING);
        // The key must be on the bookingpax file so it will be unique (The booking file is displayed multiple times for each pax)!
            keyArea.addKeyField(this.getField(BookingPax.BOOKING_PAX_FILE, BookingPax.ID), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Booking.BOOKING_FILE), this.getRecord(Tour.TOUR_FILE), Booking.TOUR_ID, Tour.ID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Booking.BOOKING_FILE), this.getRecord(BookingPax.BOOKING_PAX_FILE), Booking.ID, BookingPax.BOOKING_ID);
    }

}
