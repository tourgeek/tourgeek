/*
 *  @(#)Currencys.
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class Currencys extends FieldList
{
    public static final String DESCRIPTION = "Description";
    public static final String CURRENCY_CODE = "CurrencyCode";

    public Currencys()
    {
        super();
    }
    public Currencys(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String CURRENCYS_FILE = "Currencys";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Currencys.CURRENCYS_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.SHARED_DATA | Constants.LOCALIZABLE;
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
        field = new FieldInfo(this, "Description", 25, null, null);
        field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        field = new FieldInfo(this, "LastRate", 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, "RateChangedDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "RateChangedBy", 16, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CostingRate", 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, "CostingChangedDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "CostingChangedBy", 16, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "RoundAt", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "IntegerDesc", 20, null, "Dollar");
        field = new FieldInfo(this, "FractionDesc", 20, null, null);
        field = new FieldInfo(this, "FractionAmount", 10, null, new Integer(100));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Sign", 3, null, "$");
        field = new FieldInfo(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "NaturalInteger", 20, null, null);
        field = new FieldInfo(this, "NaturalFraction", 20, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "CurrencyCode");
        keyArea.addKeyField("CurrencyCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
    }

}
