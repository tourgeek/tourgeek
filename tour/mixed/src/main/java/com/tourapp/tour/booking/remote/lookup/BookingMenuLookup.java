/**
 * @(#)BookingMenuLookup.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.lookup;

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

    public static final String kBookingMenuLookupFile = "BookingMenuLookup";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingMenuLookupFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        this.getField(Booking.kBookingFile, Booking.kID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kBookingDate).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kEmployeeID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kCode).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kDescription).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kEmployeeModID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kModDate).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kBookingStatusID).setSelected(true);
        this.getField(Booking.kBookingFile, Booking.kGenericName).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kDepartureDate).setSelected(true);
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
            keyArea.addKeyField(this.getField(Booking.kBookingFile, Booking.kID), DBConstants.ASCENDING);
        }
        if (iKeyArea == DBConstants.MAIN_KEY_AREA + 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DescriptionKey");
            keyArea.addKeyField(this.getField(Booking.kBookingFile, Booking.kDescription), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Booking.kBookingFile), this.getRecord(Tour.kTourFile), Booking.kTourID, Tour.kID);
    }

}
