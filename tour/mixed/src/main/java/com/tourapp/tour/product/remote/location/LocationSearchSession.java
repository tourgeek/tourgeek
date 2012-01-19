/**
 * @(#)LocationSearchSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.remote.location;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.remote.db.*;
import com.tourapp.tour.base.db.*;
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
    public LocationSearchSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Object objectID)
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
        this.getRecord(Region.kRegionFile).setKeyArea(Region.kContinentIDKey);
        this.getRecord(Region.kRegionFile).addListener(new StringSubFileFilter(null, Region.kContinentID, null, -1, null, -1));
        this.getRecord(Country.kCountryFile).setKeyArea(Country.kRegionIDKey);
        this.getRecord(Country.kCountryFile).addListener(new StringSubFileFilter(null, Country.kRegionID, null, -1, null, -1));
        this.getRecord(State.kStateFile).setKeyArea(State.kCountryIDKey);
        this.getRecord(State.kStateFile).addListener(new StringSubFileFilter(null, State.kCountryID, null, -1, null, -1));
        this.getRecord(City.kCityFile).setKeyArea(City.kCountryIDKey);
        this.getRecord(City.kCityFile).addListener(new StringSubFileFilter(null, City.kCountryID, null, -1, null, -1));
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
