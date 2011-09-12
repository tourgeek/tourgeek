/**
 * @(#)ProductCategory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class ProductCategory extends FieldList
{

    public ProductCategory()
    {
        super();
    }
    public ProductCategory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PRODUCT_CATEGORY_FILE = "ProductCategory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? ProductCategory.PRODUCT_CATEGORY_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.SHARED_DATA | Constants.LOCALIZABLE;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Description", 30, null, null);
        field = new FieldInfo(this, "IncomeAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ArAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PPAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "XLChgAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "LandAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "UninvAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CostOUAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ApAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CurrOUAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AirAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PPTicAccountID", 7, null, null);
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
    }

}
