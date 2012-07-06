/**
  * @(#)BookingAirHeader.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.detail.db;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.history.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingAirHeader - Booking Ticket Header Detail.
 */
public class BookingAirHeader extends BookingSub
     implements BookingAirHeaderModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingAirHeader()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingAirHeader(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_AIR_HEADER_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Ticket";
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
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_AIR_HEADER_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_AIR_HEADER_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_AIR_HEADER_SCREEN_FIELD_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 8, null, null);
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
        if (iFieldSeq == 12)
            field = new ReferenceField(this, TICKET_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
        {
            field = new StringField(this, TICKET_NUMBER, 28, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
            field = new StringField(this, AIRLINE_CODE, 2, null, null);
        if (iFieldSeq == 15)
            field = new ShortField(this, AIRLINE_IATA, 4, null, null);
        if (iFieldSeq == 16)
            field = new StringField(this, AIRLINE_DESC, 16, null, null);
        if (iFieldSeq == 17)
            field = new ShortField(this, CONJUNCTION, 1, null, null);
        if (iFieldSeq == 18)
        {
            field = new StringField(this, ENDORSEMENTS, 29, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 19)
        {
            field = new StringField(this, ORIGIN_DEST, 13, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 20)
            field = new StringField(this, BOOKING_REFERENCE, 13, null, null);
        if (iFieldSeq == 21)
            field = new BookingAirHeader_IssueDate(this, ISSUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
        {
            field = new StringField(this, PAX_NAME, 29, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 23)
        {
            field = new StringField(this, FORM_OF_PAYMENT, 41, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 24)
        {
            field = new StringField(this, TOUR_CODE, 14, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 25)
        {
            field = new BooleanField(this, TOTAL_FARE_BASIS, 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 26)
        {
            field = new FloatField(this, FARE, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 27)
        {
            field = new FloatField(this, EQUIVALENT, 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 28)
        {
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 29)
        {
            field = new FloatField(this, TAX_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 30)
        {
            field = new FloatField(this, TAX_1, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 31)
            field = new StringField(this, TAX_1_DESC, 2, null, null);
        if (iFieldSeq == 32)
        {
            field = new FloatField(this, TAX_2, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 33)
            field = new StringField(this, TAX_2_DESC, 2, null, null);
        if (iFieldSeq == 34)
        {
            field = new FloatField(this, TOTAL, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 35)
            field = new StringField(this, COMMISSION, 10, null, "      10   ");
        if (iFieldSeq == 36)
            field = new StringField(this, TAX, 10, null, "      8   ");
        if (iFieldSeq == 37)
            field = new StringField(this, COMMISSION_RATE, 5, null, "  10 ");
        if (iFieldSeq == 38)
            field = new StringField(this, AGENT, 10, null, " AGENT");
        if (iFieldSeq == 39)
            field = new StringField(this, INTERNATIONAL, 3, null, "X/");
        if (iFieldSeq == 40)
        {
            field = new FloatField(this, COMM_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 41)
            field = new FloatField(this, COMM_AMOUNT, 9, null, null);
        if (iFieldSeq == 42)
            field = new StringField(this, TICKET_BY, 1, null, "U");
        if (iFieldSeq == 43)
        {
            field = new FloatField(this, NET_FARE, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 44)
        {
            field = new FloatField(this, OVERRIDE_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 45)
        {
            field = new FloatField(this, OVERRIDE_AMOUNT, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 46)
        {
            field = new FloatField(this, NET_COST, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 47)
        {
            field = new BooleanField(this, VOID, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 48)
        {
            field = new DateField(this, VOID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 49)
        {
            field = new StringField(this, EXCH_TICKET, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 50)
        {
            field = new DateField(this, DEP_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 51)
        {
            field = new BooleanField(this, SMOKER, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 52)
        {
            field = new BooleanField(this, CREDIT, 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 53)
        {
            field = new StringField(this, COMMENT_1, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 54)
        {
            field = new StringField(this, COMMENT_2, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 55)
        {
            field = new StringField(this, COMMENT_3, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 56)
        {
            field = new StringField(this, FREQ_FLIER, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 57)
        {
            field = new StringField(this, FARE_1, 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 58)
        {
            field = new StringField(this, FARE_2, 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 59)
        {
            field = new StringField(this, FARE_3, 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
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
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DetailAccess");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_HEADER_DETAIL_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_START_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingID");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TicketNumber");
            keyArea.addKeyField(TICKET_NUMBER, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DepDate");
            keyArea.addKeyField(DEP_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "IssueDate");
            keyArea.addKeyField(ISSUE_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        // Add code here
    }
    /**
     * AddSlaveListeners Method.
     */
    public void addSlaveListeners()
    {
        super.addSlaveListeners();
        HistoryHandler histBehavior = new HistoryHandler(BookingAirHeaderHistory.class.getName(), BookingAirHeaderHistory.HISTORY_DATE, null);
        this.addListener(histBehavior);
    }
    /**
     * For this tour detail, move all the detail field to the current booking line item detail.
     */
    public int setDetailProductFields(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        int iErrorCode = super.setDetailProductFields(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
        
        this.getField(BookingAirHeader.AIRLINE_CODE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.AIRLINE_CODE));
        this.getField(BookingAirHeader.AIRLINE_IATA).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.AIRLINE_IATA));
        this.getField(BookingAirHeader.AIRLINE_DESC).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.AIRLINE_DESC));
        this.getField(BookingAirHeader.CONJUNCTION).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.CONJUNCTION));
        this.getField(BookingAirHeader.ENDORSEMENTS).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.ENDORSEMENTS));
        this.getField(BookingAirHeader.ORIGIN_DEST).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.ORIGIN_DEST));
        this.getField(BookingAirHeader.BOOKING_REFERENCE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.BOOKING_REFERENCE));
        this.getField(BookingAirHeader.TOUR_CODE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TOUR_CODE));
        this.getField(BookingAirHeader.TOTAL_FARE_BASIS).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TOTAL_FARE_BASIS));
        this.getField(BookingAirHeader.FARE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.FARE));
        this.getField(BookingAirHeader.EQUIVALENT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.EQUIVALENT));
        this.getField(BookingAirHeader.CURRENCY_CODE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.CURRENCY_CODE));
        this.getField(BookingAirHeader.TAX_PERCENT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TAX_PERCENT));
        this.getField(BookingAirHeader.TAX_1).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TAX_1));
        this.getField(BookingAirHeader.TAX_1_DESC).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TAX_1_DESC));
        this.getField(BookingAirHeader.TAX_2).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TAX_2));
        this.getField(BookingAirHeader.TAX_2_DESC).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TAX_2_DESC));
        this.getField(BookingAirHeader.TOTAL).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TOTAL));
        this.getField(BookingAirHeader.COMMISSION).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.COMMISSION));
        this.getField(BookingAirHeader.TAX).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TAX));
        this.getField(BookingAirHeader.COMMISSION_RATE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.COMMISSION_RATE));
        this.getField(BookingAirHeader.AGENT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.AGENT));
        this.getField(BookingAirHeader.INTERNATIONAL).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.INTERNATIONAL));
        this.getField(BookingAirHeader.COMM_PERCENT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.COMM_PERCENT));
        this.getField(BookingAirHeader.COMM_AMOUNT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.COMM_AMOUNT));
        this.getField(BookingAirHeader.TICKET_BY).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.TICKET_BY));
        this.getField(BookingAirHeader.NET_FARE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.NET_FARE));
        this.getField(BookingAirHeader.OVERRIDE_PERCENT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.OVERRIDE_PERCENT));
        this.getField(BookingAirHeader.OVERRIDE_AMOUNT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.OVERRIDE_AMOUNT));
        this.getField(BookingAirHeader.NET_COST).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.NET_COST));
        this.getField(BookingAirHeader.VOID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.VOID));
        this.getField(BookingAirHeader.VOID_DATE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.VOID_DATE));
        this.getField(BookingAirHeader.EXCH_TICKET).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.EXCH_TICKET));
        this.getField(BookingAirHeader.DEP_DATE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.DEP_DATE));
        this.getField(BookingAirHeader.CREDIT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.CREDIT));
        this.getField(BookingAirHeader.COMMENT_1).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.COMMENT_1));
        this.getField(BookingAirHeader.COMMENT_2).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.COMMENT_2));
        this.getField(BookingAirHeader.COMMENT_3).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.COMMENT_3));
        this.getField(BookingAirHeader.FREQ_FLIER).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.FREQ_FLIER));
        this.getField(BookingAirHeader.FARE_1).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.FARE_1));
        this.getField(BookingAirHeader.FARE_2).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.FARE_2));
        this.getField(BookingAirHeader.FARE_3).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.FARE_3));
        
        return iErrorCode;
    }

}
