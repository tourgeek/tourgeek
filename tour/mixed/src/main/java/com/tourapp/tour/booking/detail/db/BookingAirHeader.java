/**
 *  @(#)BookingAirHeader.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.booking.entry.detail.air.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.history.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  BookingAirHeader - Booking Ticket Header Detail.
 */
public class BookingAirHeader extends BookingSub
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kBookingID = kBookingID;
    //public static final int kBookingPaxID = kBookingPaxID;
    //public static final int kModuleID = kModuleID;
    //public static final int kProductDetailID = kProductDetailID;
    //public static final int kQuesAnswID = kQuesAnswID;
    public static final int kTicketTrxID = kBookingSubLastField + 1;
    public static final int kTicketNumber = kTicketTrxID + 1;
    public static final int kAirlineCode = kTicketNumber + 1;
    public static final int kAirlineIATA = kAirlineCode + 1;
    public static final int kAirlineDesc = kAirlineIATA + 1;
    public static final int kConjunction = kAirlineDesc + 1;
    public static final int kEndorsements = kConjunction + 1;
    public static final int kOriginDest = kEndorsements + 1;
    public static final int kBookingReference = kOriginDest + 1;
    public static final int kIssueDate = kBookingReference + 1;
    public static final int kPaxName = kIssueDate + 1;
    public static final int kFormOfPayment = kPaxName + 1;
    public static final int kTourCode = kFormOfPayment + 1;
    public static final int kTotalFareBasis = kTourCode + 1;
    public static final int kFare = kTotalFareBasis + 1;
    public static final int kEquivalent = kFare + 1;
    public static final int kCurrencyCode = kEquivalent + 1;
    public static final int kTaxPercent = kCurrencyCode + 1;
    public static final int kTax1 = kTaxPercent + 1;
    public static final int kTax1Desc = kTax1 + 1;
    public static final int kTax2 = kTax1Desc + 1;
    public static final int kTax2Desc = kTax2 + 1;
    public static final int kTotal = kTax2Desc + 1;
    public static final int kCommission = kTotal + 1;
    public static final int kTax = kCommission + 1;
    public static final int kCommissionRate = kTax + 1;
    public static final int kAgent = kCommissionRate + 1;
    public static final int kInternational = kAgent + 1;
    public static final int kCommPercent = kInternational + 1;
    public static final int kCommAmount = kCommPercent + 1;
    public static final int kTicketBy = kCommAmount + 1;
    public static final int kNetFare = kTicketBy + 1;
    public static final int kOverridePercent = kNetFare + 1;
    public static final int kOverrideAmount = kOverridePercent + 1;
    public static final int kNetCost = kOverrideAmount + 1;
    public static final int kVoid = kNetCost + 1;
    public static final int kVoidDate = kVoid + 1;
    public static final int kExchTicket = kVoidDate + 1;
    public static final int kDepDate = kExchTicket + 1;
    public static final int kSmoker = kDepDate + 1;
    public static final int kCredit = kSmoker + 1;
    public static final int kComment1 = kCredit + 1;
    public static final int kComment2 = kComment1 + 1;
    public static final int kComment3 = kComment2 + 1;
    public static final int kFreqFlier = kComment3 + 1;
    public static final int kFare1 = kFreqFlier + 1;
    public static final int kFare2 = kFare1 + 1;
    public static final int kFare3 = kFare2 + 1;
    public static final int kBookingAirHeaderLastField = kFare3;
    public static final int kBookingAirHeaderFields = kFare3 - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDetailAccessKey = kIDKey + 1;
    public static final int kBookingIDKey = kDetailAccessKey + 1;
    public static final int kTicketNumberKey = kBookingIDKey + 1;
    public static final int kDepDateKey = kTicketNumberKey + 1;
    public static final int kIssueDateKey = kDepDateKey + 1;
    public static final int kBookingAirHeaderLastKey = kIssueDateKey;
    public static final int kBookingAirHeaderKeys = kIssueDateKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kBookingAirHeaderFile = "BookingAirHeader";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingAirHeaderFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new BookingAirHeaderScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new BookingAirHeaderGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        //if (iFieldSeq == kBookingID)
        //  field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kBookingPaxID)
        //  field = new BookingPaxField(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kModuleID)
        //  field = new TourHeaderField(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductDetailID)
        //  field = new (this, "ProductDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kQuesAnswID)
        //  field = new (this, "QuesAnswID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTicketTrxID)
            field = new ReferenceField(this, "TicketTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTicketNumber)
        {
            field = new StringField(this, "TicketNumber", 28, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirlineCode)
            field = new StringField(this, "AirlineCode", 2, null, null);
        if (iFieldSeq == kAirlineIATA)
            field = new ShortField(this, "AirlineIATA", 4, null, null);
        if (iFieldSeq == kAirlineDesc)
            field = new StringField(this, "AirlineDesc", 16, null, null);
        if (iFieldSeq == kConjunction)
            field = new ShortField(this, "Conjunction", 1, null, null);
        if (iFieldSeq == kEndorsements)
        {
            field = new StringField(this, "Endorsements", 29, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOriginDest)
        {
            field = new StringField(this, "OriginDest", 13, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBookingReference)
            field = new StringField(this, "BookingReference", 13, null, null);
        if (iFieldSeq == kIssueDate)
            field = new BookingAirHeader_IssueDate(this, "IssueDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPaxName)
        {
            field = new StringField(this, "PaxName", 29, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFormOfPayment)
        {
            field = new StringField(this, "FormOfPayment", 41, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTourCode)
        {
            field = new StringField(this, "TourCode", 14, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTotalFareBasis)
        {
            field = new BooleanField(this, "TotalFareBasis", 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare)
        {
            field = new FloatField(this, "Fare", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEquivalent)
        {
            field = new FloatField(this, "Equivalent", 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCurrencyCode)
        {
            field = new StringField(this, "CurrencyCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTaxPercent)
        {
            field = new FloatField(this, "TaxPercent", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTax1)
        {
            field = new FloatField(this, "Tax1", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTax1Desc)
            field = new StringField(this, "Tax1Desc", 2, null, null);
        if (iFieldSeq == kTax2)
        {
            field = new FloatField(this, "Tax2", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTax2Desc)
            field = new StringField(this, "Tax2Desc", 2, null, null);
        if (iFieldSeq == kTotal)
        {
            field = new FloatField(this, "Total", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCommission)
            field = new StringField(this, "Commission", 10, null, "      10   ");
        if (iFieldSeq == kTax)
            field = new StringField(this, "Tax", 10, null, "      8   ");
        if (iFieldSeq == kCommissionRate)
            field = new StringField(this, "CommissionRate", 5, null, "  10 ");
        if (iFieldSeq == kAgent)
            field = new StringField(this, "Agent", 10, null, " AGENT");
        if (iFieldSeq == kInternational)
            field = new StringField(this, "International", 3, null, "X/");
        if (iFieldSeq == kCommPercent)
        {
            field = new FloatField(this, "CommPercent", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCommAmount)
            field = new FloatField(this, "CommAmount", 9, null, null);
        if (iFieldSeq == kTicketBy)
            field = new StringField(this, "TicketBy", 1, null, "U");
        if (iFieldSeq == kNetFare)
        {
            field = new FloatField(this, "NetFare", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOverridePercent)
        {
            field = new FloatField(this, "OverridePercent", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOverrideAmount)
        {
            field = new FloatField(this, "OverrideAmount", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kNetCost)
        {
            field = new FloatField(this, "NetCost", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVoid)
        {
            field = new BooleanField(this, "Void", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVoidDate)
        {
            field = new DateField(this, "VoidDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kExchTicket)
        {
            field = new StringField(this, "ExchTicket", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDepDate)
        {
            field = new DateField(this, "DepDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSmoker)
        {
            field = new BooleanField(this, "Smoker", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCredit)
        {
            field = new BooleanField(this, "Credit", 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kComment1)
        {
            field = new StringField(this, "Comment1", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kComment2)
        {
            field = new StringField(this, "Comment2", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kComment3)
        {
            field = new StringField(this, "Comment3", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFreqFlier)
        {
            field = new StringField(this, "FreqFlier", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare1)
        {
            field = new StringField(this, "Fare1", 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare2)
        {
            field = new StringField(this, "Fare2", 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare3)
        {
            field = new StringField(this, "Fare3", 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingAirHeaderLastField)
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
        if (iKeyArea == kDetailAccessKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DetailAccess");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingPaxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTourHeaderDetailID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleStartDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBookingIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingID");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTicketNumberKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TicketNumber");
            keyArea.addKeyField(kTicketNumber, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDepDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DepDate");
            keyArea.addKeyField(kDepDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kIssueDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "IssueDate");
            keyArea.addKeyField(kIssueDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingAirHeaderLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingAirHeaderLastKey)
                keyArea = new EmptyKey(this);
        }
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
        HistoryHandler histBehavior = new HistoryHandler(BookingAirHeaderHistory.class.getName(), BookingAirHeaderHistory.kHistoryDate, -1);
        this.addListener(histBehavior);
    }
    /**
     * For this tour detail, move all the detail field to the current booking line item detail.
     */
    public int setDetailProductFields(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        int iErrorCode = super.setDetailProductFields(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
        
        this.getField(BookingAirHeader.kAirlineCode).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kAirlineCode));
        this.getField(BookingAirHeader.kAirlineIATA).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kAirlineIATA));
        this.getField(BookingAirHeader.kAirlineDesc).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kAirlineDesc));
        this.getField(BookingAirHeader.kConjunction).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kConjunction));
        this.getField(BookingAirHeader.kEndorsements).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kEndorsements));
        this.getField(BookingAirHeader.kOriginDest).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kOriginDest));
        this.getField(BookingAirHeader.kBookingReference).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kBookingReference));
        this.getField(BookingAirHeader.kTourCode).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTourCode));
        this.getField(BookingAirHeader.kTotalFareBasis).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTotalFareBasis));
        this.getField(BookingAirHeader.kFare).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kFare));
        this.getField(BookingAirHeader.kEquivalent).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kEquivalent));
        this.getField(BookingAirHeader.kCurrencyCode).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kCurrencyCode));
        this.getField(BookingAirHeader.kTaxPercent).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTaxPercent));
        this.getField(BookingAirHeader.kTax1).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTax1));
        this.getField(BookingAirHeader.kTax1Desc).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTax1Desc));
        this.getField(BookingAirHeader.kTax2).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTax2));
        this.getField(BookingAirHeader.kTax2Desc).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTax2Desc));
        this.getField(BookingAirHeader.kTotal).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTotal));
        this.getField(BookingAirHeader.kCommission).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kCommission));
        this.getField(BookingAirHeader.kTax).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTax));
        this.getField(BookingAirHeader.kCommissionRate).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kCommissionRate));
        this.getField(BookingAirHeader.kAgent).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kAgent));
        this.getField(BookingAirHeader.kInternational).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kInternational));
        this.getField(BookingAirHeader.kCommPercent).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kCommPercent));
        this.getField(BookingAirHeader.kCommAmount).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kCommAmount));
        this.getField(BookingAirHeader.kTicketBy).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kTicketBy));
        this.getField(BookingAirHeader.kNetFare).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kNetFare));
        this.getField(BookingAirHeader.kOverridePercent).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kOverridePercent));
        this.getField(BookingAirHeader.kOverrideAmount).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kOverrideAmount));
        this.getField(BookingAirHeader.kNetCost).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kNetCost));
        this.getField(BookingAirHeader.kVoid).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kVoid));
        this.getField(BookingAirHeader.kVoidDate).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kVoidDate));
        this.getField(BookingAirHeader.kExchTicket).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kExchTicket));
        this.getField(BookingAirHeader.kDepDate).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kDepDate));
        this.getField(BookingAirHeader.kCredit).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kCredit));
        this.getField(BookingAirHeader.kComment1).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kComment1));
        this.getField(BookingAirHeader.kComment2).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kComment2));
        this.getField(BookingAirHeader.kComment3).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kComment3));
        this.getField(BookingAirHeader.kFreqFlier).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kFreqFlier));
        this.getField(BookingAirHeader.kFare1).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kFare1));
        this.getField(BookingAirHeader.kFare2).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kFare2));
        this.getField(BookingAirHeader.kFare3).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderAirHeader.kFare3));
        
        return iErrorCode;
    }

}
