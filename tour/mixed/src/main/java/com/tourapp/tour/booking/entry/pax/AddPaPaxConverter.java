/**
 * @(#)AddPaPaxConverter.
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
 *  AddPaPaxConverter - Add these pax to the Profile and Detail.
 */
public class AddPaPaxConverter extends CommandConverter
{
    protected BasePanel m_screenParent = null;
    /**
     * Default constructor.
     */
    public AddPaPaxConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AddPaPaxConverter(Converter converter)
    {
        this();
        this.init(converter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        m_screenParent = null;
        super.init(field);
    }
    /**
     * AddComponent Method.
     */
    public void addComponent(Object sField)
    {
        super.addComponent(sField);
        m_screenParent = (BasePanel)((ScreenField)sField).getParentScreen();    // This screen
    }
    /**
     * User clicked to button to add the booking passengers to the profile.
     */
    public int doCommand(boolean bDisplayOption, int iMoveMode)
    {
        BookingPax recBookingPax = (BookingPax)m_screenParent.getRecord(BookingPax.BOOKING_PAX_FILE);
        Profile recProfile = (Profile)m_screenParent.getRecord(Profile.PROFILE_FILE);
        Profile recProfileDetail = (Profile)((ReferenceField)recBookingPax.getField(BookingPax.PROFILE_ID)).getReferenceRecord();
        recBookingPax.addPaxDetail(recBookingPax, recProfileDetail);
        //  Now create the maint screen for this new record
        if (recProfileDetail.getEditMode() == DBConstants.EDIT_CURRENT)
        {
            BasePanel parentScreen = Screen.makeWindow(m_screenParent.getTask().getApplication());
            ScreenParent screen = recProfileDetail.makeScreen(null, parentScreen, ScreenConstants.MAINT_MODE, true, true, true, true, null);
        }
        return DBConstants.NORMAL_RETURN;
    }

}
