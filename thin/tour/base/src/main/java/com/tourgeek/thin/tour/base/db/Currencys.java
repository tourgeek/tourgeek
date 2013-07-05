/**
  * @(#)Currencys.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.base.db.*;

public class Currencys extends FieldList
    implements CurrencysModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, DESCRIPTION, 25, null, null);
        field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        field = new FieldInfo(this, LAST_RATE, 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, RATE_CHANGED_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, RATE_CHANGED_BY, 16, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COSTING_RATE, 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, COSTING_CHANGED_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, COSTING_CHANGED_BY, 16, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ROUND_AT, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, INTEGER_DESC, 20, null, "Dollar");
        field = new FieldInfo(this, FRACTION_DESC, 20, null, null);
        field = new FieldInfo(this, FRACTION_AMOUNT, 10, null, new Integer(100));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SIGN, 3, null, "$");
        field = new FieldInfo(this, LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, NATURAL_INTEGER, 20, null, null);
        field = new FieldInfo(this, NATURAL_FRACTION, 20, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, CURRENCY_CODE_KEY);
        keyArea.addKeyField(CURRENCY_CODE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DESCRIPTION_KEY);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
    }

}
