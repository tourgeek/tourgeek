/**
 * @(#)BookingAnswer.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.thin.base.db.buff.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.model.tour.booking.db.*;

/**
 *  BookingAnswer - Booking Answer File.
 */
public class BookingAnswer extends BookingSub
     implements BookingAnswerModel
{
    private static final long serialVersionUID = 1L;

    protected BookingAirHeader m_recBookingAirHeader = null;
    protected BookingDetail m_recBookingDetail = null;
    protected BookingLine m_recBookingLine = null;
    protected TourHeaderAirHeader m_recTourHeaderAirHeader = null;
    protected TourHeaderDetail m_recTourHeaderDetail = null;
    protected TourHeaderLine m_recTourHeaderPricing = null;
    /**
     * Default constructor.
     */
    public BookingAnswer()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingAnswer(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recBookingAirHeader = null;
        m_recBookingDetail = null;
        m_recBookingLine = null;
        m_recTourHeaderAirHeader = null;
        m_recTourHeaderDetail = null;
        m_recTourHeaderPricing = null;
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_ANSWER_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour options";
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
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_ANSWER_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_ANSWER_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new StringField(this, TOUR_OR_OPTION, 1, null, null);
        if (iFieldSeq == 13)
            field = new ReferenceField(this, TOUR_OR_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new ShortField(this, SEQUENCE, 4, null, null);
        if (iFieldSeq == 15)
            field = new BooleanField(this, ASK_FOR_ANSWER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new BooleanField(this, ALWAYS_RESOLVE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new BooleanField(this, DETAIL_OPTION_EXISTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new BooleanField(this, DETAIL_PRICE_EXISTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new BooleanField(this, DETAIL_AIR_HEADER_EXISTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new BooleanField(this, TOUR_DETAIL_EXISTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new BooleanField(this, SELECTED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 22)
            field = new BooleanField(this, DETAIL_ADDED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, "BookingID");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_START_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_OR_OPTION, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_OR_OPTION_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(SEQUENCE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ScanOrder");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(BOOKING_PAX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(MODULE_START_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Free Method.
     */
    public void free()
    {
            if (m_recBookingDetail != null)
            {
                m_recBookingDetail.free();
                m_recBookingDetail = null;
                m_recTourHeaderDetail.free();
                m_recTourHeaderDetail = null;
            }
            if (m_recBookingAirHeader != null)
            {
                m_recBookingAirHeader.free();
                m_recBookingAirHeader = null;
                m_recTourHeaderAirHeader.free();
                m_recTourHeaderAirHeader = null;
            }
            if (m_recBookingLine != null)
            {
                m_recBookingLine.free();
                m_recBookingLine = null;
                m_recTourHeaderPricing.free();
                m_recTourHeaderPricing = null;
            }
        super.free();
    }
    /**
     * Add all the valid answers to my option list.
     * @param strTourOrOption Tour or Option to scan (since this is recursive).
     * @param fldTourOrOptionID Tour or option to scan the detail of.
     * @param fldTourHeaderID The Module/Tour Header
     * @param recBooking The booking
     * @param recBookingPax The booking pax that this applies to (or null if all pax)
     * @param dateStart The module start date
     * @param bSelect Set the select state of these records to (if they are not questions).
     */
    public int setupAnswerDetail(String strTourOrOption, BaseField fldTourOrOptionID, BaseField fldTourHeaderID, Booking recBooking, BookingPax recBookingPax, Date dateStart, BaseField
     fldAskForAnswer, boolean bSelect)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        TourHeaderOption recTourHeaderOption = new TourHeaderOption(this.findRecordOwner());
        try   {
            recTourHeaderOption.setKeyArea(TourHeaderOption.TOUR_OR_OPTION_KEY);
            recTourHeaderOption.addListener(new StringSubFileFilter(strTourOrOption, TourHeaderOption.TOUR_OR_OPTION, fldTourOrOptionID.getData().toString(), TourHeaderOption.TOUR_OR_OPTION_ID, null, null));
            recTourHeaderOption.close();
            while (recTourHeaderOption.hasNext())
            {
                recTourHeaderOption.next();
                BaseField fldPaxClass = null;
                if (recBookingPax != null)
                    if ((recBookingPax.getEditMode() == DBConstants.EDIT_CURRENT) || (recBookingPax.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                        fldPaxClass = recBookingPax.getField(BookingPax.PAX_CATEGORY_ID);
                if (recTourHeaderOption.isValid(recBooking, fldPaxClass, dateStart))
                {
                    this.addNew();
                    this.getField(BookingAnswer.BOOKING_ID).moveFieldToThis(recBooking.getField(Booking.ID));
                    if (recBookingPax != null)
                        this.getField(BookingAnswer.BOOKING_PAX_ID).moveFieldToThis(recBookingPax.getField(BookingPax.ID));
                    if (this.getField(BookingAnswer.BOOKING_PAX_ID).isNull())
                        this.getField(BookingAnswer.BOOKING_PAX_ID).setValue(0);
                    this.getField(BookingAnswer.MODULE_ID).moveFieldToThis(fldTourHeaderID);
                    this.getField(BookingAnswer.TOUR_HEADER_OPTION_ID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.ID));
                    if (recTourHeaderOption.getField(TourHeaderOption.USE_TOUR_HEADER_OPTION_ID).getValue() != 0)
                        this.getField(BookingAnswer.TOUR_HEADER_OPTION_ID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.USE_TOUR_HEADER_OPTION_ID));
                    this.getField(BookingAnswer.TOUR_OR_OPTION).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.TOUR_OR_OPTION));
                    this.getField(BookingAnswer.TOUR_OR_OPTION_ID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.TOUR_OR_OPTION_ID));
                    this.getField(BookingAnswer.SEQUENCE).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.SEQUENCE));
                    ((DateTimeField)this.getField(BookingAnswer.MODULE_START_DATE)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(BookingAnswer.DESCRIPTION).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.DESCRIPTION));
                    this.getField(BookingAnswer.ALWAYS_RESOLVE).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.ALWAYS_RESOLVE));
                    this.getField(BookingAnswer.ASK_FOR_ANSWER).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.ASK_FOR_ANSWER));
                    if (this.getField(BookingAnswer.ASK_FOR_ANSWER).getState())
                    {   // If there are answers to be resolved, let the booking know
                        if (fldAskForAnswer == null)
                            fldAskForAnswer = recBooking.getField(Booking.ASK_FOR_ANSWER);
                        fldAskForAnswer.setState(true);
                        if (this.getField(BookingAnswer.ALWAYS_RESOLVE).getState())
                                recBooking.getField(Booking.ALWAYS_RESOLVE).setState(true);
                    }
                    if (recTourHeaderOption.getField(TourHeaderOption.DETAIL_OPTION_COUNT).getValue() > 0)
                        this.getField(BookingAnswer.DETAIL_OPTION_EXISTS).setState(true);
                    if (recTourHeaderOption.getField(TourHeaderOption.DETAIL_PRICE_COUNT).getValue() > 0)
                        this.getField(BookingAnswer.DETAIL_PRICE_EXISTS).setState(true);
                    if (recTourHeaderOption.getField(TourHeaderOption.DETAIL_AIR_HEADER_COUNT).getValue() > 0)
                        this.getField(BookingAnswer.DETAIL_AIR_HEADER_EXISTS).setState(true);
                    if (recTourHeaderOption.getField(TourHeaderOption.DETAIL_TOUR_DETAIL_COUNT).getValue() > 0)
                        this.getField(BookingAnswer.TOUR_DETAIL_EXISTS).setState(true);
                    boolean bSetSelected = bSelect;
                    if (this.getField(BookingAnswer.ASK_FOR_ANSWER).getState() == true)
                        bSetSelected = false; // Can't add this until user selects it.
                    this.getField(BookingAnswer.SELECTED).setState(bSetSelected);
                    this.getField(BookingAnswer.DETAIL_ADDED).setState(false);
                    try {
                        this.add();
                    } catch (DBException ex)    {
                        if (ex.getErrorCode() != DBConstants.DUPLICATE_KEY)   // If it was there already, use it.
                            throw ex;   // Else do the error.
                        int iOldOrder = this.getDefaultOrder();
                        this.setKeyArea(BookingAnswer.BOOKING_ID_KEY);
                        if (this.seek(DBConstants.EQUALS))
                        {   // Always - Use the old answer, but in this order (Note: Expensive, but rarely used)
                            VectorBuffer buffer = new VectorBuffer(null);
                            buffer.fieldsToBuffer(this);
                            this.remove();
                            this.addNew();
                            buffer.bufferToFields(this, DBConstants.DONT_DISPLAY, DBConstants.READ_MOVE);
                            this.add();
                            buffer.free();
                        }
                        this.setKeyArea(iOldOrder);
                    }
                    if (recTourHeaderOption.getField(TourHeaderOption.DETAIL_OPTION_COUNT).getValue() > 0)
                    {
                        iErrorCode = this.setupAnswerDetail(TourHeaderOption.OPTION, recTourHeaderOption.getField(TourHeaderOption.ID), fldTourHeaderID, recBooking, recBookingPax, dateStart, fldAskForAnswer, bSetSelected);
                        if (iErrorCode != DBConstants.NORMAL_RETURN)
                            return iErrorCode;
                    }
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recTourHeaderOption.free();
        }
        return iErrorCode;
    }
    /**
     * AddAnswerDetail Method.
     */
    public int addAnswerDetail(Booking recBooking, Tour recTour, BookingPax recBookingPax, BaseField fldTourModuleID, Date dateStart) throws DBException
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if (this.getField(BookingAnswer.SELECTED).getState() == false)
            return DBConstants.NORMAL_RETURN;   // Don't add if not selected for add.
        if (this.getField(BookingAnswer.DETAIL_ADDED).getState() == true)
            return DBConstants.NORMAL_RETURN;   // already added.
        this.edit();
        if (this.getField(BookingAnswer.DETAIL_AIR_HEADER_EXISTS).getState() == true)
        {
            if (m_recBookingAirHeader == null)
            {
                m_recBookingAirHeader = new BookingAirHeader(this.findRecordOwner());
                m_recBookingAirHeader.addDetailBehaviors(recBooking, recTour);
                m_recTourHeaderAirHeader = new TourHeaderAirHeader(this.findRecordOwner());
            }
            iErrorCode = m_recBookingAirHeader.setupAllDetail(m_recTourHeaderAirHeader, recBooking, recTour, recBookingPax.getField(BookingPax.ID), this.getField(BookingAnswer.TOUR_HEADER_OPTION_ID), fldTourModuleID, dateStart);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
                return iErrorCode;
        }
        if (this.getField(BookingAnswer.DETAIL_PRICE_EXISTS).getState() == true)
        {
            int iTourPricingType = recBooking.getTourPricingType(recTour, fldTourModuleID, dateStart);
            if ((iTourPricingType & PricingType.OPTION_PRICING) != 0)
            {       // Use the option pricing (otherwise, I use the component pricing or markup the cost for a price)
                if (m_recBookingLine == null)
                {
                    m_recBookingLine = new BookingLine(this.findRecordOwner());
                    m_recBookingLine.addDetailBehaviors(recBooking, recTour);
                    m_recTourHeaderPricing = new TourHeaderLine(this.findRecordOwner());
                }
                iErrorCode = m_recBookingLine.setupAllDetail(m_recTourHeaderPricing, recBooking, recTour, recBookingPax.getField(BookingPax.ID), this.getField(BookingAnswer.TOUR_HEADER_OPTION_ID), fldTourModuleID, dateStart);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    return iErrorCode;
            }
        }
        if (this.getField(BookingAnswer.TOUR_DETAIL_EXISTS).getState() == true)
        {
            if (m_recBookingDetail == null)
            {
                m_recBookingDetail = new BookingDetail(this.findRecordOwner());
                m_recBookingDetail.addDetailBehaviors(recBooking, recTour);
                m_recTourHeaderDetail = new TourHeaderDetail(this.findRecordOwner());
            }
            iErrorCode = m_recBookingDetail.setupAllDetail(m_recTourHeaderDetail, recBooking, recTour, recBookingPax.getField(BookingPax.ID), this.getField(BookingAnswer.TOUR_HEADER_OPTION_ID), fldTourModuleID, dateStart);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
                return iErrorCode;
        }
        this.getField(BookingAnswer.SELECTED).setState(true);  // Being careful.
        this.getField(BookingAnswer.DETAIL_ADDED).setState(true);
        this.set();
        return DBConstants.NORMAL_RETURN;
    }

}
