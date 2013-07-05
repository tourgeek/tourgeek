/**
  * @(#)StatementNumberField.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.db.finstmt;

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

/**
 *  StatementNumberField - Number to display on Financial Statement:
(T)o-date
(N)et change in period.
 */
public class StatementNumberField extends StringField
{
    public static final String BALANCE = "T";
    public static final String NET_CHANGE = "N";
    /**
     * Default constructor.
     */
    public StatementNumberField()
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
    public StatementNumberField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * Convert this index to a display field.
     * @param index The index to convert.
     * @return The display string.
     */
    public String convertIndexToDisStr(int index)
    {
        String tempString = null;
        switch(index)
        {
            case 0:
                tempString = "To-Date Balance";break;
            case 1:
                tempString = "Net Change";break;
            default:
                tempString = "";break;
        }
        return tempString;
    }
    /**
     * Convert this index to a string.
     * This method is usually overidden by popup fields.
     * @param index The index to convert.
     * @return The display string.
     */
    public String convertIndexToString(int index)
    {
        String tempString = null;
        if (index == 1)
            tempString = NET_CHANGE;
        else
            tempString = BALANCE;
        return tempString;
    }
    /**
     * Convert this string to it's index value.
     */
    public int convertStringToIndex(String tempString)
    {
        if (tempString.equalsIgnoreCase(NET_CHANGE))
            return 1;
        return 0; // "T" or default
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
        return createScreenComponent(ScreenModel.POPUP_BOX, itsLocation, targetScreen, this, iDisplayFieldDesc, properties);
    }

}
