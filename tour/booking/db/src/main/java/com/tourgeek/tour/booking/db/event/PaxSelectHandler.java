
package com.tourgeek.tour.booking.db.event;

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
import com.tourgeek.tour.profile.db.*;
import org.jbundle.base.screen.model.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.product.base.db.*;

/**
 *  PaxSelectHandler - Count the Passengers and types.
 */
public class PaxSelectHandler extends SubCountHandler
{
    protected Record m_recPaxCategory = null;
    protected short m_sOldPaxType;
    /**
     * Default constructor.
     */
    public PaxSelectHandler()
    {
        super();
    }
    /**
     * Count a sub-field.
     */
    public PaxSelectHandler(Record record, Record recPaxCategory)
    {
        this();
        this.init(record, recPaxCategory);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, Record recPaxCategory)
    {
        m_recPaxCategory = null;
        m_sOldPaxType = 0;
        m_recPaxCategory = recPaxCategory;
        super.init(null, record, Booking.PAX, null, null, true, true, false);
    }
    /**
     * Called when a new blank record is required for the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        super.doNewRecord(bDisplayOption);
        m_sOldPaxType = 0;
    }
    /**
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);      // Initialize the record
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        Record booking = m_fldMain.getRecord();
        boolean[] rgbEnabled = null;
        int iProxyChangeType = iChangeType;
        if (iChangeType == DBConstants.MOVE_NEXT_TYPE)
        {
            rgbEnabled = m_fldMain.setEnableListeners(false);
            if (m_bRecountOnSelect) if (m_bVerifyOnEOF)
                iProxyChangeType = DBConstants.AFTER_ADD_TYPE;
        }
        if (m_sOldPaxType != 0) if ((iProxyChangeType == DBConstants.AFTER_DELETE_TYPE) || (iProxyChangeType == DBConstants.AFTER_UPDATE_TYPE))
        {
            double dPaxTypeCount = ((NumberField)booking.getField((int)(booking.getFieldSeq(Booking.SINGLE_PAX) + m_sOldPaxType-1))).getValue();
            dPaxTypeCount--;
            iErrorCode = this.setFieldCount(booking.getFieldSeq(Booking.SINGLE_PAX) + m_sOldPaxType - 1, dPaxTypeCount, true);
        }
        if ((iProxyChangeType == DBConstants.AFTER_ADD_TYPE) || (iProxyChangeType == DBConstants.AFTER_UPDATE_TYPE))
        {
            if (this.getOwner().getField(BookingPax.PAX_CATEGORY_ID).compareTo(m_recPaxCategory.getField(PaxCategory.ID)) != 0)
            {
                m_recPaxCategory.getField(PaxCategory.ID).moveFieldToThis(this.getOwner().getField(BookingPax.PAX_CATEGORY_ID));
                try   {
                    int iOldOrder = m_recPaxCategory.getDefaultOrder();
                    m_recPaxCategory.setKeyArea(PaxCategory.ID_KEY);
                    m_recPaxCategory.seek(DBConstants.EQUALS);
                    m_recPaxCategory.setKeyArea(iOldOrder);
                } catch (DBException ex)   {
                    ex.printStackTrace();
                }
            }
            short sNewPaxType = (short)((NumberField)m_recPaxCategory.getField(PaxCategory.ROOM_TYPE)).getValue();   // This is a valid pax
            if (sNewPaxType != 0) 
            {
                double dPaxTypeCount = ((NumberField)booking.getField(booking.getFieldSeq(Booking.SINGLE_PAX) + sNewPaxType - 1)).getValue();
                dPaxTypeCount++;
                iErrorCode = this.setFieldCount(booking.getFieldSeq(Booking.SINGLE_PAX) + sNewPaxType - 1, dPaxTypeCount, true);
            }
        }
        if ((iChangeType == DBConstants.AFTER_DELETE_TYPE) || (iChangeType == DBConstants.AFTER_ADD_TYPE) || (iChangeType == DBConstants.AFTER_UPDATE_TYPE))
        {
            Booking recBooking = (Booking)((ReferenceField)((BookingPax)this.getOwner()).getField(BookingPax.BOOKING_ID)).getReference();
            Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
            BookingLine recBookingLine = new BookingLine(recBooking.findRecordOwner());
            recBookingLine.addDetailBehaviors(recBooking, recTour);
            iErrorCode = recBookingLine.deleteAllDetail(recBooking, null, null, null);
            iErrorCode = recBooking.addBookingDetailPricing(recTour, recBookingLine, null, null, true); // Recost all, fix all prices
            recBookingLine.free();
        }
        if (iChangeType ==  DBConstants.AFTER_REQUERY_TYPE)
        {
            if ((m_bRecountOnSelect) || (m_bResetOnBreak))
            {   // Special case - reset count event though the record is current
                iErrorCode = this.resetCount(); // Set in main file's field
            }
        }
        if (rgbEnabled != null)
            m_fldMain.setEnableListeners(rgbEnabled);
        return iErrorCode;
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);        // Initialize the record
        if (this.getOwner().getField(BookingPax.PAX_CATEGORY_ID).compareTo(m_recPaxCategory.getField(PaxCategory.ID)) != 0)
        {
            m_recPaxCategory.getField(PaxCategory.ID).moveFieldToThis(this.getOwner().getField(BookingPax.PAX_CATEGORY_ID));
            try   {
                int iOldOrder = m_recPaxCategory.getDefaultOrder();
                m_recPaxCategory.setKeyArea(PaxCategory.ID_KEY);
                m_recPaxCategory.seek(DBConstants.EQUALS);
                m_recPaxCategory.setKeyArea(iOldOrder);
            } catch (DBException ex)   {
                ex.printStackTrace();
            }
        }
        m_sOldPaxType = (short)m_recPaxCategory.getField(PaxCategory.ROOM_TYPE).getValue();        // This is a valid pax
    }
    /**
     * Reset the field count.
     */
    public int resetCount()
    {
        int iErrorCode = super.resetCount();
        Record booking = m_fldMain.getRecord();
        for (int iFieldSeq = booking.getFieldSeq(Booking.SINGLE_PAX); iFieldSeq <= booking.getFieldSeq(Booking.QUAD_PAX); iFieldSeq++)
        {    // Zero all the other fields
            iErrorCode = this.setFieldCount(iFieldSeq, 0, true);
        }
        return iErrorCode;
    }
    /**
     * set the field count.
     * @param bDisableListeners Disable the field listeners (used for grid count verification)
     * @return An error code.
     */
    public int setFieldCount(int iFieldSeq, double dFieldCount, boolean bDisableListeners)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if (m_fldMain != null)
        {
            boolean[] rgbEnabled = null;
            if (bDisableListeners)
                rgbEnabled = m_fldMain.getRecord().getField(iFieldSeq).setEnableListeners(false);
            int iOriginalValue = (int)m_fldMain.getRecord().getField(iFieldSeq).getValue();
            boolean bOriginalModified = m_fldMain.getRecord().getField(iFieldSeq).isModified();
            iErrorCode = m_fldMain.getRecord().getField(iFieldSeq).setValue(dFieldCount, true, DBConstants.INIT_MOVE); // Set in main file's field if the record is not current (init move will not trigger refresh and write).
            if (iOriginalValue == (int)m_fldMain.getRecord().getField(iFieldSeq).getValue())
                if (bOriginalModified == false)
                    m_fldMain.getRecord().getField(iFieldSeq).setModified(bOriginalModified);   // Make sure this didn't change if change was just null to 0.
            if (rgbEnabled != null)
                m_fldMain.getRecord().getField(iFieldSeq).setEnableListeners(rgbEnabled);
        }
        return iErrorCode;
    }

}
