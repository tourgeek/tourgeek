/**
  * @(#)Country.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.base.db.*;
import com.tourgeek.model.tour.base.db.*;

public class Country extends Location
    implements CountryModel
{
    private static final long serialVersionUID = 1L;


    public Country()
    {
        super();
    }
    public Country(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String COUNTRY_FILE = "Country";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Country.COUNTRY_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, NAME, 40, null, null);
        field = new FieldInfo(this, CODE, 2, null, null);
        field = new FieldInfo(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ICAO_COUNTRY_CODE, 3, null, null);
        field = new FieldInfo(this, FAX_PREFIX, 10, null, null);
        field = new FieldInfo(this, INTERNATIONAL_TAX, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DOMESTIC_TAX, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, ARRIVAL_TAX, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, GMT_OFFSET, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, REGION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DESCRIPTION, 9999, null, null);
        field = new FieldInfo(this, PICTURE, 9999, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, NAME_KEY);
        keyArea.addKeyField(NAME, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, CODE_KEY);
        keyArea.addKeyField(CODE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, REGION_ID_KEY);
        keyArea.addKeyField(REGION_ID, Constants.ASCENDING);
        keyArea.addKeyField(NAME, Constants.ASCENDING);
    }

}
