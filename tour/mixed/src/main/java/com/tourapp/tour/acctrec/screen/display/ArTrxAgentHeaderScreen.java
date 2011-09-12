/**
 * @(#)ArTrxAgentHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.display;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ArTrxAgentHeaderScreen - .
 */
public class ArTrxAgentHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public ArTrxAgentHeaderScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public ArTrxAgentHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setEnabled(false);
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileCode).setEnabled(true);
        this.getRecord(ArTrxAgentScreenRecord.kArTrxAgentScreenRecordFile).getField(ArTrxAgentScreenRecord.kProfileID).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kGenericName).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseField fldProfile = this.getRecord(ArTrxAgentScreenRecord.kArTrxAgentScreenRecordFile).getField(ArTrxAgentScreenRecord.kProfileID);
        Record recProfile = this.getRecord(Profile.kProfileFile);
        fldProfile.setupTableLookup(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, fldProfile, ScreenConstants.DEFAULT_DISPLAY, recProfile, -1, -2, false, false);
        this.getRecord(ArTrxAgentScreenRecord.kArTrxAgentScreenRecordFile).getField(ArTrxAgentScreenRecord.kBalance).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
