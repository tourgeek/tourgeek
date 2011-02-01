/**
 *  @(#)HotelPricing.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.hotel.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class HotelPricing extends FieldList
{

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
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "ProductID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "RateID", 4, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ClassID", 4, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "StartDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "EndDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Cost", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ProductTermsID", 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Price", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Commissionable", 10, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "CommissionRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "PayAt", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "RoomCost", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "RoomPrice", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DaysOfWeek", 6, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealPlanID", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "UseRateID", 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "UseClassID", 2, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ProductID");
        keyArea.addKeyField("ProductID", Constants.ASCENDING);
        keyArea.addKeyField("PaxCategoryID", Constants.ASCENDING);
        keyArea.addKeyField("RateID", Constants.ASCENDING);
        keyArea.addKeyField("ClassID", Constants.ASCENDING);
        keyArea.addKeyField("EndDate", Constants.ASCENDING);
    }

}
