/**
 * @(#)Booking.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.history.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.base.db.*;

/**
 *  Booking - Booking entry.
 */
public class Booking extends CustSale
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kBookingDate = kCustSaleDate;
    public static final int kEmployeeID = kCustSaleAgent;
    public static final int kProfileID = kCustSaleCustID;
    public static final int kProfileCode = kCustSaleCustNo;
    public static final int kCode = kCustSaleLastField + 1;
    public static final int kDescription = kCode + 1;
    public static final int kEmployeeModID = kDescription + 1;
    public static final int kModDate = kEmployeeModID + 1;
    public static final int kBookingStatusID = kModDate + 1;
    public static final int kGenericName = kBookingStatusID + 1;
    public static final int kAddressLine1 = kGenericName + 1;
    public static final int kAddressLine2 = kAddressLine1 + 1;
    public static final int kCityOrTown = kAddressLine2 + 1;
    public static final int kStateOrRegion = kCityOrTown + 1;
    public static final int kPostalCode = kStateOrRegion + 1;
    public static final int kCountry = kPostalCode + 1;
    public static final int kTel = kCountry + 1;
    public static final int kFax = kTel + 1;
    public static final int kEmail = kFax + 1;
    public static final int kContact = kEmail + 1;
    public static final int kLanguageID = kContact + 1;
    public static final int kCurrencysID = kLanguageID + 1;
    public static final int kCurrencyCode = kCurrencysID + 1;
    public static final int kExchange = kCurrencyCode + 1;
    public static final int kPax = kExchange + 1;
    public static final int kFocs = kPax + 1;
    public static final int kSinglePax = kFocs + 1;
    public static final int kDoublePax = kSinglePax + 1;
    public static final int kTriplePax = kDoublePax + 1;
    public static final int kQuadPax = kTriplePax + 1;
    public static final int kChildren = kQuadPax + 1;
    public static final int kGateway = kChildren + 1;
    public static final int kTourID = kGateway + 1;
    public static final int kMarkup = kTourID + 1;
    public static final int kStdCommission = kMarkup + 1;
    public static final int kAcceptDate = kStdCommission + 1;
    public static final int kDepositRecDate = kAcceptDate + 1;
    public static final int kFinalPayRecDate = kDepositRecDate + 1;
    public static final int kBooked = kFinalPayRecDate + 1;
    public static final int kDepositReceived = kBooked + 1;
    public static final int kDepositDue = kDepositReceived + 1;
    public static final int kFinalPaymentReceived = kDepositDue + 1;
    public static final int kFinalPaymentDue = kFinalPaymentReceived + 1;
    public static final int kCancelled = kFinalPaymentDue + 1;
    public static final int kProperties = kCancelled + 1;
    public static final int kDeposit = kProperties + 1;
    public static final int kDepositDueDate = kDeposit + 1;
    public static final int kFinalPaymentDueDate = kDepositDueDate + 1;
    public static final int kNextEventDate = kFinalPaymentDueDate + 1;
    public static final int kTourEventID = kNextEventDate + 1;
    public static final int kGross = kTourEventID + 1;
    public static final int kCommission = kGross + 1;
    public static final int kNet = kCommission + 1;
    public static final int kBalance = kNet + 1;
    public static final int kPricingStatusID = kBalance + 1;
    public static final int kAskForAnswer = kPricingStatusID + 1;
    public static final int kAlwaysResolve = kAskForAnswer + 1;
    public static final int kTourPricingTypeID = kAlwaysResolve + 1;
    public static final int kNonTourPricingTypeID = kTourPricingTypeID + 1;
    public static final int kNextLineSequence = kNonTourPricingTypeID + 1;
    public static final int kBookingLastField = kNextLineSequence;
    public static final int kBookingFields = kNextLineSequence - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kProfileIDKey = kCodeKey + 1;
    public static final int kTourIDKey = kProfileIDKey + 1;
    public static final int kNextEventDateKey = kTourIDKey + 1;
    public static final int kBookingDateKey = kNextEventDateKey + 1;
    public static final int kModDateKey = kBookingDateKey + 1;
    public static final int kGenericNameKey = kModDateKey + 1;
    public static final int kDescriptionKey = kGenericNameKey + 1;
    public static final int kBookingLastKey = kDescriptionKey;
    public static final int kBookingKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String BUTTON_LOCATION = "tour/buttons/";
    protected Vector<Integer> m_rdwPaxMods = null;
    protected BookingDetail m_recBookingDetailModule = null;
    /**
     * Default constructor.
     */
    public Booking()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Booking(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_rdwPaxMods = null;
        m_recBookingDetailModule = null;
        super.init(screen);
    }

    public static final String kBookingFile = "Booking";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Booking";
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
            screen = new BookingMenuScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new BookingGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
            field = new CounterField(this, "ID", 6, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 20, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 50, null, null);
        if (iFieldSeq == kBookingDate)
            field = new Booking_BookingDate(this, "BookingDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEmployeeID)
            field = new ReferenceField(this, "EmployeeID", 6, null, null);
        if (iFieldSeq == kEmployeeModID)
            field = new ReferenceField(this, "EmployeeModID", 6, null, null);
        if (iFieldSeq == kModDate)
            field = new DateTimeField(this, "ModDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBookingStatusID)
            field = new BookingStatusField(this, "BookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProfileID)
            field = new ProfileField(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProfileCode)
            field = new StringField(this, "ProfileCode", 16, null, null);
        if (iFieldSeq == kGenericName)
            field = new StringField(this, "GenericName", 30, null, null);
        if (iFieldSeq == kAddressLine1)
            field = new StringField(this, "AddressLine1", 40, null, null);
        if (iFieldSeq == kAddressLine2)
            field = new StringField(this, "AddressLine2", 40, null, null);
        if (iFieldSeq == kCityOrTown)
            field = new StringField(this, "CityOrTown", 15, null, null);
        if (iFieldSeq == kStateOrRegion)
            field = new StringField(this, "StateOrRegion", 15, null, null);
        if (iFieldSeq == kPostalCode)
            field = new StringField(this, "PostalCode", 10, null, null);
        if (iFieldSeq == kCountry)
            field = new StringField(this, "Country", 15, null, null);
        if (iFieldSeq == kTel)
            field = new PhoneField(this, "Tel", 24, null, null);
        if (iFieldSeq == kFax)
            field = new FaxField(this, "Fax", 24, null, null);
        if (iFieldSeq == kEmail)
            field = new EMailField(this, "Email", 40, null, null);
        if (iFieldSeq == kContact)
            field = new StringField(this, "Contact", 25, null, null);
        if (iFieldSeq == kLanguageID)
            field = new LanguageField(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrencysID)
            field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrencyCode)
        {
            field = new StringField(this, "CurrencyCode", 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kExchange)
            field = new RealField(this, "Exchange", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPax)
            field = new ShortField(this, "Pax", 3, null, null);
        if (iFieldSeq == kFocs)
            field = new ShortField(this, "Focs", 2, null, null);
        if (iFieldSeq == kSinglePax)
            field = new ShortField(this, "SinglePax", 2, null, null);
        if (iFieldSeq == kDoublePax)
            field = new ShortField(this, "DoublePax", 2, null, null);
        if (iFieldSeq == kTriplePax)
            field = new ShortField(this, "TriplePax", 2, null, null);
        if (iFieldSeq == kQuadPax)
            field = new ShortField(this, "QuadPax", 2, null, null);
        if (iFieldSeq == kChildren)
            field = new ShortField(this, "Children", 2, null, null);
        if (iFieldSeq == kGateway)
            field = new CityField(this, "Gateway", 3, null, null);
        if (iFieldSeq == kTourID)
            field = new TourField(this, "TourID", 6, null, null);
        if (iFieldSeq == kMarkup)
            field = new PercentField(this, "Markup", 5, null, null);
        if (iFieldSeq == kStdCommission)
            field = new PercentField(this, "StdCommission", 5, null, new Float(0.10));
        if (iFieldSeq == kAcceptDate)
            field = new DateField(this, "AcceptDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepositRecDate)
            field = new DateField(this, "DepositRecDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalPayRecDate)
            field = new DateField(this, "FinalPayRecDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBooked)
            field = new BooleanField(this, "Booked", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepositReceived)
            field = new BooleanField(this, "DepositReceived", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepositDue)
            field = new BooleanField(this, "DepositDue", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalPaymentReceived)
            field = new BooleanField(this, "FinalPaymentReceived", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalPaymentDue)
            field = new BooleanField(this, "FinalPaymentDue", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCancelled)
            field = new BooleanField(this, "Cancelled", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProperties)
            field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDeposit)
        {
            field = new DoubleField(this, "Deposit", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDepositDueDate)
        {
            field = new DateField(this, "DepositDueDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFinalPaymentDueDate)
        {
            field = new DateField(this, "FinalPaymentDueDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kNextEventDate)
            field = new DateField(this, "NextEventDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourEventID)
            field = new TourEventField(this, "TourEventID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(TourEvent.NO_EVENT));
        if (iFieldSeq == kGross)
            field = new CurrencyField(this, "Gross", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCommission)
            field = new CurrencyField(this, "Commission", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNet)
            field = new CurrencyField(this, "Net", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalance)
            field = new CurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPricingStatusID)
        {
            field = new PricingStatusField(this, "PricingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PricingStatus.OKAY));
            field.setVirtual(true);
        }
        if (iFieldSeq == kAskForAnswer)
            field = new BooleanField(this, "AskForAnswer", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kAlwaysResolve)
        {
            field = new BooleanField(this, "AlwaysResolve", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
            field.setVirtual(true);
        }
        if (iFieldSeq == kTourPricingTypeID)
            field = new PricingTypeField(this, "TourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNonTourPricingTypeID)
            field = new PricingTypeField(this, "NonTourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNextLineSequence)
            field = new ShortField(this, "NextLineSequence", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingLastField)
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
        if (iKeyArea == kProfileIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProfileID");
            keyArea.addKeyField(kProfileID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTourIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourID");
            keyArea.addKeyField(kTourID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kNextEventDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NextEventDate");
            keyArea.addKeyField(kNextEventDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBookingDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingDate");
            keyArea.addKeyField(kBookingDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kModDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ModDate");
            keyArea.addKeyField(kModDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kGenericNameKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "GenericName");
            keyArea.addKeyField(kGenericName, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Destructor.
     */
    public void free()
    {
        if (m_rdwPaxMods != null)
            m_rdwPaxMods.removeAllElements();
        m_rdwPaxMods = null;
        super.free();
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getField(Booking.kNet).addListener(new MergeDataAddHandler(null));
        this.getField(Booking.kCommission).addListener(new MergeDataAddHandler(null));
        this.getField(Booking.kGross).addListener(new MergeDataAddHandler(null));
        this.getField(Booking.kBalance).addListener(new MergeDataAddHandler(null));
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.addListener(new BookingSoftDeleteHandler(this.getField(Booking.kDeleted))); // @pend(don) Add this +++ this.getField(Booking.kDeleted)));
        
        this.addListener(new DateChangedHandler(Booking.kModDate));
        
        this.addListener(new SetUserIDHandler(Booking.kEmployeeID, true));
        this.addListener(new SetUserIDHandler(Booking.kEmployeeModID, false));
        
        this.addListener(new UpdateArTrxHandler(null));
        
        this.addListener(new NewBookingHandler(null));
        
        this.getField(Booking.kPax).addListener(new CalcDepositHandler(null));
        this.getField(Booking.kTourID).addListener(new CalcDepositHandler(null));
        
        this.getField(Booking.kBookingStatusID).addListener(new BookingStatusEventHandler(TourEvent.BOOKING_STATUS));
        
        this.getField(Booking.kBooked).addListener(new TourEventHandler(TourEvent.BOOKING));
        this.getField(Booking.kDepositReceived).addListener(new TourEventHandler(TourEvent.DEPOSIT_RECEIVED));
        this.getField(Booking.kFinalPaymentReceived).addListener(new TourEventHandler(TourEvent.FINAL_PAYMENT_RECEIVED));
        this.getField(Booking.kDepositDue).addListener(new TourEventHandler(TourEvent.DEPOSIT_DUE));
        this.getField(Booking.kFinalPaymentDue).addListener(new TourEventHandler(TourEvent.FINAL_PAY_DUE));
        this.getField(Booking.kCancelled).addListener(new TourEventHandler(TourEvent.CANCELLATION));
        this.getField(Booking.kCancelled).addListener(new BookingCancelledEventHandler(null));
        
        this.getField(Booking.kBalance).addListener(new BookingPaymentEventHandler(null));
        
        this.getField(Booking.kBooked).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.kBookingDate).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.kDepositDue).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.kFinalPaymentDue).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.kDepositDueDate).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.kFinalPaymentDueDate).addListener(new CalcBookingActionDateHandler(null));
        
        this.getField(Booking.kTourPricingTypeID).addListener(new ChangePricingTypeHandler(null));
        this.getField(Booking.kNonTourPricingTypeID).addListener(new ChangePricingTypeHandler(null));
        
        this.getField(Booking.kDescription).addListener(new SyncBookingFieldHandler(Tour.kDescription));
        this.getField(Booking.kCode).addListener(new SyncBookingFieldHandler(Tour.kCode));
    }
    /**
     * AddSlaveListeners Method.
     */
    public void addSlaveListeners()
    {
        super.addSlaveListeners();
        
        this.addListener(new HistoryHandler(BookingHistory.class.getName(), BookingHistory.kHistoryDate, -1));
    }
    /**
     * Add the defaults from the control file when you have a new record.
     */
    public Record addControlDefaults(Record recBookingControl, Record recProfileControl)
    {
        if (recBookingControl == null)
        {
            recBookingControl = new BookingControl(Utility.getRecordOwner(this));
            this.addListener(new FreeOnFreeHandler(recBookingControl));
        }
        if (recProfileControl == null)
        {
            recProfileControl = new ProfileControl(Utility.getRecordOwner(this));
            this.addListener(new FreeOnFreeHandler(recProfileControl));
        }
        this.getField(Booking.kBookingStatusID).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kBookingStatusID)));
        this.getField(Booking.kMarkup).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kMarkup)));
        this.getField(Booking.kTourPricingTypeID).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kTourPricingTypeID)));
        this.getField(Booking.kNonTourPricingTypeID).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kNonTourPricingTypeID)));
        this.getField(Booking.kStdCommission).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kAgencyComm)));
        this.getField(Booking.kLanguageID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kLanguageID)));
        this.getField(Booking.kCurrencysID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kCurrencysID)));
        
        this.getField(Booking.kPax).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kPax), true, true));
        this.getField(Booking.kSinglePax).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kSinglePax), true, true));
        this.getField(Booking.kDoublePax).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.kDoublePax), true, true));
        
        if (recBookingControl.getField(BookingControl.kAutoBookingCode).getState() == true)
            this.addListener(new MoveIDToCodeHandler(Booking.kCode)); // If auto-booking numbers are turned on, set them
        return recBookingControl;
    }
    /**
     * AddSecondProfile Method.
     */
    public void addSecondProfile(Record recProfile)
    {
        ReadSecondaryHandler behavior = new ReadSecondaryHandler(recProfile, Profile.kProfileCodeKey);
        behavior.setRespondsToMode(DBConstants.READ_MOVE, false);
        this.getField(Booking.kProfileCode).addListener(behavior);
        
        behavior.addFieldSeqPair(Booking.kProfileID, Profile.kID, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kProfileCode, Profile.kProfileCode, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kGenericName, Profile.kGenericName, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kAddressLine1, Profile.kAddressLine1, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kAddressLine2, Profile.kAddressLine2, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kCityOrTown, Profile.kCityOrTown, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kCountry, Profile.kCountry, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kStateOrRegion, Profile.kStateOrRegion, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kPostalCode, Profile.kPostalCode, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kTel, Profile.kTel, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kFax, Profile.kFax, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kEmail, Profile.kEmail, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kLanguageID, Profile.kPrimaryLanguageID, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.kCurrencysID, Profile.kCurrencysID, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        // Only move contact if not blank
        Converter convTrueIfContactNotBlank = new RadioConverter(this.getField(Booking.kContact), DBConstants.BLANK, true);
        recProfile.addListener(new RemoveConverterOnCloseHandler(convTrueIfContactNotBlank));
        MoveOnValidHandler moveHandler = behavior.addFieldSeqPair(Booking.kContact, Profile.kContact, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, convTrueIfContactNotBlank, null);
        moveHandler.setDisableOnMove(false);
    }
    /**
     * Add all the tour detail to this booking.
     * @param recTour
     * @param recTourHeader
     * @param recBookingPax
     * @param recBookingAnswer If null, set up the default booking answers.
     * @param dateStart
     * @return An error code or NORMAL_RETURN if okay.
     */
    public int addTourDetail(Tour recTour, TourHeader recTourHeader, BookingPax recBookingPax, BookingAnswer recBookingAnswer, Date dateStart, BaseField fldAskForAnswer)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        BookingAnswer recBookingAnswerNew = null;
        BookingPax recBookingPaxNew = null;
        FileListener listener = null;
        if (recBookingAnswer == null)
        {   // If booking answer is not passed in, set up the default answers.
            recBookingAnswer = new BookingAnswer(Utility.getRecordOwner(this));
            recBookingAnswerNew = recBookingAnswer;
            iErrorCode = recBookingAnswer.setupAnswerDetail(TourHeaderOption.TOUR, recTourHeader.getField(TourHeader.kID), recTourHeader.getField(TourHeader.kID), this, recBookingPax, dateStart, fldAskForAnswer, true);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
                return iErrorCode;
        }
        
        BaseField fldTourModuleID = recTourHeader.getField(TourHeader.kID);
        try {
            if (recBookingPax == null)
            {
                recBookingPax = new BookingPax(Utility.getRecordOwner(this));
                recBookingPaxNew = recBookingPax;
            }
            if (recBookingPax.getField(BookingPax.kID).isNull())
                recBookingPax.getField(BookingPax.kID).setValue(0);
        
            recBookingAnswer.setKeyArea(BookingAnswer.kScanOrderKey);
            recBookingAnswer.addListener(listener = new SubFileFilter(this.getField(Booking.kID), BookingAnswer.kBookingID, recBookingPax.getField(BookingPax.kID), BookingAnswer.kBookingPaxID, fldTourModuleID, BookingAnswer.kModuleID));
        //+++    recBookingAnswer.addListener(listener2 = new SubFileFilter(dateStart, BookingAnswer.kModuleStartDate, null, -1, null, -1));
            recBookingAnswer.close();
            while (recBookingAnswer.hasNext())
            {
                recBookingAnswer.next();
                iErrorCode = recBookingAnswer.addAnswerDetail(this, recTour, recBookingPax, fldTourModuleID, dateStart);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    ; // Keep going
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recBookingAnswer.removeListener(listener, true);
            if (recBookingAnswerNew != null)
                recBookingAnswer.free();
            recBookingAnswer = null;
            if (recBookingPaxNew != null)
                recBookingPaxNew.free();
            recBookingPaxNew = null;
        }
        return iErrorCode;
    }
    /**
     * ChangeTourDetail Method.
     */
    public int changeTourDetail(Tour recTour, BookingPax recBookingPax, TourHeader recTourHeader, Date dateOriginal, Date dateStart)
    {
        BaseField fldBookingPaxID = null;
        if (recBookingPax != null)
            fldBookingPaxID = recBookingPax.getField(BookingPax.kID);
        else
        {
            fldBookingPaxID = new IntegerField(null, "ID", -1, null, null);
            fldBookingPaxID.setData(new Integer(0));
        }
        BookingAnswer recBookingAnswer = new BookingAnswer(Utility.getRecordOwner(this));
        recBookingAnswer.addDetailBehaviors(this, recTour);
        int iErrorCode = recBookingAnswer.changeAllDetail(this, fldBookingPaxID, recTourHeader.getField(TourHeader.kID), dateOriginal, dateStart);
        recBookingAnswer.free();
        recBookingAnswer = null;    // The makes answers re-resolve
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        
        iErrorCode = this.deleteTourDetail(recTour, recBookingPax, recTourHeader.getField(TourHeader.kID), dateOriginal);
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        
        BooleanField fldAskForAnswer = new BooleanField(null, "Ask", DBConstants.DEFAULT_FIELD_LENGTH, null, null);
        fldAskForAnswer.setState(false);    // Don't ask, I just moved all the answers to this module
        iErrorCode = this.addTourDetail(recTour, recTourHeader, recBookingPax, recBookingAnswer, dateStart, fldAskForAnswer);
        
        fldAskForAnswer.free();
        if (recBookingPax != null)
            fldBookingPaxID.free();
        
        return iErrorCode;
    }
    /**
     * DeleteTourDetail Method.
     */
    public int deleteTourDetail(Tour recTour, BookingPax recBookingPax, BaseField fldTourModuleID, Date dateStart)
    {
        BaseField fldBookingPaxID = null;
        if (recBookingPax != null)
            fldBookingPaxID = recBookingPax.getField(BookingPax.kID);
        else
        {
            fldBookingPaxID = new IntegerField(null, "ID", -1, null, null);
            fldBookingPaxID.setData(new Integer(0));
        }
        BookingAirHeader recBookingAirHeader = new BookingAirHeader(Utility.getRecordOwner(this));
        recBookingAirHeader.addDetailBehaviors(this, recTour);
        int iErrorCode = recBookingAirHeader.deleteAllDetail(this, fldBookingPaxID, fldTourModuleID, dateStart);
        recBookingAirHeader.free();
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        
        BookingLine recBookingLine = new BookingLine(Utility.getRecordOwner(this));
        recBookingLine.addDetailBehaviors(this, recTour);
        iErrorCode = recBookingLine.deleteAllDetail(this, fldBookingPaxID, fldTourModuleID, dateStart);
        recBookingLine.free();
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        
        BookingDetail recBookingDetail = new BookingDetail(Utility.getRecordOwner(this));
        recBookingDetail.addDetailBehaviors(this, recTour);
        iErrorCode = recBookingDetail.deleteAllDetail(this, fldBookingPaxID, fldTourModuleID, dateStart);
        recBookingDetail.free();
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        if (recBookingPax != null)
            fldBookingPaxID.free();
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * AddBookingDetailPricing Method.
     */
    public int addBookingDetailPricing(Tour recTour, BookingLine recBookingLine, BaseField fldTourModuleID, Date dateStart, boolean bRecost)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        BookingDetail recBookingDetails = null;
        BookingAnswer recBookingAnswer = null;
        TourHeaderLine recTourHeaderPricing = null;
        
        try {
            // If you just changed to TourPricing, add the main tour pricing:
            recBookingDetails = new BookingDetail(Utility.getRecordOwner(this));
            recBookingDetails.addDetailBehaviors(this, recTour);
            
            while (recBookingDetails.hasNext())
            {
                BookingDetail recBookingDetail = (BookingDetail)recBookingDetails.next();
                
                BaseField fldDetailModuleID = recBookingDetail.getField(BookingDetail.kModuleID);
                Date dateDetailStart = ((DateTimeField)recBookingDetail.getField(BookingDetail.kModuleStartDate)).getDateTime();
                if (recBookingDetail.getField(BookingDetail.kProductTypeID).getValue() == ProductType.TOUR_ID)
                {
                    fldDetailModuleID = recBookingDetail.getField(BookingDetail.kProductID);
                    dateDetailStart = ((DateTimeField)recBookingDetail.getField(BookingDetail.kDetailDate)).getDateTime();                    
                }
                if ((fldTourModuleID != null) && (!fldTourModuleID.equals(fldDetailModuleID)))
                    continue;
                if ((dateStart != null) && (!dateStart.equals(dateDetailStart)))
                    continue;
        
                int iTourPricingType = this.getTourPricingType(recTour, fldDetailModuleID, dateDetailStart);
        
                boolean bPricingListenerState = true;
                if (this.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class) != null)
                    bPricingListenerState = this.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class).setEnabledListener(false);
                if (bRecost)
                {
                    boolean bListenerState = true;
                    if (recBookingDetail.getListener(BookingDetailPriceChangeHandler.class) != null)
                        bListenerState = recBookingDetail.getListener(BookingDetailPriceChangeHandler.class).setEnabledListener(false);
                    recBookingDetail.edit();
                    recBookingDetail.getField(BookingDetail.kInfoStatusRequest).setState(true); // Information changed, recost if required.
                    recBookingDetail.set();
                    if (recBookingDetail.getListener(BookingDetailPriceChangeHandler.class) != null)
                        recBookingDetail.getListener(BookingDetailPriceChangeHandler.class).setEnabledListener(bListenerState);
                }
                boolean bTourDetailPricingType = !recBookingDetail.getField(BookingDetail.kTourHeaderOptionID).isNull();
                if (iTourPricingType == PricingType.OPTION_PRICING)
                    if (bTourDetailPricingType)
                {
                    if (this.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class) != null)
                        this.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class).setEnabledListener(bPricingListenerState);
                    continue;   // I'm going to add the price for this in the next step
                }
                recBookingDetail.getField(BookingDetail.kTotalCostLocal).setModified(true); // This will fake a cost change
                recBookingDetail.handlePriceChange(DBConstants.UPDATE_TYPE);
                if (this.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class) != null)
                    this.getField(Booking.kTourPricingTypeID).getListener(ChangePricingTypeHandler.class).setEnabledListener(bPricingListenerState);
            }
        
            int iTourPricingType = this.getTourPricingType(recTour, fldTourModuleID, dateStart);
            if (iTourPricingType == PricingType.OPTION_PRICING)
            {
                BaseField fldBookingPaxID = new IntegerField(null, "ID", -1, null, null);
                recBookingAnswer = new BookingAnswer(Utility.getRecordOwner(this));
                TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
                BookingPax recBookingPax = new BookingPax(Utility.getRecordOwner(this));
                recBookingPax.getField(BookingPax.kID).setValue(0);
                recTourHeaderPricing = new TourHeaderLine(Utility.getRecordOwner(this));
                Date dateDeparture = ((DateField)recTour.getField(Tour.kDepartureDate)).getDateTime();
                recBookingAnswer.addDetailBehaviors(this, recTour);
                while (recBookingAnswer.hasNext())
                {
                    recBookingAnswer.next();
                    
                    BaseField fldDetailModuleID = recBookingAnswer.getField(BookingAnswer.kModuleID);
                    Date dateDetailStart = ((DateTimeField)recBookingAnswer.getField(BookingAnswer.kModuleStartDate)).getDateTime();
                    if ((fldTourModuleID != null) && (!fldTourModuleID.equals(fldDetailModuleID)))
                        continue;
                    if ((dateStart != null) && (!dateStart.equals(dateDetailStart)))
                        continue;
        
                    boolean bSetupDetail = false;
                    if (recBookingAnswer.getField(BookingAnswer.kSelected).getState() == true)
                        if (recBookingAnswer.getField(BookingAnswer.kDetailPriceExists).getState() == true)
                    {
                        if ((recTour.getField(Tour.kTourHeaderID).equals(fldDetailModuleID))
                            && (recTour.getField(Tour.kDepartureDate).equals(recBookingAnswer.getField(BookingAnswer.kModuleStartDate))))
                                bSetupDetail = true;
                        else
                        {
                            if (this.getTourPricingType(recTour, fldDetailModuleID, dateDetailStart) == PricingType.OPTION_PRICING)
                                bSetupDetail = true;                                
                        }
                    }
                    if (bSetupDetail)
                        iErrorCode = recBookingLine.setupAllDetail(recTourHeaderPricing, this, recTour, recBookingPax.getField(BookingPax.kID), recBookingAnswer.getField(BookingAnswer.kTourHeaderOptionID), fldDetailModuleID, dateDetailStart);
                    if (iErrorCode != DBConstants.NORMAL_RETURN)
                        return iErrorCode;
                }
                
                BooleanField fldAskForAnswer = new BooleanField(null, "Ask", DBConstants.DEFAULT_FIELD_LENGTH, null, null);
                fldAskForAnswer.setState(false);    // Don't ask, Use the current values
                iErrorCode = this.addTourDetail(recTour, recTourHeader, recBookingPax, recBookingAnswer, dateDeparture, fldAskForAnswer);
                
                fldAskForAnswer.free();
                fldBookingPaxID.free();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            if (recBookingAnswer != null)
                recBookingAnswer.free();
            if (recTourHeaderPricing != null)
                recTourHeaderPricing.free();
            if (recBookingDetails != null)
                recBookingDetails.free();
        }
        return iErrorCode;
    }
    /**
     * CalcBookingDates Method.
     */
    public int calcBookingDates(Record recTour, Record recTourHeader)
    {
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReference();
        recTourClass.fixBasedFields();
        Calendar depositDate = ((DateTimeField)this.getField(Booking.kBookingDate)).getCalendar();
        // Add deposit due days to booking date
        depositDate.add(Calendar.DATE, (int)recTourClass.getField(TourClass.kDepositDueDays).getValue());
        
        Calendar finalDate = ((DateTimeField)recTour.getField(Tour.kDepartureDate)).getCalendar();
        // Subtract Final payment days from departure date.
        finalDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kFinalPayDays).getValue());
        
        Calendar calCurrentTime = Calendar.getInstance();
        if (finalDate.before(calCurrentTime))
            finalDate = calCurrentTime; 
        ((DateTimeField)this.getField(Booking.kFinalPaymentDueDate)).setCalendar(finalDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        if (depositDate.after(finalDate))
            depositDate = finalDate;
        ((DateTimeField)this.getField(Booking.kDepositDueDate)).setCalendar(depositDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        if (!recTourClass.getField(TourClass.kInitialBookingStatusID).isNull())
            this.getField(Booking.kBookingStatusID).moveFieldToThis(recTourClass.getField(TourClass.kInitialBookingStatusID));
        if (!recTourClass.getField(TourClass.kMarkup).isNull())
            this.getField(Booking.kMarkup).moveFieldToThis(recTourClass.getField(TourClass.kMarkup));
        if (!recTourClass.getField(TourClass.kTourPricingTypeID).isNull())
            this.getField(Booking.kTourPricingTypeID).moveFieldToThis(recTourClass.getField(TourClass.kTourPricingTypeID));
        if (!recTourClass.getField(TourClass.kNonTourPricingTypeID).isNull())
            this.getField(Booking.kNonTourPricingTypeID).moveFieldToThis(recTourClass.getField(TourClass.kNonTourPricingTypeID));
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Add the ArTrx and BookingLine detail files if they don't already exist.
     * Also add all the listeners for these files.
     * @param bForceRecount If true, make sure the booking totals are correct, especially if this record is in an indeterminate state.
     */
    public ArTrx addArDetail(ArTrx recArTrx, BookingLine recBookingLine, boolean bForceRecount)
    {
        FreeOnFreeHandler listener = (FreeOnFreeHandler)this.getListener(FreeOnFreeHandler.class);
        if (recBookingLine == null)
        {
            while (listener != null)
            {
                if (listener.getDependentObject() instanceof BookingLine)
                {
                    recBookingLine = (BookingLine)listener.getDependentObject();
                    break;
                }
                listener = (FreeOnFreeHandler)listener.getListener(FreeOnFreeHandler.class);
            }
        }
        if (recBookingLine == null)
        {
            recBookingLine = new BookingLine(Utility.getRecordOwner(this));
            this.addListener(new FreeOnFreeHandler(recBookingLine));
        }
        if (recBookingLine.getListener(SubFileFilter.class) == null)
            recBookingLine.addDetailBehaviors(this, (Tour)((ReferenceField)this.getField(Booking.kTourID)).getReferenceRecord());
        RecountOnValidHandler recountOnValidHandler = (RecountOnValidHandler)recBookingLine.getListener(RecountOnValidHandler.class);
        if (recountOnValidHandler == null)
        {
            int iCurrentEditMode = this.setEditMode(DBConstants.EDIT_NONE);    // This keeps a recount from happening on addListener.
            this.addListener(recountOnValidHandler = new RecountOnValidHandler(recBookingLine, true));
            this.setEditMode(iCurrentEditMode);            
        }
        if (bForceRecount)
            if (!this.getCounterField().isNull())
                recountOnValidHandler.recountRecords();
        listener = (FreeOnFreeHandler)this.getListener(FreeOnFreeHandler.class);
        if (recArTrx == null)
        {
            while (listener != null)
            {
                if (listener.getDependentObject() instanceof ArTrx)
                {
                    recArTrx = (ArTrx)listener.getDependentObject();
                    break;
                }
                listener = (FreeOnFreeHandler)listener.getListener(FreeOnFreeHandler.class);
            }
        }
        if (recArTrx == null)
        {
            recArTrx = new ArTrx(Utility.getRecordOwner(this));
            this.addListener(new FreeOnFreeHandler(recArTrx));
        }
        if (recArTrx.getListener(SubFileFilter.class) == null)
            recArTrx.addDetailBehaviors(this);
        recountOnValidHandler = (RecountOnValidHandler)recBookingLine.getListener(RecountOnValidHandler.class);
        if (recountOnValidHandler == null)
        {
            int iCurrentEditMode = this.setEditMode(DBConstants.EDIT_NONE);    // This keeps a recount from happening on addListener.
            this.addListener(recountOnValidHandler = new RecountOnValidHandler(recArTrx, true));
            this.setEditMode(iCurrentEditMode);            
        }
        if (bForceRecount)
            if (!this.getCounterField().isNull())
                recountOnValidHandler.recountRecords();
        return recArTrx;
    }
    /**
     * Setup the default booking description and code.
     */
    public String setupDefaultDesc(Record recTourHeader, BaseField fldDepDate)
    {
        String strDesc = DBConstants.BLANK;
        if (!this.getCounterField().isNull())
        {
            if (this.getField(Booking.kPax).getValue() > 0)
            {   // Get last name from pax list
                BookingPax recBookingPax = new BookingPax(Utility.getRecordOwner(this));
                try {
                    recBookingPax.addListener(new SubFileFilter(this));
                    if (recBookingPax.next() != null)
                        strDesc = recBookingPax.getField(BookingPax.kSurName).toString();
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    recBookingPax.free();
                }
            } 
            else if (!this.getField(Booking.kGenericName).isNull())
                strDesc = this.getField(Booking.kGenericName).toString();
            else
                strDesc = this.getField(Booking.kContact).toString();
        }
        if (strDesc.length() > 0)
            strDesc += " - ";
        String strTourDesc = recTourHeader.getField(TourHeader.kDescription).toString();
        String strDate = fldDepDate.toString();
        int iLen = strDesc.length() + strTourDesc.length() + 3 + strDate.length();
        if (iLen > this.getField(Booking.kDescription).getMaxLength())
            strTourDesc = strTourDesc.substring(0, strTourDesc.length() - Math.max(0, Math.max(0, iLen - this.getField(Booking.kDescription).getMaxLength())));
        if (strTourDesc.length() > 0)
            strDesc += strTourDesc + " - ";
        strDesc += strDate;
        int iOldOpenMode = this.getOpenMode();
        this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        boolean[] rgbEnabled = this.getField(Booking.kDescription).setEnableListeners(false);
        this.getField(Booking.kDescription).setString(strDesc);
        this.getField(Booking.kDescription).setEnableListeners(rgbEnabled);
        this.setOpenMode(iOldOpenMode);
        return strDesc;
    }
    /**
     * Get number of pax.
     */
    public int getCountPax()
    {
        return (int)this.getField(kPax).getValue();
    }
    /**
     * Get number of pax for this mod.
     */
    public int getCountPaxMod()
    {
        if (m_rdwPaxMods == null)
            return this.getCountPax();
        return m_rdwPaxMods.size();
    }
    /**
     * Get the pax mod at this index.
     */
    public int getPaxMod(int nIndex)
    {
        if (m_rdwPaxMods == null)
            this.setupPaxMod();
        return ((Integer)m_rdwPaxMods.elementAt(nIndex)).intValue();
    }
    /**
     * Remove the mod at this position.
     */
    public void removePaxMod(int nIndex)
    {
        if (m_rdwPaxMods == null)
            return;
        m_rdwPaxMods.removeElementAt(nIndex);
    }
    /**
     * Add this Pax to the list of pax mods.
     */
    public void addPaxMod(BaseField paxModRecNo)
    {
        if (m_rdwPaxMods == null)
            this.setupPaxMod();
        //+if (m_rdwPaxMod.Find((int)paxModRecNo.getValue()) == -1)
        //+m_rdwPaxMods.Add((int)paxModRecNo.getValue());
    }
    /**
     * Init/Clear the Pax Mod List.
     */
    public int setupPaxMod()
    {
        if (m_rdwPaxMods != null)
            m_rdwPaxMods = new Vector();
        m_rdwPaxMods.removeAllElements();
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Write this record and re-read if (if it has been modified).
     * @return the bookmark.
     */
    public Object writeAndRefresh() throws DBException
    {
        Object bookmark = super.writeAndRefresh();
        Record recTour = ((ReferenceField)this.getField(Booking.kTourID)).getReference();
        if (recTour != null)
            if ((recTour.getEditMode() == DBConstants.EDIT_CURRENT) || (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
        {
            int iOldOpenMode = recTour.setOpenMode(recTour.getOpenMode() & ~DBConstants.OPEN_READ_ONLY);
            recTour.writeAndRefresh();
            recTour.setOpenMode(iOldOpenMode);
        }
        return bookmark;
    }
    /**
     * GetTourPricingType Method.
     */
    public int getTourPricingType(Tour recTour, BaseField fldTourModuleID, Date dateStart)
    {
        Record recPricingType = null;
        int iTourPricingType = 0;
        
        if ((fldTourModuleID != null) && (dateStart != null))
        {
            if (recTour == null)
                recTour = (Tour)((ReferenceField)this.getField(Booking.kTourID)).getReference();
            if (!fldTourModuleID.equals(recTour.getCounterField()))
            {   // This is a module, get the pricing type for this module
                BookingDetail recBookingDetailModule = m_recBookingDetailModule;
                boolean bFound = false;
                if ((fldTourModuleID.getRecord() instanceof BookingDetail) && (fldTourModuleID == fldTourModuleID.getRecord().getField(BookingDetail.kProductID)))
                {
                    bFound = true;
                    recBookingDetailModule = (BookingDetail)fldTourModuleID.getRecord();
                    if (m_recBookingDetailModule != null)
                        if (recBookingDetailModule.getField(BookingDetail.kID).equals(m_recBookingDetailModule.getField(BookingDetail.kID)))
                        if (!recBookingDetailModule.getField(BookingDetail.kPricingTypeID).equals(m_recBookingDetailModule.getField(BookingDetail.kPricingTypeID)))
                        {
                            try {
                                m_recBookingDetailModule.addNew();  // Clear the cached record if pricing type has changed!
                            } catch (DBException e) {
                                e.printStackTrace();
                            }
                        }
                }
                else if (recBookingDetailModule == null)
                {   // This is rather expensive, but it shouldn't be done too often.
                    m_recBookingDetailModule = new BookingDetail(Utility.getRecordOwner(this));
                    if (m_recBookingDetailModule.getRecordOwner() != null)
                        m_recBookingDetailModule.getRecordOwner().removeRecord(m_recBookingDetailModule);
                    this.addListener(new FreeOnFreeHandler(m_recBookingDetailModule)
                    {
                        public void setOwner(ListenerOwner owner)
                        {
                            if (owner == null)
                                m_recBookingDetailModule = null;
                            super.setOwner(owner);
                        }
                    });
                    recBookingDetailModule = m_recBookingDetailModule;
                    recBookingDetailModule.setEnableFieldListeners(false);  // Won't be needing these to do a seek!
                    recBookingDetailModule.setEnableListeners(false);
                }
                try {
                    if (!bFound)
                    {
                        if ((recBookingDetailModule.getEditMode() == DBConstants.EDIT_CURRENT)
                            && (recBookingDetailModule.getField(BookingDetail.kProductTypeID).getValue() == ProductType.TOUR_ID)
                            && (recBookingDetailModule.getField(BookingDetail.kProductID).equals(fldTourModuleID))
                            && (dateStart.equals(((DateTimeField)recBookingDetailModule.getField(BookingDetail.kDetailDate)).getDateTime()))
                            && (this.getField(Booking.kID).equals(recBookingDetailModule.getField(BookingDetail.kBookingID))))
                                bFound = true;
                        else
                        {
                            recBookingDetailModule.getField(BookingDetail.kProductTypeID).setValue(ProductType.TOUR_ID);
                            recBookingDetailModule.addNew();
                            recBookingDetailModule.setKeyArea(BookingDetail.kProductIDKey);
                            recBookingDetailModule.getField(BookingDetail.kProductID).moveFieldToThis(fldTourModuleID);
                            ((DateTimeField)recBookingDetailModule.getField(BookingDetail.kDetailDate)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                            recBookingDetailModule.getField(BookingDetail.kBookingID).moveFieldToThis(this.getField(Booking.kID));
                            bFound = recBookingDetailModule.seek(DBConstants.EQUALS);
                        }
                    }
                    if (bFound)
                    {
                        recPricingType = ((ReferenceField)recBookingDetailModule.getField(BookingDetail.kPricingTypeID)).getReference();
                        if (recPricingType != null)
                            iTourPricingType = (int)recPricingType.getField(PricingType.kPricingCodes).getValue();
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }
        if (iTourPricingType == 0)
            recPricingType = ((ReferenceField)this.getField(Booking.kTourPricingTypeID)).getReference();
        
        if (recPricingType != null)
            iTourPricingType = (int)recPricingType.getField(PricingType.kPricingCodes).getValue();
        
        return iTourPricingType;
    }
    /**
     * Do a remote command.
     * @param strCommand The command
     * @param properties The properties for the command
     * @return The return value or Boolean.FALSE if not handled.
     */
    public Object doRemoteCommand(String strCommand, Map<String, Object> properties)
    {
        if ("addTourDetail".equalsIgnoreCase(strCommand))
        {
            Object objID = properties.get(TourHeader.kTourHeaderFile);
            if (objID instanceof Integer)
            { // Always
                Tour recTour = (Tour)((ReferenceField)this.getField(Booking.kTourID)).getReference();
                TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReferenceRecord(Utility.getRecordOwner(this));
                try {
                    recTourHeader.addNew();
                    recTourHeader.setKeyArea(Tour.kIDKey);
                    recTourHeader.getCounterField().setData(objID);
                    if (recTourHeader.seek(DBConstants.EQUALS))
                    {
                        Date dateStart = (Date)properties.get(recTour.getField(Tour.kDepartureDate).getFieldName());
                        BookingAnswer recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
                        BookingPax recBookingPax = null;
                        int iErrorCode = this.addTourDetail(recTour, recTourHeader, recBookingPax, recBookingAnswer, dateStart, this.getField(Booking.kAskForAnswer));
                        return new Integer(iErrorCode);   // Success
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                } finally {
                    recTour.free();
                    recTourHeader.free();
                }
            }
        }
        return super.doRemoteCommand(strCommand, properties);
    }

}
