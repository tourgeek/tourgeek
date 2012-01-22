/**
 * @(#)Period.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import java.util.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  Period - Periods.
 */
public class Period extends VirtualRecord
     implements PeriodModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kEndPeriod = kVirtualRecordLastField + 1;
    public static final int kPeriodClosed = kEndPeriod + 1;
    public static final int kPeriodLastField = kPeriodClosed;
    public static final int kPeriodFields = kPeriodClosed - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kEndPeriodKey = kIDKey + 1;
    public static final int kPeriodLastKey = kEndPeriodKey;
    public static final int kPeriodKeys = kEndPeriodKey - DBConstants.MAIN_KEY_FIELD + 1;
    protected Date m_lastEndDate = null;
    protected Date m_lastEndTargetDate = null;
    protected Date m_lastStartDate = null;
    protected Date m_lastStartTargetDate = null;
    /**
     * Default constructor.
     */
    public Period()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Period(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_lastEndDate = null;
        m_lastEndTargetDate = null;
        m_lastStartDate = null;
        m_lastStartTargetDate = null;
        super.init(screen);
    }

    public static final String kPeriodFile = "Period";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kPeriodFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Accounting period";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kEndPeriod)
        {
            field = new DateField(this, "EndPeriod", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kPeriodClosed)
            field = new DateField(this, "PeriodClosed", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPeriodLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kEndPeriodKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "EndPeriod");
            keyArea.addKeyField(kEndPeriod, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kPeriodLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kPeriodLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        this.setKeyArea(Period.kEndPeriodKey);  // Default order
        super.addMasterListeners();
    }
    /**
     * nding date for this target date.
     */
    public Date getPeriodEndDate(Date targetDate)
    {
        Date entryDate = null;
        if (targetDate != null)
        {
            if (targetDate.equals(m_lastEndTargetDate))
                return m_lastEndDate;
        }
        else
        {
            if (m_lastEndTargetDate == null) if (m_lastEndDate != null)
                return m_lastEndDate;
        }   
        m_lastEndTargetDate = targetDate;
        
        if ((targetDate == null) || (targetDate.getTime() == 0))
            targetDate = new Date();
        
        //      criteria = "[EndPeriod] >= #" & targetDate & "# And [PeriodClosed] = 0"
        try   {
            this.close();
        // Move this field as a virtual field
            DateField fldDate = new DateField(null, "EndPeriod", DBConstants.DEFAULT_FIELD_LENGTH, "Period end", null);
            fldDate.setDateTime(targetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            FileListener behavior1 = new CompareFileFilter(Period.kEndPeriod, fldDate, ">=", null, false);
            FileListener behavior2 = new CompareFileFilter(Period.kPeriodClosed, (String)null, "=", null, true);
            this.addListener(behavior1);
            this.addListener(behavior2);
            if (this.hasNext())
            {
                this.next();
                entryDate = ((DateField)this.getField(Period.kEndPeriod)).getDateTime();
            }
            else
            {   // Past last date, use last day of current month.
                entryDate = targetDate;
                Calendar calendar = DateTimeField.m_calendar;
                calendar.setTime(entryDate);
                int iMonth = calendar.get(Calendar.MONTH);
                if (iMonth == Calendar.DECEMBER)
                {
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                    calendar.add(Calendar.YEAR, +1);
                }
                else
                    calendar.add(Calendar.MONTH, +1);
                calendar.set(Calendar.DATE, 1);
                calendar.add(Calendar.DATE, -1);
                fldDate.setCalendar(calendar, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                entryDate = fldDate.getDateTime();
            }
            this.removeListener(behavior1, true);
            this.removeListener(behavior2, true);
            fldDate.free();
        } catch (DBException ex)    {
            entryDate = targetDate;
        }
        
        m_lastEndDate = entryDate;
        
        return entryDate;
    }
    /**
     * Get one day after the previous period (starting date of this period).
     */
    public Date getPeriodStartDate(Date targetDate)
    {
        Date entryDate = null;
        if (targetDate != null)
        {
            if (targetDate.equals(m_lastStartTargetDate))
                return m_lastStartDate;
        }
        else
        {
            if (m_lastStartTargetDate == null) if (m_lastStartDate != null)
                return m_lastStartDate;
        }   
        m_lastStartTargetDate = targetDate;
        
        Date endDate = this.getPeriodEndDate(targetDate);
        if ((targetDate == null) || (targetDate.getTime() == 0))
            targetDate = new Date();
        //  criteria = "[EndPeriod] < #" & endDate & "#"
        try   {
            // Make sure your get the largest one.
            this.getKeyArea(Period.kEndPeriodKey).getKeyField(DBConstants.MAIN_KEY_FIELD).setKeyOrder(DBConstants.DESCENDING);
            boolean bSuccess = this.seek("<");
            if (!bSuccess)
                entryDate = targetDate;
            else
            {
                Calendar calendar = ((DateTimeField)this.getField(Period.kEndPeriod)).getCalendar();
                calendar.add(Calendar.DATE, 1);
                entryDate = calendar.getTime();
            }
        } catch (DBException ex)    {
            entryDate = targetDate;
        } finally {
            this.getKeyArea(Period.kEndPeriodKey).getKeyField(DBConstants.MAIN_KEY_FIELD).setKeyOrder(DBConstants.ASCENDING);
        }
        
        m_lastStartDate = entryDate;
        
        return entryDate;
    }
    /**
     * Set the start and end fields to the current period.
     * @param record The record to set.
     * @param fsStartDateField The field to set to the start period date.
     * @param fsEndDateField The field to set to the end period date.
     * @param datePeriod The date to calc the period for (null for the current date).
     */
    public void setPeriodDefaults(Record record, int fsStartDateField, int fsEndDateField, Date datePeriod)
    {
        if (fsStartDateField != -1)
        {
            Date startDate = this.getPeriodStartDate(datePeriod);
            ((DateTimeField)record.getField(fsStartDateField)).setDate(startDate, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
        if (fsEndDateField != -1)
        {
            Date endDate = this.getPeriodEndDate(datePeriod);
            ((DateTimeField)record.getField(fsEndDateField)).setDate(endDate, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
    }

}
