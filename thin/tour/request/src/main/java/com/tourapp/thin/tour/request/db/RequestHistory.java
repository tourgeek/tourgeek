/**
 *  @(#)RequestHistory.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.request.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class RequestHistory extends com.tourapp.thin.tour.request.db.Request
{

    public RequestHistory()
    {
        super();
    }
    public RequestHistory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String REQUEST_HISTORY_FILE = "RequestHistory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? RequestHistory.REQUEST_HISTORY_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "request";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "ProfileID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileCode", 16, null, null);
        field = new FieldInfo(this, "GenericName", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Attention", 24, null, null);
        field = new FieldInfo(this, "Email", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "SendViaCode", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BundleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BundleQty", 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "BrochureText", 255, null, null);
        field = new FieldInfo(this, "PrintNow", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "HistReprint", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "HistTimePrinted", 25, null, null);
        field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProfileID");
        keyArea.addKeyField("ProfileID", Constants.ASCENDING);
        keyArea.addKeyField("HistTimePrinted", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "HistReprint");
        keyArea.addKeyField("HistReprint", Constants.ASCENDING);
        keyArea.addKeyField("HistTimePrinted", Constants.ASCENDING);
    }

}
