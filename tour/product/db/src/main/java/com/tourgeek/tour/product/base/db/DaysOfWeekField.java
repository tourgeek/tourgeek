/**
  * @(#)DaysOfWeekField.
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
import java.text.*;

/**
 *  DaysOfWeekField - Days of week - Each bit represents a day of the week.
0 = Sunday, etc.
If the bit is ZERO (0) then the day is valid.
If this field is null, all days are valid.
.
 */
public class DaysOfWeekField extends ShortField
{
    protected DateFormat m_df = DateFormat.getDateInstance(DateFormat.FULL);
    protected StringBuffer m_sb = new StringBuffer();
    /**
     * Default constructor.
     */
    public DaysOfWeekField()
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
    public DaysOfWeekField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
        ScreenComponent screenField = null;
        // Now get the first box on the calendar
        Converter.initGlobals();
        Calendar calendar = Converter.gCalendar;
        Date dateTarget = new Date();   // Pick a date, any date
        int iFirstDayOfWeek = calendar.getFirstDayOfWeek();
        calendar.setTime(dateTarget);
        int iTargetDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int iOffset = -Math.abs(iTargetDayOfWeek - iFirstDayOfWeek);
        calendar.add(Calendar.DATE, iOffset);   // This date is the first of the week
        
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        properties = new HashMap<String,Object>();
        properties.put(ScreenModel.DISPLAY_STRING, DBConstants.BLANK);
        createScreenComponent(ScreenModel.STATIC_STRING, itsLocation, targetScreen, null, 0, properties);
        String strDisplay = converter.getFieldDesc();
        if ((strDisplay != null) && (strDisplay.length() > 0))
        {
            ScreenLoc descLocation = targetScreen.getNextLocation(ScreenConstants.FIELD_DESC, ScreenConstants.DONT_SET_ANCHOR);
            properties.put(ScreenModel.DISPLAY_STRING, strDisplay);
            createScreenComponent(ScreenModel.STATIC_STRING, descLocation, targetScreen, null, 0, properties);
        }
        
        for (short i = 0; i <= 6; i++)    // 0 = First Day -> 6 = Last Day of Week
        {
            Converter dayConverter = (Converter)converter;
            String strWeek = this.getDateString(calendar.getTime(), DateFormat.DAY_OF_WEEK_FIELD);
            if (strWeek.length() > 0)
                dayConverter = new FieldDescConverter(dayConverter, strWeek.substring(0, 1));
            int sBitPosition = this.getBitDayOfWeek(calendar);
            dayConverter = new BitConverter(dayConverter, sBitPosition, false, true);
            itsLocation = targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST_CHECKBOX, ScreenConstants.DONT_SET_ANCHOR);
            screenField = dayConverter.setupDefaultView(itsLocation, targetScreen, iDisplayFieldDesc);
        
            calendar.add(Calendar.DATE, 1);
        }
        return screenField;
    }
    /**
     * Get the bit for this day of the week.
     * Bit 0 = Sunday, Bit 6 = Saturday.
     */
    public int getBitDayOfWeek(Calendar calendar)
    {
        int iDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (iDayOfWeek)
        {
            case Calendar.SUNDAY:
                return 0;
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
        }
        return iDayOfWeek;  // Never
    }
    /**
     * For the value in this field, is this date valid?.
     */
    public boolean isValidDate(Date date)
    {
        Converter.initGlobals();
        Calendar calendar = Converter.gCalendar;
        calendar.setTime(date);
        int iBit = this.getBitDayOfWeek(calendar);
        if (this.isNull())
            return true;    // All days valid
        int fieldValue = (int)this.getValue();
        if ((fieldValue & (1 << iBit)) == 0)
            return true;
        return false;
    }
    /**
     * Convert this data to a string (using the supplied format).
     * @param dateTarget The date to convert to a string.
     * @param iDateFormat The format for the date.
     * @return The date as a string.
     */
    public String getDateString(Date dateTarget, int iDateFormat)
    {
        m_sb.setLength(0);
        FieldPosition fieldPosition = new FieldPosition(iDateFormat);
        String string = null;
        string = m_df.format(dateTarget, m_sb, fieldPosition).toString();
        int iBegin = fieldPosition.getBeginIndex();
        int iEnd = fieldPosition.getEndIndex();
        string = string.substring(iBegin, iEnd);
        return string;
    }

}
