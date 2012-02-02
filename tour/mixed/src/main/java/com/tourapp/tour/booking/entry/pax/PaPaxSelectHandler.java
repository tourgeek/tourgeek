/**
 * @(#)PaPaxSelectHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.pax;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  PaPaxSelectHandler - Add the profile detail records to this booking.
 */
public class PaPaxSelectHandler extends FileListener
{
    protected BookingPax m_recBookingPax = null;
    protected Record m_recProfile = null;
    protected Record m_recProfileDetail = null;
    /**
     * Default constructor.
     */
    public PaPaxSelectHandler()
    {
        super();
    }
    /**
     * PaPaxSelectHandler Method.
     */
    public PaPaxSelectHandler(BookingPax recBookingPax, Record recProfile, Record recProfileDetail)
    {
        this();
        this.init(recBookingPax, recProfile, recProfileDetail);
    }
    /**
     * Initialize class fields.
     */
    public void init(BookingPax recBookingPax, Record recProfile, Record recProfileDetail)
    {
        m_recBookingPax = null;
        m_recProfile = null;
        m_recProfileDetail = null;
        m_recBookingPax = recBookingPax;
        m_recProfile = recProfile;
        m_recProfileDetail = recProfileDetail;
        super.init(null);
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
        int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        if (iChangeType == DBConstants.SELECT_TYPE)
        {
            Object bookmarkCurrent = m_recProfile.getField(Profile.ID).getData();
            Object bookmarkMain = bookmarkCurrent;
            if (m_recProfile.getField(Profile.MAIN_PROFILE_ID).getValue() != 0)
                if (!m_recProfile.getField(Profile.MAIN_PROFILE_ID).equals(m_recProfile.getField(Profile.ID)))
            {   // If this belongs to a detail profile, then read the main profile!
                bookmarkMain = m_recProfile.getField(Profile.MAIN_PROFILE_ID).getData();
                try {
                    if (m_recProfile.setHandle(bookmarkMain, DBConstants.BOOKMARK_HANDLE) == null)
                    {       // If the main does not exist, make one up.
                        m_recProfile.addNew();
                        m_recProfile.getField(Profile.ID).setData(bookmarkCurrent);
                    }
                } catch (DBException ex) {
                    ex.printStackTrace();
                }
            }
            m_recBookingPax.addPaPax(m_recProfile, m_recProfileDetail);
            RecordOwner screen = m_recBookingPax.getRecordOwner();
            if (screen instanceof GridScreen)
               ((GridScreen)screen).reSelectRecords();      // Redisplay
            Booking recBooking = (Booking)((ReferenceField)m_recBookingPax.getField(BookingPax.BOOKING_ID)).getReferenceRecord();
            if (recBooking != null)
                if (recBooking.getEditMode() != DBConstants.EDIT_NONE)
                    if (recBooking.getField(Booking.PROFILE_ID).isNull())
            {   // Set the agency screen to the main profile.
                Profile recProfile = new Profile(this.getOwner().findRecordOwner());
                try {
                    recBooking.addSecondProfile(recProfile);    // Agency Secondary logic
                    recProfile.setHandle(bookmarkMain, DBConstants.BOOKMARK_HANDLE);
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    recProfile.free();  // This also releases all the listeners.
                }
            }
        }
        return iErrorCode;
    }

}
