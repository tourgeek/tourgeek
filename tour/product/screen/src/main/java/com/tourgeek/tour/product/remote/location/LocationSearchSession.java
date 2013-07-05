/**
  * @(#)LocationSearchSession.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.remote.location;

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
import org.jbundle.base.remote.db.*;
import com.tourgeek.tour.base.db.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.base.remote.*;

/**
 *  LocationSearchSession - .
 */
public class LocationSearchSession extends Session
{
    /**
     * Default constructor.
     */
    public LocationSearchSession() throws RemoteException
    {
        super();
    }
    /**
     * LocationSearchSession Method.
     */
    public LocationSearchSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Map<String, Object> objectID)
    {
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Override this to open the main file for this session.
     */
    public Record openMainRecord()
    {
        return new Continent(this);     // Top-level search
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Region(this);
        new Country(this);
        new State(this);
        new City(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(Region.REGION_FILE).setKeyArea(Region.CONTINENT_ID_KEY);
        this.getRecord(Region.REGION_FILE).addListener(new StringSubFileFilter(null, Region.CONTINENT_ID, null, null, null, null));
        this.getRecord(Country.COUNTRY_FILE).setKeyArea(Country.REGION_ID_KEY);
        this.getRecord(Country.COUNTRY_FILE).addListener(new StringSubFileFilter(null, Country.REGION_ID, null, null, null, null));
        this.getRecord(State.STATE_FILE).setKeyArea(State.COUNTRY_ID_KEY);
        this.getRecord(State.STATE_FILE).addListener(new StringSubFileFilter(null, State.COUNTRY_ID, null, null, null, null));
        this.getRecord(City.CITY_FILE).setKeyArea(City.COUNTRY_ID_KEY);
        this.getRecord(City.CITY_FILE).addListener(new StringSubFileFilter(null, City.COUNTRY_ID, null, null, null, null));
    }
    /**
     * Override this to do an action sent from the client.
     * @param strCommand The command to execute
     * @param properties The properties for the command
     * @returns Object Return a Boolean.TRUE for success, Boolean.FALSE for failure.
     */
    public Object doRemoteCommand(String strCommand, Map<String,Object> properties) throws RemoteException, DBException
    {
        if (strCommand != null)
            if (strCommand.equalsIgnoreCase("requery"))
                if (properties != null)
        {
            Record record = null;
            String strRecordName = (String)properties.get("record");
            if (strRecordName != null)
                record = this.getRecord(strRecordName);
            if (record == null)
            {
                strRecordName = (String)properties.get("description");
                if (strRecordName != null)
                {
                    if (strRecordName.indexOf("All ") == 0)
                        strRecordName = strRecordName.substring(4);
                    if (strRecordName.length() > 3)
                        if (strRecordName.substring(strRecordName.length() - 3, strRecordName.length()).equals("ies"))
                            strRecordName = strRecordName.substring(strRecordName.length() - 2, strRecordName.length()) + 'y';
                    if (strRecordName.charAt(strRecordName.length() - 1) == 's')
                        strRecordName = strRecordName.substring(0, strRecordName.length() - 1);
                    record = this.getRecord(strRecordName);
                }
            }
            if (record == null)
                record = this.getMainRecord();
            StringSubFileFilter listener = (StringSubFileFilter)record.getListener("StringSubFileFilter");
            String strID = (String)properties.get("id");
            if (listener != null)
                listener.setFirst(strID);
            record.close();
            // Now, set the new parameters
            {
            }
            return Boolean.TRUE;    // Handled!
        }
        return super.doRemoteCommand(strCommand, properties);
    }

}
