/**
 * @(#)TicketTrx.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.db;

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
import com.tourapp.tour.acctpay.air.arc.*;
import com.tourapp.tour.acctpay.air.oride.*;
import com.tourapp.tour.acctpay.air.ticket.*;
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  TicketTrx - Airline Tickets.
 */
public class TicketTrx extends ApTrx
     implements TicketTrxModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kCode = kCode;
    //public static final int kDescription = kDescription;
    //public static final int kApTrxTypeID = kApTrxTypeID;
    //public static final int kTrxStatusID = kTrxStatusID;
    //public static final int kTrxUserID = kTrxUserID;
    //public static final int kActiveTrx = kActiveTrx;
    //public static final int kVendorID = kVendorID;
    //public static final int kTourID = kTourID;
    //public static final int kProductTypeID = kProductTypeID;
    //public static final int kLastChanged = kLastChanged;
    //public static final int kStartServiceDate = kStartServiceDate;
    //public static final int kEndServiceDate = kEndServiceDate;
    //public static final int kFinalizationDate = kFinalizationDate;
    //public static final int kOrderDate = kOrderDate;
    //public static final int kAcknowledgeDate = kAcknowledgeDate;
    //public static final int kAcknowledgedOn = kAcknowledgedOn;
    //public static final int kAcknowledgedBy = kAcknowledgedBy;
    //public static final int kVendorConfirmationNo = kVendorConfirmationNo;
    //public static final int kVendorConfStatus = kVendorConfStatus;
    //public static final int kDepartureEstimate = kDepartureEstimate;
    //public static final int kDepartureExchange = kDepartureExchange;
    //public static final int kDepartureEstimateLocal = kDepartureEstimateLocal;
    //public static final int kDepartureDate = kDepartureDate;
    //public static final int kInvoiceNo = kInvoiceNo;
    //public static final int kInvoiceDate = kInvoiceDate;
    //public static final int kInvoiceAmount = kInvoiceAmount;
    //public static final int kInvoiceLocal = kInvoiceLocal;
    //public static final int kInvoiceBalance = kInvoiceBalance;
    //public static final int kInvoiceBalanceLocal = kInvoiceBalanceLocal;
    //public static final int kAmountSelected = kAmountSelected;
    //public static final int kDraftVendorID = kDraftVendorID;
    //public static final int kPrepaymentApTrxID = kPrepaymentApTrxID;
    //public static final int kVoucherBasedDate = kVoucherBasedDate;
    //public static final int kTrxEntry = kTrxEntry;
    //public static final int kAccountID = kAccountID;
    //public static final int kTicketNumber = kTicketNumber;
    //public static final int kIssueDate = kIssueDate;
    //public static final int kAirlineID = kAirlineID;
    //public static final int kArcDate = kArcDate;
    //public static final int kArcPay = kArcPay;
    //public static final int kIntl = kIntl;
    //public static final int kCreditCard = kCreditCard;
    //public static final int kTotal = kTotal;
    //public static final int kFare = kFare;
    //public static final int kCommAmount = kCommAmount;
    //public static final int kCommPercent = kCommPercent;
    //public static final int kTaxAmount = kTaxAmount;
    //public static final int kTaxPercent = kTaxPercent;
    //public static final int kNetFare = kNetFare;
    //public static final int kCostAmount = kCostAmount;
    //public static final int kOverridePercent = kOverridePercent;
    //public static final int kOverrideAmount = kOverrideAmount;
    //public static final int kOverridePaidDate = kOverridePaidDate;
    //public static final int kOverridePaid = kOverridePaid;
    //public static final int kVoidDate = kVoidDate;
    public static final int kTicketTrxLastField = kApTrxLastField;
    public static final int kTicketTrxFields = kApTrxLastField - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kVendorIDKey = kCodeKey + 1;
    public static final int kTourIDKey = kVendorIDKey + 1;
    public static final int kTicketTrxLastKey = kTourIDKey;
    public static final int kTicketTrxKeys = kTourIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int ARC_REPORT_POST = ScreenConstants.DETAIL_MODE;
    public static final int OVERRIDE_SCREEN = ScreenConstants.DISPLAY_MODE | 128;
    public static final int OVERRIDE_GRID_SCREEN = ScreenConstants.DISPLAY_MODE | 256;
    /**
     * Default constructor.
     */
    public TicketTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TicketTrx(RecordOwner screen)
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

    public static final String kTicketTrxFile = "ApTrx";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTicketTrxFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Ticket transaction";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctpay";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == TicketTrx.ARC_REPORT_POST)
            screen = new ArcReportPost(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == TicketTrx.OVERRIDE_SCREEN)
            screen = new OverrideScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == TicketTrx.OVERRIDE_GRID_SCREEN)
            screen = new OverrideGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new TicketTrxScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new TicketTrxGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
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
        //if (iFieldSeq == kCode)
        //  field = new StringField(this, "Code", 28, null, null);
        //if (iFieldSeq == kDescription)
        //  field = new StringField(this, "Description", 50, null, null);
        //if (iFieldSeq == kApTrxTypeID)
        //{
        //  field = new IntegerField(this, "ApTrxTypeID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(ApTrx.AP_TRX_TYPE));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxUserID)
        //  field = new (this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kActiveTrx)
        //  field = new BooleanField(this, "ActiveTrx", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        //if (iFieldSeq == kVendorID)
        //  field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTourID)
        //  field = new TourField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductTypeID)
        //  field = new ReferenceField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kLastChanged)
        //{
        //  field = new RecordChangedField(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kStartServiceDate)
        //{
        //  field = new DateField(this, "StartServiceDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kEndServiceDate)
        //{
        //  field = new DateField(this, "EndServiceDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kFinalizationDate)
        //{
        //  field = new DateField(this, "FinalizationDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kOrderDate)
        //  field = new DateField(this, "OrderDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAcknowledgeDate)
        //{
        //  field = new DateField(this, "AcknowledgeDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kAcknowledgedOn)
        //{
        //  field = new DateField(this, "AcknowledgedOn", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kAcknowledgedBy)
        //  field = new ReferenceField(this, "AcknowledgedBy", 16, null, null);
        //if (iFieldSeq == kVendorConfirmationNo)
        //{
        //  field = new StringField(this, "VendorConfirmationNo", 20, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kVendorConfStatus)
        //  field = new StringField(this, "VendorConfStatus", 2, null, null);
        //if (iFieldSeq == kDepartureEstimate)
        //  field = new FullCurrencyField(this, "DepartureEstimate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDepartureExchange)
        //{
        //  field = new RealField(this, "DepartureExchange", 10, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kDepartureEstimateLocal)
        //  field = new CurrencyField(this, "DepartureEstimateLocal", 10, null, null);
        if (iFieldSeq == kDepartureDate)
        {
            field = new DateField(this, "DepartureDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kInvoiceNo)
        //  field = new StringField(this, "InvoiceNo", 28, null, null);
        //if (iFieldSeq == kInvoiceDate)
        //  field = new DateField(this, "InvoiceDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInvoiceAmount)
        //  field = new FullCurrencyField(this, "InvoiceAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInvoiceLocal)
        //  field = new CurrencyField(this, "InvoiceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInvoiceBalance)
        //  field = new FullCurrencyField(this, "InvoiceBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInvoiceBalanceLocal)
        //  field = new CurrencyField(this, "InvoiceBalanceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAmountSelected)
        //  field = new FullCurrencyField(this, "AmountSelected", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDraftVendorID)
        //  field = new VendorField(this, "DraftVendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPrepaymentApTrxID)
        //  field = new ApTrxField(this, "PrepaymentApTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kVoucherBasedDate)
        //  field = new DateField(this, "VoucherBasedDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxEntry)
        //  field = new DateTimeField(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAccountID)
        //  field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTicketNumber)
        {
            field = new StringField(this, "TicketNumber", 28, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kIssueDate)
        {
            field = new DateField(this, "IssueDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kArcDate)
            field = new DateField(this, "ArcDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kArcPay)
            field = new DateField(this, "ArcPay", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kIntl)
            field = new StringField(this, "Intl", 1, null, null);
        if (iFieldSeq == kCreditCard)
        {
            field = new BooleanField(this, "CreditCard", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTotal)
            field = new CurrencyField(this, "Total", 10, null, null);
        if (iFieldSeq == kFare)
            field = new CurrencyField(this, "Fare", 10, null, null);
        if (iFieldSeq == kCommAmount)
            field = new CurrencyField(this, "CommAmount", 9, null, null);
        if (iFieldSeq == kCommPercent)
            field = new PercentField(this, "CommPercent", 5, null, null);
        if (iFieldSeq == kTaxAmount)
            field = new CurrencyField(this, "TaxAmount", 9, null, null);
        if (iFieldSeq == kTaxPercent)
        {
            field = new PercentField(this, "TaxPercent", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kNetFare)
            field = new CurrencyField(this, "NetFare", 10, null, null);
        if (iFieldSeq == kCostAmount)
            field = new CurrencyField(this, "CostAmount", 10, null, null);
        if (iFieldSeq == kOverridePercent)
            field = new PercentField(this, "OverridePercent", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOverrideAmount)
            field = new CurrencyField(this, "OverrideAmount", 10, null, null);
        if (iFieldSeq == kOverridePaidDate)
            field = new DateField(this, "OverridePaidDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOverridePaid)
            field = new CurrencyField(this, "OverridePaid", 10, null, null);
        if (iFieldSeq == kVoidDate)
            field = new DateField(this, "VoidDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTicketTrxLastField)
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
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kVendorIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "VendorID");
            keyArea.addKeyField(kVendorID, DBConstants.ASCENDING);
            keyArea.addKeyField(kActiveTrx, DBConstants.ASCENDING);
            keyArea.addKeyField(kStartServiceDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTourIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourID");
            keyArea.addKeyField(kTourID, DBConstants.ASCENDING);
            keyArea.addKeyField(kVendorID, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductTypeID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTicketTrxLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTicketTrxLastKey)
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
        super.addListeners();
        
        this.addListener(new SharedFileHandler(ApTrx.kApTrxTypeID, ApTrx.TICKET_TRX_TYPE));
    }

}
