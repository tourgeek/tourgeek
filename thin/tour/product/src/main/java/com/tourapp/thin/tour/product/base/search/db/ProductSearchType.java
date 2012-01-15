/**
 * @(#)ProductSearchType.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.search.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.base.search.db.*;

public class ProductSearchType extends FieldList
    implements ProductSearchTypeModel
{

    public ProductSearchType()
    {
        super();
    }
    public ProductSearchType(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PRODUCT_SEARCH_TYPE_FILE = "ProductSearchType";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? ProductSearchType.PRODUCT_SEARCH_TYPE_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.TABLE;
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
        field = new FieldInfo(this, "Description", 30, null, null);
        field = new FieldInfo(this, "Air", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Car", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Hotel", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Item", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Tour", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Transportation", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Cruise", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Land", 10, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
    }

}
