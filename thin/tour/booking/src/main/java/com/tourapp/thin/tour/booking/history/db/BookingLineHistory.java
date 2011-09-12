/**
 * @(#)BookingLineHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.history.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class BookingLineHistory extends com.tourapp.thin.tour.booking.detail.db.BookingLine
{

    public BookingLineHistory()
    {
        super();
    }
    public BookingLineHistory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_LINE_HISTORY_FILE = "BookingLineHistory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingLineHistory.BOOKING_LINE_HISTORY_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "history";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA | Constants.SERVER_REWRITES | Constants.DONT_LOG_TRX;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 10, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleStartDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Description", 60, null, null);
        //field = new FieldInfo(this, "ProductType", 15, null, null);
        field = new FieldInfo(this, "RemoteReferenceNo", 60, null, null);
        field = new FieldInfo(this, "Sequence", 4, null, new Short((short)9999));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Price", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Quantity", 3, null, null);
        field.setDataClass(Short.class);
        //field = new FieldInfo(this, "Gross", 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, "Commissionable", 1, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "CommissionRate", 5, null, null);
        field.setDataClass(Float.class);
        //field = new FieldInfo(this, "Commission", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "Net", 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, "PricingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PayAt", 1, null, null);
        field = new FieldInfo(this, "PaxGroups", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "HistoryDate", 25, null, null);
        field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea.addKeyField("HistoryDate", Constants.ASCENDING);
    }
    /**
    * This is not an auto-counter record.
    */
    public boolean isAutoSequence()
    {
        return false;
    }

}
