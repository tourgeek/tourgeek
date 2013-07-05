/**
  * @(#)HotelPricing.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.product.hotel.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.product.base.db.*;
import com.tourgeek.model.tour.product.hotel.db.*;

public class HotelPricing extends ProductPricing
    implements HotelPricingModel
{
    private static final long serialVersionUID = 1L;


    public HotelPricing()
    {
        super();
    }
    public HotelPricing(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String HOTEL_PRICING_FILE = "HotelPricing";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? HotelPricing.HOTEL_PRICING_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, RATE_ID, 4, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CLASS_ID, 4, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, START_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PRODUCT_TERMS_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRICE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMISSIONABLE, 10, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COMMISSION_RATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, PAY_AT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, ROOM_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, ROOM_PRICE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DAYS_OF_WEEK, 6, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_ID, 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, USE_RATE_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, USE_CLASS_ID, 2, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, PRODUCT_ID_KEY);
        keyArea.addKeyField(PRODUCT_ID, Constants.ASCENDING);
        keyArea.addKeyField(PAX_CATEGORY_ID, Constants.ASCENDING);
        keyArea.addKeyField(RATE_ID, Constants.ASCENDING);
        keyArea.addKeyField(CLASS_ID, Constants.ASCENDING);
        keyArea.addKeyField(END_DATE, Constants.ASCENDING);
    }

}
