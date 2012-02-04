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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.history.db.*;

/**
 *  BookingAirHeaderHistory - .
 */
public class BookingAirHeaderHistory extends BookingAirHeader
     implements BookingAirHeaderHistoryModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_AIR_HEADER_HISTORY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if (iFieldSeq == 0)
        {
            field = new IntegerField(this, ID, 8, null, null);
            field.setHidden(true);
        }
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
        //if (iFieldSeq == 3)
        //  field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new BookingPaxField(this, BOOKING_PAX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new TourHeaderField(this, MODULE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new TourHeaderDetailField(this, TOUR_HEADER_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new TourHeaderOptionField(this, TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new DateTimeField(this, MODULE_START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new StringField(this, DESCRIPTION, 60, null, null);
        if (iFieldSeq == 10)
        {
            field = new StringField(this, PRODUCT_TYPE, 15, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 11)
        //  field = new StringField(this, REMOTE_REFERENCE_NO, 60, null, null);
        //if (iFieldSeq == 12)
        //  field = new ReferenceField(this, TICKET_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 13)
        //{
        //  field = new StringField(this, TICKET_NUMBER, 28, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 14)
        //  field = new StringField(this, AIRLINE_CODE, 2, null, null);
        //if (iFieldSeq == 15)
        //  field = new ShortField(this, AIRLINE_IATA, 4, null, null);
        //if (iFieldSeq == 16)
        //  field = new StringField(this, AIRLINE_DESC, 16, null, null);
        //if (iFieldSeq == 17)
        //  field = new ShortField(this, CONJUNCTION, 1, null, null);
        //if (iFieldSeq == 18)
        //{
        //  field = new StringField(this, ENDORSEMENTS, 29, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 19)
        //{
        //  field = new StringField(this, ORIGIN_DEST, 13, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 20)
        //  field = new StringField(this, BOOKING_REFERENCE, 13, null, null);
        //if (iFieldSeq == 21)
        //  field = new BookingAirHeaderHistory_IssueDate(this, ISSUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 22)
        //{
        //  field = new StringField(this, PAX_NAME, 29, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 23)
        //{
        //  field = new StringField(this, FORM_OF_PAYMENT, 41, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 24)
        //{
        //  field = new StringField(this, TOUR_CODE, 14, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 25)
        //{
        //  field = new BooleanField(this, TOTAL_FARE_BASIS, 1, null, new Boolean(false));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 26)
        //{
        //  field = new FloatField(this, FARE, 9, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 27)
        //{
        //  field = new FloatField(this, EQUIVALENT, 8, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 28)
        //{
        //  field = new StringField(this, CURRENCY_CODE, 3, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 29)
        //{
        //  field = new FloatField(this, TAX_PERCENT, 5, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 30)
        //{
        //  field = new FloatField(this, TAX_1, 9, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 31)
        //  field = new StringField(this, TAX_1_DESC, 2, null, null);
        //if (iFieldSeq == 32)
        //{
        //  field = new FloatField(this, TAX_2, 9, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 33)
        //  field = new StringField(this, TAX_2_DESC, 2, null, null);
        //if (iFieldSeq == 34)
        //{
        //  field = new FloatField(this, TOTAL, 9, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 35)
        //  field = new StringField(this, COMMISSION, 10, null, "      10   ");
        //if (iFieldSeq == 36)
        //  field = new StringField(this, TAX, 10, null, "      8   ");
        //if (iFieldSeq == 37)
        //  field = new StringField(this, COMMISSION_RATE, 5, null, "  10 ");
        //if (iFieldSeq == 38)
        //  field = new StringField(this, AGENT, 10, null, " AGENT");
        //if (iFieldSeq == 39)
        //  field = new StringField(this, INTERNATIONAL, 3, null, "X/");
        //if (iFieldSeq == 40)
        //{
        //  field = new FloatField(this, COMM_PERCENT, 5, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 41)
        //  field = new FloatField(this, COMM_AMOUNT, 9, null, null);
        //if (iFieldSeq == 42)
        //  field = new StringField(this, TICKET_BY, 1, null, "U");
        //if (iFieldSeq == 43)
        //{
        //  field = new FloatField(this, NET_FARE, 9, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 44)
        //{
        //  field = new FloatField(this, OVERRIDE_PERCENT, 5, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 45)
        //{
        //  field = new FloatField(this, OVERRIDE_AMOUNT, 9, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 46)
        //{
        //  field = new FloatField(this, NET_COST, 9, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 47)
        //{
        //  field = new BooleanField(this, VOID, 1, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 48)
        //{
        //  field = new DateField(this, VOID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 49)
        //{
        //  field = new StringField(this, EXCH_TICKET, 20, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 50)
        //{
        //  field = new DateField(this, DEP_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 51)
        //{
        //  field = new BooleanField(this, SMOKER, 1, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 52)
        //{
        //  field = new BooleanField(this, CREDIT, 1, null, new Boolean(false));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 53)
        //{
        //  field = new StringField(this, COMMENT_1, 20, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 54)
        //{
        //  field = new StringField(this, COMMENT_2, 20, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 55)
        //{
        //  field = new StringField(this, COMMENT_3, 20, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 56)
        //{
        //  field = new StringField(this, FREQ_FLIER, 20, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 57)
        //{
        //  field = new StringField(this, FARE_1, 60, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 58)
        //{
        //  field = new StringField(this, FARE_2, 60, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 59)
        //{
        //  field = new StringField(this, FARE_3, 60, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 60)
            field = new DateTimeField(this, HISTORY_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
            keyArea.addKeyField(HISTORY_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        // Don't call inherited
        this.addListener(new UniqueKeyHandler(this.getField(BookingAirHeaderHistory.HISTORY_DATE)));
    }

}
