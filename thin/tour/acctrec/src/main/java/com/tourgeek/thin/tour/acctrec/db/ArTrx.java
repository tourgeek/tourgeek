/**
  * @(#)ArTrx.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.acctrec.db;

import org.jbundle.model.db.*;
import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.genled.db.*;
import com.tourgeek.model.tour.acctrec.db.*;

public class ArTrx extends LinkTrx
    implements ArTrxModel
{
    private static final long serialVersionUID = 1L;


    public ArTrx()
    {
        super();
    }
    public ArTrx(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String AR_TRX_FILE = "ArTrx";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? ArTrx.AR_TRX_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctrec";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA;
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
        field = new FieldInfo(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRX_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, AMOUNT, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TRX_ENTRY, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, LINKED_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LINKED_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMENTS, 30, null, null);
        field = new FieldInfo(this, DEPARTURE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, LINKED_TRX_ID_KEY);
        keyArea.addKeyField(LINKED_TRX_ID, Constants.ASCENDING);
        keyArea.addKeyField(LINKED_TRX_DESC_ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BOOKING_ID_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
        keyArea.addKeyField(TRX_STATUS_ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TRX_STATUS_ID_KEY);
        keyArea.addKeyField(TRX_STATUS_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
    }
    /**
     * Add the booking detail behaviors.
     */
    public void addDetailBehaviors(Rec recBooking)
    {
        // Empty implementation
    }

}
