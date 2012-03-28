/**
 * @(#)UseTourHeaderOptionScreenField.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.screen;

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
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  UseTourHeaderOptionScreenField - .
 */
public class UseTourHeaderOptionScreenField extends SCannedBox
{
    /**
     * Default constructor.
     */
    public UseTourHeaderOptionScreenField()
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
    public UseTourHeaderOptionScreenField(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        Task task = null;
        if (m_record.getRecordOwner() != null)
            task = m_record.getRecordOwner().getTask();
        if (task == null)
            task = BaseApplet.getSharedInstance();
        Application application = (Application)task.getApplication();
        
        BasePanel parentScreen = Screen.makeWindow(application);
        
        BaseField fldTourHeaderOptionID = (BaseField)this.getConverter().getField();
        Record recTourHeaderOption = fldTourHeaderOptionID.getRecord();
        ReferenceField fldTourOrOptionID = (ReferenceField)recTourHeaderOption.getField(TourHeaderOption.TOUR_OR_OPTION_ID);
        Record recTourOrOption = fldTourOrOptionID.getReferenceRecord();
        try {
            recTourOrOption = (Record)recTourOrOption.clone();
            recTourOrOption.readSameRecord(fldTourOrOptionID.getReferenceRecord(), false, true);
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        GridScreen screen = new TourHeaderOptionGridScreen(recTourOrOption, null, null, parentScreen, null, ScreenConstants.SELECT_MODE | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        //x if (m_record.getScreen() == null)
            screen.setSelectQuery(m_record, false); // Since this record isn't linked to the screen, manually link it.
        return true;    // Handled
    }

}
