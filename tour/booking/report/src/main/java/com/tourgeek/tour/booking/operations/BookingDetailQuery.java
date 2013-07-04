/**
  * @(#)BookingDetailQuery.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.operations;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.operations.*;

/**
 *  BookingDetailQuery - .
 */
public class BookingDetailQuery extends QueryRecord
     implements BookingDetailQueryModel
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

    public static final String BOOKING_DETAIL_QUERY_FILE = null;    // Screen field
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
        this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.ID).setSelected(true);
        this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.PRODUCT_TYPE_ID).setSelected(true);
        this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.STATUS_SUMMARY).setSelected(true);
        this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.DETAIL_DATE).setSelected(true);
        this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.DESCRIPTION).setSelected(true);
        this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.BOOKING_ID).setSelected(true);
        this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.VENDOR_ID).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.DEPARTURE_DATE).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.TOUR_STATUS_ID).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.TOUR_HEADER_ID).setSelected(true);
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
            keyArea.addKeyField(this.getField(BookingDetail.BOOKING_DETAIL_FILE, BookingDetail.ID), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(BookingDetail.BOOKING_DETAIL_FILE), this.getRecord(Booking.BOOKING_FILE), BookingDetail.BOOKING_ID, Booking.ID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(BookingDetail.BOOKING_DETAIL_FILE), this.getRecord(Tour.TOUR_FILE), BookingDetail.TOUR_ID, Tour.ID);
    }

}
