/**
  * @(#)TourHeaderAirHeaderScreenField.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.tour.detail.screen;

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
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.product.tour.detail.db.*;

/**
 *  TourHeaderAirHeaderScreenField - .
 */
public class TourHeaderAirHeaderScreenField extends SCannedBox
{
    /**
     * Default constructor.
     */
    public TourHeaderAirHeaderScreenField()
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
    public TourHeaderAirHeaderScreenField(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        BaseField fldTourSubID = (BaseField)this.getConverter().getField();
        Record recTourHeaderAir = fldTourSubID.getRecord();
        Record recTourHeaderAirHeader = ((ReferenceField)this.getField()).makeReferenceRecord();
        
        String strOptionID = recTourHeaderAir.getField(TourSub.TOUR_HEADER_OPTION_ID).toString();
        ScreenLocation itsLocation = null;
        
        Task task = null;
        if (recTourHeaderAirHeader.getRecordOwner() != null)
            task = recTourHeaderAirHeader.getRecordOwner().getTask();
        if (task == null)
            task = BaseApplet.getSharedInstance();
        Application application = (Application)task.getApplication();
        
        BasePanel parent = Screen.makeWindow(application);
        int iDocMode = ScreenConstants.SELECT_MODE;
        boolean bCloneThisQuery = true;
        boolean bReadCurrentRecord = true;
        boolean bUseBaseTable = true;
        boolean bLinkGridToQuery = true;
        Map<String,Object> properties = new Hashtable<String,Object>();
        properties.put(DBParams.HEADER_OBJECT_ID, strOptionID);
        recTourHeaderAirHeader.makeScreen(itsLocation, parent, iDocMode, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, properties);
        
        return true;    // Handled
    }

}
