/**
 * @(#)BookingLineHistory.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.history.db.*;

/**
 *  BookingLineHistory - Line history file.
 */
public class BookingLineHistory extends BookingLine
     implements BookingLineHistoryModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kHistoryDate = kBookingLineLastField + 1;
    public static final int kBookingLineHistoryLastField = kHistoryDate;
    public static final int kBookingLineHistoryFields = kHistoryDate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBookingLineHistoryLastKey = kIDKey;
    public static final int kBookingLineHistoryKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingLineHistory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingLineHistory(RecordOwner screen)
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

    public static final String kBookingLineHistoryFile = "BookingLineHistory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingLineHistoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Line item history";
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
        return DBConstants.REMOTE | DBConstants.USER_DATA | DBConstants.SERVER_REWRITES | DBConstants.DONT_LOG_TRX;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new IntegerField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kHistoryDate)
            field = new DateTimeField(this, "HistoryDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingLineHistoryLastField)
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
        if (keyArea == null) if (iKeyArea < kBookingLineHistoryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingLineHistoryLastKey)
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
