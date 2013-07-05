/**
  * @(#)BookingHistory.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.history.db;

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
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.model.tour.booking.history.db.*;

/**
 *  BookingHistory - Booking file history.
 */
public class BookingHistory extends Booking
     implements BookingHistoryModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingHistory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingHistory(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_HISTORY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "History";
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
        if (iFieldSeq == 0)
        {
            field = new IntegerField(this, ID, 6, null, null);
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
        //  field = new BookingHistory_BookingDate(this, BOOKING_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new ReferenceField(this, EMPLOYEE_ID, 6, null, null);
        //if (iFieldSeq == 5)
        //  field = new ProfileField(this, PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new StringField(this, PROFILE_CODE, 16, null, null);
        //if (iFieldSeq == 7)
        //  field = new StringField(this, CODE, 20, null, null);
        //if (iFieldSeq == 8)
        //  field = new StringField(this, DESCRIPTION, 50, null, null);
        //if (iFieldSeq == 9)
        //  field = new ReferenceField(this, EMPLOYEE_MOD_ID, 6, null, null);
        //if (iFieldSeq == 10)
        //  field = new DateTimeField(this, MOD_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 11)
        //  field = new BookingStatusField(this, BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 12)
        //  field = new StringField(this, GENERIC_NAME, 30, null, null);
        //if (iFieldSeq == 13)
        //  field = new StringField(this, ADDRESS_LINE_1, 40, null, null);
        //if (iFieldSeq == 14)
        //  field = new StringField(this, ADDRESS_LINE_2, 40, null, null);
        //if (iFieldSeq == 15)
        //  field = new StringField(this, CITY_OR_TOWN, 15, null, null);
        //if (iFieldSeq == 16)
        //  field = new StringField(this, STATE_OR_REGION, 15, null, null);
        //if (iFieldSeq == 17)
        //  field = new StringField(this, POSTAL_CODE, 10, null, null);
        //if (iFieldSeq == 18)
        //  field = new StringField(this, COUNTRY, 15, null, null);
        //if (iFieldSeq == 19)
        //  field = new PhoneField(this, TEL, 24, null, null);
        //if (iFieldSeq == 20)
        //  field = new FaxField(this, FAX, 24, null, null);
        //if (iFieldSeq == 21)
        //  field = new EMailField(this, EMAIL, 40, null, null);
        //if (iFieldSeq == 22)
        //  field = new StringField(this, CONTACT, 25, null, null);
        //if (iFieldSeq == 23)
        //  field = new LanguageField(this, LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 24)
        //  field = new CurrencysField(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 25)
        {
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 26)
        //  field = new RealField(this, EXCHANGE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 27)
        //  field = new ShortField(this, PAX, 3, null, null);
        //if (iFieldSeq == 28)
        //  field = new ShortField(this, FOCS, 2, null, null);
        //if (iFieldSeq == 29)
        //  field = new ShortField(this, SINGLE_PAX, 2, null, null);
        //if (iFieldSeq == 30)
        //  field = new ShortField(this, DOUBLE_PAX, 2, null, null);
        //if (iFieldSeq == 31)
        //  field = new ShortField(this, TRIPLE_PAX, 2, null, null);
        //if (iFieldSeq == 32)
        //  field = new ShortField(this, QUAD_PAX, 2, null, null);
        //if (iFieldSeq == 33)
        //  field = new ShortField(this, CHILDREN, 2, null, null);
        //if (iFieldSeq == 34)
        //  field = new CityField(this, GATEWAY, 3, null, null);
        //if (iFieldSeq == 35)
        //  field = new TourField(this, TOUR_ID, 6, null, null);
        //if (iFieldSeq == 36)
        //  field = new PercentField(this, MARKUP, 5, null, null);
        //if (iFieldSeq == 37)
        //  field = new PercentField(this, STD_COMMISSION, 5, null, new Float(0.10));
        //if (iFieldSeq == 38)
        //  field = new DateField(this, ACCEPT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 39)
        //  field = new DateField(this, DEPOSIT_REC_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 40)
        //  field = new DateField(this, FINAL_PAY_REC_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 41)
        //  field = new BooleanField(this, BOOKED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 42)
        //  field = new BooleanField(this, DEPOSIT_RECEIVED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 43)
        //  field = new BooleanField(this, DEPOSIT_DUE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 44)
        //  field = new BooleanField(this, FINAL_PAYMENT_RECEIVED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 45)
        //  field = new BooleanField(this, FINAL_PAYMENT_DUE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 46)
        //  field = new BooleanField(this, CANCELLED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 47)
        //  field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 48)
        //{
        //  field = new DoubleField(this, DEPOSIT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 49)
        //{
        //  field = new DateField(this, DEPOSIT_DUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 50)
        //{
        //  field = new DateField(this, FINAL_PAYMENT_DUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 51)
        //  field = new DateField(this, NEXT_EVENT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 52)
        //  field = new TourEventField(this, TOUR_EVENT_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(TourEvent.NO_EVENT));
        //if (iFieldSeq == 53)
        //  field = new CurrencyField(this, GROSS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 54)
        //  field = new CurrencyField(this, COMMISSION, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 55)
        //  field = new CurrencyField(this, NET, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 56)
        //  field = new CurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 57)
        {
            field = new PricingStatusField(this, PRICING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PricingStatus.OKAY));
            field.setVirtual(true);
        }
        //if (iFieldSeq == 58)
        //  field = new BooleanField(this, ASK_FOR_ANSWER, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 59)
        {
            field = new BooleanField(this, ALWAYS_RESOLVE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
            field.setVirtual(true);
        }
        //if (iFieldSeq == 60)
        //  field = new PricingTypeField(this, TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 61)
        //  field = new PricingTypeField(this, NON_TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 62)
        //  field = new ShortField(this, NEXT_LINE_SEQUENCE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == 63)
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
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
        this.addListener(new UniqueKeyHandler(this.getField(BookingHistory.HISTORY_DATE)));
    }

}
