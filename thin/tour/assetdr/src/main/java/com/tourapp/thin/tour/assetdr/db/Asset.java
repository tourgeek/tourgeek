/**
 *  @(#)Asset.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class Asset extends FieldList
{

    public Asset()
    {
        super();
    }
    public Asset(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String ASSET_FILE = "Asset";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Asset.ASSET_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "assetdr";
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
        field = new FieldInfo(this, "ID", 10, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Description", 40, null, null);
        field = new FieldInfo(this, "Manufacturer", 30, null, null);
        field = new FieldInfo(this, "SerialNumber", 50, null, null);
        field = new FieldInfo(this, "InventoryNo", 50, null, null);
        field = new FieldInfo(this, "Location", 30, null, null);
        field = new FieldInfo(this, "PurchaseDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "SaleDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "FSDeprMethod", 1, null, null);
        field = new FieldInfo(this, "FSLife", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "FedDeprMethod", 1, null, null);
        field = new FieldInfo(this, "FedLife", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "StateDeprMethod", 1, null, null);
        field = new FieldInfo(this, "StateLife", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AssetAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DeprAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ExpenseAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "InventoryNo");
        keyArea.addKeyField("InventoryNo", Constants.ASCENDING);
    }

}
