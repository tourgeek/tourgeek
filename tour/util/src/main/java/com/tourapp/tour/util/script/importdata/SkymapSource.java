/**
 * @(#)SkymapSource.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.script.importdata;

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
import org.jbundle.app.program.script.data.importfix.base.*;
import java.io.*;
import com.tourapp.tour.base.db.*;

/**
 *  SkymapSource - .
 */
public class SkymapSource extends BaseSource
{
    protected String m_string = null;
    /**
     * Default constructor.
     */
    public SkymapSource()
    {
        super();
    }
    /**
     * SkymapSource Method.
     */
    public SkymapSource(LineNumberReader reader, Record record)
    {
        this();
        this.init(reader, record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Reader reader, Record record)
    {
        m_string = null;
        super.init(reader, record);
    }
    /**
     * Parse the next line and return false at EOF.
     */
    public boolean parseNextLine()
    {
        try {
            m_string = ((LineNumberReader)m_reader).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (m_string == null)
            return false;
        m_string = convertString(m_string);
        return true;
    }
    /**
     * MoveDataToRecord Method.
     */
    public Record moveDataToRecord()
    {
        try {
            m_record.addNew();
            
            StringTokenizer st = new StringTokenizer(m_string, ",");
            String strLatitude = null;
            if (st.hasMoreTokens())
                strLatitude = st.nextToken();
            String strLongitude = null;
            if (st.hasMoreTokens())
                strLongitude = st.nextToken();
            if (strLongitude != null)
                if (strLongitude.length() > 0)
                {   // Change the sign
                    if (strLongitude.startsWith("-"))
                        strLongitude = strLongitude.substring(1);
                    else
                        strLongitude = "-" + strLongitude;
                }
            String strGMTOffset = null;
            if (st.hasMoreTokens())
                strGMTOffset = st.nextToken();
            String strCountry = null;
            if (st.hasMoreTokens())
                strCountry = st.nextToken();
            String strState = null;
            if (st.hasMoreTokens())
                strState = st.nextToken();
            else
            {
                strState = strCountry;
                strCountry = null;
            }
            String strCity = null;
            if (st.hasMoreTokens())
                strCity = st.nextToken();
            else
            {
                strCity = strState;
                strState = null;
            }
            
            if ((strCity == null) || (strCity.length() == 0))
            {
                //
            }
            else
            {
                if (strCity.startsWith(" "))
                    strCity = strCity.substring(1);
            }
            
            m_record.getField(City.LATITUDE).setString(strLatitude);
            m_record.getField(City.LONGITUDE).setString(strLongitude);
            m_record.getField(City.GMT_OFFSET).setString(strGMTOffset);
            Record recCountry = BaseFixData.getRecordFromDescription(m_record.getField(City.COUNTRY_ID), strCountry);
            if (recCountry != null)
                m_record.getField(City.COUNTRY_ID).moveFieldToThis(recCountry.getCounterField());
            Record recState = BaseFixData.getRecordFromCode(m_record.getField(City.STATE_ID), strState, "StatePostalCode");
            if (recState != null)
                m_record.getField(City.STATE_ID).moveFieldToThis(recState.getCounterField());
            m_record.getField(City.DESCRIPTION).setString(strCity);
        } catch (DBException e) {
            e.printStackTrace();
        }
        
        return m_record;
    }
    /**
     * ConvertString Method.
     */
    public String convertString(String string)
    {
        int iColons = 0;
        for (int i = 1; i < string.length(); i++)
        {
            if ((string.charAt(i - 1) == ' ') && (string.charAt(i) == ' '))
            {   // Get rid of double spaces.
                string = string.substring(0, i) + string.substring(i + 1);
                i--;                
            }
            if (string.charAt(i) == ':')
                iColons++;
        }
        int iCount = 0;
        for (int i = 0; i < string.length(); i++)
        {
            if ((iCount < 3)
                && ((string.charAt(i) == ' ') || (string.charAt(i) == '\t')))
            {
                iCount++;
                string = string.substring(0, i) + ',' + string.substring(i + 1);
            }
            else if (string.charAt(i) == ':')
            {
                iCount++;
                string = string.substring(0, i) + ',' + string.substring(i + 1);                
                if (string.charAt(i + 1) == ' ')
                {
                    string = string.substring(0, i + 1) + string.substring(i + 2);
                    i--;
                }
                if (iCount == 4)
                    if (iColons < 2)
                {
                    string = string.substring(0, i + 1) + ',' + string.substring(i + 1);
                    i++;
                }
            }
        }
        return string;
    }

}
