/**
 * @(#)BookingDetailQuery.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.operations;

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
import com.tourapp.tour.booking.detail.db.*;

/**
 *  BookingDetailQuery - .
 */
public class BookingDetailQuery extends QueryRecord
{
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public BookingDetailQuery()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingDetailQuery(RecordOwner screen)
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

    public static final String kBookingDetailQueryFile = null;  // Screen field
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
        this.addTable(new BookingDetail(recordOwner));
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
        this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kID).setSelected(true);
        this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kProductTypeID).setSelected(true);
        this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kStatusSummary).setSelected(true);
        this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kDetailDate).setSelected(true);
        this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kDescription).setSelected(true);
        this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kBookingID).setSelected(true);
        this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kVendorID).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kDepartureDate).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kTourStatusID).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kTourHeaderID).setSelected(true);
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
            keyArea.addKeyField(this.getField(BookingDetail.kBookingDetailFile, BookingDetail.kID), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(BookingDetail.kBookingDetailFile), this.getRecord(Booking.kBookingFile), BookingDetail.kBookingID, Booking.kID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(BookingDetail.kBookingDetailFile), this.getRecord(Tour.kTourFile), BookingDetail.kTourID, Tour.kID);
    }

}
