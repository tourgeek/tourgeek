/**
 * @(#)BookingAirHeaderField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.db;

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
import org.jbundle.thin.base.screen.*;

/**
 *  BookingAirHeaderField - .
 */
public class BookingAirHeaderField extends ReferenceField
{
    /**
     * Default constructor.
     */
    public BookingAirHeaderField()
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
    public BookingAirHeaderField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        return new BookingAirHeader(recordOwner);
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenField setupDefaultView(ScreenLocation itsLocation, BasePanel targetScreen, Converter converter, int iDisplayFieldDesc)
    {
        Record record = this.makeReferenceRecord();
        ScreenField sField = this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, -1, BookingAirHeader.kAirlineDesc, true, false);
        for (int i = 0; ; i++)
        {
            ScreenField screenField = this.getSFieldAt(i);
            if (screenField instanceof SSelectBox)
            {
                screenField.free();
                new SSelectBox(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, converter, ScreenConstants.DONT_DISPLAY_DESC, record)
                {
                    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
                    {
                        BaseField fldBookingAirHeaderID = (BaseField)this.getConverter().getField();
                        Record recBookingAir = fldBookingAirHeaderID.getRecord();
                        Record recBookingAirHeader = makeReferenceRecord();
        
                        String strOptionID = recBookingAir.getField(BookingAir.kBookingID).toString();
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
                };
            }
            if (screenField == null)
                break;  // Just being careful.
        }
        return sField;
    }

}
