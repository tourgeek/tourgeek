/**
 * @(#)ApTrx.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import java.util.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.acctpay.screen.broker.*;
import com.tourapp.tour.booking.entry.acctpay.*;
import com.tourapp.tour.acctpay.screen.hist.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.acctpay.db.event.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  ApTrx - Accounts Payable transaction file.
 */
public class ApTrx extends Trx
     implements ApTrxModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxStatusID = kTrxStatusID;
    //public static final int kTrxUserID = kTrxUserID;
    //public static final int kLastChanged = kLastChanged;
    public static final int kCode = kTrxLastField + 1;
    public static final int kDescription = kCode + 1;
    public static final int kApTrxTypeID = kDescription + 1;
    public static final int kActiveTrx = kApTrxTypeID + 1;
    public static final int kVendorID = kActiveTrx + 1;
    public static final int kTourID = kVendorID + 1;
    public static final int kProductTypeID = kTourID + 1;
    public static final int kStartServiceDate = kProductTypeID + 1;
    public static final int kEndServiceDate = kStartServiceDate + 1;
    public static final int kFinalizationDate = kEndServiceDate + 1;
    public static final int kOrderDate = kFinalizationDate + 1;
    public static final int kAcknowledgeDate = kOrderDate + 1;
    public static final int kAcknowledgedOn = kAcknowledgeDate + 1;
    public static final int kAcknowledgedBy = kAcknowledgedOn + 1;
    public static final int kVendorConfirmationNo = kAcknowledgedBy + 1;
    public static final int kVendorConfStatus = kVendorConfirmationNo + 1;
    public static final int kDepartureEstimate = kVendorConfStatus + 1;
    public static final int kDepartureExchange = kDepartureEstimate + 1;
    public static final int kDepartureEstimateLocal = kDepartureExchange + 1;
    public static final int kDepartureDate = kDepartureEstimateLocal + 1;
    public static final int kInvoiceNo = kDepartureDate + 1;
    public static final int kInvoiceDate = kInvoiceNo + 1;
    public static final int kInvoiceAmount = kInvoiceDate + 1;
    public static final int kInvoiceLocal = kInvoiceAmount + 1;
    public static final int kInvoiceBalance = kInvoiceLocal + 1;
    public static final int kInvoiceBalanceLocal = kInvoiceBalance + 1;
    public static final int kAmountSelected = kInvoiceBalanceLocal + 1;
    public static final int kDraftVendorID = kAmountSelected + 1;
    public static final int kPrepaymentApTrxID = kDraftVendorID + 1;
    public static final int kVoucherBasedDate = kPrepaymentApTrxID + 1;
    public static final int kTrxEntry = kVoucherBasedDate + 1;
    public static final int kAccountID = kTrxEntry + 1;
    public static final int kTicketNumber = kAccountID + 1;
    public static final int kIssueDate = kTicketNumber + 1;
    public static final int kAirlineID = kIssueDate + 1;
    public static final int kArcDate = kAirlineID + 1;
    public static final int kArcPay = kArcDate + 1;
    public static final int kIntl = kArcPay + 1;
    public static final int kCreditCard = kIntl + 1;
    public static final int kTotal = kCreditCard + 1;
    public static final int kFare = kTotal + 1;
    public static final int kCommAmount = kFare + 1;
    public static final int kCommPercent = kCommAmount + 1;
    public static final int kTaxAmount = kCommPercent + 1;
    public static final int kTaxPercent = kTaxAmount + 1;
    public static final int kNetFare = kTaxPercent + 1;
    public static final int kCostAmount = kNetFare + 1;
    public static final int kOverridePercent = kCostAmount + 1;
    public static final int kOverrideAmount = kOverridePercent + 1;
    public static final int kOverridePaidDate = kOverrideAmount + 1;
    public static final int kOverridePaid = kOverridePaidDate + 1;
    public static final int kVoidDate = kOverridePaid + 1;
    public static final int kApTrxLastField = kVoidDate;
    public static final int kApTrxFields = kVoidDate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kVendorIDKey = kCodeKey + 1;
    public static final int kTourIDKey = kVendorIDKey + 1;
    public static final int kApTrxLastKey = kTourIDKey;
    public static final int kApTrxKeys = kTourIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int AP_TRX_TYPE = 0;
    public static final int TICKET_TRX_TYPE = ProductType.AIR_ID;
    public static final int BROKER_DIST_SCREEN = ScreenConstants.DISPLAY_MODE | 32768;
    public static final int BROKER_DIST_GRID_SCREEN = ScreenConstants.DISPLAY_MODE | 16384;
    public static final int TOUR_AP_SCREEN = ScreenConstants.DISPLAY_MODE | 4096;
    public static final int VENDOR_AP_SCREEN = ScreenConstants.DISPLAY_MODE | 8192;
    public static final int PAYMENT_HISTORY = ScreenConstants.DISPLAY_MODE | 65536;
    public static final int PAYMENT_DISTRIBUTION = ScreenConstants.DISPLAY_MODE | 262144;
    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DISPLAY_MODE | 131072;
    /**
     * Default constructor.
     */
    public ApTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ApTrx(RecordOwner screen)
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

    public static final String kApTrxFile = "ApTrx";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kApTrxFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "A/P Open Item";
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
        return DBConstants.REMOTE | DBConstants.BASE_TABLE_CLASS | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ApTrx.BROKER_DIST_GRID_SCREEN) == ApTrx.BROKER_DIST_GRID_SCREEN)
            screen = new BrokerDistGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ApTrx.BROKER_DIST_SCREEN) == ApTrx.BROKER_DIST_SCREEN)
            screen = new BrokerDistScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ApTrx.TOUR_AP_SCREEN) == ApTrx.TOUR_AP_SCREEN)
            screen = new TourApTrxGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ApTrx.VENDOR_AP_SCREEN) == ApTrx.VENDOR_AP_SCREEN)
            screen = new VendorApTrxGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ApTrx.PAYMENT_HISTORY) == ApTrx.PAYMENT_HISTORY)
            screen = new PaymentHistoryGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ApTrx.PAYMENT_DISTRIBUTION) == ApTrx.PAYMENT_DISTRIBUTION)
            screen = new PaymentHistoryLinkTrxGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ApTrx.DISTRIBUTION_SCREEN) == ApTrx.DISTRIBUTION_SCREEN)
            screen = new ApTrxDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
            screen = new VoucherDetailGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new ApTrxScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new VendorApTrxGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 28, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 50, null, null);
        if (iFieldSeq == kApTrxTypeID)
        {
            field = new IntegerField(this, "ApTrxTypeID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(ApTrx.AP_TRX_TYPE));
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxUserID)
        //  field = new (this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kActiveTrx)
            field = new BooleanField(this, "ActiveTrx", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourID)
            field = new TourField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductTypeID)
            field = new ReferenceField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kLastChanged)
        //{
        //  field = new RecordChangedField(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kStartServiceDate)
        {
            field = new DateField(this, "StartServiceDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEndServiceDate)
        {
            field = new DateField(this, "EndServiceDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFinalizationDate)
        {
            field = new DateField(this, "FinalizationDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOrderDate)
            field = new DateField(this, "OrderDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAcknowledgeDate)
        {
            field = new DateField(this, "AcknowledgeDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAcknowledgedOn)
        {
            field = new DateField(this, "AcknowledgedOn", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAcknowledgedBy)
            field = new ReferenceField(this, "AcknowledgedBy", 16, null, null);
        if (iFieldSeq == kVendorConfirmationNo)
        {
            field = new StringField(this, "VendorConfirmationNo", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVendorConfStatus)
            field = new StringField(this, "VendorConfStatus", 2, null, null);
        if (iFieldSeq == kDepartureEstimate)
            field = new FullCurrencyField(this, "DepartureEstimate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepartureExchange)
        {
            field = new RealField(this, "DepartureExchange", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDepartureEstimateLocal)
            field = new CurrencyField(this, "DepartureEstimateLocal", 10, null, null);
        if (iFieldSeq == kDepartureDate)
        {
            field = new DateField(this, "DepartureDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kInvoiceNo)
            field = new StringField(this, "InvoiceNo", 28, null, null);
        if (iFieldSeq == kInvoiceDate)
            field = new DateField(this, "InvoiceDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvoiceAmount)
            field = new FullCurrencyField(this, "InvoiceAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvoiceLocal)
            field = new CurrencyField(this, "InvoiceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvoiceBalance)
            field = new FullCurrencyField(this, "InvoiceBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvoiceBalanceLocal)
            field = new CurrencyField(this, "InvoiceBalanceLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountSelected)
            field = new FullCurrencyField(this, "AmountSelected", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDraftVendorID)
            field = new VendorField(this, "DraftVendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPrepaymentApTrxID)
            field = new ApTrxField(this, "PrepaymentApTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVoucherBasedDate)
            field = new DateField(this, "VoucherBasedDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxEntry)
            field = new DateTimeField(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAccountID)
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            if (field == null) if (iFieldSeq < kApTrxLastField)
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
        if (keyArea == null) if (iKeyArea < kApTrxLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kApTrxLastKey)
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
        this.getField(ApTrx.kTrxStatusID).addListener(new UpdateActiveTrxStatus(this.getField(ApTrx.kActiveTrx)));
        this.getField(ApTrx.kDepartureDate).addListener(new GetDepartureDateHandler((TourField)this.getField(ApTrx.kTourID)));
        this.addListener(new NoDeleteModifyHandler(true, false));
        Record recApControl = null;
        RecordOwner recordOwner = Utility.getRecordOwner(this);
        if (recordOwner != null)
            recApControl = recordOwner.getRecord(ApControl.kApControlFile);
        if (recApControl == null)
        {
            recApControl = new ApControl(recordOwner);
            this.addListener(new FreeOnFreeHandler(recApControl));
        }
        if (recApControl.getField(ApControl.kAutoApCode).getState() == true)
            this.addListener(new MoveIDToCodeHandler(null));
        this.addListener(new ApTrxStatusHandler(null));
    }
    /**
     * Get the record type from the field that specifies the record type.
     * (Override this).
     * @return The record type (as an object).
     */
    public BaseField getSharedRecordTypeKey()
    {
        return this.getField(ApTrx.kApTrxTypeID);
    }
    /**
     * Get the shared record that goes with this key.
     * (Always override this).
     * @param objKey The value that specifies the record type.
     * @return The correct (new) record for this type (or null if none).
     */
    public Record createSharedRecord(Object objKey, RecordOwner recordOwner)
    {
        if (objKey instanceof Integer)
        {
            int iApTrxTypeID = ((Integer)objKey).intValue();
            if (iApTrxTypeID == ApTrx.AP_TRX_TYPE)
                return this;
            if (iApTrxTypeID == ApTrx.TICKET_TRX_TYPE)
                return new TicketTrx(recordOwner);
        }
        return null;
    }
    /**
     * Get the product category for this tour.
     */
    public ProductCategory getProductCategory()
    {
        if (this.getField(ApTrx.kTourID).isNull())
            return null;    // No tour
        Tour recTour = (Tour)((ReferenceField)this.getField(ApTrx.kTourID)).getReference();
        if (recTour == null)
            return null;
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
        if (recTourHeader == null)
            return null;
        ProductCategory recProductCategory = (ProductCategory)((ReferenceField)recTourHeader.getField(TourHeader.kProductCatID)).getReference();
        return recProductCategory;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (PaymentHistory.kPaymentHistoryFile.equalsIgnoreCase(strCommand))
            return ApTrx.PAYMENT_HISTORY;
        if (PaymentHistory.PAYMENT_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return ApTrx.PAYMENT_DISTRIBUTION;
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return ApTrx.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }
    /**
     * Add a new voucher for this product detail
     * @param tblBookingDetail Product detail file.
     * @param fldTourID Tour ID (field).
     * @param fldVendorID Vendor ID (field).
     * @param fldVoucherType Unique ID (field).
     * @return An error code.
     */
    public ApTrx addNewApTrx(BaseField fldTourID, Vendor recVendor, int iProductTypeID)
    {
        ApTrx recApTrx = null;
        try   {
            this.getField(ApTrx.kApTrxTypeID).setValue(ApTrx.AP_TRX_TYPE);                
            if (iProductTypeID == ProductType.AIR_ID)
                this.getField(ApTrx.kApTrxTypeID).setValue(ApTrx.TICKET_TRX_TYPE);                
            this.addNew();
            recApTrx = (ApTrx)this.getTable().getCurrentTable().getRecord();
            recApTrx.getField(ApTrx.kTourID).moveFieldToThis(fldTourID);
            recApTrx.getField(ApTrx.kVendorID).moveFieldToThis(recVendor.getField(Vendor.kID));
            recApTrx.getField(ApTrx.kProductTypeID).setValue(iProductTypeID);
        
            TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.kTrxStatusID)).getReferenceRecord();
            int iTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.DEPARTURE_ESTIMATE);
            recApTrx.getField(ApTrx.kTrxStatusID).setValue(iTrxStatusID);
            recApTrx.add();
            Object bookmark = recApTrx.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
            recApTrx.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
        } catch (DBException ex)   {
            ex.printStackTrace();
        }
        return recApTrx;
    }
    /**
     * Update the voucher for this product detail
     * @param tblBookingDetail Product detail file.
     * @param fldTourID Tour ID (field).
     * @param fldVendorID Vendor ID (field).
     * @param fldVoucherType Unique ID (field).
     * @return An error code.
     */
    public int updateThisApTrx(BaseTable tblBookingDetail, Tour recTour, Vendor recVendor, int iProductTypeID)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        this.getField(ApTrx.kDescription).setString(DBConstants.BLANK);
        ((DateField)this.getField(ApTrx.kStartServiceDate)).setData(null);
        ((DateField)this.getField(ApTrx.kEndServiceDate)).setData(null);
        this.getField(ApTrx.kDepartureEstimate).setValue(0.00);
        this.getField(ApTrx.kDepartureEstimateLocal).setValue(0.00);
        
        BookingDetail recBookingDetail = (BookingDetail)tblBookingDetail.getCurrentTable().getRecord();
        int iOldOpenMode = this.getOpenMode();
        try {
            if (this.getListener(UpdateDepEstHandler.class) == null)
                this.addListener(new UpdateDepEstHandler(null));
            this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_READ_ONLY);   // Allow write (I don't know where this file has been)
            this.edit();
        
            while (recBookingDetail != null)
            {
                boolean bProcessThisRecord = true;
                if (!recBookingDetail.getField(BookingDetail.kApTrxID).isNull())
                    if (!recBookingDetail.getField(BookingDetail.kApTrxID).equals(this.getField(ApTrx.kID)))
                        bProcessThisRecord = false;   // If this is already tacked to another ApTrx, skip it.
                if (!this.getField(ApTrx.kTourID).equals(recBookingDetail.getField(BookingDetail.kTourID)))
                    break;
                if (this.getField(ApTrx.kVendorID).compareTo(recBookingDetail.getField(BookingDetail.kVendorID)) != 0)
                    break;
                if (recVendor.getEditMode() == DBConstants.EDIT_CURRENT)
                    if (OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                        if (this.getField(ApTrx.kProductTypeID).compareTo(recBookingDetail.getField(BookingDetail.kProductTypeID)) != 0)
                            break;  // Each A/P Trx contains all detail items in this product type
        
                if (bProcessThisRecord)
                {
                    this.addBookingDetailInfo(recBookingDetail);
            
                    try
                    {
                        recBookingDetail.edit();
                        recBookingDetail.getField(BookingDetail.kApTrxID).moveFieldToThis(this.getField(ApTrx.kID));
                        if (recBookingDetail.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                            recBookingDetail.set();   // Possible that the listeners re-wrote this record already.
                    }
                    catch (DBException ex)
                    {
                        iErrorCode = ex.getErrorCode();
                        return iErrorCode;
                    }
                }
        
                recBookingDetail = (BookingDetail)tblBookingDetail.next();
                if (recVendor.getEditMode() == DBConstants.EDIT_CURRENT)
                    if (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                        break;  // Each A/P Trx contains one detail item
            }
        
            this.set();
        }
        catch (DBException ex) {
            iErrorCode = ex.getErrorCode();
        } finally {
            this.setOpenMode(iOldOpenMode);   // Set it back
        }
        return iErrorCode;
    }
    /**
     * Add the information from this BookingDetail to this ApTrx.
     */
    public void addBookingDetailInfo(BookingDetail recBookingDetail)
    {
        double dProductCost = this.getField(ApTrx.kDepartureEstimate).getValue();
        dProductCost += recBookingDetail.getField(BookingDetail.kTotalCost).getValue();
        this.getField(ApTrx.kDepartureEstimate).setValue(dProductCost);
        double dProductCostLocal = this.getField(ApTrx.kDepartureEstimateLocal).getValue();
        dProductCostLocal += recBookingDetail.getField(BookingDetail.kTotalCostLocal).getValue();
        this.getField(ApTrx.kDepartureEstimateLocal).setValue(dProductCostLocal);
        if (dProductCostLocal != 0)
            this.getField(ApTrx.kDepartureExchange).setValue(dProductCost / dProductCostLocal);
        
        String strServiceDesc = this.getField(ApTrx.kDescription).getString();
        if (strServiceDesc.length() > 0)
            strServiceDesc += ", ";
        strServiceDesc += recBookingDetail.getProductDesc();
        this.getField(ApTrx.kDescription).setString(strServiceDesc);
        
        Date dateStart = ((DateField)this.getField(ApTrx.kStartServiceDate)).getDateTime();
        Date dateEnd = ((DateField)this.getField(ApTrx.kEndServiceDate)).getDateTime();
        Date date = recBookingDetail.getStartDate();
        if ((dateStart == null) || (date.getTime() < dateStart.getTime()))
            dateStart = date;
        date = recBookingDetail.getEndDate();
        if ((dateEnd == null) || (date.getTime() > dateEnd.getTime()))
            dateEnd = date;
        ((DateField)this.getField(ApTrx.kStartServiceDate)).setDate(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        ((DateField)this.getField(ApTrx.kEndServiceDate)).setDate(dateEnd, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        this.getField(ApTrx.kFinalizationDate).setValue(DateTimeField.currentTime());
        
        if (this.getField(ApTrx.kVendorID).isNull())
            if (dProductCost == 0.0)
        {   // Special case - This booking detail is a place holder such as a tour component
            TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)this.getField(ApTrx.kTrxStatusID)).getReferenceRecord();
            int iTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.NO_VOUCHER);
            this.getField(ApTrx.kTrxStatusID).setValue(iTrxStatusID);
        }
    }
    /**
     * Read or Create the ApTrx record for this BookingDetail
     * and link the BookingDetail to it.
     */
    public int linkBookingDetailToApTrx(BookingDetail recBookingDetail)
    {
        if ((recBookingDetail == null)
            || (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
            || (recBookingDetail.getEditMode() == DBConstants.EDIT_NONE)
            || (!recBookingDetail.getField(BookingDetail.kApTrxID).isNull()))
                return DBConstants.ERROR_RETURN;
        // Record is current and aptrx is null.
        Vendor recVendor = (Vendor)((ReferenceField)recBookingDetail.getField(BookingDetail.kVendorID)).getReference();
        if ((recVendor == null)
            || (recVendor.getEditMode() == DBConstants.EDIT_ADD)
            || (recVendor.getEditMode() == DBConstants.EDIT_NONE))
                return DBConstants.ERROR_RETURN;
        
        int iOldOpenMode = this.getOpenMode();
        try {
            if (this.getListener(UpdateDepEstHandler.class) == null)
                this.addListener(new UpdateDepEstHandler(null));
            this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_READ_ONLY);   // Allow write (I don't know where this file has been)
            this.addNew();  // Each A/P Trx contains one detail item
            if (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                this.addNew();  // Each A/P Trx contains one detail item
            else
            { // OperationTypeField.ALL_TOGETHER_CODE or OperationTypeField.LIKE_TOGETHER_CODE
                this.getField(ApTrx.kTourID).moveFieldToThis(recBookingDetail.getField(BookingDetail.kTourID));
                this.getField(ApTrx.kVendorID).moveFieldToThis(recVendor.getField(Vendor.kID));
                if (OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                    this.getField(ApTrx.kProductTypeID).moveFieldToThis(recBookingDetail.getField(BookingDetail.kProductTypeID));
                if ((this.seek(">="))
                    && (this.getField(ApTrx.kTourID).equals(recBookingDetail.getField(BookingDetail.kTourID)))
                        && (this.getField(ApTrx.kVendorID).equals(recVendor.getField(Vendor.kID))))
                {
                    if ((OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                        && (!this.getField(ApTrx.kProductTypeID).equals(recBookingDetail.getField(BookingDetail.kProductTypeID))))
                            this.addNew();
                }
                else
                    this.addNew();
            }
            if (this.getEditMode() == DBConstants.EDIT_ADD)
                this.addNewApTrx(recBookingDetail.getField(BookingDetail.kTourID), recVendor, (int)recBookingDetail.getField(BookingDetail.kProductTypeID).getValue());
        
            recBookingDetail.edit();
            recBookingDetail.getField(BookingDetail.kApTrxID).moveFieldToThis(this.getField(ApTrx.kID));
            recBookingDetail.writeAndRefresh();   // Possible that the listeners re-wrote this record already.
        
            this.edit();
            this.addBookingDetailInfo(recBookingDetail);
            this.set();
            
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            this.setOpenMode(iOldOpenMode);   // Set it back
        }
        return DBConstants.NORMAL_RETURN;
    }

}
