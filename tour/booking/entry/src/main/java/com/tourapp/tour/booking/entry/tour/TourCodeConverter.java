/**
  * @(#)TourCodeConverter.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.entry.tour;

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
 *  TourCodeConverter - Filter incoming tour code.
 */
public class TourCodeConverter extends FieldConverter
{
    protected BaseField m_fldTourDepDate = null;
    /**
     * Default constructor.
     */
    public TourCodeConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourCodeConverter(Converter converter)
    {
        this();
        this.init(converter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        m_fldTourDepDate = null;
        super.init(field);
    }
    /**
     * TourCodeConverter Method.
     */
    public TourCodeConverter(BaseField fldHdrTourCode, BaseField fldTourDepDate)
    {
        this();
        this.init(fldHdrTourCode, fldTourDepDate);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldHdrTourCode, BaseField fldTourDepDate)
    {
        m_fldTourDepDate = null;
        m_fldTourDepDate = fldTourDepDate;
        super.init(fldHdrTourCode);
    }
    /**
     * GetMaxLength Method.
     */
    public int getMaxLength()
    {
        return 10;      // 10 characters
    }
    /**
     * SetString Method.
     */
    public int setString( String strField, boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        String strTemp = strField;
        int iFirstNumber = -1;
        int iFirstSpace = strTemp.length();
        strTemp = strTemp.toUpperCase();
        for (int iIndex = 0; iIndex < strTemp.length(); iIndex++)
        {
            if (Character.isDigit(strTemp.charAt(iIndex)))
                if (iFirstNumber == -1)
                    iFirstNumber = iIndex;   // This is the first number
            if (Character.isSpaceChar(strTemp.charAt(iIndex)))
                if (iFirstSpace == strTemp.length())
                {
                    iFirstSpace = iIndex;  // This is the first space
                    break;
                }
        }
        int iCodeLength = Math.min(iFirstSpace, iFirstNumber) + 1 - 1;
        if (iFirstNumber == -1)
            iCodeLength = iFirstSpace;
        int iDateLength = strTemp.length() - iFirstNumber;
        if ((iFirstNumber != -1) & (iFirstSpace+1 >= 3) & (iFirstNumber+1 <= 5) &
             (iDateLength >= 4) & (iDateLength <= 5))
        { // Input has a date, get the date
            if (iCodeLength <= 4)    // Blank the code field to keep the tour header listener from kicking in
                iErrorCode = this.getNextConverter().setString(Constants.BLANK, bDisplayOption, iMoveMode);  // Tour Code
            int iMonth = Integer.parseInt(strTemp.substring(iFirstNumber, iFirstNumber + 2));
            int iDay = Integer.parseInt(strTemp.substring(iFirstNumber+2, iFirstNumber + 2 + 2));
            int iYearDigit = -1;
            if (iDateLength == 5)
                iYearDigit = Integer.parseInt(strTemp.substring(iFirstNumber+4, iFirstNumber+4+1));
            Calendar calTime = Calendar.getInstance();
            int iYear = calTime.get(Calendar.YEAR);   // Current year
            int iLastDigit = iYear - (iYear / 10 * 10);
            if (iYearDigit != -1) if (iYearDigit != iLastDigit)
            {
                iYear = (iYear / 10 * 10) + iYearDigit;
                if (iYear < calTime.get(Calendar.YEAR) - 3)
                    iYear = iYear + 10;
            }
            Calendar calTargetTimeTime = Calendar.getInstance();
            calTargetTimeTime.set(iYear, iMonth - 1, iDay, 0, 0, 0);
            if (iYearDigit == -1) // If they didn't enter a date
                if (calTargetTimeTime.getTime().getTime() < calTime.getTime().getTime())  // And I get a date from last year, bump to next year
                    calTargetTimeTime.set(iYear + 1, iMonth, iDay, 0, 0, 0);
            ((DateTimeField)m_fldTourDepDate).setCalendar(calTargetTimeTime, bDisplayOption, iMoveMode);  // Tour departure date
        }
        if (iCodeLength <= 4)
            iErrorCode = this.getNextConverter().setString(strTemp.substring(0, iCodeLength), bDisplayOption, iMoveMode);  // Tour Code
        return iErrorCode;
    }

}
