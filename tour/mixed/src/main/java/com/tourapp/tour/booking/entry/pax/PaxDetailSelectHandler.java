/**
 * @(#)PaxDetailSelectHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.profile.db.*;

/**
 *  PaxDetailSelectHandler - Move Info On Select.
 */
public class PaxDetailSelectHandler extends FileListener
{
    protected BookingPax m_recBookingPax = null;
    protected Profile m_recProfile = null;
    /**
     * Default constructor.
     */
    public PaxDetailSelectHandler()
    {
        super();
    }
    /**
     * PaxDetailSelectHandler Method.
     */
    public PaxDetailSelectHandler(BookingPax recBookingPax, Profile recProfile)
    {
        this();
        this.init(recBookingPax, recProfile);
    }
    /**
     * Initialize class fields.
     */
    public void init(BookingPax recBookingPax, Profile recProfile)
    {
        m_recBookingPax = null;
        m_recProfile = null;
        m_recBookingPax = recBookingPax;
        m_recProfile = recProfile;
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
            m_recBookingPax.movePaPaxDetail(m_recProfile);
        return iErrorCode;
    }

}
