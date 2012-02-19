/**
 * @(#)PaxGroup.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.db.*;

public class PaxGroup extends FieldList
    implements PaxGroupModel
{
    private static final long serialVersionUID = 1L;


    public PaxGroup()
    {
        super();
    }
    public PaxGroup(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PAX_GROUP_FILE = "PaxGroup";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? PaxGroup.PAX_GROUP_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL;
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
        field = new FieldInfo(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GROUP_NO, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, GROUP_DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, PAX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "BookingID");
        keyArea.addKeyField("BookingID", Constants.ASCENDING);
        keyArea.addKeyField("GroupNo", Constants.ASCENDING);
        keyArea.addKeyField("PaxID", Constants.ASCENDING);
    }

}
