/**
 *  @(#)BookingLine.
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
import com.tourapp.tour.booking.entry.acctrec.*;
import java.util.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.history.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  BookingLine - Booking Line Items.
 */
public class BookingLine extends BookingSub
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kBookingID = kBookingID;
    //public static final int kBookingPaxID = kBookingPaxID;
    //public static final int kModuleID = kModuleID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kModuleStartDate = kModuleStartDate;
    //public static final int kDescription = kDescription;
    public static final int kSequence = kBookingSubLastField + 1;
    public static final int kPrice = kSequence + 1;
    public static final int kQuantity = kPrice + 1;
    public static final int kGross = kQuantity + 1;
    public static final int kCommissionable = kGross + 1;
    public static final int kCommissionRate = kCommissionable + 1;
    public static final int kCommission = kCommissionRate + 1;
    public static final int kNet = kCommission + 1;
    public static final int kPricingStatusID = kNet + 1;
    public static final int kPayAt = kPricingStatusID + 1;
    public static final int kPaxGroups = kPayAt + 1;
    public static final int kBookingDetailID = kPaxGroups + 1;
    public static final int kPaxCategoryID = kBookingDetailID + 1;
    public static final int kBookingLineLastField = kPaxCategoryID;
    public static final int kBookingLineFields = kPaxCategoryID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDetailAccessKey = kIDKey + 1;
    public static final int kBookingIDKey = kDetailAccessKey + 1;
    public static final int kBookingDetailIDKey = kBookingIDKey + 1;
    public static final int kBookingLineLastKey = kBookingDetailIDKey;
    public static final int kBookingLineKeys = kBookingDetailIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingLine()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingLine(RecordOwner screen)
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

    public static final String kBookingLineFile = "BookingLine";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingLineFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Line item";
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
            screen = new BookingLineScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new BookingLineGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kBookingID)
        //  field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kBookingPaxID)
        //  field = new BookingPaxField(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSequence)
            field = new ShortField(this, "Sequence", 4, null, new Short((short)9999));
        //if (iFieldSeq == kModuleID)
        //  field = new TourHeaderField(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTourHeaderOptionID)
        //  field = new TourHeaderOptionField(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kModuleStartDate)
        //  field = new DateTimeField(this, "ModuleStartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 60, null, null);
        if (iFieldSeq == kPrice)
            field = new CurrencyField(this, "Price", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kQuantity)
            field = new ShortField(this, "Quantity", 3, null, null);
        if (iFieldSeq == kGross)
        {
            field = new CurrencyField(this, "Gross", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kCommissionable)
            field = new BooleanField(this, "Commissionable", 1, null, new Boolean(true));
        if (iFieldSeq == kCommissionRate)
            field = new BookingLine_CommissionRate(this, "CommissionRate", 5, null, null);
        if (iFieldSeq == kCommission)
        {
            field = new CurrencyField(this, "Commission", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kNet)
        {
            field = new CurrencyField(this, "Net", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPricingStatusID)
            field = new PricingStatusField(this, "PricingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPayAt)
            field = new PayAtField(this, "PayAt", 1, null, "PayAtField.FINAL_PAY_DATE");
        if (iFieldSeq == kPaxGroups)
            field = new IntegerField(this, "PaxGroups", 8, null, null);
        if (iFieldSeq == kBookingDetailID)
            field = new ReferenceField(this, "BookingDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPaxCategoryID)
            field = new PaxBaseCategoryField(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingLineLastField)
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
            keyArea.addKeyField(kSequence, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBookingDetailIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingDetailID");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingPaxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingDetailID, DBConstants.ASCENDING);
            keyArea.addKeyField(kPaxCategoryID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingLineLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingLineLastKey)
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
        this.getField(BookingLine.kPrice).addListener(new CalcLineFieldsHandler(null));
        this.getField(BookingLine.kQuantity).addListener(new CalcLineFieldsHandler(null));
        this.getField(BookingLine.kCommissionRate).addListener(new CalcLineFieldsHandler(null));
        this.getField(BookingLine.kPricingStatusID).addListener(new InitFieldHandler(Integer.toString(PricingStatus.OKAY)));
        
        Booking recBooking = this.getBooking(false);
        if (recBooking != null)
            this.addSubListeners(recBooking);
    }
    /**
     * AddSlaveListeners Method.
     */
    public void addSlaveListeners()
    {
        super.addSlaveListeners();
        HistoryHandler histBehavior = new HistoryHandler(BookingLineHistory.class.getName(), BookingLineHistory.kHistoryDate, -1);
        this.addListener(histBehavior);
    }
    /**
     * AddDetailBehaviors Method.
     */
    public void addDetailBehaviors(Booking recBooking, Tour recTour)
    {
        super.addDetailBehaviors(recBooking, recTour);
        if (recBooking != null)
        {
            this.addListener(new SubCountHandler(recBooking.getField(Booking.kGross), BookingLine.kGross, true, true));
            this.addListener(new SubCountHandler(recBooking.getField(Booking.kCommission), BookingLine.kCommission, true, true));
            this.addListener(new SubCountHandler(recBooking.getField(Booking.kNet), BookingLine.kNet, true, true));
        
            recBooking.getField(Booking.kNet).addListener(new SetDirtyOnChangeHandler(recBooking.getField(Booking.kBookingStatusID), true, true));  // This makes sure the booking will update which will trigger an A/R update
            recBooking.addListener(new UpdateOnCloseHandler(null));
        
            this.addSubListeners(recBooking);
        }
    }
    /**
     * For this tour detail, move all the detail field to the current booking line item detail.
     */
    public int setDetailProductFields(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        super.setDetailProductFields(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
        this.getField(BookingLine.kSequence).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.kSequence));
        this.getField(BookingLine.kDescription).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.kDescription));
        this.getField(BookingLine.kPrice).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.kPrice));
        
        Record recPaxCategory = ((ReferenceField)recTourHeaderDetail.getField(TourHeaderLine.kPaxCategoryID)).getReference();
        if (recPaxCategory != null)
        {
            int iPaxCat = (int)recPaxCategory.getField(PaxCategory.kRoomType).getValue();
            if (iPaxCat == PaxCategory.SINGLE_ID)
                this.getField(BookingLine.kQuantity).moveFieldToThis(recBooking.getField(Booking.kSinglePax));
            else if (iPaxCat == PaxCategory.DOUBLE_ID)
                this.getField(BookingLine.kQuantity).moveFieldToThis(recBooking.getField(Booking.kDoublePax));
            else if (iPaxCat == PaxCategory.TRIPLE_ID)
                this.getField(BookingLine.kQuantity).moveFieldToThis(recBooking.getField(Booking.kTriplePax));
            else if (iPaxCat == PaxCategory.QUAD_ID)
                this.getField(BookingLine.kQuantity).moveFieldToThis(recBooking.getField(Booking.kQuadPax));
            else if (iPaxCat == PaxCategory.CHILD_ID)
                this.getField(BookingLine.kQuantity).moveFieldToThis(recBooking.getField(Booking.kChildren));
            else if ((iPaxCat == 0) || (iPaxCat == PaxCategory.ALL_ID))
                this.getField(BookingLine.kQuantity).moveFieldToThis(recBooking.getField(Booking.kPax));
        }
        this.getField(BookingLine.kCommissionable).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.kCommissionable));
        if ((recTourHeaderDetail.getField(TourHeaderLine.kCommissionable).getState() == true) || (recTourHeaderDetail.getField(TourHeaderLine.kCommissionRate).isNull()))
            this.getField(BookingLine.kCommissionRate).moveFieldToThis(recBooking.getField(Booking.kStdCommission));
        else
            this.getField(BookingLine.kCommissionRate).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.kCommissionRate));
        this.getField(BookingLine.kPayAt).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.kPayAt));
        if (this.getField(BookingLine.kQuantity).getValue() == 0)
            return DBConstants.ERROR_RETURN;    // This is not an error, it just tells the caller not to write this record.
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Add the listeners that require the booking (main) record.
     */
    public void addSubListeners(Booking recBooking)
    {
        if (this.getListener(HistoryHandler.class.getName()) == null)
        {
            this.addListener(new SequenceHandler(this.getField(BookingLine.kSequence), recBooking.getField(Booking.kNextLineSequence)));
        
            this.getField(BookingLine.kQuantity).addListener(new InitFieldHandler(recBooking.getField(Booking.kPax)));
            this.getField(BookingLine.kCommissionRate).addListener(new InitFieldHandler(recBooking.getField(Booking.kStdCommission)));
            this.getField(BookingLine.kGross).setEnabled(false);
            this.getField(BookingLine.kCommission).setEnabled(false);
            this.getField(BookingLine.kNet).setEnabled(false);
        }
    }
    /**
     * DeleteAllDetail Method.
     */
    public int deleteAllDetail(Booking recBooking, BaseField fldBookingPaxID, BaseField fldTourModuleID, Date dateStart)
    {
        CompareFileFilter filter = new CompareFileFilter(BookingLine.kPricingStatusID, Integer.toString(PricingStatus.MANUAL), FileListener.NOT_EQUAL, null, true);
        this.addListener(filter);   // Don't delete manually entered items
        int iErrorCode = super.deleteAllDetail(recBooking, fldBookingPaxID, fldTourModuleID, dateStart);
        this.removeListener(filter, true);
        return iErrorCode;
    }
    /**
     * Get the icon that goes with this file.
     */
    public String getBitmap()
    {
        return Booking.BUTTON_LOCATION + "Price";
    }

}
