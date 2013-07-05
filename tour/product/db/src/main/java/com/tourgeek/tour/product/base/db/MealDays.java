/**
  * @(#)MealDays.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.base.db;

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
 *  MealDays - Days for Meals.
 */
public class MealDays extends ShortField
{
    /**
     * Default constructor.
     */
    public MealDays()
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
    public MealDays(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
        properties = new HashMap<String,Object>();
        properties.put(ScreenModel.DISPLAY_STRING, DBConstants.BLANK);
        ScreenComponent screenField = createScreenComponent(ScreenModel.STATIC_STRING, targetScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), targetScreen, null, 0, properties);
        String strDisplay = converter.getFieldDesc();
        if ((strDisplay != null) && (strDisplay.length() > 0))
        {
            ScreenLoc descLocation = targetScreen.getNextLocation(ScreenConstants.FIELD_DESC, ScreenConstants.DONT_SET_ANCHOR);
            properties.put(ScreenModel.DISPLAY_STRING, strDisplay);
            createScreenComponent(ScreenModel.STATIC_STRING, descLocation, targetScreen, null, 0, properties);
        }
        
        for (short sBitPosition = 0; sBitPosition < 5; sBitPosition++)
        {
            Converter dayConverter = new FieldDescConverter((Converter)converter, "+" + Short.toString(sBitPosition));
            dayConverter = new BitConverter(dayConverter, sBitPosition, false, true);
            ScreenLoc location = targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST_CHECKBOX, ScreenConstants.DONT_SET_ANCHOR);
            screenField = dayConverter.setupDefaultView(location, targetScreen, ScreenConstants.DISPLAY_FIELD_DESC);
        }
        return screenField;
    }
    /**
     * MealOnThisDay Method.
     */
    public boolean mealOnThisDay(short day)
    {
        int mealDays = (int)this.getValue();
        if (mealDays == 0)
            return true;
        mealDays = mealDays >> day;     // Shift the other days out
        if (((mealDays >> 1) << 1) == mealDays)
            return true;    // bit set = meal on this day
        if (mealDays == 0)
            return true;    // No meals after this = meal on this day
        return false;   // bit = 0, meals after this = meal on this day
    }

}
