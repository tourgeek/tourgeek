
package com.tourgeek.thin.tour.product.tour.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.product.tour.db.*;

public class TourClass extends FieldList
    implements TourClassModel
{
    private static final long serialVersionUID = 1L;


    public TourClass()
    {
        super();
    }
    public TourClass(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_CLASS_FILE = "TourClass";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourClass.TOUR_CLASS_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL;
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
        field = new FieldInfo(this, CLASS_NAME, 15, null, null);
        field = new FieldInfo(this, BASED_CLASS_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DEPOSIT_DUE_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FINAL_PAY_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FINALIZE_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ORDER_COMP_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, CLOSED_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, FINAL_DOC_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, TICKET_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SP_1_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SP_1_DESC, 15, null, null);
        field = new FieldInfo(this, SP_2_DAYS, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SP_2_DESC, 15, null, null);
        field = new FieldInfo(this, ADD_DEPOSIT, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DEPOSIT_AMOUNT, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, CANCEL_CHARGE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MOD_BEFORE_CHARGE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MOD_AFTER_CHARGE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, UPGRADE_PROFILE_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AIR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AIR_CLASS_ID, 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, HOTEL_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, HOTEL_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LAND_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LAND_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PMC_CUTOFF, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, CAR_CLASS_ID, 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CAR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRANSPORTATION_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRANSPORTATION_CLASS_ID, 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CRUISE_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CRUISE_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ITEM_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ITEM_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INITIAL_BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INITIAL_TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MARKUP, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, NON_TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, CLASS_NAME_KEY);
        keyArea.addKeyField(CLASS_NAME, Constants.ASCENDING);
    }

}
