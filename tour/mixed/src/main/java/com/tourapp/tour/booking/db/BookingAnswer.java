/**
 * @(#)BookingAnswer.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.entry.tour.*;
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

    //public static final int kID = kID;
    //public static final int kBookingID = kBookingID;
    //public static final int kBookingPaxID = kBookingPaxID;
    //public static final int kModuleID = kModuleID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kModuleStartDate = kModuleStartDate;
    //public static final int kDescription = kDescription;
    public static final int kTourOrOption = kBookingSubLastField + 1;
    public static final int kTourOrOptionID = kTourOrOption + 1;
    public static final int kSequence = kTourOrOptionID + 1;
    public static final int kAskForAnswer = kSequence + 1;
    public static final int kAlwaysResolve = kAskForAnswer + 1;
    public static final int kDetailOptionExists = kAlwaysResolve + 1;
    public static final int kDetailPriceExists = kDetailOptionExists + 1;
    public static final int kDetailAirHeaderExists = kDetailPriceExists + 1;
    public static final int kTourDetailExists = kDetailAirHeaderExists + 1;
    public static final int kSelected = kTourDetailExists + 1;
    public static final int kDetailAdded = kSelected + 1;
    public static final int kBookingAnswerLastField = kDetailAdded;
    public static final int kBookingAnswerFields = kDetailAdded - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBookingIDKey = kIDKey + 1;
    public static final int kScanOrderKey = kBookingIDKey + 1;
    public static final int kBookingAnswerLastKey = kScanOrderKey;
    public static final int kBookingAnswerKeys = kScanOrderKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kBookingAnswerFile = "BookingAnswer";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingAnswerFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kBookingID)
        //  field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kBookingPaxID)
        //  field = new BookingPaxField(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kModuleID)
        //  field = new TourHeaderField(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTourHeaderOptionID)
        //  field = new TourHeaderOptionField(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourOrOption)
            field = new StringField(this, "TourOrOption", 1, null, null);
        if (iFieldSeq == kTourOrOptionID)
            field = new ReferenceField(this, "TourOrOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSequence)
            field = new ShortField(this, "Sequence", 4, null, null);
        //if (iFieldSeq == kModuleStartDate)
        //  field = new DateTimeField(this, "ModuleStartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDescription)
        //  field = new StringField(this, "Description", 60, null, null);
        if (iFieldSeq == kAskForAnswer)
            field = new BooleanField(this, "AskForAnswer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAlwaysResolve)
            field = new BooleanField(this, "AlwaysResolve", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailOptionExists)
            field = new BooleanField(this, "DetailOptionExists", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailPriceExists)
            field = new BooleanField(this, "DetailPriceExists", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailAirHeaderExists)
            field = new BooleanField(this, "DetailAirHeaderExists", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourDetailExists)
            field = new BooleanField(this, "TourDetailExists", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSelected)
            field = new BooleanField(this, "Selected", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kDetailAdded)
            field = new BooleanField(this, "DetailAdded", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingAnswerLastField)
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
        if (iKeyArea == kBookingIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "BookingID");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingPaxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleStartDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kTourOrOption, DBConstants.ASCENDING);
            keyArea.addKeyField(kTourOrOptionID, DBConstants.ASCENDING);
            keyArea.addKeyField(kSequence, DBConstants.ASCENDING);
        }
        if (iKeyArea == kScanOrderKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ScanOrder");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingPaxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleID, DBConstants.ASCENDING);
            keyArea.addKeyField(kModuleStartDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBookingAnswerLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingAnswerLastKey)
                keyArea = new EmptyKey(this);
        }
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
        TourHeaderOption recTourHeaderOption = new TourHeaderOption(Utility.getRecordOwner(this));
        try   {
            recTourHeaderOption.setKeyArea(TourHeaderOption.kTourOrOptionKey);
            recTourHeaderOption.addListener(new StringSubFileFilter(strTourOrOption, TourHeaderOption.kTourOrOption, fldTourOrOptionID.getData().toString(), TourHeaderOption.kTourOrOptionID, null, -1));
            recTourHeaderOption.close();
            while (recTourHeaderOption.hasNext())
            {
                recTourHeaderOption.next();
                BaseField fldPaxClass = null;
                if (recBookingPax != null)
                    if ((recBookingPax.getEditMode() == DBConstants.EDIT_CURRENT) || (recBookingPax.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                        fldPaxClass = recBookingPax.getField(BookingPax.kPaxCategoryID);
                if (recTourHeaderOption.isValid(recBooking, fldPaxClass, dateStart))
                {
                    this.addNew();
                    this.getField(BookingAnswer.kBookingID).moveFieldToThis(recBooking.getField(Booking.kID));
                    if (recBookingPax != null)
                        this.getField(BookingAnswer.kBookingPaxID).moveFieldToThis(recBookingPax.getField(BookingPax.kID));
                    if (this.getField(BookingAnswer.kBookingPaxID).isNull())
                        this.getField(BookingAnswer.kBookingPaxID).setValue(0);
                    this.getField(BookingAnswer.kModuleID).moveFieldToThis(fldTourHeaderID);
                    this.getField(BookingAnswer.kTourHeaderOptionID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kID));
                    if (recTourHeaderOption.getField(TourHeaderOption.kUseTourHeaderOptionID).getValue() != 0)
                        this.getField(BookingAnswer.kTourHeaderOptionID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kUseTourHeaderOptionID));
                    this.getField(BookingAnswer.kTourOrOption).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kTourOrOption));
                    this.getField(BookingAnswer.kTourOrOptionID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kTourOrOptionID));
                    this.getField(BookingAnswer.kSequence).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kSequence));
                    ((DateTimeField)this.getField(BookingAnswer.kModuleStartDate)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(BookingAnswer.kDescription).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kDescription));
                    this.getField(BookingAnswer.kAlwaysResolve).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kAlwaysResolve));
                    this.getField(BookingAnswer.kAskForAnswer).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kAskForAnswer));
                    if (this.getField(BookingAnswer.kAskForAnswer).getState())
                    {   // If there are answers to be resolved, let the booking know
                        if (fldAskForAnswer == null)
                            fldAskForAnswer = recBooking.getField(Booking.kAskForAnswer);
                        fldAskForAnswer.setState(true);
                        if (this.getField(BookingAnswer.kAlwaysResolve).getState())
                                recBooking.getField(Booking.kAlwaysResolve).setState(true);
                    }
                    if (recTourHeaderOption.getField(TourHeaderOption.kDetailOptionCount).getValue() > 0)
                        this.getField(BookingAnswer.kDetailOptionExists).setState(true);
                    if (recTourHeaderOption.getField(TourHeaderOption.kDetailPriceCount).getValue() > 0)
                        this.getField(BookingAnswer.kDetailPriceExists).setState(true);
                    if (recTourHeaderOption.getField(TourHeaderOption.kDetailAirHeaderCount).getValue() > 0)
                        this.getField(BookingAnswer.kDetailAirHeaderExists).setState(true);
                    if (recTourHeaderOption.getField(TourHeaderOption.kDetailTourDetailCount).getValue() > 0)
                        this.getField(BookingAnswer.kTourDetailExists).setState(true);
                    boolean bSetSelected = bSelect;
                    if (this.getField(BookingAnswer.kAskForAnswer).getState() == true)
                        bSetSelected = false; // Can't add this until user selects it.
                    this.getField(BookingAnswer.kSelected).setState(bSetSelected);
                    this.getField(BookingAnswer.kDetailAdded).setState(false);
                    try {
                        this.add();
                    } catch (DBException ex)    {
                        if (ex.getErrorCode() != DBConstants.DUPLICATE_KEY)   // If it was there already, use it.
                            throw ex;   // Else do the error.
                        int iOldOrder = this.getDefaultOrder();
                        this.setKeyArea(BookingAnswer.kBookingIDKey);
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
                    if (recTourHeaderOption.getField(TourHeaderOption.kDetailOptionCount).getValue() > 0)
                    {
                        iErrorCode = this.setupAnswerDetail(TourHeaderOption.OPTION, recTourHeaderOption.getField(TourHeaderOption.kID), fldTourHeaderID, recBooking, recBookingPax, dateStart, fldAskForAnswer, bSetSelected);
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
        if (this.getField(BookingAnswer.kSelected).getState() == false)
            return DBConstants.NORMAL_RETURN;   // Don't add if not selected for add.
        if (this.getField(BookingAnswer.kDetailAdded).getState() == true)
            return DBConstants.NORMAL_RETURN;   // already added.
        this.edit();
        if (this.getField(BookingAnswer.kDetailAirHeaderExists).getState() == true)
        {
            if (m_recBookingAirHeader == null)
            {
                m_recBookingAirHeader = new BookingAirHeader(Utility.getRecordOwner(this));
                m_recBookingAirHeader.addDetailBehaviors(recBooking, recTour);
                m_recTourHeaderAirHeader = new TourHeaderAirHeader(Utility.getRecordOwner(this));
            }
            iErrorCode = m_recBookingAirHeader.setupAllDetail(m_recTourHeaderAirHeader, recBooking, recTour, recBookingPax.getField(BookingPax.kID), this.getField(BookingAnswer.kTourHeaderOptionID), fldTourModuleID, dateStart);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
                return iErrorCode;
        }
        if (this.getField(BookingAnswer.kDetailPriceExists).getState() == true)
        {
            int iTourPricingType = recBooking.getTourPricingType(recTour, fldTourModuleID, dateStart);
            if ((iTourPricingType & PricingType.OPTION_PRICING) != 0)
            {       // Use the option pricing (otherwise, I use the component pricing or markup the cost for a price)
                if (m_recBookingLine == null)
                {
                    m_recBookingLine = new BookingLine(Utility.getRecordOwner(this));
                    m_recBookingLine.addDetailBehaviors(recBooking, recTour);
                    m_recTourHeaderPricing = new TourHeaderLine(Utility.getRecordOwner(this));
                }
                iErrorCode = m_recBookingLine.setupAllDetail(m_recTourHeaderPricing, recBooking, recTour, recBookingPax.getField(BookingPax.kID), this.getField(BookingAnswer.kTourHeaderOptionID), fldTourModuleID, dateStart);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    return iErrorCode;
            }
        }
        if (this.getField(BookingAnswer.kTourDetailExists).getState() == true)
        {
            if (m_recBookingDetail == null)
            {
                m_recBookingDetail = new BookingDetail(Utility.getRecordOwner(this));
                m_recBookingDetail.addDetailBehaviors(recBooking, recTour);
                m_recTourHeaderDetail = new TourHeaderDetail(Utility.getRecordOwner(this));
            }
            iErrorCode = m_recBookingDetail.setupAllDetail(m_recTourHeaderDetail, recBooking, recTour, recBookingPax.getField(BookingPax.kID), this.getField(BookingAnswer.kTourHeaderOptionID), fldTourModuleID, dateStart);
            if (iErrorCode != DBConstants.NORMAL_RETURN)
                return iErrorCode;
        }
        this.getField(BookingAnswer.kSelected).setState(true);  // Being careful.
        this.getField(BookingAnswer.kDetailAdded).setState(true);
        this.set();
        return DBConstants.NORMAL_RETURN;
    }

}
