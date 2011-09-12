/**
 * @(#)BookingLookupQuery.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.booking.db.*;

/**
 *  BookingLookupQuery - Booking and Tour Query.
 */
public class BookingLookupQuery extends QueryRecord
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

    public static final String kBookingLookupQueryFile = null;  // Screen field
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
        //this.setGridFile(bkBooking, Booking.kBookingDateKey);
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Booking.kBookingFile, Booking.kID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kCode).setSelected(true);
        this.getField(BookingPax.kBookingPaxFile, BookingPax.kID).setSelected(true);
        this.getField(BookingPax.kBookingPaxFile, BookingPax.kNamePrefix).setSelected(true);
        this.getField(BookingPax.kBookingPaxFile, BookingPax.kFirstName).setSelected(true);
        this.getField(BookingPax.kBookingPaxFile, BookingPax.kMiddleName).setSelected(true);
        this.getField(BookingPax.kBookingPaxFile, BookingPax.kSurName).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kDepartureDate).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kDescription).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kTourStatusID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kGenericName).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kBookingDate).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kModDate).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kEmployeeID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kEmployeeModID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kBookingStatusID).setSelected(true);
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
        //x   keyArea.addKeyField(this.getField(Booking.kBookingFile, Booking.kID), DBConstants.ASCENDING);
        // The key must be on the bookingpax file so it will be unique (The booking file is displayed multiple times for each pax)!
            keyArea.addKeyField(this.getField(BookingPax.kBookingPaxFile, BookingPax.kID), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Booking.kBookingFile), this.getRecord(Tour.kTourFile), Booking.kTourID, Tour.kID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Booking.kBookingFile), this.getRecord(BookingPax.kBookingPaxFile),
        Booking.kID, BookingPax.kBookingID);
    }

}
