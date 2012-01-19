/**
 * @(#)DisplayTypeField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.lookup;

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

/**
 *  DisplayTypeField - Type of display.
 */
public class DisplayTypeField extends ShortField
{
    public final static int BOOKING_DISPLAY = 0;
    public static final int TOUR_DISPLAY = 2;
    public static final int TOUR_HEADER_DISPLAY = 1;
    /**
     * Default constructor.
     */
    public DisplayTypeField()
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
    public DisplayTypeField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * Set this field to its initial value.
     */
    public int initField(boolean bDisplayOption)
    {
        return this.setString("0", bDisplayOption, DBConstants.INIT_MOVE);
    }
    /**
     * Convert this index to a display field.
     * @param index The index to convert.
     * @return The display string.
     */
    public String convertIndexToDisStr(int index)
    {
        String tempString = null;
        switch(index)
            {
                case DisplayTypeField.BOOKING_DISPLAY:
                    tempString = "Bookings";break;
                case DisplayTypeField.TOUR_HEADER_DISPLAY:
                    tempString = "Tour Templates";break;
                case DisplayTypeField.TOUR_DISPLAY:
                    tempString = "Tour Departures";break;
                default:
                    tempString = "";break;
            }
        return tempString;
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
        return new SPopupBox(itsLocation, targetScreen, this, iDisplayFieldDesc);
    }

}
