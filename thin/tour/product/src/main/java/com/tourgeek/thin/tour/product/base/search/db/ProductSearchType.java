/**
  * @(#)ProductSearchType.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.product.base.search.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.product.base.search.db.*;

public class ProductSearchType extends FieldList
    implements ProductSearchTypeModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, AIR, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, CAR, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, HOTEL, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, ITEM, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TOUR, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TRANSPORTATION, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, CRUISE, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, LAND, 10, null, null);
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, DESCRIPTION_KEY);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
    }

}
