
package com.tourgeek.tour.base.db.event;

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
 *  CalcStartDateHandler - Calculate the start date given this period type, etc..
 */
public class CalcStartDateHandler extends FileListener
{
    protected DateTimeField m_fldDestDate = null;
    protected NumberField m_fldPeriodLength = null;
    protected PeriodTypeField m_fldPeriodType = null;
    protected DateTimeField m_fldSourceDate = null;
    protected DateTimeField m_fldStartDate = null;
    /**
     * Default constructor.
     */
    public CalcStartDateHandler()
    {
        super();
    }
    /**
     * CalcStartDateHandler Method.
     */
    public CalcStartDateHandler(BaseField fldDestDate, BaseField fldSourceDate, BaseField fldStartDate, BaseField fldPeriodType, BaseField fldPeriodLength)
    {
        this();
        this.init(fldDestDate, fldSourceDate, fldStartDate, fldPeriodType, fldPeriodLength);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldDestDate, BaseField fldSourceDate, BaseField fldStartDate, BaseField fldPeriodType, BaseField fldPeriodLength)
    {
        m_fldDestDate = null;
        m_fldPeriodLength = null;
        m_fldPeriodType = null;
        m_fldSourceDate = null;
        m_fldStartDate = null;
        m_fldStartDate = (DateTimeField)fldStartDate;
        m_fldPeriodType = (PeriodTypeField)fldPeriodType;
        m_fldPeriodLength = (NumberField)fldPeriodLength;
        m_fldDestDate = (DateTimeField)fldDestDate;
        m_fldSourceDate = (DateTimeField)fldSourceDate;
        super.init(null);
    }
    /**
     * Given this date, calculate the correct start date.
     * @param dateSrc The source date.
     * @return The correct start date.
     */
    public Date calcDate(Date dateSrc)
    {
        int iPeriodTypeDays = (int)m_fldPeriodType.getDays();
        int iPeriodTypeMonths = (int)m_fldPeriodType.getMonths();
        
        int iPeriodLength = (int)m_fldPeriodLength.getValue();
        
        int iPeriodType = Calendar.DATE;
        if (iPeriodTypeDays > 0)
        {
            iPeriodLength = iPeriodLength * iPeriodTypeDays;
        }
        else if (iPeriodTypeMonths > 0)
        {
            iPeriodLength = iPeriodLength * iPeriodTypeMonths;
            iPeriodType = Calendar.MONTH;
        }
        Calendar calSource = m_fldSourceDate.getCalendar();
        if (calSource == null)
            return null;
        Calendar calBreak = m_fldStartDate.getCalendar();
        if (calBreak == null)
            return null;
        if (calSource.before(calBreak))
            return null;
        for (int i = 0; i < 5000; i++)
        {
            Date dateBefore = calBreak.getTime();
            calBreak.add(iPeriodType, iPeriodLength);
            calBreak.getTime();     // Calc
            if (calSource.before(calBreak))
                return dateBefore;
        }
        return null;
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        
        Date dateSrc = m_fldSourceDate.getDateTime();
        
        Date dateDest = this.calcDate(dateSrc);
        
        m_fldDestDate.setDateTime(dateDest, bDisplayOption, DBConstants.SCREEN_MOVE);
    }

}
