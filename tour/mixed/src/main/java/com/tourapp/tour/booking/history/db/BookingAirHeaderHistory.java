/**
 * @(#)BookingAirHeaderHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.history.db;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.history.db.*;

/**
 *  BookingAirHeaderHistory - .
 */
public class BookingAirHeaderHistory extends BookingAirHeader
     implements BookingAirHeaderHistoryModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kHistoryDate = kBookingAirHeaderLastField + 1;
    public static final int kBookingAirHeaderHistoryLastField = kHistoryDate;
    public static final int kBookingAirHeaderHistoryFields = kHistoryDate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBookingAirHeaderHistoryLastKey = kIDKey;
    public static final int kBookingAirHeaderHistoryKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingAirHeaderHistory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingAirHeaderHistory(RecordOwner screen)
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

    public static final String kBookingAirHeaderHistoryFile = "BookingAirHeaderHistory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingAirHeaderHistoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "history";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA | DBConstants.SERVER_REWRITES;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new IntegerField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kHistoryDate)
            field = new DateTimeField(this, "HistoryDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingAirHeaderHistoryLastField)
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
            keyArea.addKeyField(kHistoryDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingAirHeaderHistoryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingAirHeaderHistoryLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        // Don't call inherited
        this.addListener(new UniqueKeyHandler(this.getField(kHistoryDate)));
    }

}
