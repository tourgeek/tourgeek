/**
 * @(#)OTASeatPreference.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.ota.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.base.ota.db.*;
import com.tourapp.model.tour.product.base.ota.db.*;

public class OTASeatPreference extends OTACode
    implements OTASeatPreferenceModel
{

    //public static final String ID = ID;
    //public static final String NAME = NAME;

    public OTASeatPreference()
    {
        super();
    }
    public OTASeatPreference(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String OTA_SEAT_PREFERENCE_FILE = "STP";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? OTASeatPreference.OTA_SEAT_PREFERENCE_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.TABLE | Constants.MAPPED;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Name", 60, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        super.setupKeys();
    }

}
