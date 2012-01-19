/**
 * @(#)UseTourHeaderOptionField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.db;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.product.tour.screen.*;

/**
 *  UseTourHeaderOptionField - Tour header option reference field.
This field has special lookup properties..
 */
public class UseTourHeaderOptionField extends TourHeaderOptionField
{
    /**
     * Default constructor.
     */
    public UseTourHeaderOptionField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public UseTourHeaderOptionField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        ScreenField sField = super.setupDefaultView(itsLocation, targetScreen, converter, iDisplayFieldDesc);
        for (int i = 0; ; i++)
        {
            ScreenComponent screenField = this.getComponent(i);
            if (screenField instanceof SSelectBox)
            {
                ((SSelectBox)screenField).free();
                Record record = this.getReferenceRecord();
                new SSelectBox(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, converter, ScreenConstants.DONT_DISPLAY_DESC, record)
                {
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
                        ReferenceField fldTourOrOptionID = (ReferenceField)recTourHeaderOption.getField(TourHeaderOption.kTourOrOptionID);
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
                };
                break;
            }
            if (screenField == null)
                break;  // Just being careful.
        }
        return sField;
    }

}
