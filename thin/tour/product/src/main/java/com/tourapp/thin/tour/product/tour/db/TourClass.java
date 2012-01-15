/**
 * @(#)TourClass.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.tour.db.*;

public class TourClass extends FieldList
    implements TourClassModel
{

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
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "ClassName", 15, null, null);
        field = new FieldInfo(this, "BasedClassID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DepositDueDays", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "FinalPayDays", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "FinalizeDays", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "OrderCompDays", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "ClosedDays", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "FinalDocDays", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "TicketDays", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Sp1Days", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Sp1Desc", 15, null, null);
        field = new FieldInfo(this, "Sp2Days", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Sp2Desc", 15, null, null);
        field = new FieldInfo(this, "AddDeposit", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DepositAmount", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "CancelCharge", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ModBeforeCharge", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ModAfterCharge", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "UpgradeProfileStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AirRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AirClassID", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "HotelRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "HotelClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "LandRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "LandClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PMCCutoff", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "CarClassID", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CarRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TransportationRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TransportationClassID", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CruiseRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CruiseClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ItemRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ItemClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InitialBookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InitialTourStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Markup", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "NonTourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ClassName");
        keyArea.addKeyField("ClassName", Constants.ASCENDING);
    }

}
