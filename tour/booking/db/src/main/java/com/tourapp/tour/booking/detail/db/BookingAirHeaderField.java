/**
  * @(#)BookingAirHeaderField.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
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
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        Record record = this.makeReferenceRecord();
        ScreenComponent sField = this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, null, BookingAirHeader.AIRLINE_DESC, true, false);
        ScreenComponent sfTemp = BaseField.createScreenComponent(ScreenModel.CANNED_BOX, targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, null, ScreenConstants.DEFAULT_DISPLAY, properties);
        for (int i = 0; ; i++)
        {
            ScreenComponent screenField = this.getComponent(i);
            if (screenField == null)
                break;  // Just being careful.
            if (screenField.getClass().isAssignableFrom(sfTemp.getClass()))
            {
                screenField.free();
                properties = new HashMap<String,Object>();
                properties.put(ScreenModel.RECORD, record);
                properties.put(ScreenModel.COMMAND, ThinMenuConstants.LOOKUP);
                properties.put(ScreenModel.IMAGE, ThinMenuConstants.LOOKUP);
                screenField = createScreenComponent(BookingAirHeader.BOOKING_AIR_HEADER_SCREEN_FIELD_CLASS, targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, converter, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                break;
            }
        }
        sfTemp.free();
        return sField;
    }

}
