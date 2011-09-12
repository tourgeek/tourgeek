/**
 * @(#)MealDays.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.product.base.screen.*;

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
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenField setupDefaultView(ScreenLocation itsLocation, BasePanel targetScreen, Converter converter, int iDisplayFieldDesc)
    {
        ScreenField screenField = new SStaticString(targetScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), targetScreen, DBConstants.BLANK);
        String strDisplay = converter.getFieldDesc();
        if ((strDisplay != null) && (strDisplay.length() > 0))
        {
            ScreenLocation descLocation = targetScreen.getNextLocation(ScreenConstants.FIELD_DESC, ScreenConstants.DONT_SET_ANCHOR);
            SStaticString staticString = new SStaticString(descLocation, targetScreen, strDisplay);
        }
        
        for (short sBitPosition = 0; sBitPosition < 5; sBitPosition++)
        {
            Converter dayConverter = new FieldDescConverter(converter, "+" + Short.toString(sBitPosition));
            dayConverter = new BitConverter(dayConverter, sBitPosition, false, true);
            ScreenLocation location = targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST_CHECKBOX, ScreenConstants.DONT_SET_ANCHOR);
            screenField = (ScreenField)dayConverter.setupDefaultView(location, targetScreen, ScreenConstants.DISPLAY_FIELD_DESC);
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
