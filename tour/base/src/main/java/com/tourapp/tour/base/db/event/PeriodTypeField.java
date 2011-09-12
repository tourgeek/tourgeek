/**
 * @(#)PeriodTypeField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.db.event;

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

/**
 *  PeriodTypeField - Pay Frequency Field.
 */
public class PeriodTypeField extends StringPopupField
{
    public final static String BIWEEKLY = "B";
    public final static String DAILY = "D";
    public final static String MONTHLY = "M";
    public final static String QUARTERLY = "Q";
    public final static String SEMIMONTHLY = "S";
    public final static String WEEKLY = "W";
    public final static String YEARLY = "Y";
    /**
     * Default constructor.
     */
    public PeriodTypeField()
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
    public PeriodTypeField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
        if (iDataLength == DBConstants.DEFAULT_FIELD_LENGTH)
            m_iMaxLength = 1;
    }
    /**
     * Get the default value.
     * @return The default value.
     */
    public Object getDefault()
    {
        Object objDefault = super.getDefault();
        if (objDefault == null)
            objDefault = WEEKLY;
        return objDefault;
    }
    /**
     * Get the conversion Map.
     */
    public String[][] getPopupMap()
    {
        String string[][] = {
            {DAILY, "Daily"}, 
            {WEEKLY, "Weekly"}, 
            {BIWEEKLY, "Bi-weekly"},
            {SEMIMONTHLY, "Semi-monthly"},
            {MONTHLY, "Monthly"},
            {QUARTERLY, "Quarterly"},
            {YEARLY, "Yearly"},
        };
        return string;
    }
    /**
     * Get the number of days for this code.
     */
    public double getDays()
    {
        String string = this.getString();
        if (DAILY.equalsIgnoreCase(string))
            return 1;
        if (WEEKLY.equalsIgnoreCase(string))
            return 7;
        if (BIWEEKLY.equalsIgnoreCase(string))
            return 14;
        return -1;  // No found
    }
    /**
     * Get the number of months for this code.
     */
    public double getMonths()
    {
        String string = this.getString();
        if (SEMIMONTHLY.equalsIgnoreCase(string))
            return 0.5;
        if (MONTHLY.equalsIgnoreCase(string))
            return 1;
        if (QUARTERLY.equalsIgnoreCase(string))
            return 3;
        if (YEARLY.equalsIgnoreCase(string))
            return 12;
        return -1;  // Not found
    }

}
