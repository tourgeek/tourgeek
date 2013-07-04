
package com.tourgeek.thin.tour.booking.inventory.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.booking.inventory.db.*;

public class InventoryDetail extends FieldList
    implements InventoryDetailModel
{
    private static final long serialVersionUID = 1L;


    public InventoryDetail()
    {
        super();
    }
    public InventoryDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String INVENTORY_DETAIL_FILE = "InventoryDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? InventoryDetail.INVENTORY_DETAIL_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
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
        field = new FieldInfo(this, INVENTORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TYPE, 10, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AMOUNT, 10, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, INVENTORY_ID_KEY);
        keyArea.addKeyField(INVENTORY_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_DETAIL_ID, Constants.ASCENDING);
        keyArea.addKeyField(TYPE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, BOOKING_DETAIL_ID_KEY);
        keyArea.addKeyField(BOOKING_DETAIL_ID, Constants.ASCENDING);
        keyArea.addKeyField(INVENTORY_ID, Constants.ASCENDING);
        keyArea.addKeyField(TYPE, Constants.ASCENDING);
    }

}
