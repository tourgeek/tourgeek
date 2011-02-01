/**
 *  @(#)TestTable.
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel.thin;

import java.util.Date;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;


public class HotelScreenTable extends FieldList
{
    public HotelScreenTable()
    {
        super();
    }
    public HotelScreenTable(Object recordOwner)
    {
        this(); 
        this.init(recordOwner);
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "HotelID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Long.class);
        field = new FieldInfo(this, "HotelRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Long.class);
        field = new FieldInfo(this, "HotelClass", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Long.class);
        field = new FieldInfo(this, "StartDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "RoomPrice", 18, null, new Double(123456789.01));
        field.setDataClass(Double.class);
    }
 }
