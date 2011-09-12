/**
 * @(#)TourEvent.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.schedule.db;

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

/**
 *  TourEvent - Tour event descriptions.
 */
public class TourEvent extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kCode = kDescription + 1;
    public static final int kBookingOrTour = kCode + 1;
    public static final int kEventOrDate = kBookingOrTour + 1;
    public static final int kTourEventLastField = kEventOrDate;
    public static final int kTourEventFields = kEventOrDate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kCodeKey = kDescriptionKey + 1;
    public static final int kTourEventLastKey = kCodeKey;
    public static final int kTourEventKeys = kCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int NO_EVENT = 0;
    public static final int BOOKING = 1;
    public static final int BOOKING_STATUS = 2;
    public static final int DEPOSIT_DUE = 4;
    public static final int DEPOSIT_RECEIVED = 5;
    public static final int FINAL_PAY_DUE = 6;
    public static final int FINAL_PAYMENT_RECEIVED = 7;
    public static final int FINALIZATION = 8;
    public static final int TOUR_CLOSED = 9;
    public static final int ORDER_COMPONENTS = 10;
    public static final int SERVICES_CONFIRMED = 11;
    public static final int FINAL_DOCS = 12;
    public static final int TICKETING = 13;
    public static final int SPECIAL_1 = 14;
    public static final int SPECIAL_2 = 15;
    public static final int DEPARTURE = 16;
    public static final int CANCELLATION = 17;
    public static final int TOUR_CANCELLED = 18;
    /**
     * Default constructor.
     */
    public TourEvent()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourEvent(RecordOwner screen)
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

    public static final String kTourEventFile = "TourEvent";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourEventFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Event";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.TABLE | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 1, null, null);
        if (iFieldSeq == kBookingOrTour)
        {
            field = new BookingOrTourField(this, "BookingOrTour", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEventOrDate)
        {
            field = new EventOrDateField(this, "EventOrDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourEventLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTourEventLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourEventLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Get the default key index for grid screens and popup displays.
     * The default key area for grid screens is the first non-unique key that is a string.
     * Override this to supply a different key area.
     * @return The key area to use for screens and popups.
     */
    public int getDefaultScreenKeyArea()
    {
        return TourEvent.kIDKey;
    }

}
