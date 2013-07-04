
package com.tourgeek.thin.tour.product.trans.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.product.base.db.*;
import com.tourgeek.model.tour.product.trans.db.*;

public class Transportation extends TransportProduct
    implements TransportationModel
{
    private static final long serialVersionUID = 1L;


    public Transportation()
    {
        super();
    }
    public Transportation(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TRANSPORTATION_FILE = "Transportation";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Transportation.TRANSPORTATION_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, CODE, 10, null, null);
        field = new FieldInfo(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, OPERATORS_CODE, 20, null, null);
        field = new FieldInfo(this, PRODUCT_CHAIN_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CITY_ID, 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ETD, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, ACK_DAYS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, COMMENTS, 32767, null, null);
        field = new FieldInfo(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, DESC_SORT, 10, null, null);
        //field = new FieldInfo(this, PRODUCT_TYPE, 15, null, null);
        //field = new FieldInfo(this, PRODUCT_COST, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, PRODUCT_COST_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        //field = new FieldInfo(this, DISPLAY_INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, INVENTORY_AVAILABILITY, 5, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        //field = new FieldInfo(this, CURRENCY_CODE_LOCAL, 3, null, null);
        //field = new FieldInfo(this, VENDOR_NAME, 30, null, null);
        //field = new FieldInfo(this, DISPLAY_COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, PP_COST, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, PP_COST_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, PRODUCT_PRICE_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, PP_PRICE_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, CITY_CODE, 3, null, null);
        field = new FieldInfo(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TO_CITY_CODE, 3, null, null);
        field = new FieldInfo(this, TRANS_REVERSE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MANUAL_FILE, 30, null, null);
        field = new FieldInfo(this, FREQUENCY, 15, null, null);
        field = new FieldInfo(this, DISTANCE, 15, null, null);
        field = new FieldInfo(this, HOURS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, BREAKFASTS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, LUNCHES, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DINNERS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DAYS_OF_WEEK, 6, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DISCONTINUED_ON, 12, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, CODE_KEY);
        keyArea.addKeyField(CODE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DESC_SORT_KEY);
        keyArea.addKeyField(DESC_SORT, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, VENDOR_ID_KEY);
        keyArea.addKeyField(VENDOR_ID, Constants.ASCENDING);
        keyArea.addKeyField(DESC_SORT, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, CITY_ID_KEY);
        keyArea.addKeyField(CITY_ID, Constants.ASCENDING);
        keyArea.addKeyField(DESC_SORT, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, CITY_CODE_KEY);
        keyArea.addKeyField(CITY_CODE, Constants.ASCENDING);
        keyArea.addKeyField(TO_CITY_CODE, Constants.ASCENDING);
    }

}
