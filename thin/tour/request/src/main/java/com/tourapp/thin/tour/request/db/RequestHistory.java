/**
 * @(#)RequestHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.request.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.request.db.*;
import com.tourapp.model.tour.request.db.*;

public class RequestHistory extends Request
    implements RequestHistoryModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, PROFILE_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PROFILE_CODE, 16, null, null);
        field = new FieldInfo(this, GENERIC_NAME, 30, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_1, 40, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_2, 40, null, null);
        field = new FieldInfo(this, CITY_OR_TOWN, 15, null, null);
        field = new FieldInfo(this, STATE_OR_REGION, 15, null, null);
        field = new FieldInfo(this, POSTAL_CODE, 10, null, null);
        field = new FieldInfo(this, COUNTRY, 15, null, null);
        field = new FieldInfo(this, ATTENTION, 24, null, null);
        field = new FieldInfo(this, EMAIL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, SEND_VIA_CODE, 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BUNDLE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BUNDLE_QTY, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, BROCHURE_TEXT, 255, null, null);
        field = new FieldInfo(this, PRINT_NOW, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, HIST_REPRINT, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, HIST_TIME_PRINTED, 25, null, null);
        field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProfileID");
        keyArea.addKeyField("ProfileID", Constants.ASCENDING);
        keyArea.addKeyField("HistTimePrinted", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "HistReprint");
        keyArea.addKeyField("HistReprint", Constants.ASCENDING);
        keyArea.addKeyField("HistTimePrinted", Constants.ASCENDING);
    }

}
