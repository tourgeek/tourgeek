/**
 * @(#)BookingLine.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.tour.booking.entry.acctrec.*;
import java.util.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.history.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingLine - Booking Line Items.
 */
public class BookingLine extends BookingSub
     implements BookingLineModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_LINE_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_LINE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_LINE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
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
        if (iFieldSeq == 9)
            field = new StringField(this, DESCRIPTION, 60, null, null);
        if (iFieldSeq == 10)
        {
            field = new StringField(this, PRODUCT_TYPE, 15, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 11)
        //  field = new StringField(this, REMOTE_REFERENCE_NO, 60, null, null);
        if (iFieldSeq == 12)
            field = new ShortField(this, SEQUENCE, 4, null, new Short((short)9999));
        if (iFieldSeq == 13)
            field = new CurrencyField(this, PRICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new ShortField(this, QUANTITY, 3, null, null);
        if (iFieldSeq == 15)
        {
            field = new CurrencyField(this, GROSS, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 16)
            field = new BooleanField(this, COMMISSIONABLE, 1, null, new Boolean(true));
        if (iFieldSeq == 17)
            field = new BookingLine_CommissionRate(this, COMMISSION_RATE, 5, null, null);
        if (iFieldSeq == 18)
        {
            field = new CurrencyField(this, COMMISSION, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 19)
        {
            field = new CurrencyField(this, NET, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 20)
            field = new PricingStatusField(this, PRICING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new PayAtField(this, PAY_AT, 1, null, "PayAtField.FINAL_PAY_DATE");
        if (iFieldSeq == 22)
            field = new IntegerField(this, PAX_GROUPS, 8, null, null);
        if (iFieldSeq == 23)
            field = new ReferenceField(this, BOOKING_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 24)
            field = new PaxBaseCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingID");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(SEQUENCE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DetailAccess");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_HEADER_DETAIL_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_START_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingDetailID");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_DETAIL_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(PAX_CATEGORY_ID, DBConstants.ASCENDING);
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
        this.getField(BookingLine.PRICE).addListener(new CalcLineFieldsHandler(null));
        this.getField(BookingLine.QUANTITY).addListener(new CalcLineFieldsHandler(null));
        this.getField(BookingLine.COMMISSION_RATE).addListener(new CalcLineFieldsHandler(null));
        this.getField(BookingLine.PRICING_STATUS_ID).addListener(new InitFieldHandler(Integer.toString(PricingStatus.OKAY)));
        
        Booking recBooking = (Booking)this.getBooking(false);
        if (recBooking != null)
            this.addSubListeners(recBooking);
    }
    /**
     * AddSlaveListeners Method.
     */
    public void addSlaveListeners()
    {
        super.addSlaveListeners();
        HistoryHandler histBehavior = new HistoryHandler(BookingLineHistory.class.getName(), BookingLineHistory.HISTORY_DATE, null);
        this.addListener(histBehavior);
    }
    /**
     * AddDetailBehaviors Method.
     */
    public void addDetailBehaviors(BookingModel recBooking, TourModel recTour)
    {
        super.addDetailBehaviors(recBooking, recTour);
        if (recBooking != null)
        {
            this.addListener(new SubCountHandler((BaseField)recBooking.getField(Booking.GROSS), BookingLine.GROSS, true, true));
            this.addListener(new SubCountHandler((BaseField)recBooking.getField(Booking.COMMISSION), BookingLine.COMMISSION, true, true));
            this.addListener(new SubCountHandler((BaseField)recBooking.getField(Booking.NET), BookingLine.NET, true, true));
        
            ((Record)recBooking.getField(Booking.NET)).addListener(new SetDirtyOnChangeHandler((BaseField)recBooking.getField(Booking.BOOKING_STATUS_ID), true, true));  // This makes sure the booking will update which will trigger an A/R update
            ((Record)recBooking).addListener(new UpdateOnCloseHandler(null));
        
            this.addSubListeners((Booking)recBooking);
        }
    }
    /**
     * For this tour detail, move all the detail field to the current booking line item detail.
     */
    public int setDetailProductFields(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        super.setDetailProductFields(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
        this.getField(BookingLine.SEQUENCE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.SEQUENCE));
        this.getField(BookingLine.DESCRIPTION).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.DESCRIPTION));
        this.getField(BookingLine.PRICE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.PRICE));
        
        Record recPaxCategory = ((ReferenceField)recTourHeaderDetail.getField(TourHeaderLine.PAX_CATEGORY_ID)).getReference();
        if (recPaxCategory != null)
        {
            int iPaxCat = (int)recPaxCategory.getField(PaxCategory.ROOM_TYPE).getValue();
            if (iPaxCat == PaxCategory.SINGLE_ID)
                this.getField(BookingLine.QUANTITY).moveFieldToThis(recBooking.getField(Booking.SINGLE_PAX));
            else if (iPaxCat == PaxCategory.DOUBLE_ID)
                this.getField(BookingLine.QUANTITY).moveFieldToThis(recBooking.getField(Booking.DOUBLE_PAX));
            else if (iPaxCat == PaxCategory.TRIPLE_ID)
                this.getField(BookingLine.QUANTITY).moveFieldToThis(recBooking.getField(Booking.TRIPLE_PAX));
            else if (iPaxCat == PaxCategory.QUAD_ID)
                this.getField(BookingLine.QUANTITY).moveFieldToThis(recBooking.getField(Booking.QUAD_PAX));
            else if (iPaxCat == PaxCategory.CHILD_ID)
                this.getField(BookingLine.QUANTITY).moveFieldToThis(recBooking.getField(Booking.CHILDREN));
            else if ((iPaxCat == 0) || (iPaxCat == PaxCategory.ALL_ID))
                this.getField(BookingLine.QUANTITY).moveFieldToThis(recBooking.getField(Booking.PAX));
        }
        this.getField(BookingLine.COMMISSIONABLE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.COMMISSIONABLE));
        if ((recTourHeaderDetail.getField(TourHeaderLine.COMMISSIONABLE).getState() == true) || (recTourHeaderDetail.getField(TourHeaderLine.COMMISSION_RATE).isNull()))
            this.getField(BookingLine.COMMISSION_RATE).moveFieldToThis(recBooking.getField(Booking.STD_COMMISSION));
        else
            this.getField(BookingLine.COMMISSION_RATE).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.COMMISSION_RATE));
        this.getField(BookingLine.PAY_AT).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderLine.PAY_AT));
        if (this.getField(BookingLine.QUANTITY).getValue() == 0)
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
            this.addListener(new SequenceHandler(this.getField(BookingLine.SEQUENCE), recBooking.getField(Booking.NEXT_LINE_SEQUENCE)));
        
            this.getField(BookingLine.QUANTITY).addListener(new InitFieldHandler(recBooking.getField(Booking.PAX)));
            this.getField(BookingLine.COMMISSION_RATE).addListener(new InitFieldHandler(recBooking.getField(Booking.STD_COMMISSION)));
            this.getField(BookingLine.GROSS).setEnabled(false);
            this.getField(BookingLine.COMMISSION).setEnabled(false);
            this.getField(BookingLine.NET).setEnabled(false);
        }
    }
    /**
     * DeleteAllDetail Method.
     */
    public int deleteAllDetail(Booking recBooking, BaseField fldBookingPaxID, BaseField fldTourModuleID, Date dateStart)
    {
        CompareFileFilter filter = new CompareFileFilter(BookingLine.PRICING_STATUS_ID, Integer.toString(PricingStatus.MANUAL), FileListener.NOT_EQUAL, null, true);
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
