/**
 * @(#)InventoryDetail.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.inventory.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class InventoryDetail extends FieldList
{

    public InventoryDetail()
    {
        super();
    }
    public InventoryDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String INVENTORY_DETAIL_FILE = "InventoryDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? InventoryDetail.INVENTORY_DETAIL_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "InventoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Type", 10, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Amount", 10, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "InventoryID");
        keyArea.addKeyField("InventoryID", Constants.ASCENDING);
        keyArea.addKeyField("BookingDetailID", Constants.ASCENDING);
        keyArea.addKeyField("Type", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "BookingDetailID");
        keyArea.addKeyField("BookingDetailID", Constants.ASCENDING);
        keyArea.addKeyField("InventoryID", Constants.ASCENDING);
        keyArea.addKeyField("Type", Constants.ASCENDING);
    }

}
