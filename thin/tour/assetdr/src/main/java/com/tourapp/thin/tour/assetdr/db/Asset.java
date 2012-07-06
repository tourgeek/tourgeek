/**
  * @(#)Asset.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.assetdr.db.*;

public class Asset extends FieldList
    implements AssetModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, 10, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, DESCRIPTION, 40, null, null);
        field = new FieldInfo(this, MANUFACTURER, 30, null, null);
        field = new FieldInfo(this, SERIAL_NUMBER, 50, null, null);
        field = new FieldInfo(this, INVENTORY_NO, 50, null, null);
        field = new FieldInfo(this, LOCATION, 30, null, null);
        field = new FieldInfo(this, PURCHASE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, SALE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, FS_DEPR_METHOD, 1, null, null);
        field = new FieldInfo(this, FS_LIFE, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FED_DEPR_METHOD, 1, null, null);
        field = new FieldInfo(this, FED_LIFE, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, STATE_DEPR_METHOD, 1, null, null);
        field = new FieldInfo(this, STATE_LIFE, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ASSET_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DEPR_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, EXPENSE_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "InventoryNo");
        keyArea.addKeyField("InventoryNo", Constants.ASCENDING);
    }

}
