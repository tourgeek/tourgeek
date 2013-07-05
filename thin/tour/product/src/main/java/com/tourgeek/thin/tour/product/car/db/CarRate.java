/**
  * @(#)CarRate.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.product.car.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.product.base.db.*;
import com.tourgeek.model.tour.product.car.db.*;

public class CarRate extends BaseRate
    implements CarRateModel
{
    private static final long serialVersionUID = 1L;


    public CarRate()
    {
        super();
    }
    public CarRate(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String CAR_RATE_FILE = "CarRate";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? CarRate.CAR_RATE_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.TABLE | Constants.SHARED_DATA | Constants.LOCALIZABLE;
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
        field = new FieldInfo(this, DESCRIPTION, 20, null, null);
        field = new FieldInfo(this, CODE, 2, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DESCRIPTION_KEY);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, CODE_KEY);
        keyArea.addKeyField(CODE, Constants.ASCENDING);
    }

}
