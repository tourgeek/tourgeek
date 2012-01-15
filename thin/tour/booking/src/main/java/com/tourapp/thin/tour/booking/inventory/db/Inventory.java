/**
 * @(#)Inventory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.inventory.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.inventory.db.*;

public class Inventory extends FieldList
    implements InventoryModel
{

    public Inventory()
    {
        super();
    }
    public Inventory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String INVENTORY_FILE = "Inventory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Inventory.INVENTORY_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.REMOTE | Constants.BASE_TABLE_CLASS | Constants.SHARED_TABLE | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 10, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "OtherID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InvDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Blocked", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Used", 3, null, new Short((short)0));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Available", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Oversell", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Closed", 10, null, null);
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "InvDate");
        keyArea.addKeyField("ProductID", Constants.ASCENDING);
        keyArea.addKeyField("ProductTypeID", Constants.ASCENDING);
        keyArea.addKeyField("InvDate", Constants.ASCENDING);
        keyArea.addKeyField("RateID", Constants.ASCENDING);
        keyArea.addKeyField("ClassID", Constants.ASCENDING);
        keyArea.addKeyField("OtherID", Constants.ASCENDING);
    }

}
