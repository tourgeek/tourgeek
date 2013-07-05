/**
  * @(#)BookingAirHeaderScreenField.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.entry.detail.air;

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
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.thin.base.screen.*;

/**
 *  BookingAirHeaderScreenField - .
 */
public class BookingAirHeaderScreenField extends SCannedBox
{
    /**
     * Default constructor.
     */
    public BookingAirHeaderScreenField()
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
    public BookingAirHeaderScreenField(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        BaseField fldBookingAirHeaderID = (BaseField)this.getConverter().getField();
        Record recBookingAir = fldBookingAirHeaderID.getRecord();
        Record recBookingAirHeader = ((ReferenceField)this.getField()).makeReferenceRecord();
        
        String strOptionID = recBookingAir.getField(BookingAir.BOOKING_ID).toString();
        ScreenLocation itsLocation = null;
        
        Task task = null;
        if (recBookingAirHeader.getRecordOwner() != null)
            task = recBookingAirHeader.getRecordOwner().getTask();
        if (task == null)
            task = BaseApplet.getSharedInstance();
        App application = task.getApplication();
        
        BasePanel parent = Screen.makeWindow(application);
        int iDocMode = ScreenConstants.SELECT_MODE;
        boolean bCloneThisQuery = true;
        boolean bReadCurrentRecord = true;
        boolean bUseBaseTable = true;
        boolean bLinkGridToQuery = true;
        Map<String,Object> properties = new Hashtable<String,Object>();
        properties.put(DBParams.HEADER_OBJECT_ID, strOptionID);
        recBookingAirHeader.makeScreen(itsLocation, parent, iDocMode, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, properties);
        
        return true;    // Handled
    }

}
