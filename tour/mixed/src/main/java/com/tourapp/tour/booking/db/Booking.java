/**
 * @(#)Booking.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
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
import com.tourapp.model.tour.booking.db.*;

/**
 *  Booking - Booking entry.
 */
public class Booking extends CustSale
     implements BookingModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_MENU_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new CounterField(this, ID, 6, null, null);
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
        if (iFieldSeq == 3)
            field = new Booking_BookingDate(this, BOOKING_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new ReferenceField(this, EMPLOYEE_ID, 6, null, null);
        if (iFieldSeq == 5)
            field = new ProfileField(this, PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new StringField(this, PROFILE_CODE, 16, null, null);
        if (iFieldSeq == 7)
            field = new StringField(this, CODE, 20, null, null);
        if (iFieldSeq == 8)
            field = new StringField(this, DESCRIPTION, 50, null, null);
        if (iFieldSeq == 9)
            field = new ReferenceField(this, EMPLOYEE_MOD_ID, 6, null, null);
        if (iFieldSeq == 10)
            field = new DateTimeField(this, MOD_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new BookingStatusField(this, BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new StringField(this, GENERIC_NAME, 30, null, null);
        if (iFieldSeq == 13)
            field = new StringField(this, ADDRESS_LINE_1, 40, null, null);
        if (iFieldSeq == 14)
            field = new StringField(this, ADDRESS_LINE_2, 40, null, null);
        if (iFieldSeq == 15)
            field = new StringField(this, CITY_OR_TOWN, 15, null, null);
        if (iFieldSeq == 16)
            field = new StringField(this, STATE_OR_REGION, 15, null, null);
        if (iFieldSeq == 17)
            field = new StringField(this, POSTAL_CODE, 10, null, null);
        if (iFieldSeq == 18)
            field = new StringField(this, COUNTRY, 15, null, null);
        if (iFieldSeq == 19)
            field = new PhoneField(this, TEL, 24, null, null);
        if (iFieldSeq == 20)
            field = new FaxField(this, FAX, 24, null, null);
        if (iFieldSeq == 21)
            field = new EMailField(this, EMAIL, 40, null, null);
        if (iFieldSeq == 22)
            field = new StringField(this, CONTACT, 25, null, null);
        if (iFieldSeq == 23)
            field = new LanguageField(this, LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 24)
            field = new CurrencysField(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 25)
        {
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 26)
            field = new RealField(this, EXCHANGE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 27)
            field = new ShortField(this, PAX, 3, null, null);
        if (iFieldSeq == 28)
            field = new ShortField(this, FOCS, 2, null, null);
        if (iFieldSeq == 29)
            field = new ShortField(this, SINGLE_PAX, 2, null, null);
        if (iFieldSeq == 30)
            field = new ShortField(this, DOUBLE_PAX, 2, null, null);
        if (iFieldSeq == 31)
            field = new ShortField(this, TRIPLE_PAX, 2, null, null);
        if (iFieldSeq == 32)
            field = new ShortField(this, QUAD_PAX, 2, null, null);
        if (iFieldSeq == 33)
            field = new ShortField(this, CHILDREN, 2, null, null);
        if (iFieldSeq == 34)
            field = new CityField(this, GATEWAY, 3, null, null);
        if (iFieldSeq == 35)
            field = new TourField(this, TOUR_ID, 6, null, null);
        if (iFieldSeq == 36)
            field = new PercentField(this, MARKUP, 5, null, null);
        if (iFieldSeq == 37)
            field = new PercentField(this, STD_COMMISSION, 5, null, new Float(0.10));
        if (iFieldSeq == 38)
            field = new DateField(this, ACCEPT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 39)
            field = new DateField(this, DEPOSIT_REC_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 40)
            field = new DateField(this, FINAL_PAY_REC_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 41)
            field = new BooleanField(this, BOOKED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 42)
            field = new BooleanField(this, DEPOSIT_RECEIVED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 43)
            field = new BooleanField(this, DEPOSIT_DUE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 44)
            field = new BooleanField(this, FINAL_PAYMENT_RECEIVED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 45)
            field = new BooleanField(this, FINAL_PAYMENT_DUE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 46)
            field = new BooleanField(this, CANCELLED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 47)
            field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 48)
        {
            field = new DoubleField(this, DEPOSIT, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 49)
        {
            field = new DateField(this, DEPOSIT_DUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 50)
        {
            field = new DateField(this, FINAL_PAYMENT_DUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 51)
            field = new DateField(this, NEXT_EVENT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 52)
            field = new TourEventField(this, TOUR_EVENT_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(TourEvent.NO_EVENT));
        if (iFieldSeq == 53)
            field = new CurrencyField(this, GROSS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 54)
            field = new CurrencyField(this, COMMISSION, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 55)
            field = new CurrencyField(this, NET, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 56)
            field = new CurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 57)
        {
            field = new PricingStatusField(this, PRICING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PricingStatus.OKAY));
            field.setVirtual(true);
        }
        if (iFieldSeq == 58)
            field = new BooleanField(this, ASK_FOR_ANSWER, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 59)
        {
            field = new BooleanField(this, ALWAYS_RESOLVE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
            field.setVirtual(true);
        }
        if (iFieldSeq == 60)
            field = new PricingTypeField(this, TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 61)
            field = new PricingTypeField(this, NON_TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 62)
            field = new ShortField(this, NEXT_LINE_SEQUENCE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
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
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProfileID");
            keyArea.addKeyField(PROFILE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourID");
            keyArea.addKeyField(TOUR_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NextEventDate");
            keyArea.addKeyField(NEXT_EVENT_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingDate");
            keyArea.addKeyField(BOOKING_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 6)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ModDate");
            keyArea.addKeyField(MOD_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 7)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "GenericName");
            keyArea.addKeyField(GENERIC_NAME, DBConstants.ASCENDING);
        }
        if (iKeyArea == 8)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(DESCRIPTION, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
        
        this.getField(Booking.NET).addListener(new MergeDataAddHandler(null));
        this.getField(Booking.COMMISSION).addListener(new MergeDataAddHandler(null));
        this.getField(Booking.GROSS).addListener(new MergeDataAddHandler(null));
        this.getField(Booking.BALANCE).addListener(new MergeDataAddHandler(null));
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.addListener(new BookingSoftDeleteHandler(this.getField(Booking.DELETED))); // @pend(don) Add this +++ this.getField(Booking.DELETED)));
        
        this.addListener(new DateChangedHandler(Booking.MOD_DATE));
        
        this.addListener(new SetUserIDHandler(Booking.EMPLOYEE_ID, true));
        this.addListener(new SetUserIDHandler(Booking.EMPLOYEE_MOD_ID, false));
        
        this.addListener(new UpdateArTrxHandler(null));
        
        this.addListener(new NewBookingHandler(null));
        
        this.getField(Booking.PAX).addListener(new CalcDepositHandler(null));
        this.getField(Booking.TOUR_ID).addListener(new CalcDepositHandler(null));
        
        this.getField(Booking.BOOKING_STATUS_ID).addListener(new BookingStatusEventHandler(TourEvent.BOOKING_STATUS));
        
        this.getField(Booking.BOOKED).addListener(new TourEventHandler(TourEvent.BOOKING));
        this.getField(Booking.DEPOSIT_RECEIVED).addListener(new TourEventHandler(TourEvent.DEPOSIT_RECEIVED));
        this.getField(Booking.FINAL_PAYMENT_RECEIVED).addListener(new TourEventHandler(TourEvent.FINAL_PAYMENT_RECEIVED));
        this.getField(Booking.DEPOSIT_DUE).addListener(new TourEventHandler(TourEvent.DEPOSIT_DUE));
        this.getField(Booking.FINAL_PAYMENT_DUE).addListener(new TourEventHandler(TourEvent.FINAL_PAY_DUE));
        this.getField(Booking.CANCELLED).addListener(new TourEventHandler(TourEvent.CANCELLATION));
        this.getField(Booking.CANCELLED).addListener(new BookingCancelledEventHandler(null));
        
        this.getField(Booking.BALANCE).addListener(new BookingPaymentEventHandler(null));
        
        this.getField(Booking.BOOKED).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.BOOKING_DATE).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.DEPOSIT_DUE).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.FINAL_PAYMENT_DUE).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.DEPOSIT_DUE_DATE).addListener(new CalcBookingActionDateHandler(null));
        this.getField(Booking.FINAL_PAYMENT_DUE_DATE).addListener(new CalcBookingActionDateHandler(null));
        
        this.getField(Booking.TOUR_PRICING_TYPE_ID).addListener(new ChangePricingTypeHandler(null));
        this.getField(Booking.NON_TOUR_PRICING_TYPE_ID).addListener(new ChangePricingTypeHandler(null));
        
        this.getField(Booking.DESCRIPTION).addListener(new SyncBookingFieldHandler(Tour.DESCRIPTION));
        this.getField(Booking.CODE).addListener(new SyncBookingFieldHandler(Tour.CODE));
    }
    /**
     * AddSlaveListeners Method.
     */
    public void addSlaveListeners()
    {
        super.addSlaveListeners();
        
        this.addListener(new HistoryHandler(BookingHistory.class.getName(), BookingHistory.HISTORY_DATE, null));
    }
    /**
     * Add the defaults from the control file when you have a new record.
     */
    public Record addControlDefaults(Record recBookingControl, Record recProfileControl)
    {
        if (recBookingControl == null)
        {
            recBookingControl = new BookingControl(this.findRecordOwner());
            this.addListener(new FreeOnFreeHandler(recBookingControl));
        }
        if (recProfileControl == null)
        {
            recProfileControl = new ProfileControl(this.findRecordOwner());
            this.addListener(new FreeOnFreeHandler(recProfileControl));
        }
        this.getField(Booking.BOOKING_STATUS_ID).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.BOOKING_STATUS_ID)));
        this.getField(Booking.MARKUP).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.MARKUP)));
        this.getField(Booking.TOUR_PRICING_TYPE_ID).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.TOUR_PRICING_TYPE_ID)));
        this.getField(Booking.NON_TOUR_PRICING_TYPE_ID).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.NON_TOUR_PRICING_TYPE_ID)));
        this.getField(Booking.STD_COMMISSION).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.AGENCY_COMM)));
        this.getField(Booking.LANGUAGE_ID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.LANGUAGE_ID)));
        this.getField(Booking.CURRENCYS_ID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.CURRENCYS_ID)));
        
        this.getField(Booking.PAX).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.PAX), true, true));
        this.getField(Booking.SINGLE_PAX).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.SINGLE_PAX), true, true));
        this.getField(Booking.DOUBLE_PAX).addListener(new InitFieldHandler(recBookingControl.getField(BookingControl.DOUBLE_PAX), true, true));
        
        if (recBookingControl.getField(BookingControl.AUTO_BOOKING_CODE).getState() == true)
            this.addListener(new MoveIDToCodeHandler(Booking.CODE)); // If auto-booking numbers are turned on, set them
        return recBookingControl;
    }
    /**
     * AddSecondProfile Method.
     */
    public void addSecondProfile(Record recProfile)
    {
        ReadSecondaryHandler behavior = new ReadSecondaryHandler(recProfile, Profile.PROFILE_CODE_KEY);
        behavior.setRespondsToMode(DBConstants.READ_MOVE, false);
        this.getField(Booking.PROFILE_CODE).addListener(behavior);
        
        behavior.addFieldSeqPair(Booking.PROFILE_ID, Profile.ID, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.PROFILE_CODE, Profile.PROFILE_CODE, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.GENERIC_NAME, Profile.GENERIC_NAME, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.ADDRESS_LINE_1, Profile.ADDRESS_LINE_1, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.ADDRESS_LINE_2, Profile.ADDRESS_LINE_2, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.CITY_OR_TOWN, Profile.CITY_OR_TOWN, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.COUNTRY, Profile.COUNTRY, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.STATE_OR_REGION, Profile.STATE_OR_REGION, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.POSTAL_CODE, Profile.POSTAL_CODE, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.TEL, Profile.TEL, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.FAX, Profile.FAX, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.EMAIL, Profile.EMAIL, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.LANGUAGE_ID, Profile.PRIMARY_LANGUAGE_ID, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        behavior.addFieldSeqPair(Booking.CURRENCYS_ID, Profile.CURRENCYS_ID, DBConstants.MOVE_TO_DEPENDENT, DBConstants.MOVE_DEPENDENT_BACK);
        // Only move contact if not blank
        Converter convTrueIfContactNotBlank = new RadioConverter(this.getField(Booking.CONTACT), DBConstants.BLANK, true);
        recProfile.addListener(new RemoveConverterOnCloseHandler(convTrueIfContactNotBlank));
        MoveOnValidHandler moveHandler = behavior.addFieldSeqPair(Booking.CONTACT, Profile.CONTACT, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, convTrueIfContactNotBlank, null);
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
            recBookingAnswer = new BookingAnswer(this.findRecordOwner());
            recBookingAnswerNew = recBookingAnswer;
            iErrorCode = recBookingAnswer.setupAnswerDetail(TourHeaderOption.TOUR, recTourHeader.getField(TourHeader.ID), recTourHeader.getField(TourHeader.ID), this, recBookingPax, dateStart, fldAskForAnswer, true);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
                return iErrorCode;
        }
        
        BaseField fldTourModuleID = recTourHeader.getField(TourHeader.ID);
        try {
            if (recBookingPax == null)
            {
                recBookingPax = new BookingPax(this.findRecordOwner());
                recBookingPaxNew = recBookingPax;
            }
            if (recBookingPax.getField(BookingPax.ID).isNull())
                recBookingPax.getField(BookingPax.ID).setValue(0);
        
            recBookingAnswer.setKeyArea(BookingAnswer.SCAN_ORDER_KEY);
            recBookingAnswer.addListener(listener = new SubFileFilter(this.getField(Booking.ID), BookingAnswer.BOOKING_ID, recBookingPax.getField(BookingPax.ID), BookingAnswer.BOOKING_PAX_ID, fldTourModuleID, BookingAnswer.MODULE_ID));
        //+++    recBookingAnswer.addListener(listener2 = new SubFileFilter(dateStart, BookingAnswer.MODULE_START_DATE, null, -1, null, -1));
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
            fldBookingPaxID = recBookingPax.getField(BookingPax.ID);
        else
        {
            fldBookingPaxID = new IntegerField(null, "ID", -1, null, null);
            fldBookingPaxID.setData(new Integer(0));
        }
        BookingAnswer recBookingAnswer = new BookingAnswer(this.findRecordOwner());
        recBookingAnswer.addDetailBehaviors(this, recTour);
        int iErrorCode = recBookingAnswer.changeAllDetail(this, fldBookingPaxID, recTourHeader.getField(TourHeader.ID), dateOriginal, dateStart);
        recBookingAnswer.free();
        recBookingAnswer = null;    // The makes answers re-resolve
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        
        iErrorCode = this.deleteTourDetail(recTour, recBookingPax, recTourHeader.getField(TourHeader.ID), dateOriginal);
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
            fldBookingPaxID = recBookingPax.getField(BookingPax.ID);
        else
        {
            fldBookingPaxID = new IntegerField(null, "ID", -1, null, null);
            fldBookingPaxID.setData(new Integer(0));
        }
        BookingAirHeader recBookingAirHeader = new BookingAirHeader(this.findRecordOwner());
        recBookingAirHeader.addDetailBehaviors(this, recTour);
        int iErrorCode = recBookingAirHeader.deleteAllDetail(this, fldBookingPaxID, fldTourModuleID, dateStart);
        recBookingAirHeader.free();
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        
        BookingLine recBookingLine = new BookingLine(this.findRecordOwner());
        recBookingLine.addDetailBehaviors(this, recTour);
        iErrorCode = recBookingLine.deleteAllDetail(this, fldBookingPaxID, fldTourModuleID, dateStart);
        recBookingLine.free();
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        
        BookingDetail recBookingDetail = new BookingDetail(this.findRecordOwner());
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
            recBookingDetails = new BookingDetail(this.findRecordOwner());
            recBookingDetails.addDetailBehaviors(this, recTour);
            
            while (recBookingDetails.hasNext())
            {
                BookingDetail recBookingDetail = (BookingDetail)recBookingDetails.next();
                
                BaseField fldDetailModuleID = recBookingDetail.getField(BookingDetail.MODULE_ID);
                Date dateDetailStart = ((DateTimeField)recBookingDetail.getField(BookingDetail.MODULE_START_DATE)).getDateTime();
                if (recBookingDetail.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.TOUR_ID)
                {
                    fldDetailModuleID = recBookingDetail.getField(BookingDetail.PRODUCT_ID);
                    dateDetailStart = ((DateTimeField)recBookingDetail.getField(BookingDetail.DETAIL_DATE)).getDateTime();                    
                }
                if ((fldTourModuleID != null) && (!fldTourModuleID.equals(fldDetailModuleID)))
                    continue;
                if ((dateStart != null) && (!dateStart.equals(dateDetailStart)))
                    continue;
        
                int iTourPricingType = this.getTourPricingType(recTour, fldDetailModuleID, dateDetailStart);
        
                boolean bPricingListenerState = true;
                if (this.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) != null)
                    bPricingListenerState = this.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).setEnabledListener(false);
                if (bRecost)
                {
                    boolean bListenerState = true;
                    if (recBookingDetail.getListener(BookingDetailPriceChangeHandler.class) != null)
                        bListenerState = recBookingDetail.getListener(BookingDetailPriceChangeHandler.class).setEnabledListener(false);
                    recBookingDetail.edit();
                    recBookingDetail.getField(BookingDetail.INFO_STATUS_REQUEST).setState(true); // Information changed, recost if required.
                    recBookingDetail.set();
                    if (recBookingDetail.getListener(BookingDetailPriceChangeHandler.class) != null)
                        recBookingDetail.getListener(BookingDetailPriceChangeHandler.class).setEnabledListener(bListenerState);
                }
                boolean bTourDetailPricingType = !recBookingDetail.getField(BookingDetail.TOUR_HEADER_OPTION_ID).isNull();
                if (iTourPricingType == PricingType.OPTION_PRICING)
                    if (bTourDetailPricingType)
                {
                    if (this.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) != null)
                        this.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).setEnabledListener(bPricingListenerState);
                    continue;   // I'm going to add the price for this in the next step
                }
                recBookingDetail.getField(BookingDetail.TOTAL_COST_LOCAL).setModified(true); // This will fake a cost change
                recBookingDetail.handlePriceChange(DBConstants.UPDATE_TYPE);
                if (this.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) != null)
                    this.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).setEnabledListener(bPricingListenerState);
            }
        
            int iTourPricingType = this.getTourPricingType(recTour, fldTourModuleID, dateStart);
            if (iTourPricingType == PricingType.OPTION_PRICING)
            {
                BaseField fldBookingPaxID = new IntegerField(null, "ID", -1, null, null);
                recBookingAnswer = new BookingAnswer(this.findRecordOwner());
                TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReference();
                BookingPax recBookingPax = new BookingPax(this.findRecordOwner());
                recBookingPax.getField(BookingPax.ID).setValue(0);
                recTourHeaderPricing = new TourHeaderLine(this.findRecordOwner());
                Date dateDeparture = ((DateField)recTour.getField(Tour.DEPARTURE_DATE)).getDateTime();
                recBookingAnswer.addDetailBehaviors(this, recTour);
                while (recBookingAnswer.hasNext())
                {
                    recBookingAnswer.next();
                    
                    BaseField fldDetailModuleID = recBookingAnswer.getField(BookingAnswer.MODULE_ID);
                    Date dateDetailStart = ((DateTimeField)recBookingAnswer.getField(BookingAnswer.MODULE_START_DATE)).getDateTime();
                    if ((fldTourModuleID != null) && (!fldTourModuleID.equals(fldDetailModuleID)))
                        continue;
                    if ((dateStart != null) && (!dateStart.equals(dateDetailStart)))
                        continue;
        
                    boolean bSetupDetail = false;
                    if (recBookingAnswer.getField(BookingAnswer.SELECTED).getState() == true)
                        if (recBookingAnswer.getField(BookingAnswer.DETAIL_PRICE_EXISTS).getState() == true)
                    {
                        if ((recTour.getField(Tour.TOUR_HEADER_ID).equals(fldDetailModuleID))
                            && (recTour.getField(Tour.DEPARTURE_DATE).equals(recBookingAnswer.getField(BookingAnswer.MODULE_START_DATE))))
                                bSetupDetail = true;
                        else
                        {
                            if (this.getTourPricingType(recTour, fldDetailModuleID, dateDetailStart) == PricingType.OPTION_PRICING)
                                bSetupDetail = true;                                
                        }
                    }
                    if (bSetupDetail)
                        iErrorCode = recBookingLine.setupAllDetail(recTourHeaderPricing, this, recTour, recBookingPax.getField(BookingPax.ID), recBookingAnswer.getField(BookingAnswer.TOUR_HEADER_OPTION_ID), fldDetailModuleID, dateDetailStart);
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
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.TOUR_CLASS_ID)).getReference();
        recTourClass.fixBasedFields();
        Calendar depositDate = ((DateTimeField)this.getField(Booking.BOOKING_DATE)).getCalendar();
        // Add deposit due days to booking date
        depositDate.add(Calendar.DATE, (int)recTourClass.getField(TourClass.DEPOSIT_DUE_DAYS).getValue());
        
        Calendar finalDate = ((DateTimeField)recTour.getField(Tour.DEPARTURE_DATE)).getCalendar();
        // Subtract Final payment days from departure date.
        finalDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.FINAL_PAY_DAYS).getValue());
        
        Calendar calCurrentTime = Calendar.getInstance();
        if (finalDate.before(calCurrentTime))
            finalDate = calCurrentTime; 
        ((DateTimeField)this.getField(Booking.FINAL_PAYMENT_DUE_DATE)).setCalendar(finalDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        if (depositDate.after(finalDate))
            depositDate = finalDate;
        ((DateTimeField)this.getField(Booking.DEPOSIT_DUE_DATE)).setCalendar(depositDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        if (!recTourClass.getField(TourClass.INITIAL_BOOKING_STATUS_ID).isNull())
            this.getField(Booking.BOOKING_STATUS_ID).moveFieldToThis(recTourClass.getField(TourClass.INITIAL_BOOKING_STATUS_ID));
        if (!recTourClass.getField(TourClass.MARKUP).isNull())
            this.getField(Booking.MARKUP).moveFieldToThis(recTourClass.getField(TourClass.MARKUP));
        if (!recTourClass.getField(TourClass.TOUR_PRICING_TYPE_ID).isNull())
            this.getField(Booking.TOUR_PRICING_TYPE_ID).moveFieldToThis(recTourClass.getField(TourClass.TOUR_PRICING_TYPE_ID));
        if (!recTourClass.getField(TourClass.NON_TOUR_PRICING_TYPE_ID).isNull())
            this.getField(Booking.NON_TOUR_PRICING_TYPE_ID).moveFieldToThis(recTourClass.getField(TourClass.NON_TOUR_PRICING_TYPE_ID));
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
            recBookingLine = new BookingLine(this.findRecordOwner());
            this.addListener(new FreeOnFreeHandler(recBookingLine));
        }
        if (recBookingLine.getListener(SubFileFilter.class) == null)
            recBookingLine.addDetailBehaviors(this, (Tour)((ReferenceField)this.getField(Booking.TOUR_ID)).getReferenceRecord());
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
            recArTrx = new ArTrx(this.findRecordOwner());
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
            if (this.getField(Booking.PAX).getValue() > 0)
            {   // Get last name from pax list
                BookingPax recBookingPax = new BookingPax(this.findRecordOwner());
                try {
                    recBookingPax.addListener(new SubFileFilter(this));
                    if (recBookingPax.next() != null)
                        strDesc = recBookingPax.getField(BookingPax.SUR_NAME).toString();
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    recBookingPax.free();
                }
            } 
            else if (!this.getField(Booking.GENERIC_NAME).isNull())
                strDesc = this.getField(Booking.GENERIC_NAME).toString();
            else
                strDesc = this.getField(Booking.CONTACT).toString();
        }
        if (strDesc.length() > 0)
            strDesc += " - ";
        String strTourDesc = recTourHeader.getField(TourHeader.DESCRIPTION).toString();
        String strDate = fldDepDate.toString();
        int iLen = strDesc.length() + strTourDesc.length() + 3 + strDate.length();
        if (iLen > this.getField(Booking.DESCRIPTION).getMaxLength())
            strTourDesc = strTourDesc.substring(0, strTourDesc.length() - Math.max(0, Math.max(0, iLen - this.getField(Booking.DESCRIPTION).getMaxLength())));
        if (strTourDesc.length() > 0)
            strDesc += strTourDesc + " - ";
        strDesc += strDate;
        int iOldOpenMode = this.getOpenMode();
        this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        boolean[] rgbEnabled = this.getField(Booking.DESCRIPTION).setEnableListeners(false);
        this.getField(Booking.DESCRIPTION).setString(strDesc);
        this.getField(Booking.DESCRIPTION).setEnableListeners(rgbEnabled);
        this.setOpenMode(iOldOpenMode);
        return strDesc;
    }
    /**
     * Get number of pax.
     */
    public int getCountPax()
    {
        return (int)this.getField(Booking.PAX).getValue();
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
        Record recTour = ((ReferenceField)this.getField(Booking.TOUR_ID)).getReference();
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
                recTour = (Tour)((ReferenceField)this.getField(Booking.TOUR_ID)).getReference();
            if (!fldTourModuleID.equals(recTour.getCounterField()))
            {   // This is a module, get the pricing type for this module
                BookingDetail recBookingDetailModule = m_recBookingDetailModule;
                boolean bFound = false;
                if ((fldTourModuleID.getRecord() instanceof BookingDetail) && (fldTourModuleID == fldTourModuleID.getRecord().getField(BookingDetail.PRODUCT_ID)))
                {
                    bFound = true;
                    recBookingDetailModule = (BookingDetail)fldTourModuleID.getRecord();
                    if (m_recBookingDetailModule != null)
                        if (recBookingDetailModule.getField(BookingDetail.ID).equals(m_recBookingDetailModule.getField(BookingDetail.ID)))
                        if (!recBookingDetailModule.getField(BookingDetail.PRICING_TYPE_ID).equals(m_recBookingDetailModule.getField(BookingDetail.PRICING_TYPE_ID)))
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
                    m_recBookingDetailModule = new BookingDetail(this.findRecordOwner());
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
                            && (recBookingDetailModule.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.TOUR_ID)
                            && (recBookingDetailModule.getField(BookingDetail.PRODUCT_ID).equals(fldTourModuleID))
                            && (dateStart.equals(((DateTimeField)recBookingDetailModule.getField(BookingDetail.DETAIL_DATE)).getDateTime()))
                            && (this.getField(Booking.ID).equals(recBookingDetailModule.getField(BookingDetail.BOOKING_ID))))
                                bFound = true;
                        else
                        {
                            recBookingDetailModule.getField(BookingDetail.PRODUCT_TYPE_ID).setValue(ProductType.TOUR_ID);
                            recBookingDetailModule.addNew();
                            recBookingDetailModule.setKeyArea(BookingDetail.PRODUCT_ID_KEY);
                            recBookingDetailModule.getField(BookingDetail.PRODUCT_ID).moveFieldToThis(fldTourModuleID);
                            ((DateTimeField)recBookingDetailModule.getField(BookingDetail.DETAIL_DATE)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                            recBookingDetailModule.getField(BookingDetail.BOOKING_ID).moveFieldToThis(this.getField(Booking.ID));
                            bFound = recBookingDetailModule.seek(DBConstants.EQUALS);
                        }
                    }
                    if (bFound)
                    {
                        recPricingType = ((ReferenceField)recBookingDetailModule.getField(BookingDetail.PRICING_TYPE_ID)).getReference();
                        if (recPricingType != null)
                            iTourPricingType = (int)recPricingType.getField(PricingType.PRICING_CODES).getValue();
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }
        if (iTourPricingType == 0)
            recPricingType = ((ReferenceField)this.getField(Booking.TOUR_PRICING_TYPE_ID)).getReference();
        
        if (recPricingType != null)
            iTourPricingType = (int)recPricingType.getField(PricingType.PRICING_CODES).getValue();
        
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
            Object objID = properties.get(TourHeader.TOUR_HEADER_FILE);
            if (objID instanceof Integer)
            { // Always
                Tour recTour = (Tour)((ReferenceField)this.getField(Booking.TOUR_ID)).getReference();
                TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReferenceRecord(this.findRecordOwner());
                try {
                    recTourHeader.addNew();
                    recTourHeader.setKeyArea(Tour.ID_KEY);
                    recTourHeader.getCounterField().setData(objID);
                    if (recTourHeader.seek(DBConstants.EQUALS))
                    {
                        Date dateStart = (Date)properties.get(recTour.getField(Tour.DEPARTURE_DATE).getFieldName());
                        BookingAnswer recBookingAnswer = null;  // This causes addTourDetail to resolve the answers automatically
                        BookingPax recBookingPax = null;
                        int iErrorCode = this.addTourDetail(recTour, recTourHeader, recBookingPax, recBookingAnswer, dateStart, this.getField(Booking.ASK_FOR_ANSWER));
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
