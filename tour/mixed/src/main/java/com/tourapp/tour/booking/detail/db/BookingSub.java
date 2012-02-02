/**
 * @(#)BookingSub.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingSub - Customer Sale Sub-File.
 */
public class BookingSub extends VirtualRecord
     implements BookingSubModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kBookingID = kVirtualRecordLastField + 1;
    public static final int kBookingPaxID = kBookingID + 1;
    public static final int kModuleID = kBookingPaxID + 1;
    public static final int kTourHeaderDetailID = kModuleID + 1;
    public static final int kTourHeaderOptionID = kTourHeaderDetailID + 1;
    public static final int kModuleStartDate = kTourHeaderOptionID + 1;
    public static final int kDescription = kModuleStartDate + 1;
    public static final int kProductType = kDescription + 1;
    public static final int kRemoteReferenceNo = kProductType + 1;
    public static final int kBookingSubLastField = kRemoteReferenceNo;
    public static final int kBookingSubFields = kRemoteReferenceNo - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDetailAccessKey = kIDKey + 1;
    public static final int kBookingKey = kDetailAccessKey + 1;
    public static final int kBookingSubLastKey = kBookingKey;
    public static final int kBookingSubKeys = kBookingKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingSub()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingSub(RecordOwner screen)
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

    public static final String kBookingSubFile = null;  // Screen field
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
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBookingPaxID)
            field = new BookingPaxField(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kModuleID)
            field = new TourHeaderField(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHeaderDetailID)
            field = new TourHeaderDetailField(this, "TourHeaderDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHeaderOptionID)
            field = new TourHeaderOptionField(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kModuleStartDate)
            field = new DateTimeField(this, "ModuleStartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 60, null, null);
        if (iFieldSeq == kProductType)
        {
            field = new StringField(this, "ProductType", 15, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kRemoteReferenceNo)
            field = new StringField(this, "RemoteReferenceNo", 60, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingSubLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * AddDetailBehaviors Method.
     */
    public void addDetailBehaviors(Booking recBooking, Tour recTour)
    {
        FileListener subFileBeh = new SubFileFilter(recBooking, true);
        this.addListener(subFileBeh);
        this.setKeyArea(BookingSub.BOOKING_KEY);
        RecordOwner screen = this.getRecordOwner();
        if (screen != null) if (screen instanceof GridScreen)
        {
            FieldListener reSelect = new FieldReSelectHandler((GridScreen)screen);
            recBooking.getField(Booking.ID).addListener(reSelect);
        }
        this.addListener(new InitBookingDetailHandler(recBooking, recTour));
    }
    /**
     * InitBookingDetailFields Method.
     */
    public int initBookingDetailFields(Booking recBooking, Tour recTour, boolean bOnlyIfTargetIsNull)
    {
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingSub.BOOKING_ID).isNull()))
            this.getField(BookingSub.BOOKING_ID).moveFieldToThis(recBooking.getField(Booking.ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingSub.MODULE_ID).isNull()))
            this.getField(BookingSub.MODULE_ID).moveFieldToThis(recTour.getField(Tour.TOUR_HEADER_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Add the detail line items from this tour detail.
     * (ie., read through this tour detail and add the items to the current booking detail).
     */
    public int setupAllDetail(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldKeyOrder = recTourHeaderDetail.getDefaultOrder();
        recTourHeaderDetail.setKeyArea(TourHeaderDetail.TOUR_HEADER_OPTION_ID_KEY);
        SubFileFilter subFileBehavior = new SubFileFilter(fldQaID, TourHeaderDetail.TOUR_HEADER_OPTION_ID, null, null, null, null);
        recTourHeaderDetail.addListener(subFileBehavior);
        try   {
            Object[] rgbFieldsEnabled = this.setEnableFieldListeners(false);
            this.addNew();  // This is required to clear InitOnce listeners (I must clear ALL the fields)
            this.setEnableFieldListeners(rgbFieldsEnabled);
            this.addNew();  // This is required to do InitOnce listeners
            recTourHeaderDetail.close();
            while (recTourHeaderDetail.hasNext())
            {
                TourSub recTourHeaderDetail2 = (TourSub)recTourHeaderDetail.next();
                iErrorCode = this.setupDetail(recTourHeaderDetail2, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    continue;   // Error just means this record doesn't need to be written here
                Record recBookingDetail = this.getTable().getCurrentTable().getRecord();
                if (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
                    recBookingDetail.add();
                else if (recBookingDetail.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBookingDetail.set(); // It is possible that setdetail wrote the record
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recTourHeaderDetail.removeListener(subFileBehavior, true);
            recTourHeaderDetail.setKeyArea(iOldKeyOrder);
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Here is a single tour detail item.
     * Add it to the current booking line item detail.
     */
    public int setupDetail(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldOpenMode = this.getOpenMode();
        this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        boolean bListenerEnabledState = true;
        try   {
            if (recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) != null)
                bListenerEnabledState = recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).setEnabledListener(false); // Don't want to sense a broken tour detail (would cause the pricingtype to change)
            if (ModifyCodeField.ADD.equals(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_CODE).getString()))
            {
                this.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);    // This will set the record type
                this.addNew();
                this.setOpenMode(iOldOpenMode);
                BookingSub recBookingDetail = (BookingSub)this.getTable().getCurrentTable().getRecord();
                recBookingDetail.initBookingDetailFields(recBooking, recTour, false);
                recBookingDetail.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                iErrorCode = recBookingDetail.setDetailProductInfo(null, recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
            }
            else // if ((recTourHeaderDetail.getField(TourDetail.MODIFY_CODE) == ModifyCodeField.REPLACE) || (recTourDetail.getField(TourDetail.MODIFY_CODE) == ModifyCodeField.DELETE))
            {
                this.setKeyArea(BookingSub.DETAIL_ACCESS_KEY);
                this.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                this.getField(BookingSub.TOUR_HEADER_DETAIL_ID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_ID));
                boolean bSuccess = this.seek(DBConstants.EQUALS);
                if (bSuccess)
                {
                    BookingSub recBookingDetail = (BookingSub)this.getTable().getCurrentTable().getRecord();
                    if (ModifyCodeField.REPLACE.equals(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_CODE).getString()))
                    {
                        recBookingDetail.edit();
                        iErrorCode = recBookingDetail.setDetailProductInfo(null, recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
                    }
                    else // if (recTourHeaderDetail.getField(TourDetail.MODIFY_CODE) == ModifyCodeField.DELETE))
                    {
                        recBookingDetail.remove();
                    }
                }
                else
                {
                    if (ModifyCodeField.REPLACE.equals(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_CODE).getString()))
                    {   // Modify item not found, add.
                        this.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);    // This will set the record type
                        this.addNew();
                        this.setOpenMode(iOldOpenMode);
                        BookingSub recBookingDetail = (BookingSub)this.getTable().getCurrentTable().getRecord();
                        recBookingDetail.initBookingDetailFields(recBooking, recTour, false);
                        recBookingDetail.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                        iErrorCode = recBookingDetail.setDetailProductInfo(null, recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
                    }
                    else
                        this.setEditMode(DBConstants.EDIT_NONE);    // Make sure this (blank) record is not written
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return ex.getErrorCode();
        } finally {
            this.setOpenMode(iOldOpenMode);
            if (recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) != null)
                recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).setEnabledListener(bListenerEnabledState); // Don't want to sense a broken tour detail (would cause the pricingtype to change)
        }
        return iErrorCode;
    }
    /**
     * Set-up the current product info.
     * If properties are supplied, look in the properties for new values.
     * Else, if the target values are not already set, use the default values
     * supplied in the tour and booking records.
     */
    public int setDetailProductInfo(Map<String,Object> properties, TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        int iErrorCode = this.setDetailProductFields(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            if (properties != null)
                iErrorCode = this.setDetailProductProperties(properties);
        return iErrorCode;
    }
    /**
     * For this tour detail, move all the detail field to the current booking line item detail.
     */
    public int setDetailProductFields(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if (fldModID != null)
            this.getField(BookingSub.MODULE_ID).moveFieldToThis(fldModID);
        if (fldQaID != null)
            this.getField(BookingSub.TOUR_HEADER_OPTION_ID).moveFieldToThis(fldQaID);
        return iErrorCode;
    }
    /**
     * SetDetailProductProperties Method.
     */
    public int setDetailProductProperties(Map<String,Object> properties)
    {
        // Do something in the concrete classes
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Setup the detail key, given this tour detail record.
     */
    public int setupDetailKey(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        this.getField(BookingSub.BOOKING_ID).moveFieldToThis(recBooking.getField(Booking.ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        this.getField(BookingSub.BOOKING_PAX_ID).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if (fldPaxID != null) if (fldPaxID.getValue() != 0)
            this.getField(BookingSub.BOOKING_PAX_ID).moveFieldToThis(fldPaxID, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if (fldModID != null)
            this.getField(BookingSub.MODULE_ID).moveFieldToThis(fldModID, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        else
            this.getField(BookingSub.MODULE_ID).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if (recTourHeaderDetail == null)
            this.getField(BookingSub.TOUR_HEADER_DETAIL_ID).initField(DBConstants.DISPLAY);
        else
            this.getField(BookingSub.TOUR_HEADER_DETAIL_ID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((DateTimeField)this.getField(BookingSub.MODULE_START_DATE)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * DeleteAllDetail Method.
     */
    public int deleteAllDetail(Booking recBooking, BaseField fldBookingPaxID, BaseField fldTourModuleID, Date dateStart)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldKeyOrder = this.getDefaultOrder();
        this.setKeyArea(BookingSub.BOOKING_KEY);
        FileListener subFileBehavior = new SubFileFilter(recBooking, true);
        this.addListener(subFileBehavior);
        
        try   {
            this.close();
            while (this.hasNext())
            {
                BookingSub recBookingSub2 = (BookingSub)this.next();
                
                BaseField fldDetailModuleID = this.getField(BookingSub.MODULE_ID);
                Date dateDetailStart = ((DateTimeField)this.getField(BookingSub.MODULE_START_DATE)).getDateTime();
                if (this instanceof BookingDetail)
                    if (this.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.TOUR_ID)
                {
                    fldDetailModuleID = this.getField(BookingDetail.PRODUCT_ID);
                    dateDetailStart = ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();                    
                }
                if ((fldTourModuleID != null) && (!fldTourModuleID.equals(fldDetailModuleID)))
                    continue;
                if ((dateStart != null) && (!dateStart.equals(dateDetailStart)))
                    continue;
        
                recBookingSub2.edit();
                recBookingSub2.remove();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            this.removeListener(subFileBehavior, true);
            this.setKeyArea(iOldKeyOrder);
        }
        return iErrorCode;
    }
    /**
     * ChangeAllDetail Method.
     */
    public int changeAllDetail(Booking recBooking, BaseField fldBookingPaxID, BaseField fldTourModuleID, Date dateOriginal, Date dateStart)
    {
        if ((dateStart == null) || (dateStart.equals(dateOriginal)))
            return DBConstants.NORMAL_RETURN;   // No change required
        int iDaysChange = (int)((dateStart.getTime() - dateOriginal.getTime() + DBConstants.KMS_IN_A_DAY - 1) / DBConstants.KMS_IN_A_DAY);
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldKeyOrder = this.getDefaultOrder();
        this.setKeyArea(BookingDetail.DETAIL_ACCESS_KEY);
        SubFileFilter subFileBehavior = new SubFileFilter(recBooking.getField(Booking.ID), BookingSub.BOOKING_ID, fldBookingPaxID, BookingSub.BOOKING_PAX_ID, fldTourModuleID, BookingSub.MODULE_ID);
        this.addListener(subFileBehavior);
        try   {
            this.close();
            while (this.hasNext())
            {
                BookingSub recBookingSub2 = (BookingSub)this.next();
                if (((DateTimeField)recBookingSub2.getField(BookingSub.MODULE_START_DATE)).getDateTime().equals(dateOriginal))
                {
                    recBookingSub2.edit();
                    if (recBookingSub2 instanceof BookingDetail)
                    {
                        Calendar calDate = ((DateTimeField)recBookingSub2.getField(BookingDetail.DETAIL_DATE)).getCalendar();
                        calDate.add(Calendar.DATE, iDaysChange);
                        ((DateTimeField)recBookingSub2.getField(BookingDetail.DETAIL_DATE)).setCalendar(calDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    }
                    if (recBookingSub2 instanceof BookingAnswer)
                    {
                        recBookingSub2.getField(BookingAnswer.DETAIL_ADDED).setState(false);
                        if ((recBookingSub2.getField(BookingAnswer.ASK_FOR_ANSWER).getState() == false)
                            || (recBookingSub2.getField(BookingAnswer.ALWAYS_RESOLVE).getState() == true))
                        {   // If automatic or forced, must ask again
                            recBookingSub2.remove();
                            continue;
                        }
                    }
                    ((DateTimeField)recBookingSub2.getField(BookingSub.MODULE_START_DATE)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    recBookingSub2.set();
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            this.removeListener(subFileBehavior, true);
            this.setKeyArea(iOldKeyOrder);
        }
        return iErrorCode;
    }
    /**
     * Get the main (Booking) record for this detail record.
     * Note: This will only return the main record if it already exists.
     */
    public Booking getBooking(boolean bCreateAndReadCurrent)
    {
        ReferenceField fldBookingID = (ReferenceField)this.getField(BookingSub.BOOKING_ID);
        if (bCreateAndReadCurrent)
            return (Booking)fldBookingID.getReference();
        else
            return (Booking)fldBookingID.getReferenceRecord(null, false);
    }

}
