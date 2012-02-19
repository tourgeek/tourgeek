/**
 * @(#)ItemPricing.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.item.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.base.db.*;
import com.tourapp.model.tour.product.item.db.*;

public class ItemPricing extends ProductPricing
    implements ItemPricingModel
{
    private static final long serialVersionUID = 1L;


    public ItemPricing()
    {
        super();
    }
    public ItemPricing(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String ITEM_PRICING_FILE = "ItemPricing";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? ItemPricing.ITEM_PRICING_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.USER_DATA;
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
        field = new FieldInfo(this, PRODUCT_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, START_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PRODUCT_TERMS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRICE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMISSIONABLE, 10, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COMMISSION_RATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, PAY_AT, Constants.DEFAULT_FIELD_LENGTH, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProductID");
        keyArea.addKeyField("ProductID", Constants.ASCENDING);
        keyArea.addKeyField("RateID", Constants.ASCENDING);
        keyArea.addKeyField("ClassID", Constants.ASCENDING);
        keyArea.addKeyField("EndDate", Constants.ASCENDING);
    }

}
