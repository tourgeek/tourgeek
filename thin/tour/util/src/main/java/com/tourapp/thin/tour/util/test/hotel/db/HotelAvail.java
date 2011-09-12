/**
 * @(#)HotelAvail.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.util.test.hotel.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class HotelAvail extends FieldList
{

    public HotelAvail()
    {
        super();
    }
    public HotelAvail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
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
        return Constants.REMOTE_MEMORY;
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
        field = new FieldInfo(this, "HotelCode", 20, null, null);
        field = new FieldInfo(this, "ChainCode", 30, null, null);
        field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        field = new FieldInfo(this, "AmountBeforeTax", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "AmountAfterTax", 18, null, null);
        field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
