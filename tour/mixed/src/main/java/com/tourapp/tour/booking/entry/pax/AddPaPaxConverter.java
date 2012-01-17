/**
 * @(#)AddPaPaxConverter.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.pax;

import org.jbundle.base.field.BaseField;
import org.jbundle.base.field.ReferenceField;
import org.jbundle.base.field.convert.CommandConverter;
import org.jbundle.base.screen.model.BasePanel;
import org.jbundle.base.screen.model.Screen;
import org.jbundle.base.screen.model.ScreenField;
import org.jbundle.base.util.DBConstants;
import org.jbundle.base.util.ScreenConstants;
import org.jbundle.model.screen.ScreenParent;
import org.jbundle.thin.base.db.Converter;

import com.tourapp.tour.booking.db.BookingPax;
import com.tourapp.tour.profile.db.Profile;

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
BookingPax recBookingPax = (BookingPax)m_screenParent.getRecord(BookingPax.kBookingPaxFile);
Profile recProfile = (Profile)m_screenParent.getRecord(Profile.kProfileFile);
Profile recProfileDetail = (Profile)((ReferenceField)recBookingPax.getField(BookingPax.kProfileID)).getReferenceRecord();
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
